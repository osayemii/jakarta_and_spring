package com.lesson.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.*;

import java.io.IOException;
import java.util.Enumeration;

public class RequestLogging implements Filter {
	private ServletContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("RequestLoggingFilter initialised");
	}
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		Enumeration<String> params = request.getParameterNames();
		
		while (params.hasMoreElements()) {
			String name = params.nextElement();
			String value = req.getParameter(name);
			this.context.log(request.getRemoteAddr() + "::Request Params::{" + name + "=" + value + "}");
		}
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				this.context.log(request.getRemoteAddr() + "::Cookie::{" + cookie.getName() + "," + cookie.getValue() + "}");
			}
		}
		
		chain.doFilter(req, res);
	}
	
	public void destroy() { }
}