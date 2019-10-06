package com.pradheep.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "daily_sms_logger")
public class DailySMSLogger implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue
	public Integer Id;

	@Column(name = "mobile_number")
	public String mobileNumber;
	
	@Column(name = "last_sent_date")
	public Date lastSentDate;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Date getLastSentDate() {
		return lastSentDate;
	}

	public void setLastSentDate(Date lastSentDate) {
		this.lastSentDate = lastSentDate;
	}

	public String toString(){
		return "Mobile :"+ getMobileNumber() + " Last sent: " + getLastSentDate().toString();
	}
}
