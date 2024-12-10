package com.pinde.sci.ctrl.jsres;

import com.pinde.core.common.enums.BaseStatusEnum;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.*;
import com.pinde.core.common.enums.osca.AuditStatusEnum;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Slf4j
@Controller
@RequestMapping("/jsres/hospital")
public class JsResHospitaltController extends GeneralController{
    private static Logger logger = LoggerFactory.getLogger(JsResHospitaltController.class);

	@Autowired
	private IResDoctorProcessBiz iResDoctorProcessBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private IResOrgSpeAssignBiz speAssignBiz;
	@Autowired
	private IJsResDoctorBiz jsResDoctorBiz;

	/**
	 * 学员考评  住院医师
	 */
	@RequestMapping(value="/tabMain")
	public String tabMain(Model model,Integer currentPage,HttpServletRequest request,String  isQuery){
		return "jsres/hospital/docProcessEval/tabMain";
	}


	/**
	 * 学员考评 助理全科
	 */
	@RequestMapping(value="/tabMainAcc")
	public String tabMainAcc(Model model,Integer currentPage,HttpServletRequest request,String  isQuery){
		return "jsres/hospital/docProcessEval/tabMainAcc";
	}

	/**
	 * 学员考评 住院医师
	 */
	@RequestMapping(value="/docProcessEvalMain")
	public String docProcessEval(Model model,Integer currentPage,HttpServletRequest request,String  isQuery){
		SysUser currentUser = GlobalContext.getCurrentUser();
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		model.addAttribute("org",currentOrg);
		List<SysOrg>  orgs = new ArrayList<>();
        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId()))
		{
			List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
			orgs.addAll(joinOrgs);
		}
		orgs.add(currentOrg);
		model.addAttribute("orgs", orgs);
		return "jsres/hospital/docProcessEval/main";
	}

	/**
	 * 学员考评		助理全科
	 */
	@RequestMapping(value="/docProcessEvalMainAcc")
	public String docProcessEvalAcc(Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		model.addAttribute("org",currentOrg);
		List<SysOrg>  orgs = new ArrayList<>();
        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId()))
		{
			List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
			orgs.addAll(joinOrgs);
		}
		orgs.add(currentOrg);
		model.addAttribute("orgs", orgs);
		return "jsres/hospital/docProcessEval/mainAcc";
	}

	@RequestMapping(value="/docProcessEvalList")
	public String docProcessEvalList(Model model,Integer currentPage ,HttpServletRequest request,
							String trainingTypeId,String trainingSpeId,String datas[],
							String sessionNumber,String graduationYear,
							String userName,String idNo,String orgFlow,String doctorStatusId,String isQuery, String[] schMonths
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
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		List<String> orgFlowList = new ArrayList();
		if (StringUtil.isNotBlank(orgFlow)) {
			orgFlowList.add(orgFlow);
		}else {
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId())) {
				List<SysOrg> joinOrgs = orgBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
				if (joinOrgs != null && joinOrgs.size() > 0) {
					for (SysOrg tempOrg : joinOrgs) {
						orgFlowList.add(tempOrg.getOrgFlow());
					}
				}
			}
			orgFlowList.add(currentOrg.getOrgFlow());
		}
		if(StringUtil.isNotBlank(isQuery)&&StringUtil.isBlank(orgFlow))
		{
			orgFlow=currentUser.getOrgFlow();
		}
		if(ArrayUtils.isNotEmpty(schMonths) && schMonths.length < 12) { // 不是全选，（全选all等于没这个条件）
			param.put("schMonthList", Arrays.asList(schMonths));
		}
		param.put("orgFlowList",orgFlowList);//培训基地
		param.put("orgFlow",orgFlow);
		param.put("docTypeList",docTypeList);
		param.put("doctorStatusId",doctorStatusId);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("graduationYear",graduationYear);
		param.put("userName",userName);
		param.put("idNo",idNo);
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
		return "jsres/hospital/docProcessEval/docProcessEvalList";
	}

	@RequestMapping(value="/exportEvalList")
	public void exportEvalList(Model model ,HttpServletResponse response,
									 String trainingTypeId,String trainingSpeId,String datas[],
									 String sessionNumber,String graduationYear,
									 String userName,String idNo,String orgFlow,String doctorStatusId,String isQuery, String[] schMonths
	) throws Exception {
		SysUser currentUser = GlobalContext.getCurrentUser();
		//查询条件
		Map<String,Object> param=new HashMap<>();
		List<String>docTypeList=new ArrayList<String>();//人员类型
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		List<String> orgFlowList = new ArrayList();
		if (StringUtil.isNotBlank(orgFlow)) {
			orgFlowList.add(orgFlow);
		}else {
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId())) {
				List<SysOrg> joinOrgs = orgBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
				if (joinOrgs != null && joinOrgs.size() > 0) {
					for (SysOrg tempOrg : joinOrgs) {
						orgFlowList.add(tempOrg.getOrgFlow());
					}
				}
			}
			orgFlowList.add(currentOrg.getOrgFlow());
		}
		if(StringUtil.isNotBlank(isQuery)&&StringUtil.isBlank(orgFlow))
		{
			orgFlow=currentUser.getOrgFlow();
		}
		if(ArrayUtils.isNotEmpty(schMonths) && schMonths.length < 12) { // 不是全选，（全选all等于没这个条件）
			param.put("schMonthList", Arrays.asList(schMonths));
		}
		param.put("orgFlowList",orgFlowList);//培训基地
		param.put("orgFlow",orgFlow);
		param.put("docTypeList",docTypeList);
		param.put("doctorStatusId",doctorStatusId);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("graduationYear",graduationYear);
		param.put("userName",userName);
		param.put("idNo",idNo);
		List<Map<String,Object>> list=iResDoctorProcessBiz.jsresSchDoctorSchProcessEval(param);
		Map<String,Integer> evalSizeMap=new HashMap<>();
		Map<String,	List<Map<String, Object>>> evalMap=new HashMap<>();
		List<Map<String,Object>> exportList = new ArrayList<>();
		if(list!=null&&list.size()>0)
		{
			for(Map<String,Object> process:list)
			{
				String startDate= (String) process.get("schStartDate");
				String endDate= (String) process.get("schEndDate");
				process.put("schDate",startDate + "~" + endDate);
				String processFlow= (String) process.get("processFlow");
                process.put("trainYear", com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(null == process.get("trainYear") ? "" : process.get("trainYear").toString()));
				List<Map<String, String>> times=getTimes(startDate,endDate,processFlow);
				evalSizeMap.put(processFlow,times.size());
				List<Map<String, Object>> evals=null;
				if(times.size()>0)
				{
					Map<String, Object> params=new HashMap<String, Object>();
					params.put("times", times);
					evals=iResDoctorProcessBiz.findOneProcessEvals(params);
					evalMap.put(processFlow,evals);
					if(null != evals && evals.size() > 0){
						evals.forEach(eval->{
							Map<String,Object> exportData = new HashMap<>();
							exportData.put("name","月度考评表");
							exportData.put("time",eval.get("startTime")+"~"+eval.get("endTime"));
							exportData.put("status",Objects.isNull(eval.get("recordFlow")) ? "待考评" : "已考评");
							exportData.put("score",eval.get("evalScore"));
							exportData.put("attendance", eval.get("ATTENDANCE"));
							exportData.put("leave", eval.get("LEAVE"));
							exportData.put("absenteeism", eval.get("ABSENTEEISM"));
							exportData.putAll(process);
							exportList.add(exportData);
						});
					}
				}else{
					exportList.add(process);
				}
			}
		}

		String[] titles = null;
		titles = new String[]{
				"userName:姓名",
				"catSpeName:培训类别",
				"speName:培训专业",
				"orgName:培训基地",
				"sessionNumber:年级",
				"trainYear:培训年限",
				"deptName:轮转科室",
				"schDate:轮转时间",
				"teacherUserName:带教老师",
				"name:名称",
				"time:时间",
				"status:状态",
				"score:成绩",
				"attendance:出勤天数",
				"leave:请假天数",
				"absenteeism:缺勤天数"
		};
		String fileName = "考评指标成绩列表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, exportList, response.getOutputStream(), new String[]{"0"});
	}


	/**
	 * 学员招录成绩
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/achievementsDetails")
	public String achievementsDetail(Model model,HttpServletRequest request){

		return "jsres/hospital/achievement/achievementDetails";
	}

	@RequestMapping(value="/achievementsDetailsAcc")
	public String achievementsDetailsAcc(Model model,HttpServletRequest request){

		return "jsres/hospital/achievement/achievementDetailsAcc";
	}

	@RequestMapping(value="/achievement/importInterviewExam")
	public String achievementImportInterviewExam(Model model,HttpServletRequest request){
		log.info("打开面试成绩导入页面窗口");
		return "jsres/hospital/achievement/importInterviewExam";
	}
	@RequestMapping(value="/achievement/importExamResult")
	public String achievementImportExamResult(Model model,HttpServletRequest request){
		log.info("打开笔试成绩导入页面窗口");
		return "jsres/hospital/achievement/importExamResult";
	}
	@RequestMapping(value="/achievement/importSkillExam")
	public String achievementImportSkillExam(Model model,HttpServletRequest request){
		log.info("打开操作成绩导入页面窗口");
		return "jsres/hospital/achievement/importSkillExam";
	}

	/**
	 * 加载医师成绩列表 住院医师
	 * @param resDoctorRecruit
	 * @param sysUser
	 * @param currentPage
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getAchievementDetails")
	public String getAchievementDetails(ResDoctorRecruit resDoctorRecruit, SysUser sysUser, String scoreType, Integer currentPage, HttpServletRequest request,
									 String sortType,Model model, String datas[]) {
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(resDoctorRecruit.getSessionNumber())) {
			model.addAttribute("sessionNumber",resDoctorRecruit.getSessionNumber());
			String[] numbers = resDoctorRecruit.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				resDoctorRecruit.setSessionNumber("");
			}
		}
		SysUser currUser = GlobalContext.getCurrentUser();
		resDoctorRecruit.setOrgFlow(currUser.getOrgFlow());
//		resDoctorRecruit.setAuditionStatusId(BaseStatusEnum.Passed.getId());//查询报名信息审核通过的
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorRecruitExt> recruitList = jsResDoctorRecruitBiz.resDoctorRecruitExtList2(resDoctorRecruit, sysUser, null, docTypeList, sessionNumbers,sortType,"");
		model.addAttribute("recruitList", recruitList);
		model.addAttribute("currentDate", DateUtil.getCurrDateTime2());

		if("oper".equals(scoreType)){
			return "jsres/hospital/achievement/operResult";
		}else if("audition".equals(scoreType)){
			return "jsres/hospital/achievement/auditionResult";
		}else{
			return "jsres/hospital/achievement/examResult";
		}
	}


	/**
	 * 加载医师成绩列表 助理全科
	 * @param resDoctorRecruit
	 * @param sysUser
	 * @param currentPage
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getAchievementDetailsAcc")
	public String getAchievementDetailsAcc(ResDoctorRecruit resDoctorRecruit, SysUser sysUser, String scoreType, Integer currentPage, HttpServletRequest request,
										String sortType,Model model, String datas[]) {
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(resDoctorRecruit.getSessionNumber())) {
			model.addAttribute("sessionNumber",resDoctorRecruit.getSessionNumber());
			String[] numbers = resDoctorRecruit.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				resDoctorRecruit.setSessionNumber("");
			}
		}
		SysUser currUser = GlobalContext.getCurrentUser();
		resDoctorRecruit.setOrgFlow(currUser.getOrgFlow());
//		resDoctorRecruit.setAuditionStatusId(BaseStatusEnum.Passed.getId());//查询报名信息审核通过的
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorRecruitExt> recruitList = jsResDoctorRecruitBiz.resDoctorRecruitExtList2(resDoctorRecruit, sysUser, null, docTypeList, sessionNumbers,sortType,"");
		model.addAttribute("recruitList", recruitList);
		model.addAttribute("currentDate", DateUtil.getCurrDateTime2());

		if("oper".equals(scoreType)){
			return "jsres/hospital/achievement/operResultAcc";
		}else if("audition".equals(scoreType)){
			return "jsres/hospital/achievement/auditionResultAcc";
		}else{
			return "jsres/hospital/achievement/examResultAcc";
		}
	}

	/**
	 * 学员招录成绩	住院医师
	 * @param model
	 * @param request
     * @return
     */
	@RequestMapping(value="/achievements")
	public String achievements(String scoreType,Model model,HttpServletRequest request){

		model.addAttribute("scoreType",scoreType);
		return "jsres/hospital/achievement/achievements";
	}


	/**
	 * 学员招录成绩  助理全科
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/achievementsAcc")
	public String achievementsAcc(String scoreType,Model model,HttpServletRequest request){

		model.addAttribute("scoreType",scoreType);
		return "jsres/hospital/achievement/achievementsAcc";
	}


	/**
	 * 加载医师成绩列表
	 * @param resDoctorRecruit
	 * @param sysUser
	 * @param currentPage
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getAchievementList")
	public String getAchievementList(ResDoctorRecruit resDoctorRecruit, SysUser sysUser, Integer currentPage, HttpServletRequest request,
									String sortType,Model model, String datas[]) {
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(resDoctorRecruit.getSessionNumber())) {
			String[] numbers = resDoctorRecruit.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				resDoctorRecruit.setSessionNumber("");
			}
		}
		SysUser currUser = GlobalContext.getCurrentUser();
		resDoctorRecruit.setOrgFlow(currUser.getOrgFlow());
//		resDoctorRecruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId());
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorRecruitExt> recruitList = jsResDoctorRecruitBiz.resDoctorRecruitExtList3(resDoctorRecruit, sysUser, null, docTypeList, sessionNumbers,sortType,null);
		model.addAttribute("recruitList", recruitList);
		model.addAttribute("currentDate", DateUtil.getCurrDateTime2());
		return "jsres/hospital/achievement/achievementList";
	}

	/**
	 * 基地导入面试成绩
	 * @param file
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value={"/impInterviewExcel"},method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> impInterviewExcel(MultipartFile file) throws Exception{
		if(file.getSize() > 0){
			try {
				return speAssignBiz.impInterviewExcel(file);
			} catch (Exception e) {
                logger.error("", e);
				Map<String, Object> resultMap = new HashMap<String, Object>() {{
					this.put("errorInfo", e.getMessage());
					this.put("errorInfoNum", "1");
				}};
				return resultMap;
			}

		}
		return null;
	}

	/**
	 * 基地导入操作成绩
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/impSkillExcel"},method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> impSkillExcel(MultipartFile file) throws Exception{
		if(file.getSize() > 0){
			try {
				return speAssignBiz.impSkillExcel(file);
			} catch (Exception e) {
                logger.error("", e);
				Map<String, Object> resultMap = new HashMap<String, Object>() {{
					this.put("errorInfo", e.getMessage());
					this.put("errorInfoNum", "1");
				}};
				return resultMap;
			}
		}
		return null;
	}

	/**
	 * 基地导入成绩(笔试 面试 操作)
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/impGradeExcel"},method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> impGradeExcel(MultipartFile file) throws Exception{
		if(file.getSize() > 0){
			try {
				return speAssignBiz.impSkillExcel(file);
			} catch (Exception e) {
                logger.error("", e);
				Map<String, Object> resultMap = new HashMap<String, Object>() {{
					this.put("errorInfo", e.getMessage());
					this.put("errorInfoNum", "1");
				}};
				return resultMap;
			}
		}
		return null;
	}

	/**
	 * 基地学员成绩修改
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/modifyResultInfo")
	public String modifyResultInfo(ResDoctorRecruitWithBLOBs docRecWithBLOBs, Model model) throws Exception{

		ResDoctorRecruit doctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(docRecWithBLOBs.getRecruitFlow());
		model.addAttribute("doctorRecruit",doctorRecruit);
		return "jsres/hospital/achievement/modifyResult";
	}

	/**
	 * 基地学员成绩
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/modifyResult")
	@ResponseBody
	public String modifyResult(ResDoctorRecruitWithBLOBs docRecWithBLOBs) throws Exception{
		int count = 0;
		try {
			count = jsResDoctorRecruitBiz.modifyResult(docRecWithBLOBs);
		} catch (Exception e) {
            logger.error("", e);
			throw e;
		}
		if(count>0){
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
	}

	/**
	 * 基地招录学员成绩状态
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/confireResult")
	@ResponseBody
	public String confireResult(ResDoctorRecruitWithBLOBs docRecWithBLOBs) throws Exception{
		int count = 0;
		try {
			count = jsResDoctorRecruitBiz.saveAuditResult(docRecWithBLOBs);
		} catch (Exception e) {
            logger.error("", e);
			throw e;
		}
		if(count>0){
            return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
	}

	/**
	 * 批量审核学员成绩
	 * @param recruitFlowList
	 * @return
	 */
	@RequestMapping(value="/confireResultAll")
	@ResponseBody
	public String confireResultAll(String [] recruitFlowList,String scoreType ,String resultFlag){
		int count = 0;
		List<String> records = Arrays.asList(recruitFlowList);
		for(int i=0;i<records.size();i++){
			String recruitFlow = records.get(i);
			ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
            if (StringUtil.isBlank(recruit.getRecruitFlag()) || com.pinde.core.common.GlobalConstant.FLAG_N.equals(recruit.getRecruitFlag())) {
				// 已录取的学员 成绩审核不进行操作
				ResDoctorRecruitWithBLOBs docRecWithBLOBs = new ResDoctorRecruitWithBLOBs();
				docRecWithBLOBs.setRecruitFlow(recruitFlow);
				if("exam".equals(scoreType)){
					docRecWithBLOBs.setExamStatusId(resultFlag);
					docRecWithBLOBs.setExamStatusName(BaseStatusEnum.getNameById(resultFlag));
				}else if("audition".equals(scoreType)){
					docRecWithBLOBs.setAuditionStatusId(resultFlag);
					docRecWithBLOBs.setAuditionStatusName(BaseStatusEnum.getNameById(resultFlag));
				}else if("oper".equals(scoreType)){
					docRecWithBLOBs.setOperStatusId(resultFlag);
					docRecWithBLOBs.setOperStatusName(BaseStatusEnum.getNameById(resultFlag));
				}
				count += jsResDoctorRecruitBiz.saveAuditResult(docRecWithBLOBs);
			}
		}
		if(count > 0){
            return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
	}

	/**
	 * 基地招录学员状态
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/confireRecruitInfo")
	@ResponseBody
	public String confireRecruitInfo(ResDoctorRecruitWithBLOBs docRecWithBLOBs,HttpServletRequest request) throws Exception{
		int count = 0;
		try {
			count = jsResDoctorRecruitBiz.saveAuditRecruitInfo(docRecWithBLOBs);
		} catch (Exception e) {
            logger.error("", e);
			throw e;
		}
		if(count>0){
            return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
	}

	@RequestMapping(value="/toTconfireRecruit")
	public String toTconfireRecruit(String recruitFlow,String doctorFlow,String userName,String recruitFlag,Model model) throws Exception{
		model.addAttribute("recruitFlow",recruitFlow);
		model.addAttribute("doctorFlow",doctorFlow);
		model.addAttribute("userName",userName);
		model.addAttribute("recruitFlag",recruitFlag);
		return "jsres/hospital/achievement/toTconfireRecruit";
	}

	/**
	 * 基地招录学员状态
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/confireRecruit")
	@ResponseBody
	public String confireRecruit(ResDoctorRecruitWithBLOBs docRecWithBLOBs) throws Exception{
		int count = 0;
		try {
			count = jsResDoctorRecruitBiz.saveAuditRecruit(docRecWithBLOBs);
		} catch (Exception e) {
            logger.error("", e);
			throw e;
		}
		if(count>0){
            return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
	}

	/**
	 * 批量审核学员
	 * @param recruitFlowList
	 * @return
     */
	@RequestMapping(value="/confireRecruitAll")
	@ResponseBody
	public String confireRecruitAll(String [] recruitFlowList,String recruitFlag){
		int count = 0;
		List<String> records = Arrays.asList(recruitFlowList);
		for(int i=0;i<records.size();i++){
			String recruitFlow = records.get(i);
			// 学员已报到则不进行操作
			ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
			//学员进行报到操作 更新rotation_flow
			if(StringUtil.isBlank(recruit.getRotationFlow())){
				//已报到 则不能一键录取/不录取
				ResDoctorRecruitWithBLOBs docRecWithBLOBs = new ResDoctorRecruitWithBLOBs();
				docRecWithBLOBs.setRecruitFlow(recruitFlow);
				docRecWithBLOBs.setRecruitFlag(recruitFlag);//录取标志
				count += jsResDoctorRecruitBiz.saveAuditRecruit(docRecWithBLOBs);
			}
		}
		if(count > 0){
            return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
	}

	/**
	 * 医师成绩列表导出
	 * @param resDoctorRecruit
	 * @param sysUser
	 * @return
	 */
	@RequestMapping(value = "/expertAchievementList")
	public void expertAchievementList(ResDoctorRecruit resDoctorRecruit, SysUser sysUser,String scoreType, HttpServletResponse response,
									String sortType,String datas[]) {

		try {
			List<String> sessionNumbers = new ArrayList<String>();//年级多选
			if (StringUtil.isNotBlank(resDoctorRecruit.getSessionNumber())) {
                String[] numbers = resDoctorRecruit.getSessionNumber().split(",");
                if (numbers != null && numbers.length > 0) {
                    sessionNumbers = Arrays.asList(numbers);
                    resDoctorRecruit.setSessionNumber("");
                }
            }
			SysUser currUser = GlobalContext.getCurrentUser();
			resDoctorRecruit.setOrgFlow(currUser.getOrgFlow());
			List<String> docTypeList = new ArrayList<String>();
			if (datas != null && datas.length > 0) {
                for (String s : datas) {
                    docTypeList.add(s);
                }
            } else {
                datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
                int i = 0;
                for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
                    docTypeList.add(e.getId());
                    datas[i++] = e.getId();
                }
            }

//			List<JsResDoctorRecruitExt> recruitList = jsResDoctorRecruitBiz.resDoctorRecruitExtList2(resDoctorRecruit, sysUser, null, docTypeList, sessionNumbers,sortType, scoreType);
			List<JsResDoctorRecruitExt> recruitList = jsResDoctorRecruitBiz.resDoctorRecruitExtList3(resDoctorRecruit, sysUser, null, docTypeList, sessionNumbers,sortType, scoreType);
			for (JsResDoctorRecruitExt jsResDoctorRecruitExt : recruitList) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jsResDoctorRecruitExt.getRecruitFlag())) {
					jsResDoctorRecruitExt.setRecruitFlag("已录取");
                } else if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(jsResDoctorRecruitExt.getRecruitFlag())) {
					jsResDoctorRecruitExt.setRecruitFlag("未录取");
				}else{
					jsResDoctorRecruitExt.setRecruitFlag("待审核");
				}
			}

			String[] titles = null;
			String fileName = "学员成绩导出.xls";
			if("exam".equals(scoreType)){
				fileName = "学员笔试成绩导出 .xls";
				titles = new String[]{
						"sysUser.userName:姓名",
						"resDoctor.doctorTypeName:人员类型",
						"sysUser.idNo:证件号   ",
						"sessionNumber:年级",
						"speName:培训专业",
						"examResult:笔试成绩",
						"examStatusName:状态"
				};
			}else if("audition".equals(scoreType)){
				fileName = "学员面试成绩导出.xls";
				titles = new String[]{
						"sysUser.userName:姓名",
						"resDoctor.doctorTypeName:人员类型",
						"sysUser.idNo:证件号   ",
						"sessionNumber:年级",
						"speName:培训专业",
						"auditionResult:面试成绩",
						"auditionStatusName:状态"
				};
			}else if("oper".equals(scoreType)){
				fileName = "学员操作成绩导出 .xls";
				titles = new String[]{
						"sysUser.userName:姓名",
						"resDoctor.doctorTypeName:人员类型",
						"sysUser.idNo:证件号   ",
						"sessionNumber:年级",
						"speName:培训专业",
						"operResult:操作成绩",
						"operStatusName:状态"
				};
			}else if("all".equals(scoreType)){
				fileName = "学员成绩信息导出 .xls";
				titles = new String[]{
						"sysUser.userName:姓名",
						"resDoctor.doctorTypeName:人员类型",
						"sysUser.idNo:证件号   ",
						"sessionNumber:年级",
						"speName:培训专业",
						"examResult:笔试成绩",
						"auditionResult:面试成绩",
						"operResult:操作成绩",
						"totleResult:总成绩",
						"recruitFlag:录取状态"
				};
			}

			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			ExcleUtile.exportSimpleExcleByObjs(titles, recruitList, response.getOutputStream());
		} catch (Exception e) {
            logger.error("", e);
		}
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

	/**
	 * 省厅签名图片查询
	 */
	@RequestMapping(value="/signSearch")
	public String signSearch(String auditStatusId,String tabTag,Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		SysOrg sysorg4Search = new SysOrg();
		sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
        sysorg4Search.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		//省里所有医院
		List<SysOrg> orgs = orgBiz.searchOrgListNew(sysorg4Search);
		model.addAttribute("orgs", orgs);
		model.addAttribute("auditStatusId",auditStatusId);
		model.addAttribute("tabTag",tabTag);
		return "jsres/hospital/signManage/signSearch";
	}

	@RequestMapping(value="/signManage")
	public String signManage(String orgFlow,String auditStatusId,Integer currentPage,Model model,HttpServletRequest request){
		Map<String,Object> param = new HashMap<>();
		param.put("orgFlow",orgFlow);
		param.put("auditStatusId",auditStatusId);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsresSign> signList = speAssignBiz.searchSignListByParam(param);
		model.addAttribute("signList",signList);
		return "jsres/hospital/signManage/signList";
	}

	/**
	 * 省厅签名图片审核
	 */
	@RequestMapping(value="/confireSignInfo")
	@ResponseBody
	public String confireSignInfo(String signFlow,String auditStatusId,Model model){
		JsresSign sign = new JsresSign();
		sign.setSignFlow(signFlow);
		sign.setAuditStatusId(auditStatusId);
		sign.setAuditStatusName(AuditStatusEnum.getNameById(auditStatusId));
		if("UnPassed".equals(auditStatusId)){
            sign.setUseStatusId(com.pinde.core.common.GlobalConstant.FLAG_N);
			sign.setUseStatusName("停用");
		}
		if("Passed".equals(auditStatusId)){
			sign.setUseTime(DateUtil.getCurrDateTime2());
		}
		return speAssignBiz.saveJsresSign(sign);
	}

	/**
	 * 基地签名图片查询
	 */
	@RequestMapping(value="/signMain")
	public String signMain(Model model,HttpServletRequest request){
		SysUser currentUser = GlobalContext.getCurrentUser();
		PageHelper.startPage(1, getPageSize(request));
		List<JsresSign> signList = speAssignBiz.searchSignListByOrgFlow(currentUser.getOrgFlow(),"");
		model.addAttribute("signList",signList);
		model.addAttribute("orgFlow",currentUser.getOrgFlow());
		model.addAttribute("orgName",currentUser.getOrgName());
		return "jsres/hospital/signManage/signMain";
	}

	@RequestMapping(value="/signSetUpList")
	public String signSetUpList(JsresSign sign,Model model,HttpServletRequest request,Integer currentPage){
		PageHelper.startPage(currentPage, getPageSize(request));
		if(StringUtil.isNotBlank(sign.getAuditStatusId()) && sign.getAuditStatusId().equals("NotUse")){
			sign.setAuditStatusId("");
            sign.setUseStatusId(com.pinde.core.common.GlobalConstant.FLAG_N);
		}
		model.addAttribute("orgFlow",sign.getOrgFlow());
		model.addAttribute("orgName",sign.getOrgName());
		List<JsresSign> signList = speAssignBiz.searchSignList(sign);
		model.addAttribute("signList",signList);
		return "jsres/hospital/signManage/signMain";
	}

	/**
	 * 基地签名图片新增
	 */
	@RequestMapping(value="/addSign")
	public String addSign(String orgFlow,String orgName,String operType,String signFlow,Model model,HttpServletRequest request){
		model.addAttribute("operType",operType);
		model.addAttribute("orgFlow",orgFlow);
		model.addAttribute("orgName",orgName);
		model.addAttribute("signFlow",signFlow);
		return "jsres/hospital/signManage/addSign";
	}

	@RequestMapping(value="/checkUploadFile",method={RequestMethod.POST})
	public String checkUploadFile(String orgFlow,String orgName,String operType,String signFlow,String presidentName,String sessionNumber, MultipartFile uploadFile, Model model){
		model.addAttribute("operType", operType);
		Map<String, MultipartFile> fileMap = new LinkedHashMap<String, MultipartFile>();
		fileMap.put(operType, uploadFile);
		if (uploadFile != null) {
			String resultPath = jsResDoctorBiz.saveFileToDirs("", uploadFile, "hospitalSign");
            model.addAttribute("result", com.pinde.core.common.GlobalConstant.FLAG_Y);
			model.addAttribute("filePath", resultPath);
			JsresSign sign = new JsresSign();
			sign.setSignFlow(signFlow);
			sign.setSessionNumber(sessionNumber);
			sign.setOrgFlow(orgFlow);
			sign.setOrgName(orgName);
			sign.setAuditStatusId("NotUse");
			sign.setAuditStatusName("未启用");
			sign.setPresidentName(presidentName);
			sign.setSignUrl(resultPath);
            sign.setUseStatusId(com.pinde.core.common.GlobalConstant.FLAG_N);
			sign.setUseStatusName("停用");
			speAssignBiz.saveJsresSign(sign);
		}else{
            model.addAttribute("result", com.pinde.core.common.GlobalConstant.FLAG_N);
		}
		return "jsres/hospital/signManage/addSign";
	}

	@RequestMapping(value="/changeUseStatus",method={RequestMethod.POST})
	@ResponseBody
	public String changeUseStatus(String signFlow,String useStatusId){
		SysUser user = GlobalContext.getCurrentUser();
		JsresSign sign = speAssignBiz.searchSignByPrimaryKey(signFlow);
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(useStatusId)) {
			//启用前查询是否有启用的签名
            List<JsresSign> signList = speAssignBiz.searchSignListByOrgFlow(user.getOrgFlow(), com.pinde.core.common.GlobalConstant.FLAG_Y);
			if (null != signList && signList.size() > 0) {
				return "已有启用或审核中数据，请先停用！";
			}
            sign.setUseStatusId(com.pinde.core.common.GlobalConstant.FLAG_Y);
			sign.setUseStatusName("启用");
			sign.setAuditStatusId("Auditing");
			sign.setAuditStatusName("待审核");
		}else{
            sign.setUseStatusId(com.pinde.core.common.GlobalConstant.FLAG_N);
			sign.setUseStatusName("停用");
			sign.setStopTime(DateUtil.getCurrDateTime2());
		}
		return speAssignBiz.saveJsresSign(sign);
	}

	@RequestMapping(value="/saveSign",method={RequestMethod.POST})
	@ResponseBody
	public String saveSign(String orgFlow,String orgName,String signUrl, Model model){
		JsresSign sign = new JsresSign();
		sign.setOrgFlow(orgFlow);
		sign.setOrgName(orgName);
		sign.setAuditStatusId("Auditing");
		sign.setAuditStatusName("待审核");
		sign.setSignUrl(signUrl);
		return speAssignBiz.saveJsresSign(sign);
	}
}

