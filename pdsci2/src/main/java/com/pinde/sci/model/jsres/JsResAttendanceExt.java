package com.pinde.sci.model.jsres;


import com.pinde.sci.model.mo.*;

import java.util.List;

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
