<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="com.fdm.wealthnow.common.UserAuth,com.fdm.wealthnow.common.UserAccount,com.fdm.wealthnow.service.UserAccountService"%>
<%@page import="java.text.DecimalFormat"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sell Page</title>
<jsp:include page="include/css_import.jsp" />
<jsp:include page="include/navbar.jsp" />

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
<a href="portfolio_viewer.jsp">Portfolio Viewer</a>
		<a href="BuyPage.jsp">Buy Stocks</a> <a href="login.jsp">Logout</a>
	</div>

	<% 
	
	
	
	 UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
	DecimalFormat df = new DecimalFormat(".##"); 
	
	String stock_symbol= session.getAttribute("stock_symbol").toString();
	Integer quantity = Integer.parseInt(session.getAttribute("quantity").toString());
	Double selling_price = Double.parseDouble(session.getAttribute("selling_price").toString());
	
	Double fixed_price = 9.95;
	
	
	
	
	


%>


	<H1>Selling Page</H1>


	<form action="SellPageController" method="post" id="selling">
		<table width="559" border="1" cellpadding="5" cellspacing="0">
			<tr>
				<th width="407">Stock</th>
				<th width="126">Details</th>
			</tr>
			<tr>
				<td>Stock Symbol</td>
				<td>
					<%
						out.print(stock_symbol);
					%>
				</td>
			</tr>
			<tr>
				<td>Selling Stock Price</td>
				<td>$ <%
						out.print(selling_price);
					%>
				</td>

			</tr>

			<tr>
				<td>Quantity</td>
				<td>
					<input type="number" name="quantity" step="1" min="1" value="0" max="<%=quantity%>">
					
				</td>

			</tr>

			
		</table>
		<br> <input type="submit" value="Sell Order" id="submit_button">
	</form>


</body>
</html>