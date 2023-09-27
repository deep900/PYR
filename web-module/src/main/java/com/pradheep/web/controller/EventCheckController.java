package com.pradheep.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pradheep.dao.model.event.EventParticipants;
import com.pradheep.web.common.event.EventManager;

/**
 * This controller is to validate the page before the form is submitted.
 * 
 * @author Pradheep
 */
@RestController
@RequestMapping("/api")
public class EventCheckController extends BaseUtility<Object> {

	@Autowired
	private EventManager eventManager;

	@RequestMapping(value = "/eventHost/checkMobileNumber", method = RequestMethod.GET)
	public String checkMobileNumber(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("Check if the mobile number already exists");
		try {
			String eventId = request.getParameter("eventId");
			String mobileNumber = request.getParameter("mobileNumber");
			String orgEventId = eventManager.decrypt(eventId);
			getLogger().info("Printing the mobile number: " + mobileNumber + ", eventId:" + orgEventId);
			Integer eventRef = Integer.parseInt(orgEventId);
			return String.valueOf(doesMobileNumberExists(mobileNumber, eventRef));
		} catch (Exception err) {
			getLogger().error("Error in checking the mobile number", err);
			return "false";
		}
	}

	@RequestMapping(value = "/eventHost/checkEmail", method = RequestMethod.GET)
	public String checkEmail(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("Check if the email already exists");
		try {
			String eventId = request.getParameter("eventId");
			String email = request.getParameter("email");
			String orgEventId = eventManager.decrypt(eventId);
			Integer eventRef = Integer.parseInt(orgEventId);
			return String.valueOf(doesEmailExists(email, eventRef));
		} catch (Exception err) {
			getLogger().error("Error in checking the email", err);
			return "false";
		}
	}

	private boolean doesMobileNumberExists(String mobileNumber, Integer eventId) {
		if (mobileNumber == null || mobileNumber.isEmpty() || eventId == null) {
			getLogger().info("Mobile number / Event ID cannot be null");
			return false;
		}
		List<EventParticipants> eventParticipants = eventManager.getParticipantsByMobile(mobileNumber, eventId);
		if (null == eventParticipants) {
			return false;
		} 
		return eventParticipants.size() > 0;			
	}

	private boolean doesEmailExists(String email, Integer eventId) {
		if (email == null || email.isEmpty() || eventId == null) {
			getLogger().info("Email / EventID cannot be null");
			return false;
		}
		List<EventParticipants> eventParticipants = eventManager.getParticipantsByEmail(email, eventId);
		if (null == eventParticipants) {
			return false;
		}
		return eventParticipants.size() > 0;			
	}
}
