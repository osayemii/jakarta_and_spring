<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="java.util.List,com.tcms3.model.Teacher"%>

<!DOCTYPE html>
<html>
<head><title>Teachers</title></head>

<body>
	<h2>Teachers</h2>

	<form action="teachers" method="post">
		Name: <input type="text" name="name" required>
		Email: <input type="text" name="email" required>
		<input type="submit">
	</form>

	<%
	List teacherList = (List) request.getAttribute("teachers");
	if (teacherList == null) {
		teacherList = List.of();
	}
	if (teacherList.isEmpty()) {
	%>
	<p>There is no teacher on the list.</p>
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
			for (int i = 0; i < teacherList.size(); i++) {
				Teacher t = (Teacher) teacherList.get(i);
			%>
			<tr>
				<td><%= i + 1 %></td>
				<td><%= t.getName() != null ? t.getName() : "" %></td>
				<td><%= t.getEmail() != null ? t.getEmail() : "" %></td>
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
