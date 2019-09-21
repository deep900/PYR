/**
 * 
 */
package com.pradheep.web.jobs;

import java.io.File;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.pradheep.web.event.ErrorNotificationEvent;
import com.pradheep.web.event.PyrApplicationEventPublisher;

/**
 * @author pradheep.p
 *
 */
public class FileCleanUpTask extends CommonTask {
	
	private final long startDelay = 20000l;

	private File fileToBeDeleted = null;
	
	private ApplicationContext applicationContext;

	private FileCleanUpTask() {

	}

	public FileCleanUpTask(File f) {
		this.fileToBeDeleted = f;
	}

	public void run() {
		System.out.println("Executing the task - " + getTaskName());
		try {
			Thread.currentThread().sleep(getStartDelayInMilliSecs());
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		if (fileToBeDeleted == null) {
			System.out.println("No file to be deleted..");
			return;
		}
		else{
			boolean flag = fileToBeDeleted.delete();
			System.out.println("--------- File Details : " + fileToBeDeleted.getAbsolutePath());
			if(flag){
				System.out.println("File deleted successfully");
				onSuccess();
			}
			else{
				System.out.println("Unable to delete the file..");
				onFailure();
			}
		}
	}

	@Override
	public long getStartDelayInMilliSecs() {		
		return startDelay;
	}

	@Override
	public void onSuccess() {	
		// do nothing
	}

	@Override
	public void onFailure() {		
		RuntimeException exception = new RuntimeException("Unable to delete the file : " + fileToBeDeleted.getAbsolutePath());
		ErrorNotificationEvent event = new ErrorNotificationEvent(exception);
		getPublisher().publishEvent(event);
	}
	
	private PyrApplicationEventPublisher getPublisher(){
		PyrApplicationEventPublisher publisher = (PyrApplicationEventPublisher) this.applicationContext.getBean(PyrApplicationEventPublisher.class);
		return publisher;
	}

	public void setApplicationContext(ApplicationContext arg0) throws BeansException {		
		this.applicationContext = arg0;
	}

	@Override
	public String getTaskName() {		
		return this.getClass().getName();
	}

}
