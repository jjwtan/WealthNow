package com.fdm.wealthnow.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.dao.UserDAO;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class UserDAOTest {
	static UserDAO userDAO = new UserDAO();

	@Before
	public void setUp() throws Exception {
		UserDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
	}

	@Test
	public void testUserTable() {
		List<User> users = null;
		try {
			users = userDAO.getAllUser();
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
			User user = userDAO.getUser(1, InfoType.FULL);
			
			
			System.out.println(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSingleFullUser() {
		try {
			User user = userDAO.getUser(2, InfoType.FULL);
			
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

}
