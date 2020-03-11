package com.pradheep.web.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pradheep.dao.model.AllCategoryAndPath;
import com.pradheep.dao.model.BibleQuizEng;
import com.pradheep.dao.model.BibleQuizTamil;
import com.pradheep.dao.model.BibleVerse;
import com.pradheep.dao.model.Category;
import com.pradheep.dao.model.CategoryPathLink;
import com.pradheep.dao.model.DidYouKnow;
import com.pradheep.dao.model.DisplayMessage;
import com.pradheep.dao.model.Message;
import com.pradheep.dao.model.PrayersModel;
import com.pradheep.dao.model.QuizTaker;
import com.pradheep.dao.model.QuizTakerEntry;
import com.pradheep.dao.model.Subscription;
import com.pradheep.dao.model.VisitCounter;
import com.pradheep.web.common.ApplicationConstants;
import com.pradheep.web.common.DailyVerseManager;
import com.pradheep.web.common.MonitorHitCounter;
import com.pradheep.web.common.OneYearBible;
import com.pradheep.web.common.PYRUtility;
import com.pradheep.web.common.PagePath;
import com.pradheep.web.common.QuizHelper;
import com.pradheep.web.common.QuizResult;

@Controller
public class ApplicationController extends BaseUtility {

	@Autowired
	private DailyVerseManager dailyVerseManager;

	@Autowired
	@Qualifier("oneYearBibleManager")
	private OneYearBible oneYearBible;

	@Autowired
	private QuizHelper quizHelper;

	public ApplicationController() {
		getLogger().info("Loading the application controller ----");
	}

	@RequestMapping("/")
	@MonitorHitCounter(path = PagePath.INDEX_PAGE)
	public ModelAndView getMainPage(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Inside get index page");
		ModelAndView model = new ModelAndView(PagePath.INDEX_PAGE);
		addTokenToModel(model);
		Locale locale = applicationLocaleResolver.resolveLocale(request);
		applicationLocaleResolver.setLocale(request, response, locale);
		setLanguageBasedStyle(locale, model);
		addVisitCounts(model, PagePath.INDEX_PAGE);
		BibleVerse bibleVerse = dailyVerseManager.getTodayVerse();
		DidYouKnow didYouKnow = daoService.getRandomDidYouKnow();
		if (getUserLanguage(request).equalsIgnoreCase("ta")) {
			model.addObject("todayVerse", PYRUtility.convertUnicodeToString(bibleVerse.getVerse()));
			model.addObject("todayChapter", PYRUtility.convertUnicodeToString(bibleVerse.getChapter()));
			model.addObject("didYouKnowText",
					PYRUtility.convertUnicodeToString(didYouKnow.getDidYouKnowContentTamil()));
			model.addObject("oneYearBibleChapter",
					PYRUtility.convertUnicodeToString(getTodayBibleChapterForDayInYear("ta")));
			model.addObject("oneYearBibleChapterYesterday",
					PYRUtility.convertUnicodeToString(getBibleChapterForYesterday("ta")));
			model.addObject("bannerfont", "CustomKomalaB");
			model.addObject("music_file_name", "sthothiram");
		} else {
			model.addObject("todayVerse", bibleVerse.getEngVerse());
			model.addObject("todayChapter", bibleVerse.getEngChapter());
			model.addObject("didYouKnowText", didYouKnow.getDidYouKnowContent());
			model.addObject("oneYearBibleChapter", getTodayBibleChapterForDayInYear("en"));
			model.addObject("oneYearBibleChapterYesterday", getBibleChapterForYesterday("en"));
			model.addObject("bannerfont", "Satisfy");
			model.addObject("music_file_name", "Give_Thanks1");
		}
		model.addObject("oneYearBibleDate", PYRUtility.getTodayDateFormatted());
		model.addObject("oneYearBibleDateYesterday", PYRUtility.getYesterdayDateFormatted());
		return model;
	}

	@RequestMapping("/messages")
	@MonitorHitCounter(path = PagePath.MESSAGES)
	public ModelAndView getMessages(@ModelAttribute("subscriptionObject") Subscription subscription,
			HttpServletRequest request, HttpServletResponse response, Model modelObj) {
		getLogger().debug("Inside messages page..");
		Object err = request.getAttribute("errorMessage");
		String errorValue = "";
		if (err != null) {
			errorValue = err.toString();
		}
		getLogger().info("Printing the error message " + errorValue);
		ModelAndView model = new ModelAndView(PagePath.MESSAGES, "COMMAND", new Subscription());
		/*
		 * String current_page = request.getParameter("startIndex"); int pageIndex = 0;
		 * if (current_page == null) { pageIndex = 0; } else { pageIndex =
		 * Integer.parseInt(current_page); } if (pageIndex == 0) {
		 * model.addObject("currentIndex", 1); } else { model.addObject("currentIndex",
		 * pageIndex); }
		 */
		// -------- Pagination settings -------------------//
		setCurrentIndex(request, model);
		addAllObjectsToModel(request, model, Message.class, "lastModified", ApplicationConstants.NUMBER_OF_RECORDS_PER_PAGE);
		// ------- End of pagination settings--------------- //
		Locale locale = applicationLocaleResolver.resolveLocale(request);
		applicationLocaleResolver.setLocale(request, response, locale);
		model.addObject("language", locale.getLanguage());
		setLanguageBasedStyle(locale, model);
		addVisitCounts(model, PagePath.MESSAGES);
		model.addObject("errMessage", errorValue);
		model.addObject("categoryMap", getCategoryMap(locale.getLanguage()));
		addTokenToModel(model);
		return model;
	}

	@RequestMapping("/readMessage")
	@MonitorHitCounter(path = PagePath.READ_MESSAGES)
	public ModelAndView readMessage(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Inside read messages page..");
		// Commenting the token feature to make it available easily.
		/*
		 * if (!checkToken(request)) { return new ModelAndView(PagePath.ACCESS_DENIED);
		 * }
		 */
		ModelAndView model = new ModelAndView(PagePath.READ_MESSAGES);
		String id = request.getParameter("id");
		Object obj = daoService.getObjectsById(Message.class, "id", id);
		Message msgObj = (Message) obj;
		DisplayMessage dispMsg = new DisplayMessage();
		convertMessage(msgObj);
		copyData(msgObj, dispMsg);
		dispMsg = populateListedPara(msgObj, dispMsg);
		Locale locale = applicationLocaleResolver.resolveLocale(request);
		setLanguageBasedStyle(locale, model);
		model.addObject("language", locale.getLanguage());
		model.addObject("message", dispMsg);
		return model;
	}

	@RequestMapping("/important-verses")
	@MonitorHitCounter(path = PagePath.IMPORTANT_VERSES)
	public ModelAndView getImportantVerses(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Inside read important verses..");
		Locale locale = applicationLocaleResolver.resolveLocale(request);
		ModelAndView model = null;// new
									// ModelAndView(PagePath.IMPORTANT_VERSES);
		if (locale.getLanguage().equalsIgnoreCase("ta")) {
			model = new ModelAndView(PagePath.IMPORTANT_VERSES_TAMIL);
		} else {
			model = new ModelAndView(PagePath.IMPORTANT_VERSES);
		}
		return model;
	}

	@RequestMapping("/prayers")
	@MonitorHitCounter(path = PagePath.PRAYERS)
	public ModelAndView getPrayerPage(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Inside prayer page..");

		ModelAndView model = new ModelAndView(PagePath.PRAYERS);
		if (getUserLanguage(request).equalsIgnoreCase("ta")) {
			model.addObject("imageName", "/resources/images/prayer1.jpg");

		} else {
			model.addObject("imageName", "/resources/images/prayer1.jpg");
		}
		addVisitCounts(model, PagePath.PRAYERS);
		return model;
	}

	private List<Object> getAllPrayers() {
		return daoService.getObjects(PrayersModel.class);
	}

	@RequestMapping("/under-development")
	public ModelAndView getUnderDevelopment(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Inside page under development");
		ModelAndView model = new ModelAndView(PagePath.UNDER_DEVELOPMENT);
		applicationLocaleResolver.resolveLocale(request);
		return model;
	}

	@RequestMapping(value = "/quizMainPage")
	public ModelAndView getBibleQuizPage(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Loading quiz main page");
		// ModelAndView modelAndView = new ModelAndView(PagePath.UNDER_DEVELOPMENT);
		ModelAndView modelAndView = new ModelAndView(PagePath.QUIZ_MAIN_PAGE, "command", new QuizTaker());
		Locale locale = applicationLocaleResolver.resolveLocale(request);
		setLanguageBasedStyle(locale, modelAndView);
		modelAndView.addObject("context", request.getContextPath());
		return modelAndView;
	}

	/*
	 * This page is the first landing page for quiz takers, they have to set the
	 * level and Bible portion for taking the test.
	 */
	@RequestMapping("/startQuiz")
	public ModelAndView startQuizPage(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Loading quiz entry page.");
		ModelAndView model = new ModelAndView(PagePath.QUIZ_ENTRY_PAGE);
		String emailAddress = request.getParameter("email");
		if (emailAddress == null) {
			emailAddress = (String) request.getAttribute("email");
		}
		String userName = "Guest";
		String userLanguage = getUserLanguage(request);
		if (emailAddress != null) {
			QuizTaker quizTaker = getQuizUserNameByEmail(emailAddress, userLanguage);
			if (quizTaker != null) {
				userName = quizTaker.getName();
			}
		} else {
			emailAddress = "";
		}
		model.addObject("quiz_user_email", emailAddress);
		model.addObject("quiz_user_name", userName);
		Locale locale = applicationLocaleResolver.resolveLocale(request);
		setLanguageBasedStyle(locale, model);
		return model;
	}

	private QuizTaker getQuizUserNameByEmail(String emailAddress, String language) {
		String userName = "Guest";
		getLogger().info("Trying to get the quiz user name by email : " + emailAddress + " Language " + language);
		if (language.equalsIgnoreCase("en")) {
			Object obj = daoService.getObjectsById(QuizTaker.class, "email", emailAddress);
			if (obj == null) {
				return null;
			}
			QuizTaker quizTakerObj = (QuizTaker) obj;
			return quizTakerObj;
		} else if (language.equalsIgnoreCase("ta")) {
			getLogger().info("This is under development : TODO");
			// TODO
		}
		return null;
	}

	@RequestMapping(value = "/registerQuizTaker", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ModelAndView registerQuizTaker(@ModelAttribute("registerQuizTaker") QuizTaker quizTakerModel,
			BindingResult result, HttpServletRequest request, HttpServletResponse reponse, Model model) {
		forceToAvoidBrowserCaching(reponse);
		String errorMessage = "";
		String csscode = "";
		boolean flag = false;
		try {
			if (doesUserIdAlreadyExists(quizTakerModel)) {
				errorMessage = "User already exists.";
			} else {
				flag = daoService.saveOrUpdateEntity(quizTakerModel);
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
		if (flag) {
			errorMessage = "  Added the bible quiz taker successfully";
			csscode = "success";
		} else {
			errorMessage = errorMessage + " Unable to add the bible quiz taker ";
			csscode = "error";
		}
		getLogger().info(errorMessage);
		String pagePath = PagePath.QUIZ_ENTRY_PAGE;
		ModelAndView modelAndView = null;
		modelAndView = new ModelAndView(pagePath);
		modelAndView.addObject("errorMessage", errorMessage);
		String emailAddress = quizTakerModel.getEmail();
		String userName = quizTakerModel.getName();
		modelAndView.addObject("quiz_user_email", emailAddress);
		modelAndView.addObject("quiz_user_name", userName);
		modelAndView.addObject("csscode", csscode);
		Locale locale = applicationLocaleResolver.resolveLocale(request);
		setLanguageBasedStyle(locale, modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "/youAreInQuiz", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ModelAndView youAreInQuiz(@ModelAttribute("startQuizForm") QuizTakerEntry quizTakerEntryModel,
			BindingResult result, HttpServletRequest request, HttpServletResponse reponse, Model model) {
		forceToAvoidBrowserCaching(reponse);
		String errorMessage = "";
		String csscode = "";

		// ----------------------------------------------------------------
		String emailAddress = request.getParameter("email");
		if (emailAddress == null) {
			emailAddress = (String) request.getAttribute("email");
		}
		QuizTaker quizTaker = getQuizUserNameByEmail(emailAddress, "en");
		quizTakerEntryModel.setQuizTaker(quizTaker);
		String level = (String) request.getParameter("quizLevel");
		String portion = (String) request.getParameter("biblePortion");
		String userLang = getUserLanguage(request);
		List<Object> questions = Collections.EMPTY_LIST;
		List<BibleQuizEng> quesEng = Collections.EMPTY_LIST;
		List<BibleQuizTamil> quesTa = Collections.EMPTY_LIST;
		System.out.println("Printing the userLang : " + userLang);
		if (userLang.equalsIgnoreCase("en")) {
			questions = quizHelper.getQuestions(ApplicationConstants.ENG_LANG, level, portion);
			quesEng = quizHelper.convertToBibleQuizEng(questions);
		} else {
			questions = quizHelper.getQuestions(ApplicationConstants.TA_LANG, level, portion);
			quesTa = quizHelper.convertToBibleQuizTamil(questions);
		}
		quizHelper.setQuestionIds(quizTakerEntryModel, questions);
		getLogger().info("Printing the level of quiz " + level + " Portion :" + portion);
		// ----------------------------------------------------------------
		getLogger().info("Printing the Email Address " + emailAddress);
		if (!emailAddress.toLowerCase().contains("guest") || emailAddress != null) {
			persistUser(quizTakerEntryModel);
		}
		getLogger().info(errorMessage);
		String pagePath = PagePath.YOU_ARE_IN_QUIZ_PAGE;
		ModelAndView modelAndView = null;
		modelAndView = new ModelAndView(pagePath);
		modelAndView.addObject("errorMessage", errorMessage);
		String userName = quizTakerEntryModel.getQuizTaker() == null ? "Guest User"
				: quizTakerEntryModel.getQuizTaker().getName();
		if (emailAddress.equalsIgnoreCase("guest")) {
			userName = "Guest";
		}
		modelAndView.addObject("quiz_user_email", emailAddress);
		modelAndView.addObject("quiz_user_name", userName);
		modelAndView.addObject("csscode", csscode);
		modelAndView.addObject("portion", portion);
		modelAndView.addObject("level", level);
		if (userLang.equalsIgnoreCase("en")) {
			modelAndView.addObject("quizQuestions", quesEng);
			modelAndView.addObject("question_ids", quizHelper.getQuestionIds(quesEng));
		} else {
			modelAndView.addObject("quizQuestions", quesTa);
			modelAndView.addObject("question_ids", quizHelper.getQuestionIds(quesTa));
		}

		Locale locale = applicationLocaleResolver.resolveLocale(request);
		setLanguageBasedStyle(locale, modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "/submitQuizAnswers", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ModelAndView handleQuizAnswers(HttpServletRequest request, HttpServletResponse reponse) {
		forceToAvoidBrowserCaching(reponse);
		String userLanguage = getUserLanguage(request);
		String emailAddress = request.getParameter("emailAddress");
		String questionIds = request.getParameter("questionIds");
		getLogger().info("Printing the question ids : " + questionIds);
		List getBibleQuestions = Collections.EMPTY_LIST;
		if(userLanguage.equalsIgnoreCase("en")) {			
			getBibleQuestions = quizHelper.getQuestionByIds(questionIds, "en");// To be changed later for tamil
		}
		else {
			getBibleQuestions = quizHelper.getQuestionByIds(questionIds, "ta");
		}
		QuizTaker quizTaker = getQuizUserNameByEmail(emailAddress, "en"); // to be changed later for tamil.
		String userName = quizTaker == null ? "Guest User" : quizTaker.getName();
		QuizResult quizResult = quizHelper.getScore(questionIds, request,userLanguage);
		quizHelper.mapUserResults(quizResult, getBibleQuestions);
		ModelAndView modelAndView = new ModelAndView(PagePath.QUIZ_RESULTS);
		modelAndView.addObject("quiz_user_email", emailAddress);
		modelAndView.addObject("quiz_user_name", userName);
		modelAndView.addObject("quizQuestions", getBibleQuestions);
		modelAndView.addObject("score", quizResult.getResult());
		modelAndView.addObject("total_questions", quizResult.getTotalQuestions());
		Locale locale = applicationLocaleResolver.resolveLocale(request);
		setLanguageBasedStyle(locale, modelAndView);
		return modelAndView;
	}

	private void persistUser(QuizTakerEntry quizTakerEntryModel) {
		boolean flag = false;
		// quizTakerEntryModel.setBiblePortion(portion);
		quizTakerEntryModel.setDateOfQuiz(new Date());

		flag = daoService.saveOrUpdateEntity(quizTakerEntryModel);
		if (flag) {
			getLogger().info("Saved the entry for bible quiz");

		} else {
			getLogger().info("Unable to save the entry for bible quiz");
		}
	}

	private boolean doesUserIdAlreadyExists(QuizTaker quizTakerModel) {
		return daoService.getObjectsById(QuizTaker.class, "email", quizTakerModel.getEmail()) == null ? false : true;
	}

	@RequestMapping("/showByCategory")
	public ModelAndView getCategoryByReference(HttpServletRequest request, HttpServletResponse response) {
		String categoryId = request.getParameter("reference");
		getLogger().debug("Inside get category by reference :" + categoryId);
		ModelAndView model = new ModelAndView(PagePath.DISPLAY_BY_CATEGORIES);
		Locale localeObj = applicationLocaleResolver.resolveLocale(request);
		Category categoryObj = getCategory(categoryId);
		model.addObject("categoryName", categoryObj.getCategoryName());
		// model.addObject("pageReferences", getAllPageReference(categoryObj));
		setLanguageBasedStyle(localeObj, model);
		addAllMessagesToModelWithFilter(model, Integer.valueOf(categoryObj.getCategoryId().toString()));
		addTokenToModel(model);
		return model;
	}

	@RequestMapping("/about")
	@MonitorHitCounter(path = PagePath.ABOUT_PAGE)
	public ModelAndView getAboutPage(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Inside about page..");
		ModelAndView model = new ModelAndView(PagePath.ABOUT_PAGE);
		applicationLocaleResolver.resolveLocale(request);
		return model;
	}

	@RequestMapping("/isaiah-prophet")
	@MonitorHitCounter(path = PagePath.ISAIAH_PAGE)
	public ModelAndView getIsaiahPage(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Loading the contents of prophet isaiah.");
		ModelAndView model = new ModelAndView(PagePath.ISAIAH_PAGE);
		applicationLocaleResolver.resolveLocale(request);
		return model;
	}

	@RequestMapping("/login-page")
	@MonitorHitCounter(path = PagePath.LOGIN_PAGE)
	public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Loading the login page");
		ModelAndView model = new ModelAndView(PagePath.LOGIN_PAGE);
		applicationLocaleResolver.resolveLocale(request);
		String arg = request.getParameter("error");
		if (arg == null || arg.isEmpty()) {
			model.addObject("errorMessage", "");
		} else {
			getLogger().info("Printing the error code " + arg);
			model.addObject("errorMessage", "Invalid username or password is invalid");
		}
		return model;
	}

	@RequestMapping("/loginSuccessHandler")
	@MonitorHitCounter(path = PagePath.LOGIN_SUCCESS_PAGE)
	public ModelAndView loginSuccessHandler(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("successfully authenticated");
		ModelAndView model = new ModelAndView(PagePath.LOGIN_SUCCESS_PAGE);
		applicationLocaleResolver.resolveLocale(request);
		return model;
	}

	@RequestMapping("/logoutSuccess")
	@MonitorHitCounter(path = PagePath.LOGOUT_SUCCESS)
	public ModelAndView handleLogoutSuccess(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("User logged out");
		ModelAndView model = new ModelAndView(PagePath.LOGOUT_SUCCESS);
		applicationLocaleResolver.resolveLocale(request);
		return model;
	}

	@RequestMapping("/invalidSession")
	public ModelAndView handleInvalidSession(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Invalid session found");
		ModelAndView model = new ModelAndView(PagePath.INVALID_SESSION);
		applicationLocaleResolver.resolveLocale(request);
		return model;
	}

	private void addVisitCounts(ModelAndView model, String path) {
		// VisitCounter counter = daoService.getVisitCounter(path);
		String queryParameter = "pageRef";
		VisitCounter counter = (VisitCounter) daoService.getObjectsById(VisitCounter.class, queryParameter, path);
		String totalVisits = "";
		if (counter == null) {
			model.addObject("totalVisits", totalVisits);
			return;
		}
		totalVisits = counter.getCounter();
		model.addObject("totalVisits", totalVisits);
	}

	private void testAdditionOfTamilVerse() {
		Resource res = new ClassPathResource("final_output.txt");
		try {
			InputStream in = res.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(in);
			try {
				HashMap map = (HashMap) ois.readObject();
				java.util.Iterator iter = map.keySet().iterator();
				while (iter.hasNext()) {
					Object obj = iter.next();
					System.out.println(obj.getClass().getName());
					System.out.println(obj.toString());
					BibleVerse bv = (BibleVerse) map.get(obj);
					bv.setVerse(PYRUtility.getUnicodeCharacter(bv.getVerse()));
					bv.setChapter(PYRUtility.getUnicodeCharacter(bv.getChapter()));
					daoService.saveOrUpdateEntity(bv);
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String readTamilVerse() {
		List<Object> tamilVerseList = daoService.getObjects(BibleVerse.class);
		String verse = "";
		for (Object obj : tamilVerseList) {
			BibleVerse tamilVerse = (BibleVerse) obj;
			verse = org.apache.commons.lang3.StringEscapeUtils.unescapeJava(tamilVerse.getVerse());
			// getLogger().info("Printing the verse from DB " + verse);
			break;
		}
		return verse;
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

	private String getBibleChapterForYesterday(String languageCode) {
		String todayChapter = "";
		SimpleDateFormat sdfObject = new SimpleDateFormat("MMM d");
		GregorianCalendar gregorianCalender = new GregorianCalendar();
		Date today = new Date();
		gregorianCalender.setTime(today);
		gregorianCalender.set(GregorianCalendar.HOUR, -24);
		String formattedDate = sdfObject.format(gregorianCalender.getTime());
		try {
			todayChapter = oneYearBible.getTodayChapter(formattedDate, languageCode);
		} catch (IllegalArgumentException exp) {
			getLogger().error(exp.getMessage(), exp);
			getLogger().error("Error in getting today's chapter");
		}
		return todayChapter;
	}

	private boolean checkIfEmailAlreadyExists(String email) {
		return false;
	}

	private HashMap<String, String> getAllPageReference(Category obj) {
		HashMap<String, String> pageReferences = new HashMap<String, String>();
		Set<AllCategoryAndPath> collection = obj.getSetOfPaths();
		Iterator<AllCategoryAndPath> iter = collection.iterator();
		while (iter.hasNext()) {
			AllCategoryAndPath allCategoryAndPath = iter.next();
			Integer pageRef = allCategoryAndPath.getPage_id();
			CategoryPathLink categoryPathLink = (CategoryPathLink) daoService.getObjectsById(CategoryPathLink.class,
					"pageId", "pageRef");
			pageReferences.put(categoryPathLink.getPageTitle(), categoryPathLink.getUrlOfPage());
		}
		return pageReferences;
	}

	@RequestMapping(value = "/getImage")
	public void redrawImage(HttpServletRequest request, HttpServletResponse response, Model model) {
		String messageId = request.getParameter("id");
		Message obj = (Message) daoService.getObjectsById(Message.class, "id", messageId);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(obj.getShortMessagePhoto());
		BufferedImage image = null;
		try {
			image = ImageIO.read(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		HttpSession session = request.getSession();
		int width = 622;
		int height = 291;
		response.setContentType("image/jpg");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			ImageIO.write(image, "jpg", os);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String _getCaptchaStr() {
		String arg = _getRandomStr();
		return arg;
	}

	@RequestMapping(value = "/getCaptcha")
	public void redrawCaptcha(HttpServletRequest request, HttpServletResponse response, Model model) {
		String appCheck = "";

		Color bgColor = null;
		try {
			Object bgcolor = request.getParameter("bgcolor");
			if (bgcolor != null) {
				System.out.println(bgcolor.toString());
				String arg[] = bgcolor.toString().split(",");
				int red = Integer.parseInt(arg[0]);
				int green = Integer.parseInt(arg[1]);
				int blue = Integer.parseInt(arg[2]);
				bgColor = new Color(red, green, blue);
				System.out.println("bgColor set " + bgColor.toString());
			}
		} catch (Exception err) {
			getLogger().error("Error while getting the color from client");
			err.printStackTrace();
		}
		HttpSession session = request.getSession();
		Object captcha_allowed = session.getAttribute("captcha_allowed");
		if (captcha_allowed == null || !captcha_allowed.toString().equals("yes")) {
			getLogger().info("Captcha was accessed outside application ..");
			return;
		}

		int width = 150;
		int height = 50;
		System.out.println("Inside processing the captcha request ..");
		char data[][] = { { 'a', 'd', 'z', 'r' }, { 'j', 's', 'e', 'u', }, { 'a', 'd', 'v', 'i', 'b' },
				{ 'f', 't', 'h', 'a', 'e' }, { 'a', 't', 'c', 's' }, { 'a', 't', 'c', 's' }, { 'p', 'd', 'x', 'e' },
				{ 'g', 'j', 'f', 'n' }, { 'v', 't', 'g', 's' }, { 'b', 'n', 'b', 'w' }, { 'r', 't', 'y', 'v' },
				{ 'h', 'x', 'q', 'z' } };

		char myshuffle[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't',
				'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q',
				'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics2D g2d = bufferedImage.createGraphics();

		Font font = new Font("Georgia", Font.BOLD, 18);
		g2d.setFont(font);

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);

		// GradientPaint gp = new GradientPaint(0, 0, Color.red, 0, height / 2,
		// Color.black, true);
		if (bgColor == null) {
			g2d.setPaint(new Color(51, 51, 51));
		} else {
			// Use custom background colors//
			g2d.setPaint(bgColor);
		}

		g2d.fillRect(0, 0, width, height);

		g2d.setColor(new Color(255, 153, 0));

		String captcha = "";
		for (int c = 0; c < 6; c++) {
			Double d = new Double(Math.random() * myshuffle.length - 1);
			int index = d.intValue();
			captcha = captcha + myshuffle[index];
		}
		request.getSession().setAttribute(ApplicationConstants.SECRET_KEY_CAPTCHA, captcha);

		int x = 0;
		int y = 0;
		Random r = new Random();
		for (int i = 0; i < 6; i++) {
			x += 15 + (Math.abs(Math.random() * 12) % 15);
			y = 20 + Math.abs(r.nextInt()) % 20;
			g2d.drawChars(captcha.toCharArray(), i, 1, x, y);
		}

		g2d.dispose();

		response.setContentType("image/png");
		OutputStream os;
		try {
			os = response.getOutputStream();
			ImageIO.write(bufferedImage, "png", os);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/daniel-prophet")
	@MonitorHitCounter(path = PagePath.DANIEL_PAGE)
	public ModelAndView getDanielPage(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Loading the contents of prophet isaiah.");
		ModelAndView model = new ModelAndView(PagePath.DANIEL_PAGE);
		applicationLocaleResolver.resolveLocale(request);
		return model;
	}
}
