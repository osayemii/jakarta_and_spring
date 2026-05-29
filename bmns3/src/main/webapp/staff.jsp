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
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Bakery — Staff</title>
</head>
<body>
	<p><a href="<%= request.getContextPath() %>/dashboard">← Dashboard</a></p>
	<h1>Staff</h1>
	<% if (flashOk != null) { %>
	<p style="color:#060;"><%= flashOk %></p>
	<% } %>
	<% if (flashError != null) { %>
	<p style="color:#b00;"><%= flashError %></p>
	<% } %>
	<% if (listError != null) { %>
	<p style="color:#b00;"><%= listError %></p>
	<% } %>

	<form action="<%= request.getContextPath() %>/staff" method="post" accept-charset="UTF-8">
		<p>
			<label for="name">Name</label><br>
			<input id="name" name="name" type="text" required>
		</p>
		<p>
			<label for="email">Email</label><br>
			<input id="email" name="email" type="email" required placeholder="you@example.com">
		</p>
		<p>
			<label for="age">Age</label><br>
			<input id="age" name="age" type="number" min="1" required>
		</p>
		<p>
			<label for="phone">Phone</label><br>
			<input id="phone" name="phone" type="text" required>
		</p>
		<p>
			<label for="address">Address</label><br>
			<input id="address" name="address" type="text" required>
		</p>
		<p><button type="submit">Add staff</button></p>
	</form>

	<hr>
	<% if (listError == null && staffList.isEmpty()) { %>
	<p><em>No staff yet.</em></p>
	<% } else if (!staffList.isEmpty()) {
			for (Staff s : staffList) {
	%>
	<p>
		<%= s.getName() %> —
		<%= s.getEmail() %> —
		<%= s.getAge() %> —
		<%= s.getPhone() != null ? s.getPhone() : "" %> —
		<%= s.getAddress() != null ? s.getAddress() : "" %>
	</p>
	<%
			}
		}
	%>
</body>
</html>
