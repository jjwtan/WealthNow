<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:useBean id="registerBean" class="com.fdm.wealthnow.beans.RegisterBean" scope="request"/>  
<jsp:setProperty name="registerBean" property="*"/>
<style type="text/css">
input[type=number]::-webkit-inner-spin-button, 
input[type=number]::-webkit-outer-spin-button { 
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
<h3>1: User Profile</h3>
<form action="RegisterUserController" method="post">  
	<table style="width: 40%">
		<tr>
			<td>User name</td>
			<td><input class="form-control" type="text" name="username" 
			value="${registerBean.username}" required/>
			<% if(request.getAttribute("errorMessage") != null) {%>
				<div style="font-style: italic; color: red"><%=request.getAttribute("errorMessage") %></div>
			<%} %></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input class="form-control" type="password" name="password" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" 
				 oninvalid="setCustomValidity('Please enter at least one uppercase, one lowercase and one number')"
				 onchange="try{setCustomValidity('')}catch(e){}"
				 value="${registerBean.password}" required/></td>
		</tr>
		<tr>
			<td>First name</td>
			<td><input class="form-control" type="text" name="firstName" value="${registerBean.firstName}" required/></td>
		</tr>
		<tr>
			<td>Last name</td>
			<td><input class="form-control" type="text" name="lastName" value="${registerBean.lastName}"/></td>
		</tr>
		<tr>
			<td>Birthday</td>
			<td><input class="form-control" type="text" name="birthday" placeholder="dd/mm/yyyy" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}" 
				value="${registerBean.birthday}" required/></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><input class="form-control" type="email" name="email" value="${registerBean.email}" required/></td>
		</tr>
		<tr>
			<td>Address</td>
			<td><input class="form-control" type="text" name="address" value="${registerBean.address}" required/></td>
		</tr>
		<tr>
			<td>Phone Number</td>
			<td><input class="form-control" type="number" name="phoneNumber" value="${registerBean.phoneNumber}" min=1 max=999999999 required/></td>
		</tr>
		<tr>
			<td>Maiden Name</td>
			<td><input class="form-control" type="text" name="maidenName" value="${registerBean.maidenName}" required/></td>
		</tr>
	</table>
	<input type="submit" value="Next"/>  
</form>  
</div>
</body>
</html>