<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Bakery — Login failed</title>
</head>
<body>
	<p>Invalid username or password.</p>
	<form action="<%= request.getContextPath() %>/login" method="get">
		<button type="submit">Back to login</button>
	</form>
</body>
</html>
