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
    $("#submit_button").click(function () {
       alert("Order Confirmed!");
    });
});
	
</script>
</head>
<body>

<% 
 UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
	DecimalFormat df = new DecimalFormat("##.##"); 
	
	String stock_symbol= session.getAttribute("stock_symbol").toString();
	Integer quantity = Integer.parseInt(session.getAttribute("quantity").toString());
	Double selling_price = Double.parseDouble(session.getAttribute("selling_price").toString());
	
	
	Double fixed_price = 9.95;
	Double final_price = selling_price * quantity + fixed_price;
	%>


<form action="SellConfirmationPageController" method="post" id="selling">
		<table width="559" border="1" cellpadding="5" cellspacing="0">
			<tr>
				<th width="407">Stock</th>
				<th width="126">SellingDetails</th>
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
						out.print(df.format(selling_price));
					%>
				</td>

			</tr>

			<tr>
				<td>Quantity</td>
				<td><% out.print(quantity);%></td>

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
		<br> <input type="submit" value="Comfirm Order" id="submit_button">   
	</form>
<button type="cancel" onclick="javascript:window.location='portfolio_viewer.jsp';">Cancel Order</button>
		

</body>
</html>