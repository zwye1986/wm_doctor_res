package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class ZseySupplies extends TeachingActivitySpeakerExample.MybatisObject {
    private String suppliesFlow;

    private String suppliesName;

    private String oneNumber;

    private String repeatNumber;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getSuppliesFlow() {
        return suppliesFlow;
    }

    public void setSuppliesFlow(String suppliesFlow) {
        this.suppliesFlow = suppliesFlow;
    }

    public String getSuppliesName() {
        return suppliesName;
    }

    public void setSuppliesName(String suppliesName) {
        this.suppliesName = suppliesName;
    }

    public String getOneNumber() {
        return oneNumber;
    }

    public void setOneNumber(String oneNumber) {
        this.oneNumber = oneNumber;
    }

    public String getRepeatNumber() {
        return repeatNumber;
    }

    public void setRepeatNumber(String repeatNumber) {
        this.repeatNumber = repeatNumber;
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