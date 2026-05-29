package com.tcms3.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ("admin".equals(username) && "1234".equals(password)) {

            HttpSession session = req.getSession();
            session.setAttribute("user", username);

            res.sendRedirect("dashboard.jsp");

        } else {
            req.setAttribute("error", "Invalid login");
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}