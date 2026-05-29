<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login Page</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>

<body>
	<div class="container">
		<h1>Login</h1>
		<%
		String loginError = (String) request.getAttribute("error");
		if (loginError != null) {
		%>
		<p class="form-error"><%= loginError %></p>
		<%
		}
		%>
		<form action="<%= request.getContextPath() %>/login" method="post">
			<label for="username">User Name:</label>
			<input type="text" id="username" name="username" required>

			<label for="password">Password:</label>
			<input type="password" id="password" name="password" required>
					
			<button type="submit" class="button-style">Login</button>
		</form>
	</div>
</body>
</html>