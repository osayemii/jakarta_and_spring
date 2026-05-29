package com.students_center.listener;
import jakarta.servlet.*;

public class AppContextListener implements ServletContextListener {
   @Override
   public void contextInitialized(ServletContextEvent sce) {
       ServletContext ctx = sce.getServletContext();
       ctx.setAttribute("totalStudents", 0);
       ctx.setAttribute("centerName", "Live Teaching Center");
   }
   @Override
   public void contextDestroyed(ServletContextEvent sce) {}
}

