package com.tcms3.servlet;

import com.tcms3.model.Teacher;
import com.tcms3.dao.TeacherJPADAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.*;

@WebServlet("/teachers")
public class TeacherServlet extends HttpServlet {

//    private List<Student> students = new ArrayList<>();

    private TeacherJPADAO dao = new TeacherJPADAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            req.setAttribute("teachers", dao.getAll());
        } catch (Exception e) {
            throw new ServletException(e);
        }

        req.getRequestDispatcher("teachers.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");

        try {
            dao.save(new Teacher(name, email));
        } catch (Exception e) {
            e.printStackTrace();
        }

        res.sendRedirect("teachers");
    }
}