package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class ResBookStudyRecord extends TeachingActivitySpeakerExample.MybatisObject {
    private String recordFlow;

    private String doctorFlow;

    private String doctorName;

    private String teacherFlow;

    private String teacherName;

    private String studyStartTime;

    private String studyEndTime;

    private String studyActionId;

    private String studyActionName;

    private String remark;

    private String recordYear;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String studyContent;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getDoctorFlow() {
        return doctorFlow;
    }

    public void setDoctorFlow(String doctorFlow) {
        this.doctorFlow = doctorFlow;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getTeacherFlow() {
        return teacherFlow;
    }

    public void setTeacherFlow(String teacherFlow) {
        this.teacherFlow = teacherFlow;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStudyStartTime() {
        return studyStartTime;
    }

    public void setStudyStartTime(String studyStartTime) {
        this.studyStartTime = studyStartTime;
    }

    public String getStudyEndTime() {
        return studyEndTime;
    }

    public void setStudyEndTime(String studyEndTime) {
        this.studyEndTime = studyEndTime;
    }

    public String getStudyActionId() {
        return studyActionId;
    }

    public void setStudyActionId(String studyActionId) {
        this.studyActionId = studyActionId;
    }

    public String getStudyActionName() {
        return studyActionName;
    }

    public void setStudyActionName(String studyActionName) {
        this.studyActionName = studyActionName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRecordYear() {
        return recordYear;
    }

    public void setRecordYear(String recordYear) {
        this.recordYear = recordYear;
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

    public String getStudyContent() {
        return studyContent;
    }

    public void setStudyContent(String studyContent) {
        this.studyContent = studyContent;
    }
}