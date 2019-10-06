package com.pradheep.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.LocaleResolver;

public class ApplicationLocaleResolver implements LocaleResolver {

  private Map<String, Properties> propertyMap = new HashMap<String, Properties>();  

  Locale localeObj;

  public Locale resolveLocale(HttpServletRequest arg0) {
	String param = arg0.getParameter("lang");
	System.out.println("Printing the param :" + param);
	localeObj = arg0.getLocale();
	System.out.println("printing the country : " + localeObj.getCountry());
	String langCode = null;
	if(arg0.getSession().getAttribute("langCode") != null){
	langCode = arg0.getSession().getAttribute("langCode").toString();
	}
	if(param != null && param.equalsIgnoreCase("ta")){		
	Locale newLocale =  new Locale.Builder().setLanguage("ta").setRegion("IN").build();
	arg0.getSession().setAttribute("langCode", "ta");
	return newLocale;
	}
	else if(param != null){
		arg0.getSession().setAttribute("langCode", param);	
	}
	//-----------------------------------------------------------------------------//
	if(langCode != null && param == null && langCode.equalsIgnoreCase("ta")){
		Locale newLocale =  new Locale.Builder().setLanguage("ta").setRegion("IN").build();		
		return newLocale;
	}
	else if(langCode != null && param == null){
		Locale newLocale =  new Locale.Builder().setLanguage("en").setRegion("US").build();		
		return newLocale;
	}
	//-----------------------------------------------------------------------------//
    System.out.println("Resolve Locale " + localeObj.getLanguage());
    System.out.println("Lang ::" +  localeObj.getDisplayLanguage());
    return localeObj;
  }

  public void setLocale(HttpServletRequest arg0, HttpServletResponse arg1, Locale arg2) {    
    localeObj = arg2;
    arg1.setLocale(arg2);
  }

  private Properties getOrCreateProperties(String language) {
    System.out.println(">>language detected : " + language);
    if (propertyMap.containsKey(language)) {
      Properties prop = propertyMap.get(language);
      return prop;
    } else {
      Properties prop = new Properties();
      ClassPathResource classPathResource =
          new ClassPathResource("messages_" + language.toLowerCase() + ".properties");
      try {
        prop.load(classPathResource.getInputStream());
        propertyMap.put(language.toLowerCase(), prop);
        return prop;
      } catch (IOException e) {        
        e.printStackTrace();
      }
    }
    return null;
  }

  public String getProperty(String propertyName) {
    Properties prop = null;
    String language = this.localeObj.getDisplayLanguage().toLowerCase();
    System.out.println("Printing the language " + language);
    System.out.println("Trying to get the property ");
    System.out.println("Getting property : " + propertyName);
    if (language.contains("english")) {
      prop = getOrCreateProperties("english");
    } else if (language.contains("de")) {
      prop = getOrCreateProperties("de");
    } else if (language.contains("ta")) {
      prop = getOrCreateProperties("ta");
    }
    if (prop != null) {
      System.out.println(propertyName);
      return prop.getProperty(propertyName);
    }
    return "NA";
  }
}
