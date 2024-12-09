package com.pinde.sci.model.hbzy;


import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysUser;
import com.pinde.sci.model.mo.JsresAttendance;

public class JszyResAttendanceExt extends SysUser{

    private JsresAttendance jsresAttendance;

    private SysDept sysDept;

    public SysDept getSysDept() {
        return sysDept;
    }

    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }

    public JsresAttendance getJsresAttendance() {
        return jsresAttendance;
    }

    public void setJsresAttendance(JsresAttendance jsresAttendance) {
        this.jsresAttendance = jsresAttendance;
    }
}
