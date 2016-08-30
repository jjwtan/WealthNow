package com.fdm.wealthnow.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.common.Watchlist;
import com.fdm.wealthnow.dao.UserDAO;
import com.fdm.wealthnow.dao.WatchlistDAO;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class UserDAOTest {
	static UserDAO userDAO = new UserDAO();
	static Connection connect;

	@Before
	public void setUp() throws Exception {
		UserDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
		connect = UserDAO.getConnection();
		connect.setAutoCommit(false);
	}

	// test get all user 
	@Test
	public void testUserTable() {
		List<User> users = null;
		try {
			users = userDAO.getAllUser(connect);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (User user : users) {
			System.out.println(user.getUsername() + ": " + user);
		}
	}
	
	@Test
	public void testSingleBasicUser() {
		try {
			User user = userDAO.getUser(1, InfoType.FULL, connect);
			
			System.out.println(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSingleFullUser() {
		try {
			User user = userDAO.getUser(2, InfoType.FULL, connect);
			
			System.out.println("User:   \t" + user.getUsername() + "\n"
					  +"Name:   \t" + user.getFirstName() + " " + user.getLastName() + "\n"
					  +"Email:   \t" + user.getEmail() + "\n"
					  +"Address:\t" + user.getAddress() + "\n"
					  +"Phone:   \t" + user.getPhoneNumber() + "\n"
					  +"Birthday:\t" + user.getBirthday());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddUser() {
		//System.out.println("--> Inside testAddUser");
		
		List<User> users = new ArrayList<>();
		User newUser = new User();

		userDAO.addUser(4, "SamAccount", "password123", "Sam", "Potter", "19 Sep 2014", "harrypotter@hotmail.com", "89345621", "Happy Town #10-152", "Tan", connect);
		
		try {
			users = userDAO.getAllUser(connect);
			newUser = userDAO.getUser(4, InfoType.FULL, connect);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(4, users.size());
		assertEquals("SamAccount", newUser.getUsername());
		assertEquals("Sam", newUser.getFirstName());
		assertEquals("harrypotter@hotmail.com", newUser.getEmail());
	}
	
	@Test
	public void testUpdateUser(){
		
		User updatedUser = new User();
		
		userDAO.updateUser(3, "Tommy", "Tom", "Lee", "19 Sep 2014", "harrypotter@hotmail.com", "89345621", "Happy Town #10-152", "Tan", connect);
		
		try {
			updatedUser = userDAO.getUser(3, InfoType.FULL, connect);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("Tommy", updatedUser.getUsername());
		assertEquals("Lee", updatedUser.getLastName());
		assertEquals("harrypotter@hotmail.com", updatedUser.getEmail());
	}
	
	@After
	public void tearDown() {
		try {
			connect.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
