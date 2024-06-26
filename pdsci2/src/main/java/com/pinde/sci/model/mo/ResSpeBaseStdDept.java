package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class ResSpeBaseStdDept extends MybatisObject {
    private String speBaseStdDeptFlow;

    private String speBaseId;

    private String standardDeptFlow;

    private String rotationRequireStatus;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getSpeBaseStdDeptFlow() {
        return speBaseStdDeptFlow;
    }

    public void setSpeBaseStdDeptFlow(String speBaseStdDeptFlow) {
        this.speBaseStdDeptFlow = speBaseStdDeptFlow;
    }

    public String getSpeBaseId() {
        return speBaseId;
    }

    public void setSpeBaseId(String speBaseId) {
        this.speBaseId = speBaseId;
    }

    public String getStandardDeptFlow() {
        return standardDeptFlow;
    }

    public void setStandardDeptFlow(String standardDeptFlow) {
        this.standardDeptFlow = standardDeptFlow;
    }

    public String getRotationRequireStatus() {
        return rotationRequireStatus;
    }

    public void setRotationRequireStatus(String rotationRequireStatus) {
        this.rotationRequireStatus = rotationRequireStatus;
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
