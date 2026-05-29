package com.rms.servlet;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public final class AuthUtil {

	private AuthUtil() {}

	public static boolean isLoggedIn(HttpSession session) {
		return session != null && session.getAttribute("user") != null;
	}

	public static boolean requireLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(false);
		if (!isLoggedIn(session)) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return false;
		}
		return true;
	}

	public static String param(HttpServletRequest req, String name) {
		String v = req.getParameter(name);
		return v == null ? "" : v;
	}
}
