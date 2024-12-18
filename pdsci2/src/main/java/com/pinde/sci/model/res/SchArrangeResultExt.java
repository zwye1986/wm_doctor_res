package com.pinde.sci.model.res;

import com.pinde.core.model.ResDoctorSchProcess;
import com.pinde.core.model.SchArrangeResult;

public class SchArrangeResultExt extends SchArrangeResult {
	
	private static final long serialVersionUID = -2772695708105111287L;
	
	private ResDoctorSchProcess process;

	public ResDoctorSchProcess getProcess() {
		return process;
	}

	public void setProcess(ResDoctorSchProcess process) {
		this.process = process;
	}
}
