package com.fdm.wealthnow.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.StockHolding;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.service.OrderManagementService;
import com.fdm.wealthnow.service.PortfolioService;
import com.fdm.wealthnow.util.DBUtil;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;


public class OrderManagementServiceTest extends DBUtil {

	private OrderManagementService oms;
	Connection connect;

	// ==============================================================================
	// Before test
	// ==============================================================================

	@Before
	public void setup() throws Exception {
		oms.setConnectionType(ConnectionType.LOCAL_CONNECTION);
		connect = oms.getConnection();
		connect.setAutoCommit(false);
	}

	// ==============================================================================
	// Test on creating open orders
	// ==============================================================================

	//@Test
	public void createOpenOrder() throws Exception {
		oms = new OrderManagementService();
		OrderDAO orderDAO = new OrderDAO();
		Integer order_id = oms.createOpenOrder(new Integer(1), "SGD", "B", new Integer(100), "Z74", "M", "11 Sep 2011",
				new Double(20), "GC", 150.1);

		// Integer user_id, String currency_code, String order_type, Integer
		// quantity,
		// String stock_symbol, String price_type, String opening_order_date,
		// Double limit_price, String term

		System.out.println("\n Start createOpenOrder");

		Order order = orderDAO.getOrderFromOpenOrder(order_id, connect);
		assertEquals(order.getUser_id(), new Integer(1));

		System.out.println("Test Completed: Created open order.");
	}

	// ==============================================================================
	// Test on processing orders
	// ==============================================================================
	//@Test
	public void processOrder() throws Exception {
		oms = new OrderManagementService();
		OrderDAO orderDAO = new OrderDAO();
		Integer order_id = oms.createOpenOrder(new Integer(1), "SGD", "B", new Integer(100), "Z74", "M", "11 Sep 2011",
				new Double(20), "GC", 150.1);
		oms.processOrder(new Integer(order_id), new Double(100));
		System.out.println("Start processOrder..");

		Order order = orderDAO.getOrderFromProcessedOrder(connect, order_id);
		assertEquals(order.getUser_id(), new Integer(1));

		System.out.println("Test Completed: Processed order.");
	}

	//@Test
	public void testprocessCancelledOrder() throws Exception {
		oms = new OrderManagementService();
		OrderDAO orderDAO = new OrderDAO();
		Integer order_id = oms.createOpenOrder(new Integer(1), "SGD", "B", new Integer(10), "Z74", "M", "11 Sep 2011",
				new Double(2.0), "GC", 150.1);
		oms.processCancelledOrders(order_id);
		
		assertEquals(order_id,orderDAO.getOrderFromProcessedOrder(getConnection(), order_id).getOrder_id());
	}
	
	@Test
	public void testprocessSellOrders() throws Exception{
		oms = new OrderManagementService();
		PortfolioService ps = new PortfolioService();
		System.out.println("Start create open order in test");
		Integer order_id1 = oms.createOpenOrder(new Integer(3), "SGD", "B", new Integer(10), "Z74", "M", "11 Sep 2011",
				new Double(2.0), "null", 150.1);
		oms.processOrder(order_id1, new Double(2.0));
		System.out.println("============================end of process buy order");
		Integer order_id = oms.createSellOrder(order_id1,new Integer(3), "SGD", "S", new Integer(5), "Z74", "M", "11 Sep 2011",
				new Double(2.0), "null", 150.1); 
		oms.processSellOrder(order_id,order_id1, new Double(2.0), new Integer(5), new Double(10));
		System.out.println(order_id1);
		StockHolding sh = ps.getStockholdingFromPortfolioService(connect, order_id1);
		
		assertEquals(order_id1, sh.getOrder_id());
		
		
	}
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
