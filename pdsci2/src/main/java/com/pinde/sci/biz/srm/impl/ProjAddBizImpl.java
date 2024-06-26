package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.TimeUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.srm.IProjAddBiz;
import com.pinde.sci.biz.srm.IProjProcessBiz;
import com.pinde.sci.biz.srm.IProjRecBiz;
import com.pinde.sci.biz.srm.IPubProjBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.srm.*;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ProjAddBizImpl implements IProjAddBiz {
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IPubProjBiz pubProjBiz;
    @Autowired
    private IProjRecBiz projRecBiz;
    @Autowired
    private IProjProcessBiz projProcessBiz;
    @Override
    public int addProjForm(List<MultipartFile> fileList, String projFlow, String recTypeId) {
        SysUser currUser = GlobalContext.getCurrentUser();
        PubProjRecExample example = new PubProjRecExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andProjFlowEqualTo(projFlow).andRecTypeIdEqualTo(recTypeId);
        List<PubProjRec> projRecList = projRecBiz.searchProjRec(example);
        PubProjRec projRec = new PubProjRec();
        projRec.setProjFlow(projFlow);
        projRec.setRecTypeId(recTypeId);
        projRec.setRecTypeName(ProjRecTypeEnum.getNameById(recTypeId));

        PubProj proj = this.pubProjBiz.readProject(projFlow);

        if (ProjRecTypeEnum.Contract.getId().equals(recTypeId)) {
            if (projRecList != null && projRecList.size() > 0) {
                return 0;
            }
            projRec.setProjStageId(ProjStageEnum.Contract.getId());
            projRec.setProjStageName(ProjStageEnum.Contract.getName());
            projRec.setProjStatusId(ProjContractStatusEnum.FirstAudit.getId());
            projRec.setProjStatusName(ProjContractStatusEnum.FirstAudit.getName());

            proj.setProjStageId(ProjStageEnum.Schedule.getId());
            proj.setProjStageName(ProjStageEnum.Schedule.getName());
            proj.setProjStatusId(ProjScheduleStatusEnum.Apply.getId());
            proj.setProjStatusName(ProjScheduleStatusEnum.Apply.getName());
        } else if (ProjRecTypeEnum.ScheduleReport.getId().equals(recTypeId)) {
            if (projRecList != null && projRecList.size() > 0) {
                for (PubProjRec rec : projRecList) {
                    //如果有未审核或未提交的进展报告（只考虑院版）
                    if (!ProjScheduleStatusEnum.FirstAudit.getId().equals(rec.getProjStatusId())) {
                        return 0;
                    }
                }
            }
            projRec.setProjStageId(ProjStageEnum.Schedule.getId());
            projRec.setProjStageName(ProjStageEnum.Schedule.getName());
            projRec.setProjStatusId(ProjContractStatusEnum.FirstAudit.getId());
            projRec.setProjStatusName(ProjContractStatusEnum.FirstAudit.getName());
            proj.setProjStatusId(ProjScheduleStatusEnum.FirstAudit.getId());
            proj.setProjStatusName(ProjScheduleStatusEnum.FirstAudit.getName());
        } else if (ProjRecTypeEnum.ChangeReport.getId().equals(recTypeId)) {
            if (projRecList != null && projRecList.size() > 0) {
                for (PubProjRec rec : projRecList) {
                    //如果有未审核或未提交的变更申请（只考虑院版）
                    if (!ProjScheduleStatusEnum.FirstAudit.getId().equals(rec.getProjStatusId())) {
                        return 0;
                    }
                }
            }
            projRec.setProjStageId(ProjStageEnum.Schedule.getId());
            projRec.setProjStageName(ProjStageEnum.Schedule.getName());
            projRec.setProjStatusId(ProjContractStatusEnum.FirstAudit.getId());
            projRec.setProjStatusName(ProjContractStatusEnum.FirstAudit.getName());
            proj.setProjStatusId(ProjScheduleStatusEnum.FirstAudit.getId());
            proj.setProjStatusName(ProjScheduleStatusEnum.FirstAudit.getName());
        } else if (ProjRecTypeEnum.TerminateReport.getId().equals(recTypeId)) {
            if (projRecList != null && projRecList.size() > 0) {
                return 0;
            }
            projRec.setProjStageId(ProjStageEnum.Complete.getId());
            projRec.setProjStageName(ProjStageEnum.Complete.getName());
            projRec.setProjStatusId(ProjContractStatusEnum.FirstAudit.getId());
            projRec.setProjStatusName(ProjContractStatusEnum.FirstAudit.getName());

            proj.setProjStageId(ProjStageEnum.Complete.getId());
            proj.setProjStageName(ProjStageEnum.Complete.getName());

            proj.setProjStatusId(ProjScheduleStatusEnum.FirstAudit.getId());
            proj.setProjStatusName(ProjScheduleStatusEnum.FirstAudit.getName());
        } else if (ProjRecTypeEnum.CompleteReport.getId().equals(recTypeId)) {
            if (projRecList != null && projRecList.size() > 0) {
                return 0;
            }
            projRec.setProjStageId(ProjStageEnum.Complete.getId());
            projRec.setProjStageName(ProjStageEnum.Complete.getName());
            projRec.setProjStatusId(ProjContractStatusEnum.FirstAudit.getId());
            projRec.setProjStatusName(ProjContractStatusEnum.FirstAudit.getName());

            proj.setProjStageId(ProjStageEnum.Complete.getId());
            proj.setProjStageName(ProjStageEnum.Complete.getName());

            proj.setProjStatusId(ProjScheduleStatusEnum.FirstAudit.getId());
            proj.setProjStatusName(ProjScheduleStatusEnum.FirstAudit.getName());
        }
        StringBuilder builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><projInfo><step1><item name=\"file\" remark=\"附件\" multiple=\"Y\" isFile=\"Y\">");

        if (fileList != null && fileList.size() > 0) {
            for (MultipartFile file : fileList) {
                String fileFlow = fileBiz.addFile(file, currUser.getUserFlow());
                builder.append("<value>").append(fileFlow).append("</value>");
            }
        }
        builder.append("</item></step1></projInfo>");
        String xml = builder.toString();
        projRec.setRecContent(xml);
        projRec.setOperUserFlow(proj.getApplyUserFlow());
        projRec.setOperUserName(proj.getApplyUserName());

        PubProjProcess process = new PubProjProcess();
        process.setProjFlow(proj.getProjFlow());
        process.setProjStageId(projRec.getProjStageId());
        process.setProjStageName(projRec.getProjStageName());
        process.setProjStatusId(projRec.getProjStatusId());
        process.setProjStatusName(projRec.getProjStatusName());
        process.setAuditContent("");
        process.setProcessRemark("");
        process.setOperTime(DateUtil.getCurrDateTime());
        this.projProcessBiz.addProcess(process);

        pubProjBiz.modProject(proj);
        projRecBiz.addProjRec(projRec);
        return GlobalConstant.ONE_LINE;
    }
}
