/**
 * 
 */
package com.pradheep.dao.model.event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pradheep
 *
 */
public class EventWrapper {
	
	private EventModel eventModel;
	
	private EventParticipants eventParticipants;
	
	private List<EventParticipantsMembers> eventParticipantMembers = new ArrayList<EventParticipantsMembers>();

	public EventModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(EventModel eventModel) {
		this.eventModel = eventModel;
	}

	public EventParticipants getEventParticipants() {
		return eventParticipants;
	}

	public void setEventParticipants(EventParticipants eventParticipants) {
		this.eventParticipants = eventParticipants;
	}

	public List<EventParticipantsMembers> getEventParticipantMembers() {
		return eventParticipantMembers;
	}

	public void setEventParticipantMembers(List<EventParticipantsMembers> eventParticipantMembers) {
		this.eventParticipantMembers = eventParticipantMembers;
	}

	@Override
	public String toString() {
		return "EventWrapper [eventModel=" + eventModel + ", eventParticipants=" + eventParticipants
				+ ", eventParticipantMembers=" + eventParticipantMembers + "]";
	}

}
