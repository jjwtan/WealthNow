package com.fdm.wealthnow.test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.service.OrderManagementService;
import com.fdm.wealthnow.service.OrderProcessor;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class OrderProcessorTest {
	private OrderProcessor orderProcessor;
	Connection connect;
	
	@Before
	public void setup() throws Exception {
		orderProcessor.setConnectionType(ConnectionType.LOCAL_CONNECTION);
		connect = orderProcessor.getConnection();
		connect.setAutoCommit(false);
	}

	//============================================================
	// Test on fetching order from DAO
	//============================================================
	
	//@Test 
	public void TestfetchOrderFromDao() throws Exception {
		OrderManagementService oms = new  OrderManagementService();
		oms.createOpenOrder(new Integer(1), "SGD", "B", new Integer(100), "Z74", "M", "11 Sep 2011",
				new Double(20), "GC");
		orderProcessor = new OrderProcessor();
		List <Order> orderlist = orderProcessor.TestfetchOrderFromDao(3);
		System.out.println(orderlist);
		Order order = null;
		for( Order ord : orderlist){
			order = ord;
		}
		
		assertEquals(order.getUser_id(),new Integer(1) );
		assertEquals(orderlist.size(), 3);
		
		
		System.out.println("Test Completed: Created Data in database.------------------------------------------------------");	
	}
	
	
	//==============================================================================
	// Test on processOrder
	//==============================================================================
	
	@Test
	public void testprocessOpenOrders() throws Exception {
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		orderProcessor = new OrderProcessor();
		OrderManagementService oms = new OrderManagementService();
		oms.createOpenOrder(new Integer(1), "SGD", "B", new Integer(100), "Z74", "M", "11 Sep 2011",
				new Double(2), "");
		oms.createOpenOrder(new Integer(1), "SGD", "B", new Integer(200), "Z74", "M", "11 Sep 2011",
				new Double(2), "");
		oms.createOpenOrder(new Integer(1), "SGD", "B", new Integer(50), "Z74", "M", "11 Sep 2011",
				new Double(2), "");
		oms.createOpenOrder(new Integer(1), "SGD", "B", new Integer(50), "Z74", "M", "11 Sep 2011",
				new Double(2), "");
		oms.createOpenOrder(new Integer(1), "SGD", "B", new Integer(50), "Z74", "M", "11 Sep 2011",
				new Double(2), "");
		System.out.println("processing orders ---------------------------------");
		int count = orderProcessor.testProcessOrders(executorService);
		System.out.println("Processing done with " + count + " orders processed.");
		assertEquals(5,count );
		
		
		
		System.out.println("Test Completed: Created Data in database.-----------------------------------------------------------------------");	
	}
	
	@After
	public void tearDown() throws SQLException {
		try {
			connect.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connect.close();
	}

}
