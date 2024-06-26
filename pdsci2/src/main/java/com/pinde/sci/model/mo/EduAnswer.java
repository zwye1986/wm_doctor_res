package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class EduAnswer extends MybatisObject {
    private String answerFlow;

    private String questionFlow;

    private String answerUserFlow;

    private String answerContent;

    private String answerTime;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String ansTypeId;

    private String ansTypeName;

    private String ansDir;

    private String ansGrade;

    public String getAnswerFlow() {
        return answerFlow;
    }

    public void setAnswerFlow(String answerFlow) {
        this.answerFlow = answerFlow;
    }

    public String getQuestionFlow() {
        return questionFlow;
    }

    public void setQuestionFlow(String questionFlow) {
        this.questionFlow = questionFlow;
    }

    public String getAnswerUserFlow() {
        return answerUserFlow;
    }

    public void setAnswerUserFlow(String answerUserFlow) {
        this.answerUserFlow = answerUserFlow;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
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

    public String getAnsTypeId() {
        return ansTypeId;
    }

    public void setAnsTypeId(String ansTypeId) {
        this.ansTypeId = ansTypeId;
    }

    public String getAnsTypeName() {
        return ansTypeName;
    }

    public void setAnsTypeName(String ansTypeName) {
        this.ansTypeName = ansTypeName;
    }

    public String getAnsDir() {
        return ansDir;
    }

    public void setAnsDir(String ansDir) {
        this.ansDir = ansDir;
    }

    public String getAnsGrade() {
        return ansGrade;
    }

    public void setAnsGrade(String ansGrade) {
        this.ansGrade = ansGrade;
    }
}