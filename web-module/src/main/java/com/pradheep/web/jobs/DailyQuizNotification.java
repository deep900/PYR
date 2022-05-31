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
	
	private int dailyIncrement = 1;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

	public DailyQuizNotification() {
		setNotificationType(NotificationService.NOTIFICATION_TYPE_EMAIL);
		setJobFrequency(NotificationJob.JOB_FREQUENCY_DAILY_HRS);
	}

	@Override
	public void run() {
		try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {			
			getLogger().error("Interrupted exception", e);
		}
		getLogger().info("Running daily quiz notification at" + getTodaysDate());
		notifyMessage();
		try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {			
			getLogger().error("Interrupted exception", e);
		}
		updateNextQuizRecord();
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
	
	private List<DailyBibleQuiz> getDailyQuiz(){
		List<DailyBibleQuiz> dailyBibleQuizList = dailyQuizManager.getBibleQuizByDate(dailyQuizManager.getCurrentQuizDate());
		return dailyBibleQuizList;
	}

	@Override
	public MessageObject getMessageObject(Object subscription) {
		List<DailyBibleQuiz> dailyBibleQuizList = getDailyQuiz();
		StringBuffer message = new StringBuffer();
		message.append("<font face='cambria'><u>Today's bible quiz -  " + getTodaysDate() +"</u>");
		String URL = "www.praiseyourredeemer.org/dailyQuiz";
		if (null == dailyBibleQuizList) {
			message = message.append("No quiz available for today");
			getLogger().info(message);
		} else {
			message.append("<br> Click the link below to participate in today's quiz.");
			Iterator<DailyBibleQuiz> dailyQuizIterator = dailyBibleQuizList.iterator();
			String lang = "";
			while(dailyQuizIterator.hasNext()){
			DailyBibleQuiz dailyBibleQuiz = dailyQuizIterator.next();	
			if(dailyBibleQuiz.getLanguage().equalsIgnoreCase("English")){
				lang = "en";
			}else if(dailyBibleQuiz.getLanguage().equalsIgnoreCase("Tamil")){
				lang = "ta";
			}
			message.append("<br><b>" + dailyBibleQuiz.getLanguage() + " Quiz </b><br>");
			message.append(URL + "?lang="+ lang + "<br><br>");
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

	private String getTodaysDate() {		
		return sdf.format(new Date());
	}
	
	public int getNextQuizId(String language,int existingId) {
		String sql;
		if(language.equalsIgnoreCase("english")){
			sql = "select id from bible_quiz_en where id > " + existingId + " limit 1";					
		} else{
			sql = "select id from bible_quiz_ta where id > " + existingId + " limit 1";
		}	
		List<Object> quizIds = dailyQuizManager.runNativeQuery(sql);
		if(null != quizIds && !quizIds.isEmpty()) {
			getLogger().info(quizIds.toString());
			String quizId = quizIds.get(0).toString();
			getLogger().info("Next quiz id:" + quizId);
			return Integer.parseInt(quizId);
		}
		getLogger().error("Unable to find the next quiz id");
		return existingId;		
	}
	
	private void updateNextQuizRecord(){
		List<DailyBibleQuiz> dailyBibleQuizList = getDailyQuiz();
		if(null == dailyBibleQuizList || dailyBibleQuizList.isEmpty()) {
			getLogger().error("No daily bible quiz found to update the next date.");
			return;
		}
		Date today = new Date();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(today);
		calendar.add(GregorianCalendar.DAY_OF_MONTH,dailyIncrement);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String nextQuizDate = sdf1.format(calendar.getTime());
		getLogger().info("Printing the next quiz date:" + nextQuizDate);
		dailyBibleQuizList.forEach(dailyBibleQuiz -> {			
			dailyQuizManager.saveBibleQuiz(createNewBibleQuiz(nextQuizDate,
					getNextQuizId(dailyBibleQuiz.getLanguage(), dailyBibleQuiz.getQuizId()),dailyBibleQuiz.getLanguage()));
		});		
	}
	
	private DailyBibleQuiz createNewBibleQuiz(String nextQuizDate, int quizId,String language) {
		DailyBibleQuiz bibleQuiz = new DailyBibleQuiz();
		bibleQuiz.setQuizDate(nextQuizDate);
		bibleQuiz.setQuizId(quizId);
		bibleQuiz.setLanguage(language);		
		return bibleQuiz;
	}

}
