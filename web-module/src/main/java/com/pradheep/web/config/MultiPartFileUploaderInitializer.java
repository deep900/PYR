package com.pradheep.web.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MultiPartFileUploaderInitializer implements ApplicationContextAware, InitializingBean {

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("Initializing MultiPart File Uploader Bean..");
		CommonsMultipartResolver commonsMultipartResolver = (CommonsMultipartResolver) applicationContext.getBean("multipartResolver");
		System.out.println(commonsMultipartResolver.toString());
	}

}
