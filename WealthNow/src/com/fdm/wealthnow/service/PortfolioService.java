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
	static Connection connect;

	public PortfolioService() {
		try {
			this.connect = getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * update for selling of stocks
	 */
	public void updateStockHolding(Integer user_id, Integer order_id, Integer sold_quantity) throws Exception {

		PortfolioDAO pfDao = new PortfolioDAO();
		System.out.println("Updating StockHolding");
		pfDao.updateStockHolding(connect, order_id, sold_quantity);
		System.out.println("StockHolding updated");
		
		connect.commit();
		
	}

	/*
	 * calculate gains/losses for realised G&L page
	 */
	public void computeGainsAndLosses() throws Exception {

		OrderDAO orderDao = new OrderDAO();
		Double netIncome = (double) 0;
		List<Order> listOfSoldGainsAndLosses = orderDao.getAllSoldOrderInDatabase(connect,1,152);
		for (Order newListofSoldGainsAndLosses : listOfSoldGainsAndLosses) {
			String symbolStock = newListofSoldGainsAndLosses.getStock_symbol();
			Double openingPrice = newListofSoldGainsAndLosses.getLimit_price();
			Double closingPrice = newListofSoldGainsAndLosses.getClosing_price();
			netIncome = openingPrice - closingPrice;
			System.out.println("Your net profit/loss for " + symbolStock + ":$" + netIncome);
		}
		connect.commit();
		

	}

	/*
	 * retrieve sold orders for realized G&L page
	 */
	

	
	public List getAllSoldOrders(Integer user_id,Integer order_id) throws Exception {

		OrderDAO orderDao = new OrderDAO();
		List<Order> listOfSoldOrders = orderDao.getAllSoldOrderInDatabase(connect,user_id,order_id);
		System.out.println(listOfSoldOrders);
		connect.commit();
		
		return listOfSoldOrders;
	}

	/*
	 * user request to view portfolio
	 */
	public List getPortfolioInStockHolding(Integer user_id) throws Exception {

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
		// System.out.println("Here are the Stock Holdings for User:" + user_id
		// + "\n");
		// System.out.println("Order ID:" + orderID + ", Stock Symbol:" +
		// symbolStock + ", Quantity Remaining:"
		// + quantity + ", Price:" + price);

		// }
		connect.commit();
		
		return stockHoldingList;
	}

	public void createStockHoldings(Integer order_id, Integer user_id) {
		PortfolioDAO pfdao = new PortfolioDAO();
		OrderDAO ord = new OrderDAO();
		Order order;
		try {
			order = ord.getOrderFromProcessedOrder(connect, order_id);
			Integer stockholding_id = getSequenceID("stockholdings_pk_seq");
			System.out.println("Stockholding id created : " + stockholding_id);
			pfdao.createStockHoldingInDatabase(connect, stockholding_id, user_id, order_id, order.getStock_symbol(),
					order.getQuantity(), order.getQuantity(), order.getLimit_price(),
					convertDateObjToString(order.getPlace_order_date()));
			
			connect.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

	}

	public void deleteStockHoldings(Integer order_id) throws SQLException {
		PortfolioDAO pfdao = new PortfolioDAO();
		StockHolding sh = pfdao.getStockholding(connect, order_id);
		if (sh.getPurchase_quantity().equals("0")) {
			pfdao.deleteStockHolding(connect, order_id);
			System.out.println("Stockholding with order id - " + order_id + " has been deleted.");
		} else {
			System.out.println("Remaining purchase is still available - " + sh.getRemaining_quantity());
		}
	
		connect.commit();
		
	}

}
