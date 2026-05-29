package com.emp.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String CORRECT_USERNAME =  "osayemi";
	private static final String CORRECT_PASSWORD =  "daniel";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/login.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String user = req.getParameter("username");
		String pwd = req.getParameter("password");

		if (CORRECT_USERNAME.equals(user) && CORRECT_PASSWORD.equals(pwd)) {
			String target = req.getContextPath() + "/home.jsp";
			res.sendRedirect(res.encodeRedirectURL(target));
		} else {
			req.setAttribute("error", "Invalid username or password");
			req.getRequestDispatcher("/login.jsp").forward(req, res);
		}
	}
}