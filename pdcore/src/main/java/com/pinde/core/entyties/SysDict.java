package com.pinde.core.entyties;

import com.pinde.core.model.MybatisObject;

public class SysDict extends MybatisObject {
    private String dictFlow;

    private String dictTypeId;

    private String dictTypeName;

    private String dictId;

    private String dictName;

    private String dictDesc;

    private Integer ordinal;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String dictIssys;

    private String orgName;

    private String orgFlow;

    private String dictNameEn;

    private String csrftoken;//字典维护需映射到javaBean而提供("令牌"为登录表单提供csrf保护设计)

    private String isSubmitId;

    private String isSubmitName;

    private String checkStatusId;

    private String checkStatusName;

    private String checkReason;

    public String getDictFlow() {
        return dictFlow;
    }

    public void setDictFlow(String dictFlow) {
        this.dictFlow = dictFlow;
    }

    public String getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(String dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    public String getDictTypeName() {
        return dictTypeName;
    }

    public void setDictTypeName(String dictTypeName) {
        this.dictTypeName = dictTypeName;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
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

    public String getDictIssys() {
        return dictIssys;
    }

    public void setDictIssys(String dictIssys) {
        this.dictIssys = dictIssys;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getDictNameEn() {
        return dictNameEn;
    }

    public void setDictNameEn(String dictNameEn) {
        this.dictNameEn = dictNameEn;
    }

    public String getCsrftoken() {
        return csrftoken;
    }

    public void setCsrftoken(String csrftoken) {
        this.csrftoken = csrftoken;
    }

    public String getIsSubmitId() {
        return isSubmitId;
    }

    public void setIsSubmitId(String isSubmitId) {
        this.isSubmitId = isSubmitId;
    }

    public String getIsSubmitName() {
        return isSubmitName;
    }

    public void setIsSubmitName(String isSubmitName) {
        this.isSubmitName = isSubmitName;
    }

    public String getCheckStatusId() {
        return checkStatusId;
    }

    public void setCheckStatusId(String checkStatusId) {
        this.checkStatusId = checkStatusId;
    }

    public String getCheckStatusName() {
        return checkStatusName;
    }

    public void setCheckStatusName(String checkStatusName) {
        this.checkStatusName = checkStatusName;
    }

    public String getCheckReason() {
        return checkReason;
    }

    public void setCheckReason(String checkReason) {
        this.checkReason = checkReason;
    }
}