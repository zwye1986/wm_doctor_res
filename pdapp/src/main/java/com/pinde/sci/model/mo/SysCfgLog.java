package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class SysCfgLog extends MybatisObject {
    private String cfgFlow;

    private String cfgCode;

    private String cfgValue;

    private String cfgOldValue;

    private String cfgDesc;

    private String wsId;

    private String wsName;

    private String saveTime;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getCfgFlow() {
        return cfgFlow;
    }

    public void setCfgFlow(String cfgFlow) {
        this.cfgFlow = cfgFlow;
    }

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

    public String getCfgOldValue() {
        return cfgOldValue;
    }

    public void setCfgOldValue(String cfgOldValue) {
        this.cfgOldValue = cfgOldValue;
    }

    public String getCfgDesc() {
        return cfgDesc;
    }

    public void setCfgDesc(String cfgDesc) {
        this.cfgDesc = cfgDesc;
    }

    public String getWsId() {
        return wsId;
    }

    public void setWsId(String wsId) {
        this.wsId = wsId;
    }

    public String getWsName() {
        return wsName;
    }

    public void setWsName(String wsName) {
        this.wsName = wsName;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
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