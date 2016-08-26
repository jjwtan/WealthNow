package com.fdm.wealthnow.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fdm.wealthnow.common.Watchlist;
import com.fdm.wealthnow.dao.WatchlistDAO;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class WatchlistDAOTest {
	
	private WatchlistDAO watchlistDAO;
	
	/*
	static WatchlistDAO watchlistDAO = new WatchlistDAO();
	static List<Watchlist> watchlists = new ArrayList<>();
	
	static {
		
		//int watchlistId, String watchlistName, String visibility, Date dateCreated, Date dateLastEdited
		Date nowDate = new Date(); //current date 
		watchlists.add(new Watchlist(1, "watchlist one", "private", nowDate, nowDate));
		watchlists.add(new Watchlist(2, "watchlist two", "public", nowDate, nowDate));
		watchlists.add(new Watchlist(3, "watchlist three", "private", nowDate, nowDate));
	}
	*/

	@Before 
	public void setup(){
		WatchlistDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
	}
	
	@Test
	public void testGetWatchlist() {
		/*
				stockService = new StockService();
		List<Stock> stocks = stockService.getStocksFromExchange(stockList);
		for(Stock stock: stocks) {
			System.out.println(stock);
		}
		 */
		
		watchlistDAO = new WatchlistDAO();
		//Watchlist watchlistToGet = new Watchlist();
		@SuppressWarnings("static-access")
		Watchlist watchlistToGet = watchlistDAO.getWatchlist(2);
		
		System.out.println(watchlistToGet);
		
		
	}

}
