package com.pinde.sci.form.jsres;

import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.SysUser;

public class JsResDoctorInfoForm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7127481617315482792L;
	
	private SysUser user;
	private ResDoctor doctor;
	private UserResumeExtInfoForm userResumeExt;
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
	public UserResumeExtInfoForm getUserResumeExt() {
		return userResumeExt;
	}
	public void setUserResumeExt(UserResumeExtInfoForm userResumeExt) {
		this.userResumeExt = userResumeExt;
	}
	
}
