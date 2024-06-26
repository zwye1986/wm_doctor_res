package com.pinde.sci.ctrl.hbzy;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbzy.IJszyResBaseBiz;
import com.pinde.sci.biz.hbzy.IJszyResOrgSpeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.res.IResRecBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sch.ISchMonthlyReportBiz;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.biz.sch.impl.SchRotationGroupBizImpl;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SchRotationDeptMapper;
import com.pinde.sci.dao.base.SysDeptMapper;
import com.pinde.sci.enums.hbzy.JszyResDoctorAuditStatusEnum;
import com.pinde.sci.enums.res.AfterRecTypeEnum;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import com.pinde.sci.enums.sch.SchUnitEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.hbzy.JszyBaseExtInfoForm;
import com.pinde.sci.form.hbzy.JszyBaseInfoForm;
import com.pinde.sci.form.hbzy.JszyResOrgSpeForm;
import com.pinde.sci.model.hbzy.JszyResBaseExt;
import com.pinde.sci.model.jszy.JszyResJointOrgExt;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.SchProcessExt;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author PPBear
 *
 */
@Controller
@RequestMapping("/hbzy/base")
public class HbzyResBaseManagerController extends GeneralController {
	final static String LING = "0";
	final static String JXCF = "1";
	final static String YN = "2";
	final static String WZBLTL = "3";
	final static String XSJZ = "4";
	final static String SWBLTL = "5";
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IJszyResBaseBiz baseBiz;
	@Autowired
	private IResJointOrgBiz resJointOrgBiz;
	@Autowired
	private IJszyResOrgSpeBiz resOrgSpeBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private IResDoctorProcessBiz doctorProcessBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private SysDeptMapper sysDeptMapper;
	@Autowired
	private ISchMonthlyReportBiz schMonthlyReportBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private SchRotationGroupBizImpl rotationGroupBiz;
	@Autowired
	private SchRotationDeptMapper rotationDeptMapper;


	@RequestMapping("/baseAudit")
	@ResponseBody
	public String baseAudit(String baseFlow,String status){
		ResBase resBase=new ResBase();
		resBase.setOrgFlow(baseFlow);
		if (GlobalConstant.FLAG_N.equals(status)) {
			resBase.setBaseStatusId(JszyResDoctorAuditStatusEnum.NotPassed.getId());
			resBase.setBaseStatusName(JszyResDoctorAuditStatusEnum.NotPassed.getName());
		}
		int result=baseBiz.saveResBase(resBase);
		if (GlobalConstant.ZERO_LINE!=result) {
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 查找协同基地（未用）
	 * @param model
	 * @return
	 */
	@RequestMapping("/findCoopBase")
	public String findCoopBase(Model model){
		SysUser user=GlobalContext.getCurrentUser();
		Map<Object,Object> jointOrgMap=new HashMap<Object,Object>();
		List<ResJointOrg> jointOrgs = resJointOrgBiz.searchResJointByOrgFlow(user.getOrgFlow());
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(ResJointOrg org:jointOrgs){
				String key = org.getJointOrgFlow();
				SysOrg sysOrg=orgBiz.readSysOrg(org.getJointOrgFlow());
				jointOrgMap.put(key, sysOrg);
			}
		}
		model.addAttribute("jointOrgs", jointOrgs);
		model.addAttribute("jointOrgMap", jointOrgMap);
		return  "hbzy/hospital/hos/editCoopBase";
	}

	/**
	 * 市厅查找基地信息
	 */
	@RequestMapping("/findBaseInfo")
	public ModelAndView findBaseInfo(String role,Integer currentPage,ResBase base, SysOrg org,  HttpServletRequest request, Model model,String auditFlag){
		SysUser user= GlobalContext.getCurrentUser();
		SysOrg sOrg =new SysOrg();
		List<SysOrg>orgList=new ArrayList<SysOrg>();
		List<String>orgFlowList=new ArrayList<String>();
		ModelAndView mav=new ModelAndView();
		SysOrg sysOrg=orgBiz.readSysOrg(user.getOrgFlow());
		sOrg.setOrgProvId(sysOrg.getOrgProvId());
		if(GlobalConstant.USER_LIST_CHARGE.equals(role)){
			sOrg.setOrgCityId(sysOrg.getOrgCityId());
		}
		sOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		orgList=orgBiz.searchOrg(sOrg);
		for(SysOrg o:orgList){
			orgFlowList.add(o.getOrgFlow());
		}
		if (GlobalConstant.FLAG_Y.equals(auditFlag)) {
			base.setBaseStatusId(JszyResDoctorAuditStatusEnum.Auditing.getId());
		}
		Map<String, Object> paramMap = new HashMap<String, Object> ();
		paramMap.put("org",org);
		paramMap.put("base",base);
		paramMap.put("orgFlowList", orgFlowList);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JszyResBaseExt> resBaseExtList = baseBiz.searchResBaseExtList(paramMap);
		model.addAttribute("resBaseExtList", resBaseExtList);
		if (GlobalConstant.USER_LIST_CHARGE.equals(role)) {
			if (GlobalConstant.FLAG_Y.equals(auditFlag)) {
				mav.setViewName("hbzy/city/hospital/auditHospitals");
			}else{
				mav.setViewName("hbzy/hospitalList");
			}
		}
		if (GlobalConstant.USER_LIST_PROVINCE.equals(role)) {
			model.addAttribute("resBaseExtList", resBaseExtList);
			if (GlobalConstant.FLAG_Y.equals(auditFlag)) {
				mav.setViewName("hbzy/province/hospital/auditHospitals");
			}else{
				mav.setViewName("hbzy/hospitalList");
			}
		}
		return mav;
	}

	/**
	 * 查找专业基地
	 * @param model
	 * @return
	 */
	@RequestMapping("/findTrainSpe")
	public ModelAndView findTrainSpe(Model model,String trainCategoryType,String orgFlow,String editFlag){
		ModelAndView mav=new ModelAndView();
		ResOrgSpe search = new ResOrgSpe();
		search.setOrgFlow(orgFlow);
		search.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<ResOrgSpe> resSpesList =resOrgSpeBiz.searchResOrgSpeList(search, trainCategoryType);
		if(resSpesList!=null && resSpesList.size()>0){
			Map<String,ResOrgSpe> rbsMap = new HashMap<String,ResOrgSpe>();
			for(ResOrgSpe rbs : resSpesList){
				String key = rbs.getSpeTypeId() + rbs.getSpeId();
				rbsMap.put(key, rbs);
			}
			mav.addObject("rbsMap", rbsMap);
		}
		ResBase resBase =baseBiz.readBase(orgFlow);
		model.addAttribute("resBase", resBase);
		if(resBase!=null){
			if (GlobalConstant.FLAG_Y.equals(editFlag)||(StringUtil.isBlank(resBase.getBaseStatusId())&&getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL))) {
				mav.setViewName("hbzy/hospital/hos/editSpe");
			}else{
				model.addAttribute("baseInfoName", trainCategoryType);
				mav.setViewName("hbzy/city/hospital/speView");
			}
		}else{
			if (GlobalConstant.FLAG_Y.equals(editFlag)) {
				mav.setViewName("hbzy/hospital/hos/editSpe");
			}else{
				ResOrgSpe resOrgSpe=new ResOrgSpe();
				resOrgSpe.setOrgFlow(orgFlow);
				List<ResOrgSpe>exitList=resOrgSpeBiz.searchResOrgSpeList(resOrgSpe, null);
				if (exitList.size()>0&&exitList!=null) {
					model.addAttribute("baseInfoName", trainCategoryType);
					mav.setViewName("hbzy/city/hospital/speView");
				}else{
					mav.setViewName("hbzy/hospital/hos/editSpe");
				}
			}
		}
		return mav;
	}

	/**
	 * 查找信息
	 * @param baseFlow
	 * @param baseInfoName
	 * @param editFlag
	 * @param viewFlag
	 * @return
	 */
	@RequestMapping("/findAllBaseInfo")
	public ModelAndView findAllBaseInfo(String baseFlow,String baseInfoName,String editFlag,String viewFlag){
		ModelAndView mav = new ModelAndView();
		mav.addObject("sysOrg", orgBiz.readSysOrg(baseFlow));
		ResBase resBase = baseBiz.readBase(baseFlow);
		mav.addObject("resBase", resBase);
		if (resBase!=null) {
			String Xml=resBase.getBaseInfo();
			if (StringUtil.isNotBlank(Xml)) {
				JszyBaseExtInfoForm JszyBaseExtInfoForm=JaxbUtil.converyToJavaBean(Xml, JszyBaseExtInfoForm.class);
				if (GlobalConstant.BASIC_INFO.equals(baseInfoName)) {
					mav.addObject("basicInfo", JszyBaseExtInfoForm.getBasicInfo());
				}else if (GlobalConstant.ORG_MANAGE.equals(baseInfoName)) {
					mav.addObject("organizationManage", JszyBaseExtInfoForm.getOrganizationManage());
				}else if (GlobalConstant.TEACH_CONDITION.equals(baseInfoName)) {
					mav.addObject("educationInfo", JszyBaseExtInfoForm.getEducationInfo());
				}else if (GlobalConstant.SUPPORT_CONDITION.equals(baseInfoName)) {
					mav.addObject("supportCondition", JszyBaseExtInfoForm.getSupportCondition());
				}
			}
			if((StringUtil.isBlank(resBase.getBaseStatusId())||GlobalConstant.FLAG_Y.equals(editFlag))&&!GlobalConstant.FLAG_Y.equals(viewFlag)) {
				mav.setViewName("hbzy/hospital/hos/edit"+baseInfoName);
			}else{
				mav.addObject("baseInfoName", baseInfoName);
				mav.setViewName("hbzy/city/hospital/"+baseInfoName.substring(0,1).toLowerCase()+baseInfoName.substring(1, baseInfoName.length()));
			}
		}else{//无记录
			if(GlobalConstant.FLAG_Y.equals(viewFlag)){
				mav.setViewName("hbzy/city/hospital/"+baseInfoName.substring(0,1).toLowerCase()+baseInfoName.substring(1, baseInfoName.length()));
			}else{
				mav.setViewName("hbzy/hospital/hos/edit"+baseInfoName);
			}
		}
		return mav;
	}

	/**
	 * 保存基本信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveBase")
	@ResponseBody
	public String saveBase(String flag, JszyBaseInfoForm form, String index) throws Exception{
		int  result =  baseBiz.saveBaseInfo(flag, form, index);
		if(GlobalConstant.ZERO_LINE !=  result){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	//**********************************   基地专业管理  **************************************************

	/**
	 * 保存专业基地信息
	 * @param form
	 * @return
	 */
	@RequestMapping("/saveTrainSpe")
	@ResponseBody
	public String saveTrainSpe(@RequestBody JszyResOrgSpeForm form, String trainCategoryTypeId){
		String orgFlow = form.getOrgFlow();
		if(StringUtil.isNotBlank(orgFlow)){
			String orgName = form.getOrgName();
			List<ResOrgSpe> resBaseSpeList = form.getOrgSpeList();
			int result= baseBiz.saveTrainSpe(resBaseSpeList,trainCategoryTypeId, orgFlow, orgName);
			if (GlobalConstant.ZERO_LINE != result) {
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 提交操作
	 * @param baseFlow
	 * @return
	 */
	@RequestMapping("/submitBaseInfo")
	@ResponseBody
	public String submitBaseInfo(String baseFlow){
		ResBase resBase=new ResBase();
		resBase.setOrgFlow(baseFlow);
		resBase.setBaseStatusId(JszyResDoctorAuditStatusEnum.Auditing.getId());
		resBase.setBaseStatusName(JszyResDoctorAuditStatusEnum.Auditing.getName());
		int result=baseBiz.saveResBase(resBase);
		if (GlobalConstant.ZERO_LINE!=result) {
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 跳转界面
	 * @return
	 */
	@RequestMapping("/hospitalMain")
	public String hospitalMain(Model model){
		return "hbzy/city/hospital/hospitalMain";
	}

	@RequestMapping("/main")
	public String main(Model model,String baseFlow){
		ResBase resBase=baseBiz.readBase(baseFlow);
		List<ResJointOrg> jointOrgs = resJointOrgBiz.searchResJointByOrgFlow(baseFlow);
		model.addAttribute("jointOrgs", jointOrgs);
		model.addAttribute("resBase",resBase);
		return "hbzy/hospital/hos/main";
	}

	/**
	 * 跳转页面用于选审核专业
	 * @return
	 */
	@RequestMapping("/spePage")
	public String spePage(){
		return "hbzy/city/hospital/trainSpeMainView";
	}

	/**
	 * 跳转页面用于tag跳转
	 * @return
	 */
	@RequestMapping("/trainSpeMain")
	public String trainSpeMain(String orgFlow,Model model){
		if(StringUtil.isNotBlank(orgFlow)){
			ResOrgSpe serach = new ResOrgSpe();
			serach.setOrgFlow(orgFlow);
			serach.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			List<ResOrgSpe> resBaseList = resOrgSpeBiz.searchResOrgSpeList(serach);
			model.addAttribute("resBaseList", resBaseList);
			ResBase resBase=baseBiz.readBase(orgFlow);
			model.addAttribute("resBase", resBase);
		}
		return "hbzy/hospital/hos/trainSpeMain";
	}
	
	/**
	 * 基地专业管理
	 * @param model
	 * @return
	 */
	@RequestMapping("/orgSpeManage")
	public String orgSpeManage(Model model){
		SysOrg sysOrg = new SysOrg();
		sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		SysOrg org=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		sysOrg.setOrgProvId(org.getOrgProvId());
		if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
			sysOrg.setOrgCityId(org.getOrgCityId());
		}
		List<SysOrg> orgList = orgBiz.searchOrg(sysOrg);
		model.addAttribute("orgList", orgList);
		return "hbzy/system/sys/baseSpecialList";
	}

	/**
	 * 加载基地专业信息
	 * @param resOrgSpe
	 * @param model
	 * @return
	 */
	@RequestMapping("/serachOrgSpeList")
	public String serachOrgSpeList(ResOrgSpe resOrgSpe, Model model){
		String orgFlow = resOrgSpe.getOrgFlow();
		model.addAttribute("orgFlow", orgFlow);
		model.addAttribute("orgName", resOrgSpe.getOrgName());
		if(StringUtil.isNotBlank(orgFlow)){
			ResOrgSpe serach = new ResOrgSpe();
			serach.setOrgFlow(orgFlow);
			serach.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			List<ResOrgSpe> resBaseList = resOrgSpeBiz.searchResOrgSpeList(serach);
			model.addAttribute("resBaseList", resBaseList);
			if(resBaseList != null && !resBaseList.isEmpty()){
				Map<String, ResOrgSpe> orgSpeMap = new HashMap<String, ResOrgSpe>();
				for(ResOrgSpe os : resBaseList){
					String key = os.getSpeTypeId() + os.getSpeId();
					orgSpeMap.put(key, os);
				}
				model.addAttribute("orgSpeMap", orgSpeMap);
			}
		}
		return "hbzy/system/sys/loadOrgSpeList";
	}

	/**
	 * 保存基地专业信息
	 * @param orgSpe
	 * @return
	 */
	@RequestMapping("/saveOrgSpeManage")
	@ResponseBody
	public String saveOrgSpeManage(ResOrgSpe orgSpe){
		int result = resOrgSpeBiz.saveOrgSpeManage(orgSpe);
		if (GlobalConstant.ZERO_LINE != result) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping("/searchJOrgInfo")
	public String searchJOrgInfo(Model model,String orgFlow){
		List<String>orgFlowList=new ArrayList<String>();
		if(StringUtil.isNotBlank(orgFlow)){
			List<ResJointOrg>jointOrgList=jointOrgBiz.searchResJointByOrgFlow(orgFlow);
			if(jointOrgList!=null&&!jointOrgList.isEmpty()){
				for(ResJointOrg org:jointOrgList){
					orgFlowList.add(org.getJointOrgFlow());
				}
				List<SysOrg>orgList=orgBiz.searchOrgFlowIn(orgFlowList);
				model.addAttribute("orgList", orgList);
			}
		}
		return "hbzy/global/hospital/basicGeneralDoc";
	}

	@RequestMapping("/baseInfo")
	public String baseInfo(Model model,String type,Integer currentPage,HttpServletRequest request){
		Map<String,String> jointFlagMap=new HashMap<String,String>();
		SysOrg sysOrg=new SysOrg();
		SysUser currUser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(currUser.getOrgFlow());
		sysOrg.setOrgProvId(org.getOrgProvId());
		if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
			sysOrg.setOrgCityId(org.getOrgCityId());
		}
		sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		if(StringUtil.isNotBlank(type)){
			if(OrgLevelEnum.CountryOrg.getId().equals(type)){
				sysOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
			}else if(OrgLevelEnum.ProvinceOrg.getId().equals(type)){
				sysOrg.setOrgLevelId(OrgLevelEnum.ProvinceOrg.getId());
			}
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<SysOrg>orgList=orgBiz.searchAllSysOrg(sysOrg);
        Map<String,List<JszyResJointOrgExt>> jointOrgExtMap = new HashMap<String,List<JszyResJointOrgExt>>();
		if(orgList!=null&&!orgList.isEmpty()){
			for(SysOrg o:orgList){
				List<JszyResJointOrgExt> jointOrgs=jointOrgBiz.findJointOrgExtByOrgFlow(o.getOrgFlow());
				if(jointOrgs!=null&&!jointOrgs.isEmpty()){
                    jointOrgExtMap.put(o.getOrgFlow(),jointOrgs);
				}
			}
		}
		model.addAttribute("jointMap", jointOrgExtMap);
		model.addAttribute("orgList", orgList);
		return "hbzy/global/hospital/basicGeneralDoc";
	}



	@RequestMapping(value="/verification")
	@ResponseBody
	public String verification(String resultFlow){
		SysUser user=GlobalContext.getCurrentUser();
		ResDoctorSchProcess doctorSchProcess= doctorProcessBiz.searchByResultFlow(resultFlow);
		String afterEvaluationId=ResRecTypeEnum.AfterEvaluation.getId();
		if (doctorSchProcess!=null) {
			List<ResRec> recs=resRecBiz.searchResRecWithBLOBs(afterEvaluationId,doctorSchProcess.getProcessFlow(),user.getUserFlow());
			if (recs.size()>0) {
				return GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;

	}
	
	@RequestMapping(value="/relationDownload")
	public void relationDownload(String resultFlow,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//定义数据容器
		Map<String, Object> dataMap = new HashMap<String, Object>();

		//获取当前用户
		SysUser user=GlobalContext.getCurrentUser();

		//新建word模板
		WordprocessingMLPackage temeplete = new WordprocessingMLPackage();

		//context获取路径
		ServletContext context =  request.getServletContext();

		//水印
		String watermark = "";

		//文件名称
		String name="";

		//获取个人基础信息
		ResDoctor doctor=resDoctorBiz.searchByUserFlow(user.getUserFlow());
		dataMap.put("userName",user.getUserName());
		dataMap.put("sessionNumber",doctor.getSessionNumber());
		dataMap.put("trainingSpeName",doctor.getTrainingSpeName());

		//轮转科室名称
		SchArrangeResult schArrangeResult=resultBiz.readSchArrangeResult(resultFlow);
		if (schArrangeResult!=null) {
			dataMap.put("startDate", StringUtil.defaultIfEmpty(schArrangeResult.getSchStartDate(), " "));
			dataMap.put("endDate", StringUtil.defaultIfEmpty(schArrangeResult.getSchEndDate(), " "));
			dataMap.put("standardDeptName",schArrangeResult.getDeptName());
			dataMap.put("monthCount",schArrangeResult.getSchMonth());
		}

		//获取resrec数据
		ResDoctorSchProcess doctorSchProcess= doctorProcessBiz.searchByResultFlow(resultFlow);
		String processFlow=doctorSchProcess.getProcessFlow();
		String operUserFlow=user.getUserFlow();
		Map<String,Object> processPerMap=resRecBiz.getRecProgressIn(operUserFlow,processFlow,null,null);
		if(processPerMap == null){
			processPerMap = new HashMap<String, Object>();
		}
		//获取不同类型并定义接受
		if(processPerMap!=null){
			String caseRegistryId=ResRecTypeEnum.CaseRegistry.getId();
			String diseaseRegistryId=ResRecTypeEnum.DiseaseRegistry.getId();
			String skillRegistryId=ResRecTypeEnum.SkillRegistry.getId();
			String operationRegistryId=ResRecTypeEnum.OperationRegistry.getId();

			String caseRegistry=(String)processPerMap.get(processFlow+caseRegistryId);
			String caseRegistryReqNum=(String)processPerMap.get(processFlow+caseRegistryId+"ReqNum");
			String caseRegistryFinished=(String)processPerMap.get(processFlow+caseRegistryId+"Finished");

			String diseaseRegistry=(String)processPerMap.get(processFlow+diseaseRegistryId);
			String diseaseRegistryReqNum=(String)processPerMap.get(processFlow+diseaseRegistryId+"ReqNum");
			String diseaseRegistryFinished=(String)processPerMap.get(processFlow+diseaseRegistryId+"Finished");

			String skillRegistry=(String)processPerMap.get(processFlow+skillRegistryId);
			String skillRegistryReqNum=(String)processPerMap.get(processFlow+skillRegistryId+"ReqNum");
			String skillRegistryFinished=(String)processPerMap.get(processFlow+skillRegistryId+"Finished");

			String skillAndOperationRegistry=(String)processPerMap.get(processFlow+operationRegistryId);
			String skillAndOperationRegistryReqNum=(String)processPerMap.get(processFlow+operationRegistryId+"ReqNum");
			String skillAndOperationRegistryFinished=(String)processPerMap.get(processFlow+operationRegistryId+"Finished");

			String recTypeIdt=ResRecTypeEnum.CampaignRegistry.getId();
			int teachingRounds=0;
			int difficult=0;
			int lecture=0;
			int death=0;
			List<String> recTypes=new ArrayList<String>();
			recTypes.add(recTypeIdt);
			List<ResRec> recs= resRecBiz.searchRecByProcessWithBLOBs(recTypes,processFlow,user.getUserFlow());
			for (ResRec resRec : recs) {
				String content=resRec.getRecContent();
				Document document=DocumentHelper.parseText(content);
				Element root=document.getRootElement();
				Element ec = root.element("activity_way");
				if (ec!=null) {
					String text=ec.attributeValue("id");
					if(JXCF.equals(text)){
						teachingRounds++;
					}else if(YN.equals(text) || WZBLTL.equals(text)){
						difficult++;
					}else if(XSJZ.equals(text)){
						lecture++;
					}else if(SWBLTL.equals(text)){
						death++;
					}
				}
			}
			List<TeachingActivityInfo> infos=resRecBiz.searchJoinActivityByProcessFlow(processFlow);
			if(infos!=null&&infos.size()>0)
			{
				for(TeachingActivityInfo info:infos)
				{
					String text=info.getActivityTypeId();
					if(JXCF.equals(text)){
						teachingRounds++;
					}else if(YN.equals(text) || WZBLTL.equals(text)){
						difficult++;
					}else if(XSJZ.equals(text)){
						lecture++;
					}else if(SWBLTL.equals(text)){
						death++;
					}
				}
			}
			dataMap.put("userName",user.getUserName());
			dataMap.put("sessionNumber",doctor.getSessionNumber());
			dataMap.put("trainingSpeName",doctor.getTrainingSpeName());

			dataMap.put("caseRegistry",StringUtil.defaultIfEmpty(caseRegistry, "0"));
			dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(caseRegistryReqNum, "0"));
			dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(caseRegistryFinished, "0"));

			dataMap.put("diseaseRegistry",StringUtil.defaultIfEmpty(diseaseRegistry, "0"));
			dataMap.put("diseaseRegistryReqNum",StringUtil.defaultIfEmpty(diseaseRegistryReqNum, "0"));
			dataMap.put("diseaseRegistryFinished", StringUtil.defaultIfEmpty(diseaseRegistryFinished, "0"));

			dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(skillRegistry, "0"));
			dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(skillRegistryReqNum, "0"));
			dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(skillRegistryFinished, "0"));

			dataMap.put("operationRegistry", StringUtil.defaultIfEmpty(skillAndOperationRegistry, "0"));
			dataMap.put("operationRegistryReqNum", StringUtil.defaultIfEmpty(skillAndOperationRegistryReqNum, "0"));
			dataMap.put("operationRegistryFinished", StringUtil.defaultIfEmpty(skillAndOperationRegistryFinished, "0"));

			dataMap.put("teachingRounds",String.valueOf(teachingRounds));
			dataMap.put("difficult",String.valueOf(difficult));
			dataMap.put("lecture",String.valueOf(lecture));
			dataMap.put("death",String.valueOf(death));
		}
		String afterEvaluationId=ResRecTypeEnum.AfterEvaluation.getId();
		List<ResRec> recs=null;
		if (doctorSchProcess!=null) {
			 recs=resRecBiz.searchResRecWithBLOBs(afterEvaluationId,doctorSchProcess.getProcessFlow(),user.getUserFlow());
		}
		if (recs!=null && recs.size()>0) {
			ResRec resRecs=recs.get(0);
			Map<String,Object> formDataMap = resRecBiz.parseRecContent(resRecs.getRecContent());
			dataMap.put("attendance", StringUtil.defaultIfEmpty((String)formDataMap.get("attendance"), " "));
			dataMap.put("leave", StringUtil.defaultIfEmpty((String)formDataMap.get("leave"), " "));
			dataMap.put("sickLeave", StringUtil.defaultIfEmpty((String)formDataMap.get("sickLeave"), " "));
			dataMap.put("materLeave", StringUtil.defaultIfEmpty((String)formDataMap.get("materLeave"), " "));
			dataMap.put("absenteeism", StringUtil.defaultIfEmpty((String)formDataMap.get("absenteeism"), " "));
//
//			dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(formDataMap.get("blsywc"), "0"));
//			dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(formDataMap.get("blsyjwc"), "0"));
//			dataMap.put("caseRegistry",StringUtil.defaultIfEmpty(formDataMap.get("blswcbl"), "0"));
//
//
//			dataMap.put("diseaseRegistry",StringUtil.defaultIfEmpty(formDataMap.get("bzswcbl"), "0"));
//			dataMap.put("diseaseRegistryReqNum",StringUtil.defaultIfEmpty(formDataMap.get("bzsywc"), "0"));
//			dataMap.put("diseaseRegistryFinished", StringUtil.defaultIfEmpty(formDataMap.get("bzsyjwc"), "0"));
//
//			dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(formDataMap.get("czswcbl"), "0"));
//			dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(formDataMap.get("czsywc"), "0"));
//			dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(formDataMap.get("czsyjwc"), "0"));
//
//			dataMap.put("operationRegistry", StringUtil.defaultIfEmpty(formDataMap.get("ssswcbl"), "0"));
//			dataMap.put("operationRegistryReqNum", StringUtil.defaultIfEmpty(formDataMap.get("sssywc"), "0"));
//			dataMap.put("operationRegistryFinished", StringUtil.defaultIfEmpty(formDataMap.get("sssyjwc"), "0"));
			
			String zsgjflfg = (String)formDataMap.get("zsgjflfg_id");
			if(JXCF.equals(zsgjflfg)){
				dataMap.put("zsgjflfgJXCF","√");
			}
			if(YN.equals(zsgjflfg)){
				dataMap.put("zsgjflfgYN","√");
			}
			if(WZBLTL.equals(zsgjflfg)){
				dataMap.put("zsgjflfgWZBLTL","√");
			}
			if(XSJZ.equals(zsgjflfg)){
				dataMap.put("zsgjflfgXSJZ","√");
			}
			String lxgwzz = (String)formDataMap.get("lxgwzz_id");
			if(JXCF.equals(lxgwzz)){
				dataMap.put("lxgwzzJXCF","√");
			}
			if(YN.equals(lxgwzz)){
				dataMap.put("lxgwzzYN","√");
			}
			if(WZBLTL.equals(lxgwzz)){
				dataMap.put("lxgwzzWZBLTL","√");
			}
			if(XSJZ.equals(lxgwzz)){
				dataMap.put("lxgwzzXSJZ","√");
			}
			String ybrwzx = (String)formDataMap.get("ybrwzx_id");
			if(JXCF.equals(ybrwzx)){
				dataMap.put("ybrwzxJXCF","√");
			}
			if(YN.equals(ybrwzx)){
				dataMap.put("ybrwzxYN","√");
			}
			if(WZBLTL.equals(ybrwzx)){
				dataMap.put("ybrwzxWZBLTL","√");
			}
			if(XSJZ.equals(lxgwzz)){
				dataMap.put("ybrwzxXSJZ","√");
			}
			String rjgtbdnl =(String) formDataMap.get("rjgtbdnl_id");
			if(JXCF.equals(rjgtbdnl)){
				dataMap.put("rjgtbdnlJXCF","√");
			}
			if(YN.equals(rjgtbdnl)){
				dataMap.put("rjgtbdnlYN","√");
			}
			if(WZBLTL.equals(rjgtbdnl)){
				dataMap.put("rjgtbdnlWZBLTL","√");
			}
			if(XSJZ.equals(rjgtbdnl)){
				dataMap.put("rjgtbdnlXSJZ","√");
			}
			String tjxzjsxm = (String)formDataMap.get("tjxzjsxm_id");
			if(JXCF.equals(tjxzjsxm)){
				dataMap.put("tjxzjsxmJXCF","√");
			}
			if(YN.equals(tjxzjsxm)){
				dataMap.put("tjxzjsxmYN","√");
			}
			if(WZBLTL.equals(tjxzjsxm)){
				dataMap.put("tjxzjsxmWZBLTL","√");
			}
			if(XSJZ.equals(tjxzjsxm)){
				dataMap.put("tjxzjsxmXSJZ","√");
			}
			String jbllzwcd = (String)formDataMap.get("jbllzwcd_id");
			if(JXCF.equals(jbllzwcd)){
				dataMap.put("jbllzwcdJXCF","√");
			}
			if(YN.equals(jbllzwcd)){
				dataMap.put("jbllzwcdYN","√");
			}
			if(WZBLTL.equals(jbllzwcd)){
				dataMap.put("jbllzwcdWZBLTL","√");
			}
			if(XSJZ.equals(jbllzwcd)){
				dataMap.put("jbllzwcdXSJZ","√");
			}
			String njbjnzwcd = (String)formDataMap.get("njbjnzwcd_id");
				if(JXCF.equals(njbjnzwcd)){
					dataMap.put("njbjnzwcdJXCF","√");
				}
				if(YN.equals(njbjnzwcd)){
					dataMap.put("njbjnzwcdYN","√");
				}
				if(WZBLTL.equals(njbjnzwcd)){
					dataMap.put("njbjnzwcdWZBLTL","√");
				}
				if(XSJZ.equals(njbjnzwcd)){
					dataMap.put("njbjnzwcdXSJZ","√");
				}
				
			String lcswnl = (String)formDataMap.get("lcswnl_id");
				if(JXCF.equals(lcswnl)){
					dataMap.put("lcswnlJXCF","√");
				}
				if(YN.equals(lcswnl)){
					dataMap.put("lcswnlYN","√");
				}
				if(WZBLTL.equals(lcswnl)){
					dataMap.put("lcswnlWZBLTL","√");
				}
				if(XSJZ.equals(lcswnl)){
					dataMap.put("lcswnlXSJZ","√");
				}
				
			String lczlnl = (String)formDataMap.get("lczlnl_id");
				if(JXCF.equals(lczlnl)){
					dataMap.put("lczlnlJXCF","√");
				}
				if(YN.equals(lczlnl)){
					dataMap.put("lczlnlYN","√");
				}
				if(WZBLTL.equals(lczlnl)){
					dataMap.put("lczlnlWZBLTL","√");
				}
				if(XSJZ.equals(lczlnl)){
					dataMap.put("lczlnlXSJZ","√");
				}
			String jjclnl =(String) formDataMap.get("jjclnl_id");
				if(JXCF.equals(jjclnl)){
					dataMap.put("jjclnlJXCF","√");
				}
				if(YN.equals(jjclnl)){
					dataMap.put("jjclnlYN","√");
				}
				if(WZBLTL.equals(jjclnl)){
					dataMap.put("jjclnlWZBLTL","√");
				}
				if(XSJZ.equals(jjclnl)){
					dataMap.put("jjclnlXSJZ","√");
				}
//			dataMap.put("teachingRounds", StringUtil.defaultIfEmpty(formDataMap.get("jxcb"), "0"));
//			dataMap.put("difficult", StringUtil.defaultIfEmpty(formDataMap.get("nwzbltl"), "0"));
//			dataMap.put("lecture", StringUtil.defaultIfEmpty(formDataMap.get("xsjz"), "0"));
//			dataMap.put("death", StringUtil.defaultIfEmpty(formDataMap.get("swbltl"), "0"));
			
			dataMap.put("theoreResult", StringUtil.defaultIfEmpty((String)formDataMap.get("theoreResult"), " "));
			dataMap.put("skillName", StringUtil.defaultIfEmpty((String)formDataMap.get("skillName"), " "));
			dataMap.put("score", StringUtil.defaultIfEmpty((String)formDataMap.get("score"), " "));
			dataMap.put("examiner1", StringUtil.defaultIfEmpty((String)formDataMap.get("examiner1"), " "));
			dataMap.put("examiner2", StringUtil.defaultIfEmpty((String)formDataMap.get("examiner2"), " "));
			
			String szkskhxzztpj = (String)formDataMap.get("szkskhxzztpj_id");
				if(JXCF.equals(szkskhxzztpj)){
					dataMap.put("szkskhxzztpjJXCF","√");
				}
				if(LING.equals(szkskhxzztpj)){
					dataMap.put("szkskhxzztpjLING","√");
				}
			dataMap.put("teacherName", StringUtil.defaultIfEmpty((String)formDataMap.get("teacherName"), " "));
			dataMap.put("teacherDate", StringUtil.defaultIfEmpty((String)formDataMap.get("teacherDate"), " "));
			dataMap.put("directorName", StringUtil.defaultIfEmpty((String)formDataMap.get("directorName"), " "));
			dataMap.put("directorDate", StringUtil.defaultIfEmpty((String)formDataMap.get("directorDate"), " "));
			
		}
		if (dataMap.get("caseRegistryReqNum")==null||"0".equals(dataMap.get("caseRegistryReqNum"))) {
			dataMap.put("caseRegistryReqNum","0");
			dataMap.put("caseRegistry","100");
		}
		if (dataMap.get("caseRegistryFinished")==null) {
			dataMap.put("caseRegistryFinished","0");
		}
		if (dataMap.get("caseRegistry")==null) {
			dataMap.put("caseRegistry","0");
		}
		if (dataMap.get("diseaseRegistry")==null) {
			dataMap.put("diseaseRegistry","0");
		}
		if (dataMap.get("diseaseRegistryReqNum")==null||"0".equals(dataMap.get("diseaseRegistryReqNum"))) {
			dataMap.put("diseaseRegistryReqNum","0");
			dataMap.put("diseaseRegistry","100");
		}
		if (dataMap.get("diseaseRegistryFinished")==null) {
			dataMap.put("diseaseRegistryFinished","0");
		}
		if (dataMap.get("skillRegistry")==null) {
			dataMap.put("skillRegistry","0");
		}
		if (dataMap.get("skillRegistryReqNum")==null||"0".equals(dataMap.get("skillRegistryReqNum"))) {
			dataMap.put("skillRegistryReqNum","0");
			dataMap.put("skillRegistry","100");
		}
		if (dataMap.get("skillRegistryFinished")==null) {
			dataMap.put("skillRegistryFinished","0");
		}

		if (dataMap.get("operationRegistry")==null) {
			dataMap.put("operationRegistry","0");
		}
		if (dataMap.get("operationRegistryReqNum")==null||"0".equals(dataMap.get("operationRegistryReqNum"))) {
			dataMap.put("operationRegistryReqNum","0");
			dataMap.put("operationRegistry","100");
		}
		if (dataMap.get("operationRegistryFinished")==null) {
			dataMap.put("operationRegistryFinished","0");
		}
		
		if (dataMap.get("zsgjflfgJXCF")==null) {
			dataMap.put("zsgjflfgJXCF","□");
		}
		if (dataMap.get("zsgjflfgYN")==null) {
			dataMap.put("zsgjflfgYN","□");
		}
		if (dataMap.get("zsgjflfgWZBLTL")==null) {
			dataMap.put("zsgjflfgWZBLTL","□");
		}
		if (dataMap.get("zsgjflfgXSJZ")==null) {
			dataMap.put("zsgjflfgXSJZ","□");
		}
		if (dataMap.get("lxgwzzJXCF")==null) {
			dataMap.put("lxgwzzJXCF","□");
		}
		if (dataMap.get("lxgwzzYN")==null) {
			dataMap.put("lxgwzzYN","□");
		}
		if (dataMap.get("lxgwzzWZBLTL")==null) {
			dataMap.put("lxgwzzWZBLTL","□");
		}
		if (dataMap.get("lxgwzzXSJZ")==null) {
			dataMap.put("lxgwzzXSJZ","□");
		}
		if (dataMap.get("ybrwzxJXCF")==null) {
			dataMap.put("ybrwzxJXCF","□");
		}
		if (dataMap.get("ybrwzxYN")==null) {
			dataMap.put("ybrwzxYN","□");
		}
		if (dataMap.get("ybrwzxWZBLTL")==null) {
			dataMap.put("ybrwzxWZBLTL","□");
		}
		if (dataMap.get("ybrwzxXSJZ")==null) {
			dataMap.put("ybrwzxXSJZ","□");
		}
		if (dataMap.get("rjgtbdnlJXCF")==null) {
			dataMap.put("rjgtbdnlJXCF","□");
		}
		if (dataMap.get("rjgtbdnlYN")==null) {
			dataMap.put("rjgtbdnlYN","□");
		}
		if (dataMap.get("rjgtbdnlWZBLTL")==null) {
			dataMap.put("rjgtbdnlWZBLTL","□");
		}
		if (dataMap.get("rjgtbdnlXSJZ")==null) {
			dataMap.put("rjgtbdnlXSJZ","□");
		}
		if (dataMap.get("tjxzjsxmJXCF")==null) {
			dataMap.put("tjxzjsxmJXCF","□");
		}
		if (dataMap.get("tjxzjsxmYN")==null) {
			dataMap.put("tjxzjsxmYN","□");
		}
		if (dataMap.get("tjxzjsxmWZBLTL")==null) {
			dataMap.put("tjxzjsxmWZBLTL","□");
		}
		if (dataMap.get("tjxzjsxmXSJZ")==null) {
			dataMap.put("tjxzjsxmXSJZ","□");
		}
		if (dataMap.get("jbllzwcdJXCF")==null) {
			dataMap.put("jbllzwcdJXCF","□");
		}
		if (dataMap.get("jbllzwcdYN")==null) {
			dataMap.put("jbllzwcdYN","□");
		}
		if (dataMap.get("jbllzwcdWZBLTL")==null) {
			dataMap.put("jbllzwcdWZBLTL","□");
		}
		if (dataMap.get("jbllzwcdXSJZ")==null) {
			dataMap.put("jbllzwcdXSJZ","□");
		}
		if (dataMap.get("njbjnzwcdJXCF")==null) {
			dataMap.put("njbjnzwcdJXCF","□");
		}
		if (dataMap.get("njbjnzwcdYN")==null) {
			dataMap.put("njbjnzwcdYN","□");
		}
		if (dataMap.get("njbjnzwcdWZBLTL")==null) {
			dataMap.put("njbjnzwcdWZBLTL","□");
		}
		if (dataMap.get("njbjnzwcdXSJZ")==null) {
			dataMap.put("njbjnzwcdXSJZ","□");
		}
		if (dataMap.get("lcswnlJXCF")==null) {
			dataMap.put("lcswnlJXCF","□");
		}
		if (dataMap.get("lcswnlYN")==null) {
			dataMap.put("lcswnlYN","□");
		}
		if (dataMap.get("lcswnlWZBLTL")==null) {
			dataMap.put("lcswnlWZBLTL","□");
		}
		if (dataMap.get("lcswnlXSJZ")==null) {
			dataMap.put("lcswnlXSJZ","□");
		}
		if (dataMap.get("lczlnlJXCF")==null) {
			dataMap.put("lczlnlJXCF","□");
		}
		if (dataMap.get("lczlnlYN")==null) {
			dataMap.put("lczlnlYN","□");
		}
		if (dataMap.get("lczlnlWZBLTL")==null) {
			dataMap.put("lczlnlWZBLTL","□");
		}
		if (dataMap.get("lczlnlXSJZ")==null) {
			dataMap.put("lczlnlXSJZ","□");
		}
		if (dataMap.get("jjclnlJXCF")==null) {
			dataMap.put("jjclnlJXCF","□");
		}
		if (dataMap.get("jjclnlYN")==null) {
			dataMap.put("jjclnlYN","□");
		}
		if (dataMap.get("jjclnlWZBLTL")==null) {
			dataMap.put("jjclnlWZBLTL","□");
		}
		if (dataMap.get("jjclnlXSJZ")==null) {
			dataMap.put("jjclnlXSJZ","□");
		}
		if (dataMap.get("szkskhxzztpjJXCF")==null) {
			dataMap.put("szkskhxzztpjJXCF","□");
		}
		if (dataMap.get("szkskhxzztpjLING")==null) {
			dataMap.put("szkskhxzztpjLING","□");
		}
		//获取本人所有排班
	/*	List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlowSun(dept.getGroupFlow(),dept.getStandardDeptId(),user.getUserFlow());
		if(resultList!=null && resultList.size()>0){
			Float monthCount = 0f;
			String startDate = null;
			String endDate = null;
			for(SchArrangeResult sar : resultList){
				String month = sar.getSchMonth();
				if(StringUtil.isNotBlank(month)){
					Float currMonth = Float.parseFloat(month);
					if(currMonth!=null){
						monthCount+=currMonth;
					}
					
					if(startDate==null){
						startDate = sar.getSchStartDate();
					}
					
					endDate = sar.getSchEndDate();
				}
				
			}
			
			dataMap.put("monthCount",monthCount.toSt　ring());
			dataMap.put("startDate",startDate);
			dataMap.put("endDate",endDate);
		}*/
		
		
		String path = "/jsp/hbzy/doctor/ckkhb.docx";//模板
		temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
		name = "江苏省住院医师规范化培训临床轮转出科表.docx";
		if(temeplete!=null){
			response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +""); 
			response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			ServletOutputStream out = response.getOutputStream ();
			(new SaveToZipFile (temeplete)).save (out);
			out.flush ();
		}
	}
	
	
	@RequestMapping(value="/download")
	public void download(String recordFlow,HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SysUser user=GlobalContext.getCurrentUser();
		WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
		ServletContext context =  request.getServletContext();
		String watermark = "";
		String name="";
		//获取个人基础信息
		ResDoctor doctor=resDoctorBiz.searchByUserFlow(user.getUserFlow());
		//轮转科室名称
		SchRotationDept	dept=schRotationDeptBiz.readSchRotationDept(recordFlow);
		//百分比Map
		Map<String,String> processPerMap=resRecBiz.getProcessPer(doctor.getDoctorFlow(),doctor.getRotationFlow(),recordFlow);
		
		//获取不同类型并定义接受
		String caseRegistryId=ResRecTypeEnum.CaseRegistry.getId();
		String diseaseRegistryId=ResRecTypeEnum.DiseaseRegistry.getId();
		String skillRegistryId=ResRecTypeEnum.SkillRegistry.getId();
		String operationRegistryId=ResRecTypeEnum.OperationRegistry.getId();
		
		String caseRegistry=processPerMap.get(recordFlow+caseRegistryId);
		String caseRegistryReqNum=processPerMap.get(recordFlow+caseRegistryId+"ReqNum");
		String caseRegistryFinished=processPerMap.get(recordFlow+caseRegistryId+"Finished");
		
		String diseaseRegistry=processPerMap.get(recordFlow+diseaseRegistryId);
		String diseaseRegistryReqNum=processPerMap.get(recordFlow+diseaseRegistryId+"ReqNum");
		String diseaseRegistryFinished=processPerMap.get(recordFlow+diseaseRegistryId+"Finished");
		
		String skillRegistry=processPerMap.get(recordFlow+skillRegistryId);
		String skillRegistryReqNum=processPerMap.get(recordFlow+skillRegistryId+"ReqNum");
		String skillRegistryFinished=processPerMap.get(recordFlow+skillRegistryId+"Finished");
		
		String skillAndOperationRegistry=processPerMap.get(recordFlow+operationRegistryId);
		String skillAndOperationRegistryReqNum=processPerMap.get(recordFlow+operationRegistryId+"ReqNum");
		String skillAndOperationRegistryFinished=processPerMap.get(recordFlow+operationRegistryId+"Finished");
		
		String recTypeId=ResRecTypeEnum.CampaignRegistry.getId();
		int teachingRounds=0;
		int difficult=0;
		int lecture=0;
		int death=0;
		List<ResRec> rec= resRecBiz.searchRecByProcess(recTypeId,recordFlow,user.getUserFlow());
		for (ResRec resRec : rec) {
			String content=resRec.getRecContent();
			Document document=DocumentHelper.parseText(content);
			Element root=document.getRootElement();
			Element ec = root.element("activity_way");
			if (ec!=null) {
				String text=ec.attributeValue("id");
				if(JXCF.equals(text)){
					teachingRounds++;
				}else if(YN.equals(text) || WZBLTL.equals(text)){
					difficult++;
				}else if(XSJZ.equals(text)){
					lecture++;
				}else if(SWBLTL.equals(text)){
					death++;
				}
			}
		}
		List<TeachingActivityInfo> infos=resRecBiz.searchJoinActivityByStrandDeptFlow(doctor.getDoctorFlow(),recordFlow);
		if(infos!=null&&infos.size()>0)
		{
			for(TeachingActivityInfo info:infos)
			{
				String text=info.getActivityTypeId();
				if(JXCF.equals(text)){
					teachingRounds++;
				}else if(YN.equals(text) || WZBLTL.equals(text)){
					difficult++;
				}else if(XSJZ.equals(text)){
					lecture++;
				}else if(SWBLTL.equals(text)){
					death++;
				}
			}
		}
		//获取resrec数据
		String afterEvaluationId=ResRecTypeEnum.AfterEvaluation.getId();
		List<ResRec> recs=resRecBiz.searchResRecWithBLOBsByRotationDeptFlow(afterEvaluationId,recordFlow,user.getUserFlow());
		if (recs!=null && recs.size()>0) {
			ResRec resRecs = recs.get(0);
			Map<String, Object> formDataMap = resRecBiz.parseRecContent(resRecs.getRecContent());
			dataMap.put("attendance", StringUtil.defaultIfEmpty((String) formDataMap.get("attendance"), " "));
			dataMap.put("leave", StringUtil.defaultIfEmpty((String) formDataMap.get("leave"), " "));
			dataMap.put("sickLeave", StringUtil.defaultIfEmpty((String) formDataMap.get("sickLeave"), " "));
			dataMap.put("materLeave", StringUtil.defaultIfEmpty((String) formDataMap.get("materLeave"), " "));
			dataMap.put("absenteeism", StringUtil.defaultIfEmpty((String) formDataMap.get("absenteeism"), " "));
		}
		/*	String zsgjflfg = formDataMap.get("zsgjflfg_id");
			if(JXCF.equals(zsgjflfg)){
				dataMap.put("zsgjflfgJXCF","√");
			}
			if(YN.equals(zsgjflfg)){
				dataMap.put("zsgjflfgYN","√");
			}
			if(WZBLTL.equals(zsgjflfg)){
				dataMap.put("zsgjflfgWZBLTL","√");
			}
			if(XSJZ.equals(zsgjflfg)){
				dataMap.put("zsgjflfgXSJZ","√");
			}
			String lxgwzz = formDataMap.get("lxgwzz_id");
			if(JXCF.equals(lxgwzz)){
				dataMap.put("lxgwzzJXCF","√");
			}
			if(YN.equals(lxgwzz)){
				dataMap.put("lxgwzzYN","√");
			}
			if(WZBLTL.equals(lxgwzz)){
				dataMap.put("lxgwzzWZBLTL","√");
			}
			if(XSJZ.equals(lxgwzz)){
				dataMap.put("lxgwzzXSJZ","√");
			}
			String ybrwzx = formDataMap.get("ybrwzx_id");
			if(JXCF.equals(ybrwzx)){
				dataMap.put("ybrwzxJXCF","√");
			}
			if(YN.equals(ybrwzx)){
				dataMap.put("ybrwzxYN","√");
			}
			if(WZBLTL.equals(ybrwzx)){
				dataMap.put("ybrwzxWZBLTL","√");
			}
			if(XSJZ.equals(lxgwzz)){
				dataMap.put("ybrwzxXSJZ","√");
			}
			String rjgtbdnl = formDataMap.get("rjgtbdnl_id");
			if(JXCF.equals(rjgtbdnl)){
				dataMap.put("rjgtbdnlJXCF","√");
			}
			if(YN.equals(rjgtbdnl)){
				dataMap.put("rjgtbdnlYN","√");
			}
			if(WZBLTL.equals(rjgtbdnl)){
				dataMap.put("rjgtbdnlWZBLTL","√");
			}
			if(XSJZ.equals(rjgtbdnl)){
				dataMap.put("rjgtbdnlXSJZ","√");
			}
			String tjxzjsxm = formDataMap.get("tjxzjsxm_id");
			if(JXCF.equals(tjxzjsxm)){
				dataMap.put("tjxzjsxmJXCF","√");
			}
			if(YN.equals(tjxzjsxm)){
				dataMap.put("tjxzjsxmYN","√");
			}
			if(WZBLTL.equals(tjxzjsxm)){
				dataMap.put("tjxzjsxmWZBLTL","√");
			}
			if(XSJZ.equals(tjxzjsxm)){
				dataMap.put("tjxzjsxmXSJZ","√");
			}
			String jbllzwcd = formDataMap.get("jbllzwcd_id");
			if(JXCF.equals(jbllzwcd)){
				dataMap.put("jbllzwcdJXCF","√");
			}
			if(YN.equals(jbllzwcd)){
				dataMap.put("jbllzwcdYN","√");
			}
			if(WZBLTL.equals(jbllzwcd)){
				dataMap.put("jbllzwcdWZBLTL","√");
			}
			if(XSJZ.equals(jbllzwcd)){
				dataMap.put("jbllzwcdXSJZ","√");
			}
			String njbjnzwcd = formDataMap.get("njbjnzwcd_id");
				if(JXCF.equals(njbjnzwcd)){
					dataMap.put("njbjnzwcdJXCF","√");
				}
				if(YN.equals(njbjnzwcd)){
					dataMap.put("njbjnzwcdYN","√");
				}
				if(WZBLTL.equals(njbjnzwcd)){
					dataMap.put("njbjnzwcdWZBLTL","√");
				}
				if(XSJZ.equals(njbjnzwcd)){
					dataMap.put("njbjnzwcdXSJZ","√");
				}
				
			String lcswnl = formDataMap.get("lcswnl_id");
				if(JXCF.equals(lcswnl)){
					dataMap.put("lcswnlJXCF","√");
				}
				if(YN.equals(lcswnl)){
					dataMap.put("lcswnlYN","√");
				}
				if(WZBLTL.equals(lcswnl)){
					dataMap.put("lcswnlWZBLTL","√");
				}
				if(XSJZ.equals(lcswnl)){
					dataMap.put("lcswnlXSJZ","√");
				}
				
			String lczlnl = formDataMap.get("lczlnl_id");
				if(JXCF.equals(lczlnl)){
					dataMap.put("lczlnlJXCF","√");
				}
				if(YN.equals(lczlnl)){
					dataMap.put("lczlnlYN","√");
				}
				if(WZBLTL.equals(lczlnl)){
					dataMap.put("lczlnlWZBLTL","√");
				}
				if(XSJZ.equals(lczlnl)){
					dataMap.put("lczlnlXSJZ","√");
				}
			String jjclnl = formDataMap.get("jjclnl_id");
				if(JXCF.equals(jjclnl)){
					dataMap.put("jjclnlJXCF","√");
				}
				if(YN.equals(jjclnl)){
					dataMap.put("jjclnlYN","√");
				}
				if(WZBLTL.equals(jjclnl)){
					dataMap.put("jjclnlWZBLTL","√");
				}
				if(XSJZ.equals(jjclnl)){
					dataMap.put("jjclnlXSJZ","√");
				}
			dataMap.put("theoreResult", StringUtil.defaultIfEmpty(formDataMap.get("theoreResult"), " "));
			dataMap.put("skillName", StringUtil.defaultIfEmpty(formDataMap.get("skillName"), " "));
			dataMap.put("score", StringUtil.defaultIfEmpty(formDataMap.get("score"), " "));
			dataMap.put("examiner1", StringUtil.defaultIfEmpty(formDataMap.get("examiner1"), " "));
			dataMap.put("examiner2", StringUtil.defaultIfEmpty(formDataMap.get("examiner2"), " "));
			
			String szkskhxzztpj = formDataMap.get("szkskhxzztpj_id");
				if(JXCF.equals(szkskhxzztpj)){
					dataMap.put("szkskhxzztpjJXCF","√");
				}
				if(LING.equals(szkskhxzztpj)){
					dataMap.put("szkskhxzztpjLING","√");
				}
			dataMap.put("teacherName", StringUtil.defaultIfEmpty(formDataMap.get("teacherName"), " "));
			dataMap.put("teacherDate", StringUtil.defaultIfEmpty(formDataMap.get("teacherDate"), " "));
			dataMap.put("directorName", StringUtil.defaultIfEmpty(formDataMap.get("directorName"), " "));
			dataMap.put("directorDate", StringUtil.defaultIfEmpty(formDataMap.get("directorDate"), " "));
			
		}*/
		if (dataMap.get("zsgjflfgJXCF")==null) {
			dataMap.put("zsgjflfgJXCF","□");
		}
		if (dataMap.get("zsgjflfgYN")==null) {
			dataMap.put("zsgjflfgYN","□");
		}
		if (dataMap.get("zsgjflfgWZBLTL")==null) {
			dataMap.put("zsgjflfgWZBLTL","□");
		}
		if (dataMap.get("zsgjflfgXSJZ")==null) {
			dataMap.put("zsgjflfgXSJZ","□");
		}
		if (dataMap.get("lxgwzzJXCF")==null) {
			dataMap.put("lxgwzzJXCF","□");
		}
		if (dataMap.get("lxgwzzYN")==null) {
			dataMap.put("lxgwzzYN","□");
		}
		if (dataMap.get("lxgwzzWZBLTL")==null) {
			dataMap.put("lxgwzzWZBLTL","□");
		}
		if (dataMap.get("lxgwzzXSJZ")==null) {
			dataMap.put("lxgwzzXSJZ","□");
		}
		if (dataMap.get("ybrwzxJXCF")==null) {
			dataMap.put("ybrwzxJXCF","□");
		}
		if (dataMap.get("ybrwzxYN")==null) {
			dataMap.put("ybrwzxYN","□");
		}
		if (dataMap.get("ybrwzxWZBLTL")==null) {
			dataMap.put("ybrwzxWZBLTL","□");
		}
		if (dataMap.get("ybrwzxXSJZ")==null) {
			dataMap.put("ybrwzxXSJZ","□");
		}
		if (dataMap.get("rjgtbdnlJXCF")==null) {
			dataMap.put("rjgtbdnlJXCF","□");
		}
		if (dataMap.get("rjgtbdnlYN")==null) {
			dataMap.put("rjgtbdnlYN","□");
		}
		if (dataMap.get("rjgtbdnlWZBLTL")==null) {
			dataMap.put("rjgtbdnlWZBLTL","□");
		}
		if (dataMap.get("rjgtbdnlXSJZ")==null) {
			dataMap.put("rjgtbdnlXSJZ","□");
		}
		if (dataMap.get("tjxzjsxmJXCF")==null) {
			dataMap.put("tjxzjsxmJXCF","□");
		}
		if (dataMap.get("tjxzjsxmYN")==null) {
			dataMap.put("tjxzjsxmYN","□");
		}
		if (dataMap.get("tjxzjsxmWZBLTL")==null) {
			dataMap.put("tjxzjsxmWZBLTL","□");
		}
		if (dataMap.get("tjxzjsxmXSJZ")==null) {
			dataMap.put("tjxzjsxmXSJZ","□");
		}
		if (dataMap.get("jbllzwcdJXCF")==null) {
			dataMap.put("jbllzwcdJXCF","□");
		}
		if (dataMap.get("jbllzwcdYN")==null) {
			dataMap.put("jbllzwcdYN","□");
		}
		if (dataMap.get("jbllzwcdWZBLTL")==null) {
			dataMap.put("jbllzwcdWZBLTL","□");
		}
		if (dataMap.get("jbllzwcdXSJZ")==null) {
			dataMap.put("jbllzwcdXSJZ","□");
		}
		if (dataMap.get("njbjnzwcdJXCF")==null) {
			dataMap.put("njbjnzwcdJXCF","□");
		}
		if (dataMap.get("njbjnzwcdYN")==null) {
			dataMap.put("njbjnzwcdYN","□");
		}
		if (dataMap.get("njbjnzwcdWZBLTL")==null) {
			dataMap.put("njbjnzwcdWZBLTL","□");
		}
		if (dataMap.get("njbjnzwcdXSJZ")==null) {
			dataMap.put("njbjnzwcdXSJZ","□");
		}
		if (dataMap.get("lcswnlJXCF")==null) {
			dataMap.put("lcswnlJXCF","□");
		}
		if (dataMap.get("lcswnlYN")==null) {
			dataMap.put("lcswnlYN","□");
		}
		if (dataMap.get("lcswnlWZBLTL")==null) {
			dataMap.put("lcswnlWZBLTL","□");
		}
		if (dataMap.get("lcswnlXSJZ")==null) {
			dataMap.put("lcswnlXSJZ","□");
		}
		if (dataMap.get("lczlnlJXCF")==null) {
			dataMap.put("lczlnlJXCF","□");
		}
		if (dataMap.get("lczlnlYN")==null) {
			dataMap.put("lczlnlYN","□");
		}
		if (dataMap.get("lczlnlWZBLTL")==null) {
			dataMap.put("lczlnlWZBLTL","□");
		}
		if (dataMap.get("lczlnlXSJZ")==null) {
			dataMap.put("lczlnlXSJZ","□");
		}
		if (dataMap.get("jjclnlJXCF")==null) {
			dataMap.put("jjclnlJXCF","□");
		}
		if (dataMap.get("jjclnlYN")==null) {
			dataMap.put("jjclnlYN","□");
		}
		if (dataMap.get("jjclnlWZBLTL")==null) {
			dataMap.put("jjclnlWZBLTL","□");
		}
		if (dataMap.get("jjclnlXSJZ")==null) {
			dataMap.put("jjclnlXSJZ","□");
		}
		if (dataMap.get("szkskhxzztpjJXCF")==null) {
			dataMap.put("szkskhxzztpjJXCF","□");
		}
		if (dataMap.get("szkskhxzztpjLING")==null) {
			dataMap.put("szkskhxzztpjLING","□");
		}
		//获取本人所有排班
		List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlow(dept.getGroupFlow(),dept.getStandardDeptId(),user.getUserFlow());
		if(resultList!=null && resultList.size()>0){
			Float monthCount = 0f;
			String startDate = null;
			String endDate = null;
			for(SchArrangeResult sar : resultList){
				String month = sar.getSchMonth();
				if(StringUtil.isNotBlank(month)){
					Float currMonth = Float.parseFloat(month);
					if(currMonth!=null){
						monthCount+=currMonth;
					}
				}
				if(startDate==null){
					startDate = sar.getSchStartDate();
				}
				
				endDate = sar.getSchEndDate();
				
			}
			
			dataMap.put("monthCount",monthCount.toString());
			dataMap.put("startDate",startDate);
			dataMap.put("endDate",endDate);
		}
		
		dataMap.put("userName",user.getUserName());
		dataMap.put("sessionNumber",doctor.getSessionNumber());
		dataMap.put("trainingSpeName",doctor.getTrainingSpeName());
		dataMap.put("standardDeptName",dept.getStandardDeptName());

		if(caseRegistryReqNum==null||"0".equals(caseRegistryReqNum))
		{
			dataMap.put("caseRegistry","100");
		}else {
			dataMap.put("caseRegistry", StringUtil.defaultIfEmpty(caseRegistry, "0"));
		}
		dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(caseRegistryReqNum, "0"));
		dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(caseRegistryFinished, "0"));

		if(diseaseRegistryReqNum==null||"0".equals(diseaseRegistryReqNum))
		{
			dataMap.put("diseaseRegistry","100");
		}else {
			dataMap.put("diseaseRegistry",StringUtil.defaultIfEmpty(diseaseRegistry, "0"));
		}
		dataMap.put("diseaseRegistryReqNum",StringUtil.defaultIfEmpty(diseaseRegistryReqNum, "0"));
		dataMap.put("diseaseRegistryFinished", StringUtil.defaultIfEmpty(diseaseRegistryFinished, "0"));

		if(skillRegistryReqNum==null||"0".equals(skillRegistryReqNum))
		{
			dataMap.put("skillRegistry", "100");
		}else {
			dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(skillRegistry, "0"));
		}
		dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(skillRegistryReqNum, "0"));
		dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(skillRegistryFinished, "0"));

		if(skillAndOperationRegistryReqNum==null||"0".equals(skillAndOperationRegistryReqNum))
		{
			dataMap.put("operationRegistry", "100");
		}else {
			dataMap.put("operationRegistry", StringUtil.defaultIfEmpty(skillAndOperationRegistry, "0"));
		}
		dataMap.put("operationRegistryReqNum", StringUtil.defaultIfEmpty(skillAndOperationRegistryReqNum, "0"));
		dataMap.put("operationRegistryFinished", StringUtil.defaultIfEmpty(skillAndOperationRegistryFinished, "0"));
		
		dataMap.put("teachingRounds",String.valueOf(teachingRounds));
		dataMap.put("difficult",String.valueOf(difficult));
		dataMap.put("lecture",String.valueOf(lecture));
		dataMap.put("death",String.valueOf(death));
		
		String path = "/jsp/hbzy/doctor/ckkhb.docx";//模板
		temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
		name = "江苏省住院医师规范化培训临床轮转出科表.docx";
		if(temeplete!=null){
			response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +""); 
			response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			ServletOutputStream out = response.getOutputStream ();
			(new SaveToZipFile (temeplete)).save (out);
			out.flush ();
		}
	}
	
	//角色切换
	@RequestMapping(value="/getRoles")
	@ResponseBody
	public List<Map<String,String>> getRoles(){
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		List<SysUserRole> userRoles = userRoleBiz.getByUserFlow(userFlow);
		if(userRoles!=null && !userRoles.isEmpty()){
			List<Map<String,String>> roles = new ArrayList<Map<String,String>>();
			for(SysUserRole sur : userRoles){
				String roleFlow = sur.getRoleFlow();
				Map<String,String> role = getRoleUrl(roleFlow);
				if(role!=null){
					roles.add(role);
				}
			}
			return roles;
		}
		return null;
	}
	
	public Map<String,String> getRoleUrl(String roleFlow){
		if (StringUtil.isNotBlank(roleFlow)){
			Map<String,String> role = new HashMap<String, String>();
			if (roleFlow.equals(InitConfig.getSysCfg("res_global_role_flow"))) {//省级部门
				role.put("roleName",InitConfig.getSysCfgDesc("res_global_role_flow"));
				role.put("roleIndex","/hbzy/manage/global");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_qkzx_role_flow"))) {//审核部门之全科中心
				role.put("roleName",InitConfig.getSysCfgDesc("res_qkzx_role_flow"));
				role.put("roleIndex","/hbzy/manage/province");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_bjw_role_flow"))) {//审核部门之毕教委
				role.put("roleName",InitConfig.getSysCfgDesc("res_bjw_role_flow"));
				role.put("roleIndex","/hbzy/manage/province");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_zyglj_role_flow"))) {//审核部门之中医管理局
				role.put("roleName",InitConfig.getSysCfgDesc("res_zyglj_role_flow"));
				role.put("roleIndex","/hbzy/manage/province");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_school_role_flow"))) {//学校
				role.put("roleName",InitConfig.getSysCfgDesc("res_school_role_flow"));
				role.put("roleIndex","/hbzy/manage/school");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_charge_role_flow"))) {//主管部门
				role.put("roleName",InitConfig.getSysCfgDesc("res_charge_role_flow"));
				role.put("roleIndex","/hbzy/manage/charge");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))) {//医院管理员
				role.put("roleName",InitConfig.getSysCfgDesc("res_admin_role_flow"));
				role.put("roleIndex","/hbzy/manage/local");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_head_role_flow"))) {//科主任
				role.put("roleName",InitConfig.getSysCfgDesc("res_head_role_flow"));
				role.put("roleIndex","/hbzy/kzr/index");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_teacher_role_flow"))) {//带教老师
				role.put("roleName",InitConfig.getSysCfgDesc("res_teacher_role_flow"));
				role.put("roleIndex","/hbzy/teacher/index");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
				role.put("roleName",InitConfig.getSysCfgDesc("res_doctor_role_flow"));
				role.put("roleIndex","/hbzy/doctor/index");
			}
			return role;
		}
		return null;
	}

	/**
	 * 科室轮转查询
	 * */
	@RequestMapping(value = {"/doc/schDept" })
	public String schDept (String startDate,String endDate,String sessionNumber,Model model){
		List<String> titleDate = null;
		boolean isWeek = SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
		if(!StringUtil.isNotBlank(startDate)){
			startDate = DateUtil.getCurrDate();
			endDate = DateUtil.newDateOfAddMonths(startDate,12);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)){
			if(isWeek){
				titleDate = getWeeksByTwoDate(startDate,endDate);
			}else{
				String schStartMonth = startDate.substring(0,7);
				String schEndMonth = endDate.substring(0,7);
				titleDate = getMonthsByTwoMonth(schStartMonth,schEndMonth);
			}
			model.addAttribute("titleDate",titleDate);

			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(GlobalContext.getCurrentUser().getOrgFlow());
			model.addAttribute("schDeptList",schDeptList);
			SysDeptExample example=new SysDeptExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
			List<SysDept> deptList=sysDeptMapper.selectByExample(example);
			model.addAttribute("deptList",deptList);
			sessionNumber = "".equals(sessionNumber)?null:sessionNumber;
			List<SchArrangeResult> arrResultList = resultBiz.searchArrangeResultByDateAndOrg(startDate, endDate,GlobalContext.getCurrentUser().getOrgFlow());
			if(arrResultList != null && arrResultList.size()>0 ){
				Map<String,List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
				for(SchArrangeResult sar : arrResultList){
					if(null != sessionNumber && !sessionNumber.equals(sar.getSessionNumber())){//sessionNumber届别过滤
						continue;
					}
					String schDeptFlow = sar.getSchDeptFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();
					if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
						if(isWeek){
							List<String> weeks = getWeeksByTwoDate(resultStartDate,resultEndDate);
							if(weeks!=null){
								for(String week : weeks){
									String key = schDeptFlow+week;
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
							//计算该轮转科室该月人数
							List<String> months = getMonthsByTwoMonth(resultStartDate,resultEndDate);
							if(months!=null){
								for(String month : months){
									String key = schDeptFlow+month;
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
				model.addAttribute("resultListMap",resultListMap);
			}
		}
		return "hbzy/doctor/schDept";
	}

	@RequestMapping("/template/exportExcel")
	public void exportExcel(String startDate, String endDate,String schDeptFlow, String sessionNumber, HttpServletResponse response) throws IOException, Exception{
		List<String> titleDate = null;
		boolean isWeek = SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)){
			if(isWeek){
				titleDate = getWeeksByTwoDate(startDate,endDate);
			}else{
				String schStartMonth = startDate.substring(0,7);
				String schEndMonth = endDate.substring(0,7);
				titleDate = getMonthsByTwoMonth(schStartMonth,schEndMonth);
			}
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(GlobalContext.getCurrentUser().getOrgFlow());
			SysDeptExample example=new SysDeptExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
			List<SysDept> deptList=sysDeptMapper.selectByExample(example);
			schDeptFlow = "".equals(schDeptFlow)?null:schDeptFlow;
			sessionNumber = "".equals(sessionNumber)?null:sessionNumber;
			List<SchArrangeResult> arrResultList = resultBiz.searchArrangeResultByDateAndOrg(startDate, endDate,GlobalContext.getCurrentUser().getOrgFlow());
			if(arrResultList != null && arrResultList.size()>0){
				Map<String,List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
				for(SchArrangeResult sar : arrResultList){
					if((null != sessionNumber && !sessionNumber.equals(sar.getSessionNumber())) || (null != schDeptFlow && !schDeptFlow.equals(sar.getSchDeptFlow()))){//sessionNumber届别及科室过滤
						continue;
					}
					String deptFlow = sar.getSchDeptFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();
					if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
						if(isWeek){
							List<String> weeks = getWeeksByTwoDate(resultStartDate,resultEndDate);
							if(weeks!=null){
								for(String week : weeks){
									String key = deptFlow+week;
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
							//计算该轮转科室该月人数
							List<String> months = getMonthsByTwoMonth(resultStartDate,resultEndDate);
							if(months!=null){
								for(String month : months){
									String key = deptFlow+month;
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
				//创建工作簿
				HSSFWorkbook wb = new HSSFWorkbook();
				// 为工作簿添加sheet
				HSSFSheet sheet = wb.createSheet("sheet1");

				//定义将用到的样式
				HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
				styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

				sheet.setColumnWidth(0, 3000);
				HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
				styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

				HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
				stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

				HSSFRow titleRow = sheet.createRow(0);
				HSSFCell cell2 = titleRow.createCell(0);
				cell2.setCellValue("科室名称");
				cell2.setCellStyle(styleLeft);
				int colnum1 = 1;
				for (String Date : titleDate) {
					HSSFCell deptCell2 = titleRow.createCell(colnum1);
					deptCell2.setCellValue(Date);
					deptCell2.setCellStyle(styleCenter);
					colnum1++;
				}
				//列宽自适应
				Map<Integer, Integer> colWidthAuto = new HashMap<Integer, Integer>();
				List<Integer> deptFLowList=new ArrayList<Integer>();
				if(deptList!=null && deptList.size()>0 && resultListMap!=null){
					int rownum = 1;
					for(SysDept rd : deptList){
						if(null != schDeptFlow && !schDeptFlow.equals(rd.getDeptFlow())){
							continue;
						}
						//HSSFRow rowDept = sheet.createRow(rownum);
						int j=0;
						for (String tDate : titleDate) {
							String flow =rd.getDeptFlow()+tDate;
							List<SchArrangeResult> sars = resultListMap.get(flow);
							if (sars!=null&&!sars.isEmpty()) {
								deptFLowList.add(sars.size());
							}
						}
						if (deptFLowList!=null&&deptFLowList.isEmpty()) {
							deptFLowList.add(1);
						}
						j= Collections.max(deptFLowList);
						for (int i = 0; i <j; i++) {
							HSSFRow rowDate = sheet.createRow(rownum+i);
							HSSFCell cell1 = rowDate.createCell(0);
							cell1.setCellValue(rd.getDeptName());
							cell1.setCellStyle(styleLeft);
							int colnum = 1;
							for (String tDate : titleDate) {
								Integer width = colWidthAuto.get(colnum);
								if(width==null){
									width = 0;
								}
								String flow =rd.getDeptFlow()+tDate;
								List<SchArrangeResult> sars = resultListMap.get(flow);
								if (sars!=null&&!sars.isEmpty()) {
									String doctorName="";
									if (sars.size()>i) {
										SchArrangeResult result=sars.get(i);
										HSSFCell cell = rowDate.createCell(colnum);
										doctorName=doctorName+result.getDoctorName()+"("+result.getSchStartDate()+"~"+result.getSchEndDate()+")";
										cell.setCellValue(doctorName);
										cell.setCellStyle(styleLeft);
										Integer dateNewWidth = doctorName.getBytes().length*1*256;
										width = width<dateNewWidth?dateNewWidth:width;
										sheet.setColumnWidth(colnum,width);
										colWidthAuto.put(colnum,width);
									}
								}else{
									HSSFCell cell = rowDate.createCell(colnum);
									String doctorName="";
									cell.setCellValue(doctorName);
									cell.setCellStyle(styleLeft);
								}
								colnum++;
							}
						}
						//合并单元格
						sheet.addMergedRegion(new CellRangeAddress(rownum, rownum + j-1, 0, 0));
						deptFLowList.clear();
//						HSSFCell cell = rowDept.createCell(0);
//						cell.setCellValue(rd.getDeptName());
//						cell.setCellStyle(styleLeft);
//						int colnum = 1;
//						for (String Date : titleDate) {
//							HSSFCell deptCell = rowDept.createCell(colnum);
//							deptCell.setCellValue(Date);
//							deptCell.setCellStyle(styleCenter);
//							colnum++;
//						}
						rownum=rownum+(j);
					}
				}
				String fileName = "学员排班表.xls";
				fileName = URLEncoder.encode(fileName, "UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				response.setContentType("application/octet-stream;charset=UTF-8");
				wb.write(response.getOutputStream());

			}
		}
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
	 * 加载基地专业
	 * @return
	 */
	@RequestMapping(value="/searchResOrgSpeList", method={RequestMethod.GET})
	@ResponseBody
	public List<ResOrgSpe> searchResOrgSpeList(String sessionNumber,ResOrgSpe resOrgSpe, Model model){
		List<ResOrgSpe> speList=null;
		resOrgSpe.setRecordStatus(GlobalConstant.FLAG_Y);
		sessionNumber = ("").equals(sessionNumber)?InitConfig.getSysCfg("res_reg_year"):sessionNumber;
		int year=Integer.parseInt(sessionNumber);
		if(year>=2015){
			speList = resOrgSpeBiz.searchResOrgSpeList(resOrgSpe,null);
		}else{
			String speTypeId = resOrgSpe.getSpeTypeId();
			if(StringUtil.isNotBlank(speTypeId)){
				Map<String,String> speMap=InitConfig.getDictNameMap(speTypeId);
				speList=new ArrayList<ResOrgSpe>();
				ResOrgSpe orgSpe=null;
				for(Map.Entry<String, String> map:speMap.entrySet()){
					orgSpe=new ResOrgSpe();
					orgSpe.setSpeId(map.getValue());
					orgSpe.setSpeName(map.getKey());
					speList.add(orgSpe);
				}
			}
		}
		return speList;
	}

	/**
	 * 医师工作量查询
	 * */
	@RequestMapping(value = {"/doc/docWorking" })
	public String schDept (String trainType,String speId,String sessionNumber,String docName,String cardNo,String graduationYear,Integer currentPage,HttpServletRequest request,Model model){
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		Map<String, Object> parMp = new HashMap<String, Object>();
		parMp.put("orgFlow", orgFlow);
		parMp.put("speId", speId);
		parMp.put("sessionNumber", sessionNumber);
		parMp.put("docName", docName);
		parMp.put("cardNo", cardNo);
		parMp.put("graduationYear", graduationYear);
		parMp.put("trainType", trainType);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> rltLst = resultBiz.docWorkingSearch(parMp);
//		if(null != rltLst && rltLst.size() > 0){
//			for(Map<String, Object> map : rltLst){
//				Integer reqSum = 0;//培训数据：要求数
//				Integer compSum = 0;//培训数据：完成数
//				Integer rotationSum = 0;//科室要求数
//				Integer lunzhuanSum = 0;//已轮转科室数
//				Integer chukeSum = 0;//出科科室数
//				parMp.clear();;
//				parMp.put("orgFlow", orgFlow);
//				parMp.put("userFlow", map.get("doctorFlow"));
//				List<Map<String,Object>> lst = resultBiz.docWorkingDetail(parMp);
//				if(null != lst){
//					rotationSum = lst.size();
//					if(lst.size() > 0){
//						for(Map<String, Object> mp : lst){
//							reqSum += Integer.valueOf(String.valueOf(mp.get("reqNum")));
//							compSum += Integer.valueOf(String.valueOf(mp.get("completeNum")));
//							if(Integer.valueOf(String.valueOf(mp.get("completeNum"))) > 0){
//								if(Integer.valueOf(String.valueOf(mp.get("evaluationNum"))) > 0){
//									chukeSum++;
//								}else{
//									lunzhuanSum++;
//								}
//							}
//						}
//					}
//				}
//				map.put("reqNum", reqSum);
//				map.put("completeNum", compSum);
//				map.put("rotationNum", rotationSum);
//				map.put("lunzhuanNum", lunzhuanSum);
//				map.put("chukeNum", chukeSum);
//			}
//		}
		model.addAttribute("rltLst", rltLst);
		model.addAttribute("speId", speId);
		model.addAttribute("trainType", trainType);
		return "hbzy/doctor/docWorking";
	}

	/**
	 * 医师工作量详情查询
	 * */
	@RequestMapping(value = {"/docWorkDetailSearch" })
	public String docWorkDetail (String userFlow,Model model){
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		Map<String, Object> parMp = new HashMap<String, Object>();
		parMp.put("orgFlow", orgFlow);
		parMp.put("userFlow", userFlow);
		ResDoctor resDoctor=resDoctorBiz.findByFlow(userFlow);
		String rotation_flow=resDoctor.getRotationFlow();
		if(StringUtil.isNotBlank(rotation_flow)) {
			parMp.put("rotationFlow", rotation_flow);
			List<Map<String, Object>> rltLst = resultBiz.docWorkingDetail(parMp);
			model.addAttribute("rltLst", rltLst);
		}
		return "hbzy/doctor/docWorkDetail";
	}
    public Map<String,List<SchProcessExt>> zuzhuangMap(List<SchProcessExt> list)
	{
		Map<String,List<SchProcessExt>> map=new HashMap<>();
		if(list!=null&&list.size()>0){
			for(SchProcessExt ext:list)
			{
				List<SchProcessExt> tempList=map.get(ext.getDeptFlow());
				if(tempList==null)
				{
					tempList=new ArrayList<>();
					map.put(ext.getDeptFlow(),tempList);
				}
				tempList.add(ext);
			}
		}
		return map;
	}
	public Map<String,Integer> getDeptMaxSize(Map<String,List<SchProcessExt>> map1,
											  Map<String,List<SchProcessExt>> map2,
											  Map<String,List<SchProcessExt>> map3)
	{
		Map<String,Integer> map=new HashMap<>();
		if(map1!=null&&map1.size()>0){
			for(String key:map1.keySet())
			{
				List<SchProcessExt> list=map1.get(key);
				if(list!=null)
					map.put(key,list.size());
				else
					map.put(key,0);
			}
		}
		if(map2!=null&&map2.size()>0){
			for(String key:map2.keySet())
			{
				List<SchProcessExt> list=map2.get(key);
				Integer count=map.get(key);
				int thisCount=0;
				if(list!=null)
					thisCount=list.size();
				if(count==null)
				{
					count=0;
				}
				if(thisCount>count)
				{
					map.put(key,thisCount);
				}
			}
		}
		if(map3!=null&&map3.size()>0){
			for(String key:map3.keySet())
			{
				List<SchProcessExt> list=map3.get(key);
				Integer count=map.get(key);
				int thisCount=0;
				if(list!=null)
					thisCount=list.size();
				if(count==null)
				{
					count=0;
				}
				if(thisCount>count)
				{
					map.put(key,thisCount);
				}
			}
		}
		return map;
	}
	/**
	 * 月报查询
	 * @return
     */
	@RequestMapping(value = {"/searchMonthlyReport"})
	public void searchMonthlyReport(String resultFlow, String schStartDate,String schStartDate1,HttpServletResponse response)throws Exception{
		SysUser sysUser = GlobalContext.getCurrentUser();
		String orgFlow=sysUser.getOrgFlow();
//		String orgFlow="031f5fd06af3466c8bb9ea05d51b8d33";
		Map<String,Object> mapIn =new HashMap<>();
		mapIn.put("orgFlow",orgFlow);
		mapIn.put("schStartDate",schStartDate);
		List<SchProcessExt> resultMIn=schMonthlyReportBiz.searchPlanAccessDoc(mapIn);
		Map<String,List<SchProcessExt>> mInDeptMap=zuzhuangMap(resultMIn);
		Map<String,Object> mapOut =new HashMap<>();
		mapOut.put("orgFlow",orgFlow);
		mapOut.put("schEndDate",schStartDate);
		List<SchProcessExt> resultMOut=schMonthlyReportBiz.searchPlanAccessDoc(mapOut);
		Map<String,List<SchProcessExt>> mOutDeptMap=zuzhuangMap(resultMOut);
		Map<String,Integer> deptCountMap=getDeptMaxSize(mInDeptMap,mOutDeptMap,null);
		List<Map<String,String>> dataList=new ArrayList<>();
		if(deptCountMap!=null&&deptCountMap.size()>0)
		{
			for(String key:deptCountMap.keySet())
			{
				Integer maxCount=deptCountMap.get(key);
				List<SchProcessExt> inList=mInDeptMap.get(key);
				List<SchProcessExt> outList=mOutDeptMap.get(key);
				if(maxCount!=null&&maxCount>0)
				{
					for(int i=0;i<maxCount;i++)
					{
						Map<String,String> data=new HashMap<>();
						SchProcessExt inExt=null;
						if(inList!=null&&inList.size()>0&&inList.size()>i)
						{
							inExt=inList.get(i);
						}
						SchProcessExt outExt=null;
						if(outList!=null&&outList.size()>0&&outList.size()>i)
						{
							outExt=outList.get(i);
						}
						String deptName="";
						String inDocName="";
						String inDate="";
						String outDocName="";
						String outTeaName="";
						String outDate="";
						if(inExt!=null)
						{
							deptName=inExt.getDeptName();
							SysDept sysDept = sysDeptMapper.selectByPrimaryKey(inExt.getDeptFlow());
							String deptOrgFlow = sysDept.getOrgFlow();
							if(StringUtil.isNotBlank(deptOrgFlow)){
								SysOrg so = orgBiz.readSysOrg(deptOrgFlow);
								if(so!=null && !so.getOrgFlow().equals(inExt.getSysUser().getOrgFlow())){
									deptName+=("["+so.getOrgName()+"]");
								}
							}
							inDocName=inExt.getSysUser().getUserName();
							inDate=inExt.getSchStartDate();
						}
						if(outExt!=null)
						{
							deptName=outExt.getDeptName();
							SysDept sysDept = sysDeptMapper.selectByPrimaryKey(outExt.getDeptFlow());
							String deptOrgFlow = sysDept.getOrgFlow();
							if(StringUtil.isNotBlank(deptOrgFlow)){
								SysOrg so = orgBiz.readSysOrg(deptOrgFlow);
								if(so!=null && !so.getOrgFlow().equals(outExt.getSysUser().getOrgFlow())){
									deptName+=("["+so.getOrgName()+"]");
								}
							}
							outDocName=outExt.getSysUser().getUserName();
							outTeaName=outExt.getTeacherUserName();
							outDate=outExt.getSchEndDate();
						}
						data.put("deptName",deptName);
						data.put("inDocName",inDocName);
						data.put("inDate",inDate);
						data.put("outDocName",outDocName);
						data.put("outTeaName",outTeaName);
						data.put("outDate",outDate);
						dataList.add(data);
					}
				}
			}
		}
//		System.err.println(JSON.toJSONString(mInDeptMap));
//		System.err.println(JSON.toJSONString(mOutDeptMap));
//		System.err.println(JSON.toJSONString(deptCountMap));
//		System.err.println("dataList:"+JSON.toJSONString(dataList));
		Map<String,Object> mapAlreadyIn =new HashMap<>();
		mapAlreadyIn.put("orgFlow",orgFlow);
		mapAlreadyIn.put("schStartDate",schStartDate1);
		List<SchProcessExt> resultAlreadyIn=schMonthlyReportBiz.searchAlreadyInDoc(mapAlreadyIn);
		//查询上月已出科和未出科的学员
		Map<String,Object> mapInAndOutDoc =new HashMap<>();
		mapInAndOutDoc.put("orgFlow",orgFlow);
		mapInAndOutDoc.put("schEndDate",schStartDate1);
		List<SchProcessExt> resultInAndOutDoc=schMonthlyReportBiz.searchInAndOutDoc(mapInAndOutDoc);
		//上月已出科学员
		List<SchProcessExt> resultLastMonthOutDoc=new ArrayList<>();
		//上月未出科学员
		List<SchProcessExt> resultLastMonthNotOutDoc=new ArrayList<>();
		if(resultInAndOutDoc != null && !resultInAndOutDoc.isEmpty()){
			for(int i=1;i<resultInAndOutDoc.size();i++){
				if(resultInAndOutDoc.get(i).getResRec()==null&&
						resultInAndOutDoc.get(i-1).getSysUser().getUserName()
								.equals(resultInAndOutDoc.get(i).getSysUser().getUserName())){
						resultInAndOutDoc.remove(i);
					}

				}
			for(int i=0;i<resultInAndOutDoc.size();i++){
				resultFlow=resultInAndOutDoc.get(i).getSchResultFlow();
				List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
				SchArrangeResult result=schArrangeResultBiz.readSchArrangeResult(resultFlow);
				SchRotationDept schRotationDept=readStandardRotationDept(resultFlow);
				if (result != null&&StringUtil.isNotBlank(result.getDoctorFlow())&&schRotationDept!=null&&StringUtil.isNotBlank(schRotationDept.getRecordFlow())) {
					ResRec rec =resRecBiz.queryResRec(schRotationDept.getRecordFlow(),result.getDoctorFlow(), AfterRecTypeEnum.AfterSummary.getId());
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
				if (imagelist.size()>0){
					if(resultInAndOutDoc.get(i).getResRec()!=null&&resultInAndOutDoc.get(i).getResRec().getAuditStatusId()!=""){
						resultLastMonthOutDoc.add(resultInAndOutDoc.get(i));
					}else{
						resultInAndOutDoc.get(i).setSchFlag("带教未审核");
						resultLastMonthNotOutDoc.add(resultInAndOutDoc.get(i));
					}
				}else{
					resultInAndOutDoc.get(i).setSchFlag("学员未上传");
					resultLastMonthNotOutDoc.add(resultInAndOutDoc.get(i));
				}
			}
		}
		Map<String,List<SchProcessExt>> alreadyInDeptMap=zuzhuangMap(resultAlreadyIn);
		Map<String,List<SchProcessExt>> lastMonthOutDeptMap=zuzhuangMap(resultLastMonthOutDoc);
		Map<String,List<SchProcessExt>> lastMonthNotOutDeptMap=zuzhuangMap(resultLastMonthNotOutDoc);
		Map<String,Integer> deptCountMap2=getDeptMaxSize(alreadyInDeptMap,lastMonthOutDeptMap,lastMonthNotOutDeptMap);
		List<Map<String,String>> dataList2=new ArrayList<>();
		if(deptCountMap2!=null&&deptCountMap2.size()>0)
		{
			for(String key:deptCountMap2.keySet())
			{
				Integer maxCount=deptCountMap2.get(key);
				List<SchProcessExt> inList=alreadyInDeptMap.get(key);
				List<SchProcessExt> outList=lastMonthOutDeptMap.get(key);
				List<SchProcessExt> notOutList=lastMonthNotOutDeptMap.get(key);
				if(maxCount!=null&&maxCount>0)
				{
					for(int i=0;i<maxCount;i++)
					{
						Map<String,String> data=new HashMap<>();
						SchProcessExt alreadyInExt=null;
						if(inList!=null&&inList.size()>0&&inList.size()>i)
						{
							alreadyInExt=inList.get(i);
						}
						SchProcessExt alreadyOutExt=null;
						if(outList!=null&&outList.size()>0&&outList.size()>i)
						{
							alreadyOutExt=outList.get(i);
						}
						SchProcessExt notOutExt=null;
						if(notOutList!=null&&notOutList.size()>0&&notOutList.size()>i)
						{
							notOutExt=notOutList.get(i);
						}
						String deptName="";
						String inDocName="";
						String inTeacherName="";
						String inDate="";
						String outDocName="";
						String outTeaName="";
						String outSchPer="";
						String outDate="";
						String notOutDocName="";
						String notOutTeaName="";
						String notOutSchPer="";
						String notOutDate="";
						String reason="";
						if(alreadyInExt!=null)
						{
							deptName=alreadyInExt.getDeptName();
							SysDept sysDept = sysDeptMapper.selectByPrimaryKey(alreadyInExt.getDeptFlow());
							String deptOrgFlow = sysDept.getOrgFlow();
							if(StringUtil.isNotBlank(deptOrgFlow)){
								SysOrg so = orgBiz.readSysOrg(deptOrgFlow);
								if(so!=null && !so.getOrgFlow().equals(alreadyInExt.getSysUser().getOrgFlow())){
									deptName+=("["+so.getOrgName()+"]");
								}
							}
							inDocName=alreadyInExt.getSysUser().getUserName();
							inTeacherName=alreadyInExt.getTeacherUserName();
							inDate=alreadyInExt.getSchStartDate();
						}
						if(alreadyOutExt!=null)
						{
							String schPer1="";
							if(alreadyOutExt.getSchPer()!=null){
								schPer1=alreadyOutExt.getSchPer()+"";
							}
							deptName=alreadyOutExt.getDeptName();
							SysDept sysDept = sysDeptMapper.selectByPrimaryKey(alreadyOutExt.getDeptFlow());
							String deptOrgFlow = sysDept.getOrgFlow();
							if(StringUtil.isNotBlank(deptOrgFlow)){
								SysOrg so = orgBiz.readSysOrg(deptOrgFlow);
								if(so!=null && !so.getOrgFlow().equals(alreadyOutExt.getSysUser().getOrgFlow())){
									deptName+=("["+so.getOrgName()+"]");
								}
							}
							outDocName=alreadyOutExt.getSysUser().getUserName();
							outTeaName=alreadyOutExt.getTeacherUserName();
							outSchPer=schPer1;
							outDate=alreadyOutExt.getSchEndDate();
						}
						if(notOutExt!=null){
							String schPer2="";
							if(notOutExt.getSchPer()!=null){
								schPer2=notOutExt.getSchPer()+"";
							}
							deptName=notOutExt.getDeptName();
							SysDept sysDept = sysDeptMapper.selectByPrimaryKey(notOutExt.getDeptFlow());
							String deptOrgFlow = sysDept.getOrgFlow();
							if(StringUtil.isNotBlank(deptOrgFlow)){
								SysOrg so = orgBiz.readSysOrg(deptOrgFlow);
								if(so!=null && !so.getOrgFlow().equals(notOutExt.getSysUser().getOrgFlow())){
									deptName+=("["+so.getOrgName()+"]");
								}
							}
							notOutDocName=notOutExt.getSysUser().getUserName();
							notOutTeaName=notOutExt.getTeacherUserName();
							notOutSchPer=schPer2;
							notOutDate=notOutExt.getSchEndDate();
							reason=notOutExt.getSchFlag();
						}
						if(null!=alreadyOutExt) {
							ResDoctor doctor = resDoctorBiz.readDoctor(alreadyOutExt.getSysUser().getUserFlow());
							//计算登记进度
							if(null!=doctor) {
								List<SchArrangeResult> results = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctor.getDoctorFlow());
								Map<String, String> alreadyOutPerMap = resRecBiz.getFinishPer(results, doctor.getDoctorFlow());
								outSchPer = alreadyOutPerMap.get(alreadyOutExt.getSchResultFlow())+"%";
							}
						}
						if(null!=notOutExt) {
							ResDoctor doctor2 = resDoctorBiz.readDoctor(notOutExt.getSysUser().getUserFlow());
							//计算登记进度
							if(null!=doctor2) {
								List<SchArrangeResult> results2 = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctor2.getDoctorFlow());
								Map<String, String> alreadyNotOutPerMap = resRecBiz.getFinishPer(results2, doctor2.getDoctorFlow());
								notOutSchPer = alreadyNotOutPerMap.get(notOutExt.getSchResultFlow())+"%";
							}
						}
						data.put("deptName",deptName);
						data.put("inDocName",inDocName);
						data.put("inTeacherName",inTeacherName);
						data.put("inDate",inDate);
						data.put("outDocName",outDocName);
						data.put("outTeaName",outTeaName);
						data.put("outSchPer",outSchPer);
						data.put("outDate",outDate);
						data.put("notOutDocName",notOutDocName);
						data.put("notOutTeaName",notOutTeaName);
						data.put("notOutSchPer",notOutSchPer);
						data.put("notOutDate",notOutDate);
						data.put("reason",reason);
						dataList2.add(data);
					}
				}
			}
		}
		String[] arrFileName=schStartDate.split("-");
		String[] arrFileName2=schStartDate1.split("-");
		String fileName =arrFileName[0]+"年"+arrFileName[1]+"月住院医师规范化培训工作报告.xls";
		String sheet1Title=arrFileName[0]+"年"+arrFileName[1]+"月住院医师规范化培训工作计划报告";
		String sheet2Title=arrFileName2[0]+"年"+arrFileName2[1]+"月住院医师规范化培训工作小结报告";
		schMonthlyReportBiz.export4MonthlyReport(fileName,sheet1Title,sheet2Title,dataList,dataList2,response);
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
	@RequestMapping(value = {"/showMonthlyReportList"})
	public String showMonthlyReportList(){
		return "hbzy/hospital/monthlyReportList";
	}
    /**
     * 编辑
     * @param model
     * @return
     */
    @RequestMapping("/setScoreConf")
    public String setScoreConf(String cfgYear,Model model){
        ResPassScoreCfg scoreCfg = new ResPassScoreCfg();
        if(StringUtil.isNotBlank(cfgYear)){
            scoreCfg.setCfgYear(cfgYear);
            ResPassScoreCfg resPassScoreCfg = baseBiz.readResPassScoreCfg(scoreCfg);
            model.addAttribute("resPassScoreCfg",resPassScoreCfg);
        }
        return "hbzy/system/sys/addScore";
    }

    /**
     * 删除
     * @param cfgYear
     * @return
     */
    @RequestMapping("/delScoreConf")
    @ResponseBody
    public String delScoreConf(String cfgYear){

        int result = baseBiz.delScoreConf(cfgYear);
        if(result>GlobalConstant.ZERO_LINE){
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }
    /**
     * 保存年份合格线配置
     * @param resPassScoreCfg
     * @param model
     * @return
     */
    @RequestMapping("/saveCfg")
    @ResponseBody
    public String  saveCfg(ResPassScoreCfg resPassScoreCfg,Model model){
        int result =baseBiz.savePassScoreCfg(resPassScoreCfg);
        if(result>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    /**
     * 查询分数配置信息
     * @param
     * @param model
     * @return
     */
    @RequestMapping("/loadScoreConf")
    public String loadScoreConf(Integer currentPage,ResPassScoreCfg resPassScoreCfg, Model model, HttpServletRequest request){

//		ResPassScoreCfg scoreCfg = baseBiz.readResPassScoreCfg(resPassScoreCfg);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResPassScoreCfg> cfgList = baseBiz.readCfgs(resPassScoreCfg);
        model.addAttribute("cfgList",cfgList);
        return "hbzy/system/sys/scoreCfgList";
    }
    /**
     * 成绩合格线配置
     * @return
     */
    @RequestMapping("/scoreCfg")
    public String scoreCfg(){
        return "hbzy/system/sys/scoreCfgQueryMain";
    }

}
