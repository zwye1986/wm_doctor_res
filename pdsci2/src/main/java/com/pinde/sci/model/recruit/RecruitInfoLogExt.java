package com.pinde.sci.model.recruit;


import com.pinde.core.model.RecruitInfo;
import com.pinde.core.model.RecruitInfoLog;
import com.pinde.core.model.SysUser;

public class RecruitInfoLogExt extends RecruitInfoLog {
    private SysUser sysUser;
    private RecruitInfo recruitInfo;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public RecruitInfo getRecruitInfo() {
        return recruitInfo;
    }

    public void setRecruitInfo(RecruitInfo recruitInfo) {
        this.recruitInfo = recruitInfo;
    }
}
