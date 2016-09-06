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
@WebServlet("/SellPage")
public class SellPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellPageController() {
		super();
		// TODO Auto-generated constructor stub
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

		String stock_symbol = session.getAttribute("stock_symbol").toString();
		Integer qty = Integer.parseInt(session.getAttribute("quantity").toString());
		Double selling_price = Double.parseDouble(session.getAttribute("selling_price").toString());
		Double final_price = Double.parseDouble(session.getAttribute("final_price").toString());

		try {
			oms.createOpenOrder(user_id, "SGD", "S", qty, stock_symbol, "M", dbu.convertDateObjToString(new Date()),
					new Double(0.0), "null", final_price);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error in pfc in selling");
		}
		
		session.setAttribute("final_price", null);
		session.setAttribute("selling_price", null);
		session.setAttribute("quantity", null);
		session.setAttribute("stock_symbol", null);
		
		request.getRequestDispatcher("portfolio_viewer.jsp").forward(request, response);
		

	}

}
