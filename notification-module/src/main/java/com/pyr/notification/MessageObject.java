package com.pyr.notification;

public abstract class MessageObject {
	
	private String subjectOfMessage;
	private String bodyOfMessage;
	private String format;
	private String footerInformation;	

	public String getSubjectOfMessage() {
		return subjectOfMessage;
	}

	public void setSubjectOfMessage(String subjectOfMessage) {
		this.subjectOfMessage = subjectOfMessage;
	}

	public String getBodyOfMessage() {
		return bodyOfMessage;
	}

	public void setBodyOfMessage(String bodyOfMessage) {
		this.bodyOfMessage = bodyOfMessage;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getFooterInformation() {
		return footerInformation;
	}

	public void setFooterInformation(String footerInformation) {
		this.footerInformation = footerInformation;
	}
}
