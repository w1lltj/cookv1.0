<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="cook1.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cook Dashboard Login</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>

<body>
<div id="login-main">

	<div id="login-main-title">
		<h2>Cook Dashboard Login</h2>
		<hr></hr>
	</div>
		
	<div id="login-main-form">
		<form action="CookUserLoginServlet" method="post">
			<div id="login-main-form-userid">User ID :</div>
			<input type="text" name="login_cookUserId" /><br/>
			<div id="login-main-form-password">Password :</div>
			<input type="password" name="login_cookUserPw" /><br/><br/>
			<input type="submit" name="btnLoginSubmit" value="Login" />
			<input type="reset" name="btnLoginClear" value="Clear" />
		</form>
	</div>
	
	<div id="login-main-footer">
		<div id="error-message" style="color:red; font-style:italic;">
			<br/>
			<%
			String loginMsg = request.getParameter("loginMsg");
			if(loginMsg!=null){
				out.println(loginMsg);
			}
			%>
		</div>
	</div>

</div>


</body>
</html>