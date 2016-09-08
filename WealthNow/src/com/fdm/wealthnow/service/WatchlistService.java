package com.fdm.wealthnow.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.Stock;
import com.fdm.wealthnow.common.Watchlist;
import com.fdm.wealthnow.dao.WatchlistDAO;
import com.fdm.wealthnow.service.StockService;

public class WatchlistService {

	static WatchlistDAO watchlistDAO = new WatchlistDAO();

	
	//==============================================================================
	// View Watchlist
	//==============================================================================
	
	public Watchlist viewWatchlist(int watchlistId) throws Exception {

		Connection connection = null;
		Watchlist thisWatchlist = new Watchlist();

		try {
			connection = WatchlistDAO.getConnection();
			connection.setAutoCommit(false);
			thisWatchlist = watchlistDAO.getWatchlist(watchlistId, connection);

			if (thisWatchlist == null) {
				System.out.println("viewWatchlist says: The watchlist does not exist!");
				return null;
			} else {
				System.out.println("Watchlist Id: " + thisWatchlist.getWatchlistId());
				System.out.println("Watchlist Name: " + thisWatchlist.getWatchlistName());
				System.out.println("Watchlist Visibility: " + thisWatchlist.getVisibility());
				System.out.println("Watchlist Date Created: " + thisWatchlist.getDateCreated());
				System.out.println("Watchlist Last Modified: " + thisWatchlist.getDateLastEdited());
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Inside exception");
		} finally {
			if (connection != null)
				connection.close();
		}

		return thisWatchlist;
	}

	//==============================================================================
	// Get User Watchlist
	//==============================================================================
	
	public List<Watchlist> getUserWatchlists(int userId) throws Exception {

		Connection connection = null;
		List<Watchlist> userWatchlists = new ArrayList<Watchlist>();

		try {
			connection = WatchlistDAO.getConnection();
			connection.setAutoCommit(false);

			userWatchlists = watchlistDAO.getAllUserWatchlist(userId, connection);

			if (userWatchlists == null || userWatchlists.size() == 0) {
				System.out.println("The user does not have any watchlist!");
			} else {
				for (int a = 0; a < userWatchlists.size(); a++) {
					System.out.println("Watchlist id: " + userWatchlists.get(a).getWatchlistId());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				connection.close();
		}
		return userWatchlists;
	}

	//==============================================================================
	// Create Watchlist
	//==============================================================================
	
	public void createWatchlist(Watchlist watchlist, Integer userId) throws Exception {
		
		Connection connection = null;
		
		int watchlistId = watchlist.getWatchlistId();
		String watchlistName = watchlist.getWatchlistName();
		String visibility = watchlist.getVisibility();
		Date dateCreated = watchlist.getDateCreated();
		Date dateLastEdited = watchlist.getDateLastEdited();
		
		try {
			connection = WatchlistDAO.getConnection();
			connection.setAutoCommit(false);
			
			System.out.println("Calling add watchlist: ");
			watchlistDAO.addWatchlist(watchlistId, watchlistName, visibility, dateCreated, dateLastEdited, userId, connection);

			connection.commit();
			
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
			if (connection != null)
				connection.close();
		}		
		
	}
	
	//==============================================================================
	// Delete Watchlist
	//==============================================================================
	
	public void deleteWatchlist(int watchListId) throws Exception {
		Connection connection = null;
		
		try {
			connection = WatchlistDAO.getConnection();
			connection.setAutoCommit(false);
		
			System.out.println("Calling add watchlist: ");
			watchlistDAO.deleteWatchlist(watchListId, connection);
			connection.commit();
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
				if (connection != null)
					connection.close();
		}		
		
	}
	
	//==============================================================================
	// Update Watchlist
	//==============================================================================
	
	public void updateWatchlist(Watchlist newWatchlist) throws Exception {
		
	}

	//==============================================================================
	// Lsit stocks from Watchlist
	//==============================================================================
	
	public List<Stock> listStocksFromWatchlist(Integer watchlistId) throws Exception {

		Connection connection = null;
		List<String> symbolsList = new ArrayList<String>();
		List<Stock> stocksList = new ArrayList<Stock>();

		try {
			connection = WatchlistDAO.getConnection();
			connection.setAutoCommit(false);

			symbolsList = watchlistDAO.getAllStocksFromWatchlist(watchlistId, connection);
			StockService ss = new StockService();
			System.out.println("size of symbol list: " + symbolsList.size());

			if (symbolsList == null || symbolsList.size() == 0) {
				System.out.println("listStocksFromWatchlist: There are no stocks in this watchlist!");
			} 
			else {
				System.out.println("Inside else. There are some stocks!");
				// returns a list of stock
				stocksList = ss.getStocksFromExchangeString(symbolsList, InfoType.WATCHLIST);
				System.out.println("size of stock list: " + stocksList.size());
				
				for(int i=0; i<stocksList.size(); i++){
					 System.out.println("Stock Symbol: " + stocksList.get(i).getStockSymbol());
					 System.out.println("Company: " + stocksList.get(i).getCompany());
					 System.out.println("Market Price: " + stocksList.get(i).getMktPrice());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				connection.close();
		}
		return stocksList;
	}
	
	//==============================================================================
	// Add stock to Watchlist
	//==============================================================================
	
	public void addStockToWatchlist(Integer watchlistId, String stockSymbol) throws Exception {
		
		Connection connection = null;
		WatchlistService wls = new WatchlistService();
		
		try {
			connection = WatchlistDAO.getConnection();
			connection.setAutoCommit(false);
			
			// call stock service validate stock
			StockService ss = new StockService();
			ss.validateStock(stockSymbol);
		
			// call in this service check for duplicate stock*********
			if (wls.checkForDuplicateStocks(watchlistId, stockSymbol) == false){
				watchlistDAO.addStockToWatchlist(watchlistId, stockSymbol, connection);
			}
			
			connection.commit();
			System.out.println(connection);
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				connection.close();
		}
	}
	
	//==============================================================================
	// Delete stock from Watchlist
	//==============================================================================
	
	public void deleteStockFromWatchlist(Integer watchlistId, String stockSymbol) throws Exception {
		Connection connection = null;
		try {
			connection = WatchlistDAO.getConnection();
			connection.setAutoCommit(false);

			watchlistDAO.deleteStockFromWatchlist(watchlistId, stockSymbol, connection);
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				connection.close();
		}
	}
	
	//==============================================================================
	// Check for duplicate stock from Watchlist
	//==============================================================================
	public boolean checkForDuplicateStocks(Integer watchlistId, String stockSymbol) throws SQLException {
	
		Connection connection = null;
	
		try {
			connection = WatchlistDAO.getConnection();
			connection.setAutoCommit(false);
		
			// call stock service validate stock
			StockService ss = new StockService();
			ss.validateStock(stockSymbol);
			
			// call in this service check for duplicate stock
			List<String> listofStocks = watchlistDAO.getAllStocksFromWatchlist(watchlistId, connection);
			
			for (String stocksymbol : listofStocks){
				if (stocksymbol.equalsIgnoreCase(stockSymbol)){
					System.out.println("Duplicate stock!");
					return true;
				}else{
					return false;
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
				if (connection != null)
					connection.close();
		}
		
		return false;
	}

}