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
		System.out.println("process order method running...");
		ord.createProcessedOrderInDatabase(connect,order.getOrder_id(), order.getUser_id(), order.getCurrency_code(),
				order.getOrder_type(), order.getQuantity(), order.getStock_symbol(), order.getPrice_type(),
				convertDateObjToString(order.getPlace_order_date()), order.getLimit_price(), convertDateObjToString(order.getPlace_order_date()), "completed", closing_price);
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

	public void createStockHoldings(Integer order_id, Integer user_id) throws Exception {
		PortfolioDAO pfdao = new PortfolioDAO();
		OrderDAO ord = new OrderDAO();
		Order order = ord.getOrderFromProcessedOrder(connect, order_id);
		Integer stockholding_id = getSequenceID("stockholdings_pk_seq");
		System.out.println("Stockholding id created : " + stockholding_id);
		pfdao.createStockHoldingInDatabase(connect, stockholding_id, user_id, order_id, order.getStock_symbol(), 
				order.getQuantity(), order.getQuantity(), order.getLimit_price(), convertDateObjToString(order.getPlace_order_date()));

	}

	public void updateStockHoldings(Integer order_id, Integer sold_quantity) throws Exception {
		PortfolioDAO pfdao = new PortfolioDAO();
		System.out.println("OMS - updating stockholdings with order id - " + order_id +
				" and quantity " + sold_quantity);
		pfdao.updateStockHolding(connect, order_id, sold_quantity);
		System.out.println("updated stockholdings in OMS.");

	}

	public void deleteStockHoldings(Integer order_id) throws SQLException {
		PortfolioDAO pfdao = new PortfolioDAO();
		StockHolding sh = pfdao.getStockholding(connect, order_id);
		if(sh.getPurchase_quantity().equals("0")){
			pfdao.deleteStockHolding(connect, order_id);
			System.out.println("Stockholding with order id - " + order_id + " has been deleted.");
		}else{
			System.out.println("Remaining purchase is still available - " + sh.getRemaining_quantity());
		}
		
		
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
