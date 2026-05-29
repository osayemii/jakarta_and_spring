package com.bank.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Login; successful POST redirects to {@code Bank_Home_Page.jsp}.
 */
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String DEMO_USER = "bankadmin";
	private static final String DEMO_PASS = "bank123";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/UserLogin.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String user = req.getParameter("username");
		String pwd = req.getParameter("password");

		if (DEMO_USER.equals(user) && DEMO_PASS.equals(pwd)) {
			HttpSession session = req.getSession();
			session.setAttribute("bankLoggedIn", Boolean.TRUE);
			String target = req.getContextPath() + "/Bank_Home_Page.jsp";
			res.sendRedirect(res.encodeRedirectURL(target));
		} else {
			req.setAttribute("error", "Invalid username or password");
			req.getRequestDispatcher("/UserLogin.jsp").forward(req, res);
		}
	}
}
