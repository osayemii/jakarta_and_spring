<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>User Login</title>
	<link rel="stylesheet" type="text/css" href="<%= ctx %>/css/bank.css">
</head>
<body class="login-body">
	<div class="login-card">
		<h1><span class="icon" aria-hidden="true">🔐</span> User Login</h1>
		<%
		String err = (String) request.getAttribute("error");
		if (err != null) {
		%>
		<p class="msg msg-error"><span class="icon-inline" aria-hidden="true">⚠️</span> <%= err %></p>
		<% } %>
		<form action="<%= ctx %>/UserServlet" method="post">
			<label for="username"><span class="icon-inline" aria-hidden="true">👤</span> Username</label>
			<input type="text" id="username" name="username" required autocomplete="username">

			<label for="password"><span class="icon-inline" aria-hidden="true">🔑</span> Password</label>
			<input type="password" id="password" name="password" required autocomplete="current-password">

			<button type="submit" class="btn btn-wide"><span class="icon-inline" aria-hidden="true">➡️</span> Submit</button>
		</form>
		<p class="hint">Demo: <code>bankadmin</code> / <code>bank123</code></p>
	</div>
</body>
</html>
