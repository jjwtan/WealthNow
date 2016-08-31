package com.fdm.wealthnow.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.StockHolding;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.dao.PortfolioDAO;
import com.fdm.wealthnow.util.DBUtil;

public class OrderManagementService extends DBUtil {
	/*
	 * user submits order and the service will pass the details to the DAO for
	 * creation.
	 */
	static Connection connect;

	public OrderManagementService() {
		try {
			connect = getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Integer createOpenOrder(Integer user_id, String currency_code, String order_type, Integer quantity,
			String stock_symbol, String price_type, String opening_order_date, Double limit_price, String term)
			throws Exception {
		OrderDAO ord = new OrderDAO();
		System.out.println("Start Order Management Service(OMS) - createOpenOrder method");
		// create sequence id for order_id
		Integer order_id = getSequenceID("order_id_seq");

		boolean validate = validateOrderData(user_id, currency_code, order_type, quantity, stock_symbol, price_type,
				opening_order_date, limit_price, term);
		// validate data of the order, only true create data else print failed
		if (validate == true) {
			ord.createOpenOrderInDatabase(connect, order_id, user_id, currency_code, order_type, quantity, stock_symbol,
					price_type, opening_order_date, limit_price, term, "OpenOrder");
		} else {
			System.out.println("Failed creating Open Order..");
		}

		System.out.println("End Of createOpenOrder..");
		return order_id;

	}

	public void processOrder(Integer order_id, Double closing_price) {
		OrderDAO ord = new OrderDAO();
		OrderManagementService oms = new OrderManagementService();
		Order order = ord.getOrderFromOpenOrder(order_id, connect);
		// format the date to string for create method
		System.out.println("process order method running...");
		ord.createProcessedOrderInDatabase(connect, order.getOrder_id(), order.getUser_id(), order.getCurrency_code(),
				order.getOrder_type().toString(), order.getQuantity(), order.getStock_symbol(),
				order.getPrice_type().toString(), convertDateObjToString(order.getPlace_order_date()),
				order.getLimit_price(), convertDateObjToString(order.getPlace_order_date()), "completed",
				closing_price);
		System.out.println("created processed order in database - " + order.getOrder_id());
		ord.deleteOpenOrderInDatabase(connect, order.getOrder_id());
		System.out.println("deleted open order in database - " + order.getOrder_id());
		System.out.println("Order has been processed... and needs to be updated in stockholdings.");
		try {
			oms.createStockHoldings(order.getOrder_id(), order.getUser_id());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean validateOrderData(Integer user_id, String currency_code, String order_type, Integer quantity,
			String stock_symbol, String price_type, String opening_order_date, Double limit_price, String term) {
		// check nulls for all parameters
		if (user_id == null || currency_code == null || order_type == null || quantity == null || stock_symbol == null
				|| price_type == null || opening_order_date == null || limit_price == null || term == null) {
			System.out.println("1");
			return false;
		}
		//
		// // check if currency_code is not more than 3 characters.
		// else if (currency_code.length() > 3) {
		// System.out.println("2");
		// return false;
		// }
		//
		// // check if price type is M, LT, SL
		// else if (!price_type.equals("M") && !price_type.equals("LT") &&
		// !price_type.equals("SL")) {
		// System.out.println(price_type);
		// return false;
		// }
		//
		// // check if only B and S for order type
		// else if (!order_type.equals("B") && !order_type.equals("S")) {
		// System.out.println(price_type);
		// return false;
		// }
		//
		// // check if quantity is not negative
		// else if (quantity < 1) {
		// System.out.println("4");
		// return false;
		// }
		//
		// // check if stock symbol not more than 4 characters.
		// else if (stock_symbol.length() > 4) {
		// System.out.println("5");
		// return false;
		// }
		//
		// // check if limit price is not negative
		// else if (limit_price < 0) {
		// System.out.println("6");
		// return false;
		// }
		//
		// // check if term is good to cancel or good for the day
		// else if (!term.equals("GC") && !term.equals("GD")) {
		// System.out.println("7");
		// return false;
		// }

		return true;
	}

	public void createStockHoldings(Integer order_id, Integer user_id) throws Exception {
		PortfolioService ps = new PortfolioService();
		// placeholder for the portfolio service
		System.out.println("Calls for portfolio Service - createStockHoldings...");
		ps.createStockHoldings(order_id, user_id);

	}

	public void updateStockHoldings(Integer user_id, Integer order_id, Integer sold_quantity) throws Exception {
		PortfolioService ps = new PortfolioService();
		// placeholder for the portfolio service
		System.out.println("Calls for portfolio Service - updateStockHoldings");
		ps.updateStockHolding(user_id, order_id, sold_quantity);
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

	}

}
