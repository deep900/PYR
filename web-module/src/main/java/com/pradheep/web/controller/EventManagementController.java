/**
 * 
 */
package com.pradheep.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pradheep.dao.model.event.EventModel;
import com.pradheep.dao.model.event.EventOptions;
import com.pradheep.dao.model.event.EventParticipants;
import com.pradheep.dao.model.event.EventParticipantsMembers;
import com.pradheep.dao.model.event.EventWrapper;
import com.pradheep.web.common.MonitorHitCounter;
import com.pradheep.web.common.PagePath;
import com.pradheep.web.common.event.EventManager;
import com.pradheep.web.event.NewUserEventRegistrationEvent;
import com.pradheep.web.event.PyrApplicationEventPublisher;
import com.pradheep.web.service.EventManagementService;

/**
 * This is for managing the events and its participants.
 * 
 * @author Pradheep
 *
 */
@Controller
public class EventManagementController extends BaseUtility<Object> {

	@Autowired
	private EventManager eventManager;

	@Autowired
	private EventManagementService eventManagementService;

	@Autowired
	private PyrApplicationEventPublisher pryEventPublisher;

	@RequestMapping("/eventHost/register")
	@MonitorHitCounter(path = PagePath.EVENT_PAGE)
	public ModelAndView getRegistrationPage(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("About to register the user details.");
		String eventId = request.getParameter("eventId");
		String orgEventId = eventManager.decrypt(eventId);
		String errorMsg = "";
		boolean isFoodOptionRequired = true;
		boolean isExtraOptionsRequired = true;
		ModelAndView model = new ModelAndView(PagePath.EVENT_PAGE, "command", new EventParticipants());
		try {
			EventModel event = eventManager.getEventById(orgEventId);
			isFoodOptionRequired = event.isFoodOptionRequired();
			model.addObject("eventModel", event);
			int eventObjId = Integer.parseInt(orgEventId);
			List<EventOptions> eventOptionsList = eventManager.getEventOptions(eventObjId);
			isExtraOptionsRequired = (eventOptionsList != null) && !eventOptionsList.isEmpty();
			if (isExtraOptionsRequired) {
				getLogger().info("Printing the event options" + eventOptionsList.toString());
			}
			getLogger().info("Is Extra options required" + isExtraOptionsRequired);
			model.addObject("eventOptionsList", eventOptionsList);
			if (eventManagementService.isEventEnded(event)) {
				getLogger().info("Event already ended" + eventId);
				model.setViewName(PagePath.EVENT_END_PAGE);
				errorMsg = "Event already ended, kindly contact the event host";
			}
		} catch (Exception err) {
			errorMsg = "Please check the event id";
			getLogger().error("Error while obtaining the event", err);
			model.setViewName(PagePath.EVENT_END_PAGE);
		}
		model.addObject("errorMsg", errorMsg);
		model.addObject("eventId", orgEventId);
		model.addObject("context", request.getContextPath());
		model.addObject("isFoodOptionRequired", isFoodOptionRequired);
		model.addObject("isExtraOptionsRequired", isExtraOptionsRequired);
		return model;
	}

	@RequestMapping(value = "/events/submitRegistration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@MonitorHitCounter(path = PagePath.SUBMIT_EVENT_PAGE)
	public ModelAndView submitRegistrationForm(@RequestBody MultiValueMap<String, String> formData,
			BindingResult result, HttpServletRequest request, HttpServletResponse reponse,
			@ModelAttribute("eventParticipants") EventParticipants eventParticipants) {
		ModelAndView model = new ModelAndView(PagePath.SUBMIT_EVENT_PAGE);
		String errorMsg = "";
		getLogger().info("Printing form data:" + formData.toString());
		boolean isFoodOptionRequired = true;
		List<String> eventId = formData.get("eventId");
		int eveId = getEventId(eventId);
		try {
			EventModel event = eventManager.getEventById(String.valueOf(eveId));
			isFoodOptionRequired = event.isFoodOptionRequired();
			model.addObject("eventModel", event);
		} catch (Exception err) {
			errorMsg = "Please check the event id";
			getLogger().error("Error while obtaining the event", err);
		}
		eventParticipants.setEventId(eveId);
		if (null == eventParticipants.getDinnerTime() || eventParticipants.getDinnerTime().isEmpty()) {
			eventParticipants.setDinnerTime("Post-Dinner");
		}
		getLogger().info("Printing the event participants:" + eventParticipants.toString());
		EventWrapper wrapper = new EventWrapper();
		wrapper.setEventParticipants(eventParticipants);
		List<String> childMembers = formData.get("childMembers.name");
		List<String> childFoodPreference = formData.get("childMembers.foodPreference");
		if (null != childMembers) {
			if (childMembers.size() > 0) {
				wrapper.getEventParticipantMembers()
						.addAll(getEventParticipantMembers(childMembers, childFoodPreference, true));
			}
		}
		List<String> adultMembers = formData.get("adultMembers.name");
		List<String> adultFoodPreference = formData.get("adultMembers.foodPreference");
		if (null != adultMembers) {
			if (adultMembers.size() > 0) {
				wrapper.getEventParticipantMembers()
						.addAll(getEventParticipantMembers(adultMembers, adultFoodPreference, false));
			}
		}
		if (!isFoodOptionRequired) {
			updateFoodOptionNotRequired(wrapper);
		}
		int flag = eventManager.saveEventParticipants(wrapper);
		if (flag != -1) {
			model.addObject("errorMsg",
					"You have successfully registered , Kindly check your email for registration QR Code. <br><br> You will need to produce the QR code during registration on the day of event. <br><br> Also take note of your reference number if you have not received your QR code.<br><br> <b> Your reference is: "
							+ flag + "</b>");
			notifyEventRegistration(eventParticipants);
		} else {
			model.addObject("errorMsg", "Unable to register at this moment. Kindly try again later");
		}
		return model;
	}

	private void updateFoodOptionNotRequired(EventWrapper wrapper) {
		wrapper.getEventParticipants().setDinnerTime("Default");
		wrapper.getEventParticipants().setFoodPreference("Default");
		wrapper.getEventParticipantMembers().forEach(eventParticipant -> {
			eventParticipant.setFoodPreference("Default");
		});
	}

	/**
	 * This is to send an email to event organizer about the new event
	 * registration happened.
	 * 
	 * @param eventParticipant
	 */
	private void notifyEventRegistration(EventParticipants eventParticipant) {
		getLogger().info("Publishing the event participant: " + eventParticipant.toString());
		pryEventPublisher.publishEvent(new NewUserEventRegistrationEvent(eventParticipant));
	}

	private int getEventId(List<String> eventId) {
		AtomicInteger eId = new AtomicInteger();
		eventId.forEach(x -> {
			if (!x.isEmpty()) {
				eId.set(Integer.parseInt(x));
			}
		});
		return eId.get();
	}

	private List<EventParticipantsMembers> getEventParticipantMembers(List<String> memberNames,
			List<String> foodPreference, boolean isChild) {
		List<EventParticipantsMembers> eventParticipntMembers = new ArrayList<EventParticipantsMembers>();
		for (int i = 0; i < memberNames.size(); i++) {
			String name = memberNames.get(i);
			String foodPref = "";
			if (foodPreference != null && !foodPreference.isEmpty()) {
				foodPref = foodPreference.get(i);
			}
			EventParticipantsMembers member = new EventParticipantsMembers();
			member.setChild(isChild);
			member.setName(name);
			member.setFoodPreference(getFoodMapping(foodPref));
			eventParticipntMembers.add(member);
		}
		return eventParticipntMembers;
	}

	private String getFoodMapping(String foodOption) {
		switch (foodOption) {
		case "Veg":
			return "Vegeterian";
		case "Non-Veg":
			return "Non-Vegeterian";
		case "Food Not Required":
			return "Food Not Required";
		case "Food-Not-Required":
			return "Food Not Required";
		case "Non-Veg-Hallal":
			return "Non-Vegeterian-Hallal";
		default:
			return "Veg";
		}
	}

}
