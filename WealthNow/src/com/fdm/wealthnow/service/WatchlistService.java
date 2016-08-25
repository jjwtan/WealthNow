package com.fdm.wealthnow.service;


import java.util.List;

import com.fdm.wealthnow.common.Stock;
import com.fdm.wealthnow.common.Watchlist;

public class WatchlistService {
	
	Watchlist watchlist = new Watchlist();
	
	// return watchlistId
	public Integer createWatchlist(Watchlist newWatchlist){
		return 1;
	}
	
	public void addStockToWatchlist(Integer watchlistId, String stockSymbol){
		
	}
	
	public void deleteStockFromWatchlist(Stock stock, Watchlist watchlist){
		
	}
	
	public void deleteWatchlist(Integer watchlistId){
		
	}
	
	public List<Stock> viewWatchlist(Integer watchlistId){
		List<Stock> thisList = null;
		
		return thisList;
	}
	
	public List<Watchlist> getDropdownList(Integer userId){
		List<Watchlist> thisList = null;
		
		return thisList;
	}
	
	public boolean checkForDuplicateStock(Watchlist watchlist, String stockSymbol){
		return true;
	}

}
