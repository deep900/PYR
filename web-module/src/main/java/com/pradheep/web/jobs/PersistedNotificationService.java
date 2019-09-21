/**
 * 
 */
package com.pradheep.web.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.pradheep.dao.config.DAOService;
import com.pradheep.dao.model.NotificationsModel;
import com.pradheep.web.common.ApplicationConstants;
import com.pradheep.web.common.ApplicationLoggerWeb;

/**
 * @author pradheep.p
 *
 */
public class PersistedNotificationService implements InitializingBean, ApplicationContextAware {

	@Autowired
	private DAOService daoService;

	@Autowired
	private ThreadPoolTaskScheduler taskScheduler;

	private List<NotificationsModel> persistedNotifications = new ArrayList<NotificationsModel>();

	private ApplicationContext applicationContext;

	private Logger getLogger() {
		return ApplicationLoggerWeb.getLogBean(this.getClass());
	}

	private void loadNotificationsFromDB() {
		getLogger().info("Loading the persisted notification jobs");
		List<Object> objects = daoService.getObjectsListById(NotificationsModel.class, "notificationStatus",ApplicationConstants.NOTIFICATION_JOB_COMPLETE,"!=",-1);
		if(objects == null) {
			getLogger().info("No objects found to schedule.");
			return;
		}
		for(Object obj : objects) {
			persistedNotifications.add((NotificationsModel) obj);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		loadNotificationsFromDB();
		scheduleJobs();
	}

	private void scheduleJobs() {
		if (persistedNotifications.isEmpty()) {
			getLogger().info("No persisted notification jobs");
			return;
		}
		for (NotificationsModel notificationModel : persistedNotifications) {
			schedule(notificationModel);
		}
	}
	
	public void addNotificationModel(NotificationsModel notificationModel) {		
		schedule(notificationModel);
	}
	
	private void setNotificationJobId(int notificationJobId,Object runnable) {
		if(runnable instanceof PersistedNotificationJob) {
			PersistedNotificationJob job = (PersistedNotificationJob) runnable;
			job.setPersistedNotificationJobId(notificationJobId);			
		}
	}
	
	private void schedule(NotificationsModel notificationModel) {
		Runnable runnableJob = (Runnable) getRunnableBean(notificationModel.getRunnableClassName());
		setNotificationJobId(notificationModel.getId(), runnableJob);
		Date startDate = notificationModel.getStartTime();
		getLogger().info("The scheduled job will start on " + startDate);
		if (notificationModel.getRepeatNotification()) {
			getLogger().info("Running the persisted notification job at fixed rate");
			long intervalInMilliSecs = notificationModel.getInterval();
			taskScheduler.scheduleAtFixedRate(runnableJob, startDate, intervalInMilliSecs);
		} else {
			getLogger().info("Running the persisted notification job - " + new Date().toString());
			taskScheduler.schedule(runnableJob, startDate);
		}
	}

	public Object getRunnableBean(String className) {
		return this.applicationContext.getBean(className);
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
	}
}
