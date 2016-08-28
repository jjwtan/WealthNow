package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.common.UserAuth;
import com.fdm.wealthnow.dao.AuthDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final int SESSION_TIMEOUT_IN_MINS = 30 * 60;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username -> " + request.getParameter("username"));
		System.out.println("password -> " + request.getParameter("password"));

		System.out.println("forwarding");
		request.getRequestDispatcher("welcome.jsp").forward(request, response);
		
//		UserAuth user = AuthDAO.authenticate(username, password);
//		if (user.isAuthenticationSuccess()) {
//			HttpSession session = request.getSession();
//			session.setMaxInactiveInterval(SESSION_TIMEOUT_IN_MINS);
//			session.setAttribute("loggedInUser", username);

//		} else {
//			System.out.println("authentication failed");
//			request.getRequestDispatcher("login.jsp").forward(request, response);
//		}

	}

}
