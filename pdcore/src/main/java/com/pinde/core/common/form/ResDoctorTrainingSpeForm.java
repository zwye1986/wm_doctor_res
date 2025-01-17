package com.pinde.core.common.form;

public class ResDoctorTrainingSpeForm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5767464030553268603L;
	
	private String speId;
	private String doctorCount;
	
	public String getDoctorCount() {
		return doctorCount;
	}
	public void setDoctorCount(String doctorCount) {
		this.doctorCount = doctorCount;
	}
	public String getSpeId() {
		return speId;
	}
	public void setSpeId(String speId) {
		this.speId = speId;
	}
}
