package com.pinde.sci.model.jsres;

import com.pinde.core.model.ResBase;
import com.pinde.core.model.SysOrg;

public class ResBaseExt extends ResBase{
	
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
