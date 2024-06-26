package com.pinde.sci.model.hbzy;

import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorOrgHistory;

public class JszyResDoctorOrgHistoryExt extends ResDoctorOrgHistory{

	private static final long serialVersionUID = 5675231490936611610L;
	
	private ResDoctor resDoctor;

	public ResDoctor getResDoctor() {
		return resDoctor;
	}

	public void setResDoctor(ResDoctor resDoctor) {
		this.resDoctor = resDoctor;
	}
}
