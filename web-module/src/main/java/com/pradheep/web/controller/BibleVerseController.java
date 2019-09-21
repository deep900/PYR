/**
 * 
 */
package com.pradheep.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pradheep.dao.model.BibleVerse;
import com.pradheep.dao.model.Message;
import com.pradheep.web.common.ApplicationConstants;
import com.pradheep.web.common.MonitorHitCounter;
import com.pradheep.web.common.PagePath;

/**
 * @author pradheep.p
 *
 */
@Controller
public class BibleVerseController extends BaseUtility {

	@Autowired
	private ApplicationLocaleResolver applicationLocaleResolver;

	@RequestMapping("admin/addNewDailyVerse")
	@Secured("ROLE_ADMIN")
	public ModelAndView addDailyVerse(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("About to daily verse..");
		if (!checkToken(request)) {
			return getBasicModelAndView(PagePath.ACCESS_DENIED);
		}
		forceToAvoidBrowserCaching(response);
		ModelAndView modelAndView = getBasicModelAndView(PagePath.ADD_NEW_DAILY_VERSE, "command", new BibleVerse());
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "admin/addDailyVerseAction", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ModelAndView addDailyVerseAction(@ModelAttribute("bibleVerseModel") BibleVerse bibleVerseModel,
			BindingResult result, HttpServletRequest request, HttpServletResponse reponse, Model model) {
		forceToAvoidBrowserCaching(reponse);
		String errorMessage = "";
		boolean flag = false;
		try {
			flag = daoService.saveOrUpdateEntity(bibleVerseModel);
		} catch (Exception err) {
			err.printStackTrace();
		}
		if (flag) {
			errorMessage = "Daily Verse added successfully";
		} else {
			errorMessage = "Unable to add the bible verse ";
		}
		getLogger().info(errorMessage);
		String pagePath = PagePath.VIEW_ALL_DAILY_VERSE;
		ModelAndView modelAndView = null;
		modelAndView = getBasicModelAndView(pagePath);
		modelAndView.addObject("errorMessage", errorMessage);
		addBibleVersesToModel(request,modelAndView,true);
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	@RequestMapping("admin/deleteDailyVerseEntry")
	@Secured("ROLE_ADMIN")
	public ModelAndView handleDeleteDailyVerseEntry(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("About to delete daily verse entry");
		if (!checkToken(request)) {
			return getBasicModelAndView(PagePath.ACCESS_DENIED);
		}
		forceToAvoidBrowserCaching(response);
		ModelAndView modelAndView = null;
		String bibleVerseId = request.getParameter("id");
		getLogger().info("Printing the id " + bibleVerseId);
		BibleVerse obj = (BibleVerse) daoService.getObjectsById(BibleVerse.class, "id", bibleVerseId);
		String errorMessage = "";
		if (obj != null) {
			daoService.deleteObject(obj);
			errorMessage = "Deleted the entry successfully";
		} else {
			getLogger().info("Unable to delete " + bibleVerseId);
			errorMessage = "Unable to delete the entry";
		}
		String pagePath = PagePath.VIEW_ALL_DAILY_VERSE;
		modelAndView = getBasicModelAndView(pagePath);
		modelAndView.addObject("errorMessage", errorMessage);
		addBibleVersesToModel(request,modelAndView,true);
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	@RequestMapping("admin/viewAllDailyVerses")
	@Secured("ROLE_ADMIN")
	@MonitorHitCounter(path = PagePath.VIEW_ALL_DAILY_VERSE)
	public ModelAndView showAllDailyVerseEntries(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Loading all daily verse entries..");
		boolean flag = checkToken(request);
		getLogger().info("printing the flag : " + flag);
		forceToAvoidBrowserCaching(response);
		if (flag) {
			ModelAndView model = getBasicModelAndView(PagePath.VIEW_ALL_DAILY_VERSE);
			addBibleVersesToModel(request,model,true);
			applicationLocaleResolver.resolveLocale(request);
			addTokenToModel(model);
			return model;
		} else {
			ModelAndView model = getBasicModelAndView(PagePath.ACCESS_DENIED);
			applicationLocaleResolver.resolveLocale(request);
			model.addObject("errorMsg", "Invalid token - try again.");
			return model;
		}
	}

	@RequestMapping("admin/editBibleVerse")
	@Secured("ROLE_ADMIN")
	public ModelAndView editBibleVerse(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("About to edit bible verse");
		if (!checkToken(request)) {
			return getBasicModelAndView(PagePath.ACCESS_DENIED);
		}
		forceToAvoidBrowserCaching(response);
		ModelAndView modelAndView = null;
		String bVerseId = request.getParameter("id");
		getLogger().info("Printing the id " + bVerseId);
		BibleVerse obj = (BibleVerse) daoService.getObjectsById(BibleVerse.class, "id", bVerseId);
		if (obj != null) {
			modelAndView = getBasicModelAndView("/admin/dailyVerse/editDailyVerse", "command", obj);

			modelAndView.addObject("id", obj.getId());
		} else {
			throw new IllegalStateException("Cannot find the message with ID : " + bVerseId);
		}
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "admin/editBibleVerseAction", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ModelAndView editBibleVerseAction(@ModelAttribute("bibleVerse") BibleVerse bibleVerseModel,
			BindingResult result, HttpServletRequest request, HttpServletResponse reponse, Model model) {
		forceToAvoidBrowserCaching(reponse);
		String errorMessage = "";
		boolean flag = false;
		try {
			String ref = request.getParameter("id").toString();
			Integer id = Integer.parseInt(ref);
			bibleVerseModel.setId(id);
			getLogger().info("About to update the bible verse with ID " + bibleVerseModel.getId());
			flag = daoService.saveOrUpdateEntity(bibleVerseModel);

		} catch (Exception err) {
			err.printStackTrace();
		}
		if (flag) {
			errorMessage = "Bible Verse updated successfully";
		} else {
			errorMessage = "Unable to update the Bible Verse";
		}
		getLogger().info(errorMessage);
		String pagePath = PagePath.VIEW_ALL_DAILY_VERSE;
		ModelAndView modelAndView = null;
		modelAndView = getBasicModelAndView(pagePath);
		modelAndView.addObject("errorMessage", errorMessage);
		addBibleVersesToModel(request,modelAndView,true);
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	private void addBibleVersesToModel(HttpServletRequest request, ModelAndView model,boolean isOrderRequired) {
		List<Object> dailyVerseData = getLimitedDailyBibleVerse(5,isOrderRequired);
		dailyVerseData = convertUnicodeToString(dailyVerseData, BibleVerse.class);
		model.addObject("dailyBibleVerseList", dailyVerseData);
		setCurrentIndex(request, model);
		addAllObjectsToModel(request, model, BibleVerse.class, "Id",ApplicationConstants.NUMBER_OF_RECORDS_PER_PAGE);
	}

	private List getLimitedDailyBibleVerse(int maxRecords,boolean isOrderRequired) {
		getLogger().info("Adding the last few verses for viewing and editing..");
		List dailyVerseData = daoService.getObjectsWithPagination(BibleVerse.class, "Id", 0, 5,isOrderRequired);
		return dailyVerseData;
	}
}
