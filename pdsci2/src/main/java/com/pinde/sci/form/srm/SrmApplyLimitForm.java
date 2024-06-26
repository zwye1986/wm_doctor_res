package com.pinde.sci.form.srm;

import com.pinde.sci.model.mo.SrmApplyLimit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SrmApplyLimitForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5555981596914017538L;
	
	private List<SrmApplyLimit> applyLimitList = new ArrayList<SrmApplyLimit>();

	public List<SrmApplyLimit> getApplyLimitList() {
		return applyLimitList;
	}

	public void setApplyLimitList(List<SrmApplyLimit> applyLimitList) {
		this.applyLimitList = applyLimitList;
	}

}
