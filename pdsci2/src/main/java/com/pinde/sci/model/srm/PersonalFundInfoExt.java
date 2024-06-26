package com.pinde.sci.model.srm;

import com.pinde.sci.model.mo.SrmProjFundInfo;
import com.pinde.sci.model.mo.SysUser;

/**
 * Created by www.0001.Ga on 2017-08-14.
 */
public class PersonalFundInfoExt extends SrmProjFundInfo {
    private SysUser sysUser;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
