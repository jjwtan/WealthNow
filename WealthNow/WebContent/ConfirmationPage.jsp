<%@page import="java.text.DecimalFormat"%>
<%@page import="com.fdm.wealthnow.common.InfoType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="com.fdm.wealthnow.common.UserAuth,com.fdm.wealthnow.common.UserAccount,com.fdm.wealthnow.service.UserAccountService,
	com.fdm.wealthnow.service.StockService"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation Page</title>


<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>
<script>
	

$("#submitbutton").click(function () {
	       alert("Thanks For Your Money Bitch");
	      
	   });
	
</script>





</head>
<body>
	<H1>Confirmation Page</H1>

	
		<%-- priceLimit = Double.parseDouble(limit_price); --%>
	<%
		Double brokerage_fee = 9.95;
		Double priceLimit = 0.00;
		StockService svc = new StockService();
		String price_type = request.getParameter("price_type");
		String quantity = request.getParameter("quantity");
		String stock_symbol = request.getParameter("stock_symbol");
		String limit_price = request.getParameter("lsl");
		DecimalFormat df = new DecimalFormat(".##");
		
		
	
		Double stock_price = Double.parseDouble(svc.getStockFromExchange(stock_symbol, InfoType.BASIC).getMktPrice().toString());				
		
		Double total_price = Double.parseDouble(quantity) * stock_price + brokerage_fee;
	
	%>

	<p>Stock Confirmation.</p>
	<form action="ConfirmationPageController" method="post"
		id="confirmation">
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
				<td>Stock Price</td>
				<td>$
					<%
						out.print(stock_price);
					%>
				</td>
				
			</tr>
			
			<tr>
				<td>Price Type</td>
				<td>
					<%
						out.print(price_type);
					%>
				</td>
				
			</tr>
			
			
			<tr>
				<td>Limit/Stop Loss</td>
				<td>$
					<%
						out.print(limit_price);
					%>
				</td>
			</tr>
			
			<tr>
				<td>Quantity</td>
				<td>
					<%
						out.print(quantity);
					%>
				</td>
			</tr>

			<tr>
				<td>Fixed Price</td>
				<td>$
					<%
						out.print(brokerage_fee);
					%>
				</td>
			</tr>

			<tr>
				<td colspan="5">&nbsp;</td>
			</tr>

			<tr>
				<td>Total price</td>
				<td width="126">$
					<%
						out.print(df.format(total_price));
					%>
				</td>
			</tr>
		</table>
		<br> <input type="submit" value="Comfirm Order" id="submitbutton">
	</form>



</body>
</html>