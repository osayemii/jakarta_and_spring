<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	/* Always use the servlet so the form (including password) cannot be an old cached JSP. */
	response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login"));
%>
