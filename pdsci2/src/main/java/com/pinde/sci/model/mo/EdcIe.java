package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class EdcIe extends MybatisObject {
    private String ieFlow;

    private String projFlow;

    private String ieName;

    private String ieVarName;

    private String typeId;

    private String typeName;

    private String inputTypeId;

    private String inputTypeName;

    private String passedValue;

    private String maxValue;

    private String minValue;

    private Integer ordinal;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getIeFlow() {
        return ieFlow;
    }

    public void setIeFlow(String ieFlow) {
        this.ieFlow = ieFlow;
    }

    public String getProjFlow() {
        return projFlow;
    }

    public void setProjFlow(String projFlow) {
        this.projFlow = projFlow;
    }

    public String getIeName() {
        return ieName;
    }

    public void setIeName(String ieName) {
        this.ieName = ieName;
    }

    public String getIeVarName() {
        return ieVarName;
    }

    public void setIeVarName(String ieVarName) {
        this.ieVarName = ieVarName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getInputTypeId() {
        return inputTypeId;
    }

    public void setInputTypeId(String inputTypeId) {
        this.inputTypeId = inputTypeId;
    }

    public String getInputTypeName() {
        return inputTypeName;
    }

    public void setInputTypeName(String inputTypeName) {
        this.inputTypeName = inputTypeName;
    }

    public String getPassedValue() {
        return passedValue;
    }

    public void setPassedValue(String passedValue) {
        this.passedValue = passedValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
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