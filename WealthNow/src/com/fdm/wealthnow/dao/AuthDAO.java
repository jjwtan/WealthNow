package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.common.UserAuth;
import com.fdm.wealthnow.util.DBUtil;

public class AuthDAO extends DBUtil{
	private static final String USER_TABLE = "user1";
	
	public static UserAuth authenticate(Connection connect, String username, String password) throws Exception {
		
		int failCount = getFailedCount(connect, username);
		if (failCount == -1 ) {
			System.out.println("username does not exist");
		} else if(failCount <= 5 ) {
			if(checkPassword(connect, username, password)) {
				return new UserAuth(getUser(username, InfoType.BASIC), true);
			} else {
				incrementFailCount(connect, username);
			}
		} else {
			System.out.println("max attempt reached");
		}
		
		connect.close();
		return new UserAuth(false);
	}
	
	public static User getUser(String username, InfoType type) throws Exception {
		Connection connect = getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;

	
		ps = connect.prepareStatement("Select user_id, user_name, first_name, last_name from " +  USER_TABLE + " where user_name = ?");
		ps.setString(1, username);
		rs = ps.executeQuery();
	
		rs.next();
		User userBasic = new User( rs.getInt("user_id"), rs.getString("user_name"), rs.getString("first_name"), rs.getString("last_name"));
		
		return userBasic;

	}
	
	
	
	public static boolean checkPassword(Connection connect, String username, String password) throws SQLException {
		PreparedStatement ps;
		ResultSet rs;
		ps = connect.prepareStatement("Select user_name, user_password from " +  USER_TABLE + " where user_name = ? and user_password = ?");
		ps.setString(1, username);
		ps.setString(2, password);
		rs = ps.executeQuery();
		
		return rs.next();
	}
	
	public static int getFailedCount(Connection connect, String username) throws SQLException {
		
		PreparedStatement ps;
		ResultSet rs;
		ps = connect.prepareStatement("Select fail_login_count from " +  USER_TABLE + " where user_name = ?");
		ps.setString(1, username);
		rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getInt("fail_login_count");
		} else {
			return -1;
		}
	}
	
	public static void incrementFailCount(Connection connect, String username) throws SQLException {
		PreparedStatement ps;

		ps = connect.prepareStatement("Update " + USER_TABLE + " Set fail_login_count = fail_login_count + 1 where user_name = ?" );
		ps.setString(1, username);
		ps.executeUpdate();
	}
	
	public static Date getLastFailedTime(Connection connect, String username) throws SQLException {
		PreparedStatement ps;
		ResultSet rs;
		ps = connect.prepareStatement("Select last_failedlogin from " +  USER_TABLE + " where user_name = ?");
		ps.setString(1, username);
		rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getTimestamp("last_failedLogin");
		}
		return null;
	}
	
	public static Date setCurrentFailTime(Connection connect, String username) throws SQLException {
		PreparedStatement ps;
		ResultSet rs;
		ps = connect.prepareStatement("Update " + USER_TABLE + " Set fail_login_count = 0, last_failedlogin = null where user_name = ?");
		ps.setString(1, username);
		rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getDate("last_failedlogin");
		}
		return null;
	}
	
	public static void resetLastFailedCountAndTime(Connection connect, String username) throws SQLException {
		PreparedStatement ps;
		
		ps = connect.prepareStatement("Update " + USER_TABLE + " Set fail_login_count = 0, last_failedlogin = null where user_name = ?" );
		ps.setString(1, username);
		ps.executeUpdate();
	}
	
	
}
