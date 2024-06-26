package com.pinde.sci.form.srm;

import com.pinde.sci.model.mo.PubProj;
import com.pinde.sci.model.mo.PubProjProcess;

import java.io.Serializable;

/**
 * 科研项目信息导出
 */
public class SrmExportExcel implements Serializable {
    private static final long serialVersionUID = 1L;
    private PubProj proj;
    private PubProjProcess projProcess;
    //学科名称
    private String subjectName;
   //学科代码
    private String subjectCode;

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public PubProj getProj() {
        return proj;
    }

    public void setProj(PubProj proj) {
        this.proj = proj;
    }

    public PubProjProcess getProjProcess() {
        return projProcess;
    }

    public void setProjProcess(PubProjProcess projProcess) {
        this.projProcess = projProcess;
    }
}
