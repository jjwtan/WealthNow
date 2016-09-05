package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
		} else if(failCount < 5 ) {
			if(checkPassword(connect, username, password)) {
				resetLastFailedCountAndTime(connect, username);
				return new UserAuth(getUser(username, InfoType.BASIC), true);
			} else {
				setCurrentFailTime(connect, username);
				incrementFailCount(connect, username);
			}
		} else {
			System.out.println("max attempt reached");
			return new UserAuth(false, null, "Max login attempts reached\nYour account has been locked");
		}
		
		connect.close();
		return new UserAuth(false, null, "Invalid username or password");
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
	
	public static void setCurrentFailTime(Connection connect, String username) throws SQLException {
		PreparedStatement ps;
		ResultSet rs;
		ps = connect.prepareStatement("Update " + USER_TABLE + " Set last_failedlogin = TO_DATE(?, 'yyyy/mm/dd hh24:mi:ss') where user_name = ?");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		ps.setString(1, sdf.format(new Date()));
		ps.setString(2, username);
		ps.executeUpdate();

	}
	
	public static void resetLastFailedCountAndTime(Connection connect, String username) throws SQLException {
		PreparedStatement ps;
		
		ps = connect.prepareStatement("Update " + USER_TABLE + " Set fail_login_count = 0, last_failedlogin = null where user_name = ?" );
		ps.setString(1, username);
		ps.executeUpdate();
	}
	
	
}
