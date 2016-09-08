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
	// Test on view Watchlist (TESTED)
	//==============================================================================
	
	//@Test
	public void testViewWatchlist() throws Exception {
		wls = new WatchlistService();
		Watchlist watchlist = wls.viewWatchlist(58);
		
		System.out.println("\nStart viewWatchlist test");
		System.out.println("\nWatchlist name: " +watchlist);
		System.out.println("\nTest Completed: View watchlist.");	
	}
	
	//==============================================================================
	// Test on get user Watchlist
	//==============================================================================
	
	//@Test
	public void testGetUserWatchlist() throws Exception {
		wls = new WatchlistService();
		List<Watchlist> watchlist = wls.getUserWatchlists(2);

		System.out.println("\nStart getUserWatchlist test");
		
		for(Watchlist watch : watchlist){
		assertEquals(watchlist.size(), 4);
		}
		
		System.out.println("Test Completed: Get user watchlist.");	
	}
	
	//==============================================================================
	// Test on creating Watchlist (Tested)
	//==============================================================================
	
	//@Test
	public void testCreateWatchlist() throws Exception {
		wls = new WatchlistService();
		int watchlist_id = getSequenceID("watchlist_id_seq");
		
		wlist = new Watchlist(watchlist_id, "Test Watchlist", "", new Date(), new Date());
		wls.createWatchlist(wlist, 1);
		
		wldao = new WatchlistDAO();
		List<Watchlist> watchlist = wldao.getAllUserWatchlist(1, connect);
		
		System.out.println("\n Start createWatchlist test");
		assertEquals(watchlist.size(), 3);
		System.out.println("Test Completed: Create watchlist.");	
	}
	
	//==============================================================================
	// Test on deleting Watchlist (Tested)
	//==============================================================================

	//@Test
	public void testDeleteWatchlist() throws Exception {
		
		wls = new WatchlistService();
		int watchlist_id = getSequenceID("watchlist_id_seq");

		wls.deleteWatchlist(47);
	
		wldao = new WatchlistDAO();
		List<Watchlist> watchlist = wldao.getAllUserWatchlist(1, connect);
	
		System.out.println("\n Start createWatchlist test");
		assertEquals(watchlist.size(), 2);
		System.out.println("Test Completed: Create watchlist.");	
	}
	
	//==============================================================================
	// Test on updating Watchlist
	//==============================================================================
	
	//==============================================================================
	// Test on listing stocks from Watchlist (Tested)
	//==============================================================================
	
	//@Test
	public void testListStocksFromWatchlist() throws Exception {
		wls = new WatchlistService();
		
		List<Stock> watchlist = wls.listStocksFromWatchlist(2);

		System.out.println("\nStart listStocksFromWatchlist test");
		assertEquals(watchlist.size(), 4);
		System.out.println("Number of stocks in list: " +watchlist.size());
		System.out.println("Test Completed: List stocks in watchlist.");	
	}
	

	//==============================================================================
	// Test on adding stock to Watchlist (Tested)
	//==============================================================================

	//@Test
	public void testAddStockToWatchlist() throws Exception {
		wls = new WatchlistService();
		wls.addStockToWatchlist(2, "Z74"); // Key in new ticker code every test OR run both add and delete test
		
		wldao = new WatchlistDAO();
		List<String> watchlist = wldao.getAllStocksFromWatchlist(2, connect);
		
		System.out.println("\nStart addStockToWatchlist test");
		assertEquals(watchlist.size(), 3);
		System.out.println("\nTest Completed: Added stock in watchlist.");	
	}
	
	//==============================================================================
	// Test on deleting stocks from Watchlist (Tested)
	//==============================================================================
	
	//@Test
	public void testDeleteStockToWatchlist() throws Exception {
		wls = new WatchlistService();
		wls.deleteStockFromWatchlist(2, "Z74");
	
		wldao = new WatchlistDAO();
		List<String> watchlist = wldao.getAllStocksFromWatchlist(2, connect);
	
		System.out.println("\nStart deleteStockFromWatchlist test");
		assertEquals(watchlist.size(), 2);
		System.out.println("\nTest Completed: Deleted stock in watchlist.");	
	}
	
	//==============================================================================
	// Test on checking for duplicate stock from Watchlist (Tested)
	//==============================================================================
	
	//@Test
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
