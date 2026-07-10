<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Bakery — Login failed</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/style.css">
	<script src="<%= request.getContextPath() %>/assets/app.js"></script>
</head>
<body>
	<div class="center-page">
		<div class="auth-card">
			<h1>Login failed</h1>
			<div class="alert alert-error">Invalid username or password.</div>
			<form action="<%= request.getContextPath() %>/login" method="get">
				<button type="submit" class="btn btn-primary">Back to login</button>
			</form>
		</div>
	</div>
</body>
</html>
