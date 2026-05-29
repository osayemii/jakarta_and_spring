package com.rms.servlet;

import java.io.IOException;
import java.util.Collections;

import com.rms.dao.MenuDAO;
import com.rms.model.MenuItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!AuthUtil.requireLogin(req, resp)) {
			return;
		}
		try {
			req.setAttribute("menuList", new MenuDAO().getAll());
		} catch (Exception e) {
			getServletContext().log("menu list", e);
			req.setAttribute("menuList", Collections.emptyList());
		}
		req.getRequestDispatcher("/menu.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!AuthUtil.requireLogin(req, resp)) {
			return;
		}
		req.setCharacterEncoding("UTF-8");
		try {
			double price = Double.parseDouble(AuthUtil.param(req, "price"));
			MenuItem item = new MenuItem(AuthUtil.param(req, "name"), price, AuthUtil.param(req, "category"));
			new MenuDAO().insert(item);
		} catch (Exception e) {
			getServletContext().log("menu save", e);
		}
		resp.sendRedirect(req.getContextPath() + "/menu");
	}
}
