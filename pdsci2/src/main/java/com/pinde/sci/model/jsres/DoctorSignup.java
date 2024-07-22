package com.pinde.sci.model.jsres;

import com.pinde.core.model.MybatisObject;

public class DoctorSignup extends MybatisObject {
    private String recordFlow;

    private String orgFlow;

    private String speId;

    private String speName;

    private String assignFlow;

    private String assignYear;

    private String signupStatus;

    private String doctorFlow;

    private String doctorSFZ;

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

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getSpeId() {
        return speId;
    }

    public void setSpeId(String speId) {
        this.speId = speId;
    }

    public String getSpeName() {
        return speName;
    }

    public void setSpeName(String speName) {
        this.speName = speName;
    }

    public String getAssignFlow() {
        return assignFlow;
    }

    public void setAssignFlow(String assignFlow) {
        this.assignFlow = assignFlow;
    }

    public String getAssignYear() {
        return assignYear;
    }

    public void setAssignYear(String assignYear) {
        this.assignYear = assignYear;
    }

    public String getSignupStatus() {
        return signupStatus;
    }

    public void setSignupStatus(String signupStatus) {
        this.signupStatus = signupStatus;
    }

    public String getDoctorFlow() {
        return doctorFlow;
    }

    public void setDoctorFlow(String doctorFlow) {
        this.doctorFlow = doctorFlow;
    }

    public String getDoctorSFZ() {
        return doctorSFZ;
    }

    public void setDoctorSFZ(String doctorSFZ) {
        this.doctorSFZ = doctorSFZ;
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