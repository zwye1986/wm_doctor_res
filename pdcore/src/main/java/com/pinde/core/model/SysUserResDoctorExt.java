package com.pinde.core.model;

import java.util.List;

public class SysUserResDoctorExt extends SysUser{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2686353456324323455L;
	
	private ResDoctor doctor;
	
	private List<ResDoctorSchProcess> resDoctorSchProcessList;

	public ResDoctor getDoctor() {
		return doctor;
	}

	public void setDoctor(ResDoctor doctor) {
		this.doctor = doctor;
	}

	public List<ResDoctorSchProcess> getResDoctorSchProcessList() {
		return resDoctorSchProcessList;
	}

	public void setResDoctorSchProcessList(
			List<ResDoctorSchProcess> resDoctorSchProcessList) {
		this.resDoctorSchProcessList = resDoctorSchProcessList;
	}

	
	
	
}
