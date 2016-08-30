package com.fdm.wealthnow.service;

import java.util.List;

import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.util.DBUtil;

public class OrderManagementService extends DBUtil{
	/*
	 * user submits order and the service will pass the details to the DAO for
	 * creation.
	 */
	public void createOpenOrder(Integer user_id, String currency_code,
			String order_type, Integer quantity, String stock_symbol, String price_type,
			String opening_order_date, Double limit_price, String term) throws Exception {
		OrderDAO ord = new OrderDAO();
		System.out.println("Start Order Management Service(OMS) - createOpenOrder method");
		//create sequence id for order_id
		Integer order_id = getSequenceID("order_id_seq");
		
		ord.createOpenOrderInDatabase(order_id, user_id, currency_code, order_type, quantity, 
				stock_symbol, price_type, opening_order_date, limit_price, term, "OpenOrder");
		//validateOrderData();

	}

	public void processOrder() {

	}

	public static boolean validateOrderData(Integer user_id, String currency_code,
			String order_type, Integer quantity, String stock_symbol, String price_type,
			String opening_order_date, Double limit_price, String term) {
		//check nulls for all parameters
		
		//check if currency_code is not more than 3 characters.
		
		//check if order type is 
		
		//check if quantity is not negative
		
		//check if stock symbol not more than 4 characters.
		
		//check if limit price is not negative
		
		//check if term is good to cancel or good for the day
		
		
		return true;
	}

	public void createStockHoldings() {

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
