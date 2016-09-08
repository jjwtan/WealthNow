package com.fdm.wealthnow.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class DBUtil {
	private static ConnectionType connectionType = ConnectionType.POOLED_CONNECTION;

	public static void setConnectionType(ConnectionType connType) {
		connectionType = connType;
	}

	public static Connection getConnection() throws Exception {
		return DatabaseConnectionFactory.getFactory(connectionType).getConnection();
	}

	public static Integer getSequenceID(String sequence_number) {

		Integer order_id = null;
		Connection connect = null;
		try {
			connect = getConnection();
			PreparedStatement ps = connect.prepareStatement("Select " + sequence_number + ".NEXTVAL FROM DUAL");

			ResultSet result = ps.executeQuery();

			while (result.next()) {
				order_id = result.getInt("NEXTVAL");
			}
			
			return order_id;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (connect != null)
				try {
					connect.close();
				} catch (Exception e) {
						e.printStackTrace();
				}
		}

		return null;
		
	
	
	}
	
	public static String convertDateObjToString(Date date){
		SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
		String datestr = format.format(date);
		return datestr;
	}
	public static Date convertStringToDateObject(String date){
		SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
		Date newDate = null;
		try {
			newDate = format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch bloc
			System.out.println(newDate);
			e.printStackTrace();
		}
		return newDate;
		
	}

}
