/**
 * 
 */
package com.pradheep.web.event;

import org.springframework.context.ApplicationEvent;

import com.pradheep.dao.model.Subscription;

/**
 * @author pradheep.p
 *
 */
public class SubscriptionNotificationEvent extends ApplicationEvent {

	private Subscription subscriptionObj;
	
	private int eventType;

	public SubscriptionNotificationEvent(Subscription source) {
		super(source);
		this.subscriptionObj = source;
	}

	@Override
	public String toString() {
		return subscriptionObj.getName() + "," + subscriptionObj.getEmailId();
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

}
