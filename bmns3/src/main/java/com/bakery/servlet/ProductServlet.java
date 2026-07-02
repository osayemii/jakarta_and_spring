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
		String editId = req.getParameter("edit");
		if (editId != null && !editId.isBlank()) {
			try {
				Product editProduct = new ProductJPADAO().findById(Integer.parseInt(editId));
				if (editProduct == null) {
					session.setAttribute("flashError_product", "Menu item not found.");
				} else {
					req.setAttribute("editProduct", editProduct);
				}
			} catch (Exception e) {
				getServletContext().log("Bakery product edit lookup failed", e);
			}
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
		String actionParam = req.getParameter("_action");
		String idParam = req.getParameter("id");

		if ("delete".equals(actionParam)) {
			try {
				new ProductJPADAO().delete(Integer.parseInt(idParam));
				session.setAttribute("flashOk_product", "Menu item deleted.");
			} catch (Exception e) {
				getServletContext().log("Bakery product delete failed", e);
				session.setAttribute("flashError_product", "Could not delete item: " + e.getMessage());
			}
			resp.sendRedirect(req.getContextPath() + "/product");
			return;
		}

		try {
			int quantity = Integer.parseInt(req.getParameter("quantity"));
			double price = Double.parseDouble(req.getParameter("price"));
			LocalDate productionDate = LocalDate.parse(req.getParameter("productionDate"));
			String name = req.getParameter("name");

			if (idParam != null && !idParam.isBlank()) {
				new ProductJPADAO().update(Integer.parseInt(idParam), name, quantity, price, productionDate);
				session.setAttribute("flashOk_product", "Menu item updated.");
			} else {
				Product product = new Product(name, quantity, price, productionDate);
				new ProductJPADAO().save(product);
				session.setAttribute("flashOk_product", "Bakery item saved.");
			}
		} catch (Exception e) {
			getServletContext().log("Bakery product save failed", e);
			session.setAttribute("flashError_product", "Could not save item: " + e.getMessage());
			resp.sendRedirect(req.getContextPath() + "/product");
			return;
		}
		resp.sendRedirect(req.getContextPath() + "/product");
	}
}
