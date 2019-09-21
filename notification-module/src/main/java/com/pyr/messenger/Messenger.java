/**
 * 
 */
package com.pyr.messenger;

import com.pyr.notification.MessageObject;

/**
 * @author pradheep.p
 *
 */
public interface Messenger {
	
	public static int EMAIL_MESSAGE = 1;
	public static int SMS_MESSAGE = 2;
	public static int BOTH = 3;

	public Object communicate(MessageObject messageObject);
}
