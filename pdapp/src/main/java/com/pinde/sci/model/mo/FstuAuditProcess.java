package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class FstuAuditProcess extends MybatisObject {
    private String processFlow;

    private String relTypeId;

    private String relFlow;

    private String proStatusId;

    private String proStatusName;

    private String auditContent;

    private String operUserFlow;

    private String operUserName;

    private String operOrgFlow;

    private String operOrgName;

    private String operTime;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getProcessFlow() {
        return processFlow;
    }

    public void setProcessFlow(String processFlow) {
        this.processFlow = processFlow;
    }

    public String getRelTypeId() {
        return relTypeId;
    }

    public void setRelTypeId(String relTypeId) {
        this.relTypeId = relTypeId;
    }

    public String getRelFlow() {
        return relFlow;
    }

    public void setRelFlow(String relFlow) {
        this.relFlow = relFlow;
    }

    public String getProStatusId() {
        return proStatusId;
    }

    public void setProStatusId(String proStatusId) {
        this.proStatusId = proStatusId;
    }

    public String getProStatusName() {
        return proStatusName;
    }

    public void setProStatusName(String proStatusName) {
        this.proStatusName = proStatusName;
    }

    public String getAuditContent() {
        return auditContent;
    }

    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent;
    }

    public String getOperUserFlow() {
        return operUserFlow;
    }

    public void setOperUserFlow(String operUserFlow) {
        this.operUserFlow = operUserFlow;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }

    public String getOperOrgFlow() {
        return operOrgFlow;
    }

    public void setOperOrgFlow(String operOrgFlow) {
        this.operOrgFlow = operOrgFlow;
    }

    public String getOperOrgName() {
        return operOrgName;
    }

    public void setOperOrgName(String operOrgName) {
        this.operOrgName = operOrgName;
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
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