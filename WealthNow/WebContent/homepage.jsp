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
		<div class="container-fluid padd">
			<div class="col-md-8">
				<h2>
					Welcome
					<%=currentUser.getUser().getFirstName() + " " + currentUser.getUser().getLastName()%>,</h2>
				
			</div>
			<div class="col-md-4" style="text-align: right">
				Your balance is $<%=ua.getBalance()%>
			</div>
		</div>
		<div class="col-md-9">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">
							<a data-toggle="collapse" href="#collapse1">Watchlist</a>
						</div>
					</div>
					<div id="collapse1" class="panel-collapse collapse in">
						<div class="panel-body">---</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">
							<a data-toggle="collapse" href="#collapse3">Order History</a>
						</div>
					</div>
					<div id="collapse3" class="panel-collapse collapse in">
						<div class="panel-body">---</div>
					</div>
				</div>
			</div>
		</div>
	
		<div class="col-md-12">
			<div class="panel-group">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">
						<a data-toggle="collapse" href="#collapse2">Portfolio</a>
					</div>
				</div>
				<div id="collapse2" class="panel-collapse collapse in">
					<div class="panel-body">---</div>
				</div>
			</div>
		</div>
		</div>
		<br>
		
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