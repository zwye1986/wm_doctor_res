package com.pinde.sci.model.osca;

import com.pinde.core.model.OscaDoctorAssessment;
import com.pinde.core.model.OscaSkillsAssessment;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.SysUser;


public class OscaSkillsAssessmentExt extends OscaSkillsAssessment{
    private OscaDoctorAssessment oscaDoctorAssessment;
    private String overplus;
    private SysUser sysUser;
    private ResDoctor resDoctor;
    private String searchNotFull;
    private String examStartTimeList;
    private String examEndTimeList;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public ResDoctor getResDoctor() {
        return resDoctor;
    }

    public void setResDoctor(ResDoctor resDoctor) {
        this.resDoctor = resDoctor;
    }

    public String getOverplus() {
        return overplus;
    }

    public void setOverplus(String overplus) {
        this.overplus = overplus;
    }

    public String getSearchNotFull() {
        return searchNotFull;
    }

    public void setSearchNotFull(String searchNotFull) {
        this.searchNotFull = searchNotFull;
    }

    public OscaDoctorAssessment getOscaDoctorAssessment() {
        return oscaDoctorAssessment;
    }

    public void setOscaDoctorAssessment(OscaDoctorAssessment oscaDoctorAssessment) {
        this.oscaDoctorAssessment = oscaDoctorAssessment;
    }

    public String getExamEndTimeList() {
        return examEndTimeList;
    }

    public void setExamEndTimeList(String examEndTimeList) {
        this.examEndTimeList = examEndTimeList;
    }

    public String getExamStartTimeList() {
        return examStartTimeList;
    }

    public void setExamStartTimeList(String examStartTimeList) {
        this.examStartTimeList = examStartTimeList;
    }
}
