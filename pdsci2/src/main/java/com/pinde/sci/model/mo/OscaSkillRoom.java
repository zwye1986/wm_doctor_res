package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class OscaSkillRoom implements java.io.Serializable {
    private String recordFlow;

    private String roomFlow;

    private String roomName;

    private String clinicalFlow;

    private String clinicalName;

    private String stationFlow;

    private String stationName;

    private Integer ordinal;

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

    public String getRoomFlow() {
        return roomFlow;
    }

    public void setRoomFlow(String roomFlow) {
        this.roomFlow = roomFlow;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getClinicalFlow() {
        return clinicalFlow;
    }

    public void setClinicalFlow(String clinicalFlow) {
        this.clinicalFlow = clinicalFlow;
    }

    public String getClinicalName() {
        return clinicalName;
    }

    public void setClinicalName(String clinicalName) {
        this.clinicalName = clinicalName;
    }

    public String getStationFlow() {
        return stationFlow;
    }

    public void setStationFlow(String stationFlow) {
        this.stationFlow = stationFlow;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
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