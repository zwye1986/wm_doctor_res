package com.pinde.sci.biz.jsres.impl;


import com.pinde.core.common.sci.dao.*;
import com.pinde.core.model.*;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResMonthlyReportBiz;
import com.pinde.sci.dao.jsres.MonthlyReportExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JsResMonthlyReportBizImpl implements IJsResMonthlyReportBiz{
	@Autowired
	private SysMonthlyMapper sysMonthlyMapper;
	@Autowired
	private SysMonthlyDoctorInfoMapper sysMonthlyDoctorInfoMapper;
	@Autowired
	private SysMonthlyChangeInfoMapper sysMonthlyChangeInfoMapper;
	@Autowired
	private SysMonthlyReturnDelayInfoMapper sysMonthlyReturnDelayInfoMapper;
	@Autowired
	private MonthlyReportExtMapper monthlyReportExtMapper;
	@Autowired
	private SysMonthlyDeptCycleInfoMapper sysMonthlyDeptCycleInfoMapper;
	@Autowired
	private SysMonthlyDocCycleInfoMapper sysMonthlyDocCycleInfoMapper;
	@Autowired
	private SysMonthlyDoctorDetailInfoMapper sysMonthlyDoctorDetailInfoMapper;
	@Autowired
	private SysMonthlyActivityInfoMapper sysMonthlyActivityInfoMapper;
	@Autowired
	private SysMonthlyBlacklistInfoMapper sysMonthlyBlacklistInfoMapper;

	@Override
	public List<SysMonthly> getMonths(SysMonthly monthly) {
		SysMonthlyExample example = new SysMonthlyExample();
        SysMonthlyExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		return sysMonthlyMapper.selectByExample(example);
	}

	@Override
	public List<SysMonthlyDoctorInfo> getMonthlyDoctorInfo(SysMonthlyDoctorInfo sysMonthlyDoctorInfo,List<String> allOrgFlow) {
		SysMonthlyDoctorInfoExample example = new SysMonthlyDoctorInfoExample();
        SysMonthlyDoctorInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(sysMonthlyDoctorInfo!=null){
			if(StringUtil.isNotBlank(sysMonthlyDoctorInfo.getDateMonth())){
				criteria.andDateMonthEqualTo(sysMonthlyDoctorInfo.getDateMonth());
			}
			if(StringUtil.isNotBlank(sysMonthlyDoctorInfo.getDoctorTypeId())){
				if("Graduate".equals(sysMonthlyDoctorInfo.getDoctorTypeId())){
					criteria.andDoctorTypeIdEqualTo("Graduate");
				}else{
					criteria.andDoctorTypeIdNotEqualTo("Graduate");
				}
			}
			if(StringUtil.isNotBlank(sysMonthlyDoctorInfo.getDoctorStatusId())){
				criteria.andDoctorStatusIdEqualTo(sysMonthlyDoctorInfo.getDoctorStatusId());
			}
			if(StringUtil.isNotBlank(sysMonthlyDoctorInfo.getChangeTypeId())){
				criteria.andChangeTypeIdEqualTo(sysMonthlyDoctorInfo.getChangeTypeId());
			}
			if(StringUtil.isNotBlank(sysMonthlyDoctorInfo.getOrgFlow())){
				criteria.andOrgFlowEqualTo(sysMonthlyDoctorInfo.getOrgFlow());
			}
			if(allOrgFlow!=null&&allOrgFlow.size()>0){
				criteria.andOrgFlowIn(allOrgFlow);
			}
			if(StringUtil.isNotBlank(sysMonthlyDoctorInfo.getSessionNumber())){
				criteria.andSessionNumberEqualTo(sysMonthlyDoctorInfo.getSessionNumber());
			}
		}
		return sysMonthlyDoctorInfoMapper.selectByExample(example);
	}

	@Override
	public List<SysMonthlyDoctorInfo> getMonthlyDoctorInfo2(SysMonthlyDoctorInfo sysMonthlyDoctorInfo) {
		return sysMonthlyDoctorInfoMapper.getMonthlyDoctorInfo2(sysMonthlyDoctorInfo);
	}

	@Override
	public List<SysMonthlyDoctorDetailInfo> getMonthlyDoctorDetailInfo(SysMonthlyDoctorDetailInfo sysMonthlyDoctorDetailInfo) {
		SysMonthlyDoctorDetailInfoExample example = new SysMonthlyDoctorDetailInfoExample();
        SysMonthlyDoctorDetailInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(sysMonthlyDoctorDetailInfo!=null){
			if(StringUtil.isNotBlank(sysMonthlyDoctorDetailInfo.getDateMonth())){
				criteria.andDateMonthEqualTo(sysMonthlyDoctorDetailInfo.getDateMonth());
			}
		}
		return sysMonthlyDoctorDetailInfoMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<SysMonthlyChangeInfo> getSysMonthlyChangeInfo(SysMonthlyChangeInfo sysMonthlyChangeInfo) {
		SysMonthlyChangeInfoExample example = new SysMonthlyChangeInfoExample();
        SysMonthlyChangeInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(sysMonthlyChangeInfo!=null){
			if(StringUtil.isNotBlank(sysMonthlyChangeInfo.getDateMonth())){
				criteria.andDateMonthEqualTo(sysMonthlyChangeInfo.getDateMonth());
			}
			if(StringUtil.isNotBlank(sysMonthlyChangeInfo.getDoctorTypeId())){
				if("Graduate".equals(sysMonthlyChangeInfo.getDoctorTypeId())){
					criteria.andDoctorTypeIdEqualTo("Graduate");
				}else{
					criteria.andDoctorTypeIdNotEqualTo("Graduate");
				}
			}
		}
		return sysMonthlyChangeInfoMapper.selectByExample(example);
	}

	@Override
	public List<SysMonthlyReturnDelayInfo> getSysMonthlyReturnDelayInfo(SysMonthlyReturnDelayInfo sysMonthlyReturnDelayInfo) {
		SysMonthlyReturnDelayInfoExample example = new SysMonthlyReturnDelayInfoExample();
        SysMonthlyReturnDelayInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(sysMonthlyReturnDelayInfo!=null){
			if(StringUtil.isNotBlank(sysMonthlyReturnDelayInfo.getDateMonth())){
				criteria.andDateMonthEqualTo(sysMonthlyReturnDelayInfo.getDateMonth());
			}
			if(StringUtil.isNotBlank(sysMonthlyReturnDelayInfo.getDoctorTypeId())){
				if("Graduate".equals(sysMonthlyReturnDelayInfo.getDoctorTypeId())){
					criteria.andDoctorTypeIdEqualTo("Graduate");
				}else{
					criteria.andDoctorTypeIdNotEqualTo("Graduate");
				}
			}
		}
		return sysMonthlyReturnDelayInfoMapper.selectByExample(example);
	}

	@Override
	public List<SysMonthlyDeptCycleInfo> getSysMonthlyDeptCycleInfo(SysMonthlyDeptCycleInfo sysMonthlyDeptCycleInfo) {
		SysMonthlyDeptCycleInfoExample example = new SysMonthlyDeptCycleInfoExample();
        SysMonthlyDeptCycleInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(sysMonthlyDeptCycleInfo!=null){
			if(StringUtil.isNotBlank(sysMonthlyDeptCycleInfo.getDateMonth())){
				criteria.andDateMonthEqualTo(sysMonthlyDeptCycleInfo.getDateMonth());
			}
		}
		return sysMonthlyDeptCycleInfoMapper.selectByExample(example);
	}

	@Override
	public Map<String, Object> getChart6Data(Map<String,Object> paramMap) {
		Map<String,Object> resultMap = new HashMap<>();
		//参加理论考核人数
		int participateInNum = monthlyReportExtMapper.getParticipateInNum(paramMap);
		//理论考试总次数
		int theoryAll = monthlyReportExtMapper.getTheoryAll(paramMap);
		//合格人数
		int passNum = monthlyReportExtMapper.getPassNum(paramMap);
		//不合格人数
		int notPassNum = monthlyReportExtMapper.getNotPassNum(paramMap);
		resultMap.put("participateInNum",participateInNum);
		resultMap.put("theoryAll",theoryAll);
		resultMap.put("passNum",passNum);
		resultMap.put("notPassNum",notPassNum);
		return resultMap;
	}

	@Override
	public List<SysMonthlyDocCycleInfo> getSysMonthlyDocCycleInfo(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo) {
		SysMonthlyDocCycleInfoExample example = new SysMonthlyDocCycleInfoExample();
        SysMonthlyDocCycleInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(sysMonthlyDocCycleInfo!=null){
			if(StringUtil.isNotBlank(sysMonthlyDocCycleInfo.getDateMonth())){
				criteria.andDateMonthEqualTo(sysMonthlyDocCycleInfo.getDateMonth());
			}
			if(StringUtil.isNotBlank(sysMonthlyDocCycleInfo.getDoctorTypeId())){
				if("Graduate".equals(sysMonthlyDocCycleInfo.getDoctorTypeId())){
					criteria.andDoctorTypeIdEqualTo("Graduate");
				}else{
					criteria.andDoctorTypeIdNotEqualTo("Graduate");
				}
			}
			if(StringUtil.isNotBlank(sysMonthlyDocCycleInfo.getOrgFlow())){
				criteria.andOrgFlowEqualTo(sysMonthlyDocCycleInfo.getOrgFlow());
			}
		}
		return sysMonthlyDocCycleInfoMapper.selectByExample(example);
	}

	@Override
	public Map<String, Object> getChart9Data(Map<String, Object> paramMap) {
		Map<String,Object> resultMap = new HashMap<>();
		int activitySum = monthlyReportExtMapper.getActivitySum(paramMap);
		resultMap.put("activitySum",activitySum);
		List<Map<String,Object>> dept1List = monthlyReportExtMapper.getActivityMostDept(paramMap);
		List<Map<String,Object>> dept2List = monthlyReportExtMapper.getActivityLeastDept(paramMap);
		List<Map<String,Object>> dept1FinalList = new ArrayList<>();
		List<Map<String,Object>> dept2FinalList = new ArrayList<>();
		if(!dept1List.isEmpty()){
			BigDecimal num = (BigDecimal)dept1List.get(0).get("ACTIVITYSUM");
			for(Map<String,Object> map:dept1List){
				if(num.compareTo((BigDecimal)map.get("ACTIVITYSUM"))==0){
					dept1FinalList.add(map);
				}else {
					break;
				}
			}
		}
		if(!dept2List.isEmpty()){
			BigDecimal num = (BigDecimal)dept2List.get(0).get("ACTIVITYSUM");
			for(Map<String,Object> map:dept2List){
				if(num.compareTo((BigDecimal)map.get("ACTIVITYSUM"))==0){
					dept2FinalList.add(map);
				}else {
					break;
				}
			}
		}
		resultMap.put("dept1FinalList",dept1FinalList);
		resultMap.put("dept2FinalList",dept2FinalList);
		List<Map<String,String>> activityByType = monthlyReportExtMapper.getActivityByType(paramMap);
		resultMap.put("activityByType",activityByType);
		return resultMap;
	}

	@Override
	public Map<String, Object> getChart9Data2(Map<String, Object> paramMap) {
		Map<String,Object> resultMap = new HashMap<>();
		int activitySum = monthlyReportExtMapper.getActivitySum(paramMap);
		resultMap.put("activitySum",activitySum);
		List<Map<String,Object>> dept1List = monthlyReportExtMapper.getActivityMostOrg(paramMap);
		List<Map<String,Object>> dept2List = monthlyReportExtMapper.getActivityLeastOrg(paramMap);
		List<Map<String,Object>> dept1FinalList = new ArrayList<>();
		List<Map<String,Object>> dept2FinalList = new ArrayList<>();
		if(!dept1List.isEmpty()){
			BigDecimal num = (BigDecimal)dept1List.get(0).get("ACTIVITYSUM");
			for(Map<String,Object> map:dept1List){
				if(num.compareTo((BigDecimal)map.get("ACTIVITYSUM"))==0){
					dept1FinalList.add(map);
				}else {
					break;
				}
			}
		}
		if(!dept2List.isEmpty()){
			BigDecimal num = (BigDecimal)dept2List.get(0).get("ACTIVITYSUM");
			for(Map<String,Object> map:dept2List){
				if(num.compareTo((BigDecimal)map.get("ACTIVITYSUM"))==0){
					dept2FinalList.add(map);
				}else {
					break;
				}
			}
		}
		resultMap.put("dept1FinalList",dept1FinalList);
		resultMap.put("dept2FinalList",dept2FinalList);
		List<Map<String,String>> activityByType = monthlyReportExtMapper.getActivityByType(paramMap);
		resultMap.put("activityByType",activityByType);
		return resultMap;
	}

	@Override
	public Map<String, Object> getUniverseChart5Data(Map<String, Object> paramMap) {
		Map<String,Object> resultMap = new HashMap<>();
		int inNum = monthlyReportExtMapper.findInNum(paramMap);
		int outNum = monthlyReportExtMapper.findOutNum(paramMap);
		int errorOutNum = monthlyReportExtMapper.findErrorOutNum(paramMap);
		resultMap.put("入科人数",inNum);
		resultMap.put("出科人数",outNum);
		resultMap.put("出科异常人数",errorOutNum);
		return resultMap;
	}

	@Override
	public List<SysMonthlyActivityInfo> getSysMonthlyActivityInfo(String dateMonth, List<String> orgFlows) {
		SysMonthlyActivityInfoExample example = new SysMonthlyActivityInfoExample();
        SysMonthlyActivityInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(dateMonth)){
			criteria.andDateMonthEqualTo(dateMonth);
		}
		if(!orgFlows.isEmpty()){
			criteria.andOrgFlowIn(orgFlows);
		}
		return sysMonthlyActivityInfoMapper.selectByExample(example);
	}

	@Override
	public List<SysMonthlyBlacklistInfo> getBlacklist(SysMonthlyBlacklistInfo blacklistInfo) {
		SysMonthlyBlacklistInfoExample example = new SysMonthlyBlacklistInfoExample();
		SysMonthlyBlacklistInfoExample.Criteria criteria = example.createCriteria();
		if(StringUtil.isNotBlank(blacklistInfo.getDateMonth())){
			criteria.andDateMonthEqualTo(blacklistInfo.getDateMonth());
		}
		if(StringUtil.isNotBlank(blacklistInfo.getSessionNumber())){
			criteria.andSessionNumberEqualTo(blacklistInfo.getSessionNumber());
		}
		if(StringUtil.isNotBlank(blacklistInfo.getRecordStatus())){
			criteria.andRecordStatusEqualTo(blacklistInfo.getRecordStatus());
		}
        if(StringUtil.isNotBlank(blacklistInfo.getOrgFlow())){
            criteria.andOrgFlowEqualTo(blacklistInfo.getOrgFlow());
        }
		List<String> typeList = new ArrayList<>();
        typeList.add(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
        typeList.add(com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId());
		criteria.andTrainingTypeIdIn(typeList);
		return sysMonthlyBlacklistInfoMapper.selectByExample(example);
	}
}
