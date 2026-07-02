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
		String editId = req.getParameter("edit");
		if (editId != null && !editId.isBlank()) {
			try {
				Order editOrder = new OrderJPADAO().findById(Integer.parseInt(editId));
				if (editOrder == null) {
					session.setAttribute("flashError_order", "Order not found.");
				} else {
					req.setAttribute("editOrder", editOrder);
				}
			} catch (Exception e) {
				getServletContext().log("Bakery order edit lookup failed", e);
			}
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
		String actionParam = req.getParameter("_action");
		String idParam = req.getParameter("id");

		if ("delete".equals(actionParam)) {
			try {
				new OrderJPADAO().delete(Integer.parseInt(idParam));
				session.setAttribute("flashOk_order", "Order deleted.");
			} catch (Exception e) {
				getServletContext().log("Bakery order delete failed", e);
				session.setAttribute("flashError_order", "Could not delete order: " + e.getMessage());
			}
			resp.sendRedirect(req.getContextPath() + "/order");
			return;
		}

		try {
			double totalPrice = Double.parseDouble(req.getParameter("totalPrice"));
			LocalDate orderedDate = LocalDate.parse(req.getParameter("orderedDate"));
			String customerName = req.getParameter("customerName");
			String productName = req.getParameter("productName");
			String orderedGoods = req.getParameter("orderedGoods");

			if (idParam != null && !idParam.isBlank()) {
				new OrderJPADAO().update(Integer.parseInt(idParam), customerName, productName, orderedGoods, totalPrice, orderedDate);
				session.setAttribute("flashOk_order", "Order updated.");
			} else {
				Order order = new Order(customerName, productName, orderedGoods, totalPrice, orderedDate);
				new OrderJPADAO().save(order);
				session.setAttribute("flashOk_order", "Order saved.");
			}
		} catch (Exception e) {
			getServletContext().log("Bakery order save failed", e);
			session.setAttribute("flashError_order", "Could not save order: " + e.getMessage());
			resp.sendRedirect(req.getContextPath() + "/order");
			return;
		}
		resp.sendRedirect(req.getContextPath() + "/order");
	}
}
