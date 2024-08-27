package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

import java.math.BigDecimal;

public class SysSmsTemplate extends MybatisObject {
    private String smsTempFlow;

    private String wsId;

    private String tempName;

    private String tempContent;

    private BigDecimal tempId;

    private String tempFormart;

    private String tempType;

    private String isEnabled;

    private String tempRemark;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getSmsTempFlow() {
        return smsTempFlow;
    }

    public void setSmsTempFlow(String smsTempFlow) {
        this.smsTempFlow = smsTempFlow;
    }

    public String getWsId() {
        return wsId;
    }

    public void setWsId(String wsId) {
        this.wsId = wsId;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public String getTempContent() {
        return tempContent;
    }

    public void setTempContent(String tempContent) {
        this.tempContent = tempContent;
    }

    public BigDecimal getTempId() {
        return tempId;
    }

    public void setTempId(BigDecimal tempId) {
        this.tempId = tempId;
    }

    public String getTempFormart() {
        return tempFormart;
    }

    public void setTempFormart(String tempFormart) {
        this.tempFormart = tempFormart;
    }

    public String getTempType() {
        return tempType;
    }

    public void setTempType(String tempType) {
        this.tempType = tempType;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getTempRemark() {
        return tempRemark;
    }

    public void setTempRemark(String tempRemark) {
        this.tempRemark = tempRemark;
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