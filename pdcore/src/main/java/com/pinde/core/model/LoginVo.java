package com.pinde.core.model;

import java.util.List;

/**
 * @创建人 zsq
 * @创建时间 2021/5/28
 * 描述
 */
public class LoginVo implements java.io.Serializable {
    private List<InxInfo> infos;
    private List<ResMessage> messages;
    private String loginErrorMessage;
    private String flag;
    private SysUser user;

    public List<InxInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<InxInfo> infos) {
        this.infos = infos;
    }

    public List<ResMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ResMessage> messages) {
        this.messages = messages;
    }

    public String getLoginErrorMessage() {
        return loginErrorMessage;
    }

    public void setLoginErrorMessage(String loginErrorMessage) {
        this.loginErrorMessage = loginErrorMessage;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }
}
