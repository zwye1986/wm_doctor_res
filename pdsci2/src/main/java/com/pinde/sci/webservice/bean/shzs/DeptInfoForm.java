package com.pinde.sci.webservice.bean.shzs;

public class DeptInfoForm  {
    //token 验证码
    private String token;
    //请求处理方式 Add Edit Stop Enable
    private String actionType;
    //科室信息体
    private DeptInfo deptInfo;

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

    public DeptInfo getDeptInfo() {
        return deptInfo;
    }

    public void setDeptInfo(DeptInfo deptInfo) {
        this.deptInfo = deptInfo;
    }
}