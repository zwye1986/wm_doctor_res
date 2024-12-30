package com.pinde.core.model;

public class ResEvaluationCfgItemExt implements java.io.Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = -5999934666390814967L;

	private String id;
	private String name;
	private String score;
	private String order;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	private String titleId;

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
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getTitleId() {
		return titleId;
	}
	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}
}
