<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.List,com.bakery.model.Product"%>
<%
	@SuppressWarnings("unchecked")
	List<Product> productList = (List<Product>) request.getAttribute("productList");
	if (productList == null) {
		response.sendRedirect(request.getContextPath() + "/product");
		return;
	}
	String flashOk = (String) session.getAttribute("flashOk_product");
	if (flashOk != null) {
		session.removeAttribute("flashOk_product");
	}
	String flashError = (String) session.getAttribute("flashError_product");
	if (flashError != null) {
		session.removeAttribute("flashError_product");
	}
	Object listError = request.getAttribute("listError");
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Bakery — Menu items</title>
</head>
<body>
	<p><a href="<%= request.getContextPath() %>/dashboard">← Dashboard</a></p>
	<h1>Menu items</h1>
	<% if (flashOk != null) { %>
	<p style="color:#060;"><%= flashOk %></p>
	<% } %>
	<% if (flashError != null) { %>
	<p style="color:#b00;"><%= flashError %></p>
	<% } %>
	<% if (listError != null) { %>
	<p style="color:#b00;"><%= listError %></p>
	<% } %>

	<form action="<%= request.getContextPath() %>/product" method="post" accept-charset="UTF-8">
		<p>
			<label for="name">Item name</label><br>
			<input id="name" name="name" type="text" required>
		</p>
		<p>
			<label for="price">Price</label><br>
			<input id="price" name="price" type="number" step="0.01" required>
		</p>
		<p>
			<label for="quantity">Quantity in stock</label><br>
			<input id="quantity" name="quantity" type="number" required>
		</p>
		<p>
			<label for="productionDate">Bake / production date</label><br>
			<input id="productionDate" name="productionDate" type="date" required>
		</p>
		<p><button type="submit">Add item</button></p>
	</form>

	<hr>
	<% if (listError == null && productList.isEmpty()) { %>
	<p><em>No menu items yet.</em></p>
	<% } else if (!productList.isEmpty()) {
			for (Product p : productList) {
	%>
	<p>
		<%= p.getProductName() %> —
		<%= p.getProductPrice() %> —
		<%= p.getProductQuantity() %> —
		<%= p.getProductionDate() != null ? p.getProductionDate() : "" %>
	</p>
	<%
			}
		}
	%>
</body>
</html>
