package com.fdm.wealthnow.service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.dao.PortfolioDAO;
import com.fdm.wealthnow.util.DBUtil;

public class OrderManagementService extends DBUtil {
	/*
	 * user submits order and the service will pass the details to the DAO for
	 * creation.
	 */
	static Connection connect;

	OrderManagementService() {
		try {
			connect = getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createOpenOrder(Integer user_id, String currency_code, String order_type, Integer quantity,
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

		System.out.println("End Order Management Service.");

	}

	public void processOrder(Integer order_id, Double closing_price) throws Exception {
		OrderDAO ord = new OrderDAO();
		Order order = ord.getOrderFromOpenOrder(order_id, connect);
		// format the date to string for create method
		SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyy");
		String place_order_date = format.format(order.getPlace_order_date());
		String order_completed_date = format.format(order.getOrder_completion_date());
		System.out.println("process order method running...");
		ord.createProcessedOrderInDatabase(connect,order.getOrder_id(), order.getUser_id(), order.getCurrency_code(),
				order.getOrder_type(), order.getQuantity(), order.getStock_symbol(), order.getPrice_type(),
				place_order_date, order.getLimit_price(), order_completed_date, "completed", closing_price);
		System.out.println("created processed order in database - " + order.getOrder_id());
		ord.deleteOpenOrderInDatabase(connect,order.getOrder_id());
		System.out.println("deleted open order in database - " + order.getOrder_id());
		System.out.println("Order has been processed...");
	}

	public static boolean validateOrderData(Integer user_id, String currency_code, String order_type, Integer quantity,
			String stock_symbol, String price_type, String opening_order_date, Double limit_price, String term) {
		// check nulls for all parameters
		if (user_id == null || currency_code == null || order_type == null || quantity == null || stock_symbol == null
				|| price_type == null || opening_order_date == null || limit_price == null || term == null) {
			return false;
		}

		// check if currency_code is not more than 3 characters.
		else if (currency_code.length() > 3) {
			return false;
		}

		// check if order type is M, LT, SL
		else if (!order_type.equals("M") || !order_type.equals("LT") || !order_type.equals("SL")) {
			return false;
		}

		// check if quantity is not negative
		else if (quantity < 1) {
			return false;
		}

		// check if stock symbol not more than 4 characters.
		else if (stock_symbol.length() > 4) {
			return false;
		}

		// check if limit price is not negative
		else if (limit_price < 1) {
			return false;
		}

		// check if term is good to cancel or good for the day
		else if (!term.equals("GC") || !term.equals("GD")) {
			return false;
		}

		return true;
	}

	public void createStockHoldings() {

		PortfolioDAO pfdao = new PortfolioDAO();

	}

	public void updateStockHoldings() {

	}

	public void deleteStockHoldings() {

	}

	/*
	 * system check to see if trade is executable - to be done at
	 * OrderProcessing
	 */
	public boolean checkLimitofOrder() {
		return false;
	}

	/*
	 * getting price from stock service(Jeremy) - to be done at OrderProcessing
	 */
	public float getPriceFromStockExchange() {
		return (Float) null;
	}

}
