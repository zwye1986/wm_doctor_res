package com.pinde.sci.form;


import com.pinde.sci.model.mo.JsresAttendance;
import com.pinde.sci.model.mo.SysDept;
import com.pinde.sci.model.mo.SysUser;

public class JsResAttendanceExt extends SysUser{

    private JsresAttendance jsresAttendance;

    private SysDept sysDept;

    private String doctorTypeName;

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

    public String getDoctorTypeName() {
        return doctorTypeName;
    }

    public void setDoctorTypeName(String doctorTypeName) {
        this.doctorTypeName = doctorTypeName;
    }
}
