package com.students_center.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        Part filePart = req.getPart("file");
        String fileName = filePart.getSubmittedFileName();

        filePart.write("C:/codes/uploads/" + fileName);

        res.getWriter().println("Uploaded: " + fileName);
    }
}
