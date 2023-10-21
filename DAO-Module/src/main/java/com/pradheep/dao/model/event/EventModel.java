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

	@Column(name = "welcome_note")
	private String welcomeNote;

	@Column(name = "organizer")
	private String organizer;

	@Column(name = "event_org_contact_no")
	private String eventOrgContactNumber;

	@Column(name = "event_org_email")
	private String eventOrgEmail;

	@Column(name = "event_flyer_image_path")
	private String eventFlyerImagePath;

	@Column(name = "event_org_logo_path")
	private String eventOrgLogoPath;

	@Column(name = "event_reg_notification_freq")
	private int eventNotificationFreq;

	@Column(name = "event_program_flyer_image_path")
	private String eventProgramFlyerPath;

	@Column(name = "email_remainder_template")
	private String emailRemainderTemplate;
	
	/**
	 * Added Newly from Year 2023 to send emails to administrators when user
	 * register for a event. Email address is comma delimited.
	 */
	@Column(name = "event_admin_email")
	private String eventAdministratorEmail;

	/**
	 * Added Newly from Year 2023 to send emails to administrators when user
	 * register for a event. This flag enables and disables the functionality
	 * dynamically.
	 */
	@Column(name = "notify_event_registration_to_admin")
	private boolean notifyEventRegistrationToAdmin;
	
	@Column(name = "welcome_email_template")
	private String welcomeEmailTemplate;
	
	@Column(name = "event_announcement_date_time")
	private Timestamp eventAnnouncementDate;
	
	@Column(name = "food_option_required")
	private boolean foodOptionRequired;
	
	@Column(name = "agreement_content")
	private String agreementContent;

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

	public Timestamp getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(Timestamp eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	public String getEventProgramFlyerPath() {
		return eventProgramFlyerPath;
	}

	public void setEventProgramFlyerPath(String eventProgramFlyerPath) {
		this.eventProgramFlyerPath = eventProgramFlyerPath;
	}

	public String getEmailRemainderTemplate() {
		return emailRemainderTemplate;
	}

	public void setEmailRemainderTemplate(String emailRemainderTemplate) {
		this.emailRemainderTemplate = emailRemainderTemplate;
	}	

	public String getEventAdministratorEmail() {
		return eventAdministratorEmail;
	}

	public void setEventAdministratorEmail(String eventAdministratorEmail) {
		this.eventAdministratorEmail = eventAdministratorEmail;
	}

	public boolean isNotifyEventRegistrationToAdmin() {
		return notifyEventRegistrationToAdmin;
	}

	public void setNotifyEventRegistrationToAdmin(boolean notifyEventRegistrationToAdmin) {
		this.notifyEventRegistrationToAdmin = notifyEventRegistrationToAdmin;
	}	

	public String getWelcomeEmailTemplate() {
		return welcomeEmailTemplate;
	}

	public void setWelcomeEmailTemplate(String welcomeEmailTemplate) {
		this.welcomeEmailTemplate = welcomeEmailTemplate;
	}	

	public Timestamp getEventAnnouncementDate() {
		return eventAnnouncementDate;
	}

	public void setEventAnnouncementDate(Timestamp eventAnnouncementDate) {
		this.eventAnnouncementDate = eventAnnouncementDate;
	}

	public boolean isFoodOptionRequired() {
		return foodOptionRequired;
	}

	public void setFoodOptionRequired(boolean foodOptionRequired) {
		this.foodOptionRequired = foodOptionRequired;
	}	

	public String getAgreementContent() {
		return agreementContent;
	}

	public void setAgreementContent(String agreementContent) {
		this.agreementContent = agreementContent;
	}

	@Override
	public String toString() {
		return "EventModel [Id=" + Id + ", eventName=" + eventName + ", eventDateTime=" + eventDateTime
				+ ", eventLocation=" + eventLocation + ", welcomeNote=" + welcomeNote + ", organizer=" + organizer
				+ ", eventOrgContactNumber=" + eventOrgContactNumber + ", eventOrgEmail=" + eventOrgEmail
				+ ", eventFlyerImagePath=" + eventFlyerImagePath + ", eventOrgLogoPath=" + eventOrgLogoPath
				+ ", eventNotificationFreq=" + eventNotificationFreq + ", eventProgramFlyerPath="
				+ eventProgramFlyerPath + ", emailRemainderTemplate=" + emailRemainderTemplate
				+ ", eventAdministratorEmail=" + eventAdministratorEmail + ", notifyEventRegistrationToAdmin="
				+ notifyEventRegistrationToAdmin + ", welcomeEmailTemplate=" + welcomeEmailTemplate
				+ ", eventAnnouncementDate=" + eventAnnouncementDate + ", foodOptionRequired=" + foodOptionRequired
				+ ", agreementContent=" + agreementContent + "]";
	}	

}
