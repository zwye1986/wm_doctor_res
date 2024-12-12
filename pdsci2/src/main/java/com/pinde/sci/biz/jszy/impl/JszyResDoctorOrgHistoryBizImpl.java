package com.pinde.sci.biz.jszy.impl;


import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.enums.RecDocCategoryEnum;
import com.pinde.core.model.ResDoctorRecruit;
import com.pinde.core.model.ResDoctorRecruitWithBLOBs;
import com.pinde.core.model.SysOrg;
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
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.ResDoctorOrgHistoryMapper;
import com.pinde.sci.dao.jszy.JszyResDoctorOrgHistoryExtMapper;
import com.pinde.sci.model.jszy.JszyResDoctorOrgHistoryExt;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorOrgHistory;
import com.pinde.sci.model.mo.ResDoctorOrgHistoryExample;
import com.pinde.sci.model.mo.ResDoctorOrgHistoryExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
                docOrgHistory.setChangeStatusName(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.getNameById(changeStatusId));
				docOrgHistory.setInDate(DateUtil.getCurrDateTime());
				String msgTitle = "基地变更申请审核结果";
				String msgContent = null;
                if (com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyPass.getId().equals(changeStatusId)) {//转入通过==》修改最新培训记录

					//获取最新培训记录
					if(StringUtil.isNotBlank(doctorFlow)){
						ResDoctorRecruit docRecruit =  new ResDoctorRecruit();
						docRecruit.setDoctorFlow(doctorFlow);
                        docRecruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
						List<com.pinde.core.model.ResDoctorRecruit> docRecruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(docRecruit, "CREATE_TIME DESC");
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
                                    if (StringUtil.isNotBlank(time) && com.pinde.core.common.GlobalConstant.FLAG_N.equals(chooseFlag)) {
										docRecruitWithBLOBs.setSessionNumber(time);
										if(StringUtil.isNotBlank(docRecruit.getTrainYear())){
											int year =0;
                                            if (com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId().equals(docRecruit.getTrainYear())) {
												year=1;
											}
                                            if (com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId().equals(docRecruit.getTrainYear())) {
												year=2;
											}
                                            if (com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId().equals(docRecruit.getTrainYear())) {
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
                                    if (StringUtil.isNotBlank(time) && com.pinde.core.common.GlobalConstant.FLAG_N.equals(chooseFlag)) {
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
            lastRecruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
            if (doctorResult != com.pinde.core.common.GlobalConstant.ZERO_LINE && recruitResult != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return com.pinde.core.common.GlobalConstant.ONE_LINE;
            } else {
                return com.pinde.core.common.GlobalConstant.ZERO_LINE;
            }
        } else {
            return com.pinde.core.common.GlobalConstant.ZERO_LINE;
        }
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

}
