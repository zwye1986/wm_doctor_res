package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class ErpCrmCustomerVisit extends MybatisObject {
    private String visitFlow;

    private String visitType;

    private String visitRefFlow;

    private String visitTime;

    private String visitSubject;

    private String visitContent;

    private String custUserFlow;

    private String custUserName;

    private String custUserPhone;

    private String visitUserFlow;

    private String visitUserName;

    private String remark;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getVisitFlow() {
        return visitFlow;
    }

    public void setVisitFlow(String visitFlow) {
        this.visitFlow = visitFlow;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getVisitRefFlow() {
        return visitRefFlow;
    }

    public void setVisitRefFlow(String visitRefFlow) {
        this.visitRefFlow = visitRefFlow;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitSubject() {
        return visitSubject;
    }

    public void setVisitSubject(String visitSubject) {
        this.visitSubject = visitSubject;
    }

    public String getVisitContent() {
        return visitContent;
    }

    public void setVisitContent(String visitContent) {
        this.visitContent = visitContent;
    }

    public String getCustUserFlow() {
        return custUserFlow;
    }

    public void setCustUserFlow(String custUserFlow) {
        this.custUserFlow = custUserFlow;
    }

    public String getCustUserName() {
        return custUserName;
    }

    public void setCustUserName(String custUserName) {
        this.custUserName = custUserName;
    }

    public String getCustUserPhone() {
        return custUserPhone;
    }

    public void setCustUserPhone(String custUserPhone) {
        this.custUserPhone = custUserPhone;
    }

    public String getVisitUserFlow() {
        return visitUserFlow;
    }

    public void setVisitUserFlow(String visitUserFlow) {
        this.visitUserFlow = visitUserFlow;
    }

    public String getVisitUserName() {
        return visitUserName;
    }

    public void setVisitUserName(String visitUserName) {
        this.visitUserName = visitUserName;
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