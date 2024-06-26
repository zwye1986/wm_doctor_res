package com.pinde.sci.form.res;

import com.pinde.sci.model.mo.ResKgCfg;

import java.io.Serializable;
import java.util.List;

public class TimeSetFrom implements Serializable{
	
	private static final long serialVersionUID = 3862460508039897162L;
	
	private List<ResKgCfg> cfgs;
	private String orgFlow ;

	public List<ResKgCfg> getCfgs() {
		return cfgs;
	}

	public void setCfgs(List<ResKgCfg> cfgs) {
		this.cfgs = cfgs;
	}

	public String getOrgFlow() {
		return orgFlow;
	}

	public void setOrgFlow(String orgFlow) {
		this.orgFlow = orgFlow;
	}
}
