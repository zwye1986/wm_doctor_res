package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class ErpCrmContractOtherPower extends MybatisObject {
    private String otherPowerFlow;

    private String contractFlow;

    private String powerIds;

    private String powerNames;

    private Integer peopleNum;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getOtherPowerFlow() {
        return otherPowerFlow;
    }

    public void setOtherPowerFlow(String otherPowerFlow) {
        this.otherPowerFlow = otherPowerFlow;
    }

    public String getContractFlow() {
        return contractFlow;
    }

    public void setContractFlow(String contractFlow) {
        this.contractFlow = contractFlow;
    }

    public String getPowerIds() {
        return powerIds;
    }

    public void setPowerIds(String powerIds) {
        this.powerIds = powerIds;
    }

    public String getPowerNames() {
        return powerNames;
    }

    public void setPowerNames(String powerNames) {
        this.powerNames = powerNames;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
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