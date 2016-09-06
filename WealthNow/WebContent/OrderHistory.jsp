<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.fdm.wealthnow.service.PortfolioService"%>
<%@ page
	import="com.fdm.wealthnow.common.UserAuth,com.fdm.wealthnow.common.UserAccount,com.fdm.wealthnow.service.UserAccountService,
	com.fdm.wealthnow.service.StockService,com.fdm.wealthnow.common.StockHolding"%>
	
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
		<a href="BuyPage.jsp">Buy Stocks</a> <a href="login.jsp">Logout</a>
	</div>

	<%
		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		UserAccountService uas = new UserAccountService();
		UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
		
		int user_id = currentUser.getUser().getUserId();
		
	%>

</body>
</html>