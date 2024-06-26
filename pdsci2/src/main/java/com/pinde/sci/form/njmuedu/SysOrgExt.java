package com.pinde.sci.form.njmuedu;

import com.pinde.sci.model.mo.SysOrg;

import java.util.List;

public class SysOrgExt extends SysOrg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8032164434943496229L;
	private List<MajorForm> majorFormList;

	public List<MajorForm> getMajorFormList() {
		return majorFormList;
	}

	public void setMajorFormList(List<MajorForm> majorFormList) {
		this.majorFormList = majorFormList;
	}
	
	
}
