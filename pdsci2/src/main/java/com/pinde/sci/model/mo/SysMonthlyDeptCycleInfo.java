package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class SysMonthlyDeptCycleInfo extends TeachingActivitySpeakerExample.MybatisObject {
    private String smdciFlow;

    private String dateMonth;

    private String orgFlow;

    private String orgName;

    private String deptFlow;

    private String deptName;

    private Integer inNum;

    private Integer outPass;

    private Integer errorOutNum;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private Integer graduateInNum;

    private Integer graduateOutPass;

    private Integer graduateErrorOutNum;

    public String getSmdciFlow() {
        return smdciFlow;
    }

    public void setSmdciFlow(String smdciFlow) {
        this.smdciFlow = smdciFlow;
    }

    public String getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(String dateMonth) {
        this.dateMonth = dateMonth;
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

    public String getDeptFlow() {
        return deptFlow;
    }

    public void setDeptFlow(String deptFlow) {
        this.deptFlow = deptFlow;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getInNum() {
        return inNum;
    }

    public void setInNum(Integer inNum) {
        this.inNum = inNum;
    }

    public Integer getOutPass() {
        return outPass;
    }

    public void setOutPass(Integer outPass) {
        this.outPass = outPass;
    }

    public Integer getErrorOutNum() {
        return errorOutNum;
    }

    public void setErrorOutNum(Integer errorOutNum) {
        this.errorOutNum = errorOutNum;
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

    public Integer getGraduateInNum() {
        return graduateInNum;
    }

    public void setGraduateInNum(Integer graduateInNum) {
        this.graduateInNum = graduateInNum;
    }

    public Integer getGraduateOutPass() {
        return graduateOutPass;
    }

    public void setGraduateOutPass(Integer graduateOutPass) {
        this.graduateOutPass = graduateOutPass;
    }

    public Integer getGraduateErrorOutNum() {
        return graduateErrorOutNum;
    }

    public void setGraduateErrorOutNum(Integer graduateErrorOutNum) {
        this.graduateErrorOutNum = graduateErrorOutNum;
    }
}