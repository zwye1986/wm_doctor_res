package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

import java.math.BigDecimal;

public class ResUserSpe extends TeachingActivitySpeakerExample.MybatisObject {
    private String recordFlow;

    private String userFlow;

    private String trainingSpeId;

    private String trainingSpeName;

    private String orgFlow;

    private String orgName;

    private BigDecimal ordinal;

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

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public String getTrainingSpeId() {
        return trainingSpeId;
    }

    public void setTrainingSpeId(String trainingSpeId) {
        this.trainingSpeId = trainingSpeId;
    }

    public String getTrainingSpeName() {
        return trainingSpeName;
    }

    public void setTrainingSpeName(String trainingSpeName) {
        this.trainingSpeName = trainingSpeName;
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

    public BigDecimal getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(BigDecimal ordinal) {
        this.ordinal = ordinal;
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