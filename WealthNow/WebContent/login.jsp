<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- Java script and Bootstrap -->

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- --End of Bootstrap---------- -->

<link rel = "stylesheet" type = "text/css" href="css/style.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>

<body>

	<jsp:useBean id="userBean" class="com.fdm.wealthnow.beans.UserBean"
		scope="request" />
	<jsp:setProperty name="userBean" property="*" />

	<div class="page-header" align="center">
		<h1>Log In</h1>
	</div>

	<div class="container">
		<form class="form-horizontal" action="LoginController" method="post">

			<div class="form-group" align="center">
			<font color="red">
			<%
				if (request.getAttribute("errorMessage") != null) {
					out.println(request.getAttribute("errorMessage"));
				}
			%>
			</font>
			</div>

			<div class="form-group">
				<label for="inputUserName" class="col-sm-2 control-label">User
					name: </label>
				<div class="col-xs-3">
					<input type="text" name="username" class="form-control"
						value="${userBean.username}" placeholder="e.g.myAccount" required>
				</div>
			</div>

			<!--  
	User name:<input type="text" name="username" value="${userBean.username}"/><br/><br/>
	-->

			<div class="form-group">
				<label for="inputPassword" class="col-sm-2 control-label">Password:
				</label>
				<div class="col-xs-3">
					<input type="password" name="password" class="form-control"
						placeholder="Password" required>
				</div>
			</div>

			<!--
			Password:<input type="password" name="password" /><br /> <br /> 
			-->

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" value="Login" class="btn btn-default">Sign
						in</button>
					<br> <br>

					<p>
						<a href="register_user_info.jsp">Register as new User</a>
					</p>
				</div>
			</div>
			<!--  
			<input type="submit" value="Login" />
			-->

		</form>
	</div>
</body>
<footer>
<br>
<strong>All Copyrights go to FDM Group.</strong>
<br>
Contact: enquiries@fdmgroup.com</footer>
</html>