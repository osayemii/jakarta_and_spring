package com.students_center.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if("admin".equals(username)&&"admin".equals(password)) {
			
			HttpSession session = req.getSession();
			session.setAttribute("user",username);
			
			res.sendRedirect("Dashboard.jsp");
			
		}else {
			req.setAttribute("error","invalid login");
			req.getRequestDispatcher("error.jsp").forward(req, res);
		}
	}
}