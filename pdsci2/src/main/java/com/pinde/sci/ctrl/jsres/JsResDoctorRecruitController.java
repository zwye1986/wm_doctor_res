package com.pinde.sci.ctrl.jsres;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.*;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sch.impl.PaiBanImportService;
import com.pinde.sci.biz.sch.impl.SchRotationGroupBizImpl;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.*;
import com.pinde.sci.dao.base.ResScoreMapper;
import com.pinde.sci.dao.base.SchRotationDeptMapper;
import com.pinde.sci.enums.jsres.*;
import com.pinde.sci.enums.res.*;
import com.pinde.sci.enums.sys.*;
import com.pinde.sci.excelListens.model.SchedulingDataModel;
import com.pinde.sci.form.res.ResAssessCfgItemForm;
import com.pinde.sci.form.res.ResAssessCfgTitleForm;
import com.pinde.sci.model.jsres.ArrangTdVo;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.*;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jsres/doctorRecruit")
public class JsResDoctorRecruitController extends GeneralController {
	final static String Jxcf = "1";
	final static String Ynbltl = "2"; final static String Wzbltl = "3";final static String Swbltl = "5";final static String Jxbltl = "11";
	final static String Xsjz = "4";  final static String Rkjy = "6";
	final static String Ckks = "7"; final static String Jnpx = "8"; final static String Yph = "9";
	final static String Jxhz = "10";
	final static String Lcczjnzd = "12"; final static String Lcblsxzd = "13";
	final static String Ssczzd = "14"; final static String Yxzdbgsxzd = "15"; final static String Lcwxyd = "16";
	final static String Ryjy = "17"; final static String Rzyjdjy = "18"; final static String Cjbg = "19";
	final static String Bgdfx = "20";final static String Jxsj = "21";final static String Sjys = "22";
	@Autowired
	private IJsResDoctorBiz jsResDoctorBiz;
	@Autowired
	private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;

	@Autowired
	private SchRotationDeptMapper rotationDeptMapper;
	@Autowired
	private SchRotationGroupBizImpl rotationGroupBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private OrgBizImpl orgBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private IResDoctorOrgHistoryBiz resDoctorOrgHistoryBiz;
	@Autowired
	private IResAssessCfgBiz assessCfgBiz;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private ISchDoctorDeptBiz doctorDeptBiz;
	@Autowired
	private ISchRotationGroupBiz groupBiz;
	@Autowired
	private IResScoreBiz resScoreBiz;
	@Autowired
	private IResDoctorArchiveBiz archiveBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private IResScoreBiz scoreBiz;
	@Autowired
	private IExamResultsBiz examResultsBiz;
	@Autowired
	private IResResponsibleTeacherDoctorBiz resResponsibleTeacherDoctorBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private ISchDeptExternalRelBiz deptExternalRelBiz;
	@Autowired
	private PaiBanImportService paiBanImportService;

	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;

	@Autowired
	private ResScoreMapper scoreMapper;

	private static Logger logger = LoggerFactory.getLogger(JsResDoctorRecruitController.class);

	/**
	 * 跳到父页面，用子页面处理
	 */
	@RequestMapping(value="/doctorRecruitList")
	public String doctorRecruitList(){
		return  "jsres/hospital/doctor/doctorTrendMain";
	}

	@RequestMapping(value="/provinceDoctorList")
	public String provinceDoctorList(Model model,String roleFlag,String isArchive,String []datas,String userName,
									 String isBack,String orgCityId,String yearStr,String baseFlag) throws UnsupportedEncodingException {
		// 登录用户信息
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org",org);
		SysOrg sysorg =new  SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
			sysorg.setOrgCityId(org.getOrgCityId());
		}
		sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(orgCityId)){
			sysorg.setOrgCityId(orgCityId);
		}
		List<SysOrg> orgs = null;
		// 市局角色查询地市不是当前市局地市的协同基地
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
			orgs = orgBiz.searchOrgAndJointOrgList(sysorg);
		}else{
			orgs = orgBiz.searchOrg(sysorg);
		}
		sysorg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
		List<SysOrg> countryList = orgBiz.searchOrg(sysorg);
		sysorg.setOrgLevelId(OrgLevelEnum.ProvinceOrg.getId());
		List<SysOrg> provinceList = orgBiz.searchOrg(sysorg);

		List<ResJointOrg> jointOrgs = jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList = new ArrayList<String>();
		if(jointOrgs != null && !jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList", orgFlowList);
		model.addAttribute("countryList", countryList);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("baseFlag",baseFlag);
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
			{
				orgs.clear();
				List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
				orgs.addAll(joinOrgs);
				orgs.add(org);
			}
		}
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(sysuser.getOrgFlow());
		//是协同基地
		if(!joinOrgs.isEmpty()&&joinOrgs.size()>0){
			model.addAttribute("isJointOrg","1");
		}
		model.addAttribute("datas",datas);
		if(GlobalConstant.FLAG_Y.equals(isArchive)){
			List<ResArchiveSequence> archiveSequenceList = archiveBiz.allResArchiveSequence();
			model.addAttribute("archiveSequenceList",archiveSequenceList);
			return  "jsres/archiveDoctorList";
		}
		if(StringUtil.isNotBlank(userName) && GlobalConstant.FLAG_Y.equals(isBack)){
			userName = java.net.URLDecoder.decode(userName,"UTF-8");
			model.addAttribute("isBack",isBack);
		}
		model.addAttribute("userName",userName);
		model.addAttribute("yearDatas",StringUtil.isNotBlank(yearStr)?yearStr.split(","):null);

		return  "jsres/doctorList";
	}

	@RequestMapping(value="/provinceDoctorListAcc")
	public String provinceDoctorListAcc(Model model,String roleFlag,String isArchive,String []datas,String userName,
										String isBack,String orgCityId,String yearStr,String baseFlag) throws UnsupportedEncodingException {
		// 登录用户信息
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org",org);
		SysOrg sysorg =new  SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
			sysorg.setOrgCityId(org.getOrgCityId());
		}
		sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(orgCityId)){
			sysorg.setOrgCityId(orgCityId);
		}
		List<SysOrg> orgs = null;
		// 市局角色查询地市不是当前市局地市的协同基地
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
			orgs = orgBiz.searchOrgAndJointOrgList(sysorg);
		}else{
			orgs = orgBiz.searchOrg(sysorg);
		}
		sysorg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
		List<SysOrg> countryList = orgBiz.searchOrg(sysorg);
		sysorg.setOrgLevelId(OrgLevelEnum.ProvinceOrg.getId());
		List<SysOrg> provinceList = orgBiz.searchOrg(sysorg);

		List<ResJointOrg> jointOrgs = jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList = new ArrayList<String>();
		if(jointOrgs != null && !jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList", orgFlowList);
		model.addAttribute("countryList", countryList);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("baseFlag",baseFlag);
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
			{
				orgs.clear();
				List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
				orgs.addAll(joinOrgs);
				orgs.add(org);
			}
		}
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(sysuser.getOrgFlow());
		//是协同基地
		if(!joinOrgs.isEmpty()&&joinOrgs.size()>0){
			model.addAttribute("isJointOrg","1");
		}
		model.addAttribute("datas",datas);
		if(GlobalConstant.FLAG_Y.equals(isArchive)){
			List<ResArchiveSequence> archiveSequenceList = archiveBiz.allResArchiveSequence();
			model.addAttribute("archiveSequenceList",archiveSequenceList);
			return  "jsres/archiveDoctorList";
		}
		if(StringUtil.isNotBlank(userName) && GlobalConstant.FLAG_Y.equals(isBack)){
			userName = java.net.URLDecoder.decode(userName,"UTF-8");
			model.addAttribute("isBack",isBack);
		}
		model.addAttribute("userName",userName);
		model.addAttribute("yearDatas",StringUtil.isNotBlank(yearStr)?yearStr.split(","):null);

		return  "jsres/doctorListAcc";
	}

	@RequestMapping(value = "/provinceDoctorListNew")
	public String provinceDoctorListNew(Model model, String roleFlag, String[] datas, String userName,
										String isBack, String orgCityId, String yearStr, String baseFlag,HttpServletRequest request) throws UnsupportedEncodingException {
		// 登录用户信息
		SysUser sysuser = GlobalContext.getCurrentUser();
		model.addAttribute("user", sysuser);
		SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org", org);
		SysOrg sysorg = new SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
		sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
//		sysorg.setOrgLevelId("CountryOrg");
		if (StringUtil.isNotBlank(orgCityId)) {
			sysorg.setOrgCityId(orgCityId);
		}
		List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
		sysorg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
		List<SysOrg> countryList = orgBiz.searchOrg(sysorg);
		sysorg.setOrgLevelId(OrgLevelEnum.ProvinceOrg.getId());
		List<SysOrg> provinceList = orgBiz.searchOrg(sysorg);

		List<ResJointOrg> jointOrgs = jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList = new ArrayList<String>();
		if (jointOrgs != null && !jointOrgs.isEmpty()) {
			for (ResJointOrg jointOrg : jointOrgs) {
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList", orgFlowList);
		model.addAttribute("countryList", countryList);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("baseFlag", baseFlag);
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(sysuser.getOrgFlow());
		//是协同基地
		if (!joinOrgs.isEmpty() && joinOrgs.size() > 0) {
			model.addAttribute("isJointOrg", "1");
		}
		model.addAttribute("datas", datas);
		if (StringUtil.isNotBlank(userName) && GlobalConstant.FLAG_Y.equals(isBack)) {
			userName = java.net.URLDecoder.decode(userName, "UTF-8");
			model.addAttribute("isBack", isBack);
		}
		model.addAttribute("userName", userName);
		model.addAttribute("yearDatas", StringUtil.isNotBlank(yearStr) ? yearStr.split(",") : null);
		List<String> currRoleList = (List<String>) request.getSession().getAttribute(GlobalConstant.CURRENT_ROLE_LIST);
		if (currRoleList != null && currRoleList.contains(InitConfig.getSysCfg("res_quality_control_role_flow"))) {
			model.addAttribute("isQuality", "Y");
		}
		if (StringUtil.isNotBlank(roleFlag) && roleFlag.equals("speAdmin")){
			return "jsres/doctorListSpeAdmin";
		}
		return "jsres/doctorListNew";
	}

	@RequestMapping(value = "/provinceDoctorListNewAcc")
	public String provinceDoctorListNewAcc(Model model, String roleFlag, String[] datas, String userName,
										   String isBack, String orgCityId, String yearStr, String baseFlag,HttpServletRequest request) throws UnsupportedEncodingException {
		// 登录用户信息
		SysUser sysuser = GlobalContext.getCurrentUser();
		SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org", org);
		SysOrg sysorg = new SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
		sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
//		sysorg.setOrgLevelId("CountryOrg");
		if (StringUtil.isNotBlank(orgCityId)) {
			sysorg.setOrgCityId(orgCityId);
		}
		List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
		sysorg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
		List<SysOrg> countryList = orgBiz.searchOrg(sysorg);
		sysorg.setOrgLevelId(OrgLevelEnum.ProvinceOrg.getId());
		List<SysOrg> provinceList = orgBiz.searchOrg(sysorg);

		List<ResJointOrg> jointOrgs = jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList = new ArrayList<String>();
		if (jointOrgs != null && !jointOrgs.isEmpty()) {
			for (ResJointOrg jointOrg : jointOrgs) {
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList", orgFlowList);
		model.addAttribute("countryList", countryList);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("baseFlag", baseFlag);
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(sysuser.getOrgFlow());
		//是协同基地
		if (!joinOrgs.isEmpty() && joinOrgs.size() > 0) {
			model.addAttribute("isJointOrg", "1");
		}
		model.addAttribute("datas", datas);
		if (StringUtil.isNotBlank(userName) && GlobalConstant.FLAG_Y.equals(isBack)) {
			userName = java.net.URLDecoder.decode(userName, "UTF-8");
			model.addAttribute("isBack", isBack);
		}
		model.addAttribute("userName", userName);
		model.addAttribute("yearDatas", StringUtil.isNotBlank(yearStr) ? yearStr.split(",") : null);
		List<String> currRoleList = (List<String>) request.getSession().getAttribute(GlobalConstant.CURRENT_ROLE_LIST);
		if (currRoleList != null && currRoleList.contains(InitConfig.getSysCfg("res_quality_control_role_flow"))) {
			model.addAttribute("isQuality", "Y");
		}
		return "jsres/doctorListNewAcc";
	}

	public SchRotationDept readStandardRotationDept(String resultFlow){
		SchRotationDept rotationDept = null;
		if(StringUtil.isNotBlank(resultFlow)){
			SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
			if(result!=null){
				String rotationFlow = result.getRotationFlow();
				String standardDeptId = result.getStandardDeptId();
				SchRotationGroup group = rotationGroupBiz.readSchRotationGroup(result.getStandardGroupFlow());
				if(group!=null){
					if(StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(standardDeptId)){
						SchRotationDeptExample example = new SchRotationDeptExample();
						SchRotationDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y)
								.andRotationFlowEqualTo(rotationFlow)
								.andStandardDeptIdEqualTo(standardDeptId)
								.andOrgFlowIsNull()
								.andGroupFlowEqualTo(result.getStandardGroupFlow());
						List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExampleWithBLOBs(example);
						if(rotationDeptList!=null && rotationDeptList.size()>0){
							rotationDept = rotationDeptList.get(0);
						}
					}
				}
			}
		}
		return rotationDept;
	}
	/**
	 * 轮转科室培训详情
	 */
	@RequestMapping(value="/catalogue",method={RequestMethod.GET,RequestMethod.POST})
	public String catalogue(String resultFlow,String recTypeId,Model model,String queryTypes) throws Exception{

		if (StringUtil.isBlank(recTypeId)) {
			return "jsres/hospital/catalogue";
		}
		if("afterFile".equals(recTypeId))
		{
			List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
			SchArrangeResult result=schArrangeResultBiz.readSchArrangeResult(resultFlow);
			SchRotationDept schRotationDept=readStandardRotationDept(resultFlow);
			if (result != null&&StringUtil.isNotBlank(result.getDoctorFlow())&&schRotationDept!=null&&StringUtil.isNotBlank(schRotationDept.getRecordFlow())) {
//				ResRec rec =resRecBiz.queryResRec(schRotationDept.getRecordFlow(),result.getDoctorFlow(),AfterRecTypeEnum.AfterSummary.getId());
				ResSchProcessExpress rec = expressBiz.queryResRec(schRotationDept.getRecordFlow(),result.getDoctorFlow(),AfterRecTypeEnum.AfterSummary.getId());
				String content = null == rec ? "" : rec.getRecContent();
				if (StringUtil.isNotBlank(content)) {
					Document doc = DocumentHelper.parseText(content);
					Element root = doc.getRootElement();
					List<Element> imageEles = root.elements();
					if (imageEles != null && imageEles.size() > 0) {
						for (Element image : imageEles) {
							Map<String, Object> recContent = new HashMap<String, Object>();
							String imageFlow = image.attributeValue("imageFlow");
							List<Element> elements = image.elements();
							for (Element attr : elements) {
								String attrName = attr.getName();
								String attrValue = attr.getText();
								recContent.put(attrName, attrValue);
							}
							imagelist.add(recContent);
						}
					}
				}
			}
			model.addAttribute("imagelist",imagelist);
		}else {
			ResDoctorSchProcess resDoctorSchProcess = resDoctorProcessBiz.searchByResultFlow(resultFlow);
			if (resDoctorSchProcess != null) {
				String processFlow = resDoctorSchProcess.getProcessFlow();
				Map<String, Map<String, Object>> resRecMap = new HashMap<String, Map<String, Object>>();
				if(recTypeId.equals(ResRecTypeEnum.AfterEvaluation.getId())){
					List<ResSchProcessExpress> resRecList = expressBiz.searchByProcessFlowAndRecTypeIdClob(processFlow, recTypeId);

					if (resRecList != null) {
						for (ResSchProcessExpress resRec : resRecList) {
							String recContent = resRec.getRecContent();
							Map<String, Object> formDataMap = resRecBiz.parseRecContent(recContent);
							resRecMap.put(resRec.getRecFlow(), formDataMap);
						}
					}
					model.addAttribute("resRecList", resRecList);
					model.addAttribute("resRecMap", resRecMap);


					String operUserFlow=resDoctorSchProcess.getUserFlow();
					boolean f = false;
					f=resDoctorBiz.getCkkPower(operUserFlow);
					if (f) {
						ResScore outScore = resScoreBiz.getScoreByProcess(processFlow);
						model.addAttribute("outScore",outScore);
					}
					model.addAttribute("ckk",f);
					Map<String, Object> dataMap = new HashMap<String, Object>();
					Map<String,Object> processPerMap=resRecBiz.getRecProgressIn(operUserFlow,processFlow,null,null);
					if(processPerMap == null){
						processPerMap = new HashMap<String, Object>();
					}
					//获取不同类型并定义接受
					if(processPerMap!=null) {
						String caseRegistryId = ResRecTypeEnum.CaseRegistry.getId();
						String diseaseRegistryId = ResRecTypeEnum.DiseaseRegistry.getId();
						String skillRegistryId = ResRecTypeEnum.SkillRegistry.getId();
						String operationRegistryId = ResRecTypeEnum.OperationRegistry.getId();

						String caseRegistry = (String) processPerMap.get(processFlow + caseRegistryId);
						String caseRegistryReqNum = (String) processPerMap.get(processFlow + caseRegistryId + "ReqNum");
						String caseRegistryFinished = (String) processPerMap.get(processFlow + caseRegistryId + "Finished");

						String diseaseRegistry = (String) processPerMap.get(processFlow + diseaseRegistryId);
						String diseaseRegistryReqNum = (String) processPerMap.get(processFlow + diseaseRegistryId + "ReqNum");
						String diseaseRegistryFinished = (String) processPerMap.get(processFlow + diseaseRegistryId + "Finished");

						String skillRegistry = (String) processPerMap.get(processFlow + skillRegistryId);
						String skillRegistryReqNum = (String) processPerMap.get(processFlow + skillRegistryId + "ReqNum");
						String skillRegistryFinished = (String) processPerMap.get(processFlow + skillRegistryId + "Finished");

						String skillAndOperationRegistry = (String) processPerMap.get(processFlow + operationRegistryId);
						String skillAndOperationRegistryReqNum = (String) processPerMap.get(processFlow + operationRegistryId + "ReqNum");
						String skillAndOperationRegistryFinished = (String) processPerMap.get(processFlow + operationRegistryId + "Finished");

						String recTypeIdt = ResRecTypeEnum.CampaignRegistry.getId();
//						int teachingRounds = 0;
//						int difficult = 0;
//						int lecture = 0;
//						int death = 0;
						int jxcf = 0;  int xjk = 0;
						int rkjy = 0;  int ckkh = 0; int jnpx = 0;
						int yph = 0; int jxhz = 0; int jxbltl = 0; int lcczjnzd = 0;
						int lcblsxzd = 0; int ssczzd = 0; int yxzdbgsxzd = 0; int lcwxyd = 0;
						int ryjy = 0; int rzyjdjy = 0; int cjbg = 0;
						int ynbltl=0;	int wzbltl=0; int swbltl=0;
						int bgdfx=0;	int jxsj=0;	int sjys=0;
						List<String> recTypes = new ArrayList<String>();
						recTypes.add(recTypeIdt);
						List<ResRec> recs = resRecBiz.searchRecByProcessWithBLOBs(recTypes, processFlow, operUserFlow);
						for (ResRec resRec : recs) {
							String content = resRec.getRecContent();
							Document document = DocumentHelper.parseText(content);
							Element root = document.getRootElement();
							Element ec = root.element("activity_way");
							if (ec != null) {
								String text = ec.attributeValue("id");
								if(Jxcf.equals(text)){
									jxcf++;
								}else if(Ynbltl.equals(text)){
									ynbltl++;
								}else if(Wzbltl.equals(text)){
									wzbltl++;
								}else if(Xsjz.equals(text)){
									xjk++;
								}else if(Swbltl.equals(text)){
									swbltl++;
								}else if(Rkjy.equals(text)){
									rkjy++;
								}else if(Ckks.equals(text)){
									ckkh++;
								}else if(Jnpx.equals(text)){
									jnpx++;
								}else if(Yph.equals(text)){
									yph++;
								}else if(Jxhz.equals(text)){
									jxhz++;
								}else if(Jxbltl.equals(text)){
									jxbltl++;
								}else if(Lcczjnzd.equals(text)){
									lcczjnzd++;
								}else if(Lcblsxzd.equals(text)){
									lcblsxzd++;
								}else if(Ssczzd.equals(text)){
									ssczzd++;
								}else if(Yxzdbgsxzd.equals(text)){
									yxzdbgsxzd++;
								}else if(Lcwxyd.equals(text)){
									lcwxyd++;
								}else if(Ryjy.equals(text)){
									ryjy++;
								}else if(Rzyjdjy.equals(text)){
									rzyjdjy++;
								}else if(Cjbg.equals(text)){
									cjbg++;
								}else if (Bgdfx.equals(text)){
									bgdfx++;
								}else if(Jxsj.equals(text)){
									jxsj++;
								}else if (Sjys.equals(text)){
									sjys++;
								}
							}
						}
						List<TeachingActivityInfo> infos=resRecBiz.searchJoinActivityByProcessFlow(processFlow);
						if(infos!=null&&infos.size()>0)
						{
							for(TeachingActivityInfo info:infos)
							{
								String text=info.getActivityTypeId();
								if(Jxcf.equals(text)){
									jxcf++;
								}else if(Ynbltl.equals(text)){
									ynbltl++;
								}else if(Wzbltl.equals(text)){
									wzbltl++;
								}else if(Xsjz.equals(text)){
									xjk++;
								}else if(Swbltl.equals(text)){
									swbltl++;
								}else if(Rkjy.equals(text)){
									rkjy++;
								}else if(Ckks.equals(text)){
									ckkh++;
								}else if(Jnpx.equals(text)){
									jnpx++;
								}else if(Yph.equals(text)){
									yph++;
								}else if(Jxhz.equals(text)){
									jxhz++;
								}else if(Jxbltl.equals(text)){
									jxbltl++;
								}else if(Lcczjnzd.equals(text)){
									lcczjnzd++;
								}else if(Lcblsxzd.equals(text)){
									lcblsxzd++;
								}else if(Ssczzd.equals(text)){
									ssczzd++;
								}else if(Yxzdbgsxzd.equals(text)){
									yxzdbgsxzd++;
								}else if(Lcwxyd.equals(text)){
									lcwxyd++;
								}else if(Ryjy.equals(text)){
									ryjy++;
								}else if(Rzyjdjy.equals(text)){
									rzyjdjy++;
								}else if(Cjbg.equals(text)){
									cjbg++;
								}else if (Bgdfx.equals(text)){
									bgdfx++;
								}else if(Jxsj.equals(text)){
									jxsj++;
								}else if (Sjys.equals(text)){
									sjys++;
								}
							}
						}
						dataMap.put("caseRegistry", StringUtil.defaultIfEmpty(caseRegistry, "0"));
						dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(caseRegistryReqNum, "0"));
						dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(caseRegistryFinished, "0"));

						dataMap.put("diseaseRegistry", StringUtil.defaultIfEmpty(diseaseRegistry, "0"));
						dataMap.put("diseaseRegistryReqNum", StringUtil.defaultIfEmpty(diseaseRegistryReqNum, "0"));
						dataMap.put("diseaseRegistryFinished", StringUtil.defaultIfEmpty(diseaseRegistryFinished, "0"));

						dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(skillRegistry, "0"));
						dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(skillRegistryReqNum, "0"));
						dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(skillRegistryFinished, "0"));

						dataMap.put("operationRegistry", StringUtil.defaultIfEmpty(skillAndOperationRegistry, "0"));
						dataMap.put("operationRegistryReqNum", StringUtil.defaultIfEmpty(skillAndOperationRegistryReqNum, "0"));
						dataMap.put("operationRegistryFinished", StringUtil.defaultIfEmpty(skillAndOperationRegistryFinished, "0"));

						dataMap.put("jxcf",String.valueOf(jxcf));
//			dataMap.put("ynbltl",String.valueOf(ynbltl));
//			dataMap.put("wzbltl",String.valueOf(wzbltl));
						dataMap.put("xjk",String.valueOf(xjk));
//			dataMap.put("swbltl",String.valueOf(swbltl));
						dataMap.put("rkjy",String.valueOf(rkjy));
						dataMap.put("ckkh",String.valueOf(ckkh));
						dataMap.put("jnpx",String.valueOf(jnpx));
						dataMap.put("yph",String.valueOf(yph));
						dataMap.put("jxhz",String.valueOf(jxhz));
						dataMap.put("jxbltl",String.valueOf(jxbltl));
						dataMap.put("wzbltl",String.valueOf(wzbltl));
						dataMap.put("swbltl",String.valueOf(swbltl));
						dataMap.put("ynbltl",String.valueOf(ynbltl));
						dataMap.put("lcczjnzd",String.valueOf(lcczjnzd));
						dataMap.put("lcblsxzd",String.valueOf(lcblsxzd));
						dataMap.put("ssczzd",String.valueOf(ssczzd));
						dataMap.put("yxzdbgsxzd",String.valueOf(yxzdbgsxzd));
						dataMap.put("lcwxyd",String.valueOf(lcwxyd));
						dataMap.put("ryjy",String.valueOf(ryjy));
						dataMap.put("rzyjdjy",String.valueOf(rzyjdjy));
						dataMap.put("cjbg",String.valueOf(cjbg));
						dataMap.put("bgdfx",String.valueOf(bgdfx));
						dataMap.put("jxsj",String.valueOf(jxsj));
						dataMap.put("sjys",String.valueOf(sjys));
						model.addAttribute("dataMap", dataMap);
					}

				}else{
					List<ResRec> resRecList = new ArrayList<ResRec>();
					if("1".equals(queryTypes)){
						Map<String, Object> paramMap = new HashMap<>();
						String doctorFlow = resDoctorSchProcess.getUserFlow()== null ? "" : resDoctorSchProcess.getUserFlow();
						paramMap.put("recTypeId",recTypeId);
						paramMap.put("processFlow",processFlow);
						paramMap.put("doctorFlow",doctorFlow);
						// 点击活动登记表查询
						resRecList= resRecBiz.searchRecAndActivityByProcess(paramMap);
					} else {
						resRecList = resRecBiz.searchByProcessFlowAndRecTypeIdClob(processFlow, recTypeId);
					}

					if (resRecList != null) {
						for (ResRec resRec : resRecList) {
							Map<String, Object> formDataMap = new HashMap<>();
							String queryType = resRec.getQueryType() == null ? "" : resRec.getQueryType();
							// 增加参加教学活动类型 + 点击活动登记表查询
							if("1".equals(queryType) && "1".equals(queryTypes)){
								formDataMap.put("activity_date",resRec.getCreateTime() == null ? "" : resRec.getCreateTime());//  活动时间
								formDataMap.put("activity_way",resRec.getRecTypeName()  == null ? "" : resRec.getRecTypeName());  //  活动形式
								formDataMap.put("activity_period","1"); //  学时 默认为1
								formDataMap.put("activity_speaker",resRec.getAuditUserName()  == null ? "" : resRec.getAuditUserName()); // 主讲人
								formDataMap.put("activity_content",resRec.getRecForm()  == null ? "" : resRec.getRecForm()); // 内容
								formDataMap.put("create_time",resRec.getCreateTime() == null ? "" : DateUtil.transDateTime(resRec.getCreateTime(), "yyyyMMddHHmmss", "yyyy-MM-dd"));//  创建时间
							} else{
								String recContent = resRec.getRecContent();
								formDataMap = resRecBiz.parseRecContent(recContent);
								formDataMap.put("create_time",DateUtil.transDateTime(resRec.getCreateTime(), "yyyyMMddHHmmss", "yyyy-MM-dd"));
							}
							resRecMap.put(resRec.getRecFlow(), formDataMap);
						}
					}
					model.addAttribute("resRecList", resRecList);
					model.addAttribute("resRecMap", resRecMap);

					if (ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId) || ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)) {
						String cfgCodeId = null;
						if (ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId)) {
							cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
						} else if (ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)) {
							cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
						}
						if (resRecList != null && resRecList.size() > 0) {
							ResRec rec = resRecList.get(0);
							if (rec != null) {
								model.addAttribute("rec", rec);
								Map<String, Object> gradeMap = resRecBiz.parseGradeXml(rec.getRecContent());
								model.addAttribute("gradeMap", gradeMap);
							}
						}
						ResAssessCfg search = new ResAssessCfg();
						search.setCfgCodeId(cfgCodeId);
						List<ResAssessCfg> assessCfgList = assessCfgBiz.searchAssessCfgList(search);
						if (assessCfgList != null && !assessCfgList.isEmpty()) {
							ResAssessCfg assessCfg = assessCfgList.get(0);
							model.addAttribute("assessCfg", assessCfg);
							Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
							String titleXpath = "//title";
							List<Element> titleElementList = dom.selectNodes(titleXpath);
							if (titleElementList != null && !titleElementList.isEmpty()) {
								List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
								for (Element te : titleElementList) {
									ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
									titleForm.setId(te.attributeValue("id"));
									titleForm.setName(te.attributeValue("name"));
									List<Element> itemElementList = te.elements("item");
									List<ResAssessCfgItemForm> itemFormList = null;
									if (itemElementList != null && !itemElementList.isEmpty()) {
										itemFormList = new ArrayList<ResAssessCfgItemForm>();
										for (Element ie : itemElementList) {
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
								model.addAttribute("titleFormList", titleFormList);
							}
						}
					}
				}

			}
		}
		return "jsres/hospital/catalogue";
	}
	/**
	 * 轮转科室详细信息
	 */
	@RequestMapping(value="/cycleDeptDetails")
	public String cycleDeptDetails(Model model,String resultFlow,String schStartDate,String schEndDate){
		ResDoctorSchProcess resDoctorSchProcess = resDoctorProcessBiz.searchByResultFlow(resultFlow);
		if (resDoctorSchProcess!=null) {
			String teacherName = resDoctorSchProcess.getTeacherUserName();
			String schDeptName = resDoctorSchProcess.getSchDeptName();
			String processFlow = resDoctorSchProcess.getProcessFlow();
			model.addAttribute("schDeptName", schDeptName);
			model.addAttribute("teacherName", teacherName);
			if (StringUtil.isNotBlank(resultFlow)) {
				SchArrangeResult schArrangeResult = schArrangeResultBiz.readSchArrangeResult(resultFlow);
				String doctorFlow = schArrangeResult.getDoctorFlow();
				if (StringUtil.isNotBlank(doctorFlow)) {
					Map<String, Object> processPerMap = resRecBiz.getRecProgressIn(doctorFlow, processFlow,null,null);
					model.addAttribute("processPerMap", processPerMap);
				}
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
		return "jsres/hospital/cycleDetails";
	}
	/**
	 * 医师轮转培训查询 住院医师
	 */
	@RequestMapping(value = "/cycle")
	public String cycle(String userName,String idNo,String trainingTypeId,String trainingSpeId,String sessionNumber,String trainingYears,
						String graduationTime,Model model,String[] docTypes,HttpServletRequest request,String roleFlag,
						String startDate,String endDate,Integer currentPage,String onlyProblem,String baseFlag) throws DocumentException {
		List<String> titleDate=new ArrayList<>();
		if(!StringUtil.isNotBlank(startDate)){
			endDate = DateUtil.getCurrDate();
			startDate = DateUtil.newDateOfAddMonths(endDate,-6);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			String cycStartMonth = startDate.substring(0, 7);
			String cycEndMonth = endDate.substring(0, 7);
			titleDate = getMonthsByTwoMonth(cycStartMonth, cycEndMonth);
			model.addAttribute("titleDate", titleDate);
		}
		SysUser currUser=GlobalContext.getCurrentUser();
		String orgFlow = currUser.getOrgFlow();
		String isQuality = "N";//是否为质控组
		List<String> currRoleList = (List<String>) request.getSession().getAttribute(GlobalConstant.CURRENT_ROLE_LIST);
		if (currRoleList != null && currRoleList.contains(InitConfig.getSysCfg("res_quality_control_role_flow"))) {
			trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
			orgFlow = "";
			isQuality = "Y";
			model.addAttribute("isQuality", "Y");
		}
		if (GlobalConstant.USER_LIST_SPELOCAL.equals(roleFlag) || GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(roleFlag)) {
			trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
		}
		Map<String,Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("trainingTypeId",TrainCategoryEnum.DoctorTrainingSpe.getId());	//住院医师
		if(docTypes!=null&&docTypes.length>0){
			paramMap.put("docTypeList",docTypes);
		}
		model.addAttribute("docTypeList",docTypes);
		if(StringUtil.isNotBlank(orgFlow)){
			//判断基地是否为协同基地
			List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(orgFlow);
			if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
				paramMap.put("secondOrgFlow",orgFlow);
			}else{
				paramMap.put("orgFlow",orgFlow);
			}
		}
		if(userName!=null ){
			paramMap.put("userName",userName);
		}
		if(idNo!=null ){
			paramMap.put("idNo",idNo);
		}
		if(trainingSpeId!=null){
			paramMap.put("trainingSpeId",trainingSpeId);
		}
		if(sessionNumber!=null){
			paramMap.put("sessionNumber",sessionNumber);
		}
		if(trainingYears!=null){
			paramMap.put("trainingYears",trainingYears);
		}
		if(graduationTime!=null){
			paramMap.put("graduationTime",graduationTime);
		}
		paramMap.put("onlyProblem",onlyProblem);
		paramMap.put("startDate",startDate);
		paramMap.put("endDate",endDate);
		paramMap.put("baseFlag",baseFlag);
		PageHelper.startPage(currentPage, getPageSize(request));//分页,必须放在分页sql前
		List<Map<String, Object>> docCycleList = new ArrayList<>();
		if(StringUtil.isNotBlank(baseFlag)){
			docCycleList = schArrangeResultBiz.searchDocCycleBaseList(paramMap);
		} else {
			docCycleList = schArrangeResultBiz.searchDocCycleList(paramMap);
		}
		model.addAttribute("docCycleList",docCycleList);
//		if(StringUtil.isNotBlank(orgFlow)){
//			paramMap.put("orgFlow",orgFlow);
//		}
		if(docCycleList!=null&&docCycleList.size()>0) {
			Map<String, List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
//			Map<String, SchRotationDept> deptMap = new HashMap<String, SchRotationDept>();
//			Map<String, Object> imageMap = new HashMap<String, Object>();
			Map<String, Object> recMap = new HashMap<String, Object>();

			List<SchArrangeResult> arrResultList = new ArrayList<SchArrangeResult>();
			if(!(StringUtil.isBlank(orgFlow) && StringUtil.isBlank(baseFlag)))
			{
				arrResultList = schArrangeResultBiz.searchCycleArrangeResults(paramMap);
			}

			// 非空判断
			if(arrResultList.size() > 0){
				for (SchArrangeResult sar : arrResultList) {
					String doctorFlow = sar.getDoctorFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();

//				String standardDeptId = sar.getStandardDeptId();//轮转部门颜色
//				String standarGroupFlow = sar.getStandardGroupFlow();
//				if (StringUtil.isNotBlank(standardDeptId) && StringUtil.isNotBlank(standarGroupFlow)) {
//					List<Map<String, Object>> imagelist= (List<Map<String, Object>>) imageMap.get(doctorFlow+standarGroupFlow+ standardDeptId);
//					if(imagelist==null) {
//						SchRotationDept schRotationDept = deptMap.get(standarGroupFlow + standardDeptId);
//						if (schRotationDept == null)
//							schRotationDept = schRotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(standarGroupFlow, standardDeptId);
//						deptMap.put(standarGroupFlow + standardDeptId, schRotationDept);
//						if (schRotationDept != null) {
//							String recordFlow = schRotationDept.getRecordFlow();
////						ResRec resRec = resRecBiz.queryResRec(recordFlow, doctorFlow, ResRecTypeEnum.AfterSummary.getId());
//							ResSchProcessExpress resRec = expressBiz.queryResRec(recordFlow, doctorFlow, ResRecTypeEnum.AfterSummary.getId());
//							String content = null == resRec ? "" : resRec.getRecContent();
//							if (StringUtil.isNotBlank(content)) {
//								Document doc = DocumentHelper.parseText(content);
//								Element root = doc.getRootElement();
//								List<Element> imageEles = root.elements();
//								if (imageEles != null && imageEles.size() > 0) {
//									imagelist = new ArrayList<>();
//									for (Element image : imageEles) {
//										Map<String, Object> recContent = new HashMap<String, Object>();
//										List<Element> elements = image.elements();
//										for (Element attr : elements) {
//											String attrName = attr.getName();
//											String attrValue = attr.getText();
//											recContent.put(attrName, attrValue);
//										}
//										imagelist.add(recContent);
//									}
//									recMap.put(sar.getResultFlow(), imagelist);
//									imageMap.put(doctorFlow+standarGroupFlow+ standardDeptId, imagelist);
//								}
//							}
//						}
//					}else{
//						recMap.put(sar.getResultFlow(), imagelist);
//					}
//				}
					if (StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)) {
						resultStartDate = resultStartDate.substring(0, 7);
						resultEndDate = resultEndDate.substring(0, 7);
						for(String title:titleDate)
						{
							if(title.compareTo(resultStartDate)>=0&&title.compareTo(resultEndDate)<=0)
							{
								String key = doctorFlow + title;
								List<SchArrangeResult> sarList = resultListMap.get(key);
								if (sarList == null) {
									sarList = new ArrayList<SchArrangeResult>();
									resultListMap.put(key, sarList);
								}
								sarList.add(sar);
							}
						}
//					List<String> months = getMonthsByTwoMonth(resultStartDate, resultEndDate);
//					if (months != null) {
//						for (String month : months) {
//							String key = doctorFlow + month;
//							List<SchArrangeResult> sarList = resultListMap.get(key);
//							if (sarList == null) {
//								sarList = new ArrayList<SchArrangeResult>();
//								resultListMap.put(key, sarList);
//							}
//							sarList.add(sar);
//						}
//					}
					}
				}
			}


			model.addAttribute("recMap", recMap);
			model.addAttribute("resultListMap",resultListMap);
		}

		return "jsres/hospital/cycle";
	}

	/**
	 * 医师轮转培训查询 助理全科
	 */
	@RequestMapping(value = "/cycleAcc")
	public String cycleAcc(String userName,String idNo,String trainingTypeId,String trainingSpeId,String sessionNumber,String trainingYears,
						   String graduationTime,Model model,String[] docTypes,HttpServletRequest request,String roleFlag,
						   String startDate,String endDate,Integer currentPage,String onlyProblem,String baseFlag) throws DocumentException {
		List<String> titleDate=new ArrayList<>();
		if(!StringUtil.isNotBlank(startDate)){
			endDate = DateUtil.getCurrDate();
			startDate = DateUtil.newDateOfAddMonths(endDate,-6);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			String cycStartMonth = startDate.substring(0, 7);
			String cycEndMonth = endDate.substring(0, 7);
			titleDate = getMonthsByTwoMonth(cycStartMonth, cycEndMonth);
			model.addAttribute("titleDate", titleDate);
		}
		SysUser currUser=GlobalContext.getCurrentUser();
		String orgFlow = currUser.getOrgFlow();
		String isQuality = "N";//是否为质控组
		List<String> currRoleList = (List<String>) request.getSession().getAttribute(GlobalConstant.CURRENT_ROLE_LIST);
		if (currRoleList != null && currRoleList.contains(InitConfig.getSysCfg("res_quality_control_role_flow"))) {
			trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
			orgFlow = "";
			isQuality = "Y";
			model.addAttribute("isQuality", "Y");
		}
		if (GlobalConstant.USER_LIST_SPELOCAL.equals(roleFlag) || GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(roleFlag)) {
			trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
		}
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("trainingTypeId",TrainCategoryEnum.AssiGeneral.getId());	//助理全科
		if(docTypes!=null&&docTypes.length>0){
			paramMap.put("docTypeList",docTypes);
		}
		model.addAttribute("docTypeList",docTypes);
		if(StringUtil.isNotBlank(orgFlow)){
			//判断基地是否为协同基地
			List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(orgFlow);
			if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
				paramMap.put("secondOrgFlow",orgFlow);
			}else{
				paramMap.put("orgFlow",orgFlow);
			}
		}
		if(userName!=null ){
			paramMap.put("userName",userName);
		}
		if(idNo!=null ){
			paramMap.put("idNo",idNo);
		}
		if(trainingSpeId!=null){
			paramMap.put("trainingSpeId",trainingSpeId);
		}
		if(sessionNumber!=null){
			paramMap.put("sessionNumber",sessionNumber);
		}
		if(trainingYears!=null){
			paramMap.put("trainingYears",trainingYears);
		}
		if(graduationTime!=null){
			paramMap.put("graduationTime",graduationTime);
		}
		paramMap.put("onlyProblem",onlyProblem);
		paramMap.put("startDate",startDate);
		paramMap.put("endDate",endDate);
		paramMap.put("baseFlag",baseFlag);
		PageHelper.startPage(currentPage, getPageSize(request));//分页,必须放在分页sql前
		List<Map<String, Object>> docCycleList = new ArrayList<>();
		if(StringUtil.isNotBlank(baseFlag)){
			docCycleList = schArrangeResultBiz.searchDocCycleBaseList(paramMap);
		} else {
			docCycleList = schArrangeResultBiz.searchDocCycleList(paramMap);
		}
		model.addAttribute("docCycleList",docCycleList);
//		if(StringUtil.isNotBlank(orgFlow)){
//			paramMap.put("orgFlow",orgFlow);
//		}
		if(docCycleList!=null&&docCycleList.size()>0) {
			Map<String, List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
//			Map<String, SchRotationDept> deptMap = new HashMap<String, SchRotationDept>();
//			Map<String, Object> imageMap = new HashMap<String, Object>();
			Map<String, Object> recMap = new HashMap<String, Object>();

			List<SchArrangeResult> arrResultList = new ArrayList<SchArrangeResult>();
			if(!(StringUtil.isBlank(orgFlow) && StringUtil.isBlank(baseFlag)))
			{
				arrResultList = schArrangeResultBiz.searchCycleArrangeResults(paramMap);
			}

			// 非空判断
			if(arrResultList.size() > 0){
				for (SchArrangeResult sar : arrResultList) {
					String doctorFlow = sar.getDoctorFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();

//				String standardDeptId = sar.getStandardDeptId();//轮转部门颜色
//				String standarGroupFlow = sar.getStandardGroupFlow();
//				if (StringUtil.isNotBlank(standardDeptId) && StringUtil.isNotBlank(standarGroupFlow)) {
//					List<Map<String, Object>> imagelist= (List<Map<String, Object>>) imageMap.get(doctorFlow+standarGroupFlow+ standardDeptId);
//					if(imagelist==null) {
//						SchRotationDept schRotationDept = deptMap.get(standarGroupFlow + standardDeptId);
//						if (schRotationDept == null)
//							schRotationDept = schRotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(standarGroupFlow, standardDeptId);
//						deptMap.put(standarGroupFlow + standardDeptId, schRotationDept);
//						if (schRotationDept != null) {
//							String recordFlow = schRotationDept.getRecordFlow();
////						ResRec resRec = resRecBiz.queryResRec(recordFlow, doctorFlow, ResRecTypeEnum.AfterSummary.getId());
//							ResSchProcessExpress resRec = expressBiz.queryResRec(recordFlow, doctorFlow, ResRecTypeEnum.AfterSummary.getId());
//							String content = null == resRec ? "" : resRec.getRecContent();
//							if (StringUtil.isNotBlank(content)) {
//								Document doc = DocumentHelper.parseText(content);
//								Element root = doc.getRootElement();
//								List<Element> imageEles = root.elements();
//								if (imageEles != null && imageEles.size() > 0) {
//									imagelist = new ArrayList<>();
//									for (Element image : imageEles) {
//										Map<String, Object> recContent = new HashMap<String, Object>();
//										List<Element> elements = image.elements();
//										for (Element attr : elements) {
//											String attrName = attr.getName();
//											String attrValue = attr.getText();
//											recContent.put(attrName, attrValue);
//										}
//										imagelist.add(recContent);
//									}
//									recMap.put(sar.getResultFlow(), imagelist);
//									imageMap.put(doctorFlow+standarGroupFlow+ standardDeptId, imagelist);
//								}
//							}
//						}
//					}else{
//						recMap.put(sar.getResultFlow(), imagelist);
//					}
//				}
					if (StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)) {
						resultStartDate = resultStartDate.substring(0, 7);
						resultEndDate = resultEndDate.substring(0, 7);
						for(String title:titleDate)
						{
							if(title.compareTo(resultStartDate)>=0&&title.compareTo(resultEndDate)<=0)
							{
								String key = doctorFlow + title;
								List<SchArrangeResult> sarList = resultListMap.get(key);
								if (sarList == null) {
									sarList = new ArrayList<SchArrangeResult>();
									resultListMap.put(key, sarList);
								}
								sarList.add(sar);
							}
						}
//					List<String> months = getMonthsByTwoMonth(resultStartDate, resultEndDate);
//					if (months != null) {
//						for (String month : months) {
//							String key = doctorFlow + month;
//							List<SchArrangeResult> sarList = resultListMap.get(key);
//							if (sarList == null) {
//								sarList = new ArrayList<SchArrangeResult>();
//								resultListMap.put(key, sarList);
//							}
//							sarList.add(sar);
//						}
//					}
					}
				}
			}


			model.addAttribute("recMap", recMap);
			model.addAttribute("resultListMap",resultListMap);
		}

		return "jsres/hospital/cycleAcc";
	}

	/**
	 * 医师轮转培训导出
	 */
	@RequestMapping(value = "/export/doctorCycle")
	@ResponseBody
	public void exportExcelForDoctor(String userName,String idNo,String trainingTypeId,String trainingSpeId,String sessionNumber,String trainingYears,
									 String graduationTime,Model model,String[] docTypes,HttpServletRequest request,String roleFlag,
									 String startDate,String endDate,Integer currentPage,String onlyProblem,String baseFlag, HttpServletResponse response) throws IOException, ParseException {
		List<String> titleDate=new ArrayList<>();
		if(!StringUtil.isNotBlank(startDate)){
			endDate = DateUtil.getCurrDate();
			startDate = DateUtil.newDateOfAddMonths(endDate,-6);
		}
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			String cycStartMonth = startDate.substring(0, 7);
			String cycEndMonth = endDate.substring(0, 7);
			titleDate = getMonthsByTwoMonth(cycStartMonth, cycEndMonth);
		}
		SysUser currUser=GlobalContext.getCurrentUser();
		String orgFlow = currUser.getOrgFlow();
		String isQuality = "N";//是否为质控组
		List<String> currRoleList = (List<String>) request.getSession().getAttribute(GlobalConstant.CURRENT_ROLE_LIST);
		if (currRoleList != null && currRoleList.contains(InitConfig.getSysCfg("res_quality_control_role_flow"))) {
			trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
			orgFlow = "";
			isQuality = "Y";
		}
		if (GlobalConstant.USER_LIST_SPELOCAL.equals(roleFlag) || GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(roleFlag)) {
			trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
		}
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("trainingTypeId",trainingTypeId);
		if(docTypes!=null&&docTypes.length>0){
			paramMap.put("docTypeList",docTypes);
		}
		if(StringUtil.isNotBlank(orgFlow)){
			//判断基地是否为协同基地
			List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(orgFlow);
			if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
				paramMap.put("secondOrgFlow",orgFlow);
			}else{
				paramMap.put("orgFlow",orgFlow);
			}
		}
		if(userName!=null ){
			paramMap.put("userName",userName);
		}
		if(idNo!=null ){
			paramMap.put("idNo",idNo);
		}
		if(trainingSpeId!=null){
			paramMap.put("trainingSpeId",trainingSpeId);
		}
		if(sessionNumber!=null){
			paramMap.put("sessionNumber",sessionNumber);
		}
		if(trainingYears!=null){
			paramMap.put("trainingYears",trainingYears);
		}
		if(graduationTime!=null){
			paramMap.put("graduationTime",graduationTime);
		}
		paramMap.put("onlyProblem",onlyProblem);
		paramMap.put("startDate",startDate);
		paramMap.put("endDate",endDate);
		paramMap.put("baseFlag",baseFlag);
//		PageHelper.startPage(currentPage, getPageSize(request));//分页,必须放在分页sql前
		List<Map<String, Object>> docCycleList = new ArrayList<>();
		if(StringUtil.isNotBlank(baseFlag)){
			docCycleList = schArrangeResultBiz.searchDocCycleBaseList(paramMap);
		} else {
			docCycleList = schArrangeResultBiz.searchDocCycleList(paramMap);
		}
		Map<String, List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
		if(docCycleList!=null&&docCycleList.size()>0) {

			List<SchArrangeResult> arrResultList = new ArrayList<SchArrangeResult>();
			if(!(StringUtil.isBlank(orgFlow) && StringUtil.isBlank(baseFlag)))
			{
				arrResultList = schArrangeResultBiz.searchCycleArrangeResults(paramMap);
			}

			// 非空判断
			if(arrResultList.size() > 0){
				for (SchArrangeResult sar : arrResultList) {
					String doctorFlow = sar.getDoctorFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();
					if (StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)) {
						resultStartDate = resultStartDate.substring(0, 7);
						resultEndDate = resultEndDate.substring(0, 7);
						for(String title:titleDate)
						{
							if(title.compareTo(resultStartDate)>=0&&title.compareTo(resultEndDate)<=0)
							{
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
		}
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");

		HSSFFont font = wb.createFont();
		font.setColor(Font.COLOR_RED);
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);
		HSSFFont font1 = wb.createFont();
//		font1.setColor(HSSFColor);
		HSSFCellStyle style1 = wb.createCellStyle();
		style1.setFont(font1);
		HSSFFont font2 = wb.createFont();
		font2.setColor(IndexedColors.GREEN.getIndex());
		HSSFCellStyle style2 = wb.createCellStyle();
		style2.setFont(font2);
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);

		sheet.setColumnWidth(0, 3000);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

		//填写工作簿内容
		HSSFRow titleRow = sheet.createRow(0);
		HSSFCell cell0 = titleRow.createCell(0);
		cell0.setCellValue("姓名");
		cell0.setCellStyle(styleLeft);
		HSSFCell cell1 = titleRow.createCell(1);
		cell1.setCellValue("培训类别");
		cell1.setCellStyle(styleLeft);
		HSSFCell cell2 = titleRow.createCell(2);
		cell2.setCellValue("专业");
		cell2.setCellStyle(styleLeft);
		HSSFCell cell3 = titleRow.createCell(3);
		cell3.setCellValue("年级");
		cell3.setCellStyle(styleLeft);
		HSSFCell cell4 = titleRow.createCell(4);
		cell4.setCellValue("培训年限");
		cell4.setCellStyle(styleLeft);
		int colnum1 = 5;
		for (String Date : titleDate) {
			HSSFCell deptCell2 = titleRow.createCell(colnum1);
			deptCell2.setCellValue(Date);
			deptCell2.setCellStyle(styleCenter);
			colnum1++;
		}
		if (docCycleList != null && docCycleList.size() > 0) {
			int rowNumber = 1;
			for (Map<String, Object> docCycle : docCycleList) {
				HSSFRow dataRow = sheet.createRow(rowNumber);
				HSSFCell cell10 = dataRow.createCell(0);
				cell10.setCellValue((String)docCycle.get("userName"));
				cell10.setCellStyle(styleLeft);
				HSSFCell cell11 = dataRow.createCell(1);
				cell11.setCellValue((String)docCycle.get("trainingTypeName"));
				cell11.setCellStyle(styleLeft);
				HSSFCell cell12 = dataRow.createCell(2);
				cell12.setCellValue((String)docCycle.get("trainingSpeName"));
				cell12.setCellStyle(styleLeft);
				HSSFCell cell13 = dataRow.createCell(3);
				cell13.setCellValue((String)docCycle.get("sessionNumber"));
				cell13.setCellStyle(styleLeft);
				HSSFCell cell14 = dataRow.createCell(4);
				if (docCycle.get("trainingYears").equals("OneYear")) {
					cell14.setCellValue("一年");
				}
				if (docCycle.get("trainingYears").equals("TwoYear")) {
					cell14.setCellValue("两年");
				}
				if (docCycle.get("trainingYears").equals("ThreeYear")) {
					cell14.setCellValue("三年");
				}
				cell14.setCellStyle(styleLeft);
				int colNumber = 5;
//				for (String Date : titleDate) {
//					HSSFCell deptCell2 = dataRow.createCell(colNumber);
//					String data = "";
//					List<SchArrangeResult> resultList = resultListMap.get(docCycle.get("doctorFlow") + Date);
//					if (resultList == null || resultList.size() <= 0) {
//						data = "-";
//					} else {
//						for (SchArrangeResult result : resultList) {
//							data = data + result.getSchDeptName() + ";";
//						}
//					}
//					deptCell2.setCellValue(data);
//					deptCell2.setCellStyle(styleCenter);
//					colNumber += 1;
//				}
				for (String Date : titleDate) {
					HSSFCell deptCell2 = dataRow.createCell(colNumber);
					String data = "";
					List<SchArrangeResult> resultList = resultListMap.get(docCycle.get("doctorFlow") + Date);
					if (resultList == null || resultList.isEmpty()) {
						data = "-";
					} else {
						for (SchArrangeResult result : resultList) {
							Date currDate = new Date();
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							Date schStartDate = dateFormat.parse(result.getSchStartDate());
							Date schEndDate = dateFormat.parse(result.getSchEndDate());
							if((schStartDate.compareTo(currDate) < 0 || schStartDate.compareTo(currDate) == 0) && schEndDate.compareTo(currDate) > 0){
								deptCell2.setCellStyle(style1);
							}else if(GlobalConstant.FLAG_Y.equals(result.getHaveAfterPic()) && (schStartDate.compareTo(currDate) < 0 || schEndDate.compareTo(currDate) == 0)){
								deptCell2.setCellStyle(style2);
							}else if(GlobalConstant.FLAG_N.equals(result.getHaveAfterPic()) && (schStartDate.compareTo(currDate) < 0 || schEndDate.compareTo(currDate) == 0)){
								deptCell2.setCellStyle(style);
							}
							data += result.getSchDeptName() + ";";
						}
					}
					deptCell2.setCellValue(data);
//					deptCell2.setCellStyle(style);
					colNumber++;
				}
				rowNumber += 1;
			}
			String fileName = "学员轮转表.xls";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			wb.write(response.getOutputStream());
		}
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
	 * 医师出科成绩细节
	 */
	@RequestMapping(value="/resultsDetails")
	public String resultsDetails(String resultFlow,String recTypeId,Model model)throws DocumentException {
		if (StringUtil.isBlank(recTypeId)) {
			return "jsres/hospital/cycleResultsDetails";
		}
		if(ResRecTypeEnum.AfterSummary.getId().equals(recTypeId)) {
			SchArrangeResult schArrangeResult = schArrangeResultBiz.readSchArrangeResult(resultFlow);
			String standardDeptId = schArrangeResult.getStandardDeptId();
			String standardGroupFlow = schArrangeResult.getStandardGroupFlow();
			String doctorFlow = schArrangeResult.getDoctorFlow();
			if (standardDeptId!=null&&standardGroupFlow!=null&&doctorFlow!=null) {
				SchRotationDept schRotationDept = schRotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(standardGroupFlow, standardDeptId);
				String recordFlow = schRotationDept.getRecordFlow();
//				ResRec resRec = resRecBiz.queryResRec(recordFlow, doctorFlow, recTypeId);
				ResSchProcessExpress resRec = expressBiz.queryResRec(recordFlow, doctorFlow, recTypeId);
				if(resRec!=null) {
					String content = resRec.getRecContent();
					List<Map<String, String>> imageList = resRecBiz.parseImageXml(content);
					model.addAttribute("imageList", imageList);
				}
			}
		}else {
			ResDoctorSchProcess resDoctorSchProcess = resDoctorProcessBiz.searchByResultFlow(resultFlow);
			if (resDoctorSchProcess != null) {
				String processFlow = resDoctorSchProcess.getProcessFlow();
				Map<String, Map<String, Object>> resRecMap = new HashMap<String, Map<String, Object>>();
//				List<ResRec> resRecList = resRecBiz.searchByProcessFlowAndRecTypeIdClob(processFlow, recTypeId);
				List<ResSchProcessExpress> resRecList = expressBiz.searchByProcessFlowAndRecTypeIdClob(processFlow, recTypeId);

				if (resRecList != null && !resRecList.isEmpty()) {
					for (ResSchProcessExpress resRec : resRecList) {
						String content = resRec.getRecContent();
						Map<String, Object> formDataMap = resRecBiz.parseRecContent(content);
						resRecMap.put(resRec.getRecFlow(), formDataMap);
					}
				}
				if(recTypeId.equals(ResRecTypeEnum.AfterEvaluation.getId())){
					String operUserFlow=resDoctorSchProcess.getUserFlow();
					boolean f = false;
					f=resDoctorBiz.getCkkPower(operUserFlow);
					if (f) {
						ResScore outScore = resScoreBiz.getScoreByProcess(processFlow);
						model.addAttribute("outScore",outScore);
					}
					model.addAttribute("ckk",f);

					Map<String, Object> dataMap = new HashMap<String, Object>();
					Map<String,Object> processPerMap=resRecBiz.getRecProgressIn(operUserFlow,processFlow,null,null);
					if(processPerMap == null){
						processPerMap = new HashMap<String, Object>();
					}
					//获取不同类型并定义接受
					if(processPerMap!=null) {
						String caseRegistryId = ResRecTypeEnum.CaseRegistry.getId();
						String diseaseRegistryId = ResRecTypeEnum.DiseaseRegistry.getId();
						String skillRegistryId = ResRecTypeEnum.SkillRegistry.getId();
						String operationRegistryId = ResRecTypeEnum.OperationRegistry.getId();

						String caseRegistry = (String) processPerMap.get(processFlow + caseRegistryId);
						String caseRegistryReqNum = (String) processPerMap.get(processFlow + caseRegistryId + "ReqNum");
						String caseRegistryFinished = (String) processPerMap.get(processFlow + caseRegistryId + "Finished");

						String diseaseRegistry = (String) processPerMap.get(processFlow + diseaseRegistryId);
						String diseaseRegistryReqNum = (String) processPerMap.get(processFlow + diseaseRegistryId + "ReqNum");
						String diseaseRegistryFinished = (String) processPerMap.get(processFlow + diseaseRegistryId + "Finished");

						String skillRegistry = (String) processPerMap.get(processFlow + skillRegistryId);
						String skillRegistryReqNum = (String) processPerMap.get(processFlow + skillRegistryId + "ReqNum");
						String skillRegistryFinished = (String) processPerMap.get(processFlow + skillRegistryId + "Finished");

						String skillAndOperationRegistry = (String) processPerMap.get(processFlow + operationRegistryId);
						String skillAndOperationRegistryReqNum = (String) processPerMap.get(processFlow + operationRegistryId + "ReqNum");
						String skillAndOperationRegistryFinished = (String) processPerMap.get(processFlow + operationRegistryId + "Finished");

						String recTypeIdt = ResRecTypeEnum.CampaignRegistry.getId();
//						int teachingRounds = 0;
//						int difficult = 0;
//						int lecture = 0;
//						int death = 0;
						int jxcf = 0;  int xjk = 0;
						int rkjy = 0;  int ckkh = 0; int jnpx = 0;
						int yph = 0; int jxhz = 0; int jxbltl = 0; int lcczjnzd = 0;
						int lcblsxzd = 0; int ssczzd = 0; int yxzdbgsxzd = 0; int lcwxyd = 0;
						int ryjy = 0; int rzyjdjy = 0; int cjbg = 0;
						int ynbltl=0;	int wzbltl=0; int swbltl=0;
						List<String> recTypes = new ArrayList<String>();
						recTypes.add(recTypeIdt);
//						List<ResRec> recs = resRecBiz.searchRecByProcessWithBLOBs(recTypes, processFlow, operUserFlow);
						List<ResSchProcessExpress> recs = expressBiz.searchRecByProcessWithBLOBs(recTypes, processFlow);

						for (ResSchProcessExpress resRec : recs) {
							String content = resRec.getRecContent();
							Document document = DocumentHelper.parseText(content);
							Element root = document.getRootElement();
							Element ec = root.element("activity_way");
							if (ec != null) {
								String text = ec.attributeValue("id");
								if(Jxcf.equals(text)){
									jxcf++;
								}else if(Ynbltl.equals(text)){
									ynbltl++;
								}else if(Wzbltl.equals(text)){
									wzbltl++;
								}else if(Xsjz.equals(text)){
									xjk++;
								}else if(Swbltl.equals(text)){
									swbltl++;
								}else if(Rkjy.equals(text)){
									rkjy++;
								}else if(Ckks.equals(text)){
									ckkh++;
								}else if(Jnpx.equals(text)){
									jnpx++;
								}else if(Yph.equals(text)){
									yph++;
								}else if(Jxhz.equals(text)){
									jxhz++;
								}else if(Jxbltl.equals(text)){
									jxbltl++;
								}else if(Lcczjnzd.equals(text)){
									lcczjnzd++;
								}else if(Lcblsxzd.equals(text)){
									lcblsxzd++;
								}else if(Ssczzd.equals(text)){
									ssczzd++;
								}else if(Yxzdbgsxzd.equals(text)){
									yxzdbgsxzd++;
								}else if(Lcwxyd.equals(text)){
									lcwxyd++;
								}else if(Ryjy.equals(text)){
									ryjy++;
								}else if(Rzyjdjy.equals(text)){
									rzyjdjy++;
								}else if(Cjbg.equals(text)){
									cjbg++;
								}
							}
						}

						List<TeachingActivityInfo> infos=resRecBiz.searchJoinActivityByProcessFlow(processFlow);
						if(infos!=null&&infos.size()>0)
						{
							for(TeachingActivityInfo info:infos)
							{
								String text=info.getActivityTypeId();
								if(Jxcf.equals(text)){
									jxcf++;
								}else if(Ynbltl.equals(text)){
									ynbltl++;
								}else if(Wzbltl.equals(text)){
									wzbltl++;
								}else if(Xsjz.equals(text)){
									xjk++;
								}else if(Swbltl.equals(text)){
									swbltl++;
								}else if(Rkjy.equals(text)){
									rkjy++;
								}else if(Ckks.equals(text)){
									ckkh++;
								}else if(Jnpx.equals(text)){
									jnpx++;
								}else if(Yph.equals(text)){
									yph++;
								}else if(Jxhz.equals(text)){
									jxhz++;
								}else if(Jxbltl.equals(text)){
									jxbltl++;
								}else if(Lcczjnzd.equals(text)){
									lcczjnzd++;
								}else if(Lcblsxzd.equals(text)){
									lcblsxzd++;
								}else if(Ssczzd.equals(text)){
									ssczzd++;
								}else if(Yxzdbgsxzd.equals(text)){
									yxzdbgsxzd++;
								}else if(Lcwxyd.equals(text)){
									lcwxyd++;
								}else if(Ryjy.equals(text)){
									ryjy++;
								}else if(Rzyjdjy.equals(text)){
									rzyjdjy++;
								}else if(Cjbg.equals(text)){
									cjbg++;
								}
							}
						}
						dataMap.put("caseRegistry", StringUtil.defaultIfEmpty(caseRegistry, "0"));
						dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(caseRegistryReqNum, "0"));
						dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(caseRegistryFinished, "0"));

						dataMap.put("diseaseRegistry", StringUtil.defaultIfEmpty(diseaseRegistry, "0"));
						dataMap.put("diseaseRegistryReqNum", StringUtil.defaultIfEmpty(diseaseRegistryReqNum, "0"));
						dataMap.put("diseaseRegistryFinished", StringUtil.defaultIfEmpty(diseaseRegistryFinished, "0"));

						dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(skillRegistry, "0"));
						dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(skillRegistryReqNum, "0"));
						dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(skillRegistryFinished, "0"));

						dataMap.put("operationRegistry", StringUtil.defaultIfEmpty(skillAndOperationRegistry, "0"));
						dataMap.put("operationRegistryReqNum", StringUtil.defaultIfEmpty(skillAndOperationRegistryReqNum, "0"));
						dataMap.put("operationRegistryFinished", StringUtil.defaultIfEmpty(skillAndOperationRegistryFinished, "0"));

						dataMap.put("jxcf",String.valueOf(jxcf));
//			dataMap.put("ynbltl",String.valueOf(ynbltl));
//			dataMap.put("wzbltl",String.valueOf(wzbltl));
						dataMap.put("xjk",String.valueOf(xjk));
//			dataMap.put("swbltl",String.valueOf(swbltl));
						dataMap.put("rkjy",String.valueOf(rkjy));
						dataMap.put("ckkh",String.valueOf(ckkh));
						dataMap.put("jnpx",String.valueOf(jnpx));
						dataMap.put("yph",String.valueOf(yph));
						dataMap.put("jxhz",String.valueOf(jxhz));
						dataMap.put("jxbltl",String.valueOf(jxbltl));
						dataMap.put("wzbltl",String.valueOf(wzbltl));
						dataMap.put("swbltl",String.valueOf(swbltl));
						dataMap.put("ynbltl",String.valueOf(ynbltl));
						dataMap.put("lcczjnzd",String.valueOf(lcczjnzd));
						dataMap.put("lcblsxzd",String.valueOf(lcblsxzd));
						dataMap.put("ssczzd",String.valueOf(ssczzd));
						dataMap.put("yxzdbgsxzd",String.valueOf(yxzdbgsxzd));
						dataMap.put("lcwxyd",String.valueOf(lcwxyd));
						dataMap.put("ryjy",String.valueOf(ryjy));
						dataMap.put("rzyjdjy",String.valueOf(rzyjdjy));
						dataMap.put("cjbg",String.valueOf(cjbg));
						model.addAttribute("dataMap", dataMap);
					}
				}

				model.addAttribute("resRecMap", resRecMap);
				model.addAttribute("resRecList", resRecList);
			}
		}
		return "jsres/hospital/cycleResultsDetails";
	}


	//出科成绩查询
	@RequestMapping("/doctorRecruitResult")
	public String doctorRecruitResult(Model model,Integer currentPage,HttpServletRequest request,ResDoctor doctor,SysUser user,String[] datas,String roleId,String baseFlag){
		SysUser currentUser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(currentUser.getOrgFlow());

		model.addAttribute("org",org);
		List<SysOrg> orgs = new ArrayList<>();
		if (OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
			List<SysOrg> joinOrgs = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
			orgs.addAll(joinOrgs);
			orgs.add(org);
		}
		model.addAttribute("orgs", orgs);
		List<String> jointOrgFlowList=new ArrayList<String>();
		if(StringUtil.isBlank(doctor.getOrgFlow())){
			jointOrgFlowList.add(currentUser.getOrgFlow());
			List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(currentUser.getOrgFlow());
			if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:resJointOrgList){
					jointOrgFlowList.add(jointOrg.getJointOrgFlow());
				}
			}
		}else {
			jointOrgFlowList.add(doctor.getOrgFlow());
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if("kzr".equals(roleId)){
			List<SysUserDept> userDeptList = userBiz.getUserDept(GlobalContext.getCurrentUser());

			if(CollectionUtils.isNotEmpty(userDeptList)){
				paramMap.put("deptList",userDeptList);
			}else{
				paramMap.put("deptFlow",currentUser.getDeptFlow());
			}
			paramMap.put("kzrFlow",currentUser.getUserFlow());
		}else {
			paramMap.put("jointOrgFlowList",jointOrgFlowList);
		}
		paramMap.put("orgFlow",currentUser.getOrgFlow());
		paramMap.put("idNo",user.getIdNo());
		paramMap.put("userName",user.getUserName());
//		paramMap.put("trainingTypeId",TrainCategoryEnum.DoctorTrainingSpe.getId());
		paramMap.put("trainingTypeId",doctor.getTrainingTypeId());
		paramMap.put("trainingSpeId",doctor.getTrainingSpeId());
		paramMap.put("sessionNumber",doctor.getSessionNumber());
		paramMap.put("docTypeList",docTypeList);
		paramMap.put("graduationYear",doctor.getGraduationYear());
		paramMap.put("baseFlag",baseFlag);
		paramMap.put("schStartDate", doctor.getSchStartDate());
		paramMap.put("schEndDate", doctor.getSchEndDate());
		if("speAdmin".equals(roleId)) {
			paramMap.put("trainingSpeId",currentUser.getResTrainingSpeId());
		}


		PageHelper.startPage(currentPage, getPageSize(request));
		logger.info("--------------开始查询学员出科查询数据--------------：");

		logger.info("出科查询的参数：kzrFlow:{}", JSON.toJSONString(paramMap));
		List<Map<String,Object>> docResultsList = schArrangeResultBiz.searchDocResultsListNew(paramMap);
		//TODO::理论成绩 docResultsList
		model.addAttribute("doctorScore",new HashMap<>());
		if (CollectionUtil.isNotEmpty(docResultsList)) {
			List<String> doctorFlow = docResultsList.stream().map(e -> (String)e.get("doctorFlow")).collect(Collectors.toList());
			Map<String, Map<String, BigDecimal>> doctorScore = schArrangeResultBiz.getScoreByDoctorIds(doctorFlow, doctor.getSchStartDate(), doctor.getSchEndDate());
			model.addAttribute("doctorScore",doctorScore);
		}
		model.addAttribute("datas",datas);
		model.addAttribute("docResultsList",docResultsList);
		return "jsres/hospital/cycleResults2";
	}

	//出科成绩查询
	@RequestMapping("/doctorRecruitResultAcc")
	public String doctorRecruitResultAcc(Model model,Integer currentPage,HttpServletRequest request,ResDoctor doctor,SysUser user,String[] datas,String roleId,String baseFlag){
		SysUser currentUser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(currentUser.getOrgFlow());

		model.addAttribute("org",org);
		List<SysOrg> orgs = new ArrayList<>();
		if (OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
			List<SysOrg> joinOrgs = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
			orgs.addAll(joinOrgs);
			orgs.add(org);
		}
		model.addAttribute("orgs", orgs);
		List<String> jointOrgFlowList=new ArrayList<String>();
		if(StringUtil.isBlank(doctor.getOrgFlow())){
			jointOrgFlowList.add(currentUser.getOrgFlow());
			List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(currentUser.getOrgFlow());
			if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:resJointOrgList){
					jointOrgFlowList.add(jointOrg.getJointOrgFlow());
				}
			}
		}else {
			jointOrgFlowList.add(doctor.getOrgFlow());
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if("kzr".equals(roleId)){
			List<SysUserDept> userDeptList = userBiz.getUserDept(GlobalContext.getCurrentUser());

			if(CollectionUtils.isNotEmpty(userDeptList)){
				paramMap.put("deptList",userDeptList);
			}else{
				paramMap.put("deptFlow",currentUser.getDeptFlow());
			}
			paramMap.put("kzrFlow",currentUser.getUserFlow());
		}else {
			paramMap.put("jointOrgFlowList",jointOrgFlowList);
		}
		paramMap.put("orgFlow",currentUser.getOrgFlow());
		paramMap.put("idNo",user.getIdNo());
		paramMap.put("userName",user.getUserName());
//		paramMap.put("trainingTypeId",TrainCategoryEnum.DoctorTrainingSpe.getId());
		paramMap.put("trainingTypeId",doctor.getTrainingTypeId());
		paramMap.put("trainingSpeId",doctor.getTrainingSpeId());
		paramMap.put("sessionNumber",doctor.getSessionNumber());
		paramMap.put("docTypeList",docTypeList);
		paramMap.put("graduationYear",doctor.getGraduationYear());
		paramMap.put("baseFlag",baseFlag);
		if("speAdmin".equals(roleId)) {
			paramMap.put("trainingSpeId",currentUser.getResTrainingSpeId());
		}
		logger.info("--------------开始查询学员出科查询数据--------------：");

		logger.info("出科查询的参数：kzrFlow:{}", JSON.toJSONString(paramMap));

		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String,Object>> docResultsList = schArrangeResultBiz.searchDocResultsListNew(paramMap);
		model.addAttribute("datas",datas);
		model.addAttribute("docResultsList",docResultsList);
		return "jsres/hospital/cycleResults2Acc";
	}

	//出科异常查询 住院医师
	@RequestMapping("/doctorRecruitErrorResult")
	public String doctorRecruitErrorResult(Model model,Integer currentPage,HttpServletRequest request,ResOutOfficeLock doctor,
										   SysUser user,String[] datas,String baseFlag) throws ParseException {
		SysUser currentUser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(currentUser.getOrgFlow());
//		doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getTypeId());
		model.addAttribute("org",org);
		List<SysOrg> orgs = new ArrayList<>();
		List<String> orgFlows = new ArrayList<>();
		if (OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
			List<SysOrg> joinOrgs = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
			orgs.addAll(joinOrgs);
			orgs.add(org);
		}
		model.addAttribute("orgs", orgs);
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName",user.getUserName());

		for (SysOrg sysOrg : orgs) {
			orgFlows.add(sysOrg.getOrgFlow());
		}
		orgFlows.add(org.getOrgFlow());

		paramMap.put("orgs",orgFlows);
		paramMap.put("doctor",doctor);
		paramMap.put("docTypeList",docTypeList);
		paramMap.put("baseFlag",baseFlag);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResOutOfficeLock> docResultsList = schArrangeResultBiz.searchDocErrorResultsList(paramMap);
		model.addAttribute("datas",datas);
		model.addAttribute("docResultsList",docResultsList);
		return "jsres/hospital/cycleErrorResults";
	}


	//出科异常查询 助理全科
	@RequestMapping("/doctorRecruitErrorResultAcc")
	public String doctorRecruitErrorResultAcc(Model model,Integer currentPage,HttpServletRequest request,ResOutOfficeLock doctor,
											  SysUser user,String[] datas,String baseFlag) throws ParseException {
		SysUser currentUser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(currentUser.getOrgFlow());
		doctor.setTrainingTypeId(TrainCategoryEnum.AssiGeneral.getTypeId());
		model.addAttribute("org",org);
		List<SysOrg> orgs = new ArrayList<>();
		List<String> orgFlows = new ArrayList<>();
		if (OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
			List<SysOrg> joinOrgs = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
			orgs.addAll(joinOrgs);
			orgs.add(org);
		}
		model.addAttribute("orgs", orgs);
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName",user.getUserName());

		for (SysOrg sysOrg : orgs) {
			orgFlows.add(sysOrg.getOrgFlow());
		}
		orgFlows.add(org.getOrgFlow());

		paramMap.put("orgs",orgFlows);
		paramMap.put("doctor",doctor);
		paramMap.put("docTypeList",docTypeList);
		paramMap.put("baseFlag",baseFlag);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResOutOfficeLock> docResultsList = schArrangeResultBiz.searchDocErrorResultsList(paramMap);
		model.addAttribute("datas",datas);
		model.addAttribute("docResultsList",docResultsList);
		return "jsres/hospital/cycleErrorResultsAcc";
	}

	//出科异常详情
	@RequestMapping("/doctorRecruitErrorResultDetail")
	public String doctorRecruitErrorResultDetail(String recordFlow,Model model,String userFlow) throws ParseException {
		ResDoctor resDoctor = resDoctorBiz.readDoctor(userFlow);
		model.addAttribute("doctor",resDoctor);
		SysUser sysUser = userBiz.readSysUser(userFlow);
		model.addAttribute("sysUser",sysUser);
		List<ResDoctorSchProcess> resDoctorSchProcesses = schArrangeResultBiz.searchDocErrorResultInfo(recordFlow, resDoctor.getOrgFlow());
		model.addAttribute("resDoctorSchProcesses",resDoctorSchProcesses);
		return "jsres/hospital/cycleErrorInfo";
	}

	//审核解锁
	@RequestMapping(value="/auditLockRecruitInfo")
	@ResponseBody
	public String auditLockRecruitInfo(String recordFlow,String auditStatusId,String auditStatusName,HttpServletRequest request) throws Exception{
		int count = 0;
		try {
			ResOutOfficeLock resOutOfficeLock = new ResOutOfficeLock();
			resOutOfficeLock.setRecordFlow(recordFlow);
			resOutOfficeLock.setAuditStatusId(auditStatusId);
			resOutOfficeLock.setAuditStatusName(auditStatusName);
			resOutOfficeLock.setModifyTime(DateUtil.getCurrDate());
			count = schArrangeResultBiz.saveAuditLockInfo(resOutOfficeLock);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		if(count>0){
			return GlobalConstant.OPERATE_SUCCESSED;
		}
		return GlobalConstant.OPERATE_FAIL;
	}
	@RequestMapping("/doctorRecruitResultDetail")
	public String doctorRecruitResultDetail(String doctorFlow,Model model,String roleId, String schStartDate, String schEndDate){
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
			model.addAttribute("doctor",doctor);
			model.addAttribute("schStartDate", schStartDate);
			model.addAttribute("schEndDate", schEndDate);
			Map<String,String> pMap = new HashMap<>();
			if("kzr".equals(roleId)){
				SysUser currentUser=GlobalContext.getCurrentUser();
				pMap.put("deptFlow",currentUser.getDeptFlow());
				pMap.put("kzrFlow",currentUser.getUserFlow());
				pMap.put("doctorFlow",doctorFlow);
			}else {
				pMap.put("doctorFlow",doctorFlow);
			}
			pMap.put("schStartDate", schStartDate);
			pMap.put("schEndDate", schEndDate);
			List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByMap(pMap);
			model.addAttribute("arrResultList",arrResultList);
			Map<String, SchRotationDept> deptMap = new HashMap<String, SchRotationDept>();
			Map<String,Object> resultMap = new HashMap<>();
			Map<String,Object> skillMap = new HashMap<>();
			Map<String,String> DOPSFlowMap = new HashMap<>();
			Map<String,String> MiniFlowMap = new HashMap<>();
			Map<String,String> AfterFlowMap = new HashMap<>();
			Map<String,String> AfterSummFlowMap = new HashMap<>();
			String killScore = "0";
			String thryScore = "0";
			List<String> processList = new ArrayList<>();
			if(arrResultList!=null&&arrResultList.size()>0){
				for(SchArrangeResult schArrangeResult:arrResultList){
					String resultFlow = schArrangeResult.getResultFlow();

					String standardDeptId = schArrangeResult.getStandardDeptId();
					String standarGroupFlow = schArrangeResult.getStandardGroupFlow();
					SchRotationDept schRotationDept = deptMap.get(standarGroupFlow + standardDeptId);
					if (schRotationDept == null)
						schRotationDept = schRotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(standarGroupFlow, standardDeptId);
					deptMap.put(standarGroupFlow + standardDeptId, schRotationDept);
					ResDoctorSchProcess doctorSchProcess = resDoctorProcessBiz.searchByResultFlow(resultFlow);
					resultMap.put(resultFlow,doctorSchProcess);
					if(doctorSchProcess!=null){
						String processFlow = doctorSchProcess.getProcessFlow();
						processList.add(processFlow);
						List<ResSchProcessExpress> resRecs = expressBiz.searchByProcessFlowClob(processFlow);
						if(resRecs!=null&&resRecs.size()>0)
						{
							for(ResSchProcessExpress r:resRecs)
							{
								if(AfterRecTypeEnum.DOPS.getId().equals(r.getRecTypeId()))
								{
									DOPSFlowMap.put(processFlow,r.getRecFlow());
								}
								if(AfterRecTypeEnum.Mini_CEX.getId().equals(r.getRecTypeId()))
								{
									MiniFlowMap.put(processFlow,r.getRecFlow());
								}
								if(AfterRecTypeEnum.AfterSummary.getId().equals(r.getRecTypeId()))
								{
									AfterSummFlowMap.put(processFlow,r.getRecFlow());
								}
								if(AfterRecTypeEnum.AfterEvaluation.getId().equals(r.getRecTypeId()))
								{
									AfterFlowMap.put(processFlow,r.getRecFlow());
									String recContent = r.getRecContent();
									skillMap.put(processFlow, resRecBiz.parseRecContent(recContent));
								}
							}
						}
					}
				}
			}
			//处理平均分
			BigDecimal lilun = new BigDecimal("0");
			int lilunCount = 0;
			BigDecimal jineng = new BigDecimal("0");
			int jinengCount = 0;
			if (CollectionUtil.isNotEmpty(skillMap)) {
				for (String processFlow : skillMap.keySet()) {
					try {
						Map<String, Object> content = (Map<String,Object>)skillMap.get(processFlow);
						String ll = (String)content.get("theoreResult");
						String jn = (String)content.get("score");
						if (StringUtils.isNotEmpty(ll)) {
							lilun = lilun.add(new BigDecimal(ll));
							lilunCount ++;
						}
						if (StringUtils.isNotEmpty(jn)) {
							jineng = jineng.add(new BigDecimal(jn));
							jinengCount++;
						}

					}catch (Exception e) {

					}
				}
			}

			if(CollectionUtils.isNotEmpty(processList)) {
				List<String> noneTheoryScoreList = new ArrayList<>();
				for (String processFlow : processList) {
					if(!skillMap.containsKey(processFlow)) {
						noneTheoryScoreList.add(processFlow);
					}
				}
				if(CollectionUtils.isNotEmpty(noneTheoryScoreList)) {
					ResScoreExample scoreExample = new ResScoreExample();
					scoreExample.createCriteria().andRecordStatusEqualTo("Y").andProcessFlowIn(noneTheoryScoreList);
					List<ResScore> scoreList = scoreMapper.selectByExample(scoreExample);
					if (CollectionUtil.isNotEmpty(scoreList)) {
						for (ResScore resScore : scoreList) {
							if (resScore.getTheoryScore() != null) {
								lilun = lilun.add(resScore.getTheoryScore());
								lilunCount++;
							}
						}
					}
				}
			}
			model.addAttribute("resultMap",resultMap);
			model.addAttribute("skillMap",skillMap);
			model.addAttribute("DOPSFlowMap",DOPSFlowMap);
			model.addAttribute("MiniFlowMap",MiniFlowMap);
			model.addAttribute("AfterFlowMap",AfterFlowMap);
			model.addAttribute("AfterSummFlowMap",AfterSummFlowMap);
			model.addAttribute("deptMap",deptMap);
			model.addAttribute("thrAvaScore",lilunCount>0? lilun.divide(new BigDecimal(lilunCount),2,BigDecimal.ROUND_HALF_DOWN):0);
			model.addAttribute("killAvaScore",jinengCount>0? jineng.divide(new BigDecimal(jinengCount),2,BigDecimal.ROUND_HALF_DOWN):0);
		}
		return "jsres/hospital/cycleDetails2";
	}

	@RequestMapping(value = "/exportCycleResults")
	public void exportAttendanceTab(ResDoctor doctor,String[] datas,SysUser user,String roleId, String schStart, String schEnd,HttpServletResponse response)throws Exception{
		SysUser currentUser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(currentUser.getOrgFlow());
		List<SysOrg> orgs = new ArrayList<>();
		if (OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
			List<SysOrg> joinOrgs = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
			orgs.addAll(joinOrgs);
			orgs.add(org);
		}
		List<String> jointOrgFlowList=new ArrayList<String>();
		if(StringUtil.isBlank(doctor.getOrgFlow())){
			jointOrgFlowList.add(currentUser.getOrgFlow());
			List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(currentUser.getOrgFlow());
			if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:resJointOrgList){
					jointOrgFlowList.add(jointOrg.getJointOrgFlow());
				}
			}
		}else {
			jointOrgFlowList.add(doctor.getOrgFlow());
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
//        String schStartDate = "";
//        String schEndDate = "";
//        if(StringUtil.isNotBlank(sycleMonth)){
//            String lastDay = DateUtil.getLastDateOfMonth(sycleMonth);
//            schStartDate = sycleMonth + "-01";
//            schEndDate = sycleMonth + "-" + lastDay;
//        }
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if("kzr".equals(roleId)){
//			paramMap.put("deptFlow",currentUser.getDeptFlow());
			List<SysUserDept> userDeptList = userBiz.getUserDept(GlobalContext.getCurrentUser());
			if(CollectionUtils.isNotEmpty(userDeptList)){
				paramMap.put("deptList",userDeptList);
			}else{
				paramMap.put("deptFlow",currentUser.getDeptFlow());
			}
			paramMap.put("kzrFlow",currentUser.getUserFlow());
		}else {
			paramMap.put("jointOrgFlowList",jointOrgFlowList);
		}
		paramMap.put("userName",user.getUserName());
		paramMap.put("trainingTypeId",doctor.getTrainingTypeId());
		paramMap.put("trainingSpeId",doctor.getTrainingSpeId());
		paramMap.put("sessionNumber",doctor.getSessionNumber());
		paramMap.put("docTypeList",docTypeList);
		paramMap.put("graduationYear",doctor.getGraduationYear());
		paramMap.put("schStartDate",schStart);
		paramMap.put("schEndDate",schEnd);
		if("speAdmin".equals(roleId)){
			paramMap.put("trainingSpeId",currentUser.getResTrainingSpeId());
		}
		List<Map<String,String>> docResultsList = schArrangeResultBiz.searchDocResultsByMonth(paramMap);
		HSSFWorkbook wb = createCycleResultsWb(docResultsList, roleId,schStart,schEnd);
		String fileName = "学员出科记录表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}
	@RequestMapping(value = "/exportCycleResults2")
	public void exportCycleResults2(ResDoctor doctor,String[] datas,SysUser user,String roleId, String schStart,String schEnd,HttpServletResponse response)throws Exception{
		SysUser currentUser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(currentUser.getOrgFlow());
		List<SysOrg> orgs = new ArrayList<>();
		if (OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
			List<SysOrg> joinOrgs = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
			orgs.addAll(joinOrgs);
			orgs.add(org);
		}
		List<String> jointOrgFlowList=new ArrayList<String>();
		if(StringUtil.isBlank(doctor.getOrgFlow())){
			jointOrgFlowList.add(currentUser.getOrgFlow());
			List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(currentUser.getOrgFlow());
			if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:resJointOrgList){
					jointOrgFlowList.add(jointOrg.getJointOrgFlow());
				}
			}
		}else {
			jointOrgFlowList.add(doctor.getOrgFlow());
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		String schStartDate = "";
		String schEndDate = "";
		String lastDay = DateUtil.getLastDateOfMonth(schEnd);
		schStartDate = schStart + "-01";
		schEndDate = schEnd + "-" + lastDay;

		Map<String,Object> paramMap = new HashMap<String, Object>();
		if("kzr".equals(roleId)){
			paramMap.put("deptFlow",currentUser.getDeptFlow());
			paramMap.put("kzrFlow",currentUser.getUserFlow());
		}else {
			paramMap.put("jointOrgFlowList",jointOrgFlowList);
		}
		paramMap.put("userName",user.getUserName());
		paramMap.put("trainingTypeId",doctor.getTrainingTypeId());
		paramMap.put("trainingSpeId",doctor.getTrainingSpeId());
		paramMap.put("sessionNumber",doctor.getSessionNumber());
		paramMap.put("docTypeList",docTypeList);
		paramMap.put("graduationYear",doctor.getGraduationYear());
		paramMap.put("schStartDate",schStartDate);
		paramMap.put("schEndDate",schEndDate);
		List<Map<String,String>> docResultsList = schArrangeResultBiz.searchDocResultsByMonth(paramMap);
		HSSFWorkbook wb = createCycleResultsWb(docResultsList, roleId,schStartDate,schEndDate);
		String fileName = "学员出科记录表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	/**
	 * 学员出科查询 HSSFWorkbook
	 */
	private HSSFWorkbook createCycleResultsWb(List<Map<String,String>> docResultsList, String roleId,String schStartDate,String schEndDate ){
		HSSFWorkbook wb = new HSSFWorkbook();
		if(docResultsList != null) {
			// 为工作簿添加sheet
			HSSFSheet sheet = wb.createSheet("sheet1");
			//定义将用到的样式
			HSSFCellStyle styleCenter = wb.createCellStyle();
			//居中
			styleCenter.setAlignment(HorizontalAlignment.CENTER);
			styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
			// 设置边框
			styleCenter.setBorderBottom(BorderStyle.THIN);
			styleCenter.setBorderTop(BorderStyle.THIN);
			styleCenter.setBorderRight(BorderStyle.THIN);
			styleCenter.setBorderLeft(BorderStyle.THIN);
			//第一行 列宽自适应
			HSSFRow rowDep = sheet.createRow(0);
			//合并单元格
			CellRangeAddress cellRowOne = new CellRangeAddress(0, 0, 0, 37);
			sheet.addMergedRegion(cellRowOne);
			// 给合并单元格设置边框
			excelSetBorderForMergeCell(wb, sheet, cellRowOne);
			HSSFCell cellOne = rowDep.createCell(0);
			cellOne.setCellStyle(styleCenter);
			cellOne.setCellValue("学员出科记录表");
			//第二行
			HSSFRow rowTwo = sheet.createRow(1);
			//合并单元格
			CellRangeAddress cellRowTwo = new CellRangeAddress(1, 1, 11, 25);
			sheet.addMergedRegion(cellRowTwo);
			// 给合并单元格设置边框
			excelSetBorderForMergeCell(wb, sheet, cellRowOne);
			HSSFCell secCellZero = rowTwo.createCell(11);
			secCellZero.setCellStyle(styleCenter);
			secCellZero.setCellValue("DOPS");
			//合并单元格
			CellRangeAddress cellFiveRowTwo = new CellRangeAddress(1, 1, 26, 36);
			sheet.addMergedRegion(cellFiveRowTwo);
			// 给合并单元格设置边框
			excelSetBorderForMergeCell(wb, sheet, cellRowOne);
			HSSFCell cellFive = rowTwo.createCell(26);
			cellFive.setCellStyle(styleCenter);
			cellFive.setCellValue("Mini-Cex");
			//第三行
			HSSFRow rowThree = sheet.createRow(2);
			String[] titles = new String[]{
					"姓名","基地","人员类型","专业","年级","轮转科室","轮转时间","带教老师","理论成绩","技能名称","技能成绩",
					"操作例数","复杂程度","1","2","3","4","5","6","7","8","9","10","11",
					"满意度","评语","严重情况","诊治重点","1","2","3","4","5","6","7",
					"满意度","评语","出科考核表材料" };
			HSSFCell cellTitle = null;
			for (int i = 0; i < titles.length; i++) {
				if (i < 11 || i == 37){
					// 合并单元格
					CellRangeAddress cellRangePlanNo = new CellRangeAddress(1, 2, i, i);
					sheet.addMergedRegion(cellRangePlanNo);
					cellTitle = rowTwo.createCell(i);
					// 给合并单元格设置边框
					excelSetBorderForMergeCell(wb, sheet, cellRangePlanNo);
				} else{
					cellTitle = rowThree.createCell(i);
				}
				cellTitle.setCellValue(titles[i]);
				cellTitle.setCellStyle(styleCenter);
				sheet.setColumnWidth(i, titles.length * 1 * 156);
			}
			int rowNum = 3;
			String[] resultList = null;
			if (docResultsList != null && !docResultsList.isEmpty()) {
				for (int i = 0; i < docResultsList.size(); i++) {
					String doctorFlow = docResultsList.get(i).get("doctorFlow");
					Map<String, String> pMap = new HashMap<>();
					pMap.put("doctorFlow", doctorFlow);
					pMap.put("schStartDate", schStartDate);
					pMap.put("schEndDate", schEndDate);
					if ("kzr".equals(roleId)) {
						SysUser currentUser=GlobalContext.getCurrentUser();
						pMap.put("deptFlow", currentUser.getDeptFlow());
					}
					List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByDoctorAndDate(pMap);
					Map<String, SchRotationDept> deptMap = new HashMap<String, SchRotationDept>();
					Map<String, ResDoctorSchProcess> resultMap = new HashMap<>();
					Map<String, Map<String, Object>> skillMap = new HashMap<>();
					Map<String, String> DOPSFlowMap = new HashMap<>();
					Map<String,Object> dopsInfoMap = new HashMap<>();
					Map<String,String> MiniFlowMap = new HashMap<>();
					Map<String,Object> miniInfoMap = new HashMap<>();
					Map<String, String> AfterFlowMap = new HashMap<>();
					Map<String, String> AfterSummFlowMap = new HashMap<>();
					if (arrResultList != null && arrResultList.size() > 0) {
						for (SchArrangeResult schArrangeResult : arrResultList) {
							String resultFlow = schArrangeResult.getResultFlow();

							String standardDeptId = schArrangeResult.getStandardDeptId();
							String standarGroupFlow = schArrangeResult.getStandardGroupFlow();
							SchRotationDept schRotationDept = deptMap.get(standarGroupFlow + standardDeptId);
							if (schRotationDept == null) {
								schRotationDept = schRotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(standarGroupFlow, standardDeptId);
							}
							deptMap.put(standarGroupFlow + standardDeptId, schRotationDept);

							ResDoctorSchProcess doctorSchProcess = resDoctorProcessBiz.searchByResultFlow(resultFlow);
							resultMap.put(resultFlow, doctorSchProcess);
							if (doctorSchProcess != null) {
								String processFlow = doctorSchProcess.getProcessFlow();
								List<ResSchProcessExpress> resRecs = expressBiz.searchByProcessFlowClob(processFlow);
								if (resRecs != null && resRecs.size() > 0) {
									for (ResSchProcessExpress r : resRecs) {
										if (AfterRecTypeEnum.DOPS.getId().equals(r.getRecTypeId())) {
											DOPSFlowMap.put(processFlow, r.getRecFlow());
											String recContent = r.getRecContent();
											dopsInfoMap.put(processFlow, resRecBiz.parseRecContent(recContent));
										}
										if (AfterRecTypeEnum.Mini_CEX.getId().equals(r.getRecTypeId())) {
											MiniFlowMap.put(processFlow, r.getRecFlow());
											String recContent = r.getRecContent();
											miniInfoMap.put(processFlow, resRecBiz.parseRecContent(recContent));
										}
										if (AfterRecTypeEnum.AfterSummary.getId().equals(r.getRecTypeId())) {
											AfterSummFlowMap.put(processFlow, r.getRecFlow());
										}
										if (AfterRecTypeEnum.AfterEvaluation.getId().equals(r.getRecTypeId())) {
											AfterFlowMap.put(processFlow, r.getRecFlow());
											String recContent = r.getRecContent();
											skillMap.put(processFlow, resRecBiz.parseRecContent(recContent));
										}
									}
								}
							}
						}
					}
					if (arrResultList != null && arrResultList.size() > 0) {
						String theoryScore = "";
						String skillName = "";
						String skillScore = "";
						String haveAfterPic = "";
						for (int j = 0; j < arrResultList.size(); j++) {
							theoryScore = "";
							skillName = "";
							skillScore = "";
							SchArrangeResult schArrangeResult = arrResultList.get(j);
							if(null == schArrangeResult || StringUtil.isBlank(schArrangeResult.getResultFlow())){
								continue;
							}
							ResDoctorSchProcess schProcess = resultMap.get(schArrangeResult.getResultFlow());
							if(null == schProcess){
								continue;
							}
							String key = resultMap.get(schArrangeResult.getResultFlow()).getProcessFlow();
							if(StringUtil.isBlank(key)){
								continue;
							}
							Map<String, Object> obj1 = skillMap.get(key);
							Map<String, Object> dopsInfo = (Map<String, Object>) dopsInfoMap.get(key);
							Map<String, Object> miniInfo = (Map<String, Object>) miniInfoMap.get(key);
							if (obj1 != null) {
								if (obj1.get("theoreResult") != null) {
									theoryScore = obj1.get("theoreResult").toString();
								} else {
									if (resultMap.get(schArrangeResult.getResultFlow()).getSchScore() != null) {
										theoryScore = resultMap.get(schArrangeResult.getResultFlow()).getSchScore().toString();
									}
								}
								if (obj1.get("skillName") != null) {
									skillName = obj1.get("skillName").toString();
								}
								if (obj1.get("score") != null) {
									skillScore = obj1.get("score").toString();
								}
							}
							if (GlobalConstant.FLAG_Y.equals(schArrangeResult.getHaveAfterPic())) {
								haveAfterPic = "已上传";
							} else {
								haveAfterPic = "未上传";
							}
							HSSFRow rowFour = sheet.createRow(rowNum);
							resultList = new String[]{
									docResultsList.get(i).get("userName"),
									docResultsList.get(i).get("orgNameAll"),
									docResultsList.get(i).get("doctorTypeName"),
									docResultsList.get(i).get("trainingSpeName"),
									docResultsList.get(i).get("sessionNumber"),
									schArrangeResult.getSchDeptName(),
									schArrangeResult.getSchStartDate() + "~" + schArrangeResult.getSchEndDate(),
									resultMap.get(schArrangeResult.getResultFlow()).getTeacherUserName(),
									theoryScore,
									skillName,
									skillScore,
									(null == dopsInfo || null == dopsInfo.get("studentSkillNum")) ? "" : dopsInfo.get("studentSkillNum").toString(),
									(null == dopsInfo || null == dopsInfo.get("skillComplexDegree")) ? "" : dopsInfo.get("skillComplexDegree").toString(),
									(null == dopsInfo || null == dopsInfo.get("skillLevel")) ? "" : dopsInfo.get("skillLevel").toString(),
									(null == dopsInfo || null == dopsInfo.get("consentForm")) ? "" : dopsInfo.get("consentForm").toString(),
									(null == dopsInfo || null == dopsInfo.get("readyToWork")) ? "" : dopsInfo.get("readyToWork").toString(),
									(null == dopsInfo || null == dopsInfo.get("painAndStabilization")) ? "" : dopsInfo.get("painAndStabilization").toString(),
									(null == dopsInfo || null == dopsInfo.get("SkillAbility")) ? "" : dopsInfo.get("SkillAbility").toString(),
									(null == dopsInfo || null == dopsInfo.get("asepticTechnique")) ? "" : dopsInfo.get("asepticTechnique").toString(),
									(null == dopsInfo || null == dopsInfo.get("seekAssistance")) ? "" : dopsInfo.get("seekAssistance").toString(),
									(null == dopsInfo || null == dopsInfo.get("relatedDisposal")) ? "" : dopsInfo.get("relatedDisposal").toString(),
									(null == dopsInfo || null == dopsInfo.get("communicationSkills")) ? "" : dopsInfo.get("communicationSkills").toString(),
									(null == dopsInfo || null == dopsInfo.get("feelProfessionalDegree")) ? "" : dopsInfo.get("feelProfessionalDegree").toString(),
									(null == dopsInfo || null == dopsInfo.get("overallPerformance")) ? "" : dopsInfo.get("overallPerformance").toString(),
									(null == dopsInfo || null == dopsInfo.get("degreeSatisfaction")) ? "" : dopsInfo.get("degreeSatisfaction").toString(),
									(null == dopsInfo || null == dopsInfo.get("teacherComment")) ? "" : dopsInfo.get("teacherComment").toString(),
									(null == miniInfo || null == miniInfo.get("severity")) ? "" : miniInfo.get("severity").toString(),
									(null == miniInfo || null == miniInfo.get("diagnosisKeynote")) ? "" : miniInfo.get("diagnosisKeynote").toString(),
									(null == miniInfo || null == miniInfo.get("medicalInterview")) ? "" : miniInfo.get("medicalInterview").toString(),
									(null == miniInfo || null == miniInfo.get("physicalExamination")) ? "" : miniInfo.get("physicalExamination").toString(),
									(null == miniInfo || null == miniInfo.get("humanisticCare")) ? "" : miniInfo.get("humanisticCare").toString(),
									(null == miniInfo || null == miniInfo.get("clinicalJudgment")) ? "" : miniInfo.get("clinicalJudgment").toString(),
									(null == miniInfo || null == miniInfo.get("communicationSkills")) ? "" : miniInfo.get("communicationSkills").toString(),
									(null == miniInfo || null == miniInfo.get("organization")) ? "" : miniInfo.get("organization").toString(),
									(null == miniInfo || null == miniInfo.get("holisticCare")) ? "" : miniInfo.get("holisticCare").toString(),
									(null == miniInfo || null == miniInfo.get("degreeSatisfaction")) ? "" : miniInfo.get("degreeSatisfaction").toString(),
									(null == miniInfo || null == miniInfo.get("teacherComment")) ? "" : miniInfo.get("teacherComment").toString(),
									haveAfterPic
							};
							for (int k = 0; k < titles.length; k++) {
								HSSFCell cellFirst = rowFour.createCell(k);
								cellFirst.setCellStyle(styleCenter);
								cellFirst.setCellValue(resultList[k]);
							}
							rowNum++;
						}
					}
				}
			}
		}
		return wb;
	}

	/**
	 * @Department：研发部
	 * @Description 给合并的单元格设置边框
	 * @Author fengxf
	 * @Date 2019/11/12
	 */
	private void excelSetBorderForMergeCell(HSSFWorkbook wb, HSSFSheet sheet, CellRangeAddress cellRangePlanNo){
		// 下边框
		RegionUtil.setBorderBottom(BorderStyle.THIN, cellRangePlanNo, sheet);
		// 左边框
		RegionUtil.setBorderLeft(BorderStyle.THIN, cellRangePlanNo, sheet);
		// 右边框
		RegionUtil.setBorderRight(BorderStyle.THIN, cellRangePlanNo, sheet);
		// 上边框
		RegionUtil.setBorderTop(BorderStyle.THIN, cellRangePlanNo, sheet);
	}

	@RequestMapping(value = "/exportCycleResultsByDoc")
	public void exportCycleResultsByDoc(String doctorFlow,HttpServletResponse response,String roleId, String schStartDate, String schEndDate)throws Exception{
		HSSFWorkbook wb = jsResDoctorRecruitBiz.createCycleResultsByDoc(doctorFlow, roleId, schStartDate, schEndDate);
		String fileName = "学员出科记录表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	@RequestMapping(value = "/exportCycleResultsAndPapers")
	public void exportCycleResultsAndPapers(ResDoctor doctor,String[] datas,SysUser user,String roleId,String schStart,String schEnd,HttpServletResponse response) throws IOException {
		SysUser currentUser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(currentUser.getOrgFlow());
		List<SysOrg> orgs = new ArrayList<>();
		if (OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
			List<SysOrg> joinOrgs = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
			orgs.addAll(joinOrgs);
			orgs.add(org);
		}
		List<String> jointOrgFlowList=new ArrayList<String>();
		if(StringUtil.isBlank(doctor.getOrgFlow())){
			jointOrgFlowList.add(currentUser.getOrgFlow());
			List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(currentUser.getOrgFlow());
			if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:resJointOrgList){
					jointOrgFlowList.add(jointOrg.getJointOrgFlow());
				}
			}
		}else {
			jointOrgFlowList.add(doctor.getOrgFlow());
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
//        String schStartDate = "";
//        String schEndDate = "";
//        if(StringUtil.isNotBlank(sycleMonth)){
//            String lastDay = DateUtil.getLastDateOfMonth(sycleMonth);
//            schStartDate = sycleMonth + "-01";
//            schEndDate = sycleMonth + "-" + lastDay;
//        }
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if("kzr".equals(roleId)){
			paramMap.put("deptFlow",currentUser.getDeptFlow());
			paramMap.put("kzrFlow",currentUser.getUserFlow());
		}else {
			paramMap.put("jointOrgFlowList",jointOrgFlowList);
		}
		paramMap.put("userName",user.getUserName());
		paramMap.put("trainingTypeId",doctor.getTrainingTypeId());
		paramMap.put("trainingSpeId",doctor.getTrainingSpeId());
		paramMap.put("sessionNumber",doctor.getSessionNumber());
		paramMap.put("docTypeList",docTypeList);
		paramMap.put("graduationYear",doctor.getGraduationYear());
		paramMap.put("schStartDate",schStart);
		paramMap.put("schEndDate",schEnd);
		List<Map<String,String>> docResultsList = schArrangeResultBiz.searchDocResultsByMonth(paramMap);
		//1.获取临时文件夹
		String tempFolder = System.getProperty("java.io.tmpdir") + File.separator + PkUtil.getUUID();
		String[] resultList = null;
		if (docResultsList != null && !docResultsList.isEmpty()) {
			for (int i = 0; i < docResultsList.size(); i++) {
				String doctorFlow = docResultsList.get(i).get("doctorFlow");
				String userName = docResultsList.get(i).get("userName");
				if(StringUtil.isBlank(doctorFlow)) return;
				Map<String, String> pMap = new HashMap<>();
				if ("kzr".equals(roleId)) {
					pMap.put("deptFlow", currentUser.getDeptFlow());
					pMap.put("kzrFlow", currentUser.getUserFlow());
					pMap.put("doctorFlow", doctorFlow);
				} else {
					pMap.put("doctorFlow", doctorFlow);
				}
				pMap.put("schStartDate",schStart);
				pMap.put("schEndDate",schEnd);
				List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByMap(pMap);
				//2.获取试卷下载列表
				Map<String,String> downloadUrls = getDownloadUrls(arrResultList);
				//3.根据下载链接，下载试卷放入临时文件夹
				String dest = tempFolder + File.separator +"papers"+File.separator + userName;
				downloadPapersByUrl(downloadUrls, dest);
			}
		}

		//4.生成成绩excel放入临时文件夹
		String pdest = tempFolder + File.separator +"papers";
		createScoreExcel(docResultsList, roleId,schStart,schEnd,pdest);
		//5.压缩文件夹
		File directory =new File(tempFolder + File.separator +"papers");
		File zipFlie =new File(tempFolder+ File.separator + schStart + "~" + schEnd +".zip");
		String zipFolderName = "";
		ZipUtil.makeDirectoryToZip(directory,zipFlie,zipFolderName,7);

		//6.输出
		BufferedInputStream bis =  new BufferedInputStream(new FileInputStream(zipFlie));
		byte[] data= new byte[bis.available()];
		int len = 2048;
		byte[] b = new byte[len];
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(zipFlie.getName().getBytes("gbk"),"ISO8859-1" ) + "\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream;charset=UTF-8");
		OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
		while ((len = bis.read(b)) != -1)
		{
			outputStream.write(b, 0, len);
		}
		bis.close();
		outputStream.flush();
		outputStream.close();




	}
	private void createScoreExcel(List<Map<String,String>> docResultsList, String roleId,String schStartDate,String schEndDate , String dest) throws UnsupportedEncodingException {
		HSSFWorkbook wb = createCycleResultsWb(docResultsList, roleId,schStartDate,schEndDate);
		FileOutputStream fos = null;
		String fileName = "学员出科记录表.xls";
		try {
			File file= new File(dest + File.separator + fileName);
			File fileParent = file.getParentFile();
			if (!fileParent.exists()) {
				fileParent.mkdirs();
			}
			file.createNewFile();
			fos = new FileOutputStream(file);
			wb.write(fos);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 根据url列表下载试卷到指定文件夹
	 * @param urlMap
	 * @param dest
	 * @return
	 */
	private void downloadPapersByUrl(Map<String,String> urlMap, String dest) {
		URL urlfile = null;
		HttpURLConnection httpUrl = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			for (String url : urlMap.keySet()) {
				String fileName = urlMap.get(url);
				urlfile = new URL(url);
				httpUrl = (HttpURLConnection) urlfile.openConnection();
				httpUrl.connect();
				bis = new BufferedInputStream(httpUrl.getInputStream());
				file = new File(dest + File.separator + fileName);
				File fileParent = file.getParentFile();
				if (!fileParent.exists()) {
					fileParent.mkdirs();
				}
				file.createNewFile();
				fos = new FileOutputStream(file);
				int len = 2048;
				byte[] b = new byte[len];
				while ((len = bis.read(b)) != -1) {
					fos.write(b, 0, len);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
					fos.close();
				}
				if(httpUrl !=null){
					httpUrl.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取下载链接列表
	 * @param arrResultList
	 * @return
	 */
	private Map<String,String> getDownloadUrls(  List<SchArrangeResult>  arrResultList ) {
		String downloadUrl = InitConfig.getSysCfg("res_after_exam_download_url");
		Map<String, String> urlMap = new HashMap<String, String>();

		String url = "";
		String fileName = "";
		URL urlfile = null;
		HttpURLConnection httpUrl = null;
		BufferedInputStream bis = null;
		try {
			for(SchArrangeResult schArrangeResult:arrResultList){
				String resultFlow = schArrangeResult.getResultFlow();
				ResDoctorSchProcess process = processBiz.searchByResultFlow(resultFlow);
				if (process == null) continue;// 轮转记录不存在
				String processFlow = process.getProcessFlow();
				ResScore score = scoreBiz.getScoreByProcess(processFlow);
				if (score == null || score.getTheoryScore() == null) continue;//该学员还未参加出科考试
				ExamResults result = examResultsBiz.getByProcessFlowAndScore(processFlow, score.getTheoryScore());
				if (result == null) continue;//该学员还未参加出科考试
				List<ResSchProcessExpress> resRecs = expressBiz.searchByProcessFlowAndRecTypeIdClob(processFlow, AfterRecTypeEnum.AfterEvaluation.getId());
				if (resRecs == null || resRecs.size()==0) continue;//该学员带教还未审核出科考核表
				String dowland = "";
				if (result != null && StringUtil.isNotBlank(result.getWordUrl())) {
					dowland = result.getWordUrl();
				}
				SysUser user = userBiz.readSysUser(process.getUserFlow());
				if (StringUtil.isBlank(dowland)) {
					if (result == null || StringUtil.isBlank(result.getResultsId())) continue;//该考试无试卷信息
					url = downloadUrl + "/api/examresultword.ashx?Action=ExamResultWord&CardID=" + user.getUserCode() + "&ResultID=" + result.getResultsId();
					urlfile = new URL(url);
					httpUrl = (HttpURLConnection) urlfile.openConnection();
					httpUrl.setRequestProperty("Accept-Charset", "utf-8");
					httpUrl.setRequestProperty("contentType", "utf-8");
					httpUrl.connect();
					bis = new BufferedInputStream(httpUrl.getInputStream());
					int len = 2048;
					byte[] b = new byte[len];
					while ((len = bis.read(b)) != -1) {
						dowland += new String(b, "UTF-8").trim();
					}
					if(StringUtil.isNotBlank(dowland))
					{
						dowland=java.net.URLDecoder.decode(dowland, "UTF-8");
					}
					url = downloadUrl + "/" + dowland;
					fileName = user.getUserName() +"_"+ process.getDeptName() +"_"+ process.getSchStartDate()+"_"+process.getSchEndDate()+".doc";
					urlMap.put(url, fileName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if(httpUrl !=null){
					httpUrl.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return urlMap;
	}
	private void setColumnWidth(int length, int key, Map<Integer, Integer> columnWidth) {
		if(columnWidth.containsKey(key)) {
			Integer ol = columnWidth.get(key);
			if(ol<length)
				columnWidth.put(key,length);
		}else{
			columnWidth.put(key,length);
		}
	}

	/*
	 *医师出科成绩查询
	 */
	@RequestMapping(value="/cycleResults")
	public String cycleResults(String trainingSpeId,String sessionNumber,String userName,Model model,HttpServletRequest request,Integer currentPage,
							   String startDate,String endDate){
		if(!StringUtil.isNotBlank(startDate)){
			endDate = DateUtil.getCurrDate();
			startDate = DateUtil.newDateOfAddMonths(endDate,-6);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}
		List<String> titleDate=new ArrayList<>();
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			String startMonth = startDate.substring(0, 7);
			String endMonth = endDate.substring(0, 7);
			titleDate = getMonthsByTwoMonth(startMonth, endMonth);
			model.addAttribute("titleDate", titleDate);
		}
		PageHelper.startPage(currentPage, getPageSize(request));//分页,必须放在分页sql前
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
		if(startDate!=null){
			paramMap.put("startDate",startDate);
		}
		if(endDate!=null){
			paramMap.put("endDate",endDate);
		}
		List<Map<String,Object>> docResultsList = schArrangeResultBiz.searchDocResultsList(paramMap);
		model.addAttribute("docResultsList",docResultsList);
		Map<String,List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();

		List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchCycleArrangeResults(paramMap);
		for (SchArrangeResult sar : arrResultList) {
			String doctorFlow = sar.getDoctorFlow();
			String resultStartDate = sar.getSchStartDate();
			String resultEndDate = sar.getSchEndDate();
			if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
				resultStartDate = resultStartDate.substring(0,7);
				resultEndDate = resultEndDate.substring(0,7);
				for(String title:titleDate)
				{
					if(title.compareTo(resultStartDate)>=0&&title.compareTo(resultEndDate)<=0)
					{
						String key = doctorFlow + title;
						List<SchArrangeResult> sarList = resultListMap.get(key);
						if (sarList == null) {
							sarList = new ArrayList<SchArrangeResult>();
							resultListMap.put(key, sarList);
						}
						sarList.add(sar);
					}
				}
//					List<String> months = getMonthsByTwoMonth(resultStartDate, resultEndDate);
//					if (months != null) {
//						for (String month : months) {
//							String key = doctorFlow + month;
//							List<SchArrangeResult> sarList = resultListMap.get(key);
//							if (sarList == null) {
//								sarList = new ArrayList<SchArrangeResult>();
//								resultListMap.put(key, sarList);
//							}
//							sarList.add(sar);
//						}
//					}
			}
		}
		model.addAttribute("resultListMap",resultListMap);

		return "jsres/hospital/cycleResults";
	}
	/**
	 * 查询培训信息，上传培训记录，添加学习计划
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/trainInfo")
	public String trainInfo(Model model,SysUser user,ResDoctorRecruit doctorRecruit){
		return  "jsres/hospital/doctor/trainList";
	}
	@RequestMapping(value="/refreshCount")
	public String refreshCount(Model model){
		int passCount=0;
		int noStudyCount=0;
		int uploadCount=0;
		SysUser currUser=GlobalContext.getCurrentUser();
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)){
			recruit.setOrgFlow(currUser.getOrgFlow());
		}
		//查询本基地共有多少的培训记录
		passCount=jsResDoctorRecruitBiz.searchDoctorNum(recruit);
		//查询本基地没有学习计划的人数
		ResDoctorRecruitWithBLOBs recruitWb=new ResDoctorRecruitWithBLOBs();
		if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)){
			recruitWb.setOrgFlow(currUser.getOrgFlow());
		}
		recruitWb.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		noStudyCount=jsResDoctorRecruitBiz.searchCountByCondition(recruitWb, GlobalConstant.FLAG_Y);
		//查询本基地的需要上传减免证明的人数
//		recruitWb.setCatSpeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
		recruitWb.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
		uploadCount=jsResDoctorRecruitBiz.searchCountByCondition(recruitWb, GlobalConstant.FLAG_N);
		//查询所有符合条件的人
		model.addAttribute("passCount", passCount);
		model.addAttribute("noStudyCount", noStudyCount);
		model.addAttribute("uploadCount", uploadCount);
		return  "jsres/hospital/doctor/count";
	}
	/**
	 * 查询培训记录
	 * @param model
	 * @param user
	 * @param doctorRecruit
	 * @return
	 */
	@RequestMapping(value="/trainDocInfo")
	public String trainDocInfo(Model model,SysUser user,ResDoctorRecruit doctorRecruit,String derateFlag, HttpServletRequest request,Integer currentPage,String jointOrgFlag){
		SysUser currUser=GlobalContext.getCurrentUser();
		List<String>jointOrgFlowList =new ArrayList<String>();
		doctorRecruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)){
			if(StringUtil.isBlank(jointOrgFlag) || !GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				doctorRecruit.setOrgFlow(currUser.getOrgFlow());
			}
		}
		if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
			List<ResJointOrg> jointOrgs=jointOrgBiz.searchResJointByOrgFlow(currUser.getOrgFlow());
			if(jointOrgs!=null&&!jointOrgs.isEmpty()){
				for(ResJointOrg o:jointOrgs){
					jointOrgFlowList.add(o.getJointOrgFlow());
				}
				jointOrgFlowList.add(currUser.getOrgFlow());
			}
		}
		if(StringUtil.isNotBlank(derateFlag)){
			if(GlobalConstant.FLAG_Y.equals(derateFlag)){
//				doctorRecruit.setCatSpeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
				doctorRecruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
			}else{
				derateFlag="";
			}
		}else{
			derateFlag="";
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<JsResDoctorRecruitExt>recruitList=jsResDoctorRecruitBiz.searchTrainInfoList(jointOrgFlowList,doctorRecruit, user, derateFlag);
		model.addAttribute("recruitList", recruitList);
		return  "jsres/hospital/doctor/trainInfoZi";
	}
	/**
	 * 保存基地上传的url和学习计划
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveTrainInfo")
	@ResponseBody
	public String saveTrainInfo(Model model,ResDoctorRecruitWithBLOBs dBloBs){
		if(StringUtil.isNotBlank(dBloBs.getRecruitFlow())){
			ResDoctorRecruitWithBLOBs exitBloBs=new ResDoctorRecruitWithBLOBs();
			exitBloBs.setRecruitFlow(dBloBs.getRecruitFlow());
			ResDoctorRecruitWithBLOBs doctorRecruitWb=jsResDoctorRecruitBiz.readRecruit(dBloBs.getRecruitFlow());
			doctorRecruitWb.setProveFileUrl(dBloBs.getProveFileUrl());
			doctorRecruitWb.setProvRemark(dBloBs.getProvRemark());
			int result=jsResDoctorRecruitBiz.saveDoctorRecruit(doctorRecruitWb);
			if(GlobalConstant.ZERO_LINE==result){
				return GlobalConstant.SAVE_FAIL;
			}else{
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return  "jsres/hospital/doctor/trainInfoZi";
	}
	/**
	 * 判断同一个级别是否存在相同的记录
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/validate")
	@ResponseBody
	public String validate(Model model,String doctorFlow,String sessionNumber){
		if(StringUtil.isNotBlank(doctorFlow) && StringUtil.isNotBlank(sessionNumber)){
			ResDoctorRecruit recruit=new ResDoctorRecruit();
			recruit.setSessionNumber(sessionNumber);
			recruit.setDoctorFlow(doctorFlow);
			recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
			List<ResDoctorRecruit>  recruits=jsResDoctorRecruitBiz.readDoctorRecruits(recruit);
			if(recruit!=null && (!recruits.isEmpty())){
				return GlobalConstant.FLAG_N;
			}else{
				return GlobalConstant.FLAG_Y;
			}
		}
		return  GlobalConstant.FLAG_Y;
	}
	/**
	 * 当前机构下所有医师
	 */
	@RequestMapping(value="/doctorTrendList")
	public String doctorRecruit(Model model,Integer currentPage, HttpServletRequest request,ResDoctorRecruit resDoctorRecruit,SysUser user){
		SysUser sysuser=GlobalContext.getCurrentUser();
		if (sysuser!=null) {
			resDoctorRecruit.setOrgFlow(sysuser.getOrgFlow());
			resDoctorRecruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
			PageHelper.startPage(currentPage, getPageSize(request));
			List<JsResDoctorRecruitExt> RecruitList=jsResDoctorRecruitBiz.resDoctorRecruitExtList(resDoctorRecruit,user,null,null);
			model.addAttribute("recruitList",RecruitList);
		}
		return  "jsres/hospital/doctor/doctorTrendList";
	}

	@RequestMapping(value="/delDoctorRecruit")
	@ResponseBody
	public String delDoctorRecruit(String recruitFlow){
		ResDoctorRecruitWithBLOBs recruit=(ResDoctorRecruitWithBLOBs) this.jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
		if(recruit!=null){
			recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			int result=this.jsResDoctorRecruitBiz.saveDoctorRecruit(recruit);
			if(result==GlobalConstant.ONE_LINE){
				return GlobalConstant.DELETE_SUCCESSED;
			}
		}
		return GlobalConstant.DELETE_FAIL;
	}

	@RequestMapping(value="/doctorTrendListSun")
	public String doctorRecruitSun(Model model,Integer currentPage,String roleFlag, HttpServletRequest request,ResDoctor doctor,
								   SysUser user,String baseId,String jointOrgFlag,String derateFlag,String orgLevel,String[] datas,
								   String[] yearDatas,String graduationYear,String isPostpone,String orgCityId,String baseFlag,
								   String flag,String isRetrain,String isArmy, String workOrgId){
		ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
		if(StringUtil.isNotBlank(graduationYear)){
			resDoctorRecruit.setGraduationYear(graduationYear);
		}
		if (GlobalConstant.USER_LIST_SPELOCAL.equals(roleFlag) || GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(roleFlag)) {
			doctor.setTrainingSpeId(GlobalContext.getCurrentUser().getResTrainingSpeId());
			resDoctorRecruit.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		}
		List<String> jointOrgFlowList=new ArrayList<String>();
		List<String>docTypeList=new ArrayList<String>();
		List<String>trainYearList=new ArrayList<String>();
		//orgCityId参数2个(form和url里）
		if(!",".equals(orgCityId)){
			String[] orgCity = orgCityId.split(",");
			if(orgCity.length>1){
				orgCityId = orgCity[1];
			}else{
				orgCityId = orgCity[0];
			}
		}else{
			orgCityId = "";
		}
		SysOrg org=new SysOrg();
		org.setOrgCityId(orgCityId);
		SysUser sysuser=GlobalContext.getCurrentUser();
		String userOrgFlow = "";
		if(sysuser != null) {
			userOrgFlow = sysuser.getOrgFlow();
		}
		//当前基地是否为协同基地
		String isJointOrg = "N";
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(sysuser.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
			isJointOrg = "Y";
		}

		SysUser sysUser =new SysUser();
		if(StringUtil.isNotBlank(user.getNativePlaceCityId())){
			sysUser.setNativePlaceCityId(user.getNativePlaceCityId());
		}
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
//						searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
					if (GlobalConstant.FLAG_Y.equals(flag)) {
						searchOrg.setOrgLevelId("CountryOrg");
//						searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
					searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
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
//						org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
					if (GlobalConstant.FLAG_Y.equals(flag)) {
						org.setOrgLevelId("CountryOrg");
//						org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
					org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
				}
			}
		}else{
			jointOrgFlowList.add(doctor.getOrgFlow());
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
//						if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
//							String cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
//							SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
//							if(StringUtil.isNotBlank(cityId)&&cityId.equals(sysOrg.getOrgCityId())){
//								String cityId2 = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
//								if(StringUtil.isNotBlank(cityId2)&&cityId2.equals(sysOrg.getOrgCityId())){
//									jointOrgFlowList.add(jointOrg.getJointOrgFlow());
//								}
//							}
//						}else{
//							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
//						}
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
//				doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
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
		String yearStr = "";
		if(yearDatas!=null&&yearDatas.length>0){
			for(String s:yearDatas){
				trainYearList.add(s);
				yearStr+=s+",";
			}
		}
		model.addAttribute("yearStr",yearStr);
		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(doctor.getSessionNumber())){
			String[] numbers=doctor.getSessionNumber().split(",");
			if(numbers!=null&&numbers.length>0){
				sessionNumbers=Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		resDoctorRecruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		if(StringUtil.isNotBlank(isRetrain)){
			resDoctorRecruit.setIsRetrain(isRetrain);
		}
		List<JsResDoctorRecruitExt> doctorList=null;

		String workOrgName = null;
		ServletContext servletContext2=request.getServletContext();
		if (servletContext2!=null) {
			List<SysDict> sysDictList = (List<SysDict>) servletContext2.getAttribute("dictTypeEnum" + "SendSchool" + "List");
			if(CollectionUtils.isNotEmpty(sysDictList)){
				Map<String, String> cityRelsMap = sysDictList.stream().collect(Collectors.toMap(SysDict::getDictId, SysDict::getDictName, (key1, key2)-> key1));
				if(StringUtils.isNotEmpty(workOrgId)){
					workOrgName = cityRelsMap.get(workOrgId);
				}
			}
		}


		if (GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
			ServletContext servletContext=request.getServletContext();
			if (servletContext!=null) {
				List<SysDict> sysDictList=(List<SysDict>) servletContext.getAttribute("dictTypeEnum"+"SendSchool"+"List");
				for (SysDict sysDict : sysDictList) {
					String dictId="send_school_"+sysDict.getDictId()+"_org_rel";
					String orgFlow=InitConfig.getSysCfg(dictId);
					doctor.setWorkOrgId(sysDict.getDictId());
					if (sysuser.getOrgFlow().equals(orgFlow)) {
						doctorList=jsResDoctorRecruitBiz.searchDoctorInfoExts1(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,docTypeList,trainYearList,sessionNumbers,"","","",isJointOrg);
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
			doctorList = jsResDoctorRecruitBiz.searchDoctorInfoExts2(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,docTypeList,trainYearList,sessionNumbers,baseFlag,userOrgFlow,flag,isJointOrg,isPostpone,isArmy, workOrgId , workOrgName);
		}
		Map<String, List<ResResponsibleteacherDoctor>> teaMap = new HashMap<>();
		ResResponsibleteacherDoctor search = new ResResponsibleteacherDoctor();
		if (doctorList != null && doctorList.size() > 0) {
			for (JsResDoctorRecruitExt jsResDoctorRecruitExt : doctorList) {
				String doctorFlow = jsResDoctorRecruitExt.getDoctorFlow();
				search.setDoctorFlow(doctorFlow);
				List<ResResponsibleteacherDoctor> resResponsibleteacherDoctorList = resResponsibleTeacherDoctorBiz.search(search);
				teaMap.put(doctorFlow, resResponsibleteacherDoctorList);
			}
		}
		model.addAttribute("teaMap",teaMap);
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("doctorList",doctorList);
		model.addAttribute("datas",datas);
		model.addAttribute("yearDatas",yearDatas);
		model.addAttribute("mainOrgFlow",sysuser.getOrgFlow());
		return  "jsres/doctorListZi";
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
	/****************************高******校******管******理******员******角******色************************************************/
	/**
	 * 医师信息查询
	 * @param model
	 * @param roleFlag
	 * @return
	 */
	@RequestMapping(value="/universityDoctorList")
	public String universityDoctorList(Model model,String roleFlag){
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org",org);
		SysOrg sysorg =new  SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
		sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs=orgBiz.getUniOrgs(org.getSendSchoolId(),org.getSendSchoolName());
		sysorg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
		List<SysOrg>  countryList=orgBiz.searchOrg(sysorg);
		sysorg.setOrgLevelId(OrgLevelEnum.ProvinceOrg.getId());
		List<SysOrg>  provinceList=orgBiz.searchOrg(sysorg);
		List<String> orgFlowList=new ArrayList<String>();
		model.addAttribute("countryList", countryList);
		model.addAttribute("provinceList", provinceList);
		if(StringUtil.isNotEmpty(sysuser.getSchool())){
			model.addAttribute("school", sysuser.getSchool());
		}
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		return  "jsres/university/doctorList";
	}

	/**
	 * 医师信息查询
	 * @param model
	 * @param currentPage
	 * @param roleFlag
	 * @param request
	 * @param doctor
	 * @param user
	 * @param derateFlag
	 * @param orgLevel
	 * @param graduationYear
	 * @return
	 */
	@RequestMapping(value="/doctorTrendListSunForUni")
	public String doctorTrendListSunForUni(Model model,Integer currentPage,String roleFlag, HttpServletRequest request,ResDoctor doctor,SysUser user,String derateFlag,String orgLevel,String graduationYear){
		ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
		if(StringUtil.isNotBlank(graduationYear)){
			resDoctorRecruit.setGraduationYear(graduationYear);
		}
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysUser sysUser =new SysUser();
		if (StringUtil.isBlank(doctor.getOrgFlow())) {
			{
				org.setOrgProvId("320000");
				if(StringUtil.isNotBlank(orgLevel)){
					org.setOrgLevelId(orgLevel);
					org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
				}
			}
		}
		if(StringUtil.isNotBlank(user.getIdNo())){
			sysUser.setIdNo(user.getIdNo());
		}
		sysUser.setUserName(user.getUserName());
		if(StringUtil.isNotBlank(derateFlag)){
			if(GlobalConstant.FLAG_Y.equals(derateFlag)){
//				doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
				resDoctorRecruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
			}else{
				derateFlag="";
			}
		}else{
			derateFlag="";
		}
		docTypeList.add(JsResDocTypeEnum.Graduate.getId());
		PageHelper.startPage(currentPage,getPageSize(request));
		resDoctorRecruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		List<JsResDoctorRecruitExt> doctorList=null;
		if(StringUtil.isNotEmpty(sysuser.getSchool()) && StringUtil.isEmpty(doctor.getOrgFlow())){
			doctor.setSchool(sysuser.getSchool());
		}
		doctorList = jsResDoctorRecruitBiz.searchDoctorInfoExtsForUni(resDoctorRecruit,doctor, sysUser, org,derateFlag,docTypeList,sysuser);
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("doctorList",doctorList);
		return  "jsres/doctorListZi";
	}

	/**
	 *医师轮转查询
	 * @param userName
	 * @param idNo
	 * @param trainingSpeId
	 * @param sessionNumber
	 * @param trainingYears
	 * @param graduationTime
	 * @param model
	 * @param request
	 * @param startDate
	 * @param endDate
	 * @param currentPage
	 *  @param orgFlow
	 * @return
	 */
	@RequestMapping(value = "/cycleForUniList")
	public String cycleForUniList(String userName,String idNo,String trainingSpeId,String trainingTypeId, String sessionNumber,String trainingYears,
								  String graduationTime,Model model,HttpServletRequest request,
								  String startDate,String endDate,Integer currentPage,String orgFlow){
		List<String> titleDate=new ArrayList<>();
		if(!StringUtil.isNotBlank(startDate)){
			String currDate = DateUtil.getCurrDate();
			startDate=DateUtil.newDateOfAddMonths(currDate,-6);
			endDate = currDate;
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			String cycStartMonth = startDate.substring(0, 7);
			String cycEndMonth = endDate.substring(0, 7);
			titleDate = getMonthsByTwoMonth(cycStartMonth, cycEndMonth);
			model.addAttribute("titleDate", titleDate);
		}
		SysUser currUser=GlobalContext.getCurrentUser();
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		SysOrg currOrg = orgBiz.readSysOrg(currUser.getOrgFlow());
		orgs=orgBiz.getUniOrgs(currOrg.getSendSchoolId(),currOrg.getSendSchoolName());
		model.addAttribute("orgs", orgs);
		PageHelper.startPage(currentPage, getPageSize(request));//分页,必须放在分页sql前
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if(orgFlow!=null){
			paramMap.put("orgFlow",orgFlow);
		}
		if(StringUtil.isNotEmpty(currUser.getSchool())){
			paramMap.put("school",currUser.getSchool());
			model.addAttribute("school",currUser.getSchool());
		}
		if(userName!=null ){
			paramMap.put("userName",userName);
		}
		if(idNo!=null ){
			paramMap.put("idNo",idNo);
		}
		if(trainingSpeId!=null){
			paramMap.put("trainingSpeId",trainingSpeId);
		}
		if(trainingTypeId!=null){
			paramMap.put("trainingTypeId",trainingTypeId);
		}
		if(sessionNumber!=null){
			paramMap.put("sessionNumber",sessionNumber);
		}
		if(trainingYears!=null){
			paramMap.put("trainingYears",trainingYears);
		}
		if(graduationTime!=null){
			paramMap.put("graduationTime",graduationTime);
		}
		paramMap.put("doctorTypeId",JsResDocTypeEnum.Graduate.getId());
		paramMap.put("workOrgId",currOrg.getSendSchoolId());
		paramMap.put("workOrgName",currOrg.getSendSchoolName());
		List<Map<String,Object>> docCycleList = schArrangeResultBiz.searchDocCycleListForUni(paramMap);
		model.addAttribute("docCycleList",docCycleList);
		if(docCycleList!=null&&docCycleList.size()>0) {
			Map<String, List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
			Map<String, ResRec> recMap = new HashMap<String, ResRec>();
//			List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchCycleArrangeResultsForUniTwo(paramMap);
			List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchCycleArrangeResultsForUni(paramMap);
			for (SchArrangeResult sar : arrResultList) {
				String doctorFlow = sar.getDoctorFlow();
				String resultStartDate = sar.getSchStartDate();
				String resultEndDate = sar.getSchEndDate();
				String standardDeptId = sar.getStandardDeptId();//轮转部门颜色
				String standarGroupFlow = sar.getStandardGroupFlow();
				if (StringUtil.isNotBlank(standardDeptId) && StringUtil.isNotBlank(standarGroupFlow)) {
//					SchRotationDept schRotationDept = schRotationDeptBiz.searchGroupFlowAndStandardDeptIdQueryTwo(standarGroupFlow, standardDeptId);
					SchRotationDept schRotationDept = schRotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(standarGroupFlow, standardDeptId);
					if (schRotationDept != null) {
						String recordFlow = schRotationDept.getRecordFlow();
						ResRec resRec = resRecBiz.queryResRec(recordFlow, doctorFlow, ResRecTypeEnum.AfterSummary.getId());
						recMap.put(sar.getResultFlow(), resRec);
					}
				}
				if (StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)) {
					resultStartDate = resultStartDate.substring(0, 7);
					resultEndDate = resultEndDate.substring(0, 7);
					for(String title:titleDate)
					{
						if(title.compareTo(resultStartDate)>=0&&title.compareTo(resultEndDate)<=0)
						{
							String key = doctorFlow + title;
							List<SchArrangeResult> sarList = resultListMap.get(key);
							if (sarList == null) {
								sarList = new ArrayList<SchArrangeResult>();
								resultListMap.put(key, sarList);
							}
							sarList.add(sar);
						}
					}
//					List<String> months = getMonthsByTwoMonth(resultStartDate, resultEndDate);
//					if (months != null) {
//						for (String month : months) {
//							String key = doctorFlow + month;
//							List<SchArrangeResult> sarList = resultListMap.get(key);
//							if (sarList == null) {
//								sarList = new ArrayList<SchArrangeResult>();
//								resultListMap.put(key, sarList);
//							}
//							sarList.add(sar);
//						}
//					}
				}
			}

			model.addAttribute("recMap", recMap);
			model.addAttribute("resultListMap",resultListMap);
		}
		return "jsres/university/cycle";
	}

	@RequestMapping(value = "/cycleForUni")
	public String cycleForUni(Model model,HttpServletRequest request){
		SysUser currUser=GlobalContext.getCurrentUser();
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		SysOrg currOrg = orgBiz.readSysOrg(currUser.getOrgFlow());
		orgs=orgBiz.getUniOrgs(currOrg.getSendSchoolId(),currOrg.getSendSchoolName());
		model.addAttribute("orgs", orgs);
		if(StringUtil.isNotEmpty(currUser.getSchool())){
			model.addAttribute("school", currUser.getSchool());
		}
		model.addAttribute("orgs", orgs);
		return "jsres/university/cycle";
	}

	/**
	 * 医师出科成绩查询
	 * @param trainingSpeId
	 * @param sessionNumber
	 * @param userName
	 * @param model
	 * @param request
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(value="/cycleResultsForUni")
	public String cycleResultsForUni(String trainingSpeId,String trainingTypeId,String sessionNumber,String userName,Model model,HttpServletRequest request,Integer currentPage,
									 String startDate,String endDate,String orgFlow){
		List<String> titleDate=new ArrayList<>();
		if(!StringUtil.isNotBlank(startDate)){
			String currDate = DateUtil.getCurrDate();
			startDate=DateUtil.newDateOfAddMonths(currDate,-6);
			endDate = currDate;
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			String cycStartMonth = startDate.substring(0, 7);
			String cycEndMonth = endDate.substring(0, 7);
			titleDate = getMonthsByTwoMonth(cycStartMonth, cycEndMonth);
			model.addAttribute("titleDate", titleDate);
		}
		SysUser currUser=GlobalContext.getCurrentUser();
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		SysOrg currOrg = orgBiz.readSysOrg(currUser.getOrgFlow());
		orgs=orgBiz.getUniOrgs(currOrg.getSendSchoolId(),currOrg.getSendSchoolName());
		model.addAttribute("orgs", orgs);
		PageHelper.startPage(currentPage, getPageSize(request));//分页,必须放在分页sql前
		String sysOrgFlow = currUser.getOrgFlow();
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if(StringUtil.isNotBlank(orgFlow)){
			paramMap.put("orgFlow",orgFlow);
		}
		if(StringUtil.isNotEmpty(currUser.getSchool())){
			paramMap.put("school",currUser.getSchool());
			model.addAttribute("school", currUser.getSchool());
		}
		if(userName!=null ){
			paramMap.put("userName",userName);
		}
		if(trainingSpeId!=null){
			paramMap.put("trainingSpeId",trainingSpeId);
		}
		if(trainingTypeId!=null){
			paramMap.put("trainingTypeId",trainingTypeId);
		}
		if(sessionNumber!=null){
			paramMap.put("sessionNumber",sessionNumber);
		}
		if(startDate!=null){
			paramMap.put("startDate",startDate);
		}
		if(endDate!=null){
			paramMap.put("endDate",endDate);
		}
		paramMap.put("doctorTypeId",JsResDocTypeEnum.Graduate.getId());
		paramMap.put("workOrgId",currOrg.getSendSchoolId());
		paramMap.put("workOrgName",currOrg.getSendSchoolName());
		List<Map<String,Object>> docResultsList = schArrangeResultBiz.searchDocResultsListForUni(paramMap);
		model.addAttribute("docResultsList",docResultsList);
		Map<String,List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();

		List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchCycleArrangeResultsForUni(paramMap);
		for (SchArrangeResult sar : arrResultList) {
			String doctorFlow = sar.getDoctorFlow();
			String resultStartDate = sar.getSchStartDate();
			String resultEndDate = sar.getSchEndDate();
			if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
				resultStartDate = resultStartDate.substring(0,7);
				resultEndDate = resultEndDate.substring(0,7);
				for(String title:titleDate)
				{
					if(title.compareTo(resultStartDate)>=0&&title.compareTo(resultEndDate)<=0)
					{
						String key = doctorFlow + title;
						List<SchArrangeResult> sarList = resultListMap.get(key);
						if (sarList == null) {
							sarList = new ArrayList<SchArrangeResult>();
							resultListMap.put(key, sarList);
						}
						sarList.add(sar);
					}
				}
//					List<String> months = getMonthsByTwoMonth(resultStartDate, resultEndDate);
//					if (months != null) {
//						for (String month : months) {
//							String key = doctorFlow + month;
//							List<SchArrangeResult> sarList = resultListMap.get(key);
//							if (sarList == null) {
//								sarList = new ArrayList<SchArrangeResult>();
//								resultListMap.put(key, sarList);
//							}
//							sarList.add(sar);
//						}
//					}
			}
		}
		model.addAttribute("resultListMap",resultListMap);
		return "jsres/university/cycleResults";
	}

	/**
	 * @Department：研发部
	 * @Description 导出学员出科成绩和技能评估成绩
	 * @Author fengxf
	 * @Date 2019/11/12
	 */
	@RequestMapping("/exportDoctorRecruitResult")
	public void exportDoctorRecruitResult(ResDoctorExt resDoctor, String[] doctorTypeList, String userName, String papersFlag, String roleId, HttpServletResponse response){
		try {
			// 当前登录用户
			SysUser user = GlobalContext.getCurrentUser();
			if("kzr".equals(roleId)){
				resDoctor.setDeptFlow(user.getDeptFlow());
				resDoctor.setKzrFlow(user.getUserFlow());
			}
			// 基地为空则取当前登录用户的基地赋值给当前基地
			if(StringUtil.isBlank(resDoctor.getOrgFlow())){
				resDoctor.setCurrOrgFlow(user.getOrgFlow());
			}
			// 人员类型
			if(null != doctorTypeList && 0 < doctorTypeList.length){
				resDoctor.setDoctorTypeIdList(Arrays.asList(doctorTypeList));
			}
			resDoctor.setDoctorName(userName);
			// 导出学员出科信息和技能评估信息
			resDoctorBiz.exportDoctorRecruitResultList(resDoctor, response, papersFlag);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	//排班查询 住院医师
	@RequestMapping(value = "/schedulingSearch")
	public String schedulingSearch() {
		return "jsres/hospital/schedulingSearch";
	}

	//排班查询 助理全科
	@RequestMapping(value = "/schedulingSearchAcc")
	public String schedulingSearchAcc() {
		return "jsres/hospital/schedulingSearchAcc";
	}

	//排班查询主页面  住院医师
	@RequestMapping("/schedulingSearchMain")
	public String schedulingSearchMain(Model model, String type) {
		if (type.equals("dept")) {
			SysDept sysDept = new SysDept();
			sysDept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			List<SysDept> deptList = deptBiz.searchDept(sysDept);
			model.addAttribute("deptList", deptList);
			model.addAttribute("searchTime", DateUtil.getMonth());
			return "jsres/hospital/schedulingSearchDeptMain";
		} else if (type.equals("doctor")) {
			model.addAttribute("searchTime", DateUtil.getMonth());
			return "jsres/hospital/schedulingSearchDoctorMain";
		}

		return "jsres/hospital/schedulingSearchDeptMain";
	}

	//排班查询主页面  主力全科
	@RequestMapping("/schedulingSearchMainAcc")
	public String schedulingSearchMainAcc(Model model, String type) {
		if (type.equals("dept")) {
			SysDept sysDept = new SysDept();
			sysDept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			List<SysDept> deptList = deptBiz.searchDept(sysDept);
			model.addAttribute("deptList", deptList);
			model.addAttribute("searchTime", DateUtil.getMonth());
			return "jsres/hospital/schedulingSearchDeptMainAcc";
		} else if (type.equals("doctor")) {
			model.addAttribute("searchTime", DateUtil.getMonth());
			return "jsres/hospital/schedulingSearchDoctorMainAcc";
		}
		return "jsres/hospital/schedulingSearchDeptMainAcc";
	}

	//排班查询-科室条件页面 住院医师
	@RequestMapping("/schedulingSearchDeptList")
	public String schedulingSearchDeptList(Model model, String deptFlow, String searchTime) throws Exception {
		String firstDayOfMonth = "";
		String lastDayOfMonth = "";
		if(StringUtils.isBlank(searchTime)){
			firstDayOfMonth = com.pinde.sci.common.util.DateUtil.getFirstDayOfMonth();
			lastDayOfMonth = com.pinde.sci.common.util.DateUtil.getLastDayOfMonth();
		}else{
			// 正则表达式，匹配YYYY-MM格式
			String regex = "^(\\d{4})-(0[1-9]|1[0-2])$";

			// 使用Pattern和Matcher进行校验
			Pattern pattern = Pattern.compile(regex);
			boolean isValid = pattern.matcher(searchTime).matches();

			if (!isValid) {
				logger.error("invalid param : searchTime {}", searchTime);
				throw new Exception("invalid param : searchTime = " + searchTime );
			}else{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

				// 将字符串解析为YearMonth对象
				YearMonth yearMonth = YearMonth.parse(searchTime, formatter);

				// 获取这个月份的第一天
				firstDayOfMonth = yearMonth.atDay(1).toString();

				// 获取这个月份的最后一天
				lastDayOfMonth = yearMonth.atEndOfMonth().toString();
			}
		}
		List<Map<String, Object>> list = schArrangeResultBiz.schedulingSearchDeptList(
				GlobalContext.getCurrentUser().getOrgFlow(), firstDayOfMonth,lastDayOfMonth, deptFlow);
		model.addAttribute("list", list);
		return "jsres/hospital/schedulingSearchDeptList";
	}

	//排班查询-科室条件页面 助理全科
	@RequestMapping("/schedulingSearchDeptListAcc")
	public String schedulingSearchDeptListAcc(Model model, String deptFlow, String searchTime) throws Exception {
		String firstDayOfMonth = "";
		String lastDayOfMonth = "";
		if(StringUtils.isBlank(searchTime)){
			firstDayOfMonth = com.pinde.sci.common.util.DateUtil.getFirstDayOfMonth();
			lastDayOfMonth = com.pinde.sci.common.util.DateUtil.getLastDayOfMonth();
		}else{
			// 正则表达式，匹配YYYY-MM格式
			String regex = "^(\\d{4})-(0[1-9]|1[0-2])$";

			// 使用Pattern和Matcher进行校验
			Pattern pattern = Pattern.compile(regex);
			boolean isValid = pattern.matcher(searchTime).matches();

			if (!isValid) {
				logger.error("invalid param : searchTime {}", searchTime);
				throw new Exception("invalid param : searchTime = " + searchTime );
			}else{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

				// 将字符串解析为YearMonth对象
				YearMonth yearMonth = YearMonth.parse(searchTime, formatter);

				// 获取这个月份的第一天
				firstDayOfMonth = yearMonth.atDay(1).toString();

				// 获取这个月份的最后一天
				lastDayOfMonth = yearMonth.atEndOfMonth().toString();
			}
		}
		List<Map<String, Object>> list = schArrangeResultBiz.schedulingSearchDeptList2(
				GlobalContext.getCurrentUser().getOrgFlow(), firstDayOfMonth,lastDayOfMonth, deptFlow);
		model.addAttribute("list", list);
		return "jsres/hospital/schedulingSearchDeptList";
	}


	//排班查询-科室列表  住院医师
	@RequestMapping("/schedulingSearchDeptUserList")
	public String schedulingSearchDeptUserList(Model model, String deptFlow, String searchTime) throws Exception {
		String firstDayOfMonth = "";
		String lastDayOfMonth = "";
		if(StringUtils.isBlank(searchTime)){
			firstDayOfMonth = com.pinde.sci.common.util.DateUtil.getFirstDayOfMonth();
			lastDayOfMonth = com.pinde.sci.common.util.DateUtil.getLastDayOfMonth();
		}else{
			// 正则表达式，匹配YYYY-MM格式
			String regex = "^(\\d{4})-(0[1-9]|1[0-2])$";

			// 使用Pattern和Matcher进行校验
			Pattern pattern = Pattern.compile(regex);
			boolean isValid = pattern.matcher(searchTime).matches();

			if (!isValid) {
				logger.error("invalid param : searchTime {}", searchTime);
				throw new Exception("invalid param : searchTime = " + searchTime );
			}else{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

				// 将字符串解析为YearMonth对象
				YearMonth yearMonth = YearMonth.parse(searchTime, formatter);

				// 获取这个月份的第一天
				firstDayOfMonth = yearMonth.atDay(1).toString();

				// 获取这个月份的最后一天
				lastDayOfMonth = yearMonth.atEndOfMonth().toString();
			}
		}
		List<Map<String, Object>> list = schArrangeResultBiz.schedulingSearchDeptUserList(
				GlobalContext.getCurrentUser().getOrgFlow(), firstDayOfMonth,lastDayOfMonth, deptFlow,"");
		model.addAttribute("list", list);
		return "jsres/hospital/schedulingSearchDeptUserList";
	}

	//排班查询-科室列表
	@RequestMapping("/schedulingSearchDeptUserListAcc")
	public String schedulingSearchDeptUserListAcc(Model model, String deptFlow, String searchTime) throws Exception {
		String firstDayOfMonth = "";
		String lastDayOfMonth = "";
		if(StringUtils.isBlank(searchTime)){
			firstDayOfMonth = com.pinde.sci.common.util.DateUtil.getFirstDayOfMonth();
			lastDayOfMonth = com.pinde.sci.common.util.DateUtil.getLastDayOfMonth();
		}else{
			// 正则表达式，匹配YYYY-MM格式
			String regex = "^(\\d{4})-(0[1-9]|1[0-2])$";

			// 使用Pattern和Matcher进行校验
			Pattern pattern = Pattern.compile(regex);
			boolean isValid = pattern.matcher(searchTime).matches();

			if (!isValid) {
				logger.error("invalid param : searchTime {}", searchTime);
				throw new Exception("invalid param : searchTime = " + searchTime );
			}else{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

				// 将字符串解析为YearMonth对象
				YearMonth yearMonth = YearMonth.parse(searchTime, formatter);

				// 获取这个月份的第一天
				firstDayOfMonth = yearMonth.atDay(1).toString();

				// 获取这个月份的最后一天
				lastDayOfMonth = yearMonth.atEndOfMonth().toString();
			}
		}
		List<Map<String, Object>> list = schArrangeResultBiz.schedulingSearchDeptUserList(
				GlobalContext.getCurrentUser().getOrgFlow(), firstDayOfMonth,lastDayOfMonth, deptFlow,TrainCategoryEnum.AssiGeneral.getId());
		model.addAttribute("list", list);
		return "jsres/hospital/schedulingSearchDeptUserList";
	}


	//排版查询-学员列表 住院医师
	@RequestMapping("/schedulingSearchDoctorList")
	public String schedulingSearchDoctorList(Model model, Integer currentPage, HttpServletRequest request, String userName,
											 String doctorTypeId, String trainingSpeId, String sessionNumber, String searchTime) throws Exception {

		String firstDayOfMonth = "";
		String lastDayOfMonth = "";
		if(StringUtils.isBlank(searchTime)){
			firstDayOfMonth = com.pinde.sci.common.util.DateUtil.getFirstDayOfMonth();
			lastDayOfMonth = com.pinde.sci.common.util.DateUtil.getLastDayOfMonth();
		}else{
			// 正则表达式，匹配YYYY-MM格式
			String regex = "^(\\d{4})-(0[1-9]|1[0-2])$";

			// 使用Pattern和Matcher进行校验
			Pattern pattern = Pattern.compile(regex);
			boolean isValid = pattern.matcher(searchTime).matches();

			if (!isValid) {
				logger.error("invalid param : searchTime {}", searchTime);
				throw new Exception("invalid param : searchTime = " + searchTime );
			}else{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

				// 将字符串解析为YearMonth对象
				YearMonth yearMonth = YearMonth.parse(searchTime, formatter);

				// 获取这个月份的第一天
				firstDayOfMonth = yearMonth.atDay(1).toString();

				// 获取这个月份的最后一天
				lastDayOfMonth = yearMonth.atEndOfMonth().toString();
			}
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", userName);
		paramMap.put("doctorTypeId", doctorTypeId);
		paramMap.put("trainingSpeId", trainingSpeId);
		paramMap.put("sessionNumber", sessionNumber);
		paramMap.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
		paramMap.put("firstDayOfMonth",firstDayOfMonth);
		paramMap.put("lastDayOfMonth",lastDayOfMonth);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, Object>> list = schArrangeResultBiz.schedulingSearchDoctorList(paramMap);
		model.addAttribute("list", list);
		return "jsres/hospital/schedulingSearchDoctorList";
	}

	//排版查询-学员列表 助理全科
	@RequestMapping("/schedulingSearchDoctorListAcc")
	public String schedulingSearchDoctorListAcc(Model model, Integer currentPage, HttpServletRequest request, String userName,
												String doctorTypeId, String trainingSpeId, String sessionNumber, String searchTime) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", userName);
		paramMap.put("doctorTypeId", doctorTypeId);
		paramMap.put("trainingSpeId", trainingSpeId);
		paramMap.put("sessionNumber", sessionNumber);
		paramMap.put("month", searchTime);
		paramMap.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
		paramMap.put("trainingTypeId",TrainCategoryEnum.AssiGeneral.getId());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, Object>> list = schArrangeResultBiz.schedulingSearchDoctorList(paramMap);
		model.addAttribute("list", list);
		return "jsres/hospital/schedulingSearchDoctorList";
	}

	//排班审核	住院医师
	@RequestMapping(value = "/schedulingAudit")
	public String schedulingAudit(Model model) {
		SysDept sysDept = new SysDept();
		sysDept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<SysDept> deptList = deptBiz.searchDept(sysDept);
		model.addAttribute("deptList", deptList);
		model.addAttribute("sessionNumber",DateUtil.getYear());
		model.addAttribute("checkOpen","N");
		//查询是否需要校验
		JsresPowerCfg openCfg = jsResPowerCfgBiz.read("process_scheduling_check_" + GlobalContext.getCurrentUser().getOrgFlow());
		if (ObjectUtil.isEmpty(openCfg) || StringUtils.isEmpty(openCfg.getCfgValue())) {
			model.addAttribute("checkOpen","N");
		}else if (GlobalConstant.FLAG_Y.equalsIgnoreCase(openCfg.getCfgValue())) {
			model.addAttribute("checkOpen","Y");
		}else {
			model.addAttribute("checkOpen","N");
		}
		return "jsres/hospital/schedulingAudit";
	}



	//排班审核  助理全科
	@RequestMapping(value = "/schedulingAuditAcc")
	public String schedulingAuditAcc(Model model) {
		SysDept sysDept = new SysDept();
		sysDept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<SysDept> deptList = deptBiz.searchDept(sysDept);
		model.addAttribute("deptList", deptList);
		model.addAttribute("sessionNumber",DateUtil.getYear());
		return "jsres/hospital/schedulingAuditAcc";
	}

	//排班-审核列表  住院医师
	@RequestMapping(value = "/schedulingAuditList")
	public String schedulingAuditList(String speId,String schDeptFlow,String userName,String schStartDate,String sessionNumber,String trainingTypeId,
									  String schEndDate, Model model, Integer currentPage, HttpServletRequest request) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("speId",speId);
		paramMap.put("schDeptFlow",schDeptFlow);
		paramMap.put("userName",userName);
		paramMap.put("schStartDate",schStartDate);
		paramMap.put("schEndDate",schEndDate);
		paramMap.put("baseAudit","Passing");
		paramMap.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
		paramMap.put("trainingTypeId",trainingTypeId);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, Object>> list = schArrangeResultBiz.schedulingSearchAuditList(paramMap);
		model.addAttribute("list", list);
		return "jsres/hospital/schedulingAuditList";
	}


	//排班-审核列表  助理全科
	@RequestMapping(value = "/schedulingAuditListAcc")
	public String schedulingAuditListAcc(String speId,String schDeptFlow,String userName,String schStartDate,String sessionNumber
			,String schEndDate, Model model, Integer currentPage, HttpServletRequest request) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("speId",speId);
		paramMap.put("schDeptFlow",schDeptFlow);
		paramMap.put("userName",userName);
		paramMap.put("schStartDate",schStartDate);
		paramMap.put("schEndDate",schEndDate);
		paramMap.put("baseAudit","Passing");
		paramMap.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
		paramMap.put("trainingTypeId",TrainCategoryEnum.AssiGeneral.getId());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, Object>> list = schArrangeResultBiz.schedulingSearchAuditList(paramMap);
		model.addAttribute("list", list);
		return "jsres/hospital/schedulingAuditList";
	}


	//排班-审核页面
	@RequestMapping(value = "/toSchedulingAudit")
	public String toSchedulingAudit(String resultFlow,Model model) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("resultFlow",resultFlow);
		List<Map<String, Object>> list = schArrangeResultBiz.schedulingSearchAuditList(paramMap);
		if (null!=list && list.size()>0){
			model.addAttribute("result",list.get(0));
		}
		return "jsres/hospital/toSchedulingAudit";
	}

	//排班-审核操作
	@RequestMapping(value = "/schedulingAuditOpe")
	@ResponseBody
	public String schedulingAuditOpe(String resultFlow,String type,Model model) {
		SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
		result.setBaseAudit(type);
		int update = schArrangeResultBiz.update(result);
		if (update>0){
			return GlobalConstant.OPERATE_SUCCESSED;
		}
		return GlobalConstant.OPERATE_FAIL;
	}

	@RequestMapping(value = "/importSchedulingAudit")
	public String importSchedulingAudit(Model model) {
		List<SchRotation> list = rotationBiz.searchSchRotationByIsPublish();
		model.addAttribute("list",list);
		return "jsres/hospital/importSchedulingAudit";
	}
	@RequestMapping(value = "/importSchedulingAudit2")
	public String importSchedulingAudit2(Model model) {
		return "jsres/hospital/importSchedulingAudit2";
	}
	@GetMapping(value = "/importSchedulingAudit2Dept")
	@ResponseBody
	public List<SysDept> importSchedulingAudit2Dept(Model model) {
		SysDept sysDept = new SysDept();
		sysDept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<SysDept> deptList = deptBiz.searchDept(sysDept);
		return deptList;
	}

	@RequestMapping(value = "jumpImportSchedulingAuditCache")
	public String jumpImportSchedulingAuditCache(Model model) {

		return "jsres/hospital/importSchedulingAuditCache";
	}

	@RequestMapping(value = "/importSchedulingiImport")
	public String importSchedulingiImport(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String,String> map = (Map)session.getAttribute("map");
		session.removeAttribute("map");
		if (CollectionUtil.isEmpty(map)) {
			return "jsres/hospital/schedulingAuditImportList";
		}
		String headone = map.get("head1");
		if (StringUtils.isNotEmpty(headone)) {
			JSONArray head1 = JSONArray.parseArray(headone);
			model.addAttribute("head1",head1);
		}
		String headtwo = map.get("head2");
		if (StringUtils.isNotEmpty(headtwo)) {
			JSONArray head2 = JSONArray.parseArray(headtwo);
			model.addAttribute("head2",head2);
		}
		String datastr = map.get("data");
		if (StringUtils.isNotEmpty(datastr)) {
			JSONArray data = JSONArray.parseArray(datastr);
			model.addAttribute("data",data);
		}
		return "jsres/hospital/schedulingAuditImportList";
	}

	//排班  下载导入模板
//	@RequestMapping(value = "/expertSchTemp")
//	@ResponseBody
//	public void expertSchTemp(HttpServletRequest request, HttpServletResponse response,String rotationFlow){
////		SchRotation rotation = rotationBiz.readSchRotation(rotationFlow);
//		try {
//			String[] titleArray = {"学员姓名","身份证号","专业","年级","年限"};
//			List<String> titles =new ArrayList<>(Arrays.asList(titleArray));
//
//			//生成当前年月往后推三年
//			int month = 3 * 12;
//			Calendar calendar = Calendar.getInstance();
//			// 创建SimpleDateFormat对象，用于格式化日期
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//			// 循环输出当前年月往后12个月的具体年月
//			for (int i = 0; i < month; i++) {
//				// 将当前年月格式化为yyyy-MM格式
//				String yearMonth = sdf.format(new Date(calendar.getTimeInMillis()));
////					System.out.println(yearMonth);
//				titles.add(yearMonth);
//				// 将年月递增一个月
//				calendar.add(Calendar.MONTH, 1);
//			}
//			titleArray = titles.toArray(new String[titles.size()]);
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			HSSFSheet sheet = workbook.createSheet();
//			HSSFRow row = sheet.createRow(0);
//			for (int i = 0; i < titleArray.length; i++) {
//				String title = titleArray[i];
//				HSSFCell cell = row.createCell(i);
//				cell.setCellValue(title);
//			}
//
//			HSSFRow row1 = sheet.createRow(1);
//			for (int i = 0; i < 5; i++) {
//				String title = titleArray[i];
//				if("学员姓名".equals(title)){
//					HSSFCell cell = row1.createCell(i);
//					cell.setCellValue("例:张三");
//				}
//				if("身份证号".equals(title)){
//					HSSFCell cell = row1.createCell(i);
//					cell.setCellValue("例:4312211......");
//				}
//				if("专业".equals(title)){
//					HSSFCell cell = row1.createCell(i);
//					cell.setCellValue("例:内科");
//				}
//				if("年级".equals(title)){
//					HSSFCell cell = row1.createCell(i);
//					cell.setCellValue("例:2022");
//				}
//				if("年限".equals(title)){
//					HSSFCell cell = row1.createCell(i);
//					cell.setCellValue("例:三年");
//				}
//			}
///*			//标准科室下拉框填空
//			List<SchRotationDept> standardRotationDeptList = schRotationDeptBiz.searchSchRotationDept(rotationFlow);
//			if (null!=standardRotationDeptList && standardRotationDeptList.size()>0){
//				ArrayList<String> SchRotationDept = new ArrayList<>();
//				for (SchRotationDept dept : standardRotationDeptList) {
//					SchRotationDept.add(dept.getStandardDeptName());
//				}
//				String[] SchRotationDeptArray = SchRotationDept.toArray(new String[SchRotationDept.size()]);
//				selectList(workbook, 2, 2, SchRotationDeptArray );
//			}*/
//
//			//轮转科室下拉框填空
////			SysDept sysDept = new SysDept();
////			sysDept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
////			List<SysDept> schDeptList = deptBiz.searchDept(sysDept);
////			List<SchDeptExternalRel> schDeptExternalRels = deptExternalRelBiz.readSchDeptExtRelByRelOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
//			ArrayList<String> list = new ArrayList<>();
////			for (SysDept dept : schDeptList) {
////				list.add(dept.getDeptName().trim());
////			}
////			//对外开发科室
////			if(CollectionUtils.isNotEmpty(schDeptExternalRels)){
////				for (SchDeptExternalRel schDeptExternalRel : schDeptExternalRels) {
////					list.add(schDeptExternalRel.getDeptName()+"（"+schDeptExternalRel.getOrgName()+"）");
////				}
////			}
//			//新的获取轮转科室下拉框的方法
//			List<LzDeptItem> lzDeptItems = paiBanImportService.deptSelectData(GlobalContext.getCurrentUser().getOrgFlow());
//			if (CollectionUtil.isNotEmpty(lzDeptItems)) {
//				for (LzDeptItem lzDeptItem : lzDeptItems) {
//					if (StringUtils.isEmpty(lzDeptItem.getOrgFlow())) {
//						continue;
//					}
//					if (lzDeptItem.getOrgFlow().equalsIgnoreCase(GlobalContext.getCurrentUser().getOrgFlow())) {
//						list.add(lzDeptItem.getDeptName().trim());
//					}
//					else {
//						list.add(lzDeptItem.getDeptName()+"（"+lzDeptItem.getOrgName()+"）");
//					}
//				}
//			}
//			String[] deptArray = list.toArray(new String[list.size()]);
//			selectList(workbook, 5, titles.size() -1, deptArray );
////			selectList(workbook, 3, 3, deptArray );
//
//			workbook.setSheetName(0, "排班安排导入模板");
//			try {
//				//一个流 两个头
//				//文件名称
//				String filename = "排班安排导入模板.xls";
//				response.setContentType("application/ms-excel;charset=UTF-8");
//				response.setCharacterEncoding("UTF-8");
//				String encodedFileName = null;
//				if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
//					encodedFileName = URLEncoder.encode(filename, "UTF-8");
//				} else {
//					encodedFileName = new String(filename.getBytes("UTF-8"), "ISO8859-1");
//				}
//				response.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName);//设置文件头编码方式和文件名
//				OutputStream out = response.getOutputStream();
//				workbook.write(out);
//			} catch (Exception e) {
//				logger.info("下载模板报错误:"+e);
//			}
//		}catch (Exception e){
//			logger.info("下载模板报错误:"+e);
//		}
//	}


	/**
	 * ~~~~~~~~~溺水的鱼~~~~~~~~
	 * @Author: 吴强
	 * @Date: 2024/10/24 10:43
	 * @Description: 重构的模板导出--排班导入模板
	 */
	@RequestMapping(value = "/expertSchTemp")
	@ResponseBody
	public void expertSchTemp(HttpServletRequest request, HttpServletResponse response,String rotationFlow) throws IOException {
		schArrangeResultBiz.expertSchTemp(request,response,rotationFlow);
	}


	/**
	 * firstRow 起始行 下标0开始
	 * lastRow  结束行 默认为最大65535
	 * firstCol 区域中第一个单元格的列号 (下标0开始)
	 * lastCol 区域中最后一个单元格的列号
	 * strings 下拉内容
	 * */
	private void selectList(Workbook workbook, int firstCol, int lastCol, String[] strings ){
		//超过20行下拉框会出问题， String literals in formulas can't be bigger than 255 characters ASCII 需要else单独处理
		if(strings.length<=20){
			Sheet sheet = workbook.getSheetAt(0);
			//  生成下拉列表
			//  只对(x，x)单元格有效
			CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(1, 65535, firstCol, lastCol);
			//  生成下拉框内容
			DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(strings);
			HSSFDataValidation dataValidation = new HSSFDataValidation(cellRangeAddressList, dvConstraint);
			//  对sheet页生效
			sheet.addValidationData(dataValidation);
		}else {
			//将下拉框数据放到新的sheet里，然后excle通过新的sheet数据加载下拉框数据
			Sheet hidden = workbook.createSheet("hidden");
			//创建单元格对象
			Cell cell =null;
			//遍历我们上面的数组，将数据取出来放到新sheet的单元格中
			for (int i = 0, length = strings.length; i < length; i++){
				//取出数组中的每个元素
				String name = strings[i];
				//根据i创建相应的行对象（说明我们将会把每个元素单独放一行）
				Row row = hidden.createRow(i);
				//创建每一行中的第一个单元格
				cell = row.createCell(0);
				//然后将数组中的元素赋值给这个单元格
				cell.setCellValue(name);
			}
			// 创建名称，可被其他单元格引用
			Name namedCell = workbook.createName();
			namedCell.setNameName("hidden");
			// 设置名称引用的公式
			namedCell.setRefersToFormula("hidden"+"!$A$1:$A$" + strings.length);
			//加载数据,将名称为hidden的sheet中的数据转换为List形式
			DVConstraint constraint = DVConstraint.createFormulaListConstraint("hidden");

			// 设置第一列的3-65534行为下拉列表
			// (3, 65534, 2, 2) ====> (起始行,结束行,起始列,结束列)
			CellRangeAddressList regions = new CellRangeAddressList(1, 65535, firstCol, lastCol);
			// 将设置下拉选的位置和数据的对应关系 绑定到一起
			DataValidation dataValidation = new HSSFDataValidation(regions, constraint);

			//将第二个sheet设置为隐藏
			workbook.setSheetHidden(1, true);
			//将数据赋给下拉列表
			workbook.getSheetAt(0).addValidationData(dataValidation);
		}
	}


	@RequestMapping(value = {"/importSchedulingAuditExcel"}, method = {RequestMethod.POST})
	@ResponseBody
	public String importSchedulingAuditExcel(MultipartFile file,String rotationFlow) {
		int count = 0;
		String msg = "";
		if (file.getSize() > 0) {
			try {
				Map<String,Object> resultMap = schArrangeResultBiz.importSchedulingAuditExcel(file,rotationFlow,"");
				if(null != resultMap){
					count = (Integer) resultMap.get("count");
					msg = resultMap.get("msg").toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
			if (count != 0) {
				if(StringUtil.isNotEmpty(msg)){
					return GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录!<br>"+msg;
				}
				return GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
			}
		}
		return GlobalConstant.UPLOAD_FAIL+msg;
	}


	/**
	 * ~~~~~~~~~溺水的鱼~~~~~~~~
	 * @Author: 吴强
	 * @Date: 2024/9/18 15:04
	 * @Description: 解析excel数据,此处解析excel导入的文件表头和数据
	 */
	@RequestMapping(value = {"/parseSchedulingAuditExcelCache"}, method = {RequestMethod.POST})
	@ResponseBody
	public String importSchedulingAuditExcelCache(MultipartFile file,Model model,HttpServletRequest request){
		logger.info("导入文件：：：：：：：：：：：：：：：：：：：：：：：：：：");
		Map<String, String> map = new HashMap<>();
		List<String> headers = new ArrayList<>();
		List<Map<Object, Object>> data = new ArrayList<>();
		map.put("head1",JSONArray.toJSONString(headers));
		map.put("head2",JSONArray.toJSONString(headers));
		map.put("data",JSONArray.toJSONString(data));
		HttpSession session = request.getSession();
		session.setAttribute("map",map);
		if (file.isEmpty()) {
			return "success";
		}
		try {
			Map<String, String> stringObjectMap = schArrangeResultBiz.importSchedulingAuditExcelCache(file);
			map.put("head1",stringObjectMap.get("head1"));
			map.put("head2",stringObjectMap.get("head2"));
			map.put("data",stringObjectMap.get("data"));
			session.setAttribute("map",map);
			return "success";
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
//			return map;
			return "success";
		}
	}

//	@PostMapping("/updateItemImportData")
//	@ResponseBody
//	public Map<String,Object> updateItemImportData(@RequestBody List<Map<String, ArrangTdVo>> data){
//		return schArrangeResultBiz.updateImportData(data);
//	}

	@PostMapping("/updateItemImportData")
	@ResponseBody
	public Map<String,Object> checkRowData(@RequestBody List<SchedulingDataModel> data){
		return schArrangeResultBiz.checkRowData(data);
	}

	@PostMapping("/submitPbImport")
	@ResponseBody
	public Map<String,Object> submitPbImport(@RequestBody List<SchedulingDataModel> data) throws Exception {
		return schArrangeResultBiz.submitPbImport(data);
	}


	@PostMapping("/saveDbImportArrang")
	@ResponseBody
	public Map<String,Object> saveDbImportArrang(@RequestBody List<Map<String, ArrangTdVo>> data) throws ParseException {
		return schArrangeResultBiz.saveDbImportArrang(data);
	}

	@PostMapping("/submitImportData")
	@ResponseBody
	public Map<String,Object> submitImportData(@RequestBody List<Map<String, ArrangTdVo>> data){
		return schArrangeResultBiz.submitImportData(data);
	}

	@RequestMapping(value = {"/importSchedulingAuditExcelAcc"}, method = {RequestMethod.POST})
	@ResponseBody
	public String importSchedulingAuditExcelAcc(MultipartFile file,String rotationFlow) {
		int count = 0;
		String msg = "";
		if (file.getSize() > 0) {
			try {
				Map<String,Object> resultMap = schArrangeResultBiz.importSchedulingAuditExcel(file,rotationFlow,TrainCategoryEnum.AssiGeneral.getTypeId());
				if(null != resultMap){
					count = (Integer) resultMap.get("count");
					msg = resultMap.get("msg").toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
			if (count != 0) {
				if(StringUtil.isNotEmpty(msg)){
					return GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录!<br>"+msg;
				}
				return GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
			}
		}
		return GlobalConstant.UPLOAD_FAIL+msg;
	}


}
