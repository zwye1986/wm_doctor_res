package com.pinde.sci.biz.jsres.impl;


import com.pinde.core.model.*;
import com.pinde.core.model.ResDoctorOrgHistoryExample.Criteria;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorOrgHistoryBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ResDoctorOrgHistoryMapper;
import com.pinde.sci.dao.jsres.JsResDoctorOrgHistoryExtMapper;
import com.pinde.sci.model.jsres.JsResDoctorOrgHistoryExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author tiger
 *
 */
@Service
//@Transactional(rollbackFor=Exception.class)
public class JsResDoctorOrgHistoryBizImpl implements IJsResDoctorOrgHistoryBiz{

	@Autowired
	private ResDoctorOrgHistoryMapper docOrgHistoryMapper;
	@Autowired
	private JsResDoctorOrgHistoryExtMapper docOrgHistoryExtMapper;
	@Autowired
	private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private IUserBiz userBiz;

	@Override
	public int saveDocOrgHistory(ResDoctorOrgHistory docOrgHistory) {
		if(StringUtil.isNotBlank(docOrgHistory.getRecordFlow())){
			GeneralMethod.setRecordInfo(docOrgHistory, false);
			return docOrgHistoryMapper.updateByPrimaryKeySelective(docOrgHistory);
		}else{
			docOrgHistory.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(docOrgHistory, true);
			return docOrgHistoryMapper.insert(docOrgHistory);
		}
	}

	@Override
	public List<ResDoctorOrgHistory> searchDoctorOrgHistoryList(ResDoctorOrgHistory doctorOrgHistory) {
		ResDoctorOrgHistoryExample example = new ResDoctorOrgHistoryExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(doctorOrgHistory.getDoctorFlow())){
			criteria.andDoctorFlowEqualTo(doctorOrgHistory.getDoctorFlow());
		}
		if(StringUtil.isNotBlank(doctorOrgHistory.getChangeStatusId())){
			criteria.andChangeStatusIdEqualTo(doctorOrgHistory.getChangeStatusId());
		}
		if(StringUtil.isNotBlank(doctorOrgHistory.getChangeTypeId())){
			criteria.andChangeTypeIdEqualTo(doctorOrgHistory.getChangeTypeId());
		}
		if(StringUtil.isNotBlank(doctorOrgHistory.getOrgFlow())){
			criteria.andOrgFlowEqualTo(doctorOrgHistory.getOrgFlow());
		}
		if(StringUtil.isNotBlank(doctorOrgHistory.getHistoryOrgFlow())){
			criteria.andHistoryOrgFlowEqualTo(doctorOrgHistory.getHistoryOrgFlow());
		}
		example.setOrderByClause("CREATE_TIME DESC");
		return docOrgHistoryMapper.selectByExample(example);
	}

	@Override
	public List<JsResDoctorOrgHistoryExt> searchDoctorOrgHistoryExtList(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList,
			ResDoctor resDoctor,List<String> orgFlowList,List<String> docTypeList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("docOrgHistory", docOrgHistory);
		paramMap.put("resDoctor", resDoctor);
		if(changeStatusIdList != null && !changeStatusIdList.isEmpty()){
			paramMap.put("changeStatusIdList", changeStatusIdList);
		}
		if(orgFlowList!=null && !orgFlowList.isEmpty()){
			paramMap.put("orgFlowList", orgFlowList);
		}
		if(docTypeList!=null && !docTypeList.isEmpty()){
			paramMap.put("docTypeList", docTypeList);
		}
		return docOrgHistoryExtMapper.searchDoctorOrgHistoryExtListNew(paramMap);
	}

	@Override
	public List<JsResDoctorOrgHistoryExt> searchDoctorOrgHistoryExtList1(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList,
																			ResDoctor resDoctor,List<String> orgFlowList,List<String> docTypeList,List<String> sessionNumbers) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("docOrgHistory", docOrgHistory);
		paramMap.put("resDoctor", resDoctor);
		paramMap.put("sessionNumbers", sessionNumbers);
		if(changeStatusIdList != null && !changeStatusIdList.isEmpty()){
			paramMap.put("changeStatusIdList", changeStatusIdList);
		}
		if(orgFlowList!=null && !orgFlowList.isEmpty()){
			paramMap.put("orgFlowList", orgFlowList);
		}
		if(docTypeList!=null && !docTypeList.isEmpty()){
			paramMap.put("docTypeList", docTypeList);
		}
		return docOrgHistoryExtMapper.searchDoctorOrgHistoryExtList(paramMap);
	}

	@Override
	public List<JsResDoctorOrgHistoryExt> searchDoctorOrgHistoryExtList1New(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList,
			ResDoctor resDoctor,List<String> orgFlowList,List<String> docTypeList,List<String> sessionNumbers,List<String> jointOrgFlowList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("docOrgHistory", docOrgHistory);
		paramMap.put("resDoctor", resDoctor);
		paramMap.put("sessionNumbers", sessionNumbers);
		if(changeStatusIdList != null && !changeStatusIdList.isEmpty()){
			paramMap.put("changeStatusIdList", changeStatusIdList);
		}
		if(orgFlowList!=null && !orgFlowList.isEmpty()){
			paramMap.put("orgFlowList", orgFlowList);
		}
		if(docTypeList!=null && !docTypeList.isEmpty()){
			paramMap.put("docTypeList", docTypeList);
		}
		if(jointOrgFlowList!=null && !jointOrgFlowList.isEmpty()){
			paramMap.put("jointOrgFlowList", jointOrgFlowList);
		}
		return docOrgHistoryExtMapper.searchDoctorOrgHistoryExtList(paramMap);
	}

	@Override
	public int auditTurnInOrg(String recordFlow, String changeStatusId, String doctorFlow,String time,String chooseFlag) {
		if(StringUtil.isNotBlank(recordFlow) && StringUtil.isNotBlank(changeStatusId)){
			ResDoctorOrgHistory docOrgHistory = readDocOrgHistory(recordFlow);
			if(docOrgHistory != null){
				docOrgHistory.setChangeStatusId(changeStatusId);
                docOrgHistory.setChangeStatusName(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.getNameById(changeStatusId));
				docOrgHistory.setInDate(DateUtil.getCurrDateTime());
				String msgTitle = "基地变更申请审核结果";
				String msgContent = null;
                if (com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyWaiting.getId().equals(changeStatusId)) {//基地转入通过
					docOrgHistory.setIsAgreeOldTrain(chooseFlag);
                    if (StringUtil.isNotBlank(time) && com.pinde.core.common.GlobalConstant.FLAG_N.equals(chooseFlag)) {
						docOrgHistory.setChangeInGraduationYear(time);
					}
					//变更记录
					msgContent = "基地变更申请（" +docOrgHistory.getHistoryOrgName() +" → "+docOrgHistory.getOrgName()+"）审核结果：" + docOrgHistory.getChangeStatusName() + "。";
					msgBiz.addSysMsg(doctorFlow, msgTitle , msgContent);
					return saveDocOrgHistory(docOrgHistory);
				}else{//转入不通过
					msgContent = "基地变更申请（" +docOrgHistory.getHistoryOrgName() +" → "+docOrgHistory.getOrgName()+"）审核结果：" + docOrgHistory.getChangeStatusName() + "。";
					msgBiz.addSysMsg(doctorFlow, msgTitle , msgContent);
					return saveDocOrgHistory(docOrgHistory);
				}
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}
	@Override
	public int auditTurnInOrgGlobal(ResDoctorOrgHistory docOrgHistory) {
		if(StringUtil.isNotBlank(docOrgHistory.getRecordFlow()) && StringUtil.isNotBlank(docOrgHistory.getChangeStatusId())){
			ResDoctorOrgHistory docOrgHistory1 = readDocOrgHistory(docOrgHistory.getRecordFlow());
			if(docOrgHistory1 != null){
				docOrgHistory1.setChangeStatusId(docOrgHistory.getChangeStatusId());
                docOrgHistory1.setChangeStatusName(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.getNameById(docOrgHistory.getChangeStatusId()));
//				docOrgHistory.setInDate(DateUtil.getCurrDateTime());
				String msgTitle = "基地变更申请审核结果";
				String msgContent = null;
                if (com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyPass.getId().equals(docOrgHistory.getChangeStatusId())) {//转入通过==》修改最新培训记录

					//获取最新培训记录
					if(StringUtil.isNotBlank(docOrgHistory1.getDoctorFlow())){
						ResDoctorRecruit docRecruit =  jsResDoctorRecruitBiz.readRecruit(docOrgHistory1.getRecruitFlow());
//						docRecruit.setDoctorFlow(doctorFlow);
//						docRecruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//						List<com.pinde.core.model.ResDoctorRecruit>  docRecruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(docRecruit, "CREATE_TIME DESC");
						if(docRecruit != null){

							String orgFlow = docOrgHistory1.getOrgFlow();
							if(StringUtil.isNotBlank(orgFlow)){
								SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);//获取变更机构信息
								if(sysOrg != null){
									//DoctorRecruit回写
									ResDoctorRecruitWithBLOBs docRecruitWithBLOBs = new ResDoctorRecruitWithBLOBs();
									docRecruitWithBLOBs.setRecruitFlow(docRecruit.getRecruitFlow());
									docRecruitWithBLOBs.setOrgFlow(orgFlow);
									docRecruitWithBLOBs.setOrgName(sysOrg.getOrgName());
									docRecruitWithBLOBs.setPlaceId(sysOrg.getOrgCityId());
									docRecruitWithBLOBs.setPlaceName(sysOrg.getOrgCityName());
									String isAgreeOldTrain = docOrgHistory1.getIsAgreeOldTrain();
									String changeInGraduationYear = docOrgHistory1.getChangeInGraduationYear();
                                    if (StringUtil.isNotBlank(changeInGraduationYear) && com.pinde.core.common.GlobalConstant.FLAG_N.equals(isAgreeOldTrain)) {
										docRecruitWithBLOBs.setGraduationYear(changeInGraduationYear);
									}
//									if(StringUtil.isNotBlank(time)){
//										docOrgHistory.setSessionNumber(time);
//									}else{
//										docOrgHistory.setSessionNumber(docRecruit.getSessionNumber());
//									}
									//变更记录
									saveDocOrgHistory(docOrgHistory1);
									//Doctor回写
									ResDoctor doctor = doctorBiz.readDoctor(docOrgHistory1.getDoctorFlow());
									doctor.setOrgFlow(orgFlow);
									doctor.setOrgName(sysOrg.getOrgName());
//									if(StringUtil.isNotBlank(time)&&com.pinde.core.common.GlobalConstant.FLAG_N.equals(chooseFlag)){
//										doctor.setSessionNumber(time);
//									}
									doctorBiz.editDoctor(doctor, null);
									SysUser user=userBiz.readSysUser(docOrgHistory1.getDoctorFlow());
									if(user!=null)
									{
										user.setOrgFlow(doctor.getOrgFlow());
										user.setOrgName(doctor.getOrgName());
										GeneralMethod.setRecordInfo(user,false);
										userBiz.edit(user);
									}
									msgContent = "基地变更申请（" +docOrgHistory1.getHistoryOrgName() +" → "+docOrgHistory1.getOrgName()+"）审核结果：" + docOrgHistory1.getChangeStatusName() + "。";
									msgBiz.addSysMsg(docOrgHistory1.getDoctorFlow(), msgTitle , msgContent);

									return jsResDoctorRecruitBiz.saveDoctorRecruit(docRecruitWithBLOBs);
								}
							}
						}
					}
				}else{//转入不通过
					msgContent = "基地变更申请（" +docOrgHistory1.getHistoryOrgName() +" → "+docOrgHistory1.getOrgName()+"）审核结果：" + docOrgHistory1.getChangeStatusName() + "。";
					msgBiz.addSysMsg(docOrgHistory1.getDoctorFlow(), msgTitle , msgContent);
					return saveDocOrgHistory(docOrgHistory1);
				}
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}
	@Override
	public ResDoctorOrgHistory readDocOrgHistory(String recordFlow) {
		return docOrgHistoryMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public List<ResDoctorOrgHistory> searchWaitingChangeOrgHistoryList(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList) {
		ResDoctorOrgHistoryExample example = new ResDoctorOrgHistoryExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		//Criteria criteria2 =  example.createCriteria();
		if(StringUtil.isNotBlank(docOrgHistory.getDoctorFlow())){
			criteria.andDoctorFlowEqualTo(docOrgHistory.getDoctorFlow());
		}
		if(changeStatusIdList != null && !changeStatusIdList.isEmpty()){
			criteria.andChangeStatusIdIn(changeStatusIdList);
		}
		if(StringUtil.isNotBlank(docOrgHistory.getChangeTypeId())){
			criteria.andChangeTypeIdEqualTo(docOrgHistory.getChangeTypeId());
		}
		if(StringUtil.isNotBlank(docOrgHistory.getChangeTypeName())){
			criteria.andChangeTypeNameEqualTo(docOrgHistory.getChangeTypeName());
		}
		if(StringUtil.isNotBlank(docOrgHistory.getOrgFlow())){
			criteria.andOrgFlowEqualTo(docOrgHistory.getOrgFlow());
		}
		if(StringUtil.isNotBlank(docOrgHistory.getHistoryOrgFlow())){
			criteria.andHistoryOrgFlowEqualTo(docOrgHistory.getHistoryOrgFlow());
		}
		if(StringUtil.isNotBlank(docOrgHistory.getRecruitFlow())){
			criteria.andRecruitFlowEqualTo(docOrgHistory.getRecruitFlow());
		}
		/*criteria2.andChangeStatusIdIsNull();
		example.or(criteria2);*/
		example.setOrderByClause("CREATE_TIME DESC");
		return docOrgHistoryMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int auditTurnOutOrg(ResDoctorOrgHistory history,String time) {
		if(StringUtil.isNotBlank(history.getRecordFlow()) && StringUtil.isNotBlank(history.getChangeStatusId())){
			ResDoctorOrgHistory docOrgHistory = readDocOrgHistory(history.getRecordFlow());
			if(docOrgHistory!=null){
				docOrgHistory.setChangeStatusId(history.getChangeStatusId());
                docOrgHistory.setChangeStatusName(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.getNameById(history.getChangeStatusId()));
				if(StringUtil.isBlank(time)){
                    if (StringUtil.isNotBlank(docOrgHistory.getChangeStatusId()) && com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyUnPass.getId().equals(docOrgHistory.getChangeStatusId())) {
						docOrgHistory.setInDate(DateUtil.getCurrDateTime());
					}else{
						docOrgHistory.setOutDate(DateUtil.getCurrDateTime());
					}
				}else{
                    if (StringUtil.isNotBlank(docOrgHistory.getChangeStatusId()) && com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyUnPass.getId().equals(docOrgHistory.getChangeStatusId())) {
						docOrgHistory.setInDate(time);
					}else{
						docOrgHistory.setOutDate(time);
					}
				}
				if(StringUtil.isNotBlank(history.getAuditOpinion())){
					docOrgHistory.setAuditOpinion(history.getAuditOpinion());
					docOrgHistory.setAuditUserFlow(GlobalContext.getCurrentUser().getUserFlow());
					docOrgHistory.setAuditUserName(history.getAuditUserName());
				}
				String msgTitle = "基地变更申请审核结果";
				String msgContent = null;
                if (com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyWaiting.getId().equals(history.getChangeStatusId())) {//通过==》带转入
					msgContent = "基地变更申请（" +docOrgHistory.getHistoryOrgName() +" → "+docOrgHistory.getOrgName()+"）审核结果：转出审核通过。";
				}else{
					msgContent = "基地变更申请（" +docOrgHistory.getHistoryOrgName() +" → "+docOrgHistory.getOrgName()+"）审核结果：" + docOrgHistory.getChangeStatusName() + "。";
				}
				msgBiz.addSysMsg(history.getDoctorFlow(), msgTitle , msgContent);
				return saveDocOrgHistory(docOrgHistory);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
    public int changeStatus(ResDoctorOrgHistory history, ResDoctorRecruit doctorRecruit) {
		int doctorResult=0;int recruitResult=0;
		if(history!=null){
			ResDoctor doctor=doctorBiz.readDoctor(history.getDoctorFlow());
			if(doctor!=null){
				doctor.setTrainingSpeId(history.getTrainingSpeId());
				doctor.setTrainingSpeName(history.getTrainingSpeName());
				doctor.setTrainingTypeId(history.getTrainingTypeId());
				doctor.setTrainingTypeName(history.getTrainingTypeName());
				doctor=rotationBiz.updateDocRotation(doctor);
				doctorResult=doctorBiz.editDoctor(doctor, null);
			}
			ResDoctorRecruitWithBLOBs lastRecruit = new ResDoctorRecruitWithBLOBs();
			lastRecruit.setDoctorFlow(history.getDoctorFlow());
            lastRecruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<ResDoctorRecruitWithBLOBs> recruitList = jsResDoctorRecruitBiz.searchRecruitWithBLOBs(lastRecruit);
			if(recruitList != null && !recruitList.isEmpty()){
				lastRecruit = recruitList.get(0);
				lastRecruit.setSpeId(history.getTrainingSpeId());
				lastRecruit.setSpeName(history.getTrainingSpeName());
				lastRecruit.setCatSpeId(history.getTrainingTypeId());
				lastRecruit.setCatSpeName(history.getTrainingTypeName());
				lastRecruit.setGraduationYear(doctorRecruit.getGraduationYear());
				if(doctor !=null){
					//每一条培训记录保存一个培训方案
					lastRecruit.setRotationFlow(doctor.getRotationFlow());
					lastRecruit.setRotationName(doctor.getRotationName());
				}
				recruitResult=jsResDoctorRecruitBiz.updateRecruit(lastRecruit);
			}
            if (doctorResult != com.pinde.core.common.GlobalConstant.ZERO_LINE && recruitResult != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return com.pinde.core.common.GlobalConstant.ONE_LINE;
			}else{
                return com.pinde.core.common.GlobalConstant.ZERO_LINE;
			}
	}else{
            return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}
}


	@Override
	public List<JsResDoctorOrgHistoryExt> seearchInfoByFlow(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList,
															List<String> docTypeList,ResDoctor resDoctor, List<String> orgFlowList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("docOrgHistory", docOrgHistory);
		paramMap.put("resDoctor", resDoctor);
		if(changeStatusIdList != null && !changeStatusIdList.isEmpty()){
			paramMap.put("changeStatusIdList", changeStatusIdList);
		}
		if(orgFlowList!=null && !orgFlowList.isEmpty()){
			paramMap.put("orgFlowList", orgFlowList);
		}
		if(docTypeList != null && !docTypeList.isEmpty()){
			paramMap.put("docTypeList", docTypeList);
		}
		return docOrgHistoryExtMapper.searchDoctorOrgHistoryExtList(paramMap);
	}
	@Override
	public List<JsResDoctorOrgHistoryExt> seearchInfoByFlow1(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList,
															List<String> docTypeList,ResDoctor resDoctor, List<String> orgFlowList,List<String> sessionNumbers) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("docOrgHistory", docOrgHistory);
		paramMap.put("resDoctor", resDoctor);
		paramMap.put("sessionNumbers", sessionNumbers);
		if(changeStatusIdList != null && !changeStatusIdList.isEmpty()){
			paramMap.put("changeStatusIdList", changeStatusIdList);
		}
		if(orgFlowList!=null && !orgFlowList.isEmpty()){
			paramMap.put("orgFlowList", orgFlowList);
		}
		if(docTypeList != null && !docTypeList.isEmpty()){
			paramMap.put("docTypeList", docTypeList);
		}
		return docOrgHistoryExtMapper.searchDoctorOrgHistoryExtList(paramMap);
	}


	/****************************高******校******管******理******员******角******色************************************************/

	@Override
	public List<JsResDoctorOrgHistoryExt> seearchInfoByFlowForUni(ResDoctorOrgHistory orgHistory, List<String> changeStatusIdList,
																  ResDoctor doctor,List<String> docTypeList) {
		if(StringUtil.isBlank(doctor.getWorkOrgId())&&StringUtil.isBlank(doctor.getWorkOrgName())){
			return null;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("docOrgHistory", orgHistory);
		paramMap.put("resDoctor", doctor);
		if(changeStatusIdList != null && !changeStatusIdList.isEmpty()){
			paramMap.put("changeStatusIdList", changeStatusIdList);
		}
		if(docTypeList != null && !docTypeList.isEmpty()){
			paramMap.put("docTypeList", docTypeList);
		}
		return docOrgHistoryExtMapper.searchDoctorOrgHistoryExtListForUni(paramMap);
	}

	@Override
	public List<JsResDoctorOrgHistoryExt> searchDoctorOrgHistoryExtListForUni(ResDoctorOrgHistory orgHistory, List<String> changeStatusIdList, ResDoctor doctor) {
		if(StringUtil.isBlank(doctor.getWorkOrgId())&&StringUtil.isBlank(doctor.getWorkOrgName())){
			return null;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("docOrgHistory", orgHistory);
		paramMap.put("resDoctor", doctor);
		if(changeStatusIdList != null && !changeStatusIdList.isEmpty()){
			paramMap.put("changeStatusIdList", changeStatusIdList);
		}
		return docOrgHistoryExtMapper.searchDoctorOrgHistoryExtListForUni(paramMap);
	}

}
