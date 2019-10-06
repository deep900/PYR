/**
 * 
 */
package com.pradheep.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author pradheep.p
 *
 */
@Entity
@Table(name = "messages")
public class Message implements Serializable{
	
	@Id
	@Column(name = "id")
	@GeneratedValue
	public Integer Id;
	
	@Column(name = "message_title")
	public String messageTitleEnglish;
	
	@Column(name = "message_title_ta")
	public String messageTitleTamil;
	
	@Column(name = "short_description")
	public String shortDescriptionEnglish;
	
	@Column(name = "short_description_ta")
	public String shortDescriptionTamil;
	
	@Column(name = "message_en")
	public byte[] messageEnglish;
	
	@Column(name = "message_ta")
	public byte[] messageTamil;
	
	@Column(name = "last_modified")
	public Date lastModified;	
	
	@Column(name = "short_message_photo")
	public byte[] shortMessagePhoto;
	
	@Column(name = "long_message_photo")
	public byte[] longMessagePhoto;
	
	@Column(name = "category_id")
	public int categoryReference;
	
	public boolean photoStatus = false;
	
	public String msgTamilForDisplay;
	
	public String msgEnglishForDisplay;
	
	public String lastModifiedDisplay;	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getMessageTitleEnglish() {
		return messageTitleEnglish;
	}

	public void setMessageTitleEnglish(String messageTitleEnglish) {
		this.messageTitleEnglish = messageTitleEnglish;
	}

	public String getMessageTitleTamil() {
		return messageTitleTamil;
	}

	public void setMessageTitleTamil(String messageTitleTamil) {
		this.messageTitleTamil = messageTitleTamil;
	}

	public String getShortDescriptionEnglish() {
		return shortDescriptionEnglish;
	}

	public void setShortDescriptionEnglish(String shortDescriptionEnglish) {
		this.shortDescriptionEnglish = shortDescriptionEnglish;
	}

	public String getShortDescriptionTamil() {
		return shortDescriptionTamil;
	}

	public void setShortDescriptionTamil(String shortDescriptionTamil) {
		this.shortDescriptionTamil = shortDescriptionTamil;
	}

	public byte[] getMessageEnglish() {
		return messageEnglish;
	}

	public void setMessageEnglish(byte[] messageEnglish) {
		this.messageEnglish = messageEnglish;
	}

	public byte[] getMessageTamil() {
		return messageTamil;
	}

	public void setMessageTamil(byte[] messageTamil) {
		this.messageTamil = messageTamil;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public byte[] getShortMessagePhoto() {
		return shortMessagePhoto;
	}

	public void setShortMessagePhoto(byte[] shortMessagePhoto) {
		this.shortMessagePhoto = shortMessagePhoto;
	}

	public byte[] getLongMessagePhoto() {
		return longMessagePhoto;
	}

	public void setLongMessagePhoto(byte[] longMessagePhoto) {
		this.longMessagePhoto = longMessagePhoto;
	}

	public int getCategoryReference() {
		return categoryReference;
	}

	public void setCategoryReference(int categoryReference) {
		this.categoryReference = categoryReference;
	}
	
	public boolean getPhotoStatus(){
		if(getShortMessagePhoto() == null){
			return false;
		}
		if(getLongMessagePhoto() == null){
			return false;
		}
		return true;
	}

	public String getMsgTamilForDisplay() {
		return msgTamilForDisplay;
	}

	public void setMsgTamilForDisplay(String msgTamilForDisplay) {
		this.msgTamilForDisplay = msgTamilForDisplay;
	}

	public String getMsgEnglishForDisplay() {
		return msgEnglishForDisplay;
	}

	public void setMsgEnglishForDisplay(String msgEnglishForDisplay) {
		this.msgEnglishForDisplay = msgEnglishForDisplay;
	}

	public String getLastModifiedDisplay() {
		return lastModifiedDisplay;
	}

	public void setLastModifiedDisplay(String lastModifiedDisplay) {
		this.lastModifiedDisplay = lastModifiedDisplay;
	}	

	public void setPhotoStatus(boolean photoStatus) {
		this.photoStatus = photoStatus;
	}
	
}
