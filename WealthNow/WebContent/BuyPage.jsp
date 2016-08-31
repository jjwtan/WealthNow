<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ page 
	import="com.fdm.wealthnow.common.UserAuth" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buy Page</title>
</head>
<body>


<form action="BuyPageController" >
<fieldset>
    <legend>Buy Stocks</legend>

<input type="radio" name="buy" value="buy"> Buy
  <input type="radio" name="sell" value="sell"> Sell <br><br>
  
  Quantity: <input type="number" name="quantity" value="quantity">
  Stock Symbol:<input type="text" name="stock_symbol" value="">
  <br><br>
  Price Type:<br><select>
  <option value="M">Market</option>
  <option value="SL">Stop Loss</option>
  <option value="L">Limit</option>
</select>
<br>
Term:<br><select>
  <option value="GD">Good For The Day</option>
  <option value="GC">Good Till Cancelled</option>
 
</select>
  <br><br>
  
    <input type="submit" value="Submit">
  
</fieldset>
</form>

</body>
</html>