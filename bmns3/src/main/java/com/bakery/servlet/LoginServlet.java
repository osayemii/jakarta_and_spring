package com.bakery.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			// Navigating (or back-navigating) to /login while already signed in
			// ends the session, so landing on the login page always means a
			// fresh login rather than bouncing back to the dashboard.
			session.invalidate();
		}
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		if ("osayemi".equals(username) && "daniel".equals(password)) {
			HttpSession session = req.getSession(true);
			session.setAttribute("user", username);
			resp.sendRedirect(req.getContextPath() + "/dashboard");
		} else {
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
		}
	}
}
