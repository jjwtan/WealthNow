package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.UserAccount;
import com.fdm.wealthnow.common.UserAuth;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.service.OrderManagementService;
import com.fdm.wealthnow.service.PortfolioService;
import com.fdm.wealthnow.service.StockService;
import com.fdm.wealthnow.service.UserAccountService;
import com.fdm.wealthnow.util.DBUtil;

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
		DBUtil dbu = new DBUtil();
		 HttpSession session = request.getSession();
		 System.out.println("Inside doPost for Portfolio Viewer");
		 UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		 
			UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
			Integer user_id =currentUser.getUser().getUserId();
			System.out.println("PortFolio for User ID:" + user_id);
			
			Integer orderID = new Integer(request.getParameter("order_ID"));
			 PrintWriter out = response.getWriter();
		        out.println(orderID);
		        
		       
		        
		        OrderManagementService oms = new OrderManagementService();
		        Order order = oms.getOrderFromProcessedOrder(orderID);
		        Integer qty = order.getQuantity();
		       StockService svc = new StockService();
		       
		        String stock_symbol = order.getStock_symbol();
		        Double selling_price = Double.parseDouble(svc.getStockFromExchange(stock_symbol, InfoType.FULL).getMktPrice().toString());
		       Double final_price = qty*selling_price;
		       
		       session.setAttribute("final_price", final_price);
		       session.setAttribute("selling_price", selling_price);
		       session.setAttribute("stock_symbol", stock_symbol);
		       session.setAttribute("quantity", qty);
		        
		        
		       
		        request.getRequestDispatcher("SellPage.jsp").forward(request, response);
			
			
			
	
	}

}
