<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Bakery — Dashboard</title>
</head>
<body>
	<h1>${applicationScope.bakeryName}</h1>
	<p>Signed in as <strong>${sessionScope.user}</strong>.</p>
	<ul>
		<li><a href="<%= request.getContextPath() %>/staff">Staff</a></li>
		<li><a href="<%= request.getContextPath() %>/product">Menu items</a></li>
		<li><a href="<%= request.getContextPath() %>/order">Orders</a></li>
		<li><a href="<%= request.getContextPath() %>/logout">Logout</a></li>
	</ul>
</body>
</html>
