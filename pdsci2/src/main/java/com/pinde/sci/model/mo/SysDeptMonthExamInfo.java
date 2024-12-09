package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class SysDeptMonthExamInfo extends TeachingActivitySpeakerExample.MybatisObject {
    private String examFlow;

    private String planFlow;

    private String userTypeId;

    private String userTypeName;

    private String examUserFlow;

    private String examUserName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getExamFlow() {
        return examFlow;
    }

    public void setExamFlow(String examFlow) {
        this.examFlow = examFlow;
    }

    public String getPlanFlow() {
        return planFlow;
    }

    public void setPlanFlow(String planFlow) {
        this.planFlow = planFlow;
    }

    public String getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getExamUserFlow() {
        return examUserFlow;
    }

    public void setExamUserFlow(String examUserFlow) {
        this.examUserFlow = examUserFlow;
    }

    public String getExamUserName() {
        return examUserName;
    }

    public void setExamUserName(String examUserName) {
        this.examUserName = examUserName;
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