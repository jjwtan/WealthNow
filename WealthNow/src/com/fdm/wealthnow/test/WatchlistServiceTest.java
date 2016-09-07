package com.fdm.wealthnow.test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.Stock;
import com.fdm.wealthnow.common.Watchlist;
import com.fdm.wealthnow.dao.WatchlistDAO;
import com.fdm.wealthnow.service.WatchlistService;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class WatchlistServiceTest {
	static Connection connect;
	private WatchlistDAO wldao;
	private WatchlistService wls;

	//==============================================================================
	// Before test
	//==============================================================================

	@Before
	public void setup() throws Exception{
		WatchlistDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
		connect = WatchlistDAO.getConnection();
		connect.setAutoCommit(false);
	}
	
	//==============================================================================
	// Test on view Watchlist
	//==============================================================================
	

	public void testViewWatchlist() throws Exception {
		wls = new WatchlistService();
		wls.addStockToWatchlist(7, "Z74");
		
//		Watchlist wl = wl.getOrderFromProcessedOrder(connect, order_id1);
		wldao.getAllStocksFromWatchlist(7, connect);

		System.out.println("\n Start addStockToWatchlist");
	
		System.out.println("Test Completed: Added stock in watchlist.");	
	}
	
	//==============================================================================
	// Test on get user Watchlist
	//==============================================================================
	
	//==============================================================================
	// Test on creating Watchlist
	//==============================================================================
	
	@Test
	public void testCreateWatchlistlist() throws Exception {
		
	wls = new WatchlistService();
		wls.createWatchlist(77, "testWatchList", "PRIVATE", "22-APR-16", "22-APR-16", 2);
		
		wldao = new WatchlistDAO();
		List<String> watchlist = wldao.getAllStocksFromWatchlist(2, connect);
		
		System.out.println("\n Start addStockToWatchlist");
		
		assertEquals(watchlist.size(), 3);
		
		System.out.println("Test Completed: Added stock in watchlist.");	
	}
	
	//==============================================================================
	// Test on deleting Watchlist
	//==============================================================================
	
	@Test
	public void testDeleteWatchlistlist() throws Exception {
		
	wls = new WatchlistService();
		wls.deleteWatchlist(watchListId);
		
		wldao = new WatchlistDAO();
		List<String> watchlist = wldao.getAllStocksFromWatchlist(2, connect);
		
		System.out.println("\n Start addStockToWatchlist");
		
		assertEquals(watchlist.size(), 3);
		
		System.out.println("Test Completed: Added stock in watchlist.");	
	}
	
	//==============================================================================
	// Test on updating Watchlist
	//==============================================================================
	
	//==============================================================================
	// Test on listing stocks from Watchlist (Tested)
	//==============================================================================
	
	
	public void testListStocksFromWatchlist() throws Exception {
		wls = new WatchlistService();
		
		List<Stock> watchlist = wls.listStocksFromWatchlist(2);

		System.out.println("\nStart listStocksFromWatchlist");
			
		assertEquals(watchlist.size(), 4);
		System.out.println("Number of stocks in list: " +watchlist.size());

		System.out.println("Test Completed: List stocks in watchlist.");	
	}
	

	//==============================================================================
	// Test on adding stock to Watchlist (Tested)
	//==============================================================================

	@Test
	public void testAddStockToWatchlist() throws Exception {
		wls = new WatchlistService();
		wls.addStockToWatchlist(2, "Z74"); // Key in new ticker code every test OR run both add and delete test
		
		wldao = new WatchlistDAO();
		List<String> watchlist = wldao.getAllStocksFromWatchlist(2, connect);
		
		System.out.println("\n Start addStockToWatchlist");
		
		assertEquals(watchlist.size(), 3);
		
		System.out.println("Test Completed: Added stock in watchlist.");	
	}
	
	//==============================================================================
	// Test on deleting stocks from Watchlist (Tested)
	//==============================================================================
	
	@Test
	public void testDeleteStockToWatchlist() throws Exception {
		wls = new WatchlistService();
		wls.deleteStockFromWatchlist(2, "Z74");
	
		wldao = new WatchlistDAO();
		List<String> watchlist = wldao.getAllStocksFromWatchlist(2, connect);
	
		System.out.println("\n Start deleteStockFromWatchlist");
		
		assertEquals(watchlist.size(), 2);
	
		System.out.println("Test Completed: Deleted stock in watchlist.");	
	}
	
	//==============================================================================
	// Test on checking for duplicate stock from Watchlist (Tested)
	//==============================================================================
	
 
	public void testCheckForDuplicateStocks() throws Exception {
		wls = new WatchlistService();
		wls.addStockToWatchlist(2, "B2F");
		
		boolean checkDuplicate = wls.checkForDuplicateStocks(2, "B2F");
		boolean isDuplicate = true;

		System.out.println("\n Start checkForDuplicateStocks");
			
		assertEquals(checkDuplicate, isDuplicate);	
		
		System.out.println("Test Completed: Check for duplicate stocks.");	
	}
	
	//==============================================================================
	// After test
	//==============================================================================
	
	@After
	public void tearDown() {
		try {
			connect.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//==============================================================================
	// END 
	//==============================================================================
	
}
