
/**
 * 
 */
package com.pradheep.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

import com.pradheep.dao.model.DidYouKnow;
import com.pradheep.web.common.MonitorHitCounter;
import com.pradheep.web.common.PagePath;

/**
 * @author pradheep.p
 *
 */
@Controller
public class DidYouKnowController extends BaseUtility {
	
	@Autowired
	private ApplicationLocaleResolver applicationLocaleResolver;

	@RequestMapping("admin/viewAllDidYouKnow")
	@Secured("ROLE_ADMIN")
	@MonitorHitCounter(path = PagePath.VIEW_ALL_DID_YOU_KNOW)
	public ModelAndView showAllDidYouKnow(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Loading all did you know content..");
		boolean flag = checkToken(request);
		getLogger().info("printing the flag : " + flag);
		forceToAvoidBrowserCaching(response);
		if (flag) {
			ModelAndView model = getBasicModelAndView(PagePath.VIEW_ALL_DID_YOU_KNOW);
			List<Object> didYouKnowData = daoService.getObjects(DidYouKnow.class, "lastModified");
			List<DidYouKnow> collection = new ArrayList<DidYouKnow>();
			Iterator iter = didYouKnowData.iterator();
			while(iter.hasNext()){
				DidYouKnow obj = (DidYouKnow) iter.next();
				collection.add(obj);
			}
			System.out.println("Printing the list size :" + didYouKnowData.size());
			didYouKnowData = convertUnicodeToString(collection, DidYouKnow.class);
			model.addObject("didYouKnowList", collection);
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

	@RequestMapping("admin/editDidYouKnow")
	@Secured("ROLE_ADMIN")
	public ModelAndView handleEditDidYouKnow(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("About to edit did you know");
		if (!checkToken(request)) {
			return getBasicModelAndView(PagePath.ACCESS_DENIED);
		}
		forceToAvoidBrowserCaching(response);
		ModelAndView modelAndView = null;
		String didYouKnowId = request.getParameter("id");
		getLogger().info("Printing the id " + didYouKnowId);
		DidYouKnow obj = (DidYouKnow) daoService.getObjectsById(DidYouKnow.class, "id", didYouKnowId);
		if (obj != null) {
			modelAndView = getBasicModelAndView("/admin/editDidYouKnow", "command", obj);
		} else {
			modelAndView = getBasicModelAndView("/admin/editDidYouKnow", "command", new DidYouKnow());
		}
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "admin/editDidYouKnowAction", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ModelAndView editDidYouKnowAction(@ModelAttribute("didYouKnowModel") DidYouKnow didYouKnowModel,
			BindingResult result, HttpServletRequest request, HttpServletResponse reponse, Model model) {

		String errorMessage = "";
		forceToAvoidBrowserCaching(reponse);
		boolean flag = false;
		try {
			didYouKnowModel.setLastModified(new Date());
			flag = daoService.saveOrUpdateEntity(didYouKnowModel);

		} catch (Exception err) {
			err.printStackTrace();
		}
		if (flag) {
			errorMessage = "Did you know updated successfully";
		} else {
			errorMessage = "Unable to update the did you know";
		}
		getLogger().info(errorMessage);
		String pagePath = PagePath.VIEW_ALL_DID_YOU_KNOW;
		ModelAndView modelAndView = null;
		modelAndView = getBasicModelAndView(pagePath);
		modelAndView.addObject("errorMessage", errorMessage);
		List<Object> didYouKnowData = daoService.getObjects(DidYouKnow.class, "lastModified");
		modelAndView.addObject("didYouKnowList", didYouKnowData);
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	@RequestMapping("admin/addDidYouKnow")
	@Secured("ROLE_ADMIN")
	public ModelAndView addDidYouKnow(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("About to add a did you know");
		if (!checkToken(request)) {
			return getBasicModelAndView(PagePath.ACCESS_DENIED);
		}
		forceToAvoidBrowserCaching(response);
		ModelAndView modelAndView = getBasicModelAndView("/admin/addDidYouKnow", "command", new DidYouKnow());
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "admin/addDidYouKnowAction", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ModelAndView addDidYouKnowAction(@ModelAttribute("didYouKnowModel") DidYouKnow didYouKnowModel,
			BindingResult result, HttpServletRequest request, HttpServletResponse reponse, Model model) {
		forceToAvoidBrowserCaching(reponse);
		String errorMessage = "";
		boolean flag = false;
		try {
			didYouKnowModel.setLastModified(new Date());
			flag = daoService.saveOrUpdateEntity(didYouKnowModel);

		} catch (Exception err) {
			err.printStackTrace();
		}
		if (flag) {
			errorMessage = "Did you know added successfully";
		} else {
			errorMessage = "Unable to add the did you know";
		}
		getLogger().info(errorMessage);
		String pagePath = PagePath.VIEW_ALL_DID_YOU_KNOW;
		ModelAndView modelAndView = null;
		modelAndView = getBasicModelAndView(pagePath);
		modelAndView.addObject("errorMessage", errorMessage);
		List<Object> didYouKnowData = daoService.getObjects(DidYouKnow.class, "lastModified");
		modelAndView.addObject("didYouKnowList", didYouKnowData);
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	@RequestMapping("admin/deleteDidYouKnow")
	@Secured("ROLE_ADMIN")
	public ModelAndView handleDeleteDidYouKnow(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("About to delete did you know");
		if (!checkToken(request)) {
			return getBasicModelAndView(PagePath.ACCESS_DENIED);
		}
		forceToAvoidBrowserCaching(response);
		ModelAndView modelAndView = null;
		String didYouKnowId = request.getParameter("id");
		getLogger().info("Printing the id " + didYouKnowId);
		DidYouKnow obj = (DidYouKnow) daoService.getObjectsById(DidYouKnow.class, "id", didYouKnowId);
		String errorMessage = "";
		if (obj != null) {
			daoService.deleteObject(obj);
			errorMessage = "Deleted the entry successfully";
		} else {
			getLogger().info("Unable to delete " + didYouKnowId);
			errorMessage = "Unable to delete the entry";
		}
		String pagePath = PagePath.VIEW_ALL_DID_YOU_KNOW;

		modelAndView = getBasicModelAndView(pagePath);
		modelAndView.addObject("errorMessage", errorMessage);
		List<Object> didYouKnowData = daoService.getObjects(DidYouKnow.class, "lastModified");
		modelAndView.addObject("didYouKnowList", didYouKnowData);
		addTokenToModel(modelAndView);
		return modelAndView;
	}
}
