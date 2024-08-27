package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

import java.math.BigDecimal;

public class SysSmsLog extends MybatisObject {
    private String smsLogFlow;

    private String smsUserName;

    private String smsSendTime;

    private String smsMobile;

    private String smsContent;

    private BigDecimal smsReceiverCount;

    private String smsTempFlow;

    private BigDecimal tempId;

    private String statusCode;

    private String statusName;

    private String smsResponseMsg;

    private String relId;

    private String relType;

    private String remark;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getSmsLogFlow() {
        return smsLogFlow;
    }

    public void setSmsLogFlow(String smsLogFlow) {
        this.smsLogFlow = smsLogFlow;
    }

    public String getSmsUserName() {
        return smsUserName;
    }

    public void setSmsUserName(String smsUserName) {
        this.smsUserName = smsUserName;
    }

    public String getSmsSendTime() {
        return smsSendTime;
    }

    public void setSmsSendTime(String smsSendTime) {
        this.smsSendTime = smsSendTime;
    }

    public String getSmsMobile() {
        return smsMobile;
    }

    public void setSmsMobile(String smsMobile) {
        this.smsMobile = smsMobile;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public BigDecimal getSmsReceiverCount() {
        return smsReceiverCount;
    }

    public void setSmsReceiverCount(BigDecimal smsReceiverCount) {
        this.smsReceiverCount = smsReceiverCount;
    }

    public String getSmsTempFlow() {
        return smsTempFlow;
    }

    public void setSmsTempFlow(String smsTempFlow) {
        this.smsTempFlow = smsTempFlow;
    }

    public BigDecimal getTempId() {
        return tempId;
    }

    public void setTempId(BigDecimal tempId) {
        this.tempId = tempId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getSmsResponseMsg() {
        return smsResponseMsg;
    }

    public void setSmsResponseMsg(String smsResponseMsg) {
        this.smsResponseMsg = smsResponseMsg;
    }

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public String getRelType() {
        return relType;
    }

    public void setRelType(String relType) {
        this.relType = relType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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