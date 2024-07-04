package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class ErpCrmChargeUserLog extends MybatisObject {
    private String logFlow;

    private String contractFlow;

    private String changeFlow;

    private String auditUserFlow;

    private String auditUserName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getLogFlow() {
        return logFlow;
    }

    public void setLogFlow(String logFlow) {
        this.logFlow = logFlow;
    }

    public String getContractFlow() {
        return contractFlow;
    }

    public void setContractFlow(String contractFlow) {
        this.contractFlow = contractFlow;
    }

    public String getChangeFlow() {
        return changeFlow;
    }

    public void setChangeFlow(String changeFlow) {
        this.changeFlow = changeFlow;
    }

    public String getAuditUserFlow() {
        return auditUserFlow;
    }

    public void setAuditUserFlow(String auditUserFlow) {
        this.auditUserFlow = auditUserFlow;
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
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