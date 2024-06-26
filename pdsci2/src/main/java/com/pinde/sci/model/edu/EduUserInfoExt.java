package com.pinde.sci.model.edu;

import com.pinde.sci.model.mo.EduUser;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.SysUser;

public class EduUserInfoExt extends EduUser {
	private static final long serialVersionUID = 383549218879953465L;

	private SysUser sysUser;
	private ResDoctor resDoctor;
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
