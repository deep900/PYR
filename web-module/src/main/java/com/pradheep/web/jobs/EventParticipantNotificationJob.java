/**
 * 
 */
package com.pradheep.web.jobs;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pradheep.dao.model.Message;
import com.pradheep.dao.model.event.EventModel;
import com.pradheep.dao.model.event.EventParticipants;
import com.pradheep.web.common.ApplicationConstants;
import com.pradheep.web.service.EventManagementService;
import com.pyr.notification.EmailMessageObject;
import com.pyr.notification.MessageObject;

/**
 * This class is to notify the event participants about the event happening.
 * 
 * It checks for the events and sends a reminder 48 hours before the event.
 * Sends reminder email to all the participants.
 * 
 * @author Pradheep
 *
 */
public class EventParticipantNotificationJob extends NotificationJob<Message> {

	/*
	 * Time delay between emails.
	 */
	private long timeDelayBetweenEmails = 10000l;

	/*
	 * Send notification if the event is occuring in the below threshold.
	 */
	private int hoursThresholdForEmailNotification = 48;

	private final static String PARTICIPANT_NAME = "${PARTICIPANT_NAME}";

	private final static String PARTICIPANT_ID = "${PARTICIPANT_ID}";

	public EventParticipantNotificationJob() {
		setNotificationType(NotificationService.NOTIFICATION_TYPE_EMAIL);
		setJobFrequency(JOB_FREQUENCY_DAILY_HRS);
	}

	@Autowired
	private EventManagementService eventManagementService;

	@Override
	public void run() {
		notifyMessage();
		getLogger().info(this.getClass().getSimpleName() + " - Completed at " + new Date());
	}

	@Override
	public void notifyMessage() {
		try {
			List<EventModel> events = eventManagementService.getAllActiveEvents();
			if (null != events && !events.isEmpty()) {
				Iterator<EventModel> iter = events.iterator();
				while (iter.hasNext()) {
					EventModel eventModel = iter.next();
					if (canNotifyEvent(eventModel)) {
						List<EventParticipants> participantList = eventManagementService
								.getAllParticipants(eventModel.getId());
						Iterator<EventParticipants> participantListIterator = participantList.iterator();
						while (participantListIterator.hasNext()) {
							EventParticipants eventParticipantsObj = participantListIterator.next();
							String participantEmail = eventParticipantsObj.getEmail();
							boolean emailReminded = eventParticipantsObj.isRemindedByEmail();
							if (!participantEmail.isEmpty() && !emailReminded) {
								boolean flag = (boolean) pryMessenger
										.communicate(getMsg(eventModel, eventParticipantsObj));
								if (flag) {
									eventParticipantsObj.setRemindedByEmail(true);
									daoService.saveOrUpdateEntity(eventParticipantsObj);
									getLogger().info("Email sent successfully, updated the user records");
								}
								try {
									Thread.currentThread().sleep(timeDelayBetweenEmails);
								} catch (InterruptedException err) {
									getLogger().error("Error while sleep between threads", err);
								}
							}
						}
					}
				}
			} else {
				getLogger().info("No events to nofity the participants.");
			}
		} catch (Exception err) {
			getLogger().error("Error while sending the notification", err);
		}
	}

	public MessageObject getMsg(EventModel event, EventParticipants eventParticipants) {
		EmailMessageObject messageObject = new EmailMessageObject();
		messageObject.setToList(new String[] { eventParticipants.getEmail() });
		if (event.getEmailRemainderTemplate() != null && !event.getEmailRemainderTemplate().isEmpty()) {
			getLogger().info("Printing the template:" + event.getEmailRemainderTemplate());
			String template = event.getEmailRemainderTemplate();
			template = template.replace(PARTICIPANT_NAME, eventParticipants.getName());
			template = template.replace(PARTICIPANT_ID, String.valueOf(eventParticipants.getId()));
			messageObject.setBodyOfMessage(template);
		} else {
			messageObject.setBodyOfMessage("Dear " + eventParticipants.getName() + ",<br> Thanks for registering for :"
					+ event.getEventName() + "<br><br> Your registration number is:" + eventParticipants.getId()
					+ " <br> Kindly participate in the event at: " + formatDate(event.getEventDateTime())
					+ "<br> Thank you<br>" + event.getOrganizer());
		}
		messageObject.setFromAddress(ApplicationConstants.FROM_EMAIL_ADDRESS);
		messageObject.setSubjectOfMessage("Event Reminder:" + event.getEventName());
		return messageObject;
	}

	@Override
	public MessageObject getMessageObject(Message messageObj) {
		return null;
	}

	/**
	 * Notify the event participants only once before 48 Hours of start of
	 * event.
	 * 
	 * @param event
	 * @return
	 */
	public boolean canNotifyEvent(EventModel event) {
		if (null == event) {
			getLogger().error("Event model cannot be null");
			return false;
		}
		getLogger().info("Printing the event " + event.toString());
		if (eventManagementService.isEventEnded(event)) {
			getLogger().info("Event already ended");
			return false;
		}
		int hourDiff = eventManagementService.getTimeDiffInHrsStartOfEvent(event);
		getLogger().info("Printing the time diff in hours" + hourDiff);
		if (hourDiff == -1) {
			return false;
		}
		if (hourDiff <= this.hoursThresholdForEmailNotification) {
			getLogger().info("The event is about to start in " + hourDiff + " hrs");
			return true;
		}
		getLogger().info(event.getEventName() + " Event cannot be notified at this moment");
		return false;
	}

	private String formatDate(Timestamp time) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, hh:mm aaa yyyy");
		Date obj = new Date(time.getTime());
		return sdf.format(obj);
	}

}
