package com.pradheep.web.common;



import org.apache.log4j.PropertyConfigurator;
import org.springframework.core.io.ClassPathResource;

public class ApplicationLoggerWeb {	
	
	private static org.apache.log4j.LogManager logManager = new org.apache.log4j.LogManager();	
	public static org.apache.log4j.Logger getLogBean(Class classObj){
		ClassPathResource resource = new ClassPathResource("log4j_web.properties");
		try {		    
			PropertyConfigurator.configure(resource.getInputStream());
		} catch (Exception e) {			
			e.printStackTrace();
		}		
    return logManager.getLogger(classObj);
	}	

}
