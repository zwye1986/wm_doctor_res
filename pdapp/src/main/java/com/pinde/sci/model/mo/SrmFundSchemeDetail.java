package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class SrmFundSchemeDetail extends MybatisObject {
    private String itemFlow;

    private String schemeFlow;

    private String schemeName;

    private String itemId;

    private String itemName;

    private String maxLimit;

    private String remark;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String itemPid;

    public String getItemFlow() {
        return itemFlow;
    }

    public void setItemFlow(String itemFlow) {
        this.itemFlow = itemFlow;
    }

    public String getSchemeFlow() {
        return schemeFlow;
    }

    public void setSchemeFlow(String schemeFlow) {
        this.schemeFlow = schemeFlow;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(String maxLimit) {
        this.maxLimit = maxLimit;
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

    public String getItemPid() {
        return itemPid;
    }

    public void setItemPid(String itemPid) {
        this.itemPid = itemPid;
    }
}