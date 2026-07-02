<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Bakery — Login</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/style.css">
</head>
<body>
	<div class="center-page">
		<div class="auth-card">
			<h1>Staff login</h1>
			<form class="stacked-form" action="<%= request.getContextPath() %>/login" method="post" accept-charset="UTF-8" style="text-align:left;">
				<p>
					<label for="username">Username</label>
					<input id="username" name="username" type="text" required>
				</p>
				<p>
					<label for="password">Password</label>
					<input id="password" name="password" type="password" required>
				</p>
				<div class="form-actions" style="justify-content:center;">
					<button type="submit" class="btn btn-primary">Sign in</button>
				</div>
			</form>
			<p class="hint">Demo: admin / admin</p>
		</div>
	</div>
</body>
</html>
