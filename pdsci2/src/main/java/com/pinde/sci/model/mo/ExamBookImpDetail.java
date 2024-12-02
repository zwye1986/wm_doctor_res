package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class ExamBookImpDetail extends TeachingActivitySpeakerExample.MybatisObject {
    private String impDetailFlow;

    private String bookImpFlow;

    private String questionTypeId;

    private String questionTypeName;

    private String memo;

    private Integer questionNum;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String bookFlow;

    private byte[] questionFile;

    public String getImpDetailFlow() {
        return impDetailFlow;
    }

    public void setImpDetailFlow(String impDetailFlow) {
        this.impDetailFlow = impDetailFlow;
    }

    public String getBookImpFlow() {
        return bookImpFlow;
    }

    public void setBookImpFlow(String bookImpFlow) {
        this.bookImpFlow = bookImpFlow;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
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

    public String getBookFlow() {
        return bookFlow;
    }

    public void setBookFlow(String bookFlow) {
        this.bookFlow = bookFlow;
    }

    public byte[] getQuestionFile() {
        return questionFile;
    }

    public void setQuestionFile(byte[] questionFile) {
        this.questionFile = questionFile;
    }
}