package com.pinde.sci.biz.inx;

import com.pinde.core.model.SysUser;

import java.util.List;
import java.util.Map;

public interface IGateUserManageBiz {

    List<Map<String,Object>> userList(Map<String, Object> params);

    Map<String,Object> getUserRoles(String userFlow);

    void saveUser(SysUser user, String[] roleFlows);
}
