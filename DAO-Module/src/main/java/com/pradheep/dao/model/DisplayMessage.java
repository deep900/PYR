package com.pradheep.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DisplayMessage extends Message implements Serializable {

	public List<String> listedParaTamil = new ArrayList<String>();
	public List<String> listedParaEnglish = new ArrayList<String>();

	public List<String> getListedParaTamil() {
		return listedParaTamil;
	}

	public void setListedParaTamil(List<String> listedParaTamil) {
		this.listedParaTamil = listedParaTamil;
	}

	public List<String> getListedParaEnglish() {
		return listedParaEnglish;
	}

	public void setListedParaEnglish(List<String> listedParaEnglish) {
		this.listedParaEnglish = listedParaEnglish;
	}

}
