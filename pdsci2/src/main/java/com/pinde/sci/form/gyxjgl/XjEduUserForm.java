package com.pinde.sci.form.gyxjgl;

import com.pinde.sci.model.mo.EduUser;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.SysUser;

public class XjEduUserForm {

    private XjEduUserExtInfoForm eduUserExtInfoForm;
    private SysUser sysUser;
    private EduUser eduUser;
    private ResDoctor resDoctor;

    public XjEduUserExtInfoForm getEduUserExtInfoForm() {
        return eduUserExtInfoForm;
    }

    public void setEduUserExtInfoForm(XjEduUserExtInfoForm eduUserExtInfoForm) {
        this.eduUserExtInfoForm = eduUserExtInfoForm;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public EduUser getEduUser() {
        return eduUser;
    }

    public void setEduUser(EduUser eduUser) {
        this.eduUser = eduUser;
    }

    public ResDoctor getResDoctor() {
        return resDoctor;
    }

    public void setResDoctor(ResDoctor resDoctor) {
        this.resDoctor = resDoctor;
    }

}
