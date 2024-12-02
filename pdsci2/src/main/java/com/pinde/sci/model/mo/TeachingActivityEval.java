package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class TeachingActivityEval extends TeachingActivitySpeakerExample.MybatisObject {
    private String evalFlow;

    private String resultFlow;

    private String userFlow;

    private String targetFlow;

    private String targetName;

    private Integer ordinal;

    private Integer evalScore;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String isText;

    private String evalRemarks;

    public String getEvalFlow() {
        return evalFlow;
    }

    public void setEvalFlow(String evalFlow) {
        this.evalFlow = evalFlow;
    }

    public String getResultFlow() {
        return resultFlow;
    }

    public void setResultFlow(String resultFlow) {
        this.resultFlow = resultFlow;
    }

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public String getTargetFlow() {
        return targetFlow;
    }

    public void setTargetFlow(String targetFlow) {
        this.targetFlow = targetFlow;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public Integer getEvalScore() {
        return evalScore;
    }

    public void setEvalScore(Integer evalScore) {
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

    public String getIsText() {
        return isText;
    }

    public void setIsText(String isText) {
        this.isText = isText;
    }

    public String getEvalRemarks() {
        return evalRemarks;
    }

    public void setEvalRemarks(String evalRemarks) {
        this.evalRemarks = evalRemarks;
    }
}