package com.fdm.wealthnow.test;

import org.junit.Test;

import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.dao.PortfolioDAO;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

public class PortfolioDAOTest {
	private PortfolioDAO portfolioDAO;

@Before
public void setup(){
	OrderDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
}

@Test 
public void testCreateStockHoldingInDatabase(){
	portfolioDAO = new PortfolioDAO();
	portfolioDAO.createStockHoldingInDatabase(user_id, order_id, stock_symbol, purchase_quantity, remaining_quantity, purchase_price, purchase_date);
	System.out.println("Test Completed: Created Data in database.");
}


@Test 
public void testUpdateStockHoldingInDatabase(){
	System.out.println("Test Completed: Update Stock holding in database.");
}

}
