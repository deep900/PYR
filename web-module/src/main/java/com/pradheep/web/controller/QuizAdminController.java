/**
 * 
 */
package com.pradheep.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pradheep.dao.model.BibleQuizEng;
import com.pradheep.dao.model.BibleQuizTamil;
import com.pradheep.dao.model.BibleVerse;
import com.pradheep.web.common.PYRUtility;
import com.pradheep.web.common.PagePath;

/**
 * @author pradheep.p
 *
 */
@Controller
public class QuizAdminController extends BaseUtility {

	@RequestMapping("admin/addBibleQuizEng")
	@Secured("ROLE_ADMIN")
	public ModelAndView addDailyVerseEng(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("About to bible quiz english.");
		if (!checkToken(request)) {
			return getBasicModelAndView(PagePath.ACCESS_DENIED);
		}
		forceToAvoidBrowserCaching(response);
		ModelAndView modelAndView = getBasicModelAndView(PagePath.ADD_NEW_BIBLE_QUIZ_ENG, "command",
				new BibleQuizEng());
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	@RequestMapping("admin/addBibleQuizTamil")
	@Secured("ROLE_ADMIN")
	public ModelAndView addDailyVerseTamil(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("About to bible quiz tamil.");
		if (!checkToken(request)) {
			return getBasicModelAndView(PagePath.ACCESS_DENIED);
		}
		forceToAvoidBrowserCaching(response);
		ModelAndView modelAndView = getBasicModelAndView(PagePath.ADD_NEW_BIBLE_QUIZ_TAM, "command",
				new BibleQuizTamil());
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "admin/addBibleQuizEngAction", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ModelAndView addDailyVerseAction(@ModelAttribute("bibleVerseModel") BibleQuizEng bibleQuizEngModel,
			BindingResult result, HttpServletRequest request, HttpServletResponse reponse, Model model) {
		forceToAvoidBrowserCaching(reponse);
		String errorMessage = "";
		int id = 0;
		boolean flag = false;
		try {
			flag = daoService.saveOrUpdateEntity(bibleQuizEngModel);
			id = bibleQuizEngModel.getId();
			getLogger().info("Printing the ID" + id);
		} catch (Exception err) {
			err.printStackTrace();
		}
		if (flag) {
			errorMessage = "Bible quiz english added successfully";
		} else {
			errorMessage = "Unable to add the bible quiz english ";
		}
		getLogger().info(errorMessage);
		String pagePath = PagePath.VIEW_BIBLE_QUIZ;
		ModelAndView modelAndView = null;
		modelAndView = getBasicModelAndView(pagePath);
		modelAndView.addObject("errorMessage", errorMessage);
		addBibleQuizToModel(modelAndView, id, "english");
		modelAndView.addObject("language", "english");
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "admin/addBibleQuizTamilAction", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ModelAndView addDailyVerseActionTamil(@ModelAttribute("bibleVerseModel") BibleQuizTamil bibleQuizTamModel,
			BindingResult result, HttpServletRequest request, HttpServletResponse reponse, Model model) {
		forceToAvoidBrowserCaching(reponse);
		String errorMessage = "";
		boolean flag = false;
		int id = 0;
		try {
			flag = daoService.saveOrUpdateEntity(bibleQuizTamModel);
			id = bibleQuizTamModel.getId();
		} catch (Exception err) {
			err.printStackTrace();
		}
		if (flag) {
			errorMessage = "Bible quiz english added successfully";
		} else {
			errorMessage = "Unable to add the bible quiz tamil ";
		}
		getLogger().info(errorMessage);
		String pagePath = PagePath.VIEW_BIBLE_QUIZ;
		ModelAndView modelAndView = null;
		modelAndView = getBasicModelAndView(pagePath);
		modelAndView.addObject("errorMessage", errorMessage);
		addBibleQuizToModel(modelAndView, id, "tamil");
		modelAndView.addObject("language", "tamil");
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	private void addBibleQuizToModel(ModelAndView modelAndView, int id, String language) {

		if (language.equalsIgnoreCase("english")) {
			List<BibleQuizEng> engVerseList = new ArrayList<BibleQuizEng>();
			BibleQuizEng engObj = (BibleQuizEng) daoService.getObjectsById(BibleQuizEng.class, "id",
					String.valueOf(id));
			if(engObj == null) {
				modelAndView.addObject("bibleQuizList", Collections.emptyList());
				return;
			}
			engVerseList.add(engObj);
			modelAndView.addObject("bibleQuizList", engVerseList);
		} else {
			List<BibleQuizTamil> engVerseList = new ArrayList<BibleQuizTamil>();
			BibleQuizTamil taObj = (BibleQuizTamil) daoService.getObjectsById(BibleQuizTamil.class, "id",
					String.valueOf(id));
			if(taObj == null) {
				modelAndView.addObject("bibleQuizList", Collections.emptyList());
				return;
			}
			taObj.setQuestion(PYRUtility.convertUnicodeToString(taObj.getQuestion()));
			taObj.setChoice(PYRUtility.convertUnicodeToString(taObj.getChoice()));
			taObj.setCorrectAnswer(PYRUtility.convertUnicodeToString(taObj.getCorrectAnswer()));
			taObj.setBibleReference(PYRUtility.convertUnicodeToString(taObj.getBibleReference()));
			engVerseList.add(taObj);
			modelAndView.addObject("bibleQuizList", engVerseList);
		}
	}

	@RequestMapping("admin/deleteBibleQuiz")
	@Secured("ROLE_ADMIN")
	public ModelAndView handleDeleteBibleQuizEntry(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("About to delete bible quiz entry");
		if (!checkToken(request)) {
			return getBasicModelAndView(PagePath.ACCESS_DENIED);
		}
		forceToAvoidBrowserCaching(response);
		ModelAndView modelAndView = null;
		String pagePath = PagePath.VIEW_BIBLE_QUIZ;
		modelAndView = getBasicModelAndView(pagePath);
		String bibleQuizId = request.getParameter("id");
		String language = request.getParameter("lang");
		getLogger().info("Printing the id " + bibleQuizId);
		String errorMessage = "";
		if (language.equalsIgnoreCase("english")) {
			BibleQuizEng obj = (BibleQuizEng) daoService.getObjectsById(BibleQuizEng.class, "id", bibleQuizId);

			if (obj != null) {
				daoService.deleteObject(obj);
				errorMessage = "Deleted the entry successfully";
			} else {
				getLogger().info("Unable to delete " + bibleQuizId);
				errorMessage = "Unable to delete the entry";
			}
			addBibleQuizToModel(modelAndView, Integer.parseInt(bibleQuizId), "english");
		} else {
			BibleQuizTamil obj = (BibleQuizTamil) daoService.getObjectsById(BibleQuizTamil.class, "id", bibleQuizId);

			if (obj != null) {
				daoService.deleteObject(obj);
				errorMessage = "Deleted the entry successfully";
			} else {
				getLogger().info("Unable to delete " + bibleQuizId);
				errorMessage = "Unable to delete the entry";
			}
			addBibleQuizToModel(modelAndView, Integer.parseInt(bibleQuizId), "tamil");
		}		
		
		modelAndView.addObject("errorMessage", errorMessage);
		addTokenToModel(modelAndView);
		return modelAndView;
	}
	
	
	@RequestMapping("admin/viewAllBibleQuiz")
	@Secured("ROLE_ADMIN")
	public ModelAndView viewAllBibleQuiz(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("View all bible quiz questions");
		if (!checkToken(request)) {
			return getBasicModelAndView(PagePath.ACCESS_DENIED);
		}		
		ModelAndView modelAndView = null;
		String pagePath = PagePath.VIEW_ALL_BIBLE_QUIZ;
		modelAndView = getBasicModelAndView(pagePath);		
		List<BibleQuizEng> englishQuiz = (List) daoService.getObjects(BibleQuizEng.class);		
		List<BibleQuizTamil> tamilQuiz = (List) daoService.getObjects(BibleQuizTamil.class);
		modelAndView.addObject("englishQuizList", englishQuiz);
		modelAndView.addObject("tamilQuizList", convertUnicodeToString(tamilQuiz,BibleQuizTamil.class));		
		return modelAndView;
	}
	
	
}
