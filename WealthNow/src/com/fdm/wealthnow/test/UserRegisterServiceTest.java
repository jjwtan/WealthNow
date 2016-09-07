package com.fdm.wealthnow.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.SecurityQnAndAns;
import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.dao.UserDAO;
import com.fdm.wealthnow.service.UserRegisterService;

public class UserRegisterServiceTest {
	static Connection connection;
	
	@After
	public void tearDown() {
		System.out.println("done");
		
	}

	@Test
	public void testAdd() {
		System.out.println("test adding");
		UserRegisterService urs = new UserRegisterService();
		System.out.println("reg service created");
		User user = new User(50, "hello", "Pika", "Chu", "10/10/1991", "asdsa@sfds", "654654564", "sdfsdfsD", "sdfsdfsdf");
		SecurityQnAndAns sqa = new SecurityQnAndAns(1, "booooooooooooo");
		String password = "helloworld";
		Float balance = 5210.50f;
		boolean toCommit = true;
		System.out.println("registering user");
		Connection connect = urs.registerUser(user, sqa, password, balance, toCommit);
		
		connection = connect;
		
		System.out.println("user registered: " + user.getUserId() );

		System.out.println("getting user");
		UserDAO userDAO = new UserDAO();
		try {
			User userGet = userDAO.getUser(50, InfoType.BASIC, connection);
			
			System.out.println(userGet.getFirstName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			connection.rollback();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
