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
				Double total_price_deducted = result.getDouble("total_price_deducted");
				Integer old_order_id = result.getInt("old_order_id");
				if (term == null) {
					order = new Order(user_id, order_id, currency_code, OrderTypeEnum.valueOf(order_type), quantity,
							stock_symbol, PriceTypeEnum.valueOf(price_type), opening_order_date, limit_price, null,
							status, total_price_deducted, old_order_id);
				} else {
					order = new Order(user_id, order_id, currency_code, OrderTypeEnum.valueOf(order_type), quantity,
							stock_symbol, PriceTypeEnum.valueOf(price_type), opening_order_date, limit_price,
							TermEnum.valueOf(term), status, total_price_deducted, old_order_id);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return order;

	}

	public Order getOrderFromProcessedOrder(Connection connect, Integer order_id) {

		String SQL = "SELECT * FROM PROCESSEDORDER WHERE ORDER_ID =?";
		PreparedStatement ps;
		Order order = null;
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
				Date place_order_date = result.getDate("place_order_date");
				Double limit_price = result.getDouble("limit_price");
				String status = result.getString("status");
				Date order_completion_date = result.getDate("order_completion_date");
				Double closing_price = result.getDouble("closing_price");
				Double total_price_deducted = result.getDouble("total_price_deducted");
				Integer old_order_id = null;

				order = new Order(user_id, order_id, currency_code, OrderTypeEnum.valueOf(order_type), quantity,
						stock_symbol, PriceTypeEnum.valueOf(price_type), place_order_date, limit_price,
						order_completion_date, status, closing_price, total_price_deducted, old_order_id);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("catch at OrderDAO");
			e.printStackTrace();
		}

		return order;
	}

	public List getAllProcessedOrderFromUser(Connection connect, Integer user_id) throws Exception {
		List<Order> processedCompletedOrderfromUser = new ArrayList<Order>();

		String SQL = "SELECT * FROM PROCESSEDORDER WHERE USER_ID = ? ORDER BY order_id desc";

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
			Double total_price_deducted = result.getDouble("total_price_deducted");
			Integer old_order_id = null;

			Order order = new Order(user_id, order_id, currency_code, OrderTypeEnum.valueOf(order_type), quantity,
					stock_symbol, PriceTypeEnum.valueOf(price_type), place_order_date, limit_price,
					order_completion_date, status, closing_price, total_price_deducted, old_order_id);
			processedCompletedOrderfromUser.add(order);
		}

		return processedCompletedOrderfromUser;
	}

	public List getCompletedOrdersFromDatabase(Connection connect, Integer user_id) throws Exception {
		List<Order> processedCompletedOrderfromUser = new ArrayList<Order>();

		String SQL = "SELECT * FROM PROCESSEDORDER WHERE USER_ID = ? AND STATUS = 'completed' ORDER BY order_id desc";

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
			Double total_price_deducted = result.getDouble("total_price_deducted");
			Integer old_order_id = null;

			Order order = new Order(user_id, order_id, currency_code, OrderTypeEnum.valueOf(order_type), quantity,
					stock_symbol, PriceTypeEnum.valueOf(price_type), place_order_date, limit_price,
					order_completion_date, status, closing_price, total_price_deducted, old_order_id);
			processedCompletedOrderfromUser.add(order);
		}

		return processedCompletedOrderfromUser;
	}

	public List getCancelledOrdersFromDatabase(Connection connect, Integer user_id) throws Exception {
		List<Order> processedCompletedOrderfromUser = new ArrayList<Order>();

		String SQL = "SELECT * FROM PROCESSEDORDER WHERE USER_ID = ? AND STATUS = 'cancelled' ORDER BY order_id desc";

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
			Double total_price_deducted = result.getDouble("total_price_deducted");
			Integer old_order_id = null;

			Order order = new Order(user_id, order_id, currency_code, OrderTypeEnum.valueOf(order_type), quantity,
					stock_symbol, PriceTypeEnum.valueOf(price_type), place_order_date, limit_price,
					order_completion_date, status, closing_price, total_price_deducted, old_order_id);
			processedCompletedOrderfromUser.add(order);
		}

		return processedCompletedOrderfromUser;
	}

	public List getOpenOrdersFromDatabase(Connection connect, Integer user_id) throws Exception {
		List<Order> processedCompletedOrderfromUser = new ArrayList<Order>();

		String SQL = "SELECT * FROM OPENORDER WHERE USER_ID = ? ORDER BY order_id desc";

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
			Date opening_order_date = result.getDate("opening_order_date");
			Double limit_price = result.getDouble("limit_price");
			String term = result.getString("term");

			Integer old_order_id = null;

			Order order = new Order(user_id, order_id, currency_code, OrderTypeEnum.valueOf(order_type), quantity,
					stock_symbol, PriceTypeEnum.valueOf(price_type), opening_order_date, limit_price,
					TermEnum.valueOf(term), "", new Double(0.00), old_order_id);
		
			processedCompletedOrderfromUser.add(order);
		}

		return processedCompletedOrderfromUser;
	}

	public List getAllSoldOrderInDatabase(Connection connect, Integer user_ID, Integer order_ID) throws Exception {
		List<Order> AllSoldOrderInDatabase = new ArrayList<Order>();

		String SQL = "SELECT * FROM PROCESSEDORDER WHERE STATUS = 'completed' AND USER_ID =" + user_ID
				+ " AND ORDER_ID=" + order_ID;

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
			Double total_price_deducted = result.getDouble("total_price_deducted");
			String status = result.getString("status");
			Integer old_order_id = null;

			Order order = new Order(user_id, order_id, currency_code, OrderTypeEnum.valueOf(order_type), quantity,
					stock_symbol, PriceTypeEnum.valueOf(price_type), place_order_date, limit_price,
					order_completion_date, status, closing_price, total_price_deducted, old_order_id);
			AllSoldOrderInDatabase.add(order);
		}

		return AllSoldOrderInDatabase;
	}

	public void createProcessedOrderInDatabase(Connection connect, Integer user_id, Integer order_id,
			String currency_code, String order_type, Integer quantity, String stock_symbol, String price_type,
			String opening_order_date, Double limit_price, String order_completion_date, String status,
			Double closing_price, Double total_price_deducted) {

		String SQL = "INSERT INTO PROCESSEDORDER (order_id, user_id, currency_code, "
				+ "order_type, quantity, stock_symbol, "
				+ "price_type, status ,place_order_date, limit_price, order_completion_date, closing_price , total_price_deducted) "
				+ "VALUES(" + order_id + ", " + user_id + ",'" + currency_code + "','" + order_type + "'," + quantity
				+ ",'" + stock_symbol + "','" + price_type + "','" + status + "','" + opening_order_date + "',"
				+ limit_price + ",'" + order_completion_date + "'," + closing_price + "," + total_price_deducted + ")";

		PreparedStatement ps;
		try {
			ps = connect.prepareStatement(SQL);

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
			Order order = null;
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
				Double total_price_deducted = result.getDouble("total_price_deducted");
				Integer old_order_id = result.getInt("old_order_id");
				if (term == null) {
					order = new Order(user_id, order_id, currency_code, OrderTypeEnum.valueOf(order_type), quantity,
							stock_symbol, PriceTypeEnum.valueOf(price_type), opening_order_date, limit_price, null,
							status, total_price_deducted, old_order_id);
				} else {
					order = new Order(user_id, order_id, currency_code, OrderTypeEnum.valueOf(order_type), quantity,
							stock_symbol, PriceTypeEnum.valueOf(price_type), opening_order_date, limit_price,
							TermEnum.valueOf(term), status, total_price_deducted, old_order_id);
				}
				OpenOrderList.add(order);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return OpenOrderList;
	}

	public void createOpenOrderInDatabase(Connection connect, Integer old_order_id, Integer order_id, Integer user_id,
			String currency_code, String order_type, Integer quantity, String stock_symbol, String price_type,
			String opening_order_date, Double limit_price, String term, String status, Double total_price_deducted) {
		String SQL = null;
		if (old_order_id != null) {
			SQL = "INSERT INTO OPENORDER (order_id, user_id, currency_code, " + "order_type, quantity, stock_symbol, "
					+ "price_type, opening_order_date, limit_price, " + "total_price_deducted, old_order_id) "
					+ "VALUES(" + order_id + ", " + user_id + ",'" + currency_code + "','" + order_type + "',"
					+ quantity + ",'" + stock_symbol + "','" + price_type + "','" + opening_order_date + "',"
					+ limit_price + "," + total_price_deducted + "," + old_order_id + ")";

		} else {

			if (term.equalsIgnoreCase("null")) {
				SQL = "INSERT INTO OPENORDER (order_id, user_id, currency_code, "
						+ "order_type, quantity, stock_symbol, "
						+ "price_type, opening_order_date, limit_price, total_price_deducted) " + "VALUES(" + order_id
						+ ", " + user_id + ",'" + currency_code + "','" + order_type + "'," + quantity + ",'"
						+ stock_symbol + "','" + price_type + "','" + opening_order_date + "'," + limit_price + ","
						+ total_price_deducted + ")";
			}

			else {
				SQL = "INSERT INTO OPENORDER (order_id, user_id, currency_code, "
						+ "order_type, quantity, stock_symbol, "
						+ "price_type, opening_order_date, limit_price, total_price_deducted , term) " + "VALUES("
						+ order_id + ", " + user_id + ",'" + currency_code + "','" + order_type + "'," + quantity + ",'"
						+ stock_symbol + "','" + price_type + "','" + opening_order_date + "'," + limit_price + ","
						+ total_price_deducted + ",'" + term + "')";
			}
		}
		PreparedStatement ps;
		try {
			ps = connect.prepareStatement(SQL);
			System.out.println("Before executing update..");
			System.out.println(SQL);
			ps.executeUpdate();
			System.out.println(SQL + "\nThe SQL statement above has been executed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("catch at Order DAO delete");
			e.printStackTrace();
		}

		System.out.println(SQL + "\nThe SQL statement above has been executed");

		return success;
	}

}
