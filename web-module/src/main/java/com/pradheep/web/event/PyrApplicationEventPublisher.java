package com.pradheep.web.event;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.pradheep.web.common.ApplicationLoggerWeb;

public class PyrApplicationEventPublisher implements ApplicationEventPublisherAware {
	
	private ApplicationEventPublisher eventPublisher;
	
	private Logger logger = null;
	
	private Logger getLogger() {
		if (logger == null) {
			logger = ApplicationLoggerWeb.getLogBean(getClass());
		}
		return logger;
	}

	public void setApplicationEventPublisher(org.springframework.context.ApplicationEventPublisher arg0) {		
		this.eventPublisher = arg0;
	}
	
	public void publishEvent(ApplicationEvent event){
		getLogger().info("Publishing an event " + event.toString());
		eventPublisher.publishEvent(event);
	}	
	
}
