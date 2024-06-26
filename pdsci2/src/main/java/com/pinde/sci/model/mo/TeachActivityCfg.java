package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class TeachActivityCfg extends MybatisObject {
    private String recordFlow;

    private String submitRole;

    private String subRoleName;

    private String auditRole;

    private String auditRoleName;

    private String recordStatus;

    private String createTime;

    private String modifyTime;

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

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }
}