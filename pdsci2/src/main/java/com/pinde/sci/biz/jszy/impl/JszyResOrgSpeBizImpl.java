package com.pinde.sci.biz.jszy.impl;


import com.pinde.core.common.enums.TrainCategoryTypeEnum;
import com.pinde.core.common.sci.dao.ResOrgSpeMapper;
import com.pinde.core.model.ResOrgSpe;
import com.pinde.core.model.ResOrgSpeExample;
import com.pinde.core.model.ResOrgSpeExample.Criteria;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyResOrgSpeBiz;
import com.pinde.sci.common.GeneralMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tiger
 *
 */
@Service
//@Transactional(rollbackFor=Exception.class)
public class JszyResOrgSpeBizImpl implements IJszyResOrgSpeBiz {

	@Autowired
	private ResOrgSpeMapper resOrgSpeMapper;


	@Override
	public List<ResOrgSpe> searchResOrgSpeList(ResOrgSpe resOrgSpe,String trainCategoryTypeId) {
		ResOrgSpeExample example=new ResOrgSpeExample();
		Criteria criteria =example.createCriteria();
		List<String>speTypeIdList=new ArrayList<String>();
        if (TrainCategoryTypeEnum.BeforeCfgDate.getId().equals(trainCategoryTypeId)) {
            speTypeIdList.add(com.pinde.core.common.enums.DictTypeEnum.WMFirst.getId());
		}
        if (TrainCategoryTypeEnum.AfterCfgDate.getId().equals(trainCategoryTypeId)) {
            speTypeIdList.add(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId());
		}
        if (TrainCategoryTypeEnum.Independent.getId().equals(trainCategoryTypeId)) {
            speTypeIdList.add(com.pinde.core.common.enums.DictTypeEnum.WMSecond.getId());
            speTypeIdList.add(com.pinde.core.common.enums.DictTypeEnum.AssiGeneral.getId());
		}
		if (speTypeIdList!=null && !speTypeIdList.isEmpty()) {
			criteria.andSpeTypeIdIn(speTypeIdList);
		}
		andCriteria(resOrgSpe, criteria);
		example.setOrderByClause("spe_id");
		return resOrgSpeMapper.selectByExample(example);

	}

	private void andCriteria(ResOrgSpe resOrgSpe, Criteria criteria) {
		if (StringUtil.isNotBlank(resOrgSpe.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(resOrgSpe.getOrgFlow());
		}
		if (StringUtil.isNotBlank(resOrgSpe.getOrgName())) {
			criteria.andOrgNameLike(resOrgSpe.getOrgName());
		}
		if (StringUtil.isNotBlank(resOrgSpe.getSpeTypeId())) {
			criteria.andSpeTypeIdEqualTo(resOrgSpe.getSpeTypeId());
		}
		if (StringUtil.isNotBlank(resOrgSpe.getSpeId())) {
			criteria.andSpeIdEqualTo(resOrgSpe.getSpeId());
		}
		if(StringUtil.isNotBlank(resOrgSpe.getRecordStatus())){
			criteria.andRecordStatusEqualTo(resOrgSpe.getRecordStatus());
		}
	}

	@Override
	public int saveResOrgSpe(ResOrgSpe resOrgSpe) {
		if(StringUtil.isNotBlank(resOrgSpe.getOrgSpeFlow())){
			GeneralMethod.setRecordInfo(resOrgSpe, false);
			return resOrgSpeMapper.updateByPrimaryKeySelective(resOrgSpe);
		}else{
			resOrgSpe.setOrgSpeFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(resOrgSpe, true);
			return resOrgSpeMapper.insert(resOrgSpe);
		}
	}

	@Override
	public List<ResOrgSpe> searchResOrgSpeList(ResOrgSpe resOrgSpe) {
		ResOrgSpeExample example=new ResOrgSpeExample();
		Criteria criteria =example.createCriteria();
		andCriteria(resOrgSpe, criteria);
		example.setOrderByClause("SPE_ID");
		return resOrgSpeMapper.selectByExample(example);
	}

	@Override
	public int saveOrgSpeManage(ResOrgSpe orgSpe) {
		String orgFlow = orgSpe.getOrgFlow();
		String speTypeId = orgSpe.getSpeTypeId();
		String speId = orgSpe.getSpeId();
		if(StringUtil.isNotBlank(orgFlow) && StringUtil.isNotBlank(speTypeId) && StringUtil.isNotBlank(speId)){
			ResOrgSpe exitOrgSpe = new ResOrgSpe();
			exitOrgSpe.setOrgFlow(orgFlow);
			exitOrgSpe.setSpeTypeId(speTypeId);
			exitOrgSpe.setSpeId(speId);
			List<ResOrgSpe> orgSpeList = searchResOrgSpeList(exitOrgSpe);
			if(orgSpeList != null && !orgSpeList.isEmpty()){//修改已存在的记录
				exitOrgSpe = orgSpeList.get(0);
				String recordStatus =  orgSpe.getRecordStatus();
				exitOrgSpe.setRecordStatus(recordStatus);
				exitOrgSpe.setSpeName(orgSpe.getSpeName());
				return saveResOrgSpe(exitOrgSpe);
			}else{//新增
				return saveResOrgSpe(orgSpe);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<ResOrgSpe> searchSpeByCondition(ResOrgSpe resOrgSpe, String flag) {
		ResOrgSpeExample example=new ResOrgSpeExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if (StringUtil.isNotBlank(resOrgSpe.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(resOrgSpe.getOrgFlow());
		}
		if (StringUtil.isNotBlank(resOrgSpe.getOrgName())) {
			criteria.andOrgNameLike(resOrgSpe.getOrgName());
		}
		if(StringUtil.isBlank(flag)){
			if (StringUtil.isNotBlank(resOrgSpe.getSpeTypeId())) {
				criteria.andSpeTypeIdEqualTo(resOrgSpe.getSpeTypeId());
			}
		}
		if (StringUtil.isNotBlank(resOrgSpe.getSpeId())) {
			criteria.andSpeIdEqualTo(resOrgSpe.getSpeId());
		}
		if(StringUtil.isNotBlank(resOrgSpe.getRecordStatus())){
			criteria.andRecordStatusEqualTo(resOrgSpe.getRecordStatus());
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag) && StringUtil.isNotBlank(resOrgSpe.getSpeTypeId())) {
			criteria.andSpeTypeIdNotEqualTo(resOrgSpe.getSpeTypeId());
		}
		example.setOrderByClause("CREATE_TIME DESC");
		return resOrgSpeMapper.selectByExample(example);
	}
}
