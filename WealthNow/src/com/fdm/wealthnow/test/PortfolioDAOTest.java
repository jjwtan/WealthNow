package com.fdm.wealthnow.test;

import org.junit.Test;

import com.fdm.wealthnow.common.StockHolding;
import com.fdm.wealthnow.dao.AuthDAO;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.dao.PortfolioDAO;
import com.fdm.wealthnow.util.DBUtil;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;

public class PortfolioDAOTest extends DBUtil{
	static Connection connect;
	private PortfolioDAO portfolioDAO;

	//==============================================================================
	// Before test
	//==============================================================================
	
	@Before
	public void setup() throws Exception {
		PortfolioDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
		connect = PortfolioDAO.getConnection();
		connect.setAutoCommit(false);
	}
	
	//==============================================================================
	// Test on creating stockholding in database
	//==============================================================================

	@Test
	public void testCreateStockHoldingInDatabase() throws Exception {

		PortfolioDAO portfolioDAO1 = new PortfolioDAO();

		portfolioDAO1.createStockHoldingInDatabase(connect, new Integer(10),new Integer(1), new Integer(131), "MAC", new Integer(100),
				new Integer(100), new Double(99.99), "20 Sep 2001");

		List<StockHolding> newTestList = portfolioDAO1.getStockHoldingInDataBase(1, connect);

		for (StockHolding newListTest : newTestList) {
			System.out.println(newListTest.getUser_id());

			assertEquals(newListTest.getUser_id(), new Integer(1));

		}

		System.out.println("Test Completed: Created Data in database.");
	}

	//==============================================================================
	// Test on updating stockholding database
	//==============================================================================
	
	@Test
	public void testUpdateStockHoldingInDatabase() throws Exception {

		PortfolioDAO portfolioDAO = new PortfolioDAO();
		
//		portfolioDAO.createStockHoldingInDatabase(new Integer(1), new Integer(131), "9FINGERS", new Integer(400),
//				new Integer(400), new Double(55.50), "20 Sep 2001");
		
		portfolioDAO.updateStockHolding(connect, new Integer(131), new Integer(40));

		List<StockHolding> newTestList = portfolioDAO.getStockHoldingInDataBase(1, connect);
		System.out.println(newTestList);
		
		for (StockHolding newListTest : newTestList) {
			assertEquals(newListTest.getRemaining_quantity(), new Integer(60));

			System.out.println("Test Completed: Update Stock holding in database.");
		}

	}
	
	//==============================================================================
	// After test
	//==============================================================================
	
	@After
	public void tearDown() throws SQLException {
		try {
			connect.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connect.close();
	}
	
}
