<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ page 
	import="com.fdm.wealthnow.common.UserAuth" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage</title>
</head>
<body>
<% UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));%>
<h1> Welcome <%= currentUser.getUser().getFirstName() + " " + currentUser.getUser().getLastName() %></h1>

</body>
</html>