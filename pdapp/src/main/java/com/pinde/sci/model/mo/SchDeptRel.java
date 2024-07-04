package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class SchDeptRel extends MybatisObject {
    private String recordFlow;

    private String schDeptFlow;

    private String schDeptName;

    private String standardDeptId;

    private String standardDeptName;

    private String orgFlow;

    private String orgName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String deptFlow;

    private String deptName;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getSchDeptFlow() {
        return schDeptFlow;
    }

    public void setSchDeptFlow(String schDeptFlow) {
        this.schDeptFlow = schDeptFlow;
    }

    public String getSchDeptName() {
        return schDeptName;
    }

    public void setSchDeptName(String schDeptName) {
        this.schDeptName = schDeptName;
    }

    public String getStandardDeptId() {
        return standardDeptId;
    }

    public void setStandardDeptId(String standardDeptId) {
        this.standardDeptId = standardDeptId;
    }

    public String getStandardDeptName() {
        return standardDeptName;
    }

    public void setStandardDeptName(String standardDeptName) {
        this.standardDeptName = standardDeptName;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    public String getDeptFlow() {
        return deptFlow;
    }

    public void setDeptFlow(String deptFlow) {
        this.deptFlow = deptFlow;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}