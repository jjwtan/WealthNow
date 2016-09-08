<%@page import="com.fdm.wealthnow.service.PortfolioService"%>
<%@page import="com.fdm.wealthnow.common.InfoType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.DecimalFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="com.fdm.wealthnow.common.UserAuth,com.fdm.wealthnow.common.UserAccount,com.fdm.wealthnow.service.UserAccountService,
	com.fdm.wealthnow.service.StockService,com.fdm.wealthnow.common.StockHolding"%>
<html>
<head>

<style>
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
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portfolio Viewer</title>


<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>
<script>

    
</script>
</head>


<body>
	<div style="float: right">
	<a href="view_watchlist.jsp">My Watchlists</a>
<a href="update_balance.jsp">Update Balance</a>
<a href="OrderHistory.jsp">Order History</a>
		<a href="BuyPage.jsp">Buy Stocks</a> <a href="login.jsp">Logout</a>
	</div>
	<div style="float: left">
		<label for="order_ID" class="button">Refresh</label>
	</div>
	
	
	<%
		response.setIntHeader("Refresh", 20);
		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		UserAccountService uas = new UserAccountService();
		UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
		StockHolding sh = new StockHolding();
		int user_id = currentUser.getUser().getUserId();
		PortfolioService pfs = new PortfolioService();
	%>
	<h4>
		Welcome To Your Portfolio Viewer
		<%=currentUser.getUser().getFirstName() + " " + currentUser.getUser().getLastName()%>
		<br>Your Current Balance $<%=ua.getBalance()%></h4>


	<form action="PortfolioViewerController" method="post">

		<table id="t01">
			 
			<tr>
				<th colspan="2">Stock Symbol</th>
				<th>Last Trade($)</th>
				<th colspan="2">Change</th>
				<th>Qty</th>
				<th>Price Paid($)</th>
				<th colspan="2">Total Gain</th>

			</tr>
			<tr>
				<th></th>
				<th></th>
				<th></th>
				<th>%</th>
				<th>$</th>
				<th></th>
				<th></th>
				<th>$</th>
				<th>%</th>

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
					Double total_gain = purchase_price - mkt_price;
					Double total_gain_percent = total_gain / mkt_price;
					DecimalFormat df2 = new DecimalFormat("##.#####");
			%>



			<tr>
				<td><%=stock_symbol%></td>
				<td>
				
				<button type="submit" value="<%=order_id %>" name="order_ID" id="order_ID">Sell</button>
				
				<td><%=closing_price%></td>
				<td><%=change%></td>
				<td><%=percent_change%></td>
				<td><%=quantity%></td>
				<td><%=purchase_price%></td>
				<td><%=df2.format(total_gain)%></td>
				<td><%=df2.format(total_gain_percent)%></td>

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


</body>
</html>