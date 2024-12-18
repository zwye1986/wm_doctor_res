package com.pinde.sci.model.jsres;

import com.pinde.core.model.PubUserResume;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorRecruit;
import com.pinde.core.model.SysUser;

public class JsDoctorInfoExt extends ResDoctorRecruit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675231490936611610L;
	
	private SysUser sysUser;
	private ResDoctor resDoctor;
	private PubUserResume userResume;

	private String armyType;


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

	public String getArmyType() {
		return armyType;
	}

	public void setArmyType(String armyType) {
		this.armyType = armyType;
	}
}
