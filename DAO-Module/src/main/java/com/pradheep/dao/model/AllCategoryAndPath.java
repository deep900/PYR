/**
 * 
 */
package com.pradheep.dao.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author pradheep.p
 *
 */
@Entity
@Table(name = "cat_path_link")
public class AllCategoryAndPath implements Serializable {
	
	@Id
	@Column(name = "unique_id")
	@GeneratedValue
	public Integer uniqueId;
	
	@Column(name="category_id")
	public Integer categoryId;
	
	@Column(name="page_id")
	public Integer page_id;
	
	/*@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="category_id")
	public Category categoryObj;
	*/
	/*@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)	
	public CategoryPathLink categoryPathLinkObj;*/

	public Integer getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Integer uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getPage_id() {
		return page_id;
	}

	public void setPage_id(Integer page_id) {
		this.page_id = page_id;
	}

	/*public CategoryPathLink getCategoryPathLinkObj() {
		return categoryPathLinkObj;
	}

	public void setCategoryPathLinkObj(CategoryPathLink categoryPathLinkObj) {
		this.categoryPathLinkObj = categoryPathLinkObj;
	}
*/
	/*public Category getCategoryObj() {
		return categoryObj;
	}

	public void setCategoryObj(Category categoryObj) {
		this.categoryObj = categoryObj;
	}

	public CategoryPathLink getCategoryPathLinkObj() {
		return categoryPathLinkObj;
	}

	public void setCategoryPathLinkObj(CategoryPathLink categoryPathLinkObj) {
		this.categoryPathLinkObj = categoryPathLinkObj;
	}*/
}
