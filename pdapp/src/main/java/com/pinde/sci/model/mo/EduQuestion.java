package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class EduQuestion extends MybatisObject {
    private String questionFlow;

    private String userFlow;

    private String courseFlow;

    private String chapterFlow;

    private String questionStatusId;

    private String questionStatusName;

    private String questionContent;

    private String questionTime;

    private String submitTeacher;

    private String quintessence;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String quesTypeId;

    private String quesTypeName;

    private String quesDir;

    public String getQuestionFlow() {
        return questionFlow;
    }

    public void setQuestionFlow(String questionFlow) {
        this.questionFlow = questionFlow;
    }

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public String getCourseFlow() {
        return courseFlow;
    }

    public void setCourseFlow(String courseFlow) {
        this.courseFlow = courseFlow;
    }

    public String getChapterFlow() {
        return chapterFlow;
    }

    public void setChapterFlow(String chapterFlow) {
        this.chapterFlow = chapterFlow;
    }

    public String getQuestionStatusId() {
        return questionStatusId;
    }

    public void setQuestionStatusId(String questionStatusId) {
        this.questionStatusId = questionStatusId;
    }

    public String getQuestionStatusName() {
        return questionStatusName;
    }

    public void setQuestionStatusName(String questionStatusName) {
        this.questionStatusName = questionStatusName;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(String questionTime) {
        this.questionTime = questionTime;
    }

    public String getSubmitTeacher() {
        return submitTeacher;
    }

    public void setSubmitTeacher(String submitTeacher) {
        this.submitTeacher = submitTeacher;
    }

    public String getQuintessence() {
        return quintessence;
    }

    public void setQuintessence(String quintessence) {
        this.quintessence = quintessence;
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

    public String getQuesTypeId() {
        return quesTypeId;
    }

    public void setQuesTypeId(String quesTypeId) {
        this.quesTypeId = quesTypeId;
    }

    public String getQuesTypeName() {
        return quesTypeName;
    }

    public void setQuesTypeName(String quesTypeName) {
        this.quesTypeName = quesTypeName;
    }

    public String getQuesDir() {
        return quesDir;
    }

    public void setQuesDir(String quesDir) {
        this.quesDir = quesDir;
    }
}