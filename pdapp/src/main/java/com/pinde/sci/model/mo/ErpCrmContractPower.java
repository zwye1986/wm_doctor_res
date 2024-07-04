package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class ErpCrmContractPower extends MybatisObject {
    private String powerFlow;

    private String contractFlow;

    private String sessionNumber;

    private String powerIds;

    private String powerNames;

    private Integer peopleNum;

    private String fileFlow;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private Integer graduatePeopleNum;

    public String getPowerFlow() {
        return powerFlow;
    }

    public void setPowerFlow(String powerFlow) {
        this.powerFlow = powerFlow;
    }

    public String getContractFlow() {
        return contractFlow;
    }

    public void setContractFlow(String contractFlow) {
        this.contractFlow = contractFlow;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
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

    public String getFileFlow() {
        return fileFlow;
    }

    public void setFileFlow(String fileFlow) {
        this.fileFlow = fileFlow;
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

    public Integer getGraduatePeopleNum() {
        return graduatePeopleNum;
    }

    public void setGraduatePeopleNum(Integer graduatePeopleNum) {
        this.graduatePeopleNum = graduatePeopleNum;
    }
}