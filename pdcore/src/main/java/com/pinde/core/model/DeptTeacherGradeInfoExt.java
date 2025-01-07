package com.pinde.core.model;

public class DeptTeacherGradeInfoExt extends DeptTeacherGradeInfo {

	private static final long serialVersionUID = 5283259991088005661L;

	private ResDoctorSchProcess process;
	public ResDoctorSchProcess getProcess() {
		return process;
	}
	public void setProcess(ResDoctorSchProcess process) {
		this.process = process;
	}

}
