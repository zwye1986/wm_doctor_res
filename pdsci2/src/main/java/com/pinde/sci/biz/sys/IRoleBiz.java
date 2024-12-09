package com.pinde.sci.biz.sys;

import com.pinde.sci.model.jsres.ActivityCfgExt;
import com.pinde.core.model.ActivityAuditCfg;
import com.pinde.sci.model.mo.SysCfg;
import com.pinde.sci.model.mo.SysRole;
import com.pinde.sci.model.mo.SysRoleAuthGx;

import java.util.List;
import java.util.Map;

public interface IRoleBiz {

    List<SysRole> search(SysRole sysRole, List<String> levelIds);

    boolean save(SysRole sysRole);

    SysRole read(String roleFlow);

    boolean delete(String roleFlow, String recordStatus);

    boolean realDelete(String roleFlow);

    List<String> getPopedom(String roleFlow);

    boolean savePop(SysRole sysRole, String[] menuIds);

    Integer saveRoleAuth(SysRole sysRole,String orgFlow, String[] schoolNames);

    SysRoleAuthGx readRoleAuth(String roleFlow);

    void saveOrder(String[] roleFlow);

    /**
     * 查询用户的所有角色
     *
     * @param userFlow 用户流水号
     * @param wsId     工作站id
     * @return
     */
    List<SysRole> search(String userFlow, String wsId);

    List<String> getColumn(String roleFlow);

    boolean saveCol(SysRole sysRole, String[] columnIds);

    SysRole searchByRoleName(String roleName);

    SysRole selectByRoleName(String roleName);

    int addActivityCfg(Map<String,String> param);

    int delActivityCfg(String recordFlow);

    int updActivityCfg(Map<String,String> param);

    List<ActivityCfgExt> searchActvity(Map<String,String> param);
    List<ActivityAuditCfg> searchActvityNew(Map<String,String> param);


    List<SysCfg> searchRoleList();
}
