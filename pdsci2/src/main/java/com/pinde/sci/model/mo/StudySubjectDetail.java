package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class StudySubjectDetail extends TeachingActivitySpeakerExample.MybatisObject {
    private String detailFlow;

    private String doctorFlow;

    private String subjectFlow;

    private String orderTime;

    private String auditStatusId;

    private String auditStatusName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getDetailFlow() {
        return detailFlow;
    }

    public void setDetailFlow(String detailFlow) {
        this.detailFlow = detailFlow;
    }

    public String getDoctorFlow() {
        return doctorFlow;
    }

    public void setDoctorFlow(String doctorFlow) {
        this.doctorFlow = doctorFlow;
    }

    public String getSubjectFlow() {
        return subjectFlow;
    }

    public void setSubjectFlow(String subjectFlow) {
        this.subjectFlow = subjectFlow;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getAuditStatusId() {
        return auditStatusId;
    }

    public void setAuditStatusId(String auditStatusId) {
        this.auditStatusId = auditStatusId;
    }

    public String getAuditStatusName() {
        return auditStatusName;
    }

    public void setAuditStatusName(String auditStatusName) {
        this.auditStatusName = auditStatusName;
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