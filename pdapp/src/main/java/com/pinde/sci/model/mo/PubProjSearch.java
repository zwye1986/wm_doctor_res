package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class PubProjSearch extends MybatisObject {
    private String projSearchFlow;

    private String projFlow;

    private String searchColumn;

    private String searchValue;

    private String searchRemark;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getProjSearchFlow() {
        return projSearchFlow;
    }

    public void setProjSearchFlow(String projSearchFlow) {
        this.projSearchFlow = projSearchFlow;
    }

    public String getProjFlow() {
        return projFlow;
    }

    public void setProjFlow(String projFlow) {
        this.projFlow = projFlow;
    }

    public String getSearchColumn() {
        return searchColumn;
    }

    public void setSearchColumn(String searchColumn) {
        this.searchColumn = searchColumn;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getSearchRemark() {
        return searchRemark;
    }

    public void setSearchRemark(String searchRemark) {
        this.searchRemark = searchRemark;
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