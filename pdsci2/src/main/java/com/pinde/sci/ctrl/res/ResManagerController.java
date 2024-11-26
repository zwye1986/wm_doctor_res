package com.pinde.sci.ctrl.res;

import com.alibaba.fastjson.JSON;
import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.jsres.IResLectureRandomSignBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.sys.SysOrgExtMapper;
import com.pinde.sci.enums.jsres.JsResDocTypeEnum;
import com.pinde.sci.enums.jsres.TrainCategoryEnum;
import com.pinde.sci.enums.res.*;
import com.pinde.sci.enums.sch.SchStatusEnum;
import com.pinde.sci.enums.sch.SchUnitEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.form.res.ResAssessCfgItemForm;
import com.pinde.sci.form.res.ResAssessCfgTitleForm;
import com.pinde.sci.form.sch.SchGradeFrom;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import com.pinde.sci.model.res.ResDoctorSchProcessExt;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/res/manager")
public class ResManagerController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResManagerController.class);

    private static final String GZZY_ORG_FLOW="5cb53b872c38457a8e2a798d6c4d002f";

    @Autowired
    private IStuUserResumeBiz stuUserBiz;
    @Autowired
    private IStuBatchBiz stuBatchBiz;
    @Autowired
	private ISchArrangeBiz arrangeBiz;
	@Autowired
	private IResAssessCfgBiz assessCfgBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
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
	private IResUserSpeBiz userSpeBiz;
	@Autowired
	private IResRotationOrgBiz iResRotationOrgBiz;
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
	private IResTrainingSpeDeptBiz resTrainingSpeDeptBiz;
	@Autowired
	private IResResponsibleTeacherDoctorBiz resResponsibleTeacherDoctorBiz;
	@Autowired
	private IResDiscipleInfoBiz discipleInfoBiz;
    @Autowired
    private IDiscipleBiz iDiscipleBiz;
	@Autowired
	private SysOrgExtMapper orgExtMapper;
	@Autowired
	private IResLectureRandomSignBiz randomSignBiz;
	@Autowired
	private IStudentUntiedRecordingBiz recordingBiz;
	@Autowired
	private IJsResDoctorBiz jsResDoctorBiz;
	@Autowired
	private IResDoctorProcessBiz iResDoctorProcessBiz;


	/**
	 * 培训查询
	 */
	@RequestMapping(value = "/registryNote/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
	public String registryNote(@PathVariable String scope, String sessionNumber,
							   String doctorCategoryId,
							   String trainingSpeId,
							   //String specialized,
							   //String deptFlow,
							   String userName,
							   //String doctorCode,
							   //String idNo,
							   Integer currentPage,
							   String orgName,
							   String deptFlow,
							   String is5plus3,
							   Model model) {

		SysUser currUser = GlobalContext.getCurrentUser();

		ResDoctorExt doctorExt = new ResDoctorExt();
		doctorExt.setIs5plus3(is5plus3);
		SysUser user = new SysUser();
		doctorExt.setSysUser(user);

		doctorExt.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		doctorExt.setSessionNumber(sessionNumber);
		doctorExt.setDoctorCategoryId(doctorCategoryId);
		doctorExt.setTrainingSpeId(trainingSpeId);
		doctorExt.setOrgName(orgName);
		//doctorExt.setSpecialized(specialized);
		//doctorExt.setDeptFlow(deptFlow);
		//doctorExt.setDoctorCode(doctorCode);

		user.setUserName(userName);
		//user.setIdNo(idNo);
		//user.setOrgFlow(orgFlow);
		user.setDeptFlow(deptFlow);

		if (currentPage == null) {
			currentPage = 1;
		}
		PageHelper.startPage(currentPage, 10);
		String medicineTypeId="";
		if (GlobalConstant.RES_ROLE_SCOPE_ADMIN.equals(scope)) {
			doctorExt.setOrgFlow(currUser.getOrgFlow());
		} else if (GlobalConstant.RES_ROLE_SCOPE_GLOBAL.equals(scope)) {
			//doctorExt.getSysUser().setOrgFlow(currUser.getOrgFlow());
			medicineTypeId=currUser.getMedicineTypeId();
		}

		List<ResDoctorExt> doctorExtList = doctorBiz.searchDocUser(doctorExt, medicineTypeId);
		if (doctorExtList != null && doctorExtList.size() > 0) {
			model.addAttribute("doctorExtList", doctorExtList);

			List<String> doctorFlows = new ArrayList<String>();
			for (ResDoctorExt doctorExtTemp : doctorExtList) {
				doctorFlows.add(doctorExtTemp.getDoctorFlow());
			}

			List<Map<String, Object>> resultCountMapList = resultBiz.countResultByUser(doctorFlows);
			if (resultCountMapList != null && resultCountMapList.size() > 0) {
				Map<String, Object> resultCountMap = new HashMap<String, Object>();
				for (Map<String, Object> map : resultCountMapList) {
					resultCountMap.put((String) map.get("key"), map.get("value"));
				}
				model.addAttribute("resultCountMap", resultCountMap);
			}
			List<Map<String, Object>> processCountMapList = resRecBiz.countProcessByUser(doctorFlows);
			if (processCountMapList != null && processCountMapList.size() > 0) {
				Map<String, Object> processCountMap = new HashMap<String, Object>();
				for (Map<String, Object> map : processCountMapList) {
					processCountMap.put((String) map.get("key"), map.get("value"));
				}
				model.addAttribute("processCountMap", processCountMap);
			}
		}

		SysDept dept = new SysDept();
		dept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		dept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<SysDept> deptList = deptBiz.searchDept(dept);
		model.addAttribute("deptList", deptList);

		model.addAttribute("scope", scope);
		if (GlobalConstant.USER_LIST_GLOBAL.equals(scope)) {
			List<SysOrg> orgList = orgBiz.searchSysOrg();
			model.addAttribute("orgList", orgList);
		}
		return "res/manager/registryNoteList";
	}

	@RequestMapping("/searchSysOrg")
	@ResponseBody
	public List<SysOrg> searchSysOrg() {
		List<SysOrg> orgList = orgBiz.searchSysOrg();
		return orgList;
	}

	/**
	 * 培训医师概况
	 */
	@RequestMapping(value = "/doctorDetail")
	public String doctorDetail(String doctorFlow, String isProcess, Model model) {
		String goPath = "/res/manager/doctorDetail";
		if (StringUtil.isNotBlank(doctorFlow)) {
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			model.addAttribute("doctor", doctor);

			SysUser user = userBiz.readSysUser(doctorFlow);
			model.addAttribute("user", user);

			if (user != null && StringUtil.isNotBlank(user.getOrgFlow())) {
				List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(user.getOrgFlow());
				if (schDeptList != null && schDeptList.size() > 0) {
					Map<String, SchDept> schDeptMap = new HashMap<String, SchDept>();
					for (SchDept schDept : schDeptList) {
						schDeptMap.put(schDept.getSchDeptFlow(), schDept);
					}
					model.addAttribute("schDeptMap", schDeptMap);
				}
			}

			List<ResDoctorSchProcess> processList = processBiz.searchProcessByDoctor(doctorFlow);
			if (processList != null && processList.size() > 0) {
				model.addAttribute("processList", processList);

				Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
				for (ResDoctorSchProcess process : processList) {
					processMap.put(process.getSchResultFlow(), process);
				}
				model.addAttribute("processMap", processMap);
			}

			List<SchArrangeResult> resultList = resultBiz.searchSchArrangeResultByDoctor(doctorFlow);
			if (resultList != null && resultList.size() > 0) {
				model.addAttribute("resultList", resultList);
				model.addAttribute("resultFlow", resultList.get(0).getResultFlow());
				model.addAttribute("schDeptFlow", resultList.get(0).getSchDeptFlow());

				Map<String, String> perMap = resRecBiz.getFinishPer(resultList, doctorFlow);
				model.addAttribute("perMap", perMap);

//				Map<String,SchArrangeResult> resultMap = new HashMap<String, SchArrangeResult>();
//				for(SchArrangeResult result : resultList){
//					resultMap.put(result.getResultFlow(),result);
//				}
//				model.addAttribute("resultMap",resultMap);
			}
			if (GlobalConstant.FLAG_Y.equals(isProcess)) {
				if (user != null) {
					model.addAttribute("userFlow", user.getUserFlow());
				}
				goPath = "redirect:/res/doc/goDetailView";
			}
		}

		return goPath;
	}

	/**
	 * 出科考核表
	 */
	@RequestMapping(value = "/evaluation")
	public String evaluation(String doctorFlow, String schDeptFlow, Model model) {
//		List<ResSchProcessExpress> recList = expressBiz.searchByRec(ResRecTypeEnum.AfterEvaluation.getId(), schDeptFlow, doctorFlow);
//
//		if (recList != null && recList.size() > 0) {
//			model.addAttribute("recFlow", recList.get(0).getRecFlow());
//		}
		model.addAttribute("recTypeId", ResRecTypeEnum.AfterEvaluation.getId());
		model.addAttribute("roleFlag", GlobalConstant.RES_ROLE_SCOPE_DOCTOR);
		model.addAttribute("schDeptFlow", schDeptFlow);
		return "redirect:/res/rec/showRegistryFormNew";
	}


	/**
	 * 登记统计报表main
	 */
	@RequestMapping(value = "/registryReportMain", method = {RequestMethod.POST, RequestMethod.GET})
	public String registryReportMain(Model model) {

		return "res/manager/registryReportMain";
	}

	/**
	 * 考核手册列表
	 */
	@RequestMapping(value = "/registryReportList")
	public String registryReportList(String recTypeId,
									 String itemName,
									 String sessionNumber,
									 String specialized,
									 //String deptFlow,
									 String userName,
									 String doctorCode,
									 String startDate,
									 String endDate,
									 Model model) {

		ResDoctorSchProcessExt processExt = new ResDoctorSchProcessExt();
		ResDoctorExt doctorExt = new ResDoctorExt();
		SysUser user = new SysUser();
		processExt.setDoctorExt(doctorExt);
		doctorExt.setSysUser(user);

		processExt.setStartDate(startDate);
		processExt.setEndDate(endDate);

		doctorExt.setSessionNumber(sessionNumber);
		doctorExt.setSpecialized(specialized);
		doctorExt.setDoctorCode(doctorCode);

		user.setUserName(userName);

		List<ResDoctorSchProcessExt> processExtList = resRecBiz.searchProcessExt(processExt);
		if (processExtList != null && processExtList.size() > 0) {
			model.addAttribute("processExtList", processExtList);

			List<String> rotationFlows = new ArrayList<String>();
			List<String> schDeptFlows = new ArrayList<String>();
			List<String> userFlows = new ArrayList<String>();

			for (ResDoctorSchProcessExt processExtTemp : processExtList) {
				String rotationFlow = processExtTemp.getDoctorExt().getRotationFlow();
				String schdeptFlow = processExtTemp.getSchDeptFlow();
				String userFlow = processExtTemp.getUserFlow();

				if (!rotationFlows.contains(rotationFlow)) {
					rotationFlows.add(rotationFlow);
				}

				if (!schDeptFlows.contains(schdeptFlow)) {
					schDeptFlows.add(schdeptFlow);
				}

				if (!userFlows.contains(userFlow)) {
					userFlows.add(userFlow);
				}
			}

			List<SchRotationDeptReq> deptReqList = rotationDeptBiz.searchReqByRotationAndSchDept(rotationFlows, schDeptFlows, recTypeId, itemName);
			if (deptReqList != null && deptReqList.size() > 0) {
				Map<String, List<SchRotationDeptReq>> deptReqMap = new HashMap<String, List<SchRotationDeptReq>>();
				for (SchRotationDeptReq deptReqTemp : deptReqList) {
					String key = deptReqTemp.getRotationFlow() + deptReqTemp.getStandardDeptId();

					if (deptReqMap.get(key) == null) {
						List<SchRotationDeptReq> deptReqTempList = new ArrayList<SchRotationDeptReq>();
						deptReqTempList.add(deptReqTemp);
						deptReqMap.put(key, deptReqTempList);
					} else {
						if (ResRecTypeEnum.CaseRegistry.getId().equals(recTypeId)) {
							BigDecimal reqNum = deptReqMap.get(key).get(0).getReqNum();
							deptReqMap.get(key).get(0).setReqNum(reqNum.add(deptReqTemp.getReqNum()));
						} else {

							deptReqMap.get(key).add(deptReqTemp);
						}
					}
				}
				model.addAttribute("deptReqMap", deptReqMap);
			}

			List<ResRec> recList = resRecBiz.searchRecByUserAndSchdept(userFlows, schDeptFlows, recTypeId, itemName);
			if (recList != null && recList.size() > 0) {
				Map<String, Integer> countMap = new HashMap<String, Integer>();
				for (ResRec rec : recList) {
					String key = rec.getOperUserFlow() + rec.getSchDeptFlow() + StringUtil.defaultString(rec.getItemId());

					if (countMap.get(key + "finish") == null) {
						countMap.put(key + "finish", 1);
					} else {
						countMap.put(key + "finish", countMap.get(key + "finish") + 1);
					}

					if (RecStatusEnum.TeacherAuditY.getId().equals(rec.getAuditStatusId())) {
						if (countMap.get(key + "audit") == null) {
							countMap.put(key + "audit", 1);
						} else {
							countMap.put(key + "audit", countMap.get(key + "audit") + 1);
						}
					}
				}
				model.addAttribute("countMap", countMap);
			}
		}

		String path = "error/404";
		if (StringUtil.isNotBlank(recTypeId) && recTypeId.length() > 1) {
			String head = recTypeId.substring(0, 1).toLowerCase();
			String body = recTypeId.substring(1);
			path = "/res/manager/" + head + body + "ReportList";
		}
		return path;
	}

	/**
	 * 数据详情
	 */
	@RequestMapping(value = "/recDetail", method = {RequestMethod.POST, RequestMethod.GET})
	public String recDetail(String itemName, String rotationFlow, String userFlow, String schDeptFlow, String recTypeId, Model model, Integer currentPage) {
		if (StringUtil.isNotBlank(itemName)) {
			SchRotationDeptReq deptReq = rotationDeptBiz.readDeptReq(itemName);
			if (deptReq != null) {
				itemName = deptReq.getItemId();
			}
		}

		if (currentPage == null) {
			currentPage = 1;
		}
		PageHelper.startPage(currentPage, 10);

		List<ResRec> recList = resRecBiz.searchByRecWithBLOBs(recTypeId, schDeptFlow, userFlow, itemName);
		if (recList != null && recList.size() > 0) {
			Map<String, Map<String, Object>> recContentMap = new HashMap<String, Map<String, Object>>();
			for (ResRec rec : recList) {
				String recContent = rec.getRecContent();
				if (StringUtil.isNotBlank(recContent)) {
					Map<String, Object> contentMap = resRecBiz.parseRecContent(recContent);
					recContentMap.put(rec.getRecFlow(), contentMap);
				}
			}
			model.addAttribute("doctorName", recList.get(0).getOperUserName());
			model.addAttribute("schDeptName", recList.get(0).getSchDeptName());
			model.addAttribute("recContentMap", recContentMap);
			model.addAttribute("recList", recList);
		}
		if (StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(schDeptFlow)) {
			List<SchRotationDeptReq> deptReqList = rotationDeptBiz.searchDeptReqByItemId(rotationFlow, schDeptFlow, itemName);
			if (deptReqList != null && deptReqList.size() > 0) {
				Map<String, SchRotationDeptReq> deptReqMap = new HashMap<String, SchRotationDeptReq>();
				for (SchRotationDeptReq req : deptReqList) {
					deptReqMap.put(req.getReqFlow(), req);
				}
				model.addAttribute("deptReqMap", deptReqMap);
			}
		}
		return "/res/manager/recDetailList";
	}

	/**
	 * 申述查询
	 */
	@RequestMapping(value = "/appealList")
	public String appealList(Model model) {

		return "/res/manager/appealList";
	}

	/**
	 * 出科情况查询
	 */
	@RequestMapping(value = "/afterInfoList")
	public String afterInfoList(Model model) {

		return "/res/manager/afterInfoList";
	}

	/**
	 * 人员维护
	 */
	@RequestMapping(value = "/userList/{role}")
	public String userList(ResDoctorExt doctor, Integer currentPage, HttpServletRequest request, Model model,@PathVariable String role,
						   String orgFlow) {
		model.addAttribute("role",role);
		model.addAttribute("currentPage",currentPage);
        if (currentPage == null) {
			currentPage = 1;
		}
        if (!"/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
            if(StringUtil.isBlank(doctor.getDoctorCategoryId())){
				for(RecDocCategoryEnum e:RecDocCategoryEnum.values())
				{
					if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_doctor_category_"+e.getId())))
					{
						doctor.setDoctorCategoryId(e.getId());
						break;
					}
				}
            }
        }

        if("all".equals(doctor.getDoctorCategoryId())){
            doctor.setDoctorCategoryId("");
        }

		model.addAttribute("doctorCategoryId",doctor.getDoctorCategoryId());
		doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<String> orgFlowList = new ArrayList<>();
		if (GlobalConstant.USER_LIST_GLOBAL.equals(role)) {
			//查询所有医院
			SysOrg org = new SysOrg();
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			List<SysOrg> orgs = orgBiz.searchOrg(org);
			model.addAttribute("orgs",orgs);
			doctor.setOrgFlow(orgFlow);
		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
			//查询高校下所有医院
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			String workOrgId = currentOrg.getSendSchoolId();
			List<SysOrg> orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			if(orgs!=null&&orgs.size()>0){
				for(SysOrg org:orgs){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
			model.addAttribute("orgs",orgs);
			doctor.setWorkOrgId(workOrgId);
			doctor.setOrgFlow(orgFlow);
			doctor.setDoctorTypeId("Graduate");
		}
		//复选框勾选标识
		Map<String,String> doctorTypeSelectMap = new HashMap<>();
		List<String> doctorTypeIdList = doctor.getDoctorTypeIdList();
		SysDict sysDict = new SysDict();
		sysDict.setDictTypeId(DictTypeEnum.DoctorType.getId());
		sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		if(dictList!=null&&dictList.size()>0){
			if(doctorTypeIdList!=null&&doctorTypeIdList.size()>0){
				for (SysDict dict:dictList){
					if(doctorTypeIdList.contains(dict.getDictId())){
						doctorTypeSelectMap.put(dict.getDictId(),"checked");
					}
				}
			}else {
				doctorTypeIdList = new ArrayList<>();
				for (SysDict dict:dictList){
					doctorTypeIdList.add(dict.getDictId());
					doctorTypeSelectMap.put(dict.getDictId(),"checked");
				}
				doctor.setDoctorTypeIdList(doctorTypeIdList);
			}
		}

		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResDoctorExt> doctorList = doctorBiz.searchDocUser(doctor, "");

		//结束
		//责任导师
		Map<String,List<ResResponsibleteacherDoctor>> teaMap = new HashMap<>();
		ResResponsibleteacherDoctor search = new ResResponsibleteacherDoctor();
		if(doctorList!=null&&doctorList.size()>0){
			for(ResDoctorExt doctorExt:doctorList){
				String doctorFlow = doctorExt.getDoctorFlow();
				search.setDoctorFlow(doctorFlow);
				List<ResResponsibleteacherDoctor> resResponsibleteacherDoctorList = resResponsibleTeacherDoctorBiz.search(search);
				teaMap.put(doctorFlow,resResponsibleteacherDoctorList);
			}
		}
		model.addAttribute("teaMap",teaMap);
		model.addAttribute("doctorTypeSelectMap",doctorTypeSelectMap);
		model.addAttribute("doctorList", doctorList);
		return "/res/manager/userList";
	}
	/**
	 * 人员绑定手机
	 */
	@RequestMapping(value = "/userBindList/{role}")
	public String userBindList(ResDoctorExt doctor, Integer currentPage, HttpServletRequest request, Model model,@PathVariable String role) {
		model.addAttribute("role",role);
		model.addAttribute("currentPage",currentPage);
        if (currentPage == null) {
			currentPage = 1;
		}
        if (!"/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
            if(StringUtil.isBlank(doctor.getDoctorCategoryId())){
				for(RecDocCategoryEnum e:RecDocCategoryEnum.values())
				{
					if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_doctor_category_"+e.getId())))
					{
						doctor.setDoctorCategoryId(e.getId());
						break;
					}
				}
            }
        }

        if("all".equals(doctor.getDoctorCategoryId())){
            doctor.setDoctorCategoryId("");
        }

		model.addAttribute("doctorCategoryId",doctor.getDoctorCategoryId());
		PageHelper.startPage(currentPage, getPageSize(request));
		doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<ResDoctorExt> doctorList = doctorBiz.searchDocUser(doctor, "");

		//复选框勾选标识
		Map<String,String> doctorTypeSelectMap = new HashMap<>();
		List<String> doctorTypeIdList = doctor.getDoctorTypeIdList();
		SysDict sysDict = new SysDict();
		sysDict.setDictTypeId(DictTypeEnum.DoctorType.getId());
		sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		if(dictList!=null&&dictList.size()>0&&doctorTypeIdList!=null&&doctorTypeIdList.size()>0){
			for (SysDict dict:dictList){
				if(doctorTypeIdList.contains(dict.getDictId())){
					doctorTypeSelectMap.put(dict.getDictId(),"checked");
				}
			}
		}
		Map<String,ResUserBindMacid> macidMap=new HashMap<>();
		if(doctorList!=null&&doctorList.size()>0){
			for(ResDoctorExt doctorExt:doctorList){
				String doctorFlow = doctorExt.getDoctorFlow();
				macidMap.put(doctorFlow,doctorBiz.readBindMacidByFlow(doctorFlow));

			}
		}

		model.addAttribute("doctorTypeSelectMap",doctorTypeSelectMap);
		model.addAttribute("doctorList", doctorList);
		model.addAttribute("macidMap", macidMap);
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			return "/res/bindManager/userBindList4jszy";
		}
		return "/res/bindManager/userBindList";
	}

	/**
	 * 人员解绑
	 */
	@RequestMapping(value = "/removeBind")
	@ResponseBody
	public String removeBind(String doctorFlow) {

		if(StringUtil.isBlank(doctorFlow))
		{
			return "请选择需要解绑的学员！";
		}
		ResUserBindMacid macid=doctorBiz.readBindMacidByFlow(doctorFlow);
		if(macid==null)
		{
			macid=new ResUserBindMacid();
		}
		macid.setUserFlow(doctorFlow);
		macid.setMacId("");
		int c=doctorBiz.saveUserMacid(macid);
		if(c==1)
		{
			return "解绑成功！";
		}
		return "解绑失败！";
	}

	/**
	 *  查询解锁记录详情
	 *  add shengl @2019年7月17日
	 */
	@RequestMapping(value = "/studentUntiedRecordingList")
	public String studentUntiedRecordingList(String doctorFlow, Model model) {
		DoctorUntiedRecording recording = new DoctorUntiedRecording();
		if(StringUtil.isNotBlank(doctorFlow)){
			recording.setDoctorFlow(doctorFlow);
		}
		recording.setLockStatus("UnLock");
		List<DoctorUntiedRecording> doctorUntiedList = recordingBiz.selectDoctorUntiedRecordingList(recording);
		model.addAttribute("doctorUntiedList",doctorUntiedList);
		return "/res/untiedRecording/studentUntiedRecordingLog";
	}


	@RequestMapping(value = "/queryStudentUntiedRecording")
	public String queryStudentUntiedRecording(Model model, String doctorFlow, String userName) {
		DoctorUntiedRecording recording = new DoctorUntiedRecording();
		recording.setDoctorFlow(doctorFlow);
		recording.setLockStatus("Lock");
		List<DoctorUntiedRecording> recordingList = recordingBiz.selectDoctorUntiedRecordingList(recording);

		if(recordingList != null && recordingList.size()>0){
			model.addAttribute("Recording",recordingList.get(0));
		}
		model.addAttribute("userName",userName);
		model.addAttribute("doctorFlow",doctorFlow);
		return "/res/untiedRecording/studentUntiedRecording";
	}


    @RequestMapping("/uploadFile")
    public String uploadFile(Model model,String itemId,String itemName) throws UnsupportedEncodingException {
        String tempStr = java.net.URLDecoder.decode(itemName,"UTF-8");
        model.addAttribute("itemId",itemId);
        model.addAttribute("itemName",tempStr);
        return "jsres/hospital/hos/uploadFile";
    }

	@RequestMapping(value="/checkUploadFile",method={RequestMethod.POST})
	public String checkUploadFile(String operType, MultipartFile uploadFile, Model model){
		if(uploadFile!=null && !uploadFile.isEmpty()){
			String fileResult = jsResDoctorBiz.checkImg(uploadFile);
			String resultPath = "";
			if(!GlobalConstant.FLAG_Y.equals(fileResult)){
				model.addAttribute("fileErrorMsg", fileResult);
			}else{
				resultPath = jsResDoctorBiz.saveFileToDirs("", uploadFile, "jsresImages");
				model.addAttribute("filePath" , resultPath);

			}
			model.addAttribute("result", fileResult);
		}
		return "jsres/doctor/uploadFile";
	}

	/**
	 * 解锁
	 * @param model
	 * @param itemId
	 * @param itemName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/UnlockUserUnlock")
	@ResponseBody
	public String UnlockUserUnlock(DoctorUntiedRecording recording,Model model,String itemId,String itemName) throws UnsupportedEncodingException {

		if(recording != null){
			String doctorFlow = recording.getDoctorFlow();
			recording.setUntiedDate(DateUtil.getCurrDate());
			recording.setLockStatus("UnLock");
			int count = recordingBiz.editDoctorUntiedRecording(recording);
			if(count > 0 && StringUtil.isNotBlank(doctorFlow)){
				// 修改账号锁定状态
				ResDoctor doctor = new ResDoctor();
				doctor.setDoctorFlow(doctorFlow);
				doctor.setLockStatus("UnLock");
				doctorBiz.editDoctor(doctor);
			}
		}

		return "1";
	}

	/**
	 * 上传文件
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/uploadNoticeFile"})
	@ResponseBody
	public String uploadNoticeFile(MultipartFile file) throws Exception{
		if(file!=null && !file.isEmpty()){
			String checkResult = this.checkImgFile(file);
			String resultPath = "";
			if(!GlobalConstant.FLAG_Y.equals(checkResult)){
				return checkResult;
			}else{
				resultPath = jsResDoctorBiz.saveFileToDirs("", file, "account_unlock");
				return resultPath;
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	public String checkImgFile(MultipartFile file) {
		List<String> mimeList = new ArrayList<String>();
		if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))){
			mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
		}
		List<String> suffixList = new ArrayList<String>();
		if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))){
			suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix").toLowerCase()).split(","));
		}
		String fileType = file.getContentType();//MIME类型;
		String fileName = file.getOriginalFilename();//文件名
		String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
		if(!(mimeList.contains(fileType) &&  suffixList.contains(suffix.toLowerCase()))){
			return "请上传 "+InitConfig.getSysCfg("inx_image_support_suffix")+"格式的文件";
		}
		// 图片大小限制200M
		long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("student_account_unbind_upload_attachment_size")));
		if(file.getSize() > limitSize*1024*1024){
			return GlobalConstant.UPLOAD_IMG_SIZE_ERROR +limitSize +"M！" ;
		}
		return GlobalConstant.FLAG_Y;//可执行保存
	}


	/**
	 * 人员管理导入
	 */
	@RequestMapping(value = "/importPerManageFromExcel")
	@ResponseBody
	public String importPerManageFromExcel(MultipartFile file, String[] roles) {
		String message = "";
		if (file != null && file.getSize() > 0) {
			try {
				int result = userBiz.importPerManageFromExcel(file, roles);
				if (GlobalConstant.ZERO_LINE != result) {
					message = GlobalConstant.UPLOAD_SUCCESSED + "导入" + result + "条记录！";
				}
			} catch (RuntimeException re) {
				re.printStackTrace();
				message = re.getMessage();
			}
		}
		return message;
	}

	/**
	 * 人员管理导入
	 */
	@RequestMapping(value = "/importDiscAndResponFromExcel")
	@ResponseBody
	public String importDiscAndResponFromExcel(MultipartFile file, String type) {
		String message = "";
		if (file != null && file.getSize() > 0) {
			try {
				int result = userBiz.importDiscAndResponFromExcel(file, type);
				if(result<0)
				{
					message ="导入文件内容为空！请确认后导入！";
					return message;
				}
				if (GlobalConstant.ZERO_LINE != result) {
					message = GlobalConstant.UPLOAD_SUCCESSED + "导入" + result + "条记录！";
				}
			} catch (RuntimeException re) {
				re.printStackTrace();
				message = re.getMessage();
			}
		}
		return message;
	}


	/**
	 * 国家医师协会导出
	 * @param doctor
	 * @param response
	 * @throws Exception
     */
	@RequestMapping(value = "/exportDoc")
	public void exportDoc(ResDoctorExt doctor, HttpServletResponse response,String role, String orgFlow)throws Exception{
		if (!"/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			if(StringUtil.isBlank(doctor.getDoctorCategoryId())){
				for(RecDocCategoryEnum e:RecDocCategoryEnum.values())
				{
					if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_doctor_category_"+e.getId())))
					{
						doctor.setDoctorCategoryId(e.getId());
						break;
					}
				}
			}
		}

		if("all".equals(doctor.getDoctorCategoryId())){
			doctor.setDoctorCategoryId("");
		}

		doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<String> orgFlowList = new ArrayList<>();
		if (GlobalConstant.USER_LIST_GLOBAL.equals(role)) {
			//查询所有医院
			SysOrg org = new SysOrg();
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			List<SysOrg> orgs = orgBiz.searchOrg(org);
			doctor.setOrgFlow(orgFlow);
		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
			//查询高校下所有医院
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			String workOrgId = currentOrg.getSendSchoolId();
			List<SysOrg> orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			if(orgs!=null&&orgs.size()>0){
				for(SysOrg org:orgs){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
			doctor.setWorkOrgId(workOrgId);
			doctor.setOrgFlow(orgFlow);
		}
		//复选框勾选标识
		Map<String,String> doctorTypeSelectMap = new HashMap<>();
		List<String> doctorTypeIdList = doctor.getDoctorTypeIdList();
		SysDict sysDict = new SysDict();
		sysDict.setDictTypeId(DictTypeEnum.DoctorType.getId());
		sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		if(dictList!=null&&dictList.size()>0){
			if(doctorTypeIdList!=null&&doctorTypeIdList.size()>0){
				for (SysDict dict:dictList){
					if(doctorTypeIdList.contains(dict.getDictId())){
						doctorTypeSelectMap.put(dict.getDictId(),"checked");
					}
				}
			}else {
				doctorTypeIdList = new ArrayList<>();
				for (SysDict dict:dictList){
					doctorTypeIdList.add(dict.getDictId());
					doctorTypeSelectMap.put(dict.getDictId(),"checked");
				}
				doctor.setDoctorTypeIdList(doctorTypeIdList);
			}
		}

		List<ResDoctorExt> doctorList = doctorBiz.searchDocUser(doctor, "");
		if("Intern".equals(InitConfig.getSysCfg("res_sch_type"))){
			doctorBiz.exportForDetail2(doctorList,response);
		}else if("/inx/hbres".equals(InitConfig.getSysCfg("sys_index_url"))){
			doctorBiz.exportForDetail4HB2017(doctorList,response);
		}else{
			doctorBiz.exportForDetail(doctorList,response);
		}
	}

	@RequestMapping(value = "/manageUser")
	public String manageUser(Integer currentPage, SysUser user, String moreDept,String orgFlow,String roleFlag,
							 HttpServletRequest request, Model model,String roleFlow) {
		if (currentPage == null) {
			currentPage = 1;
		}
		user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<String> orgFlowList = new ArrayList<>();
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
			//查询所有医院
			SysOrg org = new SysOrg();
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			List<SysOrg> orgs = orgBiz.searchOrg(org);
			model.addAttribute("orgs",orgs);
			user.setOrgFlow(orgFlow);
		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
			//查询高校下所有医院
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			String workOrgId = currentOrg.getSendSchoolId();
			List<SysOrg> orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			if(orgs!=null&&orgs.size()>0){
				for(SysOrg org:orgs){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
			user.setOrgFlow(orgFlow);
			model.addAttribute("orgs",orgs);
		}
		user.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		List<SysUser> sysUserList = null;

		List<String> roleList = new ArrayList<String>();

		String tutorRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList.add(tutorRoleFlow);
		}
		 tutorRoleFlow = InitConfig.getSysCfg("res_secretary_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList.add(tutorRoleFlow);
		}
		 tutorRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList.add(tutorRoleFlow);
		}
		 tutorRoleFlow = InitConfig.getSysCfg("res_manager_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList.add(tutorRoleFlow);
		}
		 tutorRoleFlow = InitConfig.getSysCfg("res_disciple_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList.add(tutorRoleFlow);
		}
		 tutorRoleFlow = InitConfig.getSysCfg("res_tutor_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList.add(tutorRoleFlow);
		}
		 tutorRoleFlow = InitConfig.getSysCfg("res_responsible_teacher_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList.add(tutorRoleFlow);
		}
		tutorRoleFlow = InitConfig.getSysCfg("res_train_teacher_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList.add(tutorRoleFlow);
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		if (GlobalConstant.FLAG_Y.equals(moreDept)) {
			sysUserList = userBiz.searchResManageUserByModeDept2(user, moreDept, roleList, roleFlow, orgFlowList);
		} else {
			sysUserList = userBiz.searchResManageUser2(user, roleList, roleFlow, orgFlowList);
		}

		if (sysUserList != null && sysUserList.size() > 0) {
			model.addAttribute("sysUserList", sysUserList);

			List<String> userFlows = new ArrayList<String>();
			Map<String, List<SysUserDept>> userDeptMap = new HashMap<>();
			for (SysUser su : sysUserList) {
				userFlows.add(su.getUserFlow());
				List<SysUserDept> userDeptList = userBiz.getUserDept(su);
				if (userDeptList != null && userDeptList.size() > 0) {
					userDeptMap.put(su.getUserFlow(), userDeptList);
				}
			}
			model.addAttribute("userDeptMap", userDeptMap);
			String managerRoleFlow = InitConfig.getSysCfg("res_manager_role_flow");
			if (StringUtil.isNotBlank(managerRoleFlow)) {
				List<ResUserSpe> userSpeList = userSpeBiz.searchAllUserSpes(userFlows);
				if (userSpeList != null && userSpeList.size() > 0) {
					Map<String, List<ResUserSpe>> spesMap = new HashMap<String, List<ResUserSpe>>();
					for (ResUserSpe rus : userSpeList) {
						String key = rus.getUserFlow();
						if (spesMap.get(key) == null) {
							List<ResUserSpe> russ = new ArrayList<ResUserSpe>();
							russ.add(rus);
							spesMap.put(key, russ);
						} else {
							spesMap.get(key).add(rus);
						}
					}
					model.addAttribute("spesMap", spesMap);
				}
			}
		}

		String wsId = (String) getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
		List<SysUserRole> sysUserRoleList = userRoleBiz.getByOrgFlow(null, wsId);
		Map<String, List<String>> sysUserRoleMap = new HashMap<String, List<String>>();
		for (SysUserRole sysUserRole : sysUserRoleList) {
			String userFlow = sysUserRole.getUserFlow();
			if (sysUserRoleMap.containsKey(userFlow)) {
				List<String> list = sysUserRoleMap.get(userFlow);
				list.add(sysUserRole.getRoleFlow());
			} else {
				List<String> list = new ArrayList<String>();
				list.add(sysUserRole.getRoleFlow());
				sysUserRoleMap.put(userFlow, list);
			}
		}
		model.addAttribute("sysUserRoleMap", sysUserRoleMap);
		List<SysDept> sysDeptList = new ArrayList<>();
		SysDept sysDept = new SysDept();
		sysDept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		sysDept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
			if(StringUtil.isNotBlank(orgFlow)){
				sysDept.setOrgFlow(orgFlow);
			}else {
				sysDept.setOrgFlow("");
			}
		}
		if(StringUtil.isNotBlank(sysDept.getOrgFlow())){
			sysDeptList = deptBiz.searchDept(sysDept);
		}
		model.addAttribute("sysDeptList",sysDeptList);
		model.addAttribute("roleFlag",roleFlag);
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)||GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
			return "/res/manager/manageUser4Global";
		}
		return "/res/manager/manageUser";
	}

	@RequestMapping(value = "/exportTeacher")
	public void exportTeacher(HttpServletRequest request, HttpServletResponse response, SysUser user, String moreDept,
							  String roleFlow,String orgFlow,String roleFlag)throws Exception{
		user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		user.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		List<String> orgFlowList = new ArrayList<>();
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
			user.setOrgFlow(orgFlow);
		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
			//查询高校下所有医院
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			String workOrgId = currentOrg.getSendSchoolId();
			List<SysOrg> orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			if(orgs!=null&&orgs.size()>0){
				for(SysOrg org:orgs){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
			user.setOrgFlow(orgFlow);
		}
		List<SysUser> sysUserList = null;
		List<String> roleList1 = new ArrayList<String>();

		String tutorRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList1.add(tutorRoleFlow);
		}
		tutorRoleFlow = InitConfig.getSysCfg("res_secretary_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList1.add(tutorRoleFlow);
		}
		tutorRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList1.add(tutorRoleFlow);
		}
		tutorRoleFlow = InitConfig.getSysCfg("res_manager_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList1.add(tutorRoleFlow);
		}
		tutorRoleFlow = InitConfig.getSysCfg("res_disciple_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList1.add(tutorRoleFlow);
		}
		tutorRoleFlow = InitConfig.getSysCfg("res_tutor_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList1.add(tutorRoleFlow);
		}
		tutorRoleFlow = InitConfig.getSysCfg("res_responsible_teacher_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList1.add(tutorRoleFlow);
		}
		tutorRoleFlow = InitConfig.getSysCfg("res_train_teacher_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList1.add(tutorRoleFlow);
		}

		if (GlobalConstant.FLAG_Y.equals(moreDept)) {
			sysUserList = userBiz.searchResManageUserByModeDept2(user, moreDept, roleList1, roleFlow, orgFlowList);
		} else
			sysUserList = userBiz.searchResManageUser2(user, roleList1, roleFlow, orgFlowList);
//		List<SysUser> sysUserList = userBiz.searchUser(user);
		Map<String, List<ResUserSpe>> spesMap = new HashMap<String, List<ResUserSpe>>();
		if (sysUserList != null && sysUserList.size() > 0) {

			List<String> userFlows = new ArrayList<String>();
			for (SysUser su : sysUserList) {
				userFlows.add(su.getUserFlow());
			}

			String managerRoleFlow = InitConfig.getSysCfg("res_manager_role_flow");
			if (StringUtil.isNotBlank(managerRoleFlow)) {
				List<ResUserSpe> userSpeList = userSpeBiz.searchAllUserSpes(userFlows);
				if (userSpeList != null && userSpeList.size() > 0) {
					for (ResUserSpe rus : userSpeList) {
						String key = rus.getUserFlow();
						if (spesMap.get(key) == null) {
							List<ResUserSpe> russ = new ArrayList<ResUserSpe>();
							russ.add(rus);
							spesMap.put(key, russ);
						} else {
							spesMap.get(key).add(rus);
						}
					}
				}
			}
		}

		String wsId = (String) getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
		List<SysUserRole> sysUserRoleList = userRoleBiz.getByOrgFlow(null, wsId);
		Map<String, List<String>> sysUserRoleMap = new HashMap<String, List<String>>();
		for (SysUserRole sysUserRole : sysUserRoleList) {
			String userFlow = sysUserRole.getUserFlow();
			if (sysUserRoleMap.containsKey(userFlow)) {
				List<String> list = sysUserRoleMap.get(userFlow);
				list.add(sysUserRole.getRoleFlow());
			} else {
				List<String> list = new ArrayList<String>();
				list.add(sysUserRole.getRoleFlow());
				sysUserRoleMap.put(userFlow, list);
			}
		}
		String[] headLines = null;
		headLines = new String[]{
				"师资信息表"
		};
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCenter.setWrapText(true);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setWrapText(true);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setWrapText(true);
		//列宽自适应
//		sheet.setDefaultColumnWidth((short)50);
		HSSFRow rowDep = sheet.createRow(0);//第一行
		rowDep.setHeightInPoints(20);
		HSSFCell cellOne = rowDep.createCell(0);
		cellOne.setCellStyle(styleCenter);
		cellOne.setCellValue("师资信息表");

		HSSFRow rowTwo = sheet.createRow(1);//第二行
		String[] titles = new String[]{
				"序号",
				"姓名",
				"登录名",
				"状态",
				"性别",
				"部门",
				"身份证",
				"手机",
				"邮件",
				"角色"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowTwo.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 2 * 256);
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));//合并单元格
		//行数
		int rowNum = 2;
		//存放在excel中的行数据
		String[] resultList = null;
		ServletContext application = request.getServletContext();
		Map<String,String> sysCfgMap = (Map<String, String>) application.getAttribute("sysCfgMap");
//		sysCfgMap['res_tea	cher_role_flow'],sysUserRoleMap[sysUser.userFlow]
		//对当前师资信息List循环
		if (sysUserList != null && !sysUserList.isEmpty()) {
			for (int i = 0; i < sysUserList.size(); i++ , rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行
				String roles = "";
				List<String> roleList = sysUserRoleMap.get(sysUserList.get(i).getUserFlow());
				if(roleList!=null&&roleList.size()>0){
					for (String roleTemp:roleList) {
						if(StringUtil.isNotBlank(sysCfgMap.get("res_teacher_role_flow"))&&sysCfgMap.get("res_teacher_role_flow").equals(roleTemp)){
							roles+="带教老师,";
						}
						if(StringUtil.isNotBlank(sysCfgMap.get("res_secretary_role_flow"))&&sysCfgMap.get("res_secretary_role_flow").equals(roleTemp)){
							roles+="科秘,";
						}
						if(StringUtil.isNotBlank(sysCfgMap.get("res_head_role_flow"))&&sysCfgMap.get("res_head_role_flow").equals(roleTemp)){
							roles+="科主任,";
						}
						if(StringUtil.isNotBlank(sysCfgMap.get("res_manager_role_flow"))&&sysCfgMap.get("res_manager_role_flow").equals(roleTemp)){
							List<ResUserSpe> userspeList = spesMap.get(sysUserList.get(i).getUserFlow());
							if(userspeList!=null&&userspeList.size()>0){
								String spe = "关联专业：";
								for (ResUserSpe speTemp:userspeList) {
									spe+=speTemp.getTrainingSpeName()+",";
								}
								if(spe.length()>0){
									spe=spe.substring(0,spe.length()-1);
								}
								roles+="基地主任(" + spe + "),";
							}else {
								roles+="基地主任,";
							}
						}
						if(StringUtil.isNotBlank(sysCfgMap.get("res_disciple_role_flow"))&&sysCfgMap.get("res_disciple_role_flow").equals(roleTemp)){
							roles+="师承老师,";
						}
						if(StringUtil.isNotBlank(sysCfgMap.get("res_tutor_role_flow"))&&sysCfgMap.get("res_tutor_role_flow").equals(roleTemp)){
							roles+="导师,";
						}
						if(StringUtil.isNotBlank(sysCfgMap.get("res_responsible_teacher_role_flow"))&&sysCfgMap.get("res_responsible_teacher_role_flow").equals(roleTemp)){
							roles+="责任老师,";
						}
						if(StringUtil.isNotBlank(sysCfgMap.get("res_train_teacher_role_flow"))&&sysCfgMap.get("res_train_teacher_role_flow").equals(roleTemp)){
							roles+="培训老师,";
						}
					}
					if(roles.length()>0){
						roles=roles.substring(0,roles.length()-1);
					}
				}
				//根据医院科室的Flow取出轮转科室List
				resultList = new String[]{
						i+1+"",
						sysUserList.get(i).getUserName(),
						sysUserList.get(i).getUserCode(),
						sysUserList.get(i).getStatusDesc(),
						sysUserList.get(i).getSexName(),
						sysUserList.get(i).getDeptName(),
						sysUserList.get(i).getIdNo(),
						sysUserList.get(i).getUserPhone(),
						sysUserList.get(i).getUserEmail(),
						roles
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
				}
			}
		}
		String fileName = "师资信息表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	/**
	 * 用户编辑
	 */
	@RequestMapping(value = {"/editDocSimple"}, method = {RequestMethod.GET})
	public String editDocSimple(String roleFlag, String doctorFlow, Model model,String role) throws DocumentException {
		String orgFlow4search="";
		if(StringUtil.isBlank(role)){
			role=roleFlag;
		}
		model.addAttribute("role",role);
		List<SysDept> depts=null;
		if (StringUtil.isNotBlank(doctorFlow)) {
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			model.addAttribute("doctor", doctor);
			SysUser user = userBiz.readSysUser(doctorFlow);
			model.addAttribute("user", user);
			if(StringUtil.isNotBlank(doctor.getOrgFlow()))
			{
				orgFlow4search=doctor.getOrgFlow();
				depts = deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
				model.addAttribute("depts", depts);
			}
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
			String isTeacher=GlobalConstant.FLAG_N;
			if(StringUtil.isNotBlank(tchRoleFlow)||StringUtil.isNotBlank(headRoleFlow))
			{
				List<SysRole> userRoles = roleBiz.search(doctorFlow,GlobalConstant.RES_WS_ID);
				if(userRoles!=null&&userRoles.size()>0){
					for(SysRole userRole:userRoles){
						String roleFlow = userRole.getRoleFlow();
						if(tchRoleFlow.equals(roleFlow)||headRoleFlow.equals(roleFlow)){
							isTeacher=GlobalConstant.FLAG_Y;
						}
					}
				}
			}
			model.addAttribute("isTeacher",isTeacher);
//			if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
//				SchRotation rotation = schRotationtBiz.readSchRotation(doctor.getRotationFlow());
//				if(rotation!=null){
//					doctor.setRotationFlow(rotation.getRotationFlow());
//				}
//			}

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

		if (GlobalConstant.USER_LIST_GLOBAL.equals(role)) {
			SysOrg searchOrg = new SysOrg();
			searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
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
		}else if (GlobalConstant.USER_LIST_UNIVERSITY.equals(role)) {
			//查询高校下所有医院
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			String workOrgId = currentOrg.getSendSchoolId();
			String workOrgName = currentOrg.getSendSchoolName();
			List<SysOrg> orgList = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
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
			model.addAttribute("workOrgId", workOrgId);
			model.addAttribute("workOrgName", workOrgName);
		}else{
			if(depts==null) {
				depts = deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
				model.addAttribute("depts", depts);
			}
		}
		SysUser user = GlobalContext.getCurrentUser();
		if(!GlobalConstant.USER_LIST_GLOBAL.equals(role)&&!GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
			orgFlow4search=user.getOrgFlow();
		}
		List<SchRotation> rotationList = schRotationtBiz.searchSchRotationByIsPublish();
		if (!GlobalConstant.USER_LIST_GLOBAL.equals(role)) {
			rotationList = schRotationtBiz.schRotations(rotationList, user.getOrgFlow());
		}
		model.addAttribute("rotationList", rotationList);


		SysDept dept = new SysDept();
		dept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		dept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<SysDept> deptList = deptBiz.searchDept(dept);
		model.addAttribute("deptList", deptList);


		//所有导师
		String tutorRoleFlow = InitConfig.getSysCfg("res_tutor_role_flow");
		if (StringUtil.isNotBlank(tutorRoleFlow)) {
			List<SysUser> tutorList = userBiz.findUserByOrgFlowAndRoleFlow(orgFlow4search,tutorRoleFlow);
			model.addAttribute("tutorList", tutorList);
		}
        //所有师承老师
        String discipleFlow = InitConfig.getSysCfg("res_disciple_role_flow");
        if(StringUtil.isNotBlank(discipleFlow)&&StringUtil.isNotBlank(orgFlow4search)){
			List<String> orgFlows=new ArrayList<>();
			orgFlows.add(orgFlow4search);
			if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
				SysOrg org = orgBiz.readSysOrg(orgFlow4search);
				if (org != null) {
					if (OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
						List<SysOrg> jointOrgs = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
						if (jointOrgs != null) {
							for (SysOrg so : jointOrgs) {
								orgFlows.add(so.getOrgFlow());
							}
						}
					}
				}
			}
			List<SysUser> discipleList = userBiz.searchUserByRoleAndOrgFlows(discipleFlow,orgFlows);
			model.addAttribute("discipleList", discipleList);
        }

		List<ResDocotrDelayTeturn> docotrDelayTeturns = resDoctorDelayTeturnBiz.searchByDoctorFlow(doctorFlow);
		if(docotrDelayTeturns!=null&&docotrDelayTeturns.size()>0){
			String typeId = docotrDelayTeturns.get(0).getTypeId();
			String auditStatusId = docotrDelayTeturns.get(0).getAuditStatusId();
			if(ResRecTypeEnum.ReturnTraining.getId().equals(typeId)&&ResBaseStatusEnum.Auditing.getId().equals(auditStatusId)){
				model.addAttribute("waitReturn","waitReturn");
			}
		}
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDiscipleInfo resDiscipleInfo=discipleInfoBiz.readResDiscipleInfo(doctorFlow);
			model.addAttribute("resDiscipleInfo",resDiscipleInfo);
		}
		if("Intern".equals(InitConfig.getSysCfg("res_sch_type"))){
			return "res/doctor/user/editInternSimple";
		}else {
			if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
				if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
					return "res/doctor/user/jsZyEditDoc4University";
				}
				return "res/doctor/user/jsZyEditDocSimpleNew";
			}
			if ("/inx/xnyd".equals(InitConfig.getSysCfg("sys_index_url"))) {
				return "res/doctor/user/jsZyEditDocSimple";
			}
			return "res/doctor/user/editDocSimple";
		}
	}

	/**
	 * 医师登记详情
	 */
	@RequestMapping(value = {"/doctorRegistryDetatil"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String doctorRegistryDetatil(String userFlow, Model model) {
//		List<String> doctorFlows = new ArrayList<String>();
//		doctorFlows.add(userFlow);
//		List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchArrangeResultByDoctorFlows(doctorFlows);
		if (StringUtil.isNotBlank(userFlow)) {
			List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(userFlow);
			if (arrResultList != null && !arrResultList.isEmpty()) {
				List<String> schResultFlows = new ArrayList<String>();
				for (SchArrangeResult result : arrResultList) {
					schResultFlows.add(result.getResultFlow());
				}
				model.addAttribute("arrResultList", arrResultList);

				List<ResDoctorSchProcess> processList = processBiz.searchByResultFlows(schResultFlows);
				if (processList != null && processList.size() > 0) {
					Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
					for (ResDoctorSchProcess process : processList) {
						processMap.put(process.getSchResultFlow(), process);
					}
					model.addAttribute("processMap", processMap);
				}

				Map<String, String> finishPerMap = resRecBiz.getFinishPer(arrResultList, userFlow);
				model.addAttribute("finishPerMap", finishPerMap);
			}
		}
		return "res/manager/doctor/process";
	}

	@RequestMapping(value = "/requireDataList", method = {RequestMethod.GET, RequestMethod.POST})
	public String requireDataList(String schDeptFlow, String userFlow, String rotationFlow, String recTypeId, String resultFlow,String processFlow, Model model) {

		Map<String, Integer> recCountMap = new HashMap<String, Integer>();

		if (StringUtil.isBlank(userFlow)) {
			userFlow = GlobalContext.getCurrentUser().getUserFlow();
		}
		if(StringUtil.isBlank(processFlow))
		{
			ResDoctorSchProcess process=processBiz.searchByResultFlow(resultFlow);
			if(process!=null)
				processFlow=process.getProcessFlow();
		}
		if (StringUtil.isNotBlank(recTypeId) && StringUtil.isNotBlank(schDeptFlow) && StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(processFlow)) {
//			List<ResRec> recList = resRecBiz.searchByRecWithBLOBs(recTypeId, schDeptFlow, userFlow);
			List<ResRec> recList = resRecBiz.searchByRecAndProcess(recTypeId,  userFlow,processFlow);
			if (recList != null && recList.size() > 0) {
				model.addAttribute("recList", recList);

				//Map<String,Map<String,String>> recMap = new HashMap<String, Map<String,String>>();
				Map<String, List<Map<String, String>>> viewListMap = new HashMap<String, List<Map<String, String>>>();
				Map<String, List<ResRec>> recListMap = new HashMap<String, List<ResRec>>();
				for (ResRec recTemp : recList) {
					//Map<String,String> formDataMap = resRecBiz.parseRecContent(recTemp.getRecContent());
					//recMap.put(recTemp.getRecFlow(),formDataMap);

					String content = recTemp.getRecContent();
					String form = recTemp.getRecForm();
					String type = recTemp.getRecTypeId();
					String ver = recTemp.getRecVersion();

					List<Map<String, String>> viewInfoList = resRecBiz.parseViewValue(form, type, ver, content);
					viewListMap.put(recTemp.getRecFlow(), viewInfoList);

					//String itemName = StringUtil.defaultIfEmpty(recTemp.getItemName(),"other");

					String itemId = recTemp.getItemId();

					if (recListMap.get(itemId) == null) {
						List<ResRec> recTempList = new ArrayList<ResRec>();
						recTempList.add(recTemp);
						recListMap.put(itemId, recTempList);
					} else {
						recListMap.get(itemId).add(recTemp);
					}

					if (RecStatusEnum.TeacherAuditY.getId().equals(recTemp.getAuditStatusId())) {
						if (recCountMap.get("auditCount") == null) {
							recCountMap.put("auditCount", 1);
						} else {
							recCountMap.put("auditCount", recCountMap.get("auditCount") + 1);
						}

						if (recCountMap.get(itemId + "auditCount") == null) {
							recCountMap.put(itemId + "auditCount", 1);
						} else {
							recCountMap.put(itemId + "auditCount", recCountMap.get(itemId + "auditCount") + 1);
						}
					}

					if (recCountMap.get("finishCount") == null) {
						recCountMap.put("finishCount", 1);
					} else {
						recCountMap.put("finishCount", recCountMap.get("finishCount") + 1);
					}

					if (recCountMap.get(itemId + "finishCount") == null) {
						recCountMap.put(itemId + "finishCount", 1);
					} else {
						recCountMap.put(itemId + "finishCount", recCountMap.get(itemId + "finishCount") + 1);
					}

					if (recCountMap.get(recTemp.getRecTypeId() + "finish") == null) {
						recCountMap.put(recTemp.getRecTypeId() + "finish", 1);
					} else {
						recCountMap.put(recTemp.getRecTypeId() + "finish", recCountMap.get(recTemp.getRecTypeId() + "finish") + 1);
					}
				}
				//model.addAttribute("recMap",recMap);
				model.addAttribute("viewListMap", viewListMap);
				model.addAttribute("recListMap", recListMap);
			}

			List<ResAppeal> appealList = resRecBiz.searchAppeal(recTypeId, schDeptFlow, userFlow,processFlow);
			if (appealList != null && appealList.size() > 0) {
				Map<String, ResAppeal> appealMap = new HashMap<String, ResAppeal>();
				for (ResAppeal appeal : appealList) {
					appealMap.put(appeal.getItemId(), appeal);

//					if(RecStatusEnum.TeacherAuditY.getId().equals(appeal.getAuditStatusId())){
//						if(recCountMap.get("appealCount")==null){
//							recCountMap.put("appealCount",appeal.getAppealNum().intValue());
//						}else{
//							recCountMap.put("appealCount",recCountMap.get("appealCount")+appeal.getAppealNum().intValue());
//						}
//
//						if(recCountMap.get(appeal.getItemName()+"appealCount")==null){
//							recCountMap.put(appeal.getItemName()+"appealCount",appeal.getAppealNum().intValue());
//						}else{
//							recCountMap.put(appeal.getItemName()+"appealCount",recCountMap.get(appeal.getItemName()+"appealCount")+appeal.getAppealNum().intValue());
//						}
//					}
				}
				model.addAttribute("appealMap", appealMap);
			}
		}

		//计算要求数
		if (StringUtil.isNotBlank(resultFlow)) {
			SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
			ResDoctor doctor=doctorBiz.readDoctor(result.getDoctorFlow());
			List<SchRotationDeptReq> deptReqList = rotationDeptBiz.searchStandardReqByResult(result, recTypeId);
			if (deptReqList != null && deptReqList.size() > 0) {
				model.addAttribute("deptReqList", deptReqList);

				Map<String, SchRotationDeptReq> deptReqMap = new HashMap<String, SchRotationDeptReq>();
				for (SchRotationDeptReq deptReqTemp : deptReqList) {
					deptReqMap.put(deptReqTemp.getReqFlow(), deptReqTemp);
					if (recCountMap.get(deptReqTemp.getRecTypeId() + "reqNum") == null) {
						recCountMap.put(deptReqTemp.getRecTypeId() + "reqNum", deptReqTemp.getReqNum().intValue());
					} else {
						recCountMap.put(deptReqTemp.getRecTypeId() + "reqNum", recCountMap.get(deptReqTemp.getRecTypeId() + "reqNum") + deptReqTemp.getReqNum().intValue());
					}
				}

				model.addAttribute("deptReqMap", deptReqMap);
			}
		}

		model.addAttribute("recCountMap", recCountMap);

		return "res/manager/doctor/registryView";
	}

	/**
	 * 培训视图
	 */
	@RequestMapping(value = "/processView", method = {RequestMethod.GET})
	public String processView(String resultFlow, String processFlow, String userFlow, Model model) {
		String currUserFlow = null;
		if (StringUtil.isNotBlank(userFlow)) {
			currUserFlow = userFlow;
		} else {
			currUserFlow = GlobalContext.getCurrentUser().getUserFlow();
		}
		SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
		if (result != null) {
			model.addAttribute("result", result);
			List<SchArrangeResult> results = new ArrayList<SchArrangeResult>();
			results.add(result);
			Map<String, String> recFinishMap = resRecBiz.getFinishPer(results, currUserFlow);
			model.addAttribute("recFinishMap", recFinishMap);
			ResDoctor doctor=doctorBiz.readDoctor(currUserFlow);
			//要求数
			List<SchRotationDeptReq> deptReqList = rotationDeptBiz.searchStandardReqByResult(result,doctor);
			if (deptReqList != null && deptReqList.size() > 0) {
				Map<String, BigDecimal> reqNumMap = new HashMap<String, BigDecimal>();
				for (SchRotationDeptReq req : deptReqList) {
					String key = req.getRecTypeId();
					if (reqNumMap.get(key) == null) {
						reqNumMap.put(key, req.getReqNum());
					} else {
						reqNumMap.put(key, reqNumMap.get(key).add(req.getReqNum()));
					}
				}
				model.addAttribute("reqNumMap", reqNumMap);
			}
		}

		ResDoctorSchProcess process = processBiz.read(processFlow);
		if (process != null) {
			model.addAttribute("process", process);
			String schDeptFlow = process.getSchDeptFlow();
			//申述数
			List<Map<String, Object>> appealCountList = resRecBiz.searchAppealCount(schDeptFlow, currUserFlow);
			if (appealCountList != null && appealCountList.size() > 0) {
				Map<Object, Object> appealCount = new HashMap<Object, Object>();
				for (Map<String, Object> map : appealCountList) {
					appealCount.put(map.get("appealKey"), map.get("appealSum"));
				}
				model.addAttribute("appealCount", appealCount);
			}

			//带教老师,科室考评
			List<String> recTypeIds = new ArrayList<String>();
			String dept = InitConfig.getSysCfg("res_"+ResAssessTypeEnum.DeptAssess.getId());
			String teacher = InitConfig.getSysCfg("res_"+ResAssessTypeEnum.TeacherAssess.getId());
			if (GlobalConstant.FLAG_Y.equals(dept)) {
				recTypeIds.add(ResRecTypeEnum.DeptGrade.getId());
			}
			if (GlobalConstant.FLAG_Y.equals(teacher)) {
				recTypeIds.add(ResRecTypeEnum.TeacherGrade.getId());
			}
			for (AfterRecTypeEnum after : AfterRecTypeEnum.values()) {
				String recTypeId = after.getId();
				String openFlag = InitConfig.getSysCfg("res_" + recTypeId + "_form_flag");
				if (GlobalConstant.FLAG_Y.equals(openFlag)) {
					recTypeIds.add(recTypeId);
				}
			}

//			recTypeIds.add(ResRecTypeEnum.AfterEvaluation.getId());

			//List<ResRec> deptGradeList = resRecBiz.searchByRecWithBLOBs(recTypeIds, schDeptFlow, currUserFlow);
			List<ResSchProcessExpress> deptGradeList = expressBiz.searchRecByProcessWithBLOBs(recTypeIds, processFlow);

			if (deptGradeList != null && deptGradeList.size() > 0) {
				Map<String, Object> afterRecMap = new HashMap<String, Object>();
				for (ResSchProcessExpress rec : deptGradeList) {
					String recTypeId = rec.getRecTypeId();
					String content = rec.getRecContent();
					Map<String, Object> contentMap = resRecBiz.parseRecContent(content);
					afterRecMap.put(recTypeId, rec);
					afterRecMap.put(recTypeId + "Map", contentMap);
				}
				model.addAttribute("afterRecMap", afterRecMap);
			}

			//评价带教老师,评价科室
			Map<String,Object> paramMap = new HashMap();
			paramMap.put("recTypeIds",recTypeIds);
			paramMap.put("processFlow",processFlow);
			paramMap.put("currentUserFlow",currUserFlow);
			List<DeptTeacherGradeInfo> gradeInfoList = resGradeBiz.searchResGradeByItems(paramMap);
			if(gradeInfoList!=null&&gradeInfoList.size()>0)
			{
				for(DeptTeacherGradeInfo gradeInfo:gradeInfoList) {
					String recTypeId = gradeInfo.getRecTypeId();
					if (ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)) {
						Map<String, Object> gradeMap = resRecBiz.parseGradeXml(gradeInfo.getRecContent());
						model.addAttribute("deptGrade", gradeInfo);
						model.addAttribute("deptGradeMap", gradeMap);
					} else if (ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId)) {
						Map<String, Object> gradeMap = resRecBiz.parseGradeXml(gradeInfo.getRecContent());
						model.addAttribute("teacherGrade", gradeInfo);
						model.addAttribute("teacherGradeMap", gradeMap);
					}
				}
			}
		}

		return "res/manager/doctor/processView";
	}

	/**
	 * 轮转变更
	 */
	@RequestMapping(value = {"/rotationChangeMain"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String orgSpanAudit(Model model) {

		return "res/manager/rotationChangeMain";
	}

	@RequestMapping(value = {"/rotationChangeList"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String rotationChangeList(ResDoctor doctor, String type, Model model) {
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();

		List<ResDoctor> doctorList = null;
		ResDoctorOrgHistory docOrgHis = new ResDoctorOrgHistory();

		if ("toOut".equals(type)) {
			doctor.setOrgFlow(orgFlow);
			doctorList = doctorBiz.searchDoctorForChange(doctor, null);
		} else if ("outing".equals(type)) {
			docOrgHis.setHistoryOrgFlow(orgFlow);
			docOrgHis.setChangeStatusId(SchStatusEnum.Submit.getId());
			doctorList = doctorBiz.searchDoctorForChange(doctor, docOrgHis);
		} else if ("isOut".equals(type)) {
			docOrgHis.setHistoryOrgFlow(orgFlow);
			docOrgHis.setChangeStatusId(SchStatusEnum.AuditY.getId());
			doctorList = doctorBiz.searchDoctorForChange(doctor, docOrgHis);
		} else if ("willIn".equals(type)) {
			docOrgHis.setOrgFlow(orgFlow);
			docOrgHis.setChangeStatusId(SchStatusEnum.Submit.getId());
			doctorList = doctorBiz.searchDoctorForChange(doctor, docOrgHis);
		} else if ("isIn".equals(type)) {
			docOrgHis.setOrgFlow(orgFlow);
			docOrgHis.setChangeStatusId(SchStatusEnum.AuditY.getId());
			doctorList = doctorBiz.searchDoctorForChange(doctor, docOrgHis);
		}
//		switch(type){
//			case "toOut" :
//				doctor.setOrgFlow(orgFlow);
//				doctorList = doctorBiz.searchDoctorForChange(doctor,null);
//				break;
//			case "outing" :
//				docOrgHis.setHistoryOrgFlow(orgFlow);
//				docOrgHis.setChangeStatusId(SchStatusEnum.Submit.getId());
//				doctorList = doctorBiz.searchDoctorForChange(doctor,docOrgHis);
//				break;
//			case "isOut" :
//				docOrgHis.setHistoryOrgFlow(orgFlow);
//				docOrgHis.setChangeStatusId(SchStatusEnum.AuditY.getId());
//				doctorList = doctorBiz.searchDoctorForChange(doctor,docOrgHis);
//				break;
//			case "willIn" :
//				docOrgHis.setOrgFlow(orgFlow);
//				docOrgHis.setChangeStatusId(SchStatusEnum.Submit.getId());
//				doctorList = doctorBiz.searchDoctorForChange(doctor,docOrgHis);
//				break;
//			case "isIn" :
//				docOrgHis.setOrgFlow(orgFlow);
//				docOrgHis.setChangeStatusId(SchStatusEnum.AuditY.getId());
//				doctorList = doctorBiz.searchDoctorForChange(doctor,docOrgHis);
//		}

		if (doctorList != null && doctorList.size() > 0) {
			model.addAttribute("doctorList", doctorList);

			List<String> doctorFlows = new ArrayList<String>();
			for (ResDoctor doctorTemp : doctorList) {
				doctorFlows.add(doctorTemp.getDoctorFlow());
			}

			List<ResDoctorOrgHistory> docOrgHistoryList = docOrgHisBiz.searchHistoryByDoctorFlows(doctorFlows);
			if (docOrgHistoryList != null && docOrgHistoryList.size() > 0) {
				Map<String, ResDoctorOrgHistory> okDocOrgHisMap = new HashMap<String, ResDoctorOrgHistory>();
				Map<String, ResDoctorOrgHistory> nkDocOrgHisMap = new HashMap<String, ResDoctorOrgHistory>();

				for (ResDoctorOrgHistory docOrgHisTemp : docOrgHistoryList) {
					String ok = docOrgHisTemp.getDoctorFlow() + docOrgHisTemp.getHistoryOrgFlow();
					String nk = docOrgHisTemp.getDoctorFlow() + docOrgHisTemp.getOrgFlow();

					okDocOrgHisMap.put(ok, docOrgHisTemp);
					nkDocOrgHisMap.put(nk, docOrgHisTemp);
				}

				model.addAttribute("okDocOrgHisMap", okDocOrgHisMap);
				model.addAttribute("nkDocOrgHisMap", nkDocOrgHisMap);
			}

			List<SysUser> userList = userBiz.searchSysUserByuserFlows(doctorFlows);
			if (userList != null && userList.size() > 0) {
				Map<String, SysUser> userMap = new HashMap<String, SysUser>();
				for (SysUser user : userList) {
					userMap.put(user.getUserFlow(), user);
				}
				model.addAttribute("userMap", userMap);
			}
		}

		return "res/manager/rotationChangeList";
	}

	@RequestMapping(value = {"/docOrgHisEdit"}, method = {RequestMethod.POST})
	@ResponseBody
	public String docOrgHisEdit(ResDoctorOrgHistory docOrgHis) {
		if (StringUtil.isNotBlank(docOrgHis.getTrainingSpeId())) {
			docOrgHis.setTrainingSpeName(DictTypeEnum.DoctorTrainingSpe.getDictNameById(docOrgHis.getTrainingSpeId()));
		}
		int result = docOrgHisBiz.editDocOrgHistory(docOrgHis);
		if (result != GlobalConstant.ZERO_LINE) {
			return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}

	@RequestMapping(value = {"/agreeTurnIn"}, method = {RequestMethod.GET})
	@ResponseBody
	public String agreeTurnIn(String doctorFlow, String recordFlow, String rotationFlow) {
		ResDoctorOrgHistory docOrgHis = null;
		ResDoctor doctor = null;

		SysUser user=null;
		if (StringUtil.isNotBlank(recordFlow)) {
			docOrgHis = docOrgHisBiz.readDocOrgHistory(recordFlow);
			if (docOrgHis != null) {
				docOrgHis.setChangeStatusId(SchStatusEnum.AuditY.getId());
				docOrgHis.setChangeStatusName(SchStatusEnum.AuditY.getName());
				docOrgHis.setInDate(DateUtil.getCurrDate());

				doctor = new ResDoctor();
				doctor.setDoctorFlow(doctorFlow);
				doctor.setOrgFlow(docOrgHis.getOrgFlow());
				doctor.setOrgName(docOrgHis.getOrgName());
				doctor.setSchFlag("");
				doctor.setSelDeptFlag("");
				doctor.setRotationFlow("");
				doctor.setRotationName("");
				user = new SysUser();
				user.setUserFlow(doctorFlow);
				user.setMedicineTypeId("");
				user.setMedicineTypeName("");

				if (StringUtil.isNotBlank(rotationFlow)) {
					SchRotation rotation = schRotationtBiz.readSchRotation(rotationFlow);

					if (rotation != null) {
						doctor.setRotationFlow(rotation.getRotationFlow());
						doctor.setRotationName(rotation.getRotationName());
						user.setMedicineTypeId(rotation.getRotationTypeId());
						user.setMedicineTypeName(rotation.getRotationTypeName());
					}
				}
			}
		}

		int result = docOrgHisBiz.editDocOrgHistoryAndDoc(docOrgHis, doctor);
		if (result != GlobalConstant.ZERO_LINE) {

			if(user!=null)
				userBiz.updateUser(user);
			return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}

	@RequestMapping(value = {"/rotationList"}, method = {RequestMethod.GET})
	@ResponseBody
	public List<SchRotation> rotationList(String orgFlow) {
		List<SchRotation> rotationList = null;
		if (StringUtil.isNotBlank(orgFlow)) {
			rotationList = schRotationtBiz.searchSchRotationByIsPublish();
		}
		return rotationList;
	}

	@RequestMapping(value = {"/selRole"}, method = {RequestMethod.GET})
	@ResponseBody
	public String rotationList(String userFlow, String roleFlow, String wsId) {
		int result = userRoleBiz.saveSysUserRole(userFlow, roleFlow, wsId);
		if (GlobalConstant.ZERO_LINE != result) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 医院视图
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/view"})
	public String view(String deptFlow, Model model) {
		SysUser currUser = GlobalContext.getCurrentUser();

		//获取本机构所有科室
		List<SysDept> deptList = deptBiz.searchDeptByOrg(currUser.getOrgFlow());
		model.addAttribute("deptList", deptList);



		model.addAttribute("roleFlag", GlobalConstant.RES_ROLE_SCOPE_ADMIN);

		//获取当前机构正在轮转的学员的轮转信息
		List<ResDoctorSchProcess> processList = processBiz.searchProcessByOrg(currUser.getOrgFlow());
		List<String> userFlows = new ArrayList<String>();
		if (processList != null && processList.size() > 0) {
			Map<String, List<ResDoctorSchProcess>> processListMap = new HashMap<String, List<ResDoctorSchProcess>>();
			for (ResDoctorSchProcess rdsp : processList) {
				if (StringUtil.isNotBlank(rdsp.getDeptFlow())) {
					if (rdsp.getDeptFlow().equals(deptFlow)) {
						userFlows.add(rdsp.getUserFlow());
					}
				}
				String key = rdsp.getDeptFlow();
				if (processListMap.get(key) == null) {
					List<ResDoctorSchProcess> rdspList = new ArrayList<ResDoctorSchProcess>();
					rdspList.add(rdsp);
					processListMap.put(key, rdspList);
				} else {
					processListMap.get(key).add(rdsp);
				}
			}
			model.addAttribute("processListMap", processListMap);

		}

		//待入科查询
		List<SchArrangeResult> resultList = resultBiz.willInDoctor(currUser.getOrgFlow(), null);
		if (resultList != null && resultList.size() > 0) {
			List<String> doctorFlows = new ArrayList<String>();
			Map<String, List<SchArrangeResult>> willInResultListMap = new HashMap<String, List<SchArrangeResult>>();
			for (SchArrangeResult result : resultList) {
				String doctorFlow = result.getDoctorFlow();
				if (!doctorFlows.contains(doctorFlow)) {
					doctorFlows.add(doctorFlow);
					String key = result.getDeptFlow();
					if (willInResultListMap.get(key) == null) {
						List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
						sarList.add(result);
						willInResultListMap.put(key, sarList);
					} else {
						willInResultListMap.get(key).add(result);
					}
					if (StringUtil.isNotBlank(result.getDeptFlow())) {
						if (result.getDeptFlow().equals(deptFlow)) {
							userFlows.add(doctorFlow);
						}
					}
				}
			}
			model.addAttribute("willInResultListMap", willInResultListMap);
		}

		//获取所有用户的出科小结和出科考核表
		List<Map<String, Object>> willAfterCountMapList = resRecBiz.searchDeptWillAfter(currUser.getOrgFlow());
		if (willAfterCountMapList != null && willAfterCountMapList.size() > 0) {
			Map<String, Object> willAfterCountMap = new HashMap<String, Object>();
			for (Map<String, Object> map : willAfterCountMapList) {
				willAfterCountMap.put((String) map.get("deptFlow"), map.get("countNum"));
			}
			model.addAttribute("willAfterCountMap", willAfterCountMap);
		}
		//获取用户和医师信息
		if (userFlows.size() > 0) {

			List<Map<String, Object>> willAfterDocCountMapList = resRecBiz.searchDeptWillAfterDoc(currUser.getOrgFlow(),deptFlow);
			if (willAfterDocCountMapList != null && willAfterDocCountMapList.size() > 0) {
				Map<String, Object> willAfterDocCountMap = new HashMap<String, Object>();
				for (Map<String, Object> map : willAfterDocCountMapList) {
					willAfterDocCountMap.put((String) map.get("processFlow"), map.get("countNum"));
				}
				model.addAttribute("willAfterDocCountMap", willAfterDocCountMap);
			}
			if (StringUtil.isNotBlank(deptFlow)) {
				List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
				if (userList != null && userList.size() > 0) {
					Map<String, SysUser> userMap = new HashMap<String, SysUser>();
					for (SysUser su : userList) {
						userMap.put(su.getUserFlow(), su);
					}
					model.addAttribute("userMap", userMap);
				}
				List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
				if (doctorList != null && doctorList.size() > 0) {
					Map<String, ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
					for (ResDoctor rd : doctorList) {
						doctorMap.put(rd.getDoctorFlow(), rd);
					}
					model.addAttribute("doctorMap", doctorMap);
				}
			}
		}
		return "res/manager/view";
	}


	@RequestMapping(value = {"/userSpeRel"}, method = {RequestMethod.GET})
	public String userSpeRel(String userFlow, Model model) {
		List<ResUserSpe> resUserSpeList = userSpeBiz.searchUserSpesByUser(userFlow);
		if (resUserSpeList != null && resUserSpeList.size() > 0) {
			Map<String, ResUserSpe> resUserSpeMap = new HashMap<String, ResUserSpe>();
			for (ResUserSpe ru : resUserSpeList) {
				resUserSpeMap.put(ru.getTrainingSpeId(), ru);
			}
			model.addAttribute("resUserSpeMap", resUserSpeMap);
		}
		return "res/manager/userSpeRel";
	}

	/**
	 * 保存关联专业
	 *
	 * @return
	 */
	@RequestMapping("/saveMajor")
	@ResponseBody
	public String saveMajor(String userFlow, String[] speIds) {
		List<String> speIdList = null;
		if (speIds != null && speIds.length > 0) {
			speIdList = Arrays.asList(speIds);
			speIdList = new ArrayList<String>(speIdList);
		}
		int majorFlow = userSpeBiz.editUserSpesBySpes(userFlow, speIdList);
		if (majorFlow == 0) {
			return GlobalConstant.SAVE_FAIL;
		} else {
			return GlobalConstant.SAVE_SUCCESSED;
		}
	}
	/**
	 * 医师工作量查询
	 * */
	@RequestMapping(value = {"/doc/docWorking" })
	public String schDept (String trainingSpeId,String role,String doctorCategoryId,
						   String sessionNumber,String docName,String orgFlow,
						   String cardNo,String isShow,String order,String graduationYear,String pynx,
						   Integer currentPage,HttpServletRequest request,Model model,String[] studentTypes){
		//查询后台配置是否为进修过程管理页面
        String showflag = InitConfig.getSysCfg("is_show_jxres");
        //设置当前用户部门列表
        setSessionAttribute(GlobalConstant.CURRENT_DEPT_LIST, deptBiz.searchDeptByOrg(GZZY_ORG_FLOW));
        model.addAttribute("role",role);
		SysUser curUser=GlobalContext.getCurrentUser();
		List<SysOrg> orgList=new ArrayList<>();
		List<String> orgFlowList = new ArrayList<>();
		String workOrgId = "";
		if(GlobalConstant.USER_LIST_GLOBAL.equals(role)){
			SysOrg org = new SysOrg();
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			orgList = orgBiz.searchOrg(org);
		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
			//查询高校下所有医院
			SysOrg currentOrg = orgBiz.readSysOrg(curUser.getOrgFlow());
			workOrgId = currentOrg.getSendSchoolId();
			orgList = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			if(orgList!=null&&orgList.size()>0){
				for(SysOrg org:orgList){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
		}else if(GlobalConstant.USER_LIST_LOCAL.equals(role)){
			//医院管理员增加查协同基地学员轮转情况
			SysOrg sysOrg=orgBiz.readSysOrg(curUser.getOrgFlow());
			orgList.add(sysOrg);
			orgFlowList.add(curUser.getOrgFlow());
			List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			if(jointOrgList!=null&&jointOrgList.size()>0){
				for (SysOrg so:jointOrgList) {
					orgList.add(so);
				}
			}
			if(jointOrgList!=null&&jointOrgList.size()>0){
				for(SysOrg org:jointOrgList){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
		}
		model.addAttribute("orgList",orgList);
		if(currentPage==null){
			currentPage=1;
		}
        if(StringUtil.isBlank(sessionNumber)&&GlobalConstant.FLAG_Y.equals(isShow)){
			int year = 0;
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			if(month<9){
				year = cal.get(Calendar.YEAR) - 1;
			}else{
				year = cal.get(Calendar.YEAR);
			}
			sessionNumber = String.valueOf(year);
        }
        model.addAttribute("sessionNumber", sessionNumber);
		List<String> recTypeIds = new ArrayList<String>();
		for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))){
				recTypeIds.add(regType.getId());
			}
		}
		Map<String, Object> parMp = new HashMap<String, Object>();
		parMp.put("orgFlow", orgFlow);
		parMp.put("orgFlowList", orgFlowList);
		if("professionalBase".equals(role)){
			trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
		}
		if("responsibleTeacher".equals(role)){
			List<String> doctorFlows = new ArrayList<>();
			ResResponsibleteacherDoctor search = new ResResponsibleteacherDoctor();
			search.setResponsibleteacherFlow(GlobalContext.getCurrentUser().getUserFlow());
			List<ResResponsibleteacherDoctor> resResponsibleteacherDoctorList = resResponsibleTeacherDoctorBiz.search(search);
			if(resResponsibleteacherDoctorList!=null&&resResponsibleteacherDoctorList.size()>0){
				for(ResResponsibleteacherDoctor resResponsibleteacherDoctor:resResponsibleteacherDoctorList){
					String doctorFlow = resResponsibleteacherDoctor.getDoctorFlow();
					doctorFlows.add(doctorFlow);
				}
			}else {
				return "res/hospital/docWorking";
			}
			parMp.put("doctorFlows",doctorFlows);
		}
		if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
			parMp.put("workOrgId",workOrgId);
		}
		parMp.put("speId", trainingSpeId);
		parMp.put("doctorCategoryId", doctorCategoryId);
        if(!GlobalConstant.FLAG_Y.equals(showflag)){
            parMp.put("sessionNumber", sessionNumber);
        }
		parMp.put("docName", docName);
		parMp.put("cardNo", cardNo);
		parMp.put("graduationYear", graduationYear);
		parMp.put("trainingYears", pynx);//培养年限

		parMp.put("recTypeIds",recTypeIds);

		String flag=InitConfig.getSysCfg("res_custom_result_flag")==null?"N":InitConfig.getSysCfg("res_custom_result_flag");
		parMp.put("flag",flag);
		String dataStr = "";
		List<String> docTypeList = new ArrayList<>();
		if(studentTypes!=null&&studentTypes.length>0){
			docTypeList = Arrays.asList(studentTypes);
			for(String d : studentTypes){
				dataStr += d+",";
			}
		}
        if(!GlobalConstant.FLAG_Y.equals(showflag)){
            parMp.put("docTypeList",docTypeList);
        }
		parMp.put("order",StringUtil.isBlank(order)?"wcxx":order);//排序规则
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> rltLst = processBiz.docWorkingSearch(parMp);

		model.addAttribute("rltLst", rltLst);
		model.addAttribute("studentTypes",studentTypes);
//************************************************以下江苏中医跟师数据完成比例*****************************************//
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			//查询学员对应的跟师数据完成数
			//discipleDocFlows用于查询学员对应的跟师数据完成数 学员的流水号集合
			List<String> discipleDocFlows = new ArrayList<>();
			//不同年级、不同专业、不同跟师记录类型（NoteTypeEnum）的要求数不一样
			//discipleDocKeyMap key是doctorFlow，value是学员的年级+专业
			Map<String, String> discipleDocKeyMap = new HashMap<>();
			//discipleDocTrainingYearsMap key是doctorFlow，value是学员的培养年限
			Map<String, String> discipleDocTrainingYearsMap = new HashMap<>();
			if (rltLst != null && rltLst.size() > 0) {
				for (Map<String, Object> tempMap : rltLst) {
					/**江苏中医要求数需根据实际培养年限进行缩减*/
					if(!"中医助理全科".equals(tempMap.get("trainingTypeName")==null?"":tempMap.get("trainingTypeName").toString())){
						String reqNumTemp=tempMap.get("reqNum")==null?"":tempMap.get("reqNum").toString();
						String trainingYearsTemp=tempMap.get("trainingYears")==null?"":tempMap.get("trainingYears").toString();
						if(StringUtil.isNotBlank(reqNumTemp)&&StringUtil.isNotBlank(trainingYearsTemp)){
							BigDecimal b = new BigDecimal(Double.parseDouble(reqNumTemp)*Double.parseDouble(trainingYearsTemp)/30.0);
							double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue()*10;
							tempMap.put("reqNum",(int)f1);
						}
					}
					String tempDoctorFlow = tempMap.get("doctorFlow")==null?"":tempMap.get("doctorFlow").toString();
					discipleDocFlows.add(tempDoctorFlow);
					String discipleSessionNumber = tempMap.get("sessionNumber")==null?"":tempMap.get("sessionNumber").toString();
					String discipleTrainingTypeId = tempMap.get("trainingTypeId")==null?"":tempMap.get("trainingTypeId").toString();
					String discipleTrainingYears = tempMap.get("trainingYears")==null?"":tempMap.get("trainingYears").toString();
					//将学员对应的年级培训专业取出备用
					discipleDocKeyMap.put(tempDoctorFlow, discipleSessionNumber + discipleTrainingTypeId);
					discipleDocTrainingYearsMap.put(tempDoctorFlow, discipleTrainingYears);
				}
			}
			//查询每个学生总的完成数
			//discipleInfoFinMaps中 Map的key是学员流水号doctorFlow，跟师类型noteTypeId，完成数discipleCount
			List<Map<String, String>> discipleInfoFinMaps = discipleInfoBiz.findDiscipleInfoFinbyDocFlow(discipleDocFlows);
			//将每个学员的完成数放入map,key是doctorFlow，value是完成数
			Map<String, String> discipleInfoFinMap4DocFlow = null;
			if (discipleInfoFinMaps != null && discipleInfoFinMaps.size() > 0) {
				discipleInfoFinMap4DocFlow = new HashMap<>();
				for (Map<String, String> tempMap : discipleInfoFinMaps) {
					String discipleCount = tempMap.get("discipleCount");
					String doctorFlow = tempMap.get("doctorFlow");
					String noteTypeId = tempMap.get("noteTypeId");
					String key = doctorFlow + noteTypeId;
					discipleCount = Integer.parseInt(discipleCount) + "";
					discipleInfoFinMap4DocFlow.put(key, discipleCount);
				}
			}
			//查询对应年级、专业的年要求数
			//discipleInfoReqMaps中 Map的key是key（sessionNumber+trainingTypeId+noteTypeId），要求数reqNumber
			List<Map<String, String>> discipleInfoReqMaps = discipleInfoBiz.findEveDiscipleInfoReq();
			//将不同年级不同培训专业下的要求数放入map,key是sessionNumber+trainingTypeId，value是要求数
			Map<String, String> discipleInfoReqMap4DocFlow = null;
			if (discipleInfoReqMaps != null && discipleInfoReqMaps.size() > 0) {
				discipleInfoReqMap4DocFlow = new HashMap<>();
				for (Map<String, String> tempMap : discipleInfoReqMaps) {
					String key = tempMap.get("key");
					String reqNumber = tempMap.get("reqNumber");
					discipleInfoReqMap4DocFlow.put(key, reqNumber);
				}
			}
			//计算每个学员的跟师数据完成比例并放入map中key是doctorFlow，value是比例
			//discipleCountPerCent key是doctorFlow，value是比例
			Map<String, String> discipleCountPerCent = new HashMap<>();
			if (discipleDocFlows != null && discipleDocFlows.size() > 0) {
				for (String tempDoctorFlow : discipleDocFlows) {
					if (discipleInfoReqMap4DocFlow != null && discipleInfoReqMap4DocFlow.size() > 0) {
						Enum[] noteTypeEnums = NoteTypeEnum.values();
						//查询学员对应的年级专业下是否有维护要求数，如果没有用‘-’代替
						Boolean ifHasReq = false;
						String key = discipleDocKeyMap.get(tempDoctorFlow);
						for (Enum temp : noteTypeEnums) {
							String tempReq = discipleInfoReqMap4DocFlow.get(key + temp.toString());
							if(StringUtil.isNotBlank(tempReq)){
								ifHasReq = true;
								continue;
							}
						}

						if (ifHasReq) {
							if (discipleInfoFinMap4DocFlow != null && discipleInfoFinMap4DocFlow.size() > 0) {
								//学员的培养年限计算比例留用,因配置的要求数是每一年的，故需要乘以培养年份
								String trainingYears = discipleDocTrainingYearsMap.get(tempDoctorFlow);
								//有要求数的跟师类型的数量用于计算比例
								int reqNoteTypeIdSum = 0;
								//学员跟师数据单项要求数
								String reqCount = "";
								//学员跟师数据单项完成数
								String finCount = "";
								//学员跟师数据单项完成数与单项要求数之比的和
								Double finPerReqSum = 0.0;

								for (Enum temp : noteTypeEnums) {
									String noteTypeId = temp.toString();
									//学员跟师数据单项要求数
									reqCount = discipleInfoReqMap4DocFlow.get(discipleDocKeyMap.get(tempDoctorFlow) + noteTypeId);
									if (StringUtil.isNotBlank(reqCount)) {
										reqCount = Integer.parseInt(reqCount) * Integer.parseInt(trainingYears) + "";
										//如果该项没有要求数则不计算完成数
										reqNoteTypeIdSum = reqNoteTypeIdSum + 1;
										//学员跟师数据单项完成数
										finCount = discipleInfoFinMap4DocFlow.get(tempDoctorFlow + noteTypeId);
										if (StringUtil.isNotBlank(finCount)) {
											//完成数大于等于要求数比例为1
											if (Integer.parseInt(reqCount) <= Integer.parseInt(finCount)) {
												finPerReqSum += 100;
											} else {
												Double perCent1 = 100 * Double.parseDouble(finCount) / Double.parseDouble(reqCount);
												BigDecimal b = new BigDecimal(perCent1);
												//保留1位小数
												Double perCent2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
												finPerReqSum += perCent2;
											}
										}else {
											//完成数为空的情况下，如果要求数为0，单项完成比例应按照100%计算
											if("0".equals(reqCount)){
												finPerReqSum += 100;
											}
										}

									}
								}
								//比例
								if (reqNoteTypeIdSum != 0) {
									//完成比例=总比例/总个数
									Double perCent1 = finPerReqSum / reqNoteTypeIdSum;
									BigDecimal b = new BigDecimal(perCent1);
									//保留2位小数
									String perCent2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
									if("100.00".equals(perCent2)){
										perCent2 = "100";
									}
									if("0.00".equals(perCent2)){
										perCent2 = "0";
									}
									discipleCountPerCent.put(tempDoctorFlow, perCent2 + "");
								} else {
									//如果该年级+专业的要求数为空则比例为“-”
									discipleCountPerCent.put(tempDoctorFlow, "-");
								}
							} else {
								//如果要求数不为为空，完成数为空则比例为“0”
								discipleCountPerCent.put(tempDoctorFlow, "0");
							}
						}else {
							//如果要求数为空则比例为“-”
							discipleCountPerCent.put(tempDoctorFlow, "-");
						}
					} else {
						//如果要求数为空则比例为“-”
						discipleCountPerCent.put(tempDoctorFlow, "-");
					}
				}
			}

            List<String > auditList = new ArrayList<String>();

            String cfgCodeP010="jswjw_"+orgFlow+"_P010";
            String cfgCodeP011="jswjw_"+orgFlow+"_P011";
            SysCfg cfgP010=cfgBiz.read(cfgCodeP010);
            if(cfgP010 !=null&&GlobalConstant.RECORD_STATUS_Y.equals(cfgP010.getCfgValue())){
                auditList.add("P010");
            }
            SysCfg cfgP011=cfgBiz.read(cfgCodeP011);
            if(cfgP011 !=null&&GlobalConstant.RECORD_STATUS_Y.equals(cfgP011.getCfgValue())){
                auditList.add("P011");
            }
            model.addAttribute("auditList",auditList);


			model.addAttribute("discipleCountPerCent", discipleCountPerCent);
			return "res/hospital/docWorking4jszy";
		}
//************************************************以上江苏中医跟师数据完成比例*****************************************//
		if (GlobalConstant.FLAG_Y.equals(showflag)) {
			return "res/hospital/docWorking_jx";
		}
		return "res/hospital/docWorking";
	}

	/**
	 * 学员跟师比例详情查询
	 *
	 * */
	@RequestMapping(value = {"/discipleDetailSearch"})
	public String discipleDetailSearch(String userFlow, Model model) {
		ResDoctor discipleDoc = doctorBiz.findByFlow(userFlow);
		List<String> discipleDocFlows = new ArrayList<>();
		discipleDocFlows.add(userFlow);
		//查询每个学生总的完成数
		//discipleInfoFinMaps中 Map的key是学员流水号doctorFlow，跟师类型noteTypeId，完成数discipleCount
		List<Map<String, String>> discipleInfoFinMaps = discipleInfoBiz.findDiscipleInfoFinbyDocFlow(discipleDocFlows);
		//将学员的每个单项的完成数放入map,noteTypeId，value是完成数
		Map<String,String> noteTypeFinMap = null;
		if (discipleInfoFinMaps != null && discipleInfoFinMaps.size() > 0) {
			noteTypeFinMap = new HashMap<>();
			for(Map<String, String> tempMap : discipleInfoFinMaps){
				String key = tempMap.get("noteTypeId");
				String discipleCount = tempMap.get("discipleCount");
				noteTypeFinMap.put(key,discipleCount);
			}
		}
		model.addAttribute("noteTypeFinMap",noteTypeFinMap);
		//查询对应年级、专业、跟师类型的年要求数

		String sesionNumber = discipleDoc.getSessionNumber();
		String trainingTypeId = discipleDoc.getTrainingTypeId();
		String trainingYears = discipleDoc.getTrainingYears();
		//discipleInfoReqMaps中 Map的key是key（sessionNumber+trainingTypeId+discipleTypeId），要求数reqNumber
		List<Map<String, String>> discipleInfoReqMaps = discipleInfoBiz.findEveDiscipleInfoReq();
		String reqNumber = "";
		//计算学员的每个单项跟师数据完成比例并放入map中key是noteTypeId，value是比例
		Map<String,String> noteTypeReqMap = null;
		if (discipleInfoReqMaps != null && discipleInfoReqMaps.size() > 0) {
			noteTypeReqMap = new HashMap<>();
			Enum[] noteTypeEnums = NoteTypeEnum.values();
			for (Enum temp : noteTypeEnums) {
				String key = sesionNumber + trainingTypeId + temp;
				for (Map<String, String> tempMap : discipleInfoReqMaps) {
					String keyInMap = tempMap.get("key");
					if(keyInMap.equals(key)){
						reqNumber = tempMap.get("reqNumber");
						reqNumber = Integer.parseInt(trainingYears) * Integer.parseInt(reqNumber) + "";
						noteTypeReqMap.put(temp.toString(), reqNumber);
					}
				}
			}
		}
		model.addAttribute("noteTypeReqMap",noteTypeReqMap);
		return "res/hospital/discipleStatisticsDetail";
	}

    /**
     * 带教工作量查询
     * @param isShow
     * @param model
     * @param doctorTypeIdList
     * @param schDept
     * @param operStartDate
     * @param userName
     * @param operEndDate
     * @param currentPage
     * @param request
     * @return
     * @throws DocumentException
     */
	@RequestMapping(value = "/teacherWorkload")
	public String teacherWorkload(String isShow,String orgFlow,Model model,String[] doctorTypeIdList,SchDept schDept,
								  String operStartDate,String userName,String operEndDate,String roleFlag,Integer currentPage,
								  HttpServletRequest request) throws DocumentException {
		SysUser curUser=GlobalContext.getCurrentUser();
		List<SysOrg> orgList=new ArrayList<>();
		List<String> orgFlowList = new ArrayList<>();
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
			SysOrg org = new SysOrg();
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			orgList = orgBiz.searchOrg(org);
		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
			//查询高校下所有医院
			SysOrg currentOrg = orgBiz.readSysOrg(curUser.getOrgFlow());
			String workOrgId = currentOrg.getSendSchoolId();
			orgList = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			if(orgList!=null&&orgList.size()>0){
				for(SysOrg org:orgList){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
		}else{
			//医院管理员增加查协同基地学员轮转情况
			SysOrg sysOrg=orgBiz.readSysOrg(curUser.getOrgFlow());
			orgList.add(sysOrg);
			orgFlowList.add(curUser.getOrgFlow());
			List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			if(jointOrgList!=null&&jointOrgList.size()>0){
				for (SysOrg so:jointOrgList) {
					orgList.add(so);
				}
			}
			if(jointOrgList!=null&&jointOrgList.size()>0){
				for(SysOrg org:jointOrgList){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
//			List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
//			model.addAttribute("deptList", deptList);
		}
		model.addAttribute("orgList",orgList);

        //查询后台配置是否为进修过程管理界面
        String flag = InitConfig.getSysCfg("is_show_jxres");

		if(currentPage==null){
			currentPage=1;
		}
        if(StringUtil.isBlank(operStartDate) && GlobalConstant.FLAG_Y.equals(isShow)){
            operStartDate = DateUtil.addDate(DateUtil.getCurrDate(),-30);
        }
        if(StringUtil.isBlank(operEndDate) && GlobalConstant.FLAG_Y.equals(isShow)){
            operEndDate = DateUtil.getCurrDate();
        }
		List<String> recTypeIds = new ArrayList<String>();
		for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))){
				recTypeIds.add(regType.getId());
			}
		}
		//复选框勾选标识
		Map<String,String> doctorTypeSelectMap = new HashMap<>();
		SysDict sysDict = new SysDict();
		sysDict.setDictTypeId(DictTypeEnum.DoctorType.getId());
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<String> typeList =null;
		if(dictList!=null&&dictList.size()>0&&doctorTypeIdList!=null&&doctorTypeIdList.length>0){
			typeList=Arrays.asList(doctorTypeIdList);
			for (SysDict dict:dictList){
				if(typeList.contains(dict.getDictId())){
					doctorTypeSelectMap.put(dict.getDictId(),"checked");
				}
			}
		}
		if(doctorTypeIdList==null)
		{
			typeList=new ArrayList<>();
			for (SysDict dict:dictList){
				typeList.add(dict.getDictId());
				doctorTypeSelectMap.put(dict.getDictId(),"checked");
			}
		}
        model.addAttribute("operStartDate",operStartDate);
        model.addAttribute("operEndDate",operEndDate);
		Map<String,Object> map=new HashMap<String,Object>();
		String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
		map.put("schDept",schDept);
		map.put("operStartDate",operStartDate);
		map.put("operEndDate",operEndDate);
		map.put("recTypeIds",recTypeIds);
		map.put("userName",userName);
		map.put("orgFlow",orgFlow);
		map.put("orgFlowList",orgFlowList);
		map.put("roleFlow",teacherRoleFlow);
        if(!GlobalConstant.FLAG_Y.equals(flag)){
            map.put("doctorTypeIdList",typeList);
        }
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> list=processBiz.searchTeacherWorkload(map);

		if(list!=null&&list.size()>0)
		{
			List<String> teacherFlows=new ArrayList<>();
			for(Map<String,Object> b:list)
			{
				String teacherFlow= (String) b.get("userFlow");
				if(StringUtil.isNotBlank(teacherFlow))
				{
					teacherFlows.add(teacherFlow);
				}
			}
			map.put("teacherFlows",teacherFlows);
			Map<String,Object> allDocNumMap=processBiz.getTeaAllDocNumMap(map);
			Map<String,Object> currDocNumMap=processBiz.getTeaCurrDocNumMap(map);
			Map<String,Object> schDocNumMap=processBiz.getTeaSchDocNumMap(map);
			Map<String,Object> lectureNumMap=processBiz.getTeaLectureNumMap(map);
			model.addAttribute("allDocNumMap",allDocNumMap);
			model.addAttribute("currDocNumMap",currDocNumMap);
			model.addAttribute("schDocNumMap",schDocNumMap);
			model.addAttribute("lectureNumMap",lectureNumMap);

			Map<String,Object> auditNumMap=processBiz.getTeaAuditNumMap(map);
			Map<String,Object> notAuditNumMap=processBiz.getTeaNotAuditNumMap(map);
			model.addAttribute("auditNumMap",auditNumMap);
			model.addAttribute("notAuditNumMap",notAuditNumMap);
		}
		model.addAttribute("list",list);
		model.addAttribute("doctorTypeSelectMap",doctorTypeSelectMap);
		model.addAttribute("roleFlag",roleFlag);
        if(GlobalConstant.FLAG_Y.equals(flag))
            return "res/hospital/teacherWorkload_jx";
		return "res/hospital/teacherWorkload";
	}

    /**
     * 带教工作量详情查询
     * @param model
     * @param teacherUserFlow
     * @param schDeptFlow
     * @param operStartDate
     * @param operEndDate
     * @param biaoJi
     * @param doctorTypeIdList
     * @return
     * @throws DocumentException
     */
	@RequestMapping(value = "/details")
	public String details(Model model,String teacherUserFlow,String schDeptFlow,String operStartDate,String operEndDate,String biaoJi,String[] doctorTypeIdList) throws DocumentException {
		List<String> doctorTypeIds = new ArrayList<>();
        //查询后台配置是否为进修过程管理界面
        String flag = InitConfig.getSysCfg("is_show_jxres");
        if(!GlobalConstant.FLAG_Y.equals(flag)){
            doctorTypeIds = Arrays.asList(doctorTypeIdList);
        }
		List<Map<String, Object>> doctorListMap=new ArrayList<Map<String,Object>>();
		if (GlobalConstant.OPRE_SUCCESSED_FLAG.equals(biaoJi)) {
			doctorListMap=processBiz.schDoctorQuery(teacherUserFlow,null,operEndDate,operStartDate,doctorTypeIds);
		}
		if (GlobalConstant.OPRE_FAIL_FLAG.equals(biaoJi)) {
			List<Map<String, Object>> TeacherProcessMap=processBiz.chTeacherProcessFlowRec(teacherUserFlow,null,operEndDate,operStartDate,doctorTypeIds);
			for (Map<String, Object> map : TeacherProcessMap) {

					doctorListMap.add(map);
			}
		}
		model.addAttribute("doctorListMap", doctorListMap);
        model.addAttribute("isShow",flag);
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			model.addAttribute("jszyFlag",GlobalConstant.FLAG_Y);
		}
		return "res/hospital/studentDetails";
	}

	/**
	 * 医师工作量详情查询
	 * */
	@RequestMapping(value = {"/docWorkDetailSearch" })
	public String docWorkDetailSearch (String userFlow,Model model){
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		List<String> recTypeIds = new ArrayList<String>();
		for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))){
				recTypeIds.add(regType.getId());
			}
		}
		Map<String, Object> parMp = new HashMap<String, Object>();
		parMp.put("orgFlow", orgFlow);
		parMp.put("userFlow", userFlow);
		parMp.put("recTypeIds",recTypeIds);
		List<Map<String,Object>> rltLst = resultBiz.newDocWorkingDetail(parMp);
		model.addAttribute("rltLst", rltLst);
		return "res/hospital/docWorkDetail";
	}

	@RequestMapping(value = "/doctorRecruit/catalogue/detail")
	public String detail(String processFlow,String recTypeId,String f,Model model)
	{
		model.addAttribute("processFlow",processFlow);
		model.addAttribute("recTypeId",recTypeId);
		model.addAttribute("f",f);
		if(StringUtil.isBlank(processFlow))
		{
			return "res/hospital/404";
		}
		ResDoctorSchProcess resDoctorSchProcess = processBiz.read(processFlow);
		if(resDoctorSchProcess!=null) {
			String doctorFlow = resDoctorSchProcess.getUserFlow();
			if(StringUtil.isBlank(f)||f.equals("1")) {
				if (StringUtil.isBlank(recTypeId)) {
					return "res/hospital/registrationRecord";
				}
				Map<String, Map<String, Object>> resRecMap = new HashMap<String, Map<String, Object>>();
				List<Map<String, String>> titleMap = new ArrayList<Map<String, String>>();
					List<ResRec> resRecList = resRecBiz.searchResRecWithBLOBs(recTypeId, processFlow, doctorFlow);
					if (resRecList != null && !resRecList.isEmpty()) {
						Map<String, Object> contentMap = null;
						ResRec rec = resRecList.get(0);
						titleMap = resRecBiz.parseTitle(rec.getRecForm(), rec.getRecTypeId(), rec.getRecVersion());
						for (ResRec r : resRecList) {
							if (titleMap != null && !titleMap.isEmpty()) {
								contentMap = new HashMap<String, Object>();
								contentMap = resRecBiz.parseContent(r.getRecContent());
								String key = r.getRecFlow();
								resRecMap.put(key, contentMap);
							}

						}
					}
					model.addAttribute("titleMap", titleMap);
					model.addAttribute("resRecMap", resRecMap);
					model.addAttribute("resRecList", resRecList);
				return "res/hospital/registrationRecord";

			}else if(f.equals("2")){
				ResDoctor resDoctor=doctorBiz.findByFlow(doctorFlow);
				model.addAttribute("doctor", resDoctor);
				model.addAttribute("doctorSchProcess", resDoctorSchProcess);
				model.addAttribute("currRegProcess", resDoctorSchProcess);
				String rotationFlow=resDoctor.getRotationFlow();
//				List<ResRec> resRecList = resRecBiz.searchResRecWithBLOBs(recTypeId, processFlow, doctorFlow);
				String medicineTypeId="";

				SysUser operUser  = userBiz.readSysUser(doctorFlow);
				if(operUser!=null)
					medicineTypeId=operUser.getMedicineTypeId();

				List<ResSchProcessExpress> resRecList = expressBiz.searchResRecExpressWithBLOBs(recTypeId, processFlow);

				if (resRecList != null && !resRecList.isEmpty()) {
					Map<String, Object> contentMap = null;
					ResSchProcessExpress rec = resRecList.get(0);
					String currVer = null;
					String recForm = null;

					Map<String,Object> formDataMap = null;
					if(rec!=null){
						model.addAttribute("rec",rec);
						currVer = rec.getRecVersion();
						medicineTypeId = rec.getMedicineType();
						String recContent = rec.getRecContent();
						recForm = rec.getRecForm();
						formDataMap = resRecBiz.parseRecContent(recContent);
						model.addAttribute("formDataMap", formDataMap);
					}
					model.addAttribute("formFileName", recTypeId);
					SchRotationDept dept=rotationDeptBiz.readStandardRotationDept(resDoctorSchProcess.getSchResultFlow());
					String type="";
					String recordFlow="";
					if(dept!=null)
					{
						if(JszyTCMPracticEnum.BasicPractice.getId().equals(dept.getPracticOrTheory()))
						{
							type="B";
						}
						recordFlow=dept.getRecordFlow();
					}
					String jspPath = resRecBiz.getFormPath(rotationFlow,recTypeId,currVer,recForm, type,medicineTypeId,recordFlow);
					model.addAttribute("jspPath", jspPath);
					return jspPath;
				}
				return "res/hospital/404";
			}
		}
		return "res/hospital/404";
	}

	/**
	 * 轮转科室培训详情
	 */
	@RequestMapping(value="/doctorRecruit/catalogue",method={RequestMethod.GET,RequestMethod.POST})
	public String catalogue(String processFlow,String recTypeId,String f,Model model,String resultFlow,String userFlow) throws Exception{
		SchRotationDept schRotationDept = rotationDeptBiz.readStandardRotationDept(resultFlow);
		model.addAttribute("typeId",schRotationDept.getPracticOrTheory());
		model.addAttribute("processFlow",processFlow);
		model.addAttribute("recTypeId",recTypeId);
		model.addAttribute("f",f);
		SysUser user=userBiz.readSysUser(userFlow);;
		model.addAttribute("user",user);
		return "res/hospital/catalogue";
	}
	/**
	 * 轮转科室详细信息
	 */
	@RequestMapping(value="/doctorRecruit/cycleDeptDetails")
	public String cycleDeptDetails(Model model,String resultFlow,String schStartDate,String schEndDate){
		ResDoctorSchProcess resDoctorSchProcess = processBiz.searchByResultFlow(resultFlow);
		if (resDoctorSchProcess!=null) {
			model.addAttribute("processFlow",resDoctorSchProcess.getProcessFlow());
			String teacherName = resDoctorSchProcess.getTeacherUserName();
			String schDeptName = resDoctorSchProcess.getSchDeptName();
			String processFlow = resDoctorSchProcess.getProcessFlow();
			model.addAttribute("schDeptName", schDeptName);
			model.addAttribute("teacherName", teacherName);
		}
		if (StringUtil.isNotBlank(resultFlow)) {
			SchArrangeResult schArrangeResult = schArrangeResultBiz.readSchArrangeResult(resultFlow);
			if(schArrangeResult!=null)
			{
				String schDeptName = schArrangeResult.getSchDeptName();
				model.addAttribute("schDeptName", schDeptName);
				String doctorFlow = schArrangeResult.getDoctorFlow();
				if (StringUtil.isNotBlank(doctorFlow)) {
					List<SchArrangeResult> arrangeResults=new ArrayList<>();
					arrangeResults.add(schArrangeResult);
					Map<String, String> processPerMap = resRecBiz.getFinishPer(arrangeResults,doctorFlow);
					model.addAttribute("processPerMap", processPerMap);
				}
				model.addAttribute("userFlow", doctorFlow);
			}
		}

		if(StringUtil.isNotBlank(schStartDate)){
			model.addAttribute("schStartDate",schStartDate);
		}
		if(StringUtil.isNotBlank(schEndDate)){
			model.addAttribute("schEndDate",schEndDate);
		}
		if(StringUtil.isNotBlank(resultFlow)){
			model.addAttribute("resultFlow",resultFlow);
		}
		return "res/hospital/cycleDetails";
	}
	/**
	 * 医师轮转培训查询
	 */
	@RequestMapping(value = "/doctorRecruit/cycle")
	public String cycle(String userName,String idNo,String trainingSpeId,String sessionNumber,String trainingYears,
						Model model,HttpServletRequest request,String doctorCategoryId,String role,String orgFlow,
						String startDate,String endDate,Integer currentPage,String[] studentTypes){
		if(StringUtil.isBlank(sessionNumber)){
			int year = 0;
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			if(month<9){
				year = cal.get(Calendar.YEAR) - 1;
			}else{
				year = cal.get(Calendar.YEAR);
			}
			sessionNumber = String.valueOf(year);
		}
		model.addAttribute("sessionNumber", sessionNumber);
		List<String> titleDate;
		boolean isWeek = SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
		if(!StringUtil.isNotBlank(startDate)){
            endDate = DateUtil.getCurrDate();
            startDate = DateUtil.newDateOfAddMonths(endDate,-6);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}
		if(StringUtil.isBlank(endDate))
		{
			endDate = DateUtil.newDateOfAddMonths(startDate,6);
			model.addAttribute("endDate",endDate);
		}
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			if(isWeek){
				titleDate = getWeeksByTwoDate(startDate,endDate);
			}else{// if(SchUnitEnum.Month.getId().equals(InitConfig.getSysCfg("res_rotation_unit")))
				String schStartMonth = startDate.substring(0,7);
				String schEndMonth = endDate.substring(0,7);
				titleDate = getMonthsByTwoMonth(schStartMonth,schEndMonth);
			}
			model.addAttribute("titleDate", titleDate);
		}

		SysUser currUser=GlobalContext.getCurrentUser();
		if(StringUtil.isBlank(orgFlow)&&!"pt".equals(role)&&!"gx".equals(role)){
			orgFlow = currUser.getOrgFlow();
		}
		String medicineTypeId="";
		String workOrgId="";
		if("pt".equals(role)){
			medicineTypeId=currUser.getMedicineTypeId();
			List<SysOrg> orgList = orgBiz.searchTrainOrgList();
			model.addAttribute("orgList",orgList);
		}else if("gx".equals(role)){
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			workOrgId = currentOrg.getSendSchoolId();
			List<SysOrg> orgList = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			model.addAttribute("orgList",orgList);
			if("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))){//江苏中医默认展示江苏省中医院
				if(StringUtil.isBlank(orgFlow)){
					SysOrg sysOrg = orgBiz.readSysOrgByName("江苏省中医院");
					if(sysOrg!=null){
						orgFlow = sysOrg.getOrgFlow();
						model.addAttribute("orgFlow",orgFlow);
					}
				}
			}
		}else{
			//医院管理员增加查协同基地学员轮转情况
			List<SysOrg> orgList=new ArrayList<>();
			SysOrg sysOrg=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			orgList.add(sysOrg);
			List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			if(jointOrgList!=null&&jointOrgList.size()>0){
				for (SysOrg so:jointOrgList) {
					orgList.add(so);
				}
			}
			model.addAttribute("orgList",orgList);
		}
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if(orgFlow!=null){
			paramMap.put("orgFlow",orgFlow);
		}
		if(userName!=null ){
			paramMap.put("userName",userName);
		}
		if(idNo!=null ){
			paramMap.put("idNo",idNo);
		}
		if(trainingYears!=null ){
			paramMap.put("trainingYears",trainingYears);
		}
		if(trainingSpeId!=null){
			paramMap.put("trainingSpeId",trainingSpeId);
		}
		if(doctorCategoryId!=null){
			paramMap.put("doctorCategoryId",doctorCategoryId);
		}
		if(sessionNumber!=null){
			if(!"all".equals(sessionNumber)){
				paramMap.put("sessionNumber",sessionNumber);
			}
		}
		paramMap.put("workOrgId",workOrgId);
		List<String>docTypeList=new ArrayList<String>();
		if(studentTypes!=null&&studentTypes.length>0){
			for(String s:studentTypes){
				docTypeList.add(s);
			}
		}else {
			List<SysDict> dicts = DictTypeEnum.DoctorType.getSysDictList();
			for (SysDict dict:dicts) {
				docTypeList.add(dict.getDictId());
			}
		}
		paramMap.put("docTypeList",docTypeList);
		paramMap.put("medicineTypeId",medicineTypeId);
		//查询医师信息
		PageHelper.startPage(currentPage, getPageSize(request));//分页,必须放在分页sql前
		List<Map<String,Object>> docCycleList = schArrangeResultBiz.searchNewDocCycleList(paramMap);

		model.addAttribute("docCycleList",docCycleList);
		model.addAttribute("studentTypes",studentTypes);

		Map<String,List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
		//查询SCH_ARRANGE_RESULT信息
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> processFlowMap=new HashMap<String, Object>();
		List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchNewCycleArrangeResults(paramMap);
		for (SchArrangeResult sar : arrResultList) {
			String resultFlow=sar.getResultFlow();
			//根据resultFlow查询RES_DOCTOR_SCH_PROCESS
			ResDoctorSchProcess process=processBiz.searchByResultFlow(resultFlow);
			String time=DateUtil.getCurrDate();
			if(null==process){
				map.put(resultFlow,"");
			}else {
				if (StringUtil.isNotBlank(process.getSchFlag()) && process.getSchFlag().equals(GlobalConstant.FLAG_Y)) {
					map.put(resultFlow, "1");//已出科 "style=\"background-color: #00B83F\""
				}else if (!GlobalConstant.FLAG_Y.equals(process.getSchFlag()) && StringUtil.isNotBlank(process.getSchEndDate()) && compare_date(time, process.getSchEndDate()) > 0) {
					ResSchProcessExpress rec=expressBiz.queryResRecByProcessAndType(process.getProcessFlow(),ResRecTypeEnum.AfterSummary.getId());
					if(null==rec)
					{
						map.put(resultFlow+"_rec","学生未提交出科小结");
					}else{
						String teaAudit=rec.getAuditStatusId();
						String headAudit=rec.getHeadAuditStatusId();
						if(StringUtil.isEmpty(teaAudit))
						{
							map.put(resultFlow+"_rec","老师未提交数据");
						}else if(StringUtil.isEmpty(headAudit)){
							map.put(resultFlow+"_rec","主任未提交数据");
						}
					}
					map.put(resultFlow, "3");//"style=\"background-color: #ee0101\""
				} else if (StringUtil.isNotBlank(process.getIsCurrentFlag()) && process.getIsCurrentFlag().equals(GlobalConstant.FLAG_Y)) {
					map.put(resultFlow, "2");//轮转中"style=\"background-color: #f8da4e\""

				}
				processFlowMap.put(resultFlow,process.getProcessFlow());
			}
			//根据时间区分
			String resultStartDate = sar.getSchStartDate();
			String resultEndDate = sar.getSchEndDate();
			String doctorFlow = sar.getDoctorFlow();
			if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
				if(isWeek){
					List<String> weeks = getWeeksByTwoDate(resultStartDate,resultEndDate);
					if(weeks!=null){
						for(String week : weeks){
							String key = doctorFlow+week;
							if(resultListMap.get(key)==null){
								List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
								sarList.add(sar);
								resultListMap.put(key,sarList);
							}else{
								resultListMap.get(key).add(sar);
							}
						}
					}
				}else{
					resultStartDate = resultStartDate.substring(0,7);
					resultEndDate = resultEndDate.substring(0,7);
					//计算该月该轮转科室
					List<String> months = getMonthsByTwoMonth(resultStartDate,resultEndDate);
					if(months!=null){
						for(String month : months){
							String key = doctorFlow+month;
							if(resultListMap.get(key)==null){
								List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
								sarList.add(sar);
								resultListMap.put(key,sarList);
							}else{
								resultListMap.get(key).add(sar);
							}
						}
					}
				}
			}
		}
		model.addAttribute("recMap", map);
		model.addAttribute("resultListMap",resultListMap);

		return "res/hospital/docCycle";
	}

	/**
	 * 轮转状态查询导出
	 */
	@RequestMapping(value = "/doctorRecruit/exportCycle")
	@ResponseBody
	public void exportCycle(String userName,String idNo,String trainingSpeId,String sessionNumber,String trainingYears,String doctorCategoryId,String orgFlow,
							String startDate,String endDate,String role,String[] studentTypes,HttpServletResponse response) throws Exception{
		if(StringUtil.isBlank(sessionNumber)){
			int year = 0;
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			if(month<9){
				year = cal.get(Calendar.YEAR) - 1;
			}else{
				year = cal.get(Calendar.YEAR);
			}
			sessionNumber = String.valueOf(year);
		}
		List<String> titleDate = new ArrayList<>();
		boolean isWeek = SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
		if(!StringUtil.isNotBlank(startDate)){
			startDate = DateUtil.getCurrDate();
			endDate = DateUtil.newDateOfAddMonths(startDate,6);
		}
		if(StringUtil.isBlank(endDate)) {
			endDate = DateUtil.newDateOfAddMonths(startDate,6);
		}
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			if(isWeek){
				titleDate = getWeeksByTwoDate(startDate,endDate);
			}else{
				String schStartMonth = startDate.substring(0,7);
				String schEndMonth = endDate.substring(0,7);
				titleDate = getMonthsByTwoMonth(schStartMonth,schEndMonth);
			}
		}
		SysUser currUser=GlobalContext.getCurrentUser();
		if(StringUtil.isBlank(orgFlow)&&!"pt".equals(role)){
			orgFlow = currUser.getOrgFlow();
		}
		String medicineTypeId="";
		String workOrgId="";
		if("pt".equals(role)){
			medicineTypeId=currUser.getMedicineTypeId();
		}else if("gx".equals(role)){
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			workOrgId = currentOrg.getSendSchoolId();
		}
		Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("workOrgId",workOrgId);
		if(orgFlow!=null){
			paramMap.put("orgFlow",orgFlow);
		}
		if(userName!=null ){
			paramMap.put("userName",userName);
		}
		if(idNo!=null ){
			paramMap.put("idNo",idNo);
		}
		if(trainingYears!=null ){
			paramMap.put("trainingYears",trainingYears);
		}
		if(trainingSpeId!=null){
			paramMap.put("trainingSpeId",trainingSpeId);
		}
		if(doctorCategoryId!=null){
			paramMap.put("doctorCategoryId",doctorCategoryId);
		}
		if(sessionNumber!=null){
			if(!"all".equals(sessionNumber)){
				paramMap.put("sessionNumber",sessionNumber);
			}
		}
		List<String>docTypeList=new ArrayList<String>();
		if(studentTypes!=null&&studentTypes.length>0){
			for(String s:studentTypes){
				docTypeList.add(s);
			}
		}else {
			List<SysDict> dicts = DictTypeEnum.DoctorType.getSysDictList();
			for (SysDict dict:dicts) {
				docTypeList.add(dict.getDictId());
			}
		}
		paramMap.put("docTypeList",docTypeList);
		paramMap.put("medicineTypeId",medicineTypeId);
		//查询医师信息
		List<Map<String,Object>> docCycleList = schArrangeResultBiz.searchNewDocCycleList(paramMap);

		Map<String,List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
		//查询SCH_ARRANGE_RESULT信息
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> processFlowMap=new HashMap<String, Object>();
		List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchNewCycleArrangeResults(paramMap);
		for (SchArrangeResult sar : arrResultList) {
			String resultFlow=sar.getResultFlow();
			//根据resultFlow查询RES_DOCTOR_SCH_PROCESS
			ResDoctorSchProcess process=processBiz.searchByResultFlow(resultFlow);
			String time=DateUtil.getCurrDate();
			if(null==process){
				map.put(resultFlow,"");
			}else {
				if (StringUtil.isNotBlank(process.getSchFlag()) && process.getSchFlag().equals(GlobalConstant.FLAG_Y)) {
					map.put(resultFlow, "1");
				}else if (!GlobalConstant.FLAG_Y.equals(process.getSchFlag()) && StringUtil.isNotBlank(process.getSchEndDate()) && compare_date(time, process.getSchEndDate()) > 0) {
					ResSchProcessExpress rec=expressBiz.queryResRecByProcessAndType(process.getProcessFlow(),ResRecTypeEnum.AfterSummary.getId());
					if(null==rec)
					{
						map.put(resultFlow+"_rec","学生未提交出科小结");
					}else{
						String teaAudit=rec.getAuditStatusId();
						String headAudit=rec.getHeadAuditStatusId();
						if(StringUtil.isEmpty(teaAudit))
						{
							map.put(resultFlow+"_rec","老师未提交数据");
						}else if(StringUtil.isEmpty(headAudit)){
							map.put(resultFlow+"_rec","主任未提交数据");
						}
					}
					map.put(resultFlow, "3");
				} else if (StringUtil.isNotBlank(process.getIsCurrentFlag()) && process.getIsCurrentFlag().equals(GlobalConstant.FLAG_Y)) {
					map.put(resultFlow, "2");

				}
				processFlowMap.put(resultFlow,process.getProcessFlow());
			}
			//根据时间区分
			String resultStartDate = sar.getSchStartDate();
			String resultEndDate = sar.getSchEndDate();
			String doctorFlow = sar.getDoctorFlow();
			if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
				if(isWeek){
					List<String> weeks = getWeeksByTwoDate(resultStartDate,resultEndDate);
					if(weeks!=null){
						for(String week : weeks){
							String key = doctorFlow+week;
							if(resultListMap.get(key)==null){
								List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
								sarList.add(sar);
								resultListMap.put(key,sarList);
							}else{
								resultListMap.get(key).add(sar);
							}
						}
					}
				}else{
					resultStartDate = resultStartDate.substring(0,7);
					resultEndDate = resultEndDate.substring(0,7);
					//计算该月该轮转科室
					List<String> months = getMonthsByTwoMonth(resultStartDate,resultEndDate);
					if(months!=null){
						for(String month : months){
							String key = doctorFlow+month;
							if(resultListMap.get(key)==null){
								List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
								sarList.add(sar);
								resultListMap.put(key,sarList);
							}else{
								resultListMap.get(key).add(sar);
							}
						}
					}
				}
			}
		}
		List<String> titles = new ArrayList<>();
		titles.add("姓名");
		titles.add("培养单位");
		titles.add("专业");
		titles.add("年级");
		titles.add("培训年限");
		for(int i=0;i<titleDate.size();i++){
			titles.add(titleDate.get(i));
		}
		String[] title = titles.toArray(new String[titles.size()]);
		String fileName = "轮转状态信息.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		OutputStream os = response.getOutputStream();
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		// 创建一个居中格式
		style.setAlignment(HorizontalAlignment.CENTER);
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		style.setFont(font);
		//设置背景色
		HSSFCellStyle style2 = wb.createCellStyle();
		HSSFCell cell = null;
		for(int i = 0 ; i<title.length ; i++){
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
			sheet.setColumnWidth(i, 4000);
		}
		if(docCycleList!=null){
			for(int i=0; i<docCycleList.size(); i++){
				row = sheet.createRow(i + 1);
				row.createCell(0).setCellValue((String)docCycleList.get(i).get("userName"));
				row.createCell(1).setCellValue((String)docCycleList.get(i).get("orgName"));
				if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
					row.createCell(2).setCellValue((String)docCycleList.get(i).get("doctorCategoryName"));
				}else{
					row.createCell(2).setCellValue((String)docCycleList.get(i).get("trainingSpeName"));
				}
				row.createCell(3).setCellValue((String)docCycleList.get(i).get("sessionNumber"));
				String year = (String)docCycleList.get(i).get("trainingYears");
				if("1".equals(year)){
					year = "一年";
				}else if("2".equals(year)){
					year = "二年";
				}else if("3".equals(year)){
					year = "三年";
				}
				row.createCell(4).setCellValue(year);
				for(int j=0;j<titleDate.size();j++){
					String key = docCycleList.get(i).get("doctorFlow")+titleDate.get(j);
					List<SchArrangeResult> resultList = resultListMap.get(key);
					if(null != resultList && !resultList.isEmpty()){
						String schDeptName = "";
						//多个轮转科室则已最后为准
						String resultFlow = "";
						for(int n=0;n<resultList.size();n++){
							schDeptName += resultList.get(n).getSchDeptName();
							if(n<resultList.size()-1){
								schDeptName+=",";
							}
							resultList.get(n).getResultFlow();
						}
						String color = (String)map.get(resultFlow);
						HSSFCell hc = row.createCell(j+5);
						if("1".equals(color)){
							style2.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.LIGHT_GREEN.getIndex());
						}else if("2".equals(color)){
							style2.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.TAN.getIndex());
						}else if("3".equals(color)){
							style2.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.ROSE.getIndex());
						}else{
							style2.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.SKY_BLUE.getIndex());
						}
						hc.setCellStyle(style2);
						hc.setCellValue(schDeptName);
					}else{
						row.createCell(j+5).setCellValue("");
					}
				}
			}
		}
		wb.write(os);
	}

	/**
	 * 医师出科成绩查询
	 */
	@RequestMapping(value = "/doctorRecruit/doctorChuke")
	public String doctorChuke(String userName,String trainingSpeId,String sessionNumber,
						Model model,HttpServletRequest request,String role,
						String startDate,String endDate,Integer currentPage,String[] studentTypes){
		model.addAttribute("role",role);
		if(StringUtil.isBlank(sessionNumber)){
			int year = 0;
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			if(month<9){
				year = cal.get(Calendar.YEAR) - 1;
			}else{
				year = cal.get(Calendar.YEAR);
			}
			sessionNumber = String.valueOf(year);
		}
		model.addAttribute("sessionNumber", sessionNumber);
		List<String> titleDate;
		boolean isWeek = SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
		if(!StringUtil.isNotBlank(startDate)){
			endDate = DateUtil.getCurrDate();
			startDate = DateUtil.newDateOfAddMonths(endDate,-5);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}
//		if(StringUtil.isBlank(endDate))
//		{
//			endDate = DateUtil.newDateOfAddMonths(startDate,6);
//			model.addAttribute("endDate",endDate);
//		}
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			if(isWeek){
				titleDate = getWeeksByTwoDate(startDate,endDate);
			}else{// if(SchUnitEnum.Month.getId().equals(InitConfig.getSysCfg("res_rotation_unit")))
				String schStartMonth = startDate.substring(0,7);
				String schEndMonth = endDate.substring(0,7);
				titleDate = getMonthsByTwoMonth(schStartMonth,schEndMonth);
			}
			model.addAttribute("titleDate", titleDate);
		}
		SysUser currUser=GlobalContext.getCurrentUser();
		String orgFlow = currUser.getOrgFlow();
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if(orgFlow!=null){
			paramMap.put("orgFlow",orgFlow);
		}
		if(userName!=null ){
			paramMap.put("userName",userName);
		}
		if(trainingSpeId!=null){
			paramMap.put("trainingSpeId",trainingSpeId);
		}
		if(sessionNumber!=null){
			if(!"all".equals(sessionNumber)){
				paramMap.put("sessionNumber",sessionNumber);
			}
		}
		List<String>docTypeList=new ArrayList<String>();
		if(studentTypes!=null&&studentTypes.length>0){
			for(String s:studentTypes){
				docTypeList.add(s);
			}
		}else {
			List<SysDict> dicts = DictTypeEnum.DoctorType.getSysDictList();
			for (SysDict dict:dicts) {
				docTypeList.add(dict.getDictId());
			}
		}
		model.addAttribute("studentTypes",studentTypes);
		paramMap.put("docTypeList",docTypeList);
		if("responsibleTeacher".equals(role)){
			List<String> doctorFlows = new ArrayList<>();
			ResResponsibleteacherDoctor search = new ResResponsibleteacherDoctor();
			search.setResponsibleteacherFlow(currUser.getUserFlow());
			List<ResResponsibleteacherDoctor> resResponsibleteacherDoctorList = resResponsibleTeacherDoctorBiz.search(search);
			if(resResponsibleteacherDoctorList!=null&&resResponsibleteacherDoctorList.size()>0){
				for(ResResponsibleteacherDoctor resResponsibleteacherDoctor:resResponsibleteacherDoctorList){
					String doctorFlow = resResponsibleteacherDoctor.getDoctorFlow();
					doctorFlows.add(doctorFlow);
				}
			}else {
				return "res/hospital/doctorChukeList";
			}
			paramMap.put("doctorFlows",doctorFlows);
		}
		//查询医师信息
		PageHelper.startPage(currentPage, getPageSize(request));//分页,必须放在分页sql前
		List<Map<String,Object>> docCycleList = schArrangeResultBiz.searchNewDocCycleList(paramMap);

		model.addAttribute("docCycleList",docCycleList);

		Map<String,List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
		//查询SCH_ARRANGE_RESULT信息
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> processFlowMap=new HashMap<String, Object>();
		List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchNewCycleArrangeResults(paramMap);
		for (SchArrangeResult sar : arrResultList) {
			String resultFlow=sar.getResultFlow();
			//根据resultFlow查询RES_DOCTOR_SCH_PROCESS
			ResDoctorSchProcess process=processBiz.searchByResultFlow(resultFlow);
			if(null!=process)
			{
				processFlowMap.put(resultFlow,process.getProcessFlow());
			}
			//根据时间区分
			String resultStartDate = sar.getSchStartDate();
			String resultEndDate = sar.getSchEndDate();
			String doctorFlow = sar.getDoctorFlow();
			if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
				if(isWeek){
					List<String> weeks = getWeeksByTwoDate(resultStartDate,resultEndDate);
					if(weeks!=null){
						for(String week : weeks){
							String key = doctorFlow+week;
							if(resultListMap.get(key)==null){
								List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
								sarList.add(sar);
								resultListMap.put(key,sarList);
							}else{
								resultListMap.get(key).add(sar);
							}
						}
					}
				}else{
					resultStartDate = resultStartDate.substring(0,7);
					resultEndDate = resultEndDate.substring(0,7);
					//计算该月该轮转科室
					List<String> months = getMonthsByTwoMonth(resultStartDate,resultEndDate);
					if(months!=null){
						for(String month : months){
							String key = doctorFlow+month;
							if(resultListMap.get(key)==null){
								List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
								sarList.add(sar);
								resultListMap.put(key,sarList);
							}else{
								resultListMap.get(key).add(sar);
							}
						}
					}
				}
			}
		}
		model.addAttribute("recMap", map);
		model.addAttribute("resultListMap",resultListMap);
		model.addAttribute("processFlowMap",processFlowMap);

		return "res/hospital/doctorChukeList";
	}
	/**
	 * 医师出科成绩导出
	 */
	@RequestMapping(value = "/exportCkScore")
	public void exportCkScore(String userName,String trainingSpeId,String sessionNumber,
						Model model,HttpServletRequest request,String role,
						String startDate,String endDate,HttpServletResponse response) throws Exception {
		if(!StringUtil.isNotBlank(startDate)){
			endDate = DateUtil.getCurrDate();
			startDate = DateUtil.newDateOfAddMonths(endDate,-5);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}
		if(StringUtil.isBlank(endDate))
		{
			endDate = DateUtil.newDateOfAddMonths(startDate,6);
			model.addAttribute("endDate",endDate);
		}
		SysUser currUser=GlobalContext.getCurrentUser();
		String orgFlow = currUser.getOrgFlow();
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if(orgFlow!=null){
			paramMap.put("orgFlow",orgFlow);
		}
		if(userName!=null ){
			paramMap.put("userName",userName);
		}
		if(trainingSpeId!=null){
			paramMap.put("trainingSpeId",trainingSpeId);
		}
		if(sessionNumber!=null){
			paramMap.put("sessionNumber",sessionNumber);
		}
		boolean flag =false;
		if("responsibleTeacher".equals(role)){
			List<String> doctorFlows = new ArrayList<>();
			ResResponsibleteacherDoctor search = new ResResponsibleteacherDoctor();
			search.setResponsibleteacherFlow(currUser.getUserFlow());
			List<ResResponsibleteacherDoctor> resResponsibleteacherDoctorList = resResponsibleTeacherDoctorBiz.search(search);
			if(resResponsibleteacherDoctorList!=null&&resResponsibleteacherDoctorList.size()>0){
				for(ResResponsibleteacherDoctor resResponsibleteacherDoctor:resResponsibleteacherDoctorList){
					String doctorFlow = resResponsibleteacherDoctor.getDoctorFlow();
					doctorFlows.add(doctorFlow);
				}
			}else{
				flag = true;
			}
			paramMap.put("doctorFlows",doctorFlows);
		}
		paramMap.put("startDate",startDate);
		paramMap.put("endDate",endDate);
		//查询医师信息
		List<Map<String,Object>> docCycleList = schArrangeResultBiz.exportCkScore(paramMap);
		if(flag){
			docCycleList = null;
		}
		String[] titles = new String[]{
				"userName:姓名",
				"trainingSpeName:专业",
				"sessionNumber:届别",
				"schDeptName:科室",
				"schScore:分数"
		};
		String fileName = "学员出科理论成绩表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, docCycleList,response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}
	/**
	 * 医师出科成绩细节
	 */
	@RequestMapping(value="/doctorRecruit/resultsDetails")
	public String resultsDetails(String processFlow,String recTypeId,Model model)throws DocumentException {
		model.addAttribute("processFlow",processFlow);
		model.addAttribute("recTypeId",recTypeId);
		List<ResRec> clinic=resRecBiz.searchByRecForAudit(processFlow,AfterRecTypeEnum.MonthlyAssessment_clinic.getId());
		if(clinic!=null&&clinic.size()>0)
		{
			model.addAttribute("clinic","Y");
		}
		List<ResRec> area=resRecBiz.searchByRecForAudit(processFlow,AfterRecTypeEnum.MonthlyAssessment_inpatientArea.getId());
		if(area!=null&&area.size()>0)
		{
			model.addAttribute("area","Y");
		}

		return "res/hospital/cycleResultsDetails";
	}
	/**
	 * 医师出科详情
	 */
	@RequestMapping(value = "/doctorRecruit/loadResult")
	public String loadResult(String processFlow,String recTypeId,Model model)
	{
		model.addAttribute("processFlow",processFlow);
		model.addAttribute("recTypeId",recTypeId);
		if(StringUtil.isBlank(processFlow))
		{
			return "res/hospital/404";
		}
		ResDoctorSchProcess resDoctorSchProcess = processBiz.read(processFlow);
		if(resDoctorSchProcess!=null) {
			String doctorFlow = resDoctorSchProcess.getUserFlow();
				ResDoctor resDoctor=doctorBiz.findByFlow(doctorFlow);
				model.addAttribute("doctor", resDoctor);
				model.addAttribute("doctorSchProcess", resDoctorSchProcess);
				model.addAttribute("currRegProcess", resDoctorSchProcess);
				String rotationFlow=resDoctor.getRotationFlow();
//				List<ResRec> resRecList = resRecBiz.searchResRecWithBLOBs(recTypeId, processFlow, doctorFlow);
				List<ResSchProcessExpress> resRecList = expressBiz.searchResRecExpressWithBLOBs(recTypeId, processFlow);
				String medicineTypeId="";

				SysUser operUser  = userBiz.readSysUser(doctorFlow);
				if(operUser!=null)
					medicineTypeId=operUser.getMedicineTypeId();
				if (resRecList != null && !resRecList.isEmpty()) {
					Map<String, Object> contentMap = null;
					ResSchProcessExpress rec = resRecList.get(0);
					String currVer = null;
					String recForm = null;

					Map<String,Object> formDataMap = null;
					if(rec!=null){
						model.addAttribute("rec",rec);
						currVer = rec.getRecVersion();
						medicineTypeId = rec.getMedicineType();
						String recContent = rec.getRecContent();
						recForm = rec.getRecForm();
						formDataMap = resRecBiz.parseRecContent(recContent);
						model.addAttribute("formDataMap", formDataMap);
					}
					model.addAttribute("formFileName", recTypeId);
					SchRotationDept dept=rotationDeptBiz.readStandardRotationDept(resDoctorSchProcess.getSchResultFlow());
					String type="";
					String recordFlow="";
					if(dept!=null)
					{
						if(JszyTCMPracticEnum.BasicPractice.getId().equals(dept.getPracticOrTheory()))
						{
							type="B";
						}
						recordFlow=dept.getRecordFlow();
					}
					String jspPath = resRecBiz.getFormPath(rotationFlow,recTypeId,currVer,recForm, type,medicineTypeId,recordFlow);
					model.addAttribute("jspPath", jspPath);
					return jspPath;
				}
			return "res/hospital/404";
		}
		return "res/hospital/404";
	}
	public static void main(String ags[])
	{

		for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
			System.out.println("res_registry_type_"+regType.getId()+"=Y");
		}
		System.out.println(compare_date("2016-06-30","2016-06-25"));
	}
	//时间比较
	public static int compare_date(String DATE1, String DATE2) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	/**
	 * 获取两个月份之间的所有月
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private List<String> getMonthsByTwoMonth(String startDate,String endDate){
		List<String> months = null;
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && startDate.compareTo(endDate)<=0){
			months = new ArrayList<String>();
			months.add(startDate);
			if(!startDate.equals(endDate)){
				String currDate = startDate;
				while(!currDate.equals(endDate)){
					currDate = DateUtil.newMonthOfAddMonths(currDate,1);
					months.add(currDate);
				}
			}
		}
		return months;
	}

	/**
	 * 获取两个日期之间的所有周
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private List<String> getWeeksByTwoDate(String startDate,String endDate){
		List<String> weeks = null;
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && startDate.compareTo(endDate)<=0){
			weeks = new ArrayList<String>();
			String startYear = startDate.substring(0,4);
			String endYear = endDate.substring(0,4);
			if(startYear.equals(endYear)){
				long startDays = DateUtil.signDaysBetweenTowDate(startDate,startYear+"-01-01");
				long endDays = DateUtil.signDaysBetweenTowDate(endDate,startYear+"-01-01");
				long startWeek = weekFormat(startDays);
				long endWeek = weekFormat(endDays);
				while(startWeek<=endWeek){
					weeks.add(startYear+"-"+startWeek);
					startWeek++;
				}
			}else{
				int start = Integer.parseInt(startYear);
				int end = Integer.parseInt(endYear);
				while(start<=end){
					String currYear = String.valueOf(start);
					long dayNum = 0;
					if(startYear.equals(currYear)){
						dayNum = DateUtil.signDaysBetweenTowDate(startDate,startYear+"-01-01");
						long endNum = DateUtil.signDaysBetweenTowDate(currYear+"-12-31",currYear+"-01-01");
						long startWeek = weekFormat(dayNum);
						long endWeek = weekFormat(endNum);
						while(startWeek<=endWeek){
							weeks.add(currYear+"-"+startWeek);
							startWeek++;
						}
					}else if(endYear.equals(currYear)){
						dayNum = DateUtil.signDaysBetweenTowDate(endDate,currYear+"-01-01");
						long startWeek = 1;
						long endWeek = weekFormat(dayNum);
						while(startWeek<=endWeek){
							weeks.add(currYear+"-"+startWeek);
							startWeek++;
						}
					}else{
						dayNum = DateUtil.signDaysBetweenTowDate(currYear+"-12-31",currYear+"-01-01");
						long startWeek = 1;
						long endWeek = weekFormat(dayNum);
						while(startWeek<=endWeek){
							weeks.add(currYear+"-"+startWeek);
							startWeek++;
						}
					}
					start++;
				}
			}
		}
		return weeks;
	}

	private long weekFormat(long days){
		long result = 1;
		if(days!=0){
			result = (long)Math.ceil(days/7.0);
		}
		return result;
	}



	/**
	 * 讲座查询
	 */
	@RequestMapping("/lectureView")
	public String lectureView(ResLectureInfo resLectureInfo, Model model, Integer currentPage,HttpServletRequest request,String roleId) {
		model.addAttribute("roleId", roleId);
		PageHelper.startPage(currentPage, getPageSize(request));
		String lectureTrainDate = resLectureInfo.getLectureTrainDate();
		String lectureTeacherName = resLectureInfo.getLectureTeacherName();
		String content = resLectureInfo.getLectureContent();
		String lectureTypeId = resLectureInfo.getLectureTypeId();
		String place = resLectureInfo.getLectureTrainPlace();
		SysUser user=GlobalContext.getCurrentUser();
		String orgFlow = user.getOrgFlow();
		List<ResLectureInfo> lectureInfos = resLectureInfoBiz.SearchByDateContentTeacherNameType(orgFlow,lectureTrainDate, content, lectureTeacherName, lectureTypeId,place);
		if (lectureInfos != null) {
			model.addAttribute("lectureInfos", lectureInfos);
		}
		Map<String, Integer> evaMap = new HashMap<String, Integer>();
		if (lectureInfos != null && lectureInfos.size() > 0) {
			for (ResLectureInfo lectureInfo : lectureInfos) {
				String lectureFlow = lectureInfo.getLectureFlow();
				List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.SearchByLectureFlow(lectureFlow);
				if (lectureEvaDetails != null && lectureEvaDetails.size() > 0) {
					double sum = 0, i = 0;
					for (ResLectureEvaDetail lectureEvaDetail : lectureEvaDetails) {
						String evaScore = lectureEvaDetail.getEvaScore();
						double score = 0;
						if(StringUtil.isNotBlank(evaScore)){
							score = Double.parseDouble(evaScore);
						}
						sum += score;
						i++;
					}
					double result = sum / i;
					int eva = (int) Math.round(result);
					evaMap.put(lectureFlow, eva);
				}
			}
			model.addAttribute("evaMap", evaMap);
		}
		return "res/manager/lectureView";
	}
	/**
	 * 讲座添加/修改
	 */
	@RequestMapping("/lectureDetail")
	public String addLecture(String lectureFlow, Model model) {
		ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
		model.addAttribute("lectureInfo", lectureInfo);
		if(StringUtil.isBlank(lectureFlow)) {
			lectureFlow = PkUtil.getUUID();
			model.addAttribute("signUrl", "func://funcFlow=lectureSignin&lectureFlow=" + lectureFlow);
			model.addAttribute("signOutUrl", "func://funcFlow=lectureOutSignin&lectureFlow=" + lectureFlow);
			model.addAttribute("lectureFlow",lectureFlow);
//			model.addAttribute("isNew",true);
		}else{
			model.addAttribute("signUrl", "func://funcFlow=lectureSignin&lectureFlow=" + lectureFlow);
			model.addAttribute("signOutUrl", "func://funcFlow=lectureOutSignin&lectureFlow=" + lectureFlow);
			model.addAttribute("lectureFlow",lectureFlow);
		}
		List<ResLectureInfoRole> roles=resLectureInfoBiz.readLectureRoleList(lectureFlow);
		if(roles!=null) {
			model.addAttribute("roles", roles);
		}
		return "res/manager/lectureAdd";
	}

	/**
	 * 讲座保存
	 */
	@RequestMapping("/saveLecture")
	@ResponseBody
	public String saveLecture(ResLectureInfo lectureInfo,String itemId[]){
		SysUser current = GlobalContext.getCurrentUser();
		String orgFlow = current.getOrgFlow();
		lectureInfo.setOrgFlow(orgFlow);
		String lectureTypeId = lectureInfo.getLectureTypeId();
		String lectureTypeName = DictTypeEnum.LectureType.getDictNameById(lectureTypeId);
		lectureInfo.setLectureTypeName(lectureTypeName);
		String lectureSpeakerRoleId = lectureInfo.getLectureSpeakerRoleId();
		String lectureSpeakerRoleName = DictTypeEnum.LectureSpeakerRole.getDictNameById(lectureSpeakerRoleId);
		lectureInfo.setLectureSpeakerRoleName(lectureSpeakerRoleName);
//		resLectureInfoBiz.editLectureInfo(lectureInfo);
		int resultNum = resLectureInfoBiz.addLectureInfo(lectureInfo,itemId);
		if(resultNum != 0){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}
	/**
	 * 讲座删除
	 */
	@RequestMapping("/delLecture")
	@ResponseBody
	public String delLecture(String lectureFlow){
		// 查询该讲座是否能删除
		boolean flag = resLectureInfoBiz.getDelLectureInfoFlag(lectureFlow);
		// flag:true表示不能删除 返回N 前台加提示信息
		if(flag){
			return "N";
		}
		ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
		lectureInfo.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		resLectureInfoBiz.editLectureInfo(lectureInfo);
		return GlobalConstant.DELETE_SUCCESSED;
	}
	/**
	 * 打开扫码或报名页面
	 */
	@RequestMapping("/getEva")
	public String getEva(String lectureFlow,String flag,Model model){
		model.addAttribute("flag",flag);
		model.addAttribute("lectureFlow",lectureFlow);
		List<String> roles=new ArrayList<>();

		ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
		model.addAttribute("lectureInfo",lectureInfo);

		String doctorRoleFlow= InitConfig.getSysCfg("res_doctor_role_flow");
		if (StringUtil.isNotBlank(doctorRoleFlow)) {
			roles.add(doctorRoleFlow);
		}
		List<ResLectureScanRegist> scans = resLectureScanRegistBiz.searchIsScan(lectureFlow,roles);
		model.addAttribute("scans",scans);
		List<ResLectureScanRegist> regists = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow,roles);
		model.addAttribute("regists",regists);

		roles=new ArrayList<>();
		String roleTeacher= InitConfig.getSysCfg("res_teacher_role_flow");
		if (StringUtil.isNotBlank(roleTeacher)) {
			roles.add(roleTeacher);
		}
		String roleHead= InitConfig.getSysCfg("res_head_role_flow");
		if (StringUtil.isNotBlank(roleHead)) {
			roles.add(roleHead);
		}
		String roleSecretary= InitConfig.getSysCfg("res_secretary_role_flow");
		if (StringUtil.isNotBlank(roleSecretary)) {
			roles.add(roleSecretary);
		}
		String roleDisciple= InitConfig.getSysCfg("res_disciple_role_flow");
		if (StringUtil.isNotBlank(roleDisciple)) {
			roles.add(roleDisciple);
		}
		List<ResLectureScanRegist> scans2 = resLectureScanRegistBiz.searchIsScan(lectureFlow,roles);
		model.addAttribute("scans2",scans2);
		List<ResLectureScanRegist> regists2 = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow,roles);
		model.addAttribute("regists2",regists2);
		return "res/manager/evaView";
	}

	/**
	 * 已扫码学员页面
	 */
	@RequestMapping("/evaList")
	public String evaList(String lectureFlow,Model model,String flag){
		List<String> roles=new ArrayList<>();

		ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
		model.addAttribute("lectureInfo",lectureInfo);
		//随机签到
		List<ResLectureRandomSign> randomSignList = new ArrayList<>();
		if(GlobalConstant.FLAG_Y.equals(flag)) {
			randomSignList = randomSignBiz.searchRandomByLectureFlow(lectureFlow);
		}
		model.addAttribute("randomSignList", randomSignList);
		String doctorRoleFlow= InitConfig.getSysCfg("res_doctor_role_flow");
		if (StringUtil.isNotBlank(doctorRoleFlow)) {
			roles.add(doctorRoleFlow);
		}
		// 查询评价信息
		List<Map<String,Object>> lectureTargetEvalList = resLectureScanRegistBiz.searchLectureTargetEvalList(lectureFlow, roles);
		model.addAttribute("lectureTargetEvalList", lectureTargetEvalList);
		List<Map<String, String>> lectureScanRegists = resLectureScanRegistBiz.searchIsScanNew(lectureFlow, roles);
		Map<String,String> evaDetailMap = new HashMap<>();
		Map<String,String> scanMap = new HashMap<>();
		if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
			for (Map<String, String> lectureScanRegist : lectureScanRegists) {
				String operUserFlow = lectureScanRegist.get("operUserFlow");
				List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(operUserFlow,lectureFlow);
				if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
					for(ResLectureEvaDetail evaDetail : lectureEvaDetails){
						evaDetailMap.put(evaDetail.getScanRegistFlow() + evaDetail.getTargetFlow(), evaDetail.getEvaScore());
					}
				}
				if(randomSignList!=null && randomSignList.size()>0) {
					for (int i = 0; i < randomSignList.size(); i++) {
						List<ResLectureRandomScan> scanList = randomSignBiz.searchRandomScan(operUserFlow, lectureFlow, randomSignList.get(i).getRandomFlow());
						if (scanList != null && scanList.size() > 0) {
							scanMap.put(operUserFlow + randomSignList.get(i).getRandomFlow(), DateUtil.transTime(scanList.get(0).getScanTime()));
						} else {
							scanMap.put(operUserFlow + randomSignList.get(i).getRandomFlow(), "");
						}
					}
				}
			}
			model.addAttribute("lectureScanRegists",lectureScanRegists);
		}
		model.addAttribute("evaDetailMap",evaDetailMap);
		model.addAttribute("scanMap",scanMap);
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			model.addAttribute("jszyFlag",GlobalConstant.FLAG_Y);
		}

		return "res/manager/evaList";
	}
	/**
	 * 报名页面
	 */
	@RequestMapping("/noRegist")
	public String noRegist(String lectureFlow,Model model){
		List<String> roles=new ArrayList<>();
		String doctorRoleFlow= InitConfig.getSysCfg("res_doctor_role_flow");
		if (StringUtil.isNotBlank(doctorRoleFlow)) {
			roles.add(doctorRoleFlow);
		}
		List<Map<String, String>> lectureScanRegists = resLectureScanRegistBiz.searchRegistByLectureFlowNew(lectureFlow, roles);
//		List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, roles);
		if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
			model.addAttribute("lectureScanRegists",lectureScanRegists);
		}
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			model.addAttribute("jszyFlag",GlobalConstant.FLAG_Y);
		}
		return "res/manager/noRegist";
	}

	/**
	 * 扫码页面
	 */
	@RequestMapping("/evaList2")
	public String evaList2(String lectureFlow,Model model,String flag){
		//随机签到
		List<ResLectureRandomSign> randomSignList = new ArrayList<>();
		if (GlobalConstant.FLAG_Y.equals(flag)) {
			randomSignList = randomSignBiz.searchRandomByLectureFlow(lectureFlow);
		}
		model.addAttribute("randomSignList", randomSignList);
		List<String> roles=new ArrayList<>();

		String roleTeacher= InitConfig.getSysCfg("res_teacher_role_flow");
		if (StringUtil.isNotBlank(roleTeacher)) {
			roles.add(roleTeacher);
		}
		String roleHead= InitConfig.getSysCfg("res_head_role_flow");
		if (StringUtil.isNotBlank(roleHead)) {
			roles.add(roleHead);
		}
		String roleSecretary= InitConfig.getSysCfg("res_secretary_role_flow");
		if (StringUtil.isNotBlank(roleSecretary)) {
			roles.add(roleSecretary);
		}
		String roleDisciple= InitConfig.getSysCfg("res_disciple_role_flow");
		if (StringUtil.isNotBlank(roleDisciple)) {
			roles.add(roleDisciple);
		}
		Map<String,SysUser> userMap=new HashMap<>();
		Map<String,String> teaMap=new HashMap<>();
		Map<String,String> headMap=new HashMap<>();
		Map<String,String> secretaryMap=new HashMap<>();
		Map<String,String> discipleMap=new HashMap<>();
		// 查询评价信息
		List<Map<String,Object>> lectureTargetEvalList = resLectureScanRegistBiz.searchLectureTargetEvalList(lectureFlow, roles);
		model.addAttribute("lectureTargetEvalList", lectureTargetEvalList);
		List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchIsScan(lectureFlow, roles);
		Map<String,String> evaDetailMap = new HashMap<>();
		if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
			for(ResLectureScanRegist lectureScanRegist:lectureScanRegists){
				String operUserFlow = lectureScanRegist.getOperUserFlow();
				List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(operUserFlow,lectureFlow);
				if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
					for(ResLectureEvaDetail evaDetail : lectureEvaDetails){
						evaDetailMap.put(evaDetail.getScanRegistFlow() + evaDetail.getTargetFlow(), evaDetail.getEvaScore());
					}
				}

				SysUser user=userBiz.readSysUser(operUserFlow);
				userMap.put(operUserFlow,user);
				List<SysUserRole> userRoles=userRoleBiz.getByUserFlow(user.getUserFlow());
				if(userRoles!=null)
				{
					for(SysUserRole userRole:userRoles)
					{
						if (StringUtil.isNotBlank(roleTeacher)&&roleTeacher.equals(userRole.getRoleFlow())) {
							teaMap.put(user.getUserFlow(),"Y");
						}
						if (StringUtil.isNotBlank(roleHead)&&roleHead.equals(userRole.getRoleFlow())) {
							headMap.put(user.getUserFlow(),"Y");
						}
						if (StringUtil.isNotBlank(roleSecretary) && roleSecretary.equals(userRole.getRoleFlow())) {
							secretaryMap.put(user.getUserFlow(),"Y");
						}
						if (StringUtil.isNotBlank(roleDisciple) && roleDisciple.equals(userRole.getRoleFlow())) {
							discipleMap.put(user.getUserFlow(),"Y");
						}
					}
				}
			}
			model.addAttribute("userMap",userMap);
			model.addAttribute("teaMap",teaMap);
			model.addAttribute("headMap",headMap);
			model.addAttribute("secretaryMap",secretaryMap);
			model.addAttribute("discipleMap",discipleMap);
			model.addAttribute("lectureScanRegists",lectureScanRegists);
		}
		model.addAttribute("evaDetailMap",evaDetailMap);
		List<Map<String, String>> lectureScanRegistsNew = resLectureScanRegistBiz.searchIsScanNew(lectureFlow, roles);
		Map<String, String> scanMap = new HashMap<>();
		if (lectureScanRegists != null && lectureScanRegists.size() > 0) {
			for (Map<String, String> lectureScanRegist : lectureScanRegistsNew) {
				String operUserFlow = lectureScanRegist.get("operUserFlow");
				List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(operUserFlow, lectureFlow);
				if (lectureEvaDetails != null && lectureEvaDetails.size() > 0) {
					for (ResLectureEvaDetail evaDetail : lectureEvaDetails) {
						evaDetailMap.put(evaDetail.getScanRegistFlow() + evaDetail.getTargetFlow(), evaDetail.getEvaScore());
					}
				}
				if (randomSignList != null && randomSignList.size() > 0) {
					for (int i = 0; i < randomSignList.size(); i++) {
						List<ResLectureRandomScan> scanList = randomSignBiz.searchRandomScan(operUserFlow, lectureFlow, randomSignList.get(i).getRandomFlow());
						if (scanList != null && scanList.size() > 0) {
							scanMap.put(operUserFlow + randomSignList.get(i).getRandomFlow(), DateUtil.transTime(scanList.get(0).getScanTime()));
						} else {
							scanMap.put(operUserFlow + randomSignList.get(i).getRandomFlow(), "");
						}
					}
				}
			}
		}
		model.addAttribute("scanMap", scanMap);
		return "res/manager/evaList2";
	}

	/**
	 * 报名页面
	 */
	@RequestMapping("/noRegist2")
	public String noRegist2(String lectureFlow,Model model){
		List<String> roles=new ArrayList<>();

		String roleTeacher= InitConfig.getSysCfg("res_teacher_role_flow");
		if (StringUtil.isNotBlank(roleTeacher)) {
			roles.add(roleTeacher);
		}
		String roleHead= InitConfig.getSysCfg("res_head_role_flow");
		if (StringUtil.isNotBlank(roleHead)) {
			roles.add(roleHead);
		}
		String roleSecretary= InitConfig.getSysCfg("res_secretary_role_flow");
		if (StringUtil.isNotBlank(roleSecretary)) {
			roles.add(roleSecretary);
		}
		String roleDisciple= InitConfig.getSysCfg("res_disciple_role_flow");
		if (StringUtil.isNotBlank(roleDisciple)) {
			roles.add(roleDisciple);
		}
		Map<String,SysUser> userMap=new HashMap<>();
		Map<String,String> teaMap=new HashMap<>();
		Map<String,String> headMap=new HashMap<>();
		Map<String,String> secretaryMap=new HashMap<>();
		Map<String,String> discipleMap=new HashMap<>();
		List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, roles);
		if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
			model.addAttribute("lectureScanRegists",lectureScanRegists);
			for(ResLectureScanRegist regist:lectureScanRegists)
			{
				SysUser user=userBiz.readSysUser(regist.getOperUserFlow());
				userMap.put(regist.getOperUserFlow(),user);
				List<SysUserRole> userRoles=userRoleBiz.getByUserFlow(user.getUserFlow());
				if(userRoles!=null)
				{
					for(SysUserRole userRole:userRoles)
					{
						if (StringUtil.isNotBlank(roleTeacher)&&roleTeacher.equals(userRole.getRoleFlow())) {
							teaMap.put(user.getUserFlow(),"Y");
						}
						if (StringUtil.isNotBlank(roleHead)&&roleHead.equals(userRole.getRoleFlow())) {
							headMap.put(user.getUserFlow(),"Y");
						}
						if (StringUtil.isNotBlank(roleSecretary) && roleSecretary.equals(userRole.getRoleFlow())) {
							secretaryMap.put(user.getUserFlow(),"Y");
						}
						if (StringUtil.isNotBlank(roleDisciple) && roleDisciple.equals(userRole.getRoleFlow())) {
							discipleMap.put(user.getUserFlow(),"Y");
						}
					}
				}

			}
			model.addAttribute("userMap",userMap);
			model.addAttribute("teaMap",teaMap);
			model.addAttribute("headMap",headMap);
			model.addAttribute("secretaryMap",secretaryMap);
			model.addAttribute("discipleMap",discipleMap);
		}
		return "res/manager/noRegist2";
	}

	/**
	 * 随机签到
	 * @param lectureFlow
	 * @param model
	 * @return
	 */
	@RequestMapping("/randomSignIn")
	public String randomSignIn(String lectureFlow,Model model){
		List<ResLectureRandomSign> randomSignList = randomSignBiz.searchRandomByLectureFlow(lectureFlow);
		ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
		model.addAttribute("date",lectureInfo.getLectureTrainDate());
		model.addAttribute("lectureFlow",lectureFlow);
		model.addAttribute("randomSignList",randomSignList);
		return "res/manager/randomSignIn";
	}

	/**
	 * 随机签到保存
	 */
	@RequestMapping("/saveRandomSign")
	@ResponseBody
	public String saveRandomSign(ResLectureRandomSign randomSign){
		if(GlobalConstant.FLAG_N.equals(randomSign.getCodeStatusType())){
			randomSign.setCodeStatusName("静态二维码");
		}
		if(GlobalConstant.FLAG_Y.equals(randomSign.getCodeStatusType())){
			randomSign.setCodeStatusName("动态二维码");
		}
		int i = randomSignBiz.saveRandom(randomSign);
		if(i>GlobalConstant.ZERO_LINE){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 查看随机签到二维码
	 */
	@RequestMapping("/randomSignUrl")
	public String randomSignUrl(String randomFlow,Model model){
		ResLectureRandomSign randomSign = randomSignBiz.read(randomFlow);
		model.addAttribute("signUrl", "func://funcFlow=randomSignIn&randomFlow=" + randomFlow);
		model.addAttribute("randomSign",randomSign);
		return "res/manager/randomSignCode";
	}

	/**
	 * 学员信息导出
	 * @param flag 已扫码/已报名标识
	 * @param lectureFlow 讲座流水号
	 * @param response
	 * @throws Exception
     */
	@RequestMapping(value="/expDoctorExcel", method={RequestMethod.POST,RequestMethod.GET})
	public void expDoctorExcel(String lectureFlow,String flag, HttpServletResponse response) throws Exception {
		List<ResLectureScanRegist> dataList = resLectureScanRegistBiz.queryScanRegistDocList(lectureFlow,flag);
		String[] titles = new String[]{
				"operUserName:姓名",
				"trainingSpeName:专业",
				"sessionNumber:届别"
		};
		ExcleUtile.exportSimpleExcleByObjs(titles, dataList,response.getOutputStream());
		String fileName = ("scan".equals(flag)?"扫码":"报名")+"学员名单.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping("/doctorLectureView")
	public String doctorLectureView(){
		return "res/doctor/lectureView";
	}
	/**
	 * 最新讲座查询
	 */
	@RequestMapping("/getNewLectures")
	public String getNewLectures(Integer currentPage,ResLectureInfo resLectureInfo,Model model,String roleId, HttpServletRequest request) {
		SysUser currUser = GlobalContext.getCurrentUser();
		String orgFlow = currUser.getOrgFlow();
		//获取当前配置的医师角色
		String doctorRole = InitConfig.getSysCfg("res_doctor_role_flow");
		//获取当前配置的老师角色
		String teacherRole = InitConfig.getSysCfg("res_teacher_role_flow");
		//获取当前配置的科主任角色
		String headRole = InitConfig.getSysCfg("res_head_role_flow");
		//获取当前配置的科秘角色
		String secretaryRole = InitConfig.getSysCfg("res_secretary_role_flow");
		//获取当前配置的科秘角色
		String discipleRole = InitConfig.getSysCfg("res_disciple_role_flow");
		String roleFlow="";
		if("doctor".equals(roleId)) {
			ResDoctor doctor = doctorBiz.readDoctor(currUser.getUserFlow());
			orgFlow=doctor.getSecondOrgFlow() ;
			if(StringUtil.isBlank(orgFlow))
			{
				orgFlow = doctor.getOrgFlow();
			}
			roleFlow=doctorRole;
		}
		if("teacher".equals(roleId)){
			roleFlow=teacherRole;
		}
		if("head".equals(roleId)) {
			roleFlow=headRole;
		}
		if("secretary".equals(roleId)) {
			roleFlow=secretaryRole;
		}
		if("disciple".equals(roleId)) {
			roleFlow=discipleRole;
		}

		String userFlow = currUser.getUserFlow();
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResLectureInfo> lectureInfos = resLectureInfoBiz.searchNewLectures(orgFlow,roleId,roleFlow,resLectureInfo);
		model.addAttribute("lectureInfos",lectureInfos);
		Map<String,ResLectureScanRegist> registMap = new HashMap<>();
		if(lectureInfos!=null&&lectureInfos.size()>0){
			for(ResLectureInfo lectureInfo:lectureInfos){
				String lectureFlow = lectureInfo.getLectureFlow();
				ResLectureScanRegist lectureScanRegist = resLectureScanRegistBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
				registMap.put(lectureFlow,lectureScanRegist);
			}
			model.addAttribute("registMap",registMap);
		}
		return "res/doctor/newLectures";
	}
	/**
	 * 历史讲座查询
	 */
	@RequestMapping("/getHistoryLectures")
	public String getHistoryLectures(ResLectureInfo resLectureInfo,Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		String userFlow = currentUser.getUserFlow();
		List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchByUserFLowAndRegist(userFlow);
		List<ResLectureInfo> lectureInfos = new ArrayList<ResLectureInfo>();
		Map<String,ResLectureEvaDetail> evaDetailMap = new HashMap<>();
		Map<String,Integer> evaMap = new HashMap<>();
		Map<String,String> scanMap = new HashMap<>();
		Map<String,String> scan2Map = new HashMap<>();
		if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
			String currDateTime = DateUtil.getCurrDateTime();
			String currDate = currDateTime.substring(0,4)+"-"+currDateTime.substring(4,6)+"-"+currDateTime.substring(6,8);
			String currTime = currDateTime.substring(8,10)+":"+currDateTime.substring(10,12);
			for(ResLectureScanRegist lectureScanRegist:lectureScanRegists){
				String isScan = lectureScanRegist.getIsScan();
				String isScan2 = lectureScanRegist.getIsScan2();
				String lectureFlow = lectureScanRegist.getLectureFlow();
				ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
				String lectureEndTime = lectureInfo.getLectureEndTime();
				String lectureTrainDate = lectureInfo.getLectureTrainDate();
				//判断是否到评价期限
				String date = lectureInfo.getLectureTrainDate();
				String time = lectureInfo.getLectureEndTime();
				String unitID = lectureInfo.getLectureUnitId();
				String period = lectureInfo.getLectureEvaPeriod();
				String startDate = date.substring(0,4)+date.substring(5,7)+date.substring(8,10)+time.substring(0,2)+time.substring(3,5)+"00";
				int step = 0;
				if(SchUnitEnum.Hour.getId().equals(unitID)){
					step = Integer.parseInt(period);
				}
				if(SchUnitEnum.Day.getId().equals(unitID)){
					step = Integer.parseInt(period)*24;
				}
				if(SchUnitEnum.Week.getId().equals(unitID)){
					step = Integer.parseInt(period)*24*7;
				}
				if(SchUnitEnum.Month.getId().equals(unitID)){
					step = Integer.parseInt(period)*24*30;
				}
				if(SchUnitEnum.Year.getId().equals(unitID)){
					step = Integer.parseInt(period)*24*365;
				}
				String endDate = DateUtil.addHour(startDate,step);
				String currentDate = DateUtil.getCurrDateTime();
				int dateFlag = endDate.compareTo(currentDate);
				//判断结束
				if(GlobalConstant.RECORD_STATUS_Y.equals(lectureInfo.getRecordStatus())&&((lectureEndTime.compareTo(currTime)<0&&lectureTrainDate.compareTo(currDate)==0)||(lectureTrainDate.compareTo(currDate)<0))){
					lectureInfos.add(lectureInfo);
					if(GlobalConstant.FLAG_Y.equals(isScan))
					{
						scanMap.put(lectureFlow,"Y");
					}
					if(GlobalConstant.FLAG_Y.equals(isScan2)) {
						scan2Map.put(lectureFlow, "Y");
					}
					evaMap.put(lectureFlow,dateFlag);
				}
				List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(userFlow,lectureFlow);
				if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
					ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
					evaDetailMap.put(lectureFlow,lectureEvaDetail);
				}
			}
		}
		String lectureTrainDate = resLectureInfo.getLectureTrainDate();
		String lectureTeacherName = resLectureInfo.getLectureTeacherName();
		String content = resLectureInfo.getLectureContent();
		String lectureTypeId = resLectureInfo.getLectureTypeId();
		String place = resLectureInfo.getLectureTrainPlace();
		if (lectureInfos != null && lectureInfos.size() > 0) {
			if (StringUtil.isNotBlank(lectureTrainDate)) {
				//过滤获取讲座日期与参数一样的
				lectureInfos = lectureInfos.stream().filter(resLectureInfoNew ->
						lectureTrainDate.equals(resLectureInfoNew.getLectureTrainDate())).collect(Collectors.toList());
			}
			//过滤获取主讲人与参数一样的（模糊查询）
			if (StringUtil.isNotBlank(lectureTeacherName)) {
				lectureInfos = lectureInfos.stream().filter(resLectureInfoNew ->
						StringUtil.isNotBlank(resLectureInfoNew.getLectureTeacherName()) &&
								resLectureInfoNew.getLectureTeacherName().contains(lectureTeacherName)).collect(Collectors.toList());
			}
			//过滤获取讲座标题与参数一样的（模糊查询）
			if (StringUtil.isNotBlank(content)) {
				lectureInfos = lectureInfos.stream().filter(resLectureInfoNew ->
						StringUtil.isNotBlank(resLectureInfoNew.getLectureContent()) &&
								resLectureInfoNew.getLectureContent().contains(content)).collect(Collectors.toList());
			}
			//过滤获取讲座类型与参数一样的
			if (StringUtil.isNotBlank(lectureTypeId)) {
				lectureInfos = lectureInfos.stream().filter(resLectureInfoNew ->
						lectureTypeId.equals(resLectureInfoNew.getLectureTypeId())).collect(Collectors.toList());
			}
			//过滤获取讲座地点与参数一样的（模糊查询）
			if (StringUtil.isNotBlank(place)) {
				lectureInfos = lectureInfos.stream().filter(resLectureInfoNew ->
						StringUtil.isNotBlank(resLectureInfoNew.getLectureTrainPlace()) &&
								resLectureInfoNew.getLectureTrainPlace().contains(place)).collect(Collectors.toList());
			}
		}
		model.addAttribute("scanMap",scanMap);
		model.addAttribute("scan2Map",scan2Map);
		model.addAttribute("evaMap",evaMap);
		model.addAttribute("evaDetailMap",evaDetailMap);
		model.addAttribute("lectureInfos",lectureInfos);
		return "res/doctor/historyLectures";
	}
	/**
	 * 报名讲座
	 */
	@RequestMapping("/lectureRegist")
	public synchronized String lectureRegist(String lectureFlow,Model model){
		boolean successFlag = true;
		ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
		if(resLectureScanRegistBiz.editLectureScanRegist(lectureFlow,lectureInfo.getLimitNum())==0){
			successFlag = false;
		}
		model.addAttribute("successFlag",successFlag);
		if(successFlag){
			String lectureTrainDate = lectureInfo.getLectureTrainDate();
			String lectureStartTime = lectureInfo.getLectureStartTime();
			String year = lectureTrainDate.substring(0,4);
			model.addAttribute("year",year);
			String month = lectureTrainDate.substring(5,7);
			model.addAttribute("month",month);
			String day =  lectureTrainDate.substring(8,10);
			model.addAttribute("day",day);
			String hour = lectureStartTime.substring(0,2);
			model.addAttribute("hour",hour);
			String min = lectureStartTime.substring(3,5);
			model.addAttribute("min",min);
		}
		return "res/doctor/lectureRegist";
	}
	/**
	 * 进入评分页面
	 */
	@RequestMapping("/evaluate")
	public String evaluate(String lectureFlow,Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		// 查询评价人信息
		ResLectureScanRegist scanRegist = resLectureScanRegistBiz.searchByUserFlowAndLectureFlow(currUser.getUserFlow(), lectureFlow);
		model.addAttribute("scanRegist", scanRegist);
		// 查询评价指标信息
		List<LectureInfoTarget> lectureTargetList = resLectureScanRegistBiz.searchLectureInfoTargetList(lectureFlow);
		model.addAttribute("lectureTargetList", lectureTargetList);
		return "res/doctor/addEvaluate";
	}


	/**
	 * 保存评价
	 */
	@RequestMapping("/saveEvaluate")
	@ResponseBody
	public String saveEvaluate(ResLectureEvaDetail resLectureEvaDetail){
		SysUser current = GlobalContext.getCurrentUser();
		String userFlow = current.getUserFlow();
		String userName = current.getUserName();
		if(StringUtil.isNotBlank(userFlow)){
			resLectureEvaDetail.setOperUserFlow(userFlow);
		}
		if(StringUtil.isNotBlank(userName)){
			resLectureEvaDetail.setOperUserName(userName);
		}
		List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(userFlow,resLectureEvaDetail.getLectureFlow());
		if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0) {
			return  "已经评价过讲座信息！请刷新页面后重试！";
		}
		resLectureEvaDetailBiz.editResLectureEvaDetail(resLectureEvaDetail);
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	/**
	 * 打开签到二维码
	 */
	@RequestMapping("/signUrl")
	public String signUrl(String lectureFlow,Model model){
		ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
		String signUrl = lectureInfo.getLectureCodeUrl();
		String signOutUrl = lectureInfo.getLectureOutUrl();
		model.addAttribute("orgFlow",lectureInfo.getOrgFlow());
		model.addAttribute("signUrl",signUrl);
		model.addAttribute("signOutUrl",signOutUrl);
		model.addAttribute("lectureInfo",lectureInfo);
		return "res/manager/lectureSign";
	}

    /**
     * 带教工作量查询导出
     * @param schDept
     * @param operStartDate
     * @param userName
     * @param operEndDate
     * @param response
     * @throws Exception
     */
    @RequestMapping("/exportTeacherWorkload")
    @ResponseBody
    public void exportTeacherWorkload(SchDept schDept,String[] doctorTypeIdList, String operStartDate, String userName, String operEndDate,
									  String roleFlag,String role,String orgFlow,HttpServletResponse response) throws Exception {
    	String[] titles = {
                "userName:带教老师",
                "orgName:基地",
                "schDeptName:科室",
                "auditNumber:已审核数",
                "notAuditNumber:未审核数",
                "isCurrentFlagNumber:当前带教人数",
                "schFlagNumber:已出科学员",
                "allNumber:总带教学员"
        };

        List<String> recTypeIds = new ArrayList<String>();
        for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
            if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))){
                recTypeIds.add(regType.getId());
            }
        }
		List<String> deptFlows=null;
		if("professionalBase".equals(role)){
			String trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
			ResTrainingSpeDept search = new ResTrainingSpeDept();//查出当前专业基地的所有科室
			search.setOrgFlow(orgFlow);
			search.setTrainingSpeId(trainingSpeId);
			List<ResTrainingSpeDept> trainingSpeDeptList = resTrainingSpeDeptBiz.search(search);

			deptFlows = new ArrayList<>();
			if(trainingSpeDeptList!=null&&trainingSpeDeptList.size()>0){
				for(ResTrainingSpeDept resTrainingSpeDept:trainingSpeDeptList){
					String deptFLow = resTrainingSpeDept.getDeptFlow();
					deptFlows.add(deptFLow);
				}
			}
		}
		SysUser curUser=GlobalContext.getCurrentUser();
		List<SysOrg> orgList=new ArrayList<>();
		List<String> orgFlowList = new ArrayList<>();
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){

		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
			//查询高校下所有医院
			SysOrg currentOrg = orgBiz.readSysOrg(curUser.getOrgFlow());
			String workOrgId = currentOrg.getSendSchoolId();
			orgList = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			if(orgList!=null&&orgList.size()>0){
				for(SysOrg org:orgList){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
		}else if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
			//医院管理员增加查协同基地学员轮转情况
			SysOrg sysOrg=orgBiz.readSysOrg(curUser.getOrgFlow());
			orgFlowList.add(curUser.getOrgFlow());
			List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			if(jointOrgList!=null&&jointOrgList.size()>0){
				for(SysOrg org:jointOrgList){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
		}
		//查询后台配置是否为进修过程管理界面
		String flag = InitConfig.getSysCfg("is_show_jxres");
		//复选框勾选标识
		Map<String,String> doctorTypeSelectMap = new HashMap<>();
		SysDict sysDict = new SysDict();
		sysDict.setDictTypeId(DictTypeEnum.DoctorType.getId());
		sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		List<String> typeList =null;
		if(dictList!=null&&dictList.size()>0&&doctorTypeIdList!=null&&doctorTypeIdList.length>0){
			typeList=Arrays.asList(doctorTypeIdList);
			for (SysDict dict:dictList){
				if(typeList.contains(dict.getDictId())){
					doctorTypeSelectMap.put(dict.getDictId(),"checked");
				}
			}
		}
		if(doctorTypeIdList==null)
		{
			typeList=new ArrayList<>();
			for (SysDict dict:dictList){
				typeList.add(dict.getDictId());
				doctorTypeSelectMap.put(dict.getDictId(),"checked");
			}
		}
        Map<String,Object> map=new HashMap<String,Object>();
		map.put("deptFlows",deptFlows);
        map.put("schDept",schDept);
        map.put("operStartDate",operStartDate);
        map.put("operEndDate",operEndDate);
        map.put("recTypeIds",recTypeIds);
        map.put("userName",userName);
        map.put("orgFlow",orgFlow);
        map.put("orgFlowList",orgFlowList);
		String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
		map.put("roleFlow",teacherRoleFlow);
		if(!GlobalConstant.FLAG_Y.equals(flag)){
			map.put("doctorTypeIdList",typeList);
		}
        List<Map<String,Object>> list=processBiz.searchTeacherWorkload(map);
		Map<String,Object> allDocNumMap=new HashMap<>();
		Map<String,Object> currDocNumMap=new HashMap<>();
		Map<String,Object> schDocNumMap=new HashMap<>();
		Map<String,Object> auditNumMap=new HashMap<>();
		Map<String,Object> notAuditNumMap=new HashMap<>();
		if(list!=null&&list.size()>0)
		{

			List<String> teacherFlows=new ArrayList<>();
			for(Map<String,Object> b:list)
			{
				String teacherFlow= (String) b.get("userFlow");
				if(StringUtil.isNotBlank(teacherFlow))
				{
					teacherFlows.add(teacherFlow);
				}
			}
			map.put("teacherFlows",teacherFlows);
			allDocNumMap=processBiz.getTeaAllDocNumMap(map);
			currDocNumMap=processBiz.getTeaCurrDocNumMap(map);
			schDocNumMap=processBiz.getTeaSchDocNumMap(map);
			auditNumMap=processBiz.getTeaAuditNumMap(map);
			notAuditNumMap=processBiz.getTeaNotAuditNumMap(map);
			for(Map<String,Object> b:list)
			{
				String teacherFlow= (String) b.get("userFlow");

				b.put("auditNumber",auditNumMap.get(teacherFlow)==null?"0":auditNumMap.get(teacherFlow));
				b.put("notAuditNumber",notAuditNumMap.get(teacherFlow)==null?"0":notAuditNumMap.get(teacherFlow));
				b.put("allNumber",allDocNumMap.get(teacherFlow)==null?"0":allDocNumMap.get(teacherFlow));
				b.put("isCurrentFlagNumber",currDocNumMap.get(teacherFlow)==null?"0":currDocNumMap.get(teacherFlow));
				b.put("schFlagNumber",schDocNumMap.get(teacherFlow)==null?"0":schDocNumMap.get(teacherFlow));
			}

		}
        ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream());
        String fileName = "带教工作量导出.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

	/**
	 * 带教工作量查询导出(青浦)
	 * @param schDept
	 * @param operStartDate
	 * @param userName
	 * @param operEndDate
	 * @param role
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/exportTeacherWorkload4qingpu")
	@ResponseBody
	public void exportTeacherWorkload4qingpu(SchDept schDept,String[] doctorTypeIdList, String operStartDate, String userName, String operEndDate,
									  String role,HttpServletResponse response) throws Exception {
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		String[] titles = {
				"userName:带教老师",
				"schDeptName:科室",
				"auditNumber:已审核数",
				"notAuditNumber:未审核数",
				"isCurrentFlagNumber:当前带教人数",
				"schFlagNumber:已出科学员",
				"allNumber:总带教学员",
				"lectureNumber:讲座总数"
		};

		List<String> recTypeIds = new ArrayList<String>();
		for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))){
				recTypeIds.add(regType.getId());
			}
		}
		List<String> deptFlows=null;
		if("professionalBase".equals(role)){
			String trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
			ResTrainingSpeDept search = new ResTrainingSpeDept();//查出当前专业基地的所有科室
			search.setOrgFlow(orgFlow);
			search.setTrainingSpeId(trainingSpeId);
			List<ResTrainingSpeDept> trainingSpeDeptList = resTrainingSpeDeptBiz.search(search);

			deptFlows = new ArrayList<>();
			if(trainingSpeDeptList!=null&&trainingSpeDeptList.size()>0){
				for(ResTrainingSpeDept resTrainingSpeDept:trainingSpeDeptList){
					String deptFLow = resTrainingSpeDept.getDeptFlow();
					deptFlows.add(deptFLow);
				}
			}
		}

		//查询后台配置是否为进修过程管理界面
		String flag = InitConfig.getSysCfg("is_show_jxres");
		//复选框勾选标识
		Map<String,String> doctorTypeSelectMap = new HashMap<>();
		SysDict sysDict = new SysDict();
		sysDict.setDictTypeId(DictTypeEnum.DoctorType.getId());
		sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		List<String> typeList =null;
		if(dictList!=null&&dictList.size()>0&&doctorTypeIdList!=null&&doctorTypeIdList.length>0){
			typeList=Arrays.asList(doctorTypeIdList);
			for (SysDict dict:dictList){
				if(typeList.contains(dict.getDictId())){
					doctorTypeSelectMap.put(dict.getDictId(),"checked");
				}
			}
		}
		if(doctorTypeIdList==null)
		{
			typeList=new ArrayList<>();
			for (SysDict dict:dictList){
				typeList.add(dict.getDictId());
				doctorTypeSelectMap.put(dict.getDictId(),"checked");
			}
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deptFlows",deptFlows);
		map.put("schDept",schDept);
		map.put("operStartDate",operStartDate);
		map.put("operEndDate",operEndDate);
		map.put("recTypeIds",recTypeIds);
		map.put("userName",userName);
		map.put("orgFlow",orgFlow);
		String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
		map.put("roleFlow",teacherRoleFlow);
		if(!GlobalConstant.FLAG_Y.equals(flag)){
			map.put("doctorTypeIdList",typeList);
		}
		List<Map<String,Object>> list=processBiz.searchTeacherWorkload(map);
		Map<String,Object> allDocNumMap=new HashMap<>();
		Map<String,Object> currDocNumMap=new HashMap<>();
		Map<String,Object> schDocNumMap=new HashMap<>();
		Map<String,Object> lectureNumMap=new HashMap<>();
		Map<String,Object> auditNumMap=new HashMap<>();
		Map<String,Object> notAuditNumMap=new HashMap<>();
		if(list!=null&&list.size()>0)
		{

			List<String> teacherFlows=new ArrayList<>();
			for(Map<String,Object> b:list)
			{
				String teacherFlow= (String) b.get("userFlow");
				if(StringUtil.isNotBlank(teacherFlow))
				{
					teacherFlows.add(teacherFlow);
				}
			}
			map.put("teacherFlows",teacherFlows);
			allDocNumMap=processBiz.getTeaAllDocNumMap(map);
			currDocNumMap=processBiz.getTeaCurrDocNumMap(map);
			schDocNumMap=processBiz.getTeaSchDocNumMap(map);
			lectureNumMap=processBiz.getTeaLectureNumMap(map);
			auditNumMap=processBiz.getTeaAuditNumMap(map);
			notAuditNumMap=processBiz.getTeaNotAuditNumMap(map);
			for(Map<String,Object> b:list)
			{
				String teacherFlow= (String) b.get("userFlow");

				b.put("auditNumber",auditNumMap.get(teacherFlow)==null?"0":auditNumMap.get(teacherFlow));
				b.put("notAuditNumber",notAuditNumMap.get(teacherFlow)==null?"0":notAuditNumMap.get(teacherFlow));
				b.put("allNumber",allDocNumMap.get(teacherFlow)==null?"0":allDocNumMap.get(teacherFlow));
				b.put("isCurrentFlagNumber",currDocNumMap.get(teacherFlow)==null?"0":currDocNumMap.get(teacherFlow));
				b.put("schFlagNumber",schDocNumMap.get(teacherFlow)==null?"0":schDocNumMap.get(teacherFlow));
				b.put("lectureNumber",lectureNumMap.get(teacherFlow)==null?"0":lectureNumMap.get(teacherFlow));
			}

		}
		ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream());
		String fileName = "带教工作量导出.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

    @RequestMapping("/exportDocWorking")
    @ResponseBody
    public void exportDocWorking (String trainingSpeId,
                           String sessionNumber,String docName,
								  String role,String orgFlow,
                           String cardNo,HttpServletResponse response) throws Exception {
        String[] titles = new String[6];
        titles[0] = "姓名";
        titles[1] = "专业";
        titles[2] = "届别";
        titles[3] = "培训数据";
        titles[5] = "轮转科室";
        String[] titles2 = {
                "doctorName:",
                "speName:",
                "sessionNumber:",
                "reqNum:要求数",
                "completeNum:完成数",
                "rotationNum:要求数",
                "lunzhuanNum:已轮转",
                "chukeNum:已出科"
        };
        List<String> recTypeIds = new ArrayList<String>();
        for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
            if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))){
                recTypeIds.add(regType.getId());
            }
        }
		if("professionalBase".equals(role)){
			trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
		}
		List<String> doctorFlows = new ArrayList<>();
		boolean flag2 = false;
		if("responsibleTeacher".equals(role)){
			ResResponsibleteacherDoctor search = new ResResponsibleteacherDoctor();
			search.setResponsibleteacherFlow(GlobalContext.getCurrentUser().getUserFlow());
			List<ResResponsibleteacherDoctor> resResponsibleteacherDoctorList = resResponsibleTeacherDoctorBiz.search(search);
			if(resResponsibleteacherDoctorList!=null&&resResponsibleteacherDoctorList.size()>0){
				for(ResResponsibleteacherDoctor resResponsibleteacherDoctor:resResponsibleteacherDoctorList){
					String doctorFlow = resResponsibleteacherDoctor.getDoctorFlow();
					doctorFlows.add(doctorFlow);
				}
			}else {
				flag2 = true;
			}
		}
		SysUser curUser=GlobalContext.getCurrentUser();
		List<SysOrg> orgList=new ArrayList<>();
		List<String> orgFlowList = new ArrayList<>();
		String workOrgId = "";
		if(GlobalConstant.USER_LIST_GLOBAL.equals(role)){

		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
			//查询高校下所有医院
			SysOrg currentOrg = orgBiz.readSysOrg(curUser.getOrgFlow());
			workOrgId = currentOrg.getSendSchoolId();
			orgList = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			if(orgList!=null&&orgList.size()>0){
				for(SysOrg org:orgList){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
		}else if(GlobalConstant.USER_LIST_LOCAL.equals(role)){
			//医院管理员增加查协同基地学员轮转情况
			SysOrg sysOrg=orgBiz.readSysOrg(curUser.getOrgFlow());
			orgList.add(sysOrg);
			orgFlowList.add(curUser.getOrgFlow());
			List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			if(jointOrgList!=null&&jointOrgList.size()>0){
				for (SysOrg so:jointOrgList) {
					orgList.add(so);
				}
			}
			if(jointOrgList!=null&&jointOrgList.size()>0){
				for(SysOrg org:jointOrgList){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
		}
		Map<String, Object> parMp = new HashMap<String, Object>();
		parMp.put("doctorFlows",doctorFlows);
		parMp.put("speId", trainingSpeId);
        parMp.put("orgFlow", orgFlow);
        parMp.put("orgFlowList", orgFlowList);
        parMp.put("sessionNumber", sessionNumber);
        parMp.put("docName", docName);
        parMp.put("cardNo", cardNo);
        parMp.put("recTypeIds",recTypeIds);
        String flag=InitConfig.getSysCfg("res_custom_result_flag")==null?"N":InitConfig.getSysCfg("res_custom_result_flag");
        parMp.put("flag",flag);
        parMp.put("workOrgId",workOrgId);
        List<Map<String,Object>> rltLst = processBiz.docWorkingSearch(parMp);
		if(flag2){
			rltLst = null;
		}
        ExcleUtile.exportExcleExpertProj(titles, titles2, rltLst, response.getOutputStream());
        String fileName = "医师工作量导出.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        //return "res/hospital/docWorking";
    }

	@RequestMapping("/exportDocWorking4jszy")
	@ResponseBody
	public void exportDocWorking4jszy(String trainingSpeId,String doctorCategoryId,
									  String sessionNumber, String docName,String graduationYear,String pynx,
									  String role, String order,String orgFlow,String[] studentTypes,
									  String cardNo, HttpServletResponse response) throws Exception {
		List<String> recTypeIds = new ArrayList<String>();
		for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))){
				recTypeIds.add(regType.getId());
			}
		}
		if ("professionalBase".equals(role)) {
			if(StringUtil.isBlank(orgFlow)){
				orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			}
			trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
		}
		List<String> doctorFlows = new ArrayList<>();
		boolean flag2 = false;
		if ("responsibleTeacher".equals(role)) {
			if(StringUtil.isBlank(orgFlow)){
				orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			}
			ResResponsibleteacherDoctor search = new ResResponsibleteacherDoctor();
			search.setResponsibleteacherFlow(GlobalContext.getCurrentUser().getUserFlow());
			List<ResResponsibleteacherDoctor> resResponsibleteacherDoctorList = resResponsibleTeacherDoctorBiz.search(search);
			if (resResponsibleteacherDoctorList != null && resResponsibleteacherDoctorList.size() > 0) {
				for (ResResponsibleteacherDoctor resResponsibleteacherDoctor : resResponsibleteacherDoctorList) {
					String doctorFlow = resResponsibleteacherDoctor.getDoctorFlow();
					doctorFlows.add(doctorFlow);
				}
			} else {
				flag2 = true;
			}
		}
		SysUser curUser=GlobalContext.getCurrentUser();
		List<SysOrg> orgList=new ArrayList<>();
		List<String> orgFlowList = new ArrayList<>();
		String workOrgId = "";
		if(GlobalConstant.USER_LIST_GLOBAL.equals(role)){

		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
			//查询高校下所有医院
			SysOrg currentOrg = orgBiz.readSysOrg(curUser.getOrgFlow());
			workOrgId = currentOrg.getSendSchoolId();
			orgList = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			if(orgList!=null&&orgList.size()>0){
				for(SysOrg org:orgList){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
		}else if(GlobalConstant.USER_LIST_LOCAL.equals(role)){
			//医院管理员增加查协同基地学员轮转情况
			SysOrg sysOrg=orgBiz.readSysOrg(curUser.getOrgFlow());
			orgList.add(sysOrg);
			orgFlowList.add(curUser.getOrgFlow());
			List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			if(jointOrgList!=null&&jointOrgList.size()>0){
				for (SysOrg so:jointOrgList) {
					orgList.add(so);
				}
			}
			if(jointOrgList!=null&&jointOrgList.size()>0){
				for(SysOrg org:jointOrgList){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
		}
		Map<String, Object> parMp = new HashMap<String, Object>();
		parMp.put("doctorFlows", doctorFlows);
		parMp.put("speId", trainingSpeId);
		parMp.put("doctorCategoryId", doctorCategoryId);
		parMp.put("orgFlow", orgFlow);
		parMp.put("orgFlowList", orgFlowList);
		parMp.put("sessionNumber", sessionNumber);
		parMp.put("docName", docName);
		parMp.put("cardNo", cardNo);
		parMp.put("graduationYear", graduationYear);
		parMp.put("trainingYears", pynx);//培养年限
		parMp.put("recTypeIds", recTypeIds);
		String flag = InitConfig.getSysCfg("res_custom_result_flag") == null ? "N" : InitConfig.getSysCfg("res_custom_result_flag");
		parMp.put("flag", flag);
		parMp.put("order", StringUtil.isBlank(order) ? "wcxx" : order);//排序规则
		parMp.put("workOrgId",workOrgId);
		List<String> docTypeList = new ArrayList<>();
		if(studentTypes!=null&&studentTypes.length>0){
			docTypeList = Arrays.asList(studentTypes);
		}
		//查询后台配置是否为进修过程管理页面
		String showflag = InitConfig.getSysCfg("is_show_jxres");
		if(!GlobalConstant.FLAG_Y.equals(showflag)){
			parMp.put("docTypeList",docTypeList);
		}
		List<Map<String, Object>> rltLst = processBiz.docWorkingSearch(parMp);
		if (flag2) {
			rltLst = null;
		}

		//查询学员对应的跟师数据完成数
		//discipleDocFlows用于查询学员对应的跟师数据完成数 学员的流水号集合
		List<String> discipleDocFlows = new ArrayList<>();
		//不同年级、不同专业、不同跟师记录类型（NoteTypeEnum）的要求数不一样
		//discipleDocKeyMap key是doctorFlow，value是学员的年级+专业
		Map<String, String> discipleDocKeyMap = new HashMap<>();
		//discipleDocTrainingYearsMap key是doctorFlow，value是学员的培养年限
		Map<String, String> discipleDocTrainingYearsMap = new HashMap<>();
		if (rltLst != null && rltLst.size() > 0) {
			for (Map<String, Object> tempMap : rltLst) {
				/**江苏中医要求数需根据实际培养年限进行缩减*/
				if(!"中医助理全科".equals(tempMap.get("trainingTypeName")==null?"":tempMap.get("trainingTypeName").toString())){
					String reqNumTemp=tempMap.get("reqNum")==null?"":tempMap.get("reqNum").toString();
					String trainingYearsTemp=tempMap.get("trainingYears")==null?"":tempMap.get("trainingYears").toString();
					if(StringUtil.isNotBlank(reqNumTemp)&&StringUtil.isNotBlank(trainingYearsTemp)){
						BigDecimal b = new BigDecimal(Double.parseDouble(reqNumTemp)*Double.parseDouble(trainingYearsTemp)/30.0);
						double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue()*10;
						tempMap.put("reqNum",(int)f1);
					}
				}
				String tempDoctorFlow = tempMap.get("doctorFlow")==null?"":tempMap.get("doctorFlow").toString();
				discipleDocFlows.add(tempDoctorFlow);
				String discipleSessionNumber = tempMap.get("sessionNumber")==null?"":tempMap.get("sessionNumber").toString();
				String discipleTrainingTypeId = tempMap.get("trainingTypeId")==null?"":tempMap.get("trainingTypeId").toString();
				String discipleTrainingYears = tempMap.get("trainingYears")==null?"":tempMap.get("trainingYears").toString();
				//将学员对应的年级培训专业取出备用
				discipleDocKeyMap.put(tempDoctorFlow, discipleSessionNumber + discipleTrainingTypeId);
				discipleDocTrainingYearsMap.put(tempDoctorFlow, discipleTrainingYears);
			}
		}
		//查询每个学生总的完成数
		//discipleInfoFinMaps中 Map的key是学员流水号doctorFlow，跟师类型noteTypeId，完成数discipleCount
		List<Map<String, String>> discipleInfoFinMaps = discipleInfoBiz.findDiscipleInfoFinbyDocFlow(discipleDocFlows);
		//将每个学员的完成数放入map,key是doctorFlow，value是完成数
		Map<String, String> discipleInfoFinMap4DocFlow = null;
		if (discipleInfoFinMaps != null && discipleInfoFinMaps.size() > 0) {
			discipleInfoFinMap4DocFlow = new HashMap<>();
			for (Map<String, String> tempMap : discipleInfoFinMaps) {
				String discipleCount = tempMap.get("discipleCount");
				String doctorFlow = tempMap.get("doctorFlow");
				String noteTypeId = tempMap.get("noteTypeId");
				String key = doctorFlow + noteTypeId;
				discipleCount = Integer.parseInt(discipleCount) + "";
				discipleInfoFinMap4DocFlow.put(key, discipleCount);
			}
		}
		//查询对应年级、专业的年要求数
		//discipleInfoReqMaps中 Map的key是key（sessionNumber+trainingTypeId+noteTypeId），要求数reqNumber
		List<Map<String, String>> discipleInfoReqMaps = discipleInfoBiz.findEveDiscipleInfoReq();
		//将不同年级不同培训专业下的要求数放入map,key是sessionNumber+trainingTypeId，value是要求数
		Map<String, String> discipleInfoReqMap4DocFlow = null;
		if (discipleInfoReqMaps != null && discipleInfoReqMaps.size() > 0) {
			discipleInfoReqMap4DocFlow = new HashMap<>();
			for (Map<String, String> tempMap : discipleInfoReqMaps) {
				String key = tempMap.get("key");
				String reqNumber = tempMap.get("reqNumber");
				discipleInfoReqMap4DocFlow.put(key, reqNumber);
			}
		}
		//计算每个学员的跟师数据完成比例并放入map中key是doctorFlow，value是比例
		//discipleCountPerCent key是doctorFlow，value是比例
		Map<String, String> discipleCountPerCent = new HashMap<>();
		if (discipleDocFlows != null && discipleDocFlows.size() > 0) {
			for (String tempDoctorFlow : discipleDocFlows) {
				if (discipleInfoReqMap4DocFlow != null && discipleInfoReqMap4DocFlow.size() > 0) {
					Enum[] noteTypeEnums = NoteTypeEnum.values();
					//查询学员对应的年级专业下是否有维护要求数，如果没有用‘-’代替
					Boolean ifHasReq = false;
					String key = discipleDocKeyMap.get(tempDoctorFlow);
					for (Enum temp : noteTypeEnums) {
						String tempReq = discipleInfoReqMap4DocFlow.get(key + temp.toString());
						if (StringUtil.isNotBlank(tempReq)) {
							ifHasReq = true;
							continue;
						}
					}

					if (ifHasReq) {
						if (discipleInfoFinMap4DocFlow != null && discipleInfoFinMap4DocFlow.size() > 0) {
							//学员的培养年限计算比例留用,因配置的要求数是每一年的，故需要乘以培养年份
							String trainingYears = StringUtil.isBlank(discipleDocTrainingYearsMap.get(tempDoctorFlow))?"0":discipleDocTrainingYearsMap.get(tempDoctorFlow);
							//有要求数的跟师类型的数量用于计算比例
							int reqNoteTypeIdSum = 0;
							//学员跟师数据单项要求数
							String reqCount = "";
							//学员跟师数据单项完成数
							String finCount = "";
							//学员跟师数据单项完成数与单项要求数之比的和
							Double finPerReqSum = 0.0;

							for (Enum temp : noteTypeEnums) {
								String noteTypeId = temp.toString();
								//学员跟师数据单项要求数
								reqCount = discipleInfoReqMap4DocFlow.get(discipleDocKeyMap.get(tempDoctorFlow) + noteTypeId);
								if (StringUtil.isNotBlank(reqCount)) {
									reqCount = Integer.parseInt(reqCount) * Integer.parseInt(trainingYears) + "";
									//如果该项没有要求数则不计算完成数
									reqNoteTypeIdSum = reqNoteTypeIdSum + 1;
									//学员跟师数据单项完成数
									finCount = discipleInfoFinMap4DocFlow.get(tempDoctorFlow + noteTypeId);
									if (StringUtil.isNotBlank(finCount)) {
										//完成数大于等于要求数比例为1
										if (Integer.parseInt(reqCount) <= Integer.parseInt(finCount)) {
											finPerReqSum += 100;
										} else {
											Double perCent1 = 100 * Double.parseDouble(finCount) / Double.parseDouble(reqCount);
											BigDecimal b = new BigDecimal(perCent1);
											//保留1位小数
											Double perCent2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
											finPerReqSum += perCent2;
										}
									} else {
										//完成数为空的情况下，如果要求数为0，单项完成比例应按照100%计算
										if ("0".equals(reqCount)) {
											finPerReqSum += 100;
										}
									}

								}
							}
							//比例
							if (reqNoteTypeIdSum != 0) {
								//完成比例=总比例/总个数
								Double perCent1 = finPerReqSum / reqNoteTypeIdSum;
								BigDecimal b = new BigDecimal(perCent1);
								//保留2位小数
								String perCent2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
								if("100.00".equals(perCent2)){
									perCent2 = "100";
								}
								if("0.00".equals(perCent2)){
									perCent2 = "0";
								}
								discipleCountPerCent.put(tempDoctorFlow, perCent2 + "");
							} else {
								//如果该年级+专业的要求数为空则比例为“-”
								discipleCountPerCent.put(tempDoctorFlow, "-");
							}
						} else {
							//如果要求数不为为空，完成数为空则比例为“0”
							discipleCountPerCent.put(tempDoctorFlow, "0");
						}
					} else {
						//如果要求数为空则比例为“-”
						discipleCountPerCent.put(tempDoctorFlow, "-");
					}
				} else {
					//如果要求数为空则比例为“-”
					discipleCountPerCent.put(tempDoctorFlow, "-");
				}
			}
		}

		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//列宽自适应
//        sheet.setDefaultColumnWidth((short)50);
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));//合并单元格
		HSSFRow rowOne = sheet.createRow(0);//第一行
		HSSFCell cellOne = rowOne.createCell(0);
		cellOne.setCellStyle(stylevwc);
		cellOne.setCellValue("姓名");
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));//合并单元格
		HSSFCell cellTwo = rowOne.createCell(1);
		cellTwo.setCellStyle(stylevwc);
		cellTwo.setCellValue("专业");
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));//合并单元格
		HSSFCell cellThree = rowOne.createCell(2);
		cellThree.setCellStyle(stylevwc);
		cellThree.setCellValue("年级");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 7));//合并单元格
		HSSFCell cellFour = rowOne.createCell(3);
		cellFour.setCellStyle(styleCenter);
		cellFour.setCellValue("培训数据");
		HSSFRow rowTwo = sheet.createRow(1);//第二行
		HSSFCell cellFour1 = rowTwo.createCell(3);
		cellFour1.setCellStyle(styleCenter);
		cellFour1.setCellValue("要求数");
		HSSFCell cellFour2 = rowTwo.createCell(4);
		cellFour2.setCellStyle(styleCenter);
		cellFour2.setCellValue("完成数");
		HSSFCell cellFour3 = rowTwo.createCell(5);
		cellFour3.setCellStyle(styleCenter);
		cellFour3.setCellValue("完成比例");
		HSSFCell cellFour4 = rowTwo.createCell(6);
		cellFour4.setCellStyle(styleCenter);
		cellFour4.setCellValue("审核数");
		HSSFCell cellFour5 = rowTwo.createCell(7);
		cellFour5.setCellStyle(styleCenter);
		cellFour5.setCellValue("审核比例");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 8, 10));//合并单元格
		HSSFCell cellFive = rowOne.createCell(8);
		cellFive.setCellStyle(styleCenter);
		cellFive.setCellValue("轮转科室");
		HSSFCell cellFive1 = rowTwo.createCell(8);
		cellFive1.setCellStyle(styleCenter);
		cellFive1.setCellValue("要求数");
		HSSFCell cellFive2 = rowTwo.createCell(9);
		cellFive2.setCellStyle(styleCenter);
		cellFive2.setCellValue("已轮转");
		HSSFCell cellFive3 = rowTwo.createCell(10);
		cellFive3.setCellStyle(styleCenter);
		cellFive3.setCellValue("已出科");
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 11, 11));//合并单元格
		HSSFCell cellSix = rowOne.createCell(11);
		cellSix.setCellStyle(stylevwc);
		cellSix.setCellValue("跟师数据完成比例");
		String[] titles = new String[12];
		int rowNum = 2;
		String[] resultList = null;
		if (rltLst != null && !rltLst.isEmpty()) {
			for (int i = 0; i < rltLst.size(); i++, rowNum++) {
				HSSFRow rowThree = sheet.createRow(rowNum);
				String reqNum = rltLst.get(i).get("reqNum") == null ? "" : rltLst.get(i).get("reqNum").toString();
				String completeNum = rltLst.get(i).get("completeNum") == null ? "" : rltLst.get(i).get("completeNum").toString();
				String checkNum = rltLst.get(i).get("checkNum") == null ? "" : rltLst.get(i).get("checkNum").toString();
				String completePercent = "";
				String checkPercent = "";
				if (StringUtil.isNotBlank(reqNum)) {
					if ("0".equals(reqNum)) {
						completePercent = "-";
					} else {
						Double perCent1 = Double.parseDouble(completeNum) / Double.parseDouble(reqNum) * 100;
						BigDecimal b = new BigDecimal(perCent1);
						//保留2位小数
						completePercent = b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						if("100.00".equals(completePercent)){
							completePercent = "100";
						}
						if("0.00".equals(completePercent)){
							completePercent = "0";
						}
					}
				} else {
					completePercent = "-";
				}

				if (StringUtil.isNotBlank(completeNum)) {
					if ("0".equals(completeNum)) {
						checkPercent = "-";
					} else {
						Double perCent1 = Double.parseDouble(checkNum) / Double.parseDouble(completeNum) * 100;
						BigDecimal b = new BigDecimal(perCent1);
						//保留2位小数
						checkPercent = b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						if("100.00".equals(checkPercent)){
							checkPercent = "100";
						}
						if("0.00".equals(checkPercent)){
							checkPercent = "0";
						}
					}
				} else {
					checkPercent = "-";
				}

				String doctorFlow = rltLst.get(i).get("doctorFlow") == null ? "" : rltLst.get(i).get("doctorFlow").toString();
				String disciplePercent = discipleCountPerCent.get(doctorFlow);

				resultList = new String[]{
						rltLst.get(i).get("doctorName") == null ? "" : rltLst.get(i).get("doctorName").toString(),
						rltLst.get(i).get("doctorCategoryName") == null ? "" : rltLst.get(i).get("doctorCategoryName").toString(),
						rltLst.get(i).get("sessionNumber") == null ? "" : rltLst.get(i).get("sessionNumber").toString(),
						reqNum,
						completeNum,
						completePercent.equals("-") ? "-" : completePercent + "%",
						checkNum,
						checkPercent.equals("-") ? "-" : checkPercent + "%",
						rltLst.get(i).get("rotationNum") == null ? "" : rltLst.get(i).get("rotationNum").toString(),
						rltLst.get(i).get("lunzhuanNum") == null ? "" : rltLst.get(i).get("lunzhuanNum").toString(),
						rltLst.get(i).get("chukeNum") == null ? "" : rltLst.get(i).get("chukeNum").toString(),
						disciplePercent + "%"
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowThree.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(resultList[j]);
				}
			}
		}
		String fileName = "医师工作量导出.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

    @RequestMapping("/showOrgSysRecords")
    public String showOrgSysRecords(Model model,String isShow,HttpServletRequest request,String sessionNumber,String startDate,String endDate,Integer currentPage){
        SysUser currentUser = GlobalContext.getCurrentUser();
        if(StringUtil.isBlank(sessionNumber)&&GlobalConstant.FLAG_Y.equals(isShow)){
            sessionNumber = DateUtil.getYear();
        }
        model.addAttribute("sessionNumber",sessionNumber);
        String wsId = (String)getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
        SysCfg cfg=new SysCfg();
        cfg.setWsId(wsId);
        List<SysCfg> sysCfgList=cfgBiz.search(cfg);
        Map<String, String> sysCfgMap=new HashMap<String, String>();
        for(SysCfg sysCfg:sysCfgList ){
            sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgValue());
            if(StringUtil.isNotBlank(sysCfg.getCfgBigValue())){
                sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgBigValue());
            }
        }
        List<String> recTypeIds = new ArrayList<>();
        for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
            if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))){
                recTypeIds.add(regType.getId());
            }
        }
        recTypeIds.add(ResRecTypeEnum.AfterSummary.getId());
        recTypeIds.add(ResRecTypeEnum.TeacherGrade.getId());
        recTypeIds.add(ResRecTypeEnum.DeptGrade.getId());
        Map<String,Object> beMap= new HashMap<>();
        if(StringUtil.isNotBlank(startDate)){
            startDate = DateUtil.transDateTime(startDate,"yyyy-MM-dd","yyyyMMdd")+"000000";
           // System.err.println(startDate);
        }
        if(StringUtil.isNotBlank(endDate)){
            endDate = DateUtil.transDateTime(endDate,"yyyy-MM-dd","yyyyMMdd")+"235959";
         //   System.err.println(endDate);
        }
        beMap.put("startDate",startDate);
        beMap.put("endDate",endDate);
        beMap.put("sessionNumber",sessionNumber);

        beMap.put("recTypeIds",recTypeIds);
        PageHelper.startPage(currentPage, getPageSize(request));
        if(StringUtil.isNotBlank(currentUser.getOrgFlow())) {
            beMap.put("orgFlow", currentUser.getOrgFlow());
            List<Map<String, Object>> records = doctorBiz.searchDocSysRecords(beMap);
            model.addAttribute("records", records);
        }
        model.addAttribute("sysCfgMap",sysCfgMap);
        return "res/hospital/stuDocSysRecords";
    }


    @RequestMapping(value="/exportSysRecords" )
    @ResponseBody
    public void exportSysRecords(HttpServletResponse response,String sessionNumber,String startDate,String endDate) throws Exception {
        SysUser currentUser = GlobalContext.getCurrentUser();
        List<String> titleList = new ArrayList<>();
        titleList.add("doctorName:姓名");
        titleList.add("AfterSummary:出科小结");
        List<String> recTypeIds = new ArrayList<>();
        for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
            if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))){
                recTypeIds.add(regType.getId());
                titleList.add(regType.getId()+":"+regType.getName());
            }
        }
        titleList.add("TeacherGrade:带教评价");
        titleList.add("DeptGrade:科室评价");
        titleList.add("siginNum:考勤签到数");
        String[] titles = (String[])titleList.toArray(new String[titleList.size()]);
        recTypeIds.add(ResRecTypeEnum.AfterSummary.getId());
        recTypeIds.add(ResRecTypeEnum.TeacherGrade.getId());
        recTypeIds.add(ResRecTypeEnum.DeptGrade.getId());
        Map<String,Object> beMap= new HashMap<>();
        if(StringUtil.isNotBlank(startDate)){
            startDate = DateUtil.transDateTime(startDate,"yyyy-MM-dd","yyyyMMdd")+"000000";
    //       System.err.println(startDate);
        }
        if(StringUtil.isNotBlank(endDate)){
            endDate = DateUtil.transDateTime(endDate,"yyyy-MM-dd","yyyyMMdd")+"235959";
     //       System.err.println(endDate);
        }
        beMap.put("startDate",startDate);
        beMap.put("endDate",endDate);
        beMap.put("sessionNumber",sessionNumber);
        beMap.put("recTypeIds",recTypeIds);
        List<Map<String,Object>> records = new ArrayList<>();
        if(StringUtil.isNotBlank(currentUser.getOrgFlow())) {
            beMap.put("orgFlow", currentUser.getOrgFlow());
            records = doctorBiz.searchDocSysRecords(beMap);

        }
        ExcleUtile.exportSimpleExcleByObjs(titles, records, response.getOutputStream());
        String fileName = "实习生系统数据导出.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

	/**
	 * 查询带教/科主任对学员评分
	 * @param model
	 * @return
     */
	@RequestMapping("/gradeSearch/{roleFlag}")
	public String gradeSearch(@PathVariable String roleFlag,String isShow,String doctorName,String schStartDate,String schEndDate,HttpServletRequest request,Integer currentPage,Model model){
		SysUser user = GlobalContext.getCurrentUser();
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("isShow",isShow);


		PageHelper.startPage(currentPage, getPageSize(request));
		List<SchGradeFrom> results = arrangeBiz.searchGradeByOrgFlow(doctorName,null,null,schStartDate,schEndDate);
		if(results!=null && results.size()>0){
			model.addAttribute("results",results);
			Map<String,Map<String,Object>> teacherGradeMap = new HashMap<>();
			Map<String,Map<String,Object>> headGradeMap = new HashMap<>();

			for(SchGradeFrom gradeFrom:results){
				DeptTeacherGradeInfo teacherGradeInfo = resGradeBiz.readResGrade(gradeFrom.getIsTeacher());
				if(teacherGradeInfo != null){
					model.addAttribute("teacherGradeInfo",teacherGradeInfo);
					Map<String,Object> gradeMap = resRecBiz.parseGradeXml(teacherGradeInfo.getRecContent());
					teacherGradeMap.put(gradeFrom.getIsTeacher(),gradeMap);
					model.addAttribute("teacherGradeMap",teacherGradeMap);
				}
				DeptTeacherGradeInfo headGradeInfo = resGradeBiz.readResGrade(gradeFrom.getIsHead());
				if(headGradeInfo != null){
					model.addAttribute("headGradeInfo",headGradeInfo);
					Map<String,Object> headgradeMap = resRecBiz.parseGradeXml(headGradeInfo.getRecContent());
					headGradeMap.put(gradeFrom.getIsHead(),headgradeMap);
					model.addAttribute("headGradeMap",headGradeMap);
				}
			}
		}
		return "res/manager/gradeSearch";
	}








	private List<ResAssessCfgTitleForm> _getTitleFormList(String cfgCodeId,String orgFlow){
		List<ResAssessCfgTitleForm> titleFormList = null;

		ResAssessCfg search = new ResAssessCfg();
		search.setCfgCodeId(cfgCodeId);
//		search.setOrgFlow(orgFlow);
		List<ResAssessCfg> assessCfgList = assessCfgBiz.searchAssessCfgList(search);
		if(assessCfgList != null && !assessCfgList.isEmpty()){
			ResAssessCfg assessCfg = assessCfgList.get(0);
			try {
				Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(titleElementList != null && !titleElementList.isEmpty()){
					titleFormList = new ArrayList<ResAssessCfgTitleForm>();
					for(Element te :titleElementList){
						ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						List<Element> itemElementList = te.elements("item");
						List<ResAssessCfgItemForm> itemFormList = null;
						if(itemElementList != null && !itemElementList.isEmpty()){
							itemFormList = new ArrayList<ResAssessCfgItemForm>();
							for(Element ie : itemElementList){
								ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
								itemForm.setId(ie.attributeValue("id"));
								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
								itemFormList.add(itemForm);
							}
						}
						titleForm.setItemList(itemFormList);
						titleFormList.add(titleForm);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return titleFormList;
	}


    //基地一键审核培训数据及跟师数据
    @RequestMapping(value="/oneKeyAudit",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String  oneKeyAudit(String auditType){
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow = user.getOrgFlow();
        String userFlow =  user.getUserFlow();
        //培训数据一键审核
        if("train".equals(auditType)){
            resRecBiz.oneKeyAuditByOrg(orgFlow,userFlow);
        }
        // 跟师数据一键审核
        else if("disciple".equals(auditType)){
            iDiscipleBiz.oneKeyAuditByOrg(orgFlow,userFlow);
        }
        return GlobalConstant.OPRE_SUCCESSED_FLAG;

    }


	@RequestMapping("/delLectureFlag")
	@ResponseBody
	public String delLectureFlag(String lectureFlow) {
		// 查询该讲座是否能删除
		boolean flag = resLectureInfoBiz.getDelLectureInfoFlag(lectureFlow);
		// flag:true表示不能删除 返回N 不能删除 Y可以删除
		if (flag) {
			return "N";
		}
		return "Y";
	}


	/**
	 * 导出功能
	 * @param response
	 * @param pageType
	 * @param lectureFlow
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportInfo")
	public void exportInfo(HttpServletResponse response, String pageType,String lectureFlow) throws Exception {

		String[] head = new String[]{};
		String[] titles = null;
		if ("evaList".equals(pageType)) {
			List<String> roles=new ArrayList<>();

			String doctorRoleFlow= InitConfig.getSysCfg("res_doctor_role_flow");
			if (StringUtil.isNotBlank(doctorRoleFlow)) {
				roles.add(doctorRoleFlow);
			}
			List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchIsScan(lectureFlow, roles);
			Map<String,ResLectureEvaDetail> evaDetailMap = new HashMap<>();
			if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
				for(ResLectureScanRegist lectureScanRegist:lectureScanRegists){
					String operUserFlow = lectureScanRegist.getOperUserFlow();
					List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(operUserFlow,lectureFlow);
					if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
						ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
						evaDetailMap.put(operUserFlow,lectureEvaDetail);
					}
				}
			}
			//创建工作簿
			HSSFWorkbook wb = new HSSFWorkbook();
			//定义将用到的样式
			HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
			styleCenter.setAlignment(HorizontalAlignment.CENTER);
			HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
			styleLeft.setAlignment(HorizontalAlignment.LEFT);
			styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
			HSSFCellStyle styleRight = wb.createCellStyle(); //居中
			styleRight.setAlignment(HorizontalAlignment.RIGHT);
			styleRight.setVerticalAlignment(VerticalAlignment.CENTER);
			// 为工作簿添加sheet
			HSSFSheet sheet = wb.createSheet("sheet1");
			HSSFRow rowOne = sheet.createRow(0);//第一行
			titles = new String[]{
					"姓名",
					"签到时间",
					"签退时间",
					"培训类型",
					"培训专业",
					"年级",
					"评价内容",
					"评分内容"
			};
			if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
				titles = new String[]{
						"姓名",
						"签到时间",
						"签退时间",
						"培训专业",
						"对应专业",
						"年级",
						"评价内容",
						"评分内容"
				};
			}
			HSSFCell cellTitle = null;
			for (int i = 0; i < titles.length; i++) {
				cellTitle = rowOne.createCell(i);
				cellTitle.setCellValue(titles[i]);
				cellTitle.setCellStyle(styleCenter);
				sheet.setColumnWidth(i, titles.length * 506);
			}
			int rowNum = 1;
			String[] resultList = null;
			if (lectureScanRegists != null && !lectureScanRegists.isEmpty()) {
				for (int i = 0; i < lectureScanRegists.size(); i++, rowNum++) {
					HSSFRow rowTwo = sheet.createRow(rowNum);
					ResLectureEvaDetail detail=evaDetailMap.get(lectureScanRegists.get(i).getOperUserFlow());
					if(detail==null) detail=new ResLectureEvaDetail();
					resultList = new String[]{
							lectureScanRegists.get(i).getOperUserName(),
							DateUtil.transTime(lectureScanRegists.get(i).getScanTime()),
							DateUtil.transTime(lectureScanRegists.get(i).getScan2Time()),
							lectureScanRegists.get(i).getTrainingTypeName(),
							lectureScanRegists.get(i).getTrainingSpeName(),
							lectureScanRegists.get(i).getSessionNumber(),
							detail.getEvaContent(),
							detail.getEvaScore(),
					};
					for (int j = 0; j < titles.length; j++) {
						HSSFCell cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(resultList[j]);
					}
				}
			}
			String fileName = "已扫码学员信息.xls";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			wb.write(response.getOutputStream());
		} else if("noRegist".equals(pageType)){
			List<String> roles=new ArrayList<>();

			String doctorRoleFlow= InitConfig.getSysCfg("res_doctor_role_flow");
			if (StringUtil.isNotBlank(doctorRoleFlow)) {
				roles.add(doctorRoleFlow);
			}
			List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, roles);
			titles = new String[]{
					"operUserName:姓名",
					"trainingTypeName:培训类型",
					"trainingSpeName:培训专业",
					"sessionNumber:年级"
			};
			if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
				titles = new String[]{
						"operUserName:姓名",
						"trainingTypeName:培训专业",
						"trainingSpeName:对应专业",
						"sessionNumber:年级"
				};
			}
			String fileName = "已报名学员信息.xls";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			ExcleUtile.exportSimpleExcleByObjs(titles, lectureScanRegists, response.getOutputStream());
		} else if("evaList2".equals(pageType)){
			List<String> roles=new ArrayList<>();

			String roleTeacher= InitConfig.getSysCfg("res_teacher_role_flow");
			if (StringUtil.isNotBlank(roleTeacher)) {
				roles.add(roleTeacher);
			}
			String roleHead= InitConfig.getSysCfg("res_head_role_flow");
			if (StringUtil.isNotBlank(roleHead)) {
				roles.add(roleHead);
			}
			String roleSecretary= InitConfig.getSysCfg("res_secretary_role_flow");
			if (StringUtil.isNotBlank(roleSecretary)) {
				roles.add(roleSecretary);
			}
			String roleDisciple= InitConfig.getSysCfg("res_disciple_role_flow");
			if (StringUtil.isNotBlank(roleDisciple)) {
				roles.add(roleDisciple);
			}
			List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchIsScan(lectureFlow, roles);
			Map<String,ResLectureEvaDetail> evaDetailMap = new HashMap<>();
			if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
				for(ResLectureScanRegist lectureScanRegist:lectureScanRegists){
					String operUserFlow = lectureScanRegist.getOperUserFlow();
					List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(operUserFlow,lectureFlow);
					if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
						ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
						evaDetailMap.put(operUserFlow,lectureEvaDetail);
					}
				}
			}
			//创建工作簿
			HSSFWorkbook wb = new HSSFWorkbook();
			//定义将用到的样式
			HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
			styleCenter.setAlignment(HorizontalAlignment.CENTER);
			HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
			styleLeft.setAlignment(HorizontalAlignment.LEFT);
			styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
			HSSFCellStyle styleRight = wb.createCellStyle(); //居中
			styleRight.setAlignment(HorizontalAlignment.RIGHT);
			styleRight.setVerticalAlignment(VerticalAlignment.CENTER);
			// 为工作簿添加sheet
			HSSFSheet sheet = wb.createSheet("sheet1");
			HSSFRow rowOne = sheet.createRow(0);//第一行
			titles = new String[]{
					"姓名",
					"签到时间",
					"签退时间",
					"科室",
					"是否带教",
					"是否教秘",
					"是否科主任",
					"评价内容",
					"评分内容"
			};
			HSSFCell cellTitle = null;
			for (int i = 0; i < titles.length; i++) {
				cellTitle = rowOne.createCell(i);
				cellTitle.setCellValue(titles[i]);
				cellTitle.setCellStyle(styleCenter);
				sheet.setColumnWidth(i, titles.length * 506);
			}
			int rowNum = 1;
			String[] resultList = null;
			if (lectureScanRegists != null && !lectureScanRegists.isEmpty()) {
				for (int i = 0; i < lectureScanRegists.size(); i++, rowNum++) {
					HSSFRow rowTwo = sheet.createRow(rowNum);
					ResLectureEvaDetail detail=evaDetailMap.get(lectureScanRegists.get(i).getOperUserFlow());
					if(detail==null) detail=new ResLectureEvaDetail();

					SysUser user=userBiz.readSysUser(lectureScanRegists.get(i).getOperUserFlow());
					List<SysUserRole> userRoles=userRoleBiz.getByUserFlow(user.getUserFlow());
					String isTea="否"  ;
					String isHead="否";
					String isSecretary="否";
					String isDisciple="否";
					if(userRoles!=null)
					{
						for(SysUserRole userRole:userRoles)
						{
							if (StringUtil.isNotBlank(roleTeacher)&&roleTeacher.equals(userRole.getRoleFlow())) {
								isTea="是";
							}
							if (StringUtil.isNotBlank(roleHead)&&roleHead.equals(userRole.getRoleFlow())) {
								isHead="是";
							}
							if (StringUtil.isNotBlank(roleSecretary) && roleSecretary.equals(userRole.getRoleFlow())) {
								isSecretary = "是";
							}
							if (StringUtil.isNotBlank(roleDisciple) && roleDisciple.equals(userRole.getRoleFlow())) {
								isDisciple = "是";
							}
						}
					}
					resultList = new String[]{
							user.getUserName(),
							DateUtil.transTime(lectureScanRegists.get(i).getScanTime()),
							DateUtil.transTime(lectureScanRegists.get(i).getScan2Time()),
							user.getDeptName(),
							isTea,
							isSecretary,
							isHead,
							detail.getEvaContent(),
							detail.getEvaScore(),
					};
					for (int j = 0; j < titles.length; j++) {
						HSSFCell cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(resultList[j]);
					}
				}
			}
			String fileName = "已扫码师资人员信息.xls";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			wb.write(response.getOutputStream());
		} else if("noRegist2".equals(pageType)){
			List<String> roles=new ArrayList<>();

			String roleTeacher= InitConfig.getSysCfg("res_teacher_role_flow");
			if (StringUtil.isNotBlank(roleTeacher)) {
				roles.add(roleTeacher);
			}
			String roleHead= InitConfig.getSysCfg("res_head_role_flow");
			if (StringUtil.isNotBlank(roleHead)) {
				roles.add(roleHead);
			}
			String roleSecretary= InitConfig.getSysCfg("res_secretary_role_flow");
			if (StringUtil.isNotBlank(roleSecretary)) {
				roles.add(roleSecretary);
			}
			String roleDisciple= InitConfig.getSysCfg("res_disciple_role_flow");
			if (StringUtil.isNotBlank(roleDisciple)) {
				roles.add(roleDisciple);
			}
			List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, roles);
			titles = new String[]{
					"operUserName:姓名",
					"trainingSpeName:科室",
					"isScan:是否带教",
					"isScan2:是否教秘",
					"isRegist:是否科主任"
			};
			if(lectureScanRegists!=null&&lectureScanRegists.size()>0) {
				for (int i = 0; i < lectureScanRegists.size(); i++) {
					SysUser user = userBiz.readSysUser(lectureScanRegists.get(i).getOperUserFlow());
					List<SysUserRole> userRoles = userRoleBiz.getByUserFlow(user.getUserFlow());
					String isTea = "否";
					String isHead = "否";
					String isSecretary = "否";
					String isDisciple = "否";
					if (userRoles != null) {
						for (SysUserRole userRole : userRoles) {
							if (StringUtil.isNotBlank(roleTeacher) && roleTeacher.equals(userRole.getRoleFlow())) {
								isTea = "是";
							}
							if (StringUtil.isNotBlank(roleHead) && roleHead.equals(userRole.getRoleFlow())) {
								isHead = "是";
							}
							if (StringUtil.isNotBlank(roleSecretary) && roleSecretary.equals(userRole.getRoleFlow())) {
								isSecretary = "是";
							}
							if (StringUtil.isNotBlank(roleDisciple) && roleDisciple.equals(userRole.getRoleFlow())) {
								isDisciple = "是";
							}
						}
					}
					lectureScanRegists.get(i).setOperUserName(user.getUserName());
					lectureScanRegists.get(i).setTrainingSpeName(user.getDeptName());
					lectureScanRegists.get(i).setIsScan(isTea);
					lectureScanRegists.get(i).setIsScan2(isSecretary);
					lectureScanRegists.get(i).setIsRegist(isHead);
				}
			}
			String fileName = "已报名师资人员信息.xls";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			ExcleUtile.exportSimpleExcleByObjs(titles, lectureScanRegists, response.getOutputStream());
		}
	}


	@RequestMapping(value = "/exportInfo2")
	public void exportInfo2(HttpServletResponse response,String pageType,String lectureFlow,String flag) throws Exception {
		String[] head = new String[]{};
		String[] titles = null;
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFCellStyle styleRight = wb.createCellStyle(); //居中
		styleRight.setAlignment(HorizontalAlignment.RIGHT);
		styleRight.setVerticalAlignment(VerticalAlignment.CENTER);

		List<String> roles=new ArrayList<>();

		String doctorRoleFlow= InitConfig.getSysCfg("res_doctor_role_flow");
		if (StringUtil.isNotBlank(doctorRoleFlow)) {
			roles.add(doctorRoleFlow);
		}

		List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchIsScan(lectureFlow, roles);
		Map<String, ResLectureEvaDetail> evaDetailMap = new HashMap<>();
		if (lectureScanRegists != null && lectureScanRegists.size() > 0) {
			for (ResLectureScanRegist lectureScanRegist : lectureScanRegists) {
				String operUserFlow = lectureScanRegist.getOperUserFlow();
				List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(operUserFlow, lectureFlow);
				if (lectureEvaDetails != null && lectureEvaDetails.size() > 0) {
					ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
					evaDetailMap.put(operUserFlow, lectureEvaDetail);
				}
			}
		}
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("已扫码学员");
		HSSFRow rowOne = sheet.createRow(0);//第一行

		List<ResLectureRandomSign> randomSigns = randomSignBiz.searchRandomByLectureFlow(lectureFlow);
		List<String> list = new ArrayList<>();
		list.add("姓名");
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))){
			list.add("培训专业");
			list.add("对应专业");
		}else{
			list.add("培训类型");
			list.add("培训专业");
		}


		list.add("届别");
		list.add("签到时间");
		if(randomSigns!=null && randomSigns.size()>0){
			for (int i = 0; i<randomSigns.size();i++) {
				list.add("随机签到");
			}
		}
		list.add("签退时间");
//		list.add("人员类型");
//		list.add("年级");
//		list.add("评价内容");
		list.add("评分");

		titles = list.toArray(new String[list.size()]);

		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowOne.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 506);
		}
		int rowNum = 1;
		String[] resultList = null;

		if (lectureScanRegists != null && !lectureScanRegists.isEmpty()) {
			for (int i = 0; i < lectureScanRegists.size(); i++, rowNum++) {
				List<String> list5 = new ArrayList<>();
				HSSFRow rowTwo = sheet.createRow(rowNum);
				ResLectureEvaDetail detail = evaDetailMap.get(lectureScanRegists.get(i).getOperUserFlow());
				if (detail == null) detail = new ResLectureEvaDetail();

				ResDoctor doctor = doctorBiz.readDoctor(lectureScanRegists.get(i).getOperUserFlow());
				if (doctor != null)
					lectureScanRegists.get(i).setTrainingTypeId(doctor.getDoctorTypeName());

				list5.add(lectureScanRegists.get(i).getOperUserName());
				list5.add(lectureScanRegists.get(i).getTrainingTypeName());
				list5.add(lectureScanRegists.get(i).getTrainingSpeName());
				list5.add(lectureScanRegists.get(i).getSessionNumber());

				list5.add(DateUtil.transTime(lectureScanRegists.get(i).getScanTime()));
				if(randomSigns!=null && randomSigns.size()>0){
					for(int j=0;j<randomSigns.size();j++){
						List<ResLectureRandomScan> scanList = randomSignBiz.searchRandomScan(lectureScanRegists.get(i).getOperUserFlow(),lectureFlow,
								randomSigns.get(j).getRandomFlow());
						if(scanList!=null && scanList.size()>0){
							list5.add(DateUtil.transTime(scanList.get(0).getScanTime()));
						}else{
							list5.add("");
						}
					}
				}
				list5.add(DateUtil.transTime(lectureScanRegists.get(i).getScan2Time()));

//				list5.add(doctor.getDoctorTypeName());
//				list5.add(detail.getEvaContent());
				list5.add(null != lectureScanRegists.get(i).getEvalScore() ? String.valueOf(lectureScanRegists.get(i).getEvalScore()) : "");

				resultList = list5.toArray(new String[list5.size()]);

				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowTwo.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(resultList[j]);
				}
			}
		}


		HSSFSheet sheet2 = wb.createSheet("已报名学员");
		HSSFRow rowTwo = sheet2.createRow(0);//第一行
//		titles = new String[]{
//				"姓名",
//				"培训类型",
//				"培训专业",
//				"届别"
//		};
		List<String> list1 = new ArrayList<>();
		list1.add("姓名");
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))){
			list1.add("培训专业");
			list1.add("对应专业");
		}else{
			list1.add("培训类型");
			list1.add("培训专业");
		}
		list1.add("届别");


		titles = list1.toArray(new String[list1.size()]);

		HSSFCell cellTitle3 = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle3 = rowTwo.createCell(i);
			cellTitle3.setCellValue(titles[i]);
			cellTitle3.setCellStyle(styleCenter);
			sheet2.setColumnWidth(i, titles.length * 506);
		}
		List<String> roles2=new ArrayList<>();

		String doctorRoleFlow2= InitConfig.getSysCfg("res_doctor_role_flow");
		if (StringUtil.isNotBlank(doctorRoleFlow2)) {
			roles2.add(doctorRoleFlow2);
		}
		List<ResLectureScanRegist> lectureScanRegists4 = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, roles2);

		String[] resultList2 = null;
		int rowN = 1;
		if(lectureScanRegists4!=null&&lectureScanRegists4.size()>0){
			for(int i=0;i< lectureScanRegists4.size();i++,rowN++)
			{
				HSSFRow rowSix = sheet2.createRow(rowN);
				ResDoctor doctor=doctorBiz.readDoctor(lectureScanRegists4.get(i).getOperUserFlow());
				if(doctor!=null)
					lectureScanRegists4.get(i).setTrainingTypeId(doctor.getDoctorTypeName());
				resultList2 = new String[]{
						lectureScanRegists4.get(i).getOperUserName(),
						lectureScanRegists4.get(i).getTrainingTypeName(),
						lectureScanRegists4.get(i).getTrainingSpeName(),
//						doctor.getDoctorTypeName(),
						lectureScanRegists4.get(i).getSessionNumber(),
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowSix.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(resultList2[j]);
				}
			}
		}


		List<String> roles3=new ArrayList<>();
		String roleTeacher= InitConfig.getSysCfg("res_teacher_role_flow");
		if (StringUtil.isNotBlank(roleTeacher)) {
			roles3.add(roleTeacher);
		}
		String roleHead= InitConfig.getSysCfg("res_head_role_flow");
		if (StringUtil.isNotBlank(roleHead)) {
			roles3.add(roleHead);
		}
		String roleSecretary= InitConfig.getSysCfg("res_secretary_role_flow");
		if (StringUtil.isNotBlank(roleSecretary)) {
			roles3.add(roleSecretary);
		}

		List<ResLectureScanRegist> lectureScanRegists2 = resLectureScanRegistBiz.searchIsScan(lectureFlow, roles3);
		Map<String,ResLectureEvaDetail> evaDetailMap2 = new HashMap<>();

		if(lectureScanRegists2!=null&&lectureScanRegists2.size()>0){
			for(ResLectureScanRegist lectureScanRegist:lectureScanRegists2){
				String operUserFlow = lectureScanRegist.getOperUserFlow();
				List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(operUserFlow,lectureFlow);
				if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
					ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
					evaDetailMap2.put(operUserFlow,lectureEvaDetail);
				}
			}
		}
		List<LectureInfoTarget> lectureInfoTargets = resLectureEvaDetailBiz.searchLectureInfoTargetList(lectureFlow);
		// 为工作簿添加sheet
		HSSFSheet sheet3 = wb.createSheet("已扫码师资人员");
		HSSFRow rowThree = sheet3.createRow(0);//第一行
		List<String> listTeacher = new ArrayList<>();
		listTeacher.add("姓名");
		listTeacher.add("身份证号");
		listTeacher.add("签到时间");
		if(randomSigns!=null && randomSigns.size()>0){
			for (int i = 0; i<randomSigns.size();i++) {
				listTeacher.add("随机签到");
			}
		}
		listTeacher.add("签退时间");
		listTeacher.add("基地");
		listTeacher.add("科室");
		listTeacher.add("是否带教");
		listTeacher.add("是否科主任");
		listTeacher.add("是否教秘");
		if (lectureInfoTargets != null && lectureInfoTargets.size() > 0) {
			for (int i = 0; i < lectureInfoTargets.size(); i++) {
				listTeacher.add(lectureInfoTargets.get(i).getTargetName());
			}
		}
		titles = listTeacher.toArray(new String[list.size()]);
		HSSFCell cellTitle2 = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle2 = rowThree.createCell(i);
			cellTitle2.setCellValue(titles[i]);
			cellTitle2.setCellStyle(styleCenter);
			sheet3.setColumnWidth(i, titles.length * 506);
		}
		int rowNu = 1;
		String[] result = null;
		if (lectureScanRegists2 != null && !lectureScanRegists2.isEmpty()) {
			for (int i = 0; i < lectureScanRegists2.size(); i++, rowNu++) {
				List<String> list6 = new ArrayList<>();
				HSSFRow rowFive = sheet3.createRow(rowNu);
				ResLectureEvaDetail detail=evaDetailMap2.get(lectureScanRegists2.get(i).getOperUserFlow());
				if(detail==null) detail=new ResLectureEvaDetail();

				SysUser user=userBiz.readSysUser(lectureScanRegists2.get(i).getOperUserFlow());
				List<SysUserRole> userRoles=userRoleBiz.getByUserFlow(user.getUserFlow());
				String isTea="否"  ;
				String isHead="否";
				String isSecretary="否";
				if(userRoles!=null)
				{
					for(SysUserRole userRole:userRoles)
					{
						if (StringUtil.isNotBlank(roleTeacher)&&roleTeacher.equals(userRole.getRoleFlow())) {
							isTea="是";
						}
						if (StringUtil.isNotBlank(roleHead)&&roleHead.equals(userRole.getRoleFlow())) {
							isHead="是";
						}
						if (StringUtil.isNotBlank(roleSecretary) && roleSecretary.equals(userRole.getRoleFlow())) {
							isSecretary = "是";
						}
					}
				}
				list6.add(user.getUserName());
				list6.add(user.getIdNo());
				list6.add(DateUtil.transTime(lectureScanRegists2.get(i).getScanTime()));
				if(randomSigns!=null && randomSigns.size()>0){
					for(int j=0;j<randomSigns.size();j++){
						List<ResLectureRandomScan> scanList = randomSignBiz.searchRandomScan(lectureScanRegists2.get(i).getOperUserFlow(),lectureFlow,
								randomSigns.get(j).getRandomFlow());
						if(scanList!=null && scanList.size()>0){
							list6.add(DateUtil.transTime(scanList.get(0).getScanTime()));
						}else{
							list6.add("");
						}
					}
				}
				list6.add(DateUtil.transTime(lectureScanRegists2.get(i).getScan2Time()));
				list6.add(user.getOrgName());
				list6.add(user.getDeptName());
				list6.add(isTea);
				list6.add(isHead);
				list6.add(isSecretary);
				List<ResLectureEvaDetail> resLectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(user.getUserFlow(), lectureFlow);
				if (resLectureEvaDetails != null && resLectureEvaDetails.size() > 0) {
					for (ResLectureEvaDetail resLectureEvaDetail : resLectureEvaDetails) {
						list6.add(resLectureEvaDetail.getEvaScore());
					}
				}
				if (resLectureEvaDetails == null || resLectureEvaDetails.size() == 0) {
					for (LectureInfoTarget lectureInfoTarget : lectureInfoTargets) {
						list6.add("无评分");
					}
				}
				result = list6.toArray(new String[list6.size()]);
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFive.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(result[j]);
				}
			}
		}


		HSSFSheet sheet4 = wb.createSheet("已报名师资人员");
		HSSFRow rowFour = sheet4.createRow(0);//第一行
		titles = new String[]{
				"姓名",
				"科室",
				"是否带教",
				"是否科主任",
				"是否教秘"
		};
		HSSFCell cellTitle4 = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle4 = rowFour.createCell(i);
			cellTitle4.setCellValue(titles[i]);
			cellTitle4.setCellStyle(styleCenter);
			sheet4.setColumnWidth(i, titles.length * 506);
		}

		List<String> roles4=new ArrayList<>();

		String roleTeacher2= InitConfig.getSysCfg("res_teacher_role_flow");
		if (StringUtil.isNotBlank(roleTeacher2)) {
			roles4.add(roleTeacher2);
		}
		String roleHead2= InitConfig.getSysCfg("res_head_role_flow");
		if (StringUtil.isNotBlank(roleHead2)) {
			roles4.add(roleHead2);
		}
		String roleSecretary2= InitConfig.getSysCfg("res_secretary_role_flow");
		if (StringUtil.isNotBlank(roleSecretary2)) {
			roles4.add(roleSecretary2);
		}
		List<ResLectureScanRegist> lectureScanRegists3 = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, roles4);
		int row = 1;
		String[] resultList3 = null;
		if(lectureScanRegists3!=null&&lectureScanRegists3.size()>0) {
			for (int i = 0; i < lectureScanRegists3.size(); i++,row++) {
				HSSFRow rowEight = sheet4.createRow(row);
				SysUser user = userBiz.readSysUser(lectureScanRegists3.get(i).getOperUserFlow());
				List<SysUserRole> userRoles = userRoleBiz.getByUserFlow(user.getUserFlow());
				String isTea = "否";
				String isHead = "否";
				String isSecretary = "否";
				if (userRoles != null) {
					for (SysUserRole userRole : userRoles) {
						if (StringUtil.isNotBlank(roleTeacher) && roleTeacher.equals(userRole.getRoleFlow())) {
							isTea = "是";
						}
						if (StringUtil.isNotBlank(roleHead) && roleHead.equals(userRole.getRoleFlow())) {
							isHead = "是";
						}
						if (StringUtil.isNotBlank(roleSecretary) && roleSecretary.equals(userRole.getRoleFlow())) {
							isSecretary = "是";
						}
					}
				}
				lectureScanRegists3.get(i).setOperUserName(user.getUserName());
				lectureScanRegists3.get(i).setTrainingSpeName(user.getDeptName());
				lectureScanRegists3.get(i).setIsScan(isTea);
				lectureScanRegists3.get(i).setIsRegist(isHead);
				lectureScanRegists3.get(i).setIsScan2(isSecretary);
				resultList3 = new String[]{
						lectureScanRegists3.get(i).getOperUserName(),
						lectureScanRegists3.get(i).getTrainingSpeName(),
						lectureScanRegists3.get(i).getIsScan(),
						lectureScanRegists3.get(i).getIsRegist(),
						lectureScanRegists3.get(i).getIsScan2(),
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowEight.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(resultList3[j]);
				}
			}
		}

		String fileName = "导出信息.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());

	}
	/**
	 * 取消报名讲座
	 */
	@RequestMapping("/lectureRegistCancel")
	@ResponseBody
	public String lectureRegistCancel(String lectureFlow){
		SysUser currUser = GlobalContext.getCurrentUser();
		String userFlow = currUser.getUserFlow();
		if(StringUtil.isNotBlank(lectureFlow))
		{
			ResLectureScanRegist regist=resLectureScanRegistBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
			if(regist==null)
			{
				return "你未报名，无法取消报名信息！";
			}
			if(StringUtil.isBlank(regist.getIsRegist()))
			{
				return "你未报名，无法取消报名信息！";
			}
			if (!GlobalConstant.FLAG_Y.equals(regist.getIsRegist()))
			{
				return "你已取消报名！";
			}
			if(GlobalConstant.FLAG_Y.equals(regist.getIsScan()))
			{
				return "你已扫码签到，无法取消报名信息！";
			}
			regist.setIsRegist(GlobalConstant.FLAG_N);
			int c=resLectureScanRegistBiz.saveRegist(regist);
			if(c>0){
				return GlobalConstant.OPERATE_SUCCESSED;
			}
			return GlobalConstant.OPERATE_FAIL;
		}else{
			return "请选择需要取消报名的讲座！";
		}
	}
	/**
	 * 报名讲座(新)
	 */
	@RequestMapping("/lectureRegistNew")
	public synchronized String lectureRegistNew(String lectureFlow,String roleId,Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		String userFlow = currUser.getUserFlow();
		ResLectureScanRegist regist=resLectureScanRegistBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
		if(regist!=null&&GlobalConstant.FLAG_Y.equals(regist.getIsRegist()))
		{
			model.addAttribute("isRegiest", "N");
			model.addAttribute("msg", "已经报过名了！！请刷新列表");
			return "res/doctor/lectureRegistNew";
		}
		List<ResLectureScanRegist> resLectureScanRegists=resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, null);
		ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
		if(StringUtil.isBlank(lectureInfo.getLimitNum())||resLectureScanRegists==null||resLectureScanRegists.size()<Integer.valueOf(lectureInfo.getLimitNum())) {
			List<ResLectureInfo> infos=resLectureScanRegistBiz.checkJoinList(lectureFlow,userFlow);
			if(infos!=null&&infos.size()>0)
			{
				ResLectureInfo resLectureInfo=infos.get(0);
				model.addAttribute("isRegiest", "N");
				model.addAttribute("msg", "已报名同一时间【"+resLectureInfo.getLectureContent()+"】，不能报名！");
				return "res/doctor/lectureRegistNew";
			}
			int count= resLectureScanRegistBiz.editLectureScanRegist(lectureFlow);
			if(count<0)
			{
				model.addAttribute("isRegiest", "N");
				model.addAttribute("msg", "该讲座报名人数已满，请刷新列表页面！");
				return "res/doctor/lectureRegistNew";
			}
			if(count==0)
			{
				model.addAttribute("isRegiest", "N");
				model.addAttribute("msg", "报名失败，请刷新列表页面！");
				return "res/doctor/lectureRegistNew";
			}
			String lectureTrainDate = lectureInfo.getLectureTrainDate();
			String lectureStartTime = lectureInfo.getLectureStartTime();
			String year = lectureTrainDate.substring(0, 4);
			model.addAttribute("year", year);
			String month = lectureTrainDate.substring(5, 7);
			model.addAttribute("month", month);
			String day = lectureTrainDate.substring(8, 10);
			model.addAttribute("day", day);
			String hour = lectureStartTime.substring(0, 2);
			model.addAttribute("hour", hour);
			String min = lectureStartTime.substring(3, 5);
			model.addAttribute("min", min);
			model.addAttribute("isRegiest", "Y");
		}else{
			model.addAttribute("isRegiest", "N");
			model.addAttribute("msg", "该讲座报名人数已满，请刷新列表页面！");
		}
		return "res/doctor/lectureRegistNew";
	}

	/**
	 * @Department：研发部
	 * @Description 评价讲座活动
	 * @Author fengxf
	 * @Date 2020/2/13
	 */
	@ResponseBody
	@RequestMapping(value = {"/saveLectureEval"}, method = RequestMethod.POST)
	public String saveLectureEval(String evals, String recordFlow, BigDecimal avgScore) {
		try {
			if(StringUtil.isBlank(recordFlow)) {
				return "请选择需要评价的活动！";
			}
			// 查询当前用户报名签到信息
			ResLectureScanRegist scanRegist = resLectureScanRegistBiz.getLectureScanRegistInfoByFlow(recordFlow);
			if(null == scanRegist) {
				return "您未参加该活动，无法评价";
			}
			if(null != scanRegist.getEvalScore()) {
				return "该活动已评价，请刷新页面！";
			}
			// 讲座活动评分信息
			scanRegist.setEvalScore(avgScore);
			if(StringUtil.isBlank(evals)) {
				return "请选择评分！";
			}
			List<ResLectureEvaDetail> lectureEvaDetailList = JSON.parseArray(evals, ResLectureEvaDetail.class);
			if(null == lectureEvaDetailList || 0 == lectureEvaDetailList.size()) {
				return "请选择评分！";
			}
			// 保存评分用到的参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			SysUser currUser = GlobalContext.getCurrentUser();
			paramMap.put("recordStatus", "Y");
			paramMap.put("operUserFlow", currUser.getUserFlow());
			paramMap.put("operUserName", currUser.getUserName());
			paramMap.put("createTime", DateUtil.getCurrDateTime());
			paramMap.put("lectureEvaDetailList", lectureEvaDetailList);
			return resLectureInfoBiz.saveLectureEval(scanRegist, paramMap);
		}catch (Exception e){
			e.printStackTrace();
			return "系统异常，请稍后重试！";
		}
	}

	@RequestMapping(value="/showLectureEval")
	public String showLectureEval(Model model,String lectureFlow){
		//评价人员
		SysUser currUser = GlobalContext.getCurrentUser();
		// 查询评价人信息
		ResLectureScanRegist scanRegist = resLectureScanRegistBiz.searchByUserFlowAndLectureFlow(currUser.getUserFlow(), lectureFlow);
		model.addAttribute("scanRegist", scanRegist);
		// 查询评价指标信息
		List<LectureInfoTarget> lectureTargetList = resLectureScanRegistBiz.searchLectureInfoTargetList(lectureFlow);
		model.addAttribute("lectureTargetList", lectureTargetList);
		// 查询当前用户评价信息
		Map<String, String> param = new HashMap<String, String>();
		param.put("lectureFlow", lectureFlow);
		param.put("userFlow", currUser.getUserFlow());
		List<ResLectureEvaDetail> lectureEvaDetailList = resLectureEvaDetailBiz.searchUserEvalList(param);
		//评价人员评分详情
		Map<String,Object> evalDetailMap = new HashMap<>();
		if(null != lectureEvaDetailList && 0 < lectureEvaDetailList.size()){
			for(ResLectureEvaDetail evaDetail : lectureEvaDetailList){
				evalDetailMap.put(evaDetail.getScanRegistFlow() + evaDetail.getTargetFlow(), evaDetail.getEvaScore());
			}
		}
		model.addAttribute("evalDetailMap",evalDetailMap);
		return "res/doctor/evalView";
	}

	//临时出科查询 住院医师
	@RequestMapping(value="/temporaryOutSearch")
	public String temporaryOutSearch(Model model,Integer currentPage,HttpServletRequest request,SchArrangeResult arrangeResult, String roleId, String temporaryAuditStatusId, String biaoJi, String datas[]){
		SysUser sysUser=GlobalContext.getCurrentUser();
		Map<String, Object> schArrangeResultMap=new HashMap<String, Object>();
		schArrangeResultMap.put("deptFlow", sysUser.getDeptFlow());
		schArrangeResultMap.put("userFlow", sysUser.getUserFlow());
		schArrangeResultMap.put("doctorName", arrangeResult.getDoctorName());
		schArrangeResultMap.put("schStartDate", arrangeResult.getSchStartDate());
		schArrangeResultMap.put("schEndDate", arrangeResult.getSchEndDate());
		schArrangeResultMap.put("temporaryAuditStatusId", temporaryAuditStatusId);
		schArrangeResultMap.put("roleId", roleId);
		if("org".equals(roleId)){
			schArrangeResultMap.put("orgFlow", sysUser.getOrgFlow());
		}
		List<String>docTypeList=new ArrayList<String>();
		model.addAttribute("datas", datas);
		model.addAttribute("roleId", roleId);
		model.addAttribute("biaoJi", biaoJi);
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}else{
			datas=new String[JsResDocTypeEnum.values().length];
			int i=0;
			for(JsResDocTypeEnum e: JsResDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		schArrangeResultMap.put("docTypeList", docTypeList);
		schArrangeResultMap.put("isAfter", "Y");
		schArrangeResultMap.put("biaoJi", biaoJi);
		schArrangeResultMap.put("trainingTypeId", TrainCategoryEnum.DoctorTrainingSpe.getId());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, String>> schArrangeResult=iResDoctorProcessBiz.temporaryOutSearch(schArrangeResultMap);
		model.addAttribute("schArrangeResult", schArrangeResult);
		return "jsres/kzr/orgTemporaryOutSearch";
	}


	//临时出科查询 助理全科
	@RequestMapping(value="/temporaryOutSearchAcc")
	public String temporaryOutSearchAcc(Model model,Integer currentPage,HttpServletRequest request,SchArrangeResult arrangeResult, String roleId, String temporaryAuditStatusId, String biaoJi, String datas[]){
		SysUser sysUser=GlobalContext.getCurrentUser();
		Map<String, Object> schArrangeResultMap=new HashMap<String, Object>();
		schArrangeResultMap.put("deptFlow", sysUser.getDeptFlow());
		schArrangeResultMap.put("userFlow", sysUser.getUserFlow());
		schArrangeResultMap.put("doctorName", arrangeResult.getDoctorName());
		schArrangeResultMap.put("schStartDate", arrangeResult.getSchStartDate());
		schArrangeResultMap.put("schEndDate", arrangeResult.getSchEndDate());
		schArrangeResultMap.put("temporaryAuditStatusId", temporaryAuditStatusId);
		schArrangeResultMap.put("roleId", roleId);
		if("org".equals(roleId)){
			schArrangeResultMap.put("orgFlow", sysUser.getOrgFlow());
		}
		List<String>docTypeList=new ArrayList<String>();
		model.addAttribute("datas", datas);
		model.addAttribute("roleId", roleId);
		model.addAttribute("biaoJi", biaoJi);
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}else{
			datas=new String[JsResDocTypeEnum.values().length];
			int i=0;
			for(JsResDocTypeEnum e: JsResDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		schArrangeResultMap.put("docTypeList", docTypeList);
		schArrangeResultMap.put("isAfter", "Y");
		schArrangeResultMap.put("biaoJi", biaoJi);
		schArrangeResultMap.put("trainingTypeId",TrainCategoryEnum.AssiGeneral.getId());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, String>> schArrangeResult=iResDoctorProcessBiz.temporaryOutSearch(schArrangeResultMap);
		model.addAttribute("schArrangeResult", schArrangeResult);
		return "jsres/kzr/orgTemporaryOutSearchAcc";
	}



	//临时出科导出 住院医师
	@RequestMapping(value="/exportTemporaryOut")
	public void exportTemporaryOut(Model model,HttpServletResponse response,SchArrangeResult arrangeResult, String roleId,
								   String temporaryAuditStatusId, String biaoJi, String datas[]) throws Exception {
		SysUser sysUser=GlobalContext.getCurrentUser();
		Map<String, Object> schArrangeResultMap=new HashMap<String, Object>();
		schArrangeResultMap.put("deptFlow", sysUser.getDeptFlow());
		schArrangeResultMap.put("userFlow", sysUser.getUserFlow());
		schArrangeResultMap.put("doctorName", arrangeResult.getDoctorName());
		schArrangeResultMap.put("schStartDate", arrangeResult.getSchStartDate());
		schArrangeResultMap.put("schEndDate", arrangeResult.getSchEndDate());
		schArrangeResultMap.put("temporaryAuditStatusId", temporaryAuditStatusId);
		schArrangeResultMap.put("roleId", roleId);
		if("org".equals(roleId)){
			schArrangeResultMap.put("orgFlow", sysUser.getOrgFlow());
		}
		List<String>docTypeList=new ArrayList<String>();
		model.addAttribute("datas", datas);
		model.addAttribute("roleId", roleId);
		model.addAttribute("biaoJi", biaoJi);
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}else{
			datas=new String[JsResDocTypeEnum.values().length];
			int i=0;
			for(JsResDocTypeEnum e: JsResDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		schArrangeResultMap.put("docTypeList", docTypeList);
		schArrangeResultMap.put("isAfter", "Y");
		schArrangeResultMap.put("biaoJi", biaoJi);
		schArrangeResultMap.put("trainingTypeId",TrainCategoryEnum.DoctorTrainingSpe.getId());
		List<Map<String, String>> schArrangeResult=iResDoctorProcessBiz.temporaryOutSearch(schArrangeResultMap);
		String fileName = "临时出科信息.xls";
		String[] titles = new String[]{
				"userName:姓名",
				"doctorTypeName:人员类型",
				"schDeptName:轮转科室",
				"schStartDate:轮转开始时间",
				"schEndDate:轮转结束时间",
				"temporaryAuditStatusName:审核状态"
		};
//		fileName = URLEncoder.encode(fileName, "UTF-8");
		fileName = new String(fileName.getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjsAllString(titles, schArrangeResult, response.getOutputStream());
	}

	//临时出科导出 住院医师
	@RequestMapping(value="/exportTemporaryOutAcc")
	public void exportTemporaryOutAcc(Model model,HttpServletResponse response,SchArrangeResult arrangeResult, String roleId,
									  String temporaryAuditStatusId, String biaoJi, String datas[]) throws Exception {
		SysUser sysUser=GlobalContext.getCurrentUser();
		Map<String, Object> schArrangeResultMap=new HashMap<String, Object>();
		schArrangeResultMap.put("deptFlow", sysUser.getDeptFlow());
		schArrangeResultMap.put("userFlow", sysUser.getUserFlow());
		schArrangeResultMap.put("doctorName", arrangeResult.getDoctorName());
		schArrangeResultMap.put("schStartDate", arrangeResult.getSchStartDate());
		schArrangeResultMap.put("schEndDate", arrangeResult.getSchEndDate());
		schArrangeResultMap.put("temporaryAuditStatusId", temporaryAuditStatusId);
		schArrangeResultMap.put("roleId", roleId);
		if("org".equals(roleId)){
			schArrangeResultMap.put("orgFlow", sysUser.getOrgFlow());
		}
		List<String>docTypeList=new ArrayList<String>();
		model.addAttribute("datas", datas);
		model.addAttribute("roleId", roleId);
		model.addAttribute("biaoJi", biaoJi);
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}else{
			datas=new String[JsResDocTypeEnum.values().length];
			int i=0;
			for(JsResDocTypeEnum e: JsResDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		schArrangeResultMap.put("docTypeList", docTypeList);
		schArrangeResultMap.put("isAfter", "Y");
		schArrangeResultMap.put("biaoJi", biaoJi);
		schArrangeResultMap.put("trainingTypeId",TrainCategoryEnum.AssiGeneral.getId());
		List<Map<String, String>> schArrangeResult=iResDoctorProcessBiz.temporaryOutSearch(schArrangeResultMap);
		String fileName = "临时出科信息.xls";
		String[] titles = new String[]{
				"userName:姓名",
				"doctorTypeName:人员类型",
				"schDeptName:轮转科室",
				"schStartDate:轮转开始时间",
				"schEndDate:轮转结束时间",
				"temporaryAuditStatusName:审核状态"
		};
//		fileName = URLEncoder.encode(fileName, "UTF-8");
		fileName = new String(fileName.getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjsAllString(titles, schArrangeResult, response.getOutputStream());
	}
}


