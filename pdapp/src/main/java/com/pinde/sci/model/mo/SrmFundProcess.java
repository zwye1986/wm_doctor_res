package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class SrmFundProcess extends MybatisObject {
    private String fundProcessFlow;

    private String fundFlow;

    private String fundDetailFlow;

    private String operateUserFlow;

    private String operateUserName;

    private String operateTime;

    private String operStatusId;

    private String operStatusName;

    private String content;

    private String remark;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getFundProcessFlow() {
        return fundProcessFlow;
    }

    public void setFundProcessFlow(String fundProcessFlow) {
        this.fundProcessFlow = fundProcessFlow;
    }

    public String getFundFlow() {
        return fundFlow;
    }

    public void setFundFlow(String fundFlow) {
        this.fundFlow = fundFlow;
    }

    public String getFundDetailFlow() {
        return fundDetailFlow;
    }

    public void setFundDetailFlow(String fundDetailFlow) {
        this.fundDetailFlow = fundDetailFlow;
    }

    public String getOperateUserFlow() {
        return operateUserFlow;
    }

    public void setOperateUserFlow(String operateUserFlow) {
        this.operateUserFlow = operateUserFlow;
    }

    public String getOperateUserName() {
        return operateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperStatusId() {
        return operStatusId;
    }

    public void setOperStatusId(String operStatusId) {
        this.operStatusId = operStatusId;
    }

    public String getOperStatusName() {
        return operStatusName;
    }

    public void setOperStatusName(String operStatusName) {
        this.operStatusName = operStatusName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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