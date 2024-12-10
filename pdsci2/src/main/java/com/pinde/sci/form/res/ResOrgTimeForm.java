package com.pinde.sci.form.res;

import com.pinde.sci.model.mo.ResOrgTime;

import java.io.Serializable;
import java.util.List;

public class ResOrgTimeForm implements java.io.Serializable {

	private static final long serialVersionUID = -2420819931570058734L;
	private String orgFlow;
	private List<ResOrgTime> times;

	public String getOrgFlow() {
		return orgFlow;
	}

	public void setOrgFlow(String orgFlow) {
		this.orgFlow = orgFlow;
	}

	public List<ResOrgTime> getTimes() {
		return times;
	}

	public void setTimes(List<ResOrgTime> times) {
		this.times = times;
	}
}
