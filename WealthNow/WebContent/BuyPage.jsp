<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.fdm.wealthnow.common.UserAuth,com.fdm.wealthnow.common.UserAccount,com.fdm.wealthnow.service.UserAccountService"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buy Page</title>

<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		$("#lsl").hide();
		$("#term").hide();

		$("#SL").on("click", function() {
			$("#lsl").show();
			$("#term").show();

		});
		
		
		$("#L").on("click", function() {
			$("#lsl").show();
			$("#term").show();

		});
		
		
		$("#M").on("click", function() {
			$("#lsl").hide();
			$("#term").hide().val("null");
			

		});
	});
</script>
</head>
<body>


	<form action="BuyPageController" method="post">
		<fieldset>
			<legend>Buy Stocks</legend>
			<p>
				Today's date:
				<%=(new java.util.Date()).toLocaleString()%>  </p>
				
			<% 
				UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
				UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
			%>
			<h4> Welcome <%= currentUser.getUser().getFirstName() + " " + currentUser.getUser().getLastName() %>
			,Your Current Balance $<%=ua.getBalance() %></h4>
			
			<h3 style="color:red;">${errorMessage}</h3>
			

			<input type="radio" name="Selection" value="B" required> Buy <input
				type="radio" name="Selection" value="S" required> Sell <br> <br>
			Quantity: <input type="number" name="quantity"
				value="quantity" required min="1" max="100000" step="1"> 
				Stock Symbol:<input type="text" name="stock_symbol" value="" pattern="[A-Za-z0-9]{3,4}"
				required placeholder="3-4 Characters"  style="text-transform: uppercase">
				
				 <br> <br>
				 Price Type:<br> <select required name="price_type" id="price_type">
				<option selected disabled hidden style='display: none' value=''></option>
				<option value="M" id="M">Market</option>
				<option value="SL" id="SL">Stop Loss</option>
				<option value="L" id="L">Limit</option>
			</select> 
			
			<input type="number" id="lsl" name="lsl" required placeholder="Limit/Stop Loss Price" min="0" value="0.00"> <br> 
				<br> <select required selected disabled hidden style='display: none' value="" >
				<option selected style='display: none' value="null"></option>
				<option value="GD">Good For The Day</option>
				<option value="GC">Good Till Cancelled</option>

			</select> <br> <br> 
			<input type="submit" value="Submit">

		</fieldset>
	</form>

</body>
</html>