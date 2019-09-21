/**
 * 
 */
package com.pradheep.web.jobs;

import com.pradheep.dao.model.Subscription;
import com.pyr.notification.MessageObject;

/**
 * This class is to see the weekly status of the Web Application.
 * 
 * @author pradheep.p
 *
 */
public class WeeklyAlertsByEmailJob extends NotificationJob {
	
	
	@Override
	public void run() {
		
	}
	

	/* (non-Javadoc)
	 * @see com.pradheep.web.jobs.NotificationJob#notifyMessage()
	 */
	@Override
	public void notifyMessage() {		
		
	}

	@Override
	public MessageObject getMessageObject(Object messageObj) {
		// TODO Auto-generated method stub
		return null;
	}

}
