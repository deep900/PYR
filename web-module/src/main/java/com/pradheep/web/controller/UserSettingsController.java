/**
 * 
 */
package com.pradheep.web.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pradheep.dao.model.Subscription;
import com.pradheep.web.common.ApplicationConstants;
import com.pradheep.web.common.PagePath;
import com.pradheep.web.event.ErrorNotificationEvent;
import com.pradheep.web.event.PyrApplicationEventPublisher;
import com.pradheep.web.event.SubscriptionNotificationEvent;

/**
 * @author pradheep.p
 *
 */
@Controller
public class UserSettingsController extends BaseUtility {

	static {
		System.out.println("Loading user settings controller..");
	}

	@Autowired
	private PyrApplicationEventPublisher eventPublisher;

	@RequestMapping(value = "/subscribeAction", method = RequestMethod.POST)
	public ModelAndView addSubscrption(@ModelAttribute("subscriptionObject") Subscription subscription,
			BindingResult result, HttpServletRequest request, HttpServletResponse reponse, Model model) {
		getLogger().info("Saving the subscription object " + subscription);
		String email = subscription.getEmailId();
		String errMsg = "";
		String name = subscription.getName();
		String captcha = request.getSession().getAttribute(ApplicationConstants.SECRET_KEY_CAPTCHA).toString();
		String inputCaptcha = request.getParameter("captchastr").toString();
		subscription.setOtp(generateOTP());
		if (!captcha.equals(inputCaptcha)) {
			errMsg = "Captcha string does not match.. Try again.";
			return getModelAndViewForSubscribe(errMsg);
		}

		if (null == name || name.isEmpty()) {
			errMsg = "Please enter your name, unable to process the subscription";
			return getModelAndViewForSubscribe(errMsg);
		}
		if (null == email || email.isEmpty()) {
			errMsg = "Please enter your valid email, unable to process the subscription";
			return getModelAndViewForSubscribe(errMsg);
		}
		Subscription obj = getSubscriptionByEmail(subscription.getEmailId());
		int eventType = ApplicationConstants.NEW_USER_ADDED;
		if (obj != null) {
			eventType = ApplicationConstants.USER_ALREADY_EXISTS;
			errMsg = "The given email id already exists, in our records and the subscription is activated.";
			subscription = obj;
			activateSubscription(subscription);
			daoService.saveOrUpdateEntity(subscription);
			publishEvent(subscription, eventType);
			return getModelAndViewForSubscribe(errMsg);
		}
		Subscription obj1 = getSubscriptionByContact(subscription.getContactNumber());
		if (obj1 != null) {
			eventType = ApplicationConstants.USER_ALREADY_EXISTS;
			errMsg = "The given contact already exists in our records. Subscription will be activated.";
			subscription = obj1;
			activateSubscription(subscription);
			daoService.saveOrUpdateEntity(subscription);
			publishEvent(subscription, eventType);
			return getModelAndViewForSubscribe(errMsg);
		}
		try {
			if(eventType == ApplicationConstants.NEW_USER_ADDED){
			activateSubscription(subscription);
			daoService.saveOrUpdateEntity(subscription);			
			errMsg = "Successfully added your subscription. You will receieve a welcome email.";			
			}
			publishEvent(subscription, eventType);
			
		} catch (Exception err) {
			errMsg = "Error while proceesing your request. Please try again later";
			publishErrorEvent(err);
		}
		return getModelAndViewForSubscribe(errMsg);
	}

	private void activateSubscription(Subscription subscription) {
		subscription.setApproved(true);
		subscription.setUnsubscribe(false);
	}
	
	private void publishEvent(Subscription subscription,int eventType){
		SubscriptionNotificationEvent notificationEvent = new SubscriptionNotificationEvent(subscription);
		notificationEvent.setEventType(eventType);
		publishEventPostSubscription(notificationEvent);
	}

	private ModelAndView getModelAndViewForSubscribe(String errorMsg) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(PagePath.SUBSCRIBE_ACTION);
		modelAndView.addObject("errorMessage", errorMsg);
		return modelAndView;
	}

	private  Subscription getSubscriptionByEmail(String emailId) {		
		Subscription subscriptionObj = (Subscription) daoService.getObjectsById(Subscription.class, "emailId", emailId);
		return subscriptionObj;
	}

	private Subscription getSubscriptionByContact(String mobileNumber) {		
		Subscription subscriptionObj = (Subscription) daoService.getObjectsById(Subscription.class, "contactNumber",
				mobileNumber);
		return subscriptionObj;
	}

	private void publishErrorEvent(Exception exception) {
		getLogger().info("About to publish an event on exception " + exception.getMessage());
		eventPublisher.publishEvent(new ErrorNotificationEvent(exception));
	}

	private void publishEventPostSubscription(SubscriptionNotificationEvent subscriptionNotificationEvent) {
		getLogger().info("About to publish an event on email subscription");
		Thread d = new Thread(){
			public void run(){				
				eventPublisher.publishEvent(subscriptionNotificationEvent);
			}
		};
		d.setName("publish event post subscribe thread");
		d.start();
	}

	private String generateOTP() {
		String arg = "ABCDEFGHIJKLMNOPQRSTUVWXYZabc123def456ghi789jkl0mnopqrstuvwxyz";
		StringBuffer result = new StringBuffer();
		Random ran = new Random();
		for (int i = 0; i <= 7; i++) {
			result.append(arg.charAt(ran.nextInt(arg.length() - 1)));
		}
		return result.toString();
	}

	@RequestMapping(PagePath.SUBSCRIBE)
	@Secured("ROLE_ADMIN")
	public ModelAndView addSubscription(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("About to add new subscription");
		if (!checkToken(request)) {
			return new ModelAndView(PagePath.ACCESS_DENIED);
		}
		request.getSession().setAttribute("captcha_allowed", "yes");
		ModelAndView modelAndView = new ModelAndView(PagePath.SUBSCRIBE, "subscriptionObject", new Subscription());
		addTokenToModel(modelAndView);
		return modelAndView;
	}

}
