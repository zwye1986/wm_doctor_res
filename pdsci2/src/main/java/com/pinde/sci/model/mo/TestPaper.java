package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

import java.math.BigDecimal;

public class TestPaper implements java.io.Serializable {
    private String paperFlow;

    private String paperName;

    private BigDecimal totleScore;

    private BigDecimal passScore;

    private String testTime;

    private String paperTypeId;

    private String paperTypeName;

    private String paperUserFlow;

    private String paperUserCode;

    private String paperUserName;

    private String paperTime;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getPaperFlow() {
        return paperFlow;
    }

    public void setPaperFlow(String paperFlow) {
        this.paperFlow = paperFlow;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public BigDecimal getTotleScore() {
        return totleScore;
    }

    public void setTotleScore(BigDecimal totleScore) {
        this.totleScore = totleScore;
    }

    public BigDecimal getPassScore() {
        return passScore;
    }

    public void setPassScore(BigDecimal passScore) {
        this.passScore = passScore;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    public String getPaperTypeId() {
        return paperTypeId;
    }

    public void setPaperTypeId(String paperTypeId) {
        this.paperTypeId = paperTypeId;
    }

    public String getPaperTypeName() {
        return paperTypeName;
    }

    public void setPaperTypeName(String paperTypeName) {
        this.paperTypeName = paperTypeName;
    }

    public String getPaperUserFlow() {
        return paperUserFlow;
    }

    public void setPaperUserFlow(String paperUserFlow) {
        this.paperUserFlow = paperUserFlow;
    }

    public String getPaperUserCode() {
        return paperUserCode;
    }

    public void setPaperUserCode(String paperUserCode) {
        this.paperUserCode = paperUserCode;
    }

    public String getPaperUserName() {
        return paperUserName;
    }

    public void setPaperUserName(String paperUserName) {
        this.paperUserName = paperUserName;
    }

    public String getPaperTime() {
        return paperTime;
    }

    public void setPaperTime(String paperTime) {
        this.paperTime = paperTime;
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