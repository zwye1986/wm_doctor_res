package com.pinde.sci.biz.login;

import com.pinde.sci.model.mo.SysLog;
import com.pinde.sci.model.mo.SysLoginAbility;
import com.pinde.sci.model.mo.SysUser;

import java.util.List;
import java.util.Map;

/**
 * Created by shij on 2016-03-08.
 */
public interface ILoginBiz {

    public void loadSysRole(String userFlow);

    //加载特定工作站
    public void loadSysRole(String userFlow,String workStation);

    public void addSysLog(SysLog sysLog);

    public List<SysLog> getCurrWsSysLog(String startDate,String endDate,SysLog log);

    public void loadSysRoleOsce(String userFlow);

    Map<String,Object> loadSysRole2(String userFlow);

    SysLoginAbility readAbility(String userCode);
    void updateLoginAbility(String userCode);
    void deleteLoginAbility(String userCode);

    void registRecruitUser(SysUser registerUser);
}
