package com.pradheep.web.event;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.pradheep.dao.config.DAOService;
import com.pradheep.dao.model.Message;
import com.pradheep.dao.model.NotificationsModel;
import com.pradheep.dao.model.Subscription;
import com.pradheep.dao.model.event.EventParticipants;
import com.pradheep.web.common.ApplicationConstants;
import com.pradheep.web.common.ApplicationLoggerWeb;
import com.pradheep.web.common.PYRUtility;
import com.pradheep.web.common.event.NotifyEventRegistration;
import com.pradheep.web.jobs.PersistedNotificationService;
import com.pry.security.utility.PublicUtility;
import com.pyr.messenger.Messenger;
import com.pyr.messenger.MessengerDelegate;
import com.pyr.messenger.PyrMessenger;
import com.pyr.notification.EmailMessageObject;
import com.pyr.notification.NotificationFormat;
import com.pyr.templates.DailySMSMessageObject;
import com.pyr.templates.EmailWelcomeMessage;
import com.pyr.templates.ErrorMessageTemplate;
import com.pyr.templates.PyrTemplate;

public class EventHandler implements ApplicationListener<ApplicationEvent> {

	@Autowired
	private MessengerDelegate messengerDelegate;

	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Autowired
	private DAOService daoService;	
	
	@Autowired
	private PublicUtility publicUtility;
	
	@Autowired
	private PersistedNotificationService persistedNotificationService;

	private String getUnsubscribeKey(String email) {
		String arg = "";
		arg = arg + email + "," + new Date().toString();		
		String key = publicUtility.EncryptText(arg);
		return key;
	}

	private Logger logger = null;

	private Logger getLogger() {
		if (logger == null) {
			logger = ApplicationLoggerWeb.getLogBean(getClass());
		}
		return logger;
	}

	public void onApplicationEvent(ApplicationEvent arg0) {
		
		if (arg0.getClass().equals(SubscriptionNotificationEvent.class)) {
			getLogger().info("Inside application event handler..");
			SubscriptionNotificationEvent notificationEvent = (SubscriptionNotificationEvent) arg0;
			handleNewSubscription(notificationEvent);
		} else if (arg0.getClass().equals(ErrorNotificationEvent.class)) {
			Exception exception = (Exception) arg0.getSource();
			getLogger().error("Got an error event ", exception);
			handleErrorEvent(exception);
		} else if (arg0 instanceof NotificationJobCompleteEvent) {
			NotificationJobCompleteEvent completedEvent = (NotificationJobCompleteEvent) arg0;
			if (completedEvent.getMessage().equals(ApplicationConstants.NOTIFICATION_JOB_COMPLETE)) {
				getLogger().info("Recieved a notification job completed event");
				int notificationId = completedEvent.getNotificationJobId();
				NotificationsModel model = (NotificationsModel) daoService.getObjectsById(NotificationsModel.class,
						"id", String.valueOf(notificationId));
				model.setCompletedTime(new Date());
				model.setNotificationStatus(ApplicationConstants.NOTIFICATION_JOB_COMPLETE);
				daoService.saveOrUpdateEntity(model);
			}			
		}
		else if(arg0 instanceof NewMonthlyMessageEvent) {		
			getLogger().info("------------Received the event " + arg0.getClass().toString() + "----------");
			Message msgModel = (Message) arg0.getSource();
			NotificationsModel model = new NotificationsModel();
			model.setInterval(0l);
			model.setNotificationStatus(ApplicationConstants.NOTIFICATION_JOB_YET_TO_START);
			model.setOtherInformation(String.valueOf(msgModel.getId()));
			model.setRunnableClassName("monthlyMessageNotification");
			model.setStartTime(PYRUtility.getThreeDaysFromNow());
			model.setNotificationType(String.valueOf(PyrMessenger.BOTH));
			model.setRepeatNotification(false);
			daoService.saveOrUpdateEntity(model);
			persistedNotificationService.addNotificationModel(model);				
		}
		else if(arg0 instanceof NewUserEventRegistrationEvent) {
			getLogger().info("New user registered - received event.");
			EventParticipants eventParticipants = (EventParticipants) arg0.getSource();
			NotifyEventRegistration notifyEventRegistrationObj = this.persistedNotificationService.getApplicationContext().getBean(NotifyEventRegistration.class);
			notifyEventRegistrationObj.setEventParticipantsModel(eventParticipants);
			this.threadPoolTaskExecutor.execute(notifyEventRegistrationObj);
		}
	}

	
	private void handleErrorEvent(Exception err) {
		try {
			String emailAddress = "administrator@praiseyourredeemer.org";
			getLogger().info("Sending an error notification by email");
			String[] toList = new String[1];
			toList[0] = emailAddress;
			EmailMessageObject emailMessage = new EmailMessageObject();
			ErrorMessageTemplate template = new ErrorMessageTemplate(err);
			emailMessage.setFromAddress(emailAddress);
			emailMessage.setBodyOfMessage(template.getFormatString());
			emailMessage.setFormat(NotificationFormat.EMAIL_FORMAT);
			emailMessage.setSubjectOfMessage("A serious error occured - " + err.getMessage());
			emailMessage.setFooterInformation("");
			emailMessage.setToList(toList);
			boolean flag = (Boolean) messengerDelegate.communicateMessage(emailMessage, Messenger.EMAIL_MESSAGE);
			if (flag) {
				getLogger().info("Email sent successfully to :" + toList[0]);
			} else {
				getLogger().warn("Unable to email to :" + toList[0]);
			}
		} catch (Exception exp) {
			getLogger().error("Error in sending the welcome email", exp);
		}
	}

	private void handleNewSubscription(SubscriptionNotificationEvent subscriptionNotificationEvent) {
		Subscription subscription = (Subscription) subscriptionNotificationEvent.getSource();
		sendEmailNotificationToNewUser(subscription);
		if (subscriptionNotificationEvent.getEventType() == ApplicationConstants.NEW_USER_ADDED) {
			sendSMSNotificationToNewUser(subscription);
		} else {
			getLogger().info("SMS notification sent only to new users");
		}
	}

	private void sendEmailNotificationToNewUser(Subscription subscription) {
		Thread d = new Thread() {
			public void run() {
				try {
					getLogger().info("Trying to send an welcome email to :" + subscription.toString());
					String[] toList = new String[1];
					toList[0] = subscription.getEmailId();
					String email = toList[0];

					String[] bccList = new String[1];
					bccList[0] = "deep90@gmail.com";
					String contactDetails = "<br> Your Contact Number: " + subscription.getContactNumber()
							+ "<br> Your Preferred Language :" + subscription.getPreferredLanguage() + "<br>"
							+ "Country :" + subscription.getCountry();
					PyrTemplate template = new EmailWelcomeMessage("Dear " + subscription.getName(),
							"Yours <br> PYR Ministry", EventConstants.WELCOME_MESSAGE + contactDetails,
							getUnsubscribeKey(email));
					EmailMessageObject emailMessage = new EmailMessageObject();
					emailMessage.setFromAddress("administrator@praiseyourredeemer.org");
					emailMessage.setBodyOfMessage(template.getFormatString());
					emailMessage.setFormat(NotificationFormat.EMAIL_FORMAT);
					emailMessage.setSubjectOfMessage("Welcome to Praise Your Redeemer");
					emailMessage.setFooterInformation("");
					emailMessage.setToList(toList);
					emailMessage.setBccList(bccList);
					boolean flag = (Boolean) messengerDelegate.communicateMessage(emailMessage,
							Messenger.EMAIL_MESSAGE);
					if (flag) {
						getLogger().info("Email sent successfully to :" + toList[0]);
					} else {
						getLogger().warn("Unable to email to :" + toList[0]);
					}
				} catch (Exception err) {
					getLogger().error("Error in sending the welcome email", err);
				}
			}
		};
		d.setName("Send email notification");
		threadPoolTaskExecutor.execute(d);
	}

	private void sendSMSNotificationToNewUser(Subscription subscription) {
		Thread d = new Thread() {
			public void run() {
				try {
					getLogger().info("Trying to send an welcome SMS to :" + subscription.toString());
					DailySMSMessageObject template = new DailySMSMessageObject(
							"Welcome to PYR Ministry You will receive daily alerts on verses and daily bible study");
					template.setContactNumber(subscription.getContactNumber());
					template.setDestinationCountryName(subscription.getCountry());
					boolean flag = (Boolean) messengerDelegate.communicateMessage(template, Messenger.SMS_MESSAGE);
					if (flag) {
						getLogger().info("SMS sent successfully to :" + subscription.getContactNumber());
					} else {
						getLogger().warn("Unable to SMS to :" + subscription.getContactNumber());
					}
				} catch (Exception err) {
					getLogger().error("Error in sending the welcome SMS", err);
				}
			}
		};
		d.setName("Send email notification");
		threadPoolTaskExecutor.execute(d);
	}
	
	

}
