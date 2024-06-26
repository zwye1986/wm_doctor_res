package com.pinde.sci.form.czyyjxres;


import java.io.Serializable;

public class AuditEnForm implements Serializable{

    private ExtInfoForm extInfo;
    private String resumeFlow;
    private String userFlow;
    private String reason;
    private String statusId;

    public ExtInfoForm getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(ExtInfoForm extInfo) {
        this.extInfo = extInfo;
    }

    public String getResumeFlow() {
        return resumeFlow;
    }

    public void setResumeFlow(String resumeFlow) {
        this.resumeFlow = resumeFlow;
    }

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
}
