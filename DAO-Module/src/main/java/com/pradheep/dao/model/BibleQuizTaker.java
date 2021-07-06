/**
 * 
 */
package com.pradheep.dao.model;

/**
 * @author deep90
 *
 */
public class BibleQuizTaker {
	
	private String name;
	
	private String email;
	
	private String quizId;
	
	private String answer;
	
	private String language;
	
	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "BibleQuizTaker [" + (name != null ? "name=" + name + ", " : "")
				+ (email != null ? "email=" + email + ", " : "") + (quizId != null ? "quizId=" + quizId + ", " : "")
				+ (answer != null ? "answer=" + answer + ", " : "") + (language != null ? "language=" + language : "")
				+ "]";
	}
}
