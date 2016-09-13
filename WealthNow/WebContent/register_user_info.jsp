<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:useBean id="registerBean"
	class="com.fdm.wealthnow.beans.RegisterBean" scope="request" />
<jsp:setProperty name="registerBean" property="*" />
<style type="text/css">
input[type=number]::-webkit-inner-spin-button, input[type=number]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
	margin: 0;
}
</style>
<title>Registration</title>
<jsp:include page="include/css_import.jsp" />
<jsp:include page="include/blanknav.jsp" />
</head>
<body>
	<div class="container">
		<div class="col-lg-offset-2 col-lg-8">
		
		
		 <div class="row bs-wizard" style="border-bottom:0;">
                
               <div class="col-xs-4 bs-wizard-step active">
                 <div class="text-center bs-wizard-stepnum">Step 1</div>
                 <div class="progress"><div class="progress-bar"></div></div>
                 <a href="#" class="bs-wizard-dot"></a>
                 <div class="bs-wizard-info text-center">User Profile</div>
               </div>
               
               <div class="col-xs-4 bs-wizard-step disabled"><!-- complete -->
                 <div class="text-center bs-wizard-stepnum">Step 2</div>
                 <div class="progress"><div class="progress-bar"></div></div>
                 <a href="#" class="bs-wizard-dot"></a>
                 <div class="bs-wizard-info text-center">Security Question</div>
               </div>
               
                <div class="col-xs-4 bs-wizard-step disabled"><!-- complete -->
                 <div class="text-center bs-wizard-stepnum">Step 3</div>
                 <div class="progress"><div class="progress-bar"></div></div>
                 <a href="#" class="bs-wizard-dot"></a>
                 <div class="bs-wizard-info text-center">Initial Balance</div>
               </div>
               
           </div>
		
		<form action="RegisterUserController" method="post"
			class="form-horizontal">
<!--  		<h3>1: User Profile</h3> -->
			<div class="form-group">
				<label class="control-label col-sm-4" for="email">Username:</label>
				<div class="col-sm-6">
					<input class="form-control" type="text" name="username"
						value="${registerBean.username}" required /> 
						<%if (request.getAttribute("errorMessage") != null) {%>
						<div style="font-style: italic; color: red">
						<%=request.getAttribute("errorMessage")%></div>
						<%}%>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="email">Password:</label>
				<div class="col-sm-6">
					<input class="form-control" type="password"
						name="password"
						pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$"
						oninvalid="setCustomValidity('Please enter at least one uppercase, one lowercase and one number')"
						onchange="try{setCustomValidity('')}catch(e){}"
						value="${registerBean.password}" required />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="email">First name:</label>
				<div class="col-sm-6">
					<input class="form-control" type="text" name="firstName"
						value="${registerBean.firstName}" required />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="email">Last name:</label>
				<div class="col-sm-6">
					<input class="form-control" type="text" name="lastName"
						value="${registerBean.lastName}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="email">Birthday:</label>
				<div class="col-sm-6">
					<input class="form-control" type="text" name="birthday"
						placeholder="dd/mm/yyyy"
						pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}"
						value="${registerBean.birthday}" required />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="email">Email:</label>
				<div class="col-sm-6">
					<input class="form-control" type="email" name="email"
						value="${registerBean.email}" required />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="email">Address:</label>
				<div class="col-sm-6">
					<input class="form-control" type="text" name="address"
						value="${registerBean.address}" required />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="email">Phone Number:</label>
				<div class="col-sm-6">
				<input class="form-control" type="number"
						name="phoneNumber" value="<%=(registerBean.getPhoneNumber() == 0) ? null : registerBean.getPhoneNumber()%>" min=1
						max=999999999 required />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="email">Maiden Name:</label>
				<div class="col-sm-6">
					<input class="form-control" type="text" name="maidenName"
						value="${registerBean.maidenName}" required />
				</div>
			</div>

			<div class="form-group">
				<div style="float: right; padding-top: 15px">
							<input class="btn btn-primary" type="submit" value="Next" />
				</div>
			</div>

				<!-- 
			<table style="width: 40%">
				<tr>
					<td>User name</td>
					<td><input class="form-control" type="text" name="username"
						value="${registerBean.username}" required /> <%if (request.getAttribute("errorMessage") != null) {%>
						<div style="font-style: italic; color: red"><%=request.getAttribute("errorMessage")%></div>
						<%}%></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input class="form-control" type="password"
						name="password"
						pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$"
						oninvalid="setCustomValidity('Please enter at least one uppercase, one lowercase and one number')"
						onchange="try{setCustomValidity('')}catch(e){}"
						value="${registerBean.password}" required /></td>
				</tr>
				<tr>
					<td>First name</td>
					<td><input class="form-control" type="text" name="firstName"
						value="${registerBean.firstName}" required /></td>
				</tr>
				<tr>
					<td>Last name</td>
					<td><input class="form-control" type="text" name="lastName"
						value="${registerBean.lastName}" /></td>
				</tr>
				<tr>
					<td>Birthday</td>
					<td><input class="form-control" type="text" name="birthday"
						placeholder="dd/mm/yyyy"
						pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}"
						value="${registerBean.birthday}" required /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input class="form-control" type="email" name="email"
						value="${registerBean.email}" required /></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><input class="form-control" type="text" name="address"
						value="${registerBean.address}" required /></td>
				</tr>
				<tr>
					<td>Phone Number</td>
					<td><input class="form-control" type="number"
						name="phoneNumber" value="<%=(registerBean.getPhoneNumber() == 0) ? null : registerBean.getPhoneNumber()%>" min=1
						max=999999999 required /></td>
				</tr>
				<tr>
					<td>Maiden Name</td>
					<td><input class="form-control" type="text" name="maidenName"
						value="${registerBean.maidenName}" required /></td>
				</tr>
				<tr>
					<td colspan="2">
						<div style="float: right">
							<input class="btn btn-primary" type="submit" value="Next" />
						</div>
					</td>
				</tr>
			</table>
-->
		</form>
		</div>
	</div>
</body>
</html>