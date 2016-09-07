<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="com.fdm.wealthnow.common.UserAuth,com.fdm.wealthnow.common.UserAccount,com.fdm.wealthnow.service.UserAccountService"%>
<%@page import="java.text.DecimalFormat"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>
	
	<script>
	

$(document).ready ( function () {
    $("#submitbutton").click(function () {
       alert("Order Confirmed!");
    });
});
	
</script>

</head>
<body>

	<% 
	
	
	 UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
	DecimalFormat df = new DecimalFormat(".##"); 
	
	String stock_symbol= session.getAttribute("stock_symbol").toString();
	Integer quantity = Integer.parseInt(session.getAttribute("quantity").toString());
	Double selling_price = Double.parseDouble(session.getAttribute("selling_price").toString());
	Double final_price = Double.parseDouble(session.getAttribute("final_price").toString());
	
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

			<tr>
				<td>Fixed Price</td>
				<td>$ <%
						out.print(fixed_price);
					%>
				</td>
			</tr>

			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>

			<tr>
				<td>Total Selling price</td>
				<td width="126">$ <%
						out.print(df.format(final_price));
					%>
				</td>
			</tr>
		</table>
		<br> <input type="submit" value="Sell Order" id="submit_button">
	</form>


</body>
</html>