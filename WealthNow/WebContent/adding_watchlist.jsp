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
<!-- Java script and Bootstrap -->

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- --End of Bootstrap---------- -->
<link rel = "stylesheet" type = "text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adding WatchList</title>
</head>
<body>

	<%
		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		int userId = currentUser.getUser().getUserId();
	%>
	
		<div class="page-header" align="center">
		<h1>Add A New Watchlist</h1>
	</div>
	
	<form action="AddingWatchlistController" method="post">
		Please Enter Your WatchList Name:<input type="text"
			name="new_watchlist" id="new_watchlist" required> <input type="submit"
			value="Submit">
	</form>
</body>
</html>