/**
 * 
 */
package com.pradheep.web.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.pradheep.dao.config.ApplicationLogger;
import com.pradheep.dao.config.DAOService;
import com.pradheep.dao.model.BibleQuizEng;
import com.pradheep.dao.model.BibleQuizTamil;
import com.pradheep.dao.model.DailyBibleQuiz;
import com.pry.security.utility.PublicUtility;

/**
 * @author deep90
 *
 */
public class DailyQuizManager {

	@Autowired
	private DAOService daoService;
	
	private Logger logger;
	
	@Autowired
	private PublicUtility publicUtility;
	
	private Logger getLogger() {
		if (logger == null) {
			return ApplicationLogger.getLogBean(this.getClass());
		}
		return logger;
	}

	public List<DailyBibleQuiz> getBibleQuizByDate(String quizDate) {
		insertDailyQuiz();
		return daoService.getObjectsListId(DailyBibleQuiz.class, "quizDate", quizDate);		
	}

	private void insertDailyQuiz() {
		BibleQuizEng quizEng = getRandomEngBibleQuiz();
		if(quizEng == null) {
			getLogger().info("No bible quiz found for english");
		}else {
			DailyBibleQuiz dailyBibleQuiz = new DailyBibleQuiz();
			dailyBibleQuiz.setLanguage("English");
			dailyBibleQuiz.setQuizId(quizEng.getId());
			dailyBibleQuiz.setQuizDate(getCurrentQuizDate());
			daoService.saveObject(dailyBibleQuiz);
		}
		BibleQuizTamil quizTamil = getRandomTamBibleQuiz();
		if(null == quizTamil) {
			getLogger().info("No bible quiz found for tamil.");
		}else {
			DailyBibleQuiz dailyBibleQuiz = new DailyBibleQuiz();
			dailyBibleQuiz.setLanguage("Tamil");
			dailyBibleQuiz.setQuizDate(getCurrentQuizDate());
			dailyBibleQuiz.setQuizId(quizTamil.getId());
			daoService.saveObject(dailyBibleQuiz);
		}
		
	}

	private String getExistingDailyQuizIdForEnglish() {
		return getIdsForQuiz("SELECT quiz_id from daily_bible_quiz where language= 'English'");
	}

	private String getExistingDailyQuizIdForTamil() {
		return getIdsForQuiz("SELECT quiz_id from daily_bible_quiz where language= 'Tamil'");
	}

	private BibleQuizEng getRandomEngBibleQuiz() {
		String idsEnglish = getExistingDailyQuizIdForEnglish();
		if (idsEnglish.length() > 0) {
			List<Object> engBibleQuizList = daoService
					.queryUsingNativeSQL("SELECT * FROM bible_quiz_en where id not in (" + idsEnglish + ")");
			if (null != engBibleQuizList && !engBibleQuizList.isEmpty()) {
				return (BibleQuizEng) engBibleQuizList.get(0);
			}
		} else {
			List<Object> engBibleQuizList = daoService.getObjects(BibleQuizEng.class);
			if (null != engBibleQuizList && !engBibleQuizList.isEmpty()) {
				return (BibleQuizEng) engBibleQuizList.get(0);
			}
		}
		return null;
	}
	
	private BibleQuizTamil getRandomTamBibleQuiz() {
		String idsEnglish = getExistingDailyQuizIdForTamil();
		if (idsEnglish.length() > 0) {
			List<Object> tamBibleQuizList = daoService
					.queryUsingNativeSQL("SELECT * FROM bible_quiz_ta where id not in (" + idsEnglish + ")");
			if (null != tamBibleQuizList && !tamBibleQuizList.isEmpty()) {
				return (BibleQuizTamil) tamBibleQuizList.get(0);
			}
		} else {
			List<Object> tamBibleQuizList = daoService.getObjects(BibleQuizTamil.class);
			if (null != tamBibleQuizList && !tamBibleQuizList.isEmpty()) {
				return (BibleQuizTamil) tamBibleQuizList.get(0);
			}
		}
		return null;
	}

	private String getIdsForQuiz(String query) {
		List<Object> ids = daoService.queryUsingNativeSQL(query);
		StringBuffer idsVal = new StringBuffer();
		if (null != ids && !ids.isEmpty()) {
			Iterator<Object> idsIterator = ids.iterator();
			while (idsIterator.hasNext()) {
				String id = idsIterator.next().toString();
				idsVal.append(id);
				if (idsIterator.hasNext()) {
					idsVal.append(",");
				}
			}
		}
		return idsVal.toString();
	}
	
	public DailyBibleQuiz getBibleQuizById(Integer quizId) {		
		DailyBibleQuiz dailyBibleQuiz = (DailyBibleQuiz) daoService.getObjectsById(DailyBibleQuiz.class, "quizId",quizId.toString());
		System.out.println("Printing the daily quiz" + dailyBibleQuiz);
		return dailyBibleQuiz;
	}
	
	public String getCurrentQuizDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String parsedDate = sdf.format(new Date());
		return parsedDate;
	}
	
	public DailyBibleQuiz getBibleQuizByDateAndLanguage(String language){		
		String[] queryParameters = new String[]{"quizDate","language"};
		Object[] reference = new Object[] {getCurrentQuizDate(),language};
		String[] symbol = new String[]{"=","="};
		List<DailyBibleQuiz> bibleQuizList = (List) daoService.getObjectsListByMultipleParameters(DailyBibleQuiz.class, queryParameters, reference, symbol, -1, false);
		if(bibleQuizList != null && !bibleQuizList.isEmpty()){
			return bibleQuizList.get(0);
		}
		return null;
	}
	
	public BibleQuizEng getEngBibleQuiz(Integer quizId) {
		BibleQuizEng engQuiz = (BibleQuizEng) daoService.getObjectsById(BibleQuizEng.class,"id" , quizId.toString());
		return engQuiz;
	}
	
	public BibleQuizTamil getTamilBibleQuiz(Integer quizId) {
		BibleQuizTamil bibleQuizTam = (BibleQuizTamil) daoService.getObjectsById(BibleQuizTamil.class, "id", quizId.toString());
		return bibleQuizTam;
	}
	
	
}
