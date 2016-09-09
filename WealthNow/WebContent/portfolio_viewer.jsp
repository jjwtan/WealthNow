<%@page import="com.fdm.wealthnow.service.PortfolioService"%>
<%@page import="com.fdm.wealthnow.common.InfoType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.DecimalFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="com.fdm.wealthnow.common.UserAuth,com.fdm.wealthnow.common.UserAccount,com.fdm.wealthnow.service.UserAccountService,
	com.fdm.wealthnow.service.StockService,com.fdm.wealthnow.common.StockHolding,java.util.Date,
			java.text.SimpleDateFormat"%>
<html>
<head>
<!--  -->
<!--  <style>
table {
	width: 100%;
}

table, th, td {
	border: 1px solid grey;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
	text-align: left;
}

table#t01 tr:nth-child(even) {
	background-color: #eee;
}

table#t01 tr:nth-child(odd) {
	background-color: #fff;
}

table#t01 th {
	background-color: black;
	color: white;
}
</style> -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portfolio Viewer</title>

<jsp:include page="include/css_import.jsp" />
<jsp:include page="include/navbar.jsp" />
<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>
<script>

    
</script>
</head>


<body>
<div class="container">
<!--  
	<div style="float: right">
	<a href="view_watchlist.jsp">My Watchlists</a>
<a href="update_balance.jsp">Update Balance</a>
<a href="OrderHistory.jsp">Order History</a>
		<a href="BuyPage.jsp">Buy Stocks</a> <a href="login.jsp">Logout</a>
	</div>
	-->
	
	
	
	<%
		session.setAttribute("quantity", null);
		//response.setIntHeader("Refresh", 20);
		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		UserAccountService uas = new UserAccountService();
		UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
		StockHolding sh = new StockHolding();
		int user_id = currentUser.getUser().getUserId();
		PortfolioService pfs = new PortfolioService();
		SimpleDateFormat sdf = new SimpleDateFormat("E dd/MM/yyyy hh:mm:ssa z");
	
		
		
	%>
	
		<div class="col-lg-12">
		<h4 style="text-align: center">
				Welcome To Your Portfolio Viewer
		<%=currentUser.getUser().getFirstName() + " " + currentUser.getUser().getLastName()%>
		<br>Your Current Balance $<%=ua.getBalance()%>
		</h4>
	</div>
	
	
	
	<div style="float: right">
			<i>Updated: <%=sdf.format(new Date())%></i>
		</div>
	
	<!--  
	<h4>
		Welcome To Your Portfolio Viewer
		<%=currentUser.getUser().getFirstName() + " " + currentUser.getUser().getLastName()%>
		<br>Your Current Balance $<%=ua.getBalance()%>
		</h4>
	-->


	<form action="PortfolioViewerController" method="post">

		<table class="table table-striped">
			 
			<tr>
				<th colspan="2">Stock Symbol</th>
				<th>Closing Price(Last Trade)</th>
				<th>Opening Price</th>
				<th colspan="2">Change</th>
				<th>Market Price</th>
				<th>Qty</th>
				<th>Price Paid</th>
				<th colspan="2">Gain Per Stock</th>
				<th>Total Price</th>

			</tr>
			<tr>
				<th></th>
				<th></th>
				<th>$</th>
				<th>$</th>
				<th>$</th>
				<th>%</th>
				<th>$</th>
				<th></th>
				<th>$</th>
				<th>$</th>
				<th>%</th>
				<th>$</th>

			</tr>
	

			<%
				int counter = 1;
				List<StockHolding> shList = pfs.getPortfolioInStockHolding(user_id);
				for (StockHolding newShList : shList) {
					System.out.println("List $$$$$$$$" + newShList);

					String stock_symbol = newShList.getStock_symbol();
					Integer order_id = newShList.getOrder_id();
					System.out.println("ORDEEERRRRRR£££££ :" + order_id);
					System.out.println("Stocksymbol@@@@@@@ " + stock_symbol);
					Integer quantity = newShList.getRemaining_quantity();
					Double purchase_price = newShList.getPurchase_price();

					StockService svc = new StockService();

					Double change = Double
							.parseDouble((svc.getStockFromExchange(stock_symbol, InfoType.FULL).getChange().toString()));

					String percent_change = svc.getStockFromExchange(stock_symbol, InfoType.FULL).getPercentChange();
					String day_val_change = svc.getStockFromExchange(stock_symbol, InfoType.FULL).getDaysValueChange();
					Double mkt_price = Double
							.parseDouble((svc.getStockFromExchange(stock_symbol, InfoType.FULL).getMktPrice().toString()));
					Double closing_price = Double
							.parseDouble(svc.getStockFromExchange(stock_symbol, InfoType.FULL).getClose().toString());
					Double total_gain =  mkt_price - purchase_price ;
					Double total_gain_percent = total_gain / mkt_price *100;
					DecimalFormat df2 = new DecimalFormat("##.#####");
					Double total_stock_price = purchase_price * quantity;
					
					Double opening_price = Double.parseDouble(svc.getStockFromExchange(stock_symbol, InfoType.FULL).getOpen().toString());
			%>



			<tr>
				<td><%=stock_symbol%></td>
				<td>
				
				<div style="padding-top: 5px; padding-bottom: 5px" class="center-block">
				<button type="submit" value="<%=order_id %>" name="order_ID" id="order_ID" class="btn btn-primary center-block">Sell</button>
				</div>
				
				<td><%=df2.format(closing_price)%></td>
				<td><%=df2.format(opening_price)%></td>
				<td><%=change%></td>
				<td><%=percent_change%></td>
				<td><%=df2.format(mkt_price)%></td>
				<td><%=quantity%></td>
				<td><%=df2.format(purchase_price)%></td>
				<td><%=df2.format(total_gain)%></td>
				<td><%=df2.format(total_gain_percent)%></td>
				<td><%=df2.format(total_stock_price) %></td>

			</tr>
			<%
				counter++;
				}

				if (shList.size() == 0) {
			%>
			<tr>
				<td colspan="9" align="center">You Have 0 Portfolio</td>
			</tr>
			<%
				}
			%>

		</table>
	

	</form>
	<br>
<button class="btn btn-success"><a href="portfolio_viewer.jsp" class="button"><font color="white">Refresh</font></a></button>
</div>
</body>
</html>