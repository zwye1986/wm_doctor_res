package com.pinde.sci.biz.gyxjgl.impl;


import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gyxjgl.IGyXjNoticeBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.EduTeachingnoticeMapper;
import com.pinde.sci.dao.base.FeeNoticeConfigMapper;
import com.pinde.sci.dao.base.FeeNoticeTemplateMapper;
import com.pinde.sci.dao.gyxjgl.GyXjEduNoticeExtMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class GyXjNoticeBizImpl implements IGyXjNoticeBiz {

	@Autowired
	private EduTeachingnoticeMapper inxInfoMapper;
	@Autowired
	private GyXjEduNoticeExtMapper eduNoticeExtMapper;
	@Autowired
	private FeeNoticeConfigMapper configMapper;
	@Autowired
	private FeeNoticeTemplateMapper templateMapper;
	
	@Override
	public int editNotice(EduTeachingnotice teachingnotice) {
		if(StringUtil.isBlank(teachingnotice.getInfoFlow())){
			teachingnotice.setInfoFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(teachingnotice, true);
			return this.inxInfoMapper.insert(teachingnotice);
		}else{
			GeneralMethod.setRecordInfo(teachingnotice, false);
			return this.inxInfoMapper.updateByPrimaryKeySelective(teachingnotice);
		}
	}

	@Override
	public List<EduTeachingnotice> findNotice(EduTeachingnotice teachingnotice) {
		EduTeachingnoticeExample example = new EduTeachingnoticeExample();
		example.setOrderByClause("CREATE_TIME DESC");
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return inxInfoMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public EduTeachingnotice findNoticByFlow(String flow) {
		return this.inxInfoMapper.selectByPrimaryKey(flow);
	}

	@Override
	public int delNotice(String flow) {
		EduTeachingnotice record = new EduTeachingnotice();
		record.setInfoFlow(flow);
		record.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		GeneralMethod.setRecordInfo(record, false);
		return this.inxInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<EduTeachingnotice> searchAllNotice(){
		EduTeachingnoticeExample example = new EduTeachingnoticeExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("INFO_TYPE_ID,CREATE_TIME DESC");
		return inxInfoMapper.selectByExample(example);
	}

	@Override
	public List<EduTeachingnotice> searchByRoles(String userFlow, String infoTypeId) {
		return eduNoticeExtMapper.searchByRoles(userFlow,infoTypeId);
	}

	@Override
	public Map<String, Object> searchAgencyThing() {
		return eduNoticeExtMapper.searchAgencyThing();
	}

	@Override
	public void sendPaidFeeNotice(String userFlow, String noticeFlag) {

	}

	@Override
	public List<FeeNoticeTemplate> searchAllFeeNotice() {
		FeeNoticeTemplateExample example = new FeeNoticeTemplateExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("PUBLISH_TIME DESC,CREATE_TIME DESC");
		return templateMapper.selectByExample(example);
	}

	@Override
	public List<FeeNoticeTemplate> searchFeeNotice(String paidFee) {
		if(StringUtil.isNotBlank(paidFee)){
			FeeNoticeTemplateExample example = new FeeNoticeTemplateExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andPublishSwitchEqualTo(GlobalConstant.FLAG_Y).andReceiverFlagEqualTo(paidFee);
			return templateMapper.selectByExampleWithBLOBs(example);
		}
		return null;
	}

	@Override
	public int saveFeeTemplate(FeeNoticeTemplate template) {
		if(StringUtil.isBlank(template.getRecordFlow())){
			template.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(template,true);
			return templateMapper.insertSelective(template);
		}else{
			GeneralMethod.setRecordInfo(template,false);
			return templateMapper.updateByPrimaryKeySelective(template);
		}
	}

	@Override
	public FeeNoticeTemplate findFeeNoticByFlow(String recordFlow) {
		return templateMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public int delFeeNotice(String recordFlow) {
		return templateMapper.deleteByPrimaryKey(recordFlow);
	}

	@Override
	public FeeNoticeConfig queryFeeConfig() {
		FeeNoticeConfigExample example = new FeeNoticeConfigExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<FeeNoticeConfig> dataList = configMapper.selectByExample(example);
		return dataList== null || dataList.isEmpty()?null:dataList.get(0);
	}

	@Override
	public int saveFeeConfig(FeeNoticeConfig config) {
		if(StringUtil.isBlank(config.getRecordFlow())){
			config.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(config,true);
			return configMapper.insertSelective(config);
		}else{
			GeneralMethod.setRecordInfo(config,false);
			return configMapper.updateByPrimaryKeySelective(config);
		}
	}
}
