package com.pinde.core.model;

public class ResDoctorExt extends ResDoctor {

	private SysUser sysUser;
	
	private ResDoctorRecruit doctorRecruit;

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public ResDoctorRecruit getDoctorRecruit() {
		return doctorRecruit;
	}

	public void setDoctorRecruit(ResDoctorRecruit doctorRecruit) {
		this.doctorRecruit = doctorRecruit;
	}
	
	
}
