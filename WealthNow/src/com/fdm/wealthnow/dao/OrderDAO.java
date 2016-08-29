package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.util.DBUtil;

public class OrderDAO extends DBUtil {
	
	public Integer getOrder(Integer order_id) throws Exception{
		Connection connect = getConnection();
		
		return new Integer(1);
	}

	public List getAllProcessedOrderFromUser(Integer user_id) throws Exception {
		List<Order> processedCompletedOrderfromUser = new ArrayList<Order>();

		String SQL = "SELECT * FROM PROCESSEDORDER WHERE STATUS = 'completed' AND USER_ID = ?";
		Connection connect = getConnection();
		PreparedStatement ps = connect.prepareStatement(SQL);
		ps.setInt(1, user_id);

		ResultSet result = ps.executeQuery();
		System.out.println("The SQL statement below has been executed\n" + SQL);

		while (result.next()) {
			Integer order_id = result.getInt("order_id");
			String currency_code = result.getString("currency_code");
			String order_type = result.getString("order_type");
			Integer quantity = result.getInt("quantity");
			String stock_symbol = result.getString("stock_symbol");
			String price_type = result.getString("price_type");
			Date opening_order_date = result.getDate("opening_order_date");
			Float limit_price = result.getFloat("limit_price");
			String term = result.getString("term");
			String status = "OpenOrder";
			Order order = new Order(user_id, order_id, currency_code, order_type, quantity, stock_symbol, price_type,
					opening_order_date, limit_price, term, status);
			processedCompletedOrderfromUser.add(order);
		}

		return processedCompletedOrderfromUser;
	}

	public List getAllSoldOrderInDatabase() throws Exception {
		List<Order> AllSoldOrderInDatabase = new ArrayList<Order>();

		String SQL = "SELECT * FROM PROCESSEDORDER WHERE STATUS = 'completed'";
		Connection connect = getConnection();
		PreparedStatement ps = connect.prepareStatement(SQL);

		ResultSet result = ps.executeQuery();
		System.out.println("The SQL statement below has been executed\n" + SQL);

		while (result.next()) {
			Integer order_id = result.getInt("order_id");
			Integer user_id = result.getInt("user_id");
			String currency_code = result.getString("currency_code");
			String order_type = result.getString("order_type");
			Integer quantity = result.getInt("quantity");
			String stock_symbol = result.getString("stock_symbol");
			String price_type = result.getString("price_type");
			Date opening_order_date = result.getDate("opening_order_date");
			Float limit_price = result.getFloat("limit_price");
			String term = result.getString("term");
			String status = "OpenOrder";
			Order order = new Order(user_id, order_id, currency_code, order_type, quantity, stock_symbol, price_type,
					opening_order_date, limit_price, term, status);
			AllSoldOrderInDatabase.add(order);
		}

		return AllSoldOrderInDatabase;
	}

	public void createProcessedOrderInDatabase(Integer user_id, Integer order_id, String currency_code,
			String order_type, Integer quantity, String stock_symbol, String price_type, Date opening_order_date,
			Float limit_price, Date order_completion_date, String status, Float closing_price) throws Exception {

		String SQL = "INSERT INTO PROCESSEDORDER (order_id, user_id, currency_code, "
				+ "order_type, quantity, stock_symbol, "
				+ "price_type, status ,place_order_date, limit_price, order_completion_date, closing_price) "
				+ "VALUES("+ getSequenceID("order_id_seq")+", '" + user_id + "','" + currency_code + "','" + order_type + "',"
				+ quantity + ",'" + stock_symbol + "','" + price_type + "','" + status + "','" + opening_order_date
				+ "'," + limit_price + "','" + order_completion_date + "'," + limit_price + ")";

		Connection connect = getConnection();
		PreparedStatement ps = connect.prepareStatement(SQL);
		ps.executeUpdate();
		System.out.println("The SQL statement below has been executed\n" + SQL);

	}

	public List getListOfOpenOrder(Integer count) throws Exception {
		List<Order> OpenOrderList = new ArrayList<Order>();

		String SQL = "SELECT * FROM OPENORDER WHERE ROWNUM <= ?";
		Connection connect = getConnection();
		PreparedStatement ps = connect.prepareStatement(SQL);
		ps.setInt(1, count);
		
		ResultSet result = ps.executeQuery();
		System.out.println("The SQL statement below has been executed\n" + SQL);

		while (result.next()) {
			Integer order_id = result.getInt("order_id");
			Integer user_id = result.getInt("user_id");
			String currency_code = result.getString("currency_code");
			String order_type = result.getString("order_type");
			Integer quantity = result.getInt("quantity");
			String stock_symbol = result.getString("stock_symbol");
			String price_type = result.getString("price_type");
			Date opening_order_date = result.getDate("opening_order_date");
			Float limit_price = result.getFloat("limit_price");
			String term = result.getString("term");
			String status = "OpenOrder";
			Order order = new Order(user_id, order_id, currency_code, order_type, quantity, stock_symbol, price_type,
					opening_order_date, limit_price, term, status);
			OpenOrderList.add(order);
		}

		return OpenOrderList;
	}

	public void createOpenOrderInDatabase(Integer user_id, Integer order_id, String currency_code, String order_type,
			Integer quantity, String stock_symbol, String price_type, String opening_order_date, Double limit_price,
			String term, String status) throws Exception {

		String SQL = "INSERT INTO OPENORDER (order_id, user_id, currency_code, "
				+ "order_type, quantity, stock_symbol, " + "price_type, opening_order_date, limit_price, term) "
				+ "VALUES("+ getSequenceID("order_id_seq")+", " + user_id + ",'" + currency_code + "','" + order_type + "',"
				+ quantity + ",'" + stock_symbol + "','" + price_type + "','" + opening_order_date + "'," + limit_price
				+ ",'" + term + "')";
		Connection connect = getConnection();
		System.out.println(SQL);
		PreparedStatement ps = connect.prepareStatement(SQL);
		ps.executeUpdate();
		System.out.println("The SQL statement below has been executed\n" + SQL);

	}

	public boolean deleteOpenOrderInDatabase(Integer order_id) throws Exception {
		
		Connection connect = getConnection();
		connect.setAutoCommit(false);
		String SQL = "DELETE FROM OPENORDER WHERE ORDER_ID =?";
		PreparedStatement ps = connect.prepareStatement(SQL);
		ps.setInt(1, order_id);
		ps.executeUpdate();
		connect.commit();
		System.out.println("The SQL statement below has been executed\n" + SQL);
		return true;
	}

}
