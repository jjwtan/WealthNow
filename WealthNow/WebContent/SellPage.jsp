<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="com.fdm.wealthnow.common.UserAuth,com.fdm.wealthnow.common.UserAccount,com.fdm.wealthnow.service.UserAccountService"%>
<%@page import="java.text.DecimalFormat"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>

</head>
<body>

	<% 
	
	
	 UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));
		UserAccount ua = new UserAccountService().getAccountBalance(currentUser.getUser().getUserId());
		

	DecimalFormat df = new DecimalFormat(".##"); 


%>


	<H1>Selling Page</H1>


	<form action="ConfirmationPageController" method="post"
		id="confirmation">
		<table width="559" border="1" cellpadding="5" cellspacing="0">
			<tr>
				<th width="407">Stock</th>
				<th width="126">Details</th>
			</tr>
			<tr>
				<td>Stock Symbol</td>
				<td>
					<%
						out.print("A");
					%>
				</td>
			</tr>
			<tr>
				<td>Stock Price</td>
				<td>$ <%
						out.print("B");
					%>
				</td>

			</tr>

			<tr>
				<td>Price Type</td>
				<td>
					<%
						out.print("C");
					%>
				</td>

			</tr>


			<tr>
				<td>Limit/Stop Loss</td>
				<td>$ <%
						out.print("D");
					%>
				</td>
			</tr>


			<tr>
				<td>Term</td>
				<td>
					<%
						out.print("E");
					%>
				</td>
			</tr>


			<tr>
				<td>Quantity</td>
				<td>
					<%
						out.print("F");
					%>
				</td>
			</tr>

			<tr>
				<td>Fixed Price</td>
				<td>$ <%
						out.print("G");
					%>
				</td>
			</tr>

			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>

			<tr>
				<td>Total Selling price</td>
				<td width="126">$ <%
						out.print(df.format(1.23456));
					%>
				</td>
			</tr>
		</table>
		<br> <input type="submit" value="Comfirm Order" id="submitbutton">
	</form>


</body>
</html>