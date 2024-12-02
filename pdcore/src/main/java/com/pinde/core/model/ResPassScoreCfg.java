package com.pinde.core.model;

public class ResPassScoreCfg extends TeachingActivitySpeakerExample.MybatisObject {
    private String cfgYear;

    private String cfgPassScore;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getCfgYear() {
        return cfgYear;
    }

    public void setCfgYear(String cfgYear) {
        this.cfgYear = cfgYear;
    }

    public String getCfgPassScore() {
        return cfgPassScore;
    }

    public void setCfgPassScore(String cfgPassScore) {
        this.cfgPassScore = cfgPassScore;
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