package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class DoctorData extends TeachingActivitySpeakerExample.MybatisObject {
    private String userName;

    private String doctorFlow;

    private String userPhone;

    private String idNo;

    private String sessionNumber;

    private String orgFlow;

    private String speId;

    private String speName;

    private String doctorTypeId;

    private String doctorTypeName;

    private String completeNum;

    private String reqNum;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDoctorFlow() {
        return doctorFlow;
    }

    public void setDoctorFlow(String doctorFlow) {
        this.doctorFlow = doctorFlow;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
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

    public String getDoctorTypeId() {
        return doctorTypeId;
    }

    public void setDoctorTypeId(String doctorTypeId) {
        this.doctorTypeId = doctorTypeId;
    }

    public String getDoctorTypeName() {
        return doctorTypeName;
    }

    public void setDoctorTypeName(String doctorTypeName) {
        this.doctorTypeName = doctorTypeName;
    }

    public String getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(String completeNum) {
        this.completeNum = completeNum;
    }

    public String getReqNum() {
        return reqNum;
    }

    public void setReqNum(String reqNum) {
        this.reqNum = reqNum;
    }
}