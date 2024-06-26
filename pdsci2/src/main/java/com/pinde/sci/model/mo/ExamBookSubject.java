package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class ExamBookSubject extends MybatisObject {
    private String bookSubjectFlow;

    private String bookFlow;

    private String bookName;

    private String bookMemo;

    private String subjectFlow;

    private String subjectName;

    private String subjectMemo;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getBookSubjectFlow() {
        return bookSubjectFlow;
    }

    public void setBookSubjectFlow(String bookSubjectFlow) {
        this.bookSubjectFlow = bookSubjectFlow;
    }

    public String getBookFlow() {
        return bookFlow;
    }

    public void setBookFlow(String bookFlow) {
        this.bookFlow = bookFlow;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookMemo() {
        return bookMemo;
    }

    public void setBookMemo(String bookMemo) {
        this.bookMemo = bookMemo;
    }

    public String getSubjectFlow() {
        return subjectFlow;
    }

    public void setSubjectFlow(String subjectFlow) {
        this.subjectFlow = subjectFlow;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectMemo() {
        return subjectMemo;
    }

    public void setSubjectMemo(String subjectMemo) {
        this.subjectMemo = subjectMemo;
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