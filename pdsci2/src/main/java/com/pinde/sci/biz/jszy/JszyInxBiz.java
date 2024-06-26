package com.pinde.sci.biz.jszy;

import com.pinde.sci.model.mo.SysUser;

public interface JszyInxBiz {
    SysUser login(String userCode, String passwd);

    void regist(SysUser user);

    void activateUser(String userFlow);

    void registSczy(SysUser user);

    void sendResetPassEmail(String userEmail, String userFlow);
}
