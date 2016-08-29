package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fdm.wealthnow.common.StockHolding;

public class PortfolioDAO extends BaseDAO {
	///header

	public List getStockHoldingInDataBase(Integer user_id) throws Exception {

		Connection connect = getConnection();
		System.out.println("Connected to DB");
		
		PreparedStatement ps = connect.prepareStatement("SELECT STOCK_SYMBOL,REMAINING_QUANTITY,PURCHASE_PRICE FROM "
				+ "STOCKHOLDING WHERE USER_ID=" + user_id + "");
		
		System.out.println("Executing SQL Queries");
		ResultSet rs = ps.executeQuery();
		
		

		List<StockHolding> stockHoldingList = new ArrayList();

		while (rs.next()) {
			String stockSymbol = rs.getString("Stock_Symbol");
			int qty = rs.getInt("Remaining_Quantity");
			float price = rs.getFloat("Purchase_Price");
			System.out.println(stockSymbol + qty + price);
			StockHolding sh = new StockHolding(stockSymbol, qty, price);
			stockHoldingList.add(sh);
			System.out.println("new stock has been added");
		}

		connect.close();
		 System.out.println("Connection Closed");

		return stockHoldingList;
	}

	public void createStockHoldingInDatabase(Integer user_id,Integer order_id,
	String stock_symbol,Integer purchase_quantity,Integer remaining_quantity,Float purchase_price,Date purchase_date) throws Exception{
		String sql = "INSERT INTO STOCKHOLDING(USER_ID ORDER_ID,STOCK_SYMBOL,PURCHASE_QUANTITY,"
				+ "REMAINING_QUANTITY,PURCHASE_PRICE,PURCHASE_DATE) VALUES(" + user_id + "," + order_id + ", '"
				+ stock_symbol + "' ," + purchase_quantity + "," + remaining_quantity + "," + purchase_price + ", '" + purchase_date +"')";
		
		
		 Connection connect = getConnection();
		 System.out.println("Connected to DB");
		 PreparedStatement ps = connect.prepareStatement(sql);
		 System.out.println("Executing SQL Queries");
		 ResultSet rs =  ps.executeQuery();
		 
		 System.out.println("Data has been inserted");
		 
		
		 connect.close();
		 System.out.println("Connection Closed");
	}

	public void updateStockHolding(Integer order_id, Integer sold_quantity) throws Exception {
		

		
		String sql = "UPDATE STOCKHOLDING sh" 
					+ "SET sh.REMAINING_QUANTITY = sh.REMAINING_QUANTITY -" + sold_quantity 
				+ "WHERE sh.order_id=" +order_id + "";
		
		
		Connection connect = getConnection();
		System.out.println("Connected to DB");
		
		PreparedStatement ps = connect.prepareStatement(sql);
		 ResultSet rs =  ps.executeQuery();
		 System.out.println("SQL executed");
		 
		 connect.close();
		 }
		
		 
		 
		 
		

	

}
