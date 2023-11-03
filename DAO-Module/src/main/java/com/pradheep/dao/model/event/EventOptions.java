/**
 * 
 */
package com.pradheep.dao.model.event;

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
@Table(name = "event_options")
public class EventOptions implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer Id;

	@Column(name = "event_id")
	private Integer eventId;

	@Column(name = "option")
	private String option;

	@Column(name = "datatype")
	private String datatype;

	@Column(name = "option_menu_item")
	private String optionMenuItem;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getOptionMenuItem() {
		return optionMenuItem;
	}

	public void setOptionMenuItem(String optionMenuItem) {
		this.optionMenuItem = optionMenuItem;
	}

	@Override
	public String toString() {
		return "EventOptions [Id=" + Id + ", eventId=" + eventId + ", option=" + option + ", datatype=" + datatype
				+ ", optionMenuItem=" + optionMenuItem + "]";
	}

}
