package com.pinde.core.model;


/**
 * 教学活动配置
 */
public class ActivityCfgExt extends ResDoctorRecruit {

    /**
     * 教学活动配置主键
     */
    private String recordFlow;

    /**
     * 教学活动发起人角色
     */
    private String submitRole;

    /**
     * 教学活动发起人角色名称
     */
    private String subRoleName;

    /**
     * 教学活动审批人角色
     */
    private String auditRole;

    /**
     * 教学活动审批人角色名称
     */
    private String auditRoleName;

    /**
     * 教学活动记录状态
     */
    private String recordStatus;

    /**
     * 教学活动创建时间
     */
    private String createTime;

    /**
     * 教学活动修改时间
     */
    private String modifyTime;

    /**
     * 基地flow
     */
    private String orgFlow;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getSubmitRole() {
        return submitRole;
    }

    public void setSubmitRole(String submitRole) {
        this.submitRole = submitRole;
    }

    public String getSubRoleName() {
        return subRoleName;
    }

    public void setSubRoleName(String subRoleName) {
        this.subRoleName = subRoleName;
    }

    public String getAuditRole() {
        return auditRole;
    }

    public void setAuditRole(String auditRole) {
        this.auditRole = auditRole;
    }

    public String getAuditRoleName() {
        return auditRoleName;
    }

    public void setAuditRoleName(String auditRoleName) {
        this.auditRoleName = auditRoleName;
    }

    @Override
    public String getRecordStatus() {
        return recordStatus;
    }

    @Override
    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getModifyTime() {
        return modifyTime;
    }

    @Override
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String getOrgFlow() {
        return orgFlow;
    }

    @Override
    public void setOrgFlow(String orgFlow) {
		this.orgFlow = orgFlow;
	}
}
