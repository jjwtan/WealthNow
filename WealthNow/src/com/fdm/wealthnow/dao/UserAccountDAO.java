package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fdm.wealthnow.common.UserAccount;
import com.fdm.wealthnow.util.DBUtil;

public class UserAccountDAO extends DBUtil{
	private static final String USER_ACCOUNT_TABLE = "useraccount";

	
	public UserAccount getAccountBalance(Connection connect, int userId) {
		try {
			PreparedStatement ps = connect.prepareStatement("select * from " + USER_ACCOUNT_TABLE + " where user_id = ?");
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
	
	public double debitBalance(Connection connect, int userId, double amountToDeduct) {
		if (amountToDeduct < 0) {
			System.out.println("invalid parameter amount");
			return -1;
		}
		try {
			PreparedStatement ps = connect.prepareStatement("Update " + USER_ACCOUNT_TABLE + " SET balance = balance - ? where user_id = ?");
			ps.setDouble(1, amountToDeduct);
			ps.setInt(2, userId);
			
			int result = ps.executeUpdate();
			
			if (result == 0) {
				System.out.println("user id does not exist");
				return -1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return getAccountBalance(connect, userId).getBalance();
	}
	
	public double creditBalance(Connection connect, int userId, double amountToCredit) {
		
		if (amountToCredit < 0) {
			System.out.println("invalid parameter amount");
			return -1;
		}
		try {
			PreparedStatement ps = connect.prepareStatement("Update " + USER_ACCOUNT_TABLE + " SET balance = balance + ? where user_id = ?");
			ps.setDouble(1, amountToCredit);
			ps.setInt(2, userId);
			
			int result = ps.executeUpdate();
			
			if (result == 0) {
				System.out.println("user id does not exist");
				return -1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return getAccountBalance(connect, userId).getBalance();
	}

}
