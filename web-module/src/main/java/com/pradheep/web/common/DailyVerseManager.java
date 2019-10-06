package com.pradheep.web.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pradheep.dao.config.DAOService;
import com.pradheep.dao.model.BibleVerse;
import com.pradheep.dao.model.DailyVerseEntry;

/**
 * This class provides the daily verse based on the date.
 * @author pradheep.p
 *
 */
@Component
public class DailyVerseManager {

	@Autowired
	DAOService daoService;	

	private Logger logger = null;

	private SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyy");	

	private Logger getLogger() {
		if (logger == null) {
			logger = ApplicationLoggerWeb.getLogBean(getClass()).getLogger("Daily Verse");
		}
		return logger;
	}

	public BibleVerse getTodayVerse() {
		String todayDate = getTodayDate();
		getLogger().info("Printing today date " + todayDate);
		String queryParam = "date";
		DailyVerseEntry bVerseEntry = (DailyVerseEntry) daoService.getObjectsById(DailyVerseEntry.class, queryParam, todayDate);
		if (bVerseEntry != null) {
			int bVerseId = bVerseEntry.getBibleVerseRef();			
			BibleVerse bibleVerse = (BibleVerse) daoService.getObjectsById(BibleVerse.class, "id", String.valueOf(bVerseId));
			return bibleVerse;
		}
		getLogger().info("Unable to find the verse for today" + todayDate);
		BibleVerse bVerse = null;
		if (bVerse == null) {
			bVerse = getRecommendedVerse(getPreviousDate());
		}
		makeEntry(bVerse,todayDate);
		return bVerse;
	}

	/*private int findNextEntry(int verseIdRef) {
		String arg = "";
		for (int i = verseIdRef + 1; i < verseIdRef + 10; i++) {
			arg = arg + i;
			if (i < verseIdRef + 9) {
				arg = arg + ",";
			}
		}

		getLogger().info("Printing the next set of values" + arg);
		String sql = "";
		if (verseIdRef == -1) {
			sql = "select id from bible_verse limit 1";
		} else {
			sql = "select id from bible_verse where id in " + arg;
		}
		List<Object> bibleVerses = daoService.queryUsingNativeSQL(sql);
		if (bibleVerses == null || bibleVerses.isEmpty()) {
			sql = "select * from bible_verse limit 1";
			List<Object> bibleVerses1 = daoService.queryUsingNativeSQL(sql);
			int id = (Integer) bibleVerses1.get(0);
			return id;
		} else {
			Object obj = bibleVerses.get(0);
			getLogger().info("Printing the class name " + obj.getClass().getName());
			getLogger().info("Printing the value " + obj.toString());
			getLogger().info("Yes it is a instance of Bible Verse");
			int id = (Integer) obj;
			getLogger().info("Printing the next verse id " + id);
			return id;
		}

	}*/

	private BibleVerse getRecommendedVerse(String date) {
		getLogger().info("Inside get recommended verse " + date);		
		String queryParam = "date";
		DailyVerseEntry bibleVerseEntry = (DailyVerseEntry) daoService.getObjectsById(DailyVerseEntry.class, queryParam, date);
		int verseIdRef = 0;
		String entryDate = date;
		if (bibleVerseEntry != null) {
			verseIdRef = bibleVerseEntry.getBibleVerseRef();			
		}
		List<Object> bibleVerses = daoService.getObjects(BibleVerse.class);
		java.util.Iterator iter = bibleVerses.iterator();
		BibleVerse verse = null;
		while(iter.hasNext()){
			Object obj = iter.next();
			if(obj instanceof BibleVerse){
				BibleVerse bVerse = (BibleVerse) obj;
				if(verseIdRef == 0){
					verse =  bVerse;//Return the first verse;
					break;
				}
				if((bVerse.getId() == verseIdRef) && iter.hasNext()){
					verse = (BibleVerse) iter.next();
					break;
				}
			}
		}
		if(verse == null){
			java.util.Iterator iter1 = bibleVerses.iterator();
			while(iter1.hasNext()){
				verse = (BibleVerse) iter1.next();
				break;
			}
		}
		return verse;
	}

	private void makeEntry(BibleVerse bibleVerse, String entryDate) {
		DailyVerseEntry verseEntry = new DailyVerseEntry();
		verseEntry.setBibleVerseRef(bibleVerse.getId());
		verseEntry.setDate(entryDate);
		daoService.saveOrUpdateEntity(verseEntry);
		getLogger().info("Successfully made an entry for " + entryDate);
	}

	public String getPreviousDate() {
		Date date = new Date();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(GregorianCalendar.DAY_OF_MONTH, -1);
		String previousDate = sdf.format(calendar.getTime());
		return previousDate;
	}

	public String getTodayDate() {
		Date date = new Date();		
		return sdf.format(date);		
	}

}
