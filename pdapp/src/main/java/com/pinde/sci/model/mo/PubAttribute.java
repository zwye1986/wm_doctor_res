package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class PubAttribute extends MybatisObject {
    private String attrFlow;

    private String attrName;

    private String attrCode;

    private String attrVarName;

    private String moduleCode;

    private String elementCode;

    private String elementName;

    private String dataTypeId;

    private String dataTypeName;

    private String dataLength;

    private String dataDecimalLength;

    private String inputTypeId;

    private String inputTypeName;

    private Integer ordinal;

    private String isViewName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getAttrFlow() {
        return attrFlow;
    }

    public void setAttrFlow(String attrFlow) {
        this.attrFlow = attrFlow;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrCode() {
        return attrCode;
    }

    public void setAttrCode(String attrCode) {
        this.attrCode = attrCode;
    }

    public String getAttrVarName() {
        return attrVarName;
    }

    public void setAttrVarName(String attrVarName) {
        this.attrVarName = attrVarName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getElementCode() {
        return elementCode;
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(String dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public String getDataLength() {
        return dataLength;
    }

    public void setDataLength(String dataLength) {
        this.dataLength = dataLength;
    }

    public String getDataDecimalLength() {
        return dataDecimalLength;
    }

    public void setDataDecimalLength(String dataDecimalLength) {
        this.dataDecimalLength = dataDecimalLength;
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

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public String getIsViewName() {
        return isViewName;
    }

    public void setIsViewName(String isViewName) {
        this.isViewName = isViewName;
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