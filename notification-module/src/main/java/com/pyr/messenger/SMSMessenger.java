package com.pyr.messenger;

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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.pry.security.utility.PublicUtility;
import com.pyr.notification.MessageObject;
import com.pyr.templates.DailySMSMessageObject;

public class SMSMessenger implements Messenger {

	// http://login.blesssms.com/api/mt/SendSMS?user=Pradheep&password=8610279716&senderid=Praise&channel=Trans&DCS=0&flashsms=0&number=917401504728&text=Welcome%20to%20PYR&route=1

	private String appKey = "IMXYlDmP4f4=";

	// private NotificationLogger notificationLogger;

	@Autowired
	ThreadPoolTaskExecutor threadPoolExecutor;

	private Properties prop = new Properties();

	public SMSMessenger() {
		loadProperties();
	}

	private Logger getLogger() {
		return NotificationLogger.getLogBean(this.getClass());
	}

	private void loadProperties() {
		Resource res = new ClassPathResource("sms.properties");
		try {
			prop.load(res.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
					if(messageToBeSent.length() > 160){
						getLogger().info("Message length exceeds 160 Characters - Cannot send SMS" + messageToBeSent.length());						
					}
					String contactNumber = smsMessageobj.getContactNumber();
					if(!isIndiaNumber(smsMessageobj.getDestinationCountryName())){
						getLogger().info("SMS can be sent only in India : Target- "+ smsMessageobj.getDestinationCountryName());
						return;
					}
					if (isValidNumber(contactNumber)) {
						String url = constructURL(messageToBeSent, contactNumber, dcsCode);
						getLogger().info("Printing the URL " + url);
						CloseableHttpResponse response = makeHttpPostAndRespond(url,
								getBasicParams(contactNumber, messageToBeSent));
						if(response != null){
							getLogger().info("Printing the response message " + response.getStatusLine());
						}
					}		
				}
			}
		};
		threadPoolExecutor.execute(d);
		return true;
	}

	private boolean isIndiaNumber(String country){
		if(country.equalsIgnoreCase("india")){
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
		//StringBuffer urlObj = new StringBuffer();
		loadProperties();
		// message = message.replace(" ", "%");
		String baseUrl = "http://login.blesssms.com/api/mt/SendSMS?user=Pradheep&password=8610279716&senderid=Praise&channel=Trans&DCS="
				+ dcsCode + "&flashsms=0&number=91" + contactNumber + "&text=" + message + "&route=1";// prop.getProperty("url");

		// System.out.println("Printing the base URL :" + baseUrl);
		//makeHttpPostAndRespond(baseUrl, getBasicParams(contactNumber, message));
		return baseUrl;
	}

	private CloseableHttpResponse makeHttpPostAndRespond(String url, BasicHttpParams httpParams) {
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

	private BasicHttpParams getBasicParams(String contactNumber, String message) {
		String user = prop.getProperty("user");
		String password = PublicUtility.getInstance(appKey).DecryptText(prop.getProperty("password"));
		String senderid = prop.getProperty("senderid");
		String channel = prop.getProperty("channel");
		String route = prop.getProperty("route");
		String dcs = prop.getProperty("DCS");
		BasicHttpParams httpParams = new BasicHttpParams();
		httpParams.setParameter("user", user);
		httpParams.setParameter("password", password);
		httpParams.setParameter("senderid", senderid);
		httpParams.setParameter("channel", channel);
		httpParams.setParameter("DCS", dcs);
		httpParams.setParameter("number=91", contactNumber);
		httpParams.setIntParameter("flashsms", 0);
		httpParams.setParameter("text", message);
		httpParams.setParameter("route", route);

		// urlObj.append(baseUrl).append("?").append("user=" +
		// user).append("&").append("password=" + password);
		// urlObj.append("&").append("senderid=" +
		// senderid).append("&").append("channel=" + channel);
		// urlObj.append("&").append("DCS=" +
		// dcs).append("&").append("flashsms=0");
		// urlObj.append("&").append("number=91" + contactNumber);
		// urlObj.append("&").append("text=").append(message).append("&route=").append(route);
		return httpParams;
	}

	public static void main(String args[]) {
		
		 SMSMessenger messenger = new SMSMessenger(); String url =
		 messenger.constructURL("Chottu%20Pintu%20Is%20Good", "7401504728",1);
		 CloseableHttpResponse response =
		 messenger.makeHttpPostAndRespond(url,messenger.getBasicParams(
		 "7401504728", "Welcome"));
		System.out.println(response.getStatusLine().toString());
		
	}

}
