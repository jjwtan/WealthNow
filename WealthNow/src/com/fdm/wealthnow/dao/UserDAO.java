package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.util.DBUtil;

public class UserDAO extends DBUtil {
	private static final String USER_TABLE = "user1";

	public User getUser(int userId, InfoType type, Connection connect) throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		switch (type) {
		case BASIC:
			ps = connect.prepareStatement(
					"Select user_id, user_name, first_name, last_name from " + USER_TABLE + " where user_id = ?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			rs.next();
			User userBasic = new User(userId, rs.getString("user_name"), rs.getString("first_name"),
					rs.getString("last_name"));

			return userBasic;

		case FULL:
			ps = connect.prepareStatement("Select * from " + USER_TABLE + " where user_id = ?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			rs.next();
			User userFull = new User(userId, rs.getString("user_name"), rs.getString("first_name"),
					rs.getString("last_name"), rs.getDate("birthday"), rs.getString("email"), rs.getString("phone_num"),
					rs.getString("address"));

			return userFull;

		default:

			return null;
		}

	}

	public List<User> getAllUser(Connection connect) throws Exception {

		List<User> users = new ArrayList<>();
		PreparedStatement ps = connect.prepareStatement("Select * from User1");

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			User user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("first_name"),
					rs.getString("last_name"));

			users.add(user);
		}

		return users;
	}

	public void addUser(int userId, String username, String password, String firstName, String lastName,
			String birthday, String email, String phoneNumber, String address, String maidenName, Connection connect) {

		try {

			String SQLStatement = "INSERT INTO User1(user_id, user_name, user_password, first_name, last_name, birthday, email, address , phone_num, maiden_name) VALUES ("
					+ userId + ", '" + username + "', '" + password + "', '" + firstName + "', '" + lastName + "', '"
					+ birthday + "', '" + email + "', '" + address + "', '" + phoneNumber + "', '" + maidenName + "')";

			PreparedStatement ps = connect.prepareStatement(SQLStatement);
			ps.executeUpdate();
			System.out.println("--> Adding user SQL executed" + SQLStatement);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Allow user to change username, firstName, lastName, birthday, email,
	// phoneNumber, address, maidenName
	public void updateUser(int userId, String username, String firstName, String lastName, String birthday, String email,
			String phoneNumber, String address, String maidenName, Connection connect) {

		try {

			// NEED TO add phone number
			String SQLStatement = "UPDATE user1 set user_name = '" + username + "', first_name = '" + firstName
					+ "', last_name = '" + lastName + "', birthday = '" + birthday + "', email = '" + email
					+ "', address = '" + address + "', maiden_name = '" + maidenName + "', phone_num = '" + phoneNumber
					+ "' where user_id = " + userId;

			PreparedStatement ps = connect.prepareStatement(SQLStatement);
			ps.executeUpdate();
			System.out.println("--> Updating user SQL executed" + SQLStatement);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void updatePassword (int userId, String newPassword, Connection connect) {
		
		try {

			// NEED TO add phone number
			String SQLStatement = "UPDATE user1 set user_password = '" + newPassword + "' where user_id = " + userId;

			PreparedStatement ps = connect.prepareStatement(SQLStatement);
			ps.executeUpdate();
			System.out.println("--> Updating user's password SQL executed" + SQLStatement);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// skip for now
	public boolean softDeleteUser(int userId) {
		return false;

	}

}
