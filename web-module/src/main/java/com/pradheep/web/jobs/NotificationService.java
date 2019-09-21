package com.pradheep.web.jobs;

public interface NotificationService {
	public static int NOTIFICATION_TYPE_EMAIL = 1;
	public static int NOTIFICATION_TYPE_SMS = 2;	
	public void addNotificationJob(NotificationJob notificationJob);
	public void scheduleNotificationJobs();
}
