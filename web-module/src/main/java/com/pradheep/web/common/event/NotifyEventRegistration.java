/**
 * 
 */
package com.pradheep.web.common.event;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pradheep.dao.config.ApplicationLogger;
import com.pradheep.dao.config.DAOService;
import com.pradheep.dao.model.event.EventModel;
import com.pradheep.dao.model.event.EventParticipants;
import com.pradheep.dao.model.event.EventParticipantsMembers;
import com.pyr.messenger.Messenger;
import com.pyr.messenger.MessengerDelegate;
import com.pyr.notification.EmailMessageObject;
import com.pyr.notification.NotificationFormat;

/**
 * This program is to send the welcome email to the member who is registering to
 * the event.
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

	@Autowired
	private ChurchEventWelcomeEmail welcomeEmailAttachmentUtility;

	public void setEventParticipantsModel(EventParticipants eventParticipants) {
		this.eventParticipants = eventParticipants;
	}

	public EventModel getEvent(int eventId) {
		EventModel eventObj = (EventModel) daoService.getObjectsById(EventModel.class, "Id", String.valueOf(eventId));
		return eventObj;
	}

	public List<Object> getMembersByParticipantId(int participantId) {
		List<Object> eventParticipantsMembersList = daoService.getObjectsListById(EventParticipantsMembers.class,
				"participantId", participantId, "=", -1);
		return eventParticipantsMembersList;
	}

	private String getRandomName() {
		UUID uuid = new UUID(128, 64);
		return (String) uuid.randomUUID().toString().subSequence(0, 6);
	}

	@Override
	public void run() {
		Thread.currentThread().setName(this.getClass().getSimpleName() + "_" + getRandomName());
		try {
			Thread.currentThread().sleep(1000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		getLogger().info("About to send email.: " + this.eventParticipants.getName());
		File attachment = null;
		try {
			EventModel eventModel = getEvent(this.eventParticipants.getEventId());
			getLogger().info("Printing the event model" + eventModel.toString());
			String[] toList = { this.eventParticipants.getEmail() };
			EmailMessageObject emailMessage = new EmailMessageObject();
			emailMessage.setFromAddress("administrator@praiseyourredeemer.org");
			emailMessage.setBodyOfMessage(createEmailMessage(eventModel));
			emailMessage.setFormat(NotificationFormat.EMAIL_FORMAT);
			emailMessage.setSubjectOfMessage("Thanks for event registration - " + this.eventParticipants.getName());
			emailMessage.setFooterInformation("");
			emailMessage.setToList(toList);
			attachment = getWelcomeAttachment(eventModel.getOrganizer() + "\n\n" + eventModel.getEventName()
					+ "\n\n Timing :" + parseDate(eventModel.getEventDateTime().toString()) + "\n\n" + "Location:"
					+ eventModel.getEventLocation() + "\n\n" + "(Use the event passes below while attending the event.)",
					eventModel.getEventName());
			emailMessage.setAttachment(attachment);
			boolean flag = (Boolean) messengerDelegate.communicateMessage(emailMessage, Messenger.EMAIL_MESSAGE);
			if (flag) {
				getLogger().info("Email sent successfully to :" + toList[0]);
			} else {
				getLogger().warn("Unable to email to :" + toList[0]);
			}
			if (null != attachment) {
				Thread.currentThread().sleep(1500l);
				System.out.println("About to delete file:" + attachment.getAbsolutePath());
				attachment.delete();
			}
		} catch (Exception err) {
			getLogger().error("Error in sending the welcome email", err);
		}
	}

	public String createEmailMessage(EventModel eventModel) {
		String template = eventModel.getWelcomeEmailTemplate();
		template = template.replace("#member_name#", this.eventParticipants.getName());
		template = template.replace("#reg_code#", "<b>" + String.valueOf(this.eventParticipants.getId()) + "</b> ");
		template = template.replace("#image_ref#",
				"<img src='https://www.praiseyourredeemer.org/resources/images/flyer1.jpg' style='width:50%' />");
		return template;
	}

	private Logger getLogger() {
		if (logger == null) {
			return ApplicationLogger.getLogBean(getClass());
		}
		return logger;
	}

	private String parseDate(String inputDate) {
		SimpleDateFormat outputSDF = new SimpleDateFormat("EEE, d MMM yyyy 'at' hh:mm aaa");
		SimpleDateFormat inputSDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date eventDate = inputSDF.parse(inputDate);
			return outputSDF.format(eventDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return inputDate;
	}

	private File getWelcomeAttachment(String title, String fileName) {
		try {
			return welcomeEmailAttachmentUtility.getWelcomeEmailAttachment(getEmailAttachmentContent(), title,
					fileName);
		} catch (Exception err) {
			err.printStackTrace();
			return null;
		}
	}

	private ParticipantInformation getParticipantInformation(String name, String id, String email,
			String mobileNumber) {
		ParticipantInformation participantInformation = new ParticipantInformation();
		participantInformation.setEmail(email);
		participantInformation.setId(id);
		participantInformation.setMobile(mobileNumber);
		participantInformation.setParticipantName(name);
		return participantInformation;
	}

	private List<ParticipantInformation> getEmailAttachmentContent() {
		List<ParticipantInformation> participantInformationList = new ArrayList<ParticipantInformation>();
		final String email = this.eventParticipants.getEmail();
		final String mobile = this.eventParticipants.getMobileNumber();
		final Integer id = this.eventParticipants.getId();
		participantInformationList.add(getParticipantInformation(this.eventParticipants.getName(),
				String.valueOf(this.eventParticipants.getId()), email, mobile));
		int participantId = this.eventParticipants.getId();
		getLogger().info("Printing the event participant id:" + participantId);
		List<Object> participantMembersList = getMembersByParticipantId(participantId);
		AtomicInteger counter = new AtomicInteger(1);
		if (null != participantMembersList && !participantMembersList.isEmpty()) {
			participantMembersList.forEach(eventParticipantMember -> {
				EventParticipantsMembers member = (EventParticipantsMembers) eventParticipantMember;
				participantInformationList.add(getParticipantInformation(member.getName(),
						String.valueOf(id) + "-" + counter.getAndIncrement(), email, mobile));
			});

		} else {
			this.getLogger().info("No participants found for participant: " + this.eventParticipants.getName());
		}
		return participantInformationList;
	}
}
