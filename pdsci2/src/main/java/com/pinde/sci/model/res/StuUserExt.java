package com.pinde.sci.model.res;

import com.pinde.core.model.StuAuditProcess;
import com.pinde.core.model.StuBatch;
import com.pinde.core.model.StuUserResume;
import com.pinde.core.model.SysUser;

public class StuUserExt extends StuUserResume {
	
	private SysUser sysUser;

	private StuAuditProcess auditProcess;

	private StuBatch stuBatch;

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public StuAuditProcess getAuditProcess() {
		return auditProcess;
	}

	public void setAuditProcess(StuAuditProcess auditProcess) {
		this.auditProcess = auditProcess;
	}

	public StuBatch getStuBatch() {
		return stuBatch;
	}

	public void setStuBatch(StuBatch stuBatch) {
		this.stuBatch = stuBatch;
	}
}
