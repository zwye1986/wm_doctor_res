package com.pinde.sci.ctrl.jsres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResGradeBiz;
import com.pinde.sci.biz.res.IResRecBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.jsres.JsResDocTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.form.jsres.TeacherWorkForm;
import com.pinde.sci.model.mo.ResJointOrg;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysUser;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * @ClassName JsResUniversityManageController
 * @Description 高校管理业务层
 * @Author fengxf
 * @Date 2020/10/12
 */
@Controller
@RequestMapping("/jsres/universityMgr")
public class JsResUniversityManageController extends GeneralController {

	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResGradeBiz resGradeBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;

	/**
	 * @Department：研发部
	 * @Description 高校查询带教工作量
	 * @Author fengxf
	 * @Date 2020/10/12
	 */
	@RequestMapping(value = "/teacherWorkload")
	public String teacherWorkload(Model model, SysUser sysUser,TeacherWorkForm teacherWorkForm, Integer currentPage, HttpServletRequest request) {
		teacherWorkForm.setSysUser(sysUser);
		SysUser currUser = GlobalContext.getCurrentUser();
		List<String> orgFlowList = new ArrayList<>();
		//查询高校下所有医院
		SysOrg currentOrg = orgBiz.readSysOrg(currUser.getOrgFlow());
		teacherWorkForm.setWorkOrgId(currentOrg.getSendSchoolId());
		List<SysOrg> orgList = orgBiz.searchUniversityOrgList(currentOrg.getSendSchoolId());
		model.addAttribute("orgList", orgList);
		if(StringUtil.isBlank(teacherWorkForm.getOrgFlow())){
			if(StringUtil.isNotEmpty(currUser.getSchool())){
				teacherWorkForm.setOrgFlow(currUser.getSchool());
			}else{
			if(orgList != null && orgList.size() > 0){
				for(SysOrg org : orgList){
					orgFlowList.add(org.getOrgFlow());
				}
				teacherWorkForm.setOrgFlowList(orgFlowList);
			}}
		}
		if (StringUtil.isBlank(teacherWorkForm.getOperStartDate()) && StringUtil.isBlank(teacherWorkForm.getOperEndDate())) {
			teacherWorkForm.setOperStartDate(DateUtil.addMonth(DateUtil.getCurrMonth(), -6) + "-01");
			teacherWorkForm.setOperEndDate(DateUtil.getCurrDate());
		}
		model.addAttribute("operStartDate", teacherWorkForm.getOperStartDate());
		model.addAttribute("operEndDate", teacherWorkForm.getOperEndDate());
		if (StringUtil.isNotBlank(teacherWorkForm.getOperStartDate())) {
			teacherWorkForm.setOperStartDate(DateUtil.getDate(teacherWorkForm.getOperStartDate()) + "000000");
		}
		if (StringUtil.isNotBlank(teacherWorkForm.getOperEndDate())) {
			teacherWorkForm.setOperEndDate(DateUtil.getDate(teacherWorkForm.getOperEndDate()) + "235959");
		}
		//复选框勾选标识
		Map<String, String> doctorTypeSelectMap = new HashMap<>();
		List<String> typeList = null;
		if (teacherWorkForm.getDoctorTypeIdList() != null && teacherWorkForm.getDoctorTypeIdList().length > 0) {
			typeList = Arrays.asList(teacherWorkForm.getDoctorTypeIdList());
			for (JsResDocTypeEnum dict : JsResDocTypeEnum.values()) {
				if (typeList.contains(dict.getId())) {
					doctorTypeSelectMap.put(dict.getId(), "checked");
				}
			}
		}else{
			typeList = new ArrayList<>();
			for (JsResDocTypeEnum dict : JsResDocTypeEnum.values()) {
				typeList.add(dict.getId());
				doctorTypeSelectMap.put(dict.getId(), "checked");
			}
		}
		teacherWorkForm.setTypeList(typeList);
//		if(StringUtil.isNotBlank(teacherWorkForm.getOrgFlow())){
//			List<SysDept> deptList = deptBiz.searchDeptByOrg(teacherWorkForm.getOrgFlow());
//			model.addAttribute("deptList", deptList);
//		}
		String cfgTeacher = InitConfig.getSysCfg("res_teacher_role_flow");
		teacherWorkForm.setRoleFlow(cfgTeacher);
		if (StringUtil.isBlank(teacherWorkForm.getOrderItem())) {
			teacherWorkForm.setOrderItem("currStudentHe");
		}else{
			if ("0".equals(teacherWorkForm.getOrderItem())) {
				teacherWorkForm.setOrderItem("currStudentHe");
			} else if ("1".equals(teacherWorkForm.getOrderItem())) {
				teacherWorkForm.setOrderItem("notAudited");
			} else if ("2".equals(teacherWorkForm.getOrderItem())) {
				teacherWorkForm.setOrderItem("isNotAudited");
			} else {
				teacherWorkForm.setOrderItem("");
			}
		}
		if (StringUtil.isBlank(teacherWorkForm.getSortName())) {
			teacherWorkForm.setSortName("DESC");
		}
		if(currentPage == null){
			currentPage = 1;
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, String>> dataList = processBiz.searchUniversityTeacherWorkInfoList(teacherWorkForm);
		model.addAttribute("dataList", dataList);
		if (dataList != null && dataList.size() > 0) {
			List<String> teacherFlows = new ArrayList<>();
			for (Map<String, String> tmap : dataList) {
				teacherFlows.add(tmap.get("USER_FLOW"));
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sysUser", sysUser);
			paramMap.put("teacherUserFlows", teacherFlows);
			paramMap.put("startDate", teacherWorkForm.getOperStartDate());
			paramMap.put("endDate", teacherWorkForm.getOperEndDate());
			paramMap.put("typeList", typeList);
			paramMap.put("workOrgId", currentOrg.getSendSchoolId());
			paramMap.put("roleFlow", cfgTeacher);
			paramMap.put("orgFlow", teacherWorkForm.getOrgFlow());
			paramMap.put("orgFlowList", teacherWorkForm.getOrgFlowList());
//			List<Map<String, String>> notAuditedMaps = resRecBiz.notAuditedMaps(paramMap);
//			List<Map<String, String>> isNotAuditedMaps = resRecBiz.isNotAuditedMaps(paramMap);
			List<Map<String, String>> auditedMapList = resRecBiz.searchAuditedDataList(paramMap);
			List<Map<String, String>> gradeAvgMaps = resGradeBiz.searchGradeAvgMaps(paramMap);
			for (Map<String, String> tmap : dataList) {
				String teacherFlow = tmap.get("USER_FLOW");
				if (auditedMapList != null && auditedMapList.size() > 0) {
					for (Map<String, String> tempMap : auditedMapList) {
						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
							tmap.put("notAudited", tempMap.get("notAudited"));
							tmap.put("isNotAudited", tempMap.get("isNotAudited"));
							continue;
						}
					}
				}
//				if (notAuditedMaps != null && notAuditedMaps.size() > 0) {
//					for (Map<String, String> tempMap : notAuditedMaps) {
//						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
//							tmap.put("notAudited", tempMap.get("counts") + "");
//							continue;
//						}
//					}
//				}
//				if (isNotAuditedMaps != null && isNotAuditedMaps.size() > 0) {
//					for (Map<String, String> tempMap : isNotAuditedMaps) {
//						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
//							tmap.put("isNotAudited", tempMap.get("counts") + "");
//							continue;
//						}
//					}
//				}
				if (gradeAvgMaps != null && gradeAvgMaps.size() > 0) {
					for (Map<String, String> tempMap : gradeAvgMaps) {
						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
							tmap.put("AVERAGE", tempMap.get("counts") + "");
							continue;
						}
					}
				}
			}
		}
		model.addAttribute("doctorTypeSelectMap", doctorTypeSelectMap);
		return "jsres/university/teacherWorkload";
	}


	@RequestMapping(value = "/main")
	public String main(Model model, String userListScope) {
		return "jsres/university/appUseInfo/main";
	}

	@RequestMapping("/appUserTable")
	public String appUserMain(Model model, String userListScope,HttpServletRequest request) {
		if (GlobalConstant.USER_LIST_UNIVERSITY.equals(userListScope)) {//基地
			List<SysOrg> orgs = new ArrayList<>();
			SysUser user = GlobalContext.getCurrentUser();
			SysOrg org = orgBiz.readSysOrg(user.getOrgFlow());
			orgs.add(org);
			if (OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
				List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
				if (orgList != null && orgList.size() > 0) {
					orgs.addAll(orgList);
				}
			}
			model.addAttribute("orgs", orgs);
		}
		return "jsres/university/appUseInfo/appUserMain";
	}

	@RequestMapping("/universityAppNotUseList")
	public String appNotUseList(Integer currentPage, HttpServletRequest request, Model model, String userListScope,
								String orgLevel, String orgFlow, String[] datas, String trainingTypeId, String trainingSpeId,
								String sessionNumber, String type,String startTime,String endTime) {
		Map<String, Object> param = new HashMap<>();
		List<ResJointOrg> resJointOrgs = null;
		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		}
		String startDate = DateUtil.getCurrMonth();
		String endDate = DateUtil.getCurrMonth();
		if ("twoMonth".equals(type)) {
			startDate = DateUtil.addMonth(startDate, -1);
			endDate = DateUtil.addMonth(endDate, -1);
		}
		if ("threeMonth".equals(type)) {
			startDate = DateUtil.addMonth(startDate, -3);
			endDate = DateUtil.addMonth(endDate, -1);
		}
		if("other".equals(type)){
			startDate = "";
			endDate = "";
			param.put("startTime",startTime);
			param.put("endTime",endTime);
		}
		param.put("orgLevel", orgLevel);
		param.put("orgFlow", orgFlow);
		param.put("docTypeList", docTypeList);
		param.put("trainingTypeId", trainingTypeId);
		param.put("trainingSpeId", trainingSpeId);
		param.put("sessionNumber", sessionNumber);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("userListScope", userListScope);
		List<String> orgFlows = new ArrayList<>();
		param.put("orgFlows", orgFlows);
		SysUser user = GlobalContext.getCurrentUser();
		param.put("workOrgName", user.getOrgName());
		if(StringUtil.isNotEmpty(user.getSchool())){
			param.put("school", user.getSchool());
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, String>> list = jsResDoctorRecruitBiz.searchOrgNotUseAppDoc(param);
		model.addAttribute("list", list);
		return "jsres/university/appUseInfo/appNotUseList";
	}

	@RequestMapping("/universityExportExcel")
	public void exportExcel(String userListScope,String orgLevel, String orgFlow, String[] datas,
							String trainingTypeId, String trainingSpeId, String sessionNumber,
							String type, HttpServletResponse response,String startTime,String endTime) throws Exception {
		Map<String, Object> param = new HashMap<>();
		List<ResJointOrg> resJointOrgs = null;
		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		}
		String startDate = DateUtil.getCurrMonth();
		String endDate = DateUtil.getCurrMonth();
		if ("twoMonth".equals(type)) {
			startDate = DateUtil.addMonth(startDate, -1);
			endDate = DateUtil.addMonth(endDate, -1);
		}
		if ("threeMonth".equals(type)) {
			startDate = DateUtil.addMonth(startDate, -3);
			endDate = DateUtil.addMonth(endDate, -1);
		}
		if("other".equals(type)){
			startDate = "";
			endDate = "";
			param.put("startTime",startTime);
			param.put("endTime",endTime);
		}
		param.put("orgLevel", orgLevel);
		param.put("orgFlow", orgFlow);
		param.put("docTypeList", docTypeList);
		param.put("trainingTypeId", trainingTypeId);
		param.put("trainingSpeId", trainingSpeId);
		param.put("sessionNumber", sessionNumber);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("userListScope", userListScope);
		SysUser user = GlobalContext.getCurrentUser();
		param.put("workOrgName", user.getOrgName());
		List<Map<String, String>> list = jsResDoctorRecruitBiz.searchOrgNotUseAppDoc(param);
		String fileName = "未使用APP学员信息.xls";
		String []titles = new String[]{
				"userName:学员姓名",
				"orgName:培训基地",
				"sessionNumber:年级",
				"speName:培训专业",
				"userPhone:手机号码",
				"idNo:证件号码"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream());
	}

	/**
	 * @Department：研发部
	 * @Description 带教学员列表
	 * @Author fengxf
	 * @Date 2020/10/13
	 */
	@RequestMapping(value = "/doctorDetailList")
	public String doctorDetailList(Model model, String teacherUserFlow, String[] doctorTypeIdList, String startDate, String endDate) {
		SysUser currUser = GlobalContext.getCurrentUser();
		SysOrg currentOrg = orgBiz.readSysOrg(currUser.getOrgFlow());
		List<String> typeList = null;
		if (doctorTypeIdList != null && doctorTypeIdList.length > 0) {
			typeList = Arrays.asList(doctorTypeIdList);
		}
		List<Map<String, Object>> doctorListMap = resDoctorBiz.schDoctorQuery(teacherUserFlow, startDate, endDate, typeList, currentOrg.getSendSchoolId());
		model.addAttribute("doctorListMap", doctorListMap);
		return "jsres/hospital/studentDetails";
	}

	/**
	 * @Department：研发部
	 * @Description 导出
	 * @Author fengxf
	 * @Date 2020/10/14
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletResponse response, SysUser sysUser, TeacherWorkForm teacherWorkForm) {
		teacherWorkForm.setSysUser(sysUser);
		SysUser currUser = GlobalContext.getCurrentUser();
		List<String> orgFlowList = new ArrayList<>();
		//查询高校下所有医院
		SysOrg currentOrg = orgBiz.readSysOrg(currUser.getOrgFlow());
		teacherWorkForm.setWorkOrgId(currentOrg.getSendSchoolId());
		List<SysOrg> orgList = orgBiz.searchUniversityOrgList(currentOrg.getSendSchoolId());
		if(StringUtil.isBlank(teacherWorkForm.getOrgFlow())){
			if(StringUtil.isNotEmpty(currUser.getSchool())){
				teacherWorkForm.setOrgFlow(currUser.getSchool());
			}else{
			if(orgList != null && orgList.size() > 0){
				for(SysOrg org : orgList){
					orgFlowList.add(org.getOrgFlow());
				}
				teacherWorkForm.setOrgFlowList(orgFlowList);
			}}
		}
		if (StringUtil.isBlank(teacherWorkForm.getOperStartDate()) && StringUtil.isBlank(teacherWorkForm.getOperEndDate())) {
			teacherWorkForm.setOperStartDate(DateUtil.addMonth(DateUtil.getCurrMonth(), -6) + "-01");
			teacherWorkForm.setOperEndDate(DateUtil.getCurrDate());
		}
		String startDate = teacherWorkForm.getOperStartDate();
		String endDate = teacherWorkForm.getOperEndDate();
		if (StringUtil.isNotBlank(teacherWorkForm.getOperStartDate())) {
			teacherWorkForm.setOperStartDate(DateUtil.getDate(teacherWorkForm.getOperStartDate()) + "000000");
		}
		if (StringUtil.isNotBlank(teacherWorkForm.getOperEndDate())) {
			teacherWorkForm.setOperEndDate(DateUtil.getDate(teacherWorkForm.getOperEndDate()) + "235959");
		}
		//复选框勾选标识
		Map<String, String> doctorTypeSelectMap = new HashMap<>();
		List<String> typeList = null;
		if (teacherWorkForm.getDoctorTypeIdList() != null && teacherWorkForm.getDoctorTypeIdList().length > 0) {
			typeList = Arrays.asList(teacherWorkForm.getDoctorTypeIdList());
			for (JsResDocTypeEnum dict : JsResDocTypeEnum.values()) {
				if (typeList.contains(dict.getId())) {
					doctorTypeSelectMap.put(dict.getId(), "checked");
				}
			}
		}else{
			typeList = new ArrayList<>();
			for (JsResDocTypeEnum dict : JsResDocTypeEnum.values()) {
				typeList.add(dict.getId());
				doctorTypeSelectMap.put(dict.getId(), "checked");
			}
		}
		teacherWorkForm.setTypeList(typeList);
		String cfgTeacher = InitConfig.getSysCfg("res_teacher_role_flow");
		teacherWorkForm.setRoleFlow(cfgTeacher);
		if (StringUtil.isBlank(teacherWorkForm.getOrderItem())) {
			teacherWorkForm.setOrderItem("currStudentHe");
		}else{
			if ("0".equals(teacherWorkForm.getOrderItem())) {
				teacherWorkForm.setOrderItem("currStudentHe");
			} else if ("1".equals(teacherWorkForm.getOrderItem())) {
				teacherWorkForm.setOrderItem("notAudited");
			} else if ("2".equals(teacherWorkForm.getOrderItem())) {
				teacherWorkForm.setOrderItem("isNotAudited");
			} else {
				teacherWorkForm.setOrderItem("");
			}
		}
		if (StringUtil.isBlank(teacherWorkForm.getSortName())) {
			teacherWorkForm.setSortName("DESC");
		}
		List<Map<String, String>> dataList = processBiz.searchUniversityTeacherWorkInfoList(teacherWorkForm);
		if (dataList != null && dataList.size() > 0) {
			List<String> teacherFlows = new ArrayList<>();
			for (Map<String, String> tmap : dataList) {
				teacherFlows.add(tmap.get("USER_FLOW"));
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sysUser", sysUser);
			paramMap.put("teacherUserFlows", teacherFlows);
			paramMap.put("startDate", teacherWorkForm.getOperStartDate());
			paramMap.put("endDate", teacherWorkForm.getOperEndDate());
			paramMap.put("typeList", typeList);
			paramMap.put("workOrgId", currentOrg.getSendSchoolId());
			paramMap.put("roleFlow", cfgTeacher);
			paramMap.put("orgFlow", teacherWorkForm.getOrgFlow());
			paramMap.put("orgFlowList", teacherWorkForm.getOrgFlowList());
			paramMap.put("schStartDate", startDate);
			paramMap.put("schEndDate", endDate);
//			List<Map<String, String>> notAuditedMaps = resRecBiz.notAuditedMaps(paramMap);
//			List<Map<String, String>> isNotAuditedMaps = resRecBiz.isNotAuditedMaps(paramMap);
			List<Map<String, String>> auditedMapList = resRecBiz.searchAuditedDataList(paramMap);
			List<Map<String, String>> gradeAvgMaps = resGradeBiz.searchGradeAvgMaps(paramMap);
			List<Map<String, String>> doctorListMap = resDoctorBiz.searchSchDoctorList(paramMap);
			for (Map<String, String> tmap : dataList) {
				String teacherFlow = tmap.get("USER_FLOW");
				if (auditedMapList != null && auditedMapList.size() > 0) {
					for (Map<String, String> tempMap : auditedMapList) {
						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
							tmap.put("NOTAUDITED", tempMap.get("notAudited"));
							tmap.put("ISNOTAUDITED", tempMap.get("isNotAudited"));
							continue;
						}
					}
				}
//				if (notAuditedMaps != null && notAuditedMaps.size() > 0) {
//					for (Map<String, String> tempMap : notAuditedMaps) {
//						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
//							tmap.put("notAudited", tempMap.get("counts") + "");
//							continue;
//						}
//					}
//				}
				if(StringUtil.isBlank(tmap.get("notAudited"))){
					tmap.put("notAudited","0");
				}
//				if (isNotAuditedMaps != null && isNotAuditedMaps.size() > 0) {
//					for (Map<String, String> tempMap : isNotAuditedMaps) {
//						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
//							tmap.put("isNotAudited", tempMap.get("counts") + "");
//							continue;
//						}
//					}
//				}
				if(StringUtil.isBlank(tmap.get("isNotAudited"))){
					tmap.put("isNotAudited","0");
				}
				if (gradeAvgMaps != null && gradeAvgMaps.size() > 0) {
					for (Map<String, String> tempMap : gradeAvgMaps) {
						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
							tmap.put("AVERAGE", tempMap.get("counts") + "");
							continue;
						}
					}
				}
//				//江苏西医平台优化11.17需求 一
//				List<Map<String, Object>> doctorListMap = resDoctorBiz.schDoctorQuery(tmap.get("USER_FLOW"), startDate, endDate, typeList, currentOrg.getSendSchoolId());
//				StringBuilder sb = new StringBuilder();
//				if (doctorListMap != null && doctorListMap.size() > 0) {
//					sb.append("[");
//					Map<String, Object> tInfoMap = new HashMap<>();
//					List<String> tempList = new ArrayList<>();
//					int offset = doctorListMap.size() - 1;
//					for (int k = 0; k < offset; k++) {
//						tInfoMap = doctorListMap.get(k);
//						if (!tempList.contains((String) tInfoMap.get("doctorName"))) {
//							sb.append(tInfoMap.get("doctorName")).append(",");
//							tempList.add((String) tInfoMap.get("doctorName"));
//						}
//					}
//					if (!tempList.contains(doctorListMap.get(offset).get("doctorName"))) {
//						sb.append(doctorListMap.get(offset).get("doctorName")).append("]");
//					} else {
//						sb.replace(sb.length() - 1, sb.length(), "]");
//					}
//				}
//				tmap.put("doctorList", sb + "");
				if (doctorListMap != null && doctorListMap.size() > 0) {
					for (Map<String, String> tempMap : doctorListMap) {
						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
							tmap.put("doctorList", tempMap.get("doctorNameStr"));
							continue;
						}
					}
				}
			}
		}
		String[] titles = new String[]{
				"ORG_NAME:培训基地",
				"USER_NAME:姓名",
				"USER_CODE:用户名",
				"DEPT_NAME:科室",
				"AVERAGE:老师均分",
				"ISNOTAUDITED:已审核数",
				"NOTAUDITED:未审核数",
				"ACTIVITYNUM:教学活动数",
				"CURRSTUDENTHE:带教名单",
				"CURRSTUDENTCOUNT:带教人次",
				"doctorList:学员姓名"
		};
		String fileName = "带教老师工作量统计.xls";
		try {
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
			style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
			List<String> paramIds = new ArrayList<String>();
			Map<Integer, Integer> columnWidth = new HashMap<>();
			HSSFCell cell = null;
			for (int i = 0; i < titles.length; i++) {
				String[] title = titles[i].split(":");
				cell = row.createCell(i);
				cell.setCellValue(title[1]);
				cell.setCellStyle(style);
				paramIds.add(title[0]);
				int length = title[1].length();
				//宽度自适应
				setColumnWidth(title[1].toString().getBytes().length, i, columnWidth);
			}
			if (dataList != null) {
				for (int i = 0; i < dataList.size(); i++) {
					Map item = dataList.get(i);
					row = sheet.createRow(i + 1);
					Object result = null;
					for (int j = 0; j < paramIds.size(); j++) {
						String paramId = paramIds.get(j);
						result = item.get(paramId);
						if (result == null)
							result = "";
						row.createCell(j).setCellValue(result.toString());
						//宽度自适应
						setColumnWidth(result.toString().getBytes().length, j, columnWidth);
					}
				}
			}
			wb.write(os);
		} catch(Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * @Department：研发部
	 * @Description 设置行宽
	 * @Author fengxf
	 * @Date 2020/10/14
	 */
	private void setColumnWidth(int length, int key, Map<Integer, Integer> columnWidth) {
		if (columnWidth.containsKey(key)) {
			Integer ol = columnWidth.get(key);
			if (ol < length)
				columnWidth.put(key, length);
		} else {
			columnWidth.put(key, length);
		}
	}

}
