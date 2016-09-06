package com.fdm.wealthnow.test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.Stock;
import com.fdm.wealthnow.common.Watchlist;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.dao.WatchlistDAO;
import com.fdm.wealthnow.service.WatchlistService;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class WatchlistServiceTest {
	static Connection connect;
	private WatchlistDAO watchlistDAO;

	//==============================================================================
	// Before test
	//==============================================================================

	@Before
	public void setup() throws Exception{
		OrderDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
		connect = WatchlistDAO.getConnection();
		connect.setAutoCommit(false);
	}

	//==============================================================================
	// Test on adding stock to Watchlist
	//==============================================================================

	@Test
	public void testAddStockToWatchlist() throws Exception {
		watchlistDAO = new WatchlistDAO();
		watchlistDAO.addStockToWatchlist(7, "Z74", connect);
		
//		Watchlist wl = wl.getOrderFromProcessedOrder(connect, order_id1);
		watchlistDAO.getAllStocksFromWatchlist(7, connect);

		System.out.println("\n Start addStockToWatchlist");
	

		System.out.println("Test Completed: Added stock in watchlist.");	
	}

}
