package com.pinde.sci.form;

import com.pinde.sci.model.mo.*;

public class JsRecruitDocInfoExt extends JsresRecruitDocInfo {
	
	private SysUser sysUser;
	private ResDoctor resDoctor;
	private JsresRecruitInfo recruitInfo;
	private PubUserResume userResume;

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

	public JsresRecruitInfo getRecruitInfo() {
		return recruitInfo;
	}

	public void setRecruitInfo(JsresRecruitInfo recruitInfo) {
		this.recruitInfo = recruitInfo;
	}

	public PubUserResume getUserResume() {
		return userResume;
	}

	public void setUserResume(PubUserResume userResume) {
		this.userResume = userResume;
	}
}
