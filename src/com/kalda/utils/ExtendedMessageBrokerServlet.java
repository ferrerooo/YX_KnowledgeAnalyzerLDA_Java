package com.kalda.utils;

import flex.messaging.MessageBrokerServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;

public class ExtendedMessageBrokerServlet extends MessageBrokerServlet {
	
	private static final long serialVersionUID = 1762369691490551212L;
	
	public void init(ServletConfig servletConfig)
			throws ServletException, UnavailableException
		{
			super.init(servletConfig);
		}
	
	protected String getFlexWritePath(ServletConfig servletConfig) {		
		String flexWritePath = null;
		
		try {
			//flexWritePath = super.getFlexWritePath(servletConfig);
		} catch(Exception e) {
			if( flexWritePath == null )
				flexWritePath = System.getProperty("java.io.tmpdir");
		}
		
		return flexWritePath;
	}

}
