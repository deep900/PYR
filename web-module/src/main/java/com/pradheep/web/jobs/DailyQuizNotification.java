/**
 * 
 */
package com.pradheep.web.jobs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pradheep.dao.model.DailyBibleQuiz;
import com.pradheep.dao.model.Subscription;
import com.pradheep.web.common.DailyQuizManager;
import com.pradheep.web.common.SubscriptionManager;
import com.pry.security.utility.PublicUtility;
import com.pyr.notification.EmailMessageObject;
import com.pyr.notification.MessageObject;

/**
 * @author Pradheep
 * Daily send subscribers information on Bible quiz. 
 */
public class DailyQuizNotification extends NotificationJob {	

	@Autowired
	private DailyQuizManager dailyQuizManager;

	@Autowired
	private SubscriptionManager subscriptionManager;
	
	@Autowired
	private PublicUtility publicUtility;

	public DailyQuizNotification() {
		setNotificationType(NotificationService.NOTIFICATION_TYPE_EMAIL);
		setJobFrequency(NotificationJob.JOB_FREQUENCY_DAILY_HRS);
	}

	@Override
	public void run() {
		getLogger().info("Running daily quiz notification at" + getTodaysDate());
		notifyMessage();
	}

	@Override
	public void notifyMessage() {
		getLogger().info("Notifying daily quiz for : " + getTodaysDate());
		List<Subscription> subscriptionList = subscriptionManager.getSubscriptionList();
		if (null != subscriptionList && !subscriptionList.isEmpty()) {
			subscriptionList.forEach(subscription -> {
				Object status = pryMessenger.communicate(getMessageObject(subscription));
				getLogger().info("Printing the status:" + status.toString());
			});
		} else {
			getLogger().info("No subscribers found for daily quiz.");
		}
	}

	@Override
	public MessageObject getMessageObject(Object subscription) {		
		List<DailyBibleQuiz> dailyBibleQuizList = dailyQuizManager.getBibleQuizByDate(dailyQuizManager.getCurrentQuizDate());
		StringBuffer message = new StringBuffer();
		message.append("<font face='cambria'><u>Today's bible quiz -  " + getTodaysDate() +"</u>");
		String URL = "www.praiseyourredeemer.org/dailyQuiz";
		if (null == dailyBibleQuizList) {
			message = message.append("No quiz available for today");
			getLogger().info(message);
		} else {
			message.append("<br> Click the link below to participate in today's quiz.");
			Iterator<DailyBibleQuiz> dailyQuizIterator = dailyBibleQuizList.iterator();
			while(dailyQuizIterator.hasNext()){
			DailyBibleQuiz dailyBibleQuiz = dailyQuizIterator.next();	
			message.append("<br><b>" + dailyBibleQuiz.getLanguage() + " Quiz </b><br>");
			message.append(URL + "?quizId=" + encryptQuizId(dailyBibleQuiz.getQuizId()) + "<br><br>");
			}			
		}
		message.append("<br> - Praise your redeemer ministry.</font>");
		Subscription subscriptionObj = (Subscription) subscription;
		EmailMessageObject messageObject = new EmailMessageObject();
		messageObject.setBodyOfMessage(message.toString());
		messageObject.setToList(new String[] { subscriptionObj.getEmailId() });
		messageObject.setFromAddress("administrator@praiseyourredeemer.org");
		messageObject.setSubjectOfMessage("Daily Quiz-" + getTodaysDate());
		return messageObject;
	}	
	
	private String encryptQuizId(Integer quizId){
		return publicUtility.EncryptText(String.valueOf(quizId));
	}

	private String getTodaysDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		return sdf.format(new Date());
	}

}
