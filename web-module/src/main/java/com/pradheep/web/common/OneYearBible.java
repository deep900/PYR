/**
 * 
 */
package com.pradheep.web.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * This class provides the advice on the daily chapter reading and the previous
 * day.
 * 
 * @author pradheep.p
 *
 */
public class OneYearBible {

	private Logger logger = null;

	private SimpleDateFormat sdf = new SimpleDateFormat("MMM d");

	private HashMap<String, String> chapterMap = new HashMap<String, String>();
	private HashMap<String, String> chapterMapTamil = new HashMap<String, String>();

	private Logger getLogger() {
		if (logger == null) {
			logger = ApplicationLoggerWeb.getLogBean(getClass()).getLogger("Daily Verse");
		}
		return logger;
	}

	public OneYearBible() {
		loadMap();
		loadMapTamil();
	}

	/**
	 * 
	 * @param date
	 *            - Date input format should be like Jan 1 or Dec 23
	 * @return - Returns the chapter that can be read for that day.
	 */
	public String getTodayChapter(String date,String language) throws IllegalArgumentException {
		if(language.equalsIgnoreCase("en")){
		if (chapterMap.containsKey(date)) {
			return chapterMap.get(date);
		} else {
			throw new IllegalArgumentException("Check the date format - it should be like Jan 1 or Dec 23");
		}
		}
		else if(language.equalsIgnoreCase("ta")){
			if (chapterMapTamil.containsKey(date)) {
				return chapterMapTamil.get(date);
			} else {
				throw new IllegalArgumentException("Check the date format - it should be like Jan 1 or Dec 23");
			}
		}
		return "";
	}

	private void loadMap() {
		getLogger().info("Loading the one year bible chapter verses ..");
		Resource res = new ClassPathResource("one_year_bible.txt");
		InputStream inputStream = null;
		try {
			inputStream = res.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader bReader = new BufferedReader(reader);
			String str = "";
			while ((str = bReader.readLine()) != null) {
				String[] args = str.split(":");
				chapterMap.put(args[0], args[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		getLogger().info("-------------Loading one year bible chapter complete ------ ");
	}
	
	private void loadMapTamil() {
		getLogger().info("Loading the one year bible chapter verses tamil..");
		Resource res = new ClassPathResource("one_year_bible_ta.txt");
		InputStream inputStream = null;
		try {
			inputStream = res.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader bReader = new BufferedReader(reader);
			String str = "";
			while ((str = bReader.readLine()) != null) {
				String[] args = str.split(":");
				chapterMapTamil.put(args[0], args[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		getLogger().info("-------------Loading one year bible chapter in tamil complete ------ ");
	}
	
	
	public String getURLToRead(String language,String chapter){
		return null;
	}

	public static void main(String args[]) {

	}
}
