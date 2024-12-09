package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class ExamBookReloadRec extends TeachingActivitySpeakerExample.MybatisObject {
    private String reloadRecFlow;

    private String bookNum;

    private String bookFlow;

    private String bookMemo;

    private String questionTypeId;

    private String questionTypeName;

    private String operUserFlow;

    private String operUserName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String fileName;

    public String getReloadRecFlow() {
        return reloadRecFlow;
    }

    public void setReloadRecFlow(String reloadRecFlow) {
        this.reloadRecFlow = reloadRecFlow;
    }

    public String getBookNum() {
        return bookNum;
    }

    public void setBookNum(String bookNum) {
        this.bookNum = bookNum;
    }

    public String getBookFlow() {
        return bookFlow;
    }

    public void setBookFlow(String bookFlow) {
        this.bookFlow = bookFlow;
    }

    public String getBookMemo() {
        return bookMemo;
    }

    public void setBookMemo(String bookMemo) {
        this.bookMemo = bookMemo;
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

    public String getOperUserFlow() {
        return operUserFlow;
    }

    public void setOperUserFlow(String operUserFlow) {
        this.operUserFlow = operUserFlow;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}