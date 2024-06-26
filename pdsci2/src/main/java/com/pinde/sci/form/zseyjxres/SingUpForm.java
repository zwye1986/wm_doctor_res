package com.pinde.sci.form.zseyjxres;

import com.pinde.sci.model.mo.StuUserResume;
import com.pinde.sci.model.mo.SysUser;

import java.io.Serializable;

public class SingUpForm implements Serializable{

	private SysUser user;

	private StuUserResume stuUser;
	
	private ExtInfoForm extInfo;

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public StuUserResume getStuUser() {
		return stuUser;
	}

	public void setStuUser(StuUserResume stuUser) {
		this.stuUser = stuUser;
	}

	public ExtInfoForm getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(ExtInfoForm extInfo) {
		this.extInfo = extInfo;
	}
}
