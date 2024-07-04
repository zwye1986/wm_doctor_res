package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class EdcProjOrg extends MybatisObject {
    private String recordFlow;

    private String projFlow;

    private String orgFlow;

    private String randomLock;

    private String normalValueLock;

    private String normalValueFileFlow;

    private String normalValueFileName;

    private String inputLock;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getProjFlow() {
        return projFlow;
    }

    public void setProjFlow(String projFlow) {
        this.projFlow = projFlow;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getRandomLock() {
        return randomLock;
    }

    public void setRandomLock(String randomLock) {
        this.randomLock = randomLock;
    }

    public String getNormalValueLock() {
        return normalValueLock;
    }

    public void setNormalValueLock(String normalValueLock) {
        this.normalValueLock = normalValueLock;
    }

    public String getNormalValueFileFlow() {
        return normalValueFileFlow;
    }

    public void setNormalValueFileFlow(String normalValueFileFlow) {
        this.normalValueFileFlow = normalValueFileFlow;
    }

    public String getNormalValueFileName() {
        return normalValueFileName;
    }

    public void setNormalValueFileName(String normalValueFileName) {
        this.normalValueFileName = normalValueFileName;
    }

    public String getInputLock() {
        return inputLock;
    }

    public void setInputLock(String inputLock) {
        this.inputLock = inputLock;
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