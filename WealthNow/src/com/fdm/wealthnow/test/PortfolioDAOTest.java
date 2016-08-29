package com.fdm.wealthnow.test;

import org.junit.Test;

import com.fdm.wealthnow.common.StockHolding;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.dao.PortfolioDAO;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

public class PortfolioDAOTest {
	private PortfolioDAO portfolioDAO;

@Before
public void setup(){
	OrderDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
}

@Test 
public void testCreateStockHoldingInDatabase() throws Exception{
	
	 PortfolioDAO portfolioDAO1 = new PortfolioDAO();
	
	portfolioDAO1.createStockHoldingInDatabase(new Integer(1), new Integer(3),"APPL", new Integer(500),new Integer(500), new Double(99.99), "20 Sep 2001");
	
	
	
	List<StockHolding> newTestList = portfolioDAO1.getStockHoldingInDataBase(1);
	
	for(StockHolding newListTest : newTestList){
		System.out.println(newListTest.getUser_id());
		
		assertEquals(newListTest.getUser_id(), new Integer(1));
		
	}
//	portfolioDAO.createStockHoldingInDatabase(new Integer(2), new Integer(300),"MCDS", new Integer(20),
//			new Integer(500), new Double(5.99), "08 May 2009");
	
	
//	newTestList.add(portfolioDAO1);
	
	
	
	
	
	System.out.println("Test Completed: Created Data in database.");
}


@Test 
public void testUpdateStockHoldingInDatabase(){
	System.out.println("Test Completed: Update Stock holding in database.");
}

}
