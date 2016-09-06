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
import com.fdm.wealthnow.service.UserAccountService;

/**
 * Servlet implementation class PortfolioViewerController
 */
@WebServlet("/PortfolioViewerController")
public class PortfolioViewerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PortfolioViewerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 
		 UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
			UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
			int user_id =currentUser.getUser().getUserId();
			System.out.println("PortFolio for User ID:" + user_id);
		
			
	
	}

}
