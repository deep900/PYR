/**
 * 
 */
package com.pradheep.web.common.event;

import java.util.List;

import com.pradheep.dao.model.event.EventParticipants;
import com.pradheep.dao.model.event.EventParticipantsMembers;

/**
 * @author deep90
 *
 */
public class EventParticipantsModel extends EventParticipants{

	public List<EventParticipantsMembers> childMembers;

	public List<EventParticipantsMembers> adultMembers;

	public List<EventParticipantsMembers> getChildMembers() {
		return childMembers;
	}

	public void setChildMembers(List<EventParticipantsMembers> childMembers) {
		this.childMembers = childMembers;
	}

	public List<EventParticipantsMembers> getAdultMembers() {
		return adultMembers;
	}

	public void setAdultMembers(List<EventParticipantsMembers> adultMembers) {
		this.adultMembers = adultMembers;
	}

}
