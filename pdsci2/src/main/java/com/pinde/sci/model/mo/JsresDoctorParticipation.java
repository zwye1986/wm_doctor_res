package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class JsresDoctorParticipation extends MybatisObject {
    private String recordFlow;

    private String doctorFlow;

    private String doctorName;

    private String participationDate;

    private String participationRoom;

    private String participationTitle;

    private String participationRole;

    private String participationComplete;

    private String participationAuthor;

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

    public String getParticipationDate() {
        return participationDate;
    }

    public void setParticipationDate(String participationDate) {
        this.participationDate = participationDate;
    }

    public String getParticipationRoom() {
        return participationRoom;
    }

    public void setParticipationRoom(String participationRoom) {
        this.participationRoom = participationRoom;
    }

    public String getParticipationTitle() {
        return participationTitle;
    }

    public void setParticipationTitle(String participationTitle) {
        this.participationTitle = participationTitle;
    }

    public String getParticipationRole() {
        return participationRole;
    }

    public void setParticipationRole(String participationRole) {
        this.participationRole = participationRole;
    }

    public String getParticipationComplete() {
        return participationComplete;
    }

    public void setParticipationComplete(String participationComplete) {
        this.participationComplete = participationComplete;
    }

    public String getParticipationAuthor() {
        return participationAuthor;
    }

    public void setParticipationAuthor(String participationAuthor) {
        this.participationAuthor = participationAuthor;
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