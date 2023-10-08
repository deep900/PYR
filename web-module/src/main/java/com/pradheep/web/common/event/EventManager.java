/**
 * 
 */
package com.pradheep.web.common.event;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.pradheep.dao.config.ApplicationLogger;
import com.pradheep.dao.config.DAOService;
import com.pradheep.dao.model.event.EventModel;
import com.pradheep.dao.model.event.EventParticipants;
import com.pradheep.dao.model.event.EventParticipantsMembers;
import com.pradheep.dao.model.event.EventWrapper;
import com.pry.security.utility.PublicUtility;

/**
 * This class manages the events and data persistance.
 * 
 * @author Pradheep
 *
 */
public class EventManager {

	@Autowired
	private DAOService daoService;

	private Logger logger;

	@Autowired
	private PublicUtility publicUtility;

	public EventModel getEventById(String id) {
		getLogger().info("Trying to get the event details by id" + id);
		EventModel eModelObj = (EventModel) daoService.getObjectsById(EventModel.class, "id", id);
		return eModelObj;
	}

	public List<EventParticipants> getParticipants(int eventId) {
		List participantsList = daoService.getObjectsListById(EventParticipants.class, "eventId", eventId, "=", -1);
		return participantsList;
	}

	public List<EventParticipantsMembers> getParticipantMembers(int participantId) {
		List participantsMem = daoService.getObjectsListById(EventParticipantsMembers.class, "participantId",
				participantId, "=", -1);
		return participantsMem;
	}

	public String decrypt(String raw) {
		return publicUtility.DecryptText(raw);
	}

	private Logger getLogger() {
		if (logger == null) {
			return ApplicationLogger.getLogBean(this.getClass());
		}
		return logger;
	}

	/**
	 * Returns true if the participants are persisted successfully.
	 * 
	 * @param eventWrapper
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public int saveEventParticipants(EventWrapper eventWrapper) {
		if (null == eventWrapper) {
			getLogger().info("No valid event data found for persistance");
			return -1;
		}
		int participantId = 0;
		try {
			if (null != eventWrapper.getEventParticipants()) {
				EventParticipants eventPartObj = (EventParticipants) daoService
						.saveObjectAndReturn(eventWrapper.getEventParticipants());
				getLogger().info("Persisted the event participants successfully: " + eventPartObj.toString());
				participantId = eventPartObj.getId();
			}
			if (null != eventWrapper.getEventParticipantMembers()) {
				if (eventWrapper.getEventParticipantMembers().isEmpty()) {
					getLogger().info("No member participants found !");
				} else {
					Iterator<EventParticipantsMembers> participantIterator = eventWrapper.getEventParticipantMembers()
							.iterator();
					while (participantIterator.hasNext()) {
						EventParticipantsMembers memberObj = participantIterator.next();
						memberObj.setParticipantId(participantId);
						EventParticipantsMembers members = (EventParticipantsMembers) daoService
								.saveObjectAndReturn(memberObj);
						getLogger().info("Persisted the event participant member successfully: " + members);
					}
				}
			}
			return participantId;
		} catch (Exception err) {
			getLogger().error("Error while saving the participants", err);
			return -1;
		}
	}

	public List<EventParticipants> getParticipantsByMobile(String mobileNumber, Integer eventId) {
		String[] queryParams = { "eventId", "mobileNumber" };
		Object[] values = { eventId, mobileNumber };
		String[] sign = { "=", "=" };
		List participantsList = daoService.getObjectsListByMultipleParameters(EventParticipants.class, queryParams,
				values, sign, -1, false);
		if (participantsList != null) {
			getLogger().info("Found : " + participantsList.size() + " items for (Mobile):" + mobileNumber);
		} else {
			getLogger().info("No results found for " + mobileNumber);
		}
		return participantsList;
	}

	public List<EventParticipants> getParticipantsByEmail(String email, Integer eventId) {
		String[] queryParams = { "eventId", "email" };
		Object[] values = { eventId, email };
		String[] sign = { "=", "=" };
		List participantsList = daoService.getObjectsListByMultipleParameters(EventParticipants.class, queryParams,
				values, sign, -1, false);
		if(null != participantsList) {
			getLogger().info("Found : " + participantsList.size() + " items for (Email):" + email);
		}else{
			getLogger().info("No results found for " + email);
		}
		
		return participantsList;
	}
}
