package com.bakery.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;

import com.bakery.dao.ProductJPADAO;
import com.bakery.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		try {
			req.setAttribute("productList", new ProductJPADAO().getAll());
		} catch (Exception e) {
			getServletContext().log("Bakery product list failed", e);
			req.setAttribute("productList", Collections.emptyList());
			req.setAttribute("listError", "Could not load menu items. Check DB connection and schema. (" + e.getMessage() + ")");
		}
		req.getRequestDispatcher("/product.jsp").forward(req, resp);
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
			int quantity = Integer.parseInt(req.getParameter("quantity"));
			double price = Double.parseDouble(req.getParameter("price"));
			LocalDate productionDate = LocalDate.parse(req.getParameter("productionDate"));
			Product product = new Product(req.getParameter("name"), quantity, price, productionDate);
			new ProductJPADAO().save(product);
			session.setAttribute("flashOk_product", "Bakery item saved.");
		} catch (Exception e) {
			getServletContext().log("Bakery product insert failed", e);
			session.setAttribute("flashError_product", "Could not save item: " + e.getMessage());
			resp.sendRedirect(req.getContextPath() + "/product");
			return;
		}
		resp.sendRedirect(req.getContextPath() + "/product");
	}
}
