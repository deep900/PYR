package com.pradheep.dao.model;
// Generated Jun 14, 2017 11:13:22 AM by Hibernate Tools 5.2.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "subscription")
public class Subscription implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue
	public Integer id;
	
	@Column(name = "name")
	public String name;
	
	@Column(name = "email_id")
	public String emailId;
	
	@Column(name = "contact_number")
	public String contactNumber;
	
	@Column(name = "country")
	public String country;
	
	@Column(name = "address")
	public String address;
	
	@Column(name = "approved")
	public boolean approved;
	
	@Column(name = "unsubscribe")
	public Boolean unsubscribe;
	
	@Column(name="preferred_language")
	public String preferredLanguage;
	
	@Column(name = "OTP")
	public String otp;

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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Boolean getUnsubscribe() {
		return unsubscribe;
	}

	public void setUnsubscribe(Boolean unsubscribe) {
		this.unsubscribe = unsubscribe;
	}
	
	public String toString(){
		return getName() + "," + getAddress() + "," + getContactNumber() + "," + getEmailId() + "," + getCountry();
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}
}
