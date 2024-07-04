package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class ExamBookRecognizeInfo extends MybatisObject {
    private String recognizeFlow;

    private String bookFlow;

    private String recognizeUserFlow;

    private String recognizeUserName;

    private String recognizeTime;

    private String signUserFlow;

    private String signUserName;

    private String problem;

    private String solution;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getRecognizeFlow() {
        return recognizeFlow;
    }

    public void setRecognizeFlow(String recognizeFlow) {
        this.recognizeFlow = recognizeFlow;
    }

    public String getBookFlow() {
        return bookFlow;
    }

    public void setBookFlow(String bookFlow) {
        this.bookFlow = bookFlow;
    }

    public String getRecognizeUserFlow() {
        return recognizeUserFlow;
    }

    public void setRecognizeUserFlow(String recognizeUserFlow) {
        this.recognizeUserFlow = recognizeUserFlow;
    }

    public String getRecognizeUserName() {
        return recognizeUserName;
    }

    public void setRecognizeUserName(String recognizeUserName) {
        this.recognizeUserName = recognizeUserName;
    }

    public String getRecognizeTime() {
        return recognizeTime;
    }

    public void setRecognizeTime(String recognizeTime) {
        this.recognizeTime = recognizeTime;
    }

    public String getSignUserFlow() {
        return signUserFlow;
    }

    public void setSignUserFlow(String signUserFlow) {
        this.signUserFlow = signUserFlow;
    }

    public String getSignUserName() {
        return signUserName;
    }

    public void setSignUserName(String signUserName) {
        this.signUserName = signUserName;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
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