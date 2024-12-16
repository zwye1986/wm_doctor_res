package com.pinde.sci.model.res;


import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysUser;
import com.pinde.core.model.JsresAttendance;
import com.pinde.sci.model.mo.SchDept;

public class ResAttendanceExt extends SysUser{

    private JsresAttendance jsresAttendance;

    private SysDept sysDept;

    private SchDept schDept;

    public SchDept getSchDept() {
        return schDept;
    }

    public void setSchDept(SchDept schDept) {
        this.schDept = schDept;
    }

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
