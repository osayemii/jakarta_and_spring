<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>RMS — Menu</title>
</head>
<body>
	<h1>Menu</h1>
	<form action="${pageContext.request.contextPath}/menu" method="post" accept-charset="UTF-8">
		<p><label>Dish name <input name="name" type="text" required></label></p>
		<p><label>Price <input name="price" type="number" step="0.01" min="0" required></label></p>
		<p><label>Category <input name="category" type="text" required placeholder="e.g. Main"></label></p>
		<p><button type="submit">Add</button></p>
	</form>
	<hr>
	<c:forEach var="m" items="${menuList}">
		<p>${m.name} — ${m.price} — ${m.category}</p>
	</c:forEach>
</body>
</html>
