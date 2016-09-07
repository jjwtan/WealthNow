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

import sun.font.CreatedFontTracker;

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

	 //@Test
	public void testResultsOfComputeGainsAndLosses() throws Exception {// TEST
																		// GET
																		// SOLD
																		// ORDERS
						OrderDAO ord = new OrderDAO();	
						ord.createProcessedOrderInDatabase(connect, 2, 303, "SGD", "B", 100, "HKL","M", "19 Sep 2014", 90.0, 
								"12 Oct 2015", "completed", 100.0, 150.1);
											connect.commit();							
		PortfolioService pfs = new PortfolioService();
		
		Double netIncome = pfs.computeGainsAndLosses();
		
			assertEquals(netIncome, new Double(-10));// change the expected value
													// check with DB.
		

		System.out.println("Test Completed: Results of compute gains and losses.");
		connect.close();
	}

	//@Test
	public void testUpdateStockHolding() throws Exception {// test update and
															// get together
		OrderManagementService oms = new OrderManagementService();
		PortfolioService pfs = new PortfolioService();
		System.out.println("creating new stockholding");
		Integer stockHolding_id = getSequenceID("stockholdings_pk_seq");
		pfs.createStockHoldings(connect,335, 3);
		System.out.println("stockholding created");

		System.out.println("Updating stock via portfolio service method now");
		
		pfs.updateStockHolding(3, 335, 50);
		StockHolding sh = null;
		List<StockHolding> stockHoldingList = pfs.getPortfolioInStockHolding(3);
		System.out.println(stockHoldingList);
		for (StockHolding newStock : stockHoldingList) {
			sh = newStock;
			
		}
		
		Integer remain_qty = sh.getRemaining_quantity();
		assertEquals(remain_qty, new Integer(50));

		connect.close();
	}
}
