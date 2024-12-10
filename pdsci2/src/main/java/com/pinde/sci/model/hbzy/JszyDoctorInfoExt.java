package com.pinde.sci.model.hbzy;

import com.pinde.sci.model.mo.PubUserResume;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.core.model.SysUser;

public class JszyDoctorInfoExt extends ResDoctorRecruit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675231490936611610L;
	
	private SysUser sysUser;
	private ResDoctor resDoctor;
	private PubUserResume userResume;


	public PubUserResume getUserResume() {
		return userResume;
	}

	public void setUserResume(PubUserResume userResume) {
		this.userResume = userResume;
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
