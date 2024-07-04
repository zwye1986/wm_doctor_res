package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;
import java.math.BigDecimal;

public class EduCourseSchedule extends MybatisObject {
    private String recordFlow;

    private String userFlow;

    private String courseFlow;

    private String chapterFlow;

    private String studyStatusId;

    private String studyStatusName;

    private String examResults;

    private String praiseStatus;

    private BigDecimal score;

    private String evaluate;

    private String evaluateTime;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String currentTime;

    private String chapterFinishFlag;

    private String examPassFlag;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
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

    public String getStudyStatusId() {
        return studyStatusId;
    }

    public void setStudyStatusId(String studyStatusId) {
        this.studyStatusId = studyStatusId;
    }

    public String getStudyStatusName() {
        return studyStatusName;
    }

    public void setStudyStatusName(String studyStatusName) {
        this.studyStatusName = studyStatusName;
    }

    public String getExamResults() {
        return examResults;
    }

    public void setExamResults(String examResults) {
        this.examResults = examResults;
    }

    public String getPraiseStatus() {
        return praiseStatus;
    }

    public void setPraiseStatus(String praiseStatus) {
        this.praiseStatus = praiseStatus;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(String evaluateTime) {
        this.evaluateTime = evaluateTime;
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

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getChapterFinishFlag() {
        return chapterFinishFlag;
    }

    public void setChapterFinishFlag(String chapterFinishFlag) {
        this.chapterFinishFlag = chapterFinishFlag;
    }

    public String getExamPassFlag() {
        return examPassFlag;
    }

    public void setExamPassFlag(String examPassFlag) {
        this.examPassFlag = examPassFlag;
    }
}