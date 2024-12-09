package com.pinde.core.model;

public class SysRole implements java.io.Serializable {
    private String roleFlow;

    private String roleName;

    private String wsId;

    private String wsName;

    private String roleLevelId;

    private String roleLevelName;

    private String parentRoleFlow;

    private String parentRoleName;

    private String allowRegFlag;

    private String regPageId;

    private String regPageName;

    private Integer ordinal;

    private String remark;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getRoleFlow() {
        return roleFlow;
    }

    public void setRoleFlow(String roleFlow) {
        this.roleFlow = roleFlow;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getWsId() {
        return wsId;
    }

    public void setWsId(String wsId) {
        this.wsId = wsId;
    }

    public String getWsName() {
        return wsName;
    }

    public void setWsName(String wsName) {
        this.wsName = wsName;
    }

    public String getRoleLevelId() {
        return roleLevelId;
    }

    public void setRoleLevelId(String roleLevelId) {
        this.roleLevelId = roleLevelId;
    }

    public String getRoleLevelName() {
        return roleLevelName;
    }

    public void setRoleLevelName(String roleLevelName) {
        this.roleLevelName = roleLevelName;
    }

    public String getParentRoleFlow() {
        return parentRoleFlow;
    }

    public void setParentRoleFlow(String parentRoleFlow) {
        this.parentRoleFlow = parentRoleFlow;
    }

    public String getParentRoleName() {
        return parentRoleName;
    }

    public void setParentRoleName(String parentRoleName) {
        this.parentRoleName = parentRoleName;
    }

    public String getAllowRegFlag() {
        return allowRegFlag;
    }

    public void setAllowRegFlag(String allowRegFlag) {
        this.allowRegFlag = allowRegFlag;
    }

    public String getRegPageId() {
        return regPageId;
    }

    public void setRegPageId(String regPageId) {
        this.regPageId = regPageId;
    }

    public String getRegPageName() {
        return regPageName;
    }

    public void setRegPageName(String regPageName) {
        this.regPageName = regPageName;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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