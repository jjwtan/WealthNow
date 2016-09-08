<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="java.util.List,
			java.util.Date,
			java.text.SimpleDateFormat,
			com.fdm.wealthnow.common.UserAuth,
			com.fdm.wealthnow.common.UserAccount,
			com.fdm.wealthnow.common.Stock,
			com.fdm.wealthnow.common.Watchlist,
			com.fdm.wealthnow.service.UserAccountService,
			com.fdm.wealthnow.service.WatchlistService"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adding Stocks To WatchList</title>
</head>
<body>
	<h2>Add New Stocks</h2>

	<%
		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		int userId = currentUser.getUser().getUserId();
	%>
Your watchlist id: <%=session.getAttribute("add_stock_watchlist_id") %>
	<h3 style="color: red;">${errorMessage}</h3>

	<form action="AddStockWatchlistController" method="post">
		Stock Symbol:<input type="text" name="stock_Symbol"
			value="${stock_symbol}" pattern="[A-Za-z0-9]{3,4}" required
			placeholder="3-4 Characters" style="text-transform: uppercase">


		<input type="submit" id="submit" value="Submit">
	</form>

</body>
</html>