package com.pinde.sci.biz.jszy.impl;


import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyResDoctorOrgHistoryBiz;
import com.pinde.sci.biz.jszy.IJszyResDoctorRecruitBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.ResDoctorOrgHistoryMapper;
import com.pinde.sci.dao.jszy.JszyResDoctorOrgHistoryExtMapper;
import com.pinde.sci.enums.jszy.JszyResChangeApplySpeEnum;
import com.pinde.sci.enums.jszy.JszyResChangeApplyStatusEnum;
import com.pinde.sci.enums.jszy.JszyResTrainYearEnum;
import com.pinde.sci.enums.jszy.JszyTrainCategoryEnum;
import com.pinde.sci.enums.res.RecDocCategoryEnum;
import com.pinde.sci.model.jszy.JszyResDoctorOrgHistoryExt;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.ResDoctorOrgHistoryExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JszyResDoctorOrgHistoryBizImpl implements IJszyResDoctorOrgHistoryBiz {

	@Autowired
	private ResDoctorOrgHistoryMapper docOrgHistoryMapper;
	@Autowired
	private JszyResDoctorOrgHistoryExtMapper docOrgHistoryExtMapper;
	@Autowired
	private IJszyResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;

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
		Criteria criteria =  example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
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
	public List<JszyResDoctorOrgHistoryExt> searchDoctorOrgHistoryExtList(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList, ResDoctor resDoctor, List<String> orgFlowList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("docOrgHistory", docOrgHistory);
		paramMap.put("resDoctor", resDoctor);
		if(changeStatusIdList != null && !changeStatusIdList.isEmpty()){
			paramMap.put("changeStatusIdList", changeStatusIdList);
		}
		if(orgFlowList!=null && !orgFlowList.isEmpty()){
			paramMap.put("orgFlowList", orgFlowList);
		}
		return docOrgHistoryExtMapper.searchDoctorOrgHistoryExtList(paramMap);
	}

	@Override
	public int auditTurnInOrg(String recordFlow, String changeStatusId, String doctorFlow,String time,String chooseFlag) {
		if(StringUtil.isNotBlank(recordFlow) && StringUtil.isNotBlank(changeStatusId)){
			ResDoctorOrgHistory docOrgHistory = readDocOrgHistory(recordFlow);
			if(docOrgHistory != null){
				docOrgHistory.setChangeStatusId(changeStatusId);
				docOrgHistory.setChangeStatusName(JszyResChangeApplyStatusEnum.getNameById(changeStatusId));
				docOrgHistory.setInDate(DateUtil.getCurrDateTime());
				String msgTitle = "基地变更申请审核结果";
				String msgContent = null;
				if(JszyResChangeApplyStatusEnum.GlobalApplyPass.getId().equals(changeStatusId)){//转入通过==》修改最新培训记录

					//获取最新培训记录
					if(StringUtil.isNotBlank(doctorFlow)){
						ResDoctorRecruit docRecruit =  new ResDoctorRecruit();
						docRecruit.setDoctorFlow(doctorFlow);
						docRecruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
						List<ResDoctorRecruit> docRecruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(docRecruit, "CREATE_TIME DESC");
						if(docRecruitList != null && !docRecruitList.isEmpty()){
							docRecruit =  docRecruitList.get(0);

							String orgFlow = docOrgHistory.getOrgFlow();
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
									if(StringUtil.isNotBlank(time)&&GlobalConstant.FLAG_N.equals(chooseFlag)){
										docRecruitWithBLOBs.setSessionNumber(time);
										if(StringUtil.isNotBlank(docRecruit.getTrainYear())){
											int year =0;
											if(JszyResTrainYearEnum.OneYear.getId().equals(docRecruit.getTrainYear())){
												year=1;
											}
											if(JszyResTrainYearEnum.TwoYear.getId().equals(docRecruit.getTrainYear())){
												year=2;
											}
											if(JszyResTrainYearEnum.ThreeYear.getId().equals(docRecruit.getTrainYear())){
												year=3;
											}
											docRecruitWithBLOBs.setGraduationYear((Integer.parseInt(time)+year)+"");

										}
									}
									if(StringUtil.isNotBlank(time)){
										docOrgHistory.setSessionNumber(time);
									}else{
										docOrgHistory.setSessionNumber(docRecruit.getSessionNumber());
									}
									//变更记录
									saveDocOrgHistory(docOrgHistory);
									//Doctor回写
									ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
									doctor.setOrgFlow(orgFlow);
									doctor.setOrgName(sysOrg.getOrgName());
									if(StringUtil.isNotBlank(time)&&GlobalConstant.FLAG_N.equals(chooseFlag)){
										doctor.setSessionNumber(time);
									}
									doctorBiz.editDoctor(doctor, null);

									msgContent = "基地变更申请（" +docOrgHistory.getHistoryOrgName() +" → "+docOrgHistory.getOrgName()+"）审核结果：" + docOrgHistory.getChangeStatusName() + "。";
									msgBiz.addSysMsg(doctorFlow, msgTitle , msgContent);

									return jsResDoctorRecruitBiz.saveDoctorRecruit(docRecruitWithBLOBs);
								}
							}
						}
					}
				}else{//转入不通过
					msgContent = "基地变更申请（" +docOrgHistory.getHistoryOrgName() +" → "+docOrgHistory.getOrgName()+"）审核结果：" + docOrgHistory.getChangeStatusName() + "。";
					msgBiz.addSysMsg(doctorFlow, msgTitle , msgContent);
					return saveDocOrgHistory(docOrgHistory);
				}
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public ResDoctorOrgHistory readDocOrgHistory(String recordFlow) {
		return docOrgHistoryMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public List<ResDoctorOrgHistory> searchWaitingChangeOrgHistoryList(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList) {
		ResDoctorOrgHistoryExample example = new ResDoctorOrgHistoryExample();
		Criteria criteria =  example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
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
				docOrgHistory.setChangeStatusName(JszyResChangeApplyStatusEnum.getNameById(history.getChangeStatusId()));
				if(StringUtil.isBlank(time)){
					if(StringUtil.isNotBlank(docOrgHistory.getChangeStatusId())&&JszyResChangeApplyStatusEnum.InApplyUnPass.getId().equals(docOrgHistory.getChangeStatusId())){
						docOrgHistory.setInDate(DateUtil.getCurrDateTime());
					}else{
						docOrgHistory.setOutDate(DateUtil.getCurrDateTime());
					}
				}else{
					if(StringUtil.isNotBlank(docOrgHistory.getChangeStatusId())&&JszyResChangeApplyStatusEnum.InApplyUnPass.getId().equals(docOrgHistory.getChangeStatusId())){
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
				if(JszyResChangeApplyStatusEnum.InApplyWaiting.getId().equals(history.getChangeStatusId())){//通过==》带转入
					msgContent = "基地变更申请（" +docOrgHistory.getHistoryOrgName() +" → "+docOrgHistory.getOrgName()+"）审核结果：转出审核通过。";
				}else{
					msgContent = "基地变更申请（" +docOrgHistory.getHistoryOrgName() +" → "+docOrgHistory.getOrgName()+"）审核结果：" + docOrgHistory.getChangeStatusName() + "。";
				}
				msgBiz.addSysMsg(history.getDoctorFlow(), msgTitle , msgContent);
				return saveDocOrgHistory(docOrgHistory);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	/**
	 * @param docOrgHistory
	 * @return
	 */
	@Override
	public int auditChangeOrg(ResDoctorOrgHistory docOrgHistory) {
		if (docOrgHistory != null) {
			String doctorFlow = docOrgHistory.getDoctorFlow();
			if (JszyResChangeApplyStatusEnum.GlobalApplyPass.getId().equals(docOrgHistory.getChangeStatusId())) {//省厅通过==》修改最新培训记录
				//获取最新培训记录
				if (StringUtil.isNotBlank(doctorFlow)) {
					ResDoctorRecruit docRecruit = new ResDoctorRecruit();
					docRecruit.setDoctorFlow(doctorFlow);
					docRecruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
					List<ResDoctorRecruit> docRecruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(docRecruit, "CREATE_TIME DESC");
					if (docRecruitList != null && !docRecruitList.isEmpty()) {
						docRecruit = docRecruitList.get(0);

						String orgFlow = docOrgHistory.getOrgFlow();
						if (StringUtil.isNotBlank(orgFlow)) {
							SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);//获取变更机构信息
							if (sysOrg != null) {
								//DoctorRecruit回写
								ResDoctorRecruitWithBLOBs docRecruitWithBLOBs = new ResDoctorRecruitWithBLOBs();
								docRecruitWithBLOBs.setRecruitFlow(docRecruit.getRecruitFlow());
								docRecruitWithBLOBs.setOrgFlow(orgFlow);
								docRecruitWithBLOBs.setOrgName(sysOrg.getOrgName());
								docRecruitWithBLOBs.setPlaceId(sysOrg.getOrgCityId());
								docRecruitWithBLOBs.setPlaceName(sysOrg.getOrgCityName());
								String isAgreeOldTrain = docOrgHistory.getIsAgreeOldTrain();
								String changeInGraduationYear = docOrgHistory.getChangeInGraduationYear();
								if (StringUtil.isNotBlank(changeInGraduationYear) && GlobalConstant.FLAG_N.equals(isAgreeOldTrain)) {
									docRecruitWithBLOBs.setGraduationYear(changeInGraduationYear);
								}
								//变更记录
								saveDocOrgHistory(docOrgHistory);
								//Doctor回写
								ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
								doctor.setOrgFlow(orgFlow);
								doctor.setOrgName(sysOrg.getOrgName());
								if (StringUtil.isNotBlank(changeInGraduationYear) && GlobalConstant.FLAG_N.equals(isAgreeOldTrain)) {
									doctor.setGraduationYear(changeInGraduationYear);
								}
								doctorBiz.editDoctor(doctor, null);

								String msgTitle = "基地变更申请审核结果";
								String msgContent = "基地变更申请（" + docOrgHistory.getHistoryOrgName() + " → " + docOrgHistory.getOrgName() + "）审核结果：" + docOrgHistory.getChangeStatusName() + "。";
								msgBiz.addSysMsg(docOrgHistory.getDoctorFlow(), msgTitle, msgContent);

								return jsResDoctorRecruitBiz.saveDoctorRecruit(docRecruitWithBLOBs);
							}
						}
					}
				}
			} else {//转入不通过
				String msgTitle = "基地变更申请审核结果";
				String msgContent = "基地变更申请（" + docOrgHistory.getHistoryOrgName() + " → " + docOrgHistory.getOrgName() + "）审核结果：" + docOrgHistory.getChangeStatusName() + "。";
				msgBiz.addSysMsg(docOrgHistory.getDoctorFlow(), msgTitle, msgContent);
				return saveDocOrgHistory(docOrgHistory);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int changeStatus(ResDoctorOrgHistory history,ResDoctorRecruit doctorRecruit) {
		int doctorResult=0;int recruitResult=0;
		if(history!=null){
			ResDoctor doctor=doctorBiz.readDoctor(history.getDoctorFlow());
			if(doctor!=null){
				doctor.setTrainingSpeId(history.getTrainingSpeId());
				doctor.setTrainingSpeName(history.getTrainingSpeName());
				doctor.setTrainingTypeId(history.getTrainingTypeId());
				doctor.setTrainingTypeName(history.getTrainingTypeName());
				if( !RecDocCategoryEnum.ChineseMedicine.getId().equals(doctor.getTrainingTypeId()))
				{
					doctor.setSecondSpeId("");
					doctor.setSecondSpeName("");
				}
				doctor=rotationBiz.updateDocRotation(doctor);
				doctorResult=doctorBiz.editDoctor(doctor, null);
			}
			ResDoctorRecruitWithBLOBs lastRecruit = new ResDoctorRecruitWithBLOBs();
			lastRecruit.setDoctorFlow(history.getDoctorFlow());
			lastRecruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			List<ResDoctorRecruitWithBLOBs> recruitList = jsResDoctorRecruitBiz.searchRecruitWithBLOBs(lastRecruit);
			if(recruitList != null && !recruitList.isEmpty()){
				lastRecruit = recruitList.get(0);
				lastRecruit.setSpeId(history.getTrainingSpeId());
				lastRecruit.setSpeName(history.getTrainingSpeName());
				lastRecruit.setCatSpeId(history.getTrainingTypeId());
				lastRecruit.setCatSpeName(history.getTrainingTypeName());
				lastRecruit.setGraduationYear(doctorRecruit.getGraduationYear());
				if( !RecDocCategoryEnum.ChineseMedicine.getId().equals(doctor.getTrainingTypeId()))
				{
					lastRecruit.setSecondSpeId("");
					lastRecruit.setSecondSpeName("");
				}
				recruitResult=jsResDoctorRecruitBiz.updateRecruit(lastRecruit);
			}
			if(doctorResult!=GlobalConstant.ZERO_LINE&&recruitResult!=GlobalConstant.ZERO_LINE){
				return GlobalConstant.ONE_LINE;
			}else{
				return GlobalConstant.ZERO_LINE;
			}
	}else{
		return GlobalConstant.ZERO_LINE;	
	}
}

	@Override
	public List<JszyResDoctorOrgHistoryExt> seearchInfoByFlow(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList,ResDoctor resDoctor, List<String> orgFlowList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("docOrgHistory", docOrgHistory);
		paramMap.put("resDoctor", resDoctor);
		if(changeStatusIdList != null && !changeStatusIdList.isEmpty()){
			paramMap.put("changeStatusIdList", changeStatusIdList);
		}
		if(orgFlowList!=null && !orgFlowList.isEmpty()){
			paramMap.put("orgFlowList", orgFlowList);
		}
		return docOrgHistoryExtMapper.searchDoctorOrgHistoryExtList(paramMap);
	}

	@Override
	public List<Map<String,String>> searchChangeOrgInfoByParamMap(Map<String, Object> paramMap) {
		return docOrgHistoryExtMapper.searchChangeOrgInfoByParamMap(paramMap);
	}

	@Override
	public List<Map<String,String>> searchChangeSpeInfoByParamMap(Map<String, Object> paramMap) {
		return docOrgHistoryExtMapper.searchChangeSpeInfoByParamMap(paramMap);
	}

	@Override
	public int auditChangeSpe(ResDoctorOrgHistory docOrgHistory, ResDoctorRecruit doctorRecruit) {
		int doctorResult = 0;
		int recruitResult = 0;
		if (docOrgHistory != null) {
			String doctorFlow = docOrgHistory.getDoctorFlow();
			//仅变更二级专业不需要省厅审核
			if (JszyResChangeApplySpeEnum.GlobalAuditPass.getId().equals(docOrgHistory.getChangeStatusId())
					|| (JszyResChangeApplySpeEnum.BaseAuditPass.getId().equals(docOrgHistory.getChangeStatusId())
					&& GlobalConstant.FLAG_Y.equals(docOrgHistory.getIsOnlySecond()))) {//省厅通过==》修改最新培训记录

				//获取最新培训记录
				if (StringUtil.isNotBlank(doctorFlow)) {
					ResDoctorRecruit docRecruit = new ResDoctorRecruit();
					docRecruit.setDoctorFlow(doctorFlow);
					docRecruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
					List<ResDoctorRecruit> docRecruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(docRecruit, "CREATE_TIME DESC");
					ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
					if (docRecruitList != null && !docRecruitList.isEmpty()) {
						docRecruit = docRecruitList.get(0);

						//Sch_Arrange_Result res_doctor_sch_process 轮转记录删除
						//判断修改了一阶段还是二阶段
						//修改一阶段
						if(!docOrgHistory.getHistoryTrainingTypeId().equals(docOrgHistory.getTrainingTypeId())){
							Map<String,Object> paramMap = new HashMap<>();
							paramMap.put("doctorFlow",doctorFlow);
							paramMap.put("rotationFlow",doctor.getRotationFlow());
							List<SchArrangeResult> arrangeResults =  resultBiz.searchSchArrangeResultBySpeAndDoc(paramMap);
							if(arrangeResults != null && arrangeResults.size() > 0){
								for(SchArrangeResult tempResult : arrangeResults){
									resultBiz.delResultByResultFlow(tempResult.getResultFlow());
								}
							}
						}
						// 修改二阶段 doctor.getSecondSpeId()为空则以前没有二阶段轮转记录
						if(StringUtil.isNotBlank(doctor.getSecondSpeId())){
							if(!doctor.getSecondSpeId().equals(docOrgHistory.getSecondSpeId())){
								Map<String,Object> paramMap = new HashMap<>();
								paramMap.put("doctorFlow",doctorFlow);
                                paramMap.put("rotationFlow",doctor.getSecondRotationFlow());
								List<SchArrangeResult> arrangeResults =  resultBiz.searchSchArrangeResultBySpeAndDoc(paramMap);
								if(arrangeResults != null && arrangeResults.size() > 0){
									for(SchArrangeResult tempResult : arrangeResults){
										resultBiz.delResultByResultFlow(tempResult.getResultFlow());
									}
								}
							}
						}


						//DoctorRecruit回写
						ResDoctorRecruitWithBLOBs docRecruitWithBLOBs = new ResDoctorRecruitWithBLOBs();
						docRecruitWithBLOBs.setRecruitFlow(docRecruit.getRecruitFlow());
						String graduationYear = doctorRecruit.getGraduationYear();
						if (StringUtil.isNotBlank(graduationYear)) {
							docRecruitWithBLOBs.setGraduationYear(graduationYear);
						}
						docRecruitWithBLOBs.setSpeId(docOrgHistory.getTrainingSpeId());
						docRecruitWithBLOBs.setSpeName(docOrgHistory.getTrainingSpeName());
						docRecruitWithBLOBs.setCatSpeId(docOrgHistory.getTrainingTypeId());
						docRecruitWithBLOBs.setCatSpeName(docOrgHistory.getTrainingTypeName());
						if (JszyTrainCategoryEnum.ChineseMedicine.getId().equals(docOrgHistory.getTrainingTypeId())) {
							docRecruitWithBLOBs.setSecondSpeId(docOrgHistory.getSecondSpeId());
							docRecruitWithBLOBs.setSecondSpeName(docOrgHistory.getSecondSpeName());
						} else {
							docRecruitWithBLOBs.setSecondSpeId("");
							docRecruitWithBLOBs.setSecondSpeName("");
						}
						//修改一阶段
						if(!docOrgHistory.getHistoryTrainingTypeId().equals(docOrgHistory.getTrainingTypeId())){
							List<SchRotation> rotations = rotationBiz.searchSchRotation(docOrgHistory.getTrainingSpeId());
							if(rotations != null && rotations.size()>0){
								docRecruitWithBLOBs.setRotationFlow(rotations.get(0).getRotationFlow());
								docRecruitWithBLOBs.setRotationName(rotations.get(0).getRotationName());
							}
						}
						recruitResult = jsResDoctorRecruitBiz.saveDoctorRecruit(docRecruitWithBLOBs);

						//Doctor回写
						//设置学员的住院医师类型
						SysUser user=null;

						if (doctor != null) {
							//修改一阶段轮转方案
							if(!docOrgHistory.getHistoryTrainingTypeId().equals(docOrgHistory.getTrainingTypeId())){
								List<SchRotation> rotations = rotationBiz.searchSchRotation(docOrgHistory.getTrainingSpeId());
								doctor.setRotationFlow(rotations.get(0).getRotationFlow());
								doctor.setRotationName(rotations.get(0).getRotationName());
								user=new SysUser();
								user.setUserFlow(doctor.getDoctorFlow());
								user.setMedicineTypeId(rotations.get(0).getRotationTypeId());
								user.setMedicineTypeName(rotations.get(0).getRotationTypeName());
							}
							//修改二阶段轮转方案
							if (StringUtil.isNotBlank(docOrgHistory.getSecondSpeId())) {
								if (StringUtil.isBlank(docOrgHistory.getHistorySecondSpeId()) || !docOrgHistory.getHistorySecondSpeId().equals(docOrgHistory.getSecondSpeId())) {
									List<SchRotation> rotations = rotationBiz.searchSchRotation(docOrgHistory.getSecondSpeId());
									if(rotations != null && rotations.size() > 0){
										doctor.setSecondRotationFlow(rotations.get(0).getRotationFlow());
										doctor.setSecondRotationName(rotations.get(0).getRotationName());
									}

								}
							}

							doctor.setTrainingSpeId(docOrgHistory.getTrainingSpeId());
							doctor.setTrainingSpeName(docOrgHistory.getTrainingSpeName());
							doctor.setTrainingTypeId(docOrgHistory.getTrainingTypeId());
							doctor.setTrainingTypeName(docOrgHistory.getTrainingTypeName());
                            doctor.setDoctorCategoryId(docOrgHistory.getTrainingTypeId());
                            doctor.setDoctorCategoryName(docOrgHistory.getTrainingTypeName());
							if (StringUtil.isNotBlank(docOrgHistory.getSecondSpeId())) {
								doctor.setSecondSpeId(docOrgHistory.getSecondSpeId());
								doctor.setSecondSpeName(docOrgHistory.getSecondSpeName());
							} else {
								doctor.setSecondSpeId("");
								doctor.setSecondSpeName("");
								doctor.setSecondRotationFlow("");
								doctor.setSecondRotationName("");
							}
//							doctor = rotationBiz.updateDocRotation(doctor);
							doctorResult = doctorBiz.editDoctor(doctor, null);
						}


						//变更记录
						saveDocOrgHistory(docOrgHistory);

						String msgTitle = "变更专业审核结果";
						StringBuffer msgContent = new StringBuffer();
						msgContent.append("专业变更申请（");
						msgContent.append(docOrgHistory.getHistoryTrainingTypeName());
						msgContent.append("/");
						msgContent.append(docOrgHistory.getHistoryTrainingSpeName());
						if(StringUtil.isNotBlank(docOrgHistory.getHistorySecondSpeId())){
							msgContent.append("/");
							msgContent.append(docOrgHistory.getHistorySecondSpeName());
						}
						msgContent.append(" → ");
						msgContent.append(docOrgHistory.getTrainingTypeName());
						msgContent.append("/");
						msgContent.append(docOrgHistory.getTrainingSpeName());
						if(StringUtil.isNotBlank(docOrgHistory.getSecondSpeId())){
							msgContent.append("/");
							msgContent.append(docOrgHistory.getSecondSpeName());
						}
						msgContent.append("）审核结果：");
						msgContent.append(docOrgHistory.getChangeStatusName());
						msgContent.append("。");
						msgBiz.addSysMsg(docOrgHistory.getDoctorFlow(), msgTitle, msgContent.toString());

						if (doctorResult != GlobalConstant.ZERO_LINE && recruitResult != GlobalConstant.ZERO_LINE) {
							if(user!=null)
								userBiz.updateUser(user);
							return GlobalConstant.ONE_LINE;
						} else {
							return GlobalConstant.ZERO_LINE;
						}
					}
				}
			} else {//转入不通过
				String msgTitle = "变更专业审核结果";
				StringBuffer msgContent = new StringBuffer();
				msgContent.append("专业变更申请（");
				msgContent.append(docOrgHistory.getHistoryTrainingTypeName());
				msgContent.append("/");
				msgContent.append(docOrgHistory.getHistoryTrainingSpeName());
				if(StringUtil.isNotBlank(docOrgHistory.getHistorySecondSpeId())){
					msgContent.append("/");
					msgContent.append(docOrgHistory.getHistorySecondSpeName());
				}
				msgContent.append(" → ");
				msgContent.append(docOrgHistory.getTrainingTypeName());
				msgContent.append("/");
				msgContent.append(docOrgHistory.getTrainingSpeName());
				if(StringUtil.isNotBlank(docOrgHistory.getSecondSpeId())){
					msgContent.append("/");
					msgContent.append(docOrgHistory.getSecondSpeName());
				}
				msgContent.append("）审核结果：");
				msgContent.append(docOrgHistory.getChangeStatusName());
				msgContent.append("。");
				msgBiz.addSysMsg(docOrgHistory.getDoctorFlow(), msgTitle, msgContent.toString());

				return saveDocOrgHistory(docOrgHistory);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public String checkFile(MultipartFile file) {
		List<String> mimeList = new ArrayList<String>();
		if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_mime")))){
			mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_mime")).split(","));
		}
		List<String> suffixList = new ArrayList<String>();
		if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_suffix")))){
			suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_suffix").toLowerCase()).split(","));
		}
		String fileType = file.getContentType();//MIME类型;
		String fileName = file.getOriginalFilename();//文件名
		String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
		if(!(mimeList.contains(fileType) &&  suffixList.contains(suffix.toLowerCase()))){
			return "请上传 "+InitConfig.getSysCfg("res_file_support_suffix")+"格式的文件";
		}
		return GlobalConstant.FLAG_Y;//可执行保存
	}

	@Override
	public String saveCheckFileToDirs(MultipartFile file, String changeRecruitFile, String changeTypeId) {
		String path = GlobalConstant.FLAG_N;
		if(file.getSize() > 0){
			//创建目录
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator + changeRecruitFile + File.separator+ changeTypeId;
			File fileDir = new File(newDir);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			//文件名
			String fileName = file.getOriginalFilename();
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			String originalFilename = PkUtil.getGUID()+suffix;
			File newFile = new File(fileDir, originalFilename);
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("保存文件失败！");
			}


			path = changeRecruitFile + "/"+ changeTypeId + "/" + originalFilename;
		}

		return path;
	}
}
