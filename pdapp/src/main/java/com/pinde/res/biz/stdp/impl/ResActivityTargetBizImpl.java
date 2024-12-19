package com.pinde.res.biz.stdp.impl;

import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.stdp.IResActivityTargetBiz;
import com.pinde.res.dao.stdp.ext.TeachingActivityInfoExtMapper;
import com.pinde.sci.dao.base.TeachingActivityFormValueMapper;
import com.pinde.sci.dao.base.TeachingActivityInfoTargetMapper;
import com.pinde.sci.dao.base.TeachingActivityTargetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResActivityTargetBizImpl implements IResActivityTargetBiz {

	@Autowired
	private TeachingActivityTargetMapper activityTargetMapper;
	@Autowired
	private TeachingActivityFormValueMapper formValueMapper;
	@Autowired
	private TeachingActivityInfoTargetMapper activityInfoTargetMapper;

	@Autowired
	private TeachingActivityInfoExtMapper activityInfoExtMapper;

	@Override
	public List<TeachingActivityTarget> list(Map<String, String> param) {
		TeachingActivityTargetExample example=new TeachingActivityTargetExample();
        TeachingActivityTargetExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(param.get("orgFlow")))
		{
			criteria.andOrgFlowEqualTo(param.get("orgFlow"));
		}
		if(StringUtil.isNotBlank(param.get("targetName")))
		{
			criteria.andTargetNameLike("%"+param.get("targetName")+"%");
		}
		example.setOrderByClause("create_time ");
		return activityTargetMapper.selectByExample(example);
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
	public int saveInfoTarget(TeachingActivityInfoTarget info, SysUser user) {
		if(StringUtil.isNotBlank(info.getRecordFlow()))
		{
			info.setModifyUserFlow(user.getUserFlow());
			info.setModifyTime(DateUtil.getCurrDateTime());
			return  activityInfoTargetMapper.updateByPrimaryKeySelective(info);
		}else{
			info.setRecordFlow(PkUtil.getUUID());
            info.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			info.setCreateUserFlow(user.getUserFlow());
			info.setCreateTime(DateUtil.getCurrDateTime());
			info.setModifyUserFlow(user.getUserFlow());
			info.setModifyTime(DateUtil.getCurrDateTime());
			return  activityInfoTargetMapper.insertSelective(info);
		}
	}

	@Override
	public TeachingActivityTarget readByFlow(String targetFlow) {
		return activityTargetMapper.selectByPrimaryKey(targetFlow);
	}

	@Override
	public List<TeachingActivityFormValue> activityFormValues(String activityFlow) {
		TeachingActivityFormValueExample example = new TeachingActivityFormValueExample();
		TeachingActivityFormValueExample.Criteria criteria = example.createCriteria().
                andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
criteria.andActivityFlowEqualTo(activityFlow);
		return formValueMapper.selectByExample(example);
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
	public List<String> selectJointOrgFlow(String userFlow) {

		return activityTargetMapper.selectJointOrgFlow(userFlow);

	}

	@Override
	public List<String> selectMainOrgFlow(String userFlow) {

		return activityTargetMapper.selectMainOrgFlow(userFlow);

	}

	@Override
	public List<TeachingActivityTarget> readByOrgNewNoStatus(String activityTypeId,String orgFlow) {
		TeachingActivityTargetExample example=new TeachingActivityTargetExample();
		TeachingActivityTargetExample.Criteria criteria = example.createCriteria();
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


}
