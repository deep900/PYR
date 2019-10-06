package com.pradheep.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "did_you_know")
public class DidYouKnow implements java.io.Serializable{
	@Id
	@Column(name = "id")
	@GeneratedValue
	public int id;
	
	@Column(name = "didyouknow")
	public String didYouKnowContent;
	
	@Column(name = "didyouknow_ta")
	public String didYouKnowContentTamil;
	
	@Column(name = "last_modified")
	public Date lastModified;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDidYouKnowContent() {
		return didYouKnowContent;
	}

	public void setDidYouKnowContent(String didYouKnowContent) {
		this.didYouKnowContent = didYouKnowContent;
	}

	public String getDidYouKnowContentTamil() {
		return didYouKnowContentTamil;
	}

	public void setDidYouKnowContentTamil(String didYouKnowContentTamil) {
		this.didYouKnowContentTamil = didYouKnowContentTamil;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
}
