package com.pinde.sci.ctrl.res;

import com.pinde.core.model.SysDict;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.core.common.enums.RecDocCategoryEnum;
import com.pinde.core.common.enums.RegistryTypeEnum;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/res/responsibleTeacher")
public class ResResponsibleTeacherController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResResponsibleTeacherController.class);

	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResGradeBiz resGradeBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IResDoctorOrgHistoryBiz docOrgHisBiz;
	@Autowired
	private ISchDoctorDeptBiz doctorDeptBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IResUserSpeBiz userSpeBiz;
	@Autowired
	private IResRotationOrgBiz iResRotationOrgBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private IResLectureInfoBiz resLectureInfoBiz;
	@Autowired
	private IResLectureEvaDetailBiz resLectureEvaDetailBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private IResDoctorDelayTeturnBiz resDoctorDelayTeturnBiz;
	@Autowired
	private IResLectureScanRegistBiz resLectureScanRegistBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private ISchDoctorBiz schDoctortBiz;
	@Autowired
	private ISchDoctorAbsenceBiz schDoctorAbsenceBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private ISchExamScoreQueryBiz scoreQueryBiz;
	@Autowired
	private IResTrainingSpeDeptBiz resTrainingSpeDeptBiz;
	@Autowired
	private IResResponsibleTeacherDoctorBiz resResponsibleTeacherDoctorBiz;

	/**
	 * @Department：研发部
	 * @Description 责任导师主页面
	 * @Author Zjie
	 * @Date 0012, 2021年1月12日
	 */
	@RequestMapping(value = "/index")
	public String index(Model model, Integer currentPage, HttpServletRequest request) {
        setSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE, com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_RESPONSIBLETEACHER);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE, com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_RESPONSIBLETEACHER);
		return "res/responsibleTeacher/index";
	}

	//打开责任导师配置页面
	@RequestMapping("/responsibleTeacherCfg")
	public String responsibleTeacherCfg(String doctorFlow,String currentPage,Model model,HttpServletRequest request){
		model.addAttribute("doctorFlow",doctorFlow);
		model.addAttribute("currentPage",currentPage);
		SysUser currentUser = GlobalContext.getCurrentUser();
		String orgFlow = currentUser.getOrgFlow();
		ServletContext application = request.getServletContext();
		Map<String,String> sysCfgMap = (Map<String, String>) application.getAttribute("sysCfgMap");
		String roleFlow = sysCfgMap.get("res_responsible_teacher_role_flow");
		if(StringUtil.isBlank(roleFlow)){
			return "res/responsibleTeacher/responsibleTeacherCfg";
		}
		List<SysUser> userList = userBiz.findUserByOrgFlowAndRoleFlow(orgFlow,roleFlow);
		model.addAttribute("userList",userList);

		//已选责任导师
		ResResponsibleteacherDoctor search = new ResResponsibleteacherDoctor();
		search.setDoctorFlow(doctorFlow);
		List<ResResponsibleteacherDoctor> resResponsibleteacherDoctorList = resResponsibleTeacherDoctorBiz.search(search);
		model.addAttribute("resResponsibleteacherDoctorList",resResponsibleteacherDoctorList);
		return "res/responsibleTeacher/responsibleTeacherCfg";
	}

	//保存责任导师配置
	@RequestMapping(value = "/saveResponsibleTeacher",method = RequestMethod.POST)
	@ResponseBody
	public String saveResponsibleTeacher(ResResponsibleteacherDoctor resResponsibleteacherDoctor){
		SysUser user=GlobalContext.getCurrentUser();
		String recordStatus = resResponsibleteacherDoctor.getRecordStatus();
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recordStatus)) {
			resResponsibleteacherDoctor.setOrgFlow(user.getOrgFlow());
			ResDoctor doctor=doctorBiz.readDoctor(resResponsibleteacherDoctor.getDoctorFlow());
			if(doctor!=null)
				resResponsibleteacherDoctor.setDoctorName(doctor.getDoctorName());
			resResponsibleTeacherDoctorBiz.edit(resResponsibleteacherDoctor);
		}else {
			List<ResResponsibleteacherDoctor> resultList = resResponsibleTeacherDoctorBiz.search(resResponsibleteacherDoctor);
			ResResponsibleteacherDoctor edit = resultList.get(0);
            edit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
			resResponsibleTeacherDoctorBiz.edit(edit);
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}

	//出科成绩查询
	@RequestMapping("/doctorInfoList")
	public String doctorInfoList(ResDoctorExt resDoctor,Integer currentPage,Model model,HttpServletRequest request){
			model.addAttribute("doctorTypeIdList",resDoctor.getDoctorTypeIdList());//上一次选中的条件传到页面上
		if(resDoctor.getDoctorTypeIdList()==null){
			List<String> paramList = new ArrayList<>();
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("DoctorType");
			for(SysDict s:dictList){
				paramList.add(s.getDictId());
			}
			resDoctor.setDoctorTypeIdList(paramList);
		}
		if(StringUtil.isBlank(resDoctor.getDoctorCategoryId())){
			resDoctor.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
		}
		if("all".equals(resDoctor.getDoctorCategoryId())){
			resDoctor.setDoctorCategoryId("");
		}
		model.addAttribute("doctorCategoryId",resDoctor.getDoctorCategoryId());
		List<String> doctorFlows = new ArrayList<>();
		SysUser currentUser = GlobalContext.getCurrentUser();
		ResResponsibleteacherDoctor search = new ResResponsibleteacherDoctor();
		search.setResponsibleteacherFlow(currentUser.getUserFlow());
		List<ResResponsibleteacherDoctor> resResponsibleteacherDoctorList = resResponsibleTeacherDoctorBiz.search(search);
		if(resResponsibleteacherDoctorList!=null&&resResponsibleteacherDoctorList.size()>0){
			for(ResResponsibleteacherDoctor resResponsibleteacherDoctor:resResponsibleteacherDoctorList){
				String doctorFlow = resResponsibleteacherDoctor.getDoctorFlow();
				doctorFlows.add(doctorFlow);
			}
		}else {
			return "res/responsibleTeacher/doctorInfoList";
		}
		resDoctor.setDoctorFlows(doctorFlows);
		if(StringUtil.isBlank(resDoctor.getDoctorCategoryId())){
			resDoctor.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
		}
		List<JsResDoctorRecruitExt> doctorList = null;
		PageHelper.startPage(currentPage,getPageSize(request));
//		doctorList = doctorBiz.searchDocUser(resDoctor, "");
		doctorList = doctorBiz.searchDocUserNew(resDoctor, "");
		model.addAttribute("doctorList",doctorList);

		return "res/responsibleTeacher/doctorInfoList";
	}

	//学员详细信息
	@RequestMapping(value = {"/editDocSimple"}, method = {RequestMethod.GET})
	public String editDocSimple(String roleFlag, String doctorFlow, Model model,String role) throws DocumentException {
		model.addAttribute("role",role);
		if (StringUtil.isNotBlank(doctorFlow)) {
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			model.addAttribute("doctor", doctor);
			SysUser user = userBiz.readSysUser(doctorFlow);
			model.addAttribute("user", user);
			//大字段信息
			PubUserResume pubUserResume = userResumeBiz.readPubUserResume(doctorFlow);
			if(pubUserResume != null){
				String xmlContent =  pubUserResume.getUserResume();
				if(StringUtil.isNotBlank(xmlContent)){
					//xml转换成JavaBean
					BaseUserResumeExtInfoForm extInfo=null;
					extInfo=userResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
					model.addAttribute("extInfo", extInfo);
				}
			}

			String tchRoleFlow=InitConfig.getSysCfg("res_teacher_role_flow");
			String headRoleFlow=InitConfig.getSysCfg("res_head_role_flow");
            String isTeacher = com.pinde.core.common.GlobalConstant.FLAG_N;
			if(StringUtil.isNotBlank(tchRoleFlow)||StringUtil.isNotBlank(headRoleFlow))
			{
                List<SysRole> userRoles = roleBiz.search(doctorFlow, com.pinde.core.common.GlobalConstant.RES_WS_ID);
				if(userRoles!=null&&userRoles.size()>0){
					for(SysRole userRole:userRoles){
						String roleFlow = userRole.getRoleFlow();
						if(tchRoleFlow.equals(roleFlow)||headRoleFlow.equals(roleFlow)){
                            isTeacher = com.pinde.core.common.GlobalConstant.FLAG_Y;
						}
					}
				}
			}
			model.addAttribute("isTeacher",isTeacher);

			model.addAttribute("rotationInUse", false);
			List<SchDoctorDept> doctorDeptList = doctorDeptBiz.searchSchDoctorDept(doctorFlow);
			if (doctorDeptList != null && doctorDeptList.size() > 0) {
				model.addAttribute("rotationInUse", true);
			} else {
				List<SchArrangeResult> resultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
				if (resultList != null && resultList.size() > 0) {
					model.addAttribute("rotationInUse", true);
				}
			}
		}

        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
			SysOrg searchOrg = new SysOrg();
            searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			List<SysOrg> orgList = orgBiz.searchSysOrg(searchOrg);
			model.addAttribute("orgList", orgList);
			List<ResRotationOrg> orgRotationOrgs = iResRotationOrgBiz.ResRotationOrgAll();
			Map<String, List<ResRotationOrg>> map = new HashMap<String, List<ResRotationOrg>>();
			List<String> rotationFlowList = new ArrayList<String>();
			for (ResRotationOrg resRotationOrg : orgRotationOrgs) {
				if (!rotationFlowList.contains(resRotationOrg.getRotationFlow())) {
					List<ResRotationOrg> orgs = iResRotationOrgBiz.searchByRotationFlow(resRotationOrg.getRotationFlow());
					map.put(resRotationOrg.getRotationFlow(), orgs);
				}
				rotationFlowList.add(resRotationOrg.getRotationFlow());
			}
			model.addAttribute("map", map);
		}
		SysUser user = GlobalContext.getCurrentUser();
		List<SchRotation> rotationList = schRotationtBiz.searchSchRotationByIsPublish();
        if (!com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
			rotationList = schRotationtBiz.schRotations(rotationList, user.getOrgFlow());
		}
		model.addAttribute("rotationList", rotationList);


		SysDept dept = new SysDept();
        dept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		dept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<SysDept> deptList = deptBiz.searchDept(dept);
		model.addAttribute("deptList", deptList);

		//所有导师
		String tutorRoleFlow = InitConfig.getSysCfg("res_tutor_role_flow");
		if (StringUtil.isNotBlank(tutorRoleFlow)) {
			List<SysUser> tutorList = userBiz.findUserByOrgFlowAndRoleFlow(user.getOrgFlow(),tutorRoleFlow);
			model.addAttribute("tutorList", tutorList);
		}
		//所有师承老师
		String discipleFlow = InitConfig.getSysCfg("res_disciple_role_flow");
		if(StringUtil.isNotBlank(discipleFlow)&&StringUtil.isNotBlank(user.getOrgFlow())){
			List<SysUser> discipleList = userBiz.findUserByOrgFlowAndRoleFlow(user.getOrgFlow(),discipleFlow);
			model.addAttribute("discipleList", discipleList);
		}

		return "res/responsibleTeacher/editDocSimple";
	}

	//年度成绩查询
	@RequestMapping(value="/annualExamScore")
	public String scorelist(Model model,Integer currentPage ,HttpServletRequest request,String[] doctorTypeList,
							String sessionNumber,String assessmentYear,String trainingSpeId,
							String userName ,String doctorCategoryId , String graduationYear){
		SysUser sysuser=GlobalContext.getCurrentUser();
		if(StringUtil.isBlank(assessmentYear))
			assessmentYear= String.valueOf(Integer.valueOf(DateUtil.getYear())-1);
		model.addAttribute("currentYear",assessmentYear);
		Integer firstYear=Integer.valueOf(assessmentYear)-2;
		Integer secondYear=Integer.valueOf(assessmentYear)-1;
		List<String> years=new ArrayList<>();
		years.add(String.valueOf(firstYear));
		years.add(String.valueOf(secondYear));
		years.add(assessmentYear);
		model.addAttribute("years",years);
		ResDoctorExt doctor=new ResDoctorExt();
		doctor.setSessionNumber(sessionNumber);
		doctor.setTrainingSpeId(trainingSpeId);
		SysUser d=new SysUser();
		d.setUserName(userName);
		doctor.setSysUser(d);
		if(doctorTypeList!=null&&doctorTypeList.length>0){
			doctor.setDoctorTypeIdList(Arrays.asList(doctorTypeList));
			model.addAttribute("doctorTypeIdList",Arrays.asList(doctorTypeList));
		}else{
			List<String> paramList = new ArrayList<>();
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("DoctorType");
			for(SysDict s:dictList){
				paramList.add(s.getDictId());
			}
			doctor.setDoctorTypeIdList(paramList);
		}
		model.addAttribute("doctorCategoryId",doctor.getDoctorCategoryId());
		List<String> doctorFlows = new ArrayList<>();
		ResResponsibleteacherDoctor search = new ResResponsibleteacherDoctor();
		search.setResponsibleteacherFlow(sysuser.getUserFlow());
		List<ResResponsibleteacherDoctor> resResponsibleteacherDoctorList = resResponsibleTeacherDoctorBiz.search(search);
		if(resResponsibleteacherDoctorList!=null&&resResponsibleteacherDoctorList.size()>0){
			for(ResResponsibleteacherDoctor resResponsibleteacherDoctor:resResponsibleteacherDoctorList){
				String doctorFlow = resResponsibleteacherDoctor.getDoctorFlow();
				doctorFlows.add(doctorFlow);
			}
		}else {
			return "res/responsibleTeacher/annualExamScore";
		}
		doctor.setDoctorFlows(doctorFlows);
		doctor.setGraduationYear(graduationYear);
        doctor.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResDoctorExt> list = doctorBiz.searchDocUser(doctor, "");
		model.addAttribute("list",list);
		if(list!=null&&list.size()>0)
		{
			List<String> userFlows=new ArrayList<>();
			for(ResDoctorExt user:list)
			{
				userFlows.add(user.getDoctorFlow());
			}//查询条件
			Map<String,Object> param=new HashMap<>();
			param.put("orgFlow",sysuser.getOrgFlow());
			param.put("userFlows",userFlows);
			List<SchExamDoctorArrangement> doctorArrangements=scoreQueryBiz.getDoctorArrangements(param);
			if(doctorArrangements!=null&&doctorArrangements.size()>0){
				Map<String,SchExamDoctorArrangement> doctorArrangementMap=new HashMap<>();
				for(SchExamDoctorArrangement da:doctorArrangements)
				{
					doctorArrangementMap.put(da.getAssessmentYear()+da.getDoctorFlow()+da.getSessionNumber(),da);
				}
				model.addAttribute("daMap",doctorArrangementMap);
			}
		}

		return "res/responsibleTeacher/annualExamScore";
	}


	/**
	 * @Department：研发部
	 * @Description 学员工作量查询查询条件页
	 * @Author Zjie
	 * @Date 0014, 2021年1月14日
	 */
	@RequestMapping(value = "/docWorkQuery")
	public String docWorkQuery(Model model) {
		return "res/responsibleTeacher/workQuery";
	}

	/**
	 * @Department：研发部
	 * @Description 学员工作量查询
	 * @Author Zjie
	 * @Date 0014, 2021年1月14日
	 */
	@RequestMapping(value = {"/doc/docWorking"})
	public String schDept(String trainingTypeId, String trainingSpeId, String sessionNumber,
						  String userName, String idNo, String graduationYear, String[] datas, String sort,
						  Integer currentPage, HttpServletRequest request, Model model, String baseFlag) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		String userOrgFlow = "";
		if (currentUser != null) {
			userOrgFlow = currentUser.getOrgFlow();
		}
		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		}
		List<String> recTypeIds = new ArrayList<String>();
		for (RegistryTypeEnum regType : RegistryTypeEnum.values()) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))) {
				recTypeIds.add(regType.getId());
			}
		}
		Map<String, Object> parMp = new HashMap<String, Object>();
		parMp.put("docTypeList", docTypeList);
		parMp.put("typeId", trainingTypeId);
		parMp.put("speId", trainingSpeId);
		parMp.put("sessionNumber", sessionNumber);
		parMp.put("sort", sort);
		parMp.put("userName", userName);
		parMp.put("idNo", idNo);
		parMp.put("graduationYear", graduationYear);
		parMp.put("recTypeIds", recTypeIds);
		parMp.put("baseFlag", baseFlag);
		parMp.put("userOrgFlow", userOrgFlow);
		List<String> doctorFlows = new ArrayList<>();
		ResResponsibleteacherDoctor search = new ResResponsibleteacherDoctor();
		search.setResponsibleteacherFlow(currentUser.getUserFlow());
		List<ResResponsibleteacherDoctor> resResponsibleteacherDoctorList = resResponsibleTeacherDoctorBiz.search(search);
		if (resResponsibleteacherDoctorList != null && resResponsibleteacherDoctorList.size() > 0) {
			for (ResResponsibleteacherDoctor resResponsibleteacherDoctor : resResponsibleteacherDoctorList) {
				String doctorFlow = resResponsibleteacherDoctor.getDoctorFlow();
				doctorFlows.add(doctorFlow);
			}
		} else {
			return "res/responsibleTeacher/workList";
		}
		parMp.put("doctorFlows", doctorFlows);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, Object>> rltLst = resultBiz.docWorkingSearchNew(parMp);
		model.addAttribute("rltLst", rltLst);
		return "res/responsibleTeacher/workList";
	}


	/**
	 * @Department：研发部
	 * @Description 医师轮转培训查询
	 * @Author Zjie
	 * @Date 0015, 2021年1月14日
	 */
	@RequestMapping(value = "/cycle")
	public String cycle(String userName, String idNo, String trainingSpeId, String sessionNumber, String trainingYears,
						String graduationTime, Model model, String[] docTypes, HttpServletRequest request,
						String startDate, String endDate, Integer currentPage, String onlyProblem, String baseFlag) throws DocumentException {
		List<String> titleDate = new ArrayList<>();
		if (!StringUtil.isNotBlank(startDate)) {
			endDate = DateUtil.getCurrDate();
			startDate = DateUtil.newDateOfAddMonths(endDate, -6);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}
		if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			String cycStartMonth = startDate.substring(0, 7);
			String cycEndMonth = endDate.substring(0, 7);
			titleDate = getMonthsByTwoMonth(cycStartMonth, cycEndMonth);
			model.addAttribute("titleDate", titleDate);
		}
		SysUser currUser = GlobalContext.getCurrentUser();
		String orgFlow = currUser.getOrgFlow();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (docTypes != null && docTypes.length > 0) {
			paramMap.put("docTypeList", docTypes);
		}
		model.addAttribute("docTypeList", docTypes);
		if (userName != null) {
			paramMap.put("userName", userName);
		}
		if (idNo != null) {
			paramMap.put("idNo", idNo);
		}
		if (trainingSpeId != null) {
			paramMap.put("trainingSpeId", trainingSpeId);
		}
		if (sessionNumber != null) {
			paramMap.put("sessionNumber", sessionNumber);
		}
		if (trainingYears != null) {
			paramMap.put("trainingYears", trainingYears);
		}
		if (graduationTime != null) {
			paramMap.put("graduationTime", graduationTime);
		}
		paramMap.put("onlyProblem", onlyProblem);
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		List<String> doctorFlows = new ArrayList<>();
		ResResponsibleteacherDoctor search = new ResResponsibleteacherDoctor();
		search.setResponsibleteacherFlow(currUser.getUserFlow());
		List<ResResponsibleteacherDoctor> resResponsibleteacherDoctorList = resResponsibleTeacherDoctorBiz.search(search);
		if (resResponsibleteacherDoctorList != null && resResponsibleteacherDoctorList.size() > 0) {
			for (ResResponsibleteacherDoctor resResponsibleteacherDoctor : resResponsibleteacherDoctorList) {
				String doctorFlow = resResponsibleteacherDoctor.getDoctorFlow();
				doctorFlows.add(doctorFlow);
			}
		} else {
			return "res/responsibleTeacher/cycle";
		}
		paramMap.put("doctorFlows", doctorFlows);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, Object>> docCycleList = schArrangeResultBiz.searchDocCycleListNew(paramMap);

		model.addAttribute("docCycleList", docCycleList);

		if (docCycleList != null && docCycleList.size() > 0) {
			Map<String, List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
			Map<String, Object> recMap = new HashMap<String, Object>();

			List<SchArrangeResult> arrResultList = new ArrayList<SchArrangeResult>();
			if (!(StringUtil.isBlank(orgFlow) && StringUtil.isBlank(baseFlag))) {
				arrResultList = schArrangeResultBiz.searchCycleArrangeResults(paramMap);
			}

			// 非空判断
			if (arrResultList.size() > 0) {
				for (SchArrangeResult sar : arrResultList) {
					String doctorFlow = sar.getDoctorFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();

					if (StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)) {
						resultStartDate = resultStartDate.substring(0, 7);
						resultEndDate = resultEndDate.substring(0, 7);
						for (String title : titleDate) {
							if (title.compareTo(resultStartDate) >= 0 && title.compareTo(resultEndDate) <= 0) {
								String key = doctorFlow + title;
								List<SchArrangeResult> sarList = resultListMap.get(key);
								if (sarList == null) {
									sarList = new ArrayList<SchArrangeResult>();
									resultListMap.put(key, sarList);
								}
								sarList.add(sar);
							}
						}
					}
				}
			}
			model.addAttribute("recMap", recMap);
			model.addAttribute("resultListMap", resultListMap);
		}
		return "res/responsibleTeacher/cycle";
	}

	/**
	 * @Department：研发部
	 * @Description 获取两个月份之间的所有月
	 * @Author Zjie
	 * @Date 0015, 2021年1月15日
	 */
	private List<String> getMonthsByTwoMonth(String startDate, String endDate) {
		List<String> months = null;
		if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && startDate.compareTo(endDate) <= 0) {
			months = new ArrayList<String>();
			months.add(startDate);
			if (!startDate.equals(endDate)) {
				String currDate = startDate;
				while (!currDate.equals(endDate)) {
					currDate = DateUtil.newMonthOfAddMonths(currDate, 1);
					months.add(currDate);
				}
			}
		}
		return months;
	}
}


