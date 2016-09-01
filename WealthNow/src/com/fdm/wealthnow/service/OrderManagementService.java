package com.fdm.wealthnow.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.Stock;
import com.fdm.wealthnow.common.StockHolding;
import com.fdm.wealthnow.common.UserAccount;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.dao.PortfolioDAO;
import com.fdm.wealthnow.util.DBUtil;

public class OrderManagementService extends DBUtil {
	/*
	 * user submits order and the service will pass the details to the DAO for
	 * creation.
	 */

	public Integer createOpenOrder(Integer user_id, String currency_code, String order_type, Integer quantity,
			String stock_symbol, String price_type, String opening_order_date, Double limit_price, String term)
			throws Exception {

		Connection connect = null;
		
		try {
			connect = getConnection();
			connect.setAutoCommit(false);

			OrderDAO ord = new OrderDAO();
			System.out.println("Start Order Management Service(OMS) - createOpenOrder method");
			// create sequence id for order_id
			Integer order_id = getSequenceID("order_id_seq");

			boolean validate = validateOrderData(user_id, currency_code, order_type, quantity, stock_symbol, price_type,
					opening_order_date, limit_price, term);
			// validate data of the order, only true create data else print
			// failed
			if (validate == true) {
				ord.createOpenOrderInDatabase(connect, order_id, user_id, currency_code, order_type, quantity,
						stock_symbol, price_type, opening_order_date, limit_price, term, "OpenOrder");
			} else {
				System.out.println("Failed creating Open Order..");
			}
			connect.commit();

			System.out.println("End Of createOpenOrder..");

			return order_id;

		} catch (Exception e) {
			connect.rollback();
		} finally {
			if (connect != null)
				connect.close();
		}

		return null;
	}

	public void processOrder(Integer order_id, Double closing_price) {
		Connection connect = null;
		try {
			connect = getConnection();
			connect.setAutoCommit(false);
			System.out.println("connection  is - "+connect);

			OrderDAO ord = new OrderDAO();
			OrderManagementService oms = new OrderManagementService();
			Date date = new Date();
			Order order = ord.getOrderFromOpenOrder(order_id, connect);
			// format the date to string for create method
			System.out.println("process order method running...");
			ord.createProcessedOrderInDatabase(connect, order.getUser_id(), order.getOrder_id(),
					order.getCurrency_code(), order.getOrder_type().toString(), order.getQuantity(),
					order.getStock_symbol(), order.getPrice_type().toString(),
					convertDateObjToString(order.getPlace_order_date()), order.getLimit_price(),
					convertDateObjToString(date), "completed", closing_price);
			connect.commit();
			System.out.println("created processed order in database - " + order.getOrder_id());
			ord.deleteOpenOrderInDatabase(connect, order.getOrder_id());
			connect.commit();
			System.out.println("deleted open order in database - " + order.getOrder_id());
			System.out.println("Order has been processed... and needs to be updated in stockholdings.");

			oms.createStockHoldings(order.getOrder_id(), order.getUser_id());
			connect.commit();
		} catch (Exception e) {
			try {
				System.out.println("simitachi@ OMS");
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if (connect != null)
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

	public boolean validateOrderData(Integer user_id, String currency_code, String order_type, Integer quantity,
			String stock_symbol, String price_type, String opening_order_date, Double limit_price, String term) {

		Connection connect = null;
		try {
			connect = getConnection();
			connect.setAutoCommit(false);

			// check nulls for all parameters
			if (user_id == null || currency_code == null || order_type == null || quantity == null
					|| stock_symbol == null || price_type == null || opening_order_date == null || 
					limit_price == null) {
				System.out.println("something null");
				return false;
			}

			// check if quantity is not negative
			else if (quantity < 1) {
				System.out.println("quantity negative");
				return false;
			}

			// // check if stock symbol not more than 4 characters.
			// else if (stock_symbol.length() > 4) {
			// System.out.println("5");
			// return false;
			// }

			// check if limit price is not negative
			else if (price_type.equals("LT") || price_type.equals("SL")) {
				if (limit_price < 0) {
					System.out.println("limit price negative");
					return false;
				}
			}

		StockService ss = new StockService();
		// set up calls for checking balance.
		Stock stock = ss.getStockFromExchange(stock_symbol, InfoType.BASIC);
		UserAccountService uas = new UserAccountService();
		UserAccount ua = uas.getAccountBalance(user_id);

		// check if stocksymbol exists
		if (stock == null) {
			System.out.println("Stock is null.");
			return false;
		} else {
		// check if balance is enough for transaction
			Double mktprice = Double.parseDouble(stock.getMktPrice().toString());
			Double totalprice = mktprice * quantity;
			if (ua.getBalance() < totalprice) {
				System.out.println("Balance not enough.");
				return false;

			}
		}
		
		} catch (Exception e) {
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if (connect != null)
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return true;
	}

	public void createStockHoldings(Integer order_id, Integer user_id) throws Exception {
		Connection connect = null;
		try {
			connect = getConnection();
			connect.setAutoCommit(false);
		PortfolioService ps = new PortfolioService();
		// placeholder for the portfolio service
		System.out.println("Calls for portfolio Service - createStockHoldings...");
		Integer stockholding_id = ps.createStockHoldings(connect,order_id, user_id);
		System.out.println("created stockholdings - " + stockholding_id);
		}catch (Exception e) {
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if (connect != null)
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

	public void updateStockHoldings(Integer user_id, Integer order_id, Integer sold_quantity) throws Exception {
		PortfolioService ps = new PortfolioService();
		// placeholder for the portfolio service
		System.out.println("Calls for portfolio Service - updateStockHoldings");
		ps.updateStockHolding(user_id, order_id, sold_quantity);
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

}
