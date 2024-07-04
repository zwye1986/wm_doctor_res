package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;
import java.math.BigDecimal;

public class SrmAchScore extends MybatisObject {
    private String scoreFlow;

    private String scoreName;

    private BigDecimal scoreDeptValue;

    private BigDecimal scorePersonalValue;

    private String scoreRemark;

    private String scoreStatusId;

    private String scoreStatusName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String parentScoreFlow;

    public String getScoreFlow() {
        return scoreFlow;
    }

    public void setScoreFlow(String scoreFlow) {
        this.scoreFlow = scoreFlow;
    }

    public String getScoreName() {
        return scoreName;
    }

    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
    }

    public BigDecimal getScoreDeptValue() {
        return scoreDeptValue;
    }

    public void setScoreDeptValue(BigDecimal scoreDeptValue) {
        this.scoreDeptValue = scoreDeptValue;
    }

    public BigDecimal getScorePersonalValue() {
        return scorePersonalValue;
    }

    public void setScorePersonalValue(BigDecimal scorePersonalValue) {
        this.scorePersonalValue = scorePersonalValue;
    }

    public String getScoreRemark() {
        return scoreRemark;
    }

    public void setScoreRemark(String scoreRemark) {
        this.scoreRemark = scoreRemark;
    }

    public String getScoreStatusId() {
        return scoreStatusId;
    }

    public void setScoreStatusId(String scoreStatusId) {
        this.scoreStatusId = scoreStatusId;
    }

    public String getScoreStatusName() {
        return scoreStatusName;
    }

    public void setScoreStatusName(String scoreStatusName) {
        this.scoreStatusName = scoreStatusName;
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

    public String getParentScoreFlow() {
        return parentScoreFlow;
    }

    public void setParentScoreFlow(String parentScoreFlow) {
        this.parentScoreFlow = parentScoreFlow;
    }
}