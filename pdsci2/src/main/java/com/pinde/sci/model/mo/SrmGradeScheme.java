package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class SrmGradeScheme extends MybatisObject {
    private String schemeFlow;

    private String schemeName;

    private String projTypeId;

    private String projTypeName;

    private String evaluationId;

    private String evaluationName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

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

    public String getProjTypeId() {
        return projTypeId;
    }

    public void setProjTypeId(String projTypeId) {
        this.projTypeId = projTypeId;
    }

    public String getProjTypeName() {
        return projTypeName;
    }

    public void setProjTypeName(String projTypeName) {
        this.projTypeName = projTypeName;
    }

    public String getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(String evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getEvaluationName() {
        return evaluationName;
    }

    public void setEvaluationName(String evaluationName) {
        this.evaluationName = evaluationName;
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