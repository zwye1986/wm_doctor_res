package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

import java.math.BigDecimal;

public class ResExamDoctor extends MybatisObject {
    private String recordFlow;

    private String examFlow;

    private String doctorFlow;

    private String doctorName;

    private String siteCode;

    private String siteFlow;

    private String ticketNum;

    private String ticketPrintFlag;

    private BigDecimal examResult;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String roomFlow;

    private String roomCode;

    private String siteName;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getExamFlow() {
        return examFlow;
    }

    public void setExamFlow(String examFlow) {
        this.examFlow = examFlow;
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

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getSiteFlow() {
        return siteFlow;
    }

    public void setSiteFlow(String siteFlow) {
        this.siteFlow = siteFlow;
    }

    public String getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getTicketPrintFlag() {
        return ticketPrintFlag;
    }

    public void setTicketPrintFlag(String ticketPrintFlag) {
        this.ticketPrintFlag = ticketPrintFlag;
    }

    public BigDecimal getExamResult() {
        return examResult;
    }

    public void setExamResult(BigDecimal examResult) {
        this.examResult = examResult;
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

    public String getRoomFlow() {
        return roomFlow;
    }

    public void setRoomFlow(String roomFlow) {
        this.roomFlow = roomFlow;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}