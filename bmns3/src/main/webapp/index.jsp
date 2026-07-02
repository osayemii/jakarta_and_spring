<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Bakery — Welcome</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/style.css">
</head>
<body>
	<div class="center-page">
		<div class="auth-card">
			<h1>${applicationScope.bakeryName}</h1>
			<p class="subtitle">${applicationScope.bakeryTagline}</p>
			<form action="<%= request.getContextPath() %>/login" method="get">
				<button type="submit" class="btn btn-primary">Staff login</button>
			</form>
		</div>
	</div>
</body>
</html>
