package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderTypeEnum;
import com.fdm.wealthnow.common.PriceTypeEnum;
import com.fdm.wealthnow.common.TermEnum;
import com.fdm.wealthnow.util.DBUtil;

public class OrderDAO extends DBUtil {

	public Order getOrderFromOpenOrder(Integer order_id, Connection connect) {
		Order order = null;
		String SQL = "SELECT * FROM OPENORDER WHERE ORDER_ID =?";
		PreparedStatement ps;
		try {
			ps = connect.prepareStatement(SQL);
			ps.setInt(1, order_id);

			ResultSet result = ps.executeQuery();
			
			while (result.next()) {
				Integer user_id = result.getInt("user_id");
				String currency_code = result.getString("currency_code");
				String order_type = result.getString("order_type");
				Integer quantity = result.getInt("quantity");
				String stock_symbol = result.getString("stock_symbol");
				String price_type = result.getString("price_type");
				Date opening_order_date = result.getDate("opening_order_date");
				Double limit_price = result.getDouble("limit_price");
				String term = result.getString("term");
				String status = "OpenOrder";
				order = new Order(user_id, order_id, currency_code, OrderTypeEnum.valueOf(order_type), quantity, stock_symbol, PriceTypeEnum.valueOf(price_type),
						opening_order_date, limit_price, TermEnum.valueOf(term), status);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return order;

	}

	public Order getOrderFromProcessedOrder(Connection connect, Integer order_id) throws Exception {
		
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
			Double limit_price = result.getDouble("limit_price");
			String status = result.getString("status");
			Date order_completion_date = result.getDate("order_completion_date");
			Double closing_price = result.getDouble("closing_price");
			Double open_market_price = result.getDouble("open_market_price");

			order = new Order(user_id, order_id, currency_code, OrderTypeEnum.valueOf(order_type), quantity, stock_symbol, PriceTypeEnum.valueOf(price_type),
					place_order_date, limit_price, order_completion_date, status, closing_price, open_market_price);

		}
		
		return order;
	}

	public List getAllProcessedOrderFromUser(Connection connect, Integer user_id) throws Exception {
		List<Order> processedCompletedOrderfromUser = new ArrayList<Order>();

		String SQL = "SELECT * FROM PROCESSEDORDER WHERE STATUS = 'completed' AND USER_ID = ?";
		
		PreparedStatement ps = connect.prepareStatement(SQL);
		ps.setInt(1, user_id);

		ResultSet result = ps.executeQuery();
		System.out.println(SQL + "\nThe SQL statement above has been executed");

		while (result.next()) {
			Integer order_id = result.getInt("order_id");
			String currency_code = result.getString("currency_code");
			String order_type = result.getString("order_type");
			Integer quantity = result.getInt("quantity");
			String stock_symbol = result.getString("stock_symbol");
			String price_type = result.getString("price_type");
			Date place_order_date = result.getDate("place_order_date");
			Double limit_price = result.getDouble("limit_price");
			String status = result.getString("status");
			Date order_completion_date = result.getDate("order_completion_date");
			Double closing_price = result.getDouble("closing_price");
			Double open_market_price = result.getDouble("open_market_price");

			Order order = new Order(user_id, order_id, currency_code, OrderTypeEnum.valueOf(order_type), quantity, stock_symbol, PriceTypeEnum.valueOf(price_type),
					place_order_date, limit_price, order_completion_date, status, closing_price, open_market_price);
			processedCompletedOrderfromUser.add(order);
		}
		
		return processedCompletedOrderfromUser;
	}

	public List getAllSoldOrderInDatabase(Connection connect,Integer user_ID,Integer order_ID) throws Exception {
		List<Order> AllSoldOrderInDatabase = new ArrayList<Order>();

		String SQL = "SELECT * FROM PROCESSEDORDER WHERE STATUS = 'completed' AND USER_ID =" + user_ID + " AND ORDER_ID="
				+ order_ID;
		
		PreparedStatement ps = connect.prepareStatement(SQL);

		ResultSet result = ps.executeQuery();
		System.out.println(SQL + "\nThe SQL statement above has been executed");

		while (result.next()) {
			Integer order_id = result.getInt("order_id");
			Integer user_id = result.getInt("user_id");
			String currency_code = result.getString("currency_code");
			String order_type = result.getString("order_type");
			Integer quantity = result.getInt("quantity");
			String stock_symbol = result.getString("stock_symbol");
			String price_type = result.getString("price_type");
			Date place_order_date = result.getDate("place_order_date");
			Double limit_price = result.getDouble("limit_price");
			Date order_completion_date = result.getDate("order_completion_date");
			Double closing_price = result.getDouble("closing_price");
			Double open_market_price = result.getDouble("open_market_price");
			String status = result.getString("status");

			Order order = new Order( user_id,  order_id,  currency_code,  OrderTypeEnum.valueOf(order_type),  quantity,
					 stock_symbol,  PriceTypeEnum.valueOf(price_type),  place_order_date,  limit_price,
					 order_completion_date,  status,  closing_price,  open_market_price);
			AllSoldOrderInDatabase.add(order);
		}
		
		return AllSoldOrderInDatabase;
	}

	public void createProcessedOrderInDatabase(Connection connect,  Integer user_id,Integer order_id,
			String currency_code, String order_type, Integer quantity, String stock_symbol, String price_type,
			String opening_order_date, Double limit_price, String order_completion_date, String status,
			Double closing_price) {

		String SQL = "INSERT INTO PROCESSEDORDER (order_id, user_id, currency_code, "
				+ "order_type, quantity, stock_symbol, "
				+ "price_type, status ,place_order_date, limit_price, order_completion_date, closing_price) "
				+ "VALUES(" + order_id + ", " + user_id + ",'" + currency_code + "','" + order_type + "'," + quantity
				+ ",'" + stock_symbol + "','" + price_type + "','" + status + "','" + opening_order_date + "',"
				+ limit_price + ",'" + order_completion_date + "'," + closing_price + ")";

		
		PreparedStatement ps;
		try {
			ps = connect.prepareStatement(SQL);
			System.out.println(SQL);
			ps.executeUpdate();
			System.out.println("The SQL statement below has been executed\n" + SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public List getListOfOpenOrder(Connection connect, Integer count) {
		List<Order> OpenOrderList = new ArrayList<Order>();

		String SQL = "SELECT * FROM OPENORDER WHERE ROWNUM <= ?";
		
		PreparedStatement ps;
		try {
			ps = connect.prepareStatement(SQL);
			ps.setInt(1, count);

			ResultSet result = ps.executeQuery();
			System.out.println(SQL + "\nThe SQL statement above has been executed");

			while (result.next()) {
				Integer order_id = result.getInt("order_id");
				Integer user_id = result.getInt("user_id");
				String currency_code = result.getString("currency_code");
				String order_type = result.getString("order_type");
				Integer quantity = result.getInt("quantity");
				String stock_symbol = result.getString("stock_symbol");
				String price_type = result.getString("price_type");
				Date opening_order_date = result.getDate("opening_order_date");
				Double limit_price = result.getDouble("limit_price");
				String term = result.getString("term");
				String status = "OpenOrder";
				Order order = new Order(user_id, order_id, currency_code, OrderTypeEnum.valueOf(order_type), quantity, stock_symbol, PriceTypeEnum.valueOf(price_type),
						opening_order_date, limit_price, TermEnum.valueOf(term), status);
				OpenOrderList.add(order);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return OpenOrderList;
	}

	public void createOpenOrderInDatabase(Connection connect, Integer order_id, Integer user_id, String currency_code,
			String order_type, Integer quantity, String stock_symbol, String price_type, String opening_order_date,
			Double limit_price, String term, String status) throws Exception {

		String SQL = "INSERT INTO OPENORDER (order_id, user_id, currency_code, "
				+ "order_type, quantity, stock_symbol, " + "price_type, opening_order_date, limit_price, term) "
				+ "VALUES(" + order_id + ", " + user_id + ",'" + currency_code + "','" + order_type + "'," + quantity
				+ ",'" + stock_symbol + "','" + price_type + "','" + opening_order_date + "'," + limit_price + ",'"
				+ term + "')";
		
		PreparedStatement ps = connect.prepareStatement(SQL);
		System.out.println("Before executing update..");
		ps.executeUpdate();
		System.out.println(SQL + "\nThe SQL statement above has been executed");

		
	}

	public boolean deleteOpenOrderInDatabase(Connection connect, Integer order_id) {
		boolean success = false;
		String SQL = "DELETE FROM OPENORDER WHERE ORDER_ID =?";
		try {
			connect.setAutoCommit(false);
			PreparedStatement ps = connect.prepareStatement(SQL);
			ps.setInt(1, order_id);
			ps.executeUpdate();
			success = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		System.out.println(SQL + "\nThe SQL statement above has been executed");

		return success;
	}

}
