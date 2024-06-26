package com.pinde.sci.model.erp;

import com.pinde.sci.model.mo.ErpProductManageUser;
import com.pinde.sci.model.mo.SysUser;

public class ErpProductManageUserExt extends ErpProductManageUser {

    private SysUser sysUser;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
