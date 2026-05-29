package com.jemimahRecipe.servlet;

import java.io.IOException;

import com.jemimahRecipe.dao.RecipeDAO;
import com.jemimahRecipe.model.Recipe;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/jemimahRecipe")
public class jemimahRecipe extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		try {
			String name = req.getParameter("name");
			String description = req.getParameter("description");
			
			Recipe recipe = new Recipe(name, description);
			
			new RecipeDAO().insert(recipe);
		} catch (Exception e) {
			req.setAttribute("error", e);
			req.getRequestDispatcher("/jemimahRecipe.jsp").forward(req, res);
			return;
		}
		res.sendRedirect(req.getContextPath() + "/jemimahRecipe");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			req.setAttribute("orderlist", new RecipeDAO().getAll());
			req.getRequestDispatcher("/jemimahRecipe.jsp").forward(req, res);
			return;
		} catch (Exception e) {
			req.setAttribute("error", e);
			req.getRequestDispatcher("/jemimahRecipe.jsp").forward(req, res);
			return;
		}
	}
}