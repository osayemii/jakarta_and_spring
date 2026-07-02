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
	Product editProduct = (Product) request.getAttribute("editProduct");
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Bakery — Menu items</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/nav.jsp" %>
	<div class="page">
		<h1>Menu items</h1>
		<p class="subtitle">Everything on the bakery menu, with stock and pricing.</p>

		<% if (flashOk != null) { %>
		<div class="alert alert-success"><%= flashOk %></div>
		<% } %>
		<% if (flashError != null) { %>
		<div class="alert alert-error"><%= flashError %></div>
		<% } %>
		<% if (listError != null) { %>
		<div class="alert alert-error"><%= listError %></div>
		<% } %>

		<div class="card">
			<% if (editProduct != null) { %>
			<div class="edit-banner">Editing <%= editProduct.getProductName() %> — <a href="<%= request.getContextPath() %>/product">cancel</a></div>
			<% } %>
			<form class="stacked-form" action="<%= request.getContextPath() %>/product" method="post" accept-charset="UTF-8">
				<input type="hidden" name="id" value="<%= editProduct != null ? editProduct.getId() : "" %>">
				<div class="form-grid">
					<p>
						<label for="name">Item name</label>
						<input id="name" name="name" type="text" required value="<%= editProduct != null ? editProduct.getProductName() : "" %>">
					</p>
					<p>
						<label for="price">Price</label>
						<input id="price" name="price" type="number" step="0.01" required value="<%= editProduct != null ? editProduct.getProductPrice() : "" %>">
					</p>
					<p>
						<label for="quantity">Quantity in stock</label>
						<input id="quantity" name="quantity" type="number" required value="<%= editProduct != null ? editProduct.getProductQuantity() : "" %>">
					</p>
					<p>
						<label for="productionDate">Bake / production date</label>
						<input id="productionDate" name="productionDate" type="date" required value="<%= editProduct != null && editProduct.getProductionDate() != null ? editProduct.getProductionDate() : "" %>">
					</p>
				</div>
				<div class="form-actions">
					<button type="submit" class="btn btn-primary"><%= editProduct != null ? "Update item" : "Add item" %></button>
					<% if (editProduct != null) { %>
					<a class="btn btn-secondary" href="<%= request.getContextPath() %>/product">Cancel</a>
					<% } %>
				</div>
			</form>
		</div>

		<% if (listError == null && productList.isEmpty()) { %>
		<p class="empty-state">No menu items yet. Add your first one above.</p>
		<% } else if (!productList.isEmpty()) { %>
		<table class="data-table">
			<thead>
				<tr>
					<th>Item</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Production date</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Product p : productList) {
				%>
				<tr>
					<td><%= p.getProductName() %></td>
					<td><%= p.getProductPrice() %></td>
					<td><%= p.getProductQuantity() %></td>
					<td><%= p.getProductionDate() != null ? p.getProductionDate() : "" %></td>
					<td>
						<div class="row-actions">
							<a class="btn btn-secondary btn-small" href="<%= request.getContextPath() %>/product?edit=<%= p.getId() %>">Edit</a>
							<form method="post" action="<%= request.getContextPath() %>/product" onsubmit="return confirm('Delete this menu item?');">
								<input type="hidden" name="id" value="<%= p.getId() %>">
								<input type="hidden" name="_action" value="delete">
								<button type="submit" class="btn btn-danger btn-small">Delete</button>
							</form>
						</div>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<% } %>
	</div>
</body>
</html>
