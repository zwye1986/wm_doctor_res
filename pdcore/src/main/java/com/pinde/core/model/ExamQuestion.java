package com.pinde.core.model;

public class ExamQuestion extends TeachingActivitySpeakerExample.MybatisObject {
    private String questionFlow;

    private String questionTypeId;

    private String questionTypeName;

    private String questionCode;

    private String createSubjectFlow;

    private String createSubjectName;

    private String createBookFlow;

    private String createBookName;

    private String memo;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getQuestionFlow() {
        return questionFlow;
    }

    public void setQuestionFlow(String questionFlow) {
        this.questionFlow = questionFlow;
    }

    public String getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(String questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getQuestionTypeName() {
        return questionTypeName;
    }

    public void setQuestionTypeName(String questionTypeName) {
        this.questionTypeName = questionTypeName;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public String getCreateSubjectFlow() {
        return createSubjectFlow;
    }

    public void setCreateSubjectFlow(String createSubjectFlow) {
        this.createSubjectFlow = createSubjectFlow;
    }

    public String getCreateSubjectName() {
        return createSubjectName;
    }

    public void setCreateSubjectName(String createSubjectName) {
        this.createSubjectName = createSubjectName;
    }

    public String getCreateBookFlow() {
        return createBookFlow;
    }

    public void setCreateBookFlow(String createBookFlow) {
        this.createBookFlow = createBookFlow;
    }

    public String getCreateBookName() {
        return createBookName;
    }

    public void setCreateBookName(String createBookName) {
        this.createBookName = createBookName;
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