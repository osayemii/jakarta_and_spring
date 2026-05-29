<%@ page language="java" contentType="text/html; charset=ISO8859-1"
 pageEncoding="ISO-8859-1"
import="com.classwork.model.LoginBean"%>
<!DOCTYPE html>
<html>
<p>You are successfully logged in!</p>
<%
LoginBean bean=(LoginBean)request.getAttribute("bean");
out.print("Welcome, "+bean.getName());
%>
</html>