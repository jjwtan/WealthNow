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
<jsp:include page="include/css_import.jsp" />
<jsp:include page="include/navbar.jsp" />
</head>
<body>
<div class="container">
<h3>2: Security Question</h3>
<form action="SecurityQuestionController" method="POST">
<table>
		<tr>
			<td>Security Qustion</td>
			<td>
			<select name="question" >
					<%
						UserRegisterService urs = new UserRegisterService();
						List<String> questions = urs.getAllSecurityQuestions();
						int counter = 1;
						for (String question : questions) {
					%>
					<option value="<%=counter %>"><%=question%></option>
					<%
						counter++;
						}
					%>
			</select>
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="text" style="width: 99%" name="answer" required/></td>
		</tr>

	</table>
	<input type="submit" value="Next"/>
	</form>
	</div>
</body>
</html>