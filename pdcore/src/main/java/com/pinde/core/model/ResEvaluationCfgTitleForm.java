package com.pinde.core.model;

import java.util.List;

public class ResEvaluationCfgTitleForm implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1343184604234324999L;
	
	private String id;
	private String name;
	private String score;
	private String evalTypeId;
	private String evalTypeName;
	private List<ResEvaluationCfgItemForm> itemList;
	
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
	public List<ResEvaluationCfgItemForm> getItemList() {
		return itemList;
	}
	public void setItemList(List<ResEvaluationCfgItemForm> itemList) {
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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
}
