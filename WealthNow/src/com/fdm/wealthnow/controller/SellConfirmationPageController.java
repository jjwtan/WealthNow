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
import com.fdm.wealthnow.service.OrderManagementService;
import com.fdm.wealthnow.service.UserAccountService;
import com.fdm.wealthnow.util.DBUtil;

/**
 * Servlet implementation class SellConfirmationPage
 */
@WebServlet("/SellConfirmationPageController")
public class SellConfirmationPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellConfirmationPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("------------------Inside doPost for Confirmation Sell------------------------");
		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
		int user_id = currentUser.getUser().getUserId();
		DBUtil dbu = new DBUtil();
		OrderManagementService oms = new OrderManagementService();
		
		Integer order_ID = Integer.parseInt(session.getAttribute("order_ID").toString());
		System.out.println(order_ID);

		String stock_symbol = session.getAttribute("stock_symbol").toString();
//		Integer qty = request.getAttribute("quantity");
		Integer qty = Integer.parseInt(session.getAttribute("quantity").toString());
		Double selling_price = Double.parseDouble(session.getAttribute("selling_price").toString());
		Double fixed_price = 9.95;
		Double final_price = qty*selling_price + fixed_price;
		System.out.println("&&&&& fINAL PRICE IN SELLCOMFIRM CONTROLLER:" + final_price);
		
		
		
		
		System.out.println("try to create open order in doPost for sell page controller");
		try {
			oms.createSellOrder(order_ID,user_id, "SGD", "S", qty, stock_symbol, "M", dbu.convertDateObjToString(new Date()), new Double(0.0),
					"null", final_price);
//			oms.createOpenOrder(user_id, "SGD", "S", qty, stock_symbol, "M", dbu.convertDateObjToString(new Date()),
//					new Double(0.0), "null", final_price);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error in sell page controller in selling");
		}
		
		System.out.println("setting sessions to null");
//		session.setAttribute("orderID", null);
		session.setAttribute("final_price", null);
		session.setAttribute("selling_price", null);
		session.setAttribute("quantity", null);
		session.setAttribute("stock_symbol", null);
		
		 UserAccountService uas = new UserAccountService();
//		 System.out.println("crediting back to user" );
	        //uas.creditBalance(user_id,final_price);
//	        System.out.println(final_price);
		
		request.getRequestDispatcher("portfolio_viewer.jsp").forward(request, response);
	}

}
