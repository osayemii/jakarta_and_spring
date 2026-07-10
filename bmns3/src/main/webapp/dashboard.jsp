<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	Object statsError = request.getAttribute("statsError");
	Object staffCount = request.getAttribute("staffCount");
	Object productCount = request.getAttribute("productCount");
	Object orderCount = request.getAttribute("orderCount");
	Object revenue = request.getAttribute("revenue");
	String revenueText = revenue != null ? String.format("$%.2f", Double (revenue)) : "$0.00";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Bakery — Dashboard</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/nav.jsp" %>
	<div class="page">
		<h1>${applicationScope.bakeryName}</h1>
		<p class="subtitle">Signed in as <strong>${sessionScope.user}</strong>. ${applicationScope.bakeryTagline}</p>

		<% if (statsError != null) { %>
		<div class="alert alert-error"><%= statsError %></div>
		<% } %>

		<div class="stat-grid">
			<div class="stat-card">
				<div class="stat-value"><%= staffCount != null ? staffCount : 0 %></div>
				<div class="stat-label">Staff members</div>
			</div>
			<div class="stat-card">
				<div class="stat-value"><%= productCount != null ? productCount : 0 %></div>
				<div class="stat-label">Menu items</div>
			</div>
			<div class="stat-card">
				<div class="stat-value"><%= orderCount != null ? orderCount : 0 %></div>
				<div class="stat-label">Orders</div>
			</div>
			<div class="stat-card">
				<div class="stat-value"><%= revenueText %></div>
				<div class="stat-label">Total revenue</div>
			</div>
		</div>

		<div class="card">
			<h2 style="margin-top:0;color:var(--brown);">Quick links</h2>
			<div class="row-actions" style="gap:10px;">
				<a class="btn btn-primary" href="<%= request.getContextPath() %>/staff">Manage staff</a>
				<a class="btn btn-primary" href="<%= request.getContextPath() %>/product">Manage menu items</a>
				<a class="btn btn-primary" href="<%= request.getContextPath() %>/order">Manage orders</a>
			</div>
		</div>
	</div>
</body>
</html>
