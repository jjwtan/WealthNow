package com.fdm.wealthnow.test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.Stock;
import com.fdm.wealthnow.common.Watchlist;
import com.fdm.wealthnow.dao.WatchlistDAO;
import com.fdm.wealthnow.service.WatchlistService;
import com.fdm.wealthnow.util.DBUtil;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class WatchlistServiceTest extends DBUtil {
	static Connection connect;
	private Watchlist wlist;
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
	public void testCreateWatchlist() throws Exception {
		wls = new WatchlistService();
		int watchlist_id = getSequenceID("watchlist_id_seq");

		wlist = new Watchlist(watchlist_id, "Test Watchlist", null, new Date(2016, 02, 11), new Date(2016, 02, 11));
		wls.createWatchlist(wlist, 1);
		
		wldao = new WatchlistDAO();
		List<Watchlist> watchlist = wldao.getAllUserWatchlist(1, connect);
		
		System.out.println("\n Start createWatchlist");
		assertEquals(watchlist.size(), 2);
		System.out.println("Test Completed: Create watchlist.");	
	}
	
	//==============================================================================
	// Test on deleting Watchlist
	//==============================================================================
	
	
	public void testDeleteWatchlist() throws Exception {
		
		wls = new WatchlistService();
		wls.deleteWatchlist(77);
		
		wldao = new WatchlistDAO();
		List<Watchlist> userWatchlist = wls.getUserWatchlists(2);
		
		System.out.println("\n Start deleteWatchlist");
		assertEquals(userWatchlist.size(), 0);
		System.out.println("Test Completed: Delete watchlist.");	
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
