package com.pradheep.web.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.pradheep.dao.config.DAOService;
import com.pradheep.dao.model.BibleQuiz;
import com.pradheep.dao.model.BibleQuizEng;
import com.pradheep.dao.model.BibleQuizTamil;
import com.pradheep.dao.model.QuizBaseModel;
import com.pradheep.dao.model.QuizTakerEntry;

public class QuizHelper<T extends QuizBaseModel> {

	@Autowired
	private DAOService daoService;

	private Logger logger = null;

	public Logger getLogger() {
		if (logger == null) {
			logger = ApplicationLoggerWeb.getLogBean(getClass());
		}
		return logger;
	}

	public void mapUserResults(QuizResult userResult, List<T> bibleQuizQuestions) {
		getLogger().info("Mapping the user results");
		for (T obj : bibleQuizQuestions) {
			if (obj instanceof BibleQuizEng) {
				BibleQuizEng engQuiz = (BibleQuizEng) obj;
				if (userResult.userChoiceMap.containsKey(engQuiz.getId())
						&& userResult.userChoiceMap.get(engQuiz.getId()) != null) {
					engQuiz.setMyChoice(userResult.userChoiceMap.get(engQuiz.getId()).trim());
				} else {
					engQuiz.setMyChoice("No option selected");
				}
				engQuiz.setCorrectAnswer(engQuiz.getCorrectAnswer().trim());
			}
			if (obj instanceof BibleQuizTamil) {
				BibleQuizTamil tamQuiz = (BibleQuizTamil) obj;
				tamQuiz.setChoice(PYRUtility.convertUnicodeToString(tamQuiz.getChoice()));
				tamQuiz.setCorrectAnswer(PYRUtility.convertUnicodeToString(tamQuiz.getCorrectAnswer()));
				tamQuiz.setQuestion(PYRUtility.convertUnicodeToString(tamQuiz.getQuestion()));
				if (userResult.userChoiceMap.containsKey(tamQuiz.getId())
						&& userResult.userChoiceMap.get(tamQuiz.getId()) != null) {
					tamQuiz.setMyChoice(userResult.userChoiceMap.get(tamQuiz.getId()).trim());
				} else {
					tamQuiz.setMyChoice("No option selected");
				}
				tamQuiz.setCorrectAnswer(tamQuiz.getCorrectAnswer().trim());
			}
		}
	}

	public QuizResult getScore(String questionIds, HttpServletRequest request, String language) {
		int score = 0;
		int totalQuestions = 0;
		List<T> getQuestions = getQuestionByIds(questionIds, language);
		Iterator iter = getQuestions.iterator();
		QuizResult quizResult = new QuizResult();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (iter.hasNext()) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			totalQuestions = totalQuestions + 1;
			if (language.equalsIgnoreCase("en")) {
				BibleQuizEng obj = (BibleQuizEng) iter.next();
				int id = obj.getId();
				String correctAnswer = obj.getCorrectAnswer();
				String userChoice = "";
				if (request.getParameter(String.valueOf(id)) == null) {
					userChoice = "No option selected";
				} else {
					Integer choice = Integer.parseInt(request.getParameter(String.valueOf(id)));
					userChoice = getAnswerByToken(choice, obj, "en");
				}
				quizResult.userChoiceMap.put(id, userChoice);
				obj.setMyChoice(userChoice);
				if (userChoice == null) {
					userChoice = "No option selected";
				}
				if (userChoice.equalsIgnoreCase(correctAnswer)) {
					score = score + 1;
				}
			} else {
				BibleQuizTamil obj = (BibleQuizTamil) iter.next();
				int id = obj.getId();
				String correctAnswer = obj.getCorrectAnswer();
				String userChoice = "";
				if (request.getParameter(String.valueOf(id)) == null) {
					userChoice = "No option selected";
				} else {
					Integer choice = Integer.parseInt(request.getParameter(String.valueOf(id)));
					userChoice = getAnswerByToken(choice, obj, "ta");
				}
				quizResult.userChoiceMap.put(id, userChoice);
				obj.setMyChoice(userChoice);
				if (userChoice == null) {
					userChoice = "No option selected";
				}
				if (userChoice.equalsIgnoreCase(PYRUtility.convertUnicodeToString(correctAnswer))) {
					score = score + 1;
				}
			}
		}

		quizResult.setResult(score);
		quizResult.setTotalQuestions(totalQuestions);
		return quizResult;
	}

	private String getAnswerByToken(int token, BibleQuiz quiz, String language) {
		String delimiter;
		if (language.equalsIgnoreCase("en")) {
			delimiter = ",";
		} else {
			delimiter = ",";
		}
		String[] args = PYRUtility.convertUnicodeToString(quiz.getChoice()).split(delimiter);
		System.out.println("Printing the args .... " + quiz.getChoice());
		return args[token];
	}

	public List<T> getQuestionByIds(String ids, String language) {
		List<T> selectedQuestions = new ArrayList<T>();
		StringTokenizer tokenizer = new StringTokenizer(ids, ",");
		while (tokenizer.hasMoreTokens()) {
			String questionId = tokenizer.nextToken();
			if (language.equalsIgnoreCase("en")) {
				BibleQuizEng bibleQuizEng = new BibleQuizEng();
				selectedQuestions.add(getBibleQuizById((T) bibleQuizEng, questionId));
			} else {
				BibleQuizTamil bibleQuizTa = new BibleQuizTamil();
				selectedQuestions.add(getBibleQuizById((T) bibleQuizTa, questionId));
			}
		}
		return selectedQuestions;
	}

	public List<BibleQuizEng> convertToBibleQuizEng(List<Object> questionsList) {
		List<BibleQuizEng> bibleQuizEngList = new ArrayList<BibleQuizEng>();
		for (Object obj : questionsList) {
			if (obj instanceof BibleQuizEng) {
				BibleQuizEng bibleQuizEng = (BibleQuizEng) obj;
				bibleQuizEngList.add(bibleQuizEng);
			}
		}
		return bibleQuizEngList;
	}

	public List<BibleQuizTamil> convertToBibleQuizTamil(List<Object> questionsList) {
		List<BibleQuizTamil> bibleQuizTamilList = new ArrayList<BibleQuizTamil>();
		for (Object obj : questionsList) {
			if (obj instanceof BibleQuizTamil) {
				BibleQuizTamil bibleQuizTa = (BibleQuizTamil) obj;
				bibleQuizTa.setQuestion(PYRUtility.convertUnicodeToString(bibleQuizTa.getQuestion()));
				bibleQuizTa.setCorrectAnswer(PYRUtility.convertUnicodeToString(bibleQuizTa.getCorrectAnswer()));
				bibleQuizTa.setChoice(PYRUtility.convertUnicodeToString(bibleQuizTa.getChoice()));
				bibleQuizTamilList.add(bibleQuizTa);
			}
		}
		return bibleQuizTamilList;
	}

	public String getQuestionIds(List<BibleQuiz> bibleQuizList) {
		String arg = "";
		Iterator<BibleQuiz> iter = bibleQuizList.iterator();
		while (iter.hasNext()) {
			arg = arg + iter.next().getId();
			if (iter.hasNext()) {
				arg = arg + ",";
			}
		}
		return arg;
	}

	public List<Object> getQuestions(String language, String level, String testament) {
		List<Object> bibleQuizList = null;
		if (language.equalsIgnoreCase(ApplicationConstants.ENG_LANG)) {
			if (testament.equalsIgnoreCase("Both")) {
				bibleQuizList = daoService.getObjectsListById(BibleQuizEng.class, "level", level, "=",
						ApplicationConstants.MAX_QUESTIONS_PER_QUIZ);
			} else {
				String[] queryParams = new String[2];
				String[] reference = new String[2];
				String[] operators = new String[2];

				queryParams[0] = "level";
				queryParams[1] = "testament";

				reference[0] = level;
				if (testament.contains("Old")) {
					reference[1] = "Old";
				} else if (testament.contains("New")) {
					reference[1] = "New";
				}

				operators[0] = "=";
				operators[1] = "=";

				bibleQuizList = daoService.getObjectsListByMultipleParameters(BibleQuizEng.class, queryParams,
						reference, operators, ApplicationConstants.MAX_QUESTIONS_PER_QUIZ,true);
			}
			if (bibleQuizList == null) {
				return Collections.emptyList();
			}
			return bibleQuizList;
		}

		if (language.equalsIgnoreCase(ApplicationConstants.TA_LANG)) {
			if (testament.equalsIgnoreCase("Both")) {
				bibleQuizList = daoService.getObjectsListById(BibleQuizEng.class, "level", level, "=",
						ApplicationConstants.MAX_QUESTIONS_PER_QUIZ);
			} else {
				String[] queryParams = new String[2];
				String[] reference = new String[2];
				String[] operators = new String[2];

				queryParams[0] = "level";
				queryParams[1] = "testament";

				reference[0] = level;
				if (testament.contains("Old")) {
					reference[1] = "Old";
				} else if (testament.contains("New")) {
					reference[1] = "New";
				}

				operators[0] = "=";
				operators[1] = "=";

				bibleQuizList = daoService.getObjectsListByMultipleParameters(BibleQuizTamil.class, queryParams,
						reference, operators, ApplicationConstants.MAX_QUESTIONS_PER_QUIZ,true);
			}
			if (bibleQuizList == null) {
				return Collections.emptyList();
			}
			return bibleQuizList;
		}
		return Collections.emptyList();
	}

	public T getBibleQuizById(T classRef, String id) {
		return (T) daoService.getObjectsById(classRef.getClass(), "id", id);
	}

	public void setQuestionIds(QuizTakerEntry quizTakerEntryModel, List<Object> bibleQuizList) {
		if (quizTakerEntryModel != null && bibleQuizList != null) {
			Iterator<Object> iter = bibleQuizList.iterator();
			String arg = "";
			while (iter.hasNext()) {
				Object obj = iter.next();
				if (obj instanceof BibleQuiz) {
					BibleQuiz bibleQuiz = (BibleQuiz) obj;
					arg = arg + bibleQuiz.getId();
					if (iter.hasNext()) {
						arg = arg + ",";
					}
				}
			}
			quizTakerEntryModel.setQuestionIds(arg);
		} else {
			getLogger().error("Unable to set the question ids either quizTakerEntryModel / bibleQuizList is null");
		}
	}
}
