package com.fdm.wealthnow.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.dao.AuthDAO;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.service.OrderManagementService;
import com.fdm.wealthnow.util.DBUtil;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;


public class OrderManagementServiceTest extends DBUtil {

	private OrderManagementService oms;
	Connection connect;
	
	//==============================================================================
	// Before test
	//==============================================================================

	@Before
	public void setup() throws Exception{
		OrderDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
		connect = AuthDAO.getConnection();
		connect.setAutoCommit(false);
	}
	
	//==============================================================================
	// Test on creating open orders
	//==============================================================================
	
	@Test 
	public void createOpenOrder() throws Exception {
		oms = new OrderManagementService();
		OrderDAO orderDAO = new OrderDAO();
		Integer order_id = oms.createOpenOrder(new Integer(1), "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(0), "GC");
		
//		Integer user_id, String currency_code, String order_type, Integer quantity,
//		String stock_symbol, String price_type, String opening_order_date, Double limit_price, String term

		System.out.println("\n Start createOpenOrder");
	
		Order order = orderDAO.getOrderFromOpenOrder(order_id, connect);
		assertEquals(order.getUser_id(), new Integer(1));
		
		System.out.println("Test Completed: Created open order.");	
	}
	
	//==============================================================================
	// Test on processing orders
	//==============================================================================
	

	public void processOrder() throws Exception {
		oms = new OrderManagementService();
		OrderDAO orderDAO = new OrderDAO();
		oms.createOpenOrder(new Integer(1), "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(0), "GC");
		
		System.out.println("\n Start processOrder");
		
		Order order = orderDAO.getOrderFromOpenOrder(1, connect);
		assertEquals(order.getUser_id(), new Integer(1));
		
		System.out.println("Test Completed: Processed order.");	
	}
	
	//==============================================================================
	// Test on validation of orders
	//==============================================================================
	
	@Test
	public void validateOrderData() throws Exception {
	
		oms = new OrderManagementService();
		
		//Check for all parameters
		boolean nullUser = oms.validateOrderData(null, "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(0), "GC"); 									// Null user_id
		boolean nullCurrency = oms.validateOrderData(new Integer(1), null, "B", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(0), "GC"); 		// Null currency_code
		boolean nullOrderType = oms.validateOrderData(new Integer(1), "SGD", null, new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(0), "GC"); 	// Null order type
		boolean nullQuantity = oms.validateOrderData(new Integer(1), "SGD", "B", null, "AAPL", "M", "11 Sep 2011", new Double(0), "GC"); 								// Null quantity
		boolean nullSymbol = oms.validateOrderData(new Integer(1), "SGD", "B", new Integer(100), null, "M", "11 Sep 2011", new Double(0), "GC"); 				// Null stock_symbol
		boolean nullPriceType = oms.validateOrderData(new Integer(1), "SGD", "B", new Integer(100), "AAPL", null, "11 Sep 2011", new Double(0), "GC"); 	// Null price_type
		boolean nullOpenDate = oms.validateOrderData(new Integer(1), "SGD", "B", new Integer(100), "AAPL", "M", null, new Double(0), "GC"); 						// Null opening_order_date
		boolean nullLimit = oms.validateOrderData(new Integer(1), "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011", null, "GC"); 								// Null limit_price
		boolean nullTerm = oms.validateOrderData(new Integer(1), "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(0), null); 				// Null term
		
		//check if currency_code is not more than 3 characters.
		boolean checkCurrency = oms.validateOrderData(new Integer(1), "SGDD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(0), "GC");
		//check if order type is B,S
		boolean checkOrderType = oms.validateOrderData(new Integer(1), "SGD", "Buy", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(0), "GC");
		//check if quantity is not negative
		boolean checkQuantity = oms.validateOrderData(new Integer(1), "SGD", "B", new Integer(-50), "AAPL", "M", "11 Sep 2011", new Double(0), "GC");
		//check if stock symbol not more than 4 characters.
		boolean checkSymbol = oms.validateOrderData(new Integer(1), "SGD", "B", new Integer(100), "APPLE", "M", "11 Sep 2011", new Double(0), "GC");
		//check if order type is M, LT, SL
		boolean checkPriceType = oms.validateOrderData(new Integer(1), "SGD", "B", new Integer(100), "APPLE", "Market", "11 Sep 2011", new Double(0), "GC");
		//check if limit price is not negative	
		boolean checkLimit= oms.validateOrderData(new Integer(1), "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(-60), "GC");
		//check if term is good to cancel or good for the day
		boolean checkTerm = oms.validateOrderData(new Integer(1), "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(0), "Good"); 
		
		System.out.println("\n Start validateOrderData");
		
		boolean isNull = false;
		
		//Check for all parameters
		assertEquals(nullUser, isNull);
		assertEquals(nullCurrency, isNull);
		assertEquals(nullOrderType, isNull);
		assertEquals(nullQuantity, isNull);
		assertEquals(nullSymbol, isNull);
		assertEquals(nullPriceType, isNull);
		assertEquals(nullOpenDate, isNull);
		assertEquals(nullLimit, isNull);
		assertEquals(nullTerm, isNull);
		
		//check if currency_code is not more than 3 characters.
		assertEquals(checkCurrency, isNull);
		//check if order type is M, LT, SL
		assertEquals(checkOrderType, isNull);
		//check if quantity is not negative
		assertEquals(checkQuantity, isNull);
		//check if stock symbol not more than 4 characters.
		assertEquals(checkSymbol, isNull);
		//check if order type is M, LT, SL
		assertEquals(checkPriceType, isNull);
		//check if limit price is not negative
		assertEquals(checkLimit, isNull);
		//check if term is good to cancel or good for the day
		assertEquals(checkTerm, isNull);

		System.out.println("Test Completed: Validated order data.");	
	}
	
	//==============================================================================
	// Test on creating stockholding list
	//==============================================================================
	

	public void createStockHoldings() throws Exception {
		oms = new OrderManagementService();
		oms.createOpenOrder(new Integer(1), "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(0), "GC");

		
		System.out.println("\n Start createStockHoldings");
		
		
		System.out.println("Test Completed: Created stockholding list.");	
	}
	
	//==============================================================================
	// Test on updating stockholding list
	//==============================================================================


	public void updateStockHoldings() throws Exception {
		oms = new OrderManagementService();

		
		System.out.println("\n Start updateStockHoldings");

		
		System.out.println("Test Completed: Updated stockholding list.");	
	}
	
	//==============================================================================
	// Test on deleting stockholding list
	//==============================================================================
	

	public void deleteStockHoldings() throws Exception {
		oms = new OrderManagementService();


	
		System.out.println("\n Start deleteStockHoldings");
		
		
		System.out.println("Test Completed: Deleted stockholding list.");	
	}

}
