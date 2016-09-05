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
import com.fdm.wealthnow.common.UserAccount;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.util.DBUtil;

public class OrderProcessor extends DBUtil implements ServletContextListener {
	private static final int WORKER_THREAD_POOL_SIZE = 5;
	private static final int ORDER_FETCH_SIZE = 5;

	ScheduledExecutorService scheduledExecutorService;
	ExecutorService executorService;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("************* Server context initialized *************");

		// Create worker thread pool that does stock trading
		executorService = Executors.newFixedThreadPool(WORKER_THREAD_POOL_SIZE);

		// Create a thread that wakes up periodically and scans for open orders.
		// It fetches the orders and delegates to thread pool
		scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scheduledExecutorService.schedule(() -> processOpenOrders(executorService), 30, TimeUnit.SECONDS);

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("************* Server context destroyed *************");

		scheduledExecutorService.shutdown();
		executorService.shutdown();
	}

	public int testProcessOrders(ExecutorService ex) {
		return processOpenOrders(ex);
	}

	private int processOpenOrders(ExecutorService ex) {
		System.out.println("Processing open orders running at:" + new Date());

		// Get 20 open orders from OrderService
		// Delegate processing to worker thread pool
		List<Order> orderList = fetchOrderFromDao(ORDER_FETCH_SIZE);
		System.out.println(orderList);
		OrderManagementService oms = new OrderManagementService();
		UserAccountService uas = new UserAccountService();
		

		StockService stksvc = new StockService();
		int count = 0;

		for (Order order : orderList) {
			//if the term is null because the ordertype "Market" do the following:
			boolean success;
			if (order.getTerm() == null) {
				 success = oms.validateOrderData(order.getUser_id(), order.getCurrency_code(),
						order.getOrder_type().toString(), order.getQuantity(), order.getStock_symbol(),
						order.getPrice_type().toString(), convertDateObjToString(order.getPlace_order_date()),
						order.getLimit_price(), "");
			} else {
				 success = oms.validateOrderData(order.getUser_id(), order.getCurrency_code(),
						order.getOrder_type().toString(), order.getQuantity(), order.getStock_symbol(),
						order.getPrice_type().toString(), convertDateObjToString(order.getPlace_order_date()),
						order.getLimit_price(), order.getTerm().toString());
			}

			if (success == true) {
				Double stockPrice = Double.parseDouble(
						stksvc.getStockFromExchange(order.getStock_symbol(), InfoType.BASIC).getMktPrice().toString());
				Integer quantity = order.getQuantity();
				Double total_price = stockPrice * quantity;
				Double balance = uas.getAccountBalance(order.getUser_id()).getBalance();
				System.out.println("stock price : " +stockPrice);
				System.out.println("limit price : " +order.getLimit_price());
				System.out.println("balance is " + balance);
				System.out.println("total price: "+ total_price);
				// if statement to check if the limit price
				if (order.getPrice_type().toString().equals("M") && balance > total_price) {
					System.out.println("Executing processOrder at OrderProcessor.\nPrice type is M");
					ex.submit(() -> oms.processOrder(order.getOrder_id(), stockPrice));
					count++;
					System.out.println("Count: " +count);
					//edit balance account
				    uas.debitBalance(order.getUser_id(), total_price);
				}else if((order.getPrice_type().toString().equals("LT")||order.getPrice_type().equals("SL")) && balance > total_price){
					if(order.getLimit_price()<stockPrice){
						System.out.println("Executing processOrder at OrderProcessor.\nPrice type is LT or SL");
						ex.submit(() -> oms.processOrder(order.getOrder_id(), order.getLimit_price()));
						count++;
						System.out.println("Count - " +count);
						uas.debitBalance(order.getUser_id(), total_price);
					}
				}else{
					System.out.println("Insufficient funds. Please contact administrator. UserID : " + order.getUser_id());
				}
			} else {
				System.out.println("Validation failed.");
			}
		}
		return count;
	}

	public List<Order> TestfetchOrderFromDao(int limit) {
		return fetchOrderFromDao(limit);

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
