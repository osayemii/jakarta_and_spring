package com.classwork2.servlet;

import java.io.*;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(
    value = "/displayProfiles",
    asyncSupported = true
)
public class DisplayProfilesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");

        AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(120000);

        asyncContext.start(() -> {
            try {
                HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();

                String uploadPath = getServletContext().getRealPath("") + File.separator + "employee_profiles";
                File profilesDir = new File(uploadPath);
                File[] profileFiles = profilesDir.listFiles((dir, name) ->
                    name.toLowerCase().endsWith(".pdf"));

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                PrintWriter out = response.getWriter();
                StringBuilder json = new StringBuilder();
                json.append("[");

                if (profileFiles != null) {
                    for (int i = 0; i < profileFiles.length; i++) {
                        File pdf = profileFiles[i];
                        json.append("\"").append(pdf.getName()).append("\"");
                        if (i < profileFiles.length - 1) {
                            json.append(",");
                        }
                    }
                }

                json.append("]");
                out.print(json.toString());
                out.flush();
                asyncContext.complete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
