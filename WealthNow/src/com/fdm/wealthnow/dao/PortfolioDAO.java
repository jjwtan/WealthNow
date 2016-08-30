package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fdm.wealthnow.common.StockHolding;
import com.fdm.wealthnow.util.DBUtil;

public class PortfolioDAO extends DBUtil {
	///header

	public List getStockHoldingInDataBase(Integer user_id, Connection connect) throws Exception {

		connect = getConnection();
		
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
		connect.commit();
		connect.close();
		
		 System.out.println("Connection Closed");

		return stockHoldingList;
	}

	public void createStockHoldingInDatabase(Connection connect,Integer user_id,Integer order_id,
	String stock_symbol,Integer purchase_quantity,Integer remaining_quantity,Double purchase_price,String purchase_date) throws Exception{
		
		Integer stochHolding_id = getSequenceID("stockholdings_pk_seq");
		
		String sql = "INSERT INTO STOCKHOLDING(stockholding_id,USER_ID, ORDER_ID,STOCK_SYMBOL,PURCHASE_QUANTITY,"
				+ "REMAINING_QUANTITY,PURCHASE_PRICE,PURCHASE_DATE) VALUES("+ stochHolding_id +", "
				+ user_id + " , " + order_id + " , '"
				+ stock_symbol + "' ," + purchase_quantity + "," + remaining_quantity + "," + purchase_price + ", '"
				+ purchase_date +"')";
		
		System.out.println(sql);
		 connect = getConnection();
		 
		 System.out.println("Connected to DB");
		 PreparedStatement ps = connect.prepareStatement(sql);
		 System.out.println("Executing SQL Queries");
//		 ResultSet rs =  ps.executeQuery();
		 ps.executeUpdate();
		 
		 System.out.println("Data has been inserted");
		 
		connect.commit();
		connect.close();
		 System.out.println("Connection Closed");
	}

	public void updateStockHolding(Connection connect,Integer order_id, Integer sold_quantity) throws Exception {

		String sql = "UPDATE STOCKHOLDING SET REMAINING_QUANTITY = REMAINING_QUANTITY - " 
		+ sold_quantity + " WHERE order_id=" + order_id ;
		
		System.out.println(sql);
		
		connect = getConnection();
		connect.setAutoCommit(true);
		 System.out.println("Connected to DB");
		 PreparedStatement ps = connect.prepareStatement(sql);
		 System.out.println("Executing SQL Queries");

		 ps.executeUpdate();
		
		 connect.commit();
		 connect.close(); 
 }

}
