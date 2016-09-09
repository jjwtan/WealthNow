package com.fdm.wealthnow.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class OrderProcessor extends DBUtil implements ServletContextListener {
	private static final int WORKER_THREAD_POOL_SIZE = 5;
	private static final int ORDER_FETCH_SIZE = 10;

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
		scheduledExecutorService.scheduleAtFixedRate(() -> processOpenOrders(executorService), 2, 30, TimeUnit.SECONDS);

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
		System.out.println("Processing open orders running at:" + new Date() + "-----------------------");

		// Get 20 open orders from OrderService
		// Delegate processing to worker thread pool
		List<Order> orderList = fetchOrderFromDao(ORDER_FETCH_SIZE);
		System.out.println(orderList);
		OrderManagementService oms = new OrderManagementService();
		UserAccountService uas = new UserAccountService();

		StockService stksvc = new StockService();
		int count = 0;

		for (Order order : orderList) {
			// validate the list either with term or not.
			// boolean success;
			// if (order.getTerm() == null) {
			// success = oms.validateOrderData(order.getUser_id(),
			// order.getCurrency_code(),
			// order.getOrder_type().toString(), order.getQuantity(),
			// order.getStock_symbol(),
			// order.getPrice_type().toString(),
			// convertDateObjToString(order.getPlace_order_date()),
			// order.getLimit_price(), "");
			// } else {
			// success = oms.validateOrderData(order.getUser_id(),
			// order.getCurrency_code(),
			// order.getOrder_type().toString(), order.getQuantity(),
			// order.getStock_symbol(),
			// order.getPrice_type().toString(),
			// convertDateObjToString(order.getPlace_order_date()),
			// order.getLimit_price(), order.getTerm().toString());
			// }
			////
			// if (success == true) {

			Double stockPrice = Double.parseDouble(
					stksvc.getStockFromExchange(order.getStock_symbol(), InfoType.BASIC).getMktPrice().toString());
			Integer quantity = order.getQuantity();
			Double total_price = stockPrice * quantity;
			Double balance = uas.getAccountBalance(order.getUser_id()).getBalance();
			System.out.println("stock price : " + stockPrice);
			System.out.println("limit price : " + order.getLimit_price());
			System.out.println("balance is " + balance);
			System.out.println("total price: " + total_price);
			System.out.println("Order price type is : " + order.getPrice_type().toString());
			System.out.println("Order type is : " + order.getOrder_type().toString());

			// check for Good for the day order to be cancelled at 5pm daily.

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// eg.
																					// 17:00:00
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			String nowtime = format2.format(now);
			String closetime = nowtime + " 18:00:00";
			Date closeDate = null;
			try {
				closeDate = (Date) format.parse(closetime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("TIME CHECK: " + closeDate);
			if (order.getTerm() != null && order.getTerm().toString().equals("GD")) {
				System.out.println("after close date");
				// check if term is GD
				if (now.after(closeDate)) {
					oms.processCancelledOrders(order.getOrder_id());
					System.out.println("This order has been cancelled for the day : orderID - " + order.getOrder_id());
					count++;
				}else if (order.getLimit_price() <= stockPrice) {
					System.out.println("Executing processOrder at OrderProcessor.\nPrice type is LT or SL");
					ex.submit(() -> oms.processOrder(order.getOrder_id(), stockPrice));
					count++;
					System.out.println("Count - " + count);
				}else{
					System.out.println("stock price is too high for now.");
				}

			} else {
				// if it is a buy order type, do the following
				System.out.println("inside else");
				if (order.getOrder_type().toString().equals("B")) {
					System.out.println("Inside B order");
					// if statement to ensure balance > total_price
					if (balance > total_price) {
						if (order.getPrice_type().toString().equals("SL")
								|| order.getPrice_type().toString().equals("LT")) {
							// if statement to check if the limit price
							if (order.getLimit_price() <= stockPrice) {
								System.out.println("Executing processOrder at OrderProcessor.\nPrice type is LT or SL");
								ex.submit(() -> oms.processOrder(order.getOrder_id(), stockPrice));
								count++;
								System.out.println("Count - " + count);
								// uas.debitBalance(order.getUser_id(),
								// total_price);
							}
						} else if (order.getPrice_type().toString().equals("M")) {
							System.out.println("Executing processOrder at OrderProcessor.\nPrice type is M");
							ex.submit(() -> oms.processOrder(order.getOrder_id(), stockPrice));
							count++;
							System.out.println("Count: " + count);
							// uas.debitBalance(order.getUser_id(),
							// total_price);
						}

						else {
							System.out.println("Please contact administrator. UserID : " + order.getUser_id()
									+ " OrderID " + order.getOrder_id());
						}
					} else {
						// this is when the balance is not enough
						// should the order be cancelled?
						System.out.println("problem with balance account - " + order.getUser_id() + "");
						oms.processCancelledOrders(order.getOrder_id());
					}
				}
				// if it is a sell order, do the following..
				else if (order.getOrder_type().toString().equals("S")) {
					System.out.println("Inside S order");
					// if the order price type is SL or LT
					if (order.getPrice_type().toString().equals("SL")
							|| order.getPrice_type().toString().equals("LT")) {
						// if statement to check if the limit price
						if (order.getLimit_price() < stockPrice) {
							System.out.println("Executing processOrder at OrderProcessor.\nPrice type is LT or SL");
							ex.submit(() -> oms.processSellOrder(order.getOrder_id(), order.getOld_order_id(),
									stockPrice, order.getQuantity()));
							count++;
							System.out.println("Count - " + count);
							uas.creditBalance(order.getUser_id(), total_price);
							System.out.println(
									"Amount - " + total_price + " has been credited to user " + order.getUser_id());
						}
					} else if (order.getPrice_type().toString().equals("M")) {
						System.out.println("Executing processOrder at OrderProcessor.\nPrice type is M");
						ex.submit(() -> oms.processSellOrder(order.getOrder_id(), order.getOld_order_id(), stockPrice,
								order.getQuantity()));
						count++;
						System.out.println("Count: " + count);
						uas.creditBalance(order.getUser_id(), total_price);
						System.out.println(
								"Amount - " + total_price + " has been credited to user " + order.getUser_id());
					}

				} else {
					System.out.println("Got problem.");
				}
			}
			// } else {
			// //this is when success is false
			// System.out.println("Validation failed.");
			// }
		}
		System.out.println(count + " order(s) has been processed.");
		return count;

	}

	public List<Order> TestfetchOrderFromDao(int limit) {
		return fetchOrderFromDao(limit);

	}

	private List<Order> fetchOrderFromDao(int limit) {

		Connection connect = null;
		try {
			this.setConnectionType(ConnectionType.LOCAL_CONNECTION);
			connect = getConnection();
			
			OrderDAO orderDao = new OrderDAO();
			List<Order> listOfOpenOrder = orderDao.getListOfOpenOrder(connect, limit);
			return listOfOpenOrder;
			
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			if (connect != null)
				try {
					connect.close();
				} catch (Exception e) {
						e.printStackTrace();
				}
		}

		return null;

	}
}
