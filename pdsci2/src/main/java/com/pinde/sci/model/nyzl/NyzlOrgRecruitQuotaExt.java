package com.pinde.sci.model.nyzl;


import com.pinde.sci.model.mo.NyzlRecruitQuota;
import com.pinde.sci.model.mo.SysOrg;

public class NyzlOrgRecruitQuotaExt extends SysOrg {
    private NyzlRecruitQuota recruitQuota;
    private String totleQuota;
    private String year;

    public NyzlRecruitQuota getRecruitQuota() {
        return recruitQuota;
    }

    public void setRecruitQuota(NyzlRecruitQuota recruitQuota) {
        this.recruitQuota = recruitQuota;
    }

    public String getTotleQuota() {
        return totleQuota;
    }

    public void setTotleQuota(String totleQuota) {
        this.totleQuota = totleQuota;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
