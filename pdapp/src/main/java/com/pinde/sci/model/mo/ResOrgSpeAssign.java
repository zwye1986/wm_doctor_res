package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;
import java.math.BigDecimal;

public class ResOrgSpeAssign extends MybatisObject {
    private String recordFlow;

    private String orgFlow;

    private String speId;

    private String speName;

    private BigDecimal assignPlan;

    private BigDecimal assignReal;

    private String assignYear;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private BigDecimal assignPlanOrg;

    private String isSend;

    private String graduateSpe;

    private String education;

    private String auditStatusId;

    private String auditStatusName;

    private String startTime;

    private String endTime;

    private String speDesc;

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

    public BigDecimal getAssignPlan() {
        return assignPlan;
    }

    public void setAssignPlan(BigDecimal assignPlan) {
        this.assignPlan = assignPlan;
    }

    public BigDecimal getAssignReal() {
        return assignReal;
    }

    public void setAssignReal(BigDecimal assignReal) {
        this.assignReal = assignReal;
    }

    public String getAssignYear() {
        return assignYear;
    }

    public void setAssignYear(String assignYear) {
        this.assignYear = assignYear;
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

    public BigDecimal getAssignPlanOrg() {
        return assignPlanOrg;
    }

    public void setAssignPlanOrg(BigDecimal assignPlanOrg) {
        this.assignPlanOrg = assignPlanOrg;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

    public String getGraduateSpe() {
        return graduateSpe;
    }

    public void setGraduateSpe(String graduateSpe) {
        this.graduateSpe = graduateSpe;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSpeDesc() {
        return speDesc;
    }

    public void setSpeDesc(String speDesc) {
        this.speDesc = speDesc;
    }
}