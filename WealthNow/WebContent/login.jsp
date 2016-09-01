<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<%
	if(request.getAttribute("errorMessage") != null) {
		out.println(request.getAttribute("errorMessage"));
	}
%>
<jsp:useBean id="userBean" class="com.fdm.wealthnow.beans.UserBean" scope="request"/>  
<jsp:setProperty name="userBean" property="*"/>
<h3>Login Form</h3>  
<form action="LoginController" method="post">  
	User name:<input type="text" name="username" value="${userBean.username}"/><br/><br/>  
	Password:<input type="password" name="password"/><br/><br/>  
	<input type="submit" value="Login"/>  
</form>  

<a href="register_user_info.jsp">New User</a>

</body>
</html>