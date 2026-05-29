package com.classwork2.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet(
    value = "/uploadProgress",
    asyncSupported = true
)
public class UploadProgressServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");

        AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(120000);

        asyncContext.start(() -> {
            try {
                HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();
                PrintWriter out = response.getWriter();

                for (int i = 0; i <= 10; i++) {
                    Thread.sleep(1000);
                    int percent = i * 10;
                    out.print(percent);
                    out.flush();
                    response.flushBuffer();
                }

                asyncContext.complete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
