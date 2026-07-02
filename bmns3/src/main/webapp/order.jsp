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
	Order editOrder = (Order) request.getAttribute("editOrder");
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Bakery — Orders</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/nav.jsp" %>
	<div class="page">
		<h1>Customer orders</h1>
		<p class="subtitle">Track what customers have ordered.</p>

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
			<% if (editOrder != null) { %>
			<div class="edit-banner">Editing order for <%= editOrder.getCustomerName() %> — <a href="<%= request.getContextPath() %>/order">cancel</a></div>
			<% } %>
			<form class="stacked-form" action="<%= request.getContextPath() %>/order" method="post" accept-charset="UTF-8">
				<input type="hidden" name="id" value="<%= editOrder != null ? editOrder.getId() : "" %>">
				<div class="form-grid">
					<p>
						<label for="customerName">Customer name</label>
						<input id="customerName" name="customerName" type="text" required value="<%= editOrder != null ? editOrder.getCustomerName() : "" %>">
					</p>
					<p>
						<label for="productName">Item / product name</label>
						<input id="productName" name="productName" type="text" required value="<%= editOrder != null ? editOrder.getProductName() : "" %>">
					</p>
					<p>
						<label for="orderedGoods">Details (e.g. dozen croissants)</label>
						<input id="orderedGoods" name="orderedGoods" type="text" required value="<%= editOrder != null ? editOrder.getOrderedGoods() : "" %>">
					</p>
					<p>
						<label for="totalPrice">Total price</label>
						<input id="totalPrice" name="totalPrice" type="number" step="0.01" required value="<%= editOrder != null ? editOrder.getTotalPrice() : "" %>">
					</p>
					<p>
						<label for="orderedDate">Order date</label>
						<input id="orderedDate" name="orderedDate" type="date" required value="<%= editOrder != null && editOrder.getOrderedDate() != null ? editOrder.getOrderedDate() : "" %>">
					</p>
				</div>
				<div class="form-actions">
					<button type="submit" class="btn btn-primary"><%= editOrder != null ? "Update order" : "Save order" %></button>
					<% if (editOrder != null) { %>
					<a class="btn btn-secondary" href="<%= request.getContextPath() %>/order">Cancel</a>
					<% } %>
				</div>
			</form>
		</div>

		<% if (listError == null && orderList.isEmpty()) { %>
		<p class="empty-state">No orders yet. Add the first one above.</p>
		<% } else if (!orderList.isEmpty()) { %>
		<table class="data-table">
			<thead>
				<tr>
					<th>Customer</th>
					<th>Item</th>
					<th>Details</th>
					<th>Total</th>
					<th>Date</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Order o : orderList) {
				%>
				<tr>
					<td><%= o.getCustomerName() %></td>
					<td><%= o.getProductName() %></td>
					<td><%= o.getOrderedGoods() %></td>
					<td><%= o.getTotalPrice() %></td>
					<td><%= o.getOrderedDate() != null ? o.getOrderedDate() : "" %></td>
					<td>
						<div class="row-actions">
							<a class="btn btn-secondary btn-small" href="<%= request.getContextPath() %>/order?edit=<%= o.getId() %>">Edit</a>
							<form method="post" action="<%= request.getContextPath() %>/order" onsubmit="return confirm('Delete this order?');">
								<input type="hidden" name="id" value="<%= o.getId() %>">
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
