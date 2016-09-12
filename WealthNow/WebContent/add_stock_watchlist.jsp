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
<title>Adding Stocks to WatchList</title>
</head>
<body>
<div class="col-lg-12"><h2 style="text-align: center">Add New Stocks</h2></div>

	<%
		UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		int userId = currentUser.getUser().getUserId();
	%>
<br>
<div class="row">
<div class="col-md-4 col-md-offset-2">
Your watchlist id: <%=session.getAttribute("add_stock_watchlist_id") %>
	<h3 style="color: red;">${errorMessage}</h3>
	</div>
	</div>

<div class="row">
<div class="col-md-4 col-md-offset-2">
	<!-- Start of stock symbol form -->
	<form action="AddStockWatchlistController" method="post">
		Stock Symbol:<input class="form-control" type="text" name="stock_Symbol"
			value="${stock_symbol}" pattern="[A-Za-z0-9]{3,4}" required
			placeholder="3-4 Characters" style="text-transform: uppercase" autofocus>
		<!--  
		<input type="submit" id="submit" value="Submit">
		-->
				<div style="padding-top: 1em">
							<button type="submit" id="submit" class="btn btn-primary">Add this stock</button>
				</div>
	</form>
	<!-- End of stock symbol form -->
	</div>
	</div>

</body>
</html>