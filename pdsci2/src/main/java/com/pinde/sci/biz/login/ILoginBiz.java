package com.pinde.sci.biz.login;

import com.pinde.sci.model.mo.SysLog;
import com.pinde.sci.model.mo.SysLoginAbility;
import com.pinde.core.model.SysUser;

import java.util.List;
import java.util.Map;

/**
 * Created by shij on 2016-03-08.
 */
public interface ILoginBiz {

    void loadSysRole(String userFlow);

    //加载特定工作站
    void loadSysRole(String userFlow, String workStation);

    void addSysLog(SysLog sysLog);

    List<SysLog> getCurrWsSysLog(String startDate, String endDate, SysLog log);

    void loadSysRoleOsce(String userFlow);

    Map<String,Object> loadSysRole2(String userFlow);

    SysLoginAbility readAbility(String userCode);
    void updateLoginAbility(String userCode);
    void deleteLoginAbility(String userCode);

    void registRecruitUser(SysUser registerUser);
}
