package com.fdm.wealthnow.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fdm.wealthnow.common.Watchlist;
import com.fdm.wealthnow.dao.WatchlistDAO;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

@SuppressWarnings("static-access")
public class WatchlistDAOTest {

	private WatchlistDAO watchlistDAO;

	@Before
	public void setup() {
		WatchlistDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
	}

	@Test
	public void testGetWatchlist() {

		System.out.println("Inside testGetWatchlist");
		watchlistDAO = new WatchlistDAO();
		// Watchlist watchlistToGet = new Watchlist();

		Watchlist watchlistToGet1 = watchlistDAO.getWatchlist(1);
		assertEquals("following", watchlistToGet1.getWatchlistName());
		Watchlist watchlistToGet2 = watchlistDAO.getWatchlist(2);
		assertEquals("myList", watchlistToGet2.getWatchlistName());
		/*
		 * Watchlist watchlistToGet3 = watchlistDAO.getWatchlist(4); // date
		 * format shows like this assertEquals("2016-03-15",
		 * watchlistToGet3.getDateCreated());
		 */
	}

	@Test
	public void testGetAllUserWatchlist() {

		// set user_id and number of watchlists to get
		// Integer userId = 3;
		// Integer watchlistSize = 4;

		System.out.println("Inside testGetAllUserWatchlist");
		watchlistDAO = new WatchlistDAO();
		List<Watchlist> watchlistsBelongingToUser = new ArrayList<Watchlist>();
		watchlistsBelongingToUser = watchlistDAO.getAllUserWatchlist(3);

		assertEquals(4, watchlistsBelongingToUser.size());

		/*
		 * for(int i=0; i<watchlistsBelongingToUser.size(); i++){
		 * assertEquals("following", watchlistToGet1.getWatchlistName()); }
		 */
	}

	@Test
	public void testAddWatchlist() throws ParseException {
		
		System.out.println("--> Inside testAddWatchlist");
		Integer userId;
		Watchlist watchlistToAdd = new Watchlist();
		
		/* Solution online:
DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
Date myDate = formatter.parse(date);
java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
		 */		
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString1 = "03-06-2015";
		String dateString2 = "25-02-2016";
		Date date1 = (Date) formatter.parse(dateString1);
		Date date2 = (Date) formatter.parse(dateString2);
		
		java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
		java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
		userId = 2;

		watchlistToAdd = new Watchlist(10, "Amys watchlist five", "PRIVATE", sqlDate1, sqlDate2);
		watchlistDAO = new WatchlistDAO();
		
		// create watchlist for user
		System.out.println("--> Calling addWatchlist for userId: " + userId);
		watchlistDAO.addWatchlist(watchlistToAdd, userId);

		// check that all the user's watchlists are present
		List<Watchlist> watchlistsBelongingToUser = new ArrayList<Watchlist>();
		watchlistsBelongingToUser = watchlistDAO.getAllUserWatchlist(2);

		assertEquals(5, watchlistsBelongingToUser.size());
	}
}
