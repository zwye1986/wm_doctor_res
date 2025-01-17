package com.pinde.core.model;

public class ResExamDoctorExt extends ResExamDoctor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResDoctor doctor;
	private SysUser user;
	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public ResDoctor getDoctor() {
		return doctor;
	}

	public void setDoctor(ResDoctor doctor) {
		this.doctor = doctor;
	}
	
	
	
}
