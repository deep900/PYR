/**
 * 
 */
package com.pradheep.dao.model.event;

/**
 * Helps to map the result set for the Native SQL Query on Event Management
 * Reports.
 * 
 * @author pradheep
 */
public class EventManagementReportEntity {

	public static final String DELIMITER = ",";

	private String id;

	private String participantName;

	private String mobileNumber;

	private String email;

	private String participantFoodPreference;

	private String personInvited;

	private String registeringFor;

	private String childCnt;

	private String adultCount;

	private String registeredTime;

	private String dinnerTime;

	private String memberName;

	private String memberFoodPreference;

	private String eventId;

	private boolean isChild;
	
	private String eventName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getParticipantFoodPreference() {
		return participantFoodPreference;
	}

	public void setParticipantFoodPreference(String participantFoodPreference) {
		this.participantFoodPreference = participantFoodPreference;
	}

	public String getPersonInvited() {
		return personInvited;
	}

	public void setPersonInvited(String personInvited) {
		this.personInvited = personInvited;
	}

	public String getRegisteringFor() {
		return registeringFor;
	}

	public void setRegisteringFor(String registeringFor) {
		this.registeringFor = registeringFor;
	}

	public String getChildCnt() {
		return childCnt;
	}

	public void setChildCnt(String childCnt) {
		this.childCnt = childCnt;
	}

	public String getAdultCount() {
		return adultCount;
	}

	public void setAdultCount(String adultCount) {
		this.adultCount = adultCount;
	}

	public String getRegisteredTime() {
		return registeredTime;
	}

	public void setRegisteredTime(String registeredTime) {
		this.registeredTime = registeredTime;
	}

	public String getDinnerTime() {
		return dinnerTime;
	}

	public void setDinnerTime(String dinnerTime) {
		this.dinnerTime = dinnerTime;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberFoodPreference() {
		return memberFoodPreference;
	}

	public void setMemberFoodPreference(String memberFoodPreference) {
		this.memberFoodPreference = memberFoodPreference;
	}

	public boolean isChild() {
		return isChild;
	}

	public void setChild(boolean isChild) {
		this.isChild = isChild;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@Override
	public String toString() {
		return id + "," + participantName + "," + mobileNumber + "," + email + "," + participantFoodPreference + ","
				+ personInvited + "," + registeringFor + "," + registeredTime + "," + dinnerTime + ","
				+ (isChild ? "Yes" : "No" + "," + eventName);
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

}
