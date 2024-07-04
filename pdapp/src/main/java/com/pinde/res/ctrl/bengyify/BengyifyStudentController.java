package com.pinde.res.ctrl.bengyify;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.BengyifyGlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.MathUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.bengyify.IBengyifyAppBiz;
import com.pinde.res.biz.bengyify.IBengyifyDoctorBiz;
import com.pinde.res.biz.bengyify.IBengyifyStudentBiz;
import com.pinde.res.biz.bengyify.IBengyifyTeacherBiz;
import com.pinde.res.dao.bengyify.ext.BengyifyStudentMapper;
import com.pinde.res.enums.bengyify.DeptStatusEnum;
import com.pinde.res.model.bengyify.mo.Activity;
import com.pinde.res.model.bengyify.mo.Evaluation;
import com.pinde.res.model.bengyify.mo.MedicalInfo;
import com.pinde.res.model.bengyify.mo.StudyInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/bengyify/student")
public class BengyifyStudentController {

	private static Logger logger = LoggerFactory.getLogger(BengyifyStudentController.class);

	@Autowired
	private IBengyifyAppBiz bengyifyAppBiz;
	@Autowired
	private IBengyifyStudentBiz bengyifyStudentBiz;
	@Autowired
	private IBengyifyTeacherBiz bengyifyTeacherBiz;
	@Autowired
	private BengyifyStudentMapper bengyifystudentMapper;
	@Autowired
	private IBengyifyDoctorBiz bengyifyDoctorBiz;
	@Value("#{configProperties['bengyify.funcFlow.0001.httpurl']}")
	private String bengyifyFuncFlow0001HttpUrl;

	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(getClass().getCanonicalName()+" some error happened",e);
		return "res/bengyify/500";
    }

	@RequestMapping("/schPlanList")
	public String schPlanList(String userFlow, String searchData, Integer pageIndex, Integer pageSize,
							  Model model , HttpServletRequest request) throws UnsupportedEncodingException{
		String schStatusId = "";
		String schDeptName = "";
		if(StringUtil.isNotBlank(searchData)){
			searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
			Map<String , String> searchMap = (Map<String, String>) JSON.parse(searchData);
			model.addAttribute("searchMap" , searchMap);
			schStatusId = searchMap.get("schStatusId");
			schDeptName = searchMap.get("schDeptName");
		}

		String result = "res/bengyify/student/schPlanList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(schStatusId)){
			schStatusId = DeptStatusEnum.Entering.getId();
		}
		if(pageIndex==null){
			pageIndex = BengyifyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = BengyifyGlobalConstant.DEFAULT_PAGE_SIZE;
		}


		Map<String,String> userInfo = bengyifyAppBiz.readUser(userFlow);
		if(userInfo==null){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SERVICEERROR);
		}
		String userType = userInfo.get("userType");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SERVICEERROR);
		}
		int userTypeInt = Integer.valueOf(userType);

		int examStatusId = userTypeInt==4?9:7;

		List<Map<String,Object>> schPlans = bengyifyStudentBiz.getPlanList(userFlow, schStatusId, schDeptName , pageIndex, pageSize,examStatusId);
		model.addAttribute("schPlans" , schPlans);
		model.addAttribute("dataCount", bengyifyStudentBiz.getPlanList(userFlow,schStatusId,schDeptName , 1,Integer.MAX_VALUE,examStatusId).size());

		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/schFuncList"},method={RequestMethod.GET})
	public String schFuncList(String userFlow,String schDeptFlow,Model model){
		String result = "res/bengyify/student/schFuncList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DEPTFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DEPTFLOW_NULL);
			return result;
		}

		Map<String,String> doctor = bengyifyAppBiz.readUser(userFlow);
		model.addAttribute("doctor" , doctor);
		//查看是否填写过入科教育
		Map<String,Object> deptEdu = bengyifyStudentBiz.readEnteredDeptEdu(schDeptFlow);
		model.addAttribute("deptEdu" , deptEdu);

		//查看是否评价过科室
		List<Map<String,Object>> marksDept = bengyifyStudentBiz.getMarksForDept(userFlow, schDeptFlow);
		model.addAttribute("marksDept" , marksDept);

		//查看是否评价过老师
    	List<Map<String,Object>> marksTec = bengyifyStudentBiz.getMarksForTec(userFlow, schDeptFlow);
		model.addAttribute("marksTec" , marksTec);

		//Dops量表
		Map<String,Object> outDops = bengyifyTeacherBiz.readOutDops(schDeptFlow);
		model.addAttribute("outDops" , outDops);

		//Minicex
		Map<String,Object> outMiniCex = bengyifyTeacherBiz.readOutMiniCex(schDeptFlow);
		model.addAttribute("outMiniCex" , outMiniCex);

		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/categoryProgress"},method={RequestMethod.GET})
	public String categoryProgress(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow,
								   Integer pageIndex, Integer pageSize, String dataCount, Model model){
		String result = "res/bengyify/student/categoryProgress";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DEPTFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DEPTFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
		model.addAttribute("dataCount", 0);
		if(pageIndex==null){
			pageIndex = BengyifyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = BengyifyGlobalConstant.DEFAULT_PAGE_SIZE;
		}

		//病种
		if("D002".equals(funcFlow)){
			List<Map<String,Object>> caseClassCatas = bengyifyDoctorBiz.getCaseClassCatas(userFlow,schDeptFlow,pageIndex, pageSize);
			for(Map<String,Object> cata : caseClassCatas){
//				String neededNum = StringUtil.defaultIfEmpty((String) cata.get("neededNum"),"0");
//				String finishedNum = StringUtil.defaultIfEmpty((String) cata.get("finishedNum"),"0");
//				int iNeededNum = Integer.parseInt(neededNum);
//				int iFinishedNum = Integer.parseInt(finishedNum);
				Integer iNeededNum = (Integer) cata.get("neededNum");
				Integer iFinishedNum =(Integer) cata.get("finishedNum");
				Integer iAppealNum =(Integer) cata.get("appealNum");
				iFinishedNum = iFinishedNum+iAppealNum;
				float process = 0.0f;
				if(iNeededNum==0&&iFinishedNum>0){
					process = 100.0f;
				}else if(iNeededNum==0&&iFinishedNum==0){
					process = 0.0f;
				}else{
					process = (float) MathUtil.scale2Double(100.f*(float)iFinishedNum/(float)iNeededNum);
				}
				cata.put("progress", process+"");

			}
			model.addAttribute("caseClassCatas" , caseClassCatas);
			model.addAttribute("dataCount", bengyifyDoctorBiz.getCaseClassCatas(userFlow,schDeptFlow,1, Integer.MAX_VALUE).size());
		}
		//操作技能
		if("D003".equals(funcFlow)){
			List<Map<String,Object>> operateSkillCatas = bengyifyDoctorBiz.getOperateSkillCatas(userFlow,schDeptFlow,pageIndex, pageSize);
			for(Map<String,Object> cata : operateSkillCatas){
//				String neededNum = StringUtil.defaultIfEmpty((String) cata.get("neededNum"),"0");
//				String finishedNum = StringUtil.defaultIfEmpty((String) cata.get("finishedNum"),"0");
//				int iNeededNum = Integer.parseInt(neededNum);
//				int iFinishedNum = Integer.parseInt(finishedNum);
				Integer iNeededNum = (Integer) cata.get("neededNum");
				Integer iFinishedNum =(Integer) cata.get("finishedNum");
				Integer iAppealNum =(Integer) cata.get("appealNum");
				iFinishedNum = iFinishedNum+iAppealNum;
				float process = 0.0f;
				if(iNeededNum==0&&iFinishedNum>0){
					process = 100.0f;
				}else if(iNeededNum==0&&iFinishedNum==0){
					process = 0.0f;
				}else{
					process = (float) MathUtil.scale2Double(100.f*(float)iFinishedNum/(float)iNeededNum);
				}
				cata.put("progress", process+"");

			}
			model.addAttribute("operateSkillCatas" , operateSkillCatas);
			model.addAttribute("dataCount", bengyifyDoctorBiz.getOperateSkillCatas(userFlow,schDeptFlow,1, Integer.MAX_VALUE).size());
		}
		//手术
		if("D004".equals(funcFlow)){
			List<Map<String,Object>> posSkillCatas = bengyifyDoctorBiz.getPOSSkillCatas(userFlow,schDeptFlow,pageIndex, pageSize);
			for(Map<String,Object> cata : posSkillCatas){
//				String neededNum = StringUtil.defaultIfEmpty((String) cata.get("neededNum"),"0");
//				String finishedNum = StringUtil.defaultIfEmpty((String) cata.get("finishedNum"),"0");
//				int iNeededNum = Integer.parseInt(neededNum);
//				int iFinishedNum = Integer.parseInt(finishedNum);
				Integer iNeededNum = (Integer) cata.get("neededNum");
				Integer iFinishedNum =(Integer) cata.get("finishedNum");
				Integer iAppealNum =(Integer) cata.get("appealNum");
				iFinishedNum = iFinishedNum+iAppealNum;
				float process = 0.0f;
				if(iNeededNum==0&&iFinishedNum>0){
					process = 100.0f;
				}else if(iNeededNum==0&&iFinishedNum==0){
					process = 0.0f;
				}else{
					process = (float) MathUtil.scale2Double(100.f*(float)iFinishedNum/(float)iNeededNum);
				}
				cata.put("progress", process+"");
			}
			model.addAttribute("posSkillCatas" , posSkillCatas);
			model.addAttribute("dataCount", bengyifyDoctorBiz.getPOSSkillCatas(userFlow,schDeptFlow,1, Integer.MAX_VALUE).size());
		}

		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/dataList"},method={RequestMethod.GET})
	public String dataList(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow, String cataFlow,
						   Integer pageIndex, Integer pageSize, String dataCount, String searchData,
						   HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException{
		Map<String , String> searchMap = new HashMap<String, String>();
		if(StringUtil.isNotBlank(searchData)){
			searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
			searchMap = (Map<String, String>) JSON.parse(searchData);
			model.addAttribute("searchMap" , searchMap);
		}
		String result = "res/bengyify/student/dataList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DEPTFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DEPTFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)||!("dataInputNN".equals(funcTypeId)||"dataInput1N".equals(funcTypeId) || "dataInput11".equals(funcTypeId))){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
		if(pageIndex==null){
			pageIndex = BengyifyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = BengyifyGlobalConstant.DEFAULT_PAGE_SIZE;
		}
		model.addAttribute("pageIndex" , pageIndex);
		model.addAttribute("pageSize" , pageSize);
		model.addAttribute("dataCount" , 0);
		//入科教育
		//ios不用
//		if("0001".equals(funcFlow)){
//			Map<String,Object> enteredDeptEdu = bengyifyStudentBiz.readEnteredDeptEdu(schDeptFlow);
//			model.addAttribute("enteredDeptEdu" , enteredDeptEdu);
//			model.addAttribute("dataCount" , 1);
//		}
		//学习情况
		if("0002".equals(funcFlow)){
			List<StudyInfo> studyInfos = bengyifyStudentBiz.getStudyInfos(schDeptFlow, pageIndex, pageSize);
			model.addAttribute("studyInfos" , studyInfos);
			model.addAttribute("dataCount", bengyifyStudentBiz.getStudyInfos(schDeptFlow, 1, Integer.MAX_VALUE).size());
		}
		//病例与管床
		if("0003".equals(funcFlow)){
			List<MedicalInfo> medicalInfos = bengyifyStudentBiz.getMedicalInfos(schDeptFlow, pageIndex, pageSize);
			for(MedicalInfo medicalInfo : medicalInfos){
				String name = StringUtil.defaultString((String)medicalInfo.getName());
				if(name.length()>0){
					name = name.substring(0, name.length() - 1) + "★";
					medicalInfo.setName(name);
				}
			}
			int count = bengyifyStudentBiz.getMedicalInfos(schDeptFlow, 1, Integer.MAX_VALUE).size();
			model.addAttribute("medicalInfos" , medicalInfos);
			model.addAttribute("dataCount" , count);
		}
		//教学活动
		if("0004".equals(funcFlow)){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userFlow", userFlow);
			params.put("deptFlow", schDeptFlow);
			String join = searchMap.get("join");
			if(StringUtil.isBlank(join)){
				join = "NotEntered";
			}
			params.put("join", join);
			String mainSpeaker = searchMap.get("mainSpeaker");
			params.put("mainSpeaker", mainSpeaker);
			List<Activity> activitys = bengyifyStudentBiz.getActicitys(params , pageIndex , pageSize);
			model.addAttribute("activitys" , activitys);
			int count =  bengyifyStudentBiz.getActicitys(params , 1 , Integer.MAX_VALUE).size();
			model.addAttribute("dataCount" , count);
		}

		//大病历
		if("D001".equals(funcFlow)){
			List<Map<String,Object>> bigHistorys = bengyifyDoctorBiz.getBigHistorys(userFlow,schDeptFlow, pageIndex, pageSize);
			for(Map<String,Object> bigHistory : bigHistorys){
				String RecData2 = StringUtil.defaultString((String)bigHistory.get("RecData2"));
				if(RecData2.length()>0){
					RecData2 = RecData2.substring(0, RecData2.length() - 1) + "★";
					bigHistory.put("RecData2", RecData2);
				}
			}
			model.addAttribute("bigHistorys" , bigHistorys);
			model.addAttribute("dataCount", bengyifyDoctorBiz.getBigHistorys(userFlow,schDeptFlow, 1, Integer.MAX_VALUE).size());
		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            List<Map<String,Object>> outPatients = bengyifyDoctorBiz.getOutPatients(userFlow,schDeptFlow, pageIndex, pageSize);
            for(Map<String,Object> outPatient : outPatients){
                String RecData2 = StringUtil.defaultString((String)outPatient.get("RecData2"));
                if(RecData2.length()>0){
                    RecData2 = RecData2.substring(0,RecData2.length()-1)+"★";
                    outPatient.put("RecData2", RecData2);
                }
            }
            model.addAttribute("outPatients" , outPatients);
            model.addAttribute("dataCount", bengyifyDoctorBiz.getOutPatients(userFlow,schDeptFlow, 1, Integer.MAX_VALUE).size());
        }

        //病种
		if("D002".equals(funcFlow)){
			List<Map<String,Object>> caseClasses = bengyifyDoctorBiz.getCaseClasses(userFlow,schDeptFlow,cataFlow, pageIndex, pageSize);
			for(Map<String,Object> caseClass : caseClasses){
				String RecData3 = StringUtil.defaultString((String)caseClass.get("RecData3"));
				if(RecData3.length()>0){
					RecData3 = RecData3.substring(0, RecData3.length() - 1) + "★";
					caseClass.put("RecData3", RecData3);
				}
			}
			model.addAttribute("caseClasses" , caseClasses);
			model.addAttribute("dataCount", bengyifyDoctorBiz.getCaseClasses(userFlow,schDeptFlow,cataFlow, 1, Integer.MAX_VALUE).size());
		}
		//操作技能
		if("D003".equals(funcFlow)){
			List<Map<String,Object>> operateSkills = bengyifyDoctorBiz.getOperateSkills(userFlow,schDeptFlow,cataFlow, pageIndex, pageSize);
			for(Map<String,Object> operateSkill : operateSkills){
				String RecData3 = StringUtil.defaultString((String)operateSkill.get("RecData3"));
				if(RecData3.length()>0){
					RecData3 = RecData3.substring(0, RecData3.length() - 1) + "★";
					operateSkill.put("RecData3", RecData3);
				}
			}
			model.addAttribute("operateSkills" , operateSkills);
			model.addAttribute("dataCount", bengyifyDoctorBiz.getOperateSkills(userFlow,schDeptFlow,cataFlow, 1, Integer.MAX_VALUE).size());
		}
		//手术
		if("D004".equals(funcFlow)){
			List<Map<String,Object>> posSkills = bengyifyDoctorBiz.getPOSSkills(userFlow,schDeptFlow,cataFlow, pageIndex, pageSize);
			for(Map<String,Object> posSkill : posSkills){
				String RecData3 = StringUtil.defaultString((String)posSkill.get("RecData3"));
				if(RecData3.length()>0){
					RecData3 = RecData3.substring(0, RecData3.length() - 1) + "★";
					posSkill.put("RecData3", RecData3);
				}
			}
			model.addAttribute("posSkills" , posSkills);
			model.addAttribute("dataCount", bengyifyDoctorBiz.getPOSSkills(userFlow,schDeptFlow,cataFlow, 1, Integer.MAX_VALUE).size());
		}

		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/addData"},method={RequestMethod.POST})
	public String addData(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow,
						  String cataFlow, HttpServletRequest request , Model model){

		String result = "res/bengyify/student/addData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DEPTFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DEPTFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)||!("dataInputNN".equals(funcTypeId)||"dataInput1N".equals(funcTypeId) || "dataInput11".equals(funcTypeId))){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
		//入科教育
		if("0001".equals(funcFlow)){
			Map<String,Object> exitEnteredDeptEdu = bengyifyStudentBiz.readEnteredDeptEdu(schDeptFlow);
			if(exitEnteredDeptEdu==null){
				Map<String,Object> enteredDeptEdu = new HashMap<String,Object>();
				enteredDeptEdu.put("userFlow", userFlow);
				enteredDeptEdu.put("schDeptFlow", schDeptFlow);
				enteredDeptEdu.put("tecName", request.getParameter("tecName"));
				enteredDeptEdu.put("dateTime", request.getParameter("dateTime"));
				String dataFlow = bengyifyStudentBiz.addEnteredDeptEdu(enteredDeptEdu);
//				model.addAttribute("dataFlow" , dataFlow);
			}
		}
		//学习情况
		if("0002".equals(funcFlow)){
			StudyInfo studyInfo = new StudyInfo();
			String name = request.getParameter("name");
			String no = request.getParameter("no");
			String dateTime = request.getParameter("dateTime");
			String study = request.getParameter("study");
			String master = request.getParameter("master");
			studyInfo.setName(name);
			studyInfo.setNo(no);
			studyInfo.setDateTime(dateTime);
			studyInfo.setStudy(study);
			studyInfo.setMaster(master);
			studyInfo.setUserFlow(userFlow);
			studyInfo.setSchDeptFlow(schDeptFlow);
			String dataFlow = bengyifyStudentBiz.addStudyInfo(studyInfo);
//			model.addAttribute("dataFlow" , dataFlow);
		}
		//病例与管床
		if("0003".equals(funcFlow)){
			MedicalInfo medicalInfo = new MedicalInfo();
			String name = request.getParameter("name");
			String inDateTime = request.getParameter("inDateTime");
			String no = request.getParameter("no");
			String patient = request.getParameter("patient");
			String diagnosis = request.getParameter("diagnosis");
			String tecName = request.getParameter("tecName");
			medicalInfo.setName(name);
			medicalInfo.setInDateTime(inDateTime);
			medicalInfo.setNo(no);
			medicalInfo.setPatient(patient);
			medicalInfo.setDiagnosis(diagnosis);
			medicalInfo.setTecName(tecName);
			medicalInfo.setUserFlow(userFlow);
			medicalInfo.setSchDeptFlow(schDeptFlow);
			String dataFlow = bengyifyStudentBiz.addMedicalInfo(medicalInfo);
//			model.addAttribute("dataFlow" , dataFlow);

		}
		//评价科室
		if("0005".equals(funcFlow)){
			Map<String, Object> examInfo = new HashMap<String, Object>();
			List<Map<String,Object>> marks = new ArrayList<Map<String,Object>>();
			examInfo.put("examInfoType", "6");
			examInfo.put("userFlow", userFlow);
			examInfo.put("schDeptFlow", schDeptFlow);

			String examInfoDF = request.getParameter("total_score");
			examInfo.put("examInfoDF", examInfoDF);
			List<Map<String, Object>> assessTmp = bengyifyStudentBiz.getExamItems("6");
			for(Map<String, Object> tmp:assessTmp){
				String reqItemId = (String) tmp.get("reqItemId");
				String score = request.getParameter(reqItemId+"_score");
				String cause = request.getParameter(reqItemId+"_cause");
				Map<String,Object> e = new HashMap<String,Object>();
				e.put("markDF", score);
				e.put("markKF", cause);
				e.put("reqItemId", reqItemId);
				marks.add(e);
			}
			bengyifyStudentBiz.addAssess(examInfo , marks );
		}
		//评价带教老师
		if("0006".equals(funcFlow)){
			Map<String, Object> examInfo = new HashMap<String, Object>();
			List<Map<String,Object>> marks = new ArrayList<Map<String,Object>>();
			examInfo.put("examInfoType", "0");
			examInfo.put("userFlow", userFlow);
			examInfo.put("schDeptFlow", schDeptFlow);

			String examInfoDF = request.getParameter("total_score");
			examInfo.put("examInfoDF", examInfoDF);
			List<Map<String, Object>> assessTmp = bengyifyStudentBiz.getExamItems("0");
			for(Map<String, Object> tmp:assessTmp){
				String reqItemId = (String) tmp.get("reqItemId");
				String score = request.getParameter(reqItemId+"_score");
				String cause = request.getParameter(reqItemId+"_cause");
				Map<String,Object> e = new HashMap<String,Object>();
				e.put("markDF", score);
				e.put("markKF", cause);
				e.put("reqItemId", reqItemId);
				marks.add(e);
			}

			bengyifyStudentBiz.addAssess(examInfo , marks );
		}
		//自我评价
		if("0007".equals(funcFlow)){
			Evaluation temp = bengyifyStudentBiz.readOutSecBrief(schDeptFlow);
			if(temp==null){
				Evaluation evaluation = new Evaluation();
				String briefRequrie = request.getParameter("briefRequrie");
				evaluation.setBriefRequrie(briefRequrie);
				evaluation.setUserFlow(userFlow);
				evaluation.setSchDeptFlow(schDeptFlow);
				bengyifyStudentBiz.addOutSecBrief(evaluation);
//				model.addAttribute("dataFlow" , dataFlow);
			}
		}

        //大病历
  		if("D001".equals(funcFlow)){
  			bengyifyDoctorBiz.addBigHistory(userFlow,schDeptFlow,request);
  		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            bengyifyDoctorBiz.addOutPatient(userFlow,schDeptFlow,request);
        }
  		//病种
		if("D002".equals(funcFlow)){
			bengyifyDoctorBiz.addCaseClass(userFlow,schDeptFlow,cataFlow,request);
		}
		//操作技能
		if("D003".equals(funcFlow)){
			bengyifyDoctorBiz.addOperateSkill(userFlow,schDeptFlow,cataFlow,request);
		}
		//手术
		if("D004".equals(funcFlow)){
			bengyifyDoctorBiz.addPOSSkill(userFlow,schDeptFlow,cataFlow,request);
		}
		//出科小结
		if("D005".equals(funcFlow)){
			Map<String, Object> temp = bengyifyDoctorBiz.readOutSecBrief(schDeptFlow);
			if(temp==null){
				bengyifyDoctorBiz.addOutSecBrief(userFlow,schDeptFlow,request);
			}
		}

		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/viewData"},method={RequestMethod.GET})
	public String viewData(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow,
						   String cataFlow, String dataFlow, HttpServletRequest request, HttpServletResponse response, Model model){

		String result = "res/bengyify/student/viewData";

		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DEPTFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DEPTFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)||!("dataInputNN".equals(funcTypeId)||"dataInput1N".equals(funcTypeId) || "dataInput11".equals(funcTypeId))){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
//		if(StringUtil.isBlank(dataFlow)){
//			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DATAFLOW_NULL);
//			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DATAFLOW_NULL);
//			return result;
//		}

		//入科教育
        if("0001".equals(funcFlow)){
        	Map<String,Object> enteredDeptEdu = bengyifyStudentBiz.readEnteredDeptEdu(schDeptFlow);
        	model.addAttribute("enteredDeptEdu" , enteredDeptEdu);
		}
        //学习情况
        if("0002".equals(funcFlow)){
        	if(StringUtil.isNotBlank(dataFlow)){
            	StudyInfo studyInfo = bengyifyStudentBiz.readStudyInfo(dataFlow);
            	model.addAttribute("studyInfo" , studyInfo);
        	}
        }
        //病例与管床
        if("0003".equals(funcFlow)){
        	if(StringUtil.isNotBlank(dataFlow)){
            	MedicalInfo medicalInfo = bengyifyStudentBiz.readMedicalInfo(dataFlow);
            	model.addAttribute("medicalInfo" , medicalInfo);
        	}
        }
        //教学活动
        if("0004".equals(funcFlow)){
        	Activity activity = bengyifyStudentBiz.findActivity(dataFlow, userFlow);
        	model.addAttribute("activity" , activity);
        }
        //评价科室
        if("0005".equals(funcFlow)){
        	Map<String,Object> examInfo = bengyifyStudentBiz.readExamInfo("6", schDeptFlow);
        	model.addAttribute("examInfo" , examInfo);
        	//获取评价模板
        	List<Map<String, Object>> assessTmp = bengyifyStudentBiz.getExamItems("6");
        	model.addAttribute("assessTmp" , assessTmp);
        	List<Map<String,Object>> marks = bengyifyStudentBiz.getMarksForDept(userFlow, schDeptFlow);
        	model.addAttribute("marks" , marks);
        }
        //评价带教老师
        if("0006".equals(funcFlow)){
        	Map<String,Object> examInfo = bengyifyStudentBiz.readExamInfo("0", schDeptFlow);
        	model.addAttribute("examInfo" , examInfo);

			List<Map<String, Object>> assessTmp = bengyifyStudentBiz.getExamItems("0");
        	model.addAttribute("assessTmp" , assessTmp);
        	List<Map<String,Object>> marks = bengyifyStudentBiz.getMarksForTec(userFlow, schDeptFlow);
        	model.addAttribute("marks" , marks);
        }
        //自我评价
        if("0007".equals(funcFlow)){
        	Evaluation evaluation = bengyifyStudentBiz.readOutSecBrief(schDeptFlow);
        	model.addAttribute("evaluation" , evaluation);
        }
        //DOPS评估表
        if("0008".equals(funcFlow)){
    		Map<String,Object> outDops = bengyifyTeacherBiz.readOutDops(schDeptFlow);
    		model.addAttribute("outDops" , outDops);

			Map<String,Object> examInfo = bengyifyTeacherBiz.readExamInfo(schDeptFlow,"11");
        	model.addAttribute("examInfo" , examInfo);
			//查询科室名称
			String secName = bengyifystudentMapper.selectSecName(schDeptFlow);
			secName = StringUtil.defaultString(secName);
			String ExamItemID = null;
			List<Map<String,String>> examItemIdList = bengyifystudentMapper.selectExamItemByType("11");
			if(examItemIdList!=null&&examItemIdList.size()>0){
				ExamItemID = examItemIdList.get(0).get("itemId");
				for(Map<String,String> itemMap:examItemIdList)
				{
					String itemId=itemMap.get("itemId");
					String itemName=itemMap.get("itemName");
					if(itemName.contains(secName)){
						ExamItemID=itemId;
					}
				}
			}
			List<Map<String, Object>> assessTmp = bengyifyStudentBiz.getExamItemsByItemId("11",ExamItemID);
        	model.addAttribute("assessTmp" , assessTmp);

			List<Map<String,Object>> marks = bengyifyTeacherBiz.getMarks(schDeptFlow,"11");
        	model.addAttribute("marks" , marks);
        }
        //Mini-CEX评估表
        if("0009".equals(funcFlow)){
    		Map<String,Object> outMiniCex = bengyifyTeacherBiz.readOutMiniCex(schDeptFlow);
    		model.addAttribute("outMiniCex" , outMiniCex);

			Map<String,Object> examInfo = bengyifyTeacherBiz.readExamInfo(schDeptFlow,"12");
        	model.addAttribute("examInfo" , examInfo);

			List<Map<String, Object>> assessTmp = bengyifyStudentBiz.getExamItems("12");
        	model.addAttribute("assessTmp" , assessTmp);

			List<Map<String,Object>> marks = bengyifyTeacherBiz.getMarks(schDeptFlow,"12");
        	model.addAttribute("marks" , marks);
        }

		//大病历
  		if("D001".equals(funcFlow)){
  			if(StringUtil.isNotBlank(dataFlow)){
  	  			Map<String,Object> bigHistory = bengyifyDoctorBiz.readBigHistory(userFlow,schDeptFlow,dataFlow);
  				model.addAttribute("bigHistory" , bigHistory);
  			}
  		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            if(StringUtil.isNotBlank(dataFlow)){
                Map<String,Object> outPatient = bengyifyDoctorBiz.readOutPatient(userFlow,schDeptFlow,dataFlow);
                model.addAttribute("outPatient" , outPatient);
            }
        }
  		//病种
		if("D002".equals(funcFlow)){
			if(StringUtil.isNotBlank(dataFlow)){
  	  			Map<String,Object> caseClass = bengyifyDoctorBiz.readCaseClass(userFlow,schDeptFlow,dataFlow);
  				model.addAttribute("caseClass" , caseClass);
  			}
			if(StringUtil.isNotBlank(cataFlow)){
				String caseClassCataName = bengyifyDoctorBiz.readCaseClassCataName(cataFlow);
				model.addAttribute("caseClassCataName" , caseClassCataName);
			}
		}
		//操作技能
		if("D003".equals(funcFlow)){
			if(StringUtil.isNotBlank(dataFlow)){
  	  			Map<String,Object> operateSkill = bengyifyDoctorBiz.readOperateSkill(userFlow,schDeptFlow,dataFlow);
  				model.addAttribute("operateSkill" , operateSkill);
  			}
			if(StringUtil.isNotBlank(cataFlow)){
				String operateSkillCataName = bengyifyDoctorBiz.readOperateSkillCataName(cataFlow);
				model.addAttribute("operateSkillCataName" , operateSkillCataName);
			}
		}
		//手术
		if("D004".equals(funcFlow)){
			if(StringUtil.isNotBlank(dataFlow)){
  	  			Map<String,Object> posSkill = bengyifyDoctorBiz.readPOSSkill(userFlow,schDeptFlow,dataFlow);
  				model.addAttribute("posSkill" , posSkill);
  			}
			if(StringUtil.isNotBlank(cataFlow)){
				String posSkillCataName = bengyifyDoctorBiz.readPOSSkillCataName(cataFlow);
				model.addAttribute("posSkillCataName" , posSkillCataName);
			}
		}
		//出科小结
		if("D005".equals(funcFlow)){
			Map<String,String> doctor = bengyifyAppBiz.readUser(userFlow);
			model.addAttribute("doctor" , doctor);

			Map<String, Object> outSecBrief = bengyifyDoctorBiz.readOutSecBrief(schDeptFlow);
			model.addAttribute("outSecBrief" , outSecBrief);
		}
		model.addAttribute("nfyyFuncFlow0001HttpUrl", bengyifyFuncFlow0001HttpUrl);
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/modData"},method={RequestMethod.PUT , RequestMethod.POST})
	public String modData(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow,
						  String cataFlow, String dataFlow, HttpServletRequest request , Model model){

		String result = "res/bengyify/student/modData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DEPTFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DEPTFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)||!("dataInputNN".equals(funcTypeId)||"dataInput1N".equals(funcTypeId) || "dataInput11".equals(funcTypeId))){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
//		if(StringUtil.isBlank(dataFlow)){
//			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DATAFLOW_NULL);
//			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DATAFLOW_NULL);
//			return result;
//		}
		//入科教育
		//暂时不用修改功能
		if("0001".equals(funcFlow)){
			Map<String,Object> enteredDeptEdu = new HashMap<String,Object>();
			enteredDeptEdu.put("userFlow", userFlow);
			enteredDeptEdu.put("schDeptFlow", schDeptFlow);
			enteredDeptEdu.put("tecName", request.getParameter("tecName"));
			enteredDeptEdu.put("dateTime", request.getParameter("dateTime"));
			bengyifyStudentBiz.modEnteredDeptEdu(enteredDeptEdu);
//			model.addAttribute("dataFlow" , dataFlow);
		}
		//学习情况
		if("0002".equals(funcFlow)){
			StudyInfo studyInfo = new StudyInfo();
			String name = request.getParameter("name");
			String no = request.getParameter("no");
			String dateTime = request.getParameter("dateTime");
			String study = request.getParameter("study");
			String master = request.getParameter("master");
			studyInfo.setDataFlow(dataFlow);
			studyInfo.setName(name);
			studyInfo.setNo(no);
			studyInfo.setDateTime(dateTime);
			studyInfo.setStudy(study);
			studyInfo.setMaster(master);
			studyInfo.setUserFlow(userFlow);
			studyInfo.setSchDeptFlow(schDeptFlow);
			bengyifyStudentBiz.modStudyInfo(studyInfo );
		}
		//病例与管床
		if("0003".equals(funcFlow)){
			MedicalInfo medicalInfo = new MedicalInfo();
			String name = request.getParameter("name");
			String inDateTime = request.getParameter("inDateTime");
			String no = request.getParameter("no");
			String patient = request.getParameter("patient");
			String diagnosis = request.getParameter("diagnosis");
			String tecName = request.getParameter("tecName");
			medicalInfo.setDataFlow(dataFlow);
			medicalInfo.setName(name);
			medicalInfo.setInDateTime(inDateTime);
			medicalInfo.setNo(no);
			medicalInfo.setPatient(patient);
			medicalInfo.setDiagnosis(diagnosis);
			medicalInfo.setTecName(tecName);
			medicalInfo.setUserFlow(userFlow);
			medicalInfo.setSchDeptFlow(schDeptFlow);
			bengyifyStudentBiz.modMedicalInfo(medicalInfo);
		}
		//教学活动
		if("0004".equals(funcFlow)){
			Activity exitactivity = bengyifyStudentBiz.findActivity(dataFlow, userFlow);
			Integer score = exitactivity.getScore();
			if(score==null){
				//是否可参加
				Activity activity = bengyifyStudentBiz.readActivity(dataFlow);
				String currDateTime = DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
				String startDateTime = activity.getTime();

				int sing = (int) DateUtil.signMinutesBetweenTowDate(currDateTime, startDateTime);
				if (sing >= -10) {
					bengyifyStudentBiz.joinActivity(dataFlow, schDeptFlow , userFlow);
				}else{
					model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_NOT_JOIN_ACTIVITY);
					model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_NOT_JOIN_ACTIVITY);
					return result;
				}
			}else if(score>=0){
				Activity exitActivity = bengyifyStudentBiz.readActivity(dataFlow);
			    String endDateTime = exitActivity.getEndTime();
			    String currDateTime = DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
			    int sing = (int) DateUtil.signMinutesBetweenTowDate(endDateTime, currDateTime);
			    if(score==0&&sing>=0&&sing<=BengyifyGlobalConstant.PLAY_SCORE_ACTIVITY_TIME){
			    	Activity activity = new Activity();
					activity.setDataFlow(dataFlow);
					activity.setUserFlow(userFlow);
					int mdmq = Integer.parseInt(request.getParameter("mdmq"));
					int gfsl = Integer.parseInt(request.getParameter("gfsl"));
					int jxdw = Integer.parseInt(request.getParameter("jxdw"));
					int xgh = Integer.parseInt(request.getParameter("xgh"));
					activity.setMdmq(mdmq);
					activity.setGfsl(gfsl);
					activity.setJxdw(jxdw);
					activity.setXgh(xgh);
					bengyifyStudentBiz.scoreActivity(activity);
				}else{
			    	model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_NOT_PLAY_SCORE_ACTIVITY);
					model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_NOT_PLAY_SCORE_ACTIVITY);
					return result;
			    }
			}
		}
		//自我评价
		if("0007".equals(funcFlow)){
			String briefRequrie = request.getParameter("briefRequrie");
			bengyifyStudentBiz.modOutSecBrief(schDeptFlow, briefRequrie);
		}
		//DOPS评估表
        if("0008".equals(funcFlow)){
        	bengyifyStudentBiz.saveOutDops(userFlow, schDeptFlow,request);
		}
        //Mini-CEX评估表
        if("0009".equals(funcFlow)){
        	bengyifyStudentBiz.saveOutMiniCex(userFlow, schDeptFlow,request);
		}
        //大病历
  		if("D001".equals(funcFlow)){
  			bengyifyDoctorBiz.modBigHistory(userFlow,schDeptFlow,dataFlow,request);
  		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            bengyifyDoctorBiz.modOutPatient(userFlow,schDeptFlow,dataFlow,request);
        }
  		//病种
		if("D002".equals(funcFlow)){
			bengyifyDoctorBiz.modCaseClass(userFlow,schDeptFlow,cataFlow,dataFlow,request);
		}
		//操作技能
		if("D003".equals(funcFlow)){
			bengyifyDoctorBiz.modOperateSkill(userFlow,schDeptFlow,cataFlow,dataFlow,request);
		}
		//手术
		if("D004".equals(funcFlow)){
			bengyifyDoctorBiz.modPOSSkill(userFlow,schDeptFlow,cataFlow,dataFlow,request);
		}
		//出科小结
		if("D005".equals(funcFlow)){
			Map<String, Object> temp = bengyifyDoctorBiz.readOutSecBrief(schDeptFlow);
			if(temp!=null){
				bengyifyDoctorBiz.modOutSecBrief(userFlow,schDeptFlow,request);
			}
		}
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/deleteData"},method={RequestMethod.POST , RequestMethod.DELETE})
	public String deleteData(String userFlow,String schDeptFlow,String funcTypeId,String funcFlow,
			String dataFlow,HttpServletRequest request , Model model){
		String result = "res/bengyify/student/deleteData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DEPTFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DEPTFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)||!("dataInputNN".equals(funcTypeId)||"dataInput1N".equals(funcTypeId) || "dataInput11".equals(funcTypeId))){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
		if(StringUtil.isBlank(dataFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DATAFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DATAFLOW_NULL);
			return result;
		}
		//学习情况
		if("0002".equals(funcFlow)){
			bengyifyStudentBiz.delStudyInfo(dataFlow);
		}
		//病例与管床
		if("0003".equals(funcFlow)){
			bengyifyStudentBiz.delMedicalInfo(dataFlow);
		}
		//大病历
  		if("D001".equals(funcFlow)){
  			bengyifyDoctorBiz.delBigHistory(userFlow,schDeptFlow,dataFlow);
  		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            bengyifyDoctorBiz.delOutPatient(userFlow,schDeptFlow,dataFlow);
        }
  		//病种
		if("D002".equals(funcFlow)){
			bengyifyDoctorBiz.delCaseClass(userFlow,schDeptFlow,dataFlow);
		}
		//操作技能
		if("D003".equals(funcFlow)){
			bengyifyDoctorBiz.delOperateSkill(userFlow,schDeptFlow,dataFlow);
		}
		//手术
		if("D004".equals(funcFlow)){
			bengyifyDoctorBiz.delPOSSkill(userFlow,schDeptFlow,dataFlow);
		}
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/studentSignIn"},method ={RequestMethod.POST})
	public String studentSignIn(String userFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/bengyify/student/studentSignIn";
		}
		String nowDay=DateUtil.getCurrDate();
		List<Map<String, Object>> list=bengyifyStudentBiz.getUserSignList(nowDay,userFlow);
		int count=0;
		if(list!=null){
			count=list.size();
		}
		model.addAttribute("count",count);
		model.addAttribute("list",list);
		model.addAttribute("nowDay",nowDay);
		return "res/bengyify/student/studentSignIn";
	}
	@RequestMapping(value={"/saveSignIn"},method ={RequestMethod.POST})
	public String saveSignIn(String userFlow,String local,
							 String remark,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/bengyify/student/success";
		}
		if(StringUtil.isBlank(local)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "签到地点为空");
			return "res/bengyify/student/success";
		}
		Map<String,Object> nowDept=bengyifyStudentBiz.getDocNowDept(userFlow);
		if(nowDept==null||StringUtil.isBlank((String) nowDept.get("deptFlow")))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "当前无轮转科室，无法签到");
			return "res/bengyify/student/success";
		}
		Map<String,Object>param=new HashMap<>();
		param.put("deptFlow",nowDept.get("deptFlow"));
		param.put("userFlow",userFlow);
		param.put("remark",remark);
		param.put("local",local);
		int count=0;
		count=bengyifyStudentBiz.addSign(param);
		if(count!=1){
			model.addAttribute("resultId", "3011102");
			model.addAttribute("resultType", "签到失败！");
		}
		return "res/bengyify/student/success";
	}
}
