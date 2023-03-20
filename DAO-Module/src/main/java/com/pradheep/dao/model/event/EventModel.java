package com.pradheep.dao.model.event;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class EventModel implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer Id;

	@Column(name = "event_name")
	private String eventName;
	
	@Column(name = "event_date_time")
	private Timestamp eventDateTime;
	
	@Column(name = "event_location")
	private String eventLocation;
	
	@Column(name="welcome_note")
	private String welcomeNote;
	
	@Column(name="organizer")
	private String organizer;
	
	@Column(name="event_org_contact_no")
	private String eventOrgContactNumber;
	
	@Column(name="event_org_email")
	private String eventOrgEmail;

	@Column(name="event_flyer_image_path")
	private String eventFlyerImagePath;
	
	@Column(name="event_org_logo_path")
	private String eventOrgLogoPath;
	
	@Column(name="event_reg_notification_freq")
	private int eventNotificationFreq;
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}	

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getWelcomeNote() {
		return welcomeNote;
	}

	public void setWelcomeNote(String welcomeNote) {
		this.welcomeNote = welcomeNote;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}	

	public String getEventOrgContactNumber() {
		return eventOrgContactNumber;
	}

	public void setEventOrgContactNumber(String eventOrgContactNumber) {
		this.eventOrgContactNumber = eventOrgContactNumber;
	}

	public String getEventOrgEmail() {
		return eventOrgEmail;
	}

	public void setEventOrgEmail(String eventOrgEmail) {
		this.eventOrgEmail = eventOrgEmail;
	}	

	public String getEventFlyerImagePath() {
		return eventFlyerImagePath;
	}

	public void setEventFlyerImagePath(String eventFlyerImagePath) {
		this.eventFlyerImagePath = eventFlyerImagePath;
	}

	public String getEventOrgLogoPath() {
		return eventOrgLogoPath;
	}

	public void setEventOrgLogoPath(String eventOrgLogoPath) {
		this.eventOrgLogoPath = eventOrgLogoPath;
	}	

	public int getEventNotificationFreq() {
		return eventNotificationFreq;
	}

	public void setEventNotificationFreq(int eventNotificationFreq) {
		this.eventNotificationFreq = eventNotificationFreq;
	}

	@Override
	public String toString() {
		return "EventModel [Id=" + Id + ", eventName=" + eventName + ", eventDateTime=" + eventDateTime
				+ ", eventLocation=" + eventLocation + ", welcomeNote=" + welcomeNote + ", organizer=" + organizer
				+ ", eventOrgContactNumber=" + eventOrgContactNumber + ", eventOrgEmail=" + eventOrgEmail
				+ ", eventFlyerImagePath=" + eventFlyerImagePath + ", eventOrgLogoPath=" + eventOrgLogoPath
				+ ", eventNotificationFreq=" + eventNotificationFreq + "]";
	}

	public Timestamp getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(Timestamp eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	
}
