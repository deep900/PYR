/**
 * 
 */
package com.pradheep.web.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pradheep.p
 *
 */
public class QuizResult {
	
	public int result;
	public int totalQuestions;
	public Map<Integer,String> userChoiceMap = new HashMap<Integer,String>();

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
}
