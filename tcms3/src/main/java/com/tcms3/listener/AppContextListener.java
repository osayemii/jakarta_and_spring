package com.tcms3.listener;

import jakarta.servlet.*;

public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("totalStudents", 0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}