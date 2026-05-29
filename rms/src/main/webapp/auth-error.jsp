<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	String msg = (String) request.getAttribute("authError");
	if (msg == null) {
		msg = (String) session.getAttribute("authError");
		if (msg != null) {
			session.removeAttribute("authError");
		}
	}
	if (msg == null) {
		msg = "Something went wrong.";
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>RMS — Error</title>
</head>
<body>
	<h1>Error</h1>
	<p><%= msg %></p>
	<p><a href="<%= request.getContextPath() %>/dashboard">Back to dashboard</a></p>
	<p><a href="<%= request.getContextPath() %>/login">Back to login</a></p>
</body>
</html>
