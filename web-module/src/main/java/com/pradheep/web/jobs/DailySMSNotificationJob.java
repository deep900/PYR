/**
 * 
 */
package com.pradheep.web.jobs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.pradheep.dao.model.BibleVerse;
import com.pradheep.dao.model.DailySMSLogger;
import com.pradheep.dao.model.Subscription;
import com.pradheep.web.common.DailyVerseManager;
import com.pradheep.web.common.OneYearBible;
import com.pradheep.web.common.PYRUtility;
import com.pyr.notification.MessageObject;
import com.pyr.templates.DailySMSMessageObject;

/**
 * @author pradheep.p
 *
 */
public class DailySMSNotificationJob extends NotificationJob {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");

	@Autowired
	private DailyVerseManager dailyVerseManager;

	@Autowired
	@Qualifier("oneYearBibleManager")
	private OneYearBible oneYearBible;
	
	private List<Subscription> subscriptionList = new ArrayList<Subscription>();

	public DailySMSNotificationJob() {
		setNotificationType(NotificationService.NOTIFICATION_TYPE_SMS);
		setJobFrequency(NotificationJob.JOB_FREQUENCY_DAILY_HRS);
	}

	@Override
	public void notifyMessage() {
		getLogger().info("Calling the " + this.getClass().getName() + " starting the job at :" + new Date().toString());
		if (getSubscriptionList() == null || getSubscriptionList().isEmpty()) {
			getLogger().info("No subscribers found ");
			return;
		}
		for (Subscription subscriber : getSubscriptionList()) {
			MessageObject msgObject = getMessageObject(subscriber);
			getLogger().info("Sending the SMS notification to " + subscriber.getContactNumber());
			String mobileNumber = subscriber.getContactNumber();
			if(isMessageAlreadySentToday(mobileNumber)){
				getLogger().info("Message already sent to " + mobileNumber);
				continue;
			}
			Object value = smsMessenger.communicate(msgObject);
			logSMSDate(mobileNumber);
			getLogger().info("Status of communication - " + subscriber.getContactNumber() + ":" + value.toString());
			getLogger().info("Message Length :" + msgObject.getBodyOfMessage().length() + " Characters..");
		}
	}

	@Override
	public void run() {
		this.notifyMessage();
		getLogger().info("Job completed at .. " + new Date());
	}

	
	public MessageObject getMessageObject(Subscription subscriptionInfo) {
		BibleVerse bVerse = dailyVerseManager.getTodayVerse();
		String displayText = "";
		boolean isUnicode = false;
		String todayBibleReading = "";
		if (subscriptionInfo.getPreferredLanguage().equalsIgnoreCase("English")) {
			todayBibleReading = getTodayBibleChapterForDayInYear("en");
			displayText = "Today's Verse:" + bVerse.getEngVerse() + " " + bVerse.getEngChapter()
					+ "| Today's Bible Reading:" + todayBibleReading + "| www.praiseyourredeemer.org";
			isUnicode = false;
		} else if (subscriptionInfo.getPreferredLanguage().equalsIgnoreCase("Tamil")) {
			todayBibleReading = getTodayBibleChapterForDayInYear("ta");
			isUnicode = true;
			displayText = "\u0b87\u0ba9\u0bcd\u0bb1\u0baf\u0020\u0bb5\u0b9a\u0ba9\u0bae\u0bcd\u0020\u003a";
			String bVerseObj = PYRUtility.convertUnicodeToString(bVerse.getVerse());
			String chapterObj = PYRUtility.convertUnicodeToString(bVerse.getChapter());
			displayText = displayText + "\u002d" + bVerseObj + "\u003a" + chapterObj + " |";
			displayText = displayText + "-";
			displayText = displayText
					+ "\u0020\u0b87\u0ba9\u0bcd\u0bb1\u0bc8\u0baf\u0020\u0020\u0bb5\u0bc7\u0ba4\u0020\u0bb5\u0bbe\u0b9a\u0bbf\u0baa\u0bcd\u0baa\u0bc1\u0020\u003a";
			displayText = displayText + todayBibleReading + "| www.praiseyourredeemer.org?lang=ta";
			// displayText = PYRUtility.convertUnicodeToString(displayText);
			getLogger().info(displayText);
			// return null;
		}
		DailySMSMessageObject messageObject = new DailySMSMessageObject(displayText);
		messageObject.setUnicode(isUnicode);
		messageObject.setContactNumber(subscriptionInfo.getContactNumber());
		messageObject.setDestinationCountryName(subscriptionInfo.getCountry());
		return messageObject;
	}

	private String getTodayBibleChapterForDayInYear(String languageCode) {
		String todayChapter = "";
		SimpleDateFormat sdfObject = new SimpleDateFormat("MMM d");
		Date today = new Date();
		String formattedDate = sdfObject.format(today);
		try {
			todayChapter = oneYearBible.getTodayChapter(formattedDate, languageCode);
		} catch (IllegalArgumentException exp) {
			getLogger().error(exp.getMessage(), exp);
			getLogger().error("Error in getting today's chapter");
		}
		return todayChapter;
	}

	private DailySMSLogger getSMSLogger(String mobileNumber) {
		DailySMSLogger dailySMSLogger = (DailySMSLogger) daoService.getObjectsById(DailySMSLogger.class, "mobileNumber",
				mobileNumber);
		return dailySMSLogger;
	}

	private boolean isMessageAlreadySentToday(String mobileNumber) {
		boolean flag = false;
		DailySMSLogger dailySMSLogger = getSMSLogger(mobileNumber);
		if (dailySMSLogger == null) {
			return flag;
		}
		Date sentDate = dailySMSLogger.getLastSentDate();

		Date today = new Date();
		String tDay = sdf.format(today);

		String lsDate = sdf.format(sentDate);
		if (tDay.equals(lsDate)) {
			flag = true;
		}
		return flag;
	}

	private void logSMSDate(String mobileNumber) {
		DailySMSLogger smsLogger = getSMSLogger(mobileNumber);
		if (smsLogger == null) {
			smsLogger = new DailySMSLogger();
			smsLogger.setMobileNumber(mobileNumber);
			smsLogger.setLastSentDate(new Date());
			daoService.saveObject(smsLogger);
			getLogger().info("Created a new entry for daily SMS logger" + smsLogger.toString());
			return;
		} else {
			smsLogger.setLastSentDate(new Date());
			daoService.updateEntity(smsLogger);
			getLogger().info("Updated the entry for daily SMS logger" + smsLogger.toString());
		}
	}
	
	public List<Subscription> getSubscriptionList() {
		subscriptionList.clear();
		
		for(Object subscriber : getAllSubscribers()){
			Subscription obj = (Subscription) subscriber;
			// Forward the SMS to others by receiving at one mobile.. cost cutting
			if(obj.getContactNumber().equalsIgnoreCase("7401504728")){
			subscriptionList.add(obj);
			break;
			}
			
		}
		return subscriptionList;
	}

	public void setSubscriptionList(List<Subscription> subscriptionList) {
		this.subscriptionList = subscriptionList;
	}
	
	private List<Object> getAllSubscribers(){
		List<Object> objects = daoService.getObjectsListById(Subscription.class, "approved", new Boolean(true), "=",-1);
		if(objects == null){
			return Collections.emptyList();
		}
		return objects;
	}

	@Override
	public MessageObject getMessageObject(Object messageObj) {		
		return getMessageObject((Subscription) messageObj);
	}

}
