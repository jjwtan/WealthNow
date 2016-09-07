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
<title>Adding WatchList</title>
</head>
<body>

	<%
		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		int userId = currentUser.getUser().getUserId();
	%>
	<h2>Adding WatchList</h2>
	<form action="AddingWatchlistController" method="post">
		Please Enter Your WatchList Name:<input type="text"
			name="new_watchlist" id="new_watchlist" required> <input type="submit"
			value="Submit">
	</form>
</body>
</html>