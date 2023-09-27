/**
 * 
 */
package com.pradheep.web.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author Pradheep
 *
 */
public class NewUserEventRegistrationEvent extends ApplicationEvent {

	public NewUserEventRegistrationEvent(Object source) {
		super(source);
		
	}
}
