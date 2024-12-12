package com.pinde.sci.form.hbres;

import com.pinde.core.model.ResDoctorRecruit;
import com.pinde.core.model.SysUser;
import com.pinde.sci.model.mo.ResDoctor;

public class ReplenishInfoForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private SysUser user;
	
	private ResDoctor doctor;

    private com.pinde.core.model.ResDoctorRecruit recruit;
	
	private ExtInfoForm extInfo;

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

	public ResDoctorRecruit getRecruit() {
		return recruit;
	}

    public void setRecruit(com.pinde.core.model.ResDoctorRecruit recruit) {
		this.recruit = recruit;
	}

	public ExtInfoForm getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(ExtInfoForm extInfo) {
		this.extInfo = extInfo;
	}
}
