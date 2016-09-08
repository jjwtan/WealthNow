package com.fdm.wealthnow.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.wealthnow.service.StockService;
import com.fdm.wealthnow.service.WatchlistService;

/**
 * Servlet implementation class AddStockWatchlistController
 */
@WebServlet("/AddStockWatchlistController")
public class AddStockWatchlistController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddStockWatchlistController() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("--------------------Inside add stockwatchlist controller------------------");
		String stock_symbol = request.getParameter("stock_Symbol");
		StockService ss = new StockService();
		WatchlistService wls = new WatchlistService();
//		Integer watchlist_ID = Integer.parseInt(session.getAttribute("watchlist_id").toString());
		Integer watchlist_ID = Integer.parseInt(request.getParameter("add_stock_watchlist_id"));

		if (ss.validateStock(stock_symbol) == true) {
			
			
			
			try {
				wls.addStockToWatchlist(watchlist_ID, stock_symbol);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("catch in addstock watchlist controller");
			}
			
			
			
			System.out.println("validating stock symbol");
			request.getRequestDispatcher("view_watchlist.jsp").forward(request, response);
			
		} else if (ss.validateStock(stock_symbol) == false) {

			request.setAttribute("errorMessage", "Please Enter a Valid Stock Symbol !");

			request.setAttribute("stock_symbol", stock_symbol);
			request.getRequestDispatcher("add_stock_watchlist.jsp").forward(request, response);
		}
	}

}
