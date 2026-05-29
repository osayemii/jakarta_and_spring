<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ page import="com.emp.model.Employee" %>
<%
String ctx = request.getContextPath();
String servletUrl = ctx + "/TestServlet";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Employee Information</title>
	<link rel="stylesheet" type="text/css" href="<%= ctx %>/css/home.css">
	<script>
		var contextPath = "<%= ctx %>";
		function showErrorPopup(message) {
			alert(message); // Display the error message as an alert popup
		}
		function enableEditing() {
			document.querySelectorAll('.editable').forEach(function (element) {
				element.removeAttribute('disabled');
			});
		}
		function deleteEmployee(employeeName) {
			if (confirm("Are you sure you want to delete this employee ? ")) {
				document.getElementById('actionForm').action = contextPath + '/TestServlet?action=delete';
				document.getElementById('actionForm').submit();
			}
		}
		function toggleViewButton(disable) {
			document.getElementById('viewButton').disabled = disable;
		}
		function toggleViewButtonOnAdd(disable) {
			document.getElementById('viewButton').disabled = disable;
		}
	</script>
</head>
<body>
	<div class="container">
		<div class="left">
			<h1>Select Employee</h1>
			<form id="viewForm" action="<%= servletUrl %>" method="post">
				<label for="Employee_Name">Employee Name:</label>
				<select name="Employee_Name" id="Employee_Name" onchange="toggleViewButton(false);">
					<option value="">Select Option</option>
					<%
					List<String> employeeNames = (List<String>) request.getAttribute("employeeNames");
					if (employeeNames != null) {
						for (String name : employeeNames) {
					%>
					<option value="<%= name %>"><%= name %></option>
					<%
						}
					}
					%>
				</select>
				<br><br>
				<button type="button" class="button-style" onclick="window.location.href='<%= ctx %>/home.jsp?action=add'; toggleViewButton(true);">Add</button>
				<input type="submit" class="button-style" value="View" id="viewButton" disabled>
			</form>

			<%-- Check if error message attribute is set in request and display popup if present --%>
			<%
			String errorMessage = (String) request.getAttribute("errorMessage");
			if (errorMessage != null) {
			%>
			<script>alert('<%= errorMessage.replace("'", "\\'") %>');</script>
			<%
			}
			%>
		</div>
		<div class="right">
			<h2>Employee Information</h2>
			<%-- Check if employee attribute is set in request and display employee details if present --%>
			<%
			String action = request.getParameter("action");
			Employee employee = (Employee) request.getAttribute("Employee");
			if ("add".equals(action)) {
			%>
			<form id="actionForm" action="<%= servletUrl %>" method="post">
				<input type="hidden" name="action" value="add">
				<table>
					<tr>
						<th>Employee Name</th>
						<td><input type="text" name="employeeName" value="" required autocomplete="off"></td>
					</tr>
					<tr>
						<th>Date of Joining (DOJ)</th>
						<td><input type="date" name="doj" value="" required></td>
					</tr>
					<tr>
						<th>Department</th>
						<td><input type="text" name="department" value="" required></td>
					</tr>
					<tr>
						<th>Date of Birth (DOB)</th>
						<td><input type="date" name="dob" value="" required></td>
					</tr>
					<tr>
						<th>Salary</th>
						<td><input type="number" name="salary" value="" min="0" step="0.01" required></td>
					</tr>
				</table>
				<div class="buttons">
					<button type="submit" class="button-style" onclick="toggleViewButton(false);">Add Employee</button>
				</div>
			</form>
			<%
			} else if (employee != null) {
			%>
			<form id="actionForm" action="<%= servletUrl %>" method="post">
				<table>
					<tr>
						<th>Employee Name</th>
						<td><input type="text" name="employeeName" value="<%= employee.getEmployeeName() %>" readonly></td>
					</tr>
					<tr>
						<th>Date of Joining (DOJ)</th>
						<td><input type="date" name="doj" value="<%= employee.getDOJ() %>" class="editable" disabled></td>
					</tr>
					<tr>
						<th>Department</th>
						<td><input type="text" name="department" value="<%= employee.getDepartment() %>" class="editable" disabled></td>
					</tr>
					<tr>
						<th>Date of Birth (DOB)</th>
						<td><input type="date" name="dob" value="<%= employee.getDOB() %>" class="editable" disabled></td>
					</tr>
					<tr>
						<th>Salary</th>
						<td><input type="number" name="salary" value="<%= employee.getSalary() %>" class="editable" disabled min="0" step="0.01"></td>
					</tr>
				</table>
				<div class="buttons">
					<button type="button" class="button-style" onclick="enableEditing()">Edit</button>
					<button type="submit" class="button-style" formaction="<%= servletUrl %>?action=update" onclick="toggleViewButton(false);">Submit</button>
					<button type="button" class="button-style" onclick="deleteEmployee('<%= employee.getEmployeeName().replace("'", "\\'") %>')">Delete</button>
				</div>
			</form>
			<%
			} else {
			%>
			<p>No employee found with the given details.</p>
			<%
			}
			%>
		</div>
	</div>
</body>
</html>
