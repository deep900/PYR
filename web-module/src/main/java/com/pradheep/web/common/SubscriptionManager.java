/**
 * 
 */
package com.pradheep.web.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pradheep.dao.config.DAOService;
import com.pradheep.dao.model.Subscription;

/**
 * @author deep90
 *
 */
public class SubscriptionManager {

	@Autowired
	private DAOService daoService;

	private List<Subscription> subscriptionList = new ArrayList<Subscription>();

	public List<Subscription> getSubscriptionList() {
		subscriptionList.clear();

		for (Object subscriber : getAllSubscribers()) {
			Subscription obj = (Subscription) subscriber;
			subscriptionList.add(obj);
		}
		return subscriptionList;
	}

	public void setSubscriptionList(List<Subscription> subscriptionList) {
		this.subscriptionList = subscriptionList;
	}

	private List<Object> getAllSubscribers() {
		List<Object> objects = daoService.getObjectsListById(Subscription.class, "approved", new Boolean(true), "=",
				-1);
		if (objects == null) {
			return Collections.emptyList();
		}
		return objects;
	}
}
