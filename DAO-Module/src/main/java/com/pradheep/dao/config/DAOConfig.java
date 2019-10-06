/**
 * 
 */
package com.pradheep.dao.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.pradheep.dao.model.BibleQuizEng;
import com.pradheep.data.util.BibleQuizDataMigration;
import com.pry.security.utility.PublicUtility;
import com.pry.security.utility.SecurityConfiguration;

/**
 * @author pradheep.p
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.pradheep.dao*" })
@Import({ SecurityConfiguration.class })
public class DAOConfig {

	@Autowired
	private PublicUtility publicUtility;
	
	private String basePath = "/home/pyr/properties/";	

	/*
	 * Using dbcp db connection to establish a persistant relationship. Changed
	 * 29-Aug-2017
	 */
	@Bean(name = "sessionFactory")
	public AnnotationSessionFactoryBean getSessionFactory() {
		AnnotationSessionFactoryBean sessionFactory = new org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.pradheep.dao.model" });
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}

	private void loadPropertiesFromFile(Properties prop, String filePathString) {
		System.out.println("Loading the properties from file " + filePathString);
		try {
			prop.load(new FileInputStream(basePath + filePathString));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error while loading the properties");
		}
		System.out.println("Properties loading complete");
	}

	@Bean(name = "dataSource")
	public org.apache.commons.dbcp.BasicDataSource getDataSource() {
		Properties prop = new Properties();
		loadPropertiesFromFile(prop, "db_connectivity.properties");
		org.apache.commons.dbcp.BasicDataSource dataSource = new org.apache.commons.dbcp.BasicDataSource();
		dataSource.setUrl(prop.getProperty("url"));
		dataSource.setUsername(prop.getProperty("user"));
		dataSource.setDriverClassName(prop.getProperty("driverClassName"));
		dataSource.setPassword(publicUtility.DecryptText(prop.getProperty("password")));
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

	@Bean(name = "transactionManager")
	public org.springframework.jdbc.datasource.DataSourceTransactionManager getTransactionManager() {
		org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager = new org.springframework.jdbc.datasource.DataSourceTransactionManager();
		transactionManager.setDataSource(getDataSource());
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(getDataSource());
		em.setPackagesToScan(new String[] { "com.pradheep.dao.model" });
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}

	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return properties;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public BibleQuizDataMigration dataMigrator() {
		BibleQuizDataMigration bibleQuizDataMigration = new BibleQuizDataMigration();
		return bibleQuizDataMigration;
	}

	@Bean
	public DBPropertyInitializer getPropertySourceInitializer() {
		DBPropertyInitializer propertySourceInitializer = new DBPropertyInitializer();
		return propertySourceInitializer;
	}

}
