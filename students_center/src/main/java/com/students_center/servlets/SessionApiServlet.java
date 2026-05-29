package com.students_center.servlets;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.json.*;
import java.io.IOException;
@SuppressWarnings("serial")
@WebServlet("/api/session")
public class SessionApiServlet extends HttpServlet {
   protected void doGet(HttpServletRequest req, HttpServletResponse res)
           throws IOException {
       HttpSession session = req.getSession(false);
       res.setContentType("application/json");
       JsonObject json = Json.createObjectBuilder()
               .add("loggedIn", session != null)
               .add("user", session != null ? (String) session.getAttribute("user") : "")
               .build();
       res.getWriter().write(json.toString());
   }
}
