<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<%@ page import="java.util.List"%>
<%@ page import="com.tcms3.model.Student"%>

<!DOCTYPE html>
<html>
<head><title>Students</title></head>

<body>
	<h2>Students</h2>

	<form action="students" method="post">
		Name: <input type="text" name="name" required>
		Email: <input type="text" name="email" required>
		<input type="submit">
	</form>

	<%
	@SuppressWarnings("unchecked")
	List<Student> studentList = (List<Student>) request.getAttribute("students");
	if (studentList == null) {
		studentList = List.of();
	}
	if (studentList.isEmpty()) {
	%>
	<p>There is no student on the list.</p>
	<%
	} else {
	%>
	<table border="1" cellpadding="6" cellspacing="0">
		<thead>
			<tr>
				<th>S/N</th>
				<th>Name</th>
				<th>Email</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < studentList.size(); i++) {
				Student s = studentList.get(i);
			%>
			<tr>
				<td><%= i + 1 %></td>
				<td><%= s.getName() != null ? s.getName() : "" %></td>
				<td><%= s.getEmail() != null ? s.getEmail() : "" %></td>
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
