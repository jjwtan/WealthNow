<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logout page</title>
</head>
<body>
<h1> You have been successfully logged out</h1>
<%
  request.getSession(true).invalidate();
  System.out.println("Session exist:" +  request.getSession(false));
%> 
</body>
</html>