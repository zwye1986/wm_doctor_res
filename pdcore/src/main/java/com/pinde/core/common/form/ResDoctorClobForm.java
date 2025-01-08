package com.pinde.core.common.form;

/**
 * //注册医师信息bean
 */
public class ResDoctorClobForm implements java.io.Serializable {

	private String doctorFlow;
	private String doctorName;
	private String userResume;

	public String getDoctorFlow() {
		return doctorFlow;
	}

	public void setDoctorFlow(String doctorFlow) {
		this.doctorFlow = doctorFlow;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getUserResume() {
		return userResume;
	}

	public void setUserResume(String userResume) {
		this.userResume = userResume;
	}
}
