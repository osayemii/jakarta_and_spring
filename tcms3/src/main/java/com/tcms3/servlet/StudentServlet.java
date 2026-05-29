package com.tcms3.servlet;

import com.tcms3.model.Student;
import com.tcms3.dao.StudentJPADAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.*;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

//    private List<Student> students = new ArrayList<>();

    private StudentJPADAO dao = new StudentJPADAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            req.setAttribute("students", dao.getAll());
        } catch (Exception e) {
            throw new ServletException(e);
        }

        req.getRequestDispatcher("students.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");

        try {
            dao.save(new Student(name, email));
        } catch (Exception e) {
            e.printStackTrace();
        }

        res.sendRedirect("students");
    }
}