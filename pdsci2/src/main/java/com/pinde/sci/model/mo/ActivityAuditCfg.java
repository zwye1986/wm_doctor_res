package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class ActivityAuditCfg extends TeachingActivitySpeakerExample.MybatisObject {
    private String recordFlow;

    private String addRoleFlow;

    private String addRoleName;

    private String auditRoleFlow;

    private String auditRoleName;

    private String orgFlow;

    private String orgName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getAddRoleFlow() {
        return addRoleFlow;
    }

    public void setAddRoleFlow(String addRoleFlow) {
        this.addRoleFlow = addRoleFlow;
    }

    public String getAddRoleName() {
        return addRoleName;
    }

    public void setAddRoleName(String addRoleName) {
        this.addRoleName = addRoleName;
    }

    public String getAuditRoleFlow() {
        return auditRoleFlow;
    }

    public void setAuditRoleFlow(String auditRoleFlow) {
        this.auditRoleFlow = auditRoleFlow;
    }

    public String getAuditRoleName() {
        return auditRoleName;
    }

    public void setAuditRoleName(String auditRoleName) {
        this.auditRoleName = auditRoleName;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserFlow() {
        return createUserFlow;
    }

    public void setCreateUserFlow(String createUserFlow) {
        this.createUserFlow = createUserFlow;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUserFlow() {
        return modifyUserFlow;
    }

    public void setModifyUserFlow(String modifyUserFlow) {
        this.modifyUserFlow = modifyUserFlow;
    }
}