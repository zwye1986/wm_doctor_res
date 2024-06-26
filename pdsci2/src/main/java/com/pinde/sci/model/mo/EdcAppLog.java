package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class EdcAppLog extends MybatisObject {
    private String logFlow;

    private String reqCode;

    private String reqCodeName;

    private String reqParam;

    private String reqUserFlow;

    private String reqUserName;

    private String reqProjFlow;

    private String reqProjName;

    private String resultId;

    private String resultName;

    private String sign;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String responContent;

    public String getLogFlow() {
        return logFlow;
    }

    public void setLogFlow(String logFlow) {
        this.logFlow = logFlow;
    }

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getReqCodeName() {
        return reqCodeName;
    }

    public void setReqCodeName(String reqCodeName) {
        this.reqCodeName = reqCodeName;
    }

    public String getReqParam() {
        return reqParam;
    }

    public void setReqParam(String reqParam) {
        this.reqParam = reqParam;
    }

    public String getReqUserFlow() {
        return reqUserFlow;
    }

    public void setReqUserFlow(String reqUserFlow) {
        this.reqUserFlow = reqUserFlow;
    }

    public String getReqUserName() {
        return reqUserName;
    }

    public void setReqUserName(String reqUserName) {
        this.reqUserName = reqUserName;
    }

    public String getReqProjFlow() {
        return reqProjFlow;
    }

    public void setReqProjFlow(String reqProjFlow) {
        this.reqProjFlow = reqProjFlow;
    }

    public String getReqProjName() {
        return reqProjName;
    }

    public void setReqProjName(String reqProjName) {
        this.reqProjName = reqProjName;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

    public String getResponContent() {
        return responContent;
    }

    public void setResponContent(String responContent) {
        this.responContent = responContent;
    }
}