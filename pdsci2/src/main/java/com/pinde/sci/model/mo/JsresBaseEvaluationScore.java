package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class JsresBaseEvaluationScore extends TeachingActivitySpeakerExample.MybatisObject {
    private String recordFlow;

    private String evaluationRecordFlow;

    private String itemId;

    private String itemName;

    private String planYear;

    private String ownerScore;

    private String speScore;

    private String speUserFlow;

    private String speUserName;

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

    public String getEvaluationRecordFlow() {
        return evaluationRecordFlow;
    }

    public void setEvaluationRecordFlow(String evaluationRecordFlow) {
        this.evaluationRecordFlow = evaluationRecordFlow;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPlanYear() {
        return planYear;
    }

    public void setPlanYear(String planYear) {
        this.planYear = planYear;
    }

    public String getOwnerScore() {
        return ownerScore;
    }

    public void setOwnerScore(String ownerScore) {
        this.ownerScore = ownerScore;
    }

    public String getSpeScore() {
        return speScore;
    }

    public void setSpeScore(String speScore) {
        this.speScore = speScore;
    }

    public String getSpeUserFlow() {
        return speUserFlow;
    }

    public void setSpeUserFlow(String speUserFlow) {
        this.speUserFlow = speUserFlow;
    }

    public String getSpeUserName() {
        return speUserName;
    }

    public void setSpeUserName(String speUserName) {
        this.speUserName = speUserName;
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