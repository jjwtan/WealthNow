package com.fdm.wealthnow.test;

import org.junit.Test;

import com.fdm.wealthnow.common.Order;
import static org.junit.Assert.assertEquals;
import java.util.Date;


public class OrderManagementServiceTest {
	private Order order;

@Test
public void testValidateOrderData(){
	order = new Order(new Integer(1), new Integer(1), "SGD", "BUY", new Integer(100), "AAPL", "Market", new Date("04/05/2016"), new Float(0f), "NA", "Open");

	assertEquals(order.getUser_id(), new Integer(1));
	assertEquals(order.getOrder_id(), new Integer(1));	
	assertEquals(order.getCurrency_code(), "SGD");
	assertEquals(order.getOrder_type(), "BUY");
	assertEquals(order.getQuantity(), new Integer(100));
	assertEquals(order.getStock_symbol(),"AAPL");
	assertEquals(order.getPrice_type(), "Market");
	assertEquals(order.getOpening_order_date(), new Date("04/05/2016"));
	assertEquals(order.getLimit_price(), new Float(0f));
	assertEquals(order.getTerm(), "NA");
	assertEquals(order.getStatus(), "Open");

	System.out.println("Test Completed: Validate Order Data.");
}

}
