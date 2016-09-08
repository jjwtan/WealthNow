<%@page import="com.fdm.wealthnow.dao.OrderDAO"%>
<%@page import="com.fdm.wealthnow.service.OrderManagementService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.fdm.wealthnow.service.PortfolioService"%>
<%@ page
	import="com.fdm.wealthnow.common.UserAuth,com.fdm.wealthnow.common.UserAccount,com.fdm.wealthnow.service.UserAccountService,
	com.fdm.wealthnow.service.StockService,com.fdm.wealthnow.common.StockHolding, java.util.List, com.fdm.wealthnow.common.Order"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>Order History</title>
</head>
<body>

	<div style="float: right">
	<a href="view_watchlist.jsp">My Watchlists</a>
<a href="update_balance.jsp">Update Balance</a>
<a href="portfolio_viewer.jsp">Portfolio Viewer</a>
		<a href="BuyPage.jsp">Buy Stocks</a> <a href="login.jsp">Logout</a>
	</div>

	<%
	UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
	OrderManagementService oms = new OrderManagementService();
	PortfolioService ps = new PortfolioService();
	OrderDAO ord = new OrderDAO();
	Integer user_id = currentUser.getUser().getUserId();
		
		
	%>
	<h1>Completed/Cancelled Orders</h1>
	<table  id="t01">
			<tr>
				<th>Date Placed Order</th>
				<th>Order No</th>
				<th>Type</th>
				<th>Quantity</th>
				<th>Symbol</th>
				<th>Price Type</th>
				<th>Price</th>		
				<th>Status</th>
			</tr>
			<%
			List <Order> orderList = oms.getAllProcessedOrderFromOrderDAO(user_id);

					for (Order order : orderList) {
						
			%>
			<tr>
				
				<td><%=order.getPlace_order_date()%></td>
				<td><b># <%=order.getOrder_id()%></b></td>
				<td><%=order.getOrder_type().toString()%></td>
				<td><%=order.getQuantity()%></td>
				<td><%=order.getStock_symbol()%></td>
				<td><%=order.getPrice_type()%></td>
				<td>$ <%=order.getClosing_price()%></td>
				<td><b><%=order.getStatus()%></b></td>
			</tr>
			<%
				}
					
			%>
		

</body>
</html>