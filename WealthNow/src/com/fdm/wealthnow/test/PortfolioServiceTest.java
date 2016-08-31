package com.fdm.wealthnow.test;

import org.junit.Test;

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

import org.junit.Before;

public class PortfolioServiceTest extends DBUtil{

	
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

//	@Test
	public void testResultsOfComputeGainsAndLosses() {
		
		
		

		System.out.println("Test Completed: Results of compute gains and losses.");
	}

	@Test
	public void testUpdateStockHolding() throws Exception {//test update and get together
		
		PortfolioDAO pfDao = new PortfolioDAO();
		System.out.println("creating new stockholding");
		Integer stockHolding_id = getSequenceID("stockholdings_pk_seq");
		pfDao.createStockHoldingInDatabase(connect, stockHolding_id,new Integer(1), new Integer(124), "XXX", new Integer(500),
				new Integer(100), new Double(99.99), "20 Sep 2001");
		System.out.println("stockholding created");
		
		System.out.println("Updating stock via portfolio service method now");
		PortfolioService pfs = new PortfolioService();
		pfs.updateStockHolding(1, 124, 50);
		
		StockHolding sh = pfDao.getStockholding(connect, 124);
		
		assertEquals(sh.getRemaining_quantity(), new Integer(450));
		
		
	
		
		
		
		
	}
}
