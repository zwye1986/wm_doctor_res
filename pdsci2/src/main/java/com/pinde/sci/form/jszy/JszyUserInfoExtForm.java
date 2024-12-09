package com.pinde.sci.form.jszy;

import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.core.model.SysUser;

import java.io.Serializable;

public class JszyUserInfoExtForm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7127481617315482792L;
	
	private SysUser sysUser;
	private ResDoctor doctor;
	private BaseUserResumeExtInfoForm userResumeExt;
	private ResDoctorRecruit recruit;
	public ResDoctorRecruit getRecruit() {
		return recruit;
	}
	public void setRecruit(ResDoctorRecruit recruit) {
		this.recruit = recruit;
	}
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	public ResDoctor getDoctor() {
		return doctor;
	}
	public void setDoctor(ResDoctor doctor) {
		this.doctor = doctor;
	}
	public BaseUserResumeExtInfoForm getUserResumeExt() {
		return userResumeExt;
	}
	public void setUserResumeExt(BaseUserResumeExtInfoForm userResumeExt) {
		this.userResumeExt = userResumeExt;
	}
	
}
