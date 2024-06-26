package com.pinde.sci.form.gzykdx;

import com.pinde.sci.form.xjgl.XjEduUserExtInfoForm;
import com.pinde.sci.model.mo.DoctorTeacherRecruit;
import com.pinde.sci.model.mo.EduUser;
import com.pinde.sci.model.mo.SysUser;

public class GzykdxStudentExportForm {

    private SysUser sysUser;
    private DoctorTeacherRecruit doctorTeacherRecruit;
    private GzykdxRecruitExtInfoForm gzykdxRecruitExtInfoForm;


    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public DoctorTeacherRecruit getDoctorTeacherRecruit() {
        return doctorTeacherRecruit;
    }

    public void setDoctorTeacherRecruit(DoctorTeacherRecruit doctorTeacherRecruit) {
        this.doctorTeacherRecruit = doctorTeacherRecruit;
    }

    public GzykdxRecruitExtInfoForm getGzykdxRecruitExtInfoForm() {
        return gzykdxRecruitExtInfoForm;
    }

    public void setGzykdxRecruitExtInfoForm(GzykdxRecruitExtInfoForm gzykdxRecruitExtInfoForm) {
        this.gzykdxRecruitExtInfoForm = gzykdxRecruitExtInfoForm;
    }
}
