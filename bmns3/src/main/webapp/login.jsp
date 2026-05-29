<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Bakery — Login</title>
</head>
<body>
	<h1>Staff login</h1>
	<form action="<%= request.getContextPath() %>/login" method="post" accept-charset="UTF-8">
		<p>
			<label for="username">Username</label><br>
			<input id="username" name="username" type="text" required>
		</p>
		<p>
			<label for="password">Password</label><br>
			<input id="password" name="password" type="password" required>
		</p>
		<p>
			<button type="submit">Sign in</button>
		</p>
	</form>
	<p><small>Demo: admin / admin</small></p>
</body>
</html>
