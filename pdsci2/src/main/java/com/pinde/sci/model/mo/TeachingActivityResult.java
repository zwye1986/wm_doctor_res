package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

import java.math.BigDecimal;

public class TeachingActivityResult extends TeachingActivitySpeakerExample.MybatisObject {
    private String resultFlow;

    private String activityFlow;

    private String userFlow;

    private BigDecimal evalScore;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String isRegiest;

    private String isScan;

    private String regiestTime;

    private String scanTime;

    private String isEffective;

    private String isScan2;

    private String scanTime2;

    public String getResultFlow() {
        return resultFlow;
    }

    public void setResultFlow(String resultFlow) {
        this.resultFlow = resultFlow;
    }

    public String getActivityFlow() {
        return activityFlow;
    }

    public void setActivityFlow(String activityFlow) {
        this.activityFlow = activityFlow;
    }

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public BigDecimal getEvalScore() {
        return evalScore;
    }

    public void setEvalScore(BigDecimal evalScore) {
        this.evalScore = evalScore;
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

    public String getIsRegiest() {
        return isRegiest;
    }

    public void setIsRegiest(String isRegiest) {
        this.isRegiest = isRegiest;
    }

    public String getIsScan() {
        return isScan;
    }

    public void setIsScan(String isScan) {
        this.isScan = isScan;
    }

    public String getRegiestTime() {
        return regiestTime;
    }

    public void setRegiestTime(String regiestTime) {
        this.regiestTime = regiestTime;
    }

    public String getScanTime() {
        return scanTime;
    }

    public void setScanTime(String scanTime) {
        this.scanTime = scanTime;
    }

    public String getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(String isEffective) {
        this.isEffective = isEffective;
    }

    public String getIsScan2() {
        return isScan2;
    }

    public void setIsScan2(String isScan2) {
        this.isScan2 = isScan2;
    }

    public String getScanTime2() {
        return scanTime2;
    }

    public void setScanTime2(String scanTime2) {
        this.scanTime2 = scanTime2;
    }
}