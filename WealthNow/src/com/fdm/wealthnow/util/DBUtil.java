package com.fdm.wealthnow.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class DBUtil {
	private static ConnectionType connectionType = ConnectionType.POOLED_CONNECTION;

	public static void setConnectionType(ConnectionType connType) {
		connectionType = connType;
	}

	public static Connection getConnection() throws Exception {
		return DatabaseConnectionFactory.getFactory(connectionType).getConnection();
	}

	public static Integer getSequenceID(String sequence_number) throws Exception {

		Integer order_id = null;
		Connection connect = getConnection();
		PreparedStatement ps = connect.prepareStatement("Select " + sequence_number + ".NEXTVAL FROM DUAL");

		ResultSet result = ps.executeQuery();

		while (result.next()) {
			order_id = result.getInt("NEXTVAL");
		}
		
		return order_id;
	}

}
