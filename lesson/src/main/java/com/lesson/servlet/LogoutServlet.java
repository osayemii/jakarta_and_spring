package com.lesson.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.*;

import java.io.*;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public LogoutServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("JSESSIONID")) {
					System.out.println("JSESSIONID="+cookie.getValue());
					break;
				}
			}
		}
		
		HttpSession session = req.getSession(false);
		if (session != null) {
			System.out.println("User="+session.getAttribute("user"));
			session.invalidate();
		}
		res.sendRedirect("Login.html");
	}
}