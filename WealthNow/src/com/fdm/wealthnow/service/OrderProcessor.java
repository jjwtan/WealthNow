package com.fdm.wealthnow.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.util.DBUtil;

public class OrderProcessor extends DBUtil implements ServletContextListener {
	private static final int WORKER_THREAD_POOL_SIZE = 5;
	private static final int ORDER_FETCH_SIZE = 20;

	
	

	ScheduledExecutorService scheduledExecutorService;
	ExecutorService executorService;

	@Override
	public void contextInitialized(ServletContextEvent arg0){
		System.out.println("************* Server context initialized *************");

		// Create worker thread pool that does stock trading
		executorService = Executors.newFixedThreadPool(WORKER_THREAD_POOL_SIZE);

		// Create a thread that wakes up periodically and scans for open orders.
		// It fetches the orders and delegates to thread pool
		scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scheduledExecutorService.schedule(() -> processOpenOrders(), 60, TimeUnit.SECONDS);

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("************* Server context destroyed *************");

		scheduledExecutorService.shutdown();
		executorService.shutdown();
	}

	private void processOpenOrders() {
		System.out.println("Processing open orders running at:" + new Date());

		// Get 20 open orders from OrderService
		// Delegate processing to worker thread pool
		List<Order> orderList = fetchOrderFromDao(ORDER_FETCH_SIZE);
		OrderManagementService oms = new OrderManagementService();
		StockService stksvc = new StockService();

		for (Order newOrderList : orderList) {
			Integer orderID = newOrderList.getOrder_id();
			Double limitPrice = newOrderList.getLimit_price();
			String stockSymb = newOrderList.getStock_symbol();
			Double stockPrice = Double
					.parseDouble(stksvc.getStockFromExchange(stockSymb, InfoType.BASIC).getMktPrice().toString());
			
			if(limitPrice<stockPrice){
			executorService.submit(() -> oms.processOrder(orderID, limitPrice));  //Execute the order
			}
		}

	}

	private List<Order> fetchOrderFromDao(int limit) {
		
		Connection connect = null;
		try {
			connect = getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		OrderDAO orderDao = new OrderDAO();
		List<Order> listOfOpenOrder = orderDao.getListOfOpenOrder(connect, limit);

		return listOfOpenOrder;

	}
}
