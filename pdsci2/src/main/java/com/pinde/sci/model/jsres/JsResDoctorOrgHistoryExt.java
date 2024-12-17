package com.pinde.sci.model.jsres;

import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorOrgHistory;

public class JsResDoctorOrgHistoryExt extends ResDoctorOrgHistory{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675231490936611610L;
	
	private ResDoctor resDoctor;

	public ResDoctor getResDoctor() {
		return resDoctor;
	}

	public void setResDoctor(ResDoctor resDoctor) {
		this.resDoctor = resDoctor;
	}
}
