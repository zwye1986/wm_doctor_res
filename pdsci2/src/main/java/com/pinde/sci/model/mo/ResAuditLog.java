package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class ResAuditLog implements java.io.Serializable {
    private String recordFlow;

    private String parentRecordFlow;

    private String auditRole;

    private String auditStatusId;

    private String auditStatusName;

    private String auditActions;

    private String remarks;

    private String productTypeId;

    private String productTypeName;

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

    public String getParentRecordFlow() {
        return parentRecordFlow;
    }

    public void setParentRecordFlow(String parentRecordFlow) {
        this.parentRecordFlow = parentRecordFlow;
    }

    public String getAuditRole() {
        return auditRole;
    }

    public void setAuditRole(String auditRole) {
        this.auditRole = auditRole;
    }

    public String getAuditStatusId() {
        return auditStatusId;
    }

    public void setAuditStatusId(String auditStatusId) {
        this.auditStatusId = auditStatusId;
    }

    public String getAuditStatusName() {
        return auditStatusName;
    }

    public void setAuditStatusName(String auditStatusName) {
        this.auditStatusName = auditStatusName;
    }

    public String getAuditActions() {
        return auditActions;
    }

    public void setAuditActions(String auditActions) {
        this.auditActions = auditActions;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
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