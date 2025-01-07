package com.pinde.core.model;

public class ResDoctorSchProcessExt extends ResDoctorSchProcess {
	
	private static final long serialVersionUID = -4345822451119941541L;
	
	private ResDoctorExt doctorExt;

	public ResDoctorExt getDoctorExt() {
		return doctorExt;
	}

	public void setDoctorExt(ResDoctorExt doctorExt) {
		this.doctorExt = doctorExt;
	}
}
