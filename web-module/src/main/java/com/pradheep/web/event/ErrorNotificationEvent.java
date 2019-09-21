/**
 * 
 */
package com.pradheep.web.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author pradheep.p
 *
 */
public class ErrorNotificationEvent extends ApplicationEvent {

	private Exception exception;
	
	public ErrorNotificationEvent(Exception source) {
		super(source);
		this.exception = source;
	}
	
	public String toString(){
		return exception.getMessage();
	}
	

}
