package com.rms.servlet;

import java.io.IOException;
import java.util.Collections;

import com.rms.dao.StaffDAO;
import com.rms.model.Staff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/staff")
public class StaffServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!AuthUtil.requireLogin(req, resp)) {
			return;
		}
		try {
			req.setAttribute("staffList", new StaffDAO().getAll());
		} catch (Exception e) {
			getServletContext().log("staff list", e);
			req.setAttribute("staffList", Collections.emptyList());
		}
		req.getRequestDispatcher("/staff.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!AuthUtil.requireLogin(req, resp)) {
			return;
		}
		req.setCharacterEncoding("UTF-8");
		try {
			int age = Integer.parseInt(AuthUtil.param(req, "age"));
			Staff staff = new Staff(
					AuthUtil.param(req, "name"),
					AuthUtil.param(req, "email"),
					age,
					AuthUtil.param(req, "phone"),
					AuthUtil.param(req, "address"));
			new StaffDAO().insert(staff);
		} catch (Exception e) {
			getServletContext().log("staff save", e);
		}
		resp.sendRedirect(req.getContextPath() + "/staff");
	}
}
