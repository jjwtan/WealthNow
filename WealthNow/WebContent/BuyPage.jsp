<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.fdm.wealthnow.common.UserAuth"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buy Page</title>
</head>
<body>


	<form
		action="/WealthNow/com/fdm/wealthnow/controller/BuyPageController"
		method="post">
		<fieldset>
			<legend>Buy Stocks</legend>

			<input type="radio" name="A" value="buy" required> Buy <input
				type="radio" name="A" value="sell" required> Sell <br> <br>
			Quantity: <input type="number" name="quantity" value="quantity"
				required> Stock Symbol:<input type="text"
				name="stock_symbol" value="" pattern="[A-Za-z]{3}" required placeholder="3 Characters">
			<br> <br> Price Type:<br> <select required>
				<option selected disabled hidden style='display: none' value=''></option>
				<option value="M">Market</option>
				<option value="SL">Stop Loss</option>
				<option value="L">Limit</option>
			</select> <br> Term:<br> <select required>
				<option selected disabled hidden style='display: none' value=''></option>
				<option value="GD">Good For The Day</option>
				<option value="GC">Good Till Cancelled</option>

			</select> <br> <br> <input type="submit" value="Submit">

		</fieldset>
	</form>

</body>
</html>