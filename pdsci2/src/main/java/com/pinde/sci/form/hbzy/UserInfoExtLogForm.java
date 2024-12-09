package com.pinde.sci.form.hbzy;

import com.pinde.sci.model.mo.PubUserResume;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.core.model.SysUser;

import java.io.Serializable;
import java.util.Map;

public class UserInfoExtLogForm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7127481617315482792L;
	
	private SysUser sysUser;
	private ResDoctor doctor;
	private PubUserResume userResumeExt;
	private ResDoctorRecruit recruit;
	//导出花名册用
	private Map<String,String> userResumeExtMap;

	public ResDoctor getDoctor() {
		return doctor;
	}

	public void setDoctor(ResDoctor doctor) {
		this.doctor = doctor;
	}

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

	public PubUserResume getUserResumeExt() {
		return userResumeExt;
	}

	public void setUserResumeExt(PubUserResume userResumeExt) {
		this.userResumeExt = userResumeExt;
	}

	public Map<String, String> getUserResumeExtMap() {
		return userResumeExtMap;
	}

	public void setUserResumeExtMap(Map<String, String> userResumeExtMap) {
		this.userResumeExtMap = userResumeExtMap;
	}
}
