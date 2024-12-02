package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class RecruitInterviewInfo extends TeachingActivitySpeakerExample.MybatisObject {
    private String recruitFlow;

    private String orgFlow;

    private String doctorFlow;

    private String interviewStartTime;

    private String interviewEndTime;

    private String interviewAddress;

    private String interviewDemo;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getRecruitFlow() {
        return recruitFlow;
    }

    public void setRecruitFlow(String recruitFlow) {
        this.recruitFlow = recruitFlow;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getDoctorFlow() {
        return doctorFlow;
    }

    public void setDoctorFlow(String doctorFlow) {
        this.doctorFlow = doctorFlow;
    }

    public String getInterviewStartTime() {
        return interviewStartTime;
    }

    public void setInterviewStartTime(String interviewStartTime) {
        this.interviewStartTime = interviewStartTime;
    }

    public String getInterviewEndTime() {
        return interviewEndTime;
    }

    public void setInterviewEndTime(String interviewEndTime) {
        this.interviewEndTime = interviewEndTime;
    }

    public String getInterviewAddress() {
        return interviewAddress;
    }

    public void setInterviewAddress(String interviewAddress) {
        this.interviewAddress = interviewAddress;
    }

    public String getInterviewDemo() {
        return interviewDemo;
    }

    public void setInterviewDemo(String interviewDemo) {
        this.interviewDemo = interviewDemo;
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