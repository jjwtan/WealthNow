package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fdm.wealthnow.common.UserAccount;
import com.fdm.wealthnow.util.DBUtil;

public class UserAccountDAO extends DBUtil{
	private static final String USER_TABLE = "useraccount";

	
	public UserAccount getAccountBalance(Connection connect, int userId) {
		try {
			PreparedStatement ps = connect.prepareStatement("select * from " + USER_TABLE + " where user_id = ?");
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return new UserAccount(userId, rs.getDouble("balance"), rs.getString("currency"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
