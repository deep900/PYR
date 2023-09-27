/**
 * 
 */
package com.pradheep.web.common.event;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pradheep.dao.config.ApplicationLogger;
import com.pradheep.dao.config.DAOService;
import com.pradheep.dao.model.event.EventModel;
import com.pradheep.dao.model.event.EventParticipants;
import com.pyr.messenger.Messenger;
import com.pyr.messenger.MessengerDelegate;
import com.pyr.notification.EmailMessageObject;
import com.pyr.notification.NotificationFormat;

/**
 * Sends email to the event administrators upon a event registration.
 * 
 * @author Pradheep
 *
 */
@Component
public class NotifyEventRegistration implements Runnable {

	private EventParticipants eventParticipants;

	private Logger logger;

	@Autowired
	private MessengerDelegate messengerDelegate;

	@Autowired
	private DAOService daoService;

	public void setEventParticipantsModel(EventParticipants eventParticipants) {
		this.eventParticipants = eventParticipants;
	}

	public EventModel getEvent(int eventId) {
		EventModel eventObj = (EventModel) daoService.getObjectsById(EventModel.class, "Id", String.valueOf(eventId));
		return eventObj;
	}

	private String getRandomName() {
		UUID uuid = new UUID(128, 64);
		return (String) uuid.randomUUID().toString().subSequence(0, 6);
	}

	@Override
	public void run() {
		Thread.currentThread().setName(this.getClass().getSimpleName() + "_" + getRandomName());
		getLogger().info("About to send email.: " + this.eventParticipants.getName());
		try {
			EventModel eventModel = getEvent(this.eventParticipants.getEventId());
			getLogger().info("Printing the event model" + eventModel.toString());			
			String[] toList = eventModel.getEventAdministratorEmail().split(",");			
			String[] bccList = new String[1];
			bccList[0] = "deep90@gmail.com";
			EmailMessageObject emailMessage = new EmailMessageObject();
			emailMessage.setFromAddress("administrator@praiseyourredeemer.org");
			emailMessage.setBodyOfMessage(createEmailMessage());
			emailMessage.setFormat(NotificationFormat.EMAIL_FORMAT);
			emailMessage.setSubjectOfMessage("New User Registered - " + this.eventParticipants.getName());
			emailMessage.setFooterInformation("");
			emailMessage.setToList(toList);
			emailMessage.setBccList(bccList);
			boolean flag = (Boolean) messengerDelegate.communicateMessage(emailMessage, Messenger.EMAIL_MESSAGE);
			if (flag) {
				getLogger().info("Email sent successfully to :" + toList[0]);
			} else {
				getLogger().warn("Unable to email to :" + toList[0]);
			}
		} catch (Exception err) {
			getLogger().error("Error in sending the welcome email", err);
		}
	}

	public String createEmailMessage() {
		return "Dear Administrator,<br> Please note that a new user ," + this.eventParticipants.getName()
				+ " has registered for the event." + "<br> Contact Number" + this.eventParticipants.getMobileNumber()
				+ "<br> Email:" + this.eventParticipants.getEmail() + "<br>Accompanying Adult Count:"
				+ this.eventParticipants.getAdultCount() + "<br>Accompanying Child Count:"
				+ this.eventParticipants.getChildCount() + "<br>Registered Time: "
				+ getRegisteredTime() + "<br><br> - Administrator";
	}

	private String getRegisteredTime() {
		Date date = new Date();		
		String formattedDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
		return formattedDate;
	}

	private Logger getLogger() {
		if (logger == null) {
			return ApplicationLogger.getLogBean(getClass());
		}
		return logger;
	}
}
