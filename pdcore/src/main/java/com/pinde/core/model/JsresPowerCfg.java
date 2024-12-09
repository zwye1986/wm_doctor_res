package com.pinde.core.model;

public class JsresPowerCfg extends TeachingActivitySpeakerExample.MybatisObject {
    private String cfgCode;

    private String cfgValue;

    private String cfgDesc;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String powerStartTime;

    private String powerEndTime;

    private String checkStatusId;

    private String checkStatusName;

    public String getCfgCode() {
        return cfgCode;
    }

    public void setCfgCode(String cfgCode) {
        this.cfgCode = cfgCode;
    }

    public String getCfgValue() {
        return cfgValue;
    }

    public void setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue;
    }

    public String getCfgDesc() {
        return cfgDesc;
    }

    public void setCfgDesc(String cfgDesc) {
        this.cfgDesc = cfgDesc;
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

    public String getPowerStartTime() {
        return powerStartTime;
    }

    public void setPowerStartTime(String powerStartTime) {
        this.powerStartTime = powerStartTime;
    }

    public String getPowerEndTime() {
        return powerEndTime;
    }

    public void setPowerEndTime(String powerEndTime) {
        this.powerEndTime = powerEndTime;
    }

    public String getCheckStatusId() {
        return checkStatusId;
    }

    public void setCheckStatusId(String checkStatusId) {
        this.checkStatusId = checkStatusId;
    }

    public String getCheckStatusName() {
        return checkStatusName;
    }

    public void setCheckStatusName(String checkStatusName) {
        this.checkStatusName = checkStatusName;
    }
}