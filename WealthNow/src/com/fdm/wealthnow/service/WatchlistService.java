package com.fdm.wealthnow.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.Stock;
import com.fdm.wealthnow.common.Watchlist;
import com.fdm.wealthnow.dao.WatchlistDAO;
import com.fdm.wealthnow.service.StockService;

public class WatchlistService {

	static WatchlistDAO watchlistDAO = new WatchlistDAO();

	// TESTED
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
			connection.rollback();
			System.out.println("Inside exception");
		} finally {
			if (connection != null)
				connection.close();
		}

		return thisWatchlist;
	}

	// TESTED
	public List<Watchlist> getUserWatchlists(int userId) throws Exception {

		Connection connection = null;
		List<Watchlist> userWatchlists = new ArrayList<Watchlist>();

		try {
			connection = WatchlistDAO.getConnection();
			connection.setAutoCommit(false);

			userWatchlists = watchlistDAO.getAllUserWatchlist(userId, connection);

			if (userWatchlists.size() == 0) {
				System.out.println("The user does not have any watchlist!");
				userWatchlists = null;
			} else {
				for (int a = 0; a < userWatchlists.size(); a++) {
					System.out.println("Watchlist id: " + userWatchlists.get(a).getWatchlistId());
				}
			}

			connection.commit();
		} catch (Exception e) {
			connection.rollback();
		} finally {
			if (connection != null)
				connection.close();
		}
		return userWatchlists;
	}

	public void createWatchlist(int watchlistId, String watchlistName, String visibility, String dateCreated,
			String dateLastEdited, Integer userId) throws Exception {

	}

	public void deleteWatchlist(int watchListId) throws Exception {

	}

	public void updateWatchlist(Watchlist newWatchlist) throws Exception {

	}

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

			if (symbolsList.size() == 0) {
				System.out.println("listStocksFromWatchlist: There are no stocks in this watchlist!");
				stocksList = null;
			} 
			else {
				System.out.println("Inside else. There are some stocks!");
				// returns a list of stock
				stocksList = ss.getStocksFromExchangeString(symbolsList, InfoType.FULL);
				System.out.println("size of stock list: " + stocksList.size());
				
				for(int i=0; i<stocksList.size(); i++){
					 System.out.println("Stock Symbol: " + stocksList.get(i).getStockSymbol());
					 System.out.println("Company: " + stocksList.get(i).getCompany());
					 System.out.println("Market Price: " + stocksList.get(i).getMktPrice());
				}
			}
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
		} finally {
			if (connection != null)
				connection.close();
		}
		return stocksList;
	}

	public void addStockToWatchlist(Integer watchlistId, String stockSymbol) throws Exception {
		// call stock service validate stock
		// call in this service check for duplicate stock

	}

	public void deleteStockFromWatchlist(Integer watchlistId, String stockSymbol) throws Exception {

	}
	
	// to be changed later
	public boolean checkForDuplicateStocks(Integer watchlistId, String stockSymbol) {
		return true;
	}

}
