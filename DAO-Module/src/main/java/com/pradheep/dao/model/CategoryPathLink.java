package com.pradheep.dao.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "page_path_category")
public class CategoryPathLink implements Serializable {

	@Id
	@Column(name = "path_id")
	@GeneratedValue
	public Integer pageId;
	
	@Column(name = "page_path_title")
	public String pageTitle;
	
	@Column(name = "page_url")
	public String urlOfPage;

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getUrlOfPage() {
		return urlOfPage;
	}

	public void setUrlOfPage(String urlOfPage) {
		this.urlOfPage = urlOfPage;
	}
	
}
