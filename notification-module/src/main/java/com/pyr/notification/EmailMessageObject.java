/**
 * 
 */
package com.pyr.notification;

import java.io.File;

/**
 * @author pradheep.p
 *
 */
public class EmailMessageObject extends MessageObject {

	private String[] toList = null;
	private String[] ccList = null;
	private String[] bccList = null;
	private String fromAddress;
	private File attachment;	
	

	public EmailMessageObject() {
		setFormat(NotificationFormat.EMAIL_FORMAT);
	}

	public String[] getToList() {
		return toList;
	}

	public void setToList(String[] toList) {
		this.toList = toList;
	}

	public String[] getCcList() {
		return ccList;
	}

	public void setCcList(String[] ccList) {
		this.ccList = ccList;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String[] getBccList() {
		return bccList;
	}

	public void setBccList(String[] bccList) {
		this.bccList = bccList;
	}

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

}
