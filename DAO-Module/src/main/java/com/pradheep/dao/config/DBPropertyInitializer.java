/**
 * 
 */
package com.pradheep.dao.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;

/**
 * @author pradheep
 *
 */
public class DBPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	private HashMap propertiesMap = new HashMap<String, String>();

	private String basePath = "/home/pyr/properties/";

	private String dbPropertiesFile = new String("db_connectivity.properties");	

	private HashMap getProperties(String fileName) {
		Properties properties = new Properties();
		File file = new File(fileName);
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.println("Exception occured in getProperties() : FileNotFoundException ");
		}
		try {
			properties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (final String name : properties.stringPropertyNames()) {
			propertiesMap.put(name, properties.getProperty(name));
		}
		return propertiesMap;
	}

	public void initialize(ConfigurableApplicationContext applicationContext) {
		System.out.println("---------- Configuring the application properties --------");
		String fileName = basePath + dbPropertiesFile;
		System.out.println("Loading :" + fileName);
		getProperties(fileName);			
		applicationContext.getEnvironment().getPropertySources()
				.addLast(new MapPropertySource("application.properties", propertiesMap));
		
	}
}
