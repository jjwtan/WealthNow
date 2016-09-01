package com.fdm.wealthnow.service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.Stock;
import com.fdm.wealthnow.common.Watchlist;
import com.fdm.wealthnow.dao.WatchlistDAO;
import com.fdm.wealthnow.service.StockService;

public class WatchlistService {
	
	static WatchlistDAO watchlistDAO = new WatchlistDAO();
	
	public Watchlist viewWatchlist(int watchlistId) throws Exception {
		
		System.out.println("Inside viewWatchlist");
		Connection connection = null;
        Watchlist thisWatchlist = new Watchlist();
        
        try {
        	System.out.println("Inside viewWatchlist try lalalas");
        	connection = WatchlistDAO.getConnection();
            //connection.setAutoCommit(false);

           System.out.println("Calling check if watchlist exists:");
            Boolean watchlistExists = checkWatchlistExists(watchlistId);
           System.out.println("True or false: " + watchlistExists);
           
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
         // connection.rollback();
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
        	   System.out.println("The user does not have any watchlist!");
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
	
	public List<Stock> listStocksFromWatchlist(Integer watchlistId) throws Exception {
		
        Connection connection = null;
        List<String> symbolsList = new ArrayList<String>();
        List<Stock> stocksList = new ArrayList<Stock>();
        Stock stock = new Stock();
        
        try {
            connection = WatchlistDAO.getConnection();
            connection.setAutoCommit(false);
            
            symbolsList = watchlistDAO.getAllStocksFromWatchlist(watchlistId, connection);
            
            StockService ss = new StockService();
            
            for(int i=0; i<symbolsList.size(); i++){
            	stock = ss.getStockFromExchange(symbolsList.get(i), InfoType.FULL);
            	stocksList.add(stock);
            }
            
            if(stocksList.size() == 0){
         	  System.out.println("There are no stocks in this watchlist!");
         	  stocksList = null;
            }
            else {
            	for(int j=0; j<stocksList.size(); j++){
            		System.out.println("Stock Symbol: " + stocksList.get(j).getStockSymbol());
            		System.out.println("Company: " + stocksList.get(j).getCompany());
            		System.out.println("Market Price: " + stocksList.get(j).getMktPrice());
            	}
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
		return stocksList;
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
	// return false: watchlist does not exist 
	// return true: watchlist exists 
	public boolean checkWatchlistExists(Integer watchlistId) throws Exception {
		
        System.out.println("checkWatchlistExists says: Inside check watchlist exists");
		Connection connection = null;
        Watchlist thisWatchlist = new Watchlist();
        
        try {
            connection = WatchlistDAO.getConnection();
            connection.setAutoCommit(false);

           // if returns null then watchlist does not exist
            thisWatchlist = watchlistDAO.getWatchlist(1, connection);
            if (thisWatchlist == null){
            	System.out.println("checkWatchlistExists says: Watchlist does not exist.");
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
        System.out.println("checkWatchlistExists says: Watchlist exists.");
		return true;
	}

}
