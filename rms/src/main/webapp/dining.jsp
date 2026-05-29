<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>RMS — Table orders</title>
</head>
<body>
	<h1>Table orders</h1>
	<form action="${pageContext.request.contextPath}/dining" method="post" accept-charset="UTF-8">
		<p><label>Table <input name="tableNumber" type="number" min="1" required></label></p>
		<p><label>Dish <input name="dishName" type="text" required></label></p>
		<p><label>Notes <input name="notes" type="text"></label></p>
		<p><label>Total <input name="totalPrice" type="number" step="0.01" min="0" required></label></p>
		<p><label>Date <input name="orderDate" type="date" required></label></p>
		<p><button type="submit">Add</button></p>
	</form>
	<hr>
	<c:forEach var="o" items="${diningList}">
		<p>Table ${o.tableNumber} — ${o.dishName} — ${o.notes} — ${o.totalPrice} — ${o.orderDate}</p>
	</c:forEach>
</body>
</html>
