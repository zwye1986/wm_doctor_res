package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

import java.math.BigDecimal;

public class OscaSkillScoreDeatil extends TeachingActivitySpeakerExample.MybatisObject {
    private String recordFlow;

    private String scoreFlow;

    private String scoreName;

    private String scoreKey;

    private String initScore;

    private BigDecimal examScore;

    private String partnerFlow;

    private String partnerName;

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

    public String getScoreKey() {
        return scoreKey;
    }

    public void setScoreKey(String scoreKey) {
        this.scoreKey = scoreKey;
    }

    public String getInitScore() {
        return initScore;
    }

    public void setInitScore(String initScore) {
        this.initScore = initScore;
    }

    public BigDecimal getExamScore() {
        return examScore;
    }

    public void setExamScore(BigDecimal examScore) {
        this.examScore = examScore;
    }

    public String getPartnerFlow() {
        return partnerFlow;
    }

    public void setPartnerFlow(String partnerFlow) {
        this.partnerFlow = partnerFlow;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
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