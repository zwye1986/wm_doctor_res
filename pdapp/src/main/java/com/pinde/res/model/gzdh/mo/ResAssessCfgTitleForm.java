package com.pinde.res.model.gzdh.mo;

import java.io.Serializable;
import java.util.List;

public class ResAssessCfgTitleForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1343184604234324999L;
	
	private String id;
	private String name;
	private String score;
	private String evalTypeId;
	private String evalTypeName;
	private List<ResAssessCfgItemForm> itemList;

	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ResAssessCfgItemForm> getItemList() {
		return itemList;
	}
	public void setItemList(List<ResAssessCfgItemForm> itemList) {
		this.itemList = itemList;
	}

	public String getEvalTypeId() {
		return evalTypeId;
	}

	public void setEvalTypeId(String evalTypeId) {
		this.evalTypeId = evalTypeId;
	}

	public String getEvalTypeName() {
		return evalTypeName;
	}

	public void setEvalTypeName(String evalTypeName) {
		this.evalTypeName = evalTypeName;
	}
	
}
