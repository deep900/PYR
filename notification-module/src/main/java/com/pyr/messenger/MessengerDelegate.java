/**
 * 
 */
package com.pyr.messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.pyr.notification.MessageObject;

/**
 * @author pradheep.p
 *
 */
public class MessengerDelegate {
	
	@Autowired
	@Qualifier("emailMessenger")
	private Messenger emailMessenger;
	
	@Autowired
	@Qualifier("smsMessenger")
	private Messenger smsMessenger;

	public Object communicateMessage(MessageObject obj,int messageType){
		if(messageType == Messenger.EMAIL_MESSAGE){
			return sendEmailCommunication(obj);
		}
		if(messageType == Messenger.SMS_MESSAGE){
			return sendSMSCommunication(obj);
		}
		if(messageType == Messenger.BOTH){
			sendEmailCommunication(obj);
			sendSMSCommunication(obj);
			return "0";
		}
		return "Invalid Code";
	}
	
	private Object sendEmailCommunication(MessageObject emailObject){
		return emailMessenger.communicate(emailObject);
	}
	
	private Object sendSMSCommunication(MessageObject emailObject){
		return smsMessenger.communicate(emailObject);
	}
}
