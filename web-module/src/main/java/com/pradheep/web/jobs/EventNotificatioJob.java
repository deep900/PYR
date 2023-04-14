/**
 * 
 */
package com.pradheep.web.jobs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pradheep.dao.model.event.EventModel;
import com.pradheep.dao.model.event.EventParticipants;
import com.pradheep.web.common.event.EventManager;
import com.pyr.notification.EmailMessageObject;
import com.pyr.notification.MessageObject;

/**
 * @author Pradheep This job is to send the email notification to the event
 *         administrator about the participants of the event. The email will
 *         have the details of the participants who have registered to this
 *         event. This job will end once the event is completed.
 *
 */
public class EventNotificatioJob extends NotificationJob {

	private SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");

	private SimpleDateFormat currentDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private EventManager eventManager;

	@Override
	public void run() {

	}

	public String writeParticipantsToFile(int eventId) {
		String comma = ",";
		StringBuffer header = new StringBuffer("EventId,Event Name,Participant Name,contact,email,Food preference,Person invited,Child count,Adult Count");
		for (int i = 1; i <= 10; i++) {
			if (i < 10) {
				header.append(comma  + "Child " + i + " Name" + comma);
				header.append( comma + "Child " + i + " Food Preference" + comma);
				header.append( comma + "Adult " + i + " Name" + comma);
				header.append( comma + "Adult " + i + " Food Preference" + comma);
			} else {
				header.append(comma + "Child " + i + " Name");
				header.append( comma + "Child " + i + " Food Preference");
				header.append( comma + "Adult " + i + " Name");
				header.append( comma + "Adult " + i + " Food Preference");
			}
		}
		EventModel event = eventManager.getEventById(String.valueOf(eventId));
		String eventName = event.getEventName();
		List<EventParticipants> participants = eventManager.getParticipants(eventId);
		Iterator<EventParticipants> iter = participants.iterator();
		while(iter.hasNext()) {
			String line = "";
			EventParticipants obj = iter.next();
			//line = line + eventId + "," + eventName + "," + obj.getName() + "," + obj 
		}
		return "";
	}	

	@Override
	public void notifyMessage() {

	}

	@Override
	public MessageObject getMessageObject(Object messageObj) {
		EmailMessageObject messageObject = new EmailMessageObject();
		messageObject.setBodyOfMessage("Participants registered for this event");
		//messageObject.setToList(new String[] { subscriptionObj.getEmailId() });
		messageObject.setFromAddress("administrator@praiseyourredeemer.org");
		messageObject.setSubjectOfMessage("Event registered." + getEmailDate());
		return null;
	}

	private String getEmailDate() {
		return sdf.format(new Date());
	}

	private String getTodayDate() {
		return currentDateFormat.format(new Date());
	}

}
