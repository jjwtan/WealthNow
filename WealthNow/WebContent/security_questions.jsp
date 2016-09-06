<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page
	import="java.util.List,
			com.fdm.wealthnow.service.UserRegisterService"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Security Questions</title>
</head>
<body>
<h3>2: Security Questions</h3>
	Security Qustion 1
	<select>
		<%
		UserRegisterService urs = new UserRegisterService();
		List<String> questions = urs.getAllSecurityQuestions();
		for(String question:questions) {
		%>
			<option><%=question %></option>
		<%
		}
		%>
	</select>
</body>
</html>