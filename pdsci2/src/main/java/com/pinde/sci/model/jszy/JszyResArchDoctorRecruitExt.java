package com.pinde.sci.model.jszy;

import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.core.model.SysUser;

/**
 * Created by www.0001.Ga on 2017-05-24.
 */
public class JszyResArchDoctorRecruitExt extends ResDoctorRecruit {
    private static final long serialVersionUID = 5675231490936611610L;

    private SysUser sysUser;
    private ResDoctor resDoctor;

    public ResDoctor getResDoctor() {
        return resDoctor;
    }

    public void setResDoctor(ResDoctor resDoctor) {
        this.resDoctor = resDoctor;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
