package com.fdm.wealthnow.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class LocalConnectionfactory implements ConnectionFactory {
	
	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";  
	private static final String CONNECTION_URL="jdbc:oracle:thin:@159.100.176.123:1521:XE";  
	private static final String USERNAME="fdm01";  
	private static final String PASSWORD="fdm01";  
	
	private static LocalConnectionfactory factory = new LocalConnectionfactory();
	
	static {
		try {
			Class.forName(DRIVER);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static LocalConnectionfactory getInstance() {
		return factory;
	}

	public Connection getConnection() throws Exception {
		return DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
	}

}
