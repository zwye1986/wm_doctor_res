package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class SrmApplyLimit extends MybatisObject {
    private String limitFlow;

    private String orgFlow;

    private String orgName;

    private String projCategroyId;

    private String projCategroyName;

    private String projTypeId;

    private String projTypeName;

    private Short limitNum;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getLimitFlow() {
        return limitFlow;
    }

    public void setLimitFlow(String limitFlow) {
        this.limitFlow = limitFlow;
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

    public String getProjCategroyId() {
        return projCategroyId;
    }

    public void setProjCategroyId(String projCategroyId) {
        this.projCategroyId = projCategroyId;
    }

    public String getProjCategroyName() {
        return projCategroyName;
    }

    public void setProjCategroyName(String projCategroyName) {
        this.projCategroyName = projCategroyName;
    }

    public String getProjTypeId() {
        return projTypeId;
    }

    public void setProjTypeId(String projTypeId) {
        this.projTypeId = projTypeId;
    }

    public String getProjTypeName() {
        return projTypeName;
    }

    public void setProjTypeName(String projTypeName) {
        this.projTypeName = projTypeName;
    }

    public Short getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Short limitNum) {
        this.limitNum = limitNum;
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