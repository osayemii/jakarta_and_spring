<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List" import="com.jemimahRecipe.model.Recipe" %>

<!DOCTYPE html>
<html>
<head><title>Dashboard Page</title></head>

<body>
<form action="jemimahRecipe" method="post">
	Name: <input type="text" name="name" /><br><br>
	Description: <input type="text" name="description" /><br><br>
	<input type="submit" value="Add" />
</form>

<%
	Object error = request.getAttribute("error");
	if (error != null) {
%>
<p style="color:red;">Error: <%= error %></p>
<%
	}
	
	List<Recipe> orderlist = (List<Recipe>) request.getAttribute("orderlist");
	if (orderlist == null || orderlist.isEmpty()) {
%>
	<p>No recipes yet.</p>
<%
	} else {	
%>
	<h3>Recipe List</h3>
	<table border="1" cellpadding="8" cellspace="0" style="textalign:left;">
		<tr>
		<th>Name</th>
		<th>Description</th>
		</tr>
		
		<% 
			for (Recipe r : orderlist) {
		%>
		
		<tr>
		<td><%= r.getName() %></td>
		<td><%= r.getDescription() %></td>
		</tr>
		<% } %>
	</table>
<% } %>
</body>
</html>