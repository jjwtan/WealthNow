package com.fdm.wealthnow.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.wealthnow.common.UserAccount;
import com.fdm.wealthnow.common.UserAuth;
import com.fdm.wealthnow.service.StockService;
import com.fdm.wealthnow.service.UserAccountService;
import com.fdm.wealthnow.service.WatchlistService;
import com.fdm.wealthnow.util.DBUtil;

/**
 * Servlet implementation class DeleteStockWatchlistController
 */
@WebServlet("/DeleteStockWatchlistController")
public class DeleteStockWatchlistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStockWatchlistController() {
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
		HttpSession session = request.getSession();
		System.out.println("--------------------Inside delete stockwatchlist controller------------------");
		
		String stock_symbol = request.getParameter("delete");
		Integer watchlist_ID = Integer.parseInt(session.getAttribute("add_stock_watchlist_id").toString());
		
		request.setAttribute("watchlist_id", watchlist_ID);
		
		System.out.println("stock symbol:" + stock_symbol + "id:" + watchlist_ID);
		WatchlistService wls = new WatchlistService();
		
	
		try{
		
			wls.deleteStockFromWatchlist(watchlist_ID, stock_symbol);
		
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("catch in delete stock watchlist controller");
		}

		System.out.println("Deleting stock symbol");
		request.getRequestDispatcher("view_watchlist.jsp").forward(request, response);
		
	}

}






