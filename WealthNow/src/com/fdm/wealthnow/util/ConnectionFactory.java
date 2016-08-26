package com.trading.util;

import java.sql.Connection;

public interface ConnectionFactory {
	public Connection getConnection() throws Exception;
}
