package com.pinde.core.model;

/**
 * Created by www.0001.Ga on 2017-05-24.
 */
public class JsResArchDoctorRecruitExt extends ResDoctorRecruitLog {
    private static final long serialVersionUID = 5675231490936611610L;

    private SysUserLog sysUser;
    private ResDoctorLog resDoctor;

    public SysUserLog getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUserLog sysUser) {
        this.sysUser = sysUser;
    }

    public ResDoctorLog getResDoctor() {
        return resDoctor;
    }

    public void setResDoctor(ResDoctorLog resDoctor) {
        this.resDoctor = resDoctor;
    }
}
