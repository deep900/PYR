/**
 * 
 */
package com.pradheep.dao.config;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.HibernateException;
import org.hibernate.connection.DatasourceConnectionProvider;
import org.springframework.core.io.ClassPathResource;

import com.mysql.jdbc.Connection;

/**
 * @author pradheep.p
 *
 */
public class CustomConnectionProvider extends DatasourceConnectionProvider{
	
	public CustomConnectionProvider(){
		
	}
	
	@Override
	public void configure(Properties prop) throws HibernateException{		
        setDataSource(getDataSource());
        super.configure(prop);
	}
	
	@Override
	public java.sql.Connection getConnection() throws SQLException{
		return getDataSource().getConnection();
	}
	
	private void loadPropertiesFromFile(Properties prop, String filePathString) {
		System.out.println("Loading the properties from file " + filePathString);
		try {
			ClassPathResource resource = new ClassPathResource(filePathString);
			if (resource != null) {
				if (prop == null) {
					System.out.println("Properties cannot be null .. Loading properties failed.");
					return;
				} else {
					try {
						prop.load(resource.getInputStream());
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("Error while loading the properties");
					}
					System.out.println("Properties loading complete");
				}
			}
		} catch (Exception err) {
			System.out.println("Error in loading the class path resource");
			err.printStackTrace();
		}
	}
	
	public org.apache.commons.dbcp.BasicDataSource getDataSource() {
		Properties prop = new Properties();
		loadPropertiesFromFile(prop, "db_connectivity.properties");
		org.apache.commons.dbcp.BasicDataSource dataSource = new org.apache.commons.dbcp.BasicDataSource();
		dataSource.setUrl(prop.getProperty("url"));
		dataSource.setUsername(prop.getProperty("user"));
		dataSource.setDriverClassName(prop.getProperty("driverClassName"));
		dataSource.setPassword(prop.getProperty("password"));
		System.out.println("Printing the validation query : " + prop.getProperty("validationQuery"));
		dataSource.setValidationQuery(prop.getProperty("validationQuery"));

		if (prop.getProperty("testWhileIdle").equalsIgnoreCase("true")) {
			dataSource.setTestWhileIdle(true);
		} else {
			dataSource.setTestWhileIdle(false);
		}
		if (prop.getProperty("testOnBorrow").equalsIgnoreCase("true")) {
			dataSource.setTestOnBorrow(true);
		} else {
			dataSource.setTestOnBorrow(false);
		}
		if (prop.getProperty("testWhileIdle").equalsIgnoreCase("true")) {
			dataSource.setTestWhileIdle(true);
		} else {
			dataSource.setTestWhileIdle(false);
		}
		if (dataSource.isClosed()) {
			System.out.println("Datasource is closed");
		} else {
			System.out.println("Datasource is open");
		}
		return dataSource;
	}
	
}
