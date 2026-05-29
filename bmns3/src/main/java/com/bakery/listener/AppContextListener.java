package com.bakery.listener;

import jakarta.servlet.*;

public class AppContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();
		ctx.setAttribute("bakeryTagline", "Fresh breads, pastries and orders");
		ctx.setAttribute("bakeryName", "Artisan Bakery");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {}
}
