package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class PubRegulation extends MybatisObject {
    private String regulationFlow;

    private String regulationName;

    private String regulationCode;

    private String regulationCategoryId;

    private String regulationCategoryName;

    private String regulationTypeId;

    private String regulationTypeName;

    private String fileFlow;

    private String deptFlow;

    private String deptName;

    private String regulationYear;

    private Integer ordinal;

    private String regulationRemark;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getRegulationFlow() {
        return regulationFlow;
    }

    public void setRegulationFlow(String regulationFlow) {
        this.regulationFlow = regulationFlow;
    }

    public String getRegulationName() {
        return regulationName;
    }

    public void setRegulationName(String regulationName) {
        this.regulationName = regulationName;
    }

    public String getRegulationCode() {
        return regulationCode;
    }

    public void setRegulationCode(String regulationCode) {
        this.regulationCode = regulationCode;
    }

    public String getRegulationCategoryId() {
        return regulationCategoryId;
    }

    public void setRegulationCategoryId(String regulationCategoryId) {
        this.regulationCategoryId = regulationCategoryId;
    }

    public String getRegulationCategoryName() {
        return regulationCategoryName;
    }

    public void setRegulationCategoryName(String regulationCategoryName) {
        this.regulationCategoryName = regulationCategoryName;
    }

    public String getRegulationTypeId() {
        return regulationTypeId;
    }

    public void setRegulationTypeId(String regulationTypeId) {
        this.regulationTypeId = regulationTypeId;
    }

    public String getRegulationTypeName() {
        return regulationTypeName;
    }

    public void setRegulationTypeName(String regulationTypeName) {
        this.regulationTypeName = regulationTypeName;
    }

    public String getFileFlow() {
        return fileFlow;
    }

    public void setFileFlow(String fileFlow) {
        this.fileFlow = fileFlow;
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

    public String getRegulationYear() {
        return regulationYear;
    }

    public void setRegulationYear(String regulationYear) {
        this.regulationYear = regulationYear;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public String getRegulationRemark() {
        return regulationRemark;
    }

    public void setRegulationRemark(String regulationRemark) {
        this.regulationRemark = regulationRemark;
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