package com.fdm.wealthnow.test;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.util.DBUtil;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class OrderDAOTest extends DBUtil {
	private OrderDAO orderDAO;

	@Before
	public void setup() {
		OrderDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
	}

	@Test
	public void testCreatedDataInDatabase() throws Exception {
		orderDAO = new OrderDAO();
		Integer order_id1 = getSequenceID("order_id_seq");
		
		orderDAO.createOpenOrderInDatabase(order_id1, new Integer(1), "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011",
				new Double(88.9), "GC", "Open");
		System.out.println("Start testCreatedDataInDatabase");
		
		Order order1 = orderDAO.getOrderFromOpenOrder(order_id1);
		assertEquals(order1.getOrder_id(), order_id1);
		
		orderDAO.deleteOpenOrderInDatabase(order_id1);
		System.out.println("Deleted "+order_id1+" successfully.");

		
		System.out.println("Test Completed: Created Data in database.");
		
	}
	
	@Test
	public void testGetListOfOpenOrder() throws Exception{
		orderDAO = new OrderDAO();
		List<Order> TestList = orderDAO.getListOfOpenOrder(3);
		Integer testcount = TestList.size();
		assertEquals(testcount, new Integer(3));
	}

	@Test
	public void testCreateProcessedOrderData() throws Exception{
		orderDAO = new OrderDAO();
		
		Integer order_id1 = getSequenceID("order_id_seq");
		System.out.println("order id is " + order_id1);
		orderDAO.createProcessedOrderInDatabase(order_id1, 1, "SGD", "B", 100, "SAISAI","M", "19 Sep 2014", 90.9, 
				"12 Oct 2015", "cancelled", 90.2);
		Order order = orderDAO.getOrderFromProcessedOrder(order_id1);
		assertEquals(order.getUser_id(), new Integer(1));
		System.out.println("Test Completed: Inserted Data in database.");
	}
	@Test
	public void testGetAllProcessedOrder() throws Exception{
		Integer user_id = 1;
		orderDAO  = new OrderDAO();
		List<Order> newOrder = orderDAO.getAllProcessedOrderFromUser(user_id);
		for(Order ord : newOrder){
			assertEquals(ord.getUser_id(),user_id);
			orderDAO.deleteOpenOrderInDatabase(ord.getOrder_id());
		}
		
		
	}
	
	@Test
	public void testGetAllSoldOrders() throws Exception{
		System.out.println("start testGetAllSoldOrders");
		orderDAO = new OrderDAO();
		List <Order> soldList = orderDAO.getAllSoldOrderInDatabase();
		
		for(Order ord : soldList){
			assertEquals(ord.getStatus(), "completed");
			System.out.println(ord.getOrder_id());
		}
		
	}
	
	
}
