package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.service.UserService;
import com.fdm.wealthnow.util.DBUtil;

/**
 * Servlet implementation class RegisterUserController
 */
@WebServlet("/RegisterUserController")
public class RegisterUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserController() {
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
		String username = request.getParameter("username");
		
		if(!validateUsername(request, response, username)){
			request.getRequestDispatcher("register_user_info.jsp").forward(request, response);
		}
		
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		String maidenName = request.getParameter("maidenName");
		
		System.out.println(	username + " " +
							password + " " +
							firstName + " " +
							lastName +  " " +
							birthday + " " +
							email +  " " +
							address + " " +
							phoneNumber);
		
		int userId;
		try {
			User userProfile = new User(9, username, firstName, lastName, birthday, email, phoneNumber, address, maidenName);
			
			HttpSession session = request.getSession();
			session.setAttribute("UserProfile", userProfile);
			session.setAttribute("password", password);
			System.out.println("forwarding to security questions");
			request.getRequestDispatcher("security_questions.jsp").forward(request, response);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	}

	private boolean validateUsername(HttpServletRequest request, HttpServletResponse response, String username) throws ServletException, IOException {
		request.setAttribute("errorMessage", "username has already been taken");
		return true;
	}



}
