package com.pinde.res.model.jswjw.mo;

import com.pinde.sci.model.mo.ResDoctorKq;

public class ResDoctorKqExt extends ResDoctorKq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675231490936611610L;

	private String userFlow;

	private String recordFlowNew;

	private String filearr;

	public String getUserFlow() {
		return userFlow;
	}

	public void setUserFlow(String userFlow) {
		this.userFlow = userFlow;
	}

	public String getFilearr() {
		return filearr;
	}

	public void setFilearr(String filearr) {
		this.filearr = filearr;
	}

	public String getRecordFlowNew() {
		return recordFlowNew;
	}

	public void setRecordFlowNew(String recordFlowNew) {
		this.recordFlowNew = recordFlowNew;
	}
}
