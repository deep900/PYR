/**
 * 
 */
package com.pradheep.web.common;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author Pradheep
 *
 */
@Component
public class UniqueIDGenerator {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
	
	private Base64.Encoder b64Encoder = Base64.getUrlEncoder();

	private Logger logger = null;

	public Logger getLogger() {
		if (logger == null) {
			logger = ApplicationLoggerWeb.getLogBean(getClass());
		}
		return logger;
	}

	public String getTodayFormattedDate() {
		Date todayObj = new Date();
		String dateStr = sdf.format(todayObj);
		getLogger().info("Printing the Today formatted " + dateStr);
		return dateStr;
	}

	public String getMessageReaderUniqueID() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getTodayFormattedDate());		
		return b64Encoder.encodeToString(buffer.toString().getBytes());

	}

	public static void main(String args[]) {
		UniqueIDGenerator uniqueIDGenerator = new UniqueIDGenerator();
		System.out.println(uniqueIDGenerator.getMessageReaderUniqueID());
	}

	public boolean isValidLink(String uniqueId) {
		try {
			if (null == uniqueId || uniqueId.isEmpty()) {
				return false;
			}
			return b64Encoder.encodeToString(getTodayFormattedDate().getBytes()).equals(uniqueId);
		} catch (Exception err) {
			getLogger().error("Unable to find this link as valid one.", err);			
		}
		return false;
	}

}
