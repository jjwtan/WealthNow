package com.fdm.wealthnow.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.Watchlist;
import com.fdm.wealthnow.util.DBUtil;

import oracle.jdbc.proxy.annotation.Pre;

public class WatchlistDAO extends DBUtil {

	// return watchlist object
	public static Watchlist getWatchlist(int watchlistId) {

		Watchlist newWatchlist = new Watchlist();

		try {
			Connection connect = getConnection();
			// SQL statement
			String SQLStatement = "select watchlist_name, visibility, date_created, date_modified from watchlist where watchlist_id = ?";
			PreparedStatement ps = connect.prepareStatement(SQLStatement);
			// insert the first ? as watchlistId
			ps.setInt(1, watchlistId);

			// execute query
			ResultSet result = ps.executeQuery();
			System.out.println("The SQL statement below has been executed\n" + SQLStatement);

			while (result.next()) {
				String watchlistName = result.getString("watchlist_name");
				String visibility = result.getString("visibility");
				Date dateCreated = result.getDate("date_created");
				Date dateLastEdited = result.getDate("date_modified");
				newWatchlist = new Watchlist(watchlistId, watchlistName, visibility, dateCreated, dateLastEdited);
			}
			
			connect.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return newWatchlist;
	};

	// get all watchlists belonging to one user
	public static List<Watchlist> getAllUserWatchlist(int userId) {
		
		System.out.println("Inside getAllUserWatchlist in WatchlistDAO");
		List<Watchlist> watchlistsBelongingToUser = new ArrayList<Watchlist>();

		try {
			Connection connect = getConnection();
			// SQL statement
			String SQLStatement = "Select watchlist.watchlist_id, watchlist.watchlist_name, watchlist.visibility, watchlist.date_created, watchlist.date_modified from watchlist inner join userwatchlist on watchlist.watchlist_id = userwatchlist.watchlist_id inner join user1 on user1.user_id = userwatchlist.user_id where user1.user_id = ?";
			PreparedStatement ps = connect.prepareStatement(SQLStatement);
			// insert the first ? as watchlistId
			ps.setInt(1, userId);

			// execute query
			ResultSet result = ps.executeQuery();
			System.out.println("The SQL statement below has been executed\n" + SQLStatement);

			while (result.next()) {
				Integer watchlistId = result.getInt("watchlist_id");
				String watchlistName = result.getString("watchlist_name");
				String visibility = result.getString("visibility");
				Date dateCreated = result.getDate("date_created");
				Date dateLastEdited = result.getDate("date_modified");
				Watchlist newWatchlist = new Watchlist(watchlistId, watchlistName, visibility, dateCreated, dateLastEdited);
				watchlistsBelongingToUser.add(newWatchlist);		
			}
			
			connect.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return watchlistsBelongingToUser;
	};

	// return watchlist id
	public static int addWatchlist(Watchlist watchlist, Integer userId) {
		
		System.out.println("Inside addWatchlist in WatchlistDAO");
		Watchlist watchlistToAdd = new Watchlist();
		
		try {
			Connection connect = getConnection();
			
			// PART 1: insert record into watchlist 
			String SQLStatement = "INSERT INTO Watchlist(watchlist_id, watchlist_name, visibility, date_created, date_modified) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = connect.prepareStatement(SQLStatement);
			
			ps.setInt(1, watchlist.getWatchlistId());
			ps.setString(2, watchlist.getWatchlistName());
			ps.setString(3, watchlist.getVisibility());
			ps.setDate(4, (Date) watchlist.getDateCreated());
			ps.setDate(5, (Date) watchlist.getDateLastEdited());
			
			ps.executeUpdate();
			System.out.println("The SQL statement below has been executed\n" + SQLStatement);
			
			// PART 2: insert userid to watchlistid into mapping table
			String SQLStatement2 = "INSERT INTO UserWatchlist(user_id , watchlist_id) VALUES (?, ?)";
			PreparedStatement ps2 = connect.prepareStatement(SQLStatement2);
			
			ps2.setInt(1, userId);
			ps2.setInt(2, watchlist.getWatchlistId());
			
			ps2.executeUpdate(); 
			System.out.println("The SQL statement below has been executed\n" + SQLStatement2);
			
			// PART 3: commit
			String SQLStatement3 = "commit";
			PreparedStatement ps3 = connect.prepareStatement(SQLStatement3);
			ps3.executeQuery();
			System.out.println("The SQL statement below has been executed\n" + SQLStatement3);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return watchlist.getWatchlistId();
	};

	public static void deleteWatchlist(int watchListId) {

	};

	public static boolean updateWatchlist(Watchlist watchlist) {
		return true;
	};

}
