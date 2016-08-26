package com.trading.util;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Get a connection from Server connection pool
 *
 */
public class ConnectionPoolFactory implements ConnectionFactory {

	@Override
	public Connection getConnection() throws Exception {
		// Lookup connection using JNDI protocol
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/OracleDB");
		Connection conn = ds.getConnection();

		return conn;
	}
}
