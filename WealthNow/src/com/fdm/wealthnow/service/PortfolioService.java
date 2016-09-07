package com.fdm.wealthnow.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.StockHolding;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.dao.PortfolioDAO;
import com.fdm.wealthnow.util.DBUtil;

public class PortfolioService extends DBUtil {

	/*
	 * update for selling of stocks
	 */
	public void updateStockHolding(Integer user_id, Integer order_id, Integer sold_quantity) throws Exception {
		Connection connect = null;
		try {
			connect = getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PortfolioDAO pfDao = new PortfolioDAO();
		System.out.println("Updating StockHolding");
		pfDao.updateStockHolding(connect, order_id, sold_quantity);
		System.out.println("StockHolding updated");

		connect.commit();

	}

	/*
	 * calculate gains/losses for realised G&L page
	 */
	public double computeGainsAndLosses() throws Exception {

		Connection connect = null;
		try {
			connect = getConnection();
			connect.setAutoCommit(false);
		OrderDAO orderDao = new OrderDAO();
		Double netIncome = (double) 0;
		List<Order> listOfSoldGainsAndLosses = orderDao.getAllSoldOrderInDatabase(connect, 2, 301);

		for (Order newListofSoldGainsAndLosses : listOfSoldGainsAndLosses) {
			String symbolStock = newListofSoldGainsAndLosses.getStock_symbol();
			Double openingPrice = newListofSoldGainsAndLosses.getLimit_price();
			System.out.println(openingPrice);
			Double closingPrice = newListofSoldGainsAndLosses.getClosing_price();
			System.out.println(closingPrice);
			netIncome = openingPrice - closingPrice;
			System.out.println("Your net profit/loss for " + symbolStock + ":$" + netIncome);
		}
		connect.commit();
		return netIncome;
		
		} catch (Exception e) {
			connect.rollback();
		} finally {
			if (connect != null)
				connect.close();
		}
		return (Double) null;
		

	}

	/*
	 * retrieve sold orders for realized G&L page
	 */

	public List getAllSoldOrders(Integer user_id, Integer order_id) throws Exception {

		Connection connect = null;
		try {
			connect = getConnection();
			connect.setAutoCommit(false);

		OrderDAO orderDao = new OrderDAO();
		List<Order> listOfSoldOrders = orderDao.getAllSoldOrderInDatabase(connect, user_id, order_id);
		System.out.println(listOfSoldOrders);
		connect.commit();

		return listOfSoldOrders;
		
	} catch (Exception e) {
		connect.rollback();
	} finally {
		if (connect != null)
			connect.close();
	}
	return null;
	}

	/*
	 * user request to view portfolio
	 */
	public List getPortfolioInStockHolding(Integer user_id) throws Exception {
		Connection connect = null;
		try {
			connect = getConnection();
			connect.setAutoCommit(false);

			PortfolioDAO pfDAO = new PortfolioDAO();

			List<StockHolding> stockHoldingList = pfDAO.getStockHoldingInDataBase(user_id, connect);

			// for (StockHolding newStockHoldingList : stockHoldingList) {
			// int stockHoldingID = newStockHoldingList.getStockholding_id();
			// int userID = newStockHoldingList.getUser_id();
			// int orderID = newStockHoldingList.getOrder_id();
			// String symbolStock = newStockHoldingList.getStock_symbol();
			// int quantity = newStockHoldingList.getRemaining_quantity();
			// Double price = newStockHoldingList.getPurchase_price();
			//
			// System.out.println("Here are the Stock Holdings for User:" +
			// user_id
			// + "\n");
			// System.out.println("Order ID:" + orderID + ", Stock Symbol:" +
			// symbolStock + ", Quantity Remaining:"
			// + quantity + ", Price:" + price);

			// }
			connect.commit();

			return stockHoldingList;
		} catch (Exception e) {
			connect.rollback();
		} finally {
			if (connect != null)
				connect.close();
		}
		return null;
	}

	public Integer createStockHoldings(Connection connect, Integer order_id, Integer user_id) throws Exception {
		//Connection connect = null;
		System.out.println("connection  is - "+connect);
		System.out.println("order id to be pulled from processed order - "+order_id);
		Integer stockholding_id = null;
		try {
			//connect = getConnection();
			connect.setAutoCommit(false);

			PortfolioDAO pfdao = new PortfolioDAO();
			OrderDAO ord = new OrderDAO();
			Order order = ord.getOrderFromProcessedOrder(connect, order_id);
			stockholding_id = getSequenceID("stockholdings_pk_seq");
			System.out.println("Stockholding id created : " + stockholding_id);
			System.out.println("Order@@@@@: " + order);
			pfdao.createStockHoldingInDatabase(connect, stockholding_id, user_id, order_id, order.getStock_symbol(),
					order.getQuantity(), order.getQuantity(), order.getClosing_price(),
					convertDateObjToString(order.getPlace_order_date()));

			connect.commit();
		} catch (Exception e) {
			System.out.println("simitachi stockholding..");
			e.printStackTrace();
			connect.rollback();
		} finally {
			if (connect != null)
				connect.close();
		}
		return stockholding_id;
	}

	public void deleteStockHoldings(Integer order_id) throws SQLException {

		Connection connect = null;
		try {
			connect = getConnection();
			connect.setAutoCommit(false);

			PortfolioDAO pfdao = new PortfolioDAO();
			StockHolding sh = pfdao.getStockholding(connect, order_id);
			if (sh.getPurchase_quantity().equals("0")) {
				pfdao.deleteStockHolding(connect, order_id);
				System.out.println("Stockholding with order id - " + order_id + " has been deleted.");
			} else {
				System.out.println("Remaining purchase is still available - " + sh.getRemaining_quantity());
			}

			connect.commit();

		} catch (Exception e) {
			connect.rollback();
		} finally {
			if (connect != null)
				connect.close();
		}
	}
	
	public StockHolding getStockholdingFromPortfolioService(Connection connect, Integer order_id){
		PortfolioDAO pdao = new PortfolioDAO();
		StockHolding sh = null;
		
		try {
			sh = pdao.getStockholding(connect, order_id);
			System.out.println(sh);
			System.out.println("fetch stockholding.. - " + sh.getOrder_id() + sh.getStock_symbol());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("stockholding object - " + sh);
			e.printStackTrace();
		}
		
		
		return sh;
	}

}
