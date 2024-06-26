package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class ResStandardDept extends MybatisObject {
    private String standardDeptFlow;

    private String standardDeptCode;

    private String standardDeptName;

    private String topLevelDeptCode;

    private String parentDeptCode;

    private String deptLevel;

    private String deptLevelName;

    private String deptStatus;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getModifyUserFlow() {
        return modifyUserFlow;
    }

    public void setModifyUserFlow(String modifyUserFlow) {
        this.modifyUserFlow = modifyUserFlow;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateUserFlow() {
        return createUserFlow;
    }

    public void setCreateUserFlow(String createUserFlow) {
        this.createUserFlow = createUserFlow;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getStandardDeptFlow() {
        return standardDeptFlow;
    }

    public void setStandardDeptFlow(String standardDeptFlow) {
        this.standardDeptFlow = standardDeptFlow;
    }

    public String getStandardDeptCode() {
        return standardDeptCode;
    }

    public void setStandardDeptCode(String standardDeptCode) {
        this.standardDeptCode = standardDeptCode;
    }

    public String getStandardDeptName() {
        return standardDeptName;
    }

    public void setStandardDeptName(String standardDeptName) {
        this.standardDeptName = standardDeptName;
    }

    public String getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getDeptLevelName() {
        return deptLevelName;
    }

    public void setDeptLevelName(String deptLevelName) {
        this.deptLevelName = deptLevelName;
    }

    public String getParentDeptCode() {
        return parentDeptCode;
    }

    public void setParentDeptCode(String parentDeptCode) {
        this.parentDeptCode = parentDeptCode;
    }

    public String getTopLevelDeptCode() {
        return topLevelDeptCode;
    }

    public void setTopLevelDeptCode(String topLevelDeptCode) {
        this.topLevelDeptCode = topLevelDeptCode;
    }

    public String getDeptStatus() {
        return deptStatus;
    }

    public void setDeptStatus(String deptStatus) {
        this.deptStatus = deptStatus;
    }
}
