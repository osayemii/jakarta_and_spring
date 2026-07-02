<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.List,com.bakery.model.Staff"%>
<%
	@SuppressWarnings("unchecked")
	List<Staff> staffList = (List<Staff>) request.getAttribute("staffList");
	if (staffList == null) {
		response.sendRedirect(request.getContextPath() + "/staff");
		return;
	}
	String flashOk = (String) session.getAttribute("flashOk_staff");
	if (flashOk != null) {
		session.removeAttribute("flashOk_staff");
	}
	String flashError = (String) session.getAttribute("flashError_staff");
	if (flashError != null) {
		session.removeAttribute("flashError_staff");
	}
	Object listError = request.getAttribute("listError");
	Staff editStaff = (Staff) request.getAttribute("editStaff");
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Bakery — Staff</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/nav.jsp" %>
	<div class="page">
		<h1>Staff</h1>
		<p class="subtitle">Manage the people who work in the bakery.</p>

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
			<% if (editStaff != null) { %>
			<div class="edit-banner">Editing <%= editStaff.getName() %> — <a href="<%= request.getContextPath() %>/staff">cancel</a></div>
			<% } %>
			<form class="stacked-form" action="<%= request.getContextPath() %>/staff" method="post" accept-charset="UTF-8">
				<input type="hidden" name="id" value="<%= editStaff != null ? editStaff.getId() : "" %>">
				<div class="form-grid">
					<p>
						<label for="name">Name</label>
						<input id="name" name="name" type="text" required value="<%= editStaff != null ? editStaff.getName() : "" %>">
					</p>
					<p>
						<label for="email">Email</label>
						<input id="email" name="email" type="email" required placeholder="you@example.com" value="<%= editStaff != null ? editStaff.getEmail() : "" %>">
					</p>
					<p>
						<label for="age">Age</label>
						<input id="age" name="age" type="number" min="1" required value="<%= editStaff != null ? editStaff.getAge() : "" %>">
					</p>
					<p>
						<label for="phone">Phone</label>
						<input id="phone" name="phone" type="tel" required value="<%= editStaff != null && editStaff.getPhone() != null ? editStaff.getPhone() : "" %>">
					</p>
					<p>
						<label for="address">Address</label>
						<input id="address" name="address" type="text" required value="<%= editStaff != null && editStaff.getAddress() != null ? editStaff.getAddress() : "" %>">
					</p>
				</div>
				<div class="form-actions">
					<button type="submit" class="btn btn-primary"><%= editStaff != null ? "Update staff" : "Add staff" %></button>
					<% if (editStaff != null) { %>
					<a class="btn btn-secondary" href="<%= request.getContextPath() %>/staff">Cancel</a>
					<% } %>
				</div>
			</form>
		</div>

		<% if (listError == null && staffList.isEmpty()) { %>
		<p class="empty-state">No staff yet. Add your first team member above.</p>
		<% } else if (!staffList.isEmpty()) { %>
		<table class="data-table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Age</th>
					<th>Phone</th>
					<th>Address</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Staff s : staffList) {
				%>
				<tr>
					<td><%= s.getName() %></td>
					<td><%= s.getEmail() %></td>
					<td><%= s.getAge() %></td>
					<td><%= s.getPhone() != null ? s.getPhone() : "" %></td>
					<td><%= s.getAddress() != null ? s.getAddress() : "" %></td>
					<td>
						<div class="row-actions">
							<a class="btn btn-secondary btn-small" href="<%= request.getContextPath() %>/staff?edit=<%= s.getId() %>">Edit</a>
							<form method="post" action="<%= request.getContextPath() %>/staff" onsubmit="return confirm('Delete this staff member?');">
								<input type="hidden" name="id" value="<%= s.getId() %>">
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
