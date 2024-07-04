package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class ErpCrmContractProduct extends MybatisObject {
    private String productFlow;

    private String contractFlow;

    private String productTypeId;

    private String productTypeName;

    private String productItem;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String installPlace;

    private String versions;

    private String regFileClientName;

    private String regFileIndate;

    public String getProductFlow() {
        return productFlow;
    }

    public void setProductFlow(String productFlow) {
        this.productFlow = productFlow;
    }

    public String getContractFlow() {
        return contractFlow;
    }

    public void setContractFlow(String contractFlow) {
        this.contractFlow = contractFlow;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductItem() {
        return productItem;
    }

    public void setProductItem(String productItem) {
        this.productItem = productItem;
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

    public String getInstallPlace() {
        return installPlace;
    }

    public void setInstallPlace(String installPlace) {
        this.installPlace = installPlace;
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }

    public String getRegFileClientName() {
        return regFileClientName;
    }

    public void setRegFileClientName(String regFileClientName) {
        this.regFileClientName = regFileClientName;
    }

    public String getRegFileIndate() {
        return regFileIndate;
    }

    public void setRegFileIndate(String regFileIndate) {
        this.regFileIndate = regFileIndate;
    }
}