package com.pinde.res.model.jswjw.mo;

import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorOrgHistory;
import com.pinde.core.model.SysUser;

public class JsResDoctorOrgHistoryExt extends ResDoctorOrgHistory{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675231490936611610L;
	
	private ResDoctor resDoctor;

	private SysUser sysUser;

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public ResDoctor getResDoctor() {
		return resDoctor;
	}

	public void setResDoctor(ResDoctor resDoctor) {
		this.resDoctor = resDoctor;
	}
}
