package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;
import java.math.BigDecimal;

public class ResDoctorSchProcessEval extends MybatisObject {
    private String recordFlow;

    private String processFlow;

    private String doctorFlow;

    private String doctorName;

    private String startTime;

    private String endTime;

    private String evalUserFlow;

    private String evalUserName;

    private String evalMonth;

    private String evalTime;

    private String isForm;

    private BigDecimal attendance;

    private BigDecimal leave;

    private BigDecimal absenteeism;

    private String evalContent;

    private BigDecimal evalScore;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getProcessFlow() {
        return processFlow;
    }

    public void setProcessFlow(String processFlow) {
        this.processFlow = processFlow;
    }

    public String getDoctorFlow() {
        return doctorFlow;
    }

    public void setDoctorFlow(String doctorFlow) {
        this.doctorFlow = doctorFlow;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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

    public String getEvalUserFlow() {
        return evalUserFlow;
    }

    public void setEvalUserFlow(String evalUserFlow) {
        this.evalUserFlow = evalUserFlow;
    }

    public String getEvalUserName() {
        return evalUserName;
    }

    public void setEvalUserName(String evalUserName) {
        this.evalUserName = evalUserName;
    }

    public String getEvalMonth() {
        return evalMonth;
    }

    public void setEvalMonth(String evalMonth) {
        this.evalMonth = evalMonth;
    }

    public String getEvalTime() {
        return evalTime;
    }

    public void setEvalTime(String evalTime) {
        this.evalTime = evalTime;
    }

    public String getIsForm() {
        return isForm;
    }

    public void setIsForm(String isForm) {
        this.isForm = isForm;
    }

    public BigDecimal getAttendance() {
        return attendance;
    }

    public void setAttendance(BigDecimal attendance) {
        this.attendance = attendance;
    }

    public BigDecimal getLeave() {
        return leave;
    }

    public void setLeave(BigDecimal leave) {
        this.leave = leave;
    }

    public BigDecimal getAbsenteeism() {
        return absenteeism;
    }

    public void setAbsenteeism(BigDecimal absenteeism) {
        this.absenteeism = absenteeism;
    }

    public String getEvalContent() {
        return evalContent;
    }

    public void setEvalContent(String evalContent) {
        this.evalContent = evalContent;
    }

    public BigDecimal getEvalScore() {
        return evalScore;
    }

    public void setEvalScore(BigDecimal evalScore) {
        this.evalScore = evalScore;
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