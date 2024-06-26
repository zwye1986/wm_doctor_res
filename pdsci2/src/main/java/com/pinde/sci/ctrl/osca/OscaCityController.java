package com.pinde.sci.ctrl.osca;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.osca.*;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.osca.AssessmentProEnum;
import com.pinde.sci.enums.osca.AuditStatusEnum;
import com.pinde.sci.enums.osca.DoctorScoreEnum;
import com.pinde.sci.enums.osca.SignStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/osca/city")
public class OscaCityController extends GeneralController{
	@Autowired
	IOscaProvincialBiz provincialBiz;
	@Autowired
	IOrgBiz orgBiz;
	@Autowired
	IOscaFormCfgBiz formCfgBiz;
	@Autowired
	IOscaCityBiz oscaCityBiz;
	@Autowired
	private IOscaDoctorScoreBiz oscaDoctorScoreBiz;
	@Autowired
	private IOscaBaseBiz oscaBaseBiz;
	@Autowired
	private IDictBiz dictBiz;

	@RequestMapping("/skillsAssessmentList")
	public String list(OscaSkillsAssessment skillsAssessment,String trainingTypeId, Model model, HttpServletRequest request,Integer currentPage){
		//当前地市
		SysUser currentUser = GlobalContext.getCurrentUser();
		String currentUserOrgFlow = currentUser.getOrgFlow();
		SysOrg currentOrg = orgBiz.readSysOrg(currentUserOrgFlow);
		String orgCityId = currentOrg.getOrgCityId();

		Map<String,Object> paramMap = new HashMap<>();
		String speId = skillsAssessment.getSpeId();
		String clinicalYear = skillsAssessment.getClinicalYear();
		String orgFlow = skillsAssessment.getOrgFlow();
		paramMap.put("orgCityId",orgCityId);
		paramMap.put("speId",speId);
		paramMap.put("clinicalYear",clinicalYear);
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("isReleased","Y");
		paramMap.put("isLocal","N");
		SysDict searchDict = new SysDict();
		searchDict.setRecordStatus("Y");
		searchDict.setDictTypeId(DictTypeEnum.OscaTrainingType.getId()+"."+trainingTypeId);
		List<SysDict> dictList = dictBiz.searchDictList(searchDict);
		paramMap.put("dictList",dictList);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<OscaSkillsAssessment> skillsAssessments = oscaCityBiz.searchSkillsAssessment(paramMap);
		model.addAttribute("skillsAssessments",skillsAssessments);
		//考核时间
		Map<String,List<OscaSkillsAssessmentTime>> timeMap = new HashMap<>();
		if(skillsAssessments!=null&&skillsAssessments.size()>0){
			for(OscaSkillsAssessment oscaSkillsAssessment:skillsAssessments){
				String clinicalFlow = oscaSkillsAssessment.getClinicalFlow();
				List<OscaSkillsAssessmentTime> oscaSkillsAssessmentTimes = oscaBaseBiz.getAssessmentTimes(clinicalFlow);
				timeMap.put(clinicalFlow,oscaSkillsAssessmentTimes);
			}
		}
		model.addAttribute("timeMap",timeMap);
		model.addAttribute("timeMapSize",timeMap.size());
		//人数
		Map<String,Integer> doctorNumMap = new HashMap<>();
		if(skillsAssessments!=null&&skillsAssessments.size()>0){
			for(OscaSkillsAssessment oscaSkillsAssessment:skillsAssessments){
				String clinicalFlow = oscaSkillsAssessment.getClinicalFlow();
				int doctorNum = provincialBiz.queryDoctorNum(clinicalFlow);
				doctorNumMap.put(clinicalFlow,doctorNum);
			}
		}
		//考点：
		SysOrg org = new SysOrg();
		org.setOrgCityId(orgCityId);
		org.setIsExamOrg("Y");
		List<SysOrg> examOrgList = orgBiz.queryAllSysOrg(org);
		model.addAttribute("examOrgList", examOrgList);
		model.addAttribute("doctorNumMap",doctorNumMap);
		return "osca/city/skillsAssessmentList";
	}

	/**
	 * 市局成绩管理查询
	 */
	@RequestMapping("/gradeManageList")
	public String gradeManageList(Integer currentPage4, String orgName, String clinicalYear,String clinicalFlow,
								  String clinicalName,String speId,String ticketNumber, String actionTypeId,
								  String gradeDoctorName, String trainCategoryId, String resultId,
								  String subjectFlow,String order,String orgFlow2,
								  HttpServletRequest request, Model model) throws IOException {
		String currYear = DateUtil.getYear();
		model.addAttribute("currYear",currYear);
		//当前地市
		SysUser currentUser = GlobalContext.getCurrentUser();
		String currentUserOrgFlow = currentUser.getOrgFlow();
		SysOrg currentOrg = orgBiz.readSysOrg(currentUserOrgFlow);
		String orgCityId = currentOrg.getOrgCityId();
		//考点：
		SysOrg org = new SysOrg();
		org.setOrgCityId(orgCityId);
		org.setIsExamOrg("Y");
		List<SysOrg> examOrgList = orgBiz.queryAllSysOrg(org);
		model.addAttribute("examOrgList", examOrgList);

		if(speId==null) {
			speId="";
			List<SysDict> speList=DictTypeEnum.CheckSpe.getSysDictList();
			if(speList!=null&&speList.size()>0){
				speId=speList.get(0).getDictId();
			}
			actionTypeId= AssessmentProEnum.ProvincialPlan.getId();
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("orgCityId",orgCityId);
			paramMap.put("speId",speId);
			paramMap.put("orgFlow",orgFlow2);
			paramMap.put("isLocal","N");
			paramMap.put("role","city");
			List<OscaSkillsAssessment> osas = oscaCityBiz.searchSkillsAssessment(paramMap);
			if(speId==""){
				osas=new ArrayList<>();
			}
			if(osas!=null&&osas.size()>0){
				clinicalFlow=osas.get(0).getClinicalFlow();
				clinicalName=osas.get(0).getClinicalName();
			}
		}
		Map<String,Object> paramMap2 = new HashMap<>();
		paramMap2.put("orgCityId",orgCityId);
		paramMap2.put("speId",speId);
		paramMap2.put("actionTypeId",actionTypeId);
		paramMap2.put("clinicalYear",clinicalYear);
		paramMap2.put("orgFlow",orgFlow2);
		paramMap2.put("isLocal","N");
		paramMap2.put("role","city");
		List<OscaSkillsAssessment> osaList= oscaCityBiz.searchSkillsAssessment(paramMap2);
		if(speId==""){
			osaList=new ArrayList<>();
		}
		Map<String,Object> param = new HashMap<>();
		param.put("clinicalFlow",clinicalFlow);
		param.put("auditStatusId", AuditStatusEnum.Passed.getId());
		param.put("signStatusId", SignStatusEnum.SignIn.getId());
		param.put("ticketNumber", ticketNumber);
		param.put("gradeDoctorName", gradeDoctorName);
		param.put("trainCategoryId", trainCategoryId);
		param.put("resultId", resultId);
		param.put("order", order);
		param.put("orgName",orgName);
		param.put("orgFlow2",orgFlow2);
		param.put("role","city");
		PageHelper.startPage(currentPage4,getPageSize(request));
		List<Map<String,Object>> gradeList = oscaDoctorScoreBiz.queryGradeList(param);
		List<OscaDoctorAssessment> odaList =new ArrayList<>();
		if(StringUtil.isBlank(clinicalFlow)){
			gradeList = new ArrayList<>();
		}
		if(clinicalFlow!=null){
			odaList = oscaDoctorScoreBiz.queryDoctorList(clinicalFlow);
		}
		OscaSkillsAssessment osa = oscaDoctorScoreBiz.queryDataByFlow(clinicalFlow);
		subjectFlow="";
		if(osa!=null){
			subjectFlow=osa.getSubjectFlow();
		}
		List<OscaSubjectStation> stationList = oscaDoctorScoreBiz.queryStationList(subjectFlow);
		int passedCount = 0;
		if(null != odaList && odaList.size() > 0){
			for(OscaDoctorAssessment oda : odaList){
				if(DoctorScoreEnum.Passed.getId().equals(oda.getIsPass())){
					passedCount++;
				}
			}
		}
		DecimalFormat df = new DecimalFormat("0.00");
//		model.addAttribute("percent",(odaList == null || odaList.size() == 0)?"":df.format((double)passedCount * 100 / (double)odaList.size()));
		model.addAttribute("osa",osa);
		model.addAttribute("osaList",osaList);
		model.addAttribute("gradeList",gradeList);
		model.addAttribute("stationList",stationList);
//        model.addAttribute("clinicalName",clinicalName);
		model.addAttribute("clinicalFlow",clinicalFlow);
		model.addAttribute("subjectFlow",subjectFlow);
//        model.addAttribute("speList",speList);
		//当前市所有考点，某年份，所有专业所有学生总通过率
		Map<String,Object> paramMap3 = new HashMap<>();
		paramMap3.put("isPass","Passed");
		paramMap3.put("year",clinicalYear==null?currYear:clinicalYear);
		paramMap3.put("isLocal","N");
		paramMap3.put("orgCityId",orgCityId);
		Map<String,Object> paramMap4 = new HashMap<>();
		paramMap4.put("year",clinicalYear==null?currYear:clinicalYear);
		paramMap4.put("isLocal","N");
		paramMap4.put("orgCityId",orgCityId);
		paramMap4.put("range","1");
		String allPercent = oscaDoctorScoreBiz.getPassPercent(paramMap3,paramMap4);
		model.addAttribute("allPercent",allPercent);
		//当前市,某考点，某年份，某专业所有学生总通过率
		Map<String,Object> paramMap5 = new HashMap<>();
		paramMap5.put("isPass","Passed");
		paramMap5.put("year",clinicalYear==null?currYear:clinicalYear);
		paramMap5.put("isLocal","N");
		paramMap5.put("orgFlow",orgFlow2);
		paramMap5.put("clinicalFlow",clinicalFlow);
		Map<String,Object> paramMap6 = new HashMap<>();
		paramMap6.put("year",clinicalYear==null?currYear:clinicalYear);
		paramMap6.put("isLocal","N");
		paramMap6.put("orgFlow",orgFlow2);
		paramMap6.put("clinicalFlow",clinicalFlow);
		paramMap6.put("range","1");
		String percent = oscaDoctorScoreBiz.getPassPercent(paramMap5,paramMap6);
		model.addAttribute("percent",percent);
		return "osca/city/doctorScoreInfoList";
	}
}

