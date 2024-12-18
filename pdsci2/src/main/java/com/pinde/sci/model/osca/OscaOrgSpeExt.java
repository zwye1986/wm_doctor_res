package com.pinde.sci.model.osca;

import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysOrg;
import com.pinde.core.model.OscaOrgSpe;

public class OscaOrgSpeExt extends OscaOrgSpe{
    private SysOrg sysOrg;
    private SysDept sysDept;
    private String toSite;
    private boolean searchSite;
    private String asMajor;

    public SysOrg getSysOrg() {
        return sysOrg;
    }

    public void setSysOrg(SysOrg sysOrg) {
        this.sysOrg = sysOrg;
    }

    public SysDept getSysDept() {
        return sysDept;
    }

    public String getAsMajor() {
        return asMajor;
    }

    public void setAsMajor(String asMajor) {
        this.asMajor = asMajor;
    }

    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }

    public String getToSite() {
        return toSite;
    }

    public void setToSite(String toSite) {
        this.toSite = toSite;
    }

    public boolean isSearchSite() {
        return searchSite;
    }

    public void setSearchSite(boolean searchSite) {
        this.searchSite = searchSite;
    }
}
