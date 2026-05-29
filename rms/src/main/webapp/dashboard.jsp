<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>RMS — Dashboard</title>
</head>
<body>
	<h1>Dashboard</h1>
	<p><%= session.getAttribute("user") %></p>
	<ul>
		<li><a href="<%= request.getContextPath() %>/staff">Staff</a></li>
		<li><a href="<%= request.getContextPath() %>/menu">Menu</a></li>
		<li><a href="<%= request.getContextPath() %>/dining">Table orders</a></li>
		<li><a href="<%= request.getContextPath() %>/logout">Logout</a></li>
	</ul>
</body>
</html>
