package com.pinde.res.model.nfyy.mo;

import java.io.Serializable;

public class MedicalInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dataFlow;
	
	//住院号
	private String no;
	
	private String name;
	
	//入院时间
	private String inDateTime;
	//患者名称
	private String patient;
	//诊断
	private String diagnosis;
	//经治医生 带教老师
	private String tecName;
	
	private String dateTime;
	
	//学习情况
	private String study;
	
	//掌握情况
	private String master;
	
	//医师ID
	private String userFlow;
	
	//轮转科室流水号
	private String schDeptFlow;

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

	public String getInDateTime() {
		return inDateTime;
	}

	public void setInDateTime(String inDateTime) {
		this.inDateTime = inDateTime;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTecName() {
		return tecName;
	}

	public void setTecName(String tecName) {
		this.tecName = tecName;
	}
	
	
	

}
