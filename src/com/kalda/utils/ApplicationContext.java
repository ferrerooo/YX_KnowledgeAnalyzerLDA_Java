
package com.kalda.utils;

import java.io.Serializable;
import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;

public class ApplicationContext implements Serializable
{

	private static final long serialVersionUID = 1762369691490551212L;
	
    private static ApplicationContext webContext = new ApplicationContext();
    
    private WebApplicationContext context = null;

	private ApplicationContext()
	{
		
	}
	public static ApplicationContext getInstance(){
		return webContext;
	}
    public Object getBean(String beanName){
    	
    	try{
    		return context.getBean(beanName);
    	}catch(BeansException ex){
    		throw new RuntimeException(ErrorMsg.FAIL_TO_LOAD_BEAN);	
    	}
    }
    public WebApplicationContext getContext(){
    	return context;
    }
    public void setContext(WebApplicationContext value){
    	context = value;
    }
	
}
