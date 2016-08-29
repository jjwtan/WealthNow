package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.util.DBUtil;

public class OrderDAO extends DBUtil {

	public Order getOrderFromOpenOrder(Integer order_id) throws Exception {
		Connection connect = getConnection();
		String SQL = "SELECT * FROM OPENORDER WHERE ORDER_ID =?";
		PreparedStatement ps = connect.prepareStatement(SQL);
		ps.setInt(1, order_id);

		ResultSet result = ps.executeQuery();
		Order order = null;
		while (result.next()) {
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
			order = new Order(user_id, order_id, currency_code, order_type, quantity, stock_symbol, price_type,
					opening_order_date, limit_price, term, status);
		}
		connect.close();
		return order;

	}

	public Order getOrderFromProcessedOrder(Integer order_id) throws Exception {
		Connection connect = getConnection();
		String SQL = "SELECT * FROM PROCESSEDORDER WHERE ORDER_ID =?";
		PreparedStatement ps = connect.prepareStatement(SQL);
		ps.setInt(1, order_id);

		ResultSet result = ps.executeQuery();
		Order order = null;
		while (result.next()) {
			Integer user_id = result.getInt("user_id");
			String currency_code = result.getString("currency_code");
			String order_type = result.getString("order_type");
			Integer quantity = result.getInt("quantity");
			String stock_symbol = result.getString("stock_symbol");
			String price_type = result.getString("price_type");
			Date place_order_date = result.getDate("place_order_date");
			Float limit_price = result.getFloat("limit_price");
			String status = result.getString("status");
			Date order_completion_date = result.getDate("order_completion_date");
			Float closing_price = result.getFloat("closing_price");
			Float open_market_price = result.getFloat("open_market_price");

			order = new Order(user_id, order_id, currency_code, order_type, quantity, stock_symbol, price_type,
					place_order_date, limit_price, order_completion_date, status, closing_price, open_market_price);

		}
		connect.close();
		return order;
	}

	public List getAllProcessedOrderFromUser(Integer user_id) throws Exception {
		List<Order> processedCompletedOrderfromUser = new ArrayList<Order>();

		String SQL = "SELECT * FROM PROCESSEDORDER WHERE STATUS = 'completed' AND USER_ID = ?";
		Connection connect = getConnection();
		PreparedStatement ps = connect.prepareStatement(SQL);
		ps.setInt(1, user_id);

		ResultSet result = ps.executeQuery();
		System.out.println( SQL +"\nThe SQL statement above has been executed" );


		while (result.next()) {
			Integer order_id = result.getInt("order_id");
			String currency_code = result.getString("currency_code");
			String order_type = result.getString("order_type");
			Integer quantity = result.getInt("quantity");
			String stock_symbol = result.getString("stock_symbol");
			String price_type = result.getString("price_type");
			Date place_order_date = result.getDate("place_order_date");
			Float limit_price = result.getFloat("limit_price");
			String status = result.getString("status");
			Date order_completion_date = result.getDate("order_completion_date");
			Float closing_price = result.getFloat("closing_price");
			Float open_market_price = result.getFloat("open_market_price");

			Order order = new Order(user_id, order_id, currency_code, order_type, quantity, stock_symbol, price_type,
					place_order_date, limit_price, order_completion_date, status, closing_price, open_market_price);
			processedCompletedOrderfromUser.add(order);
		}
		connect.close();
		return processedCompletedOrderfromUser;
	}

	public List getAllSoldOrderInDatabase() throws Exception {
		List<Order> AllSoldOrderInDatabase = new ArrayList<Order>();

		String SQL = "SELECT * FROM PROCESSEDORDER WHERE STATUS = 'completed'";
		Connection connect = getConnection();
		PreparedStatement ps = connect.prepareStatement(SQL);

		ResultSet result = ps.executeQuery();
		System.out.println( SQL +"\nThe SQL statement above has been executed" );


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
		connect.close();
		return AllSoldOrderInDatabase;
	}

	public void createProcessedOrderInDatabase(Integer order_id, Integer user_id, String currency_code,
			String order_type, Integer quantity, String stock_symbol, String price_type, String opening_order_date,
			Double limit_price, String order_completion_date, String status, Double closing_price) throws Exception {

		String SQL = "INSERT INTO PROCESSEDORDER (order_id, user_id, currency_code, "
				+ "order_type, quantity, stock_symbol, "
				+ "price_type, status ,place_order_date, limit_price, order_completion_date, closing_price) "
				+ "VALUES(" + order_id + ", " + user_id + ",'" + currency_code + "','" + order_type + "'," + quantity
				+ ",'" + stock_symbol + "','" + price_type + "','" + status + "','" + opening_order_date + "',"
				+ limit_price + ",'" + order_completion_date + "'," + limit_price + ")";

		Connection connect = getConnection();
		PreparedStatement ps = connect.prepareStatement(SQL);
		System.out.println(SQL);
		ps.executeUpdate();
		System.out.println("The SQL statement below has been executed\n" + SQL);
		connect.close();

	}

	public List getListOfOpenOrder(Integer count) throws Exception {
		List<Order> OpenOrderList = new ArrayList<Order>();

		String SQL = "SELECT * FROM OPENORDER WHERE ROWNUM <= ?";
		Connection connect = getConnection();
		PreparedStatement ps = connect.prepareStatement(SQL);
		ps.setInt(1, count);

		ResultSet result = ps.executeQuery();
		System.out.println( SQL +"\nThe SQL statement above has been executed" );

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
		connect.close();
		return OpenOrderList;
	}

	public void createOpenOrderInDatabase(Integer order_id, Integer user_id, String currency_code, String order_type,
			Integer quantity, String stock_symbol, String price_type, String opening_order_date, Double limit_price,
			String term, String status) throws Exception {

		String SQL = "INSERT INTO OPENORDER (order_id, user_id, currency_code, "
				+ "order_type, quantity, stock_symbol, " + "price_type, opening_order_date, limit_price, term) "
				+ "VALUES(" + order_id + ", " + user_id + ",'" + currency_code + "','" + order_type + "'," + quantity
				+ ",'" + stock_symbol + "','" + price_type + "','" + opening_order_date + "'," + limit_price + ",'"
				+ term + "')";
		Connection connect = getConnection();
		PreparedStatement ps = connect.prepareStatement(SQL);
		System.out.println("Before executing update..");
		ps.executeUpdate();
		System.out.println( SQL +"\nThe SQL statement above has been executed" );

		connect.close();
	}

	public boolean deleteOpenOrderInDatabase(Integer order_id) throws Exception {
		boolean success;
		Connection connect = null;
		String SQL = "DELETE FROM OPENORDER WHERE ORDER_ID =?";
		connect = getConnection();
		connect.setAutoCommit(false);
		PreparedStatement ps = connect.prepareStatement(SQL);
		ps.setInt(1, order_id);
		ps.executeUpdate();
		connect.commit();
		success = true;
		connect.close();

		System.out.println( SQL +"\nThe SQL statement above has been executed" );


		return success;
	}

}
