package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fdm.wealthnow.common.UserAuth;
import com.fdm.wealthnow.util.DBUtil;

public class AuthDAO extends DBUtil{
	private static final String USER_TABLE = "user1";
	
	public static UserAuth authenticate(String username, String password) throws Exception {
		Connection connect = getConnection();
		
		checkPassword(connect, username, password);
		
		
		connect.close();
		return null;
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
	
	private void checkFailedCount() {
		
	}
	
	private void incrementFailCount() {
		
	}
	
	private void getLastFailedTime() {
		
	}
	
	private void resetLastFailedCountAndTime() {
		
	}
	
	
}
