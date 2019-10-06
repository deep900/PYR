/**
 * 
 */
package com.pradheep.web.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.pradheep.web.event.PyrApplicationEventPublisher;

/**
 * @author pradheep.p
 *
 */
public abstract class PersistedNotificationJob extends NotificationJob {
	
	@Autowired
    protected PyrApplicationEventPublisher applicationEventPublisher;

	private int persistedNotificationJobId;

	public int getPersistedNotificationJobId() {
		return persistedNotificationJobId;
	}

	public void setPersistedNotificationJobId(int persistedNotificationJobId) {
		this.persistedNotificationJobId = persistedNotificationJobId;
	}
	
	protected PyrApplicationEventPublisher getEventPublisher() {
		return applicationEventPublisher;
	}

}
