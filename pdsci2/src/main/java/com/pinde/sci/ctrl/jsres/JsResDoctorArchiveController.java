package com.pinde.sci.ctrl.jsres;

import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.res.IResDoctorDelayTeturnBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.jsres.*;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.jsres.UserInfoExtLogForm;
import com.pinde.sci.model.jsres.JsDoctorInfoLogExt;
import com.pinde.sci.model.jsres.JsResArchDoctorRecruitExt;
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
 * Created by www.0001.Ga on 2017-05-22.
 * 江苏西医招录数据存档
 */
@Controller
@RequestMapping("/jsres/archive")
public class JsResDoctorArchiveController extends GeneralController {
	@Autowired
	private IJsResDoctorBiz jsResDoctorBiz;
	@Autowired
	private IResDoctorArchiveBiz archiveBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private OrgBizImpl orgBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private IResDoctorDelayTeturnBiz resDoctorDelayTeturnBiz;
	@Autowired
	private IJsResDoctorOrgHistoryBiz resDoctorOrgHistoryBiz;
	@Autowired
	private IJsResGraduationApplyBiz jsresGraduationApplyBiz;
	@RequestMapping("/archiveInfo")
	@ResponseBody
	public synchronized String archiveInfo(String archiveTime) throws DocumentException {
		boolean success = false;
		if (StringUtil.isNotBlank(archiveTime)) {
			archiveTime = DateUtil.getDateTime(archiveTime);
			int count = archiveBiz.checkArchive(archiveTime);
			if (count > 0) {
				return "当前时间已存在存档";
			}
			success = archiveBiz.saveArchiveInfo(archiveTime);
		}
		if(success){
			return GlobalConstant.OPRE_SUCCESSED;
		}
		//throw new RuntimeException("");
		return "存档失败";
	}
	@RequestMapping("/addArchive")
	public String addArchive(){
		return "jsres/archiveTime";
	}

	@RequestMapping(value="/doctorTrendListSun")
	public String doctorRecruitSun(Model model, Integer currentPage,String archiveFlow, String roleFlag, HttpServletRequest request, ResDoctor doctor, SysUser user, String baseId, String jointOrgFlag, String derateFlag, String orgLevel, String[] datas, String graduationYear){
		ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
		if(StringUtil.isNotBlank(graduationYear)){
			resDoctorRecruit.setGraduationYear(graduationYear);
		}
		List<String> jointOrgFlowList=new ArrayList<String>();
		List<String>docTypeList=new ArrayList<String>();
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
				doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
				resDoctorRecruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
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
		PageHelper.startPage(currentPage,getPageSize(request));
		resDoctorRecruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		List<JsResArchDoctorRecruitExt> doctorList=null;
		if (GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
			ServletContext servletContext=request.getServletContext();
			if (servletContext!=null) {
				List<SysDict> sysDictList=(List<SysDict>) servletContext.getAttribute("dictTypeEnum"+"SendSchool"+"List");
				for (SysDict sysDict : sysDictList) {
					String dictId="send_school_"+sysDict.getDictId()+"_org_rel";
					String orgFlow= InitConfig.getSysCfg(dictId);
					doctor.setWorkOrgId(sysDict.getDictId());
					if (sysuser.getOrgFlow().equals(orgFlow)) {
						doctorList=archiveBiz.searchDoctorInfoExts(archiveFlow,resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,docTypeList);
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
			doctorList = archiveBiz.searchDoctorInfoExts(archiveFlow,resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,docTypeList);
		}
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("doctorList",doctorList);
		model.addAttribute("datas",datas);
		return  "jsres/archiveDoctorListZi";
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
	public String doctorPassedList(@PathVariable String role,Model model,String doctorFlow,String studyFlag, String archiveFlow){
		if(StringUtil.isNotBlank(archiveFlow)) {
			model.addAttribute("archiveFlow",archiveFlow);
			ResDoctorRecruitLog recruit = new ResDoctorRecruitLog();
			recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());//  页面过滤 方便判断是否允许退回
			recruit.setDoctorFlow(doctorFlow);
			recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			recruit.setArchiveFlow(archiveFlow);
			List<ResDoctorRecruitLogWithBLOBs> recruitList = archiveBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
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
			return "/jsres/university/doctor/archiveDoctorMain";
		}
		return "/jsres/province/doctor/archiveDoctorMain";
	}

	/**
	 * 查看培训信息
	 * @return
	 */
	@RequestMapping("/getDoctorRecruit")
	public String getDoctorRecruit(String recruitFlow, String doctorFlow,String archiveFlow, Model model){
		boolean isLatest = false;//是否最新记录

		ResDoctor currdoctor = new ResDoctor();

		ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
		docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
		docotrDelayTeturn.setAuditStatusId("2");
		docotrDelayTeturn.setDoctorFlow(doctorFlow);
		List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn,null,null,null);
		if(resRecList!=null&&!resRecList.isEmpty()) {
			model.addAttribute("haveReturn","Y");
		}
		if(StringUtil.isNotBlank(recruitFlow)){
			ResDoctorRecruitLog doctorRecruitLog = new ResDoctorRecruitLog();
			doctorRecruitLog.setArchiveFlow(archiveFlow);
			doctorRecruitLog.setRecruitFlow(recruitFlow);
			List<ResDoctorRecruitLogWithBLOBs> doctorRecruitLogList= archiveBiz.searchResDoctorRecruitList(doctorRecruitLog,null);
			ResDoctorRecruitLog doctorRecruit = null;
			if(null != doctorRecruitLogList && doctorRecruitLogList.size()>0) {
				doctorRecruit = doctorRecruitLogList.get(0);
			}
			if(StringUtil.isNotBlank(doctorFlow)) {
				SysUserLog userLog = new SysUserLog();
				userLog.setArchiveFlow(archiveFlow);
				userLog.setUserFlow(doctorFlow);
				List<SysUserLog> userLogList = archiveBiz.searchSysUserLogList(userLog);
				SysUserLog sysUser = null;
				if (null != userLogList && userLogList.size() > 0) {
					sysUser = userLogList.get(0);
				}
				model.addAttribute("user", sysUser);
			}
			if(doctorRecruit!=null){
				model.addAttribute("doctorRecruit", doctorRecruit);
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
						if(JsResDegreeCategoryEnum.ClinicMaster.getId().equals(degreeType) || JsResDegreeCategoryEnum.ClinicDoctor.getId().equals(degreeType)){
							ResUserResumeLog  resumeLog = new ResUserResumeLog();
							resumeLog.setArchiveFlow(archiveFlow);
							resumeLog.setUserFlow(doctorRecruit.getDoctorFlow());
							List<ResUserResumeLog> resUserResumeLogList = archiveBiz.searchResUserResumeLog(resumeLog);
							ResUserResumeLog resume = null;
							if(null != resUserResumeLogList && resUserResumeLogList.size()>0){
								resume = resUserResumeLogList.get(0);
							}
							model.addAttribute("userResumeExt", resume);
							doctorRecruit.setProveFileUrl(resume.getDegreeUri());

						}
					}
				}
			}

			ResDoctorRecruitLog lastRecruit = new ResDoctorRecruitLog();
			lastRecruit.setDoctorFlow(doctorFlow);
			lastRecruit.setArchiveFlow(archiveFlow);
			lastRecruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			List<ResDoctorRecruitLogWithBLOBs> recruitList = archiveBiz.searchResDoctorRecruitList(lastRecruit,"CREATE_TIME DESC");//searchResDoctorRecruitList(lastRecruit, "CREATE_TIME DESC");
			if(recruitList != null && !recruitList.isEmpty()){
				lastRecruit = recruitList.get(0);
				if(lastRecruit.getRecruitFlow().equals(recruitFlow)){
					isLatest = true;
				}
			}

			//最新记录 && 审核通过
			if(JsResDoctorAuditStatusEnum.Passed.getId().equals(doctorRecruit.getAuditStatusId()) ){
				ResDoctorOrgHistory docOrgHistory = new ResDoctorOrgHistory();
				docOrgHistory.setDoctorFlow(doctorFlow);
				docOrgHistory.setChangeTypeId(JsResChangeTypeEnum.BaseChange.getId());
				docOrgHistory.setRecruitFlow(recruitFlow);
				//List<String> changeStatusIdList = new ArrayList<String>();
				//changeStatusIdList.add(JsResChangeApplyStatusEnum.OutApplyWaiting.getId());//待转出审核
				//changeStatusIdList.add(JsResChangeApplyStatusEnum.InApplyWaiting.getId());//待转入审核
				List<ResDoctorOrgHistory> docOrgHistoryList = resDoctorOrgHistoryBiz.searchWaitingChangeOrgHistoryList(docOrgHistory, null);
				if(docOrgHistoryList != null && !docOrgHistoryList.isEmpty()){
					model.addAttribute("docOrgHistoryList", docOrgHistoryList);
					model.addAttribute("latestDocOrgHistory", docOrgHistoryList.get(0));//最新变更记录
				}
				docOrgHistory.setChangeTypeId(JsResChangeTypeEnum.SpeChange.getId());
				List<ResDoctorOrgHistory> changeSpeList = resDoctorOrgHistoryBiz.searchWaitingChangeOrgHistoryList(docOrgHistory, null);
				if(changeSpeList != null && !changeSpeList.isEmpty()){
					model.addAttribute("changeSpeList", changeSpeList);
					model.addAttribute("lastChangeSpe",changeSpeList.get(0) );
				}
			}
		}
		model.addAttribute("isLatest", isLatest);
		return "jsres/doctor/trainInfoAchive";
	}

	/**
	 * 个人基本信息
	 * @param model
	 * @return
	 * @throws DocumentException
	 */
	@RequestMapping(value="/doctorInfo")
	public String doctorInfo(String userFlow,String archiveFlow, String viewFlag, Model model) throws DocumentException{
		SysUserLog userLog = new SysUserLog();
		userLog.setArchiveFlow(archiveFlow);
		userLog.setUserFlow(userFlow);
		List<SysUserLog> userLogList = archiveBiz.searchSysUserLogList(userLog);
		SysUserLog sysUser = null;
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
			if(StringUtil.isNotBlank(resDoctor.getDoctorTypeId())&& JsResDocTypeEnum.Graduate.getId().equals(resDoctor.getDoctorTypeId())){
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
		ResUserResumeLog  resumeLog = new ResUserResumeLog();
		resumeLog.setArchiveFlow(archiveFlow);
		resumeLog.setUserFlow(userFlow);
		List<ResUserResumeLog> resUserResumeLogList = archiveBiz.searchResUserResumeLog(resumeLog);
		ResUserResumeLog resume = null;
		if(null != resUserResumeLogList && resUserResumeLogList.size()>0){
			resume = resUserResumeLogList.get(0);
		}
		model.addAttribute("userResumeExt", resume);

		ResDoctorRecruitLog recruit=new ResDoctorRecruitLog();
		recruit.setGraduationYear(DateUtil.getYear());
		recruit.setDoctorFlow(sysUser.getUserFlow());
		recruit.setArchiveFlow(archiveFlow);
		recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		List<ResDoctorRecruitLogWithBLOBs> recruits=archiveBiz.searchResDoctorRecruitList(recruit,null);
		String canSave="Y";
		if(recruits!=null&&recruits.size()>0)
		{
			String recruitFlow=recruits.get(0).getRecruitFlow();
			JsresGraduationApply apply=jsresGraduationApplyBiz.searchByRecruitFlow(recruitFlow, recruits.get(0).getGraduationYear());
			if(apply!=null){
				if(JsResAsseAuditListEnum.WaitChargePass.getId().equals(apply.getAuditStatusId()))
				{
					canSave="N";
				}else if(JsResAsseAuditListEnum.WaitGlobalPass.getId().equals(apply.getAuditStatusId()))
				{
					canSave="N";
				}else if(JsResAsseAuditListEnum.GlobalPassed.getId().equals(apply.getAuditStatusId()))
				{
					canSave="N";
				}
			}
		}
		model.addAttribute("canSave",canSave);
		//GlobalConstant.FLAG_N.equals(canSave) 含义为有一条培训记录，结业考核年份是当前年，并且结业资格审查省厅通过的。学员无法修改个人信息
		if(GlobalConstant.FLAG_Y.equals(viewFlag)||GlobalConstant.FLAG_N.equals(canSave)){
			return "jsres/archiveDoctorInfo";
		}
		return "jsres/doctor/editDoctorInfo";
	}

	/**
	 * 导出excel
	 * @param
	 * @return
	 */
	@RequestMapping(value="/exportDoctor")
	public void exportDoctor(HttpServletRequest request,String archiveFlow, HttpServletResponse response, String sessionNumber, ResDoctor doctor, SysUser user, String baseId, String jointOrgFlag, String derateFlag, String orgLevel, String[] datas, String graduationYear)throws Exception{
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
				"userResumeExt.graduatedName:本科毕业院校及专业",
				"userResumeExt.specialized:本科毕业院校及专业",
				"userResumeExt.graduationTime:毕业时间",
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
				doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
				recruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
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
		recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
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
			List<JsDoctorInfoLogExt> doctorInfoExts = archiveBiz.searchDoctorInfoResume(recruit, doctor, user, org, jointOrgFlowList, derateFlag, docTypeList, archiveFlow);
			List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
			Map<Object, Object> orgAndJointNameMap = new HashMap<Object, Object>();
			if (jointOrgs != null && !jointOrgs.isEmpty()) {
				for (Map<String, Object> en : jointOrgs) {
					Object key = en.get("key");
					Object value = en.get("value");
					orgAndJointNameMap.put(key, value);
				}
			}
			if (doctorInfoExts != null && !doctorInfoExts.isEmpty()) {
				for (JsDoctorInfoLogExt d : doctorInfoExts) {
					UserInfoExtLogForm userInfoExtForm = new UserInfoExtLogForm();
					if (orgAndJointNameMap.containsKey(d.getOrgFlow())) {
						d.getResDoctor().setOrgName(orgAndJointNameMap.get(d.getOrgFlow()) + "(" + d.getOrgName() + ")");
					}
					userInfoExtForm.setUserResumeExt(d.getUserResume());
					SysUserLog su = d.getSysUser();
					String cretTypeId = su.getCretTypeId();
					if (StringUtil.isNotBlank(cretTypeId)) {
						if (!CertificateTypeEnum.Shenfenzheng.getId().equals(cretTypeId)) {
							su.setIdNo(su.getIdNo() + "(" + CertificateTypeEnum.getNameById(cretTypeId) + ")");
						}
					}
					userInfoExtForm.setSysUser(su);
					userInfoExtForm.setDoctor(d.getResDoctor());
					ResDoctorRecruitLog recruit3 = d;
					userInfoExtForm.setRecruit(recruit3);
					if (StringUtil.isNotBlank(d.getTrainYear())) {
						d.setTrainYear(JsResTrainYearEnum.getNameById(d.getTrainYear()));
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
	 * @param request
	 * @param response
	 * @param sessionNumber
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
				doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
				recruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
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
		recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		List<JsDoctorInfoLogExt> doctorInfoExts=archiveBiz.searchDoctorInfoResume(recruit, doctor, user, org, jointOrgFlowList, derateFlag, docTypeList,archiveFlow);
		jsResDoctorBiz.exportForDetailLog(doctorInfoExts, response);
	}
}
