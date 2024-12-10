package com.pinde.sci.model.res;

import com.pinde.sci.model.mo.ResDoctorSchProcess;
import com.pinde.sci.model.mo.ResRec;
import com.pinde.sci.model.mo.SchArrangeResult;
import com.pinde.core.model.SysUser;


public class SchProcessExt extends ResDoctorSchProcess {
    private SysUser sysUser;
    private ResDoctorSchProcess resDoctorSchProcess;
    private ResRec resRec;
    private SchArrangeResult schArrangeResult;

    public SchArrangeResult getSchArrangeResult() {
        return schArrangeResult;
    }

    public void setSchArrangeResult(SchArrangeResult schArrangeResult) {
        this.schArrangeResult = schArrangeResult;
    }


    public ResRec getResRec() {
        return resRec;
    }

    public void setResRec(ResRec resRec) {
        this.resRec = resRec;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public ResDoctorSchProcess getResDoctorSchProcess() {
        return resDoctorSchProcess;
    }

    public void setResDoctorSchProcess(ResDoctorSchProcess resDoctorSchProcess) {
        this.resDoctorSchProcess = resDoctorSchProcess;
    }
}
