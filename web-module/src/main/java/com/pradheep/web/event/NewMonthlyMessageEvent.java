/**
 * 
 */
package com.pradheep.web.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author pradheep.p
 *
 */
public class NewMonthlyMessageEvent extends ApplicationEvent {

	public NewMonthlyMessageEvent(Object source) {
		super(source);		
	}

}
