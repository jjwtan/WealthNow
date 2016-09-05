<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="com.fdm.wealthnow.common.UserAuth,com.fdm.wealthnow.common.UserAccount,com.fdm.wealthnow.service.UserAccountService,
	com.fdm.wealthnow.service.StockService"%>
<html>
<head>

<style>
table {
	width: 100%;
}

table, th, td {
	border: 1px solid black;
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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portfolio Viewer</title>


<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>
<script>
	


	
</script>
</head>


<body>




	<table id="t01">
		 
		<tr>
			   
			<th colspan="2">Stock Symbol</th>    
			<th>Last Trade</th>    
			<th colspan="2">Change</th>
			<th>Day's Gain</th>
			<th>Qty</th>
			<th>Price Paid</th>
			<th colspan="2">Total Gain</th>
			<th>Market Value</th>  
		</tr>
		<tr>
			<td>symbol</td>
			<td>B/S Link</td>
			<td>Last Trade(Price)</td>
			<td>%</td>
			<td>$</td>
			<td>Gain</td>
			<td>qty</td>
			<td>price paid</td>
			<td>%</td>
			<td>$</td>
			<td>Market Value</td>
		</tr>
		
		<tr>
			<td>symbol</td>
			<td>B/S Link</td>
			<td>Last Trade(Price)</td>
			<td>%</td>
			<td>$</td>
			<td>Gain</td>
			<td>qty</td>
			<td>price paid</td>
			<td>%</td>
			<td>$</td>
			<td>Market Value</td>
		</tr>

	</table>


</body>
</html>