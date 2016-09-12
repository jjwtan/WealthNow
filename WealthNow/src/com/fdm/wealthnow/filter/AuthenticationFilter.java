package com.fdm.wealthnow.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

	private ServletContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		System.out.println("Requested Resource:"+uri);
		
		// Special handle request to login page.Otherwise we will end up in infinite loop!
		if (checkURI(uri)) {
			//Do not create a new session if it does not exist!
			HttpSession session = req.getSession(false);
			if(session == null || session.getAttribute("loggedInUser") == null) {
				System.out.println("Unauthorized access request, redirecting to login page...");
				res.sendRedirect("login.jsp");
				return;
			}
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	private boolean checkURI(String uri) {
		return 	!uri.endsWith("/login.jsp")&&
				!uri.endsWith("/logout.jsp")&&
				!uri.endsWith("/LoginController")&&
				!uri.endsWith("/register_user_info.jsp") &&
				!uri.endsWith("/RegisterUserController")&&
				!uri.endsWith("/security_questions.jsp") &&
				!uri.endsWith("/SecurityQuestionController") &&
				!uri.endsWith("/initial_user_account.jsp") &&
				!uri.endsWith("/CompleteRegistrationController")&&
				!uri.contains("navbar")&&
				!uri.contains("css")&&
				!uri.contains("img")&&
				!uri.contains("include")&&
				!uri.contains("fonts");

	}

	public void destroy() {
		//close any resources here
	}

}