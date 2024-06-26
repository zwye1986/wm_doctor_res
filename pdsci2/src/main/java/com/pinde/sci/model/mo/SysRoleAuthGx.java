package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class SysRoleAuthGx extends MybatisObject {
    private String recordFlow;

    private String roleFlow;

    private String orgFlow;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String schools;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getRoleFlow() {
        return roleFlow;
    }

    public void setRoleFlow(String roleFlow) {
        this.roleFlow = roleFlow;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
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

    public String getSchools() {
        return schools;
    }

    public void setSchools(String schools) {
        this.schools = schools;
    }
}