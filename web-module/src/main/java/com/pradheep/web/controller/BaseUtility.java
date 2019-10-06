package com.pradheep.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.omg.CORBA.UnknownUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.pradheep.dao.config.DAOService;
import com.pradheep.dao.model.BibleVerse;
import com.pradheep.dao.model.Category;
import com.pradheep.dao.model.DidYouKnow;
import com.pradheep.dao.model.DisplayMessage;
import com.pradheep.dao.model.Message;
import com.pradheep.dao.model.VisitCounter;
import com.pradheep.web.common.ApplicationConstants;
import com.pradheep.web.common.ApplicationLoggerWeb;
import com.pradheep.web.common.PYRUtility;
import com.pradheep.web.common.PagePath;
import com.pradheep.web.exception.UnKnownUserException;
import com.pry.security.utility.PublicUtility;

public class BaseUtility<T> {

	private SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	@Autowired
	private PublicUtility publicUtility;

	private long token_validity_in_secs = 3600;

	@Autowired
	public DAOService daoService;

	@Autowired
	public ApplicationLocaleResolver applicationLocaleResolver;

	private Logger logger = null;

	public List<Object> convertUnicodeToString(List<Object> entity, Class obj) {
		if (entity == null) {
			return Collections.emptyList();
		}
		java.util.Iterator iter = entity.iterator();
		List<Object> toBeReturned = new ArrayList<Object>();
		int i = 0;
		while (iter.hasNext()) {
			if (obj.equals(DidYouKnow.class)) {
				DidYouKnow didYouKnowObj = (DidYouKnow) iter.next();
				didYouKnowObj.setDidYouKnowContentTamil(
						PYRUtility.convertUnicodeToString(didYouKnowObj.getDidYouKnowContentTamil()));
				toBeReturned.add(obj);
			}
			if (obj.equals(Message.class)) {
				Message msgObj = (Message) iter.next();
				convertMessage(msgObj);
				DisplayMessage dispMsg = new DisplayMessage();
				copyData(msgObj, dispMsg);
				dispMsg = populateListedPara(msgObj, dispMsg);
				toBeReturned.add(dispMsg);
			}
			if (obj.equals(BibleVerse.class)) {
				BibleVerse bVerse = (BibleVerse) iter.next();
				bVerse.setChapter(PYRUtility.convertUnicodeToString(bVerse.getChapter()));
				bVerse.setVerse(PYRUtility.convertUnicodeToString(bVerse.getVerse()));
				toBeReturned.add(bVerse);
			}
		}
		return toBeReturned;
	}

	public DisplayMessage populateListedPara(Message msgObj, DisplayMessage dispMsg) {
		String tamilMessage = PYRUtility.convertUnicodeToString(msgObj.getMsgTamilForDisplay().toString());
		dispMsg.setListedParaTamil(formatStringInParagraphs(tamilMessage));
		dispMsg.setListedParaEnglish(formatStringInParagraphs(msgObj.getMsgEnglishForDisplay().toString()));
		return dispMsg;
	}

	public void convertMessage(Message msgObj) {
		System.out.println("*** Unicode " + msgObj.getMessageTitleTamil());
		String tamilTitle = PYRUtility.convertUnicodeToString(msgObj.getMessageTitleTamil());
		String arg = "\u0b95\u0bb0\u0bcd\u0ba4\u0bcd\u0ba4\u0bb0\u0bcd\u0020\u0ba8\u0bb2\u0bcd\u0bb2\u0bb5\u0bb0\u0bcd";
		// getLogger().info("Printing the tamil title" + tamilTitle);
		msgObj.setMessageTitleTamil(tamilTitle);
		// -----------------------------//
		String tamilShortDesc = PYRUtility.convertUnicodeToString(msgObj.getShortDescriptionTamil());
		msgObj.setShortDescriptionTamil(tamilShortDesc);
		// getLogger().info("Printing the tamil short description" +
		// tamilShortDesc);

		// -----------------------------//
		String tMsg = new String(msgObj.getMessageTamil()).toString();
		// getLogger().info("Printing the tamil message " + tMsg);
		String tamilMessage = PYRUtility.convertUnicodeToString(tMsg);
		// getLogger().info("Printing the message-after conversion: " +
		// tamilMessage);
		msgObj.setMessageTamil(tamilMessage.getBytes());
		msgObj.setMsgTamilForDisplay(tamilMessage);
		msgObj.setMsgEnglishForDisplay(new String(msgObj.getMessageEnglish()).toString());

		// ---- Change the date format for Display -------- //
		SimpleDateFormat srcFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		SimpleDateFormat tgtFormat = new SimpleDateFormat("yyy, MMM d");
		try {
			Date srcDate = srcFormat.parse(msgObj.getLastModified().toString().substring(0,
					msgObj.getLastModified().toString().lastIndexOf(".")));
			msgObj.setLastModifiedDisplay(tgtFormat.format(srcDate));
		} catch (ParseException e) {
			e.printStackTrace();
			getLogger().error("Error in formatting date", e);
		}
	}

	public void copyData(Message source, DisplayMessage target) {
		target.setCategoryReference(source.getCategoryReference());
		target.setId(source.getId());
		target.setLastModified(source.getLastModified());
		target.setLongMessagePhoto(source.getLongMessagePhoto());
		target.setMessageEnglish(source.getMessageEnglish());
		target.setMessageTamil(source.getMessageTamil());
		target.setMessageTitleEnglish(source.getMessageTitleEnglish());
		target.setMessageTitleTamil(source.getMessageTitleTamil());
		target.setShortDescriptionEnglish(source.getShortDescriptionEnglish());
		target.setShortDescriptionTamil(source.getShortDescriptionTamil());
		target.setShortMessagePhoto(source.getShortMessagePhoto());
		target.setLastModifiedDisplay(source.getLastModifiedDisplay());
	}

	private List<String> formatStringInParagraphs(String arg) {
		String startDelimiter = ApplicationConstants.PARA_START_DELIMITER;
		String endDelimiter = ApplicationConstants.PARA_END_DELIMITER;

		StringTokenizer tokenizer = new StringTokenizer(arg, endDelimiter);
		ArrayList<String> values = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			String para = tokenizer.nextToken().trim();
			if (para.contains(startDelimiter)) {
				para = para.replace(startDelimiter, "");
			}
			if (para.contains(endDelimiter)) {
				para = para.replace(endDelimiter, "");
			}
			para = para.replace("$", "");
			values.add(para);
		}
		return values;
	}
	
	/**
	 * Pagination basic settings for ModelAndView.
	 * @param request
	 * @param model
	 */
	public void setCurrentIndex(HttpServletRequest request,ModelAndView model){
		String current_page = request.getParameter("startIndex");
		int pageIndex = 0;
		if (current_page == null) {
			pageIndex = 0;
		} else {
			pageIndex = Integer.parseInt(current_page);
		}
		if (pageIndex == 0) {
			model.addObject("currentIndex", 1);
		} else {
			model.addObject("currentIndex", pageIndex);
		}
		model.addObject("nextPageIndex", pageIndex + 1);
		getLogger().info("Printing the current page :" + pageIndex);
		String pageAction = request.getParameter("action");
		getLogger().info("Printing the action" + pageAction);
	}

	/**
	 * Set the data for Pagination.
	 * @param request
	 * @param model
	 * @param T
	 * @param sortColumn
	 */
	public void addAllObjectsToModel(HttpServletRequest request, ModelAndView model,Class T,String sortColumn,int recordsPerPage) {
		String current_page = request.getParameter("startIndex");
		String direction = request.getParameter("action");
		int pageIndex = 0;
		if (current_page == null) {
			pageIndex = 0;
		} else {
			pageIndex = Integer.parseInt(current_page);
		}

		int maxRecordsPerPage = ApplicationConstants.NUMBER_OF_RECORDS_PER_PAGE;
		if (direction != null) {
			if (direction.equalsIgnoreCase("next") || direction.equalsIgnoreCase("nextSet")) {
				pageIndex = pageIndex + 1;
			} else if (direction.equalsIgnoreCase("previous")) {
				pageIndex = pageIndex - 1;
			}
		}
		List<Object> messages = daoService.getObjectsWithPagination(T, sortColumn, pageIndex,
				maxRecordsPerPage,true);
		String maxMessagesCount = daoService.getCountOfObjects(T);
		int maxMsgCnt = Integer.parseInt(maxMessagesCount);
		messages = convertUnicodeToString(messages, T);
		if(T == Message.class){
		provideOnlyFewWords(messages);
		}
		model.addObject("objectList", messages);
		model.addObject("maxRecordsCount", maxMsgCnt);
		model.addObject("maxRecordsPerPage", maxRecordsPerPage);
	}

	private void provideOnlyFewWords(List<Object> messageList) {
		// ---------------------------
		Iterator iter = messageList.iterator();
		while (iter.hasNext()) {
			Object obj = iter.next();
			if(obj instanceof Message){
			Message msgObj = (Message) obj;
			msgObj.setMessageEnglish(" ".getBytes());
			msgObj.setMessageTamil(" ".getBytes());
			msgObj.setLongMessagePhoto(" ".getBytes());
			msgObj.setLongMessagePhoto(" ".getBytes());
		}
		}
	}

	public void addAllMessagesToModelWithFilter(ModelAndView model, Integer categoryId) {
		List<Object> messages = daoService.getObjectsListById(Message.class, "categoryReference", categoryId, "=", -1);
		messages = convertUnicodeToString(messages, Message.class);
		model.addObject("messagesList", messages);
	}

	public Map<String, String> getCategoryMap(String language) {
		HashMap<String, String> categoryMap = new HashMap<String, String>();
		List<Object> categoryList = daoService.getObjects(Category.class);
		if (categoryList == null || categoryList.isEmpty()) {
			return categoryMap;
		}
		Iterator iter = categoryList.iterator();
		while (iter.hasNext()) {
			Category catObj = (Category) iter.next();
			String categoryTamil = catObj.getCategoryNameTamil().replace("u", "\\u"); // To
																						// be
																						// changed
																						// later
																						// //
			String categoryName = language == "ta" ? PYRUtility.convertUnicodeToString(categoryTamil)
					: catObj.getCategoryName();
			String categoryId = new String(catObj.getCategoryId().toString());
			Integer catId = Integer.parseInt(categoryId);
			categoryMap.put(categoryName, String.valueOf(getMessageForCategory(catId).size()) + "," + categoryId);
		}
		return categoryMap;
	}

	private List<Object> getMessageForCategory(Integer cateRef) {
		List<Object> obj = daoService.getObjectsListById(Message.class, "categoryReference", cateRef, "=", -1);
		if (obj == null) {
			return Collections.emptyList();
		}
		return obj;
	}

	public Category getCategory(String categoryRef) {
		Category catObj = null;
		try {
			categoryRef = categoryRef.replaceAll("'", "");
			int categoryId = Integer.parseInt(categoryRef);
			catObj = (Category) daoService.getObjectsById(Category.class, "categoryId", String.valueOf(categoryId));
		} catch (Exception err) {
			getLogger().error(err);
		}
		return catObj;
	}

	public Logger getLogger() {
		if (logger == null) {
			logger = ApplicationLoggerWeb.getLogBean(getClass());
		}
		return logger;
	}

	public String _issue_PrivateToken() {
		Vector<String> _randomStr = new Vector();
		_randomStr.add(0, "redeemer");
		_randomStr.add(_getRandomStr());
		_randomStr.add(_getRandomStr());
		_randomStr.add(_getRandomStr());
		_randomStr.add(_getRandomStr());
		_randomStr.add(_getRandomStr());
		Random _ran = new Random();
		StringBuffer buffer_ = new StringBuffer();
		buffer_.append(_randomStr.get(_ran.nextInt(_randomStr.size() - 1)));
		buffer_.append(_randomStr.get(_ran.nextInt(_randomStr.size() - 1)));
		buffer_.append(_randomStr.get(0));
		Date _dateTime = new Date();
		String _cDate = _sdf.format(_dateTime);
		buffer_.append(";");
		buffer_.append(_cDate);
		return publicUtility.EncryptText(buffer_.toString());
	}

	// second level security check //
	public boolean isTokenValid(String token) {
		boolean flag = false;
		if (token == null) {
			getLogger().error("Token was null ");
			return flag;
		}
		token = token.replace(" ", "+");		
		String parse = publicUtility.DecryptText(token);
		// getLogger().info("Printing after parse :" + parse);
		StringTokenizer tokenizer = new StringTokenizer(parse, ";");
		if (tokenizer.hasMoreTokens()) {
			String name = tokenizer.nextToken();
			// getLogger().info("Printing the name :" + name);
			if (name.contains("redeemer")) {
				flag = true;
			}
		}
		if (tokenizer.hasMoreTokens()) {
			String date = tokenizer.nextToken();
			System.out.println("Printing the date : " + date);

			try {
				Date dateObj = _sdf.parse(date);
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTime(new Date());
				Date cTime = new Date();
				long diff = cTime.getTime() - dateObj.getTime();
				// getLogger().info("Printing the time difference " + diff);
				long validTime = token_validity_in_secs * 1000; // millisecs
				if (diff < validTime) {
					// will not be valid if it is more than one hr;
					flag = true;
				} else {
					flag = false;
				}
			} catch (ParseException e) {
				flag = false;
				e.printStackTrace();
				getLogger().error("Error while parsing the secret token ", e);
			}
		}
		return flag;
	}

	public boolean checkToken(HttpServletRequest request) {
		String token = request.getParameter("key");
		// getLogger().info("printing the token :" + token);
		boolean flag = isTokenValid(token);
		return flag;
	}

	public void addTokenToModel(ModelAndView model) {
		model.addObject("token", _issue_PrivateToken());
	}

	public String _getRandomStr() {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0987654321";
		StringBuffer buffer = new StringBuffer();
		Random ran = new Random();
		for (int i = 0; i < 6; i++) {
			buffer.append(ran.nextInt(str.length() - 1));
		}
		return buffer.toString();
	}

	public void setLanguageBasedStyle(Locale localeObj, ModelAndView modelAndView) {
		if (localeObj.getLanguage().equalsIgnoreCase("ta")) {
			modelAndView.addObject("main_style_sheet", "all.css");
			modelAndView.addObject("menu_css", "megamenu.css");
		} else {
			modelAndView.addObject("main_style_sheet", "all_eng.css");
			modelAndView.addObject("menu_css", "megamenu_eng.css");
		}
	}

	public void addUserInformation(ModelAndView modelAndView) throws UnknownUserException {
		String userName = getUserName();
		if (userName == null || userName.equals("") || userName.equals("anonymousUser")) {
			logger.error("Unknown user logged in");
			throw new UnKnownUserException("UnKnown User Logged in");
		}
		modelAndView.addObject("username", userName);
	}

	public String getUserName() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return userName;
	}

	public ModelAndView getBasicModelAndView(String pagePath) {
		ModelAndView modelAndView = new ModelAndView(pagePath);
		try {
			addUserInformation(modelAndView);
		} catch (UnknownUserException e) {
			modelAndView.setViewName(PagePath.INVALID_SESSION);
			return modelAndView;
		}
		return modelAndView;
	}

	public ModelAndView getBasicModelAndView(String pagePath, String command, Object obj) {
		ModelAndView modelAndView = new ModelAndView(pagePath, command, obj);
		try {
			addUserInformation(modelAndView);
		} catch (UnknownUserException e) {
			modelAndView.setViewName(PagePath.INVALID_SESSION);
			return modelAndView;
		}
		return modelAndView;
	}

	public String getUniqueFileName() {
		String arg = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxy3z1234567890";
		Random ran = new Random();
		StringBuffer randomString = new StringBuffer();
		for (int i = 0; i <= 8; i++) {
			randomString.append(arg.charAt(ran.nextInt(arg.length() - 1)));
		}
		return randomString.toString();
	}

	public void addHitCounterToModel(ModelAndView modelAndView) {
		HashMap<String, String> hitCounterMap = new HashMap<String, String>();
		List<Object> visitCounter = daoService.getObjects(VisitCounter.class);
		visitCounter = visitCounter.stream().filter(string -> !string.toString().isEmpty())
				.collect(Collectors.toList());
		Iterator iter = visitCounter.iterator();
		while (iter.hasNext()) {
			VisitCounter visitCounterObj = (VisitCounter) iter.next();
			hitCounterMap.put(visitCounterObj.getPageRef(), visitCounterObj.getCounter());
		}
		modelAndView.addObject("hitCounterMap", hitCounterMap);
	}

	public void forceToAvoidBrowserCaching(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache"); // Forces caches to
															// obtain a new copy
															// of the page from
															// the origin server
		response.setHeader("Cache-Control", "no-store"); // Directs caches not
															// to store the page
															// under any
															// circumstance
		response.setDateHeader("Expires", 0); // Causes the proxy cache to see
												// the page as "stale"
		response.setHeader("Pragma", "no-cache");
	}

	public String getUserLanguage(HttpServletRequest request) {
		Locale locale = applicationLocaleResolver.resolveLocale(request);
		return locale.getLanguage();
	}

	/**
	 * Use this method to sort th
	 * @param comparator
	 * @param numberOfRecords
	 * @param collection
	 * @return
	 */
	public List<T> getLimitedList(Comparator comparator, int numberOfRecords, List<T> collection) {
		if (collection == null) {
			throw new IllegalArgumentException("The given collection cannot be null");
		}
		if (collection.size() == 0) {
			return Collections.emptyList();
		}
		Collections.sort(collection, comparator);
		List myCollection = new ArrayList<>(numberOfRecords);
		int i = 0;
		for (; i < numberOfRecords; i++) {
			myCollection.get(i);
		}
		return myCollection;
	}
}
