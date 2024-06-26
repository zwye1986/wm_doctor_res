package com.pinde.sci.model.mo;

public class ResDoctorRecruitWithBLOBs extends ResDoctorRecruit {
    private String retestNotice;

    private String admitNotice;

    private String provRemark;

    private String globalNotice;

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

    public String getGlobalNotice() {
        return globalNotice;
    }

    public void setGlobalNotice(String globalNotice) {
        this.globalNotice = globalNotice;
    }
}