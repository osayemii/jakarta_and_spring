<%-- Shared header/nav include. Expects request/session/application scope to be available. --%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/style.css">
<header class="site-header">
	<a class="brand" href="<%= request.getContextPath() %>/dashboard"><%= application.getAttribute("bakeryName") %></a>
	<nav class="site-nav">
		<a href="<%= request.getContextPath() %>/dashboard">Dashboard</a>
		<a href="<%= request.getContextPath() %>/staff">Staff</a>
		<a href="<%= request.getContextPath() %>/product">Menu items</a>
		<a href="<%= request.getContextPath() %>/order">Orders</a>
		<a href="<%= request.getContextPath() %>/logout">Logout</a>
	</nav>
</header>
