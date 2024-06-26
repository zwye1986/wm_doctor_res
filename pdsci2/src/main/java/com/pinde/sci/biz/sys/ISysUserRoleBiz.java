package com.pinde.sci.biz.sys;

import com.pinde.sci.model.mo.SysRole;

import java.util.List;

public interface ISysUserRoleBiz {

    List<SysRole> search(SysRole sysRole);

    boolean save(SysRole sysRole);

    SysRole read(String roleFlow);

    boolean delete(String roleFlow, String recordStatus);

    boolean realDelete(String roleFlow);

    List<String> getPopedom(String roleFlow);

    boolean savePop(SysRole sysRole, String[] menuIds);

    void saveOrder(String[] roleFlow);

}
