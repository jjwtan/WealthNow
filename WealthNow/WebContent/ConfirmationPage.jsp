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
<jsp:include page="include/css_import.jsp" />
<jsp:include page="include/navbar.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation Page</title>
<div class="col-lg-12">
	<h2 style="text-align: center">Confirmation Page</h2>
</div>

<!-- Edited by Nes -->

<br>
<br>
<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#submitbutton").click(function() {
			alert("Order Confirmed!");
		});
	});
</script>
</head>
<body>
	<div class="container">
		<%-- priceLimit = Double.parseDouble(limit_price); --%>
		<%
			Double brokerage_fee = 9.95;
			Double priceLimit = 0.00;
			StockService svc = new StockService();
			String price_type = request.getParameter("price_type");
			String quantity = request.getParameter("quantity");
			String stock_symbol = request.getParameter("stock_symbol");
			String limit_price = request.getParameter("lsl");
			String term = request.getParameter("term");
			DecimalFormat df = new DecimalFormat(".##");

			Double stock_price = Double
					.parseDouble(svc.getStockFromExchange(stock_symbol, InfoType.BASIC).getMktPrice().toString());

			Double total_price = Double.parseDouble(quantity) * stock_price + brokerage_fee;
		%>

		<div class="col-md-6 col-md-offset-3">
			<p>Review Stock Details</p>
		</div>

		<div class="col-md-6 col-md-offset-3">
			<form action="ConfirmationPageController" method="post"
				id="confirmation">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Stock</th>
							<th>Details</th>
						</tr>
					</thead>

					<tbody>
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
							<td>$ <%
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
							<td>$ <%
								out.print(limit_price);
							%>
							</td>
						</tr>


						<tr>
							<td>Term</td>
							<td>
								<%
									out.print(term);
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
							<td>$ <%
								out.print(brokerage_fee);
							%>
							</td>
						</tr>

						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>

						<tr>
							<td>Total price</td>
							<td>$ <%
								out.print(df.format(total_price));
							%>
							</td>
						</tr>
					</tbody>
				</table>
			

			<div style="padding-top: 1em">
				<button type="submit" id="submitbutton" class="btn btn-primary">Confirm
					Order</button>
			</div>
		</form>

		<div style="padding-top: 1em">
			<button type="cancel"
				onclick="javascript:window.location='BuyPage.jsp';"
				class="btn btn-danger">Cancel Order</button>
		</div>
		</div>
	</div>

</body>
</html>