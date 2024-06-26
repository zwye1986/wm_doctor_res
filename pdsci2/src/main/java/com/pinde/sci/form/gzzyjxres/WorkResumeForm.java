package com.pinde.sci.form.gzzyjxres;

import java.io.Serializable;

public class WorkResumeForm implements Serializable{
	
	/**
	 * 工作起止时间
	 */
	private String clinicalRoundDate;
	
	/**
	 * 医院名称(工作单位)
	 */
	private String hospitalName;

	/**
	 * 工作描述
	 */
	private String workDescription;
	
	/**
	 * 职务
	 */
	private String postName;

	public String getClinicalRoundDate() {
		return clinicalRoundDate;
	}

	public void setClinicalRoundDate(String clinicalRoundDate) {
		this.clinicalRoundDate = clinicalRoundDate;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}


}
