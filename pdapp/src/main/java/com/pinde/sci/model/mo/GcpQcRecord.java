package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class GcpQcRecord extends MybatisObject {
    private String qcFlow;

    private String orgFlow;

    private String projFlow;

    private String qcTypeId;

    private String qcTypeName;

    private String qcCategoryId;

    private String qcCategoryName;

    private String qcStartDate;

    private String qcEndDate;

    private String qcOperator;

    private String qcDepartment;

    private String qcPatientCodes;

    private String qcStatusId;

    private String qcStatusName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getQcFlow() {
        return qcFlow;
    }

    public void setQcFlow(String qcFlow) {
        this.qcFlow = qcFlow;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getProjFlow() {
        return projFlow;
    }

    public void setProjFlow(String projFlow) {
        this.projFlow = projFlow;
    }

    public String getQcTypeId() {
        return qcTypeId;
    }

    public void setQcTypeId(String qcTypeId) {
        this.qcTypeId = qcTypeId;
    }

    public String getQcTypeName() {
        return qcTypeName;
    }

    public void setQcTypeName(String qcTypeName) {
        this.qcTypeName = qcTypeName;
    }

    public String getQcCategoryId() {
        return qcCategoryId;
    }

    public void setQcCategoryId(String qcCategoryId) {
        this.qcCategoryId = qcCategoryId;
    }

    public String getQcCategoryName() {
        return qcCategoryName;
    }

    public void setQcCategoryName(String qcCategoryName) {
        this.qcCategoryName = qcCategoryName;
    }

    public String getQcStartDate() {
        return qcStartDate;
    }

    public void setQcStartDate(String qcStartDate) {
        this.qcStartDate = qcStartDate;
    }

    public String getQcEndDate() {
        return qcEndDate;
    }

    public void setQcEndDate(String qcEndDate) {
        this.qcEndDate = qcEndDate;
    }

    public String getQcOperator() {
        return qcOperator;
    }

    public void setQcOperator(String qcOperator) {
        this.qcOperator = qcOperator;
    }

    public String getQcDepartment() {
        return qcDepartment;
    }

    public void setQcDepartment(String qcDepartment) {
        this.qcDepartment = qcDepartment;
    }

    public String getQcPatientCodes() {
        return qcPatientCodes;
    }

    public void setQcPatientCodes(String qcPatientCodes) {
        this.qcPatientCodes = qcPatientCodes;
    }

    public String getQcStatusId() {
        return qcStatusId;
    }

    public void setQcStatusId(String qcStatusId) {
        this.qcStatusId = qcStatusId;
    }

    public String getQcStatusName() {
        return qcStatusName;
    }

    public void setQcStatusName(String qcStatusName) {
        this.qcStatusName = qcStatusName;
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