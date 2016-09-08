<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.fdm.wealthnow.common.UserAuth,com.fdm.wealthnow.common.UserAccount,com.fdm.wealthnow.service.UserAccountService"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buy Page</title>
<jsp:include page="include/css_import.jsp" />
<jsp:include page="include/navbar.jsp" />
<script>
	$(document).ready(function() {

		$("#lsl").hide();
		$("#term").hide();

		$("#SL").on("click", function() {
			$("#lsl").show();
			$("#term").show();

		});
		
		
		$("#LT").on("click", function() {
			$("#lsl").show();
			$("#term").show();

		});
		
		
		$("#M").on("click", function() {
			$("#lsl").hide().val(0);
			$("#term").hide().val("null");
			

		});
	});
</script>
</head>
<body>
<div class="container">

	<form action="BuyPageController" method="post">
		<fieldset>
			<legend>Buy Stocks</legend>
			<p>
				Today's date:
				<%=(new java.util.Date()).toLocaleString()%>  </p>
				
			<% 
				UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
				UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
				
				session.setAttribute("Selection", null);
				session.setAttribute("quantity", null);
				session.setAttribute("stock_symbol", null);
				session.setAttribute("price_type", null);
				session.setAttribute("term", null);
				session.setAttribute("lsl", null);
				session.setAttribute("total_price", null);
			%>
			<h4> Welcome <%= currentUser.getUser().getFirstName() + " " + currentUser.getUser().getLastName() %>
			,Your Current Balance $<%=ua.getBalance() %></h4>
			
			<h3 style="color:red;">${errorMessage}</h3>
		
			

			<input type="radio" name="Selection" value="B" required> Buy  <br> <br>
			<div class="col-lg-6">
			Quantity: <input class="form-control" type="number" name="quantity"	value="0" required min="1" max="100000" step="1"> 
			</div>
			<div class="col-lg-6">
			Stock Symbol:<input class="form-control" type="text" name="stock_symbol" pattern="[A-Za-z0-9]{3,4}"
				required placeholder="3-4 Characters"  style="text-transform: uppercase">
			</div>
				 <br> <br>
			<div class="col-lg-6">
				 Price Type:<br> 
			<select class="form-control" required name="price_type" id="price_type" value="">
				<option selected disabled hidden style='display: none' value=''></option>
				<option value="M" id="M">Market</option>
				<option value="SL" id="SL">Stop Loss</option>
				<option value="LT" id="LT">Limit</option>
			</select> 
			</div>
			<div class="col-lg-6">
			<input class="form-control" type="number" id="lsl" name="lsl" required placeholder="Limit/Stop Loss Price" min="0" value="" step="0.01"> <br> 
				<br> 
			</div>
			<div class="col-lg-6">
			<select class="form-control" name="term" id="term" >
				<option value="null">Term</option>
				<option value="GD" >Good For The Day</option>
				<option value="GC" >Good Till Cancelled</option>

			</select> 
			</div>
			<br> <br> 
			<div class="col-lg-12">
			<input class="btn btn-primary" type="submit" value="Submit"> <!-- <button type="reset" value="Reset">Reset</button> -->
</div>
		</fieldset>
	</form>
</div>
</body>
</html>