package com.fdm.wealthnow.dao;

import java.sql.Connection;

import com.fdm.wealthnow.util.DatabaseConnectionFactory;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class BaseDAO {

	private static ConnectionType connectionType = ConnectionType.POOLED_CONNECTION;
	
	public static void setConnectionType(ConnectionType connType){
		connectionType = connType;
	}
	
	public static Connection getConnection() throws Exception{
		return DatabaseConnectionFactory.getFactory(connectionType).getConnection();
	}
}
