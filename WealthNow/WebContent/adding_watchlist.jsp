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
<jsp:include page="include/css_import.jsp" />
<jsp:include page="include/navbar.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New WatchList</title>
</head>
<body>

	<%
		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		int userId = currentUser.getUser().getUserId();
	%>

	<div class="col-lg-12">
		<h2 style="text-align: center">Add New Watchlist</h2>
	</div>

	<div class="row">
		<div class="col-md-4 col-md-offset-2">
		<br>
			<form action="AddingWatchlistController" method="post">
				Please Enter Your WatchList Name:<input type="text"
					name="new_watchlist" id="new_watchlist" required>
					
									<div style="padding-top: 1em">
							<button type="submit" id="submit" class="btn btn-primary">Add new watchlist</button>
				</div> 
			</form>
		</div>
	</div>
</body>
</html>