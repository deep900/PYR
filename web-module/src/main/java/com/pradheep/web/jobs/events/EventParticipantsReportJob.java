/**
 * 
 */
package com.pradheep.web.jobs.events;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.pradheep.dao.config.ApplicationLogger;
import com.pradheep.dao.model.event.EventManagementReportEntity;
import com.pradheep.dao.model.event.EventModel;
import com.pradheep.dao.model.event.EventParticipants;
import com.pradheep.dao.model.event.EventParticipantsMembers;
import com.pradheep.web.common.PYRUtility;
import com.pradheep.web.jobs.NotificationJob;
import com.pradheep.web.service.EventManagementService;
import com.pyr.notification.EmailMessageObject;
import com.pyr.notification.MessageObject;

/**
 * This job is to summarize the event participants with the food choices. This
 * job summarize the data in the format of .csv and sends an email to the event
 * administrators.
 * 
 * Frequency : Daily at 10 PM till the day of the event. How it works : Based on
 * the event notification start date which means the date when the registration
 * of the event begin.
 * 
 * @author Pradheep
 *
 */
public class EventParticipantsReportJob extends NotificationJob<EventModel> {

	@Autowired
	private EventManagementService eventManagementService;

	private Logger logger;

	public EventParticipantsReportJob() {
		setJobFrequency(JOB_FREQUENCY_DAILY_HRS);
		setJobTiming();
	}

	private void setJobTiming() {
		Date startDate = PYRUtility.get10PMSGT(1000);
		super.setJobStartTime(startDate);
	}

	public Logger getLogger() {
		if (logger == null) {
			return ApplicationLogger.getLogBean(getClass());
		}
		return logger;
	}

	@Override
	public void run() {
		notifyMessage();
	}

	public void emailMessage(MessageObject messageObj) {
		EmailMessageObject obj = (EmailMessageObject) messageObj;
		if (obj.getToList() == null) {
			getLogger().error("No emails are configured to send reports !");
			return;
		}
		getLogger().info(
				"Sending email reports " + obj.getAttachment() == null ? "with no attachments" : " with attachments");
		pryMessenger.communicate(messageObj);
		try {
			Thread.currentThread().sleep(1000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MessageObject getMessageObject(EventModel model) {
		return null;
	}

	private File prepareSummaryReportOfEvent(List<EventManagementReportEntity> eventManagementReportList) {
		if (null == eventManagementReportList) {
			getLogger().error("No data to write into file");
			return null;
		}
		File tempFile = null;
		try {
			tempFile = File.createTempFile("Daily_Report", ".csv");
		} catch (IOException err) {
			getLogger().error("Unable to create the temp file", err);
		}
		try (FileWriter fileWritter = new FileWriter(tempFile);
				BufferedWriter writer = new BufferedWriter(fileWritter)) {
			writer.write(getReportHeader() + "\n");
			eventManagementReportList.forEach(reportEntity -> {
				try {
					writer.write(reportEntity.toString() + "\n");
				} catch (Exception err) {
					getLogger().error("Error while writing into the file", err);
				}
			});
		} catch (IOException e) {
			this.getLogger().error("Error while writting the file", e);
		}
		getLogger().info("Printing the output file name:" + tempFile.getAbsolutePath());
		return tempFile;
	}

	private List getActiveEventThatCanBeNotified() {
		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		List<EventModel> eventList = eventManagementService.getAllActiveEvents();
		return eventList.stream().filter(event -> event.getEventAnnouncementDate().getTime() < timestamp.getTime()
				&& event.getEventDateTime().getTime() > timestamp.getTime()).collect(Collectors.toList());
	}

	private EventManagementReportEntity mapEventParticipant(EventParticipants eventParticipant,
			EventParticipantsMembers member, String memberId,String eventName) {
		EventManagementReportEntity entity = new EventManagementReportEntity();
		try {
			if (null == memberId) {
				entity.setId(String.valueOf(eventParticipant.getId()));
			} else {
				entity.setId(memberId);
			}
			entity.setAdultCount(String.valueOf(eventParticipant.getAdultCount()));
			entity.setChildCnt(String.valueOf(eventParticipant.getChildCount()));
			entity.setDinnerTime(eventParticipant.getDinnerTime());
			entity.setEmail(eventParticipant.getEmail());
			entity.setMobileNumber(eventParticipant.getMobileNumber());
			entity.setParticipantFoodPreference(eventParticipant.getFoodPreference());
			entity.setParticipantName(eventParticipant.getName());
			entity.setPersonInvited(eventParticipant.getPersonWhoInvited());
			entity.setRegisteredTime(
					this.eventManagementService.formatDate(new Date(eventParticipant.getRegisteredTime().getTime())));
			entity.setRegisteringFor(eventParticipant.getRegisteringFor());
			entity.setEventName(eventName);
			if (null != member) {
				entity.setMemberFoodPreference(member.getFoodPreference());
				entity.setMemberName(member.getName());
				entity.setChild(member.isChild());
				entity.setParticipantFoodPreference(member.getFoodPreference());
				entity.setParticipantName(member.getName());
			}
		} catch (Exception err) {
			this.getLogger().error("Error in making the event management report entity", err);
			return null;
		}
		return entity;
	}

	private List<EventManagementReportEntity> getAllParticipantsDetails(EventModel eventModel) {
		List<EventParticipants> eventParticipants = this.eventManagementService.getAllParticipants(eventModel.getId());
		List<EventManagementReportEntity> eventManagementReportEntityList = new ArrayList<EventManagementReportEntity>();
		if (null != eventParticipants) {
			eventParticipants.forEach(eventParticipant -> {
				AtomicInteger counter = new AtomicInteger(1);
				EventManagementReportEntity entity = mapEventParticipant(eventParticipant, null, null,eventModel.getEventName());
				if (null != entity) {
					eventManagementReportEntityList.add(entity);
				}
				int participantId = eventParticipant.getId();
				List<EventParticipantsMembers> memberList = this.eventManagementService
						.getAllParticipantMembers(participantId);
				if (null != memberList) {
					memberList.forEach(member -> {
						EventManagementReportEntity memEntity = mapEventParticipant(eventParticipant, member,
								participantId + "-" + counter.getAndIncrement(),eventModel.getEventName());
						if (null != memEntity) {
							eventManagementReportEntityList.add(memEntity);
						}
					});
				}
			});
		}
		return eventManagementReportEntityList;
	}

	private MessageObject prepareMessageObject(String subject, String bodyMessage, File attachment,
			EventModel eventModel) {
		EmailMessageObject messageObject = new EmailMessageObject();
		messageObject.setBodyOfMessage(bodyMessage);
		messageObject.setSubjectOfMessage(subject);
		messageObject.setAttachment(attachment);
		messageObject.setToList(getAdministrators(eventModel));
		messageObject.setFromAddress("administrator@praiseyourredeemer.org");
		return messageObject;
	}

	private String getReportHeader() {
		return "Id,ParticipantName,MobileNumber,Email,Participant Food Preference,Person Invited,Registering For,Registered Time,Dinner Time, Is Child, Event Name";
	}

	@Override
	public void notifyMessage() {
		getLogger().info("Preparing the report for the event participants.");
		List<EventModel> eventModelList = getActiveEventThatCanBeNotified();
		getLogger().info("Printing the event models ready to be notified:" + eventModelList.toString());
		if (null == eventModelList || eventModelList.isEmpty()) {
			getLogger().info("No events found to be reported.");
			return;
		} else {
			eventModelList.forEach(eventModel -> {
				int eventId = eventModel.getId();
				List<EventManagementReportEntity> eventManagementEntityList = getAllParticipantsDetails(eventModel);
				File attachment = prepareSummaryReportOfEvent(eventManagementEntityList);
				if (null != attachment) {
					MessageObject messageObj = prepareMessageObject(
							"Daily event reports - " + this.eventManagementService.getTodayDate(),
							getEmailBody(eventModel.getEventName()), attachment, eventModel);
					emailMessage(messageObj);
					try {
						Thread.currentThread().sleep(5000l);
					} catch (Exception err) {
						getLogger().info("Error occured.", err);
					}
				}
			});
		}
	}

	public String[] getAdministrators(EventModel eventModel) {
		String email = eventModel.getEventAdministratorEmail();
		getLogger().info("Printing the administrator email address" + email);
		if (email.contains(",")) {
			return email.split(",");
		} else {
			return new String[] { email };
		}
	}

	private String getEmailBody(String eventName) {
		return "Dear Event Administrator, <br><br>  Please find the list of participants for " + eventName
				+ " attached. <br><br> Thank you <br> Praise Your Redeemer.";
	}

}
