/**
 * 
 */
package com.pradheep.web.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		//insertDailyQuiz();
		return daoService.getObjectsListId(DailyBibleQuiz.class, "quizDate", quizDate);
	}
	
	public List<Integer> getExistingDailyBibleQuizIds(String language){
		List dailyBibleQuizList = daoService.getObjectsListById(DailyBibleQuiz.class,"language",language,"=",-1);
		ArrayList<Integer> dailyQuizIds = new ArrayList<Integer>();
		Iterator<DailyBibleQuiz> iterator = dailyBibleQuizList.iterator();
		while(iterator.hasNext()) {
			DailyBibleQuiz quiz = iterator.next();
			dailyQuizIds.add(quiz.getQuizId());
		}
		return dailyQuizIds;
	}

	private void insertDailyQuiz() {
		List<Integer> existingEngQuizIds = getExistingDailyBibleQuizIds("English");
		List<Integer> existingTaQuizIds = getExistingDailyBibleQuizIds("Tamil");
		getLogger().info("Printing the existing english ids" + existingEngQuizIds);
		getLogger().info("Printing the existing tamil ids:" + existingTaQuizIds);
		BibleQuizEng quizEng = getRandomEngBibleQuiz(existingEngQuizIds);
		if (quizEng == null) {
			getLogger().info("No bible quiz found for english");
		} else {			
			DailyBibleQuiz dailyBibleQuiz = new DailyBibleQuiz();
			dailyBibleQuiz.setLanguage("English");
			dailyBibleQuiz.setQuizId(quizEng.getId());
			dailyBibleQuiz.setQuizDate(getCurrentQuizDate());
			daoService.saveOrUpdateEntity(dailyBibleQuiz);
			getLogger().info("Persisted the entity :" + dailyBibleQuiz.toString());
		}		
		BibleQuizTamil quizTamil = getRandomTamBibleQuiz(existingTaQuizIds);
		if (null == quizTamil) {
			getLogger().info("No bible quiz found for tamil.");
		} else {			
			DailyBibleQuiz dailyBibleQuiz = new DailyBibleQuiz();
			dailyBibleQuiz.setLanguage("Tamil");
			dailyBibleQuiz.setQuizDate(getCurrentQuizDate());
			dailyBibleQuiz.setQuizId(quizTamil.getId());
			daoService.saveOrUpdateEntity(dailyBibleQuiz);
		}
	}	

	private BibleQuizEng getRandomEngBibleQuiz(List<Integer> existingQuizIds) {
		List<Object> engBibleQuizList = null;
		/*try {
			engBibleQuizList = daoService.queryUsingNativeSQL(
					"select * from bible_quiz_en where id not in (select distinct quiz_id from daily_bible_quiz where language= 'English') limit 1");
		getLogger().info("English quiz list: " + engBibleQuizList.toString()); 	
		} catch (Exception err) {
			getLogger().error("Error while fetching results.", err);
		}*/
		List bibleQuizEngList = daoService.getObjectsNotIn(BibleQuizEng.class, "Id", existingQuizIds);
		if (null != engBibleQuizList && !engBibleQuizList.isEmpty()) {
			return (BibleQuizEng) engBibleQuizList.get(0);
		}
		return null;
	}

	private BibleQuizTamil getRandomTamBibleQuiz(List<Integer> existingQuizIds) {
		List tamBibleQuizList = daoService.getObjectsNotIn(BibleQuizTamil.class, "Id", existingQuizIds);
		if (null != tamBibleQuizList && !tamBibleQuizList.isEmpty()) {
			return (BibleQuizTamil) tamBibleQuizList.get(0);
		}
		return null;
	}	

	public DailyBibleQuiz getBibleQuizById(Integer quizId) {
		DailyBibleQuiz dailyBibleQuiz = (DailyBibleQuiz) daoService.getObjectsById(DailyBibleQuiz.class, "quizId",
				quizId.toString());
		System.out.println("Printing the daily quiz" + dailyBibleQuiz);
		return dailyBibleQuiz;
	}

	public String getCurrentQuizDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String parsedDate = sdf.format(new Date());
		return parsedDate;
	}

	public DailyBibleQuiz getBibleQuizByDateAndLanguage(String language) {
		String[] queryParameters = new String[] { "quizDate", "language" };
		Object[] reference = new Object[] { getCurrentQuizDate(), language };
		String[] symbol = new String[] { "=", "=" };
		List<DailyBibleQuiz> bibleQuizList = (List) daoService.getObjectsListByMultipleParameters(DailyBibleQuiz.class,
				queryParameters, reference, symbol, -1, false);
		if (bibleQuizList != null && !bibleQuizList.isEmpty()) {
			return bibleQuizList.get(0);
		}
		return null;
	}

	public BibleQuizEng getEngBibleQuiz(Integer quizId) {
		BibleQuizEng engQuiz = (BibleQuizEng) daoService.getObjectsById(BibleQuizEng.class, "id", quizId.toString());
		return engQuiz;
	}

	public BibleQuizTamil getTamilBibleQuiz(Integer quizId) {
		BibleQuizTamil bibleQuizTam = (BibleQuizTamil) daoService.getObjectsById(BibleQuizTamil.class, "id",
				quizId.toString());
		return bibleQuizTam;
	} 
	
	public void saveBibleQuiz(DailyBibleQuiz dailyBibleQuiz) {
		daoService.saveOrUpdateEntity(dailyBibleQuiz);
		getLogger().info("Persisted the daily bible quiz");
	}
	
	public List<Object> runNativeQuery(String sql) {
		return daoService.queryUsingNativeSQL(sql);
	}

}
