package com.pradheep.dao.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	@Id
	@Column(name = "category_id")
	@GeneratedValue
	public Integer categoryId;

	@Column(name = "category_name")
	public String categoryName;
	
	@Column(name = "category_name_ta")
	public String categoryNameTamil;


	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)/*, fetch = FetchType.EAGER, mappedBy = "category", targetEntity = com.pradheep.dao.model.AllCategoryAndPath.class)*/
	@JoinColumn(name="category_id")
	public Set<AllCategoryAndPath> setOfPaths;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<AllCategoryAndPath> getSetOfPaths() {
		return setOfPaths;
	}

	public void setSetOfPaths(Set<AllCategoryAndPath> setOfPaths) {
		this.setOfPaths = setOfPaths;
	}

	public String getCategoryNameTamil() {
		return categoryNameTamil;
	}

	public void setCategoryNameTamil(String categoryNameTamil) {
		this.categoryNameTamil = categoryNameTamil;
	}

}
