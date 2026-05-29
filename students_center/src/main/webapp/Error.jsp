<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
<body>
	<h3>Error: ${requestScope.error}</h3>
<a href="login.jsp">Try again</a>
</body>
</html>