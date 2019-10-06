package com.pradheep.dao.config;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.core.io.ClassPathResource;

public class ApplicationLogger {	
	
	private static org.apache.log4j.LogManager logManager = new org.apache.log4j.LogManager();	
	public static org.apache.log4j.Logger getLogBean(Class classObj){
		ClassPathResource resource = new ClassPathResource("log4j_dao.properties");
		try {		    
			PropertyConfigurator.configure(resource.getInputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    return logManager.getLogger(classObj);
	}	

}
