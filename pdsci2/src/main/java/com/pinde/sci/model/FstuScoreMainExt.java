package com.pinde.sci.model;

import com.pinde.core.model.MybatisObject;

public class FstuScoreMainExt extends MybatisObject {


    private String firstScoreTypeName;

    private String firstProjTypeName;

    private String orgName;

    private String yearScore;

    private String firstScoreItemName;

    private String firstExecutionName;

    private String sessionNumber;

    private String userName;

    private String userDeptName;

    private String myScore;

    public String getFirstScoreTypeName() {
        return firstScoreTypeName;
    }

    public void setFirstScoreTypeName(String firstScoreTypeName) {
        this.firstScoreTypeName = firstScoreTypeName;
    }

    public String getFirstProjTypeName() {
        return firstProjTypeName;
    }

    public void setFirstProjTypeName(String firstProjTypeName) {
        this.firstProjTypeName = firstProjTypeName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getYearScore() {
        return yearScore;
    }

    public void setYearScore(String yearScore) {
        this.yearScore = yearScore;
    }

    public String getFirstScoreItemName() {
        return firstScoreItemName;
    }

    public void setFirstScoreItemName(String firstScoreItemName) {
        this.firstScoreItemName = firstScoreItemName;
    }

    public String getFirstExecutionName() {
        return firstExecutionName;
    }

    public void setFirstExecutionName(String firstExecutionName) {
        this.firstExecutionName = firstExecutionName;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDeptName() {
        return userDeptName;
    }

    public void setUserDeptName(String userDeptName) {
        this.userDeptName = userDeptName;
    }

    public String getMyScore() {
        return myScore;
    }

    public void setMyScore(String myScore) {
        this.myScore = myScore;
    }
}