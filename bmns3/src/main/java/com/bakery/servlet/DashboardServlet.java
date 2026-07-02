package com.bakery.servlet;

import java.io.IOException;

import com.bakery.dao.OrderJPADAO;
import com.bakery.dao.ProductJPADAO;
import com.bakery.dao.StaffJPADAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DashboardServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		try {
			req.setAttribute("staffCount", new StaffJPADAO().count());
			req.setAttribute("productCount", new ProductJPADAO().count());
			req.setAttribute("orderCount", new OrderJPADAO().count());
			req.setAttribute("revenue", new OrderJPADAO().totalRevenue());
		} catch (Exception e) {
			getServletContext().log("Bakery dashboard stats failed", e);
			req.setAttribute("statsError", "Could not load stats. Check DB connection and schema. (" + e.getMessage() + ")");
		}
		req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
	}
}
