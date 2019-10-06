package com.pradheep.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "quiz_taker_entry")
public class QuizTakerEntry implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue
	public Integer Id;

	@OneToOne
	@JoinColumn(name="quiz_taker_id")
	public QuizTaker quizTaker;

	@Column(name = "date")
	public Date dateOfQuiz;

	@Column(name = "quiz_question_ids")
	public String questionIds;

	@Column(name = "quiz_level")
	public String quizLevel;

	@Column(name = "bible_portion")
	public String biblePortion;

	@Column(name = "quiz_results")
	public Boolean quizResults;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public QuizTaker getQuizTaker() {
		return quizTaker;
	}

	public void setQuizTaker(QuizTaker quizTaker) {
		this.quizTaker = quizTaker;
	}

	public Date getDateOfQuiz() {
		return dateOfQuiz;
	}

	public void setDateOfQuiz(Date dateOfQuiz) {
		this.dateOfQuiz = dateOfQuiz;
	}

	public String getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(String questionIds) {
		this.questionIds = questionIds;
	}

	public String getQuizLevel() {
		return quizLevel;
	}

	public void setQuizLevel(String quizLevel) {
		this.quizLevel = quizLevel;
	}

	public String getBiblePortion() {
		return biblePortion;
	}

	public void setBiblePortion(String biblePortion) {
		this.biblePortion = biblePortion;
	}

	public Boolean getQuizResults() {
		return quizResults;
	}

	public void setQuizResults(Boolean quizResults) {
		this.quizResults = quizResults;
	}

	
}
