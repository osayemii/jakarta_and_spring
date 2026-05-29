<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Bakery — Welcome</title>
</head>
<body>
	<h1>${applicationScope.bakeryName}</h1>
	<p>${applicationScope.bakeryTagline}</p>
	<form action="<%= request.getContextPath() %>/login" method="get">
		<button type="submit">Staff login</button>
	</form>
</body>
</html>
