/**
 * 
 */
package com.pradheep.web.jobs;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.pradheep.dao.model.NotificationsModel;
import com.pradheep.web.common.ApplicationConstants;
import com.pradheep.web.event.NotificationJobCompleteEvent;
import com.pyr.messenger.PyrMessenger;
import com.pyr.notification.EmailMessageObject;
import com.pyr.notification.MessageObject;
import com.pyr.templates.DailySMSMessageObject;

/**
 * @author pradheep.p
 *
 */
public class MonthlyMessageNotification extends PersistedNotificationJob {

	public MonthlyMessageNotification() {

	}

	@Override
	public void run() {
		notifyMessage();
	}

	@Override
	public void notifyMessage() {
		getLogger().info("About to notify the message");
		NotificationsModel nModel = getNotificationModel();
		if(nModel.getNotificationType().equals(String.valueOf(PyrMessenger.EMAIL_MESSAGE))) {		
			emailMessage();
			publishCompletedEvent();
		}
		else if (nModel.getNotificationType().equals(String.valueOf(PyrMessenger.SMS_MESSAGE))) {
			smsMessage();
			publishCompletedEvent();
		}
		else if (nModel.getNotificationType().equals(String.valueOf(PyrMessenger.BOTH))) {
			emailMessage();
			smsMessage();
			publishCompletedEvent();
		}
	}

	private void smsMessage() {
		DailySMSMessageObject template = new DailySMSMessageObject(getMessageToBeSent());
		template.setContactNumber("7401504728");
		template.setDestinationCountryName("india");
		smsMessenger.communicate(template);
	}

	private void emailMessage() {
		EmailMessageObject emailMessageObject = new EmailMessageObject();
		emailMessageObject.setSubjectOfMessage("This Month Message Posted - " + getMonthFormatted());
		String[] toList = new String[1];
		toList[0] = "deep90@gmail.com";
		emailMessageObject.setBodyOfMessage(getMessageToBeSent());
		emailMessageObject.setToList(toList);
		emailMessageObject.setFooterInformation("Kindly do not reply to this email.");
		emailMessageObject.setFromAddress("administrator@praiseyourredeemer.org");
		pryMessenger.sendEmailMessage(emailMessageObject);		
	}

	private NotificationsModel getNotificationModel() {
		return (NotificationsModel) daoService.getObjectsById(NotificationsModel.class, "id",
				String.valueOf(getPersistedNotificationJobId()));
	}

	private void publishCompletedEvent() {
		getLogger().info("Publishing the Notification Job Completed Event");
		NotificationJobCompleteEvent event = new NotificationJobCompleteEvent(this);
		event.setNotificationJobId(super.getPersistedNotificationJobId());
		event.setMessage(ApplicationConstants.NOTIFICATION_JOB_COMPLETE);
		getEventPublisher().publishEvent(event);
	}

	private String getMonthFormatted() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-YYYY");
		String month = sdf.format(new Date());
		return month;
	}

	private String getMessageToBeSent() {
		String messageId = getNotificationModel().getOtherInformation();
		String arg = "This month " + getMonthFormatted()
				+ " message has been posted, please check the link below \n www.praiseyourredeemer.org/messages/readMessage?id="
				+ messageId + "\n";
		arg = arg + "- PyR Ministry.";
		return arg;

	}

	@Override
	public MessageObject getMessageObject(Object messageObj) {
		return null;
	}

}
