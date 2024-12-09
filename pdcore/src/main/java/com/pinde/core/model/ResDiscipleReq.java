package com.pinde.core.model;

import java.math.BigDecimal;

public class ResDiscipleReq implements java.io.Serializable {
    private String recordFlow;

    private String orgFlow;

    private String sessionNumber;

    private String trainingTypeId;

    private String trainingTypeName;

    private String discipleTypeId;

    private String discipleTypeName;

    private BigDecimal reqNumber;

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

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getTrainingTypeId() {
        return trainingTypeId;
    }

    public void setTrainingTypeId(String trainingTypeId) {
        this.trainingTypeId = trainingTypeId;
    }

    public String getTrainingTypeName() {
        return trainingTypeName;
    }

    public void setTrainingTypeName(String trainingTypeName) {
        this.trainingTypeName = trainingTypeName;
    }

    public String getDiscipleTypeId() {
        return discipleTypeId;
    }

    public void setDiscipleTypeId(String discipleTypeId) {
        this.discipleTypeId = discipleTypeId;
    }

    public String getDiscipleTypeName() {
        return discipleTypeName;
    }

    public void setDiscipleTypeName(String discipleTypeName) {
        this.discipleTypeName = discipleTypeName;
    }

    public BigDecimal getReqNumber() {
        return reqNumber;
    }

    public void setReqNumber(BigDecimal reqNumber) {
        this.reqNumber = reqNumber;
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