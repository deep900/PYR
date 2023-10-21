package com.pradheep.web.jobs;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.pradheep.dao.config.DAOService;
import com.pradheep.web.common.ApplicationLoggerWeb;
import com.pyr.messenger.PyrMessenger;
import com.pyr.messenger.SMSMessenger;
import com.pyr.notification.MessageObject;

public abstract class NotificationJob<T extends Object> implements Runnable {

	public static int JOB_FREQUENCY_DAILY_HRS = 1 * 24;

	public static int JOB_FREQUENCY_MONTHLY_HRS = 1 * 24 * 30;

	public static int JOB_FREQUENCY_YEARLY_HRS = 1 * 24 * 30 * 12;

	private int notificationType = 0;

	private Logger logger = null;

	private int jobFrequency;

	private Date jobStartTime = null;

	@Autowired	
	protected SMSMessenger smsMessenger;

	@Autowired	
	protected PyrMessenger pryMessenger;

	@Autowired
	protected DAOService daoService;

	public Logger getLogger() {
		if (logger == null) {
			logger = ApplicationLoggerWeb.getLogBean(getClass());
		}
		return logger;
	}

	public abstract void notifyMessage();

	public int getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(int notificationType) {
		this.notificationType = notificationType;
	}

	public int getJobFrequency() {
		return jobFrequency;
	}

	public void setJobFrequency(int jobFrequency) {
		this.jobFrequency = jobFrequency;
	}

	public abstract MessageObject getMessageObject(T messageObj);

	public Date getJobStartTime() {
		return this.jobStartTime;
	}

	public void setJobStartTime(Date jobStartTime) {
		this.jobStartTime = jobStartTime;
	}
}
