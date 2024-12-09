package com.pinde.sci.biz.inx.impl;


import com.pinde.core.common.enums.InfoStatusEnum;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.InxInfoMapper;
import com.pinde.sci.dao.base.ResInfoRoleMapper;
import com.pinde.sci.dao.base.ResReadInfoMapper;
import com.pinde.sci.dao.inx.InxInfoExtMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.InxInfoExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
	@Autowired
	private ResReadInfoMapper resReadInfoMapper;
	
	@Override
	public int save(InxInfo info) {
        info.setColumnId(com.pinde.core.common.GlobalConstant.RES_NOTICE_TYPE5_ID);
        info.setColumnName(com.pinde.core.common.GlobalConstant.RES_NOTICE_TYPE5_NAME);
		return editInfo(info, "","");
	}
	
	@Override
	public int editInfo(InxInfo info, String infoRoleFlows, String sessionNumber) {
		int i=0;
		if(StringUtil.isBlank(info.getInfoFlow())){
			info.setInfoFlow(PkUtil.getUUID());
			info.setInfoStatusId(InfoStatusEnum.Passed.getId());
			info.setInfoStatusName(InfoStatusEnum.Passed.getName());
			info.setViewNum(new Long(0));
			info.setInfoTime(DateUtil.getCurrDateTime());
			GeneralMethod.setRecordInfo(info, true);
			i= this.inxInfoMapper.insert(info);
		}else{
			GeneralMethod.setRecordInfo(info, false);
			i= this.inxInfoMapper.updateByPrimaryKeySelective(info);
		}
		List<String> roleFlows=null;
		if(StringUtil.isNotBlank(infoRoleFlows))
		{
			roleFlows= Arrays.asList(infoRoleFlows.split(","));
		}
		inxInfoExtMapper.deleteInfoRole(info,roleFlows);

		if(roleFlows!=null)
		{
			for(String roleFlow:roleFlows)
			{
				ResInfoRole infoRole=null;
				if(StringUtil.isNotBlank(info.getInfoFlow())) {
					infoRole=readInfoRole(info.getInfoFlow(), roleFlow);
				}
				if(infoRole==null){
					infoRole=new ResInfoRole();
				}
				//存在发送设置年级学员角色通知
				if(StringUtil.isNotBlank(sessionNumber)){
					infoRole.setSessionNumber(sessionNumber);
				}
                infoRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				infoRole.setInfoFlow(info.getInfoFlow());
				infoRole.setInfoRole(roleFlow);
				saveInfoRole(infoRole);
			}
		}
		return i;
	}

	private int saveInfoRole(ResInfoRole infoRole) {
		if(infoRole != null){
			if(StringUtil.isNotBlank(infoRole.getRecordFlow())){
				GeneralMethod.setRecordInfo(infoRole, false);
				return resInfoRoleoMapper.updateByPrimaryKeySelective(infoRole);
			}else{
				infoRole.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(infoRole, true);
				return resInfoRoleoMapper.insertSelective(infoRole);
			}
		}
		return 0;
	}

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
        example.createCriteria().andInfoFlowEqualTo(infoFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		return resInfoRoleoMapper.selectByExample(example);
	}

	@Override
	public List<InxInfo> findNoticeWithBLOBsNotHaveRole(InxInfo info) {
		return inxInfoExtMapper.findNoticeWithBLOBsNotHaveRole(info);
	}

	@Override
	public int saveReadInfo(String userFlow, String infoFlow) {

		ResReadInfo resReadInfo=getReadInfoByFlow(infoFlow,userFlow);
		if(resReadInfo==null)
		{
			resReadInfo=new ResReadInfo();
			resReadInfo.setInfoFlow(infoFlow);
			resReadInfo.setUserFlow(userFlow);
			resReadInfo.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(resReadInfo,true);
			return resReadInfoMapper.insertSelective(resReadInfo);
		}
		return 0;
	}

	public ResReadInfo getReadInfoByFlow(String infoFlow, String userFlow) {
		ResReadInfoExample example=new ResReadInfoExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andInfoFlowEqualTo(infoFlow).andUserFlowEqualTo(userFlow);
		List<ResReadInfo> list=resReadInfoMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<InxInfo> findNotice(InxInfo info) {
		InxInfoExample example = new InxInfoExample();
		example.setOrderByClause("CREATE_TIME DESC");
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(info.getColumnId())) {
			criteria.andColumnIdEqualTo(info.getColumnId());
		}
		if (StringUtil.isNotBlank(info.getRoleFlow())) {
			criteria.andRoleFlowEqualTo(info.getRoleFlow());
		}
        criteria.andInfoStatusIdNotEqualTo(InfoStatusEnum.Failure.getId());
		return inxInfoMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public List<InxInfo> searchNotice(InxInfo info) {
		InxInfoExample example = new InxInfoExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
//		example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andInfoTimeGreaterThanOrEqualTo(beforeSevenDay);
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
        record.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		GeneralMethod.setRecordInfo(record, false);
		return this.inxInfoMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public List<InxInfo> findNoticeWithBLOBs(InxInfo info) {
		InxInfoExample example = new InxInfoExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(info.getColumnId())) {
			criteria.andColumnIdEqualTo(info.getColumnId());
		}
		if (StringUtil.isNotBlank(info.getRoleFlow())) {
			criteria.andRoleFlowEqualTo(info.getRoleFlow());
		}
        criteria.andInfoStatusIdNotEqualTo(InfoStatusEnum.Failure.getId());
		example.setOrderByClause("INFO_TIME DESC");
		return inxInfoMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<InxInfo> searchInfoByOrgBeforeDate(String orgFlow, String date, String resNoticeTypeId, String resSysId, String userFlow, String sessionNumber){
		return inxInfoExtMapper.searchInfoByOrgBeforeDate(orgFlow,date,resNoticeTypeId,resSysId,userFlow,sessionNumber);
	}
	@Override
	public List<InxInfo> searchInfoByOrg(String orgFlow, String resNoticeTypeId, String resSysId ){
		return inxInfoExtMapper.searchInfoByOrg(orgFlow,resNoticeTypeId,resSysId);
	}
	@Override
	public List<InxInfo> searchInfoByOrgNoRoleFlow(String orgFlow, String resNoticeTypeId, String resSysId ){
		return inxInfoExtMapper.searchInfoByOrgNoRoleFlow(orgFlow,resNoticeTypeId,resSysId);
	}

	@Override
	public List<InxInfo> searchInfoByOrg(Map<String, String> param) {
		return inxInfoExtMapper.searchInfoByParam(param);
	}

	@Override
	public List<InxInfo> searchInfoNotRead(String orgFlow,  String resNoticeTypeId, String resSysId, String userFlow, String sessionNumber){
		return inxInfoExtMapper.searchInfoNotRead(orgFlow,resNoticeTypeId,resSysId,userFlow,sessionNumber);
	}
}
