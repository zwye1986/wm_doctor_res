package com.pinde.sci.model.gyxjgl;

import com.pinde.sci.form.gyxjgl.XjPollingTableForm;
import com.pinde.sci.model.mo.EduUser;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.SysUser;

public class XjEduGraduateInfoExt extends EduUser {

    private SysUser sysUser;
    private ResDoctor resDoctor;
    private XjPollingTableForm pollingTableForm;
    private String content;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public ResDoctor getResDoctor() {
        return resDoctor;
    }

    public void setResDoctor(ResDoctor resDoctor) {
        this.resDoctor = resDoctor;
    }

    public XjPollingTableForm getPollingTableForm() {
        return pollingTableForm;
    }

    public void setPollingTableForm(XjPollingTableForm pollingTableForm) {
        this.pollingTableForm = pollingTableForm;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
