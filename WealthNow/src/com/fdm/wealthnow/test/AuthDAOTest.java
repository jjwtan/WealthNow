package com.fdm.wealthnow.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
			PreparedStatement ps = connect.prepareStatement("Insert into user1(user_id, user_name, user_password, first_name, birthday, email, address, phone_num, maiden_name) values(?,?,?,?,?,?,?,?,?)");
			ps.setInt	(1, 12);
			ps.setString(2, "biscuit");
			ps.setString(3, "secret");
			ps.setString(4, "Biscuit");
			ps.setString(5, "12-jan-1984");
			ps.setString(6, "biscuit@bnet.com");
			ps.setString(7, "Candy Road 91");
			ps.setInt	(8, 654568741);
			ps.setString(9, "Cookie");

		
			ps.executeUpdate();
			System.out.println("query executed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void test() {
		try {
			System.out.println(AuthDAO.checkPassword(connect, "biscuit", "secret"));
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
	
	@After
	public void tearDown() {
		try {
			connect.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
