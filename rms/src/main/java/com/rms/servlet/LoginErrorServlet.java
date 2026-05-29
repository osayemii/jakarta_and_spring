package com.rms.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login-error")
public class LoginErrorServlet extends HttpServlet {

	private static final String MSG_AUTH = "Invalid username or password. Use username \"admin\" and password \"1234\".";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ("auth".equals(req.getParameter("reason"))) {
			req.setAttribute("authError", MSG_AUTH);
		}
		req.getRequestDispatcher("/auth-error.jsp").forward(req, resp);
	}
}
