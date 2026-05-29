package com.bakery.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;

import com.bakery.dao.OrderJPADAO;
import com.bakery.model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class OrderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		try {
			req.setAttribute("orderList", new OrderJPADAO().getAll());
		} catch (Exception e) {
			getServletContext().log("Bakery order list failed", e);
			req.setAttribute("orderList", Collections.emptyList());
			req.setAttribute("listError", "Could not load orders. Check DB connection and schema. (" + e.getMessage() + ")");
		}
		req.getRequestDispatcher("/order.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession authSession = req.getSession(false);
		if (authSession == null || authSession.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		try {
			double totalPrice = Double.parseDouble(req.getParameter("totalPrice"));
			LocalDate orderedDate = LocalDate.parse(req.getParameter("orderedDate"));
			Order order = new Order(
					req.getParameter("customerName"),
					req.getParameter("productName"),
					req.getParameter("orderedGoods"),
					totalPrice,
					orderedDate);
			
			new OrderJPADAO().save(order);
			session.setAttribute("flashOk_order", "Order saved.");
		} catch (Exception e) {
			getServletContext().log("Bakery order insert failed", e);
			session.setAttribute("flashError_order", "Could not save order: " + e.getMessage());
			resp.sendRedirect(req.getContextPath() + "/order");
			return;
		}
		resp.sendRedirect(req.getContextPath() + "/order");
	}
}
