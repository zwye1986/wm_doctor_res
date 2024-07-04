package com.pinde.res.ctrl.gzzl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.res.biz.hbres.*;
import com.pinde.res.biz.jswjw.IJswjwBiz;
import com.pinde.res.biz.jswjw.IJswjwTeacherBiz;
import com.pinde.res.biz.jswjw.IResDoctorProcessBiz;
import com.pinde.res.biz.stdp.*;
import com.pinde.res.enums.hbres.ResAssessScoreTypeEnum;
import com.pinde.res.enums.stdp.RegistryTypeEnum;
import com.pinde.res.enums.stdp.ResAssessTypeEnum;
import com.pinde.res.enums.stdp.ResRecTypeEnum;
import com.pinde.res.model.jswjw.mo.FromTitle;
import com.pinde.res.model.jswjw.mo.ResAssessCfgItemForm;
import com.pinde.res.model.jswjw.mo.ResAssessCfgTitleForm;
import com.pinde.sci.model.mo.*;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/gzzl/kzr")
public class GzzlKzrAppController {
	private static Logger logger = LoggerFactory.getLogger(GzzlKzrAppController.class);
	
	
	@ExceptionHandler(Throwable.class)
	public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/gzzl/500";
	}

	@Autowired
	private IJswjwBiz jswjwBiz;
	@Autowired
	private IHbresAppBiz hbresBiz;
	@Autowired
	private IResActivityTargetBiz activityTargeBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IInxInfoBiz inxInfoBiz;
	@Autowired
	private IResActivityBiz activityBiz;
	@Autowired
	private IHbresStudentBiz studentBiz;
	@Autowired
	private IHbresTeacherBiz teacherBiz;
	@Autowired
	private IResGradeBiz gradeBiz;
	@Autowired
	private IJswjwTeacherBiz jswjwTeacherBiz;
	@Autowired
	private IHbresKzrBiz hbresKzrBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private IResDoctorProcessBiz iResDoctorProcessBiz;
	@Autowired
	private IResInprocessInfoBiz resInprocessInfoBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	@Autowired
	private IResPowerCfgBiz resPowerCfgBiz;

	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/gzzl/kzr/test";
	}
	@RequestMapping(value={"/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String deptFlow,String cataFlow,String funcTypeId , String funcFlow , String dataFlow , HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("userFlow", userFlow);
		session.setAttribute("deptFlow", deptFlow);
		session.setAttribute("cataFlow", cataFlow);
		session.setAttribute("dataFlow", dataFlow);
		session.setAttribute("funcTypeId", funcTypeId);
		session.setAttribute("funcFlow", funcFlow);
		return "/res/gzzl/kzr/test";
	}
	@RequestMapping(value={"/index"})
	public String index(String userFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/index";
		}
		//验证用户是否存在
		SysUser userinfo = hbresBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/index";
		}
		model.addAttribute("userInfo",userinfo);
		//培训学员总数
		String isOpen = hbresBiz.getCfgByCode("res_permit_open_doc");
		int conut=hbresKzrBiz.schProcessStudentDistinctQuery(userinfo.getDeptFlow(),userFlow,isOpen);
		model.addAttribute("count",conut);
		//所属基地是否开通过程
		String isChargeOrg=jswjwBiz.getJsResCfgCode("jsres_"+userinfo.getOrgFlow()+"_P001");
		model.addAttribute("isChargeOrg",isChargeOrg);
		return "res/gzzl/kzr/index";
	}
	/**
	 * 学员列表
	 * @param userFlow
	 * @param roleId
	 * @param studentName
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/studentList"},method = {RequestMethod.POST})
	public String studentList(String userFlow,String roleId,String seachStr,
							  String studentName, String teaName, String speName,String sessionNumber,String statusId,
							  String typeId,String yearMonth,
							  Integer pageIndex,Integer pageSize,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/studentList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/gzzl/kzr/studentList";
		}
		if(!roleId.equals("Head")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/gzzl/kzr/studentList";
		}
		if("waitSch".equals(typeId)&&StringUtil.isBlank(yearMonth))
		{
			yearMonth=DateUtil.addMonth(DateUtil.getMonth(),1);
		}
		//验证用户是否存在
		SysUser userinfo = hbresBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/studentList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzzl/kzr/studentList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzzl/kzr/studentList";
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("deptFlow", userinfo.getDeptFlow());
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		schArrangeResultMap.put("typeId", typeId);
		schArrangeResultMap.put("yearMonth", yearMonth);
		String isOpen = hbresBiz.getCfgByCode("res_permit_open_doc");
		schArrangeResultMap.put("isOpen", isOpen);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=hbresKzrBiz.schDoctorSchProcessHead(schArrangeResultMap);

		Map<String,Object> resRecMap=new HashMap<String,Object>();
		Map<String,Integer> resRecCountMap=new HashMap<String,Integer>();
		for (Map<String, String> map : resDoctorSchProcess) {
			SchArrangeResult schArrangeResult=studentBiz.searcheDocResult(null,map.get("resultFlow"));
			if (schArrangeResult!=null) {
				//返回出科异常异常原因
				ResDoctorSchProcess process = hbresKzrBiz.searchByResultFlow(schArrangeResult.getResultFlow());
				if(process!=null) {
					String time = DateUtil.getCurrDate();
					if (!GlobalConstant.FLAG_Y.equals(process.getSchFlag()) && StringUtil.isNotBlank(process.getSchEndDate()) && compare_date(time, process.getSchEndDate()) > 0) {
						ResSchProcessExpress rec = expressBiz.queryResRec(process.getProcessFlow(),  ResRecTypeEnum.AfterSummary.getId());
						if (null == rec) {
							map.put("abnormalCause", "学生未提交出科小结");
						} else {
							String teaAudit = rec.getAuditStatusId();
							String headAudit = rec.getHeadAuditStatusId();
							if (StringUtil.isEmpty(teaAudit)) {
								map.put("abnormalCause", "带教老师未审核出科小结");
							} else if (StringUtil.isEmpty(headAudit)) {
								map.put("abnormalCause", "科主任未审核出科小结");
							}
						}
					}
				}
			}

		}
		model.addAttribute("resRecMap", resRecMap);
		model.addAttribute("resRecCountMap", resRecCountMap);
		model.addAttribute("list",resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzzl/kzr/studentList";
	}
	private static int compare_date(String DATE1, String DATE2) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				//System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				//System.out.println("dt1在dt2后");
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
	 * 科室列表
	 * @param userFlow
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/deptList"},method={RequestMethod.POST})
	public String deptList(String userFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/deptList";
		}
		SysUser user= hbresBiz.readSysUser(userFlow);
		List<SysDept> depts=jswjwBiz.getHeadDeptList(userFlow,user.getDeptFlow());
		Map<String, ResInprocessInfo> inprocessInfoMap = new HashMap<String, ResInprocessInfo>();
		for(SysDept result:depts) {
			ResInprocessInfo info = resInprocessInfoBiz.readByDeptFlowAndOrgFlow(result.getDeptFlow(), result.getOrgFlow());
			inprocessInfoMap.put(result.getDeptFlow(), info);
		}
		model.addAttribute("depts", depts);
		model.addAttribute("inprocessInfoMap", inprocessInfoMap);
		return "res/gzzl/kzr/deptList";
	}

	@RequestMapping(value={"/inProcessInfo"},method={RequestMethod.POST})
	public String inProcessInfo(String userFlow,String deptFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3020201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/inProcessInfo";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/gzzl/kzr/inProcessInfo";
		}
		SysDept dept=jswjwBiz.readSysDept(deptFlow);
		if(dept==null)
		{
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "科室信息不存在");
			return "res/gzzl/kzr/inProcessInfo";
		}
		String orgFlow = dept.getOrgFlow();
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

				Map<Integer, String> week = new HashMap<>();
				week.put(1, "周一");
				week.put(2, "周二");
				week.put(3, "周三");
				week.put(4, "周四");
				week.put(5, "周五");
				week.put(6, "周六");
				week.put(7, "周日");
				List<Map<String, String>> days = new ArrayList<>();
				for (int i = 1; i <= 7; i++) {
					String addressV = (String) arrangeMap.get("address" + i);
					String contentV = (String) arrangeMap.get("content" + i);
					if (StringUtil.isNotBlank(addressV) || StringUtil.isNotBlank(contentV)) {
						Map<String, String> m = new HashMap<>();
						m.put("address", addressV);
						m.put("content", contentV);
						m.put("dayName", week.get(i));
						days.add(m);
					}
				}
				model.addAttribute("days", days);
			}
		}else{
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "科室暂未添加入科教育信息");
			return "res/gzzl/kzr/inProcessInfo";
		}
		return "res/gzzl/kzr/inProcessInfo";
	}
	@RequestMapping(value={"/funcList"},method={RequestMethod.POST})
	public String funcList(String userFlow,String doctorFlow,String processFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3020201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/funcList";
		}
		if(StringUtil.isEmpty(processFlow)){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/gzzl/kzr/funcList";
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/gzzl/kzr/funcList";
		}
		ResDoctorSchProcess process=iResDoctorProcessBiz.read(processFlow);
		if(process==null){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "轮转信息不存在");
			return "res/gzzl/kzr/funcList";
		}
		ResDoctor resDoctor = hbresBiz.readResDoctor(process.getUserFlow());
		model.addAttribute("process",process);
		model.addAttribute("resDoctor",resDoctor);
		Map<String,Object> resRecMap=new HashMap<String,Object>();
		List<String> recTypeIds=new ArrayList<>();
		recTypeIds.add(ResRecTypeEnum.DOPS.getId());//4种出科分类
		recTypeIds.add(ResRecTypeEnum.Mini_CEX.getId());
		recTypeIds.add(ResRecTypeEnum.AfterEvaluation.getId());
		recTypeIds.add(ResRecTypeEnum.AfterSummary.getId());
		recTypeIds.add(ResRecTypeEnum.CaseRegistry.getId());//5种登记数据
		recTypeIds.add(ResRecTypeEnum.DiseaseRegistry.getId());
		recTypeIds.add(ResRecTypeEnum.SkillRegistry.getId());
		recTypeIds.add(ResRecTypeEnum.OperationRegistry.getId());
		recTypeIds.add(ResRecTypeEnum.CampaignRegistry.getId());
		List<ResSchProcessExpress> expressList = expressBiz.getDocexpressList(processFlow, recTypeIds);
		for(ResSchProcessExpress express:expressList){
			String key =express.getProcessFlow()+express.getRecTypeId();
			resRecMap.put(key, express);
		}
		model.addAttribute("resRecMap", resRecMap);
		return "res/gzzl/kzr/funcList";
	}

	/**
	 * o数据列表
	 * @param
	 * @param doctorFlow
	 * @param processFlow
	 * @param recType
	 * @return
	 */
	@RequestMapping(value="/resRecList",method=RequestMethod.GET)
	public String resRecList(String userFlow,String doctorFlow,String processFlow,String recType,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/resRecList";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/gzzl/kzr/resRecList";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/gzzl/kzr/resRecList";
		}
		//rec类型转换一下
		String recTypeId=getRecTypeId(recType);
		model.addAttribute("recTypeId",recTypeId);
		if(StringUtil.isBlank(recTypeId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型为空");
			return "res/gzzl/kzr/resRecList";
		}
		Map<String, Object> deptPerMap = studentBiz.getRegPer(0,doctorFlow);
		model.addAttribute("processPerMap", deptPerMap);

		//验证用户是否存在
		SysUser userinfo = hbresBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/resRecList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzzl/kzr/resRecList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzzl/kzr/resRecList";
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<ResRec> recList=jswjwTeacherBiz.searchRecByProcessAndRecType(processFlow,doctorFlow,recTypeId,"");
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		for(ResRec rec : recList){
			String recContent = rec.getRecContent();
			Map<String, String> formDataMap = parseRecContent(recContent);
			formDataMap.put("dataFlow", rec.getRecFlow());
			formDataMap.put("auditId", rec.getAuditStatusId());
			formDataMap.put("operTime", DateUtil.transDate(rec.getOperTime()));
			dataList.add(formDataMap);
		}
		model.addAttribute("dataList", dataList);
		model.addAttribute("dataCount", PageHelper.total);
		List<ResRec> noAuditList=jswjwTeacherBiz.searchRecByProcessAndRecType(processFlow,doctorFlow,recTypeId,"Y");
		int count=0;
		if(noAuditList!=null){
			count=noAuditList.size();
		}
		model.addAttribute("notAuditNum",count);
		return "res/gzzl/kzr/resRecList";
	}
	/**
	 * 单条数据详情
	 * @param
	 * @param recType
	 * @return
	 */
	@RequestMapping(value="/resRecDeatil",method=RequestMethod.GET)
	public String resRecDeatil(String userFlow,String recFlow,String deptFlow,String cataFlow,String recType,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/resRecDetail";
		}
		if(StringUtil.isBlank(recFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据标识符为空");
			return "res/gzzl/kzr/resRecDetail";
		}
		if(StringUtil.isBlank(recType)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型为空");
			return "res/gzzl/kzr/resRecDetail";
		}
		//验证用户是否存在
		SysUser userinfo = hbresBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/resRecDetail";
		}
		ResRec rec = jswjwBiz.readResRec(recFlow);
		String recContent = rec.getRecContent();
		Map<String, String> formDataMap = parseRecContent(recContent);
		formDataMap.put("auditId",rec.getAuditStatusId());
		model.addAttribute("resultData", formDataMap);
		model.addAttribute("isOther", GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(cataFlow));
		return "res/gzzl/kzr/resRecDetail";
	}
	private String getRecTypeId(String recType) {
		String recTypeId = "";
		switch (recType) {
			case "mr":
				recTypeId = RegistryTypeEnum.CaseRegistry.getId();
				break;
			case "disease":
				recTypeId = RegistryTypeEnum.DiseaseRegistry.getId();
				break;
			case "skill":
				recTypeId = RegistryTypeEnum.SkillRegistry.getId();
				break;
			case "operation":
				recTypeId = RegistryTypeEnum.OperationRegistry.getId();
				break;
			case "activity":
				recTypeId = RegistryTypeEnum.CampaignNoItemRegistry.getId();
				break;
			case "summary":
				recTypeId = ResRecTypeEnum.AfterSummary.getId();
				break;
			case "dops":
				recTypeId = ResRecTypeEnum.DOPS.getId();
				break;
			case "miniCex":
				recTypeId = ResRecTypeEnum.Mini_CEX.getId();
				break;
			case "after":
				recTypeId = ResRecTypeEnum.AfterEvaluation.getId();
				break;
			default:
				break;
		}
		return recTypeId;
	}
	public Map<String,String> parseRecContent(String content) {
		Map<String,String> formDataMap = null;
		if(StringUtil.isNotBlank(content)){
			formDataMap = new HashMap<String, String>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				List<Element> elements = rootElement.elements();;
				for(Element element : elements){
					List<Node> valueNodes = element.selectNodes("value");
					if(valueNodes != null && !valueNodes.isEmpty()){
						String value = "";
						for(Node node : valueNodes){
							if(StringUtil.isNotBlank(value)){
								value+=",";
							}
							value += node.getText();
						}
						formDataMap.put(element.getName(), value);
					}else {
						if(StringUtil.isNotBlank(element.attributeValue("id"))){
							formDataMap.put(element.getName()+"_id", element.attributeValue("id"));
						}
						formDataMap.put(element.getName(), element.getText());
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return formDataMap;
	}
	/**
	 * 查看出科考核上传情况
	 * @param userFlow
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/viewImage"},method={RequestMethod.GET})
	public String viewImage(String userFlow,String doctorFlow,String recordFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31501");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/viewImage";
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "31502");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/gzzl/kzr/viewImage";
		}
		if(StringUtil.isEmpty(recordFlow)){
			model.addAttribute("resultId", "31502");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/gzzl/kzr/viewImage";
		}
		List<Map<String,String>>  dataList =  jswjwBiz.viewImage(doctorFlow,recordFlow);
		model.addAttribute("dataList", dataList);
		return "res/gzzl/kzr/viewImage";
	}
	private Map<String,Object> paressXml(String content) {
		Map<String,Object> formDataMap = null;
		if(StringUtil.isNotBlank(content)){
			formDataMap = new HashMap<String, Object>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				List<Element> elements = rootElement.elements();
				for(Element element : elements){
					formDataMap.put(element.getName(), element.getText());
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return formDataMap;
	}

	final static String JXCF = "1";
	final static String YN = "2";
	final static String WZBLTL = "3";
	final static String XSJZ = "4";
	final static String SWBLTL = "5";
	/**
	 * 出科考核表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/afterEvaluation")
	public String evaluationSun(String userFlow,
								String doctorFlow,
								String processFlow,
								String recFlow,
								Model model) throws Exception{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/evaluationSun";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/gzzl/kzr/evaluationSun";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "过程标识符为空");
			return "res/gzzl/kzr/evaluationSun";
		}
		String recTypeId=ResRecTypeEnum.AfterEvaluation.getId();
		ResDoctor doctor=null;
		SysUser operUser=null;
		SysUser currUser=hbresBiz.readSysUser(userFlow);
		model.addAttribute("currUser",currUser);
		if(StringUtil.isNotBlank(doctorFlow)){
			doctor  = hbresBiz.readResDoctor(doctorFlow);
			model.addAttribute("doctor", doctor);
			operUser  = hbresBiz.readSysUser(doctorFlow);
			model.addAttribute("operUser",operUser);
		}
		ResSchProcessExpress rec=expressBiz.getExpressByRecFlow(recFlow);
		if(rec==null)
			rec=expressBiz.getExpressByRecType(processFlow,recTypeId);
		Map<String,Object> formDataMap = null;
		if(rec!=null){
			String recContent = rec.getRecContent();
			formDataMap = jswjwTeacherBiz.parseRecContent(recContent);
			model.addAttribute("formDataMap", formDataMap);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();


		Map<String, Object> processPerMap = studentBiz.getRegPer(0,userFlow);
		boolean ckk=false;
		String regScore=hbresBiz.getCfgByCode("res_doc_reg_score");
		if(GlobalConstant.FLAG_Y.equals(regScore))
		{
			String switchFlag=hbresBiz.getCfgByCode("res_after_test_switch");
			String urlCfg=hbresBiz.getCfgByCode("res_mobile_after_url_cfg");
			//学员开通出科考试权限
			Map<String,String> paramMap = new HashMap();
			String cfgCode = "res_doctor_ckks_" + userFlow;
			paramMap.put("cfgCode",cfgCode);
			String isCkksFlag = resPowerCfgBiz.getResPowerCfg(paramMap);
			if(GlobalConstant.FLAG_Y.equals(switchFlag) && GlobalConstant.FLAG_Y.equals(isCkksFlag)
					&& StringUtil.isNotBlank(urlCfg))
			{
				ckk=true;
			}

		}
		if(ckk) {
			ResScore outScore = hbresBiz.readScoreByProcessFlow(processFlow);
			model.addAttribute("outScore", outScore);
		}
		model.addAttribute("f",ckk);
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
			List<ResRec> recs= jswjwTeacherBiz.searchRecByProcessWithBLOBs(recTypes,processFlow,operUser.getUserFlow());
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
			dataMap.put("userName",operUser.getUserName());
			dataMap.put("sessionNumber",doctor.getSessionNumber());
			dataMap.put("trainingSpeName",doctor.getTrainingSpeName());

			dataMap.put("caseRegistry",StringUtil.defaultIfEmpty(caseRegistry, "0"));
			dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(caseRegistryReqNum, "0"));
			dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(caseRegistryFinished, "0"));

			if (dataMap.get("caseRegistryReqNum")==null||"0".equals(dataMap.get("caseRegistryReqNum"))) {
				dataMap.put("caseRegistryReqNum","0");
				dataMap.put("caseRegistry","100");
			}

			dataMap.put("diseaseRegistry",StringUtil.defaultIfEmpty(diseaseRegistry, "0"));
			dataMap.put("diseaseRegistryReqNum",StringUtil.defaultIfEmpty(diseaseRegistryReqNum, "0"));
			dataMap.put("diseaseRegistryFinished", StringUtil.defaultIfEmpty(diseaseRegistryFinished, "0"));

			if (dataMap.get("diseaseRegistryReqNum")==null||"0".equals(dataMap.get("diseaseRegistryReqNum"))) {
				dataMap.put("diseaseRegistryReqNum","0");
				dataMap.put("diseaseRegistry","100");
			}

			dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(skillRegistry, "0"));
			dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(skillRegistryReqNum, "0"));
			dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(skillRegistryFinished, "0"));

			if (dataMap.get("skillRegistryReqNum")==null||"0".equals(dataMap.get("skillRegistryReqNum"))) {
				dataMap.put("skillRegistryReqNum","0");
				dataMap.put("skillRegistry","100");
			}

			dataMap.put("operationRegistry", StringUtil.defaultIfEmpty(skillAndOperationRegistry, "0"));
			dataMap.put("operationRegistryReqNum", StringUtil.defaultIfEmpty(skillAndOperationRegistryReqNum, "0"));
			dataMap.put("operationRegistryFinished", StringUtil.defaultIfEmpty(skillAndOperationRegistryFinished, "0"));

			if (dataMap.get("operationRegistryReqNum")==null||"0".equals(dataMap.get("operationRegistryReqNum"))) {
				dataMap.put("operationRegistryReqNum","0");
				dataMap.put("operationRegistry","100");
			}

			dataMap.put("teachingRounds",String.valueOf(teachingRounds));
			dataMap.put("difficult",String.valueOf(difficult));
			dataMap.put("lecture",String.valueOf(lecture));
			dataMap.put("death",String.valueOf(death));
			ResDoctorSchProcess resDoctorSchProcess=iResDoctorProcessBiz.read(processFlow);
			model.addAttribute("resDoctorSchProcess", resDoctorSchProcess);
			model.addAttribute("dataMap", dataMap);
			model.addAttribute("rec", rec);
			model.addAttribute("processFlow",processFlow);
			model.addAttribute("formFileName",recTypeId);
			model.addAttribute("roleFlag", "teacher");
		}
		return "res/gzzl/kzr/evaluationSun";
	}
	/**
	 * 迷你临床演练评估量化表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mini_cex")
	public String mini_cex(String userFlow,
						   String doctorFlow,
						   String processFlow,
						   String recFlow,
						   Model model) throws Exception{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/mini_cex";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/gzzl/kzr/mini_cex";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "过程标识符为空");
			return "res/gzzl/kzr/mini_cex";
		}
		SysUser currUser=hbresBiz.readSysUser(userFlow);
		model.addAttribute("currUser",currUser);
		ResSchProcessExpress rec=expressBiz.getExpressByRecFlow(recFlow);
		String recTypeId=ResRecTypeEnum.Mini_CEX.getId();
		if(rec==null)
			rec=expressBiz.getExpressByRecType(processFlow,recTypeId);
		Map<String,Object> formDataMap = null;
		if(rec!=null){
			String recContent = rec.getRecContent();
			formDataMap = jswjwTeacherBiz.parseRecContent(recContent);
			model.addAttribute("formDataMap", formDataMap);
		}
		ResDoctor doctor=hbresBiz.readResDoctor(doctorFlow);

		model.addAttribute("doctor", doctor);
		model.addAttribute("formFileName", recTypeId);
		model.addAttribute("rec", rec);
		model.addAttribute("roleFlag", "teacher");
		return "res/gzzl/kzr/mini_cex";
	}
	/**
	 * 临床操作技能评估量化表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/DOPS")
	public String DOPS(String userFlow,
					   String doctorFlow,
					   String processFlow,
					   String recFlow,
					   Model model) throws Exception{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/DOPS";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/gzzl/kzr/DOPS";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "过程标识符为空");
			return "res/gzzl/kzr/DOPS";
		}
		SysUser currUser=hbresBiz.readSysUser(userFlow);
		model.addAttribute("currUser",currUser);
		ResSchProcessExpress rec=expressBiz.getExpressByRecFlow(recFlow);
		String recTypeId=ResRecTypeEnum.DOPS.getId();
		if(rec==null)
			rec=expressBiz.getExpressByRecType(processFlow,recTypeId);
		Map<String,Object> formDataMap = null;
		if(rec!=null){
			String recContent = rec.getRecContent();
			formDataMap = jswjwTeacherBiz.parseRecContent(recContent);
			model.addAttribute("formDataMap", formDataMap);
		}
		ResDoctor doctor=hbresBiz.readResDoctor(doctorFlow);

		model.addAttribute("doctor", doctor);
		model.addAttribute("formFileName", recTypeId);
		model.addAttribute("rec", rec);
		model.addAttribute("roleFlag", "teacher");
		return "res/gzzl/kzr/DOPS";
	}

	/**
	 * 带教评分list
	 * @return
	 */
	@RequestMapping(value="/teacherGradeList",method=RequestMethod.POST)
	public String teacherGradeList(String userFlow,String teaName,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/teacherGradeList";
		}
		SysUser userinfo = hbresBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/teacherGradeList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzzl/kzr/teacherGradeList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzzl/kzr/teacherGradeList";
		}
		String cfgTeacher=hbresBiz.getCfgCode("res_teacher_role_flow");
		PageHelper.startPage(pageIndex, pageSize);
		List<SysUser> sysUserList=hbresKzrBiz.teacherRoleCheckUser(userinfo.getDeptFlow(),cfgTeacher,teaName,userFlow);

		Map<String, List<DeptTeacherGradeInfo>> studentListMap=new HashMap<String, List<DeptTeacherGradeInfo>>();
		Map<String, String> averageMap=new HashMap<String, String>();
		Map<String,String> gradeScoreMap=new HashMap<>();
		for (SysUser user : sysUserList) {
			List<DeptTeacherGradeInfo> gradeInfoList=gradeBiz.searchProssFlowRec(user.getUserFlow());
			Float sum=0f;
			Float average=0f;
			if (gradeInfoList!=null && gradeInfoList.size()>0) {
				for (DeptTeacherGradeInfo gradeInfo : gradeInfoList) {
					Map<String,Object> gradeMap = jswjwBiz.parseGradeInfoXml(gradeInfo.getRecContent());
					Float zongFen= Float.parseFloat(gradeMap.get("totalScore").toString());
					gradeScoreMap.put(gradeInfo.getRecFlow(),gradeMap.get("totalScore").toString());
					sum=sum+zongFen;
				}
				average=sum/gradeInfoList.size();
			}
			BigDecimal bd=new BigDecimal(average).setScale(1, RoundingMode.UP);
			averageMap.put(user.getUserFlow(), bd.toString());//平均分
			studentListMap.put(user.getUserFlow(),gradeInfoList);
		}

		final Map<String, String> averageMapTemp = averageMap;
		Collections.sort(sysUserList,new Comparator<SysUser>() {
			@Override
			public int compare(SysUser o1, SysUser o2) {
				String	o1Key=o1.getUserFlow();
				String	o2Key=o2.getUserFlow();
				String s1=averageMapTemp.get(o1Key);
				String s2=averageMapTemp.get(o2Key);
				if(StringUtil.isBlank(s1))
				{
					s1="0";
				}
				if(StringUtil.isBlank(s2))
				{
					s2="0";
				}
				Float f1=0f;
				Float f2=0f;
				try {
					f1=Float.valueOf(s1);
					f2=Float.valueOf(s2);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Float result=f2-f1;
				return result>0?1:result==0?0:-1;
			}
		});
		model.addAttribute("averageMap", averageMap);
		model.addAttribute("gradeScoreMap", gradeScoreMap);
		model.addAttribute("studentListMap", studentListMap);
		model.addAttribute("sysUserList", sysUserList);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzzl/kzr/teacherGradeList";
	}
	/**
	 * 科室评价
	 * @return
	 */
	@RequestMapping(value="/deptGrade",method=RequestMethod.POST)
	public String deptGrade(String userFlow,String deptFlow,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/deptGrade";
		}
		if(StringUtil.isBlank(deptFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/gzzl/kzr/deptGrade";
		}
		SysUser userinfo = hbresBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/deptGrade";
		}

		//评分模板
		List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
		String cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
		ResAssessCfg assessCfg = jswjwBiz.getGradeTemplate(cfgCodeId);
		model.addAttribute("assessCfg", assessCfg);
		Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
		String titleXpath = "//title";
		List<Element> titleElementList = dom.selectNodes(titleXpath);
		if (titleElementList != null && !titleElementList.isEmpty()) {
			for (Element te : titleElementList) {
				ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
				titleForm.setId(te.attributeValue("id"));
				titleForm.setName(te.attributeValue("name"));
				List<Element> itemElementList = te.elements("item");
				List<ResAssessCfgItemForm> itemFormList = null;
				int score=0;
				if (itemElementList != null && !itemElementList.isEmpty()) {
					itemFormList = new ArrayList<ResAssessCfgItemForm>();
					for (Element ie : itemElementList) {
						ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
						itemForm.setId(ie.attributeValue("id"));
						itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
						itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
						if(ResAssessScoreTypeEnum.Nine.getId().equals(assessCfg.getAssessTypeId()))
						{
							score +=9;
						}else {
							if (StringUtil.isNotBlank(itemForm.getScore())) {
								score += Integer.valueOf(itemForm.getScore());
							}
						}
						itemFormList.add(itemForm);
					}
				}
				titleForm.setItemList(itemFormList);
				titleForm.setScore(String.valueOf(score));
				titleFormList.add(titleForm);
			}
			model.addAttribute("titleFormList", titleFormList);
		}
		String isOpen = gradeBiz.getCfgByCode("res_permit_open_doc");
		List<DeptTeacherGradeInfo> gradeInfoList=gradeBiz.searchDeptFlowRec(deptFlow, isOpen);
		Float sum=0f;
		Map<String, Float> heJiMap=new HashMap<String, Float>();
		if (gradeInfoList!=null && gradeInfoList.size()>0) {
			for (DeptTeacherGradeInfo gradeInfo : gradeInfoList) {
				Map<String,Object> gradeMap = jswjwBiz.parseGradeInfoXml(gradeInfo.getRecContent());
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
					Float deValue= Float.parseFloat(scoreMap.get("score"));
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
		model.addAttribute("heJiMap", heJiMap);
		BigDecimal ii=new BigDecimal(sum).setScale(1, BigDecimal.ROUND_HALF_UP);
		sum=ii.floatValue();
		model.addAttribute("average", sum);
		return "res/gzzl/kzr/deptGrade";
	}
	/**
	 * 科室评分学员list
	 * @return
	 */
	@RequestMapping(value="/deptGradeList",method=RequestMethod.POST)
	public String deptGradeList(String userFlow,String deptFlow,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/deptGradeList";
		}
		if(StringUtil.isBlank(deptFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/gzzl/kzr/deptGradeList";
		}
		SysUser userinfo = hbresBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/deptGradeList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzzl/kzr/deptGradeList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzzl/kzr/deptGradeList";
		}
		String isOpen = gradeBiz.getCfgByCode("res_permit_open_doc");
		PageHelper.startPage(pageIndex, pageSize);
		List<DeptTeacherGradeInfo> gradeInfoList=gradeBiz.searchDeptFlowRec(deptFlow,isOpen);

		Map<String,String> gradeScoreMap=new HashMap<>();
		if (gradeInfoList!=null && gradeInfoList.size()>0) {
			for (DeptTeacherGradeInfo gradeInfo : gradeInfoList) {
				Map<String,Object> gradeMap = jswjwBiz.parseGradeInfoXml(gradeInfo.getRecContent());
				gradeScoreMap.put(gradeInfo.getRecFlow(),gradeMap.get("totalScore").toString());
			}
		}
		final		Map<String,String> map=gradeScoreMap;
		Collections.sort(gradeInfoList,new Comparator<DeptTeacherGradeInfo>() {
			@Override
			public int compare(DeptTeacherGradeInfo o1, DeptTeacherGradeInfo o2) {
				String	o1Key=o1.getRecFlow();
				String	o2Key=o2.getRecFlow();
				String s1=map.get(o1Key);
				String s2=map.get(o2Key);
				Float f1=0f;
				Float f2=0f;
				try {
					f1=Float.valueOf(s1);
					f2=Float.valueOf(s2);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Float result=f2-f1;
				return result>0?1:result==0?0:-1;
			}
		});
		model.addAttribute("gradeScoreMap", gradeScoreMap);
		model.addAttribute("gradeInfoList", gradeInfoList);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzzl/kzr/deptGradeList";
	}
	/**
	 * 带教评分详情
	 * @return
	 */
	@RequestMapping(value="/gradeDetail",method=RequestMethod.POST)
	public String gradeDetail(String userFlow,String recFlow,String gradeType,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/gradeDetail";
		}
		if (StringUtil.isBlank(recFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "评价标识符为空");
			return "res/gzzl/kzr/gradeDetail";
		}
		if (StringUtil.isBlank(gradeType)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "评价类型为空");
			return "res/gzzl/kzr/gradeDetail";
		}
		if (!ResRecTypeEnum.TeacherGrade.getId().equals(gradeType) && !ResRecTypeEnum.DeptGrade.getId().equals(gradeType)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "评价类型只能是TeacherGrade或DeptGrade");
			return "res/gzzl/kzr/gradeDetail";
		}
		SysUser userinfo = hbresBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/gradeDetail";
		}
		//评分内容
		DeptTeacherGradeInfo gradeInfo = gradeBiz.readByFlow(recFlow);
		if (gradeInfo != null && StringUtil.isNotBlank(gradeInfo.getRecContent()))
		{
			Map<String,Object> gradeMap = jswjwBiz.parseGradeInfoXml(gradeInfo.getRecContent());
			model.addAttribute("gradeMap",gradeMap);
		}
		//评分模板
		List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
		String cfgCodeId =null;
		if(ResRecTypeEnum.TeacherGrade.getId().equals(gradeType) ){
			cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
		}else if(ResRecTypeEnum.DeptGrade.getId().equals(gradeType) ){
			cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
		}
		ResAssessCfg assessCfg = jswjwBiz.getGradeTemplate(cfgCodeId);
		model.addAttribute("assessCfg", assessCfg);
		Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
		String titleXpath = "//title";
		List<Element> titleElementList = dom.selectNodes(titleXpath);
		if (titleElementList != null && !titleElementList.isEmpty()) {
			for (Element te : titleElementList) {
				ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
				titleForm.setId(te.attributeValue("id"));
				titleForm.setName(te.attributeValue("name"));
				List<Element> itemElementList = te.elements("item");
				List<ResAssessCfgItemForm> itemFormList = null;
				int score=0;
				if (itemElementList != null && !itemElementList.isEmpty()) {
					itemFormList = new ArrayList<ResAssessCfgItemForm>();
					for (Element ie : itemElementList) {
						ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
						itemForm.setId(ie.attributeValue("id"));
						itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
						itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
						if(ResAssessScoreTypeEnum.Nine.getId().equals(assessCfg.getAssessTypeId()))
						{
							score +=9;
						}else {
							if (StringUtil.isNotBlank(itemForm.getScore())) {
								score += Integer.valueOf(itemForm.getScore());
							}
						}
						itemFormList.add(itemForm);
					}
				}
				titleForm.setItemList(itemFormList);
				titleForm.setScore(String.valueOf(score));
				titleFormList.add(titleForm);
			}
			model.addAttribute("titleFormList", titleFormList);
		}
		return "res/gzzl/kzr/gradeDetail";
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

	@RequestMapping(value = {"/evalStudentList"},method = {RequestMethod.POST})
	public String evalStudentList(String userFlow,String roleId,String seachStr,
								  Integer pageIndex,Integer pageSize,Model model) throws DocumentException, ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/evalStudentList";
		}
		//验证用户是否存在
		SysUser userinfo = hbresBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/evalStudentList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzzl/kzr/evalStudentList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzzl/kzr/evalStudentList";
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("deptFlow", userinfo.getDeptFlow());
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=hbresKzrBiz.schDoctorSchProcessHead(schArrangeResultMap);

		Map<String,Object> evalMap=new HashMap<String,Object>();
		for (Map<String, String> map : resDoctorSchProcess) {
			SchArrangeResult schArrangeResult=studentBiz.searcheDocResult(null,map.get("schResultFlow"));
			String processFlow=map.get("processFlow");
			List<Map<String, String>> times=new ArrayList<>();
			if (schArrangeResult!=null) {
				String startDate=schArrangeResult.getSchStartDate();
				String endDate=schArrangeResult.getSchEndDate();
				times=getTimes(startDate,endDate,processFlow);
			}
			List<Map<String, Object>> evals=new ArrayList<>();
			if(times!=null&&times.size()>0)
			{
				Map<String, Object> params=new HashMap<String, Object>();
				params.put("teacherUserFlow", userFlow);
				params.put("times", times);
				evals=jswjwTeacherBiz.findProcessEvals(params);
			}
			evalMap.put(processFlow,evals);
		}
		model.addAttribute("evalMap", evalMap);
		model.addAttribute("list",resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzzl/kzr/evalStudentList";
	}
	/**
	 * 月度考核表列表
	 * @param userFlow
	 * @param processFlow
	 * @param schResultFlow
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/monthEvalList")
	public String monthEvalList(String userFlow,String doctorFlow,String processFlow,String schResultFlow,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/monthEvalList";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/gzzl/kzr/monthEvalList";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/gzzl/kzr/monthEvalList";
		}
		if(StringUtil.isBlank(schResultFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "排班标识符为空");
			return "res/gzzl/kzr/monthEvalList";
		}
		//验证用户是否存在
		SysUser userinfo = hbresBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/monthEvalList";
		}
		//拆分学员的培训时间
		SchArrangeResult schArrangeResult=studentBiz.searcheDocResult(null,schResultFlow);
		List<Map<String, String>> times=new ArrayList<>();
		if (schArrangeResult!=null) {
			String startDate=schArrangeResult.getSchStartDate();
			String endDate=schArrangeResult.getSchEndDate();
			times=getTimes(startDate,endDate,processFlow);
		}
		List<Map<String, Object>> evals=null;
		if(times!=null&&times.size()>0)
		{
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("teacherUserFlow", userFlow);
			params.put("times", times);
			evals=jswjwTeacherBiz.findProcessEvals(params);
		}
		model.addAttribute("evals",evals);
		model.addAttribute("processFlow",processFlow);
		model.addAttribute("doctorFlow",doctorFlow);
		return "res/gzzl/kzr/monthEvalList";
	}
	@RequestMapping(value="/showMonthEval",method=RequestMethod.POST)
	public String showMonthEval(String userFlow,String doctorFlow,String processFlow,String recordFlow,String evalMonth,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/showMonthEval";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/gzzl/kzr/showMonthEval";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/gzzl/kzr/showMonthEval";
		}
		if(StringUtil.isBlank(evalMonth)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核时间不能为空");
			return "res/gzzl/kzr/showMonthEval";
		}
		//验证用户是否存在
		SysUser userinfo = hbresBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/showMonthEval";
		}
		ResDoctorSchProcessEvalWithBLOBs eval=jswjwBiz.getDoctorProcessEvalByFlow(recordFlow);
		if(eval==null)
			eval=jswjwBiz.getDoctorProcessEval(processFlow,evalMonth);
		List<FromTitle> titleList=null;
		String configXml="";
		String configFlow="";
		String IsForm="N";
		if(eval!=null)
		{
			IsForm=eval.getIsForm();
			configXml=eval.getFormCfg();
			//已经保存的分数
			Map<String, Object> map=jswjwBiz.parseScoreXml(eval.getEvalResult());
			model.addAttribute("valueMap", map);
		}else{
			//评分配置
			ResDoctorProcessEvalConfig config=jswjwBiz.getProcessEvalConfig(userinfo.getOrgFlow());
			if(config!=null) {
				configXml = config.getFormCfg();
				IsForm="Y";
				configFlow=config.getConfigFlow();
			}
		}
		model.addAttribute("isAudit",eval!=null);
		titleList=jswjwBiz.parseFromXmlForList(configXml);
		if(titleList!=null&&titleList.size()>0){
			IsForm="Y";
		}else{
			IsForm="N";
		}
		model.addAttribute("titleList",titleList);
		model.addAttribute("IsForm",IsForm);
		model.addAttribute("eval",eval);
		model.addAttribute("configFlow",configFlow);
		model.addAttribute("processFlow",processFlow);
		model.addAttribute("doctorFlow",doctorFlow);
		model.addAttribute("recordFlow",recordFlow);
		return "res/gzzl/kzr/showMonthEval";
	}
	@RequestMapping(value="/ownerInfo",method=RequestMethod.POST)
	public String ownerInfo(String userFlow,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/ownerInfo";
		}
		//验证用户是否存在
		SysUser userinfo = hbresBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/ownerInfo";
		}
		model.addAttribute("userinfo",userinfo);
		List<SysUserRole> userRoles = hbresBiz.getSysUserRole(userFlow);
		if(userRoles!=null&&userRoles.size()>0) {
			//获取当前配置的老师角色
			String teacherRole = hbresBiz.getCfgCode("res_teacher_role_flow");
			//获取当前配置的科主任角色
			String headRole = hbresBiz.getCfgCode("res_head_role_flow");
			List<Map<String,String>> roles=new ArrayList<>();
			for (SysUserRole sur : userRoles) {
				Map<String,String> map=new HashMap<>();
				String ur = sur.getRoleFlow();
				if (headRole.equals(ur)) {
					map.put("roleId", "Head");
					map.put("roleName", "科主任");
					roles.add(map);
				}
				if (teacherRole.equals(ur)) {
					map.put("roleId", "Teacher");
					map.put("roleName", "老师");
					roles.add(map);
				}
			}
			model.addAttribute("roles",roles);
		}
		return "res/gzzl/kzr/ownerInfo";
	}
	@RequestMapping(value="/saveOwnerInfo",method=RequestMethod.POST)
	public String saveOwnerInfo(SysUser user,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(user.getUserFlow())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isNotBlank(user.getSexId())) {
			if (!"Man".equals(user.getSexId()) && !"Woman".equals(user.getSexId())) {
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "性别ID不正确");
				return "res/gzzl/success";
			}
			if ("Man".equals(user.getSexId())) {
				user.setSexName("男");
			}
			if ("Woman".equals(user.getSexId())) {
				user.setSexName("女");
			}
		}
		int result=jswjwBiz.saveUserInfo(user);
		if(result==0)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "保存失败");
			return "res/gzzl/success";
		}
		return "res/gzzl/success";
	}

	//出科审核学员列表
	@RequestMapping(value="/afterFormAudit",method={RequestMethod.POST})
	public String afterFormAudit(String userFlow,String sessionNumber,String userName,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/afterFormAudit";
		}
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userFlow",userFlow);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("userName",userName);
		String isOpen = hbresBiz.getCfgByCode("res_permit_open_doc");//权限期间是否开通
		paramMap.put("isOpen",isOpen);
		PageHelper.startPage(pageIndex, pageSize);
		List<ResDoctorSchProcess> processList = hbresKzrBiz.searchProcessByDoctorNew(paramMap);
		model.addAttribute("dataList", processList);
		model.addAttribute("dataCount", PageHelper.total);
		if(processList!=null && processList.size()>0){
			List<String> userFlows = new ArrayList<String>();
			for(ResDoctorSchProcess rdsp : processList){
				if(!userFlows.contains(rdsp.getUserFlow())){
					userFlows.add(rdsp.getUserFlow());
				}
			}
			List<SysUser> userList = hbresKzrBiz.searchSysUserByuserFlows(userFlows);
			if(userList!=null && userList.size()>0){
				Map<String,SysUser> userMap = new HashMap<String, SysUser>();
				for(SysUser su : userList){
					userMap.put(su.getUserFlow(),su);
				}
				model.addAttribute("userMap",userMap);
			}
			List<ResDoctor> doctorList = hbresKzrBiz.searchDoctorByuserFlow(userFlows);
			if(doctorList!=null && doctorList.size()>0){
				Map<String,ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
				for(ResDoctor rd : doctorList){
					doctorMap.put(rd.getDoctorFlow(),rd);
				}
				model.addAttribute("doctorMap",doctorMap);
			}
		}
		return "res/gzzl/kzr/afterFormAudit";
	}
	//出科分类表详情
	@RequestMapping(value="/showRegistryForm",method={RequestMethod.GET})
	public String showRegistryForm(String userFlow, String resultFlow, String recTypeId, String recFlow, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "3010601");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/showRegistryForm";
		}
		if (StringUtil.isEmpty(recTypeId)) {
			model.addAttribute("resultId", "3010604");
			model.addAttribute("resultType", "数据类型标识为空");
			return "res/gzzl/kzr/showRegistryForm";
		}
		if (StringUtil.isEmpty(resultFlow)) {
			model.addAttribute("resultId", "3010606");
			model.addAttribute("resultType", "计划标识符为空");
			return "res/gzzl/kzr/showRegistryForm";
		}
		//当前轮转计划信息
		SchArrangeResult result = studentBiz.searcheDocResult(null, resultFlow);
		model.addAttribute("result", result);
		SysUser tea = hbresBiz.readSysUser(userFlow);
		model.addAttribute("tea", tea);

		//获取当前这条登记信息
		ResSchProcessExpress rec = null;
		if (StringUtil.isNotBlank(recFlow)) {
			rec = expressBiz.getExpressByRecFlow(recFlow);
		} else {
			String processFlow = "";
			ResDoctorSchProcess process = studentBiz.getProcessByResult(resultFlow);
			if (process != null) {
				//登记表单的科室
				processFlow = process.getProcessFlow();
			}
			rec = expressBiz.getExpressByRecType(processFlow, recTypeId);
		}
		boolean isAudit=false;
		if (rec != null) {
			String content = rec.getRecContent();
			//解析登记信息的xml
			Object formDataMap = null;
			formDataMap = hbresBiz.parseRecContent(content);
			model.addAttribute("formDataMap", formDataMap);
			//如果读取到数据就使用数据本身的funcFlow
			recTypeId = rec.getRecTypeId();
			if(StringUtil.isNotBlank(rec.getHeadAuditStatusId()))
			{
				isAudit=true;
			}
		}
		model.addAttribute("isAudit", isAudit);
		model.addAttribute("funcFlow", recTypeId);
		if (recTypeId.equals(com.pinde.res.enums.hbres.ResRecTypeEnum.AfterEvaluation.getId())) {
			ResDoctorSchProcess process = hbresBiz.readSchProcessByResultFlow(resultFlow);
			if (process != null) {
				String res_after_test_switch = hbresBiz.getCfgByCode("res_after_test_switch");
				String res_after_url_cfg = hbresBiz.getCfgByCode("res_after_url_cfg");
				//学员开通出科考试权限
				Map<String, String> paramMap = new HashMap();
				String cfgCode = "res_doctor_ckks_" + process.getUserFlow();
				paramMap.put("cfgCode", cfgCode);
				String isCkksFlag = resPowerCfgBiz.getResPowerCfg(paramMap);
				boolean testTypeFlag = false;
				if (GlobalConstant.FLAG_Y.equals(res_after_test_switch) && StringUtil.isNotBlank(res_after_url_cfg)
						&& GlobalConstant.FLAG_Y.equals(isCkksFlag)) {
					testTypeFlag = true;
				}
				model.addAttribute("testTypeFlag", testTypeFlag);
				ResScore score = hbresBiz.getScoreByProcess(process.getProcessFlow());
				model.addAttribute("outScore", score);
				model.addAttribute("doctorSchProcess", process);
				model.addAttribute("currRegProcess", process);

				//百分比算法
				Map<String, Object> perMap = studentBiz.getRegPer(0, process.getUserFlow(), resultFlow, null, null, true);
				model.addAttribute("perMap", perMap);
				//logger.debug("===================" + JSON.toJSON(perMap));
				//参加活动的信息
				Map<String, Object> capData = new HashMap<>();
				List<Map<String, Object>> capDatas = teacherBiz.getDocCapDatas(RegistryTypeEnum.CampaignNoItemRegistry.getId(), process.getProcessFlow());
				for (Map<String, Object> m : capDatas) {
					capData.put((String) m.get("INFO_KEY"), m);
				}
				model.addAttribute("capData", capData);
			}
		}
		return "res/gzzl/kzr/showRegistryForm";
	}

	//保存出科分类表
	@RequestMapping(value="/saveRegistryForm",method={RequestMethod.POST})
	public String saveRegistryForm(String userFlow, String resultFlow, String recTypeId, String dataFlow, HttpServletRequest request,Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "3010901");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/saveRegistryForm";
		}
		if (StringUtil.isEmpty(resultFlow)) {
			model.addAttribute("resultId", "3010904");
			model.addAttribute("resultType", "排班标识符为空");
			return "res/gzzl/kzr/saveRegistryForm";
		}
		if (StringUtil.isEmpty(recTypeId)) {
			model.addAttribute("resultId", "3010906");
			model.addAttribute("resultType", "功能标识为空");
			return "res/gzzl/kzr/saveRegistryForm";
		}
		SysUser user = hbresBiz.readSysUser(userFlow);
		model.addAttribute("user", user);
		//获取当前操作人
		String operUserFlow = "";
		//如果是11查出dataFlow
		ResSchProcessExpress rec = null;
		if (StringUtil.isEmpty(dataFlow)) {
			String processFlow = "";
			ResDoctorSchProcess process = studentBiz.getProcessByResult(resultFlow);
			if (process != null) {
				//登记表单的科室
				processFlow = process.getProcessFlow();
				operUserFlow = process.getUserFlow();
			}
			rec = expressBiz.getExpressByRecType(processFlow, recTypeId);
			if (rec != null) {
				dataFlow = rec.getRecFlow();
			}else{
				model.addAttribute("resultId", "3010907");
				model.addAttribute("resultType", "该出科记录不存在");
				return "res/gzzl/kzr/saveRegistryForm";
			}
		} else {
			rec = expressBiz.getExpressByRecFlow(dataFlow);
		}
		String content="";
		if (rec != null) {
			recTypeId = rec.getRecTypeId();
			content=rec.getRecContent();
		}

		dataFlow = expressBiz.editExpress(dataFlow, operUserFlow, resultFlow, recTypeId, GlobalConstant.RES_HBRES_DEFAULT_FORM, request,GlobalConstant.RES_ROLE_SCOPE_HEAD,content, "");
		//审核数据
		hbresKzrBiz.auditDate(userFlow, dataFlow);
		return "res/gzzl/kzr/saveRegistryForm";
	}

	/**
	 * 科教通知
	 *
	 * @return
	 */
	@RequestMapping(value = {"/getSysNotice"}, method = {RequestMethod.POST})
	public String getSysNotice(String userFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request,Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/sysNotices/getSysNotice";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/sysNotices/getSysNotice";
		}

		if (pageIndex == null) {
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzzl/sysNotices/getSysNotice";
		}
		if (pageSize == null) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzzl/sysNotices/getSysNotice";
		}
		String orgFlow="";
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,String>> infos = this.noticeBiz.searchInfoByOrgBeforeDate(orgFlow,null, GlobalConstant.RES_NOTICE_TYPE5_ID, GlobalConstant.RES_NOTICE_SYS_ID, userFlow,null);
		model.addAttribute("infoList",infos);

		Map<String,Object> isReadMap=new HashMap<>();
		if(infos!=null&&infos.size()>0)
		{
			for(Map<String,String> info:infos)
			{
				ResReadInfo resReadInfo=inxInfoBiz.getReadInfoByFlow(info.get("infoFlow"),userFlow);
				isReadMap.put(info.get("infoFlow"),resReadInfo);
			}
		}
		model.addAttribute("isReadMap", isReadMap);
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/gzzl/sysNotices/no-pic.png";
		model.addAttribute("hostUrl",hostUrl);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzzl/sysNotices/getSysNotice";
	}

	@RequestMapping(value={"/sysNoticeDetail"})
	public String sysNoticeDetail(String userFlow,String infoFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/sysNotices/sysNoticeDetail";
		}
		if(StringUtil.isEmpty(infoFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培动态标识符为空");
			return "res/gzzl/sysNotices/sysNoticeDetail";
		}
		InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
		if(info==null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培动态不存在，请刷新列表页面");
			return "res/gzzl/sysNotices/sysNoticeDetail";
		}
		ResReadInfo resReadInfo=inxInfoBiz.getReadInfoByFlow(infoFlow,userFlow);
		if(resReadInfo==null)
		{
			resReadInfo=new ResReadInfo();
			resReadInfo.setInfoFlow(infoFlow);
			resReadInfo.setUserFlow(userFlow);
			inxInfoBiz.saveReadInfo(userFlow,resReadInfo);
		}
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/gzzl/sysNotices/showSysNotice.jsp?recordFlow="+infoFlow;
		String androidimgurl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/gzzl/sysNotices/showSysNotice2.jsp?recordFlow="+infoFlow;
		model.addAttribute("iosDetailUrl",hostUrl);
		model.addAttribute("androidDetailUrl",androidimgurl);
		return "res/gzzl/sysNotices/sysNoticeDetail";
	}

	@RequestMapping(value = {"/activityList"}, method = {RequestMethod.POST})
	public String activityList(String userFlow
			,String isOwner,String yearMonth
			,Integer pageIndex,Integer pageSize,Model model,String roleId) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/activityList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/gzzl/kzr/activityList";
		}
		if(StringUtil.isBlank(isOwner)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "isOwner为空");
			return "res/gzzl/kzr/activityList";
		}
		if(!isOwner.equals("Y")&&!isOwner.equals("N")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "isOwner只能是Y或N");
			return "res/gzzl/kzr/activityList";
		}
		if (StringUtil.isBlank(yearMonth)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "日期为空");
			return "res/gzzl/kzr/activityList";
		}
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
			dateFormat2.parse(yearMonth);
		} catch (Exception e) {
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "yearMonth格式有误");
			return "res/gzzl/kzr/activityList";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/gzzl/kzr/activityList";
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/activityList";
		}

		if (pageIndex == null) {
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzzl/kzr/activityList";
		}
		if (pageSize == null) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzzl/kzr/activityList";
		}
		Map<String,String> param=new HashMap<>();
		param.put("isOwner",isOwner);
		param.put("yearMonth",yearMonth);
		param.put("roleFlag","head");
		param.put("userFlow",userinfo.getUserFlow());
		model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
		param.put("orgFlow", userinfo.getOrgFlow());
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> list=activityBiz.findActivityList(param);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		model.addAttribute("list", list);
		model.addAttribute("dataCount", PageHelper.total);
		model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));

		List<SysDict> activityList=hbresBiz.getDictListByDictId("ActivityType");
		model.addAttribute("activityList",activityList);
		Map<String,Object> activityTypeMap=activityBiz.findActivityTypeMap(param);
		model.addAttribute("activityTypeMap", activityTypeMap);
		return "res/gzzl/kzr/activityList";
	}

	@RequestMapping(value = {"/qrCode"}, method = {RequestMethod.POST})
	public String qrCode(String userFlow
			,String activityFlow,Model model,String roleId) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/qrCode";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/gzzl/kzr/qrCode";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/gzzl/kzr/qrCode";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/gzzl/kzr/qrCode";
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/qrCode";
		}
		TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
		if(info==null||!GlobalConstant.RECORD_STATUS_Y.equals(info.getRecordStatus()))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/gzzl/kzr/qrCode";
		}
		String url = "func://funcFlow=activitySigin&activityFlow="+activityFlow;
		String url2 = "func://funcFlow=activityOutSigin&activityFlow="+activityFlow;
		model.addAttribute("url", url);
		model.addAttribute("url2", url2);
		return "res/gzzl/kzr/qrCode";
	}
	@RequestMapping(value = {"/delActivity"}, method = {RequestMethod.POST})
	public String delActivity(String userFlow,String activityFlow,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/gzzl/success";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/gzzl/success";
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/success";
		}
		TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
		if(activity==null)
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/gzzl/success";
		}

		if(!GlobalConstant.RECORD_STATUS_Y.equals(activity.getRecordStatus()))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息已被删除，请刷新列表！");
			return "res/gzzl/success";
		}

		if( DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(activity.getStartTime())>=0)
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动已开始，无法删除！");
			return "res/gzzl/success";
		}
		activity.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		model.addAttribute("activity", activity);
		int c=activityBiz.saveActivityInfo(activity,userinfo);
		if(c==0)
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "删除失败！");
			return "res/gzzl/success";
		}
		return "res/gzzl/success";
	}
	@RequestMapping(value = {"/showActivity"}, method = {RequestMethod.POST})
	public String showActivity(String userFlow,String activityFlow,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		model.addAttribute("roleFlag", roleId);
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/showActivity";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/gzzl/kzr/showActivity";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/gzzl/kzr/showActivity";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/gzzl/kzr/showActivity";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/showActivity";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
		if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/gzzl/kzr/showActivity";
		}
		model.addAttribute("activity", activity);
		model.addAttribute("user",userinfo);
		if( DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(String.valueOf(activity.get("startTime")))<0)
		{
			List<SysDept> depts=jswjwBiz.getHeadDeptList(userFlow,userinfo.getDeptFlow());
			model.addAttribute("depts",depts);
			Map<String,List<SysUser>> deptUserMap=new HashMap<>();
			if(depts!=null)
			{
				String teacherRoleFlow=jswjwBiz.getCfgCode("res_teacher_role_flow");
				String headRoleFlow = jswjwBiz.getCfgCode("res_head_role_flow");
				for(SysDept d:depts)
				{

					List<SysUser> users=hbresKzrBiz.readDeptTeachAndHead(d.getDeptFlow(),teacherRoleFlow,headRoleFlow);
					deptUserMap.put(d.getDeptFlow(),users);
				}
			}
			List<SysDict> activityList=hbresBiz.getDictListByDictId("ActivityType");
			model.addAttribute("activityList",activityList);
			model.addAttribute("deptUserMap",deptUserMap);
			return "res/gzzl/kzr/editActivity";
		}
		int imgCount=0;
		if(activity!=null)
		{
			String content= (String) activity.get("imageUrl");
			List<Map<String, String>> imageList=activityBiz.parseImageXml(content);
			model.addAttribute("imageList", imageList);
			if(imageList!=null)
			{
				imgCount=imageList.size();
			}
		}
		model.addAttribute("imgCount",imgCount+"张");
		return "res/gzzl/kzr/showActivity";
	}
	@RequestMapping(value = {"/activityEval"}, method = {RequestMethod.POST})
	public String activityEval(String userFlow,String activityFlow,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/activityEval";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/gzzl/kzr/activityEval";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/gzzl/kzr/activityEval";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/gzzl/kzr/activityEval";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/activityEval";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
		if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/gzzl/kzr/activityEval";
		}
		//评价的指标
		List<Map<String,Object>> targets=activityTargeBiz.readActivityTargetEvals(activityFlow);
		model.addAttribute("targets",targets);
		return "res/gzzl/kzr/activityEval";
	}
	@RequestMapping(value = {"/activityEvalList"}, method = {RequestMethod.POST})
	public String activityEvalList(String userFlow,String activityFlow,String searchStr,
								   Integer pageIndex,
								   Integer pageSize,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/activityEvalList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/gzzl/kzr/activityEvalList";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/gzzl/kzr/activityEvalList";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/gzzl/kzr/activityEvalList";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/activityEvalList";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
		if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/gzzl/kzr/activityEvalList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "res/gzzl/kzr/activityEvalList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "res/gzzl/kzr/activityEvalList";
		}TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
		model.addAttribute("activity",info);
		//评价人员
		PageHelper.startPage(pageIndex,pageSize);
		List<Map<String,Object>> results=activityBiz.readActivityResults(activityFlow,searchStr);
		model.addAttribute("results",results);
		model.addAttribute("user",userinfo);
		model.addAttribute("dataCount", PageHelper.total);

		//评价的指标
		List<Map<String,Object>> targets=activityTargeBiz.readActivityTargetEvals(activityFlow);
		model.addAttribute("targets",targets);
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
		return "res/gzzl/kzr/activityEvalList";
	}
	@RequestMapping(value = {"/effectiveResult"}, method = {RequestMethod.POST})
	public String effectiveResult(String userFlow,  String activityFlow,
								  String resultFlow,  String isEffective,
								  Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isBlank(resultFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "resultFlow为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isBlank(isEffective)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "isEffective");
			return "res/gzzl/success";
		}
		if(!"Y".equals(isEffective)&&!"N".equals(isEffective))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "isEffective只能是Y或N");
			return "res/gzzl/success";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/gzzl/success";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/success";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
		if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/gzzl/success";
		}
		TeachingActivityResult info=activityBiz.readResult(resultFlow);
		if(info==null) {
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "学员参加活动结果信息不存在，请刷新列表页面！");
			return "res/gzzl/success";
		}
		info.setIsEffective(isEffective);
		int c=activityBiz.saveResult(info,userFlow);
		if(c==0){
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "修改失败");
			return "res/gzzl/success";
		}
		return "res/gzzl/success";
	}
	@RequestMapping(value = {"/addActivity"}, method = {RequestMethod.POST})
	public String addActivity(String userFlow,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		model.addAttribute("roleFlag", roleId);
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/editActivity";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/gzzl/kzr/editActivity";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/gzzl/kzr/editActivity";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/editActivity";
		}
		List<TeachingActivityTarget> targets=activityTargeBiz.readByOrg(userinfo.getOrgFlow());
		if(targets==null||targets.size()<=0)
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "暂未配置活动指标，请联系基地管理员");
			return "res/gzzl/kzr/editActivity";
		}
		model.addAttribute("user",userinfo);
		List<SysDept> depts=jswjwBiz.getHeadDeptList(userFlow,userinfo.getDeptFlow());
		model.addAttribute("depts",depts);
		Map<String,List<SysUser>> deptUserMap=new HashMap<>();
		if(depts!=null)
		{
			String teacherRoleFlow=jswjwBiz.getCfgCode("res_teacher_role_flow");
			String headRoleFlow = jswjwBiz.getCfgCode("res_head_role_flow");
			for(SysDept d:depts)
			{

				List<SysUser> users=hbresKzrBiz.readDeptTeachAndHead(d.getDeptFlow(),teacherRoleFlow,headRoleFlow);
				deptUserMap.put(d.getDeptFlow(),users);
			}
		}
		List<SysDict> activityList=hbresBiz.getDictListByDictId("ActivityType");
		model.addAttribute("activityList",activityList);
		model.addAttribute("deptUserMap",deptUserMap);
		return "res/gzzl/kzr/editActivity";
	}
	@RequestMapping(value = {"/saveActivity"}, method = {RequestMethod.POST})
	public String saveActivity(String userFlow,TeachingActivityInfo activity,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isBlank(activity.getSpeakerFlow())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "主讲人标识符为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isBlank(activity.getDeptFlow())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isBlank(activity.getActivityTypeId())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动形式为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isBlank(activity.getActivityName())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动名称为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isBlank(activity.getActivityAddress())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动地点为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isBlank(activity.getSpeakerPhone())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "联系方式为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isBlank(activity.getStartTime())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动开始时间为空");
			return "res/gzzl/success";
		}

		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateFormat2.parse(activity.getStartTime());
		} catch (Exception e) {
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "活动开始时间格式有误");
			return "res/gzzl/success";
		}
		if(StringUtil.isBlank(activity.getEndTime())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动结束时间为空");
			return "res/gzzl/success";
		}
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateFormat2.parse(activity.getEndTime());
		} catch (Exception e) {
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "活动结束时间格式有误");
			return "res/gzzl/success";
		}
		if(activity.getStartTime().compareTo(activity.getEndTime())>=0)
		{
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "开始时间不得大于等于结束时间");
			return "res/gzzl/success";
		}
		if(StringUtil.isBlank(activity.getActivityRemark())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动简介为空");
			return "res/gzzl/success";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/gzzl/success";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/success";
		}
		model.addAttribute("user",userinfo);
		List<TeachingActivityTarget> targets=activityTargeBiz.readByOrg(userinfo.getOrgFlow());
		if(targets==null||targets.size()<=0)
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "暂未配置活动指标，请联系基地管理员");
			return "res/gzzl/success";
		}
		//校验活动时间是否重复
		int count=checkTime(activity);
		if(count>0)
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "该主讲人在时间段内已开展其他活动");
			return "res/gzzl/success";
		}
		if(StringUtil.isNotBlank(activity.getActivityTypeId()))
		{
			activity.setActivityTypeName(hbresBiz.getDictNameByTypeId(activity.getActivityTypeId(),"ActivityType"));
		}
		activity.setOrgFlow(userinfo.getOrgFlow());
		int c= activityBiz.addActivityInfo(activity,userinfo,targets, null);
		if(c==0)
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "保存失败");
			return "res/gzzl/success";
		}
		return "res/gzzl/success";
	}
	private int checkTime(TeachingActivityInfo activity) {
		return activityBiz.checkTime(activity);
	}
	@RequestMapping(value={"/viewActivityImage"},method={RequestMethod.POST})
	public String viewActivityImage(String userFlow,String activityFlow,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/kzr/viewActivityImage";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/gzzl/kzr/viewActivityImage";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/gzzl/kzr/viewActivityImage";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/gzzl/kzr/viewActivityImage";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/kzr/viewActivityImage";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
		if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/gzzl/kzr/viewActivityImage";
		}
		if(activity!=null)
		{
			String content= (String) activity.get("imageUrl");
			List<Map<String, String>> imageList=activityBiz.parseImageXml(content);
			model.addAttribute("imageList", imageList);
		}
		return "res/gzzl/kzr/viewActivityImage";
	}
	@RequestMapping(value={"/addActivityImage"},method={RequestMethod.POST})
	public String addActivityImage(com.pinde.res.ctrl.hbres.ActivityImageFileForm form, HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(form.getUserFlow())){
			model.addAttribute("resultId", "31601");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isEmpty(form.getActivityFlow())){
			model.addAttribute("resultId", "31602");
			model.addAttribute("resultType", "教学活动标识符为空");
			return "res/gzzl/success";
		}
		if(StringUtil.isEmpty(form.getFileName())){
			model.addAttribute("resultId", "31603");
			model.addAttribute("resultType", "文件名为空");
			return "res/gzzl/success";
		}
		if(form.getUploadFile()==null && StringUtil.isBlank(form.getUploadFileData())){
			model.addAttribute("resultId", "31703");
			model.addAttribute("resultType", "上传文件不能为空");
			return "res/gzzl/success";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(form.getUserFlow());
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/success";
		}

		Map<String, Object> activity=activityBiz.readActivity(form.getActivityFlow());
		if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/gzzl/success";
		}
		activityBiz.addActivityImage(form,userinfo);
		return "res/gzzl/success";
	}

	@RequestMapping(value={"/deleteActivityImage"},method={RequestMethod.GET})
	public String deleteActivityImage(String userFlow,String activityFlow,String imageFlow, HttpServletRequest request,HttpServletResponse response,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31701");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzl/viewImage";
		}
		if(StringUtil.isEmpty(activityFlow)){
			model.addAttribute("resultId", "31702");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/gzzl/viewImage";
		}
		if(StringUtil.isEmpty(imageFlow)){
			model.addAttribute("resultId", "31702");
			model.addAttribute("resultType", "图片标识符为空");
			return "res/gzzl/success";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzl/success";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
		if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/gzzl/success";
		}
		activityBiz.deleteActivityImage(userinfo,activityFlow,imageFlow);
		return "res/gzzl/success";
	}
}

