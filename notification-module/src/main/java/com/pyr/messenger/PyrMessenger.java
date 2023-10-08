/**
 * 
 */
package com.pyr.messenger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;

import com.pry.security.utility.PublicUtility;
import com.pyr.notification.EmailMessageObject;
import com.pyr.notification.MessageObject;

/**
 * @author pradheep.p
 *
 */
public class PyrMessenger implements Messenger {

	private String email_user_primary = "";

	private String email_password_primary = "";

	private String basePath = "/home/praiseyourredeem/properties/";

	private String emailPropertiesFile = new String("email_settings_primary.properties");

	@Autowired
	private PublicUtility publicUtility;

	/**
	 * Returns true if the email has been sent successfully. Otherwise false.
	 * 
	 * @param emailMessageObject
	 * @return
	 */
	public boolean sendEmailMessage(EmailMessageObject emailMessageObject) {
		boolean flag = false;// Not sent by default;
		try {
			MimeMessage mimeMessage = prepareBasicEmailContent(emailMessageObject);
			Transport.send(mimeMessage);			
			System.out.println("Email sent to recipient..");
			flag = true;
		} catch (MessagingException e) {
			e.printStackTrace();
			flag = false;
		}		
		return flag;
	}	

	public MimeMessage prepareBasicEmailContent(EmailMessageObject emailMessageObject) {
		MimeMessage mimeMessage = establishPrimaryEmailConnection();
		String body = emailMessageObject.getBodyOfMessage();
		String subject = emailMessageObject.getSubjectOfMessage();
		String[] toAddress = emailMessageObject.getToList();
		String[] ccAddress = emailMessageObject.getCcList();
		String[] bccAddress = emailMessageObject.getBccList();
		String fromAddress = emailMessageObject.getFromAddress();

		try {
			mimeMessage.setSubject(subject);			
			if (emailMessageObject.getAttachment() != null) {
				System.out.println("Attaching file:" + emailMessageObject.getAttachment().getAbsolutePath());
				MimeBodyPart attachmentPart = new MimeBodyPart();
				attachmentPart.attachFile(emailMessageObject.getAttachment());
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(attachmentPart);
				BodyPart messageBodyPart = new MimeBodyPart();				
				messageBodyPart.setContent(body, "text/html; charset=utf-8");
				multipart.addBodyPart(messageBodyPart);				
				mimeMessage.setContent(multipart,"text/html; charset=utf-8");
			} else {
				mimeMessage.setContent(body, "text/html; charset=utf-8");
				System.out.println("Email content set as HTML");
				System.out.println("No email attachment.");
			}
			try {
				mimeMessage.setFrom(new InternetAddress(fromAddress, "Praise Your Redeemer"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			mimeMessage.setRecipients(MimeMessage.RecipientType.TO, getCommaSeperatedAddress(toAddress));
			if (ccAddress != null && ccAddress.length > 0) {
				mimeMessage.setRecipients(MimeMessage.RecipientType.CC, getCommaSeperatedAddress(ccAddress));
			}
			if (bccAddress != null && bccAddress.length > 0) {
				mimeMessage.setRecipients(MimeMessage.RecipientType.BCC, getCommaSeperatedAddress(bccAddress));
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
		return mimeMessage;
	}

	private Address[] getCommaSeperatedAddress(String[] address) {
		Address[] mailAddress = new Address[address.length];
		int i = 0;
		for (String obj : address) {
			try {
				System.out.println(">>-" + obj);
				if (obj == null) {
					continue;
				}
				mailAddress[i] = new InternetAddress(obj);
				i++;
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		return mailAddress;
	}

	private MimeMessage establishPrimaryEmailConnection() {
		try {
			Properties properties = getProperties();
			System.out.println("Printing the properties " + properties);
			email_user_primary = properties.getProperty("email_username").toString();
			email_password_primary = properties.getProperty("email_password").toString();
			email_password_primary = publicUtility.DecryptText(email_password_primary);
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email_user_primary, email_password_primary);
				}
			});
			MimeMessage mimeMessage = new MimeMessage(session);
			System.out.println("Establishing a session for sending an email..");
			return mimeMessage;
		} catch (Exception err) {
			err.printStackTrace();
		}
		return null;
	}

	public void testEmail() {
		EmailMessageObject email = new EmailMessageObject();
		email.setBodyOfMessage("Test Email - Do not Reply");
		email.setFooterInformation("- Praise Your Redeemer - Ministry");
		email.setSubjectOfMessage("Test Email");

		List<String> emails = new ArrayList<String>();
		emails.add("");
		Iterator<String> iter = emails.iterator();
		String[] args = new String[emails.size()];
		int i = 0;
		while (iter.hasNext()) {
			args[i] = iter.next();
			i++;
		}

		email.setToList(args);
		PyrMessenger messenger = new PyrMessenger();
		messenger.sendEmailMessage(email);

	}

	public Object communicate(MessageObject messageObject) {
		EmailMessageObject emailMessageObject = (EmailMessageObject) messageObject;
		System.out.println("Attachment :" + emailMessageObject.getAttachment());
		return sendEmailMessage(emailMessageObject);
	}

	public Properties getProperties() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(basePath + emailPropertiesFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

}
