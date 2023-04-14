/**
 * 
 */
package com.pradheep.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pradheep.dao.model.BibleQuizEng;
import com.pradheep.dao.model.BibleQuizTaker;
import com.pradheep.dao.model.BibleQuizTamil;
import com.pradheep.dao.model.DailyBibleQuiz;
import com.pradheep.dao.model.DailyQuizWinner;
import com.pradheep.web.common.DailyQuizManager;
import com.pradheep.web.common.MonitorHitCounter;
import com.pradheep.web.common.PYRUtility;
import com.pradheep.web.common.PagePath;

/**
 * @author Pradheep
 *
 */
@Controller
public class DailyQuizController extends BaseUtility {

	@Autowired
	private DailyQuizManager dailyQuizManager;

	@RequestMapping("/dailyQuiz")
	@MonitorHitCounter(path = PagePath.DAILY_QUIZ_PAGE)
	public ModelAndView getDailyQuiz(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("Inside daily quiz page");
		String language = request.getParameter("lang");
		String errorMsg = "";
		BibleQuizTaker bibleQuizTaker = new BibleQuizTaker();
		ModelAndView model = new ModelAndView(PagePath.DAILY_QUIZ_PAGE, "command", bibleQuizTaker);
		DailyBibleQuiz dailyBibleQuiz = null;

		getLogger().error("Unable to find the quiz id");
		getLogger().info("Printing the language:" + language);
		String lang = "";
		if (language.equalsIgnoreCase("en") || language.equalsIgnoreCase("english")) {
			lang = "English";
		} else if (language.equalsIgnoreCase("ta") || language.equalsIgnoreCase("tamil")) {
			lang = "Tamil";
		}
		dailyBibleQuiz = dailyQuizManager.getBibleQuizByDateAndLanguage(lang);
		if (null != dailyBibleQuiz) {
			if (dailyBibleQuiz.getLanguage().equalsIgnoreCase("English")) {
				BibleQuizEng bibleQuizEng = dailyQuizManager.getEngBibleQuiz(dailyBibleQuiz.getQuizId());
				bibleQuizTaker.setQuizId(String.valueOf(bibleQuizEng.getId()));
				bibleQuizTaker.setLanguage("English");
				model.addObject("todayQuiz", bibleQuizEng);
			} else if (dailyBibleQuiz.getLanguage().equalsIgnoreCase("Tamil")) {
				BibleQuizTamil bibleQuizTam = dailyQuizManager.getTamilBibleQuiz(dailyBibleQuiz.getQuizId());
				bibleQuizTaker.setQuizId(String.valueOf(bibleQuizTam.getId()));
				bibleQuizTaker.setLanguage("Tamil");
				bibleQuizTam.setQuestion(PYRUtility.convertUnicodeToString(bibleQuizTam.getQuestion()));
				bibleQuizTam.setChoice(PYRUtility.convertUnicodeToString(bibleQuizTam.getChoice()));
				model.addObject("todayQuiz", bibleQuizTam);
			}
		} else {
			errorMsg = "Error while loading the quiz.";
			getLogger().error("Error while loading the quiz !");
		}
		model.addObject("errorMsg", errorMsg);
		Locale locale = applicationLocaleResolver.resolveLocale(request);
		setLanguageBasedStyle(locale, model);
		model.addObject("context", request.getContextPath());
		model.addObject("quizDate", getQuizDate());
		return model;
	}

	@RequestMapping("/submitDailyQuiz")
	@MonitorHitCounter(path = PagePath.SUBMIT_DAILY_QUIZ_PAGE)
	public ModelAndView submitDailyQuiz(@ModelAttribute("bibleQuizTaker") BibleQuizTaker bibleQuizTaker,
			BindingResult result, HttpServletRequest request, HttpServletResponse reponse) {
		String quizResult = "";
		String correctAns = "";
		String reference = "";
		String question = "";
		ModelAndView model = new ModelAndView(PagePath.SUBMIT_DAILY_QUIZ_PAGE);
		Integer quizId = null;
		if (null != bibleQuizTaker.getQuizId()) {
			quizId = Integer.parseInt(bibleQuizTaker.getQuizId());
		}
		if (bibleQuizTaker.getLanguage().equalsIgnoreCase("English")) {
			if (null != quizId) {
				BibleQuizEng bibleQuizEng = dailyQuizManager.getEngBibleQuiz(quizId);
				correctAns = bibleQuizEng.getCorrectAnswer();
				reference = bibleQuizEng.getBibleReference();
				question = bibleQuizEng.getQuestion();
			}
		} else if (bibleQuizTaker.getLanguage().equalsIgnoreCase("Tamil")) {
			if (null != quizId) {
				BibleQuizTamil bibleQuizTa = dailyQuizManager.getTamilBibleQuiz(quizId);
				correctAns = PYRUtility.convertUnicodeToString(bibleQuizTa.getCorrectAnswer());
				reference = PYRUtility.convertUnicodeToString(bibleQuizTa.getBibleReference());
				question = PYRUtility.convertUnicodeToString(bibleQuizTa.getQuestion());
			}
		}
		if (correctAns.equals(bibleQuizTaker.getAnswer())) {
			quizResult = "Correct";
			markSuccess(bibleQuizTaker.getName(), bibleQuizTaker.getEmail(), quizId,
					bibleQuizTaker.getLanguage().equalsIgnoreCase("English") ? "En" : "Ta");
		} else {
			quizResult = "not correct";
		}
		getLogger()
				.info("Quiz has been submitted:" + bibleQuizTaker.toString() + "," + quizResult + "," + getQuizDate());
		model.addObject("result", quizResult);
		model.addObject("correctAnswer", correctAns);
		model.addObject("question", question);
		model.addObject("name", bibleQuizTaker.getName());
		model.addObject("reference", reference);
		model.addObject("yesterdayDate", dailyQuizManager.getYesterdayDateFormatted());
		model.addObject("previousDayWinnersEN", getPreviousDayWinners("En"));
		model.addObject("previousDayWinnersTA", getPreviousDayWinners("Ta"));
		Locale locale = applicationLocaleResolver.resolveLocale(request);
		setLanguageBasedStyle(locale, model);
		return model;
	}

	private DailyBibleQuiz getDailyBibleQuiz(int quizId) {
		return dailyQuizManager.getBibleQuizById(quizId);
	}

	private String decodeDateFromRequest(String code) {
		return publicUtility.DecryptText(code);
	}

	private String getQuizDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		return sdf.format(new Date());
	}

	private void markSuccess(String name, String email, int quizId, String language) {
		getLogger().info("Marking the success entry for " + name + ", Email:" + email + ",id:" + quizId + ",Language:"
				+ language);
		DailyQuizWinner quizWinner = new DailyQuizWinner();
		quizWinner.setEmail(email);
		quizWinner.setLanguage(language);
		quizWinner.setQuiz_id(quizId);
		quizWinner.setName(name);
		dailyQuizManager.saveBibleQuizWinner(quizWinner);
	}

	private List<DailyQuizWinner> getPreviousDayWinners(String language) {
		List<DailyQuizWinner> dailyQuizWinnersList = new ArrayList<DailyQuizWinner>();
		String yesterdayDate = dailyQuizManager.getYesterdayDateFormatted();
		String sql = "SELECT * FROM daily_quiz_winner WHERE answer_time like '%" + yesterdayDate + "%' and language ='" + language +"' order by answer_time limit 10";		
		List<Object> list = dailyQuizManager.runNativeQuery(sql,DailyQuizWinner.class);
		getLogger().info("--- >"+list);
		if (list != null) {
			list.forEach(x -> {
				getLogger().info(x.getClass());
				if (x instanceof DailyQuizWinner) {
					DailyQuizWinner winner = (DailyQuizWinner) x;
					dailyQuizWinnersList.add(winner);
					getLogger().info("Printing the winner" + winner.toString());
				}
			});
		}else{
			getLogger().info("No winners found for " + yesterdayDate);
		}
		return dailyQuizWinnersList;
	}

}
