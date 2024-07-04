package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class EdcDcf extends MybatisObject {
    private String dcfFlow;

    private String projFlow;

    private String orgFlow;

    private String dcfNo;

    private String dcfDate;

    private String dcfUserFlow;

    private String dcfUserName;

    private Integer ordinal;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getDcfFlow() {
        return dcfFlow;
    }

    public void setDcfFlow(String dcfFlow) {
        this.dcfFlow = dcfFlow;
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

    public String getDcfNo() {
        return dcfNo;
    }

    public void setDcfNo(String dcfNo) {
        this.dcfNo = dcfNo;
    }

    public String getDcfDate() {
        return dcfDate;
    }

    public void setDcfDate(String dcfDate) {
        this.dcfDate = dcfDate;
    }

    public String getDcfUserFlow() {
        return dcfUserFlow;
    }

    public void setDcfUserFlow(String dcfUserFlow) {
        this.dcfUserFlow = dcfUserFlow;
    }

    public String getDcfUserName() {
        return dcfUserName;
    }

    public void setDcfUserName(String dcfUserName) {
        this.dcfUserName = dcfUserName;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
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