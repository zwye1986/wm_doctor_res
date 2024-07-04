package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class IrbProcess extends MybatisObject {
    private String processFlow;

    private String projFlow;

    private String irbFlow;

    private String irbStageId;

    private String irbStageName;

    private String operTime;

    private String operUserFlow;

    private String operUserName;

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

    public String getProjFlow() {
        return projFlow;
    }

    public void setProjFlow(String projFlow) {
        this.projFlow = projFlow;
    }

    public String getIrbFlow() {
        return irbFlow;
    }

    public void setIrbFlow(String irbFlow) {
        this.irbFlow = irbFlow;
    }

    public String getIrbStageId() {
        return irbStageId;
    }

    public void setIrbStageId(String irbStageId) {
        this.irbStageId = irbStageId;
    }

    public String getIrbStageName() {
        return irbStageName;
    }

    public void setIrbStageName(String irbStageName) {
        this.irbStageName = irbStageName;
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
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