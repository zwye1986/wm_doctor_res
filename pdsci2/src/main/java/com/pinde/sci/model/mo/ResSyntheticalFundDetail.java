package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class ResSyntheticalFundDetail extends MybatisObject {
    private String recordFlow;

    private String mainFlow;

    private String projectOfSyntheticalId;

    private String projectOfSyntheticalName;

    private String amountOfMoney;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String isSend;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getMainFlow() {
        return mainFlow;
    }

    public void setMainFlow(String mainFlow) {
        this.mainFlow = mainFlow;
    }

    public String getProjectOfSyntheticalId() {
        return projectOfSyntheticalId;
    }

    public void setProjectOfSyntheticalId(String projectOfSyntheticalId) {
        this.projectOfSyntheticalId = projectOfSyntheticalId;
    }

    public String getProjectOfSyntheticalName() {
        return projectOfSyntheticalName;
    }

    public void setProjectOfSyntheticalName(String projectOfSyntheticalName) {
        this.projectOfSyntheticalName = projectOfSyntheticalName;
    }

    public String getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(String amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
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

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }
}