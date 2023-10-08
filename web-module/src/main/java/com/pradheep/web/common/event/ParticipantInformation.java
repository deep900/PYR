/**
 * 
 */
package com.pradheep.web.common.event;

/**
 * @author Pradheep
 *
 */
public class ParticipantInformation {

	private final static String DELIMITER = "|";

	private String participantName;

	private String id;

	private String email;

	private String mobile;

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDelimitedString() {
		return this.getParticipantName() + DELIMITER + this.getId() + DELIMITER + this.getMobile() + DELIMITER
				+ this.getEmail();
	}
}
