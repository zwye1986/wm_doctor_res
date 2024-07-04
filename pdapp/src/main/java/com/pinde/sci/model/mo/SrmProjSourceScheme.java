package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class SrmProjSourceScheme extends MybatisObject {
    private String sourceFlow;

    private String schemeFlow;

    private String schemeName;

    private String projFirstSourceId;

    private String projFirstSourceName;

    private String projSecondSourceId;

    private String projSecondSourceName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getSourceFlow() {
        return sourceFlow;
    }

    public void setSourceFlow(String sourceFlow) {
        this.sourceFlow = sourceFlow;
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

    public String getProjFirstSourceId() {
        return projFirstSourceId;
    }

    public void setProjFirstSourceId(String projFirstSourceId) {
        this.projFirstSourceId = projFirstSourceId;
    }

    public String getProjFirstSourceName() {
        return projFirstSourceName;
    }

    public void setProjFirstSourceName(String projFirstSourceName) {
        this.projFirstSourceName = projFirstSourceName;
    }

    public String getProjSecondSourceId() {
        return projSecondSourceId;
    }

    public void setProjSecondSourceId(String projSecondSourceId) {
        this.projSecondSourceId = projSecondSourceId;
    }

    public String getProjSecondSourceName() {
        return projSecondSourceName;
    }

    public void setProjSecondSourceName(String projSecondSourceName) {
        this.projSecondSourceName = projSecondSourceName;
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