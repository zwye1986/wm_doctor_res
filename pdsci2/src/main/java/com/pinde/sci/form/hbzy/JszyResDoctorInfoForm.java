package com.pinde.sci.form.hbzy;

import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.SysUser;

public class JszyResDoctorInfoForm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7127481617315482792L;
	
	private SysUser user;
	private ResDoctor doctor;
	private BaseUserResumeExtInfoForm userResumeExt;
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	public ResDoctor getDoctor() {
		return doctor;
	}
	public void setDoctor(ResDoctor doctor) {
		this.doctor = doctor;
	}
	public BaseUserResumeExtInfoForm getUserResumeExt() {
		return userResumeExt;
	}
	public void setUserResumeExt(BaseUserResumeExtInfoForm userResumeExt) {
		this.userResumeExt = userResumeExt;
	}
	
}
