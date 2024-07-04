package com.pinde.res.model.nfyy.mo;

import java.io.Serializable;

public class StudyInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dataFlow;
	
	private String no;
	
	private String name;
	
	private String dateTime;
	
	//学习情况
	private String study;
	
	//掌握情况
	private String master;
	
	private String userFlow;
	
	private String schDeptFlow;

	public String getStudy() {
		return study;
	}

	public void setStudy(String study) {
		this.study = study;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getDataFlow() {
		return dataFlow;
	}

	public void setDataFlow(String dataFlow) {
		this.dataFlow = dataFlow;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getUserFlow() {
		return userFlow;
	}

	public void setUserFlow(String userFlow) {
		this.userFlow = userFlow;
	}

	public String getSchDeptFlow() {
		return schDeptFlow;
	}

	public void setSchDeptFlow(String schDeptFlow) {
		this.schDeptFlow = schDeptFlow;
	}
	
	

}
