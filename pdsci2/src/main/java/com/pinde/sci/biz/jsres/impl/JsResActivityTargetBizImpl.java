package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.model.*;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResActivityTargetBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.TeachingActivityFormValueMapper;
import com.pinde.sci.dao.base.TeachingActivityInfoTargetMapper;
import com.pinde.sci.dao.base.TeachingActivityTargetMapper;
import com.pinde.sci.dao.jsres.TeachingActivityInfoExtMapper;
import com.pinde.sci.dao.jsres.TeachingActivityTargetExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JsResActivityTargetBizImpl implements IJsResActivityTargetBiz {

	@Autowired
	private TeachingActivityTargetMapper activityTargetMapper;
	@Autowired
	private TeachingActivityInfoTargetMapper activityInfoTargetMapper;
	@Autowired
	private TeachingActivityInfoExtMapper activityInfoExtMapper;
	@Autowired
	private TeachingActivityFormValueMapper formValueMapper;
	@Autowired
	private TeachingActivityTargetExtMapper targetExtMapper;

	@Override
	public List<TeachingActivityTarget> list(Map<String, String> param) {
		TeachingActivityTargetExample example=new TeachingActivityTargetExample();
        TeachingActivityTargetExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(param.get("orgFlow")))
		{
			criteria.andOrgFlowEqualTo(param.get("orgFlow"));
		}
		if(StringUtil.isNotBlank(param.get("activityTypeId")))
		{
			criteria.andActivityTypeIdEqualTo(param.get("activityTypeId"));
		}
		if(StringUtil.isNotBlank(param.get("targetName")))
		{
			criteria.andTargetNameLike("%"+param.get("targetName")+"%");
		}
		example.setOrderByClause("ordinal ");
		return activityTargetMapper.selectByExample(example);
	}

	@Override
	public List<TeachingActivityTarget> listNew(Map<String, String> param) {
		TeachingActivityTargetExample example = new TeachingActivityTargetExample();
        TeachingActivityTargetExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(param.get("targetType"))) {
			criteria.andTargetTypeEqualTo(param.get("targetType"));
		}
		if (StringUtil.isNotBlank(param.get("activityTypeId"))) {
			criteria.andActivityTypeIdEqualTo(param.get("activityTypeId"));
		}
		if (StringUtil.isNotBlank(param.get("orgFlow"))) {
			criteria.andOrgFlowEqualTo(param.get("orgFlow"));
		}
		if (StringUtil.isNotBlank(param.get("targetName"))) {
			criteria.andTargetNameLike("%" + param.get("targetName") + "%");
		}
		example.setOrderByClause("ordinal");
		return activityTargetMapper.selectByExample(example);
	}

	@Override
	public List<TeachingActivityTarget> readByOrgNew(String activityTypeId,String orgFlow) {
		TeachingActivityTargetExample example=new TeachingActivityTargetExample();
        TeachingActivityTargetExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andOrgFlowEqualTo(orgFlow);
		// 查询评价指标活动形式和教学活动的活动形式一致的评价指标
		if(StringUtil.isNotBlank(activityTypeId)){
			criteria.andActivityTypeIdEqualTo(activityTypeId);
		}
		// 查询类型是教学活动的评价指标
		criteria.andTargetTypeEqualTo("JX");
		example.setOrderByClause("ordinal ");
		List<TeachingActivityTarget> list= activityTargetMapper.selectByExample(example);
		return list;
	}

	@Override
	public int saveTargetNew(TeachingActivityTarget add) {
		if (StringUtil.isNotBlank(add.getTargetFlow())) {
			GeneralMethod.setRecordInfo(add, false);
			return activityTargetMapper.updateByPrimaryKeySelective(add);
		} else {
			add.setTargetFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(add, true);
			// 根据指标类型查询最大的排序码
			int ordinal = targetExtMapper.getMaxOrdinalByType(add.getTargetType());
			add.setOrdinal(ordinal);
			return activityTargetMapper.insertSelective(add);
		}
	}
	@Override
	public int delTarget(String targetFlow) {
		TeachingActivityTarget target=new TeachingActivityTarget();
		target.setTargetFlow(targetFlow);
        target.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		return activityTargetMapper.updateByPrimaryKeySelective(target);
	}

	@Override
	public TeachingActivityTarget readByName(String orgFlow, String targetName) {
		TeachingActivityTargetExample example=new TeachingActivityTargetExample();
        TeachingActivityTargetExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			criteria.andOrgFlowEqualTo(orgFlow);
			criteria.andTargetNameEqualTo(targetName);
		List<TeachingActivityTarget> list= activityTargetMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<TeachingActivityTarget> readByOrg(String orgFlow) {
		TeachingActivityTargetExample example=new TeachingActivityTargetExample();
        TeachingActivityTargetExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andOrgFlowEqualTo(orgFlow);
		example.setOrderByClause("create_time ");
		List<TeachingActivityTarget> list= activityTargetMapper.selectByExample(example);
		return list;
	}

	@Override
	public int saveTarget(TeachingActivityTarget add) {
		if(StringUtil.isNotBlank(add.getTargetFlow()))
		{
			GeneralMethod.setRecordInfo(add,false);
			return  activityTargetMapper.updateByPrimaryKeySelective(add);
		}else{
			add.setTargetFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(add,true);
			return  activityTargetMapper.insertSelective(add);
		}
	}
	@Override
	public int saveInfoTarget(TeachingActivityInfoTarget add) {
		if(StringUtil.isNotBlank(add.getRecordFlow()))
		{
			GeneralMethod.setRecordInfo(add,false);
			return  activityInfoTargetMapper.updateByPrimaryKeySelective(add);
		}else{
			add.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(add,true);
			return  activityInfoTargetMapper.insertSelective(add);
		}
	}

	@Override
	public TeachingActivityTarget readByFlow(String targetFlow) {
		return activityTargetMapper.selectByPrimaryKey(targetFlow);
	}

	@Override
	public List<TeachingActivityInfoTarget> readActivityTargets(String activityFlow) {
		TeachingActivityInfoTargetExample example=new TeachingActivityInfoTargetExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andActivityFlowEqualTo(activityFlow);
		example.setOrderByClause("ORDINAL ASC");
		return activityInfoTargetMapper.selectByExample(example);
	}
	@Override
	public TeachingActivityInfoTarget readActivityTarget(String activityFlow,String targetFlow) {
		TeachingActivityInfoTargetExample example=new TeachingActivityInfoTargetExample();
		example.createCriteria().andActivityFlowEqualTo(activityFlow).andTargetFlowEqualTo(targetFlow);
		List<TeachingActivityInfoTarget> list=activityInfoTargetMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> readActivityTargetEvals(String activityFlow) {
		return activityInfoExtMapper.readActivityTargetEvals(activityFlow);
	}

	@Override
	public List<TeachingActivityEval> readTeachingEvals(String activityFlow) {
		return activityInfoExtMapper.readTeachingEvals(activityFlow);
	}

	@Override
	public int editFrom(TeachingActivityFormValue formValue) {
		String recordFlow = formValue.getRecordFlow();
		if(StringUtil.isNotBlank(recordFlow)){
			GeneralMethod.setRecordInfo(formValue,false);
			return formValueMapper.updateByPrimaryKeySelective(formValue);
		}else{
			formValue.setRecordFlow(formValue.getRecordFlow());
			GeneralMethod.setRecordInfo(formValue,true);
			return formValueMapper.insertSelective(formValue);
		}
	}

	@Override
	public List<TeachingActivityFormValue> searchFormValue(TeachingActivityFormValue formValue) {
		TeachingActivityFormValueExample example = new TeachingActivityFormValueExample();
        TeachingActivityFormValueExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(formValue!=null){
			if(StringUtil.isNotBlank(formValue.getActivityFlow())){
				criteria.andActivityFlowEqualTo(formValue.getActivityFlow());
			}
			if(StringUtil.isNotBlank(formValue.getFormFlow())){
				criteria.andFormFlowEqualTo(formValue.getFormFlow());
			}
		}
		return formValueMapper.selectByExample(example);
	}


	@Override
	public TeachingActivityTarget readByNameNew(String orgFlow, String targetName, String targetType) {
		TeachingActivityTargetExample example = new TeachingActivityTargetExample();
        TeachingActivityTargetExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andOrgFlowEqualTo(orgFlow);
		criteria.andTargetNameEqualTo(targetName);
		criteria.andTargetTypeEqualTo(targetType);
		List<TeachingActivityTarget> list = activityTargetMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public TeachingActivityTarget readByNameNew2(String orgFlow, String targetName, String activityTypeId) {
		TeachingActivityTargetExample example = new TeachingActivityTargetExample();
        TeachingActivityTargetExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andOrgFlowEqualTo(orgFlow);
		criteria.andTargetNameEqualTo(targetName);
		criteria.andActivityTypeIdEqualTo(activityTypeId);
		List<TeachingActivityTarget> list = activityTargetMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int saveTargetNew2(TeachingActivityTarget add) {
		if (StringUtil.isNotBlank(add.getTargetFlow())) {
			GeneralMethod.setRecordInfo(add, false);
			return activityTargetMapper.updateByPrimaryKeySelective(add);
		} else {
			add.setTargetFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(add, true);
			// 根据指标类型查询最大的排序码
			int ordinal = targetExtMapper.getMaxOrdinalByTypeNew(add.getOrgFlow(),add.getActivityTypeId());
			add.setOrdinal(ordinal);
			return activityTargetMapper.insertSelective(add);
		}
	}
}
