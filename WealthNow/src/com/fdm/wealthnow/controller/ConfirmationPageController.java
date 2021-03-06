package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.UserAccount;
import com.fdm.wealthnow.common.UserAuth;
import com.fdm.wealthnow.service.OrderManagementService;
import com.fdm.wealthnow.service.UserAccountService;
import com.fdm.wealthnow.util.DBUtil;

/**
 * Servlet implementation class ConfirmationPageController
 */
@WebServlet("/ConfirmationPageController")
public class ConfirmationPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmationPageController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		DBUtil dbu = new DBUtil();

		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
		int user_id = ua.getUserId();
//		String order_type = (String) session.getAttribute("Selection");
		Integer qty = Integer.parseInt(session.getAttribute("quantity").toString());
		String stock_symbol = (String) session.getAttribute("stock_symbol");

		String price_type =(String) session.getAttribute("price_type");
		Double lsl = Double.parseDouble(session.getAttribute("lsl").toString());
		String term = (String) session.getAttribute("term");
		String txDate = dbu.convertDateObjToString(new Date());

		OrderManagementService oms = new OrderManagementService();
		UserAccountService uas = new UserAccountService();
		Double amountToDebit = (Double) session.getAttribute("total_price");
		uas.debitBalance(user_id, amountToDebit);
		
		System.out.println(user_id + " "  + " " + qty + " " + stock_symbol.toUpperCase() + " " + price_type +" " + term +" "+ txDate + " " +lsl +" " + amountToDebit);
		try {
			
			if((lsl == null) && (term.equals("null"))){
				oms.createOpenOrder(new Integer(user_id), "SGD","B",qty, stock_symbol.toUpperCase(),
						price_type, txDate, null, null, amountToDebit);
				
				
//				session.setAttribute("Selection", null);
				session.setAttribute("quantity", null);
				session.setAttribute("stock_symbol", null);
				session.setAttribute("price_type", null);
				session.setAttribute("term", null);
				session.setAttribute("lsl", null);
				session.setAttribute("total_price", null);
				
				
				
			}
			else{
				oms.createOpenOrder(new Integer(user_id), "SGD","B",qty, stock_symbol.toUpperCase(),
						price_type, txDate, lsl, term , amountToDebit);
				System.out.println(" comfirmation page controller else method:"+user_id +  " " + qty + " " + stock_symbol.toUpperCase() + " " + price_type +" " + term +" "+ txDate + " " +lsl +" " + amountToDebit);

				
//				session.setAttribute("Selection", null);
				session.setAttribute("quantity", null);
				session.setAttribute("stock_symbol", null);
				session.setAttribute("price_type", null);
				session.setAttribute("term", null);
				session.setAttribute("lsl", null);
				session.setAttribute("total_price", null);
				
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("portfolio_viewer.jsp").forward(request, response);// forward
																				// to
																				// desired
																				// page
																				// upon
																				// press
																				// of
																				// button.
	}

}
