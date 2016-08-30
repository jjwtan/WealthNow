package com.fdm.wealthnow.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdm.wealthnow.common.UserAuth;
import com.fdm.wealthnow.dao.AuthDAO;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class AuthDAOTest {
	
	static Connection connect;

	@Before
	public void setUp() throws Exception {
		AuthDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
		connect = AuthDAO.getConnection();
		connect.setAutoCommit(false);
		createUser();
	}

	private void createUser() {
		try {
			PreparedStatement ps = connect.prepareStatement("Insert into user1(user_id, user_name, user_password, first_name, birthday, email, address, phone_num, maiden_name, fail_login_count, last_failedlogin) values(?,?,?,?,?,?,?,?,?,?,  to_date(? , 'dd-MM-yyyy hh:mi'))");
			ps.setInt	(1, 12);
			ps.setString(2, "biscuit");
			ps.setString(3, "secret");
			ps.setString(4, "Biscuit");
			ps.setString(5, "12-jan-1984");
			ps.setString(6, "biscuit@bnet.com");
			ps.setString(7, "Candy Road 91");
			ps.setInt	(8, 654568741);
			ps.setString(9, "Cookie");
			ps.setInt	(10, 3);
			ps.setString(11, "15-sep-2016 10:45");


			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testPasswordChecking() {
		try {
			assertTrue(AuthDAO.checkPassword(connect, "biscuit", "secret"));
			assertFalse(AuthDAO.checkPassword(connect, "biscuit", "secret2"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				AuthDAO.getConnection().close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testFailCount() {
		try {
			assertEquals(3, AuthDAO.getFailedCount(connect, "biscuit"));
			AuthDAO.incrementFailCount(connect, "biscuit");
			assertEquals(4, AuthDAO.getFailedCount(connect, "biscuit"));
			AuthDAO.resetLastFailedCountAndTime(connect, "biscuit");
			assertEquals(0, AuthDAO.getFailedCount(connect, "biscuit"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testDateTime() {
		Date date;
		try {
			date = AuthDAO.getLastFailedTime(connect, "biscuit");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
			System.out.println("Date: " +  sdf.format(date));
			assertEquals("15/09/2016 10:45 AM", sdf.format(date));
			
			AuthDAO.resetLastFailedCountAndTime(connect, "biscuit");
			date = AuthDAO.getLastFailedTime(connect, "biscuit");
			assertNull(date);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	
	@Test
	public void testAuthenticate() {
		try {
			UserAuth userAuth = AuthDAO.authenticate(connect, "biscuit", "secret");
			assertTrue(userAuth.isAuthenticationSuccess());
			
			userAuth = AuthDAO.authenticate(connect, "biscuit", "houifdshuigk");
			assertFalse(userAuth.isAuthenticationSuccess());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() {
		try {
			connect.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
