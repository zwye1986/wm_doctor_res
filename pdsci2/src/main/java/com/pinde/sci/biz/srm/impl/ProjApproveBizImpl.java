package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IProjApproveBiz;
import com.pinde.sci.biz.srm.IProjProcessBiz;
import com.pinde.sci.biz.srm.IPubProjBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.enums.pub.ProjCategroyEnum;
import com.pinde.sci.enums.srm.*;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.PubProjExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProjApproveBizImpl implements IProjApproveBiz {

    @Autowired
    private PubProjMapper pubProjMapper;
    @Autowired
    private IPubProjBiz projBiz;
    @Autowired
    private IProjProcessBiz projProcessBiz;
    @Autowired
    private PubProjFundPlanMapper projFundPlanMapper;
    @Autowired
    private PubProjRecMapper projRecMapper;
    @Autowired
    private SrmProjFundInfoMapper fundInfoMapper;
    @Autowired
    private AchScoreBiz achScoreBiz;
    @Autowired
    private SrmExpertProjEvalMapper evalMapper;
    @Autowired
    private PubProjProcessMapper processMapper;

    @Override
    public void setUp(PubProj proj, String remark, String sug, String setUpXml) {
        SysUser currUser = GlobalContext.getCurrentUser();
        PubProjProcess process = new PubProjProcess();
        process.setProjFlow(proj.getProjFlow());
        process.setProjStageId(proj.getProjStageId());
        process.setProjStageName(proj.getProjStageName());
        process.setProjStatusId(proj.getProjStatusId());
        process.setProjStatusName(proj.getProjStatusName());
        process.setProcessRemark(remark);
        process.setAuditContent(sug);
        process.setOperOrgFlow(currUser.getOrgFlow());
        process.setOperOrgName(currUser.getOrgName());
        process.setOperUserFlow(currUser.getUserFlow());
        process.setOperUserName(currUser.getUserName());
        this.projProcessBiz.addProcess(process);
        //为立项rec添加记录
        if (StringUtil.isNotBlank(setUpXml)) {
            PubProjRec record = new PubProjRec();
            record.setRecFlow(PkUtil.getUUID());
            record.setProjFlow(proj.getProjFlow());
            record.setProjStageId(proj.getProjStageId());
            record.setProjStageName(proj.getProjStageName());
            record.setProjStatusId(proj.getProjStatusId());
            record.setProjStatusName(proj.getProjStatusName());
            record.setRecTypeId(ProjRecTypeEnum.SetUp.getId());
            record.setRecTypeName(ProjRecTypeEnum.SetUp.getName());
            record.setRecContent(setUpXml);
            record.setOperUserFlow(currUser.getUserFlow());
            record.setOperUserName(currUser.getUserName());
            record.setOperTime(DateUtil.getCurrDateTime());
            GeneralMethod.setRecordInfo(record, true);
            this.projRecMapper.insertSelective(record);
        }
        //如果同意立项
        if (ProjApproveStatusEnum.Approve.getId().equals(proj.getProjStatusId())) {
            //如果配置了合同表单  阶段改为合同阶段 状态改为填写状态
            String projTypeId = this.projBiz.readProject(proj.getProjFlow()).getProjTypeId();
            String pageGroupName = InitConfig.configMap.get(ProjRecTypeEnum.Contract.getId()).get(projTypeId);
            if (StringUtil.isNotBlank(pageGroupName)) {
                proj.setProjStageId(ProjStageEnum.Contract.getId());
                proj.setProjStageName(ProjStageEnum.Contract.getName());
                proj.setProjStatusId(ProjContractStatusEnum.Apply.getId());
                proj.setProjStatusName(ProjContractStatusEnum.Apply.getName());
            } else {
                //否则跳到进展阶段 填写状态
                proj.setProjStageId(ProjStageEnum.Schedule.getId());
                proj.setProjStageName(ProjStageEnum.Schedule.getName());
                proj.setProjStatusId(ProjScheduleStatusEnum.Apply.getId());
                proj.setProjStatusName(ProjScheduleStatusEnum.Apply.getName());
            }
        }
        if (ProjApproveStatusEnum.AwardPrizes.getId().equals(proj.getProjStatusId())) {//科技报奖 确认发奖  阶段改为 发奖阶段
            if (ProjCategroyEnum.Bj.getId().equals(proj.getProjCategoryId())) {
                proj.setProjStageId(ProjStageEnum.Award.getId());
                proj.setProjStageName(ProjStageEnum.Award.getName());
                proj.setProjStatusId(ProjApproveStatusEnum.AwardPrizes.getId());
                proj.setProjStatusName(ProjApproveStatusEnum.AwardPrizes.getName());
            }
        }
        GeneralMethod.setRecordInfo(proj, false);
        this.projBiz.modProject(proj);

    }

    @Override
    public void setUp(PubProj proj, String remark, String sug, String setUpXml, SrmProjFundInfo fundInfo, List<PubProjAuthor> authorList) {
        //立项通过
        if (ProjApproveStatusEnum.Approve.getId().equals(proj.getProjStatusId())) {
            if (fundInfo != null) {
        /*添加经费信息*/
                fundInfo.setProjFlow(proj.getProjFlow());
                fundInfo.setBudgetStatusId(AchStatusEnum.Apply.getId());
                fundInfo.setBudgetStatusName(AchStatusEnum.Apply.getName());
                fundInfo.setFundFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(fundInfo, true);
                fundInfoMapper.insertSelective(fundInfo);
            }
            //添加项目作者信息（积分统计）
            saveAuthorList(authorList,proj);
        }
        this.setUp(proj, remark, sug, setUpXml);
    }
    private void saveAuthorList(List<PubProjAuthor> authorList,PubProj proj){
        if (authorList != null && StringUtil.isNotBlank(proj.getProjFlow())) {
            for (PubProjAuthor projAuthor : authorList) {
                SrmAchScore achScore = new SrmAchScore();
                achScore.setScoreFlow(projAuthor.getScoreFlow());
                List<SrmAchScore> achScoreList = achScoreBiz.searchScoreSetList(achScore);
                if (achScoreList != null && achScoreList.size() > 0) {
                    achScore = achScoreList.get(0);
                    projAuthor.setProjFlow(proj.getProjFlow());
//                        projAuthor.setUserFlow(proj.getApplyUserFlow());
                    projAuthor.setScoreFlow(achScore.getScoreFlow());
                    projAuthor.setScoreName(achScore.getScoreName());
                    projAuthor.setAchScore(achScore.getScorePersonalValue());
                    projAuthor.setAchScoreDept(achScore.getScoreDeptValue());
                        /*projAuthor.setAuthorName(proj.getApplyUserName());
                        projAuthor.setDeptFlow(proj.getApplyDeptFlow());
                        projAuthor.setDeptName(proj.getApplyDeptName());*/
                    projBiz.modProjAuthor(projAuthor);
                }
            }
        }
    }
    @Override
    public List<PubProj> searchApproveProjList(PubProj project,String startYear,String endYear) {
        PubProjExample projectExample = new PubProjExample();
        Criteria criteria = projectExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        criteria.andProjCategoryIdEqualTo(project.getProjCategoryId());
        if (StringUtil.isNotBlank(project.getProjName())) {
            criteria.andProjNameLike("%" + project.getProjName() + "%");
        }
        if (StringUtil.isNotBlank((project.getProjNo()))) {
            criteria.andProjNoEqualTo(project.getProjNo());
        }
        if (StringUtil.isNotBlank((project.getProjDeclarerFlow()))) {
            criteria.andProjDeclarerFlowEqualTo(project.getProjDeclarerFlow());
        }
        if (StringUtil.isNotBlank((project.getProjSecondSourceId()))) {
            criteria.andProjSecondSourceIdEqualTo(project.getProjSecondSourceId());
        }
        if (StringUtil.isNotBlank(project.getApplyOrgFlow())) {
            criteria.andApplyOrgFlowEqualTo(project.getApplyOrgFlow());
        }
        if (StringUtil.isNotBlank(project.getApplyDeptFlow())) {
            criteria.andApplyDeptFlowEqualTo(project.getApplyDeptFlow());
        }
        if (StringUtil.isNotBlank(project.getChargeOrgFlow())) {
            criteria.andChargeOrgFlowEqualTo(project.getChargeOrgFlow());
        }
        if (StringUtil.isNotBlank(project.getProjStageId())) {
            criteria.andProjStageIdEqualTo(project.getProjStageId());
        }
        if (StringUtil.isNotBlank(project.getProjStatusId())) {
            criteria.andProjStatusIdEqualTo(project.getProjStatusId());
        }
        if (StringUtil.isNotBlank(project.getProjYear())) {
            criteria.andProjYearEqualTo(project.getProjYear());
        }
        if (StringUtil.isNotBlank(project.getProjTypeId())) {
            criteria.andProjTypeIdEqualTo(project.getProjTypeId());
        }
        if (StringUtil.isNotBlank(project.getSubjName())) {
            criteria.andSubjNameLike("%" + project.getSubjName() + "%");
        }
        if (StringUtil.isNotBlank(project.getSubjId())) {
            criteria.andSubjIdEqualTo(project.getSubjId());
        }
        if (StringUtil.isNotBlank(project.getApplyUserName())) {
            criteria.andApplyUserNameLike("%" + project.getApplyUserName() + "%");
        }
        if(StringUtil.isNotBlank(startYear)){
            criteria.andProjYearGreaterThanOrEqualTo(startYear);
        }
        if(StringUtil.isNotBlank(endYear)){
            criteria.andProjYearLessThanOrEqualTo(endYear);
        }
        projectExample.setOrderByClause("CREATE_TIME DESC");
        return pubProjMapper.selectByExample(projectExample);
    }

    @Override
    public List<PubProj> searchFundPlanList(PubProj proj) {
        Map<Object, Object> paramMap = new HashMap<Object, Object>();
        paramMap.put("proj", proj);
        paramMap.put("stage", ProjStageEnum.Approve.getId());
        paramMap.put("status", ProjApproveStatusEnum.Approve.getId());
        return this.projBiz.searchPubProjListForFundPlan(paramMap);
    }

    @Override
    public List<PubProjFundPlan> searchProjFundPlanList(
            PubProjFundPlan projFindPlan) {
        PubProjFundPlanExample example = new PubProjFundPlanExample();
        com.pinde.sci.model.mo.PubProjFundPlanExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(projFindPlan.getProjFlow())) {
            criteria.andProjFlowEqualTo(projFindPlan.getProjFlow());
        }
		/*if(StringUtil.isNotBlank(projFindPlan.getRecordStatus())){
			criteria.andRecordStatusEqualTo(projFindPlan.getRecordStatus());
		}*/
        example.setOrderByClause("YEAR ");
        return this.projFundPlanMapper.selectByExample(example);
    }

    @Override
    public void addFundPlan(PubProj proj, List<PubProjFundPlan> fundPlans, String flag) {
        PubProjFundPlanExample example = new PubProjFundPlanExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andProjFlowEqualTo(proj.getProjFlow());
        example.setOrderByClause("YEAR ");
        List<PubProjFundPlan> oldProjFundPlans = this.projFundPlanMapper.selectByExample(example);
        for (int i = 0; i < oldProjFundPlans.size(); i++) {
            fundPlans.get(i).setFundPlanFlow(oldProjFundPlans.get(i).getFundPlanFlow());
        }
        for (PubProjFundPlan ppjf : fundPlans) {
            this.saveProjFundPlan(ppjf);
        }

        //新增process
        PubProjProcess process = new PubProjProcess();
        process.setProjFlow(proj.getProjFlow());
        process.setProjStageId(proj.getProjStageId());
        process.setProjStageName(proj.getProjStageName());
        process.setProjStatusId(proj.getProjStatusId());
        process.setProjStatusName(proj.getProjStatusName());
        this.projProcessBiz.addProcess(process);

        //更改proj的状态
        //this.pubProjMapper.updateByPrimaryKeySelective(proj);
    }

    private void saveProjFundPlan(PubProjFundPlan projFundPlan) {
        if (StringUtil.isNotBlank(projFundPlan.getFundPlanFlow())) {
            PubProjFundPlan exitProjFundPlan = projFundPlanMapper.selectByPrimaryKey(projFundPlan.getFundPlanFlow());
            exitProjFundPlan.setAmount(projFundPlan.getAmount());
            GeneralMethod.setRecordInfo(exitProjFundPlan, false);
            this.projFundPlanMapper.updateByPrimaryKey(exitProjFundPlan);
        } else {
            projFundPlan.setFundPlanFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(projFundPlan, true);
            this.projFundPlanMapper.insertSelective(projFundPlan);
        }
    }

    @Override
    public List<PubProj> getPubProjByProjNo(String projNo) {
        List<PubProj> projList = null;
        if (StringUtil.isNotBlank(projNo)) {
            PubProjExample example = new PubProjExample();
            example.createCriteria().andProjNoEqualTo(projNo).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            projList = pubProjMapper.selectByExample(example);
        }
        return projList;
    }

    @Override
    public int addSetUp(PubProj proj, List<PubProjAuthor> projAuthorList, SrmProjFundInfo fundInfo, PubProjProcess process) {
        proj.setProjFlow(PkUtil.getUUID());
        this.projBiz.savePubProj(proj);
        /*projAuthor.setProjFlow(proj.getProjFlow());
        projBiz.modProjAuthor(projAuthor);*/
        saveAuthorList(projAuthorList,proj);
        fundInfo.setFundFlow(PkUtil.getUUID());
        fundInfo.setProjFlow(proj.getProjFlow());
        GeneralMethod.setRecordInfo(fundInfo, true);
        fundInfoMapper.insertSelective(fundInfo);
        process.setProjFlow(proj.getProjFlow());
        this.projProcessBiz.addProcess(process);
        return GlobalConstant.ONE_LINE;
    }

    @Override
    public String reAuditOption(String projFlow) {
        SrmExpertProjEvalExample example = new SrmExpertProjEvalExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjFlowEqualTo(projFlow);
        int evalCount = evalMapper.countByExample( example);
        if(evalCount > 0){
            return "该项目已立项评审，无法申报重审";
        }else{
            PubProj record = new PubProj();
            record.setProjFlow(projFlow);
            record.setProjStageId(ProjStageEnum.Apply.getId());
            record.setProjStageName(ProjStageEnum.Apply.getName());
            record.setProjStatusId(ProjApplyStatusEnum.FirstAudit.getId());
            record.setProjStatusName(ProjApplyStatusEnum.FirstAudit.getName());
            GeneralMethod.setRecordInfo(record,false);
            pubProjMapper.updateByPrimaryKeySelective(record);//撤销到上一级审核状态：申报单位审核通过
            PubProjRec  rec = new PubProjRec();
            rec.setRecTypeId(ProjRecTypeEnum.Apply.getId());
            record.setProjStageId(ProjStageEnum.Apply.getId());
            record.setProjStageName(ProjStageEnum.Apply.getName());
            record.setProjStatusId(ProjApplyStatusEnum.FirstAudit.getId());
            record.setProjStatusName(ProjApplyStatusEnum.FirstAudit.getName());
            GeneralMethod.setRecordInfo(rec,false);
            PubProjRecExample example1 = new PubProjRecExample();
            example1.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjFlowEqualTo(projFlow);
            projRecMapper.updateByExampleSelective(rec,example1);//撤销到上一级审核状态：申报单位审核通过
            PubProjProcess process = new PubProjProcess();
            process.setProjFlow(projFlow);
            process.setProjStageId(ProjStageEnum.Apply.getId());
            process.setProjStatusId(ProjApplyStatusEnum.FirstAudit.getId());
            List<PubProjProcess>  processList = projProcessBiz.searchProjProcess(process);
            if(null != processList && processList.size()> 0){
                process = processList.get(0);
                process.setProcessFlow(PkUtil.getUUID());
                process.setProjStatusId(ProjApplyStatusEnum.ThirdReAudit.getId());
                process.setProjStatusName(ProjApplyStatusEnum.ThirdReAudit.getName());
                GeneralMethod.setRecordInfo(process,true);
                processMapper.insertSelective(process);//添加过程记录
            }
            return "申报重审成功";
        }
    }
}
