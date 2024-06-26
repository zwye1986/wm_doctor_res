package com.pinde.sci.form.sch;

import java.util.List;

/**
 * Created by pdkj on 2018/6/20.
 */
public class SchArrangeForm {
    public String sessionNumber;
    public String rotationFlow;
    public String selectYear;//一年期 二年期 三年期
    public String cycleYear;//轮转类型 全年 第一年，第二年，第三年
    public String startDate;
    public String orgFlow;
    public String cycleTypeId;
    public List<SchDoctorForm> doctors;

    public String getSelectYear() {
        return selectYear;
    }

    public void setSelectYear(String selectYear) {
        this.selectYear = selectYear;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getRotationFlow() {
        return rotationFlow;
    }

    public void setRotationFlow(String rotationFlow) {
        this.rotationFlow = rotationFlow;
    }

    public String getCycleTypeId() {
        return cycleTypeId;
    }

    public void setCycleTypeId(String cycleTypeId) {
        this.cycleTypeId = cycleTypeId;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getCycleYear() {
        return cycleYear;
    }

    public void setCycleYear(String cycleYear) {
        this.cycleYear = cycleYear;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public List<SchDoctorForm> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<SchDoctorForm> doctors) {
        this.doctors = doctors;
    }
}
