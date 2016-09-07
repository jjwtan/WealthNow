package com.fdm.wealthnow.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.wealthnow.common.SecurityQnAndAns;

/**
 * Servlet implementation class SecurityQuestionController
 */
@WebServlet("/SecurityQuestionController")
public class SecurityQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecurityQuestionController() {
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
		
		
		Integer questionId = Integer.parseInt(request.getParameter("question"));
		String answer = request.getParameter("answer");
		System.out.println("question selected:" + questionId + " answer: " + answer);
		SecurityQnAndAns sqa = new SecurityQnAndAns(questionId, answer);
		
		HttpSession session = request.getSession();
		session.setAttribute("UserQnA", sqa);
		
		request.getRequestDispatcher("initial_user_account.jsp").forward(request, response);
	}

}
