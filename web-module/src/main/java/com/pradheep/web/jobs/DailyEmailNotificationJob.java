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
import com.pradheep.web.common.ApplicationConstants;
import com.pradheep.web.common.DailyVerseManager;
import com.pradheep.web.common.OneYearBible;
import com.pradheep.web.common.PYRUtility;
import com.pyr.messenger.PyrMessenger;
import com.pyr.notification.EmailMessageObject;
import com.pyr.notification.MessageObject;
import com.pyr.templates.DailySMSMessageObject;

/**
 * @author pradheep.p
 *
 */
public class DailyEmailNotificationJob extends NotificationJob {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");

	@Autowired
	private DailyVerseManager dailyVerseManager;

	@Autowired
	@Qualifier("oneYearBibleManager")
	private OneYearBible oneYearBible;
	
	private List<Subscription> subscriptionList = new ArrayList<Subscription>();

	public DailyEmailNotificationJob() {
		setNotificationType(NotificationService.NOTIFICATION_TYPE_EMAIL);
		setJobFrequency(NotificationJob.JOB_FREQUENCY_DAILY_HRS);
	}

	@Override
	public void notifyMessage() {
		getLogger().info("----------- Email notification service --------------");
		getLogger().info("Calling the " + this.getClass().getName() + " starting the job at :" + new Date().toString());
		if (getSubscriptionList() == null || getSubscriptionList().isEmpty()) {
			getLogger().info("No subscribers found ");
			return;
		}
		for (Subscription subscriber : getSubscriptionList()) {
			MessageObject msgObject = getMessageObject(subscriber);
			getLogger().info("Sending the Email notification to " + subscriber.getEmailId());
			Object value = pryMessenger.communicate(msgObject);			
			getLogger().info("Status of communication - " + subscriber.getContactNumber() + ":" + value.toString());			
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
		
		String todayBibleReading = "";
		if (subscriptionInfo.getPreferredLanguage().equalsIgnoreCase("English")) {
			todayBibleReading = getTodayBibleChapterForDayInYear("en");
			displayText = "Today's Verse:" + bVerse.getEngVerse() + " " + bVerse.getEngChapter()
					+ "| Today's Bible Reading:" + todayBibleReading + "| www.praiseyourredeemer.org";
			
		} else if (subscriptionInfo.getPreferredLanguage().equalsIgnoreCase("Tamil")) {
			todayBibleReading = getTodayBibleChapterForDayInYear("ta");
			
			displayText = "\u0b87\u0ba9\u0bcd\u0bb1\u0baf\u0020\u0bb5\u0b9a\u0ba9\u0bae\u0bcd\u0020\u003a";
			String bVerseObj = PYRUtility.convertUnicodeToString(bVerse.getVerse());
			String chapterObj = PYRUtility.convertUnicodeToString(bVerse.getChapter());
			displayText = displayText + "\u002d" + bVerseObj + "\u003a" + chapterObj + " |";
			displayText = displayText + "-";
			displayText = displayText
					+ "\u0020\u0b87\u0ba9\u0bcd\u0bb1\u0bc8\u0baf\u0020\u0020\u0bb5\u0bc7\u0ba4\u0020\u0bb5\u0bbe\u0b9a\u0bbf\u0baa\u0bcd\u0baa\u0bc1\u0020\u003a";
			displayText = displayText + todayBibleReading + "| www.praiseyourredeemer.org?lang=ta";
			displayText = PYRUtility.convertUnicodeToString(displayText);
			getLogger().info(displayText);
			// return null;
		}
		EmailMessageObject messageObject = new EmailMessageObject();
		messageObject.setToList(new String[]{subscriptionInfo.getEmailId()});
		messageObject.setBodyOfMessage(displayText);
		messageObject.setFromAddress(ApplicationConstants.FROM_EMAIL_ADDRESS);
		messageObject.setFooterInformation("From PraiseYourRedeemer Ministry");
		messageObject.setSubjectOfMessage("Daily Message : " + sdf.format(new Date()));		
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

	public List<Subscription> getSubscriptionList() {
		subscriptionList.clear();
		
		for(Object subscriber : getAllSubscribers()){
			Subscription obj = (Subscription) subscriber;			
			subscriptionList.add(obj);			
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
