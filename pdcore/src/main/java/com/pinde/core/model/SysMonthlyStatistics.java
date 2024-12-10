package com.pinde.core.model;

public class SysMonthlyStatistics implements java.io.Serializable {
    private String statisticFlow;

    private String orgFlow;

    private Integer allNum;

    private Integer userNum;

    private Integer auditNum;

    private Integer avgNum;

    private String dateMonth;

    private String doctorTypeId;

    public String getStatisticFlow() {
        return statisticFlow;
    }

    public void setStatisticFlow(String statisticFlow) {
        this.statisticFlow = statisticFlow;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public Integer getAllNum() {
        return allNum;
    }

    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    public Integer getAuditNum() {
        return auditNum;
    }

    public void setAuditNum(Integer auditNum) {
        this.auditNum = auditNum;
    }

    public Integer getAvgNum() {
        return avgNum;
    }

    public void setAvgNum(Integer avgNum) {
        this.avgNum = avgNum;
    }

    public String getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(String dateMonth) {
        this.dateMonth = dateMonth;
    }

    public String getDoctorTypeId() {
        return doctorTypeId;
    }

    public void setDoctorTypeId(String doctorTypeId) {
        this.doctorTypeId = doctorTypeId;
    }
}