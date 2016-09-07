package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.wealthnow.common.UserAccount;
import com.fdm.wealthnow.common.UserAuth;
import com.fdm.wealthnow.service.OrderManagementService;
import com.fdm.wealthnow.service.UserAccountService;
import com.fdm.wealthnow.util.DBUtil;

/**
 * Servlet implementation class SellPage
 */
@WebServlet("/SellPageController")
public class SellPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellPageController() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		System.out.println("Inside doPost for SellPage");
		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
		int user_id = currentUser.getUser().getUserId();
		DBUtil dbu = new DBUtil();
		OrderManagementService oms = new OrderManagementService();
		
		Integer order_ID = Integer.parseInt(session.getAttribute("order_ID").toString());
		System.out.println(order_ID);
		
		String stock_symbol = session.getAttribute("stock_symbol").toString();
		Integer qty = Integer.parseInt(request.getParameter("quantity"));
//		Integer qty = Integer.parseInt(session.getAttribute("quantity1").toString());
		System.out.println("Quantity in Sell Page controller $$$$$$$$:" + qty);
		Double selling_price = Double.parseDouble(session.getAttribute("selling_price").toString());
//		Double total_price = selling_price*qty;
		
		session.setAttribute("quantity", qty);
//		session.setAttribute("total_price", total_price);
	      
		
		request.getRequestDispatcher("SellConfirmationPage.jsp").forward(request, response);
		
	        
		

	}

}
