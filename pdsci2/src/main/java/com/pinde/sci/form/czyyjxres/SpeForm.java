package com.pinde.sci.form.czyyjxres;

import java.io.Serializable;

public class SpeForm implements Serializable{
	
	/**
	 * 进修专业
	 */
	private String speId;

	private String speName;

	/**
	 * 进修时间
	 */
	private String stuTimeId;

	/**
	 * 进修时间段
	 */
    private String beginDate;
    private String endDate;

	public String getSpeId() {
		return speId;
	}

	public void setSpeId(String speId) {
		this.speId = speId;
	}

	public String getSpeName() {
		return speName;
	}

	public void setSpeName(String speName) {
		this.speName = speName;
	}

	public String getStuTimeId() {
		return stuTimeId;
	}

	public void setStuTimeId(String stuTimeId) {
		this.stuTimeId = stuTimeId;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
