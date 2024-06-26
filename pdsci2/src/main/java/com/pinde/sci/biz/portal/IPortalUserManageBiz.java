package com.pinde.sci.biz.portal;

import com.pinde.sci.model.mo.SysUser;

import java.util.List;
import java.util.Map;

public interface IPortalUserManageBiz {

    List<Map<String,Object>> userList(Map<String, Object> params);

    Map<String,Object> getUserRoles(String userFlow);

    void saveUser(SysUser user, String[] roleFlows);
}
