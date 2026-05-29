<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.bank.model.Bank" %>
<%
if (request.getAttribute("customers") == null) {
	request.getRequestDispatcher("/HomeServlet").forward(request, response);
	return;
}
String ctx = request.getContextPath();
String homeServlet = ctx + "/HomeServlet";
Bank customer = (Bank) request.getAttribute("customer");
boolean editable = Boolean.TRUE.equals(request.getAttribute("editable"));
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Bank — Customer details</title>
	<link rel="stylesheet" type="text/css" href="<%= ctx %>/css/bank.css">
	<script>
		function toggleAddPanel() {
			var p = document.getElementById('addPanel');
			p.style.display = (p.style.display === 'none' || p.style.display === '') ? 'block' : 'none';
		}
		function submitToolbar(action) {
			var sel = document.getElementById('customerSelect');
			if ((action === 'view' || action === 'edit' || action === 'delete') && (!sel || !sel.value)) {
				alert('Please select a customer from the dropdown.');
				return;
			}
			document.getElementById('toolbarAction').value = action;
			document.getElementById('toolbarForm').submit();
		}
		function confirmDelete() {
			if (!confirm('Delete the selected customer?')) {
				return;
			}
			submitToolbar('delete');
		}
	</script>
</head>
<body>
	<div class="bank-container">
		<h1><span class="icon" aria-hidden="true">🏦</span> Bank — Customer details</h1>

		<% if (request.getAttribute("errorMessage") != null) { %>
		<p class="msg msg-error"><span class="icon-inline" aria-hidden="true">⚠️</span> <%= request.getAttribute("errorMessage") %></p>
		<% } %>
		<% if (request.getAttribute("infoMessage") != null) { %>
		<p class="msg msg-info"><span class="icon-inline" aria-hidden="true">✅</span> <%= request.getAttribute("infoMessage") %></p>
		<% } %>

		<form id="toolbarForm" action="<%= homeServlet %>" method="post">
			<input type="hidden" name="action" id="toolbarAction" value="list">

			<label for="customerSelect"><span class="icon-inline" aria-hidden="true">👥</span> Customers</label>
			<select name="customerId" id="customerSelect">
				<option value="">— Select —</option>
				<%
				@SuppressWarnings("unchecked")
				List<Bank> customers = (List<Bank>) request.getAttribute("customers");
				if (customers != null) {
					for (Bank c : customers) {
				%>
				<option value="<%= c.getCustomerId() %>"
					<%= customer != null && customer.getCustomerId() != null
						&& customer.getCustomerId().equals(c.getCustomerId()) ? " selected" : "" %>>
					<%= c.getName() %>
				</option>
				<%
					}
				}
				%>
			</select>

			<div class="btn-row">
				<button type="button" class="btn" onclick="toggleAddPanel();"><span class="icon-inline" aria-hidden="true">➕</span> Add</button>
				<button type="button" class="btn" onclick="submitToolbar('view');"><span class="icon-inline" aria-hidden="true">👁️</span> View</button>
				<button type="button" class="btn" onclick="submitToolbar('edit');"><span class="icon-inline" aria-hidden="true">✏️</span> Update</button>
				<button type="button" class="btn btn-danger" onclick="confirmDelete();"><span class="icon-inline" aria-hidden="true">🗑️</span> Delete</button>
			</div>
		</form>

		<div id="addPanel" class="panel" style="display: none;">
			<h2><span class="icon-inline" aria-hidden="true">➕</span> Add customer</h2>
			<form action="<%= homeServlet %>" method="post">
				<input type="hidden" name="action" value="add">
				<table>
					<tr><th>Customer ID</th><td><input type="text" name="customerId" required></td></tr>
					<tr><th>Name</th><td><input type="text" name="name" required></td></tr>
					<tr><th>Age</th><td><input type="number" name="age" min="0" max="150"></td></tr>
					<tr><th>Date of Birth</th><td><input type="date" name="dateOfBirth"></td></tr>
				</table>
				<button type="submit" class="btn"><span class="icon-inline" aria-hidden="true">💾</span> Submit add</button>
			</form>
		</div>

		<% if (customer != null) { %>
		<div class="panel">
			<h2><span class="icon-inline" aria-hidden="true">📋</span> Customer details</h2>
			<form action="<%= homeServlet %>" method="post">
				<input type="hidden" name="action" value="update">
				<table>
					<tr>
						<th>Customer ID</th>
						<td><input type="text" name="customerId" value="<%= customer.getCustomerId() %>" readonly></td>
					</tr>
					<tr>
						<th>Name</th>
						<td><input type="text" name="name" value="<%= customer.getName() != null ? customer.getName() : "" %>"
							<%= editable ? "" : "readonly" %>></td>
					</tr>
					<tr>
						<th>Age</th>
						<td><input type="number" name="age" min="0" max="150"
							value="<%= customer.getAge() != null ? customer.getAge() : "" %>"
							<%= editable ? "" : "readonly" %>></td>
					</tr>
					<tr>
						<th>Date of Birth</th>
						<td><input type="date" name="dateOfBirth" value="<%= customer.getDateOfBirth() != null ? customer.getDateOfBirth() : "" %>"
							<%= editable ? "" : "readonly" %>></td>
					</tr>
				</table>
				<% if (editable) { %>
				<button type="submit" class="btn"><span class="icon-inline" aria-hidden="true">💾</span> Save changes</button>
				<% } %>
			</form>
		</div>
		<% } %>
	</div>
</body>
</html>
