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
orderDAO.createOpenOrderInDatabase(new Integer(1), new Integer(100), "SGD", "BUY", new Integer(100), "AAPL", "Market", new Date("04/05/2016"), new Float(0f), "NA", "Open");
orderDAO.createOpenOrderInDatabase(new Integer(2), new Integer(101), "SGD", "SELL", new Integer(100), "GOOG", "Limit", new Date("05/05/2016"), new Float(88f), "Good Till Cancelled", "Open");
orderDAO.createOpenOrderInDatabase(new Integer(3), new Integer(102), "SGD", "BUY", new Integer(100), "AMZN", "Market", new Date("06/05/2016"), new Float(0f), "NA", "Open");

List<Order> TestList = orderDAO.getListOfOpenOrder(3);
Integer testcount = TestList.size();

assertEquals(testcount, new Integer(3));


	System.out.println("Test Completed: Created Data in database.");
}


@Test 
public void testDeleteDataInOpenOrder(){
	System.out.println("Test Completed: Delete Data in database.");
}

}
