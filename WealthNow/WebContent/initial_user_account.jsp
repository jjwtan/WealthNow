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
input[type=number]::-webkit-inner-spin-button, input[type=number]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
	margin: 0;
}
</style>
</head>
<body>
	<div class="container">
		<div class="col-lg-offset-2 col-lg-8">
		
		
		 <div class="row bs-wizard" style="border-bottom:0;">
                
               <div class="col-xs-4 bs-wizard-step complete">
                 <div class="text-center bs-wizard-stepnum">Step 1</div>
                 <div class="progress"><div class="progress-bar"></div></div>
                 <a href="#" class="bs-wizard-dot"></a>
                 <div class="bs-wizard-info text-center">User Profile</div>
               </div>
               
               <div class="col-xs-4 bs-wizard-step complete"><!-- complete -->
                 <div class="text-center bs-wizard-stepnum">Step 2</div>
                 <div class="progress"><div class="progress-bar"></div></div>
                 <a href="#" class="bs-wizard-dot"></a>
                 <div class="bs-wizard-info text-center">Security Question</div>
               </div>
               
                <div class="col-xs-4 bs-wizard-step active"><!-- complete -->
                 <div class="text-center bs-wizard-stepnum">Step 3</div>
                 <div class="progress"><div class="progress-bar"></div></div>
                 <a href="#" class="bs-wizard-dot"></a>
                 <div class="bs-wizard-info text-center">Initial Balance</div>
               </div>
               
           </div>


			<form action="CompleteRegistrationController" method="post" class="form-horizontal">

				<div class="form-group">
					<label class="control-label col-sm-4" for="pwd">Deposit Amount $</label>
					<div class="col-sm-6">
						<input class="form-control" type="number"
						name="deposit_amount" min="1" max="9999999" step="0.01" 
						oninvalid="setCustomValidity('Please enter an amount more than $1')"
						onchange="try{setCustomValidity('')}catch(e){}"
						required autofocus/><br /> <br /> 
					</div>
				</div>
				<div class="form-group"> 
				    <div style="float:right; padding-top: 20px">
				      <button type="submit" class="btn btn-success">Finish</button>
				    </div>
				 </div>
				
				<!-- 
				<div id="depositing">
					Deposit Amount $<input class="form-control" type="number"
						name="deposit_amount" min="1" max="9999999" /><br /> <br /> <input
						type="submit" value="Finish" name="action" />
				</div>
				-->
			</form>

	</div>
</body>
</html>