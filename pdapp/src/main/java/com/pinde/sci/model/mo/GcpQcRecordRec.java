package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class GcpQcRecordRec extends MybatisObject {
    private String recFlow;

    private String qcFlow;

    private String projFlow;

    private String recTypeId;

    private String recTypeName;

    private String recVersion;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String recContent;

    public String getRecFlow() {
        return recFlow;
    }

    public void setRecFlow(String recFlow) {
        this.recFlow = recFlow;
    }

    public String getQcFlow() {
        return qcFlow;
    }

    public void setQcFlow(String qcFlow) {
        this.qcFlow = qcFlow;
    }

    public String getProjFlow() {
        return projFlow;
    }

    public void setProjFlow(String projFlow) {
        this.projFlow = projFlow;
    }

    public String getRecTypeId() {
        return recTypeId;
    }

    public void setRecTypeId(String recTypeId) {
        this.recTypeId = recTypeId;
    }

    public String getRecTypeName() {
        return recTypeName;
    }

    public void setRecTypeName(String recTypeName) {
        this.recTypeName = recTypeName;
    }

    public String getRecVersion() {
        return recVersion;
    }

    public void setRecVersion(String recVersion) {
        this.recVersion = recVersion;
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

    public String getRecContent() {
        return recContent;
    }

    public void setRecContent(String recContent) {
        this.recContent = recContent;
    }
}