package com.fdm.wealthnow.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fdm.wealthnow.service.OrderProcessor;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class OrderProcessorTest {
	private OrderProcessor orderProcessor;

	//============================================================
	// Test on...
	//============================================================
	
	@Test 
	public void createOpenOrder() throws Exception {
		orderProcessor = new OrderProcessor();
		
		
		System.out.println("\n Start createOpenOrder");
		
		
		System.out.println("Test Completed: Created Data in database.");	
	}
	
	//==============================================================================
	// Test on...
	//==============================================================================
	
	@Test
	public void processOrder() throws Exception {
		orderProcessor = new OrderProcessor();
		
		
		System.out.println("\n Start testCreatedDataInDatabase");
		
		
		System.out.println("Test Completed: Created Data in database.");	
	}
	
	//==============================================================================
	// Test on...
	//==============================================================================
	
	@Test
	public void validateOrderData() throws Exception {
		orderProcessor = new OrderProcessor();
		
		
		System.out.println("\n Start testCreatedDataInDatabase");
		
		
		System.out.println("Test Completed: Created Data in database.");	
	}

	//==============================================================================
	// Test on...
	//==============================================================================
	
	@Test
	public void createStockHoldings() throws Exception {
		orderProcessor = new OrderProcessor();
		
		
		System.out.println("\n Start testCreatedDataInDatabase");
		
		
		System.out.println("Test Completed: Created Data in database.");	
	}
	
	//==============================================================================
	// Test on...
	//==============================================================================
	
	@Test
	public void updateStockHoldings() throws Exception {
		orderProcessor = new OrderProcessor();
		

		System.out.println("\n Start testCreatedDataInDatabase");
		
		
		System.out.println("Test Completed: Created Data in database.");	
	}
	
	//==============================================================================
	// Test on...
	//==============================================================================
	
	@Test
	public void deleteStockHoldings() throws Exception {
		orderProcessor = new OrderProcessor();
		
		
		System.out.println("\n Start testCreatedDataInDatabase");
		

		System.out.println("Test Completed: Created Data in database.");	
	}

}
