package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.UserAccount;
import com.fdm.wealthnow.common.UserAuth;
import com.fdm.wealthnow.service.StockService;
import com.fdm.wealthnow.service.UserAccountService;

/**
 * Servlet implementation class BuyPageController
 */
@WebServlet("/BuyPageController")
public class BuyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyPageController() {
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
		System.out.println("do get");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		Double brokerage_fee = 9.95;

		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		UserAccountService uas = new UserAccountService();
		StockService svc = new StockService();

		UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());

		System.out.println("buy page controller");

		String ordertype = request.getParameter("Selection");
		String quantity = request.getParameter("quantity");
		String stock_symbol = request.getParameter("stock_symbol");
		String price_type = request.getParameter("price_type");
		String term = request.getParameter("term");
		String limit_price = request.getParameter("lsl");
		String order_type = request.getParameter("Selection");
		String term1 = request.getParameter("term");

		StockService ss = new StockService();
		
		if (ss.validateStock(stock_symbol) == true) {

			Double stock_price = Double
					.parseDouble(svc.getStockFromExchange(stock_symbol, InfoType.BASIC).getMktPrice().toString());
			Double total_price = Double.parseDouble(quantity) * stock_price + brokerage_fee;
			Double newBalance = uas.getAccountBalance(ua.getUserId()).getBalance();
			Double afterDebit = newBalance - total_price;
			//store total_price to session to be used at confirmation page controller
			session.setAttribute("total_price", total_price);

			if (price_type.equals("M")) {

				if (afterDebit < 0) {

					System.out.println("Inside if afterDebit < 0 error");
					request.setAttribute("errorMessage", "Insufficient Funds! Please Try Again");
					request.setAttribute("quantity", quantity);
					request.setAttribute("stock_symbol", stock_symbol);
					request.getRequestDispatcher("BuyPage.jsp").forward(request, response);

				}
				if (afterDebit > 0) {
					System.out.println("forwarding to comfirmation page");
					request.getRequestDispatcher("ConfirmationPage.jsp").forward(request, response);

					System.out.println("Setting attributes.BP Controller");

					session.setAttribute("Selection", ordertype);
					session.setAttribute("quantity", quantity);
					session.setAttribute("stock_symbol", stock_symbol);
					session.setAttribute("price_type", price_type);
					session.setAttribute("term", term1);
					session.setAttribute("lsl", limit_price);

				}

			} else if (price_type.equals("L") || price_type.equals("SL")) {

				if (term1.equals("null")) {
					System.out.println("Inside else for term = null");

					
					request.setAttribute("errorMessage", "Please enter TERM.");

					request.setAttribute("quantity", quantity);
					request.setAttribute("stock_symbol", stock_symbol);
					request.getRequestDispatcher("BuyPage.jsp").forward(request, response);
				} else {

					if (afterDebit < 0) {

						System.out.println("Inside if afterDebit <0 error");
						request.setAttribute("errorMessage", "Insufficient Funds! Please Try Again");
						request.setAttribute("quantity", quantity);
						request.setAttribute("stock_symbol", stock_symbol);
						request.getRequestDispatcher("BuyPage.jsp").forward(request, response);

					}
					if (afterDebit > 0) {
						System.out.println("forwarding to comfirmation page");
						request.getRequestDispatcher("ConfirmationPage.jsp").forward(request, response);

						System.out.println("Setting attributes.BP Controller");

						session.setAttribute("Selection", ordertype);
						session.setAttribute("quantity", quantity);
						session.setAttribute("stock_symbol", stock_symbol);
						session.setAttribute("price_type", price_type);
						session.setAttribute("term", term1);
						session.setAttribute("lsl", limit_price);

					}

				}

			}

		}else if (ss.validateStock(stock_symbol) == false) {
			System.out.println("Inside else if method for stock_symbol = false");
			request.setAttribute("errorMessage", "Please Enter a Valid Stock Symbol !");
			request.setAttribute("quantity", quantity);
			request.setAttribute("stock_symbol", stock_symbol);
			request.getRequestDispatcher("BuyPage.jsp").forward(request, response);

		}
		
		

		// check for stock symbol using stock service validate
		// if else condition

		// just for testing
		PrintWriter pw = response.getWriter();
		pw.println(ordertype + " " + quantity + " " + stock_symbol + " " + price_type + " " + term1);
		System.out.println(ordertype + " " + quantity + " " + stock_symbol + " " + price_type + " " + term1);

		// make sure that UserID has been checked before proceeding

		// Checking for user account balance

	}

}
