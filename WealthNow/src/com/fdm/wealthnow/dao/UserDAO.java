package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.util.DBUtil;

public class UserDAO extends DBUtil{
	
	public User getUser(int userId, InfoType type) throws Exception {
		Connection connect = getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		switch (type) {
		case BASIC:
			ps = connect.prepareStatement("Select user_id, user_name, first_name, last_name from User1 where user_id = ?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
		
			rs.next();
			User user = new User(userId, rs.getString("user_name"), rs.getString("first_name"), rs.getString("last_name"));
			
			return user;

		case FULL:
			return null;
		
		default:
			
			return null;
		}

	}
	
	public List<User> getAllUser() throws Exception {
		Connection connect = getConnection();
		List<User> users = new ArrayList<>();
		PreparedStatement ps = connect.prepareStatement("Select * from User1");
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			User user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("first_name"), rs.getString("last_name"));
			
			users.add(user);
		}
		
		return users;
	}
	
	public int addUser(User user) {
		
		return 0;
	}
	
	public boolean updateUser(User user) {
		
		return true;
	}
	
	public boolean softDeleteUser(int userId) {
		return false;
		
	}

}
