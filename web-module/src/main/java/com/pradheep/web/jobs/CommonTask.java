/**
 * 
 */
package com.pradheep.web.jobs;

import org.springframework.context.ApplicationContextAware;

/**
 * @author pradheep.p
 *
 */
public abstract class CommonTask implements Runnable,ApplicationContextAware {
	
	public abstract long getStartDelayInMilliSecs();

	public abstract void onSuccess();

	public abstract void onFailure();
	
	public abstract String getTaskName();	
}
