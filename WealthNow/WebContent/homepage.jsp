<%@page import="com.fdm.wealthnow.service.PortfolioService"%>
<%@page import="com.fdm.wealthnow.service.OrderManagementService"%>
<%@page import="java.util.List"%>
<%@page import="com.fdm.wealthnow.common.StockHolding"%>
<%@page import="com.fdm.wealthnow.common.Stock"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="com.fdm.wealthnow.common.UserAuth,
			com.fdm.wealthnow.common.UserAccount,
			com.fdm.wealthnow.service.UserAccountService"%>
<html>
<head>
<title>Homepage</title>
<jsp:include page="include/css_import.jsp" />
<jsp:include page="include/navbar.jsp" />

</head>
<body>
	<div class="container">
		<%
			UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
			UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
		%>
		<!-- 
<div style="float: right">
<font color="white" size="5px">
	<a href="logout.jsp">Logout</a>
	</font>
</div>
-->

<br>
		<div class="container-fluid padd">
			<div class="col-md-8">
				<h2>
					Welcome
					<%=currentUser.getUser().getFirstName() + " " + currentUser.getUser().getLastName()%></h2>
				
			</div>
			<div class="col-md-4" style="text-align: right">
				Your balance is $<%=ua.getBalance()%>
			</div>
		</div>
		<script src="http://widgets.macroaxis.com/widgets/url.jsp?t=58&s=z74.si, u11.si, d05.si, s85.si, c6l.si"></script>
		
		<br>
		<div class="container">
		<%
		PortfolioService ps = new PortfolioService();
		List <StockHolding> arraylist = ps.getPortfolioInStockHolding(ua.getUserId());
		StockHolding a = null;
		for(StockHolding stock : arraylist){
			a = stock;
		}
		
		if(a != null){
		%>
		
		
		<script src="http://widgets.macroaxis.com/widgets/url.jsp?t=45&s=<%=a.getStock_symbol()%>.si"></script>
		<%}else{ %>
		
		<a href="http://www.accuweather.com/en/sg/singapore/300597/weather-forecast/300597" class="aw-widget-legal">

</a><div id="awcc1473751843245" class="aw-widget-current"  data-locationkey="300597" data-unit="c" data-language="en-us" data-useip="false" data-uid="awcc1473751843245"></div><script type="text/javascript" src="http://oap.accuweather.com/launch.js"></script>
		
		<script src="http://widgets.macroaxis.com/widgets/url.jsp?t=42"></script>
		<iframe width="560" height="315" src="https://www.youtube.com/embed/JNBpu-Ll8Gk" frameborder="0" allowfullscreen></iframe>
		<iframe width="560" height="315" src="https://www.youtube.com/embed/LGRPzQfqKgE" frameborder="0" allowfullscreen></iframe>
		<%} %>
		</div>
		
		
		
		<!-- 
<div align="center">
<button type="button" class="btn btn-primary"><a href="view_watchlist.jsp"><font color="white">My Watchlists</font></a></button><br><br>
<button type="button" class="btn btn-primary"><a href="BuyPage.jsp"><font color="white">Buy/Sell Securities</font></a></button><br><br>
<button type="button" class="btn btn-primary"><a href="update_balance.jsp"><font color="white">Update Balance</font></a></button><br><br>
<button type="button" class="btn btn-primary"><a href="portfolio_viewer.jsp"><font color="white">Portfolio Viewer</font></a></button><br><br>
<button type="button" class="btn btn-primary"><a href="OrderHistory.jsp"><font color="white">Order History</font></a></button><br><br>
</div>
-->
	</div>
</body>
</html>