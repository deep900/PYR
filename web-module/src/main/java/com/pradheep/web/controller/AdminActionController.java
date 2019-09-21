package com.pradheep.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pradheep.web.common.MonitorHitCounter;
import com.pradheep.web.common.PagePath;

@Controller
public class AdminActionController extends BaseUtility {

	@Autowired
	private ApplicationLocaleResolver applicationLocaleResolver;

	@RequestMapping("admin/adminHome")
	@Secured("ROLE_ADMIN")
	@MonitorHitCounter(path = PagePath.ADMIN_HOME)
	public ModelAndView showAdminHome(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Loading the admin home page");
		ModelAndView model = getBasicModelAndView(PagePath.ADMIN_HOME);
		forceToAvoidBrowserCaching(response);
		addTokenToModel(model);
		applicationLocaleResolver.resolveLocale(request);
		addHitCounterToModel(model);
		return model;
	}

	public static void main(String args[]) {
	}

}
