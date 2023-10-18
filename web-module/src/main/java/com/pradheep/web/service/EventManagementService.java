/**
 * 
 */
package com.pradheep.web.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.pradheep.dao.model.event.EventModel;
import com.pradheep.dao.model.event.EventParticipants;
import com.pradheep.dao.model.event.EventParticipantsMembers;
import com.pradheep.web.controller.BaseUtility;

/**
 * @author Pradheep This service methods are for the event management only.
 *
 */
public class EventManagementService extends BaseUtility<Object> {

	private SimpleDateFormat currentDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public boolean isEventEnded(EventModel eventModel) {
		getLogger().info("Printing the event start time:" + eventModel.getEventDateTime());
		Timestamp time = eventModel.getEventDateTime();
		return time.before(getCurrentTimestamp());
	}

	public Timestamp getCurrentTimestamp() {
		Timestamp currentTimestamp = Timestamp.from(Instant.now());
		return currentTimestamp;
	}

	public List<EventParticipants> getAllParticipants(int eventId) {
		getLogger().info("Trying to get all the participants for the event id" + eventId);
		List eventParticipants = daoService.getObjectsListById(EventParticipants.class, "eventId", eventId, "=", -1);
		List<EventParticipants> epList = new ArrayList();
		epList.addAll(eventParticipants);
		getLogger().info("Found " + epList.size() + " participants.");
		return epList;
	}

	public List<EventParticipantsMembers> getAllParticipantMembers(int participantId) {
		getLogger().info("Trying to fetch the participant members " + participantId);
		List eventParticipantMembers = daoService.getObjectsListById(EventParticipantsMembers.class, "participantId",
				participantId, "=", -1);
		if (null == eventParticipantMembers || eventParticipantMembers.isEmpty()) {
			getLogger().info("No members found for this participant");
			return Collections.emptyList();
		} else {
			List<EventParticipantsMembers> epmList = new ArrayList();
			epmList.addAll(eventParticipantMembers);
			getLogger().info("Found " + epmList.size() + " members");
			return epmList;
		}
	}

	public List<EventModel> getAllActiveEvents() {
		List eventModelList = daoService.getObjectsListById(EventModel.class, "eventDateTime", getTodayDate(), "<", -1);
		Iterator<EventModel> eventModelObj = eventModelList.iterator();
		List<EventModel> modelList = new ArrayList();
		modelList.addAll(eventModelList);
		return modelList;
	}

	public String getTodayDate() {
		return currentDateFormat.format(new Date());
	}

	public int getTimeDiffInHrsStartOfEvent(EventModel eventModel) {
		if (null != eventModel) {
			long milliseconds = eventModel.getEventDateTime().getTime() - getCurrentTimestamp().getTime();
			getLogger().info("Printing the difference in millis " + milliseconds);
			Double seconds = (double) (milliseconds / 1000);
			Double hours = seconds / 3600;
			return hours.intValue();
		}
		return -1;
	}

	public String formatDate(Date dateObj) {
		return currentDateFormat.format(dateObj);
	}

}
