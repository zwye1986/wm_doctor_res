package com.pinde.sci.biz.sczyres;

import com.pinde.sci.form.sczyres.ExtInfoForm;
import com.pinde.sci.form.sczyres.SingUpForm;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorRecruitWithBLOBs;
import com.pinde.sci.model.mo.SysDict;

import java.util.List;

public interface DoctorSingupBiz {

    /**
     * 提交基础信息
     *
     * @param form
     */
    void submitSingup(SingUpForm form);

    /**
     * 提交志愿信息
     *
     * @param recruit
     */
    void submitRecruit(ResDoctorRecruitWithBLOBs recruit,String jsonData);

    /**
     * 包装额外信息xml为form对象
     *
     * @param extInfoXml
     * @return
     */
    ExtInfoForm parseExtInfoXml(String extInfoXml);

    /**
     * 根据专业类别查询具体专业
     *
     * @param catSpeId
     * @return
     */
    List<SysDict> findSpe(String catSpeId);

    void saveRecruit(ResDoctorRecruitWithBLOBs recruit,String jsonData);

    /**
     * 修改doctor
     *
     * @param doctor
     */
    void modDoctorByDoctorFlow(ResDoctor doctor);

    /**
     * 退回审核通过
     *
     * @param userFlow
     * @return
     */
    int returnInfo(String userFlow, String recruitFlow);

}
