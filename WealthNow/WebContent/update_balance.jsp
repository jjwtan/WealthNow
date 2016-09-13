<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <%@ page 
	import="com.fdm.wealthnow.common.UserAuth,
			com.fdm.wealthnow.common.UserAccount,
			com.fdm.wealthnow.service.UserAccountService" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Balance</title>
<jsp:include page="include/css_import.jsp" />
<jsp:include page="include/navbar.jsp" />
</head>
<body>
<div class="container">



<% 
	UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
	UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
%>
<h2>Your balance is $<%=ua.getBalance() %></h2>
<br>
	<form action="UpdateBalanceController" method="post">
		<input type="radio" name="Selection" id="deposit" checked="checked" onclick="withdrawDeposit()" value="Deposit">Deposit 
		<input type="radio" name="Selection" id="withdraw" onclick="withdrawDeposit()" value="Withdraw">	Withdraw <br />
		<br />
		<div id="depositing">
			Deposit Amount $<input type="number" name="deposit_amount" /><br /> <br />
			<input class="btn btn-primary" type="submit" value="Deposit" name ="action"/>
		</div>
		<div id="withdrawing" style="display: none">
			Withdraw Amount $<input type="number" name="withdraw_amount" styl/><br /> <br />
			<input class="btn btn-primary" type="submit" value="Withdraw" name = "action"/>
		</div>
	</form>


	<script>
		$(document).ready(function() {

		});

		function withdrawDeposit() {
			if (document.getElementById('withdraw').checked) {
				document.getElementById('withdrawing').style.display = 'inline';
				document.getElementById('depositing').style.display = 'none';
			} else if (document.getElementById('deposit').checked) {
				document.getElementById('depositing').style.display = 'inline';
				document.getElementById('withdrawing').style.display = 'none';
			}
		}
	</script>
	</div>
</body>
</html>