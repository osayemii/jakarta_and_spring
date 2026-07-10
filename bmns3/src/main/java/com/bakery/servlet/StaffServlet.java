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
		
		String editId = req.getParameter("edit");
		if (editId != null && !editId.isBlank()) {
			try {
				Staff editStaff = new StaffJPADAO().findById(Integer.parseInt(editId));
				if (editStaff == null) {
					session.setAttribute("flashError_staff", "Staff member not found.");
				} else {
					req.setAttribute("editStaff", editStaff);
				}
			} catch (Exception e) {
				getServletContext().log("Bakery staff edit lookup failed", e);
			}
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
		String actionParam = req.getParameter("_action");
		String idParam = req.getParameter("id");

		if ("delete".equals(actionParam)) {
			try {
				new StaffJPADAO().delete(Integer.parseInt(idParam));
				session.setAttribute("flashOk_staff", "Staff member deleted.");
			} catch (Exception e) {
				getServletContext().log("Bakery staff delete failed", e);
				session.setAttribute("flashError_staff", "Could not delete staff: " + e.getMessage());
			}
			resp.sendRedirect(req.getContextPath() + "/staff");
			return;
		}

		try {
			int age = Integer.parseInt(req.getParameter("age"));
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			String address = req.getParameter("address");

			if (idParam != null && !idParam.isBlank()) {
				new StaffJPADAO().update(Integer.parseInt(idParam), name, email, age, phone, address);
				session.setAttribute("flashOk_staff", "Staff member updated.");
			} else {
				Staff staff = new Staff(name, email, age, phone, address);
				new StaffJPADAO().save(staff);
				session.setAttribute("flashOk_staff", "Staff member saved.");
			}
		} catch (Exception e) {
			getServletContext().log("Bakery staff save failed", e);
			session.setAttribute("flashError_staff", "Could not save staff: " + e.getMessage());
			resp.sendRedirect(req.getContextPath() + "/staff");
			return;
		}
		resp.sendRedirect(req.getContextPath() + "/staff");
	}
}
