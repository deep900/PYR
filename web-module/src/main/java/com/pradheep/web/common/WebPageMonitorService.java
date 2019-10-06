/**
 * 
 */
package com.pradheep.web.common;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.pradheep.dao.config.DAOService;
import com.pradheep.dao.model.VisitCounter;

/**
 * @author pradheep.p
 *
 */
@Aspect
public class WebPageMonitorService {

	@Autowired
	DAOService daoService;

	@Autowired
	ThreadPoolTaskExecutor threadPoolTaskExecutor;

	public WebPageMonitorService() {
		System.out.println("--------------- Loading the Web Page Monitor Service --------------------");
	}

	private Logger logger = null;

	private Logger getLogger() {
		if (logger == null) {
			logger = ApplicationLoggerWeb.getLogBean(getClass());
		}
		return logger;
	}

	@After(value = "@annotation(monitorHitCounter)")
	public void handleRequest(JoinPoint jpObj, MonitorHitCounter monitorHitCounter) {
		String pagePath = monitorHitCounter.path();
		getLogger().info("Handling the increments according to page : " + pagePath);
		delegateUpdateOperation(pagePath);
	}

	private void delegateUpdateOperation(String pagePath) {
		getLogger().info("Inside delegate update operation");
		UpdatePagePathTask operation = new UpdatePagePathTask(pagePath);
		threadPoolTaskExecutor.execute(operation);
	}

	private void incrementPageCounter(String pagePath) {
		String queryParameter = "pageRef";
		VisitCounter visitCounter = (VisitCounter) daoService.getObjectsById(VisitCounter.class, queryParameter, pagePath);
		if (visitCounter != null) {
			long counter = Long.parseLong(visitCounter.getCounter());
			getLogger().info("Printing the visit counter for " + pagePath + ":" + counter);
			visitCounter.setCounter(String.valueOf(counter + 1));
			daoService.saveOrUpdateEntity(visitCounter);
		} else {
			getLogger().info("No visit counter exists for this page path: " + pagePath);
			VisitCounter visitCounterNew = new VisitCounter();
			visitCounterNew.setCounter("0");
			visitCounterNew.setPageRef(pagePath);
			daoService.saveOrUpdateEntity(visitCounterNew);
			getLogger().info("Added a new page path entry" + pagePath);
		}
	}

	class UpdatePagePathTask implements Runnable {
		String pagePath;

		UpdatePagePathTask(String pagePath) {
			this.pagePath = pagePath;
		}

		public void run() {
			incrementPageCounter(this.pagePath);
			getLogger().info("Increment page counter has been completed successfully");
		}
	}
}
