package com.fdm.wealthnow.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fdm.wealthnow.common.Watchlist;
import com.fdm.wealthnow.util.DBUtil;

public class WatchlistDAO extends DBUtil {

	// return watchlist object
	// return null if no such watchlist 
	public Watchlist getWatchlist(int watchlistId, Connection connect) {

		Watchlist newWatchlist = new Watchlist();

		try {
			// SQL statement
			String SQLStatement = "select watchlist_name, visibility, date_created, date_modified from watchlist where watchlist_id = ?";
			PreparedStatement ps = connect.prepareStatement(SQLStatement);
			// insert the first ? as watchlistId
			ps.setInt(1, watchlistId);

			// execute query
			ResultSet result = ps.executeQuery();
			// for checking null
			Integer counter =0;

			while (result.next()) {
				counter ++;
				String watchlistName = result.getString("watchlist_name");
				String visibility = result.getString("visibility");
				Date dateCreated = result.getDate("date_created");
				Date dateLastEdited = result.getDate("date_modified");
				newWatchlist = new Watchlist(watchlistId, watchlistName, visibility, dateCreated, dateLastEdited);
			}
			
			if (counter == 0) {
				System.out.println("getWatchlist says: No such watchlist!");
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return newWatchlist;
	};

	// get all watchlists belonging to one user
	// return null if user does not have any watchlist 
	public List<Watchlist> getAllUserWatchlist(int userId, Connection connect) {

		List<Watchlist> watchlistsBelongingToUser = new ArrayList<Watchlist>();

		try {
			String SQLStatement = "Select watchlist.watchlist_id, watchlist.watchlist_name, watchlist.visibility, watchlist.date_created, watchlist.date_modified from watchlist inner join userwatchlist on watchlist.watchlist_id = userwatchlist.watchlist_id inner join user1 on user1.user_id = userwatchlist.user_id where user1.user_id = ?";
			PreparedStatement ps = connect.prepareStatement(SQLStatement);
			ps.setInt(1, userId);

			ResultSet result = ps.executeQuery();
			Integer counter = 0;

			while (result.next()) {
				counter ++;
				Integer watchlistId = result.getInt("watchlist_id");
				String watchlistName = result.getString("watchlist_name");
				String visibility = result.getString("visibility");
				Date dateCreated = result.getDate("date_created");
				Date dateLastEdited = result.getDate("date_modified");
				Watchlist newWatchlist = new Watchlist(watchlistId, watchlistName, visibility, dateCreated,
						dateLastEdited);
				watchlistsBelongingToUser.add(newWatchlist);
			}
			
			if (counter == 0) {
				System.out.println("getAllUserWatchlist says: User does not have any watchlist!");
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return watchlistsBelongingToUser;
	};

	// return watchlist id
	// use String here for the dates instead of Date
	public void addWatchlist(int watchlistId, String watchlistName, String visibility, Date dateCreated,
			Date dateLastEdited, Integer userId, Connection newConnect) {

		try {
			String SQLStatement = "INSERT INTO Watchlist(watchlist_id, watchlist_name, visibility, date_created, date_modified) VALUES ("
					+ watchlistId + ",'" + watchlistName + "','" + visibility + "','" + convertDateObjToString(dateCreated) + "','"
					+ convertDateObjToString(dateLastEdited) + "')";
			System.out.println(SQLStatement);
			
			PreparedStatement ps = newConnect.prepareStatement(SQLStatement);
			ps.executeUpdate();

			String SQLStatement2 = "INSERT INTO UserWatchlist(user_id , watchlist_id) VALUES (" + userId + ","
					+ watchlistId + ")";

			PreparedStatement ps2 = newConnect.prepareStatement(SQLStatement2);
			ps2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public void deleteWatchlist(int watchListId, Connection connect) {

		try {

			String SQLStatement1 = "delete from userwatchlist where watchlist_id = " + watchListId;
			String SQLStatement2 = "delete from watchlist where watchlist_id = " + watchListId;

			PreparedStatement ps1 = connect.prepareStatement(SQLStatement1);
			PreparedStatement ps2 = connect.prepareStatement(SQLStatement2);

			ps1.executeUpdate();
			ps2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	};

	// allow user to change watchlistName and/or visibility
	// pass in newWatchlist with existing id but new name and visibility
	public void updateWatchlist(Watchlist newWatchlist, Connection connect) {

		try {

			String SQLStatement = "UPDATE watchlist SET watchlist.watchlist_name = '" + newWatchlist.getWatchlistName()
					+ "'," + "watchlist.visibility = '" + newWatchlist.getVisibility()
					+ "' WHERE watchlist.watchlist_id = " + newWatchlist.getWatchlistId();

			PreparedStatement ps = connect.prepareStatement(SQLStatement);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	// get stock symbols from watchlist
	// return null if there are no stocks in watchlist 
	public List<String> getAllStocksFromWatchlist(Integer watchlistId, Connection connect) {

		List<String> stocksList = new ArrayList<String>();
		
		try {
			String SQLStatement = "select stock_symbol from WatchlistDetail where watchlist_id = ?";
			PreparedStatement ps = connect.prepareStatement(SQLStatement);
			ps.setInt(1, watchlistId);

			ResultSet result = ps.executeQuery();
			Integer count = 0;

			while (result.next()) {
				count ++;
				String stockSymbol = result.getString("stock_symbol");
				stocksList.add(stockSymbol);
			}
			
			if (count == 0) {
				System.out.println("getAllStocksFromWatchlist says: Watchlist does not have any stock!");
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stocksList;
	}

	// add stock symbols to watchlist
	public void addStockToWatchlist(Integer watchlistId, String stockSymbol, Connection connect) {

		try {

			String SQLStatement = "INSERT INTO WatchlistDetail(watchlist_id  , stock_symbol ) VALUES (" + watchlistId
					+ ", '" + stockSymbol + "')";

			PreparedStatement ps = connect.prepareStatement(SQLStatement);
			ps.executeUpdate();
			//System.out.println("--> Add stock to userwatchlist SQL executed\n" + SQLStatement);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// delete stock symbol from watchlist
	public void deleteStockFromWatchlist(Integer watchlistId, String stockSymbol, Connection connect) {

		try {

			String SQLStatement = "delete from WatchlistDetail where stock_symbol = '" + stockSymbol + "'";
			PreparedStatement ps = connect.prepareStatement(SQLStatement);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
