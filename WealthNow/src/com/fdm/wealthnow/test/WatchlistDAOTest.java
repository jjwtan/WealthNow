package com.fdm.wealthnow.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdm.wealthnow.common.Watchlist;
import com.fdm.wealthnow.dao.WatchlistDAO;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class WatchlistDAOTest {

	private WatchlistDAO watchlistDAO;
	static Connection connect;

	@Before
	public void setup() throws Exception {
		WatchlistDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
		connect = WatchlistDAO.getConnection();
		connect.setAutoCommit(false);
	}

	@Test
	public void testGetWatchlist() {

		//System.out.println("Inside testGetWatchlist");
		watchlistDAO = new WatchlistDAO();
		// Watchlist watchlistToGet = new Watchlist();

		Watchlist watchlistToGet1 = watchlistDAO.getWatchlist(1, connect);
		assertEquals("following", watchlistToGet1.getWatchlistName());
		Watchlist watchlistToGet2 = watchlistDAO.getWatchlist(2, connect);
		assertEquals("myList", watchlistToGet2.getWatchlistName());
	}

	@Test
	public void testGetAllUserWatchlist() {

		watchlistDAO = new WatchlistDAO();
		List<Watchlist> watchlistsBelongingToUser = new ArrayList<Watchlist>();
		watchlistsBelongingToUser = watchlistDAO.getAllUserWatchlist(3, connect);

		assertEquals(4, watchlistsBelongingToUser.size());

	}

	@Test
	public void testAddWatchlist() throws ParseException, SQLException {
		
		System.out.println("--> Inside testAddWatchlist");
		Integer userId;
			
		//this is Amy
		userId = 2;

		watchlistDAO = new WatchlistDAO();
		
		// create watchlist for user
		//System.out.println("--> Calling addWatchlist for userId: " + userId);
		// pass the connection too
		watchlistDAO.addWatchlist(10, "Amys watchlist five", "PRIVATE", "19 Sep 2014", "19 Sep 2014", userId, connect);
		
		// check that all the user's watchlists are present
		List<Watchlist> watchlistsBelongingToUser = new ArrayList<Watchlist>();
		watchlistsBelongingToUser = watchlistDAO.getAllUserWatchlist(2, connect);
		
		// test the names of all the watchlists
		/*
		for(int i=0; i<watchlistsBelongingToUser.size(); i++){
			System.out.println("This watchlist name: ");
			System.out.println(watchlistsBelongingToUser.get(i).getWatchlistName());
		}
		*/
		
		assertEquals(5, watchlistsBelongingToUser.size());
	}
	
	@Test
	public void testDeleteWatchlist() throws ParseException, SQLException {
		watchlistDAO = new WatchlistDAO();
		// delete watchlist (id 6) named My Important Stocks from John's (id 3) watchlist
		Integer watchListId = 6;
		
		watchlistDAO.deleteWatchlist(watchListId, connect);
		
		List<Watchlist> watchlistsBelongingToUser = new ArrayList<Watchlist>();
		// 3 is John
		watchlistsBelongingToUser = watchlistDAO.getAllUserWatchlist(3, connect);
		
		// test the names of all the watchlists
		for(int i=0; i<watchlistsBelongingToUser.size(); i++){
			System.out.println("This watchlist name: ");
			System.out.println(watchlistsBelongingToUser.get(i).getWatchlistName());
		}
		
		// should be 3 and not 4
		assertEquals(3, watchlistsBelongingToUser.size());
	}
	
	@Test
	public void testUpdateWatchlist() throws ParseException, SQLException {
		watchlistDAO = new WatchlistDAO();
		Integer watchListId = 1;
		Watchlist newWatchlist = new Watchlist(watchListId, "NEW", "PUBLIC");
		watchlistDAO.updateWatchlist(newWatchlist, connect);
		
		// get watchlist and test 
		Watchlist watchlistToGet = watchlistDAO.getWatchlist(watchListId, connect);
		assertEquals("NEW", watchlistToGet.getWatchlistName());
		assertEquals("PUBLIC", watchlistToGet.getVisibility());
	}
	
	@After
	public void tearDown() {
		try {
			connect.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
