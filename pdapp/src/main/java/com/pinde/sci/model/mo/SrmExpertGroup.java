package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class SrmExpertGroup extends MybatisObject {
    private String groupFlow;

    private String groupName;

    private String groupTypeId;

    private String groupTypeName;

    private String groupNote;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String meetingDate;

    private String meetingStartTime;

    private String meetingEndTime;

    private String meetingAddress;

    private String evaluationWayId;

    private String evaluationWayName;

    public String getGroupFlow() {
        return groupFlow;
    }

    public void setGroupFlow(String groupFlow) {
        this.groupFlow = groupFlow;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupTypeId() {
        return groupTypeId;
    }

    public void setGroupTypeId(String groupTypeId) {
        this.groupTypeId = groupTypeId;
    }

    public String getGroupTypeName() {
        return groupTypeName;
    }

    public void setGroupTypeName(String groupTypeName) {
        this.groupTypeName = groupTypeName;
    }

    public String getGroupNote() {
        return groupNote;
    }

    public void setGroupNote(String groupNote) {
        this.groupNote = groupNote;
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

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingStartTime() {
        return meetingStartTime;
    }

    public void setMeetingStartTime(String meetingStartTime) {
        this.meetingStartTime = meetingStartTime;
    }

    public String getMeetingEndTime() {
        return meetingEndTime;
    }

    public void setMeetingEndTime(String meetingEndTime) {
        this.meetingEndTime = meetingEndTime;
    }

    public String getMeetingAddress() {
        return meetingAddress;
    }

    public void setMeetingAddress(String meetingAddress) {
        this.meetingAddress = meetingAddress;
    }

    public String getEvaluationWayId() {
        return evaluationWayId;
    }

    public void setEvaluationWayId(String evaluationWayId) {
        this.evaluationWayId = evaluationWayId;
    }

    public String getEvaluationWayName() {
        return evaluationWayName;
    }

    public void setEvaluationWayName(String evaluationWayName) {
        this.evaluationWayName = evaluationWayName;
    }
}