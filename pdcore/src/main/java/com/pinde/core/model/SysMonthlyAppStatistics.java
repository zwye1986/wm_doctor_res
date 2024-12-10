package com.pinde.core.model;

public class SysMonthlyAppStatistics implements java.io.Serializable {
    private String appFlow;

    private String orgFlow;

    private Integer allNum;

    private Integer userNum;

    private Integer perNum;

    private String dateMonth;

    private String doctorTypeId;

    public String getAppFlow() {
        return appFlow;
    }

    public void setAppFlow(String appFlow) {
        this.appFlow = appFlow;
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

    public Integer getPerNum() {
        return perNum;
    }

    public void setPerNum(Integer perNum) {
        this.perNum = perNum;
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