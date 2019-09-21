/**
 * 
 */
package com.pradheep.web.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import com.pradheep.dao.config.ApplicationLogger;
import com.pradheep.dao.config.MyGrantedAuthority;



/**
 * @author pradheep.p
 *
 */
public class MyAccessDeniedHandler extends AccessDeniedHandlerImpl {

	static final Logger logger = ApplicationLogger.getLogBean(MyAccessDeniedHandler.class);

	String errorPage = "";

	@Override
	public void handle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response,
			AccessDeniedException accessDeniedException) {
		logger.error(accessDeniedException.getMessage());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<MyGrantedAuthority> collection = (Collection<MyGrantedAuthority>) authentication.getAuthorities();
		Iterator<MyGrantedAuthority> iter = collection.iterator();
		errorPage = "/accessDenied";
		while(iter.hasNext()){
			MyGrantedAuthority authority = iter.next();
			if(authority.getAuthority().equalsIgnoreCase("ROLE_FIRST_TIME_LOGIN")){
				errorPage = "/changeYourPassword";
				break;
			}
		}
		setErrorPage(errorPage);
		try {
			response.sendRedirect(request.getContextPath() + getErrorPage());
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	@Override
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	public String getErrorPage() {
		return errorPage;
	}
}
