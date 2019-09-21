package com.pradheep.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// Generated Jun 14, 2017 11:13:22 AM by Hibernate Tools 5.2.1.Final

/**
 * This class is to maintain the scheduled notifications if any.
 */

@Entity
@Table(name = "notifications")
public class NotificationsModel implements java.io.Serializable {

	@Id
	@Column(name = "notification_id")
	@GeneratedValue
	public Integer id;

	@Column(name = "notification_type")
	public String notificationType;

	@Column(name = "start_time")
	public Date startTime;

	@Column(name = "repeat_notification")
	public Boolean repeatNotification;

	@Column(name = "notification_status")
	public String notificationStatus;

	@Column(name = "completed_time")
	public Date completedTime;

	@Column(name = "scheduled_job_class_name")
	public String runnableClassName;

	@Column(name = "frequency")
	public String notificationFrequency;
	
	@Column(name = "interval_in_millis")
	public Long interval;
	
	@Column(name = "other_info")
	public String otherInformation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Boolean getRepeatNotification() {
		return repeatNotification;
	}

	public void setRepeatNotification(Boolean repeatNotification) {
		this.repeatNotification = repeatNotification;
	}

	public String getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public Date getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(Date completedTime) {
		this.completedTime = completedTime;
	}

	public String getRunnableClassName() {
		return runnableClassName;
	}

	public void setRunnableClassName(String runnableClassName) {
		this.runnableClassName = runnableClassName;
	}

	public String getNotificationFrequency() {
		return notificationFrequency;
	}

	public void setNotificationFrequency(String notificationFrequency) {
		this.notificationFrequency = notificationFrequency;
	}

	public Long getInterval() {
		return interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}

	public String getOtherInformation() {
		return otherInformation;
	}

	public void setOtherInformation(String otherInformation) {
		this.otherInformation = otherInformation;
	}

}
