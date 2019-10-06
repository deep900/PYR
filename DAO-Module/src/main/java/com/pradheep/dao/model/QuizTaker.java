package com.pradheep.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quiz_taker")
public class QuizTaker implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue
	public Integer Id;

	@Column(name = "name")
	public String name;

	@Column(name = "email")
	public String email;

	@Column(name = "contact_number")
	public String contactNumber;
	
	@Column(name = "country")
	public String country;

	@Column(name = "subscribe")
	public Boolean isSubscribed;

	@Column(name = "verify_code")
	public String verifyCode;

	@Column(name = "verified")
	public Boolean isVerfied;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Boolean getIsSubscribed() {
		return isSubscribed;
	}

	public void setIsSubscribed(Boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Boolean getIsVerfied() {
		return isVerfied;
	}

	public void setIsVerfied(Boolean isVerfied) {
		this.isVerfied = isVerfied;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
}
