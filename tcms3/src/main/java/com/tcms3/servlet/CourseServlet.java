package com.tcms3.servlet;

import com.tcms3.dao.CourseJPADAO;
import com.tcms3.model.Course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet{
	private CourseJPADAO dao = new CourseJPADAO();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String name = req.getParameter("name");
		String department = req.getParameter("department");

		try {
			dao.save(new Course(name, department));
		} catch (Exception e) {
			e.printStackTrace();
		}

		res.sendRedirect(req.getContextPath() + "/courses");
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		try {
			req.setAttribute("courses", dao.getAll());
		} catch (Exception e) {
			throw new ServletException(e);
		}

		req.getRequestDispatcher("courses.jsp").forward(req, res);
	}
}
