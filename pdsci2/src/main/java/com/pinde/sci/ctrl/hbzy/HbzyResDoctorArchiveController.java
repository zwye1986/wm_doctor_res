package com.pinde.sci.ctrl.hbzy;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbzy.IJszyResDoctorOrgHistoryBiz;
import com.pinde.sci.biz.hbzy.IJszyResDoctorRecruitBiz;
import com.pinde.sci.biz.hbzy.IJszyResDoctorArchiveBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorDelayTeturnBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.hbzy.*;
import com.pinde.sci.enums.res.CertificateTypeEnum;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.hbzy.BaseUserResumeExtInfoForm;
import com.pinde.sci.form.hbzy.UserInfoExtLogForm;
import com.pinde.sci.model.hbzy.JszyDoctorInfoLogExt;
import com.pinde.sci.model.hbzy.JszyResArchDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 江苏中医招录数据存档
 */
@Controller
@RequestMapping("/hbzy/archive")
public class HbzyResDoctorArchiveController extends GeneralController {
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IJszyResDoctorArchiveBiz archiveBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private OrgBizImpl orgBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IJszyResDoctorRecruitBiz jszyResDoctorRecruitBiz;
	@Autowired
	private IResDoctorDelayTeturnBiz resDoctorDelayTeturnBiz;
	@Autowired
	private IJszyResDoctorOrgHistoryBiz resDoctorOrgHistoryBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@RequestMapping("/archiveInfo")
	@ResponseBody
	public synchronized String archiveInfo(String archiveTime, String sessionNumber) throws DocumentException {
		boolean success = false;
		if (StringUtil.isNotBlank(archiveTime)) {
			archiveTime = DateUtil.getDateTime(archiveTime);
			int count = archiveBiz.checkArchive(archiveTime,sessionNumber);
			if (count > 0) {
				return "当前时间已存在存档";
			}
			success = archiveBiz.saveArchiveInfo(archiveTime, sessionNumber);
		}
		if(success){
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return "存档失败";
	}
	@RequestMapping("/addArchive")
	public String addArchive(){
		return "hbzy/archiveTime";
	}

	@RequestMapping(value="/doctorTrendListSun")
	public String doctorRecruitSun(Model model, Integer currentPage, String archiveFlow, String roleFlag, HttpServletRequest request,
								   ResDoctor doctor, SysUser user, String baseId, String jointOrgFlag, String derateFlag, String trainingTypeId,
								   String orgLevel, String[] datas, String graduationYear){
		ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
		if(StringUtil.isNotBlank(graduationYear)){
			resDoctorRecruit.setGraduationYear(graduationYear);
		}
		List<String> jointOrgFlowList=new ArrayList<String>();
		List<String> docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
		SysUser sysuser= GlobalContext.getCurrentUser();
		SysUser sysUser =new SysUser();
		if (StringUtil.isBlank(doctor.getOrgFlow())) {
			if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
				jointOrgFlowList=searchJointOrgList("Y",sysuser.getOrgFlow());
				jointOrgFlowList.add(sysuser.getOrgFlow());
			}
			if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)
					||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.RES_ROLE_SCOPE_SCHOOL)) {
				SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					jointOrgFlowList.add(sysuser.getOrgFlow());
					SysOrg searchOrg=new SysOrg();
					searchOrg.setOrgProvId(sysOrg.getOrgProvId());
					if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
						searchOrg.setOrgCityId(sysOrg.getOrgCityId());
					}
					if(StringUtil.isNotBlank(orgLevel)){
						searchOrg.setOrgLevelId(orgLevel);
						searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
					List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
					for(SysOrg g:exitOrgs){
						List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
						if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
							for(ResJointOrg jointOrg:resJointOrgList){
								if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
									String cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
									if(StringUtil.isNotBlank(cityId)&&cityId.equals(sysOrg.getOrgCityId())){
										jointOrgFlowList.add(jointOrg.getJointOrgFlow());
									}
								}else{
									jointOrgFlowList.add(jointOrg.getJointOrgFlow());
								}
							}
						}
						jointOrgFlowList.add(g.getOrgFlow());
					}
				}else{
					org.setOrgProvId(sysOrg.getOrgProvId());
					if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
						org.setOrgCityId(sysOrg.getOrgCityId());
					}
					if(StringUtil.isNotBlank(orgLevel)){
						org.setOrgLevelId(orgLevel);
						org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
				}
			}
		}else{
			jointOrgFlowList.add(doctor.getOrgFlow());
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgList){
						if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
							String cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
							SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
							if(StringUtil.isNotBlank(cityId)&&cityId.equals(sysOrg.getOrgCityId())){
								String cityId2 = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
								if(StringUtil.isNotBlank(cityId2)&&cityId2.equals(sysOrg.getOrgCityId())){
									jointOrgFlowList.add(jointOrg.getJointOrgFlow());
								}
							}
						}else{
							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}
		}
		if(StringUtil.isNotBlank(user.getIdNo())){
			sysUser.setIdNo(user.getIdNo());
		}
		sysUser.setUserName(user.getUserName());
		if(StringUtil.isNotBlank(derateFlag)){
			if(GlobalConstant.FLAG_Y.equals(derateFlag)){
				doctor.setTrainingTypeId(JszyTrainCategoryEnum.ChineseMedicine.getId());
				resDoctorRecruit.setTrainYear(JszyResTrainYearEnum.ThreeYear.getId());
			}else{
				derateFlag="";
			}
		}else{
			derateFlag="";
		}
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		ResArchiveSequence archiveSequence = archiveBiz.searchResArchiveSequenceByPK(archiveFlow);
		PageHelper.startPage(currentPage,getPageSize(request));
		resDoctorRecruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
		List<JszyResArchDoctorRecruitExt> doctorList=null;
		if (GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
			ServletContext servletContext=request.getServletContext();
			if (servletContext!=null) {
				List<SysDict> sysDictList=(List<SysDict>) servletContext.getAttribute("dictTypeEnum"+"SendSchool"+"List");
				for (SysDict sysDict : sysDictList) {
					String dictId="send_school_"+sysDict.getDictId()+"_org_rel";
					String orgFlow= InitConfig.getSysCfg(dictId);
					doctor.setWorkOrgId(sysDict.getDictId());
					Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
					if(archiveSequence != null){
						String time = archiveSequence.getArchiveTime();
						String sessionNumber = archiveSequence.getSessionNumber();
						String recruitTableName = "recruit_" + time + "_" + sessionNumber;
						String userTableName = "user_" + time + "_" + sessionNumber;
						String doctorTableName = "doctor_" + time + "_" + sessionNumber;
						doctorRecruitMap.put("doctor", doctor);
						doctorRecruitMap.put("user", sysUser);
						doctorRecruitMap.put("derateFlag", derateFlag);
						doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
						doctorRecruitMap.put("sysOrg", org);
						doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
						doctorRecruitMap.put("docTypeList", docTypeList);
						doctorRecruitMap.put("recruitTableName",recruitTableName);
						doctorRecruitMap.put("userTableName",userTableName);
						doctorRecruitMap.put("doctorTableName",doctorTableName);
						List<String> trainTypeIdList = new ArrayList<String>();
						if (StringUtil.isNotBlank(trainingTypeId)) {
							trainTypeIdList.add(trainingTypeId);
						}
						doctorRecruitMap.put("trainTypeIdList",trainTypeIdList);
					}
					if (sysuser.getOrgFlow().equals(orgFlow)) {
						doctorList=archiveBiz.searchDoctorInfoExts(doctorRecruitMap);
						break;
					}else{
						continue;
					}
				}
			}
		}else{
			if(StringUtil.isNotBlank(doctor.getWorkOrgName())){
				List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
				if(sendSchools!=null && sendSchools.size()>0){
					for(SysDict dict :sendSchools){
						if(doctor.getWorkOrgName().equals(dict.getDictName())){
							doctor.setWorkOrgId(dict.getDictId());
						}
					}
				}
			}
			Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
			if(archiveSequence != null){
				String time = archiveSequence.getArchiveTime();
				String sessionNumber = archiveSequence.getSessionNumber();
				String recruitTableName = "recruit_" + time + "_" + sessionNumber;
				String userTableName = "user_" + time + "_" + sessionNumber;
				String doctorTableName = "doctor_" + time + "_" + sessionNumber;
				doctorRecruitMap.put("doctor", doctor);
				doctorRecruitMap.put("user", sysUser);
				doctorRecruitMap.put("derateFlag", derateFlag);
				doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
				doctorRecruitMap.put("sysOrg", org);
				doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
				doctorRecruitMap.put("docTypeList", docTypeList);
				doctorRecruitMap.put("recruitTableName",recruitTableName);
				doctorRecruitMap.put("userTableName",userTableName);
				doctorRecruitMap.put("doctorTableName",doctorTableName);
				List<String> trainTypeIdList = new ArrayList<String>();
				if (StringUtil.isNotBlank(trainingTypeId)) {
					trainTypeIdList.add(trainingTypeId);
				}
				doctorRecruitMap.put("trainTypeIdList",trainTypeIdList);
			}
			doctorList = archiveBiz.searchDoctorInfoExts(doctorRecruitMap);
		}
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("doctorList",doctorList);
		model.addAttribute("datas",datas);
		model.addAttribute("archiveFlow",archiveFlow);
		return  "hbzy/archiveDoctorListZi";
	}
	public List<String> searchJointOrgList(String flag,String Flow) {
		List<String> jointOrgFlowList=new ArrayList<String>();
		if(GlobalConstant.FLAG_Y.equals(flag)){
			List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(Flow);
			if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:resJointOrgList){
					jointOrgFlowList.add(jointOrg.getJointOrgFlow());
				}
			}
		}
		return jointOrgFlowList;
	}
	/**
	 * 医师信息详情
	 */
	@RequestMapping(value={"/{role}/doctor/doctorPassedList"})
	public String doctorPassedList(@PathVariable String role, Model model, String doctorFlow, String studyFlag, String archiveFlow){
		if(StringUtil.isNotBlank(archiveFlow)) {
			model.addAttribute("archiveFlow",archiveFlow);
			ResDoctorRecruit recruit = new ResDoctorRecruit();
			recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());//  页面过滤 方便判断是否允许退回
			recruit.setDoctorFlow(doctorFlow);
			recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			Map<String,Object> paramMap = new HashMap<>();
			ResArchiveSequence archiveSequence = archiveBiz.searchResArchiveSequenceByPK(archiveFlow);
			if(archiveSequence != null){
				String time = archiveSequence.getArchiveTime();
				String sessionNumber = archiveSequence.getSessionNumber();
				String recruitTableName = "RECRUIT_" + time + "_" + sessionNumber;
				paramMap.put("recruitTableName",recruitTableName);
			}
			paramMap.put("recruit",recruit);
			paramMap.put("orderByClause","CREATE_TIME");
			List<ResDoctorRecruitWithBLOBs> recruitList = archiveBiz.searchResDoctorRecruitList(paramMap);
			model.addAttribute("recruitList", recruitList);
			if (StringUtil.isNotBlank(studyFlag)) {
				if (GlobalConstant.FLAG_Y.equals(studyFlag)) {
					model.addAttribute("studyFlag", studyFlag);
				}
			}
			ResRec resRec = new ResRec();
			resRec.setRecTypeId(ResRecTypeEnum.ReturnTraining.getId());
			List<String> operUserFlowList = new ArrayList<String>();
			operUserFlowList.add(doctorFlow);
			ResDocotrDelayTeturn docotrBackTeturn = new ResDocotrDelayTeturn();
			docotrBackTeturn.setDoctorFlow(doctorFlow);
			docotrBackTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
			List<ResDocotrDelayTeturn> backList = resDoctorDelayTeturnBiz.searchInfo(docotrBackTeturn, null, null,null);
//		List<ResRec> recList = resRecBiz.searchRecInfo(resRec, operUserFlowList);
			model.addAttribute("recList", backList);
			ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
			docotrDelayTeturn.setDoctorFlow(doctorFlow);
			docotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
			List<ResDocotrDelayTeturn> delayList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn, null, null,null);
//		List<ResRec> delayList = resRecBiz.searchByRecWithBLOBs(ResRecTypeEnum.Delay.getId(), doctorFlow);
			model.addAttribute("delayList", delayList);
		}
		if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
			return "hbzy/university/doctor/archiveDoctorMain";
		}
		return "hbzy/province/doctor/archiveDoctorMain";
	}
	/**
	 * 查看培训信息
	 * @return
	 */
	@RequestMapping("/getDoctorRecruit")
	public String getDoctorRecruit(String recruitFlow, String doctorFlow,String archiveFlow, Model model) throws DocumentException {
		boolean isLatest = false;//是否最新记录

		ResDoctor currdoctor = new ResDoctor();
		ResArchiveSequence archiveSequence = archiveBiz.searchResArchiveSequenceByPK(archiveFlow);

		ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
		docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
		docotrDelayTeturn.setAuditStatusId("2");
		docotrDelayTeturn.setDoctorFlow(doctorFlow);
		List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn,null,null,null);
		if(resRecList!=null&&!resRecList.isEmpty()) {
			model.addAttribute("haveReturn","Y");
		}
		if(StringUtil.isNotBlank(recruitFlow)){
			ResDoctorRecruit doctorRecruitLog = new ResDoctorRecruit();
			doctorRecruitLog.setRecruitFlow(recruitFlow);
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("recruit",doctorRecruitLog);
			if(archiveSequence != null){
				String time = archiveSequence.getArchiveTime();
				String sessionNumber = archiveSequence.getSessionNumber();
				String recruitTableName = "RECRUIT_" + time + "_" + sessionNumber;
				paramMap.put("recruitTableName",recruitTableName);
			}
			List<ResDoctorRecruitWithBLOBs> doctorRecruitLogList= archiveBiz.searchResDoctorRecruitList(paramMap);
			ResDoctorRecruit doctorRecruit = null;
			if(null != doctorRecruitLogList && doctorRecruitLogList.size()>0) {
				doctorRecruit = doctorRecruitLogList.get(0);
			}
			if(StringUtil.isNotBlank(doctorFlow)) {

				SysUser userLog = new SysUser();
				userLog.setUserFlow(doctorFlow);
				Map<String,Object> paramMap2 = new HashMap<>();
				if(archiveSequence != null){
					String time = archiveSequence.getArchiveTime();
					String sessionNumber = archiveSequence.getSessionNumber();
					String userTableName = "USER_" + time + "_" + sessionNumber;
					paramMap2.put("userTableName",userTableName);
				}
				paramMap2.put("user",userLog);
				List<SysUser> userLogList = archiveBiz.searchSysUserLogList(paramMap2);
				SysUser sysUser = null;
				if (null != userLogList && userLogList.size() > 0) {
					sysUser = userLogList.get(0);
				}
				model.addAttribute("user", sysUser);
			}
			if(doctorRecruit!=null){
				model.addAttribute("doctorRecruit", doctorRecruit);
				Map<String,Object> authParamMap = new HashMap<>();
				DoctorAuth auth = new DoctorAuth();
				auth.setOperUserFlow(doctorFlow);
				if(archiveSequence != null){
					String time = archiveSequence.getArchiveTime();
					String sessionNumber = archiveSequence.getSessionNumber();
					String authTableName = "AUTH_" + time + "_" + sessionNumber;
					authParamMap.put("authTableName",authTableName);
				}
				authParamMap.put("auth",auth);
				List<DoctorAuth> authLogList = archiveBiz.searchAuthLogList(authParamMap);
				if(authLogList != null && authLogList.size() > 0){
					DoctorAuth doctorAuth = authLogList.get(0);
					model.addAttribute("doctorAuth",doctorAuth);
				}
				if(StringUtil.isNotBlank(doctorRecruit.getDoctorFlow()) && !StringUtil.isNotBlank(doctorRecruit.getProveFileUrl())){
					ResDoctorLog doctorLog = new ResDoctorLog();
					doctorLog.setArchiveFlow(archiveFlow);
					doctorLog.setDoctorFlow(doctorRecruit.getDoctorFlow());
					List<ResDoctorLog> doctorLogList = archiveBiz.searchResDoctorLogList(doctorLog);
					ResDoctorLog doctor = null;
					if(null != doctorLogList && doctorLogList.size()>0){
						doctor = doctorLogList.get(0);
					}
					if(doctor!=null){
						model.addAttribute("doctor",doctor);
						String degreeType = doctor.getDegreeCategoryId();
						if(JszyResDegreeCategoryEnum.ClinicMaster.getId().equals(degreeType) || JszyResDegreeCategoryEnum.ClinicDoctor.getId().equals(degreeType)){
							ResUserResumeLog  resumeLog = new ResUserResumeLog();
							resumeLog.setArchiveFlow(archiveFlow);
							resumeLog.setUserFlow(doctorRecruit.getDoctorFlow());
							Map<String,Object> paramMap3 = new HashMap<>();
							if(archiveSequence != null){
								String time = archiveSequence.getArchiveTime();
								String sessionNumber = archiveSequence.getSessionNumber();
								String userTableName = "RESUME_" + time + "_" + sessionNumber;
								paramMap3.put("resumeTableName",userTableName);
							}
							paramMap3.put("resume",resumeLog);
							List<PubUserResume> resUserResumeLogList = archiveBiz.searchResUserResumeLog(paramMap3);
							PubUserResume resume = null;
							BaseUserResumeExtInfoForm userResumeExt = new BaseUserResumeExtInfoForm();
							if(null != resUserResumeLogList && resUserResumeLogList.size()>0){
								resume = resUserResumeLogList.get(0);
							}
							userResumeExt = userResumeBiz.converyToJavaBean(resume.getUserResume(), BaseUserResumeExtInfoForm.class);

							model.addAttribute("userResumeExt", userResumeExt);
							doctorRecruit.setProveFileUrl(userResumeExt.getDegreeUri());

						}
					}
				}
			}

			ResDoctorRecruit lastRecruit = new ResDoctorRecruit();
			lastRecruit.setDoctorFlow(doctorFlow);
			lastRecruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			Map<String,Object> paramMap4 = new HashMap<>();
			paramMap4.put("recruit",lastRecruit);
			paramMap4.put("orderByClause","CREATE_TIME");
			if(archiveSequence != null){
				String time = archiveSequence.getArchiveTime();
				String sessionNumber = archiveSequence.getSessionNumber();
				String recruitTableName = "RECRUIT_" + time + "_" + sessionNumber;
				paramMap4.put("recruitTableName",recruitTableName);
			}
			List<ResDoctorRecruitWithBLOBs> recruitList = archiveBiz.searchResDoctorRecruitList(paramMap4);//searchResDoctorRecruitList(lastRecruit, "CREATE_TIME DESC");
			if(recruitList != null && !recruitList.isEmpty()){
				lastRecruit = recruitList.get(0);
				if(lastRecruit.getRecruitFlow().equals(recruitFlow)){
					isLatest = true;
				}
			}

			//最新记录 && 审核通过
			if(JszyResDoctorAuditStatusEnum.Passed.getId().equals(doctorRecruit.getAuditStatusId()) ){
				ResDoctorOrgHistory docOrgHistory = new ResDoctorOrgHistory();
				docOrgHistory.setDoctorFlow(doctorFlow);
				docOrgHistory.setChangeTypeId(JszyResChangeTypeEnum.BaseChange.getId());
				docOrgHistory.setRecruitFlow(recruitFlow);
				//List<String> changeStatusIdList = new ArrayList<String>();
				//changeStatusIdList.add(JsResChangeApplyStatusEnum.OutApplyWaiting.getId());//待转出审核
				//changeStatusIdList.add(JsResChangeApplyStatusEnum.InApplyWaiting.getId());//待转入审核
				List<ResDoctorOrgHistory> docOrgHistoryList = resDoctorOrgHistoryBiz.searchWaitingChangeOrgHistoryList(docOrgHistory, null);
				if(docOrgHistoryList != null && !docOrgHistoryList.isEmpty()){
					model.addAttribute("docOrgHistoryList", docOrgHistoryList);
					model.addAttribute("latestDocOrgHistory", docOrgHistoryList.get(0));//最新变更记录
				}
				docOrgHistory.setChangeTypeId(JszyResChangeTypeEnum.SpeChange.getId());
				List<ResDoctorOrgHistory> changeSpeList = resDoctorOrgHistoryBiz.searchWaitingChangeOrgHistoryList(docOrgHistory, null);
				if(changeSpeList != null && !changeSpeList.isEmpty()){
					model.addAttribute("changeSpeList", changeSpeList);
					model.addAttribute("lastChangeSpe",changeSpeList.get(0) );
				}
			}
		}
		model.addAttribute("isLatest", isLatest);
		return "hbzy/doctor/trainInfoAchive";
	}
	/**
	 * 个人基本信息
	 * @param model
	 * @return
	 * @throws DocumentException
	 */
	@RequestMapping(value="/doctorInfo")
	public String doctorInfo(String userFlow,String archiveFlow, String viewFlag, Model model) throws DocumentException{
		SysUser userLog = new SysUser();
		userLog.setUserFlow(userFlow);
		Map<String,Object> paramMap = new HashMap<>();
		ResArchiveSequence archiveSequence = archiveBiz.searchResArchiveSequenceByPK(archiveFlow);
		if(archiveSequence != null){
			String time = archiveSequence.getArchiveTime();
			String sessionNumber = archiveSequence.getSessionNumber();
			String userTableName = "USER_" + time + "_" + sessionNumber;
			paramMap.put("userTableName",userTableName);
		}
		paramMap.put("user",userLog);
		List<SysUser> userLogList = archiveBiz.searchSysUserLogList(paramMap);
		SysUser sysUser = null;
		if (null != userLogList && userLogList.size() > 0) {
			sysUser = userLogList.get(0);
		}
		ResDoctorLog doctorLog = new ResDoctorLog();
		doctorLog.setArchiveFlow(archiveFlow);
		doctorLog.setDoctorFlow(userFlow);
		List<ResDoctorLog> doctorLogList = archiveBiz.searchResDoctorLogList(doctorLog);
		ResDoctorLog resDoctor = null;
		if(null != doctorLogList && doctorLogList.size()>0){
			resDoctor = doctorLogList.get(0);
		}
		if(resDoctor!=null){
			if(StringUtil.isNotBlank(resDoctor.getGraduatedId())){
				List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
				if(sysDictList!=null && !sysDictList.isEmpty()){
					for(SysDict dict:sysDictList){
						if(dict.getDictId().equals(resDoctor.getGraduatedId())){
							resDoctor.setGraduatedName(dict.getDictName());
						}
					}
				}
			}
			if(StringUtil.isNotBlank(resDoctor.getDoctorTypeId())&& JszyResDocTypeEnum.Graduate.getId().equals(resDoctor.getDoctorTypeId())){
				if(StringUtil.isNotBlank(resDoctor.getWorkOrgId())){
					List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.SendSchool.getId());
					if(sysDictList!=null && !sysDictList.isEmpty()){
						for(SysDict dict:sysDictList){
							if(dict.getDictId().equals(resDoctor.getWorkOrgId())){
								resDoctor.setWorkOrgName(dict.getDictName());
							}
						}
					}
				}
			}
		}

		model.addAttribute("user", sysUser);
		model.addAttribute("doctor", resDoctor);
		PubUserResume  resumeLog = new PubUserResume();
		resumeLog.setUserFlow(userFlow);
		Map<String,Object> paramMap2 = new HashMap<>();
		if(archiveSequence != null){
			String time = archiveSequence.getArchiveTime();
			String sessionNumber = archiveSequence.getSessionNumber();
			String userTableName = "RESUME_" + time + "_" + sessionNumber;
			paramMap2.put("resumeTableName",userTableName);
		}
		paramMap2.put("pubUserResume",resumeLog);
		List<PubUserResume> resUserResumeLogList = archiveBiz.searchResUserResumeLog(paramMap2);
		PubUserResume resume = null;
		if(null != resUserResumeLogList && resUserResumeLogList.size()>0){
			resume = resUserResumeLogList.get(0);
		}
		BaseUserResumeExtInfoForm userResumeExt = new BaseUserResumeExtInfoForm();
		userResumeExt = userResumeBiz.converyToJavaBean(resume.getUserResume(), BaseUserResumeExtInfoForm.class);
		model.addAttribute("userResumeExt", userResumeExt);

		ResDoctorRecruit recruit=new ResDoctorRecruit();
		recruit.setGraduationYear(DateUtil.getYear());
		recruit.setDoctorFlow(sysUser.getUserFlow());
		recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
		Map<String,Object> paramMap3 = new HashMap<>();
		paramMap3.put("recruit",recruit);
		if(archiveSequence != null){
			String time = archiveSequence.getArchiveTime();
			String sessionNumber = archiveSequence.getSessionNumber();
			String recruitTableName = "RECRUIT_" + time + "_" + sessionNumber;
			paramMap3.put("recruitTableName",recruitTableName);
		}
		List<ResDoctorRecruitWithBLOBs> recruits=archiveBiz.searchResDoctorRecruitList(paramMap3);
		String canSave="Y";
		if(recruits!=null&&recruits.size()>0)
		{
			String recruitFlow=recruits.get(0).getRecruitFlow();
		}
		model.addAttribute("canSave",canSave);
		//"N".equals(canSave) 含义为有一条培训记录，结业考核年份是当前年，并且结业资格审查省厅通过的。学员无法修改个人信息
		if(GlobalConstant.FLAG_Y.equals(viewFlag)||"N".equals(canSave)){
			return "hbzy/archiveDoctorInfo";
		}
		return "hbzy/doctor/editDoctorInfo";
	}
	/**
	 * 导出excel
	 * @param
	 * @return
	 */
	@RequestMapping(value="/exportDoctor")
	public void exportDoctor(String archiveFlow, HttpServletResponse response, String sessionNumber, ResDoctor doctor, SysUser user, String baseId, String jointOrgFlag, String derateFlag, String orgLevel, String[] datas, String graduationYear)throws Exception{
		String[] headLines = null;
		headLines = new String[]{
				"住院医师规范化培训"+sessionNumber+"级招收对象花名册",
				"省（区、市）卫生计生行政部门（盖章）：江苏省卫生和计划生育委员会        填报人：顾丰      联系方式：025-83620704",
		};
		String[] titles = new String[]{
				":编号",
				"sysUser.userName:姓名",
				"sysUser.sexName:性别",
				"sysUser.userBirthday:出生年月",
				"sysUser.nationName:民族",
				"sysUser.idNo:身份证号码（若为其他证件，需注明）",
				"sysUser.userPhone:联系方式（手机）",
				"sysUser.userEmail:联系方式（邮箱）",
				"userResumeExtMap.graduatedName:本科毕业院校及专业",
				"userResumeExtMap.specialized:本科毕业院校及专业",
				"userResumeExtMap.graduationTime:毕业时间",
				"sysUser.educationName:最高学历",
				"doctor.graduatedName:最高学历毕业院校",
				"doctor.specialized:最高学历毕业专业",
				"doctor.graduationTime:获得最高学历时间",
				"doctor.doctorTypeName:身份类型（单位人/社会人）",
				"doctor.workOrgName:派出单位（限“单位人”填写）",
				"doctor.orgName:培训基地（若在协同单位，需注明）",
				"doctor.trainingSpeName:培训专业",
				"recruit.recruitDate:参训时间",
				"recruit.trainYear:计划参训时限",
		};
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
		SysUser exSysUser=GlobalContext.getCurrentUser();
		SysOrg currenOrg = orgBiz .readSysOrg(exSysUser.getOrgFlow());
		List<String>jointOrgFlowList=new ArrayList<String>();
		List<UserInfoExtLogForm> userExtForms=new ArrayList<UserInfoExtLogForm>();
		ResDoctorRecruit  recruit=new ResDoctorRecruit();
		if(StringUtil.isNotBlank(graduationYear)){
			recruit.setGraduationYear(graduationYear);
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			if(OrgLevelEnum.CountryOrg.getId().equals(currenOrg.getOrgLevelId())&& StringUtil.isBlank(doctor.getOrgFlow())){
				jointOrgFlowList.add(exSysUser.getOrgFlow());
				List<ResJointOrg> resJointOrgsList=jointOrgBiz.searchResJointByOrgFlow(exSysUser.getOrgFlow());
				if(resJointOrgsList!=null&&!resJointOrgsList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgsList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
			}
			if(OrgLevelEnum.CountryOrg.getId().equals(currenOrg.getOrgLevelId())&& StringUtil.isNotBlank(doctor.getOrgFlow())){
				jointOrgFlowList.add(doctor.getOrgFlow());
			}
			if(!OrgLevelEnum.CountryOrg.getId().equals(currenOrg.getOrgLevelId())){
				jointOrgFlowList.add(exSysUser.getOrgFlow());
			}
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)){
			if(StringUtil.isNotBlank(doctor.getOrgFlow())){
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					List<ResJointOrg> resJointOrgsList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
					for(ResJointOrg jointOrg:resJointOrgsList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
				jointOrgFlowList.add(doctor.getOrgFlow());
			}else{
				SysOrg sysOrg=orgBiz.readSysOrg(exSysUser.getOrgFlow());
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					jointOrgFlowList.add(exSysUser.getOrgFlow());
					SysOrg searchOrg=new SysOrg();
					searchOrg.setOrgProvId(sysOrg.getOrgProvId());
					if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
						searchOrg.setOrgCityId(sysOrg.getOrgCityId());
					}
					if(StringUtil.isNotBlank(orgLevel)){
						searchOrg.setOrgLevelId(orgLevel);
						searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
					List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
					for(SysOrg g:exitOrgs){
						List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
						if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
							for(ResJointOrg jointOrg:resJointOrgList){
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
						jointOrgFlowList.add(g.getOrgFlow());
					}
				}else{
					org.setOrgProvId(sysOrg.getOrgProvId());
					if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
						org.setOrgCityId(sysOrg.getOrgCityId());
					}
					if(StringUtil.isNotBlank(orgLevel)){
						org.setOrgLevelId(orgLevel);
						org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
				}
			}
		}
		if(StringUtil.isNotBlank(derateFlag)){
			if(GlobalConstant.FLAG_Y.equals(derateFlag)){
				doctor.setTrainingTypeId(JszyTrainCategoryEnum.ChineseMedicine.getId());
				recruit.setTrainYear(JszyResTrainYearEnum.ThreeYear.getId());
			}else{
				derateFlag="";
			}
		}else{
			derateFlag="";
		}
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
		ResRec resRec =new ResRec();
		resRec.setRecTypeId(ResRecTypeEnum.ReturnTraining.getId());
		if(StringUtil.isNotBlank(doctor.getWorkOrgName())){
			List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
			if(sendSchools!=null && sendSchools.size()>0){
				for(SysDict dict :sendSchools){
					if(doctor.getWorkOrgName().equals(dict.getDictName())){
						doctor.setWorkOrgId(dict.getDictId());
					}
				}
			}
		}
		if(StringUtil.isNotBlank(archiveFlow)) {
			ResArchiveSequence archiveSequence = archiveBiz.searchResArchiveSequenceByPK(archiveFlow);

			Map<String,Object> paramMap=new HashMap<String,Object>();
			if(archiveSequence != null){
				String time = archiveSequence.getArchiveTime();
				String tempSessionNumber = archiveSequence.getSessionNumber();
				String recruitTableName = "RECRUIT_" + time + "_" + tempSessionNumber;
				String userTableName = "USER_" + time + "_" + tempSessionNumber;
				String doctorTableName = "DOCTOR_" + time + "_" + tempSessionNumber;
				String resumeTableName = "RESUME_" + time + "_" + tempSessionNumber;
				paramMap.put("recruitTableName",recruitTableName);
				paramMap.put("userTableName",userTableName);
				paramMap.put("doctorTableName",doctorTableName);
				paramMap.put("resumeTableName",resumeTableName);
			}
			paramMap.put("resDoctorRecruit", recruit);
			paramMap.put("doctor", doctor);
			paramMap.put("user", user);
			paramMap.put("derateFlag", derateFlag);
			paramMap.put("jointOrgFlowList", jointOrgFlowList);
			paramMap.put("sysOrg", org);
			paramMap.put("docTypeList", docTypeList);
			List<JszyDoctorInfoLogExt> doctorInfoExts = archiveBiz.searchDoctorInfoResume(paramMap);
			List<Map<String, Object>> jointOrgs = jszyResDoctorRecruitBiz.searchJointOrgList();
			Map<Object, Object> orgAndJointNameMap = new HashMap<Object, Object>();
			if (jointOrgs != null && !jointOrgs.isEmpty()) {
				for (Map<String, Object> en : jointOrgs) {
					Object key = en.get("key");
					Object value = en.get("value");
					orgAndJointNameMap.put(key, value);
				}
			}
			if (doctorInfoExts != null && !doctorInfoExts.isEmpty()) {
				for (JszyDoctorInfoLogExt d : doctorInfoExts) {
					UserInfoExtLogForm userInfoExtForm = new UserInfoExtLogForm();
					if (orgAndJointNameMap.containsKey(d.getOrgFlow())) {
						d.getResDoctor().setOrgName(orgAndJointNameMap.get(d.getOrgFlow()) + "(" + d.getOrgName() + ")");
					}
					BaseUserResumeExtInfoForm userResumeExt = new BaseUserResumeExtInfoForm();
					userResumeExt = userResumeBiz.converyToJavaBean(d.getUserResume().getUserResume(), BaseUserResumeExtInfoForm.class);
					Map<String,String> infoMap = new HashMap<>();
					infoMap.put("graduatedName",userResumeExt.getGraduatedName());
					infoMap.put("specialized",userResumeExt.getSpecialized());
					infoMap.put("graduationTime",userResumeExt.getGraduationTime());
					userInfoExtForm.setUserResumeExtMap(infoMap);
					SysUser su = d.getSysUser();
					String cretTypeId = su.getCretTypeId();
					if (StringUtil.isNotBlank(cretTypeId)) {
						if (!CertificateTypeEnum.Shenfenzheng.getId().equals(cretTypeId)) {
							su.setIdNo(su.getIdNo() + "(" + CertificateTypeEnum.getNameById(cretTypeId) + ")");
						}
					}
					userInfoExtForm.setSysUser(su);
					userInfoExtForm.setDoctor(d.getResDoctor());
					ResDoctorRecruit recruit3 = d;
					userInfoExtForm.setRecruit(recruit3);
					if (StringUtil.isNotBlank(d.getTrainYear())) {
						d.setTrainYear(JszyResTrainYearEnum.getNameById(d.getTrainYear()));
					}
					userExtForms.add(userInfoExtForm);
				}
			}
		}
		String fileName = "住院医师规范化培训"+sessionNumber+"级招收对象花名册.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		ExcleUtile.exportSimpleExcleWithHeadlin(headLines, titles, userExtForms, response.getOutputStream());
		response.setContentType("application/octet-stream;charset=UTF-8");
	}
	/**
	 * 导出excel
	 * @param response
	 * @param doctor
	 * @param user
	 * @param baseId
	 * @param jointOrgFlag
	 * @param derateFlag
	 * @param orgLevel
	 * @param datas
	 * @param graduationYear
	 * @throws Exception
	 */
	@RequestMapping(value="/exportForDetail")
	public void exportForDetail( HttpServletResponse response,String archiveFlow,ResDoctor doctor,SysUser user,String baseId,String jointOrgFlag,String derateFlag,String orgLevel,String[] datas,String graduationYear)throws Exception{
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
		SysUser exSysUser=GlobalContext.getCurrentUser();
		List<String>jointOrgFlowList=new ArrayList<String>();
		ResDoctorRecruit  recruit=new ResDoctorRecruit();
		if(StringUtil.isNotBlank(graduationYear)){
			recruit.setGraduationYear(graduationYear);
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgsList=jointOrgBiz.searchResJointByOrgFlow(exSysUser.getOrgFlow());
				if(resJointOrgsList!=null&&!resJointOrgsList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgsList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
				jointOrgFlowList.add(exSysUser.getOrgFlow());
			}else{
				jointOrgFlowList.add(exSysUser.getOrgFlow());
			}
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)){
			if(StringUtil.isNotBlank(doctor.getOrgFlow())){
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					List<ResJointOrg> resJointOrgsList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
					for(ResJointOrg jointOrg:resJointOrgsList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
				jointOrgFlowList.add(doctor.getOrgFlow());
			}else{
				SysOrg sysOrg=orgBiz.readSysOrg(exSysUser.getOrgFlow());
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					jointOrgFlowList.add(exSysUser.getOrgFlow());
					SysOrg searchOrg=new SysOrg();
					searchOrg.setOrgProvId(sysOrg.getOrgProvId());
					if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
						searchOrg.setOrgCityId(sysOrg.getOrgCityId());
					}
					if(StringUtil.isNotBlank(orgLevel)){
						searchOrg.setOrgLevelId(orgLevel);
						searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
					List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
					for(SysOrg g:exitOrgs){
						List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
						if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
							for(ResJointOrg jointOrg:resJointOrgList){
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
						jointOrgFlowList.add(g.getOrgFlow());
					}
				}else{
					org.setOrgProvId(sysOrg.getOrgProvId());
					if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
						org.setOrgCityId(sysOrg.getOrgCityId());
					}
					if(StringUtil.isNotBlank(orgLevel)){
						org.setOrgLevelId(orgLevel);
						org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
				}
			}
		}
		if(StringUtil.isNotBlank(derateFlag)){
			if(GlobalConstant.FLAG_Y.equals(derateFlag)){
				doctor.setTrainingTypeId(JszyTrainCategoryEnum.ChineseMedicine.getId());
				recruit.setTrainYear(JszyResTrainYearEnum.ThreeYear.getId());
			}else{
				derateFlag="";
			}
		}else{
			derateFlag="";
		}
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
		ResArchiveSequence archiveSequence = archiveBiz.searchResArchiveSequenceByPK(archiveFlow);

		Map<String,Object> paramMap=new HashMap<String,Object>();
		if(archiveSequence != null){
			String time = archiveSequence.getArchiveTime();
			String tempSessionNumber = archiveSequence.getSessionNumber();
			String recruitTableName = "RECRUIT_" + time + "_" + tempSessionNumber;
			String userTableName = "USER_" + time + "_" + tempSessionNumber;
			String doctorTableName = "DOCTOR_" + time + "_" + tempSessionNumber;
			String resumeTableName = "RESUME_" + time + "_" + tempSessionNumber;
			paramMap.put("recruitTableName",recruitTableName);
			paramMap.put("userTableName",userTableName);
			paramMap.put("doctorTableName",doctorTableName);
			paramMap.put("resumeTableName",resumeTableName);
		}
		paramMap.put("resDoctorRecruit", recruit);
		paramMap.put("doctor", doctor);
		paramMap.put("user", user);
		paramMap.put("derateFlag", derateFlag);
		paramMap.put("jointOrgFlowList", jointOrgFlowList);
		paramMap.put("sysOrg", org);
		paramMap.put("docTypeList", docTypeList);
		List<JszyDoctorInfoLogExt> doctorInfoExts=archiveBiz.searchDoctorInfoResume(paramMap);
		jszyResDoctorRecruitBiz.exportForDetailLog(doctorInfoExts, response);
	}
}
