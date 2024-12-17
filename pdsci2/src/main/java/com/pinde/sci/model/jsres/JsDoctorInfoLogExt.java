package com.pinde.sci.model.jsres;

import com.pinde.core.model.ResDoctorLog;
import com.pinde.core.model.ResDoctorRecruitLog;
import com.pinde.core.model.ResUserResumeLog;
import com.pinde.core.model.SysUserLog;

public class JsDoctorInfoLogExt extends ResDoctorRecruitLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675231490936611610L;
	
	private SysUserLog sysUser;
	private ResDoctorLog resDoctor;
	private ResUserResumeLog userResume;

	public SysUserLog getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserLog sysUser) {
		this.sysUser = sysUser;
	}

	public ResUserResumeLog getUserResume() {
		return userResume;
	}

	public void setUserResume(ResUserResumeLog userResume) {
		this.userResume = userResume;
	}

	public ResDoctorLog getResDoctor() {
		return resDoctor;
	}

	public void setResDoctor(ResDoctorLog resDoctor) {
		this.resDoctor = resDoctor;
	}
}
