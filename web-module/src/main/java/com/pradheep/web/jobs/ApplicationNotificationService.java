/**
 * 
 */
package com.pradheep.web.jobs;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;

import com.pradheep.web.common.ApplicationLoggerWeb;
import com.pradheep.web.common.PYRUtility;

/**
 * @author pradheep.p
 *
 */
public class ApplicationNotificationService implements InitializingBean, NotificationService, ApplicationContextAware {

	private List<NotificationJob> notificationJobs = new ArrayList<NotificationJob>();

	private ApplicationContext applicationContext;

	private Properties properties = new Properties();
	
	private long oneDayDelayInMills = 86400000l;
	
	@Autowired
	private ThreadPoolTaskScheduler taskScheduler;

	private Logger getLogger() {
		return ApplicationLoggerWeb.getLogBean(this.getClass());
	}
	
	@Override
	public void addNotificationJob(NotificationJob notificationJob) {
		this.notificationJobs.add(notificationJob);
	}	

	private void loadProperties() {
		getLogger().info("Loading properties .. NotificationService.properties");
		Resource file = new ClassPathResource("NotificationSevice.properties");
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		try {
			properties.load(inputStream);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// Loading the Jobs for timely execution //
		loadProperties();
		String jobs = properties.getProperty("jobs");
		String[] jobArray = jobs.split(",");
		for (String jobName : jobArray) {
			getLogger().info("Finding the Job by Name " + jobName);
			NotificationJob notificationJob = (NotificationJob) applicationContext.getBean(jobName);
			if (notificationJob != null && !notificationJobs.contains(notificationJob)) {
				addNotificationJob(notificationJob);
			}
			getLogger().info("Successfully added the notification Job");
		}
		scheduleNotificationJobs(); // Commented as it will call daily SMS service //
	}

	@Override				
	public void scheduleNotificationJobs() {
		getLogger().info("Scheduling the Jobs");
		int timeDiffInJobsInSecs = 0;
		for(NotificationJob job: notificationJobs){
			if(job.getJobFrequency() == NotificationJob.JOB_FREQUENCY_DAILY_HRS){
				// Daily Schedule //
				Long period = new Long(oneDayDelayInMills);
				Date startDate = PYRUtility.getNextDaySixAM(timeDiffInJobsInSecs);
				getLogger().info("------------- Scheduling Jobs ---------------------");
				getLogger().info("Job Start Time " + startDate.toString());
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
				calendar.setTime(startDate);
				calendar.add(GregorianCalendar.MILLISECOND, period.intValue());
				getLogger().info("Next schedule period : " + calendar.getTime().toString());
				getLogger().info("----------------------------------------------------");				
				taskScheduler.scheduleWithFixedDelay(job,startDate , period);
				timeDiffInJobsInSecs = timeDiffInJobsInSecs + 300; // Keep a five minutes delay between jobs.
			}
		}
	}	

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
