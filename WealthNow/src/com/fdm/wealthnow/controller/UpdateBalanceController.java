package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.wealthnow.common.UserAuth;
import com.fdm.wealthnow.service.UserAccountService;

/**
 * Servlet implementation class UpdateBalanceController
 */
@WebServlet("/UpdateBalanceController")
public class UpdateBalanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBalanceController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserAccountService uas = new UserAccountService();
		HttpSession session = request.getSession();
		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		int userId = currentUser.getUser().getUserId();
		
		String action = request.getParameter("Selection");
		System.out.println(action);
		String input;
		Double amount = null;
		switch (action) {
		case "Deposit":
			input = stringCheck(request.getParameter("deposit_amount"));
			amount = Double.parseDouble(input);
			uas.creditBalance(userId, amount);
			break;
		case "Withdraw":
			input = stringCheck(request.getParameter("withdraw_amount"));
			amount = Double.parseDouble(input);
			double status = uas.debitBalance(userId, amount);
			
			if(status < 0) {
				request.setAttribute("error_message", "Insufficient funds");
				request.getRequestDispatcher("update_balance.jsp").forward(request, response);
				return;
			}
			break;
		}

		// popup
		PrintWriter out = response.getWriter();  
		out.println("<script type=\"text/javascript\">");  
		out.println("alert('" + action + " $" + amount +"');");  
		out.println("</script>");
		request.getRequestDispatcher("homepage.jsp").forward(request, response);
	}

	private String stringCheck(String input) {
		if (input == null || input.equals("")) {
			return "0";
		} else {
			return input;
		}
		
	}

}
