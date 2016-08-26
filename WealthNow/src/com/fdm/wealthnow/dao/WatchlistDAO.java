package com.fdm.wealthnow.dao;

import java.sql.*;
import java.util.List;

import com.fdm.wealthnow.common.Watchlist;

public class WatchlistDAO extends BaseDAO {
	
	// test push
	// return watchlist object 
	public static Watchlist getWatchlist(int watchlistId){
		
		Watchlist newWatchlist = new Watchlist();
		
		try {
			PreparedStatement ps= getConnection().prepareStatement("select watchlist_name, visibility, date_created, date_modified from watchlist where watchlist_id = ?");
			
			//execute query 
			ResultSet rs=ps.executeQuery();  
			boolean status=rs.next();  
			if (!status) {
				// No row found for user name
				// return failing reason 
			}
			
			String watchlistName = rs.getString("watchlist_name");
			String visibility = rs.getString("visibility");
			Date dateCreated = rs.getDate("date_created");
			Date dateLastEdited = rs.getDate("date_modified");
			
			rs.close();
			
			newWatchlist = new Watchlist(watchlistId, watchlistName, visibility, dateCreated, dateLastEdited);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return newWatchlist;
	};
	
	//get all watchlists belonging to one user
	public static List<Watchlist> getAllUserWatchlist(int userId){
		return null;
	};
	
	// return watchlist id 
	public static int addWatchlist(Watchlist watchlist){
		return 1;
	};
	
	public static void deleteWatchlist(int watchListId){
		
	};
	
	public static boolean updateWatchlist(Watchlist watchlist){
		return true;
	};

}
