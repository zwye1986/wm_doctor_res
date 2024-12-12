package com.pinde.sci.model.jszy;

import com.pinde.core.model.SysOrg;
import com.pinde.sci.model.mo.ResBase;

public class JszyResBaseExt extends ResBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1099971312871117902L;
	private SysOrg sysOrg;

	public SysOrg getSysOrg() {
		return sysOrg;
	}

	public void setSysOrg(SysOrg sysOrg) {
		this.sysOrg = sysOrg;
	}
}
