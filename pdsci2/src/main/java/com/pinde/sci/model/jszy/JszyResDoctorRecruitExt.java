package com.pinde.sci.model.jszy;

import com.pinde.core.model.ResDoctorRecruit;
import com.pinde.core.model.SysUser;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResScore;

public class JszyResDoctorRecruitExt extends ResDoctorRecruit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675231490936611610L;
	
	private SysUser sysUser;
	private ResDoctor resDoctor;
	private ResScore resScore;

	public ResScore getResScore() {
		return resScore;
	}

	public void setResScore(ResScore resScore) {
		this.resScore = resScore;
	}

	public ResDoctor getResDoctor() {
		return resDoctor;
	}

	public void setResDoctor(ResDoctor resDoctor) {
		this.resDoctor = resDoctor;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	
}
