/**
 * 
 */
package com.pradheep.web.common;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import com.pradheep.web.jobs.CommonTask;

/**
 * @author pradheep.p
 *
 */
public class CommonTaskExecutor extends ConcurrentLinkedQueue<CommonTask> {

	@Autowired
	TaskExecutor threadPoolTaskExecutor;

	public synchronized void executeTask() {
		CommonTask commonTaskObj = null;
		while ((commonTaskObj = this.poll()) != null) {
			threadPoolTaskExecutor.execute(commonTaskObj);
		}
	}	
}
