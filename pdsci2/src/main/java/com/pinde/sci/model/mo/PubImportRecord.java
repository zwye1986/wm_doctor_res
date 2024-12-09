package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class PubImportRecord implements java.io.Serializable {
    private String impFlow;

    private String impTypeId;

    private String impTypeName;

    private String impTime;

    private String impNum;

    private String impUserFlow;

    private String impUserName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String roleFlag;

    public String getImpFlow() {
        return impFlow;
    }

    public void setImpFlow(String impFlow) {
        this.impFlow = impFlow == null ? null : impFlow.trim();
    }

    public String getImpTypeId() {
        return impTypeId;
    }

    public void setImpTypeId(String impTypeId) {
        this.impTypeId = impTypeId == null ? null : impTypeId.trim();
    }

    public String getImpTypeName() {
        return impTypeName;
    }

    public void setImpTypeName(String impTypeName) {
        this.impTypeName = impTypeName == null ? null : impTypeName.trim();
    }

    public String getImpTime() {
        return impTime;
    }

    public void setImpTime(String impTime) {
        this.impTime = impTime == null ? null : impTime.trim();
    }

    public String getImpNum() {
        return impNum;
    }

    public void setImpNum(String impNum) {
        this.impNum = impNum == null ? null : impNum.trim();
    }

    public String getImpUserFlow() {
        return impUserFlow;
    }

    public void setImpUserFlow(String impUserFlow) {
        this.impUserFlow = impUserFlow == null ? null : impUserFlow.trim();
    }

    public String getImpUserName() {
        return impUserName;
    }

    public void setImpUserName(String impUserName) {
        this.impUserName = impUserName == null ? null : impUserName.trim();
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus == null ? null : recordStatus.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getCreateUserFlow() {
        return createUserFlow;
    }

    public void setCreateUserFlow(String createUserFlow) {
        this.createUserFlow = createUserFlow == null ? null : createUserFlow.trim();
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime == null ? null : modifyTime.trim();
    }

    public String getModifyUserFlow() {
        return modifyUserFlow;
    }

    public void setModifyUserFlow(String modifyUserFlow) {
        this.modifyUserFlow = modifyUserFlow == null ? null : modifyUserFlow.trim();
    }

    public String getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(String roleFlag) {
        this.roleFlag = roleFlag == null ? null : roleFlag.trim();
    }
}