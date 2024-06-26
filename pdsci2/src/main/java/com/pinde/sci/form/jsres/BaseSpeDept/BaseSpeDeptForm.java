package com.pinde.sci.form.jsres.BaseSpeDept;

import com.pinde.sci.model.mo.ResBaseSpeDept;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class BaseSpeDeptForm {

    private ResBaseSpeDept resBaseSpeDept;

    /**
     * 科室基本信息
     */
    private DeptBasicInfoForm deptBasicInfoForm;

    /**
     * 医疗设备仪器
     */
    private TrainingForm trainingForm;

    /**
     * 科室负责人信息
     */
    private DepartmentHeadForm departmentHeadForm;


    public ResBaseSpeDept getResBaseSpeDept() {
        return resBaseSpeDept;
    }

    public void setResBaseSpeDept(ResBaseSpeDept resBaseSpeDept) {
        this.resBaseSpeDept = resBaseSpeDept;
    }

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
