<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>RMS — Staff</title>
</head>
<body>
	<h1>Staff</h1>
	<form action="${pageContext.request.contextPath}/staff" method="post" accept-charset="UTF-8">
		<p><label>Name <input name="name" type="text" required></label></p>
		<p><label>Email <input name="email" type="email" required></label></p>
		<p><label>Age <input name="age" type="number" min="1" required></label></p>
		<p><label>Phone <input name="phone" type="text" required></label></p>
		<p><label>Address <input name="address" type="text" required></label></p>
		<p><button type="submit">Add</button></p>
	</form>
	<hr>
	<c:forEach var="s" items="${staffList}">
		<p>${s.name} — ${s.email} — ${s.age} — ${s.phone} — ${s.address}</p>
	</c:forEach>
</body>
</html>
