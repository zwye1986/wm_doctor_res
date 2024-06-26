package com.pinde.sci.biz.dwjxres;

import com.pinde.sci.form.dwjxres.ExtInfoForm;
import com.pinde.sci.form.dwjxres.SingUpForm;
import com.pinde.sci.model.mo.StuBatch;
import com.pinde.sci.model.mo.StuUserResume;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IDocSingupBiz {

    /**
     * 包装额外信息xml为form对象
     */
    ExtInfoForm parseExtInfoXml(String extInfoXml);

    /**
     * 保存学员信息
     */
    void submitSingup(SingUpForm form);
    /**
     * 保存审核信息
     */
    void auditOption(Map<String,String> mp);
    /**
     * 变更录取状态
     */
    void changeRecruit(String resumeFlow,String statusId,String titleTypeName);

    /**
     * 分页保存网上报名信息
     */
    void saveSingupByPage(SingUpForm singUpForm);

    /**
     * 根据批次Flow查询批次
     */
    StuBatch findBatchByBatchFlow(String stuBatId);

    String getXmlFromExtInfo(ExtInfoForm extInfo);

    /**
     * 用于退回
     * @param resumeFlow
     * @param reason
     * @param userFlow
     * @return
     */
    int returnInfo(String resumeFlow, String reason, String userFlow,String titleTypeName);

    void auditNurseOption(Map<String, String> mp);

    int returnNurseInfo(String resumeFlow, String reason, String userFlow);
}
