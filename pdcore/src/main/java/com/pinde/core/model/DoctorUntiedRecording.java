package com.pinde.core.model;

public class DoctorUntiedRecording implements java.io.Serializable {
    private String recordFlow;

    private String doctorFlow;

    private String lockDate;

    private String untiedDate;

    private String untiedDescription;

    private String untiedFile;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String lockStatus;

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

    public String getLockDate() {
        return lockDate;
    }

    public void setLockDate(String lockDate) {
        this.lockDate = lockDate;
    }

    public String getUntiedDate() {
        return untiedDate;
    }

    public void setUntiedDate(String untiedDate) {
        this.untiedDate = untiedDate;
    }

    public String getUntiedDescription() {
        return untiedDescription;
    }

    public void setUntiedDescription(String untiedDescription) {
        this.untiedDescription = untiedDescription;
    }

    public String getUntiedFile() {
        return untiedFile;
    }

    public void setUntiedFile(String untiedFile) {
        this.untiedFile = untiedFile;
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

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }
}