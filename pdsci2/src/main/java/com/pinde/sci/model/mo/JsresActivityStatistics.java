package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class JsresActivityStatistics extends MybatisObject {
    private String recordFlow;

    private String orgCode;

    private String parentOrgFlow;

    private String orgFlow;

    private String orgName;

    private String monthDate;

    private String doctorTrainTotal;

    private String activityNum;

    private String doctorJointNum;

    private String avgJointNum;

    private String avgTime;

    private String doctorTypeId;

    private String createTime;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getParentOrgFlow() {
        return parentOrgFlow;
    }

    public void setParentOrgFlow(String parentOrgFlow) {
        this.parentOrgFlow = parentOrgFlow;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(String monthDate) {
        this.monthDate = monthDate;
    }

    public String getDoctorTrainTotal() {
        return doctorTrainTotal;
    }

    public void setDoctorTrainTotal(String doctorTrainTotal) {
        this.doctorTrainTotal = doctorTrainTotal;
    }

    public String getActivityNum() {
        return activityNum;
    }

    public void setActivityNum(String activityNum) {
        this.activityNum = activityNum;
    }

    public String getDoctorJointNum() {
        return doctorJointNum;
    }

    public void setDoctorJointNum(String doctorJointNum) {
        this.doctorJointNum = doctorJointNum;
    }

    public String getAvgJointNum() {
        return avgJointNum;
    }

    public void setAvgJointNum(String avgJointNum) {
        this.avgJointNum = avgJointNum;
    }

    public String getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(String avgTime) {
        this.avgTime = avgTime;
    }

    public String getDoctorTypeId() {
        return doctorTypeId;
    }

    public void setDoctorTypeId(String doctorTypeId) {
        this.doctorTypeId = doctorTypeId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}