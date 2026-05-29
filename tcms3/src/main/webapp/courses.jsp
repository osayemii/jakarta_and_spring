<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<%@ page import="java.util.List"%>
<%@ page import="com.tcms3.model.Course"%>

<!DOCTYPE html>
<html>
<head><title>Courses</title></head>

<body>
	<h2>Courses</h2>

	<form action="courses" method="post">
		Name: <input type="text" name="name" required>
		Department: <input type="text" name="department" required>
		<input type="submit">
	</form>

	<%
	@SuppressWarnings("unchecked")
	List<Course> courseList = (List<Course>) request.getAttribute("courses");
	if (courseList == null) {
		courseList = List.of();
	}
	if (courseList.isEmpty()) {
	%>
	<p>There is no course on the list.</p>
	<%
	} else {
	%>
	<table border="1" cellpadding="6" cellspacing="0">
		<thead>
			<tr>
				<th>S/N</th>
				<th>Name</th>
				<th>Department</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < courseList.size(); i++) {
				Course c = courseList.get(i);
			%>
			<tr>
				<td><%= i + 1 %></td>
				<td><%= c.getName() != null ? c.getName() : "" %></td>
				<td><%= c.getDepartment() != null ? c.getDepartment() : "" %></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<%
	}
	%>

</body>
</html>
