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
import com.fdm.wealthnow.common.Watchlist;
import com.fdm.wealthnow.service.StockService;
import com.fdm.wealthnow.service.UserAccountService;
import com.fdm.wealthnow.service.WatchlistService;
import com.fdm.wealthnow.util.DBUtil;

/**
 * Servlet implementation class AddingWatchlistController
 */
@WebServlet("/AddingWatchlistController")
public class AddingWatchlistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddingWatchlistController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
	UserAccountService uas = new UserAccountService();
	UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
	Integer user_id = ua.getUserId();
	DBUtil dbu = new DBUtil();
	Integer watchlist_id =0;
	try {
		watchlist_id = dbu.getSequenceID("WATCHLIST_ID_SEQ");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	String new_watchlist = session.getAttribute("new_watchlist").toString();
	session.setAttribute("new_watchlist", new_watchlist);
	WatchlistService wls = new WatchlistService();
	
	Watchlist wl = new Watchlist(watchlist_id, new_watchlist, null, new Date(), new Date());
	
	
	
	
	request.getRequestDispatcher("view_watchlist.jsp").forward(request, response);
	
	}

}
