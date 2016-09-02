package com.fdm.wealthnow.test;

import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.Stock;
import com.fdm.wealthnow.common.Watchlist;
import com.fdm.wealthnow.dao.WatchlistDAO;
import com.fdm.wealthnow.service.WatchlistService;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class WatchlistServiceTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {

		Watchlist thisWatchlist = new Watchlist();
		WatchlistService ws = new WatchlistService();
		WatchlistDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);

		// test viewWatchlist -> WORKING
		System.out.println("--> run viewWatchlist");
		thisWatchlist = ws.viewWatchlist(221);
		
		// test getUserWatchlists -> WORKING
		System.out.println("--> run getUserWatchlists");
		List<Watchlist> userWatchlists = new ArrayList<Watchlist>();
		userWatchlists = ws.getUserWatchlists(221);
		
		// test listStocksFromWatchlist -> WORKING 
		System.out.println("--> run listStocksFromWatchlist");
		List<Stock> stocksList = new ArrayList<Stock>();
		stocksList = ws.listStocksFromWatchlist(321);
		
		// test createWatchlist --> WORKING 
		

	}

}
