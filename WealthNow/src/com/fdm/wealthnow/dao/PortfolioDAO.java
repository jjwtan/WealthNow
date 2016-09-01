package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.StockHolding;
import com.fdm.wealthnow.util.DBUtil;

public class PortfolioDAO extends DBUtil {
	///header

	public List getStockHoldingInDataBase(Integer user_id, Connection connect) throws Exception {

		
		
		System.out.println("Connected to DB");
		
		PreparedStatement ps = connect.prepareStatement("SELECT STOCKHOLDING_ID, USER_ID,ORDER_ID, STOCK_SYMBOL,REMAINING_QUANTITY,PURCHASE_PRICE FROM "
				+ "STOCKHOLDING WHERE USER_ID=" + user_id );
		
		System.out.println("Executing SQL Queries");
		ResultSet rs = ps.executeQuery();
		
		
		List<StockHolding> stockHoldingList = new ArrayList();

		while (rs.next()) {
			int stockHoldingID = rs.getInt("StockHOLDING_ID");
			int userID= rs.getInt("USER_ID");
			int orderID= rs.getInt("ORDER_ID");
			String stockSymbol = rs.getString("Stock_Symbol");
			int qty = rs.getInt("Remaining_Quantity");
			Double price = rs.getDouble("Purchase_Price");
			System.out.println(stockSymbol + qty + price);
			StockHolding sh = new StockHolding(stockHoldingID,userID,orderID,stockSymbol, qty, price);
			stockHoldingList.add(sh);
			System.out.println("new stock has been added");
		}
//		connect.commit();
		
		
//		 System.out.println("Connection Closed");

		return stockHoldingList;
	}

	public void createStockHoldingInDatabase(Connection connect,Integer stockHolding_id,Integer user_id,Integer order_id,
	String stock_symbol,Integer purchase_quantity,Integer remaining_quantity,Double purchase_price,String purchase_date){
		
		String sql = "INSERT INTO STOCKHOLDING(stockholding_id,USER_ID, ORDER_ID,STOCK_SYMBOL,PURCHASE_QUANTITY,"
				+ "REMAINING_QUANTITY,PURCHASE_PRICE,PURCHASE_DATE) VALUES("+ stockHolding_id +", "
				+ user_id + " , " + order_id + " , '"
				+ stock_symbol + "' ," + purchase_quantity + "," + remaining_quantity + "," + purchase_price + ", '"
				+ purchase_date +"')";
		
		System.out.println(sql);
		 
		 
		 System.out.println("Connected to DB");
		 PreparedStatement ps;
		try {
			ps = connect.prepareStatement(sql);
			 System.out.println("Executing SQL Queries");
//			 ResultSet rs =  ps.executeQuery();
			 ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("catch at PortfolioDAO");
			e.printStackTrace();
		}
		
		 
		 System.out.println("Data has been inserted");
		 
//		connect.commit();
		
		 System.out.println("Connection Closed");
	}

	public void updateStockHolding(Connection connect,Integer order_id, Integer sold_quantity) throws Exception {
//		boolean success = false;
		String sql = "UPDATE STOCKHOLDING SET REMAINING_QUANTITY = REMAINING_QUANTITY - " 
		+ sold_quantity + " WHERE order_id=" + order_id ;
		
		System.out.println(sql);
		
		
//		connect.setAutoCommit(true);
		 System.out.println("Connected to DB");
		 PreparedStatement ps = connect.prepareStatement(sql);
		 System.out.println("Executing SQL Queries");

		 ps.executeUpdate();
		
//		 connect.commit();
//		 success = true;
		
//		return success; 
 }
	
	public boolean deleteStockHolding(Connection connect,Integer order_id) throws SQLException{
		boolean success = false;
		String sql = "DELETE FROM STOCKHolding where order_id= " + order_id;
		System.out.println(sql);
		
//		connect.setAutoCommit(true);
		System.out.println("Connecting to DB");
		PreparedStatement ps = connect.prepareStatement(sql);
		ps.executeUpdate();
//		connect.commit();
//		System.out.println("Committed");
		success = true;
		System.out.println("Success deletion!");
		
		
		return success;
		
	}
	
	public StockHolding getStockholding(Connection connect, Integer order_id) throws SQLException{
		String SQL = "SELECT * FROM STOCKHOLDING WHERE ORDER_ID =?";
		PreparedStatement ps = connect.prepareStatement(SQL);
		ps.setInt(1, order_id);
		ResultSet result = ps.executeQuery();
		StockHolding sh = null;
		
		while (result.next()) {
			Integer stockholding_id = result.getInt("stockholding_id");
			Integer user_id = result.getInt("user_id");
			String stock_symbol = result.getString("stock_symbol");
			Integer purchase_quantity = result.getInt("purchase_quantity");
			Integer remaining_quantity = result.getInt("remaining_quantity");
			Double purchase_price = result.getDouble("purchase_price");
			Date purchase_date = result.getDate("purchase_date");
		
			sh = new StockHolding( stockholding_id, user_id, order_id, stock_symbol, remaining_quantity, purchase_price);
		}
		return sh;
		
		
	}

}
