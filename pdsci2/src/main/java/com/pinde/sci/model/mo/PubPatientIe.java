package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class PubPatientIe extends TeachingActivitySpeakerExample.MybatisObject {
    private String patientFlow;

    private String projFlow;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String ieInfo;

    public String getPatientFlow() {
        return patientFlow;
    }

    public void setPatientFlow(String patientFlow) {
        this.patientFlow = patientFlow;
    }

    public String getProjFlow() {
        return projFlow;
    }

    public void setProjFlow(String projFlow) {
        this.projFlow = projFlow;
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

    public String getIeInfo() {
        return ieInfo;
    }

    public void setIeInfo(String ieInfo) {
        this.ieInfo = ieInfo;
    }
}