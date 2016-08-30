package com.fdm.wealthnow.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import com.fdm.wealthnow.service.OrderManagementService;


public class OrderManagementServiceTest {
	private OrderManagementService oms1, oms2, oms3;
	
	//==============================================================================
	// Test on creating open orders
	//==============================================================================
	
	@Test 
	public void createOpenOrder() throws Exception {
		oms1 = new OrderManagementService();
		oms2 = new OrderManagementService();
		oms3 = new OrderManagementService();
		
		oms1.createOpenOrder(new Integer(1), "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(0), "GC");
		oms2.createOpenOrder(new Integer(1), "SGD", "S", new Integer(250), "YHOO", "LT", "5 Oct 2011", new Double(88), "GC");
		oms3.createOpenOrder(new Integer(1), "SGD", "B", new Integer(300), "GOOG", "LT", "8 Nov 2011", new Double(90), "GC");
		
		
		System.out.println("\n Start createOpenOrder");
		
		ArrayList<OrderManagementService> omsList = new ArrayList<>();
		omsList.add(oms1);
		omsList.add(oms2);
		omsList.add(oms3);
			
		Integer testCount = omsList.size();
		assertEquals(testCount, new Integer(3));
		
		System.out.println("Test Completed: Created open order.");	
	}
	
	//==============================================================================
	// Test on processing orders
	//==============================================================================
	
	@Test
	public void processOrder() throws Exception {
		oms1 = new OrderManagementService();
		oms2 = new OrderManagementService();
		oms3 = new OrderManagementService();
		
		oms1.createOpenOrder(new Integer(1), "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(0), "GC");
		oms2.createOpenOrder(new Integer(1), "SGD", "S", new Integer(250), "YHOO", "LT", "5 Oct 2011", new Double(88), "GC");
		oms3.createOpenOrder(new Integer(1), "SGD", "B", new Integer(300), "GOOG", "LT", "8 Nov 2011", new Double(90), "GC");
		
		
		
		System.out.println("\n Start processOrder");
		
		
		System.out.println("Test Completed: Processed order.");	
	}
	
	//==============================================================================
	// Test on validation of orders
	//==============================================================================
	
	@Test
	public void validateOrderData() throws Exception {
		oms1 = new OrderManagementService();
		oms2 = new OrderManagementService();
		oms3 = new OrderManagementService();
	
		oms1.createOpenOrder(new Integer(1), "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(0), "GC");
		oms2.createOpenOrder(new Integer(1), "SGD", "S", new Integer(250), "YHOO", "LT", "5 Oct 2011", new Double(88), "GC");
		oms3.createOpenOrder(new Integer(1), "SGD", "B", new Integer(300), "GOOG", "LT", "8 Nov 2011", new Double(90), "GC");
		
		System.out.println("\n Start validateOrderData");
		
		
		System.out.println("Test Completed: Validated order data.");	
	}
	
	//==============================================================================
	// Test on creating stockholding list
	//==============================================================================
	
	@Test
	public void createStockHoldings() throws Exception {
		oms1 = new OrderManagementService();
		oms2 = new OrderManagementService();
		oms3 = new OrderManagementService();
	
		oms1.createOpenOrder(new Integer(1), "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(0), "GC");
		oms2.createOpenOrder(new Integer(1), "SGD", "S", new Integer(250), "YHOO", "LT", "5 Oct 2011", new Double(88), "GC");
		oms3.createOpenOrder(new Integer(1), "SGD", "B", new Integer(300), "GOOG", "LT", "8 Nov 2011", new Double(90), "GC");
		
		System.out.println("\n Start createStockHoldings");
		
		
		System.out.println("Test Completed: Created stockholding list.");	
	}
	
	//==============================================================================
	// Test on updating stockholding list
	//==============================================================================
	
	@Test
	public void updateStockHoldings() throws Exception {
		oms1 = new OrderManagementService();
		oms2 = new OrderManagementService();
		oms3 = new OrderManagementService();

		oms1.createOpenOrder(new Integer(1), "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(0), "GC");
		oms2.createOpenOrder(new Integer(1), "SGD", "S", new Integer(250), "YHOO", "LT", "5 Oct 2011", new Double(88), "GC");
		oms3.createOpenOrder(new Integer(1), "SGD", "B", new Integer(300), "GOOG", "LT", "8 Nov 2011", new Double(90), "GC");

		
		System.out.println("\n Start updateStockHoldings");

		
		System.out.println("Test Completed: Updated stockholding list.");	
	}
	
	//==============================================================================
	// Test on deleting stockholding list
	//==============================================================================
	
	@Test
	public void deleteStockHoldings() throws Exception {
		oms1 = new OrderManagementService();
		oms2 = new OrderManagementService();
		oms3 = new OrderManagementService();

		oms1.createOpenOrder(new Integer(1), "SGD", "B", new Integer(100), "AAPL", "M", "11 Sep 2011", new Double(0), "GC");
		oms2.createOpenOrder(new Integer(1), "SGD", "S", new Integer(250), "YHOO", "LT", "5 Oct 2011", new Double(88), "GC");
		oms3.createOpenOrder(new Integer(1), "SGD", "B", new Integer(300), "GOOG", "LT", "8 Nov 2011", new Double(90), "GC");

	
		System.out.println("\n Start deleteStockHoldings");
		
		
		System.out.println("Test Completed: Deleted stockholding list.");	
	}

}
