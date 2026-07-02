package com.bakery.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Handles /index. Not on the request path today (the welcome-file is
 * index.jsp directly), but web.xml maps /index to this class, so it needs to
 * exist to avoid a ClassNotFoundException if that URL is ever hit directly.
 */
public class IndexServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			resp.sendRedirect(req.getContextPath() + "/dashboard");
			return;
		}
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
