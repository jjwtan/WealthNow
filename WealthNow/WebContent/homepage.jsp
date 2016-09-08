<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="com.fdm.wealthnow.common.UserAuth,
			com.fdm.wealthnow.common.UserAccount,
			com.fdm.wealthnow.service.UserAccountService"%>
<html>
<head>
<title>Homepage</title>
<jsp:include page="include/css_import.jsp" />
<jsp:include page="include/navbar.jsp" />
</head>
<body>
	<%
		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
	%>
	<!-- 
<div style="float: right">
<font color="white" size="5px">
	<a href="logout.jsp">Logout</a>
	</font>
</div>
-->
	<div class="page-header" align="center">
		<h1>
			Welcome
			<%=currentUser.getUser().getFirstName() + " " + currentUser.getUser().getLastName()%></h1>
	</div>
	<h2>
		Your balance is $<%=ua.getBalance()%></h2>
	<br>
	<br>
	<br>

	<!-- 
<div align="center">
<button type="button" class="btn btn-primary"><a href="view_watchlist.jsp"><font color="white">My Watchlists</font></a></button><br><br>
<button type="button" class="btn btn-primary"><a href="BuyPage.jsp"><font color="white">Buy/Sell Securities</font></a></button><br><br>
<button type="button" class="btn btn-primary"><a href="update_balance.jsp"><font color="white">Update Balance</font></a></button><br><br>
<button type="button" class="btn btn-primary"><a href="portfolio_viewer.jsp"><font color="white">Portfolio Viewer</font></a></button><br><br>
<button type="button" class="btn btn-primary"><a href="OrderHistory.jsp"><font color="white">Order History</font></a></button><br><br>
</div>
-->

</body>
</html>