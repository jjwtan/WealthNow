package com.fdm.wealthnow.service;

import java.util.List;

import com.fdm.wealthnow.dao.OrderDAO;

public class OrderManagementService {
	/*
	 * user submits order and the service will pass the details to the DAO for
	 * creation.
	 */
	public void createOpenOrder() {
		OrderDAO ord = new OrderDAO();

	}

	public void processOrder() {

	}

	public boolean validateOrderData() {
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
