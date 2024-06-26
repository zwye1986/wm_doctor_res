package com.pinde.sci.webservice.bean.shzs;

/**
 * Created by yex on 2018-11-17.
 */
public class DocArrangeForm {

    //token 验证码
    private String token;
    //请求处理方式 Add
    private String actionType;
    //人员信息体
    private ArrangeInfo arrangeInfo;

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

    public ArrangeInfo getArrangeInfo() {
        return arrangeInfo;
    }

    public void setArrangeInfo(ArrangeInfo arrangeInfo) {
        this.arrangeInfo = arrangeInfo;
    }
}
