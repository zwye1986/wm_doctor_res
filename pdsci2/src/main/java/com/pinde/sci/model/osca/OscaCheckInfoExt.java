package com.pinde.sci.model.osca;

import com.pinde.sci.model.mo.*;

public class OscaCheckInfoExt extends OscaDoctorAssessment{

	//用户
	private SysUser sysUser;
	//医师
	private ResDoctor doctor;
	//考点表
	private SysOrg sysOrg;
	//发布考核信息
	private OscaSkillsAssessment skillAssess;

	//考核信息下预约学员已审核通过的数（用于生成准考证4位顺序码）
	private String auditPassNum;

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public ResDoctor getDoctor() {
		return doctor;
	}

	public void setDoctor(ResDoctor doctor) {
		this.doctor = doctor;
	}

	public SysOrg getSysOrg() {
		return sysOrg;
	}

	public void setSysOrg(SysOrg sysOrg) {
		this.sysOrg = sysOrg;
	}

	public OscaSkillsAssessment getSkillAssess() {
		return skillAssess;
	}

	public void setSkillAssess(OscaSkillsAssessment skillAssess) {
		this.skillAssess = skillAssess;
	}

	public String getAuditPassNum() {
		return auditPassNum;
	}

	public void setAuditPassNum(String auditPassNum) {
		this.auditPassNum = auditPassNum;
	}
}
