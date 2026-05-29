package com.students_center.servlets;

import com.students_center.model.Student;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.*;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
	private List<Student> students = new ArrayList<>();
	
	protected void doGet (HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setAttribute("students", students);
		req.getRequestDispatcher("Students.jsp").forward(req, res);
	}
	
	protected void doPost (HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		students.add(new Student(name, email));
		
		ServletContext ctx = getServletContext();
		int total = (int) ctx.getAttribute("totalStudents");
		ctx.setAttribute("totalStudents", total + 1);
		
		res.sendRedirect("students");
	}
}