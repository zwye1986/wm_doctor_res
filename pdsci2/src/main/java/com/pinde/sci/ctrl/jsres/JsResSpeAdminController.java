package com.pinde.sci.ctrl.jsres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchAndStandardDeptCfgBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.JsresPowerCfgMapper;
import com.pinde.sci.dao.base.SchAndStandardDeptCfgMapper;
import com.pinde.sci.enums.jsres.JsResDocTypeEnum;
import com.pinde.sci.enums.res.ResAssessTypeEnum;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.form.jsres.BaseSpeDept.BaseSpeDeptExtForm;
import com.pinde.sci.form.jsres.BaseSpeDept.BaseSpeDeptForm;
import com.pinde.sci.form.jsres.BaseSpeDept.DeptBasicInfoForm;
import com.pinde.sci.form.res.ResAssessCfgItemForm;
import com.pinde.sci.form.res.ResAssessCfgTitleForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.vo.ResSpeBaseStdDeptVO;
import com.pinde.sci.model.vo.ResStandardDeptVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/jsres/speAdmin")
public class JsResSpeAdminController extends GeneralController{

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
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IUserBiz iUserBiz;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private ISchRotationDeptBiz iSchRotationDeptBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IResScoreBiz resScoreBiz;
	@Autowired
	private IResAssessCfgBiz assessCfgBiz;
	@Autowired
	private IResDoctorProcessBiz iResDoctorProcessBiz;
	@Autowired
	private IResGradeBiz resGradeBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IResInprocessInfoBiz resInprocessInfoBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private JsresPowerCfgMapper jsresPowerCfgMapper;
	@Autowired
	private IJsResActivityBiz activityBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IJsResActivityTargetBiz activityTargeBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IDeptBasicInfoBiz deptBasicInfoBiz;
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private ISchAndStandardDeptCfgBiz deptCfgBiz;
	@Autowired
	private ISchAndStandardDeptCfgBiz schAndStandardDeptCfgBiz;
	@Autowired
	private SchAndStandardDeptCfgMapper schAndStandardDeptCfgMapper;

	@Autowired
	private IJsResDeptManagementBiz deptManagementBiz;

	/**
	 * 入科教育管理标签页
	 */
	@RequestMapping(value = "/admissionEducationMain")
	public String admissionEducationMain(Model model, Integer currentPage, HttpServletRequest request, SchArrangeResult arrangeResult) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		List<SysUserDept> userDepts = iUserBiz.searchUserDeptByUser(currentUser.getUserFlow());
		model.addAttribute("userDepts", userDepts);
		return "jsres/speAdmin/admissionEduTap";
	}

	/**
	 * 入科教育管理
	 */
	@RequestMapping(value = "/admissionEducationManage")
	public String admissionEducationManage(Model model, String role, String deptFlow, String orgFlow) {
		model.addAttribute("role", role);
		SysUser currentUser = GlobalContext.getCurrentUser();
		if(StringUtil.isBlank(orgFlow))
			orgFlow = currentUser.getOrgFlow();
		SysDept dept = deptBiz.readSysDept(deptFlow);
		model.addAttribute("dept", dept);
		ResInprocessInfo info = resInprocessInfoBiz.readByDeptFlowAndOrgFlow(deptFlow, orgFlow);
		model.addAttribute("info", info);
		if (info != null) {
			List<ResInprocessInfoMember> members = resInprocessInfoBiz.readMembersByRecordFlow(info.getRecordFlow());
			model.addAttribute("members", members);
			List<PubFile> files = pubFileBiz.searchByProductFlow(info.getRecordFlow());
			model.addAttribute("files", files);
			String arrange = info.getTeachingArrangement();
			if (StringUtil.isNotBlank(arrange)) {
				Map<String, Object> arrangeMap = new HashMap<>();
				arrangeMap = paressXml(arrange);
				model.addAttribute("arrangeMap", arrangeMap);
			}
		}
		return "jsres/speAdmin/admissionEduInfo";
	}

	private Map<String, Object> paressXml(String content) {
		Map<String, Object> formDataMap = null;
		if (StringUtil.isNotBlank(content)) {
			formDataMap = new HashMap<String, Object>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				List<Element> elements = rootElement.elements();
				for (Element element : elements) {
					formDataMap.put(element.getName(), element.getText());
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return formDataMap;
	}


	@RequestMapping(value="/activityQueryMain")
	public String activityQueryMain(Model model){
		List<Map<String, Object>> depts = deptBiz.queryDeptListByFlow(GlobalContext.getCurrentUser().getUserFlow());
		model.addAttribute("depts",depts);
		return "jsres/speAdmin/activityQueryMain";
	}

	@RequestMapping(value="/activityQuerylist")
	public String list(Model model,Integer currentPage,String activityName,String  isCurrent,
					   String userName,String activityTypeId,String deptFlow,String deptName,String isNew,String isEval,String isEffective,
					   String startTime,String endTime,String activityStatus, HttpServletRequest request) throws DocumentException {
		SysUser curUser=GlobalContext.getCurrentUser();
		SysOrg currentOrg = orgBiz.readSysOrg(curUser.getOrgFlow());
		Map<String,String> param=new HashMap<>();
		param.put("activityName",activityName);
		param.put("userName",userName);
		param.put("activityTypeId",activityTypeId);
		param.put("deptFlow",deptFlow);
		param.put("deptName",deptName);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		param.put("isNew",isNew);//最新活动
		param.put("isEval",isEval);//活动评价
		param.put("isCurrent",isCurrent);
		param.put("activityStatus",activityStatus);
		param.put("roleFlag","head");
		assert curUser != null;
		param.put("userFlow",curUser.getUserFlow());
		// 是否有效 1：是 0 ：否
		param.put("isEffective",isEffective);
		model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
		param.put("orgFlow", curUser.getOrgFlow());
		List<SysUserRole> userRoleList = userRoleBiz.getByUserFlow(GlobalContext.getCurrentUser().getUserFlow()); //获取该用户下的所有角色信息
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String,Object>> list=activityBiz.findActivityList(param);
		for (Map<String,Object> obj: list) {
			for (SysUserRole role:userRoleList) {
				if(obj.containsKey("auditRole")) {
					if (obj.get("auditRole").toString().contains(role.getRoleFlow())) {
						obj.put("audit", "Y");
					}
				}
			}
		}
		Map<String,Object> resultMap=new HashMap<>();
		if(list!=null) {
			for (Map<String,Object> info:list )
			{
				info.put("HaveImg","N");
				String imageUrl= (String) info.get("imageUrl");
				if(StringUtil.isNotBlank(imageUrl))
				{
					Document document=DocumentHelper.parseText(imageUrl);
					Element elem=document.getRootElement();
					List<Element> ec = elem.elements("image");
					if(ec!=null&&ec.size()>0)
					{
						info.put("HaveImg","Y");
					}
				}
				List<Map<String,Object>>  results=activityBiz.readActivityResults((String) info.get("activityFlow"));
				resultMap.put((String) info.get("activityFlow"),results);
			}
		}
		model.addAttribute("resultMap",resultMap);
		model.addAttribute("list",list);
		return "jsres/speAdmin/activityQuerylist";
	}

	@RequestMapping(value="/activityExportList")
	public void activityExportList(String activityName,String isEffective,String userName,String activityTypeId,
								   String deptFlow,String deptName,String isNew,String isEval,String startTime,
								   String endTime, HttpServletResponse response) throws Exception {
		SysUser curUser=GlobalContext.getCurrentUser();
		Map<String,String> param=new HashMap<>();
		param.put("activityName",activityName);
		param.put("userName",userName);
		param.put("activityTypeId",activityTypeId);
		param.put("deptFlow",deptFlow);
		param.put("deptName",deptName);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		param.put("isNew",isNew);//最新活动
		param.put("isEval",isEval);//活动评价
		param.put("roleFlag","head");
		param.put("userFlow",curUser.getUserFlow());
		param.put("isEffective",isEffective);
		param.put("orgFlow", curUser.getOrgFlow());
		List<Map<String,Object>> list=activityBiz.findActivityList(param);
		String []titles = new String[]{
				"activityName:活动名称",
				"activityTypeName:活动形式",
				"activityAddress:活动地点",
				"userName:主讲人",
				"realitySpeaker:实际主讲人",
				"deptName:所在科室",
				"startTime:活动开始时间",
				"endTime:活动结束时间",
				"regiestNum:报名人数",
				"scanNum:签到人数",
				"evalScore:评价"
		};
		String fileName = new String("教学活动信息.xls".getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		ExcleUtile.exportSimpleExcleByObjsWithWitdh(titles, list, response.getOutputStream());
		response.setContentType("application/octet-stream;charset=UTF-8");
	}


	@RequestMapping(value="/showEvalType")
	public String showEvalType(Model model,String activityFlow){

		List<Map<String,Object>> regists=activityBiz.readActivityRegists(activityFlow);
		List<Map<String,Object>> results=activityBiz.readActivityResults(activityFlow);
		int scanNum=0;
		if(results!=null) scanNum=results.size();
		int registNum=0;
		if(regists!=null) registNum=regists.size();
		model.addAttribute("scanNum",scanNum);
		model.addAttribute("registNum",registNum);
		return "jsres/speAdmin/showEvalType";
	}

	@RequestMapping(value="/showRegistEval")
	public String showRegistEval(Model model,String activityFlow,String roleFlag){

		//评价人员
		List<Map<String,Object>> results=activityBiz.readActivityRegists(activityFlow);
		model.addAttribute("results",results);
		return "jsres/speAdmin/showRegistEval";
	}

	@RequestMapping(value="/showEval")
	public String showEval(Model model,String activityFlow ){
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
		Map<String,Object> evalRemarksMap=new HashMap<>();
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
						evalRemarksMap.put(e.getResultFlow()+e.getTargetFlow(),e.getEvalRemarks());
					}
				}
			}
		}
		model.addAttribute("evalDetailMap",evalDetailMap);
		model.addAttribute("evalRemarksMap",evalRemarksMap);
		//查看基地 教学活动评价配置
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		JsresPowerCfg orgApprove = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_org_ctrl_approve_activity");//教学活动评价配置
		JsresPowerCfg approve = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_org_approve_activity");//教学活动评价配置评审类型
		if (null!=orgApprove && null!=approve && StringUtil.isNotNullAndEquala(approve.getCfgValue(),orgApprove.getCfgValue(),"Y")){
			model.addAttribute("approve","Y");
		}
		return "jsres/speAdmin/showEval";
	}

	/**
	 * 培训数据查询
	 */
	@RequestMapping(value="/recruitAuditSearch")
	public String recruitAuditSearch(Model model,Integer currentPage,HttpServletRequest request,SchArrangeResult arrangeResult, String datas[]){
		SysUser sysUser=GlobalContext.getCurrentUser();
		List<SysUserDept> depts = deptBiz.searchByUserFlow(sysUser.getUserFlow(), sysUser.getOrgFlow());
		model.addAttribute("depts",depts);
		Map<String, Object> schArrangeResultMap=new HashMap<String, Object>();
		schArrangeResultMap.put("deptFlow", sysUser.getDeptFlow());
		schArrangeResultMap.put("userFlow", sysUser.getUserFlow());
		schArrangeResultMap.put("doctorName", arrangeResult.getDoctorName());
		schArrangeResultMap.put("schStartDate", arrangeResult.getSchStartDate());
		schArrangeResultMap.put("schEndDate", arrangeResult.getSchEndDate());
		schArrangeResultMap.put("schDeptFlow", arrangeResult.getSchDeptFlow());
		List<String>docTypeList=new ArrayList<String>();
		model.addAttribute("datas", datas);
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
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, String>> schArrangeResult=iResDoctorProcessBiz.jsresSchDoctorSchProcessHead(schArrangeResultMap);

		model.addAttribute("schArrangeResult", schArrangeResult);
		return "jsres/speAdmin/recruitAuditSearch";
	}

	/**
	 * 江苏西医--培训数据查询
	 * @param doctorFlow
	 * @param processFlow
	 * @param recTypeId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/details",method={RequestMethod.GET,RequestMethod.POST})
	public String details(String doctorFlow,String processFlow,String recTypeId,Model model) throws Exception{
		if (StringUtil.isBlank(recTypeId)) {
			return "jsres/speAdmin/registrationRecord";
		}
		Map<String, Map<String,Object>> resRecMap=new HashMap<String, Map<String,Object>>();
		List<ResRec> resRecList = null;
		List<ResSchProcessExpress> expressList = null;
		if(recTypeId.equals(ResRecTypeEnum.AfterEvaluation.getId())){
			expressList = expressBiz.searchResRecExpressWithBLOBs(recTypeId,processFlow);
			if (expressList!=null&&expressList.size()>0) {
				for (ResSchProcessExpress express : expressList){
					String recContent = express.getRecContent();
					Map<String,Object> formDataMap = resRecBiz.parseRecContent(recContent);
					resRecMap.put(express.getRecFlow(), formDataMap);
				}
			}
			model.addAttribute("resRecList", expressList);
			model.addAttribute("resRecMap", resRecMap);
			JsresPowerCfg powerCfg = jsresPowerCfgMapper.selectByPrimaryKey("jsres_doctor_app_menu_" + doctorFlow);
			if (powerCfg != null) {
				model.addAttribute("statusId", powerCfg.getCheckStatusId());
			}
		}else{
			if(ResRecTypeEnum.CampaignRegistry.getId().equals(recTypeId))
			{

				List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
				List<Map<String,Object>> list=resRecBiz.queryJoinActivityAndEditList(doctorFlow,recTypeId,processFlow);
				if(list!=null)
				{
					for(Map<String,Object> rec:list)
					{
						Map<String, Object> formDataMap=new HashMap<>();
						if(rec.get("recTypeId")!=null&&StringUtil.isNotBlank(String.valueOf(rec.get("recTypeId")))) {
							String recContent = String.valueOf(rec.get("recContent"));
							formDataMap = resRecBiz.parseRecContent(recContent);
						}else{
							formDataMap.put("activity_way", rec.get("activityTypeName")==null?"":String.valueOf(rec.get("activityTypeName")));
							formDataMap.put("activity_date", rec.get("startTime")==null?"":String.valueOf(rec.get("startTime")));
							formDataMap.put("activity_period", "1");
							formDataMap.put("activity_speaker", rec.get("speakerName")==null?"":String.valueOf(rec.get("speakerName")));
							String activity_content=rec.get("activityName")==null?"":String.valueOf(rec.get("activityName"))+
									rec.get("activityRemark")==null?"":String.valueOf(rec.get("activityRemark"));
							formDataMap.put("activity_content", activity_content);
							formDataMap.put("activity_address", rec.get("activityAddress")==null?"":String.valueOf(rec.get("activityAddress")));
							formDataMap.put("isJoin","Y");
						}
						formDataMap.put("recFlow", String.valueOf(rec.get("recFlow")));
						dataList.add(formDataMap);
					}
				}
				model.addAttribute("resRecList", dataList);
			}else {
				resRecList = resRecBiz.searchResRecWithBLOBs(recTypeId, processFlow, doctorFlow);
				if (resRecList != null && resRecList.size() > 0) {
					for (ResRec resRec : resRecList) {
						String recContent = resRec.getRecContent();
						Map<String, Object> formDataMap = resRecBiz.parseRecContent(recContent);
						resRecMap.put(resRec.getRecFlow(), formDataMap);
					}
				}
				model.addAttribute("resRecList", resRecList);
				model.addAttribute("resRecMap", resRecMap);
			}
		}


		if(recTypeId.equals(ResRecTypeEnum.AfterEvaluation.getId())){
			String operUserFlow=doctorFlow;
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
//				int teachingRounds = 0;
//				int difficult = 0;
//				int lecture = 0;
//				int death = 0;
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
//				List<ResSchProcessExpress> recs = expressBiz.searchRecByProcessWithBLOBs(recTypes, processFlow, operUserFlow);

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
		}



		if(ResRecTypeEnum.AfterEvaluation.getId().equals(recTypeId)){
			if (expressList!=null && expressList.size()>0) {
				ResSchProcessExpress rec=expressList.get(0);
				if (rec!=null) {
					Map<String, Object> formDataMap=resRecMap.get(rec.getRecFlow());
					model.addAttribute("formDataMap", formDataMap);
				}
				model.addAttribute("rec", rec);
				boolean f = false;
				f=resDoctorBiz.getCkkPower(doctorFlow);
				if (f) {
					ResScore outScore = resScoreBiz.getScoreByProcess(processFlow);
					model.addAttribute("outScore",outScore);
				}
				model.addAttribute("ckk", f);

			}
		}

		if(ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId)||ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)){
			String cfgCodeId = null;
			if(ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId)){
				cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
			}else if(ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)){
				cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
			}
			if (resRecList!=null && resRecList.size()>0) {
				ResRec rec=resRecList.get(0);
				if (rec!=null) {
					model.addAttribute("rec",rec);
					Map<String,Object> gradeMap = resRecBiz.parseGradeXml(rec.getRecContent());
					model.addAttribute("gradeMap",gradeMap);
				}
			}
			ResAssessCfg search = new ResAssessCfg();
			search.setCfgCodeId(cfgCodeId);
//			search.setOrgFlow(currUser.getOrgFlow());
			List<ResAssessCfg> assessCfgList = assessCfgBiz.searchAssessCfgList(search);
			if(assessCfgList != null && !assessCfgList.isEmpty()){
				ResAssessCfg assessCfg = assessCfgList.get(0);
				model.addAttribute("assessCfg",assessCfg);
				Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(titleElementList != null && !titleElementList.isEmpty()){
					List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
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
					model.addAttribute("titleFormList", titleFormList);
				}
			}
		}

		return "jsres/speAdmin/registrationRecord";
	}


	/**
	 * 评价查询
	 */
	@RequestMapping(value="/evaluateSearch")
	public String evaluateSearch(Model model,String roleType,String doctorName,String startDate,String endDate,String deptFlow) throws Exception{
		SysUser sysUser=GlobalContext.getCurrentUser();
		String cfgTeacher=InitConfig.getSysCfg("res_teacher_role_flow");
		//查询出当前用户所在所有科室
		List<SysUserDept> deptList = resGradeBiz.searDeptNames(sysUser.getUserFlow());
		model.addAttribute("deptList",deptList);
		if (GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleType)) {
			List<SysUser> sysUserList=iUserBiz.teacherRoleCheckUser2(deptFlow,cfgTeacher,doctorName,sysUser.getUserFlow());
			Map<String, Integer> studentNumMap=new HashMap<String, Integer>();
			Map<String, String> averageMap=new HashMap<String, String>();
			for (SysUser user : sysUserList) {
				String currKey="curr"+user.getUserFlow();
				String studentKey="student"+user.getUserFlow();
				int currStudentHe=resDoctorProcessBiz.jsresSchDoctorSchProcessDistinctQuery(user.getUserFlow());
				int studentNum=resDoctorProcessBiz.jsresSchDoctorSchProcessTeacherQuery(user.getUserFlow());
				studentNumMap.put(currKey, currStudentHe);
				studentNumMap.put(studentKey, studentNum);
				List<DeptTeacherGradeInfo> gradeInfoList=resGradeBiz.searchProssFlowRec(user.getUserFlow(),startDate,endDate, null);
				Float sum=0f;
				Float average=0f;
				if (gradeInfoList!=null && gradeInfoList.size()>0) {
					for (DeptTeacherGradeInfo gradeInfo : gradeInfoList) {
						Map<String,Object> gradeMap = resRecBiz.parseGradeXml(gradeInfo.getRecContent());
						Float zongFen= Float.parseFloat(gradeMap.get("totalScore").toString());
						sum=sum+zongFen;
					}
					average=sum/gradeInfoList.size();
				}
				BigDecimal bd=new BigDecimal(average).setScale(1, RoundingMode.UP);
				averageMap.put(user.getUserFlow(), bd.toString());
			}
			final Map<String, String> averageMapTemp = averageMap;
			java.util.Collections.sort(sysUserList,new Comparator<SysUser>() {
				@Override
				public int compare(SysUser o1, SysUser o2) {
					String	o1Key=o1.getUserFlow();
					String	o2Key=o2.getUserFlow();
					String s1=averageMapTemp.get(o1Key);
					String s2=averageMapTemp.get(o2Key);
					Float f1=0f;
					Float f2=0f;
					try {
						if (s1 != null && s2 != null) {
							f1 = Float.valueOf(s1);
							f2 = Float.valueOf(s2);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					Float result=f2-f1;
					return result>0?1:result==0?0:-1;
				}
			});
			model.addAttribute("averageMap", averageMap);
			model.addAttribute("studentNumMap", studentNumMap);
			model.addAttribute("sysUserList", sysUserList);
		}
		if (GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleType)) {
			model.addAttribute("sysUser",sysUser);
			if(StringUtil.isEmpty(deptFlow)){
				deptFlow = sysUser.getDeptFlow();
			}
			List<DeptTeacherGradeInfo> gradeInfoList=resGradeBiz.searchDeptFlowRec(deptFlow,startDate,endDate);
			Float sum=0f;
			Map<String, Float> heJiMap=new HashMap<String, Float>();
			if (gradeInfoList!=null && gradeInfoList.size()>0) {
				for (DeptTeacherGradeInfo gradeInfo : gradeInfoList) {
					Map<String,Object> gradeMap = resRecBiz.parseGradeXml(gradeInfo.getRecContent());
					for (String key: gradeMap.keySet()) {
						Object o=gradeMap.get(key);
						if (o instanceof String) {
							continue;
						}
						Map<String,String> scoreMap=(Map<String,String>)o;
						Float value=heJiMap.get(key);
						if (value==null) {
							value=0f;
						}
						Float deValue= 0f;
						try {
							if(StringUtil.isNotBlank(scoreMap.get("score"))){
								deValue= Float.parseFloat(scoreMap.get("score"));
							}
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
						value=value+deValue;
						heJiMap.put(key, value);
					}
				}
				for (String key: heJiMap.keySet()) {
					Float value=heJiMap.get(key);
					value=value/gradeInfoList.size();
					BigDecimal bd=new BigDecimal(value).setScale(1, BigDecimal.ROUND_HALF_UP);
					heJiMap.put(key, bd.floatValue());
					sum=sum+bd.floatValue();
				}
			}
			String cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
			ResAssessCfg search = new ResAssessCfg();
			search.setCfgCodeId(cfgCodeId);
//			search.setOrgFlow(currUser.getOrgFlow());
			List<ResAssessCfg> assessCfgList = assessCfgBiz.searchAssessCfgList(search);
			if(assessCfgList != null && !assessCfgList.isEmpty()){
				ResAssessCfg assessCfg = assessCfgList.get(0);
				model.addAttribute("assessCfg",assessCfg);
				Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(titleElementList != null && !titleElementList.isEmpty()){
					List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
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
					model.addAttribute("titleFormList", titleFormList);
				}
			}
			model.addAttribute("heJiMap", heJiMap);
			BigDecimal ii=new BigDecimal(sum).setScale(1, BigDecimal.ROUND_HALF_UP);
			sum=ii.floatValue();
			model.addAttribute("average", sum);
		}
		return "jsres/speAdmin/evaluateSearch";
	}


	/**
	 * 学员考评
	 */
	@RequestMapping(value="/docProcessEvalMain")
	public String docProcessEval(Model model){
		//查询出当前用户所在所有科室
		List<SysUserDept> deptList = resGradeBiz.searDeptNames(GlobalContext.getCurrentUser().getUserFlow());
		model.addAttribute("deptList", deptList);
		return "jsres/speAdmin/docProcessEval/main";
	}

	@RequestMapping(value="/docProcessEvalList")
	public String docProcessEvalList(Model model,Integer currentPage ,HttpServletRequest request,
									 String trainingTypeId,String trainingSpeId,String datas[],
									 String sessionNumber,String graduationYear,
									 String userName,String idNo,String schDeptFlow
	) throws ParseException {
		SysUser currentUser = GlobalContext.getCurrentUser();
		//查询条件
		Map<String,Object> param=new HashMap<>();
		List<String>docTypeList=new ArrayList<String>();//人员类型
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		param.put("deptFlow", currentUser.getDeptFlow());
		param.put("userFlow", currentUser.getUserFlow());
		param.put("docTypeList",docTypeList);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("graduationYear",graduationYear);
		param.put("userName",userName);
		param.put("idNo",idNo);
		param.put("role","head");
		param.put("schDeptFlow",schDeptFlow);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> list=iResDoctorProcessBiz.jsresSchDoctorSchProcessEval(param);
		Map<String,Integer> evalSizeMap=new HashMap<>();
		Map<String,	List<Map<String, Object>>> evalMap=new HashMap<>();
		if(list!=null&&list.size()>0)
		{
			for(Map<String,Object> process:list)
			{
				String startDate= (String) process.get("schStartDate");
				String endDate= (String) process.get("schEndDate");
				String processFlow= (String) process.get("processFlow");
				List<Map<String, String>> times=getTimes(startDate,endDate,processFlow);
				evalSizeMap.put(processFlow,times.size());
				if(times.size()>0)
				{
					Map<String, Object> params=new HashMap<String, Object>();
					params.put("times", times);
					List<Map<String, Object>> evals=iResDoctorProcessBiz.findOneProcessEvals(params);
					evalMap.put(processFlow,evals);
				}
			}
		}
		model.addAttribute("list",list);
		model.addAttribute("evalSizeMap",evalSizeMap);
		model.addAttribute("evalMap",evalMap);
		return "jsres/kzr/docProcessEval/docProcessEvalList";
	}

	public static List<Map<String, String>> getTimes(String startDate,String endDate,String processFlow ) throws ParseException {
		List<Map<String, String>> list = null;
		if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			list = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Calendar c = Calendar.getInstance();
			Date date = sdf.parse(startDate);
			c.setTime(date);
			String startMonth= sdf.format(c.getTime());
			date = sdf.parse(endDate);
			c.setTime(date);
			String endMonth= sdf.format(c.getTime());
			while (startMonth.compareTo(endMonth) <= 0) {
				Map<String, String> newTime = new HashMap<>();
				newTime.put("evlMonth", startMonth);
				newTime.put("processFlow", processFlow);
				//获取开始与结束时间
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c1 = Calendar.getInstance();
				date = sdf.parse(startMonth);
				c1.setTime(date);
				c1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
				String  monthFirstDay = format.format(c1.getTime());
				if(monthFirstDay.compareTo(startDate)<=0)
				{
					monthFirstDay=startDate;
				}
				c1.add(Calendar.MONTH, 1);
				c1.add(Calendar.DATE,-1);
				String endTime=format.format(c1.getTime());
				if(endTime.compareTo(endDate)>=0)
				{
					endTime=endDate;
				}
				newTime.put("startTime", monthFirstDay);
				newTime.put("endTime", endTime);
				list.add(newTime);

				//开始时间加1个自然月
				date = sdf.parse(startMonth);
				c.setTime(date);
				c.add(Calendar.MONTH, 1);
				startMonth=sdf.format(c.getTime());
			}

			Collections.sort(list, new Comparator<Map<String, String>>() {
				@Override
				public int compare(Map<String, String> f1, Map<String, String> f2) {
					String order1 = f1.get("evlMonth");
					String order2 = f2.get("evlMonth");
					if (order1 == null) {
						return -1;
					} else if (order2 == null) {
						return 1;
					} else if (order1 != null && order2 != null) {
						return order1.compareTo(order2);
					}
					return 0;
				}
			});
		}
		return list;
	}

	//出科成绩查询
	@RequestMapping("/doctorRecruitResult")
	public String doctorRecruitResult(Model model,Integer currentPage,HttpServletRequest request,ResDoctor doctor,
									  SysUser user,String[] datas ,String baseFlag){
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
		paramMap.put("deptFlow",currentUser.getDeptFlow());
		paramMap.put("kzrFlow",currentUser.getUserFlow());
		paramMap.put("userName",user.getUserName());
		paramMap.put("trainingTypeId",doctor.getTrainingTypeId());
		paramMap.put("trainingSpeId",doctor.getTrainingSpeId());
		paramMap.put("sessionNumber",doctor.getSessionNumber());
		paramMap.put("docTypeList",docTypeList);
		paramMap.put("graduationYear",doctor.getGraduationYear());
		paramMap.put("baseFlag",baseFlag);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String,Object>> docResultsList = schArrangeResultBiz.searchDocResultsListNew(paramMap);
		model.addAttribute("datas",datas);
		model.addAttribute("docResultsList",docResultsList);
		return "jsres/speAdmin/cycleResults2";
	}


	@RequestMapping("/main")
	public String main(Model model,String speFlow){
		model.addAttribute("speFlow", speFlow);
		//查看基地是否付费 如果基地是非付费，科室不可以填写数据
		JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_baseInfo_maintenance_" + GlobalContext.getCurrentUser().getOrgFlow());
		if (null==cfg || StringUtil.isBlank(cfg.getCfgValue()) || !cfg.getCfgValue().equals("Y")){
			model.addAttribute("viewFlag", GlobalConstant.FLAG_Y);
		}
		return "jsres/speAdmin/speInfo/main";
	}

	/**
	 * 查找信息
	 * @param speFlow
	 * @param baseInfoName
	 * @param editFlag
	 * @param viewFlag
	 * @return
	 */
	@RequestMapping("/findAllBaseInfo")
	public ModelAndView findAllBaseInfo(String speFlow, String baseInfoName, String editFlag, String viewFlag,String ishos,
										String orgFlow,String isJoin,String sessionNumber,String onlyRead){
		if (StringUtil.isNotBlank(ishos) && ishos.equals("Y")){
			onlyRead="Y";	//省厅只能查看
		}
		if (StringUtil.isEmpty(sessionNumber)){
			sessionNumber=DateUtil.getYear();
		}
		Integer year= Integer.parseInt(sessionNumber)-1;

		if (StringUtil.isEmpty(orgFlow)){
			orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
		}
		if (StringUtil.isEmpty(speFlow)){
			speFlow=GlobalContext.getCurrentUser().getResTrainingSpeId();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("viewFlag", viewFlag);
		mav.addObject("isJoin", isJoin);
		mav.addObject("orgFlow", orgFlow);
		mav.addObject("speFlow", speFlow);
		mav.addObject("speName", DictTypeEnum.DoctorTrainingSpe.getDictNameById(speFlow));
		mav.addObject("sessionNumber", sessionNumber);
		mav.addObject("ishos", ishos);

		if (GlobalConstant.GUIDING_PHYSICIAN.equals(baseInfoName)) {	//指导医师情况
			mav.setViewName("jsres/speAdmin/speInfo/"+baseInfoName.substring(0,1).toLowerCase()+baseInfoName.substring(1, baseInfoName.length()));
			return mav;
		}

		boolean isOrg=false;

		//省厅或者客服角色，只能查看
		List<SysUserRole> sysUserRoleList = userRoleBiz.getByUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		if (null!=sysUserRoleList && sysUserRoleList.size()>0){
			for (SysUserRole userRole : sysUserRoleList) {
				if (StringUtil.isNotBlank(userRole.getRoleFlow()) &&(
						userRole.getRoleFlow().equals(InitConfig.getSysCfg("res_global_role_flow")) ||
								userRole.getRoleFlow().equals(InitConfig.getSysCfg("res_maintenance_role_flow")))){
					mav.addObject("isglobal", "Y");
					viewFlag="Y";
				}
				if (StringUtil.isNotBlank(userRole.getRoleFlow()) &&(
						userRole.getRoleFlow().equals(InitConfig.getSysCfg("res_admin_role_flow")))){
					isOrg=true;
				}
			}
		}
		mav.addObject("viewFlag", viewFlag);

		if (GlobalConstant.ROTATING_DEPARTMENTS.equals(baseInfoName)) {	//轮转科室
			SchRotation schRotation = schRotationtBiz.searchDoctorBySpeId(speFlow);
			if (null!=schRotation){ // 有轮转方案的，下面的重写
				if(StringUtils.isNotEmpty(speFlow)) {
					ResSpeBaseStdDeptVO speBaseStdDeptVO = new ResSpeBaseStdDeptVO();
					speBaseStdDeptVO.setSpeBaseId(speFlow);
					List<ResSpeBaseStdDeptVO> resSpeBaseStdDeptVOList = deptManagementBiz.searchSpeBaseStdDept(speBaseStdDeptVO);

					if (CollectionUtils.isNotEmpty(resSpeBaseStdDeptVOList)) {
						List<String> stdDeptFlowList = resSpeBaseStdDeptVOList.stream().map(vo -> vo.getStandardDeptFlow()).collect(Collectors.toList());
						ResStandardDeptVO standardDeptVO = new ResStandardDeptVO();
						standardDeptVO.setStandardDeptFlowList(stdDeptFlowList);
						List<ResStandardDeptVO> resStandardDeptVOList = deptManagementBiz.searchStandardDept(standardDeptVO);
						Map<String, ResStandardDeptVO> stdDeptFlowToEntityMap = resStandardDeptVOList.stream().collect(Collectors.toMap(vo -> vo.getStandardDeptFlow(), vo -> vo));
						resSpeBaseStdDeptVOList.forEach(vo -> {
							ResStandardDeptVO tempVO = stdDeptFlowToEntityMap.get(vo.getStandardDeptFlow());
							if (tempVO != null) {
								vo.setStandardDeptName(tempVO.getStandardDeptName());
								vo.setStandardDeptCode(tempVO.getStandardDeptCode());
							}
						});
					}

					List<ResSpeBaseStdDeptVO> requiredList = resSpeBaseStdDeptVOList.stream().filter(vo -> "1".equals(vo.getRotationRequireStatus())).sorted(Comparator.comparing(ResSpeBaseStdDeptVO::getStandardDeptCode)).collect(Collectors.toList());
					List<ResSpeBaseStdDeptVO> notRequiredList = resSpeBaseStdDeptVOList.stream().filter(vo -> "2".equals(vo.getRotationRequireStatus())).sorted(Comparator.comparing(ResSpeBaseStdDeptVO::getStandardDeptCode)).collect(Collectors.toList());


				/*List<Map<String,String>> requiredList = deptCfgBiz.searchByLastRotationBySpe(orgFlow,speFlow,"Y");	//轮转科室
				List<Map<String,String>> notRequiredList = deptCfgBiz.searchByLastRotationBySpe(orgFlow,speFlow,"N");

				// 根据轮转科室查询标准科室并实现去重
				List<SchAndStandardDeptCfg> standardDeptCfgRequiredList = new ArrayList<>();
				List<SchAndStandardDeptCfg> standardDeptCfgNotRequiredList = new ArrayList<>();
				List<SchAndStandardDeptCfg> standardDeptCfgRequiredList1 = new ArrayList<>();
				List<SchAndStandardDeptCfg> standardDeptCfgNotRequiredList1 = new ArrayList<>();
				for (Map<String, String> map : requiredList) {
					SchAndStandardDeptCfgExample example = new SchAndStandardDeptCfgExample();
					example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow).andSchDeptFlowEqualTo(map.get("schDeptFlow"));
					List<SchAndStandardDeptCfg> standardDeptCfgList = schAndStandardDeptCfgMapper.selectByExample(example);
					standardDeptCfgRequiredList1.add(standardDeptCfgList.get(0));
				}
				for (Map<String, String> map : notRequiredList) {
					SchAndStandardDeptCfgExample example = new SchAndStandardDeptCfgExample();
					example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow).andSchDeptFlowEqualTo(map.get("schDeptFlow"));
					List<SchAndStandardDeptCfg> standardDeptCfgList = schAndStandardDeptCfgMapper.selectByExample(example);
					standardDeptCfgNotRequiredList1.add(standardDeptCfgList.get(0));
				}
				// 去重
				Set<String> set1 = new HashSet<>();
				Set<String> set2 = new HashSet<>();
				for (SchAndStandardDeptCfg deptCfg : standardDeptCfgRequiredList1) {
					if (set1.add(deptCfg.getStandardDeptId())) {
						standardDeptCfgRequiredList.add(deptCfg);
					}
				}
				for (SchAndStandardDeptCfg deptCfg : standardDeptCfgNotRequiredList1) {
					if (set2.add(deptCfg.getStandardDeptId())) {
						standardDeptCfgNotRequiredList.add(deptCfg);
					}
				}
				mav.addObject("requiredList", standardDeptCfgRequiredList);
				mav.addObject("notRequiredList", standardDeptCfgNotRequiredList);*/

					mav.addObject("requiredList", requiredList);
					mav.addObject("notRequiredList", notRequiredList);
				}
			}
			mav.setViewName("jsres/speAdmin/speInfo/"+baseInfoName.substring(0,1).toLowerCase()+baseInfoName.substring(1));
			return mav;
		}


		if (GlobalConstant.DIAG_DISEASE.equals(baseInfoName)
				|| GlobalConstant.EQUIPMENT_INSTRUMENTS.equals(baseInfoName)
				|| GlobalConstant.TRAINING.equals(baseInfoName)) {	//诊疗疾病范围、医疗设备仪器、培训情况
			List<Map<String, Object>> resultList=new ArrayList<>();
			//查询专业的最新轮转方案
			SchRotation schRotation = schRotationtBiz.searchDoctorBySpeId(speFlow);
			if (null!=schRotation){ // 有轮转方案的，下面的重写
				ResSpeBaseStdDeptVO speBaseStdDeptVO = new ResSpeBaseStdDeptVO();
				speBaseStdDeptVO.setSpeBaseId(speFlow);
				speBaseStdDeptVO.setRotationRequireStatus("1");
				List<ResSpeBaseStdDeptVO> resSpeBaseStdDeptVOList = deptManagementBiz.searchSpeBaseStdDept(speBaseStdDeptVO);

				if(CollectionUtils.isNotEmpty(resSpeBaseStdDeptVOList)) {
					List<String> stdDeptFlowList = resSpeBaseStdDeptVOList.stream().map(vo -> vo.getStandardDeptFlow()).collect(Collectors.toList());
					ResStandardDeptVO standardDeptVO = new ResStandardDeptVO();
					standardDeptVO.setStandardDeptFlowList(stdDeptFlowList);
					List<ResStandardDeptVO> resStandardDeptVOList = deptManagementBiz.searchStandardDept(standardDeptVO);
					Map<String, ResStandardDeptVO> stdDeptFlowToEntityMap = resStandardDeptVOList.stream().collect(Collectors.toMap(vo -> vo.getStandardDeptFlow(), vo -> vo));
					resSpeBaseStdDeptVOList.forEach(vo -> {
						ResStandardDeptVO tempVO = stdDeptFlowToEntityMap.get(vo.getStandardDeptFlow());
						if(tempVO != null) {
							vo.setStandardDeptName(tempVO.getStandardDeptName());
							vo.setStandardDeptCode(tempVO.getStandardDeptCode());
						}
					});
				}

				/*//查询方案的必须轮转的科室
				List<Map<String, String>> requiredList = deptCfgBiz.searchByRequired(schRotation.getRotationFlow(), "Y");
				for (Map<String, String> map : requiredList) {
					//查询基地与标准科室关联的 轮转科室
					List<SchAndStandardDeptCfg> cfgList = deptCfgBiz.selectByStandardDeptId(orgFlow, map.get("standardDeptId"));
					if(CollectionUtils.isNotEmpty(cfgList)) {
						HashMap<String, Object> hashMap = new HashMap<>();	//存放每一个科室的信息
						hashMap.put("standardDeptId",map.get("standardDeptId"));
						hashMap.put("standardDeptName",map.get("standardDeptName"));
						resultList.add(hashMap);
					}
				}*/
				mav.addObject("resultList", resSpeBaseStdDeptVOList);
			}
			mav.setViewName("jsres/speAdmin/speInfo/"+baseInfoName.substring(0,1).toLowerCase()+baseInfoName.substring(1, baseInfoName.length()) + "2");
			return mav;
		}

		ResBaseSpeDept baseSpeDept = deptBasicInfoBiz.readByOrgAndSpe(orgFlow, speFlow,sessionNumber);
		mav.addObject("baseSpeDept", baseSpeDept);
		mav.addObject("speFlow", speFlow);

		boolean isPay=false;	//基地是否付费
		boolean dataIsNull=false;
		JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_baseInfo_maintenance_" + orgFlow);
		//基地是付费用户，科主任可以填写信息，如果不是就不可以填写，只能由基地填写
		if (null != cfg && StringUtil.isNotBlank(cfg.getCfgValue()) && cfg.getCfgValue().equals("Y")) {
			isPay=true;	//是付费用户
			if (StringUtil.isEmpty(onlyRead) || !onlyRead.equals("Y")){		//基地查看
				viewFlag=GlobalConstant.FLAG_N;
			}
		}else {
//			viewFlag=GlobalConstant.FLAG_Y;
//			if (isOrg){ // 管理员编辑
//				viewFlag=GlobalConstant.FLAG_N;
//			}
		}

		if (GlobalConstant.DEPT_BASIC_INFO.equals(baseInfoName) &&!GlobalConstant.FLAG_Y.equals(viewFlag) && isPay) {    //基本信息
			prompt(orgFlow,speFlow,sessionNumber,mav);	//各科室基本条件的数据汇总
			if (null==baseSpeDept && !GlobalConstant.FLAG_Y.equals(viewFlag)){	//如果是编辑并且是付费用户，当前年的数据未填写，将前一年的数据显示出来
				baseSpeDept = deptBasicInfoBiz.readByOrgAndSpe(orgFlow, speFlow,year.toString());
				dataIsNull=true;
				if (StringUtil.isEmpty(onlyRead) || !onlyRead.equals("Y")){		//基地查看
					editFlag=GlobalConstant.FLAG_Y;
				}
			}
		}
		mav.addObject("dataIsNull", dataIsNull);
		if (baseSpeDept != null) {
			String Xml=baseSpeDept.getBaseInfo();
			if (StringUtil.isNotBlank(Xml)) {
				BaseSpeDeptExtForm speDeptExtForm= JaxbUtil.converyToJavaBean(Xml, BaseSpeDeptExtForm.class);
				if (GlobalConstant.DEPT_BASIC_INFO.equals(baseInfoName)) {	//基本信息
					//如果查询年份的数据为空，就显示去年的数据（方便用户填写）
					if (null==speDeptExtForm.getDeptBasicInfoForm() && dataIsNull==false && isPay){		//如果查询年份的数据为空，就显示前一年的数据
						baseSpeDept = deptBasicInfoBiz.readByOrgAndSpe(orgFlow, speFlow,year.toString());
						if (null!=baseSpeDept && StringUtil.isNotBlank(baseSpeDept.getBaseInfo())){
							mav.addObject("deptBasicInfoForm",JaxbUtil.converyToJavaBean(baseSpeDept.getBaseInfo(), BaseSpeDeptExtForm.class).getDeptBasicInfoForm());
						}
						editFlag=GlobalConstant.FLAG_Y;
					}else {
						mav.addObject("deptBasicInfoForm", speDeptExtForm.getDeptBasicInfoForm());
					}
				}else if (GlobalConstant.DEPARTMENT_HEAD.equals(baseInfoName)) {	//负责人信息
					//如果查询年份的数据为空，就显示去年的数据（方便用户填写）
					if (null==speDeptExtForm.getDepartmentHeadForm() && dataIsNull==false && isPay){		//如果查询年份的数据为空，就显示前一年的数据
						baseSpeDept = deptBasicInfoBiz.readByOrgAndSpe(orgFlow, speFlow,year.toString());
						if (null!=baseSpeDept && StringUtil.isNotBlank(baseSpeDept.getBaseInfo())){
							mav.addObject("deptBasicInfoForm",JaxbUtil.converyToJavaBean(baseSpeDept.getBaseInfo(), BaseSpeDeptExtForm.class).getDepartmentHeadForm());
						}
						editFlag=GlobalConstant.FLAG_Y;
					}else {
						mav.addObject("departmentHeadForm", speDeptExtForm.getDepartmentHeadForm());
					}
				}
			}
			if((GlobalConstant.FLAG_Y.equals(editFlag))&&!GlobalConstant.FLAG_Y.equals(viewFlag)) {
				mav.setViewName("jsres/speAdmin/speInfo/edit"+baseInfoName);
			}else{
				mav.addObject("baseInfoName", baseInfoName);
				mav.setViewName("jsres/speAdmin/speInfo/"+baseInfoName.substring(0,1).toLowerCase()+baseInfoName.substring(1, baseInfoName.length()));
			}
		}else{//无记录
			if(GlobalConstant.FLAG_Y.equals(editFlag)) {
				mav.setViewName("jsres/speAdmin/speInfo/edit"+baseInfoName);
			}else if(GlobalConstant.FLAG_Y.equals(viewFlag)){
				mav.setViewName("jsres/speAdmin/speInfo/"+baseInfoName.substring(0,1).toLowerCase()+baseInfoName.substring(1, baseInfoName.length()));
			}else{
				mav.setViewName("jsres/speAdmin/speInfo/edit"+baseInfoName);
			}
		}
		return mav;
	}

	private void prompt(String orgFlow , String speFlow ,String  sessionNumber, ModelAndView mav){
		SchRotation schRotation = schRotationtBiz.searchDoctorBySpeId(speFlow);
		if (null!=schRotation){
			//查询方案的必须轮转的科室
			List<Map<String, String>> requiredList = deptCfgBiz.searchByRequired(schRotation.getRotationFlow(), "Y");
			Integer bzzcws=0;
			Integer syzcws=0;
			Integer nszzybrs=0;
			Integer bcsyl=0;
			Integer nmzl=0;
			Integer njzl=0;
			Integer bczzcs=0;
			Integer pjzyr=0;
			Integer ncybrs=0;
			Integer njzscls=0;
			Integer npxzrl=0;
			Integer sypxrl=0;
			for (Map<String, String> map : requiredList) {
				//查询基地与标准科室关联的 轮转科室
				List<SchAndStandardDeptCfg> cfgList = deptCfgBiz.selectByStandardDeptId(orgFlow, map.get("standardDeptId"));
				for (SchAndStandardDeptCfg cfg : cfgList) {
					ResBaseSpeDept info = deptBasicInfoBiz.readByOrgAndDept(orgFlow, cfg.getSchDeptFlow(),sessionNumber);
					if (null!=info && StringUtil.isNotBlank(info.getBaseInfo())){
						DeptBasicInfoForm basicInfoForm = JaxbUtil.converyToJavaBean(info.getBaseInfo(), BaseSpeDeptExtForm.class).getDeptBasicInfoForm();
						if (null==basicInfoForm){
							continue;
						}
						if (StringUtil.isNotBlank(basicInfoForm.getBzzcws())){
							bzzcws+=Integer.parseInt(basicInfoForm.getBzzcws());
						}
						if (StringUtil.isNotBlank(basicInfoForm.getSyzcws())){
							syzcws+=Integer.parseInt(basicInfoForm.getSyzcws());
						}
						if (StringUtil.isNotBlank(basicInfoForm.getNszzybrs())){
							nszzybrs+=Integer.parseInt(basicInfoForm.getNszzybrs());
						}
						if (StringUtil.isNotBlank(basicInfoForm.getBcsyl())){
							bcsyl+=Integer.parseInt(basicInfoForm.getBcsyl());
						}
						if (StringUtil.isNotBlank(basicInfoForm.getNmzl())){
							nmzl+=Integer.parseInt(basicInfoForm.getNmzl());
						}
						if (StringUtil.isNotBlank(basicInfoForm.getNjzl())){
							njzl+=Integer.parseInt(basicInfoForm.getNjzl());
						}
						if (StringUtil.isNotBlank(basicInfoForm.getBczzcs())){
							bczzcs+=Integer.parseInt(basicInfoForm.getBczzcs());
						}
						if (StringUtil.isNotBlank(basicInfoForm.getPjzyr())){
							pjzyr+=Integer.parseInt(basicInfoForm.getPjzyr());
						}
						if (StringUtil.isNotBlank(basicInfoForm.getNcybrs())){
							ncybrs+=Integer.parseInt(basicInfoForm.getNcybrs());
						}
						if (StringUtil.isNotBlank(basicInfoForm.getNjzscls())){
							njzscls+=Integer.parseInt(basicInfoForm.getNjzscls());
						}
						if (StringUtil.isNotBlank(basicInfoForm.getNpxzrl())){
							npxzrl+=Integer.parseInt(basicInfoForm.getNpxzrl());
						}
						if (StringUtil.isNotBlank(basicInfoForm.getSypxrl())){
							sypxrl+=Integer.parseInt(basicInfoForm.getSypxrl());
						}
					}
				}
			}
			HashMap<String, Integer> promptMap = new HashMap<>();
			promptMap.put("bzzcws",bzzcws);
			promptMap.put("syzcws",syzcws);
			promptMap.put("nszzybrs",nszzybrs);
			promptMap.put("bcsyl",bcsyl);
			promptMap.put("nmzl",nmzl);
			promptMap.put("njzl",njzl);
			promptMap.put("bczzcs",bczzcs);
			promptMap.put("pjzyr",pjzyr);
			promptMap.put("ncybrs",ncybrs);
			promptMap.put("njzscls",njzscls);
			promptMap.put("npxzrl",npxzrl);
			promptMap.put("sypxrl",sypxrl);
			mav.addObject("promptMap", promptMap);
		}
	}


	@RequestMapping("/saveBase")
	@ResponseBody
	public String saveBase(String flag, BaseSpeDeptForm baseSpeDeptForm, String index, String type,String sessionNumber,
						   String fileFlows[],String orgFlow, HttpServletRequest request) throws Exception{
		int  result =  deptBasicInfoBiz.saveBaseInfo(flag, baseSpeDeptForm, index,type,fileFlows,request,"spe");
		if(GlobalConstant.ZERO_LINE !=  result){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping("/guidingPhysicianList")
	public String guidingPhysicianList(String speFlow, Integer currentPage ,HttpServletRequest request,Model model,String orgFlow,String isJoin){
		model.addAttribute("isJoin",isJoin);
		if (StringUtil.isEmpty(orgFlow)){
			orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
		}
//		PageHelper.startPage(currentPage,getPageSize(request));
		List<ResTeacherTraining> list = deptBasicInfoBiz.searchByOrgAndSpe(orgFlow, speFlow);
		for (ResTeacherTraining teacherTraining : list) {
			int year = Integer.parseInt(DateUtil.getYear());
			int moYear = Integer.parseInt(teacherTraining.getModifyTime().substring(0, 4));
			int num=year- moYear;
			if (StringUtil.isNotBlank(teacherTraining.getDoctorAge())){
				teacherTraining.setDoctorAge(String.valueOf(Integer.parseInt(teacherTraining.getDoctorAge())+num));
			}
			if (StringUtil.isNotBlank(teacherTraining.getOfficeYear())){
				teacherTraining.setOfficeYear(String.valueOf(Integer.parseInt(teacherTraining.getOfficeYear())+num));
			}
			if (StringUtil.isNotBlank(teacherTraining.getWorkYear())){
				teacherTraining.setWorkYear(String.valueOf(Integer.parseInt(teacherTraining.getWorkYear())+num));
			}
			if (StringUtil.isNotBlank(teacherTraining.getInternYear())){
				teacherTraining.setInternYear(String.valueOf(Integer.parseInt(teacherTraining.getInternYear())+num));
			}
			if (StringUtil.isNotBlank(teacherTraining.getHosYear())){
				teacherTraining.setHosYear(String.valueOf(Integer.parseInt(teacherTraining.getHosYear())+num));
			}
		}
		model.addAttribute("list", list);
		return "jsres/speAdmin/speInfo/guidingPhysicianList";
	}

}

