package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class ResBaseFundDetail extends MybatisObject {
    private String recordFlow;

    private String mainFlow;

    private String projectOfBasefundsId;

    private String projectOfBasefundsName;

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

    public String getProjectOfBasefundsId() {
        return projectOfBasefundsId;
    }

    public void setProjectOfBasefundsId(String projectOfBasefundsId) {
        this.projectOfBasefundsId = projectOfBasefundsId;
    }

    public String getProjectOfBasefundsName() {
        return projectOfBasefundsName;
    }

    public void setProjectOfBasefundsName(String projectOfBasefundsName) {
        this.projectOfBasefundsName = projectOfBasefundsName;
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