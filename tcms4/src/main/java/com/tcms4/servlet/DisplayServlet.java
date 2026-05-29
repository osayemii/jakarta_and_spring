package com.tcms4.servlet;

import java.io.*;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(
    value="/display",
    asyncSupported=true
)
public class DisplayServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");

        AsyncContext asyncContext = req.startAsync();

        asyncContext.setTimeout(120000);

        asyncContext.start(() -> {
            try {
                HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();
                
                File reportsDir = new File("C:\\Users\\DANIEL\\eclipse-workspace\\tcms4\\src\\main\\webapp\\reports");
                File[] reportFiles = reportsDir.listFiles((dir, name) ->
                       name.toLowerCase().endsWith(".pdf"));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                PrintWriter out = response.getWriter();

                StringBuilder json = new StringBuilder();
                json.append("[");

                if (reportFiles != null) {

                    for (int i = 0; i < reportFiles.length; i++) {

                        File pdf = reportFiles[i];

                        json.append("\"")
                            .append(pdf.getName())
                            .append("\"");

                        if (i < reportFiles.length - 1) {
                            json.append(",");
                        }
                    }
                }

                json.append("]");

                out.print(json.toString());
                out.flush();


                asyncContext.complete();

            } catch(Exception e) {
                e.printStackTrace();
            }
        });
    }
}
