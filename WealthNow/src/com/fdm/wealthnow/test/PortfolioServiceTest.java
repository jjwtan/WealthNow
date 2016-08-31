package com.fdm.wealthnow.test;

import org.junit.Test;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.StockHolding;
import com.fdm.wealthnow.dao.AuthDAO;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.dao.PortfolioDAO;
import com.fdm.wealthnow.service.OrderManagementService;
import com.fdm.wealthnow.service.PortfolioService;
import com.fdm.wealthnow.util.DBUtil;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.util.List;

import org.junit.Before;

public class PortfolioServiceTest extends DBUtil {

	Connection connect;

	// ==============================================================================
	// Before test
	// ==============================================================================

	@Before
	public void setup() throws Exception {
		OrderDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
		connect = AuthDAO.getConnection();
		connect.setAutoCommit(false);
	}

	 @Test
	public void testResultsOfComputeGainsAndLosses() throws Exception {// TEST
																		// GET
																		// SOLD
																		// ORDERS
																		// AND
																		// COMPUTATION
		PortfolioService pfs = new PortfolioService();
		List<Order> soldList = pfs.getAllSoldOrders(1,152);
		for (Order newList : soldList) {
			Double soldPrice = newList.getClosing_price();
			Double boughtPrice = newList.getLimit_price();
			Double netPrice = soldPrice - boughtPrice;
			assertEquals(netPrice, new Double(100));// change the expected value
													// check with DB.
		}

		System.out.println("Test Completed: Results of compute gains and losses.");
	}

	
	public void testUpdateStockHolding() throws Exception {// test update and
															// get together

		PortfolioService pfs = new PortfolioService();
		System.out.println("creating new stockholding");
		Integer stockHolding_id = getSequenceID("stockholdings_pk_seq");
		pfs.createStockHoldings(176, 1);
		System.out.println("stockholding created");

		System.out.println("Updating stock via portfolio service method now");

		pfs.updateStockHolding(1, 176, 50);

		List<StockHolding> stockHoldingList = pfs.getPortfolioInStockHolding(1);
		for (StockHolding newStock : stockHoldingList) {
			Integer remain_qty = newStock.getRemaining_quantity();
			assertEquals(remain_qty, new Integer(50));
		}

		connect.close();
	}
}
