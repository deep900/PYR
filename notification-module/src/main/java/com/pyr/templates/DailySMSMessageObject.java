/**
 * 
 */
package com.pyr.templates;

import com.pyr.notification.MessageObject;

/**
 * @author pradheep.p
 *
 */
public class DailySMSMessageObject extends MessageObject {
	
	private String contactNumber;
	
	private boolean isUnicode = false;
	
	private String destinationCountryName;

	public DailySMSMessageObject(String args) {
		setBodyOfMessage(args);
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public boolean isUnicode() {
		return isUnicode;
	}

	public void setUnicode(boolean isUnicode) {
		this.isUnicode = isUnicode;
	}

	public String getDestinationCountryName() {
		return destinationCountryName;
	}

	public void setDestinationCountryName(String destinationCountryName) {
		this.destinationCountryName = destinationCountryName;
	}
	
	
}
