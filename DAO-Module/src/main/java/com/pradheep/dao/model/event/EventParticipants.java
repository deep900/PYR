package com.pradheep.dao.model.event;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_participants")
public class EventParticipants implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer Id;

	@Column(name = "event_id")
	private Integer eventId;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "food_preference")
	private String foodPreference;

	@Column(name = "person_invited")
	private String personWhoInvited;

	@Column(name = "registering_for")
	private String registeringFor;

	@Column(name = "child_cnt")
	private Integer childCount;

	@Column(name = "adult_count")
	private Integer adultCount;

	@Column(name = "registered_time")
	private Timestamp registeredTime;

	@Column(name = "email_remainder_sent")
	private boolean remindedByEmail;

	@Column(name = "dinner_time")
	private String dinnerTime;

	/**
	 * Generic option selected by the user for the particular event. This is an
	 * optional field.
	 */
	@Column(name = "event_option")
	private String eventOption;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFoodPreference() {
		return foodPreference;
	}

	public void setFoodPreference(String foodPreference) {
		this.foodPreference = foodPreference;
	}

	public String getPersonWhoInvited() {
		return personWhoInvited;
	}

	public void setPersonWhoInvited(String personWhoInvited) {
		this.personWhoInvited = personWhoInvited;
	}

	public String getRegisteringFor() {
		return registeringFor;
	}

	public void setRegisteringFor(String registeringFor) {
		this.registeringFor = registeringFor;
	}

	public Integer getChildCount() {
		return childCount;
	}

	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}

	public Integer getAdultCount() {
		return adultCount;
	}

	public void setAdultCount(Integer adultCount) {
		this.adultCount = adultCount;
	}

	public Timestamp getRegisteredTime() {
		return registeredTime;
	}

	public void setRegisteredTime(Timestamp registeredTime) {
		this.registeredTime = registeredTime;
	}

	public boolean isRemindedByEmail() {
		return remindedByEmail;
	}

	public void setRemindedByEmail(boolean remindedByEmail) {
		this.remindedByEmail = remindedByEmail;
	}

	public String getDinnerTime() {
		return dinnerTime;
	}

	public void setDinnerTime(String dinnerTime) {
		this.dinnerTime = dinnerTime;
	}

	public String getEventOption() {
		return eventOption;
	}

	public void setEventOption(String eventOption) {
		this.eventOption = eventOption;
	}

	@Override
	public String toString() {
		return "EventParticipants [Id=" + Id + ", eventId=" + eventId + ", name=" + name + ", email=" + email
				+ ", mobileNumber=" + mobileNumber + ", foodPreference=" + foodPreference + ", personWhoInvited="
				+ personWhoInvited + ", registeringFor=" + registeringFor + ", childCount=" + childCount
				+ ", adultCount=" + adultCount + ", registeredTime=" + registeredTime + ", remindedByEmail="
				+ remindedByEmail + ", dinnerTime=" + dinnerTime + ", eventOption=" + eventOption + "]";
	}

}
