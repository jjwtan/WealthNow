package com.fdm.wealthnow.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fdm.wealthnow.service.OrderProcessor;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class OrderProcessorTest {
	private OrderProcessor orderProcessor;

	//============================================================
	// Test on fetching order from DAO
	//============================================================
	
	@Test 
	public void fetchOrderFromDao() throws Exception {
		orderProcessor = new OrderProcessor();
		
		
		System.out.println("\n Start createOpenOrder");
		
		
		System.out.println("Test Completed: Created Data in database.");	
	}
	
	//==============================================================================
	// Test on time to process order
	//==============================================================================
	
	@Test
	public void timeToProcessOrder() throws Exception {
		orderProcessor = new OrderProcessor();
		
		
		System.out.println("\n Start testCreatedDataInDatabase");
		
		
		System.out.println("Test Completed: Created Data in database.");	
	}
	
	//==============================================================================
	// Test on check limit order
	//==============================================================================
	
	@Test
	public void checkLimitOfOrder() throws Exception {
		orderProcessor = new OrderProcessor();
		
		
		System.out.println("\n Start testCreatedDataInDatabase");
		
		
		System.out.println("Test Completed: Created Data in database.");	
	}

}
