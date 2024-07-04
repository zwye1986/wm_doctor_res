package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class EduCourseScoreRef extends MybatisObject {
    private String recordFlow;

    private String courseFlow;

    private String refTypeId;

    private String refTypeName;

    private String refFlow;

    private String refName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getCourseFlow() {
        return courseFlow;
    }

    public void setCourseFlow(String courseFlow) {
        this.courseFlow = courseFlow;
    }

    public String getRefTypeId() {
        return refTypeId;
    }

    public void setRefTypeId(String refTypeId) {
        this.refTypeId = refTypeId;
    }

    public String getRefTypeName() {
        return refTypeName;
    }

    public void setRefTypeName(String refTypeName) {
        this.refTypeName = refTypeName;
    }

    public String getRefFlow() {
        return refFlow;
    }

    public void setRefFlow(String refFlow) {
        this.refFlow = refFlow;
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
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