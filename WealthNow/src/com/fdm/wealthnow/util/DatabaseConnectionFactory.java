package com.trading.util;

import java.util.HashMap;

/**
 * Returns the appropriate connection factory
 */
public class DatabaseConnectionFactory {
	private static HashMap<ConnectionType, ConnectionFactory> map = new HashMap<>();

	static {
		map.put(ConnectionType.LOCAL_CONNECTION, LocalConnectionfactory.getInstance());  // Direct DB connection
		map.put(ConnectionType.POOLED_CONNECTION, new ConnectionPoolFactory());			 // DB Connection Pool 
	}

	public static enum ConnectionType {
		LOCAL_CONNECTION, POOLED_CONNECTION;
	}

	public static ConnectionFactory getFactory(ConnectionType type) {
		return map.get(type);
	}
}
