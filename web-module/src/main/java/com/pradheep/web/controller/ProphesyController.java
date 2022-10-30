package com.pradheep.web.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pradheep.dao.model.ProphesyModel;
import com.pradheep.web.common.MonitorHitCounter;
import com.pradheep.web.common.PagePath;

@Controller
public class ProphesyController extends BaseUtility<Object> {	
	
	@RequestMapping("/prophecy-and-fulfillment")
	@MonitorHitCounter(path = PagePath.PROPHECY_AND_FULFILLMENT)
	public ModelAndView getImportantVerses(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Showing the prophesy in the bible and its fullfilment.");
		Locale locale = applicationLocaleResolver.resolveLocale(request);
		ModelAndView model = null;
		if (locale.getLanguage().equalsIgnoreCase("ta")) {
			model = new ModelAndView(PagePath.UNDER_DEVELOPMENT);
		} else {
			model = new ModelAndView(PagePath.PROPHECY_AND_FULFILLMENT);
			List<Object> prophesyModelList = getAllProphesy();
			model.addObject("prophesyModel", prophesyModelList);		
		}
		return model;
	}
	
	private List<Object> getAllProphesy(){
		return daoService.getObjects(ProphesyModel.class);
	}
	
}