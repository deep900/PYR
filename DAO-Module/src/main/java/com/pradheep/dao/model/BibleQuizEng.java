package com.pradheep.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bible_quiz_en")
public class BibleQuizEng extends QuizBaseModel implements java.io.Serializable,BibleQuiz {
	
	@Id
	@Column(name = "id")
	@GeneratedValue
	public Integer Id;

	@Column(name = "level")
	public String level;

	@Column(name = "testament")
	public String testament;

	@Column(name = "question")
	public String question;

	@Column(name = "choice")
	public String choice;

	@Column(name = "c_answer")
	public String correctAnswer;

	@Column(name = "reference")
	public String bibleReference;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTestament() {
		return testament;
	}

	public void setTestament(String testament) {
		this.testament = testament;
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getBibleReference() {
		return bibleReference;
	}

	public void setBibleReference(String bibleReference) {
		this.bibleReference = bibleReference;
	}

	@Override
	public String toString() {
		return "BibleQuizEng [Id=" + Id + ", level=" + level + ", testament=" + testament + ", question=" + question
				+ ", choice=" + choice + ", correctAnswer=" + correctAnswer + ", bibleReference=" + bibleReference
				+ "]";
	}
}
