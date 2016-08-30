package com.fdm.wealthnow.service;

import java.sql.Connection;
import java.util.List;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.util.DBUtil;

public class OrderProcessor extends DBUtil{
	static Connection connect;
	
	public OrderProcessor(){
		try {
			this.connect = getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public List fetchOrderFromDao() throws Exception{
		
		OrderDAO orderDao = new OrderDAO();
		List<Order> listOfOpenOrder = orderDao.getListOfOpenOrder(connect,10);
		for(Order newListOfOpenOrder : listOfOpenOrder){
			Double limitPrice = newListOfOpenOrder.getLimit_price();
			
		}
		
		
		return null;
	}
	
	public void timeToProcessOrder(Integer user_Id,Integer order_id){
		
	}
	
	
	public boolean checkLimitOfOrder(Integer order_id){
		
		
		
		
		return false;
	}

	
}
