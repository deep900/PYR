/**
 * 
 */
package com.pradheep.web.jobs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.pradheep.dao.config.DAOService;
import com.pradheep.web.common.ApplicationLoggerWeb;

/**
 * @author deep90
 *
 */
public class DBCleanupTask extends CommonTask {

	private List<String> sqlQuery = new ArrayList<String>();

	private ApplicationContext applicationContext;

	private Logger getLogger() {
		return ApplicationLoggerWeb.getLogBean(this.getClass());
	}

	private void prepareStatements() {
		sqlQuery.clear();
		String sql = "DELETE FROM daily_quiz_winner where answer_time < '" + getCleanUpQuizDate() + " 00:00:00'";
		sqlQuery.add(sql);
	}

	public String getCleanUpQuizDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(new Date());
		gregorianCalendar.add(GregorianCalendar.DAY_OF_MONTH, -2);
		String parsedDate = sdf.format(gregorianCalendar.getTime());
		getLogger().info("Printing the date:" + parsedDate);
		return parsedDate;
	}

	@Override
	public void run() {
		try {
			prepareStatements();
			getLogger().info("Starting to cleanup DB " + getTaskName());
			sqlQuery.forEach(x -> {
				getLogger().info("Excuting:" + x);
				int results = executeStatements(x);
				getLogger().info("Records impacted:" + results);
			});
			getLogger().info("Completed " + getTaskName());
		} catch (Exception err) {
			getLogger().error("Error while executing the statements", err);
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.applicationContext = context;
	}

	@Override
	public long getStartDelayInMilliSecs() {
		return 0;
	}

	@Override
	public void onSuccess() {
		getLogger().info("Completed the task : " + getTaskName());
	}

	@Override
	public void onFailure() {

	}

	private int executeStatements(String sql) {
		DAOService daoService = this.applicationContext.getBean(DAOService.class);
		return daoService.executeUsingNativeSQL(sql);
	}

	@Override
	public String getTaskName() {
		return "DB Cleanup Task";
	}

}
