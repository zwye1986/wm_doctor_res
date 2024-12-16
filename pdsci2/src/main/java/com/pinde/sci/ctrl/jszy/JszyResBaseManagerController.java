package com.pinde.sci.ctrl.jszy;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.enums.AfterRecTypeEnum;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.jsres.IJsResActivityBiz;
import com.pinde.sci.biz.jsres.IJsResActivityTargetBiz;
import com.pinde.sci.biz.jszy.IJszyResBaseBiz;
import com.pinde.sci.biz.jszy.IJszyResOrgSpeBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.res.IResRecBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sch.ISchMonthlyReportBiz;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.biz.sch.impl.SchRotationGroupBizImpl;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SchRotationDeptMapper;
import com.pinde.sci.dao.base.SysDeptMapper;
import com.pinde.sci.form.jszy.JszyBaseExtInfoForm;
import com.pinde.sci.form.jszy.JszyBaseInfoForm;
import com.pinde.sci.form.jszy.JszyCountryOrgExtInfoForm;
import com.pinde.sci.form.jszy.JszyResOrgSpeForm;
import com.pinde.sci.model.jszy.JszyResBaseExt;
import com.pinde.sci.model.jszy.JszyResJointOrgExt;
import com.pinde.sci.model.mo.ResBase;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorSchProcess;
import com.pinde.sci.model.mo.ResJointOrg;
import com.pinde.sci.model.mo.ResPassScoreCfg;
import com.pinde.sci.model.mo.ResRec;
import com.pinde.sci.model.mo.SchArrangeResult;
import com.pinde.sci.model.mo.SchDept;
import com.pinde.sci.model.mo.SchRotationDept;
import com.pinde.sci.model.mo.SchRotationDeptExample;
import com.pinde.sci.model.mo.SchRotationGroup;
import com.pinde.sci.model.mo.SysDeptExample;
import com.pinde.sci.model.mo.SysUserRole;
import com.pinde.sci.model.res.SchProcessExt;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author PPBear
 *
 */
@Controller
@RequestMapping("/jszy/base")
public class JszyResBaseManagerController extends GeneralController {
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
	@Autowired
	private IJsResActivityTargetBiz activityTargeBiz;
	@Autowired
	private IJsResActivityBiz activityBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IFileBiz fileBiz;

	@RequestMapping("/baseAudit")
	@ResponseBody
	public String baseAudit(String baseFlow,String status){
		ResBase resBase=new ResBase();
		resBase.setOrgFlow(baseFlow);
        if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(status)) {
            resBase.setBaseStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotPassed.getId());
            resBase.setBaseStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotPassed.getName());
		}
		int result=baseBiz.saveResBase(resBase);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
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
		return  "jszy/hospital/hos/editCoopBase";
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
        if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(role)) {
			sOrg.setOrgCityId(sysOrg.getOrgCityId());
		}
        sOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		orgList=orgBiz.searchOrg(sOrg);
		for(SysOrg o:orgList){
			orgFlowList.add(o.getOrgFlow());
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(auditFlag)) {
            base.setBaseStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId());
		}
		Map<String, Object> paramMap = new HashMap<String, Object> ();
		paramMap.put("org",org);
		paramMap.put("base",base);
		paramMap.put("orgFlowList", orgFlowList);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JszyResBaseExt> resBaseExtList = baseBiz.searchResBaseExtList(paramMap);
		model.addAttribute("resBaseExtList", resBaseExtList);
        if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(role)) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(auditFlag)) {
				mav.setViewName("jszy/city/hospital/auditHospitals");
			}else{
				mav.setViewName("jszy/hospitalList");
			}
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_PROVINCE.equals(role)) {
			model.addAttribute("resBaseExtList", resBaseExtList);
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(auditFlag)) {
				mav.setViewName("jszy/province/hospital/auditHospitals");
			}else{
				mav.setViewName("jszy/hospitalList");
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
        search.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(editFlag) || (StringUtil.isBlank(resBase.getBaseStatusId()) && getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL))) {
				mav.setViewName("jszy/hospital/hos/editSpe");
			}else{
				model.addAttribute("baseInfoName", trainCategoryType);
				mav.setViewName("jszy/city/hospital/speView");
			}
		}else{
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(editFlag)) {
				mav.setViewName("jszy/hospital/hos/editSpe");
			}else{
				ResOrgSpe resOrgSpe=new ResOrgSpe();
				resOrgSpe.setOrgFlow(orgFlow);
				List<ResOrgSpe>exitList=resOrgSpeBiz.searchResOrgSpeList(resOrgSpe, null);
				if (exitList.size()>0&&exitList!=null) {
					model.addAttribute("baseInfoName", trainCategoryType);
					mav.setViewName("jszy/city/hospital/speView");
				}else{
					mav.setViewName("jszy/hospital/hos/editSpe");
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
                if (com.pinde.core.common.GlobalConstant.BASIC_INFO.equals(baseInfoName)) {
					mav.addObject("basicInfo", JszyBaseExtInfoForm.getBasicInfo());
                } else if (com.pinde.core.common.GlobalConstant.ORG_MANAGE.equals(baseInfoName)) {
					mav.addObject("organizationManage", JszyBaseExtInfoForm.getOrganizationManage());
                } else if (com.pinde.core.common.GlobalConstant.TEACH_CONDITION.equals(baseInfoName)) {
					mav.addObject("educationInfo", JszyBaseExtInfoForm.getEducationInfo());
                } else if (com.pinde.core.common.GlobalConstant.SUPPORT_CONDITION.equals(baseInfoName)) {
					mav.addObject("supportCondition", JszyBaseExtInfoForm.getSupportCondition());
				}
			}
            if ((StringUtil.isBlank(resBase.getBaseStatusId()) || com.pinde.core.common.GlobalConstant.FLAG_Y.equals(editFlag)) && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(viewFlag)) {
				mav.setViewName("jszy/hospital/hos/edit"+baseInfoName);
			}else{
				mav.addObject("baseInfoName", baseInfoName);
				mav.setViewName("jszy/city/hospital/"+baseInfoName.substring(0,1).toLowerCase()+baseInfoName.substring(1, baseInfoName.length()));
			}
		}else{//无记录
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(viewFlag)) {
				mav.setViewName("jszy/city/hospital/"+baseInfoName.substring(0,1).toLowerCase()+baseInfoName.substring(1, baseInfoName.length()));
			}else{
				mav.setViewName("jszy/hospital/hos/edit"+baseInfoName);
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
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
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
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
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
        resBase.setBaseStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId());
        resBase.setBaseStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getName());
		int result=baseBiz.saveResBase(resBase);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 跳转界面
	 * @return
	 */
	@RequestMapping("/hospitalMain")
	public String hospitalMain(Model model){
		return "jszy/city/hospital/hospitalMain";
	}

	@RequestMapping("/main")
	public String main(Model model,String baseFlow){
		ResBase resBase=baseBiz.readBase(baseFlow);
		List<ResJointOrg> jointOrgs = resJointOrgBiz.searchResJointByOrgFlow(baseFlow);
		model.addAttribute("jointOrgs", jointOrgs);
		model.addAttribute("resBase",resBase);
		return "jszy/hospital/hos/main";
	}

	/**
	 * 跳转页面用于选审核专业
	 * @return
	 */
	@RequestMapping("/spePage")
	public String spePage(){
		return "jszy/city/hospital/trainSpeMainView";
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
            serach.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<ResOrgSpe> resBaseList = resOrgSpeBiz.searchResOrgSpeList(serach);
			model.addAttribute("resBaseList", resBaseList);
			ResBase resBase=baseBiz.readBase(orgFlow);
			model.addAttribute("resBase", resBase);
		}
		return "jszy/hospital/hos/trainSpeMain";
	}
	
	/**
	 * 基地专业管理
	 * @param model
	 * @return
	 */
	@RequestMapping("/orgSpeManage")
	public String orgSpeManage(Model model){
		SysOrg sysOrg = new SysOrg();
        sysOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        sysOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		SysOrg org=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		sysOrg.setOrgProvId(org.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			sysOrg.setOrgCityId(org.getOrgCityId());
		}
		List<SysOrg> orgList = orgBiz.searchOrg(sysOrg);
		model.addAttribute("orgList", orgList);
		return "jszy/system/sys/baseSpecialList";
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
            serach.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
		return "jszy/system/sys/loadOrgSpeList";
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
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
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
		return "jszy/global/hospital/basicGeneralDoc";
	}

	@RequestMapping("/baseInfo")
	public String baseInfo(Model model,String type,Integer currentPage,HttpServletRequest request){
		Map<String,String> jointFlagMap=new HashMap<String,String>();
		SysOrg sysOrg=new SysOrg();
		SysUser currUser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(currUser.getOrgFlow());
		sysOrg.setOrgProvId(org.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			sysOrg.setOrgCityId(org.getOrgCityId());
		}
        sysOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		if(StringUtil.isNotBlank(type)){
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(type)) {
                sysOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
            } else if (com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId().equals(type)) {
                sysOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId());
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
		return "jszy/global/hospital/basicGeneralDoc";
	}



	@RequestMapping(value="/verification")
	@ResponseBody
	public String verification(String resultFlow){
		SysUser user=GlobalContext.getCurrentUser();
		ResDoctorSchProcess doctorSchProcess= doctorProcessBiz.searchByResultFlow(resultFlow);
        String afterEvaluationId = com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId();
		if (doctorSchProcess!=null) {
			List<ResRec> recs=resRecBiz.searchResRecWithBLOBs(afterEvaluationId,doctorSchProcess.getProcessFlow(),user.getUserFlow());
			if (recs.size()>0) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;

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
            String caseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId();
            String diseaseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.DiseaseRegistry.getId();
            String skillRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.SkillRegistry.getId();
            String operationRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.OperationRegistry.getId();

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

            String recTypeIdt = com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId();
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
        String afterEvaluationId = com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId();
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
		
		
		String path = "/jsp/jszy/doctor/ckkhb.docx";//模板
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
        String caseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId();
        String diseaseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.DiseaseRegistry.getId();
        String skillRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.SkillRegistry.getId();
        String operationRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.OperationRegistry.getId();
		
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

        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId();
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
        String afterEvaluationId = com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId();
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
		
		String path = "/jsp/jszy/doctor/ckkhb.docx";//模板
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
				role.put("roleIndex","/jszy/manage/global");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_qkzx_role_flow"))) {//审核部门之全科中心
				role.put("roleName",InitConfig.getSysCfgDesc("res_qkzx_role_flow"));
				role.put("roleIndex","/jszy/manage/province");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_bjw_role_flow"))) {//审核部门之毕教委
				role.put("roleName",InitConfig.getSysCfgDesc("res_bjw_role_flow"));
				role.put("roleIndex","/jszy/manage/province");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_zyglj_role_flow"))) {//审核部门之中医管理局
				role.put("roleName",InitConfig.getSysCfgDesc("res_zyglj_role_flow"));
				role.put("roleIndex","/jszy/manage/province");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_school_role_flow"))) {//学校
				role.put("roleName",InitConfig.getSysCfgDesc("res_school_role_flow"));
				role.put("roleIndex","/jszy/manage/school");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_charge_role_flow"))) {//主管部门
				role.put("roleName",InitConfig.getSysCfgDesc("res_charge_role_flow"));
				role.put("roleIndex","/jszy/manage/charge");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))) {//医院管理员
				role.put("roleName",InitConfig.getSysCfgDesc("res_admin_role_flow"));
				role.put("roleIndex","/jszy/manage/local");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_head_role_flow"))) {//科主任
				role.put("roleName",InitConfig.getSysCfgDesc("res_head_role_flow"));
				role.put("roleIndex","/jszy/kzr/index");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_teacher_role_flow"))) {//带教老师
				role.put("roleName",InitConfig.getSysCfgDesc("res_teacher_role_flow"));
				role.put("roleIndex","/jszy/teacher/index");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
				role.put("roleName",InitConfig.getSysCfgDesc("res_doctor_role_flow"));
				role.put("roleIndex","/jszy/doctor/index");
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
        boolean isWeek = com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
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
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
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
		return "jszy/doctor/schDept";
	}

	@RequestMapping("/template/exportExcel")
	public void exportExcel(String startDate, String endDate,String schDeptFlow, String sessionNumber, HttpServletResponse response) throws IOException, Exception{
		List<String> titleDate = null;
        boolean isWeek = com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
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
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
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
				styleCenter.setAlignment(HorizontalAlignment.CENTER);

				sheet.setColumnWidth(0, 3000);
				HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
				styleLeft.setAlignment(HorizontalAlignment.LEFT);
				styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

				HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
				stylevwc.setAlignment(HorizontalAlignment.CENTER);
				stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

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
        resOrgSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
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
		return "jszy/doctor/docWorking";
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
		return "jszy/doctor/docWorkDetail";
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
                        SchRotationDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
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
		return "jszy/hospital/monthlyReportList";
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
        return "jszy/system/sys/addScore";
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
        if (result > com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
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
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
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
        return "jszy/system/sys/scoreCfgList";
    }
    /**
     * 成绩合格线配置
     * @return
     */
    @RequestMapping("/scoreCfg")
    public String scoreCfg(){
        return "jszy/system/sys/scoreCfgQueryMain";
    }

	/**
	 * 国家基地信息维护
	 * @param model
	 * @return
	 */
	@RequestMapping("/baseInfoManage")
	public String baseInfoManage(String orgFlow,Model model){
		if(StringUtil.isBlank(orgFlow)){
			orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		}
		CountryOrgInfo orgInfo = baseBiz.queryCountryOrgInfo(orgFlow);
		if(null != orgInfo && StringUtil.isNotBlank(orgInfo.getContent())){
			List<JszyCountryOrgExtInfoForm> deptList = baseBiz.parseXmlToBean(orgInfo.getContent());
			model.addAttribute("deptList",deptList);
		}
		List<AttachedUnitInfo> unitInfoList = baseBiz.queryUnitInfoList(orgFlow);
		List<AttachedUnitInfo> jointList = new ArrayList<>();
		List<AttachedUnitInfo> unitList = new ArrayList<>();
		List<String> jointStr = new ArrayList<>();
		//unitTypeId：1协同单位 2 基层实践基地
		if(null != unitInfoList && !unitInfoList.isEmpty()){
			for(AttachedUnitInfo unit : unitInfoList){
				if("1".equals(unit.getUnitTypeId())){
					jointList.add(unit);
					jointStr.add(unit.getRecordFlow());
				}else if("2".equals(unit.getUnitTypeId())){
					unitList.add(unit);
				}
			}
		}
		//协同基地查询
		List<ResJointOrg> initJoint = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
		if(null != initJoint && !initJoint.isEmpty()){
			for(ResJointOrg org : initJoint){
				if(!jointStr.contains(org.getJointOrgFlow())){
					AttachedUnitInfo aui = new AttachedUnitInfo();
					aui.setRecordFlow(org.getJointOrgFlow());
					aui.setUnitName(org.getJointOrgName());
					jointList.add(aui);
				}
			}
		}
		model.addAttribute("initJoint",initJoint);
		model.addAttribute("jointList",jointList);
		model.addAttribute("unitList",unitList);
		model.addAttribute("orgInfo",orgInfo);
		return  "jszy/hospital/countryOrgInfo";
	}
	@RequestMapping(value="/saveBaseInfo")
	@ResponseBody
	public String saveBaseInfo(CountryOrgInfo orgInfo,String jsonStr,String unitList) throws Exception {
		List<JszyCountryOrgExtInfoForm> deptList = JSON.parseArray(jsonStr,JszyCountryOrgExtInfoForm.class);
		String content = baseBiz.parseBeanToXml(deptList);
		orgInfo.setContent(content);
		int result = baseBiz.saveCountryOrgInfo(orgInfo);
		if(result > 0){
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}
	@RequestMapping("/editJointInfo")
	public String editJointInfo(String recordFlow,Model model){
		if(StringUtil.isNotBlank(recordFlow)){
			AttachedUnitInfo unitInfo = baseBiz.queryUnitInfoByFlow(recordFlow);
			model.addAttribute("unitInfo",unitInfo);
			if(null != unitInfo && StringUtil.isNotBlank(unitInfo.getContent())){
				List<JszyCountryOrgExtInfoForm> deptList = baseBiz.parseXmlToBean(unitInfo.getContent());
				model.addAttribute("deptList",deptList);
			}
			SysOrg org = orgBiz.readSysOrg(recordFlow);
			model.addAttribute("org",org);
		}
		return  "jszy/hospital/jointInfo";
	}
	@RequestMapping(value="/saveJointInfo")
	@ResponseBody
	public String saveJointInfo(AttachedUnitInfo unitInfo,String jsonStr) throws Exception {
		//协同基地才有科室信息
		if("1".equals(unitInfo.getUnitTypeId())){
			List<JszyCountryOrgExtInfoForm> deptList = JSON.parseArray(jsonStr,JszyCountryOrgExtInfoForm.class);
			String content = baseBiz.parseBeanToXml(deptList);
			unitInfo.setContent(content);
		}
		int result = baseBiz.saveJointOrgInfo(unitInfo);
		if(result > 0){
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}
	@RequestMapping(value="/delJointInfo")
	@ResponseBody
	public String delJointInfo(String recordFlow) {
		int result = baseBiz.delJointOrgInfo(recordFlow);
		if(result > 0){
            return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}
	@RequestMapping(value="/uploadFile")
	@ResponseBody
	public String uploadFile(String recordFlow,String unitTypeId,MultipartFile file){
		if(file!=null && file.getSize() > 0){
			return baseBiz.uploadFile(recordFlow,unitTypeId,file);
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}
	/**
	 * 教学活动指标
	 */
	@RequestMapping(value="/activityTarget/manageMain")
	public String manageMain(Model model,Integer currentPage, HttpServletRequest request){
		return "jszy/activity/target/main";
	}
	@RequestMapping(value="/activityTarget/add")
	public String add(Model model, HttpServletRequest request,String targetFlow){

		TeachingActivityTarget target=activityTargeBiz.readByFlow(targetFlow);
		model.addAttribute("target",target);
		return "jszy/activity/target/add";
	}
	@RequestMapping(value="/activityTarget/list")
	public String list(Model model,Integer currentPage,String targetName, HttpServletRequest request){
		SysUser curUser=GlobalContext.getCurrentUser();
		Map<String,String> param=new HashMap<>();
		param.put("orgFlow",curUser.getOrgFlow());
		if(StringUtil.isNotBlank(targetName))
		{
			targetName=targetName.trim();
		}
		param.put("targetName",targetName);
		if(currentPage==null) currentPage=1;
		Integer currentPageSize=getPageSize(request);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("currentPageSize",currentPageSize);
		PageHelper.startPage(currentPage,currentPageSize);
		List<TeachingActivityTarget> targets=activityTargeBiz.list(param);
		model.addAttribute("targets",targets);
		return "jszy/activity/target/list";
	}
	@RequestMapping(value="/activityTarget/saveAdd")
	@ResponseBody
	public String saveAdd(Model model,String targetName,String targetFlow){
		if(StringUtil.isBlank(targetName))
		{
			return "请填写指标名称！";
		}
		SysUser curUser=GlobalContext.getCurrentUser();
		TeachingActivityTarget target=activityTargeBiz.readByName(curUser.getOrgFlow(),targetName.trim());
		if(StringUtil.isNotBlank(targetFlow))
		{
			if (target != null&&!target.getTargetFlow().equals(targetFlow)) {
				return "指标名称已存在，请修改后保存！！";
			}
			target= new TeachingActivityTarget();
			target.setTargetFlow(targetFlow);
			target.setTargetName(targetName.trim());
			target.setOrgFlow(curUser.getOrgFlow());
            target.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			int c = activityTargeBiz.saveTarget(target);
			if (c == 0) {
                return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
			}
		}else {
			if (target != null) {
				return "指标名称已存在，请修改后保存！！";
			}
			TeachingActivityTarget add = new TeachingActivityTarget();
			add.setTargetName(targetName.trim());
			add.setOrgFlow(curUser.getOrgFlow());
            add.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			int c = activityTargeBiz.saveTarget(add);
			if (c == 0) {
                return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	@RequestMapping(value="/activityTarget/delTarget")
	@ResponseBody
	public String delTarget(Model model,String targetFlow){
		if(StringUtil.isBlank(targetFlow))
		{
			return "评价指标流水号为空";
		}
		int c=activityTargeBiz.delTarget(targetFlow);
		if(c==0)
		{
            return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
		}
        return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
	}

	@RequestMapping(value="/activityQuery/main")
	public String main(Model model,String  roleFlag, HttpServletRequest request){
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag))
		{
			List<SysDept> depts=deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			model.addAttribute("depts",depts);
		}else if("teach".equals(roleFlag)||"head".equals(roleFlag))
		{
			List<Map<String, Object>> depts=deptBiz.queryDeptListByFlow(GlobalContext.getCurrentUser().getUserFlow());
			model.addAttribute("depts",depts);
		}else if("doctor".equals(roleFlag))
		{
			return "jszy/activity/activityQuery/main2";
		}
		return "jszy/activity/activityQuery/main";
	}
	@RequestMapping(value="/activityQuery/list")
	public String list(Model model,Integer currentPage,String activityName,String  roleFlag,
					   String userName,String activityTypeId,String deptFlow,String isNew,String isEval,
					   String startTime,String endTime, HttpServletRequest request) throws DocumentException {
		SysUser curUser=GlobalContext.getCurrentUser();
		Map<String,String> param=new HashMap<>();
		param.put("activityName",activityName);
		param.put("userName",userName);
		param.put("activityTypeId",activityTypeId);
		param.put("deptFlow",deptFlow);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		param.put("isNew",isNew);//最新活动
		param.put("isEval",isEval);//活动评价
		param.put("roleFlag",roleFlag);
		param.put("userFlow",curUser.getUserFlow());
		model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
		if("doctor".equals(roleFlag))
		{
			ResDoctor doctor=resDoctorBiz.readDoctor(GlobalContext.getCurrentUser().getUserFlow());
			if(doctor!=null&&StringUtil.isNotBlank(doctor.getOrgFlow())){
				param.put("orgFlow",doctor.getOrgFlow());
			}
		}else {
			param.put("orgFlow", curUser.getOrgFlow());
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String,Object>> list=activityBiz.findActivityList(param);
		Map<String,Object> resultMap=new HashMap<>();
		if(list!=null) {
			for (Map<String,Object> info:list )
			{
                info.put("HaveImg", com.pinde.core.common.GlobalConstant.FLAG_N);
				String imageUrl= (String) info.get("imageUrl");
				if(StringUtil.isNotBlank(imageUrl))
				{
					Document document=DocumentHelper.parseText(imageUrl);
					Element elem=document.getRootElement();
					List<Element> ec = elem.elements("image");
					if(ec!=null&&ec.size()>0)
					{
                        info.put("HaveImg", com.pinde.core.common.GlobalConstant.FLAG_Y);
					}
				}
				if(!"doctor".equals(roleFlag))
				{
					List<Map<String,Object>>  results=activityBiz.readActivityResults((String) info.get("activityFlow"));
					resultMap.put((String) info.get("activityFlow"),results);
				}
			}
		}
		model.addAttribute("resultMap",resultMap);
		model.addAttribute("list",list);
		return "jszy/activity/activityQuery/list";
	}
	@RequestMapping(value="/activityQuery/editActivity")
	public String editActivity(Model model,String activityFlow,String role){
		List<Map<String, Object>> depts=deptBiz.queryDeptListByFlow(GlobalContext.getCurrentUser().getUserFlow());
		model.addAttribute("depts",depts);
		model.addAttribute("user",GlobalContext.getCurrentUser());
		if(StringUtil.isNotBlank(activityFlow))
		{
			TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
			model.addAttribute("activity",activity);
			if(activity!=null)
			{
				String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
				String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
				List<SysUser> userList=userBiz.readDeptTeachAndHead(activity.getDeptFlow(),teacherRoleFlow,headRoleFlow, "");
				model.addAttribute("userList",userList);
				PubFile file=fileBiz.readFile(activity.getFileFlow());
				model.addAttribute("file",file);
			}
		}
		model.addAttribute("role",role);
		return "jszy/activity/activityQuery/editActivity";
	}
	@RequestMapping(value="/activityQuery/exportList")
	public void exportList(Model model,Integer currentPage,String activityName,String  roleFlag,
						   String userName,String activityTypeId,String deptFlow,String isNew,String isEval,
						   String startTime,String endTime, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysUser curUser=GlobalContext.getCurrentUser();
		Map<String,String> param=new HashMap<>();
		param.put("activityName",activityName);
		param.put("userName",userName);
		param.put("activityTypeId",activityTypeId);
		param.put("deptFlow",deptFlow);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		param.put("isNew",isNew);//最新活动
		param.put("isEval",isEval);//活动评价
		param.put("roleFlag",roleFlag);
		param.put("userFlow",curUser.getUserFlow());
		model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
		if("doctor".equals(roleFlag))
		{
			ResDoctor doctor=resDoctorBiz.readDoctor(GlobalContext.getCurrentUser().getUserFlow());
			if(doctor!=null&&StringUtil.isNotBlank(doctor.getOrgFlow())){
				param.put("orgFlow",doctor.getOrgFlow());
			}
		}else {
			param.put("orgFlow", curUser.getOrgFlow());
		}
//		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String,Object>> list=activityBiz.findActivityList(param);
		model.addAttribute("list",list);

		String []titles = new String[]{
				"activityName:活动名称",
				"activityTypeName:活动形式",
				"activityAddress:活动地点",
				"userName:主讲人",
				"deptName:所在科室",
				"startTime:活动开始时间",
				"endTime:活动结束时间",
				"regiestNum:报名人数",
				"scanNum:签到人数",
				"evalScore:评价"
		};
		ExcleUtile.exportSimpleExcleByObjsWithWitdh(titles, list, response.getOutputStream());
		String fileName = new String("教学活动信息.xls".getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}
	@RequestMapping(value="/activityQuery/upload")
	public String upload(String activityFlow,String isUpload,Model model) throws DocumentException{

		TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
		model.addAttribute("activity",activity);
		if(activity!=null)
		{
			String content=activity.getImageUrl();
			if(StringUtil.isBlank(content))
			{
				Document dom = DocumentHelper.createDocument();
				Element root= dom.addElement("ActivityImages");
				content=root.asXML();
				activity.setImageUrl(content);
				activityBiz.saveActivity(activity);
			}
			List<Map<String, String>> imageList=activityBiz.parseImageXml(content);
			model.addAttribute("imageList", imageList);
		}
		model.addAttribute("isUpload", isUpload);
		return "jszy/activity/activityQuery/uploadImage";
	}
	@RequestMapping(value="/activityQuery/doctorMain")
	public String doctorMain(Model model,String  roleFlag,String isNew,String isEval, HttpServletRequest request){
		List<Map<String, Object>> depts=deptBiz.searchDeptByDoctor(GlobalContext.getCurrentUser().getUserFlow(), "");
		model.addAttribute("depts",depts);
		ResDoctor doctor=resDoctorBiz.readDoctor(GlobalContext.getCurrentUser().getUserFlow());
		model.addAttribute("doctor",doctor);
		return "jszy/activity/activityQuery/doctorMain";
	}
	@RequestMapping(value="/saveActivity")
	@ResponseBody
	public String saveActivity(TeachingActivityInfo activity,MultipartFile file,String isRe,String role){
        return activityBiz.editActivity(activity, file, isRe, com.pinde.core.common.GlobalConstant.FLAG_N, null, role);
	}
	@RequestMapping(value="/activityQuery/saveActivityFile")
	@ResponseBody
	public Object saveActivityFile(String activityFlow,String fileFlow,MultipartFile file){
		Map<String,String> resp=new HashMap<>();
		resp.put("code","1");
		TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
		if(activity==null)
		{
			resp.put("code","0");
			resp.put("msg","教学活动信息不存在，请刷新页面！");
			return resp;
		}
		if(!(file!=null&&!file.isEmpty()&&file.getSize()>0)){
			resp.put("code","0");
			resp.put("msg","请选择需要上传的文件！");
			return resp;
		}
		fileFlow=activityBiz.saveActivityFile(fileFlow,file);
		resp.put("fileFlow",fileFlow);
		resp.put("fileName",file.getOriginalFilename());
		if(StringUtil.isBlank(fileFlow))
		{
			resp.put("code","0");
			resp.put("msg","上传文件失败！");
			return resp;
		}
		activity.setFileFlow(fileFlow);
		int c=activityBiz.saveActivity(activity);
		if(c==0)
		{
			resp.put("code","0");
			resp.put("msg","上传文件失败！");
			return resp;
		}
		return resp;
	}
	@RequestMapping(value="/activityQuery/loadTeacherAndHead")
	@ResponseBody
	public Object loadTeacherAndHead(String deptFlow){
		if(StringUtil.isNotBlank(deptFlow)){
			String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
			String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
			List<SysUser> userList=userBiz.readDeptTeachAndHead(deptFlow,teacherRoleFlow,headRoleFlow, "");
			return userList;
		}
		return null;
	}
	@RequestMapping(value="/activityQuery/joinActivity")
	@ResponseBody
	public String joinActivity(Model model,String activityFlow){
		if(StringUtil.isNotBlank(activityFlow))
		{
			TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
            if (info == null || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(info.getRecordStatus()))
				return "活动信息不存在，请刷新列表页面！";

			if( DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(info.getStartTime())>0)
			{
				return "活动已开始，无法报名！";
			}
			SysUser user=GlobalContext.getCurrentUser();
			TeachingActivityResult result=activityBiz.readRegistInfo(activityFlow,user.getUserFlow());
            if (result != null && com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(result.getIsRegiest()))
			{
				return "你已报名，请勿重复报名！";
			}
			int count=activityBiz.checkJoin(activityFlow,user.getUserFlow());
			if(count>0)
			{
				return "该时间段，你已报名参加其他教学活动！";
			}
			if(result==null){
				result=new TeachingActivityResult();
			}
			result.setActivityFlow(activityFlow);
			result.setUserFlow(user.getUserFlow());
            result.setIsRegiest(com.pinde.core.common.GlobalConstant.FLAG_Y);
			result.setRegiestTime(DateUtil.getCurrDateTime());
            result.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			int c=activityBiz.saveRegist(result);
			if(c==0)
				return "报名失败！";
			return "报名成功！";
		}else
			return "请选择将要参加的活动！";

	}
	@RequestMapping("/activityQuery/signUrl")
	public String signUrl(String activityFlow,Model model){
		model.addAttribute("activityFlow",activityFlow);
		TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
		model.addAttribute("activity",activity);
		return "jszy/activity/activityQuery/activityCode";
	}
	@RequestMapping(value="/activityQuery/activityDetail")
	public String activityDetail(Model model,String activityFlow,String roleFlag){
		Map<String,Object> activity=activityBiz.readActivity(activityFlow);
		model.addAttribute("activity",activity);
		return "jszy/activity/activityQuery/activityDetail";
	}
	@RequestMapping(value="/activityQuery/delActivity")
	@ResponseBody
	public String delActivity(Model model,String activityFlow){
		if(StringUtil.isNotBlank(activityFlow))
		{
			TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
			if(info==null)
				return "活动信息不存在，请刷新列表页面！";
			if( DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(info.getStartTime())>=0)
			{
				return "活动已开始，无法删除！";
			}
            info.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
			int c=activityBiz.saveActivity(info);
			if(c==0)
                return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
            return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
		}else
			return "请选择需要删除的活动！";

	}
	@RequestMapping(value="/activityQuery/showEvalType")
	public String showEvalType(Model model,String activityFlow,String roleFlag){

		List<Map<String,Object>> regists=activityBiz.readActivityRegists(activityFlow);
		List<Map<String,Object>> results=activityBiz.readActivityResults(activityFlow);
		int scanNum=0;
		if(results!=null) scanNum=results.size();
		int registNum=0;
		if(regists!=null) registNum=regists.size();
		model.addAttribute("scanNum",scanNum);
		model.addAttribute("registNum",registNum);
		return "jszy/activity/activityQuery/showEvalType";
	}
	@RequestMapping(value="/activityQuery/showDocEval")
	public String showDocEval(Model model,String resultFlow){
		//评价人员
		TeachingActivityResult result=activityBiz.readResult(resultFlow);
		model.addAttribute("result",result);
		//评价人员评分详情
		Map<String,Object> evalDetailMap=new HashMap<>();
		if(result!=null)
		{
			//评价的指标
			List<Map<String,Object>> targets=activityTargeBiz.readActivityTargetEvals(result.getActivityFlow());
			model.addAttribute("targets",targets);
			List<TeachingActivityEval> evals=activityBiz.readActivityResultEvals(resultFlow);
			if(evals!=null)
			{
				for(TeachingActivityEval e:evals)
				{
					evalDetailMap.put(e.getResultFlow()+e.getTargetFlow(),e.getEvalScore());
				}
			}
		}
		model.addAttribute("evalDetailMap",evalDetailMap);
		return "jszy/activity/activityQuery/showDocEval";
	}
	@RequestMapping(value="/activityQuery/checkEval")
	@ResponseBody
	public String checkEval(String resultFlow){
		if(StringUtil.isNotBlank(resultFlow)){
			TeachingActivityResult result=activityBiz.readResult(resultFlow);
			if(result==null)
			{
				return "你未参加该活动，无法评价";
			}
			if(result!=null&&result.getEvalScore()!=null)
			{
				return "该活动已评价，请刷新页面！";
			}else{
				return "";
			}
		}else {
			return "请选择需要评价的活动！";
		}
	}
	@RequestMapping(value="/activityQuery/evalInfo")
	public String evalInfo(Model model,String resultFlow){
		//评价人员
		TeachingActivityResult result=activityBiz.readResult(resultFlow);
		model.addAttribute("result",result);
		//评分项
		List<TeachingActivityInfoTarget> targets=activityTargeBiz.readActivityTargets(result.getActivityFlow());
		model.addAttribute("targets",targets);
		return "jszy/activity/activityQuery/evalInfo";
	}
	//下载附件
	@RequestMapping(value = {"/activityQuery/downFile" }, method = RequestMethod.GET)
	public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception{
		PubFile file = this.fileBiz.readFile(fileFlow);
		downPubFile(file,response);
	}
	public void downPubFile(PubFile file, HttpServletResponse response) throws Exception {
		/*文件是否存在*/
		Boolean fileExists = false;
		if(file !=null){
			byte[] data=null;
			long dataLength = 0;
            /*如果上传类型为“1”(文件保存在磁盘)，且文件相对路径不为空*/
			if(StringUtil.isNotBlank(file.getFilePath())&&StringUtil.isNotBlank(InitConfig.getSysCfg("upload_base_dir"))){
                /*获取文件物理路径*/
				String filePath =InitConfig.getSysCfg("upload_base_dir")+file.getFilePath();
				File downLoadFile = new File(filePath);
                /*文件是否存在*/
				if(downLoadFile.exists()){
					InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
					data = new byte[fis.available()];
					dataLength = downLoadFile.length();
					fis.read(data);
					fis.close();
					fileExists = true;
				}
			}
			if(fileExists) {
				try {

					String fileName = file.getFileName();
					response.reset();
					response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"),"ISO8859-1" )  + "\"");
					response.addHeader("Content-Length", "" + dataLength);
					response.setContentType("application/octet-stream;charset=UTF-8");
					OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
					if (data != null) {
						outputStream.write(data);
					}
					outputStream.flush();
					outputStream.close();
				}catch (IOException e){
					fileExists = false;
				}
			}
		}else {
			fileExists = false;
		}
		if(!fileExists){
            /*设置页面编码为UTF-8*/
			response.setHeader("Content-Type","text/html;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write("<a href='javascript:history.go(-1)'>未发现文件,点击返回上一页</a>".getBytes("UTF-8"));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
			outputStream.flush();
			outputStream.close();
		}
	}
	@RequestMapping(value = {"/activityQuery/saveEvalInfo"}, method = {RequestMethod.POST,RequestMethod.GET})
	public	@ResponseBody String saveEvalInfo(String evals, String resultFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws DocumentException {
		if(StringUtil.isBlank(resultFlow))
		{
			return "请选择需要评价的活动！";
		}else{
			TeachingActivityResult result=activityBiz.readResult(resultFlow);
			if(result==null)
			{
				return "你未参加该活动，无法评价";
			}
			if(result!=null&&result.getEvalScore()!=null)
			{
				return "该活动已评价，请刷新页面！";
			}
		}
		if(StringUtil.isBlank(evals))
		{
			return "请选择评分！";
		}
		List<TeachingActivityEval> activityEvals=null;
		try {
			activityEvals=JSON.parseArray(evals, TeachingActivityEval.class);
		}catch (Exception e){
			return "提交数据格式不正确！";
		}
		if(activityEvals==null||activityEvals.size()<=0)
		{
			return "请选择评分！";
		}
		int count=activityBiz.saveEvalInfo(activityEvals, resultFlow);
		if(count==0)
		{
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	@RequestMapping(value="/activityQuery/showRegistEval")
	public String showRegistEval(Model model,String activityFlow,String roleFlag){

		//评价人员
		List<Map<String,Object>> results=activityBiz.readActivityRegists(activityFlow);
		model.addAttribute("results",results);
		return "jszy/activity/activityQuery/showRegistEval";
	}
	@RequestMapping(value="/activityQuery/showEval")
	public String showEval(Model model,String activityFlow,String roleFlag){
		TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
		model.addAttribute("activity",info);
		model.addAttribute("user",GlobalContext.getCurrentUser());
		//评价的指标
		List<Map<String,Object>> targets=activityTargeBiz.readActivityTargetEvals(activityFlow);
		model.addAttribute("targets",targets);
		//评价人员
		List<Map<String,Object>> results=activityBiz.readActivityResults(activityFlow);
		model.addAttribute("results",results);
		//评价人员评分详情
		Map<String,Object> evalDetailMap=new HashMap<>();
		if(results!=null)
		{
			for(Map<String,Object> r:results)
			{
				List<TeachingActivityEval> evals=activityBiz.readActivityResultEvals(String.valueOf(r.get("resultFlow")));
				if(evals!=null)
				{
					for(TeachingActivityEval e:evals)
					{
						evalDetailMap.put(e.getResultFlow()+e.getTargetFlow(),e.getEvalScore());
					}
				}
			}
		}
		model.addAttribute("evalDetailMap",evalDetailMap);
		return "jszy/activity/activityQuery/showEval";
	}
	@RequestMapping(value="/activityQuery/exportRegiestList")
	public void exportRegiestList(Model model,String activityFlow ,HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Map<String,Object>> results=activityBiz.readActivityRegists(activityFlow);
		String []titles = new String[]{
				"userName:姓名",
				"sessionNumber:年级",
				"speName:专业",
				"regiestTime1:报名时间"
		};
		TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
		ExcleUtile.exportSimpleExcleByObjsWithWitdh(titles, results, response.getOutputStream());
		String fileName = new String((info.getActivityName()+"-报名学员表.xls").getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}
	@RequestMapping(value="/activityQuery/exportSiginList")
	public void exportSiginList(Model model,String activityFlow ,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map<String,Object>> results=activityBiz.readActivityResults(activityFlow);
		String []titles = new String[]{
				"userName:姓名",
				"sessionNumber:年级",
				"speName:专业",
				"siginTime:签到时间",
				"siginTime2:签退时间",
				"evalScore:评分"
		};
		TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
		ExcleUtile.exportSimpleExcleByObjsWithWitdh(titles, results, response.getOutputStream());
		String fileName = new String((info.getActivityName()+"-签到学员表.xls").getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}
	@RequestMapping(value="/activityQuery/effectiveResult")
	@ResponseBody
	public String effectiveResult(Model model,String resultFlow,String isEffective){
		if(StringUtil.isNotBlank(resultFlow))
		{
			if(StringUtil.isBlank(isEffective))
			{
				return "请选择需要审核的类型！";
			}
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isEffective) && !com.pinde.core.common.GlobalConstant.FLAG_N.equals(isEffective))
			{
				return "请选择【认可】还是【不认可】！";
			}
			TeachingActivityResult info=activityBiz.readResult(resultFlow);
			if(info==null)
				return "学员参加活动结果信息不存在，请刷新列表页面！";
			info.setIsEffective(isEffective);
			int c=activityBiz.saveResult(info);
			if(c==0)
				return "审核失败";
			return "审核成功";
		}else
			return "请选择需要审核的参加活动结果信息！";

	}
}
