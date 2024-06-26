package com.pinde.sci.webservice.bean.shzs;

import java.util.List;

/**
 * Created by yex on 2018-11-17.
 */
public class UserInfoForm {

    //token 验证码
    private String token;
    //请求处理方式 Add Edit Stop Enable
    private String actionType;
    //人员信息体
    private UserInfo userInfo;
    //人员角色 Head Teacher Admin
    private List<String> roles;
    //人员科室信息
    private List<String> deptIds;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<String> deptIds) {
        this.deptIds = deptIds;
    }
}
