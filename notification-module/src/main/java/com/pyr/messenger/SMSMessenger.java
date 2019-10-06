package com.pyr.messenger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.pry.security.utility.PublicUtility;
import com.pyr.notification.MessageObject;
import com.pyr.templates.DailySMSMessageObject;

public class SMSMessenger implements Messenger {

	@Autowired
	ThreadPoolTaskExecutor threadPoolExecutor;
	
	@Autowired
	private PublicUtility publicUtility;
	
	private String basePath = "/home/pyr/properties/";

	private String smsPropertiesFile = new String("sms.properties");

	public SMSMessenger() {

	}

	private Logger getLogger() {
		return NotificationLogger.getLogBean(this.getClass());
	}

	
	@Override
	public Object communicate(MessageObject messageObject) {
		Thread d = new Thread() {
			public void run() {
				getLogger().info("Inside communicate method ");
				if (messageObject instanceof DailySMSMessageObject) {
					String messageToBeSent = messageObject.getBodyOfMessage();
					messageToBeSent = messageToBeSent.replace(" ", "%20");
					DailySMSMessageObject smsMessageobj = (DailySMSMessageObject) messageObject;
					int dcsCode;
					if (smsMessageobj.isUnicode()) {
						dcsCode = 8;
					} else {
						dcsCode = 0;
					}
					if (messageToBeSent.length() > 160) {
						getLogger().info(
								"Message length exceeds 160 Characters - Cannot send SMS" + messageToBeSent.length());
					}
					String contactNumber = smsMessageobj.getContactNumber();
					if (!isIndiaNumber(smsMessageobj.getDestinationCountryName())) {
						getLogger().info(
								"SMS can be sent only in India : Target- " + smsMessageobj.getDestinationCountryName());
						return;
					}
					if (isValidNumber(contactNumber)) {
						String url = constructURL(messageToBeSent, contactNumber, dcsCode);
						getLogger().info("Printing the URL " + url);
						CloseableHttpResponse response = makeHttpPostAndRespond(url);
						if (response != null) {
							getLogger().info("Printing the response message " + response.getStatusLine());
						}
					}
				}
			}
		};
		threadPoolExecutor.execute(d);
		return true;
	}

	private boolean isIndiaNumber(String country) {
		if (country.equalsIgnoreCase("india")) {
			return true;
		}
		return false;
	}

	private boolean isValidNumber(String contactNumber) {
		boolean flag = false;
		try {
			Long.parseLong(contactNumber);
			flag = true;
		} catch (Exception err) {
			flag = false;
		}
		if (contactNumber.length() == 10) {
			flag = flag & true;
		} else {
			flag = flag & false;
		}
		if (contactNumber.startsWith("0")) {
			flag = flag & false;
		}
		return flag;
	}

	private String constructURL(String message, String contactNumber, int dcsCode) {
		Properties properties = getProperties();
		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String senderId = properties.getProperty("senderId");
		String channel = properties.getProperty("channel");
		String dcs = properties.getProperty("DCS");
		String flashsms = properties.getProperty("flashsms");
		String route = properties.getProperty("route");
		String baseUrl = url + "user=" + user + "&" + "password=" + publicUtility.DecryptText(password) + "&" + "senderid=" + senderId + "&"
				+ "channel=" + channel + "&" + "DCS=" + dcs + "&" + "flashsms=" + flashsms + "&" + "number=91"
				+ contactNumber + "&" + "text=" + message + "&" + "route=" + route;		
		return baseUrl;
	}

	private CloseableHttpResponse makeHttpPostAndRespond(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		// httpPut.setParams(httpParams);
		CloseableHttpResponse response1 = null;
		try {
			response1 = httpclient.execute(httpGet);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			getLogger().error("Error :" + e.getLocalizedMessage());
		} catch (IOException e) {
			e.printStackTrace();
			getLogger().error("Error :" + e.getLocalizedMessage());
		}
		return response1;
	}	

	public static void main(String args[]) {
		SMSMessenger messenger = new SMSMessenger();
		String url = messenger.constructURL("God is Good", "", 1);
		CloseableHttpResponse response = messenger.makeHttpPostAndRespond(url);
		System.out.println(response.getStatusLine().toString());
	}
	
	private Properties getProperties(){
		Properties properties = new Properties();
	    try {
			properties.load(new FileInputStream(basePath + smsPropertiesFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	    
	    return properties;
	}
}
