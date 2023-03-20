package com.pradheep.dao.model.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_participant_members")
public class EventParticipantsMembers implements java.io.Serializable {	
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer Id;

	@Column(name = "participant_id")
	private Integer participantId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "food_preference")
	private String foodPreference;
	
	@Column(name="isChild")
	private boolean isChild;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChild() {
		return isChild;
	}

	public void setChild(boolean isChild) {
		this.isChild = isChild;
	}

	public String getFoodPreference() {
		return foodPreference;
	}

	public void setFoodPreference(String foodPreference) {
		this.foodPreference = foodPreference;
	}

	@Override
	public String toString() {
		return "EventParticipantsMembers [Id=" + Id + ", participantId=" + participantId + ", name=" + name
				+ ", foodPreference=" + foodPreference + ", isChild=" + isChild + "]";
	}
	
}
