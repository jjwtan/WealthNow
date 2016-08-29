package com.fdm.wealthnow.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fdm.wealthnow.dao.UserDAO;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class UserTest {

	@Before
	public void setUp() throws Exception {
		UserDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
