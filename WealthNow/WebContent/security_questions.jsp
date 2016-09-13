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
<jsp:include page="include/blanknav.jsp" />
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
               
               <div class="col-xs-4 bs-wizard-step active"><!-- complete -->
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
	
		<form action="SecurityQuestionController" method="POST" class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-3" for="email">Security Question:</label>
					<div class="col-sm-7">
						<select name="question" class="form-control">
							<%
								UserRegisterService urs = new UserRegisterService();
								List<String> questions = urs.getAllSecurityQuestions();
								int counter = 1;
								for (String question : questions) {
							%>
							<option value="<%=counter%>"><%=question%></option>
							<%
								counter++;
								}
							%>
					</select>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-3" for="email"></label>
					<div class="col-sm-7">
						<input class="form-control" type="text" style="width: 99%" name="answer"
						required />
					</div>
				</div>
				
				<div class="form-group"> 
				    <div style="float:right; padding-top: 20px">
				      <button type="submit" class="btn btn-primary">Next</button>
				    </div>
				 </div>

				<!-- 
			<table>
				<tr>
					<td>Security Qustion</td>
					<td><select name="question">
				
					</select></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="text" style="width: 99%" name="answer"
						required /></td>
				</tr>

			</table>
	
					<input type="submit" value="Next" />
						-->
			</form>
		</div>
	</div>
</body>
</html>