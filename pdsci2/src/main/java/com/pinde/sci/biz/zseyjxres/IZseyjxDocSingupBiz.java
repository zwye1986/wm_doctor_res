package com.pinde.sci.biz.zseyjxres;

import com.pinde.sci.form.zseyjxres.SpeForm;
import com.pinde.sci.model.mo.StuBatch;
import com.pinde.sci.model.mo.StuHeadAuditStatus;
import com.pinde.sci.model.mo.StuUserResume;
import com.pinde.sci.form.zseyjxres.ExtInfoForm;
import com.pinde.sci.form.zseyjxres.SingUpForm;

import java.util.List;
import java.util.Map;

public interface IZseyjxDocSingupBiz {

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
    void auditOption(Map<String, String> mp);

    int headAudit(StuHeadAuditStatus headAuditStatus);

    int updateheadAudit(String batchId,String resumeFlow,String userFlow,String deptFlow,String statusId);

    int updateHeadAndResume(String batchId,String resumeFlow,String processFlow, String userFlow, String reason, String statusId);

    /**
     * 保存科室审核状态
     */
    int saveHeadstatus(Map<String, String> mp,List<SpeForm> speFormList,List<StuHeadAuditStatus> statuses);

    int deleteHeadAudit(String resumeFlow);

    List<StuHeadAuditStatus> getHeadStatus(String batchId,String resumeFlow,String deptName,String statusId,String deptFlow,String userFlow);
    /**
     * 变更录取状态
     */
    void changeRecruit(String resumeFlow, String statusId);

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
    int returnInfo(String resumeFlow, String reason, String userFlow);

    int updateStuUserResume(StuUserResume stuUserResume);
}
