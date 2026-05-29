package com.rms.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;

import com.rms.dao.DiningOrderDAO;
import com.rms.model.DiningOrder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dining")
public class DiningServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!AuthUtil.requireLogin(req, resp)) {
			return;
		}
		try {
			req.setAttribute("diningList", new DiningOrderDAO().getAll());
		} catch (Exception e) {
			getServletContext().log("dining list", e);
			req.setAttribute("diningList", Collections.emptyList());
		}
		req.getRequestDispatcher("/dining.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!AuthUtil.requireLogin(req, resp)) {
			return;
		}
		req.setCharacterEncoding("UTF-8");
		try {
			int tableNumber = Integer.parseInt(AuthUtil.param(req, "tableNumber"));
			double totalPrice = Double.parseDouble(AuthUtil.param(req, "totalPrice"));
			LocalDate orderDate = LocalDate.parse(AuthUtil.param(req, "orderDate"));
			DiningOrder order = new DiningOrder(
					tableNumber,
					AuthUtil.param(req, "dishName"),
					AuthUtil.param(req, "notes"),
					totalPrice,
					orderDate);
			new DiningOrderDAO().insert(order);
		} catch (Exception e) {
			getServletContext().log("dining save", e);
		}
		resp.sendRedirect(req.getContextPath() + "/dining");
	}
}
