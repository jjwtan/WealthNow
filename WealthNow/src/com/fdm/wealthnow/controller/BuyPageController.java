package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BuyPageController
 */
@WebServlet("/BuyPageController")
public class BuyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do get");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		System.out.println("buy page controller");
		
		String ordertype = request.getParameter("Selection");
		String quantity  = request.getParameter("quantity");
		String stock_symbol = request.getParameter("stock_symbol");
		String price_type = request.getParameter("price_type");
		String term = request.getParameter("term");
		String limit_price = request.getParameter("lsl");
		String order_type = request.getParameter("Selection");
		
		session.setAttribute("Selection", ordertype);
		session.setAttribute("quantity", quantity);
		session.setAttribute("stock_symbol", stock_symbol);
		session.setAttribute("price_type", price_type);
		session.setAttribute("term", term);
		
		System.out.println("forwarding to comfirmation page");
		request.getRequestDispatcher("ConfirmationPage.jsp").forward(request, response);
		
//		session.getAttribute("Selection");
//		session.getAttribute("quantity");
//		session.getAttribute("stock_symbol");
//		session.getAttribute("price_type");
//		session.getAttribute("term");
		
		//just for testing
		PrintWriter pw = response.getWriter();
		pw.println(ordertype+ " "+ quantity+ " "+ stock_symbol + " "+ price_type + " "+term);
		System.out.println(ordertype+ " "+ quantity+ " "+ stock_symbol + " "+ price_type + " "+term);
		
		//make sure that UserID has been checked before proceeding
		
		//Checking for user account balance
	
		
		
	}

}
