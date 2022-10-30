/**
 * 
 */
package com.pradheep.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Pradheep
 *
 */
@Entity
@Table(name = "prophesy_and_fullfilment")
public class ProphesyModel {

	@Id
	@Column(name = "id")
	@GeneratedValue
	public Integer id;

	@Column(name = "search_key")
	public String searchKey;

	@Column(name = "prophetic_scrip")
	public String propheticScrip;

	@Column(name = "subject")
	public String subject;

	@Column(name = "fulfilment")
	public String fulfilment;

	@Column(name = "book")
	public String book;

	private static final String delimiter = "#";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getPropheticScrip() {
		return propheticScrip;
	}

	public void setPropheticScrip(String propheticScrip) {
		this.propheticScrip = propheticScrip;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFulfilment() {
		return fulfilment;
	}

	public void setFulfilment(String fulfilment) {
		this.fulfilment = fulfilment;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getStringFormat() {
		return getId() + delimiter + getSearchKey() + delimiter + getPropheticScrip() + delimiter + getSubject()
				+ delimiter + getFulfilment();
	}

}
