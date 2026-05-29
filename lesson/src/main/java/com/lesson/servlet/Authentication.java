package com.lesson.servlet;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class Authentication implements Filter {
	private ServletContext context;
	
	public void init (FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("Authentication Filter started");
	}
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String uri = request.getRequestURI();
		this.context.log("Requested Resource::" + uri);
		HttpSession session = request.getSession(false);
		
		if (session == null && !(uri.endsWith("html") || uri.endsWith("login"))) {
			this.context.log("Unauthorized");
			response.sendRedirect("Login.html");
		} else {
			chain.doFilter(req, res);
		}
	}
	
	public void destroy() { }
}