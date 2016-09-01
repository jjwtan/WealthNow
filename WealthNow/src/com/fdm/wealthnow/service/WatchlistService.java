package com.fdm.wealthnow.service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.Stock;
import com.fdm.wealthnow.common.Watchlist;
import com.fdm.wealthnow.dao.WatchlistDAO;

public class WatchlistService {
	
	static WatchlistDAO watchlistDAO = new WatchlistDAO();
	
	public Watchlist viewWatchlist(int watchlistId) throws Exception {
		
		Connection connection = null;
        Watchlist thisWatchlist = new Watchlist();
        
        try {
            connection = WatchlistDAO.getConnection();
            connection.setAutoCommit(false);

           Boolean watchlistExists = checkWatchlistExists(watchlistId);
           if(watchlistExists == false){
        	   System.out.println("Watchlist does not exist!");
        	   thisWatchlist = null;
           }
           else {
        	 
        	   System.out.println("Calling getWatchlist");
        	   thisWatchlist = watchlistDAO.getWatchlist(watchlistId, connection); 
        	   
        	   System.out.println("Watchlist Id: " + thisWatchlist.getWatchlistId());
        	   System.out.println("Watchlist Name: " + thisWatchlist.getWatchlistName());
        	   System.out.println("Watchlist Visibility: " + thisWatchlist.getVisibility());
        	   System.out.println("Watchlist Date Created: " + thisWatchlist.getDateCreated());
        	   System.out.println("Watchlist Last Modified: " + thisWatchlist.getDateLastEdited());
           }
          connection.commit();
         }
        catch(Exception e) {
          connection.rollback();
       }
        finally {
          if (connection != null)
              connection.close();
        }
		
		return thisWatchlist;
	}
	
	public List<Watchlist> getUserWatchlists(int userId) throws Exception {
		
        Connection connection = null;
        List<Watchlist> userWatchlists = new ArrayList<Watchlist>();
        
        try {
            connection = WatchlistDAO.getConnection();
            connection.setAutoCommit(false);

           userWatchlists = watchlistDAO.getAllUserWatchlist(userId, connection);
           
           if(userWatchlists.size() == 0){
        	   System.out.println("There are no stocks in this watchlist!");
        	   userWatchlists = null;
           }

          connection.commit();
         }
        catch(Exception e) {
          connection.rollback();
       }
        finally {
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
	
	public List<String> listStocksFromWatchlist(Integer watchlistId) throws Exception {
		
		
		
		return null;
		// call get stock info 
	}
	
	public void addStockToWatchlist(Integer watchlistId, String stockSymbol) throws Exception {
		// call validate stock
		
	}
	
	public void deleteStockFromWatchlist(Integer watchlistId, String stockSymbol) throws Exception {
		
	}
	
	// Validations:
	
	// call validate stock
	
	// 1. Duplicate stock
	public boolean checkForDuplicateStocks(Integer watchlistId, String stockSymbol){
		return true;
	}
	
	// 2. create only if it does not exist, pull only if it exists 
	public boolean checkWatchlistExists(Integer watchlistId) throws Exception {
		
        Connection connection = null;
        Watchlist thisWatchlist = new Watchlist();
        
        try {
            connection = WatchlistDAO.getConnection();
            connection.setAutoCommit(false);

           // if returns null then watchlist does not exist
            thisWatchlist = watchlistDAO.getWatchlist(1, connection);
            if (thisWatchlist == null){
            	return false;
            }
          connection.commit();
         }
        catch(Exception e) {
          connection.rollback();
       }
        finally {
          if (connection != null)
              connection.close();
        }
		
		return true;
	}

}
