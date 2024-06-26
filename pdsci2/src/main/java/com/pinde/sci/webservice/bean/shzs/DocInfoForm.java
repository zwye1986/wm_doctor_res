package com.pinde.sci.webservice.bean.shzs;

import java.util.List;

/**
 * Created by yex on 2018-11-17.
 */
public class DocInfoForm {

    //token 验证码
    private String token;
    //请求处理方式 Add Edit Delete
    private String actionType;
    //人员信息体
    private DocInfo userInfo;
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

    public DocInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(DocInfo userInfo) {
        this.userInfo = userInfo;
    }
}
