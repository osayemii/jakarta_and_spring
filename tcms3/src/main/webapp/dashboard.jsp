<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Dashboard</h2>
	<p>Welcome ${sessionScope.user}</p>
	
	<a href="students">Manage Students</a><br>
	<a href="teachers">Manage Teachers</a><br>
	<a href="courses">Manage Course</a>
	<a href="logout">Logout</a>
</body>
</html>