package com.pinde.sci.form.jsres.BaseSpeDept;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class BaseSpeDeptExtForm {

    /**
     * 科室基本信息
     */
    private DeptBasicInfoForm deptBasicInfoForm;

    /**
     * 培训情况
     */
    private TrainingForm trainingForm;

    /**
     * 科室负责人信息
     */
    private DepartmentHeadForm departmentHeadForm;


    public DeptBasicInfoForm getDeptBasicInfoForm() {
        return deptBasicInfoForm;
    }

    public void setDeptBasicInfoForm(DeptBasicInfoForm deptBasicInfoForm) {
        this.deptBasicInfoForm = deptBasicInfoForm;
    }

    public TrainingForm getTrainingForm() {
        return trainingForm;
    }

    public void setTrainingForm(TrainingForm trainingForm) {
        this.trainingForm = trainingForm;
    }

    public DepartmentHeadForm getDepartmentHeadForm() {
        return departmentHeadForm;
    }

    public void setDepartmentHeadForm(DepartmentHeadForm departmentHeadForm) {
        this.departmentHeadForm = departmentHeadForm;
    }
}
