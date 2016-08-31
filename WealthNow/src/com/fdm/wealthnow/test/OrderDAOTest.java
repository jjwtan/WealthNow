package com.fdm.wealthnow.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.dao.AuthDAO;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.util.DBUtil;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class OrderDAOTest extends DBUtil {
	static Connection connect;
	private OrderDAO orderDAO;
	
	//==============================================================================
	// Before test
	//==============================================================================

	@Before
	public void setup() throws Exception{
		OrderDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
		connect = orderDAO.getConnection();
		connect.setAutoCommit(false);
	}
	
	//==============================================================================
	// Test on creating data in database
	//==============================================================================

	@Test
	public void testCreatedDataInDatabase() throws Exception {
		orderDAO = new OrderDAO();
		Integer order_id1 = getSequenceID("order_id_seq");
		
		orderDAO.createOpenOrderInDatabase(connect, order_id1, new Integer(1), "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011",
				new Double(88.9), "GC", "Open");
		
		System.out.println("\n Start testCreatedDataInDatabase");
		
		Order order1 = orderDAO.getOrderFromOpenOrder(order_id1, connect);
		assertEquals(order1.getOrder_id(), order_id1);
		orderDAO.deleteOpenOrderInDatabase(connect, order_id1);
		System.out.println("Deleted "+order_id1+" successfully.");
		
		System.out.println("Test Completed: Created Data in database.");	
	}
	
	@Test
	public void testGetListOfOpenOrder() throws Exception{
		orderDAO = new OrderDAO();
		List<Order> TestList = orderDAO.getListOfOpenOrder(connect, 3);
		Integer testcount = TestList.size();
		
		System.out.println("\n Start testGetListOfOpenOrder");
		assertEquals(testcount, new Integer(3));
		System.out.println("Test Completed: Get list of open order.");	
	}
	
	//==============================================================================
	// Test on creating processed order data
	//==============================================================================

	@Test
	public void testCreateProcessedOrderData() throws Exception{
		orderDAO = new OrderDAO();
		
		Integer order_id1 = getSequenceID("order_id_seq");
		System.out.println("order id is " + order_id1);
		orderDAO.createProcessedOrderInDatabase(connect, order_id1, 1, "SGD", "B", 100, "SAISAI","M", "19 Sep 2014", 90.9, 
				"12 Oct 2015", "cancelled", 90.2);
		Order order = orderDAO.getOrderFromProcessedOrder(connect, order_id1);
		
		System.out.println("\n Start testCreateProcessedOrderData");
		assertEquals(order.getUser_id(), new Integer(1));
		System.out.println("Test Completed: Create processed order in database.");
	}
	
	//==============================================================================
	// Test on getting all processed orders
	//==============================================================================
	
	@Test
	public void testGetAllProcessedOrder() throws Exception{
		Integer user_id = 1;
		orderDAO  = new OrderDAO();
		List<Order> newOrder = orderDAO.getAllProcessedOrderFromUser(connect, user_id);
		
		System.out.println("\n Start testGetAllProcessedOrder");
		for(Order ord : newOrder){
			assertEquals(ord.getUser_id(),user_id);
			orderDAO.deleteOpenOrderInDatabase(connect, ord.getOrder_id());
		}	
		System.out.println("Test Completed: Get all processed order.");	
	}
	
	//==============================================================================
	// Test on getting all sold orders
	//==============================================================================
	
	@Test
	public void testGetAllSoldOrders() throws Exception{
		System.out.println("\n Start testGetAllSoldOrders");
		orderDAO = new OrderDAO();
		List <Order> soldList = orderDAO.getAllSoldOrderInDatabase(connect,1,152);
		
		System.out.println("\n Start testGetAllSoldOrders");
		for(Order ord : soldList){
			assertEquals(ord.getStatus(), "completed");
			System.out.println(ord.getOrder_id());
		}
		System.out.println("Test Completed: Get all sold orders.");	
		
	}
	
	//==============================================================================
	// After test
	//==============================================================================
	
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
