package com.fdm.wealthnow.test;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class OrderDAOTest {
	private Order order1, order2, order3, order4, order5;
	private OrderDAO orderDAO;
	
@Before
public void setup(){
	OrderDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
}

@Test 
public void testCreatedDataInDatabase() throws Exception{
	orderDAO = new OrderDAO();
	orderDAO.createOpenOrderInDatabase(new Integer(1), new Integer(100), "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(88.9), "GC", "Open");
	orderDAO.createOpenOrderInDatabase(new Integer(2), new Integer(101), "SGD", "B", new Integer(100), "GOOG", "LT", "05/05/2016", new Double(76.2), "GC", "Open");
	orderDAO.createOpenOrderInDatabase(new Integer(3), new Integer(102), "SGD", "B", new Integer(100), "AMZN", "M", "06/05/2016", new Double(90.1), "GD", "Open");

	List<Order> TestList = orderDAO.getListOfOpenOrder(3);
	Integer testcount = TestList.size();

	assertEquals(testcount, new Integer(3));
	System.out.println("Test Completed: Created Data in database.");
}


@Test 
public void testDeleteDataInOpenOrder() throws Exception{
	orderDAO = new OrderDAO();
	boolean deletedOrder = orderDAO.deleteOpenOrderInDatabase(80);
	boolean isDeleted = true;
	
	assertEquals(deletedOrder, isDeleted);
	System.out.println("Test Completed: Deleted Data in database.");
}

}
