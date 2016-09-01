<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page 
	import="com.fdm.wealthnow.common.UserAuth,com.fdm.wealthnow.common.UserAccount,com.fdm.wealthnow.service.UserAccountService" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation Page</title>

<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>
<script>
	
</script>





</head>
<body>
<H1>Confirmation Page</H1>


<% 
	UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
%>

<p>Stock Confirmation.</p>
<form action="ComfirmationPageController" method="post" id="comfirmation">
<table width="559" border="1" cellpadding="5" cellspacing="0">
  <tr>
    <th width="407">Stock</th>
    <th width="126">Details</th>
  </tr>
  <tr>
    <td>Stock Symbol</td>
    <td><%= request.getParameter("stock_symbol")%></td>
  </tr>
  <tr>
    <td>Stock Price</td>
    <td><%  %></td>  <!--  // get from stock service-->
  </tr>
  <tr>
    <td>Quantity</td>
    <td><%= request.getParameter("quantity")%></td>
  </tr>
  
  <tr>
    <td>Fixed Price</td>
    <td>$9.95</td>
  </tr>
  
  <tr>
    <td colspan="4">&nbsp;</td>
  </tr>
  
  <tr>
    <td >Total price</td>
    <td width="126"></td>
  </tr>
</table><br>
<input type="submit" value="Comfirm Order"> 
</form>



</body>
</html>