package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class IrbStamp extends MybatisObject {
    private String stampFlow;

    private String projFlow;

    private String projName;

    private String stampDate;

    private String stampName;

    private Integer stampNum;

    private String stampUserName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getStampFlow() {
        return stampFlow;
    }

    public void setStampFlow(String stampFlow) {
        this.stampFlow = stampFlow;
    }

    public String getProjFlow() {
        return projFlow;
    }

    public void setProjFlow(String projFlow) {
        this.projFlow = projFlow;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getStampDate() {
        return stampDate;
    }

    public void setStampDate(String stampDate) {
        this.stampDate = stampDate;
    }

    public String getStampName() {
        return stampName;
    }

    public void setStampName(String stampName) {
        this.stampName = stampName;
    }

    public Integer getStampNum() {
        return stampNum;
    }

    public void setStampNum(Integer stampNum) {
        this.stampNum = stampNum;
    }

    public String getStampUserName() {
        return stampUserName;
    }

    public void setStampUserName(String stampUserName) {
        this.stampUserName = stampUserName;
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