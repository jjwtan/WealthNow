package com.fdm.wealthnow.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.wealthnow.common.SecurityQnAndAns;
import com.fdm.wealthnow.common.User;

/**
 * Servlet implementation class CompleteRegistrationController
 */
@WebServlet("/CompleteRegistrationController")
public class CompleteRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompleteRegistrationController() {
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
		System.out.println("creating user");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("UserProfile");
		System.out.println(user.getFirstName() + " " + user.getLastName());
		
		SecurityQnAndAns sqa = (SecurityQnAndAns) session.getAttribute("UserQnA");
		System.out.println(sqa.getQnId() + ": " + sqa.getAns());
		
		Float amount = Float.parseFloat(request.getParameter("deposit_amount"));
		System.out.println("intial balance: " + amount);
		
		String password = (String) session.getAttribute("password");
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

}
