<%@ page import="java.util.*, com.students_center.model.Student" %>

<h2>Students</h2>

<form method = "post" action = "students">
	Name: <input type = "text" name = "name"/>
	Email: <input type = "text" name = "email"/>
	<button>Add</button>
</form>

<ul>
<%
	List<Student> students = (List<Student>) request.getAttribute("students");
	for (Student s : students) { 
		%>
		 <li><%= s.getName() %> - <%= s.getEmail() %></li>
		<% 
	}
%>
</ul>