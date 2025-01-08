package com.pinde.core.model;

/**
 * Created by Administrator on 2016/11/15.
 */
public class OscaSkillRoomExt extends OscaSkillRoom {
    private String peopleCount;
    private String waitingCount;
    private String examStatusId;
    private String examStatusName;
    private String examScore;

    public String getWaitingCount() {
        return waitingCount;
    }

    public void setWaitingCount(String waitingCount) {
        this.waitingCount = waitingCount;
    }

    public String getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(String peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getExamStatusId() {
        return examStatusId;
    }

    public void setExamStatusId(String examStatusId) {
        this.examStatusId = examStatusId;
    }

    public String getExamStatusName() {
        return examStatusName;
    }

    public void setExamStatusName(String examStatusName) {
        this.examStatusName = examStatusName;
    }

    public String getExamScore() {
        return examScore;
    }

    public void setExamScore(String examScore) {
        this.examScore = examScore;
    }
}
