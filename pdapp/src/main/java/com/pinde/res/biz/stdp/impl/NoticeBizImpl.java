package com.pinde.res.biz.stdp.impl;


import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.stdp.INoticeBiz;
import com.pinde.res.dao.lcjn.ext.InxInfoExtMapper;
import com.pinde.sci.dao.base.InxInfoMapper;
import com.pinde.sci.dao.base.ResInfoRoleMapper;
import com.pinde.core.model.InxInfo;
import com.pinde.core.model.InxInfoExample;
import com.pinde.core.model.InxInfoExample.Criteria;
import com.pinde.core.model.ResInfoRole;
import com.pinde.core.model.ResInfoRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class NoticeBizImpl implements INoticeBiz {

	@Autowired
	private InxInfoMapper inxInfoMapper;
	@Autowired
	private ResInfoRoleMapper resInfoRoleoMapper;
	@Autowired
	private InxInfoExtMapper inxInfoExtMapper;


	private ResInfoRole readInfoRole(String infoFlow, String roleFlow) {
		ResInfoRoleExample example=new ResInfoRoleExample();
		example.createCriteria().andInfoFlowEqualTo(infoFlow).andInfoRoleEqualTo(roleFlow);
		List<ResInfoRole> list=resInfoRoleoMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}
	@Override
	public	List<ResInfoRole> readRoleByFlow(String infoFlow) {
		ResInfoRoleExample example=new ResInfoRoleExample();
		example.createCriteria().andInfoFlowEqualTo(infoFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return resInfoRoleoMapper.selectByExample(example);
	}

	@Override
	public List<InxInfo> findNoticeWithBLOBsNotHaveRole(InxInfo info) {
		return inxInfoExtMapper.findNoticeWithBLOBsNotHaveRole(info);
	}

	@Override
	public List<InxInfo> findNotice(InxInfo info) {
		InxInfoExample example = new InxInfoExample();
		example.setOrderByClause(" IS_TOP DESC, MODIFY_TIME DESC, INFO_TIME DESC");
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(info.getColumnId())) {
			criteria.andColumnIdEqualTo(info.getColumnId());
		}
		if (StringUtil.isNotBlank(info.getRoleFlow())) {
			criteria.andRoleFlowEqualTo(info.getRoleFlow());
		}
		return inxInfoMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public List<InxInfo> searchNotice(InxInfo info) {
		InxInfoExample example = new InxInfoExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("INFO_TIME DESC");
		if (StringUtil.isNotBlank(info.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(info.getOrgFlow());
		}
		if (StringUtil.isNotBlank(info.getColumnId())) {
			criteria.andColumnIdEqualTo(info.getColumnId());
		}
		if (StringUtil.isNotBlank(info.getRoleFlow())) {
			criteria.andRoleFlowEqualTo(info.getRoleFlow());
		}
		return inxInfoMapper.selectByExample(example);
	}

//	@Override
//	public List<InxInfo> searchSevenDaysNotice(InxInfo info) {
//		String beforeSevenDay = DateUtil.addHour(DateUtil.getCurrDateTime(),-7*24).substring(0,8);
//
//		InxInfoExample example = new InxInfoExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andInfoTimeGreaterThanOrEqualTo(beforeSevenDay);
//		example.setOrderByClause("INFO_TIME DESC");
//		return inxInfoMapper.selectByExample(example);
//	}

	@Override
	public InxInfo findNoticByFlow(String flow) {
		return this.inxInfoMapper.selectByPrimaryKey(flow);
	}

	@Override
	public int delNotice(String flow) {
		InxInfo record = new InxInfo();
		record.setInfoFlow(flow);
		record.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		return this.inxInfoMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public List<InxInfo> findNoticeWithBLOBs(InxInfo info) {
		InxInfoExample example = new InxInfoExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(info.getColumnId())) {
			criteria.andColumnIdEqualTo(info.getColumnId());
		}
		if (StringUtil.isNotBlank(info.getRoleFlow())) {
			criteria.andRoleFlowEqualTo(info.getRoleFlow());
		}
		example.setOrderByClause("INFO_TIME DESC");
		return inxInfoMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<Map<String,String>> searchInfoByOrgBeforeDate(String orgFlow, String date, String resNoticeTypeId, String resSysId, String userFlow,String sessionNumber){
		return inxInfoExtMapper.searchInfoByOrgBeforeDate(orgFlow,date,resNoticeTypeId,resSysId,userFlow,sessionNumber);
	}
	@Override
	public List<Map<String,String>> searchInfoByOrgNotRead(String orgFlow,  String resNoticeTypeId, String resSysId, String userFlow){
		return inxInfoExtMapper.searchInfoByOrgNotRead(orgFlow,resNoticeTypeId,resSysId,userFlow);
	}

}
