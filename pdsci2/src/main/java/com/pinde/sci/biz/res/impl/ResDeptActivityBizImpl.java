package com.pinde.sci.biz.res.impl;


import com.pinde.core.common.enums.DeptActivityItemTypeEnum;
import com.pinde.core.common.enums.DeptActivityStatusEnum;
import com.pinde.core.common.enums.DeptActivityTypeEnum;
import com.pinde.core.common.enums.DeptActivityUserTypeEnum;
import com.pinde.core.model.*;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDeptActivityBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.res.ResDeptActivityExtMapper;
import com.pinde.sci.form.res.ResDeptPlanForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResDeptActivityBizImpl implements IResDeptActivityBiz {
	@Autowired
	private ExamResultsMapper resultsMapper;
	@Autowired
	private ResDeptActivityExtMapper deptActivityExtMapper;
	@Autowired
	private SysDeptMonthPlanMapper planMapper;
	@Autowired
	private SysDeptMonthPlanItemMapper planItemMapper;
	@Autowired
	private SysDeptMonthPlanItemEvalMapper planItemEvalMapper;
	@Autowired
	private SysDeptMonthExamInfoMapper examInfoMapper;


	@Override
	public List<Map<String, Object>> searchList(Map<String, Object> param) {
		return deptActivityExtMapper.searchList(param);
	}

	@Override
	public SysDeptMonthPlan readPlan(String planFlow) {
		if(StringUtil.isBlank(planFlow))
			return null;
		return planMapper.selectByPrimaryKey(planFlow);
	}

	@Override
	public int save(SysDeptMonthPlan payPlan) {
		if(StringUtil.isNotBlank(payPlan.getPlanFlow())){
			GeneralMethod.setRecordInfo(payPlan, false);
			return planMapper.updateByPrimaryKeySelective(payPlan);
		}else{
			payPlan.setPlanFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(payPlan, true);
			return planMapper.insertSelective(payPlan);
		}
	}

	@Override
	public List<SysDeptMonthPlanItem> readPlanItems(String planFlow) {
		if(StringUtil.isNotBlank(planFlow)) {
			SysDeptMonthPlanItemExample example = new SysDeptMonthPlanItemExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
					.andPlanFlowEqualTo(planFlow);
			example.setOrderByClause("create_time");
			return planItemMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public List<SysDeptMonthExamInfo> readPlanExamInfos(String planFlow) {
		if(StringUtil.isNotBlank(planFlow)) {
			SysDeptMonthExamInfoExample example = new SysDeptMonthExamInfoExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
					.andPlanFlowEqualTo(planFlow);
			example.setOrderByClause("create_time");
			return examInfoMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public SysDeptMonthPlan findPlan(String deptFlow, String planDate, String planTypeId) {
		SysDeptMonthPlanExample example=new SysDeptMonthPlanExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andDeptFlowEqualTo(deptFlow).andPlanDateEqualTo(planDate).andPlanTypeIdEqualTo(planTypeId);
		example.setOrderByClause("create_time");
		List<SysDeptMonthPlan>list=planMapper.selectByExample(example);
		if(list!=null&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public int savePlan(ResDeptPlanForm planForm) {
		boolean isNew=false;
		if(StringUtil.isBlank(planForm.getPlanFlow()))
		{
			isNew=true;
			planForm.setPlanFlow(PkUtil.getUUID());
		}
		SysDeptMonthPlan plan=planForm;
//		plan.setPlanTypeName(DeptActivityTypeEnum.getNameById(plan.getPlanTypeId()));
        plan.setPlanTypeName(com.pinde.core.common.enums.DictTypeEnum.DeptActivityType.getDictNameById(plan.getPlanTypeId()));
		plan.setAuditStatusId(DeptActivityStatusEnum.Save.getId());
		plan.setAuditStatusName(DeptActivityStatusEnum.Save.getName());
        plan.setIsReport(com.pinde.core.common.GlobalConstant.FLAG_N);
		GeneralMethod.setRecordInfo(plan,true);
		int c=0;
		if(isNew)
		{
			c=planMapper.insert(plan);
		}else{
			c=planMapper.updateByPrimaryKey(plan);
		}
		if(c==1)
		{
			savePlanInfo(planForm);
		}
		return c;
	}

	@Override
	public List<Map<String, Object>> searchStatisticList(Map<String, Object> param) {
		return deptActivityExtMapper.searchStatisticList(param);
	}

	@Override
	public int reportPlan(SysDeptMonthPlan plan, Map<String, QingpuLectureEvalCfg> cfgMap) {
		int c=save(plan);
		if(c>=1)
		{
			String typeCfg = InitConfig.getSysCfg("dept_activity_type");
			if(DeptActivityTypeEnum.Dept.getId().equals(plan.getPlanTypeId())) {
				for (DeptActivityItemTypeEnum e : DeptActivityItemTypeEnum.values()) {
                    if (e.getIsCfg().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
						QingpuLectureEvalCfg cfg = cfgMap.get(e.getId());
						if (cfg == null) {
							throw new RuntimeException("请维护【" + e.getName() + "】活动评价指标");
						}
						deptActivityExtMapper.updateItemCfg(plan.getPlanFlow(), e.getId(), cfg.getRecordFlow());
					}
				}
            } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(typeCfg)) {
				for(DeptActivityItemTypeEnum e:DeptActivityItemTypeEnum.values())
				{
                    if (e.getIsCfg().equals(com.pinde.core.common.GlobalConstant.FLAG_N) && !DeptActivityItemTypeEnum.CKKHAP.getId().equals(e.getId())
							&&!DeptActivityItemTypeEnum.DSBGHAP.getId().equals(e.getId())
							&&!DeptActivityItemTypeEnum.JYSKHAP.getId().equals(e.getId()))
					{
						QingpuLectureEvalCfg cfg = cfgMap.get(e.getId());
						if (cfg == null) {
							throw new RuntimeException("请维护【" + e.getName() + "】活动评价指标");
						}
						deptActivityExtMapper.updateItemCfg(plan.getPlanFlow(), e.getId(), cfg.getRecordFlow());
					}
				}
			}
		}
		return c;
	}

	@Override
	public SysDeptMonthPlanItem readPlanItem(String itemFlow) {
		return  planItemMapper.selectByPrimaryKey(itemFlow);
	}

	@Override
	public List<SysDeptMonthPlanItemEval> readPlanItemEvals(String itemFlow) {
		SysDeptMonthPlanItemEvalExample evalExample=new SysDeptMonthPlanItemEvalExample();
        evalExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andItemFlowEqualTo(itemFlow);
		evalExample.setOrderByClause("EVAL_SCORE desc");
		return planItemEvalMapper.selectByExampleWithBLOBs(evalExample);
	}

	@Override
	public SysDeptMonthPlanItemEval readPlanItemEval(String evalFlow) {
		return planItemEvalMapper.selectByPrimaryKey(evalFlow);
	}

	@Override
	public SysDeptMonthPlanItemEval readPlanItemEvalByUser(String itemFlow, String userFlow) {
		SysDeptMonthPlanItemEvalExample evalExample=new SysDeptMonthPlanItemEvalExample();
        evalExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andItemFlowEqualTo(itemFlow).andOperUserFlowEqualTo(userFlow);
		evalExample.setOrderByClause("create_time desc");
		List<SysDeptMonthPlanItemEval> list= planItemEvalMapper.selectByExampleWithBLOBs(evalExample);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public int savePlanItemEval(SysDeptMonthPlanItemEval eval) {
		if(StringUtil.isBlank(eval.getEvalFlow()))
		{
			eval.setEvalFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(eval,true);
			return planItemEvalMapper.insert(eval);
		}else{
			GeneralMethod.setRecordInfo(eval,false);
			return 	planItemEvalMapper.updateByPrimaryKey(eval);
		}
	}

	private void savePlanInfo(ResDeptPlanForm planForm) {
		 List<SysDeptMonthPlanItem> jxcfs = planForm.getJxcfs();
		savePlanItem(jxcfs,planForm.getPlanFlow(), DeptActivityItemTypeEnum.JXCFAP.getId());
		 List<SysDeptMonthPlanItem> bltlaps = planForm.getBltlaps();
		savePlanItem(bltlaps,planForm.getPlanFlow(), DeptActivityItemTypeEnum.BLTLAP.getId());
		 List<SysDeptMonthPlanItem> xjkaps = planForm.getXjkaps();
		savePlanItem(xjkaps,planForm.getPlanFlow(), DeptActivityItemTypeEnum.XJKAP.getId());
		 List<SysDeptMonthPlanItem> qthds = planForm.getQthds();
		savePlanItem(qthds,planForm.getPlanFlow(), DeptActivityItemTypeEnum.QTHD.getId());
		 List<SysDeptMonthPlanItem> dsbghaps = planForm.getDsbghaps();
		savePlanItem(dsbghaps,planForm.getPlanFlow(), DeptActivityItemTypeEnum.DSBGHAP.getId());
		 List<SysDeptMonthPlanItem> jtbkaps = planForm.getJtbkaps();
		savePlanItem(jtbkaps,planForm.getPlanFlow(), DeptActivityItemTypeEnum.JTBKAP.getId());
		 List<SysDeptMonthPlanItem> tkaps = planForm.getTkaps();
		savePlanItem(tkaps,planForm.getPlanFlow(), DeptActivityItemTypeEnum.TKAP.getId());
		 List<SysDeptMonthPlanItem> ddaps = planForm.getDdaps();
		savePlanItem(ddaps,planForm.getPlanFlow(), DeptActivityItemTypeEnum.DDAP.getId());
		 List<SysDeptMonthPlanItem> sqthds = planForm.getSqthds();
		savePlanItem(sqthds,planForm.getPlanFlow(), DeptActivityItemTypeEnum.SQTHD.getId());

		 List<SysDeptMonthExamInfo> invigilators =planForm.getInvigilators();
		savePlanExam(invigilators,planForm.getPlanFlow(), DeptActivityUserTypeEnum.Invigilator.getId());
		 List<SysDeptMonthExamInfo> members =planForm.getMembers();
		savePlanExam(members,planForm.getPlanFlow(), DeptActivityUserTypeEnum.Member.getId());

		SysDeptMonthExamInfo charge=getPlanCKinfo(planForm.getPlanFlow(),DeptActivityUserTypeEnum.Charge.getId());
		if(charge==null)
			charge=new SysDeptMonthExamInfo();
		charge.setPlanFlow(planForm.getPlanFlow());
		charge.setExamUserFlow(planForm.getChargeFlow());
		charge.setExamUserName(planForm.getChargeName());
        charge.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		charge.setUserTypeId(DeptActivityUserTypeEnum.Charge.getId());
		charge.setUserTypeName(DeptActivityUserTypeEnum.Charge.getName());
		saveExam(charge);
		SysDeptMonthExamInfo groupLeader=getPlanCKinfo(planForm.getPlanFlow(),DeptActivityUserTypeEnum.GroupLeader.getId());
		if(groupLeader==null)
			groupLeader=new SysDeptMonthExamInfo();
		groupLeader.setPlanFlow(planForm.getPlanFlow());
		groupLeader.setExamUserFlow(planForm.getGroupLeaderFlow());
		groupLeader.setExamUserName(planForm.getGroupLeaderName());
        groupLeader.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		groupLeader.setUserTypeId(DeptActivityUserTypeEnum.GroupLeader.getId());
		groupLeader.setUserTypeName(DeptActivityUserTypeEnum.GroupLeader.getName());
		saveExam(groupLeader);
		
	}

	private SysDeptMonthExamInfo getPlanCKinfo(String planFlow, String userTypeId) {
		if(StringUtil.isNotBlank(planFlow)&&StringUtil.isNotBlank(userTypeId)) {
			SysDeptMonthExamInfoExample example = new SysDeptMonthExamInfoExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
					.andPlanFlowEqualTo(planFlow).andUserTypeIdEqualTo(userTypeId);
			example.setOrderByClause("create_time");
			List<SysDeptMonthExamInfo> list=examInfoMapper.selectByExample(example);
			if(list!=null&&list.size()>0)
			{
				return list.get(0);
			}
		}
		return null;
	}

	private void savePlanExam(List<SysDeptMonthExamInfo> items, String planFlow, String itemTypeId) {
		if(StringUtil.isNotBlank(planFlow))
		{
			//删除子项
			delPlanExam(planFlow,itemTypeId);
			for(SysDeptMonthExamInfo item:items)
			{
				item.setPlanFlow(planFlow);
                item.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				item.setUserTypeId(itemTypeId);
				item.setUserTypeName(DeptActivityUserTypeEnum.getNameById(itemTypeId));
				saveExam(item);
			}
		}
	}

	private int saveExam(SysDeptMonthExamInfo item) {
		if(StringUtil.isBlank(item.getExamFlow()))
		{
			item.setExamFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(item,true);
			return examInfoMapper.insert(item);
		}else{
			GeneralMethod.setRecordInfo(item,false);
			return 	examInfoMapper.updateByPrimaryKey(item);
		}
	}

	private void savePlanItem(List<SysDeptMonthPlanItem> items, String planFlow, String itemTypeId) {
		if(StringUtil.isNotBlank(planFlow))
		{
			//删除子项
			delPlanItem(planFlow,itemTypeId);
			for(SysDeptMonthPlanItem item:items)
			{
				item.setPlanFlow(planFlow);
                item.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				item.setItemTypeId(itemTypeId);
				item.setItemTypeName(DeptActivityItemTypeEnum.getNameById(itemTypeId));
				saveItem(item);
			}
		}
	}

	private int saveItem(SysDeptMonthPlanItem item) {
		if(StringUtil.isBlank(item.getItemFlow()))
		{
			item.setItemFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(item,true);
			return planItemMapper.insert(item);
		}else{
			GeneralMethod.setRecordInfo(item,false);
			return planItemMapper.updateByPrimaryKey(item);
		}
	}

	private void delPlanItem(String planFlow, String itemTypeId) {
		SysDeptMonthPlanItemExample example=new SysDeptMonthPlanItemExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andPlanFlowEqualTo(planFlow).andItemTypeIdEqualTo(itemTypeId);
		SysDeptMonthPlanItem record=new SysDeptMonthPlanItem();
        record.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		planItemMapper.updateByExampleSelective(record,example);
	}

	private void delPlanExam(String planFlow, String itemTypeId) {
		SysDeptMonthExamInfoExample example=new SysDeptMonthExamInfoExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andPlanFlowEqualTo(planFlow).andUserTypeIdEqualTo(itemTypeId);
		SysDeptMonthExamInfo record=new SysDeptMonthExamInfo();
        record.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		examInfoMapper.updateByExampleSelective(record,example);
	}
}
