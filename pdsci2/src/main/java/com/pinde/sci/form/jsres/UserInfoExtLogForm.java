package com.pinde.sci.form.jsres;

import com.pinde.sci.model.mo.ResDoctorLog;
import com.pinde.sci.model.mo.ResDoctorRecruitLog;
import com.pinde.sci.model.mo.ResUserResumeLog;
import com.pinde.sci.model.mo.SysUserLog;

import java.io.Serializable;

public class UserInfoExtLogForm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7127481617315482792L;
	
	private SysUserLog sysUser;
	private ResDoctorLog doctor;
	private ResUserResumeLog userResumeExt;
	private ResDoctorRecruitLog recruit;

	public SysUserLog getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserLog sysUser) {
		this.sysUser = sysUser;
	}

	public ResDoctorLog getDoctor() {
		return doctor;
	}

	public void setDoctor(ResDoctorLog doctor) {
		this.doctor = doctor;
	}

	public ResUserResumeLog getUserResumeExt() {
		return userResumeExt;
	}

	public void setUserResumeExt(ResUserResumeLog userResumeExt) {
		this.userResumeExt = userResumeExt;
	}

	public ResDoctorRecruitLog getRecruit() {
		return recruit;
	}

	public void setRecruit(ResDoctorRecruitLog recruit) {
		this.recruit = recruit;
	}
}
