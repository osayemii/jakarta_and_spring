package com.jemimahRecipe.listener;

import jakarta.servlet.*;

public class AppContextListeners implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {}
}