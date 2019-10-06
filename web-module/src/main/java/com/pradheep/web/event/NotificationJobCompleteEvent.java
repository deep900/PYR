/**
 * 
 */
package com.pradheep.web.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author pradheep.p
 *
 */
public class NotificationJobCompleteEvent extends ApplicationEvent {

	private String message;
	
	private Integer notificationJobId;
	
	public NotificationJobCompleteEvent(Object source) {
		super(source);		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getNotificationJobId() {
		return notificationJobId;
	}

	public void setNotificationJobId(Integer notificationJobId) {
		this.notificationJobId = notificationJobId;
	}	

}
