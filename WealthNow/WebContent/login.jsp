<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include/css_import.jsp" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<nav class="navbar navbar-inverse">
<div class="container-fluid">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#myNavbar">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#">WealthNow</a>
	</div>
	<div class="collapse navbar-collapse" id="myNavbar">
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-user"></span>
					Sign Up</a></li>
			<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
					Login</a></li>
		</ul>
	</div>
</div>
</nav>
</head>

<body>
	<div class="container loginbox">
		<jsp:useBean id="userBean" class="com.fdm.wealthnow.beans.UserBean"
			scope="request" />
		<jsp:setProperty name="userBean" property="*" />
		<!-- 
	<div class="page-header" align="center">
		<h1>Log In</h1>
	</div>
-->
		<div
			class="col-lg-offset-4 col-lg-4 col-md-offset-4 col-md-5 col-sm-offset-4 col-sm-5 panel panel-default">
			<form class="form-horizontal" action="LoginController" method="post">

				<div class="form-group" align="center">
					<font color="red"> <%
 	if (request.getAttribute("errorMessage") != null) {
 		out.println(request.getAttribute("errorMessage"));
 	}
 %>
					</font>
				</div>
				<div class="container-fluid">
					<div class="form-group">
						<!-- <label for="inputUserName" class="control-label">User
							name: </label>-->
						<div>
							<input type="text" name="username" class="form-control"
								value="${userBean.username}" placeholder="Username" required>
						</div>
					</div>


					<div class="form-group">
						<!-- <label for="inputPassword" class="control-label">Password:
						</label>-->
						<div class="">
							<input type="password" name="password" class="form-control"
								placeholder="Password" required>
						</div>
					</div>


					<div class="form-group">
						<div class="">
							<button type="submit" value="Login" class="btn btn-primary btn-block">Sign
								in</button>
							<br>
							<p>
								<a href="register_user_info.jsp">Register as new User</a>
							</p>
						</div>
					</div>

				</div>

			</form>
		</div>
	</div>
</body>
<div
	class="col-lg-offset-4 col-lg-4 col-md-offset-4 col-md-5 col-sm-offset-4 col-sm-5">
	<footer> <br>
	<strong>All Copyrights go to FDM Group.</strong> <br>
	Contact: enquiries@fdmgroup.com</footer>
</div>
</html>