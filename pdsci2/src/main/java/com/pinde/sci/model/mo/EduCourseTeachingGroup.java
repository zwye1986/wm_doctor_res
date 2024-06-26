package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class EduCourseTeachingGroup  extends MybatisObject {
    private String recordFlow;

    private String userFlow;

    private String userName;

    private String courseFlow;

    private String courseCode;

    private String courseName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow == null ? null : recordFlow.trim();
    }

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow == null ? null : userFlow.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getCourseFlow() {
        return courseFlow;
    }

    public void setCourseFlow(String courseFlow) {
        this.courseFlow = courseFlow == null ? null : courseFlow.trim();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode == null ? null : courseCode.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus == null ? null : recordStatus.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getCreateUserFlow() {
        return createUserFlow;
    }

    public void setCreateUserFlow(String createUserFlow) {
        this.createUserFlow = createUserFlow == null ? null : createUserFlow.trim();
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime == null ? null : modifyTime.trim();
    }

    public String getModifyUserFlow() {
        return modifyUserFlow;
    }

    public void setModifyUserFlow(String modifyUserFlow) {
        this.modifyUserFlow = modifyUserFlow == null ? null : modifyUserFlow.trim();
    }
}