package com.fdm.wealthnow.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdm.wealthnow.dao.UserAccountDAO;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class UserAccountTest {

	static Connection connect;
	
	@Before
	public void setUp() throws Exception {
		UserAccountDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
		connect = UserAccountDAO.getConnection();
		connect.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
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
	
	@Test
	public void test() {
		UserAccountDAO userAccountDAO = new UserAccountDAO();
		System.out.println(userAccountDAO.getAccountBalance(connect, 1).getBalance());
	}

}
