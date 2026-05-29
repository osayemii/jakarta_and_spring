<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.List,com.bakery.model.Order"%>
<%
	@SuppressWarnings("unchecked")
	List<Order> orderList = (List<Order>) request.getAttribute("orderList");
	if (orderList == null) {
		response.sendRedirect(request.getContextPath() + "/order");
		return;
	}
	String flashOk = (String) session.getAttribute("flashOk_order");
	if (flashOk != null) {
		session.removeAttribute("flashOk_order");
	}
	String flashError = (String) session.getAttribute("flashError_order");
	if (flashError != null) {
		session.removeAttribute("flashError_order");
	}
	Object listError = request.getAttribute("listError");
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Bakery — Orders</title>
</head>
<body>
	<p><a href="<%= request.getContextPath() %>/dashboard">← Dashboard</a></p>
	<h1>Customer orders</h1>
	<% if (flashOk != null) { %>
	<p style="color:#060;"><%= flashOk %></p>
	<% } %>
	<% if (flashError != null) { %>
	<p style="color:#b00;"><%= flashError %></p>
	<% } %>
	<% if (listError != null) { %>
	<p style="color:#b00;"><%= listError %></p>
	<% } %>

	<form action="<%= request.getContextPath() %>/order" method="post" accept-charset="UTF-8">
		<p>
			<label for="customerName">Customer name</label><br>
			<input id="customerName" name="customerName" type="text" required>
		</p>
		<p>
			<label for="productName">Item / product name</label><br>
			<input id="productName" name="productName" type="text" required>
		</p>
		<p>
			<label for="orderedGoods">Details (e.g. dozen croissants)</label><br>
			<input id="orderedGoods" name="orderedGoods" type="text" required>
		</p>
		<p>
			<label for="totalPrice">Total price</label><br>
			<input id="totalPrice" name="totalPrice" type="number" step="0.01" required>
		</p>
		<p>
			<label for="orderedDate">Order date</label><br>
			<input id="orderedDate" name="orderedDate" type="date" required>
		</p>
		<p><button type="submit">Save order</button></p>
	</form>

	<hr>
	<% if (listError == null && orderList.isEmpty()) { %>
	<p><em>No orders yet.</em></p>
	<% } else if (!orderList.isEmpty()) {
			for (Order o : orderList) {
	%>
	<p>
		<%= o.getCustomerName() %> —
		<%= o.getProductName() %> —
		<%= o.getOrderedGoods() %> —
		<%= o.getTotalPrice() %> —
		<%= o.getOrderedDate() != null ? o.getOrderedDate() : "" %>
	</p>
	<%
			}
		}
	%>
</body>
</html>
