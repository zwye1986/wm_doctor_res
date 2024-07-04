package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class IrbRegulation extends MybatisObject {
    private String regFlow;

    private String regName;

    private String fileFlow;

    private String regTypeId;

    private String regTypeName;

    private Integer ordinal;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getRegFlow() {
        return regFlow;
    }

    public void setRegFlow(String regFlow) {
        this.regFlow = regFlow;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getFileFlow() {
        return fileFlow;
    }

    public void setFileFlow(String fileFlow) {
        this.fileFlow = fileFlow;
    }

    public String getRegTypeId() {
        return regTypeId;
    }

    public void setRegTypeId(String regTypeId) {
        this.regTypeId = regTypeId;
    }

    public String getRegTypeName() {
        return regTypeName;
    }

    public void setRegTypeName(String regTypeName) {
        this.regTypeName = regTypeName;
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