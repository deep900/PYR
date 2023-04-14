/**
 * 
 */
package com.pradheep.web.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.pradheep.dao.model.event.EventModel;
import com.pradheep.dao.model.event.EventParticipants;
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
		List eventParticipants = daoService.getObjectsListById(EventParticipants.class, "eventId", eventId, "=", -1);
		List<EventParticipants> epList = new ArrayList();
		epList.addAll(eventParticipants);
		return epList;
	}

	public List<EventModel> getAllActiveEvents() {
		List eventModelList = daoService.getObjectsListById(EventModel.class, "eventDateTime", getTodayDate(), "<", -1);
		Iterator<EventModel> eventModelObj = eventModelList.iterator();
		List<EventModel> modelList = new ArrayList();
		modelList.addAll(eventModelList);
		return modelList;
	}

	private String getTodayDate() {
		return currentDateFormat.format(new Date());
	}

	public int getTimeDiffInHrsStartOfEvent(EventModel eventModel) {
		if (null != eventModel) {
			long milliseconds = eventModel.getEventDateTime().getTime() - getCurrentTimestamp().getTime();
			int seconds = (int) milliseconds / 1000;		    
		    int hours = seconds / 3600;
		    return hours;
		}
		return -1;
	}

}
