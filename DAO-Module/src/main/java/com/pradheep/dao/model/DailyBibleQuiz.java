/**
 * 
 */
package com.pradheep.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author deep90
 *
 */
@Entity
@Table(name = "daily_bible_quiz")
public class DailyBibleQuiz implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue
	public int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "quiz_date")
	public String quizDate;

	@Column(name = "language")
	public String language;

	@Column(name = "quiz_id")
	public Integer quizId;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getQuizDate() {
		return quizDate;
	}

	public void setQuizDate(String quizDate) {
		this.quizDate = quizDate;
	}

	public Integer getQuizId() {
		return quizId;
	}

	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}

}
