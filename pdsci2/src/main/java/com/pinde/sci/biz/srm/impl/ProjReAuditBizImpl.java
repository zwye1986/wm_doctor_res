package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IProjProcessBiz;
import com.pinde.sci.biz.srm.IProjReAuditBiz;
import com.pinde.sci.biz.srm.IPubProjBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.enums.srm.*;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by www.0001.Ga on 2017-08-21.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProjReAuditBizImpl implements IProjReAuditBiz {
    @Autowired
    private PubProjMapper pubProjMapper;
    @Autowired
    private IPubProjBiz projBiz;
    @Autowired
    private IProjProcessBiz projProcessBiz;
    @Autowired
    private PubProjRecMapper projRecMapper;
    @Autowired
    private SrmProjFundInfoMapper fundInfoMapper;
    @Autowired
    private SrmProjFundDetailMapper fundDetailMapper;
    @Autowired
    private PubProjAuthorMapper projAuthorMapper;
    @Autowired
    private SrmExpertProjEvalMapper evalMapper;
    @Autowired
    private PubProjProcessMapper processMapper;

    @Override
    public String reAuditOption(String projFlow) {
        SrmExpertProjEvalExample example = new SrmExpertProjEvalExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjFlowEqualTo(projFlow);
        int evalCount = evalMapper.countByExample(example);
        if (evalCount > 0) {
            return "该项目已立项评审，无法申报重审";
        } else {
            PubProj record = new PubProj();
            record.setProjFlow(projFlow);
            record.setProjStageId(ProjStageEnum.Apply.getId());
            record.setProjStageName(ProjStageEnum.Apply.getName());
            record.setProjStatusId(ProjApplyStatusEnum.Submit.getId());
            record.setProjStatusName(ProjApplyStatusEnum.Submit.getName());
            GeneralMethod.setRecordInfo(record, false);
            pubProjMapper.updateByPrimaryKeySelective(record);//撤销到上一级审核状态：申报单位审核通过
            PubProjRec rec = new PubProjRec();
            rec.setRecTypeId(ProjRecTypeEnum.Apply.getId());
            rec.setProjStageId(ProjStageEnum.Apply.getId());
            rec.setProjStageName(ProjStageEnum.Apply.getName());
            rec.setProjStatusId(ProjApplyStatusEnum.Submit.getId());
            rec.setProjStatusName(ProjApplyStatusEnum.Submit.getName());
            GeneralMethod.setRecordInfo(rec, false);
            PubProjRecExample example1 = new PubProjRecExample();
            example1.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjFlowEqualTo(projFlow).andRecTypeIdEqualTo(ProjRecTypeEnum.Apply.getId());
            projRecMapper.updateByExampleSelective(rec, example1);//撤销到上一级审核状态：负责人提交
            PubProjProcess process = new PubProjProcess();
            process.setProjFlow(projFlow);
            List<PubProjProcess> processList = projProcessBiz.searchProjProcess(process);
            if (null != processList && processList.size() > 0) {
                process = processList.get(0);
                process.setProcessFlow(PkUtil.getUUID());
                process.setProjStatusId(ProjReAuditEnum.ReApplyAudit.getId());
                process.setProjStatusName(ProjReAuditEnum.ReApplyAudit.getName());
                GeneralMethod.setRecordInfo(process, true);
                processMapper.insertSelective(process);//添加过程记录
            }
            return "申报重审成功";
        }
    }

    @Override
    public String approveReAudit(String projFlow) {
        if (StringUtil.isNotBlank(projFlow)) {
            PubProjRecExample example = new PubProjRecExample();
            PubProjRecExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            criteria.andProjFlowEqualTo(projFlow).andRecTypeIdEqualTo(ProjRecTypeEnum.Contract.getId());
            int recCount = projRecMapper.countByExample(example);
            if (recCount > 0) {
                return "该项目已填写合同书，无法立项重审！";
            } else {
                PubProj record = pubProjMapper.selectByPrimaryKey(projFlow);
                record.setProjStageId(ProjStageEnum.Approve.getId());
                record.setProjStageName(ProjStageEnum.Approve.getName());
                record.setProjStatusId(ProjApproveStatusEnum.Approving.getId());
                record.setProjStatusName(ProjApproveStatusEnum.Approving.getName());
                record.setProjNo("");
                GeneralMethod.setRecordInfo(record, false);
                pubProjMapper.updateByPrimaryKeySelective(record);//撤销到上一级审核状态：立项待审核

                //删除经费信息
                SrmProjFundInfoExample fundInfoExample = new SrmProjFundInfoExample();
                fundInfoExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjFlowEqualTo(projFlow);
                List<SrmProjFundInfo> fundInfoList = fundInfoMapper.selectByExample(fundInfoExample);
                if(fundInfoList != null && fundInfoList.size()>0){
                    for(SrmProjFundInfo fundInfo:fundInfoList){
                        fundInfo.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                        fundInfoMapper.updateByPrimaryKeySelective(fundInfo);

                        SrmProjFundDetail fundDetail = new SrmProjFundDetail();
                        fundDetail.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                        SrmProjFundDetailExample fundDetailExample = new SrmProjFundDetailExample();
                        fundDetailExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andFundFlowEqualTo(fundInfo.getFundFlow());
                        fundDetailMapper.updateByExampleSelective(fundDetail,fundDetailExample);
                    }
                }

                //项目作者删除
                PubProjAuthor author = new PubProjAuthor();
                author.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                PubProjAuthorExample authorExample = new PubProjAuthorExample();
                authorExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjFlowEqualTo(projFlow);
                projAuthorMapper.updateByExampleSelective(author,authorExample);

                PubProjProcess process = new PubProjProcess();
                process.setProjFlow(projFlow);
                List<PubProjProcess> processList = projProcessBiz.searchProjProcess(process);
                if (null != processList && processList.size() > 0) {
                    process = processList.get(0);
                    process.setProcessFlow(PkUtil.getUUID());
                    process.setProjStatusId(ProjReAuditEnum.ReApprove.getId());
                    process.setProjStatusName(ProjReAuditEnum.ReApprove.getName());
                    process.setProcessRemark(ProjReAuditEnum.ReApprove.getName());
                    GeneralMethod.setRecordInfo(process, true);
                    processMapper.insertSelective(process);//添加过程记录
                }
                return "立项重审成功";
            }
        }
        return "操作失败！";
    }

    @Override
    public String contractReAudit(String projFlow) {
        if (StringUtil.isNotBlank(projFlow)) {
            PubProjRecExample example = new PubProjRecExample();
            PubProjRecExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            List<String> recTypeList = new ArrayList<>();
            recTypeList.add(ProjRecTypeEnum.ScheduleReport.getId());
            recTypeList.add(ProjRecTypeEnum.ChangeReport.getId());
            criteria.andProjFlowEqualTo(projFlow).andRecTypeIdIn(recTypeList);
            int recCount = projRecMapper.countByExample(example);
            if (recCount > 0) {
                return "该项目已填写进展报告或变更申请，无法进行合同重审！";
            } else {
                PubProj record = pubProjMapper.selectByPrimaryKey(projFlow);
                record.setProjStageId(ProjStageEnum.Contract.getId());
                record.setProjStageName(ProjStageEnum.Contract.getName());
                record.setProjStatusId(ProjContractStatusEnum.Submit.getId());
                record.setProjStatusName(ProjContractStatusEnum.Submit.getName());
                GeneralMethod.setRecordInfo(record, false);
                pubProjMapper.updateByPrimaryKeySelective(record);//撤销到上一级审核状态：合同待审核

                PubProjRec rec = new PubProjRec();
                rec.setProjStageId(ProjStageEnum.Contract.getId());
                rec.setProjStageName(ProjStageEnum.Contract.getName());
                rec.setProjStatusId(ProjContractStatusEnum.Submit.getId());
                rec.setProjStatusName(ProjContractStatusEnum.Submit.getName());
                GeneralMethod.setRecordInfo(rec, false);
                PubProjRecExample example1 = new PubProjRecExample();
                example1.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjFlowEqualTo(projFlow).andRecTypeIdEqualTo(ProjRecTypeEnum.Contract.getId());
                projRecMapper.updateByExampleSelective(rec, example1);//撤销到上一级审核状态：合同待审核

                PubProjProcess process = new PubProjProcess();
                process.setProjFlow(projFlow);
                List<PubProjProcess> processList = projProcessBiz.searchProjProcess(process);
                if (null != processList && processList.size() > 0) {
                    process = processList.get(0);
                    process.setProcessFlow(PkUtil.getUUID());
                    process.setProjStatusId(ProjReAuditEnum.ReContractAudit.getId());
                    process.setProjStatusName(ProjReAuditEnum.ReContractAudit.getName());
                    process.setProcessRemark(ProjReAuditEnum.ReContractAudit.getName());
                    GeneralMethod.setRecordInfo(process, true);
                    processMapper.insertSelective(process);//添加过程记录
                }
                return "合同重审成功";
            }
        }
        return "操作失败！";
    }

    @Override
    public String scheduleReAudit(String projFlow) {
        if (StringUtil.isNotBlank(projFlow)) {
            PubProjRecExample example = new PubProjRecExample();
            PubProjRecExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            List<String> recTypeList = new ArrayList<>();
            recTypeList.add(ProjRecTypeEnum.ScheduleReport.getId());
            recTypeList.add(ProjRecTypeEnum.ChangeReport.getId());
            criteria.andProjFlowEqualTo(projFlow).andRecTypeIdIn(recTypeList);
            example.setOrderByClause("CREATE_TIME DESC");
            List<PubProjRec> recList = projRecMapper.selectByExample(example);
            if (recList!= null && recList.size() > 0) {
                PubProjRec recChange = new PubProjRec();
                for(PubProjRec rec : recList){
                    recChange=rec;
                    if(!ProjScheduleStatusEnum.FirstAudit.equals(rec.getProjStatusId())){
                        return "该项目有正在进行中的进展报告或变更申请，无法进行重审！";
                    }
                }
                PubProj record = pubProjMapper.selectByPrimaryKey(projFlow);
                if(ProjStageEnum.Schedule.getId().equals(record.getProjStageId())){
                    record.setProjStageId(ProjStageEnum.Schedule.getId());
                    record.setProjStageName(ProjStageEnum.Schedule.getName());
                    record.setProjStatusId(ProjScheduleStatusEnum.Submit.getId());
                    record.setProjStatusName(ProjScheduleStatusEnum.Submit.getName());
                    GeneralMethod.setRecordInfo(record, false);
                    pubProjMapper.updateByPrimaryKeySelective(record);//撤销到上一级审核状态：合同待审核


                    recChange.setProjStageId(ProjStageEnum.Schedule.getId());
                    recChange.setProjStageName(ProjStageEnum.Schedule.getName());
                    recChange.setProjStatusId(ProjScheduleStatusEnum.Submit.getId());
                    recChange.setProjStatusName(ProjScheduleStatusEnum.Submit.getName());
                    GeneralMethod.setRecordInfo(recChange, false);
                    PubProjRecExample example1 = new PubProjRecExample();
                    example1.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjFlowEqualTo(projFlow).andRecFlowEqualTo(recChange.getRecFlow());
                    projRecMapper.updateByExampleSelective(recChange, example1);//撤销到上一级审核状态：合同待审核

                    PubProjProcess process = new PubProjProcess();
                    process.setProjFlow(projFlow);
                    List<PubProjProcess> processList = projProcessBiz.searchProjProcess(process);
                    if (null != processList && processList.size() > 0) {
                        process = processList.get(0);
                        process.setProcessFlow(PkUtil.getUUID());
                        process.setProjStatusId(ProjReAuditEnum.ReScheduleAudit.getId());
                        process.setProjStatusName(ProjReAuditEnum.ReScheduleAudit.getName());
                        process.setProcessRemark(ProjReAuditEnum.ReScheduleAudit.getName());
                        GeneralMethod.setRecordInfo(process, true);
                        processMapper.insertSelective(process);//添加过程记录
                    }
                    return "合同重审成功";
                }
            }

        }
        return "操作失败！";
    }

    @Override
    public String changeReAudit(String projFlow) {
        return null;
    }

    @Override
    public String completeReAudit(String projFlow) {
        if (StringUtil.isNotBlank(projFlow)) {
            PubProj record = pubProjMapper.selectByPrimaryKey(projFlow);
             if (record != null ) {
                 if(ProjStageEnum.Complete.getId().equals(record.getProjStageId())){
                     record.setProjStatusId(ProjContractStatusEnum.Submit.getId());
                     record.setProjStatusName(ProjContractStatusEnum.Submit.getName());
                     GeneralMethod.setRecordInfo(record, false);
                     pubProjMapper.updateByPrimaryKeySelective(record);//撤销到上一级审核状态：待审核
                 }else if(ProjStageEnum.Archive.getId().equals(record.getProjStageId())){
                     return "该项目已归档，无法进行重审！";
                 }
                PubProjRec rec = new PubProjRec();
                rec.setProjStageId(ProjStageEnum.Complete.getId());
                rec.setProjStageName(ProjStageEnum.Complete.getName());
                rec.setProjStatusId(ProjContractStatusEnum.Submit.getId());
                rec.setProjStatusName(ProjContractStatusEnum.Submit.getName());
                GeneralMethod.setRecordInfo(rec, false);
                PubProjRecExample example1 = new PubProjRecExample();
                example1.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjFlowEqualTo(projFlow).andRecTypeIdEqualTo(ProjRecTypeEnum.CompleteReport.getId());
                projRecMapper.updateByExampleSelective(rec, example1);//撤销到上一级审核状态：合同待审核

                PubProjProcess process = new PubProjProcess();
                process.setProjFlow(projFlow);
                List<PubProjProcess> processList = projProcessBiz.searchProjProcess(process);
                if (null != processList && processList.size() > 0) {
                    process = processList.get(0);
                    process.setProcessFlow(PkUtil.getUUID());
                    process.setProjStatusId(ProjReAuditEnum.ReCompleteAudit.getId());
                    process.setProjStatusName(ProjReAuditEnum.ReCompleteAudit.getName());
                    process.setProcessRemark(ProjReAuditEnum.ReCompleteAudit.getName());
                    GeneralMethod.setRecordInfo(process, true);
                    processMapper.insertSelective(process);//添加过程记录
                }
                return "验收报告重审成功";
            }else{
                 return "重审失败！";
             }

        }
        return "重审失败";
    }

    @Override
    public String terminateReAudit(String projFlow) {
        if (StringUtil.isNotBlank(projFlow)) {
            PubProj record = pubProjMapper.selectByPrimaryKey(projFlow);
            if (record != null ) {
                if(ProjStageEnum.Complete.getId().equals(record.getProjStageId())){
                    record.setProjStatusId(ProjContractStatusEnum.Submit.getId());
                    record.setProjStatusName(ProjContractStatusEnum.Submit.getName());
                    GeneralMethod.setRecordInfo(record, false);
                    pubProjMapper.updateByPrimaryKeySelective(record);//撤销到上一级审核状态：待审核
                }else if(ProjStageEnum.Archive.getId().equals(record.getProjStageId())){
                    return "该项目已归档，无法进行重审！";
                }
                PubProjRec rec = new PubProjRec();
                rec.setProjStageId(ProjStageEnum.Complete.getId());
                rec.setProjStageName(ProjStageEnum.Complete.getName());
                rec.setProjStatusId(ProjContractStatusEnum.Submit.getId());
                rec.setProjStatusName(ProjContractStatusEnum.Submit.getName());
                GeneralMethod.setRecordInfo(rec, false);
                PubProjRecExample example1 = new PubProjRecExample();
                example1.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjFlowEqualTo(projFlow).andRecTypeIdEqualTo(ProjRecTypeEnum.TerminateReport.getId());
                projRecMapper.updateByExampleSelective(rec, example1);//撤销到上一级审核状态：合同待审核

                PubProjProcess process = new PubProjProcess();
                process.setProjFlow(projFlow);
                List<PubProjProcess> processList = projProcessBiz.searchProjProcess(process);
                if (null != processList && processList.size() > 0) {
                    process = processList.get(0);
                    process.setProcessFlow(PkUtil.getUUID());
                    process.setProjStatusId(ProjReAuditEnum.ReTerminateAudit.getId());
                    process.setProjStatusName(ProjReAuditEnum.ReTerminateAudit.getName());
                    process.setProcessRemark(ProjReAuditEnum.ReTerminateAudit.getName());
                    GeneralMethod.setRecordInfo(process, true);
                    processMapper.insertSelective(process);//添加过程记录
                }
                return "验收报告重审成功";
            }else{
                return "重审失败！";
            }

        }
        return "重审失败";
    }
}
