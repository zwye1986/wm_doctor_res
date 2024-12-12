package com.pinde.sci.model.res;

import com.pinde.core.model.SysRole;
import com.pinde.core.model.SysUser;
import com.pinde.sci.model.mo.SysUserRole;

public class ResAllowanceUserExt extends SysUser {
    private static final long serialVersionUID = -438010526424360691L;

    private SysUser sysUser;

    private SysUserRole userRole;

    private SysRole sysRole;

    private String roleFlows;

    private String roleName;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public SysUserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(SysUserRole userRole) {
        this.userRole = userRole;
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public String getRoleFlows() {
        return roleFlows;
    }

    public void setRoleFlows(String roleFlows) {
        this.roleFlows = roleFlows;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
