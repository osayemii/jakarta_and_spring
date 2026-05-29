package com.lesson.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;

public class LoginServlet extends HttpServlet {
	private final String userID = "admin";
	private final String password = "password";
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		String user = req.getParameter("username");
		String pwd = req.getParameter("password");
		
		if (userID.equals(user) && password.equals(pwd)) {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			Cookie userName = new Cookie("user", user);
			res.addCookie(userName);
			res.sendRedirect("loginSuccess.jsp");
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("Login.html");
			res.setContentType("text/html;charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<font color=red>Invalid Login!</font>");
			rd.include(req, res);
		}
	}
}