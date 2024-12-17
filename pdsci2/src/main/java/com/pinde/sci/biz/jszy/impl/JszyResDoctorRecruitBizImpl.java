package com.pinde.sci.biz.jszy.impl;


import com.pinde.core.common.enums.JsResTrainYearEnum;
import com.pinde.core.common.enums.JszyBaseStatusEnum;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyResDoctorRecruitBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.jszy.JszyResDoctorRecruitExtMapper;
import com.pinde.sci.dao.jszy.JszyResRecruitDoctorInfoExtMapper;
import com.pinde.sci.model.jszy.JszyDoctorInfoExt;
import com.pinde.sci.model.jszy.JszyResDoctorRecruitExt;
import com.pinde.core.model.ResDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JszyResDoctorRecruitBizImpl implements IJszyResDoctorRecruitBiz {
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private ResDoctorRecruitMapper doctorRecruitMapper;
    @Autowired
    private JszyResRecruitDoctorInfoExtMapper jszyResDoctorRecruitInfoExtMapper;
    @Autowired
    private JsresRecruitDocInfoMapper jsResDoctorRecruitMapper;
    @Autowired
    private JsresRecruitInfoMapper recruitInfoMapper;
    @Autowired
	private JszyResDoctorRecruitExtMapper jszyResDoctorRecruitExtMapper;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private SchRotationMapper rotationMapper;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private ResBaseMapper resBaseMapper;
	@Autowired
	private IUserBiz userBiz;

	/**
	 * 根据recruitFlow获取相对应的一条记录
	 */
	@Override
	public ResDoctorRecruit readResDoctorRecruit(String recruitFlow) {
		if (StringUtil.isNotBlank(recruitFlow)) {
			return doctorRecruitMapper.selectByPrimaryKey(recruitFlow);
		}
		return null;
	}


	/**
	 * 查询当前机构下医师
	 */
	@Override
	public List<JszyResDoctorRecruitExt> resDoctorRecruitExtList(ResDoctorRecruit resDoctorRecruit, SysUser user, List<String>orgFlowList) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("orgFlowList", orgFlowList);
		List<JszyResDoctorRecruitExt> doctorRecruitList=jszyResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<JszyResDoctorRecruitExt> resDoctorRecruitExtList(Map<String, Object> param) {
		return jszyResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList(param);
	}
	/**
	 * 修改
	 */
	@Override
	public int saveDoctorRecruit(ResDoctorRecruitWithBLOBs docRecWithBLOBs) {
		if(StringUtil.isNotBlank(docRecWithBLOBs.getRecruitFlow())){
			GeneralMethod.setRecordInfo(docRecWithBLOBs, false);
			return doctorRecruitMapper.updateByPrimaryKeySelective(docRecWithBLOBs);
		}else{
			docRecWithBLOBs.setRecruitFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(docRecWithBLOBs, true);
			return doctorRecruitMapper.insert(docRecWithBLOBs);
		}
	}

	@Override
	public List<com.pinde.core.model.ResDoctorRecruit> searchResDoctorRecruitList(com.pinde.core.model.ResDoctorRecruit recruit, String orderByClause) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		ResDoctorRecruitExample.Criteria criteria = example.createCriteria();
		if(StringUtil.isNotBlank(recruit.getDoctorFlow())){
			 criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
		}
		if(StringUtil.isNotBlank(recruit.getCatSpeId())){
			criteria.andCatSpeIdEqualTo(recruit.getCatSpeId());
		}
		if(StringUtil.isNotBlank(recruit.getAuditStatusId())){
			criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
		}
		if(StringUtil.isNotBlank(recruit.getRecordStatus())){
			criteria.andRecordStatusEqualTo(recruit.getRecordStatus());
		}
		if(StringUtil.isNotBlank(orderByClause)){
			example.setOrderByClause(orderByClause);
		}
		if(StringUtil.isNotBlank(recruit.getRecruitFlow())){
			criteria.andRecruitFlowEqualTo(recruit.getRecruitFlow());
		}
		return doctorRecruitMapper.selectByExample(example);
	}

    @Override
	public int searchBasePassCount(com.pinde.core.model.ResDoctorRecruit recruit, List<String> orgFlowList) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("recruit", recruit);
		paramMap.put("orgFlowList", orgFlowList);
		return jszyResDoctorRecruitExtMapper.searchBasePassCount(paramMap);
	}
	
	@Override
	public int saveAuditRecruit(ResDoctorRecruitWithBLOBs recWithBLOBs){
		String auditStatusId =  recWithBLOBs.getAuditStatusId();
		if(StringUtil.isNotBlank(auditStatusId)){
            recWithBLOBs.setAuditStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.getNameById(auditStatusId));
		}
		String msgTitle = "培训信息审核结果";
		String msgContent = null;
        if (com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId().equals(auditStatusId)) {//审核通过
			msgContent = "您的培训信息审核通过。";
			
			String recruitFlow = recWithBLOBs.getRecruitFlow();
			String doctorFlow = recWithBLOBs.getDoctorFlow();
			com.pinde.core.model.ResDoctorRecruit recruit = null;
			if(StringUtil.isNotBlank(recruitFlow) && StringUtil.isNotBlank(doctorFlow)){
				recruit = readResDoctorRecruit(recruitFlow);
				ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
				if(recruit != null && doctor != null){
					doctor.setOrgFlow(recruit.getOrgFlow());
					doctor.setOrgName(recruit.getOrgName());
					doctor.setTrainingTypeId(recruit.getCatSpeId());
					doctor.setTrainingTypeName(recruit.getCatSpeName());
					doctor.setDoctorCategoryId(recruit.getCatSpeId());
					doctor.setDoctorCategoryName(recruit.getCatSpeName());
					doctor.setTrainingSpeId(recruit.getSpeId());
					doctor.setTrainingSpeName(recruit.getSpeName());
					doctor.setDoctorStatusId(recruit.getDoctorStatusId());
					doctor.setDoctorStatusName(recruit.getDoctorStatusName());
					doctor.setTrainingYears(recruit.getTrainYear());
					doctor.setDegreeCategoryId(recruit.getCurrDegreeCategoryId());
					doctor.setDegreeCategoryName(recruit.getCurrDegreeCategoryName());
					doctor.setGraduationYear(recruit.getGraduationYear());
					doctor.setSecondSpeId(recruit.getSecondSpeId());
					doctor.setSecondSpeName(recruit.getSecondSpeName());
					//回写届数
					doctor.setSessionNumber(recruit.getSessionNumber());
					resDoctorBiz.editDoctor(doctor, null);
					doctor=rotationBiz.updateDocRotation(doctor);
					resDoctorBiz.editDoctor(doctor, null);
				}
			}
		}else{//审核不通过
			msgContent = "您的培训信息审核不通过。";
		}
		int result = saveDoctorRecruit(recWithBLOBs);
        if (result == 1 && com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId().equals(auditStatusId))
		{
			ResDoctorRecruit resDoctorRecruit=readResDoctorRecruit(recWithBLOBs.getRecruitFlow());
			if(resDoctorRecruit!=null) {
				String sessionNumber=resDoctorRecruit.getSessionNumber();
				String doctorFlow=resDoctorRecruit.getDoctorFlow();
				JsresRecruitDocInfoWithBLOBs info2 = resDoctorBiz.getRecruitDocInfoBySessionNumber(doctorFlow, sessionNumber);
				JsresRecruitInfo recruitInfo2 = resDoctorBiz.getRecruitInfoBysessionNumber(doctorFlow, sessionNumber);
				if (info2 != null) {
					resDoctorBiz.delJsresRecruitDocInfo(info2.getRecruitFlow());
				}
				if (recruitInfo2 != null)
					resDoctorBiz.delJsresRecruitInfo(recruitInfo2.getRecordFlow());

				//审核通过就往jsres_recruit_doc_info 插入res_doctor_recruit 的数据
				//jsres_recruit_info插入res_doctor_recruit与res_doctor 结合的数据
				resDoctorBiz.insertRecruitDocInfo(recWithBLOBs.getRecruitFlow());
				resDoctorBiz.insertRecruitInfo(recWithBLOBs.getRecruitFlow());
			}
		}
		String modifyTime = DateUtil.transDateTime(recWithBLOBs.getModifyTime());
		String operUserFlow = recWithBLOBs.getModifyUserFlow();
		SysUser user = userBiz.readSysUser(operUserFlow);
		String orgName = user.getOrgName();
		msgContent += orgName + "，" +modifyTime;
		msgBiz.addSysMsg(recWithBLOBs.getDoctorFlow(), msgTitle , msgContent);
		return result;
	}
	
	
	@Override
	public List<JszyDoctorInfoExt> searchDoctorInfoResume(Map<String,Object> paramMap) {
		return jszyResDoctorRecruitExtMapper.searchDoctorInfoExts(paramMap);
	}

	public int searchDoctorNum(com.pinde.core.model.ResDoctorRecruit recruit) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(recruit.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(recruit.getOrgFlow());
		}
		if(StringUtil.isNotBlank(recruit.getDoctorFlow())){
			criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
		}
		if (StringUtil.isNotBlank(recruit.getSpeId())) {
			criteria.andSpeIdEqualTo(recruit.getSpeId());
		}
		if (StringUtil.isNotBlank(recruit.getRecruitYear())) {
			criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
		}
		if(StringUtil.isNotBlank(recruit.getRecruitTypeId())){
			criteria.andRecruitTypeIdEqualTo(recruit.getRecruitTypeId());
		}
		if (StringUtil.isNotBlank(recruit.getRetestFlag())) {
			criteria.andRetestFlagEqualTo(recruit.getRetestFlag());
		}
		if (StringUtil.isNotBlank(recruit.getRecruitFlag())) {
			criteria.andRecruitFlagEqualTo(recruit.getRecruitFlag());
		}
		if (StringUtil.isNotBlank(recruit.getConfirmFlag())) {
			criteria.andConfirmFlagEqualTo(recruit.getConfirmFlag());
		}
		if (StringUtil.isNotBlank(recruit.getAuditStatusId())) {
			criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
		}
		return doctorRecruitMapper.countByExample(example);
	}
	
	@Override
	public int editResDoctorRecruit(ResDoctorRecruitWithBLOBs recWithBLOBs, ResDoctorRecruitWithBLOBs prevDocRec) {
		//医师状态
		if(StringUtil.isNotBlank(recWithBLOBs.getDoctorStatusId())){
            recWithBLOBs.setDoctorStatusName(com.pinde.core.common.enums.DictTypeEnum.DoctorStatus.getDictNameById(recWithBLOBs.getDoctorStatusId()));
		}else{
			recWithBLOBs.setDoctorStatusName("");
		}
		//医师走向
		if(StringUtil.isNotBlank(recWithBLOBs.getDoctorStrikeId())){
            recWithBLOBs.setDoctorStrikeName(com.pinde.core.common.enums.DictTypeEnum.DoctorStrike.getDictNameById(recWithBLOBs.getDoctorStrikeId()));
		}else{
			recWithBLOBs.setDoctorStrikeName("");
		}
		if(StringUtil.isNotBlank(recWithBLOBs.getCurrDegreeCategoryId())){
            recWithBLOBs.setCurrDegreeCategoryName(com.pinde.core.common.enums.JsResDegreeCategoryEnum.getNameById(recWithBLOBs.getCurrDegreeCategoryId()));
		}
        recWithBLOBs.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getId());
        recWithBLOBs.setAuditStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getName());
		String doctorFlow = recWithBLOBs.getDoctorFlow();

		//结业审核年份
		int year=0;
		if(StringUtil.isNotBlank(recWithBLOBs.getSessionNumber())&&StringUtil.isNotBlank(recWithBLOBs.getTrainYear())){
			int num=0;
            if (JsResTrainYearEnum.OneYear.getId().equals(recWithBLOBs.getTrainYear())) {
				num=1;
			}
            if (JsResTrainYearEnum.TwoYear.getId().equals(recWithBLOBs.getTrainYear())) {
				num=2;
			}
            if (JsResTrainYearEnum.ThreeYear.getId().equals(recWithBLOBs.getTrainYear())) {
				num=3;
			}
			year = Integer.parseInt(recWithBLOBs.getSessionNumber())+num;
			recWithBLOBs.setGraduationYear(year+"");
		}
		//其中已审核通过
		ResDoctorRecruit exitRec = new ResDoctorRecruit();
		exitRec.setDoctorFlow(doctorFlow);
        exitRec.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
        exitRec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<com.pinde.core.model.ResDoctorRecruit> passedRecruitList = searchResDoctorRecruitList(exitRec, "CREATE_TIME");
		boolean firstIsWMSecond = false;//首条是否为二阶段(有自动生成一阶段)
		if(passedRecruitList != null && !passedRecruitList.isEmpty()){
			for(ResDoctorRecruit  rec : passedRecruitList){
				if(StringUtil.isBlank(rec.getSpeId())){
					firstIsWMSecond = true;
					break;
				}
			}
		}
//		//当前为二阶段
//		if(com.pinde.core.common.enums.JsResTrainYearEnum.WMSecond.getId().equals(recWithBLOBs.getCatSpeId()) ){
//			if(passedRecruitList.isEmpty()){//无审核通过
//				ResDoctorRecruitWithBLOBs addWMFirst = new ResDoctorRecruitWithBLOBs();
//				addWMFirst.setCompleteFileUrl(recWithBLOBs.getCompleteFileUrl());
//				addWMFirst.setCompleteCertNo(recWithBLOBs.getCompleteCertNo());
//				//查询状态为N的记录
//				ResDoctorRecruit exitN = new ResDoctorRecruit();
//				exitN.setDoctorFlow(doctorFlow);
//				exitN.setCatSpeId(com.pinde.core.common.enums.JsResTrainYearEnum.WMFirst.getId());
//				exitN.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
//				List<com.pinde.core.model.ResDoctorRecruit>  exitNList = searchResDoctorRecruitList(exitN, null);
//				if(exitNList != null && !exitNList.isEmpty()){
//					exitN = exitNList.get(0);
//					addWMFirst.setRecruitFlow(exitN.getRecruitFlow());
//					addWMFirst.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//					saveDoctorRecruit(addWMFirst);
//				}else{//新增
//					addWMFirst.setDoctorFlow(doctorFlow);
//					addWMFirst.setCatSpeId(com.pinde.core.common.enums.JsResTrainYearEnum.WMFirst.getId());
//					addWMFirst.setCatSpeName(com.pinde.core.common.enums.JsResTrainYearEnum.WMFirst.getName());
//					addWMFirst.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
//					addWMFirst.setAuditStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getName());
//					addWMFirst.setRecruitFlow(PkUtil.getUUID());
//					GeneralMethod.setRecordInfo(addWMFirst, true);
//					String createTime = addWMFirst.getCreateTime();
//					if(StringUtil.isNotBlank(recWithBLOBs.getCreateTime())){//当前为首条记录 (修改创建时间解决排序：生成一阶段置前)
//						createTime = recWithBLOBs.getCreateTime();
//					}
//					long createTimeVal = Long.valueOf(createTime) - 1;
//					addWMFirst.setCreateTime(String.valueOf(createTimeVal));
//					doctorRecruitMapper.insert(addWMFirst);
//					prevDocRec.setRecruitFlow(addWMFirst.getRecruitFlow());
//				}
//			}
//		}else{//非二阶段
			if(passedRecruitList.size() == 1 && firstIsWMSecond){//首条记录为二阶段  修改为非二阶段  ==>删除自动生成的一阶段
				ResDoctorRecruitWithBLOBs deleWMFirst = new ResDoctorRecruitWithBLOBs();
				deleWMFirst.setRecruitFlow(passedRecruitList.get(0).getRecruitFlow());
                deleWMFirst.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
				saveDoctorRecruit(deleWMFirst);
			}
//		}
		//上一阶段：结业证书、证书编号
		if(StringUtil.isNotBlank(prevDocRec.getRecruitFlow())){
			saveDoctorRecruit(prevDocRec);
		}
		return saveDoctorRecruit(recWithBLOBs);
	}
	@Override
	public int updateDoctorTrend(ResDoctorRecruitWithBLOBs recruitWithBLOBs) {
		String doctorFlow = recruitWithBLOBs.getDoctorFlow();
		if(StringUtil.isNotBlank(doctorFlow)){
			//ResDoctor医师状态
			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
			doctor.setDoctorStatusId(recruitWithBLOBs.getDoctorStatusId());
			doctor.setDoctorStatusName(recruitWithBLOBs.getDoctorStatusName());
			if(StringUtil.isNotBlank(recruitWithBLOBs.getTrainYear())){
				doctor.setTrainingYears(recruitWithBLOBs.getTrainYear());
			}
			if(StringUtil.isNotBlank(recruitWithBLOBs.getSessionNumber())&&StringUtil.isNotBlank(recruitWithBLOBs.getTrainYear())){
				doctor.setSessionNumber(recruitWithBLOBs.getSessionNumber());
				int year =0;
                if (JsResTrainYearEnum.OneYear.getId().equals(recruitWithBLOBs.getTrainYear())) {
					year=1;
				}
                if (JsResTrainYearEnum.TwoYear.getId().equals(recruitWithBLOBs.getTrainYear())) {
					year=2;
				}
                if (JsResTrainYearEnum.ThreeYear.getId().equals(recruitWithBLOBs.getTrainYear())) {
					year=3;
				}
				recruitWithBLOBs.setGraduationYear((Integer.parseInt(recruitWithBLOBs.getSessionNumber())+year)+"");
			}
			if(StringUtil.isNotBlank(recruitWithBLOBs.getCurrDegreeCategoryId())){
                recruitWithBLOBs.setCurrDegreeCategoryName(com.pinde.core.common.enums.JsResDegreeCategoryEnum.getNameById(recruitWithBLOBs.getCurrDegreeCategoryId()));
			}
			
			saveDoctorRecruit(recruitWithBLOBs);
			return resDoctorBiz.editDoctor(doctor, null);
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}
	@Override
	public ResDoctorRecruitWithBLOBs readRecruit(String recruitFlow) {
		if (StringUtil.isNotBlank(recruitFlow)) {
			return doctorRecruitMapper.selectByPrimaryKey(recruitFlow);
		}
		return null;
	}

	@Override
	public List<JszyResDoctorRecruitExt> searchDoctorInfoExts(Map<String, Object> doctorRecruitMap) {
		return jszyResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList(doctorRecruitMap);
	}
	@Override
    public List<JszyResDoctorRecruitExt> searchDoctorSkillAndTheoryScoreExts(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, String scoreYear, String isHege, String skillIsHege, List<String> docTypeList)
	{
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("derateFlag", flag);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorRecruitMap.put("sysOrg", sysOrg);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("scoreYear",scoreYear);
		doctorRecruitMap.put("isHege",isHege);
		doctorRecruitMap.put("hegeScore", InitConfig.getSysCfg("res_theoryScore"));
		if(StringUtil.isNotBlank(skillIsHege))
		{
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(skillIsHege))
			{
				skillIsHege="1";
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(skillIsHege))
			{
				skillIsHege="0";
			}
		}
		doctorRecruitMap.put("skillIsHege",skillIsHege);
		List<JszyResDoctorRecruitExt> doctorRecruitList=jszyResDoctorRecruitExtMapper.searchDoctorSkillAndTheoryScoreExts(doctorRecruitMap);
		return doctorRecruitList;
	}
	@Override
	public List<JszyResDoctorRecruitExt> searchDoctorSkillScore(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, String scoreYear, String isHege, List<String> docTypeList)
	{
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("derateFlag", flag);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorRecruitMap.put("sysOrg", sysOrg);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("scoreYear",scoreYear);
		if(StringUtil.isNotBlank(isHege))
		{
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isHege))
			{
				isHege="1";
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(isHege))
			{
				isHege="0";
			}
		}
		doctorRecruitMap.put("isHege",isHege);
        doctorRecruitMap.put("typeId", com.pinde.core.common.enums.ResScoreTypeEnum.SkillScore.getId());
		List<JszyResDoctorRecruitExt> doctorRecruitList=jszyResDoctorRecruitExtMapper.searchJsDoctorSkillScoreExtList(doctorRecruitMap);
		return doctorRecruitList;
    }


	@Override
	public List<com.pinde.core.model.ResDoctorRecruit> readDoctorRecruits(com.pinde.core.model.ResDoctorRecruit recruit) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(recruit.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(recruit.getOrgFlow());
		}
		if(StringUtil.isNotBlank(recruit.getDoctorFlow())){
			criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
		}
		if (StringUtil.isNotBlank(recruit.getSpeId())) {
			criteria.andSpeIdEqualTo(recruit.getSpeId());
		}
		if (StringUtil.isNotBlank(recruit.getRecruitYear())) {
			criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
		}
		if(StringUtil.isNotBlank(recruit.getRecruitTypeId())){
			criteria.andRecruitTypeIdEqualTo(recruit.getRecruitTypeId());
		}
		if (StringUtil.isNotBlank(recruit.getRetestFlag())) {
			criteria.andRetestFlagEqualTo(recruit.getRetestFlag());
		}
		if (StringUtil.isNotBlank(recruit.getRecruitFlag())) {
			criteria.andRecruitFlagEqualTo(recruit.getRecruitFlag());
		}
		if (StringUtil.isNotBlank(recruit.getConfirmFlag())) {
			criteria.andConfirmFlagEqualTo(recruit.getConfirmFlag());
		}
		if (StringUtil.isNotBlank(recruit.getAuditStatusId())) {
			criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
		}
		if (StringUtil.isNotBlank(recruit.getSessionNumber())) {
			criteria.andSessionNumberEqualTo(recruit.getSessionNumber());
		}
		return doctorRecruitMapper.selectByExample(example);
	}

	@Override
	public List<ResDoctorRecruitWithBLOBs> searchRecruitWithBLOBs(com.pinde.core.model.ResDoctorRecruit recruit) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(recruit.getDoctorFlow())){
			criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow()); 
		}  
		if(StringUtil.isNotBlank(recruit.getAuditStatusId())){
			criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
		}
		example.setOrderByClause("create_time desc");
		return doctorRecruitMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int updateRecruit(ResDoctorRecruitWithBLOBs resDoctorRecruitWithBLOBs) {
		int result=0;
		if(StringUtil.isNotBlank(resDoctorRecruitWithBLOBs.getRecruitFlow())){
			result=doctorRecruitMapper.updateByPrimaryKeySelective(resDoctorRecruitWithBLOBs);
		}
		return result;
	}
	
	@Override
	public boolean getRecruitStatus(String doctorFlow){
		if(!StringUtil.isNotBlank(doctorFlow)){
			return false;
		}
		
		ResDoctorRecruitExample recruitExample = new ResDoctorRecruitExample();
        recruitExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
		.andDoctorFlowEqualTo(doctorFlow);
		recruitExample.setOrderByClause("CREATE_TIME DESC");

		List<com.pinde.core.model.ResDoctorRecruit> recruits = doctorRecruitMapper.selectByExample(recruitExample);
		if(recruits==null || recruits.isEmpty()){
			return false;
		}

		com.pinde.core.model.ResDoctorRecruit recruit = recruits.get(0);
		
		if(recruit==null){
			return false;
		}
		
		return JszyBaseStatusEnum.Passed.getId().equals(recruit.getAuditStatusId());
	}

	@Override
	public List<Map<String, Object>> searchJointOrgList() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return jszyResDoctorRecruitExtMapper.searchJointOrgList(paramMap);
	}

	@Override
	public List<Map<String, Object>> doctorScoreQuery(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, List<String> docTypeList, List<String> cerStatusList) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("derateFlag", flag);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorRecruitMap.put("sysOrg", sysOrg);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("cerStatusList", cerStatusList);
		List<Map<String, Object>> doctorRecruitList=jszyResDoctorRecruitExtMapper.doctorScoreQuery(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<Map<String, Object>> zlxytj(Map<String,Object> param) {
		return jszyResDoctorRecruitExtMapper.zlxytj(param);
	}

	@Override
	public List<Map<String, Object>> zlxytj2(Map<String, Object> param) {
		return jszyResDoctorRecruitExtMapper.zlxytj2(param);
	}

	@Override
	public List<Map<String, Object>> zlxytjJoint(Map<String, Object> param) {
		return jszyResDoctorRecruitExtMapper.zlxytjJoint(param);
	}
	@Override
	public List<JszyResDoctorRecruitExt> searchDoctorCertificateList(Map<String, Object> doctorRecruitMap) {
		List<JszyResDoctorRecruitExt> doctorRecruitList=jszyResDoctorRecruitExtMapper.searchDoctorCertificateList(doctorRecruitMap);
		return doctorRecruitList;
	}



	@Override
	public List<ResDoctorRecruitWithBLOBs> readDoctorRecruitBlobs(com.pinde.core.model.ResDoctorRecruit recruit) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(recruit.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(recruit.getOrgFlow());
		}
		if(StringUtil.isNotBlank(recruit.getDoctorFlow())){
			criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
		}
		if (StringUtil.isNotBlank(recruit.getSpeId())) {
			criteria.andSpeIdEqualTo(recruit.getSpeId());
		}
		if (StringUtil.isNotBlank(recruit.getRecruitYear())) {
			criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
		}
		if(StringUtil.isNotBlank(recruit.getRecruitTypeId())){
			criteria.andRecruitTypeIdEqualTo(recruit.getRecruitTypeId());
		}
		if (StringUtil.isNotBlank(recruit.getRetestFlag())) {
			criteria.andRetestFlagEqualTo(recruit.getRetestFlag());
		}
		if (StringUtil.isNotBlank(recruit.getRecruitFlag())) {
			criteria.andRecruitFlagEqualTo(recruit.getRecruitFlag());
		}
		if (StringUtil.isNotBlank(recruit.getConfirmFlag())) {
			criteria.andConfirmFlagEqualTo(recruit.getConfirmFlag());
		}
		if (StringUtil.isNotBlank(recruit.getAuditStatusId())) {
			criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
		}
		if (StringUtil.isNotBlank(recruit.getSessionNumber())) {
			criteria.andSessionNumberEqualTo(recruit.getSessionNumber());
		}
		if (StringUtil.isNotBlank(recruit.getGraduationYear())) {
			criteria.andGraduationYearEqualTo(recruit.getGraduationYear());
		}
		if (StringUtil.isNotBlank(recruit.getGraduationCertificateNo())) {
			criteria.andGraduationCertificateNoEqualTo(recruit.getGraduationCertificateNo());
		}
		return doctorRecruitMapper.selectByExampleWithBLOBs(example);
	}

    @Override
    public List<Map<String,Object>> zpxytj(Map<String, Object> param){
        return jszyResDoctorRecruitExtMapper.zpxytj(param);
    }
}
