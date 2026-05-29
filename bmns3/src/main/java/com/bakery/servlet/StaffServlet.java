package com.bakery.servlet;

import java.io.IOException;
import java.util.Collections;

import com.bakery.dao.StaffJPADAO;
import com.bakery.model.Staff;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class StaffServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		try {
			req.setAttribute("staffList", new StaffJPADAO().getAll());
		} catch (Exception e) {
			getServletContext().log("Bakery staff list failed", e);
			req.setAttribute("staffList", Collections.emptyList());
			req.setAttribute("listError", "Could not load staff. Check DB connection and schema. (" + e.getMessage() + ")");
		}
		req.getRequestDispatcher("/staff.jsp").forward(req, resp);
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
			int age = Integer.parseInt(req.getParameter("age"));
			Staff staff = new Staff(
					req.getParameter("name"),
					req.getParameter("email"),
					age,
					req.getParameter("phone"),
					req.getParameter("address"));
			new StaffJPADAO().save(staff);
			session.setAttribute("flashOk_staff", "Staff member saved.");
		} catch (Exception e) {
			getServletContext().log("Bakery staff insert failed", e);
			session.setAttribute("flashError_staff", "Could not save staff: " + e.getMessage());
			resp.sendRedirect(req.getContextPath() + "/staff");
			return;
		}
		resp.sendRedirect(req.getContextPath() + "/staff");
	}
}
