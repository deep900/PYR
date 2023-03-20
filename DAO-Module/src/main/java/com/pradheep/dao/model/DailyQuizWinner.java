package com.pradheep.dao.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "daily_quiz_winner")
public class DailyQuizWinner implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue
	public Integer id;

	@Column(name = "name")
	public String name;
	
	@Column(name = "email")
	public String email;
	
	@Column(name = "answer_time")
	public Timestamp answer_time;
	
	@Column(name = "language")
	public String language;
	
	@Column(name = "quiz_id")
	public Integer quiz_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getQuiz_id() {
		return quiz_id;
	}

	public void setQuiz_id(Integer quiz_id) {
		this.quiz_id = quiz_id;
	}

	public Timestamp getAnswer_time() {
		return answer_time;
	}

	public void setAnswer_time(Timestamp answer_time) {
		this.answer_time = answer_time;
	}	
	
	@Override
	public String toString() {
		return "DailyQuizWinner [id=" + id + ", name=" + name + ", email=" + email + ", answer_time=" + answer_time
				+ ", language=" + language + ", quiz_id=" + quiz_id + "]";
	}
	
}
