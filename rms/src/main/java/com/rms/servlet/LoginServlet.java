package com.rms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final String ADMIN_USER = "admin";
	private static final String ADMIN_PASS = "1234";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (AuthUtil.isLoggedIn(session)) {
			resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/dashboard"));
			return;
		}
		String ctx = req.getContextPath();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, private");
		resp.setHeader("Pragma", "no-cache");
		resp.setDateHeader("Expires", 0);
		PrintWriter w = resp.getWriter();
		w.println("<!DOCTYPE html>");
		w.println("<html lang=\"en\"><head><meta charset=\"UTF-8\">");
		w.println("<title>RMS — Login</title></head><body>");
		w.println("<h1>Login</h1>");
		w.println("<p><strong>Restaurant Management (RMS)</strong></p>");
		w.println("<form action=\"" + ctx + "/login\" method=\"post\" accept-charset=\"UTF-8\">");
		w.println("<p><label for=\"username\">Username</label><br>");
		w.println("<input id=\"username\" name=\"username\" type=\"text\" required autocomplete=\"username\"></p>");
		w.println("<p><label for=\"password\">Password</label><br>");
		w.println("<input id=\"password\" name=\"password\" type=\"password\" required autocomplete=\"current-password\"></p>");
		w.println("<p><button type=\"submit\">Continue</button></p>");
		w.println("</form>");
		w.println("<p><a href=\"" + ctx + "/index\">Back</a></p>");
		w.println("</body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = AuthUtil.param(req, "username").trim();
		String password = AuthUtil.param(req, "password").trim();
		if (ADMIN_USER.equals(username) && ADMIN_PASS.equals(password)) {
			HttpSession session = req.getSession(true);
			session.setAttribute("user", username);
			resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/dashboard"));
			return;
		}
		String errUrl = req.getContextPath() + "/login-error?reason=auth";
		resp.sendRedirect(resp.encodeRedirectURL(errUrl));
	}
}
