package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class OscaSubjectStationScore extends TeachingActivitySpeakerExample.MybatisObject {
    private String recordFlow;

    private String subjectFlow;

    private String stationFlow;

    private Integer stationScore;

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

    public String getSubjectFlow() {
        return subjectFlow;
    }

    public void setSubjectFlow(String subjectFlow) {
        this.subjectFlow = subjectFlow;
    }

    public String getStationFlow() {
        return stationFlow;
    }

    public void setStationFlow(String stationFlow) {
        this.stationFlow = stationFlow;
    }

    public Integer getStationScore() {
        return stationScore;
    }

    public void setStationScore(Integer stationScore) {
        this.stationScore = stationScore;
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