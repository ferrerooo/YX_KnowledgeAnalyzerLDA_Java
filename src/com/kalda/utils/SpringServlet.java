package com.kalda.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1762369691490551212L;
	
    public void init() throws ServletException {
    	ApplicationContext webContext = ApplicationContext.getInstance();
    	WebApplicationContext context
    	    = WebApplicationContextUtils
    		  .getWebApplicationContext(getServletContext());
    	webContext.setContext(context);
        }

}
