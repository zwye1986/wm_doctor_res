package com.pinde.core.model;

public class ExamQuestionDetail extends TeachingActivitySpeakerExample.MybatisObject {
    private String detailFlow;

    private String questionFlow;

    private String parentQuestionCode;

    private String questionCode;

    private String memo;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getDetailFlow() {
        return detailFlow;
    }

    public void setDetailFlow(String detailFlow) {
        this.detailFlow = detailFlow;
    }

    public String getQuestionFlow() {
        return questionFlow;
    }

    public void setQuestionFlow(String questionFlow) {
        this.questionFlow = questionFlow;
    }

    public String getParentQuestionCode() {
        return parentQuestionCode;
    }

    public void setParentQuestionCode(String parentQuestionCode) {
        this.parentQuestionCode = parentQuestionCode;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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