package com.pinde.res.ctrl.hzyy;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.NfyyGlobalConstant;
import com.pinde.core.commom.enums.DeptStatusEnum;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.MathUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hzyy.IHzyyAppBiz;
import com.pinde.res.biz.hzyy.IHzyyDoctorBiz;
import com.pinde.res.biz.hzyy.IHzyyStudentBiz;
import com.pinde.res.biz.hzyy.IHzyyTeacherBiz;
import com.pinde.res.dao.hzyy.ext.HzyyStudentMapper;
import com.pinde.res.model.nfyy.mo.Activity;
import com.pinde.res.model.nfyy.mo.Evaluation;
import com.pinde.res.model.nfyy.mo.MedicalInfo;
import com.pinde.res.model.nfyy.mo.StudyInfo;
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
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/hzyy/student")
public class HzyyStudentController {

	private static Logger logger = LoggerFactory.getLogger(HzyyStudentController.class);

	@Autowired
	private IHzyyAppBiz hzyyAppBiz;
	@Autowired
	private IHzyyStudentBiz hzyyStudentBiz;
	@Autowired
	private IHzyyTeacherBiz hzyyTeacherBiz;
	@Autowired
	private HzyyStudentMapper studentMapper;
	@Autowired
	private IHzyyDoctorBiz hzyyDoctorBiz;
	@Value("#{configProperties['nfyy.funcFlow.0001.httpurl']}")
	private String nfyyFuncFlow0001HttpUrl;
	@Value("#{configProperties['hzyy.mobile.after.url']}")
	private String mobile_after_url;
	@Value("#{configProperties['com.pinde.net.cfg.http']}")
	private String cfg_url;

	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(getClass().getCanonicalName()+" some error happened",e);
		return "res/hzyy/500";
    }

	@RequestMapping("/schPlanList")
	public String schPlanList(String userFlow, String searchData, Integer pageIndex, Integer pageSize,
							  Model model , HttpServletRequest request) throws UnsupportedEncodingException{
		String schStatusId = "";
		String schDeptName = "";
		if(StringUtil.isNotBlank(searchData)){
			searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
			Map<String , String> searchMap = (Map<String, String>) JSON.parse(searchData);
			for(String key:searchMap.keySet()){
				//System.out.println(key+":"+URLDecoder.decode(searchMap.get(key), "UTF-8"));
				searchMap.put(key, URLDecoder.decode(searchMap.get(key), "UTF-8"));
			}
			model.addAttribute("searchMap" , searchMap);
			schStatusId = searchMap.get("schStatusId");
			schDeptName = searchMap.get("schDeptName");
		}

		String result = "res/hzyy/student/schPlanList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(schStatusId)){
			schStatusId = DeptStatusEnum.Entering.getId();
		}
		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}


		Map<String,String> userInfo = hzyyAppBiz.readUser(userFlow);
		if(userInfo==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
		}
		String userType = userInfo.get("userType");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
		}
		int userTypeInt = Integer.valueOf(userType);

		int examStatusId = userTypeInt==4?9:7;

		List<Map<String,Object>> schPlans = hzyyStudentBiz.getPlanList(userFlow, schStatusId, schDeptName , pageIndex, pageSize,examStatusId);
		model.addAttribute("schPlans" , schPlans);
		model.addAttribute("dataCount", hzyyStudentBiz.getPlanList(userFlow,schStatusId,schDeptName , 1,Integer.MAX_VALUE,examStatusId).size());

		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/schFuncList"},method={RequestMethod.GET})
	public String schFuncList(String userFlow,String schDeptFlow,Model model){
		String result = "res/hzyy/student/schFuncList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DEPTFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DEPTFLOW_NULL);
			return result;
		}

		Map<String,String> doctor = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("doctor" , doctor);
		//查看是否填写过入科教育
		Map<String,Object> deptEdu = hzyyStudentBiz.readEnteredDeptEdu(schDeptFlow);
		model.addAttribute("deptEdu" , deptEdu);

		//查看是否评价过科室
		List<Map<String,Object>> marksDept = hzyyStudentBiz.getMarksForDept(userFlow, schDeptFlow);
		model.addAttribute("marksDept" , marksDept);

		//查看是否评价过老师
    	List<Map<String,Object>> marksTec = hzyyStudentBiz.getMarksForTec(userFlow, schDeptFlow);
		model.addAttribute("marksTec" , marksTec);

//		//Dops量表
//		Map<String,Object> outDops = hzyyTeacherBiz.readOutDops(schDeptFlow);
//		model.addAttribute("outDops" , outDops);
//
//		//Minicex
//		Map<String,Object> outMiniCex = hzyyTeacherBiz.readOutMiniCex(schDeptFlow);
//		model.addAttribute("outMiniCex" , outMiniCex);

		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/categoryProgress"},method={RequestMethod.GET})
	public String categoryProgress(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow,
								   Integer pageIndex, Integer pageSize, String dataCount, Model model){
		String result = "res/hzyy/student/categoryProgress";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DEPTFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DEPTFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
		model.addAttribute("dataCount", 0);
		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}

		//病种
		if("D002".equals(funcFlow)){
			List<Map<String,Object>> caseClassCatas = hzyyDoctorBiz.getCaseClassCatas(userFlow,schDeptFlow,pageIndex, pageSize);
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
			model.addAttribute("dataCount", hzyyDoctorBiz.getCaseClassCatas(userFlow,schDeptFlow,1, Integer.MAX_VALUE).size());
		}
		//操作技能
		if("D003".equals(funcFlow)){
			List<Map<String,Object>> operateSkillCatas = hzyyDoctorBiz.getOperateSkillCatas(userFlow,schDeptFlow,pageIndex, pageSize);
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
			model.addAttribute("dataCount", hzyyDoctorBiz.getOperateSkillCatas(userFlow,schDeptFlow,1, Integer.MAX_VALUE).size());
		}
		//手术
		if("D004".equals(funcFlow)){
			List<Map<String,Object>> posSkillCatas = hzyyDoctorBiz.getPOSSkillCatas(userFlow,schDeptFlow,pageIndex, pageSize);
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
			model.addAttribute("dataCount", hzyyDoctorBiz.getPOSSkillCatas(userFlow,schDeptFlow,1, Integer.MAX_VALUE).size());
		}

		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
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
			for(String key:searchMap.keySet()){
				//System.out.println(key+":"+URLDecoder.decode(searchMap.get(key), "UTF-8"));
				searchMap.put(key, URLDecoder.decode(searchMap.get(key), "UTF-8"));
			}
			model.addAttribute("searchMap" , searchMap);
		}
		String result = "res/hzyy/student/dataList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DEPTFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DEPTFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)||!("dataInputNN".equals(funcTypeId)||"dataInput1N".equals(funcTypeId) || "dataInput11".equals(funcTypeId))){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}
		model.addAttribute("pageIndex" , pageIndex);
		model.addAttribute("pageSize" , pageSize);
		model.addAttribute("dataCount" , 0);
		//入科教育
		//ios不用
//		if("0001".equals(funcFlow)){
//			Map<String,Object> enteredDeptEdu = hzyyStudentBiz.readEnteredDeptEdu(schDeptFlow);
//			model.addAttribute("enteredDeptEdu" , enteredDeptEdu);
//			model.addAttribute("dataCount" , 1);
//		}
		//学习情况
		if("0002".equals(funcFlow)){
			List<StudyInfo> studyInfos = hzyyStudentBiz.getStudyInfos(schDeptFlow, pageIndex, pageSize);
			model.addAttribute("studyInfos" , studyInfos);
			model.addAttribute("dataCount", hzyyStudentBiz.getStudyInfos(schDeptFlow, 1, Integer.MAX_VALUE).size());
		}
		//病例与管床
		if("0003".equals(funcFlow)){
			List<MedicalInfo> medicalInfos = hzyyStudentBiz.getMedicalInfos(schDeptFlow, pageIndex, pageSize);
			for(MedicalInfo medicalInfo : medicalInfos){
				String name = StringUtil.defaultString((String)medicalInfo.getName());
				if(name.length()>0){
					name = name.substring(0, name.length() - 1) + "★";
					medicalInfo.setName(name);
				}
			}
			int count = hzyyStudentBiz.getMedicalInfos(schDeptFlow, 1, Integer.MAX_VALUE).size();
			model.addAttribute("medicalInfos" , medicalInfos);
			model.addAttribute("dataCount" , count);
		}
		//教学活动
		if("0004".equals(funcFlow)){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userFlow", userFlow);
			String join = searchMap.get("join");
			if(StringUtil.isBlank(join)){
				join = "NotEntered";
			}
			params.put("join", join);
			String mainSpeaker = searchMap.get("mainSpeaker");
			params.put("mainSpeaker", mainSpeaker);
			List<Activity> activitys = hzyyStudentBiz.getActicitys(params , pageIndex , pageSize);
			model.addAttribute("activitys" , activitys);
			int count =  hzyyStudentBiz.getActicitys(params , 1 , Integer.MAX_VALUE).size();
			model.addAttribute("dataCount" , count);
		}

		//大病历
		if("D001".equals(funcFlow)){
			List<Map<String,Object>> bigHistorys = hzyyDoctorBiz.getBigHistorys(userFlow,schDeptFlow, pageIndex, pageSize);
			for(Map<String,Object> bigHistory : bigHistorys){
				String RecData2 = StringUtil.defaultString((String)bigHistory.get("RecData2"));
				if(RecData2.length()>0){
					RecData2 = RecData2.substring(0, RecData2.length() - 1) + "★";
					bigHistory.put("RecData2", RecData2);
				}
			}
			model.addAttribute("bigHistorys" , bigHistorys);
			model.addAttribute("dataCount", hzyyDoctorBiz.getBigHistorys(userFlow,schDeptFlow, 1, Integer.MAX_VALUE).size());
		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            List<Map<String,Object>> outPatients = hzyyDoctorBiz.getOutPatients(userFlow,schDeptFlow, pageIndex, pageSize);
            for(Map<String,Object> outPatient : outPatients){
                String RecData2 = StringUtil.defaultString((String)outPatient.get("RecData2"));
                if(RecData2.length()>0){
                    RecData2 = RecData2.substring(0,RecData2.length()-1)+"★";
                    outPatient.put("RecData2", RecData2);
                }
            }
            model.addAttribute("outPatients" , outPatients);
            model.addAttribute("dataCount", hzyyDoctorBiz.getOutPatients(userFlow,schDeptFlow, 1, Integer.MAX_VALUE).size());
        }

        //病种
		if("D002".equals(funcFlow)){
			List<Map<String,Object>> caseClasses = hzyyDoctorBiz.getCaseClasses(userFlow,schDeptFlow,cataFlow, pageIndex, pageSize);
			for(Map<String,Object> caseClass : caseClasses){
				String RecData3 = StringUtil.defaultString((String)caseClass.get("RecData3"));
				if(RecData3.length()>0){
					RecData3 = RecData3.substring(0, RecData3.length() - 1) + "★";
					caseClass.put("RecData3", RecData3);
				}
			}
			model.addAttribute("caseClasses" , caseClasses);
			model.addAttribute("dataCount", hzyyDoctorBiz.getCaseClasses(userFlow,schDeptFlow,cataFlow, 1, Integer.MAX_VALUE).size());
		}
		//操作技能
		if("D003".equals(funcFlow)){
			List<Map<String,Object>> operateSkills = hzyyDoctorBiz.getOperateSkills(userFlow,schDeptFlow,cataFlow, pageIndex, pageSize);
			for(Map<String,Object> operateSkill : operateSkills){
				String RecData3 = StringUtil.defaultString((String)operateSkill.get("RecData3"));
				if(RecData3.length()>0){
					RecData3 = RecData3.substring(0, RecData3.length() - 1) + "★";
					operateSkill.put("RecData3", RecData3);
				}
			}
			model.addAttribute("operateSkills" , operateSkills);
			model.addAttribute("dataCount", hzyyDoctorBiz.getOperateSkills(userFlow,schDeptFlow,cataFlow, 1, Integer.MAX_VALUE).size());
		}
		//手术
		if("D004".equals(funcFlow)){
			List<Map<String,Object>> posSkills = hzyyDoctorBiz.getPOSSkills(userFlow,schDeptFlow,cataFlow, pageIndex, pageSize);
			for(Map<String,Object> posSkill : posSkills){
				String RecData3 = StringUtil.defaultString((String)posSkill.get("RecData3"));
				if(RecData3.length()>0){
					RecData3 = RecData3.substring(0, RecData3.length() - 1) + "★";
					posSkill.put("RecData3", RecData3);
				}
			}
			model.addAttribute("posSkills" , posSkills);
			model.addAttribute("dataCount", hzyyDoctorBiz.getPOSSkills(userFlow,schDeptFlow,cataFlow, 1, Integer.MAX_VALUE).size());
		}

		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/addData"},method={RequestMethod.POST})
	public String addData(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow,
						  String cataFlow, HttpServletRequest request , Model model){

		String result = "res/hzyy/student/addData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DEPTFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DEPTFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)||!("dataInputNN".equals(funcTypeId)||"dataInput1N".equals(funcTypeId) || "dataInput11".equals(funcTypeId))){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
		//入科教育
		if("0001".equals(funcFlow)){
			Map<String,Object> exitEnteredDeptEdu = hzyyStudentBiz.readEnteredDeptEdu(schDeptFlow);
			if(exitEnteredDeptEdu==null){
				Map<String,Object> enteredDeptEdu = new HashMap<String,Object>();
				enteredDeptEdu.put("userFlow", userFlow);
				enteredDeptEdu.put("schDeptFlow", schDeptFlow);
				enteredDeptEdu.put("tecName", request.getParameter("tecName"));
				enteredDeptEdu.put("dateTime", request.getParameter("dateTime"));
				String dataFlow = hzyyStudentBiz.addEnteredDeptEdu(enteredDeptEdu);
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
			String dataFlow = hzyyStudentBiz.addStudyInfo(studyInfo);
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
			String dataFlow = hzyyStudentBiz.addMedicalInfo(medicalInfo);
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
			List<Map<String, Object>> assessTmp = hzyyStudentBiz.getExamItems("6");
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
			hzyyStudentBiz.addAssess(examInfo , marks );
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
			List<Map<String, Object>> assessTmp = hzyyStudentBiz.getExamItems("0");
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

			hzyyStudentBiz.addAssess(examInfo , marks );
		}
		//自我评价
		if("0007".equals(funcFlow)){
			Evaluation temp = hzyyStudentBiz.readOutSecBrief(schDeptFlow);
			if(temp==null){
				Evaluation evaluation = new Evaluation();
				String briefRequrie = request.getParameter("briefRequrie");
				evaluation.setBriefRequrie(briefRequrie);
				evaluation.setUserFlow(userFlow);
				evaluation.setSchDeptFlow(schDeptFlow);
				hzyyStudentBiz.addOutSecBrief(evaluation);
//				model.addAttribute("dataFlow" , dataFlow);
			}
		}

        //大病历
  		if("D001".equals(funcFlow)){
  			hzyyDoctorBiz.addBigHistory(userFlow,schDeptFlow,request);
  		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            hzyyDoctorBiz.addOutPatient(userFlow,schDeptFlow,request);
        }
  		//病种
		if("D002".equals(funcFlow)){
			hzyyDoctorBiz.addCaseClass(userFlow,schDeptFlow,cataFlow,request);
		}
		//操作技能
		if("D003".equals(funcFlow)){
			hzyyDoctorBiz.addOperateSkill(userFlow,schDeptFlow,cataFlow,request);
		}
		//手术
		if("D004".equals(funcFlow)){
			hzyyDoctorBiz.addPOSSkill(userFlow,schDeptFlow,cataFlow,request);
		}
		//出科小结
		if("D005".equals(funcFlow)){
			Map<String, Object> temp = hzyyDoctorBiz.readOutSecBrief(schDeptFlow);
			if(temp==null){
				hzyyDoctorBiz.addOutSecBrief(userFlow,schDeptFlow,request);
			}
		}

		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/viewData"},method={RequestMethod.GET})
	public String viewData(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow,
						   String cataFlow, String dataFlow, HttpServletRequest request, HttpServletResponse response, Model model){

		String result = "res/hzyy/student/viewData";

		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DEPTFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DEPTFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)||!("dataInputNN".equals(funcTypeId)||"dataInput1N".equals(funcTypeId) || "dataInput11".equals(funcTypeId))){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
//		if(StringUtil.isBlank(dataFlow)){
//			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DATAFLOW_NULL);
//			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DATAFLOW_NULL);
//			return result;
//		}

		//入科教育
        if("0001".equals(funcFlow)){
        	Map<String,Object> enteredDeptEdu = hzyyStudentBiz.readEnteredDeptEdu(schDeptFlow);
        	model.addAttribute("enteredDeptEdu" , enteredDeptEdu);
		}
        //学习情况
        if("0002".equals(funcFlow)){
        	if(StringUtil.isNotBlank(dataFlow)){
            	StudyInfo studyInfo = hzyyStudentBiz.readStudyInfo(dataFlow);
            	model.addAttribute("studyInfo" , studyInfo);
        	}
        }
        //病例与管床
        if("0003".equals(funcFlow)){
        	if(StringUtil.isNotBlank(dataFlow)){
            	MedicalInfo medicalInfo = hzyyStudentBiz.readMedicalInfo(dataFlow);
            	model.addAttribute("medicalInfo" , medicalInfo);
        	}
        }
        //教学活动
        if("0004".equals(funcFlow)){
        	Activity activity = hzyyStudentBiz.findActivity(dataFlow, userFlow);
        	model.addAttribute("activity" , activity);
        }
        //评价科室
        if("0005".equals(funcFlow)){
        	Map<String,Object> examInfo = hzyyStudentBiz.readExamInfo("6", schDeptFlow);
        	model.addAttribute("examInfo" , examInfo);
        	//获取评价模板
        	List<Map<String, Object>> assessTmp = hzyyStudentBiz.getExamItems("6");
        	model.addAttribute("assessTmp" , assessTmp);
        	List<Map<String,Object>> marks = hzyyStudentBiz.getMarksForDept(userFlow, schDeptFlow);
        	model.addAttribute("marks" , marks);
        }
        //评价带教老师
        if("0006".equals(funcFlow)){
        	Map<String,Object> examInfo = hzyyStudentBiz.readExamInfo("0", schDeptFlow);
        	model.addAttribute("examInfo" , examInfo);

			List<Map<String, Object>> assessTmp = hzyyStudentBiz.getExamItems("0");
        	model.addAttribute("assessTmp" , assessTmp);
        	List<Map<String,Object>> marks = hzyyStudentBiz.getMarksForTec(userFlow, schDeptFlow);
        	model.addAttribute("marks" , marks);
        }
        //自我评价
        if("0007".equals(funcFlow)){
        	Evaluation evaluation = hzyyStudentBiz.readOutSecBrief(schDeptFlow);
        	model.addAttribute("evaluation" , evaluation);
        }
        //DOPS评估表
        if("0008".equals(funcFlow)){
    		Map<String,Object> outDops = hzyyTeacherBiz.readOutDops(schDeptFlow);
    		model.addAttribute("outDops" , outDops);

			Map<String,Object> examInfo = hzyyTeacherBiz.readExamInfo(schDeptFlow,"11");
        	model.addAttribute("examInfo" , examInfo);
			//查询科室名称
			String secName = studentMapper.selectSecName(schDeptFlow);
			secName = StringUtil.defaultString(secName);
			String ExamItemID = null;
			List<Map<String,String>> examItemIdList = studentMapper.selectExamItemByType("11");
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
			List<Map<String, Object>> assessTmp = hzyyStudentBiz.getExamItemsByItemId("11",ExamItemID);
        	model.addAttribute("assessTmp" , assessTmp);

			List<Map<String,Object>> marks = hzyyTeacherBiz.getMarks(schDeptFlow,"11");
        	model.addAttribute("marks" , marks);
        }
        //Mini-CEX评估表
        if("0009".equals(funcFlow)){
    		Map<String,Object> outMiniCex = hzyyTeacherBiz.readOutMiniCex(schDeptFlow);
    		model.addAttribute("outMiniCex" , outMiniCex);

			Map<String,Object> examInfo = hzyyTeacherBiz.readExamInfo(schDeptFlow,"12");
        	model.addAttribute("examInfo" , examInfo);

			List<Map<String, Object>> assessTmp = hzyyStudentBiz.getExamItemsMiniCex("12");
        	model.addAttribute("assessTmp" , assessTmp);

			List<Map<String,Object>> marks = hzyyTeacherBiz.getMarks(schDeptFlow,"12");
        	model.addAttribute("marks" , marks);
        }

		//大病历
  		if("D001".equals(funcFlow)){
  			if(StringUtil.isNotBlank(dataFlow)){
  	  			Map<String,Object> bigHistory = hzyyDoctorBiz.readBigHistory(userFlow,schDeptFlow,dataFlow);
  				model.addAttribute("bigHistory" , bigHistory);
  			}
  		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            if(StringUtil.isNotBlank(dataFlow)){
                Map<String,Object> outPatient = hzyyDoctorBiz.readOutPatient(userFlow,schDeptFlow,dataFlow);
                model.addAttribute("outPatient" , outPatient);
            }
        }
  		//病种
		if("D002".equals(funcFlow)){
			if(StringUtil.isNotBlank(dataFlow)){
  	  			Map<String,Object> caseClass = hzyyDoctorBiz.readCaseClass(userFlow,schDeptFlow,dataFlow);
  				model.addAttribute("caseClass" , caseClass);
  			}
			if(StringUtil.isNotBlank(cataFlow)){
				String caseClassCataName = hzyyDoctorBiz.readCaseClassCataName(cataFlow);
				model.addAttribute("caseClassCataName" , caseClassCataName);
			}
		}
		//操作技能
		if("D003".equals(funcFlow)){
			if(StringUtil.isNotBlank(dataFlow)){
  	  			Map<String,Object> operateSkill = hzyyDoctorBiz.readOperateSkill(userFlow,schDeptFlow,dataFlow);
  				model.addAttribute("operateSkill" , operateSkill);
  			}
			if(StringUtil.isNotBlank(cataFlow)){
				String operateSkillCataName = hzyyDoctorBiz.readOperateSkillCataName(cataFlow);
				model.addAttribute("operateSkillCataName" , operateSkillCataName);
			}
		}
		//手术
		if("D004".equals(funcFlow)){
			if(StringUtil.isNotBlank(dataFlow)){
  	  			Map<String,Object> posSkill = hzyyDoctorBiz.readPOSSkill(userFlow,schDeptFlow,dataFlow);
  				model.addAttribute("posSkill" , posSkill);
  			}
			if(StringUtil.isNotBlank(cataFlow)){
				String posSkillCataName = hzyyDoctorBiz.readPOSSkillCataName(cataFlow);
				model.addAttribute("posSkillCataName" , posSkillCataName);
			}
		}
		//出科小结
		if("D005".equals(funcFlow)){
			Map<String,String> doctor = hzyyAppBiz.readUser(userFlow);
			model.addAttribute("doctor" , doctor);

			Map<String, Object> outSecBrief = hzyyDoctorBiz.readOutSecBrief(schDeptFlow);
			model.addAttribute("outSecBrief" , outSecBrief);
		}
		model.addAttribute("nfyyFuncFlow0001HttpUrl", nfyyFuncFlow0001HttpUrl);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/modData"},method={RequestMethod.PUT , RequestMethod.POST})
	public String modData(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow,
						  String cataFlow, String dataFlow, HttpServletRequest request , Model model){

		String result = "res/hzyy/student/modData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DEPTFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DEPTFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)||!("dataInputNN".equals(funcTypeId)||"dataInput1N".equals(funcTypeId) || "dataInput11".equals(funcTypeId))){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
//		if(StringUtil.isBlank(dataFlow)){
//			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DATAFLOW_NULL);
//			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DATAFLOW_NULL);
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
			hzyyStudentBiz.modEnteredDeptEdu(enteredDeptEdu);
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
			hzyyStudentBiz.modStudyInfo(studyInfo );
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
			hzyyStudentBiz.modMedicalInfo(medicalInfo);
		}
		//教学活动
		if("0004".equals(funcFlow)){
			Activity exitactivity = hzyyStudentBiz.findActivity(dataFlow, userFlow);
			Integer score = exitactivity.getScore();
			if(score==null){
				//是否可参加
				Activity activity = hzyyStudentBiz.readActivity(dataFlow);
				String currDateTime = DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
				String startDateTime = activity.getTime();

				int sing = (int) DateUtil.signMinutesBetweenTowDate(currDateTime, startDateTime);
				if (sing >= -10) {
					hzyyStudentBiz.joinActivity(dataFlow, schDeptFlow , userFlow);
				}else{
					model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_NOT_JOIN_ACTIVITY);
					model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_NOT_JOIN_ACTIVITY);
					return result;
				}
			}else if(score>=0){
				Activity exitActivity = hzyyStudentBiz.readActivity(dataFlow);
			    String endDateTime = exitActivity.getEndTime();
			    String currDateTime = DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
			    int sing = (int) DateUtil.signMinutesBetweenTowDate(endDateTime, currDateTime);
			    if(score==0&&sing>=0&&sing<=NfyyGlobalConstant.PLAY_SCORE_ACTIVITY_TIME){
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
					hzyyStudentBiz.scoreActivity(activity);
				}else{
			    	model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_NOT_PLAY_SCORE_ACTIVITY);
					model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_NOT_PLAY_SCORE_ACTIVITY);
					return result;
			    }
			}
		}
		//自我评价
		if("0007".equals(funcFlow)){
			String briefRequrie = request.getParameter("briefRequrie");
			hzyyStudentBiz.modOutSecBrief(schDeptFlow, briefRequrie);
		}
		//DOPS评估表
        if("0008".equals(funcFlow)){
        	hzyyStudentBiz.saveOutDops(userFlow, schDeptFlow,request);
		}
        //Mini-CEX评估表
        if("0009".equals(funcFlow)){
        	hzyyStudentBiz.saveOutMiniCex(userFlow, schDeptFlow,request);
		}
        //大病历
  		if("D001".equals(funcFlow)){
  			hzyyDoctorBiz.modBigHistory(userFlow,schDeptFlow,dataFlow,request);
  		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            hzyyDoctorBiz.modOutPatient(userFlow,schDeptFlow,dataFlow,request);
        }
  		//病种
		if("D002".equals(funcFlow)){
			hzyyDoctorBiz.modCaseClass(userFlow,schDeptFlow,cataFlow,dataFlow,request);
		}
		//操作技能
		if("D003".equals(funcFlow)){
			hzyyDoctorBiz.modOperateSkill(userFlow,schDeptFlow,cataFlow,dataFlow,request);
		}
		//手术
		if("D004".equals(funcFlow)){
			hzyyDoctorBiz.modPOSSkill(userFlow,schDeptFlow,cataFlow,dataFlow,request);
		}
		//出科小结
		if("D005".equals(funcFlow)){
			Map<String, Object> temp = hzyyDoctorBiz.readOutSecBrief(schDeptFlow);
			if(temp!=null){
				hzyyDoctorBiz.modOutSecBrief(userFlow,schDeptFlow,request);
			}
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/deleteData"},method={RequestMethod.POST , RequestMethod.DELETE})
	public String deleteData(String userFlow,String schDeptFlow,String funcTypeId,String funcFlow,
			String dataFlow,HttpServletRequest request , Model model){
		String result = "res/hzyy/student/deleteData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DEPTFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DEPTFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)||!("dataInputNN".equals(funcTypeId)||"dataInput1N".equals(funcTypeId) || "dataInput11".equals(funcTypeId))){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
		if(StringUtil.isBlank(dataFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DATAFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DATAFLOW_NULL);
			return result;
		}
		//学习情况
		if("0002".equals(funcFlow)){
			hzyyStudentBiz.delStudyInfo(dataFlow);
		}
		//病例与管床
		if("0003".equals(funcFlow)){
			hzyyStudentBiz.delMedicalInfo(dataFlow);
		}
		//大病历
  		if("D001".equals(funcFlow)){
  			hzyyDoctorBiz.delBigHistory(userFlow,schDeptFlow,dataFlow);
  		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            hzyyDoctorBiz.delOutPatient(userFlow,schDeptFlow,dataFlow);
        }
  		//病种
		if("D002".equals(funcFlow)){
			hzyyDoctorBiz.delCaseClass(userFlow,schDeptFlow,dataFlow);
		}
		//操作技能
		if("D003".equals(funcFlow)){
			hzyyDoctorBiz.delOperateSkill(userFlow,schDeptFlow,dataFlow);
		}
		//手术
		if("D004".equals(funcFlow)){
			hzyyDoctorBiz.delPOSSkill(userFlow,schDeptFlow,dataFlow);
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/joinExam"})
	public String joinExam(String userFlow,String SecID,String SpecialtyID,String processFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hzyy/student/joinExam";
		}
		if(StringUtil.isEmpty(SecID)){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "SecID标识符为空");
			return "res/hzyy/student/joinExam";
		}
		if(StringUtil.isEmpty(SpecialtyID)){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "SpecialtyID标识符为空");
			return "res/hzyy/student/joinExam";
		}
		//考试地址
		if(!StringUtil.isNotBlank(mobile_after_url)){
			model.addAttribute("resultId", "40405");
			model.addAttribute("resultType", "请联系管理员维护考试地址!");
			return "res/hzyy/student/joinExam";
		}
		Map<String,String> user=hzyyAppBiz.readUser(userFlow);
		//试卷id
		String ExamSoluID = "0";
		//时间戳
		String Date = "0";
		String examType="0";
		String roleFlow=user.get("roleFlow");
		if("2".equals(roleFlow))
		{
			examType="1";
		}
		if("4".equals(roleFlow))
		{
			examType="2";
		}
		if ("0".equals(examType)) {
			model.addAttribute("resultId", "40407");
			model.addAttribute("resultType", "学员人员类型错误!");
			return "res/hzyy/student/joinExam";
		}
		//标准科室
		Map<String,String> paper = hzyyStudentBiz.getPaperByStandardDeptId(SecID,SpecialtyID,examType);
		if (paper != null) {
			ExamSoluID = paper.get("Examination");
		}
		if ("0".equals(ExamSoluID)) {
			model.addAttribute("resultId", "40407");
			model.addAttribute("resultType", "该科室暂无试卷信息!");
			return "res/hzyy/student/joinExam";
		}
		String testUrl=mobile_after_url+"?Action=ChuKeMobileExam&ExamSoluID="+ExamSoluID+"&CardID="+user.get("userCode")+"&Date="+Date+"&TestNum=";
		model.addAttribute("testUrl",testUrl);
		return "res/hzyy/student/joinExam";
	}
}
