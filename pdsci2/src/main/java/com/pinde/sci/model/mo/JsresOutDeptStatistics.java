package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class JsresOutDeptStatistics extends MybatisObject {
    private String recordFlow;

    private String orgCode;

    private String parentOrgFlow;

    private String orgFlow;

    private String orgName;

    private String monthDate;

    private String outDeptNum;

    private String realNum;

    private String notNum;

    private String notNumScale;

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

    public String getOutDeptNum() {
        return outDeptNum;
    }

    public void setOutDeptNum(String outDeptNum) {
        this.outDeptNum = outDeptNum;
    }

    public String getRealNum() {
        return realNum;
    }

    public void setRealNum(String realNum) {
        this.realNum = realNum;
    }

    public String getNotNum() {
        return notNum;
    }

    public void setNotNum(String notNum) {
        this.notNum = notNum;
    }

    public String getNotNumScale() {
        return notNumScale;
    }

    public void setNotNumScale(String notNumScale) {
        this.notNumScale = notNumScale;
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