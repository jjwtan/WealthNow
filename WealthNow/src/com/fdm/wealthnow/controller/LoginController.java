package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import com.fdm.wealthnow.service.UserService;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final int SESSION_TIMEOUT_IN_MINS = 30 * 60;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
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

		String hashedPw = null;
		hashedPw = getHash(password);
		
		System.out.println("hashed password: " + hashedPw);

		AuthDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
//		UserAuth user = UserService.userLogin(username, password);
		
		UserAuth user = null;
		try {
			user = AuthDAO.authenticate(AuthDAO.getConnection(),username, hashedPw);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (user.isAuthenticationSuccess()) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(SESSION_TIMEOUT_IN_MINS);
			session.setAttribute("loggedInUser", user);
			request.getRequestDispatcher("homepage.jsp").forward(request, response);

		} else {
			System.out.println("authentication failed");
			String errorMsg = "Invalid username or password";
			request.setAttribute("errorMessage", errorMsg); 
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

	private String getHash(String password) throws UnsupportedEncodingException {
		// convert password to md5
		byte[] plainText = password.getBytes("UTF-8");
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update( plainText, 0, plainText.length);
			return new BigInteger(1, messageDigest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
