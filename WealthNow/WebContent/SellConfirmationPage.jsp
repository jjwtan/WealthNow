<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="com.fdm.wealthnow.common.UserAuth,com.fdm.wealthnow.common.UserAccount,com.fdm.wealthnow.service.UserAccountService"%>
<%@page import="java.text.DecimalFormat"%>
<html>
<head>
<jsp:include page="include/css_import.jsp" />
<jsp:include page="include/navbar.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Selling Confirmation</title>
<div class="col-lg-12">
	<h2 style="text-align: center">Selling Confirmation Page</h2>
</div>
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

<br><br>
		<div class="col-md-6 col-md-offset-3">
			<p>Review Stock Details</p>
		</div>

<div class="col-md-6 col-md-offset-3">
<form action="SellConfirmationPageController" method="post" id="selling">
		<table class="table table-striped">
			<tr>
				<th width="407">Stock</th>
				<th width="126">SellingDetails</th>
			</tr>
			<tr>
				<td>Stock Symbol</td>
				<td>
					<%
						out.print(stock_symbol.toUpperCase());
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
		<br>  
		
					<div style="padding-top: 1em">
				<button type="submit" value="Comfirm Order" id="submit_button" class="btn btn-primary">Confirm
					Order</button>
			</div>  
	</form>

		<div style="padding-top: 1em">
			<button type="cancel" onclick="javascript:window.location='portfolio_viewer.jsp';" class="btn btn-danger">Cancel Order</button>
		</div>

</div>
		

</body>
</html>