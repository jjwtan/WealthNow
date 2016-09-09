package com.fdm.wealthnow.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.wealthnow.service.OrderManagementService;
import com.fdm.wealthnow.service.WatchlistService;

/**
 * Servlet implementation class CancelOrderController
 */
@WebServlet("/CancelOrderController")
public class CancelOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CancelOrderController() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("--------------------Inside cancel order controller------------------");

		Integer order_id = Integer.parseInt(request.getParameter("cancel"));

		System.out.println("Order ID:" + order_id);
		OrderManagementService oms = new OrderManagementService();

		try {

			oms.processCancelledOrders(order_id);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("catch in cancel order controller");
		}

		System.out.println("Cancel Order Controller done.");
		request.getRequestDispatcher("OrderHistory.jsp").forward(request, response);

	}

}
