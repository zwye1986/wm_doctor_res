package com.pinde.sci.model.gyxjgl;

import com.pinde.sci.model.mo.EduUser;
import com.pinde.sci.model.mo.PubUserGenericContent;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.SysUser;

public class XjEduUserInfoExt extends EduUser {
    private static final long serialVersionUID = 383549218879953465L;

    private SysUser sysUser;
    private ResDoctor resDoctor;
    private String genericContent;
    //在校状态统计人数
    private String zxStatusCount;
    //学籍注册状态统计人数
    private String xjzcStatusCount;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public ResDoctor getResDoctor() {
        return resDoctor;
    }

    public void setResDoctor(ResDoctor resDoctor) {
        this.resDoctor = resDoctor;
    }

    public String getGenericContent() {
        return genericContent;
    }

    public void setGenericContent(String genericContent) {
        this.genericContent = genericContent;
    }

    public String getZxStatusCount() {
        return zxStatusCount;
    }

    public void setZxStatusCount(String zxStatusCount) {
        this.zxStatusCount = zxStatusCount;
    }

    public String getXjzcStatusCount() {
        return xjzcStatusCount;
    }

    public void setXjzcStatusCount(String xjzcStatusCount) {
        this.xjzcStatusCount = xjzcStatusCount;
    }
}
