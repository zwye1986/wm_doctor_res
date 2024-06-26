package com.pinde.sci.model.jsres;

import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.sci.model.mo.ResRec;

public class JsRecInfoExt extends ResDoctorRecruit{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7551047428688596168L;
	private ResRec resRec;
	private ResDoctor doctor;
	private ResDoctorRecruit recruit;
	public ResRec getResRec() {
		return resRec;
	}
	public void setResRec(ResRec resRec) {
		this.resRec = resRec;
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
	public void setRecruit(ResDoctorRecruit recruit) {
		this.recruit = recruit;
	}
	
	
}
