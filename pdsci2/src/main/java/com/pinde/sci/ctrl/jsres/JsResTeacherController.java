package com.pinde.sci.ctrl.jsres;

import com.pinde.core.common.enums.RecStatusEnum;
import com.pinde.core.common.sci.dao.JsresPowerCfgMapper;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.TimeUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.biz.sch.ISchRotationGroupBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.core.model.JsEvalCfgItemExt;
import com.pinde.core.model.JsEvalCfgTitleExt;
import com.pinde.core.model.JsResAttendanceExt;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorProcessEvalConfig;
import com.pinde.core.model.ResDoctorSchProcess;
import com.pinde.core.model.ResDoctorSchProcessEval;
import com.pinde.core.model.ResDoctorSchProcessEvalWithBLOBs;
import com.pinde.core.model.ResRec;
import com.pinde.core.model.ResScore;
import com.pinde.core.model.SchArrangeResult;
import com.pinde.core.model.SchRotationDept;
import com.pinde.core.model.SchRotationGroup;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jsres/teacher")
public class JsResTeacherController extends GeneralController{

	final static String Jxcf = "1";
	final static String Ynbltl = "2"; final static String Wzbltl = "3";final static String Swbltl = "5";final static String Jxbltl = "11";
	final static String Xsjz = "4";  final static String Rkjy = "6";
	final static String Ckks = "7"; final static String Jnpx = "8"; final static String Yph = "9";
	final static String Jxhz = "10";
	final static String Lcczjnzd = "12"; final static String Lcblsxzd = "13";
	final static String Ssczzd = "14"; final static String Yxzdbgsxzd = "15"; final static String Lcwxyd = "16";
	final static String Ryjy = "17"; final static String Rzyjdjy = "18"; final static String Cjbg = "19";
	final static String Bgdfx = "20";
	final static String Jxsj = "21";
	final static String Sjys = "22";
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IUserBiz iUserBiz;
	@Autowired
	private ISchRotationDeptBiz iSchRotationDeptBiz;
	@Autowired
	private IResRecBiz iResRecBiz;
	@Autowired
	private IResSchProcessExpressBiz  expressBiz;
	@Autowired
	private ISchRotationGroupBiz iSchRotationGroupBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IJsResDoctorBiz iJsResDoctorBiz;
	@Autowired
	private IResDoctorProcessBiz iResDoctorProcessBiz;
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IResAttendanceBiz resAttendanceBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResScoreBiz resScoreBiz;
	@Autowired
	private ResPaperBiz paperBiz;
    @Autowired
    private JsresPowerCfgMapper jsresPowerCfgMapper;

	/**
	 * 带教老师主界面
	 */
	@RequestMapping(value="/index")
	public String index(Model model,Integer currentPage,HttpServletRequest request){
        setSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE, com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER);
		InxInfo info = new InxInfo();
		PageHelper.startPage(currentPage,getPageSize(request));
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		SysUser sysUser=GlobalContext.getCurrentUser();
		String orgFlow = sysUser.getOrgFlow();
		JsresPowerCfg read = jsResPowerCfgBiz.read("jsres_" + orgFlow + "_guocheng");
		if(read!= null){
			model.addAttribute("cfgValue",read.getCfgValue());
		}
		int noAuditNumber=iResDoctorProcessBiz.jsresSchDoctorSchProcessWaitingExamineQuery(sysUser.getUserFlow());
		int currStudentHe=iResDoctorProcessBiz.jsresSchDoctorSchProcessDistinctQuery(sysUser.getUserFlow());
		int studentNum=iResDoctorProcessBiz.jsresSchDoctorSchProcessTeacherQuery(sysUser.getUserFlow());
//		schArrangeResultBiz.roundRobinStudents(sysUser.getDeptFlow());
		model.addAttribute("studentNum",studentNum);
		model.addAttribute("dShenHe",currStudentHe);
		model.addAttribute("noAuditNumber",noAuditNumber);
		model.addAttribute("infos",infos);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE, com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER);
		return "jsres/teacher/index";
	}

	/**
	 * 培训数据审核
	 * @param model
	 * @param currentPage
	 * @param request
	 * @param doctorSchProcess
	 * @param userName
     * @param biaoJi
     * @return
     */
	@RequestMapping(value="/recruitAuditSearch")
	public String recruitAuditSearch(Model model,Integer currentPage,HttpServletRequest request,ResDoctorSchProcess doctorSchProcess,
									 String userName,String biaoJi, String datas[]){
		SysUser sysUser=GlobalContext.getCurrentUser();
		Map<String, Object> schArrangeResultMap=new HashMap<String, Object>();
		schArrangeResultMap.put("teacherUserFlow", sysUser.getUserFlow());
		schArrangeResultMap.put("userName", userName);
		schArrangeResultMap.put("schStartDate", doctorSchProcess.getSchStartDate());
		schArrangeResultMap.put("schEndDate", doctorSchProcess.getSchEndDate());
		schArrangeResultMap.put("biaoJi", biaoJi);
        schArrangeResultMap.put("data", com.pinde.core.common.GlobalConstant.FLAG_Y);
		List<String>docTypeList=new ArrayList<String>();
		model.addAttribute("datas", datas);
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}else{
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i=0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		schArrangeResultMap.put("docTypeList", docTypeList);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String,String>> resDoctorSchProcess=iResDoctorProcessBiz.jsresSchDoctorSchProcessQuery(schArrangeResultMap);
		Map<String, Object> schRotationDeptMap=new HashMap<String, Object>();
		Map<String,Object> readSchRotationGroupMap=new HashMap<String,Object>();
		Map<String,Object> resRecMap=new HashMap<String,Object>();
		Map<String,Integer> resRecCountMap=new HashMap<String,Integer>();
		Map<String, Boolean> notAuditAllMap = new HashMap<>();
		for (Map<String, String> map : resDoctorSchProcess) {
			SchArrangeResult schArrangeResult=schArrangeResultBiz.readSchArrangeResult(map.get("schResultFlow"));
			if (schArrangeResult!=null) {
				SchRotationDept schRotationDept=iSchRotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(schArrangeResult.getStandardGroupFlow(),schArrangeResult.getStandardDeptId());
				if(schRotationDept!=null){
					schRotationDeptMap.put(map.get("processFlow"), schRotationDept);
					if (StringUtil.isNotBlank(schRotationDept.getGroupFlow())) {
						SchRotationGroup readSchRotationGroup=iSchRotationGroupBiz.readSchRotationGroup(schRotationDept.getGroupFlow());
						readSchRotationGroupMap.put(map.get("processFlow"), readSchRotationGroup);
					}
				}
			}
			List<ResRec> resRecList=iResRecBiz.searchRecByProcess(map.get("processFlow"),map.get("userFlow"));
			boolean notAuditAll = false;
			for (ResRec resRec : resRecList) {
				String k = map.get("processFlow")+resRec.getRecTypeId();
				String k2 = k+"notAudit";
				Integer i = resRecCountMap.get(k);
				if(i==null){
					i=0;
				}
				i++;
				resRecCountMap.put(k,i);
				if(!StringUtil.isNotBlank(resRec.getAuditStatusId())){
					if(!notAuditAll) {
						notAuditAll = true;
					}
					Integer j = resRecCountMap.get(k2);
					if(j==null){
						j=0;
					}
					j++;
					resRecCountMap.put(k2,j);
				}

			}

			//出科考核 已拆分到新表
			List<ResSchProcessExpress> expressList = expressBiz.searchRecByProcess(map.get("processFlow"),map.get("userFlow"));
			for(ResSchProcessExpress express : expressList){
                if (com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId().equals(express.getRecTypeId())) {
					resRecMap.put(map.get("processFlow"),express);
				}
			}
			notAuditAllMap.put(map.get("processFlow"), notAuditAll);
		}
		model.addAttribute("notAuditAllMap", notAuditAllMap);
		model.addAttribute("resRecMap", resRecMap);
		model.addAttribute("resRecCountMap", resRecCountMap);
		model.addAttribute("schRotationDeptMap", schRotationDeptMap);
		model.addAttribute("readSchRotationGroupMap", readSchRotationGroupMap);
		model.addAttribute("biaoJi", biaoJi);
		model.addAttribute("resDoctorSchProcess", resDoctorSchProcess);
		return "jsres/teacher/recruitAuditSearch";
	}

	/**
	 * 查看考勤信息 住院医师
	 * @param model
	 * @param currentPage
	 * @param request
	 * @param schStartDate
	 * @param schEndDate
	 * @param deptFlow
	 * @param teacherName
	 * @param statueId
     * @param studentName
     * @return
     */
	@RequestMapping(value="attendanceSearch/{roleId}")
	public String attendanceSearch(Model model, Integer currentPage, @PathVariable String roleId, HttpServletRequest request,
								   String schStartDate, String schEndDate, String deptFlow, String teacherName, String statueId,
								   String studentName, String searchType, String kqType,  String orgFlow, String datas[], String baseFlag){
		//查询本基地的协同基地
		List<SysOrg> orgList=new ArrayList<>();
		SysOrg doctorOrg=new SysOrg();
		doctorOrg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		doctorOrg.setOrgName(GlobalContext.getCurrentUser().getOrgName());
		orgList=orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("JointOrgCount",orgList.size());
		orgList.add(0,doctorOrg);
		List<String> orgFlowList=new ArrayList<>();
		if(orgList!=null&&orgList.size()>0){
			for (SysOrg so:orgList) {
				orgFlowList.add(so.getOrgFlow());
			}
		}

		SysUser sysUser=GlobalContext.getCurrentUser();
		String searchFlow="";
		if(StringUtil.isBlank(orgFlow)){
			searchFlow=sysUser.getOrgFlow();
		}else{
			searchFlow=orgFlow;
		}
		model.addAttribute("searchFlow",searchFlow);
		List<SysDept> deptList = deptBiz.searchDeptByOrg(searchFlow);
		model.addAttribute("deptList",deptList);
		Map<String,Object> beMap=new HashMap<String,Object>();
		if(StringUtil.isBlank(schEndDate)){
			schEndDate=DateUtil.getCurrDate();
		}
		if(StringUtil.isBlank(schStartDate)){
			schStartDate=DateUtil.addDate(DateUtil.getCurrDate(),-30);
		}
		beMap.put("schStartDate",schStartDate);
		beMap.put("schEndDate",schEndDate);
		if(!"teacher".equals(roleId)){
			if(StringUtil.isNotBlank(orgFlow)){//平台优化11.17 二
				beMap.put("orgFlow",orgFlow);
			}else{
				beMap.put("orgFlowList",orgFlowList);
			}
		}
		if(StringUtil.isNotBlank(deptFlow)){
			beMap.put("deptFlow",deptFlow);
		}
        Object userListScope = getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE);
        if ((!com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(userListScope)) && (!com.pinde.core.common.GlobalConstant.USER_LIST_BASE.equals(userListScope))) {
			String teacherFlow=sysUser.getUserFlow();
			beMap.put("teacherFlow",teacherFlow);
		}
		if(StringUtil.isNotBlank(teacherName)){
			beMap.put("teacherName",teacherName);
		}
		if(StringUtil.isNotBlank(studentName)){
			beMap.put("studentName",studentName);
		}
		if("1".equals(statueId)||"0".equals(statueId)||"-1".equals(statueId)){
			beMap.put("statueId",statueId);
		}
		List<String>docTypeList=new ArrayList<String>();
		model.addAttribute("datas", datas);
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}else{
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i=0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		beMap.put("docTypeList",docTypeList);
		beMap.put("baseFlag", baseFlag);
		beMap.put("kqType", kqType);
        if (com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCAL.equals(roleId) || com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(roleId)) {
			String trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
			beMap.put("trainingSpeId", trainingSpeId);
		}
//		beMap.put("trainingTypeId",com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		List<JsResAttendanceExt> jsResAttendanceExts = new ArrayList<>();
		PageHelper.startPage(currentPage,getPageSize(request));
		jsResAttendanceExts=resAttendanceBiz.searchAttendanceList2(beMap);
		Map<String,List> attendanceDetailMap = new HashMap<String,List>();
		//用于查询签到详情
		List<String> jsResAttendanceFlows = new ArrayList<>();
		//用于外院科室查询
		List<String> sysDeptFlows = new ArrayList<>();
		//用于外院科室查询
		Map<String,String> deptOrgMap = new HashMap<>();
		for (JsResAttendanceExt jsResAttendanceExt : jsResAttendanceExts) {
			String jsResAttendanceFlow = jsResAttendanceExt.getJsresAttendance().getAttendanceFlow();
			if(StringUtil.isNotBlank(jsResAttendanceFlow)){
				jsResAttendanceFlows.add(jsResAttendanceFlow);
			}
			SysDept sysDept=jsResAttendanceExt.getSysDept();
			if(sysDept!=null)
			{
				sysDeptFlows.add(sysDept.getDeptFlow());
				deptOrgMap.put(sysDept.getDeptFlow(),sysDept.getOrgFlow());
			}
		}
		List<JsresAttendanceDetail> jsresAttendanceDetails = resAttendanceBiz.searchJsresAttendanceDetailByAttendanceFlows(jsResAttendanceFlows);
		if (jsresAttendanceDetails != null && jsresAttendanceDetails.size() > 0) {
			List<JsresAttendanceDetail> tempDetails = null;
			for(JsresAttendanceDetail temp :jsresAttendanceDetails){
				if(attendanceDetailMap.get(temp.getAttendanceFlow()) != null){
					attendanceDetailMap.get(temp.getAttendanceFlow()).add(temp);
				}else {
					tempDetails = new ArrayList<>();
					tempDetails.add(temp);
					attendanceDetailMap.put(temp.getAttendanceFlow(),tempDetails);
				}
			}
		}
		List<String> orgFlows = new ArrayList<>();
		List<SysDept> tempDepts = null;
		if(sysDeptFlows != null && sysDeptFlows.size() > 0){
			tempDepts = deptBiz.searchDeptByFlows(sysDeptFlows);
			if(tempDepts != null && tempDepts.size() > 0){
				for (SysDept temp : tempDepts) {
					if (!temp.getOrgFlow().equals(deptOrgMap.get(temp.getDeptFlow()))) {
						orgFlows.add(temp.getOrgFlow());
					}
				}
			}
		}

		List<SysOrg> tempOrgs = null;
		Map<String,String> orgNameMap = null;
		if(orgFlows != null && orgFlows.size() > 0){
			tempOrgs = orgBiz.searchOrgFlowIn(orgFlows);
			if(tempOrgs != null && tempOrgs.size() > 0){
				orgNameMap = new HashMap<>();
				for(SysOrg tempOrg:tempOrgs){
					orgNameMap.put(tempOrg.getOrgFlow(),tempOrg.getOrgName());
				}
			}
		}
		if(tempDepts != null && tempDepts.size() > 0){
			for (SysDept temp : tempDepts) {
				if (!temp.getOrgFlow().equals(deptOrgMap.get(temp.getDeptFlow()))) {
					for (JsResAttendanceExt jsResAttendanceExt : jsResAttendanceExts) {
						if (temp.getDeptFlow().equals(jsResAttendanceExt.getSysDept().getDeptFlow())) {
							jsResAttendanceExt.getSysDept().setDeptName(jsResAttendanceExt.getSysDept().getDeptName() + ("[" + orgNameMap.get(temp.getOrgFlow()) + "]"));
						}
					}
				}
			}
		}
		model.addAttribute("jsResAttendanceExts",jsResAttendanceExts);
		model.addAttribute("searchType",searchType);
		model.addAttribute("roleId",roleId);
		model.addAttribute("attendanceDetailMap",attendanceDetailMap);
		model.addAttribute("schEndDate",schEndDate);
		model.addAttribute("schStartDate",schStartDate);
		model.addAttribute("orgList",orgList);
		model.addAttribute("kqType", kqType);
		return "jsres/teacher/attendanceSearch";
	}

	/**
	 * 查看考勤信息 住院医师
	 * @param model
	 * @param currentPage
	 * @param request
	 * @param schStartDate
	 * @param schEndDate
	 * @param deptFlow
	 * @param teacherName
	 * @param statueId
	 * @param studentName
	 * @return
	 */
	@RequestMapping(value="attendanceSearchAcc/{roleId}")
	public String attendanceSearchAcc(Model model, Integer currentPage, @PathVariable String roleId, HttpServletRequest request,
								   String schStartDate, String schEndDate, String deptFlow, String teacherName, String statueId,
								   String studentName, String searchType, String orgFlow, String datas[], String baseFlag){
		//查询本基地的协同基地
		List<SysOrg> orgList=new ArrayList<>();
		SysOrg doctorOrg=new SysOrg();
		doctorOrg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		doctorOrg.setOrgName(GlobalContext.getCurrentUser().getOrgName());
		orgList=orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("JointOrgCount",orgList.size());
		orgList.add(0,doctorOrg);
		List<String> orgFlowList=new ArrayList<>();
		if(orgList!=null&&orgList.size()>0){
			for (SysOrg so:orgList) {
				orgFlowList.add(so.getOrgFlow());
			}
		}

		SysUser sysUser=GlobalContext.getCurrentUser();
		String searchFlow="";
		if(StringUtil.isBlank(orgFlow)){
			searchFlow=sysUser.getOrgFlow();
		}else{
			searchFlow=orgFlow;
		}
		model.addAttribute("searchFlow",searchFlow);
		List<SysDept> deptList = deptBiz.searchDeptByOrg(searchFlow);
		model.addAttribute("deptList",deptList);
		Map<String,Object> beMap=new HashMap<String,Object>();
		if(StringUtil.isBlank(schEndDate)){
			schEndDate=DateUtil.getCurrDate();
		}
		if(StringUtil.isBlank(schStartDate)){
			schStartDate=DateUtil.addDate(DateUtil.getCurrDate(),-30);
		}
		beMap.put("schStartDate",schStartDate);
		beMap.put("schEndDate",schEndDate);
		if(!"teacher".equals(roleId)){
			if(StringUtil.isNotBlank(orgFlow)){//平台优化11.17 二
				beMap.put("orgFlow",orgFlow);
			}else{
				beMap.put("orgFlowList",orgFlowList);
			}
		}
		if(StringUtil.isNotBlank(deptFlow)){
			beMap.put("deptFlow",deptFlow);
		}
        Object userListScope = getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE);
        if ((!com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(userListScope)) && (!com.pinde.core.common.GlobalConstant.USER_LIST_BASE.equals(userListScope))) {
			String teacherFlow=sysUser.getUserFlow();
			beMap.put("teacherFlow",teacherFlow);
		}
		if(StringUtil.isNotBlank(teacherName)){
			beMap.put("teacherName",teacherName);
		}
		if(StringUtil.isNotBlank(studentName)){
			beMap.put("studentName",studentName);
		}
		if("1".equals(statueId)||"0".equals(statueId)||"-1".equals(statueId)){
			beMap.put("statueId",statueId);
		}
		List<String>docTypeList=new ArrayList<String>();
		model.addAttribute("datas", datas);
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}else{
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i=0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		beMap.put("docTypeList",docTypeList);
		beMap.put("baseFlag", baseFlag);
        if (com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCAL.equals(roleId) || com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(roleId)) {
			String trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
			beMap.put("trainingSpeId", trainingSpeId);
		}
        beMap.put("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId());
		List<JsResAttendanceExt> jsResAttendanceExts = new ArrayList<>();
		PageHelper.startPage(currentPage,getPageSize(request));
		jsResAttendanceExts=resAttendanceBiz.searchAttendanceList2(beMap);
		Map<String,List> attendanceDetailMap = new HashMap<String,List>();
		//用于查询签到详情
		List<String> jsResAttendanceFlows = new ArrayList<>();
		//用于外院科室查询
		List<String> sysDeptFlows = new ArrayList<>();
		//用于外院科室查询
		Map<String,String> deptOrgMap = new HashMap<>();
		for (JsResAttendanceExt jsResAttendanceExt : jsResAttendanceExts) {
			String jsResAttendanceFlow = jsResAttendanceExt.getJsresAttendance().getAttendanceFlow();
			if(StringUtil.isNotBlank(jsResAttendanceFlow)){
				jsResAttendanceFlows.add(jsResAttendanceFlow);
			}
			SysDept sysDept=jsResAttendanceExt.getSysDept();
			if(sysDept!=null)
			{
				sysDeptFlows.add(sysDept.getDeptFlow());
				deptOrgMap.put(sysDept.getDeptFlow(),sysDept.getOrgFlow());
			}
		}
		List<JsresAttendanceDetail> jsresAttendanceDetails = resAttendanceBiz.searchJsresAttendanceDetailByAttendanceFlows(jsResAttendanceFlows);
		if (jsresAttendanceDetails != null && jsresAttendanceDetails.size() > 0) {
			List<JsresAttendanceDetail> tempDetails = null;
			for(JsresAttendanceDetail temp :jsresAttendanceDetails){
				if(attendanceDetailMap.get(temp.getAttendanceFlow()) != null){
					attendanceDetailMap.get(temp.getAttendanceFlow()).add(temp);
				}else {
					tempDetails = new ArrayList<>();
					tempDetails.add(temp);
					attendanceDetailMap.put(temp.getAttendanceFlow(),tempDetails);
				}
			}
		}
		List<String> orgFlows = new ArrayList<>();
		List<SysDept> tempDepts = null;
		if(sysDeptFlows != null && sysDeptFlows.size() > 0){
			tempDepts = deptBiz.searchDeptByFlows(sysDeptFlows);
			if(tempDepts != null && tempDepts.size() > 0){
				for (SysDept temp : tempDepts) {
					if (!temp.getOrgFlow().equals(deptOrgMap.get(temp.getDeptFlow()))) {
						orgFlows.add(temp.getOrgFlow());
					}
				}
			}
		}

		List<SysOrg> tempOrgs = null;
		Map<String,String> orgNameMap = null;
		if(orgFlows != null && orgFlows.size() > 0){
			tempOrgs = orgBiz.searchOrgFlowIn(orgFlows);
			if(tempOrgs != null && tempOrgs.size() > 0){
				orgNameMap = new HashMap<>();
				for(SysOrg tempOrg:tempOrgs){
					orgNameMap.put(tempOrg.getOrgFlow(),tempOrg.getOrgName());
				}
			}
		}
		if(tempDepts != null && tempDepts.size() > 0){
			for (SysDept temp : tempDepts) {
				if (!temp.getOrgFlow().equals(deptOrgMap.get(temp.getDeptFlow()))) {
					for (JsResAttendanceExt jsResAttendanceExt : jsResAttendanceExts) {
						if (temp.getDeptFlow().equals(jsResAttendanceExt.getSysDept().getDeptFlow())) {
							jsResAttendanceExt.getSysDept().setDeptName(jsResAttendanceExt.getSysDept().getDeptName() + ("[" + orgNameMap.get(temp.getOrgFlow()) + "]"));
						}
					}
				}
			}
		}
		model.addAttribute("jsResAttendanceExts",jsResAttendanceExts);
		model.addAttribute("searchType",searchType);
		model.addAttribute("roleId",roleId);
		model.addAttribute("attendanceDetailMap",attendanceDetailMap);
		model.addAttribute("schEndDate",schEndDate);
		model.addAttribute("schStartDate",schStartDate);
		model.addAttribute("orgList",orgList);
		return "jsres/teacher/attendanceSearchAcc";
	}

	@RequestMapping(value="/attendanceSearchTab/{roleId}")
	public String attendanceSearchTab(Model model, Integer currentPage, @PathVariable String roleId,
									  HttpServletRequest request, String schStartDate, String schEndDate,
									  String deptFlow, String trainingTypeId,String sessionNumber, String datas[],
									  String trainingSpeId, String studentName, String searchType, String orgFlow, String baseFlag){
			//查询本基地的协同基地
		List<SysOrg> orgList=new ArrayList<>();
		SysOrg doctorOrg=new SysOrg();
		doctorOrg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		doctorOrg.setOrgName(GlobalContext.getCurrentUser().getOrgName());
        if (!com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleId) && !com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleId)) {
			orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			model.addAttribute("JointOrgCount", orgList.size());
		}
		orgList.add(0,doctorOrg);
		List<String> orgFlowList=new ArrayList<>();
		if(orgList!=null&&orgList.size()>0){
			for (SysOrg so:orgList) {
				orgFlowList.add(so.getOrgFlow());
			}
		}

		SysUser sysUser=GlobalContext.getCurrentUser();
		String searchFlow="";
		if(StringUtil.isBlank(orgFlow)){
			searchFlow=sysUser.getOrgFlow();
		}else{
			searchFlow=orgFlow;
		}
		model.addAttribute("searchFlow",searchFlow);
		List<SysDept> deptList = new ArrayList<>();
		Map<String,Object> beMap=new HashMap<String,Object>();
        if (!com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleId) && !com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleId)) {
			deptList = deptBiz.searchDeptByOrg(searchFlow);
			if(StringUtil.isNotBlank(deptFlow)){
				beMap.put("deptFlow",deptFlow);
			}
		} else {
			List<SysUserDept> userDeptList = userBiz.getUserDept(sysUser);
			if(StringUtil.isNotBlank(deptFlow)){
				beMap.put("deptFlow",deptFlow);
			} else {
				if(CollectionUtils.isNotEmpty(userDeptList)){
					beMap.put("deptFlows",userDeptList.stream().map(SysUserDept::getDeptFlow).collect(Collectors.toList()));
				}else{
					beMap.put("deptFlow", GlobalContext.getCurrentUser().getDeptFlow());
				}
			}
			for (SysUserDept sysUserDept : userDeptList) {
				SysDept sysDept = deptBiz.readSysDept(sysUserDept.getDeptFlow());
				deptList.add(sysDept);
			}
		}
		model.addAttribute("deptList",deptList);
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleId)) {
			beMap.put("teacherFlow", sysUser.getUserFlow());
		}
		if(StringUtil.isBlank(schEndDate)){
			schEndDate=DateUtil.getCurrDate();
		}
		if(StringUtil.isBlank(schStartDate)){
			schStartDate=DateUtil.getFirstDayOfMonth();
		}

		List<String>docTypeList=new ArrayList<String>();
		model.addAttribute("datas", datas);
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}else{
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i=0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		beMap.put("docTypeList",docTypeList);
		beMap.put("schStartDate",schStartDate);
		beMap.put("schEndDate",schEndDate);
		beMap.put("studentName",studentName);
		beMap.put("trainingTypeId", trainingTypeId);
		beMap.put("trainingSpeId",trainingSpeId);
		beMap.put("sessionNumber",sessionNumber);
		if(!"teacher".equals(roleId)){
			if(StringUtil.isNotBlank(orgFlow)){//平台优化11.17 二
				beMap.put("orgFlow",orgFlow);
			}else{
				beMap.put("orgFlowList",orgFlowList);
			}
		}
		if(StringUtil.isNotBlank(baseFlag)){
			beMap.put("baseFlag",baseFlag);
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCAL.equals(roleId) || com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(roleId)) {
			trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
			beMap.put("trainingSpeId", trainingSpeId);
		}
		System.out.println("baseFlag-------------:"+baseFlag);
		List<Map<String,String>> jsResAttendanceExts = new ArrayList<>();
		PageHelper.startPage(currentPage,getPageSize(request));
        if (!com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleId) && !com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleId)) {
			jsResAttendanceExts=resAttendanceBiz.attendanceList2(beMap);
		} else {
			jsResAttendanceExts=resAttendanceBiz.attendanceList3(beMap);
		}
		model.addAttribute("jsResAttendanceExts",jsResAttendanceExts);
		model.addAttribute("roleId",roleId);
		model.addAttribute("schEndDate",schEndDate);
		model.addAttribute("schStartDate",schStartDate);
		model.addAttribute("orgList",orgList);
		model.addAttribute("baseFlag",baseFlag);
		return "/jsres/attendance/hospital/attendanceList";
	}

	@RequestMapping(value="/attendanceSearchTabAcc/{roleId}")
	public String attendanceSearchTabAcc(Model model, Integer currentPage, @PathVariable String roleId,
									  HttpServletRequest request, String schStartDate, String schEndDate,
									  String deptFlow, String trainingTypeId,String sessionNumber, String datas[],
									  String trainingSpeId, String studentName, String searchType, String orgFlow, String baseFlag){
		//查询本基地的协同基地
		List<SysOrg> orgList=new ArrayList<>();
		SysOrg doctorOrg=new SysOrg();
		doctorOrg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		doctorOrg.setOrgName(GlobalContext.getCurrentUser().getOrgName());
		orgList=orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("JointOrgCount",orgList.size());
		orgList.add(0,doctorOrg);
		List<String> orgFlowList=new ArrayList<>();
		if(orgList!=null&&orgList.size()>0){
			for (SysOrg so:orgList) {
				orgFlowList.add(so.getOrgFlow());
			}
		}

		SysUser sysUser=GlobalContext.getCurrentUser();
		String searchFlow="";
		if(StringUtil.isBlank(orgFlow)){
			searchFlow=sysUser.getOrgFlow();
		}else{
			searchFlow=orgFlow;
		}
		model.addAttribute("searchFlow",searchFlow);
		List<SysDept> deptList = deptBiz.searchDeptByOrg(searchFlow);
		model.addAttribute("deptList",deptList);
		Map<String,Object> beMap=new HashMap<String,Object>();
		if(StringUtil.isBlank(schEndDate)){
			schEndDate=DateUtil.getLastDateOfCurrMonth();
		}
		if(StringUtil.isBlank(schStartDate)){
			schStartDate=DateUtil.getFirstDayOfMonth();
		}

		List<String>docTypeList=new ArrayList<String>();
		model.addAttribute("datas", datas);
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}else{
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i=0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		beMap.put("docTypeList",docTypeList);
		beMap.put("schStartDate",schStartDate);
		beMap.put("schEndDate",schEndDate);
		beMap.put("studentName",studentName);
        beMap.put("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId());
		beMap.put("trainingSpeId",trainingSpeId);
		beMap.put("sessionNumber",sessionNumber);
		if(!"teacher".equals(roleId)){
			if(StringUtil.isNotBlank(orgFlow)){//平台优化11.17 二
				beMap.put("orgFlow",orgFlow);
			}else{
				beMap.put("orgFlowList",orgFlowList);
			}
		}
		if(StringUtil.isNotBlank(deptFlow)){
			beMap.put("deptFlow",deptFlow);
		}
		if(StringUtil.isNotBlank(baseFlag)){
			beMap.put("baseFlag",baseFlag);
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCAL.equals(roleId) || com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(roleId)) {
			trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
			beMap.put("trainingSpeId", trainingSpeId);
		}
		System.out.println("baseFlag-------------:"+baseFlag);
		List<Map<String,String>> jsResAttendanceExts = new ArrayList<>();
		PageHelper.startPage(currentPage,getPageSize(request));
		jsResAttendanceExts=resAttendanceBiz.attendanceList2(beMap);
		model.addAttribute("jsResAttendanceExts",jsResAttendanceExts);
		model.addAttribute("roleId",roleId);
		model.addAttribute("schEndDate",schEndDate);
		model.addAttribute("schStartDate",schStartDate);
		model.addAttribute("orgList",orgList);
		model.addAttribute("baseFlag",baseFlag);
		return "/jsres/attendance/hospital/attendanceListAcc";
	}


	@RequestMapping(value = "/exportAttendanceTab/{roleId}")
	public void exportAttendanceTab(@PathVariable String roleId,
									HttpServletRequest request, String schStartDate, String schEndDate,
									String deptFlow, String trainingTypeId,String sessionNumber,String datas[],
									String trainingSpeId, String studentName,String orgFlow, HttpServletResponse response)throws Exception{
		//查询本基地的协同基地
		List<SysOrg> orgList=new ArrayList<>();
		SysOrg doctorOrg=new SysOrg();
		doctorOrg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		doctorOrg.setOrgName(GlobalContext.getCurrentUser().getOrgName());
        if (!com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleId) && !com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleId)) {
			orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		}
		orgList.add(0,doctorOrg);
		List<String> orgFlowList=new ArrayList<>();
		if(orgList!=null&&orgList.size()>0){
			for (SysOrg so:orgList) {
				orgFlowList.add(so.getOrgFlow());
			}
		}
		Map<String,Object> beMap=new HashMap<String,Object>();
		if(StringUtil.isBlank(schEndDate)){
			schEndDate=DateUtil.getLastDateOfCurrMonth();
		}
		if(StringUtil.isBlank(schStartDate)){
			schStartDate=DateUtil.getFirstDayOfMonth();
		}

		List<String>docTypeList=new ArrayList<String>();

		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		beMap.put("docTypeList",docTypeList);
		beMap.put("schStartDate",schStartDate);
		beMap.put("schEndDate",schEndDate);
		beMap.put("studentName",studentName);
		beMap.put("trainingTypeId",trainingTypeId);
		beMap.put("trainingSpeId",trainingSpeId);
		beMap.put("sessionNumber",sessionNumber);
			if(StringUtil.isNotBlank(orgFlow)){//平台优化11.17 二
				beMap.put("orgFlow",orgFlow);
			}else{
				beMap.put("orgFlowList",orgFlowList);
			}
		List<SysDept> deptList = new ArrayList<>();
		SysUser sysUser=GlobalContext.getCurrentUser();
		String searchFlow="";
		if(StringUtil.isBlank(orgFlow)){
			searchFlow=sysUser.getOrgFlow();
		}else{
			searchFlow=orgFlow;
		}
        if (!com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleId) && !com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleId)) {
			deptList = deptBiz.searchDeptByOrg(searchFlow);
			if(StringUtil.isNotBlank(deptFlow)){
				beMap.put("deptFlow",deptFlow);
			}
		} else {
			List<SysUserDept> userDeptList = userBiz.getUserDept(sysUser);
			if(StringUtil.isNotBlank(deptFlow)){
				beMap.put("deptFlow",deptFlow);
			} else {
				if(CollectionUtils.isNotEmpty(userDeptList)){
					beMap.put("deptFlows",userDeptList.stream().map(SysUserDept::getDeptFlow).collect(Collectors.toList()));
				}else{
					beMap.put("deptFlow", GlobalContext.getCurrentUser().getDeptFlow());
				}
			}
			for (SysUserDept sysUserDept : userDeptList) {
				SysDept sysDept = deptBiz.readSysDept(sysUserDept.getDeptFlow());
				deptList.add(sysDept);
			}
		}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleId)) {
			beMap.put("teacherFlow", sysUser.getUserFlow());
		}
		List<Map<String,String>> doctors = new ArrayList<>();
		List<String> sheetList= TimeUtil.getMonthsByTwoMonth(schStartDate.substring(0,7),schEndDate.substring(0,7));
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();

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

//		List<Map<String,String>> doctors=resAttendanceBiz.attendanceList2(beMap);

		if(sheetList!=null&&sheetList.size()>0)
		{
			Map<Integer, Integer> columnWidth = new HashMap<>();
			for(String month:sheetList)
			{
				HSSFSheet sheet = wb.createSheet(month);
				List<String> days=TimeUtil.findDates2(month);
				String[] titles = new String[]{
						"姓名",
						"年级",
						"培训类别",
						"专业",
						"轮转科室",
						"出勤天数",
						"缺勤天数",
						"轮休天数"
				};
				String[] keys = new String[]{
						"userName",
						"sessionNumber",
						"trainingTypeName",
						"trainingSpeName",
						"deptName",
						"cqNum",
						"qqNum",
						"lxNum"
				};
				int rowNum=0;
				int cellNum=0;
				HSSFRow headRow = sheet.createRow(rowNum++);//第一行

				HSSFCell cellTitle = null;
				for (int i = 0; i < titles.length; i++) {
					cellTitle = headRow.createCell(cellNum++);
					cellTitle.setCellValue(titles[i]);
					cellTitle.setCellStyle(styleCenter);
					setColumnWidth(titles[i].getBytes().length, cellNum-1, columnWidth);
				}
				if(days!=null)
				{
					for(String day:days)
					{
						if(day.compareTo(schStartDate)>=0&&day.compareTo(schEndDate)<=0)
						{
							cellTitle = headRow.createCell(cellNum++);
							cellTitle.setCellValue(day);
							cellTitle.setCellStyle(styleCenter);
							setColumnWidth(day.getBytes().length, cellNum-1, columnWidth);
						}
					}
				}
				beMap.put("month",month);
				beMap.put("days",days);
//				beMap.put("graduationYear",DateUtil.getYear());
                if (!com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleId) && !com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleId)) {
					doctors=resAttendanceBiz.exportaAttendanceList3(beMap);
				} else {
					doctors=resAttendanceBiz.exportaAttendanceList4(beMap);
				}
				List<Map<String,String>> jsResAttendanceExts=resAttendanceBiz.attendanceList2(beMap);
				for (Map<String, String> doctor : doctors) {
					String docFlow=doctor.get("userFlow");
					for (Map<String, String> jsResAttendanceExt : jsResAttendanceExts) {
						String userFlow = jsResAttendanceExt.get("userFlow");
						if (userFlow.equals(docFlow)){
							doctor.put("cqNum",String.valueOf(jsResAttendanceExt.get("cqNum")));
							doctor.put("qqNum",String.valueOf(jsResAttendanceExt.get("qqNum")));
							doctor.put("lxNum",String.valueOf(jsResAttendanceExt.get("lxNum")));
						}
						continue;
					}
				}
				if(doctors!=null)
				{
					for(Map<String,String> doc:doctors)
					{
						String docFlow=doc.get("userFlow");
						headRow = sheet.createRow(rowNum++);//第一行
						cellNum=0;

						for (int i = 0; i < keys.length; i++) {
							String value=doc.get(keys[i]);
							if(StringUtil.isBlank(value))
							{
								value="";
							}
							cellTitle = headRow.createCell(cellNum++);
							cellTitle.setCellValue(value);
							cellTitle.setCellStyle(styleCenter);
							setColumnWidth(value.getBytes().length, cellNum-1, columnWidth);
						}

						if(days!=null)
						{
							for(String day:days)
							{
								if(day.compareTo(schStartDate)>=0&&day.compareTo(schEndDate)<=0) {
									String value = "√";
									String attendStatus = doc.get(day);
									if (StringUtil.isNotBlank(attendStatus)) {
										if ("1".equals(attendStatus)) {
											value = "√";
										}else if ("-1".equals(attendStatus)) {
											value = "-";
										}
										else if ("0".equals(attendStatus)) {
											value = "×";
										}
									}
									cellTitle = headRow.createCell(cellNum++);
									cellTitle.setCellValue(value);
									cellTitle.setCellStyle(styleCenter);
									setColumnWidth(value.getBytes().length, cellNum - 1, columnWidth);
								}
							}
						}
					}
				}

				Set<Integer> columnkeys = columnWidth.keySet();
				for (Integer key : columnkeys) {
					int width = columnWidth.get(key);
					sheet.setColumnWidth(key, width * 2 * 156>255*256?70*256:width * 2 * 156);
				}
			}
		}
		String fileName = "学员考勤统计.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	/**
	 * 带教老师修改出勤状态或添加备注
	 * @param attendanceFlow
	 * @param statueId
	 * @param statueName
	 * @param doctorFlow
	 * @param attendDate
	 * @param remarks
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/modifyAttendance")
	public @ResponseBody String modifyAttendance(String attendanceFlow, String statueId, String statueName, String doctorFlow, String attendDate, String remarks)throws Exception {
		JsresAttendance jsresAttendance = null;
		statueName = java.net.URLDecoder.decode(statueName,"UTF-8");
		remarks = java.net.URLDecoder.decode(remarks,"UTF-8");
		SysUser sysUser=GlobalContext.getCurrentUser();
		SysUser doctor = resAttendanceBiz.searchSysUserByUserFlow(doctorFlow);
		if(StringUtil.isNotBlank(attendanceFlow)){
			jsresAttendance = resAttendanceBiz.searchJsresAttendanceByAttendanceFlow(attendanceFlow);
			if(jsresAttendance!=null){
				if(!"Auditing".equals(jsresAttendance.getAuditStatusId()))
				{
					return "该签到记录已经被审核，无法修改签到状态，请刷新列表！";
				}
				jsresAttendance.setAttendStatus(statueId);
				if(StringUtil.isNotBlank(statueName)){
					jsresAttendance.setAttendStatusName(statueName);
				}
				if(StringUtil.isNotBlank(remarks)){
					jsresAttendance.setTeacherRemarks(remarks);
				}
			}
		}else {

			jsresAttendance = new JsresAttendance();
			JsresAttendance attendance=resAttendanceBiz.getJsresAttendance(attendDate,doctorFlow);
			if(attendance!=null) {
				if(!"Auditing".equals(attendance.getAuditStatusId()))
				{
					return "该签到记录已经被审核，无法修改签到状态，请刷新列表！";
				}
				jsresAttendance.setAttendanceFlow(attendance.getAttendanceFlow());
			}
			jsresAttendance.setDoctorFlow(doctorFlow);
			jsresAttendance.setDoctorName(doctor.getUserName());
			jsresAttendance.setAttendDate(attendDate);
			jsresAttendance.setTeacherFlow(sysUser.getUserFlow());
			jsresAttendance.setTeacherName(sysUser.getUserName());
			jsresAttendance.setAttendStatus(statueId);
			if(StringUtil.isNotBlank(statueName)){
				jsresAttendance.setAttendStatusName(statueName);
			}
			if(StringUtil.isNotBlank(remarks)){
				jsresAttendance.setTeacherRemarks(remarks);
			}
		}
		resAttendanceBiz.saveJsresAttendance(jsresAttendance);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}

	/**
	 * 带教老师退回一键审核出勤状态
	 * @param attendanceFlow
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/returnAttendance")
	public @ResponseBody String returnAttendance(String attendanceFlow)throws Exception {
		JsresAttendance jsresAttendance = null;
		if(StringUtil.isNotBlank(attendanceFlow)){
			jsresAttendance = resAttendanceBiz.searchJsresAttendanceByAttendanceFlow(attendanceFlow);
			if(jsresAttendance!=null){
				jsresAttendance.setAuditStatusId("Auditing");
			}
		}
		resAttendanceBiz.saveJsresAttendance(jsresAttendance);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}

	/**
	 * 带教老师一键通过
	 * @param attendanceFlowsStr
	 * @param doctorStr
	 * @param attendDatesStr
     * @return
     */
	@RequestMapping(value = "/modifySelected")
	public @ResponseBody String modifySelected(String attendanceFlowsStr,String doctorStr,String attendDatesStr){
		SysUser sysUser=GlobalContext.getCurrentUser();
		String[] attendanceFlows = attendanceFlowsStr.split(",");
		String[] doctorFlows = doctorStr.split(",");
		String[] attendDates = attendDatesStr.split(",");
		for (int i = 0; i < doctorFlows.length; i++) {
			JsresAttendance attendance =null;
			String attendanceFlow ="";
			if(i<attendanceFlows.length){
				attendanceFlow = attendanceFlows[i];
			}
			if(attendanceFlow.length() != 0)
			{
				attendance = resAttendanceBiz.searchJsresAttendanceByAttendanceFlow(attendanceFlow);

			}else {
				String doctorFlow = doctorFlows[i];
				String attendDate = attendDates[i];
				attendance = new JsresAttendance();
				JsresAttendance jsresAttendance=resAttendanceBiz.getJsresAttendance(attendDate,doctorFlow);
				if(jsresAttendance!=null)
					attendance.setAttendanceFlow(jsresAttendance.getAttendanceFlow());
				SysUser doctor = resAttendanceBiz.searchSysUserByUserFlow(doctorFlow);
				attendance.setDoctorFlow(doctorFlow);
				attendance.setDoctorName(doctor.getUserName());
				attendance.setAttendDate(attendDate);
				attendance.setTeacherFlow(sysUser.getUserFlow());
				attendance.setTeacherName(sysUser.getUserName());
				attendance.setAttendStatus("0");
				attendance.setAttendStatusName("缺勤");
			}
			attendance.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
			attendance.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
			attendance.setAuditTime(DateUtil.getCurrDateTime());
			attendance.setAuditUserFlow(sysUser.getUserFlow());
			attendance.setAuditUserName(sysUser.getUserName());
			resAttendanceBiz.saveJsresAttendance(attendance);
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}

	/**
	 * 导出考勤表  住院医师
	 * @param request
	 * @param response
	 * @param schStartDate
	 * @param schEndDate
	 * @param deptFlow
	 * @param teacherName
	 * @param statueId
     * @param studentName
     * @throws Exception
     */
	@RequestMapping(value = "/exportAttendance")
	public void exportAttendance(HttpServletRequest request, HttpServletResponse response,String schStartDate, String baseFlag,
								 String schEndDate, String deptFlow, String teacherName,String datas[],
								 String statueId, String studentName,String roleId,String orgFlow)throws Exception{
		SysUser sysUser=GlobalContext.getCurrentUser();
		Map<String,Object> beMap=new HashMap<String,Object>();
		beMap.put("schStartDate",schStartDate);
		beMap.put("schEndDate",schEndDate);
		//查询本基地的协同基地
		List<SysOrg> orgList=new ArrayList<>();
		SysOrg doctorOrg=new SysOrg();
		doctorOrg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		doctorOrg.setOrgName(GlobalContext.getCurrentUser().getOrgName());
		orgList=orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		orgList.add(0,doctorOrg);
		List<String> orgFlowList=new ArrayList<>();
		if(orgList!=null&&orgList.size()>0){
			for (SysOrg so:orgList) {
				orgFlowList.add(so.getOrgFlow());
			}
		}
		if(!"teacher".equals(roleId)){
			if(StringUtil.isNotBlank(orgFlow)){//平台优化11.17 二
				beMap.put("orgFlow",orgFlow);
			}else{
				beMap.put("orgFlowList",orgFlowList);
			}
		}
		if(StringUtil.isNotBlank(deptFlow)){
			beMap.put("deptFlow",deptFlow);
		}
        Object userListScope = getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE);
        if ((!com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(userListScope)) && (!com.pinde.core.common.GlobalConstant.USER_LIST_BASE.equals(userListScope))) {
			String teacherFlow=sysUser.getUserFlow();
			beMap.put("teacherFlow",teacherFlow);
		}
		if(StringUtil.isNotBlank(teacherName)){
			beMap.put("teacherName",teacherName);
		}
		if(StringUtil.isNotBlank(studentName)){
			beMap.put("studentName",studentName);
		}
		List<String>docTypeList=new ArrayList<String>();

		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		beMap.put("docTypeList",docTypeList);
		if("1".equals(statueId)||"0".equals(statueId)||"-1".equals(statueId)){
			beMap.put("statueId",statueId);
		}
		List<JsResAttendanceExt> jsResAttendanceExts = new ArrayList<>();
		beMap.put("baseFlag",baseFlag);
//		beMap.put("trainingTypeId",com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		jsResAttendanceExts=resAttendanceBiz.searchAttendanceList2(beMap);
		Map<String,List> attendanceDetailMap = new HashMap<String,List>();
		//用于查询签到详情
		List<String> jsResAttendanceFlows = new ArrayList<>();
		//用于外院科室查询
		List<String> sysDeptFlows = new ArrayList<>();
		//用于外院科室查询
		Map<String,String> deptOrgMap = new HashMap<>();
		for (JsResAttendanceExt jsResAttendanceExt : jsResAttendanceExts) {
			String jsResAttendanceFlow = jsResAttendanceExt.getJsresAttendance().getAttendanceFlow();
			if(StringUtil.isNotBlank(jsResAttendanceFlow)){
				jsResAttendanceFlows.add(jsResAttendanceFlow);
			}
			SysDept sysDept=jsResAttendanceExt.getSysDept();
			if(sysDept!=null)
			{
				sysDeptFlows.add(sysDept.getDeptFlow());
				deptOrgMap.put(sysDept.getDeptFlow(),sysDept.getOrgFlow());
			}
		}
		//去重
		LinkedHashSet<String> hashSet = new LinkedHashSet<>(sysDeptFlows);
		ArrayList<String> listWithoutDuplicates = new ArrayList<>(hashSet);
		List<JsresAttendanceDetail> jsresAttendanceDetails = resAttendanceBiz.searchJsresAttendanceDetailByAttendanceFlows(jsResAttendanceFlows);
		if (jsresAttendanceDetails != null && jsresAttendanceDetails.size() > 0) {
			List<JsresAttendanceDetail> tempDetails = null;
			for(JsresAttendanceDetail temp :jsresAttendanceDetails){
				if(attendanceDetailMap.get(temp.getAttendanceFlow()) != null){
					attendanceDetailMap.get(temp.getAttendanceFlow()).add(temp);
				}else {
					tempDetails = new ArrayList<>();
					tempDetails.add(temp);
					attendanceDetailMap.put(temp.getAttendanceFlow(),tempDetails);
				}
			}
		}
		List<String> orgFlows = new ArrayList<>();
		List<SysDept> tempDepts = null;
		if(listWithoutDuplicates != null && listWithoutDuplicates.size() > 0){
			tempDepts = deptBiz.searchDeptByFlows(listWithoutDuplicates);
			if(tempDepts != null && tempDepts.size() > 0){
				for (SysDept temp : tempDepts) {
					if (!temp.getOrgFlow().equals(deptOrgMap.get(temp.getDeptFlow()))) {
						orgFlows.add(temp.getOrgFlow());
					}
				}
			}
		}

		List<SysOrg> tempOrgs = null;
		Map<String,String> orgNameMap = null;
		if(orgFlows != null && orgFlows.size() > 0){
			tempOrgs = orgBiz.searchOrgFlowIn(orgFlows);
			if(tempOrgs != null && tempOrgs.size() > 0){
				orgNameMap = new HashMap<>();
				for(SysOrg tempOrg:tempOrgs){
					orgNameMap.put(tempOrg.getOrgFlow(),tempOrg.getOrgName());
				}
			}
		}
		if(tempDepts != null && tempDepts.size() > 0){
			for (SysDept temp : tempDepts) {
				if (!temp.getOrgFlow().equals(deptOrgMap.get(temp.getDeptFlow()))) {
					for (JsResAttendanceExt jsResAttendanceExt : jsResAttendanceExts) {
						if (temp.getDeptFlow().equals(jsResAttendanceExt.getSysDept().getDeptFlow())) {
							jsResAttendanceExt.getSysDept().setDeptName(jsResAttendanceExt.getSysDept().getDeptName() + ("[" + orgNameMap.get(temp.getOrgFlow()) + "]"));
						}
					}
				}
			}
		}
		String[] headLines = null;
		headLines = new String[]{
				"学员考勤记录表"
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
		cellOne.setCellValue("学员考勤记录表");

		HSSFRow rowTwo = sheet.createRow(1);//第二行
		String[] titles = new String[]{
				"编号",
				"日期",
				"姓名",
				"科室",
				"带教老师",
				"考勤记录",
				"出勤状态",
				"备注",
				"学员备注"
		};
		String[] teacherTitles = new String[]{
				"编号",
				"日期",
				"姓名",
				"考勤记录",
				"出勤状态",
				"备注",
				"学员备注"
		};
		if("teacher".equals(roleId)){
			titles = teacherTitles;
		}
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowTwo.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 2 * 256);
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE))) {
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));//合并单元格
		}else {
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));//合并单元格
		}
		Map<Integer, Integer> columnWidth = new HashMap<>();
		int rowNum = 2;
		int inDocTotal=0;
		int outDocTotal=0;
		String[] resultList = null;
		String[] teacherResultList = null;
		if (jsResAttendanceExts != null && !jsResAttendanceExts.isEmpty()) {
			for (int i = 0; i < jsResAttendanceExts.size(); i++, rowNum++) {
				String attendanceDetailRecords = "";
				String attendanceSerfRemarks = "";
				List<JsresAttendanceDetail> newDetails =null;
				newDetails=attendanceDetailMap.get(jsResAttendanceExts.get(i).getJsresAttendance().getAttendanceFlow());
				if(newDetails != null && !jsResAttendanceExts.isEmpty()){
					for (JsresAttendanceDetail detail: newDetails) {
						if(detail.getAttendanceFlow().equals(jsResAttendanceExts.get(i).getJsresAttendance().getAttendanceFlow())){
							if(StringUtil.isNotBlank(detail.getAttendTime())){
								if(StringUtil.isNotBlank(attendanceDetailRecords))
									attendanceDetailRecords += "\n"+detail.getAttendTime()+"  "+detail.getAttendLocal();
								if(StringUtil.isBlank(attendanceDetailRecords))
									attendanceDetailRecords += detail.getAttendTime()+"  "+detail.getAttendLocal();

								if("teacher".equals(roleId)) {
									if(StringUtil.isNotBlank(detail.getAttendTime()+"  "+detail.getAttendLocal()))
										setColumnWidth((detail.getAttendTime()+"  "+detail.getAttendLocal()).getBytes().length, 3, columnWidth);
								}else {
									if(StringUtil.isNotBlank(detail.getAttendTime()+"  "+detail.getAttendLocal()))
										setColumnWidth((detail.getAttendTime()+"  "+detail.getAttendLocal()).getBytes().length, 5, columnWidth);
								}
							}
							if(StringUtil.isNotBlank(detail.getSelfRemarks())){
								if(StringUtil.isNotBlank(attendanceSerfRemarks))
									attendanceSerfRemarks += "\n"+detail.getAttendTime() +" :" +detail.getSelfRemarks()+"  ";
								if(StringUtil.isBlank(attendanceSerfRemarks))
									attendanceSerfRemarks += detail.getAttendTime() +" :" +detail.getSelfRemarks()+"  ";
							}
						}
					}
				}else {

				}
				if(StringUtil.isBlank(attendanceDetailRecords.trim())){
					attendanceDetailRecords="暂无签到记录！";
				}
				if(StringUtil.isBlank(attendanceSerfRemarks.trim())){
					attendanceSerfRemarks="暂无备注！";
				}
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行
				String statuesName = jsResAttendanceExts.get(i).getJsresAttendance().getAttendStatusName();
				if(StringUtil.isBlank(statuesName)){
					statuesName="待审核";
				}
				resultList = new String[]{
						i+1+"",
						jsResAttendanceExts.get(i).getJsresAttendance().getAttendDate(),
						jsResAttendanceExts.get(i).getUserName(),
						jsResAttendanceExts.get(i).getSysDept().getDeptName(),
						jsResAttendanceExts.get(i).getJsresAttendance().getTeacherName(),
						attendanceDetailRecords,
						statuesName,
						jsResAttendanceExts.get(i).getJsresAttendance().getTeacherRemarks(),
						attendanceSerfRemarks
				};
				teacherResultList = new String[]{
						i+1+"",
						jsResAttendanceExts.get(i).getJsresAttendance().getAttendDate(),
						jsResAttendanceExts.get(i).getUserName(),
						attendanceDetailRecords,
						statuesName,
						jsResAttendanceExts.get(i).getJsresAttendance().getTeacherRemarks(),
						attendanceSerfRemarks
				};
				if("teacher".equals(roleId)){
					resultList = teacherResultList;
				}
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					if("teacher".equals(roleId)&&j==3||!"teacher".equals(roleId)&&j==5) {
						cellFirst.setCellStyle(styleLeft);
					}else {
						cellFirst.setCellStyle(styleCenter);
					}
					cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
					if(StringUtil.isNotBlank(resultList[j]))
						//宽度自适应
						setColumnWidth(resultList[j].toString().getBytes().length, j, columnWidth);
				}

			}
		}
		Set<Integer> keys = columnWidth.keySet();
		for (Integer key : keys) {
			int width = columnWidth.get(key);
			sheet.setColumnWidth(key, width * 2 * 256>255*256?70*256:width * 2 * 256);
		}
		String fileName = "学员考勤记录表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	/**
	 * 导出考勤表  助理全科
	 * @param request
	 * @param response
	 * @param schStartDate
	 * @param schEndDate
	 * @param deptFlow
	 * @param teacherName
	 * @param statueId
	 * @param studentName
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportAttendanceAcc")
	public void exportAttendanceAcc(HttpServletRequest request, HttpServletResponse response,String schStartDate, String baseFlag,
								 String schEndDate, String deptFlow, String teacherName,String datas[],
								 String statueId, String studentName,String roleId,String orgFlow)throws Exception{
		SysUser sysUser=GlobalContext.getCurrentUser();
		Map<String,Object> beMap=new HashMap<String,Object>();
		beMap.put("schStartDate",schStartDate);
		beMap.put("schEndDate",schEndDate);
		//查询本基地的协同基地
		List<SysOrg> orgList=new ArrayList<>();
		SysOrg doctorOrg=new SysOrg();
		doctorOrg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		doctorOrg.setOrgName(GlobalContext.getCurrentUser().getOrgName());
		orgList=orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		orgList.add(0,doctorOrg);
		List<String> orgFlowList=new ArrayList<>();
		if(orgList!=null&&orgList.size()>0){
			for (SysOrg so:orgList) {
				orgFlowList.add(so.getOrgFlow());
			}
		}
		if(!"teacher".equals(roleId)){
			if(StringUtil.isNotBlank(orgFlow)){//平台优化11.17 二
				beMap.put("orgFlow",orgFlow);
			}else{
				beMap.put("orgFlowList",orgFlowList);
			}
		}
		if(StringUtil.isNotBlank(deptFlow)){
			beMap.put("deptFlow",deptFlow);
		}
        Object userListScope = getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE);
        if ((!com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(userListScope)) && (!com.pinde.core.common.GlobalConstant.USER_LIST_BASE.equals(userListScope))) {
			String teacherFlow=sysUser.getUserFlow();
			beMap.put("teacherFlow",teacherFlow);
		}
		if(StringUtil.isNotBlank(teacherName)){
			beMap.put("teacherName",teacherName);
		}
		if(StringUtil.isNotBlank(studentName)){
			beMap.put("studentName",studentName);
		}
		List<String>docTypeList=new ArrayList<String>();

		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		beMap.put("docTypeList",docTypeList);
		if("1".equals(statueId)||"0".equals(statueId)||"-1".equals(statueId)){
			beMap.put("statueId",statueId);
		}
		List<JsResAttendanceExt> jsResAttendanceExts = new ArrayList<>();
		beMap.put("baseFlag",baseFlag);
        beMap.put("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId());
		jsResAttendanceExts=resAttendanceBiz.searchAttendanceList2(beMap);
		Map<String,List> attendanceDetailMap = new HashMap<String,List>();
		//用于查询签到详情
		List<String> jsResAttendanceFlows = new ArrayList<>();
		//用于外院科室查询
		List<String> sysDeptFlows = new ArrayList<>();
		//用于外院科室查询
		Map<String,String> deptOrgMap = new HashMap<>();
		for (JsResAttendanceExt jsResAttendanceExt : jsResAttendanceExts) {
			String jsResAttendanceFlow = jsResAttendanceExt.getJsresAttendance().getAttendanceFlow();
			if(StringUtil.isNotBlank(jsResAttendanceFlow)){
				jsResAttendanceFlows.add(jsResAttendanceFlow);
			}
			SysDept sysDept=jsResAttendanceExt.getSysDept();
			if(sysDept!=null)
			{
				sysDeptFlows.add(sysDept.getDeptFlow());
				deptOrgMap.put(sysDept.getDeptFlow(),sysDept.getOrgFlow());
			}
		}
		//去重
		LinkedHashSet<String> hashSet = new LinkedHashSet<>(sysDeptFlows);
		ArrayList<String> listWithoutDuplicates = new ArrayList<>(hashSet);
		List<JsresAttendanceDetail> jsresAttendanceDetails = resAttendanceBiz.searchJsresAttendanceDetailByAttendanceFlows(jsResAttendanceFlows);
		if (jsresAttendanceDetails != null && jsresAttendanceDetails.size() > 0) {
			List<JsresAttendanceDetail> tempDetails = null;
			for(JsresAttendanceDetail temp :jsresAttendanceDetails){
				if(attendanceDetailMap.get(temp.getAttendanceFlow()) != null){
					attendanceDetailMap.get(temp.getAttendanceFlow()).add(temp);
				}else {
					tempDetails = new ArrayList<>();
					tempDetails.add(temp);
					attendanceDetailMap.put(temp.getAttendanceFlow(),tempDetails);
				}
			}
		}
		List<String> orgFlows = new ArrayList<>();
		List<SysDept> tempDepts = null;
		if(listWithoutDuplicates != null && listWithoutDuplicates.size() > 0){
			tempDepts = deptBiz.searchDeptByFlows(listWithoutDuplicates);
			if(tempDepts != null && tempDepts.size() > 0){
				for (SysDept temp : tempDepts) {
					if (!temp.getOrgFlow().equals(deptOrgMap.get(temp.getDeptFlow()))) {
						orgFlows.add(temp.getOrgFlow());
					}
				}
			}
		}

		List<SysOrg> tempOrgs = null;
		Map<String,String> orgNameMap = null;
		if(orgFlows != null && orgFlows.size() > 0){
			tempOrgs = orgBiz.searchOrgFlowIn(orgFlows);
			if(tempOrgs != null && tempOrgs.size() > 0){
				orgNameMap = new HashMap<>();
				for(SysOrg tempOrg:tempOrgs){
					orgNameMap.put(tempOrg.getOrgFlow(),tempOrg.getOrgName());
				}
			}
		}
		if(tempDepts != null && tempDepts.size() > 0){
			for (SysDept temp : tempDepts) {
				if (!temp.getOrgFlow().equals(deptOrgMap.get(temp.getDeptFlow()))) {
					for (JsResAttendanceExt jsResAttendanceExt : jsResAttendanceExts) {
						if (temp.getDeptFlow().equals(jsResAttendanceExt.getSysDept().getDeptFlow())) {
							jsResAttendanceExt.getSysDept().setDeptName(jsResAttendanceExt.getSysDept().getDeptName() + ("[" + orgNameMap.get(temp.getOrgFlow()) + "]"));
						}
					}
				}
			}
		}
		String[] headLines = null;
		headLines = new String[]{
				"学员考勤记录表"
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
		cellOne.setCellValue("学员考勤记录表");

		HSSFRow rowTwo = sheet.createRow(1);//第二行
		String[] titles = new String[]{
				"编号",
				"日期",
				"姓名",
				"科室",
				"带教老师",
				"考勤记录",
				"出勤状态",
				"备注",
				"学员备注"
		};
		String[] teacherTitles = new String[]{
				"编号",
				"日期",
				"姓名",
				"考勤记录",
				"出勤状态",
				"备注",
				"学员备注"
		};
		if("teacher".equals(roleId)){
			titles = teacherTitles;
		}
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowTwo.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 2 * 256);
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE))) {
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));//合并单元格
		}else {
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));//合并单元格
		}
		Map<Integer, Integer> columnWidth = new HashMap<>();
		int rowNum = 2;
		int inDocTotal=0;
		int outDocTotal=0;
		String[] resultList = null;
		String[] teacherResultList = null;
		if (jsResAttendanceExts != null && !jsResAttendanceExts.isEmpty()) {
			for (int i = 0; i < jsResAttendanceExts.size(); i++, rowNum++) {
				String attendanceDetailRecords = "";
				String attendanceSerfRemarks = "";
				List<JsresAttendanceDetail> newDetails =null;
				newDetails=attendanceDetailMap.get(jsResAttendanceExts.get(i).getJsresAttendance().getAttendanceFlow());
				if(newDetails != null && !jsResAttendanceExts.isEmpty()){
					for (JsresAttendanceDetail detail: newDetails) {
						if(detail.getAttendanceFlow().equals(jsResAttendanceExts.get(i).getJsresAttendance().getAttendanceFlow())){
							if(StringUtil.isNotBlank(detail.getAttendTime())){
								if(StringUtil.isNotBlank(attendanceDetailRecords))
									attendanceDetailRecords += "\n"+detail.getAttendTime()+"  "+detail.getAttendLocal();
								if(StringUtil.isBlank(attendanceDetailRecords))
									attendanceDetailRecords += detail.getAttendTime()+"  "+detail.getAttendLocal();

								if("teacher".equals(roleId)) {
									if(StringUtil.isNotBlank(detail.getAttendTime()+"  "+detail.getAttendLocal()))
										setColumnWidth((detail.getAttendTime()+"  "+detail.getAttendLocal()).getBytes().length, 3, columnWidth);
								}else {
									if(StringUtil.isNotBlank(detail.getAttendTime()+"  "+detail.getAttendLocal()))
										setColumnWidth((detail.getAttendTime()+"  "+detail.getAttendLocal()).getBytes().length, 5, columnWidth);
								}
							}
							if(StringUtil.isNotBlank(detail.getSelfRemarks())){
								if(StringUtil.isNotBlank(attendanceSerfRemarks))
									attendanceSerfRemarks += "\n"+detail.getAttendTime() +" :" +detail.getSelfRemarks()+"  ";
								if(StringUtil.isBlank(attendanceSerfRemarks))
									attendanceSerfRemarks += detail.getAttendTime() +" :" +detail.getSelfRemarks()+"  ";
							}
						}
					}
				}else {

				}
				if(StringUtil.isBlank(attendanceDetailRecords.trim())){
					attendanceDetailRecords="暂无签到记录！";
				}
				if(StringUtil.isBlank(attendanceSerfRemarks.trim())){
					attendanceSerfRemarks="暂无备注！";
				}
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行
				String statuesName = jsResAttendanceExts.get(i).getJsresAttendance().getAttendStatusName();
				if(StringUtil.isBlank(statuesName)){
					statuesName="待审核";
				}
				resultList = new String[]{
						i+1+"",
						jsResAttendanceExts.get(i).getJsresAttendance().getAttendDate(),
						jsResAttendanceExts.get(i).getUserName(),
						jsResAttendanceExts.get(i).getSysDept().getDeptName(),
						jsResAttendanceExts.get(i).getJsresAttendance().getTeacherName(),
						attendanceDetailRecords,
						statuesName,
						jsResAttendanceExts.get(i).getJsresAttendance().getTeacherRemarks(),
						attendanceSerfRemarks
				};
				teacherResultList = new String[]{
						i+1+"",
						jsResAttendanceExts.get(i).getJsresAttendance().getAttendDate(),
						jsResAttendanceExts.get(i).getUserName(),
						attendanceDetailRecords,
						statuesName,
						jsResAttendanceExts.get(i).getJsresAttendance().getTeacherRemarks(),
						attendanceSerfRemarks
				};
				if("teacher".equals(roleId)){
					resultList = teacherResultList;
				}
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					if("teacher".equals(roleId)&&j==3||!"teacher".equals(roleId)&&j==5) {
						cellFirst.setCellStyle(styleLeft);
					}else {
						cellFirst.setCellStyle(styleCenter);
					}
					cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
					if(StringUtil.isNotBlank(resultList[j]))
						//宽度自适应
						setColumnWidth(resultList[j].toString().getBytes().length, j, columnWidth);
				}

			}
		}
		Set<Integer> keys = columnWidth.keySet();
		for (Integer key : keys) {
			int width = columnWidth.get(key);
			sheet.setColumnWidth(key, width * 2 * 256>255*256?70*256:width * 2 * 256);
		}
		String fileName = "学员考勤记录表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
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

	/**
	 * 临床技能考核查询
	 * @param model
	 * @param currentPage
	 * @param request
	 * @param doctorSchProcess
	 * @param userName
     * @param biaoJi
     * @return
     */
	@RequestMapping(value="/vettedAuditSearch")
	public String vettedAuditSearch(Model model,Integer currentPage,HttpServletRequest request,ResDoctorSchProcess doctorSchProcess,
									String userName,String biaoJi, String datas[]){
		SysUser sysUser=GlobalContext.getCurrentUser();
		List<Map<String,String>> resDoctorSchProcess=null;
			Map<String, Object> schArrangeResultMap=new HashMap<String, Object>();
			schArrangeResultMap.put("teacherUserFlow", sysUser.getUserFlow());
			schArrangeResultMap.put("userName", userName);
			schArrangeResultMap.put("schStartDate", doctorSchProcess.getSchStartDate());
			schArrangeResultMap.put("schEndDate", doctorSchProcess.getSchEndDate());
			schArrangeResultMap.put("biaoJi", biaoJi);
        schArrangeResultMap.put("data", com.pinde.core.common.GlobalConstant.FLAG_N);
			List<String>docTypeList=new ArrayList<String>();
			model.addAttribute("datas", datas);
			if(datas!=null&&datas.length>0){
				for(String s:datas){
					docTypeList.add(s);
				}
			}else{
                datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
				int i=0;
                for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values())
				{
					docTypeList.add(e.getId());
					datas[i++]=e.getId();
				}
			}
			schArrangeResultMap.put("docTypeList", docTypeList);
			PageHelper.startPage(currentPage, getPageSize(request));
			resDoctorSchProcess=iResDoctorProcessBiz.jsresSchDoctorSchProcessQuery(schArrangeResultMap);
			Map<String, String> schRotationDeptMap=new HashMap<String, String>();
			Map<String,Object> readSchRotationGroupMap=new HashMap<String,Object>();
			Map<String,Object> resRecMap=new HashMap<String,Object>();
			for (Map<String, String> map : resDoctorSchProcess) {
				SchArrangeResult schArrangeResult=schArrangeResultBiz.readSchArrangeResult(map.get("schResultFlow"));
				if (schArrangeResult!=null) {
					SchRotationDept schRotationDept=iSchRotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(schArrangeResult.getStandardGroupFlow(),schArrangeResult.getStandardDeptId());
					if (schRotationDept!=null) {
						if (StringUtil.isNotBlank(schRotationDept.getStandardDeptName())) {
							schRotationDeptMap.put(map.get("processFlow"), schRotationDept.getStandardDeptName());
						}
						if (StringUtil.isNotBlank(schRotationDept.getGroupFlow())) {
							SchRotationGroup readSchRotationGroup=iSchRotationGroupBiz.readSchRotationGroup(schRotationDept.getGroupFlow());
							readSchRotationGroupMap.put(map.get("processFlow"), readSchRotationGroup);
						}
					}
				}
//				List<ResRec> resRecList=iResRecBiz.searchRecByProcess(map.get("processFlow"),map.get("userFlow"));
				List<ResSchProcessExpress> resRecList = expressBiz.searchRecByProcess(map.get("processFlow"),map.get("userFlow"));
				for (ResSchProcessExpress resRec : resRecList) {
                    if (com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId().equals(resRec.getRecTypeId())) {
						String dopsKey=map.get("processFlow")+"DOPS";
						resRecMap.put(dopsKey,resRec);
					}
                    if (com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId().equals(resRec.getRecTypeId())) {
						String miniKey=map.get("processFlow")+"Mini_CEX";
						resRecMap.put(miniKey,resRec);
					}
				}
			}
			model.addAttribute("resRecMap", resRecMap);
			model.addAttribute("schRotationDeptMap", schRotationDeptMap);
			model.addAttribute("readSchRotationGroupMap", readSchRotationGroupMap);
			model.addAttribute("biaoJi", biaoJi);
		model.addAttribute("resDoctorSchProcess", resDoctorSchProcess);
		return "jsres/teacher/vettedAuditSearch";
	}

	@RequestMapping(value="/details",method=RequestMethod.GET)
	public String details(String doctorFlow,String typeId,String processFlow,Model model){
		List<ResRec> resRecList=iResRecBiz.searchResRecWithBLOBs(typeId,processFlow,doctorFlow);
		SysUser user=iUserBiz.readSysUser(doctorFlow);
		ResDoctorSchProcess resDoctorSchProcess=iResDoctorProcessBiz.read(processFlow);
		/*List<SchRotationDeptReq> rotationDeptReqList=iSchRotationDeptBiz.searchDeptReqByRel(resultFlow);
		int requirement=0;
		for (SchRotationDeptReq schRotationDeptReq : rotationDeptReqList) {
			BigDecimal n=schRotationDeptReq.getReqNum();
			int reqNum=n.intValue();
			requirement=requirement+reqNum;
		}*/
		model.addAttribute("deptName", resDoctorSchProcess.getDeptName());
		model.addAttribute("user", user);
		model.addAttribute("complete", resRecList.size());
		model.addAttribute("typeId", typeId);
		/*model.addAttribute("requirement", requirement);*/
		model.addAttribute("resRecList", resRecList);
        String cfgCode = "jsres_doctor_app_menu_" + doctorFlow;
        JsresPowerCfg powerCfg = jsresPowerCfgMapper.selectByPrimaryKey(cfgCode);
        if (powerCfg != null) {
            model.addAttribute("statusId", powerCfg.getCheckStatusId());
        }
		return "jsres/teacher/recAuditList";

	}

	/**
	 * 显示审核界面
	 * @param recFlow
	 * @param recTypeId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showAudit",method=RequestMethod.POST)
	public String showAudit(String recFlow,String recTypeId, Model model ){
//		ResRec rec = this.iResRecBiz.readResRecByType(recFlow, recTypeId);
		ResRec rec = this.iResRecBiz.readResRec(recFlow);
		Map<String,Object> formDataMap = iResRecBiz.parseRecContent(rec.getRecContent());
		model.addAttribute("formDataMap", formDataMap);

		String version = null;
		String recForm = null;
		String medicineTypeId = "";
		if(rec!=null){
			version = rec.getRecVersion();
			recForm = rec.getRecForm();
			medicineTypeId = rec.getMedicineType();
		}
		String jspPath = iResRecBiz.getFormPath(recTypeId,version,recForm,"",medicineTypeId);
		model.addAttribute("jspPath", jspPath);

		String view = null;
		if(rec!=null){
			view = "jsres/teacher/common";
			model.addAttribute("rec", rec);
		}
		return view;
	}

	@RequestMapping(value="/audit",method=RequestMethod.POST)
	@ResponseBody
	public String audit(String auditResult,String recFlow, Model model,String recType ){
//		ResRec re=iResRecBiz.readResRecByType(recFlow,recType);
		ResRec re=iResRecBiz.readResRec(recFlow);
		String time=DateUtil.getCurrDateTime();
		SysUser sysUser=GlobalContext.getCurrentUser();
		re.setAuditTime(time);
		re.setAuditUserFlow(sysUser.getUserFlow());
		re.setAuditUserName(sysUser.getUserName());
		re.setAuditStatusId(auditResult);
		re.setAuditStatusName(RecStatusEnum.getNameById(auditResult));
//		int i=iResRecBiz.editByType(re, recType);
		int i=iResRecBiz.edit(re);
        if (i == com.pinde.core.common.GlobalConstant.ONE_LINE) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
		}else{
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
		}
	}

	@RequestMapping(value="/batchAudit",method=RequestMethod.POST)
	@ResponseBody
	public String batchAudit(String auditResult,String docFlow,String processFlow,String recType){
		List<ResRec> recList=iResRecBiz.searchRecByProcess(processFlow,docFlow);
		if(null != recList && recList.size() > 0){
			int i = 0;
			for (ResRec rec : recList) {
				if(rec.getRecTypeId().equals(recType)){
//					ResRec re=iResRecBiz.readResRecByType(rec.getRecFlow(),recType);
					ResRec re=iResRecBiz.readResRec(rec.getRecFlow());
					String time=DateUtil.getCurrDateTime();
					SysUser sysUser=GlobalContext.getCurrentUser();
					re.setAuditTime(time);
					re.setAuditUserFlow(sysUser.getUserFlow());
					re.setAuditUserName(sysUser.getUserName());
					re.setAuditStatusId(auditResult);
					re.setAuditStatusName(RecStatusEnum.getNameById(auditResult));
//					i+=iResRecBiz.editByType(re, recType);
					i+=iResRecBiz.edit(re);
				}
			}
            if (i >= com.pinde.core.common.GlobalConstant.ONE_LINE) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
	}
	@RequestMapping(value="/batchAuditBack",method=RequestMethod.POST)
	@ResponseBody
	public String batchAuditBack(String auditResult,String docFlow,String processFlow,String recType){
		List<ResRec> recList=iResRecBiz.searchRecByProcess(processFlow,docFlow);
		if(null != recList && recList.size() > 0){
			int i = 0;
			for (ResRec rec : recList) {
				if(rec.getRecTypeId().equals(recType)){
//					ResRec re=iResRecBiz.readResRecByType(rec.getRecFlow(),recType);
					ResRec re=iResRecBiz.readResRec(rec.getRecFlow());
					String time=DateUtil.getCurrDateTime();
					SysUser sysUser=GlobalContext.getCurrentUser();
					re.setAuditTime(time);
					re.setAuditUserFlow(sysUser.getUserFlow());
					re.setAuditUserName(sysUser.getUserName());
					re.setAuditStatusId(auditResult);
					re.setAuditStatusName("");
//					i+=iResRecBiz.editByType(re, recType);
					i+=iResRecBiz.edit(re);
				}
			}
            if (i >= com.pinde.core.common.GlobalConstant.ONE_LINE) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
	}

	/**
	 * 出科考核表
	 * @param roleFlag
	 * @param operUserFlow
	 * @param processFlow
	 * @param schDeptFlow
	 * @param recFlow
	 * @param model
     * @return
     * @throws Exception
     */
	@RequestMapping(value="/evaluationSun")
	public String evaluationSun(String roleFlag,
			String operUserFlow,
			String processFlow,
			String schDeptFlow,
			String recFlow,
			boolean notAuditAll,
			Model model) throws Exception{
        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId();
		ResDoctor doctor=null;
		SysUser operUser=null;
		// 由于有对外开放的情况，orgFlow用当前操作用户orgFlow
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		ResDoctorSchProcess process = iResDoctorProcessBiz.read(processFlow);
		model.addAttribute("currRegProcess",process);
		model.addAttribute("notAuditAll", notAuditAll);
		//查询科室是否配置出科设置
		JsresPowerCfg jsresPowerCfg= jsResPowerCfgBiz.read("out_test_check_" + orgFlow);
		if(StringUtil.isNotBlank(operUserFlow)){
			doctor  = doctorBiz.readDoctor(operUserFlow);
			model.addAttribute("doctor", doctor);
			operUser  = iUserBiz.readSysUser(operUserFlow);
			model.addAttribute("operUser",operUser);

			boolean f = false;
			f=doctorBiz.getCkkPower(operUserFlow);
			if (f) {
				ResScore outScore = resScoreBiz.getMaxScoreByProcess(processFlow);
				model.addAttribute("outScore",outScore);
				// 如果有出科成绩 出科试卷flow不为空则查询试卷对应的及格分
				if(null != outScore && StringUtil.isNotBlank(outScore.getPaperFlow())){
					// 查询对应试卷的及格分
					TestPaper testPaper = paperBiz.readTestPaper(outScore.getPaperFlow());
					model.addAttribute("testPaper", testPaper);
					// 是否校验出科理论成绩
//					String key = "theoretical_qualified_flag_" + operUser.getOrgFlow();
//					JsresPowerCfg theoreticalCfg = jsResPowerCfgBiz.read(key);
//					model.addAttribute("theoreticalCfg", theoreticalCfg);
				}
			}
			model.addAttribute("ckk",f);
		}
		//禅道201  修改
		String theoryScorePass = "";
		boolean teacherWrite = false;
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(jsresPowerCfg.getCfgValue())) {
			//查询科室是否配置
			JsresDeptConfig deptConfig = jsResPowerCfgBiz.searchDeptCfg(orgFlow,process.getSchDeptFlow());
			if(null != deptConfig){
				theoryScorePass = deptConfig.getScorePass();
                if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(deptConfig.getTeacherWrite())) {
					teacherWrite = true;
				}
			}else{
				deptConfig = jsResPowerCfgBiz.searchBaseDeptConfig(orgFlow);
				theoryScorePass = deptConfig.getScorePass();
                if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(deptConfig.getTeacherWrite())) {
					teacherWrite = true;
				}
			}
		}
		model.addAttribute("teacherWrite",teacherWrite);
		model.addAttribute("theoryScorePass",theoryScorePass);

//		ResRec rec=iResRecBiz.readResRec(recFlow);
		ResSchProcessExpress express = expressBiz.readResExpress(recFlow);

		Map<String,Object> formDataMap = null;
		if(express!=null){
			String recContent = express.getRecContent();
			formDataMap = iResRecBiz.parseRecContent(recContent);
			model.addAttribute("formDataMap", formDataMap);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		//轮转科室名称
		/*SchRotationDept	dept=iSchRotationDeptBiz.readSchRotationDept(processFlow);*/
		//百分比Map
//		Map<String,String> processPerMap=iResRecBiz.getProcessPer(doctor.getDoctorFlow(),doctor.getRotationFlow(),processFlow);


		SysDept dept=deptBiz.readSysDept(process.getDeptFlow());
        String cksh = com.pinde.core.common.GlobalConstant.FLAG_N;
		JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_"+dept.getOrgFlow()+"_org_cksh");
		if(cfg!=null)
		{
			cksh=cfg.getCfgValue();
		}
		model.addAttribute("cksh",cksh);
//		List<SchArrangeResult> results = new ArrayList<SchArrangeResult>();
//		String resultFlow = "";
//		if(process!=null){
//			resultFlow = process.getSchResultFlow();
//			if(StringUtil.isNotBlank(resultFlow)){
//				SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
//				results.add(result);
//			}
//		}

		Map<String,Object> processPerMap=iResRecBiz.getRecAuditProgressIn(operUserFlow,processFlow,null,null);
		if(processPerMap == null){
			processPerMap = new HashMap<String, Object>();
		}
		//填写百分比限制
		JsresPowerCfg jsresPowerCfg1= jsResPowerCfgBiz.read("out_filling_check_" + orgFlow);
		if(null != jsresPowerCfg1){
			model.addAttribute("checkProcess",jsresPowerCfg1.getCfgValue());
		}else{
			model.addAttribute("checkProcess","0");
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
//			int teachingRounds=0;
//			int difficult=0;
//			int lecture=0;
//			int death=0;
			int jxcf = 0;  int xjk = 0;
			int rkjy = 0;  int ckkh = 0; int jnpx = 0;
			int yph = 0; int jxhz = 0; int jxbltl = 0; int lcczjnzd = 0;
			int lcblsxzd = 0; int ssczzd = 0; int yxzdbgsxzd = 0; int lcwxyd = 0;
			int ryjy = 0; int rzyjdjy = 0; int cjbg = 0;
			int ynbltl=0;	int wzbltl=0; int swbltl=0;  int bgdfx=0;
			int jxsj=0;	int sjys=0;
			List<String> recTypes=new ArrayList<String>();
			recTypes.add(recTypeIdt);
				List<ResRec> recs= iResRecBiz.searchRecAuditByProcessWithBLOBs(recTypes,processFlow,operUser.getUserFlow());
			for (ResRec resRec : recs) {
				String content=resRec.getRecContent();
				Document document=DocumentHelper.parseText(content);
				Element root=document.getRootElement();
				Element ec = root.element("activity_way");
				if (ec!=null) {
					String text=ec.attributeValue("id");
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
					} else if(Bgdfx.equals(text)){
						bgdfx++;
					} else if(Jxsj.equals(text)){
						jxsj++;
					} else if(Sjys.equals(text)){
						sjys++;
					}
				}
			}
			List<TeachingActivityInfo> infos=new ArrayList<>();
//			String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			JsresPowerCfg orgApprove = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_org_ctrl_approve_activity");//教学活动评价配置
			JsresPowerCfg approve = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_org_approve_activity");//教学活动评价配置评审类型
            if (null != orgApprove && null != approve && StringUtil.isNotNullAndEquala(approve.getCfgValue(), orgApprove.getCfgValue(), com.pinde.core.common.GlobalConstant.FLAG_Y)) {        //开启必评
				infos=iResRecBiz.searchJoinActivityByProcessFlownotScore(processFlow);
			}else {
				infos=iResRecBiz.searchJoinActivityByProcessFlow(processFlow);
			}
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
					} else if(Bgdfx.equals(text)){
						bgdfx++;
					} else if(Jxsj.equals(text)){
						jxsj++;
					} else if(Sjys.equals(text)){
						sjys++;
					}
				}
			}
			dataMap.put("userName",operUser.getUserName());
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
			ResDoctorSchProcess resDoctorSchProcess=iResDoctorProcessBiz.read(processFlow);
			model.addAttribute("resDoctorSchProcess", resDoctorSchProcess);
			model.addAttribute("dataMap", dataMap);
			model.addAttribute("rec", express);
			model.addAttribute("processFlow",processFlow);
			model.addAttribute("formFileName",recTypeId);
			model.addAttribute("roleFlag", roleFlag);
		}
        String cfgCode = "jsres_doctor_app_menu_" + operUserFlow;
        JsresPowerCfg powerCfg = jsresPowerCfgMapper.selectByPrimaryKey(cfgCode);
        if (powerCfg != null) {
            model.addAttribute("statusId", powerCfg.getCheckStatusId());
        }
        if (null !=doctor){
			//学员出科是否需要带教老师填写出科评价
			String departure = "jsres_departure_evaluation_" + doctor.getOrgFlow();
			JsresPowerCfg departureCfg = jsresPowerCfgMapper.selectByPrimaryKey(departure);
			if (departureCfg != null) {
				model.addAttribute("departureStatusId", departureCfg.getCfgValue());
			}
		}
		return "jsres/teacher/evaluation";
	}
	@RequestMapping(value="/checkProcessEval")
	@ResponseBody
	public Object checkProcessEval(
			String processFlow,
			Model model) throws Exception{
        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId();
		ResSchProcessExpress express=expressBiz.getExpressByRecTypeNoStatus(processFlow,recTypeId);
		if(express==null) {

			int c=iResDoctorProcessBiz.checkProcessEval(processFlow);
			return c;
		}
		return 0;
	}

	/**
	 * 临床技能考核
	 * @param roleFlag
	 * @param operUserFlow
	 * @param processFlow
	 * @param schDeptFlow
	 * @param recFlow
	 * @param model
     * @return
     * @throws Exception
     */
	@RequestMapping(value="/quantizationTableDOPS")
	public String quantizationTableDOPS(String roleFlag,
			String operUserFlow,
			String processFlow,
			String schDeptFlow,
			String recFlow,
			Model model) throws Exception{
//		ResRec rec=iResRecBiz.readResRec(recFlow);
		ResSchProcessExpress rec = expressBiz.readResExpress(recFlow);
		Map<String,Object> formDataMap = null;
		if(rec!=null){
			String recContent = rec.getRecContent();
			formDataMap = iResRecBiz.parseRecContent(recContent);
			model.addAttribute("formDataMap", formDataMap);
		}
		ResDoctor doctor=doctorBiz.searchByUserFlow(operUserFlow);

        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId();
		model.addAttribute("doctor", doctor);
		model.addAttribute("formFileName", recTypeId);
		model.addAttribute("rec", rec);
		return "jsres/teacher/dops";
	}

	/**
	 * min-cex
	 * @param roleFlag
	 * @param operUserFlow
	 * @param processFlow
	 * @param schDeptFlow
	 * @param recFlow
	 * @param model
     * @return
     * @throws Exception
     */
	@RequestMapping(value="/quantizationTableMiNi")
	public String quantizationTableMiNi(String roleFlag,
			String operUserFlow,
			String processFlow,
			String schDeptFlow,
			String recFlow,
			Model model) throws Exception{
//		ResRec rec=iResRecBiz.readResRec(recFlow);
		ResSchProcessExpress rec  = expressBiz.readResExpress(recFlow);
		Map<String,Object> formDataMap = null;
		if(rec!=null){
			String recContent = rec.getRecContent();
			formDataMap = iResRecBiz.parseRecContent(recContent);
			model.addAttribute("formDataMap", formDataMap);
		}
		ResDoctor doctor=doctorBiz.searchByUserFlow(operUserFlow);

        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId();
		model.addAttribute("doctor", doctor);
		model.addAttribute("formFileName", recTypeId);
		model.addAttribute("rec", rec);
		return "jsres/teacher/mini_cex";
	}
	@RequestMapping(value="/saveRegistryForm",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveRegistryForm(String formFileName,String recFlow,String operUserFlow,String roleFlag,String cksh,
								   String processFlow,String recordFlow,HttpServletRequest req,String teacherComment){
//		ResRec resRec=iJsResDoctorBiz.searResRecZhuZhi(formFileName,recFlow,operUserFlow,roleFlag,processFlow,recordFlow,req);
		ResSchProcessExpress express = iJsResDoctorBiz.searResRecZhuZhi(formFileName,recFlow,operUserFlow,roleFlag,processFlow,recordFlow,cksh,req);
		teacherComment=teacherComment.replaceAll("\n","\\\\n");
		teacherComment=teacherComment.replaceAll("\r","\\\\r");
		express.setTeacherComment(teacherComment);
		express.setManagerAuditUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		int i=expressBiz.edit(express);
		if (i==0) {
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
		}else{
			//processFlow 科室轮转信息  加入出科时间
			ResDoctorSchProcess resDoctorSchProcess=iResDoctorProcessBiz.read(processFlow);
			if(null != resDoctorSchProcess){
				resDoctorSchProcess.setOutDate(DateUtil.getCurrDate());
				iResDoctorProcessBiz.edit(resDoctorSchProcess);
			}
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
	}


	/****************************高******校******管******理******员******角******色************************************************/
	/**
	 * 学员考勤查询
	 * @param model
	 * @param currentPage
	 * @param request
	 * @param orgFlow
	 * @param trainingSpeId
	 * @param schStartDate
	 * @param schEndDate
	 * @param deptFlow
	 * @param teacherName
	 * @param statueId
     * @param studentName
     * @param searchType
     * @return
     */
	@RequestMapping(value="attendanceSearchForUni")
	public String attendanceSearchForUni(Model model,
										 Integer currentPage,
										 HttpServletRequest request,
										 String orgFlow,
										 String trainingSpeId,
										 String trainingTypeId,
										 String schStartDate,
										 String schEndDate,
										 String deptFlow,
										 String teacherName,
										 String statueId,
										 String studentName,
										 String searchType){
		SysUser sysUser=GlobalContext.getCurrentUser();
		List<SysDept> deptList = deptBiz.searchDeptByOrg(sysUser.getOrgFlow());
		model.addAttribute("deptList",deptList);
		Map<String,String> beMap=new HashMap<String,String>();
		if(StringUtil.isBlank(schEndDate)){
			schEndDate=DateUtil.getCurrDate();
		}
		if(StringUtil.isBlank(schStartDate)){
			schStartDate=DateUtil.addDate(DateUtil.getCurrDate(),-30);
		}
		beMap.put("schStartDate",schStartDate);
		beMap.put("schEndDate",schEndDate);
		if(StringUtil.isNotBlank(deptFlow)){
			beMap.put("deptFlow",deptFlow);
		}
		if(StringUtil.isNotBlank(teacherName)){
			beMap.put("teacherName",teacherName);
		}
		if(StringUtil.isNotBlank(studentName)){
			beMap.put("studentName",studentName);
		}
		if("1".equals(statueId)||"0".equals(statueId)){
			beMap.put("statueId",statueId);
		}
		SysOrg currOrg = orgBiz.readSysOrg(sysUser.getOrgFlow());
        beMap.put("doctorTypeId", com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId());
		beMap.put("workOrgId", currOrg.getSendSchoolId());
		beMap.put("workOrgName", currOrg.getSendSchoolName());
		beMap.put("orgFlow",orgFlow);
		beMap.put("trainingSpeId",trainingSpeId);
		beMap.put("trainingTypeId",trainingTypeId);
		if(StringUtil.isNotEmpty(sysUser.getSchool())){
			beMap.put("school",sysUser.getSchool());
		}
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		orgs=orgBiz.getUniOrgs(currOrg.getSendSchoolId(),currOrg.getSendSchoolName());
		model.addAttribute("orgs", orgs);
		List<JsResAttendanceExt> jsResAttendanceExts = new ArrayList<>();
		PageHelper.startPage(currentPage,getPageSize(request));
		jsResAttendanceExts=resAttendanceBiz.searchAttendanceListForUni(beMap);
		Map<String,List> attendanceDetailMap = new HashMap<String,List>();
		for (JsResAttendanceExt jsResAttendanceExt : jsResAttendanceExts) {
			String jsResAttendanceFlow = jsResAttendanceExt.getJsresAttendance().getAttendanceFlow();
			if(StringUtil.isNotBlank(jsResAttendanceFlow)){
				List<JsresAttendanceDetail> JsresAttendanceDetails = resAttendanceBiz.searchJsresAttendanceDetailByAttendanceFlow(jsResAttendanceFlow);
				attendanceDetailMap.put(jsResAttendanceFlow,JsresAttendanceDetails);
			}
			SysDept sysDept=jsResAttendanceExt.getSysDept();
			if(sysDept!=null)
			{
				SysDept temp = deptBiz.readSysDept(sysDept.getDeptFlow());
				String deptOrgFlow = temp.getOrgFlow();
				if(StringUtil.isNotBlank(deptOrgFlow)){
					SysOrg so = orgBiz.readSysOrg(deptOrgFlow);
					if(so!=null && !so.getOrgFlow().equals(sysDept.getOrgFlow())){
						jsResAttendanceExt.getSysDept().setDeptName(jsResAttendanceExt.getSysDept().getDeptName()+("["+so.getOrgName()+"]"));
					}
				}
			}
		}
		model.addAttribute("jsResAttendanceExts",jsResAttendanceExts);
		model.addAttribute("searchType",searchType);
		model.addAttribute("attendanceDetailMap",attendanceDetailMap);
		model.addAttribute("schEndDate",schEndDate);
		model.addAttribute("schStartDate",schStartDate);
		return "jsres/university/attendanceSearch";
	}
	//学员考评列表
	@RequestMapping(value="/stuEvaluation")
	public String stuEvaluation(String userName,String trainingSpeId,String schStartDate,String schEndDate,String evalFlag,
								Integer currentPage,HttpServletRequest request,Model model, String datas[]){
		SysUser sysUser = GlobalContext.getCurrentUser();
			Map<String, Object> schArrangeResultMap = new HashMap<>();
			schArrangeResultMap.put("teacherUserFlow", sysUser.getUserFlow());
			schArrangeResultMap.put("userName", userName);
			schArrangeResultMap.put("trainingSpeId", trainingSpeId);
			schArrangeResultMap.put("schStartDate", schStartDate);
			schArrangeResultMap.put("schEndDate", schEndDate);
			schArrangeResultMap.put("evalFlag", evalFlag);

			List<String>docTypeList=new ArrayList<String>();
			model.addAttribute("datas", datas);
			if(datas!=null&&datas.length>0){
				for(String s:datas){
					docTypeList.add(s);
				}
			}else{
                datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
				int i=0;
                for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values())
				{
					docTypeList.add(e.getId());
					datas[i++]=e.getId();
				}
			}
			schArrangeResultMap.put("docTypeList", docTypeList);
			PageHelper.startPage(currentPage, getPageSize(request));
			List<Map<String,String>> resDoctorSchProcess = iResDoctorProcessBiz.jsresSchDoctorSchProcessQuery(schArrangeResultMap);
			List<String> processFlows = new ArrayList<>();
			for (Map<String, String> doctorSchProcess : resDoctorSchProcess) {
				processFlows.add(doctorSchProcess.get("processFlow"));
			}
			if(null != processFlows && processFlows.size()>0) {
				Map<String, List<ResDoctorSchProcessEval>> evalMap = new HashMap<>();
				List<ResDoctorSchProcessEval> evalList = iResDoctorProcessBiz.queryEvalListByProcessFlow(processFlows);
				if (null != evalList && evalList.size() > 0) {
					List<ResDoctorSchProcessEval> processEvalList = new ArrayList<>();
					for (int i = 0; i < evalList.size(); i++) {
						if (i == 0 || evalList.get(i).getProcessFlow().equals(evalList.get(i - 1).getProcessFlow())) {
							processEvalList.add(evalList.get(i));
						} else {
							processEvalList = new ArrayList<>();
							processEvalList.add(evalList.get(i));
						}
						evalMap.put(evalList.get(i).getProcessFlow(), processEvalList);
					}
					model.addAttribute("evalMap", evalMap);
				}
			}
			model.addAttribute("resDoctorSchProcess",resDoctorSchProcess);
		return "jsres/teacher/stuEvaluation";
	}

	//学员考评页面
	@RequestMapping(value="/editEvalForm",method=RequestMethod.GET)
	public String editEvalForm(Model model) throws DocumentException{
		SysUser user = GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(user.getOrgFlow())){
			ResDoctorProcessEvalConfig config = iJsResDoctorBiz.readEvalConfig(user.getOrgFlow());
			if(null != config && StringUtil.isNotBlank(config.getFormCfg())){
				Document dom = DocumentHelper.parseText(config.getFormCfg());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(null != titleElementList && titleElementList.size() > 0){
					List<JsEvalCfgTitleExt> titleFormList = new ArrayList<JsEvalCfgTitleExt>();
					for(Element te :titleElementList) {
						JsEvalCfgTitleExt titleForm = new JsEvalCfgTitleExt();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						titleForm.setOrderNum(te.attributeValue("orderNum"));
						List<Element> itemElementList = te.elements("item");
						List<JsEvalCfgItemExt> itemFormList = null;
						if (null != itemElementList && itemElementList.size() > 0) {
							itemFormList = new ArrayList<JsEvalCfgItemExt>();
							for (Element ie : itemElementList) {
								JsEvalCfgItemExt itemForm = new JsEvalCfgItemExt();
								itemForm.setId(ie.attributeValue("id"));
								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
								itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
								itemFormList.add(itemForm);
							}
							titleForm.setItemList(itemFormList);
							titleFormList.add(titleForm);
						}
					}
					model.addAttribute("titleFormList", titleFormList);
				}
			}
		}
		return "jsres/teacher/editEvalForm";
	}

	//学员考评保存
	@RequestMapping(value="/saveForm",method=RequestMethod.POST)
	@ResponseBody
	public String saveForm(ResDoctorSchProcessEvalWithBLOBs eval,String [] scoreId,String [] score){
		Map<String,Object> param = new HashMap<>();
		param.put("eval",eval);
		param.put("scoreIdList",scoreId==null?scoreId:Arrays.asList(scoreId));
		param.put("scoreList",score==null?score:Arrays.asList(score));
		synchronized(this) {
			ResDoctorSchProcessEvalWithBLOBs oldEval = iResDoctorProcessBiz.getDoctorProcessEval(eval.getProcessFlow(), eval.getStartTime(), eval.getEndTime());
			if (null != oldEval) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
			int result = iResDoctorProcessBiz.saveForm(param);
            if (result > com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
		}
	}
	//学员考评页面
	@RequestMapping(value="/showEvalDetail",method=RequestMethod.GET)
	public String showEvalDetail(String recordFlow,Model model) throws DocumentException{
		if(StringUtil.isNotBlank(recordFlow)){
			ResDoctorSchProcessEvalWithBLOBs eval = iResDoctorProcessBiz.readProcessEvalByFlow(recordFlow);
			model.addAttribute("eval",eval);
			if(null != eval && StringUtil.isNotBlank(eval.getFormCfg())){
				Document dom = DocumentHelper.parseText(eval.getFormCfg());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(null != titleElementList && titleElementList.size() > 0){
					List<JsEvalCfgTitleExt> titleFormList = new ArrayList<JsEvalCfgTitleExt>();
					for(Element te :titleElementList) {
						JsEvalCfgTitleExt titleForm = new JsEvalCfgTitleExt();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						titleForm.setOrderNum(te.attributeValue("orderNum"));
						List<Element> itemElementList = te.elements("item");
						List<JsEvalCfgItemExt> itemFormList = null;
						if (null != itemElementList && itemElementList.size() > 0) {
							itemFormList = new ArrayList<JsEvalCfgItemExt>();
							for (Element ie : itemElementList) {
								JsEvalCfgItemExt itemForm = new JsEvalCfgItemExt();
								itemForm.setId(ie.attributeValue("id"));
								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
								itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
								itemFormList.add(itemForm);
							}
						}
						titleForm.setItemList(itemFormList);
						titleFormList.add(titleForm);
					}
					model.addAttribute("titleFormList", titleFormList);
				}
				Document scoreDom = DocumentHelper.parseText(eval.getEvalResult());
				String scoreXpath = "//score";
				List<Element> scoreElementList = scoreDom.selectNodes(scoreXpath);
				if(null != scoreElementList && scoreElementList.size() > 0){
					Map<String,Object> scoreMap = new HashMap<>();
					for(Element score :scoreElementList){
						scoreMap.put(score.attributeValue("id"),score.getTextTrim());
					}
					model.addAttribute("scoreMap",scoreMap);
				}
			}
			model.addAttribute("flag","view");
		}
		return "jsres/teacher/editEvalForm";
	}
	@RequestMapping(value="/searchDeptList")
	@ResponseBody
	public List<SysDept> searchDeptList(String orgFlow, String roleId){
		if(StringUtil.isNotBlank(orgFlow)){
            if (!com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleId) && !com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleId)) {
				List<SysDept> deptList=deptBiz.searchDeptByOrg(orgFlow);
				return deptList;
			} else {
				List<SysUserDept> userDeptList = userBiz.getUserDept(GlobalContext.getCurrentUser());
				List<SysDept> deptList = new ArrayList<>();
				for (SysUserDept sysUserDept : userDeptList) {
					SysDept sysDept = deptBiz.readSysDept(sysUserDept.getDeptFlow());
					deptList.add(sysDept);
				}
				return deptList;
			}
		} else {
            if (!com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleId) && !com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleId)) {
				List<SysDept> deptList=deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
				return deptList;
			} else {
				List<SysUserDept> userDeptList = userBiz.getUserDept(GlobalContext.getCurrentUser());
				List<SysDept> deptList = new ArrayList<>();
				for (SysUserDept sysUserDept : userDeptList) {
					SysDept sysDept = deptBiz.readSysDept(sysUserDept.getDeptFlow());
					deptList.add(sysDept);
				}
				return deptList;
			}
		}
	}
}
