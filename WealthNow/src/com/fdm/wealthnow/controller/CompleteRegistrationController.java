package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.wealthnow.common.SecurityQnAndAns;
import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.service.UserRegisterService;
import com.fdm.wealthnow.util.DBUtil;

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
		try {
			int user_id = DBUtil.getSequenceID("user_id_seq");
			user.setUserId(user_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(user.getFirstName() + " " + user.getLastName());
		
		SecurityQnAndAns sqa = (SecurityQnAndAns) session.getAttribute("UserQnA");
		System.out.println(sqa.getQnId() + ": " + sqa.getAns());
		
		Float amount = Float.parseFloat(request.getParameter("deposit_amount"));
		System.out.println("intial balance: " + amount);
		
		String password = (String) session.getAttribute("password");
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
		
		UserRegisterService urs = new UserRegisterService();
		urs.registerUser(user, sqa, getHash(password), amount, true);
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
