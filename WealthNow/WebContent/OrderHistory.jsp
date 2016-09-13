<%@page import="java.io.PrintWriter"%>
<%@page import="com.fdm.wealthnow.common.InfoType"%>
<%@page import="com.fdm.wealthnow.dao.OrderDAO"%>
<%@page import="com.fdm.wealthnow.service.OrderManagementService"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.fdm.wealthnow.service.PortfolioService"%>
<%@ page
	import="com.fdm.wealthnow.common.UserAuth,com.fdm.wealthnow.common.UserAccount,com.fdm.wealthnow.service.UserAccountService,
	com.fdm.wealthnow.service.StockService,com.fdm.wealthnow.common.StockHolding,com.fdm.wealthnow.common.Stock, java.util.List, com.fdm.wealthnow.common.Order"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--<style>
table {
	width: 100%;
}

table, th, td {
	border: 1px solid grey;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
	text-align: left;
}

table#t01 tr:nth-child(even) {
	background-color: #eee;
}

table#t01 tr:nth-child(odd) {
	background-color: #fff;
}

table#t01 th {
	background-color: black;
	color: white;
}
</style>
-->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order History</title>
<jsp:include page="include/css_import.jsp" />
<jsp:include page="include/navbar.jsp" />
</head>
<body>
	<div class="container">
		<%
			UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
			OrderManagementService oms = new OrderManagementService();
			PortfolioService ps = new PortfolioService();
			StockService ss = new StockService();
			OrderDAO ord = new OrderDAO();
			Integer user_id = currentUser.getUser().getUserId();
		%>
		<br>
		<div class="panel-group">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">
						<a data-toggle="collapse" href="#collapse1"><h2>Open
								Orders</h2></a>
					</div>
				</div>
				<div id="collapse1" class="panel-collapse collapse in">
					<div class="panel-body">
						<form action="CancelOrderController" method="POST">
							<table class="table table-striped">
								<tr>
									<th>Cancel order?</th>
									<th>Date Placed Order</th>
									<th>Order No</th>
									<th>Type</th>
									<th>Quantity</th>
									<th>Symbol</th>
									<th>Price Type</th>
									<th>Term</th>
									<th>Limit Price</th>
									<th>Market Price</th>
								</tr>
								<%
									List<Order> orderOpen = oms.getOpenOrdersFromUser(user_id);
									DecimalFormat df2 = new DecimalFormat("##.###");
									for (Order order3 : orderOpen) {
										Stock s = ss.getStockFromExchange(order3.getStock_symbol(), InfoType.BASIC);
								%>
								<tr>
									<td>
										<%
					if(order3.getTerm()!=null){					
					if (order3.getTerm().toString().equals("GC") || order3.getTerm().toString().equals("GD")) {
						%><button class="btn btn-warning" type="submit"
											value="<%=order3.getOrder_id()%>" name="cancel" id="cancel"
											style="float: left;">Cancel</button> <%
					}
					
					}
				%>
									</td>
									<td><%=order3.getPlace_order_date()%></td>
									<td><b># <%=order3.getOrder_id()%></b></td>
									<td><%=order3.getOrder_type().toString()%></td>
									<td><%=order3.getQuantity()%></td>
									<td><%=order3.getStock_symbol()%></td>
									<td><%=order3.getPrice_type()%></td>
									<td><%=order3.getTerm()%></td>
									<td><b>$ <%=order3.getLimit_price()%></b></td>
									<td><b>$ <%=df2.format(s.getMktPrice())%></b></td>
								</tr>
								<%
									}
								%>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>

		<br>
		<div class="panel-group">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">
						<a data-toggle="collapse" href="#collapse2"><h2>Completed
								Orders</h2></a>
					</div>
				</div>
				<div id="collapse2" class="panel-collapse collapse">
					<div class="panel-body">
						<table class="table table-striped">
							<tr>
								<th>Date Placed Order</th>
								<th>Order No</th>
								<th>Type</th>
								<th>Quantity</th>
								<th>Symbol</th>
								<th>Price Type</th>
								<th>Price</th>
								<th>Total Price</th>
								<th>Status</th>
							</tr>
							<%
								List<Order> orderList = oms.getCompletedOrdersFromUser(user_id);

								for (Order order : orderList) {
							%>
							<tr>

								<td><%=order.getPlace_order_date()%></td>
								<td><b># <%=order.getOrder_id()%></b></td>
								<td><%=order.getOrder_type().toString()%></td>
								<td><%=order.getQuantity()%></td>
								<td><%=order.getStock_symbol()%></td>
								<td><%=order.getPrice_type()%></td>
								<td>$ <%=order.getClosing_price()%></td>
								<td>$ <%=order.getTotal_price_deducted()%></td>
								<td><b><%=order.getStatus()%></b></td>
							</tr>
							<%
								}
							%>

						</table>
					</div>
				</div>
			</div>
		</div>

		<br>
		<div class="panel-group">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">
						<a data-toggle="collapse" href="#collapse3"><h2>Cancelled
								Orders</h2></a>
					</div>
				</div>
				<div id="collapse3" class="panel-collapse collapse">
					<div class="panel-body">
						<table class="table table-striped">
							<tr>
								<th>Date Placed Order</th>
								<th>Order No</th>
								<th>Type</th>
								<th>Quantity</th>
								<th>Symbol</th>
								<th>Price Type</th>
								<th>Price</th>
								<th>Status</th>
							</tr>
							<%
								List<Order> orderList1 = oms.getCancelledOrdersFromUser(user_id);
							
								for (Order order1 : orderList1) {
							%>
							<tr>

								<td><%=order1.getPlace_order_date()%></td>
								<td><b># <%=order1.getOrder_id()%></b></td>
								<td><%=order1.getOrder_type().toString()%></td>
								<td><%=order1.getQuantity()%></td>
								<td><%=order1.getStock_symbol()%></td>
								<td><%=order1.getPrice_type()%></td>
								<td>$ <%=order1.getClosing_price()%></td>
								<td><b><%=order1.getStatus()%></b></td>
							</tr>
							<%
								}
							%>
						</table>
					</div>
				</div>
			</div>
		</div>
		<br>

	</div>
</body>
</html>