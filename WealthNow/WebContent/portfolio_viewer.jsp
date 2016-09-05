<%@page import="com.fdm.wealthnow.service.PortfolioService"%>
<%@page import="com.fdm.wealthnow.common.InfoType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import ="java.util.*" %>
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

	<%
		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		UserAccountService uas = new UserAccountService();
		UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
		StockHolding sh = new StockHolding();
		int user_id = ua.getUserId();
		PortfolioService pfs = new PortfolioService();
		
		
		List<StockHolding> shList = pfs.getPortfolioInStockHolding(user_id);
		for(StockHolding newShList:shList){
			sh = newShList;
			
		}
		String stock_symbol = sh.getStock_symbol();
		String stock_symbol1 = sh.getStock_symbol();
		Integer quantity = sh.getRemaining_quantity();
		Integer quantity1 = sh.getRemaining_quantity();
		Double price_paid = sh.getPurchase_price();
		Double price_paid1 = sh.getPurchase_price();
		
		StockService svc = new StockService();
		String percent_change = svc.getStockFromExchange(stock_symbol, InfoType.FULL).getPercentChange();
		Float change = svc.getStockFromExchange(stock_symbol, InfoType.FULL).getChange();
		Float day_high = svc.getStockFromExchange(stock_symbol, InfoType.FULL).getDayHigh();
		Float day_low = svc.getStockFromExchange(stock_symbol, InfoType.FULL).getDayLow();
		String day_val_change=svc.getStockFromExchange(stock_symbol, InfoType.FULL).getDaysValueChange();
		Float mkt_price=svc.getStockFromExchange(stock_symbol, InfoType.FULL).getMktPrice();
		svc.getStockFromExchange(stock_symbol, InfoType.FULL);
		
		
		
	%>


	<form action="PortfolioViewer" method="post">

		<table id="t01">
			 
			<tr>
				<th colspan="2">Stock Symbol</th>
				<th>Last Trade</th>
				<th colspan="2">Change</th>
				<th>Day's Gain</th>
				<th>Qty</th>
				<th>Price Paid</th>
				<th colspan="2">Total Gain</th>
				<th>Market Value</th>
			</tr>
			<tr>
				<th></th>
				<th></th>
				<th></th>
				<th>%</th>
				<th>$</th>
				<th></th>
				<th></th>
				<th></th>
				<th>%</th>
				<th>$</th>
				<th></th>
			</tr>

			<tr>
				<td><%=stock_symbol%></td>
				<td><a href="BuyPage.jsp">Buy/</a><a href="www.google.com">Sell</a></td>
				<td><%=change %></td>
				<td><%=percent_change%></td>
				<td><%=day_high %></td>
				<td><%=day_val_change %></td>
				<td><%=quantity %></td>
				<td><%=price_paid %></td>
				<td>%</td>
				<td>$</td>
				<td></td>
			</tr>
			<tr>
				<td><%=stock_symbol1%></td>
				<td><a href="BuyPage.jsp">Buy/</a><a href="www.google.com">Sell</a></td>
				<td><%=change %></td>
				<td><%=percent_change%></td>
				<td><%=day_high %></td>
				<td><%=day_val_change %></td>
				<td><%=quantity1 %></td>
				<td><%=price_paid1 %></td>
				<td>%</td>
				<td>$</td>
				<td></td>
			</tr>

		</table>
	</form>


</body>
</html>