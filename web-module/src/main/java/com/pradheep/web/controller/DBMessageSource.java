/**
 * 
 */
package com.pradheep.web.controller;

import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;



/**
 * This class will be used where all the properties for locale display will be loaded from a
 * Database.
 * 
 * @author pradheep.p
 * 
 */

public class DBMessageSource extends AbstractMessageSource {


  @Autowired
  ApplicationLocaleResolver responseContants;

  private String unicodeToString(String unicode) {
    String str = unicode.split(" ")[0];
    // str = str.replace("\\","");
    String[] arr = str.split("\\");
    System.out.println(arr);
    String text = "";
    for (int i = 1; i < arr.length; i++) {
      try {
        int hexVal = Integer.parseInt(arr[i], 16);
        text += (char) hexVal;
      } catch (Exception err) {
        err.printStackTrace();
      }
    }
    return text;
  }

  @Override
  protected MessageFormat resolveCode(String code, Locale arg1) {
    System.out.println("Printing the code " + code);
    System.out.println("Language >>" + arg1.getLanguage());
    responseContants.getProperty(code);
    return null;
  }
}
