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

<style>
form {
	border: 0px;
	margin-top: 100px;
	margin-bottom: 100px;
	margin-right: 150px;
	margin-left: 0px;
}

div.page-header {
	margin-top: 50px;
	margin-left: 80px;
}

a:hover {
	text-decoration: underline;
}

body {
	background-image:
		url("http://science-all.com/images/wallpapers/background-images-for-websites/background-images-for-websites-24.jpg");
	background-size: cover;
	background-repeat: no-repeat;
}

div.container {
	position: relative;
	background-color: white;
	opacity: 0.9;
}

footer {
	text-align: center;
}

div.page-header {
	font-family: "Palatino Linotype", "Book Antiqua", Palatino, serif;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>

<body>
	<%
		if (request.getAttribute("errorMessage") != null) {
			out.println(request.getAttribute("errorMessage"));
		}
	%>
	<jsp:useBean id="userBean" class="com.fdm.wealthnow.beans.UserBean"
		scope="request" />
	<jsp:setProperty name="userBean" property="*" />

	<div class="page-header" align="center">
		<h1>Log In</h1>
	</div>

	<div class="container">
		<form class="form-horizontal" action="LoginController" method="post">

			<div class="form-group">
				<label for="inputUserName" class="col-sm-2 control-label">User
					name: </label>
				<div class="col-sm-10">
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
				<div class="col-sm-10">
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
					<br>
					<br>
					<p>
						<a href="#">Forgot UserID or Password?</a>
					</p>
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
<footer><br><strong>All Copyrights go to FDM Group.</strong><br>Contact: enquiries@fdmgroup.com</footer>
</html>