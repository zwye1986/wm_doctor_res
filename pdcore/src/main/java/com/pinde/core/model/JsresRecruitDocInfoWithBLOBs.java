package com.pinde.core.model;

public class JsresRecruitDocInfoWithBLOBs extends JsresRecruitDocInfo {
    private String retestNotice;

    private String admitNotice;

    private String provRemark;

    public String getRetestNotice() {
        return retestNotice;
    }

    public void setRetestNotice(String retestNotice) {
        this.retestNotice = retestNotice;
    }

    public String getAdmitNotice() {
        return admitNotice;
    }

    public void setAdmitNotice(String admitNotice) {
        this.admitNotice = admitNotice;
    }

    public String getProvRemark() {
        return provRemark;
    }

    public void setProvRemark(String provRemark) {
        this.provRemark = provRemark;
    }
}