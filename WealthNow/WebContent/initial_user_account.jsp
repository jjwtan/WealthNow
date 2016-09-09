<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Balance</title>
<jsp:include page="include/css_import.jsp" />
<jsp:include page="include/blanknav.jsp" />
<style type="text/css">
input[type=number]::-webkit-inner-spin-button, 
input[type=number]::-webkit-outer-spin-button { 
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    margin: 0; 
}

</style>
</head>
<body>
<div class="container">
	<h3>3: Account balance</h3>
	
	<div class="col-lg-6">
	<form action="CompleteRegistrationController" method="post">
				<div id="depositing">
			Deposit Amount $<input class="form-control" type="number" name="deposit_amount" min="1" max="9999999"/><br /> <br />
			<input type="submit" value="Finish" name ="action"/>
		</div>
	</form>
	</div>
</div>
</body>
</html>