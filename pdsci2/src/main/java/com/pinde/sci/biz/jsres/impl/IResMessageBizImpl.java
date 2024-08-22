package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.sci.biz.jsres.IResMessageBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.ctrl.sch.plan.util.StringUtil;
import com.pinde.sci.dao.base.ResMessageMapper;
import com.pinde.sci.dao.res.ResMessageExtMapper;
import com.pinde.sci.model.mo.ResMessage;
import com.pinde.sci.model.mo.ResMessageExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class IResMessageBizImpl implements IResMessageBiz {

	@Autowired
	private ResMessageMapper messageMapper;
	@Autowired
	private ResMessageExtMapper resMessageExtMapper;

	@Override
	public List<ResMessage> searchMessageByOrg(String orgFlow,String messageTitle) {
		ResMessageExample example = new ResMessageExample();
		ResMessageExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("MODIFY_TIME DESC");
		criteria.andRecordStatusEqualTo("Y");
		if(StringUtil.isNotBlank(orgFlow)){
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		if(StringUtil.isNotBlank(messageTitle)){
			criteria.andMessageTitleLike("%"+messageTitle+"%");
		}
		return messageMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public ResMessage findMessageByFlow(String messageFlow) {
		ResMessageExample example = new ResMessageExample();
		ResMessageExample.Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo("Y");
		if(StringUtil.isNotBlank(messageFlow)){
			criteria.andMessageFlowEqualTo(messageFlow);
			return messageMapper.selectByPrimaryKey(messageFlow);
		}
		return null;
	}

	@Override
	public List<ResMessage> findMessageByOrgFlow(String orgFlow,String sessionNumber) {
		if(StringUtils.isBlank(orgFlow)) return Collections.EMPTY_LIST;
		return resMessageExtMapper.findMessageByOrgFlow(orgFlow,sessionNumber);
	}


	@Override
	public int editMessage(ResMessage message) {
		int i = 0;
		if(StringUtil.isBlank(message.getMessageFlow())){
			message.setMessageFlow(PkUtil.getUUID());
			message.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			message.setOrgName(GlobalContext.getCurrentUser().getOrgName());
			GeneralMethod.setRecordInfo(message, true);
			i = messageMapper.insert(message);
		}else{
			GeneralMethod.setRecordInfo(message, false);
			i = messageMapper.updateByPrimaryKeySelective(message);
		}
		return i;
	}

	@Override
	public int delMessage(String messageFlow) {
		ResMessage record = new ResMessage();
		record.setMessageFlow(messageFlow);
		record.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		GeneralMethod.setRecordInfo(record, false);
		return this.messageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<ResMessage> findMessage(ResMessage message) {
		ResMessageExample example = new ResMessageExample();
		example.setOrderByClause("MODIFY_TIME DESC");
		ResMessageExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return messageMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<Map<String, Object>> findMessageOrgAndCount(String sessionNumber) {
		return resMessageExtMapper.findMessageOrgAndCount(sessionNumber);
	}

	@Override
	public List<Map<String, Object>> findMessageOrgAndTime(String time) {
		return resMessageExtMapper.findMessageOrgAndTime(time);
	}
}
