package com.pinde.res.ctrl.gzzy;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.NfyyGlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.MathUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.gzzy.*;
import com.pinde.res.dao.gzzy.ext.GzzyStudentMapper;
import com.pinde.res.enums.nfyy.DeptStatusEnum;
import com.pinde.res.model.nfyy.mo.Activity;
import com.pinde.res.model.nfyy.mo.Evaluation;
import com.pinde.res.model.nfyy.mo.MedicalInfo;
import com.pinde.res.model.nfyy.mo.StudyInfo;
import com.pinde.sci.model.mo.ResTrainingOpinion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/gzzyres/student")
public class GzzyStudentController {

	private static Logger logger = LoggerFactory.getLogger(GzzyStudentController.class);

	@Autowired
	private IGzzyAppBiz gzyyAppBiz;
	@Autowired
	private IGzzyStudentBiz gzyyStudentBiz;
	@Autowired
	private IGzzyTeacherBiz gzyyTeacherBiz;
	@Autowired
	private GzzyStudentMapper studentMapper;
	@Autowired
	private IGzzyDoctorBiz gzyyDoctorBiz;
	@Value("#{configProperties['nfyy.funcFlow.0001.httpurl']}")
	private String nfyyFuncFlow0001HttpUrl;
	@Autowired
	private IGzzyResLiveTrainingBiz resLiveTrainingBiz;
	@Value("#{configProperties['hzyy.mobile.after.url']}")
	private String mobile_after_url;
	@Value("#{configProperties['com.pinde.net.cfg.http']}")
	private String cfg_url;

	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(getClass().getCanonicalName()+" some error happened",e);
		return "res/gzzyres/500";
    }

	@RequestMapping("/schPlanList")
	public String schPlanList(String userFlow, String searchData, Integer pageIndex, Integer pageSize,
							  Model model , HttpServletRequest request) throws UnsupportedEncodingException{
		String schStatusId = "";
		String schDeptName = "";
		if(StringUtil.isNotBlank(searchData)){
			logger.debug("搜索条件："+searchData);
			//System.out.println("搜索条件："+searchData);
			searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
			Map<String , String> searchMap = (Map<String, String>) JSON.parse(searchData);
			model.addAttribute("searchMap" , searchMap);
			schStatusId = searchMap.get("schStatusId");
			schDeptName = searchMap.get("schDeptName");
		}

		String result = "res/gzzyres/student/schPlanList";
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


		Map<String,String> userInfo = gzyyAppBiz.readUser(userFlow);
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

		List<Map<String,Object>> schPlans = gzyyStudentBiz.getPlanList(userFlow, schStatusId, schDeptName , pageIndex, pageSize,examStatusId);
		model.addAttribute("schPlans" , schPlans);
		model.addAttribute("dataCount", gzyyStudentBiz.getPlanList(userFlow,schStatusId,schDeptName , 1,Integer.MAX_VALUE,examStatusId).size());

		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/schFuncList"},method={RequestMethod.GET})
	public String schFuncList(String userFlow,String schDeptFlow,Model model){
		String result = "res/gzzyres/student/schFuncList";
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

		Map<String,String> doctor = gzyyAppBiz.readUser(userFlow);
		model.addAttribute("doctor" , doctor);
		//查看是否填写过入科教育
		Map<String,Object> deptEdu = gzyyStudentBiz.readEnteredDeptEdu(schDeptFlow);
		model.addAttribute("deptEdu" , deptEdu);

		//查看是否评价过科室
		String ExamItemType="6";
		List<Map<String,Object>> marksDept = gzyyStudentBiz.getMarksForDept(userFlow, schDeptFlow, ExamItemType);
		if(marksDept==null||marksDept.size()==0)
		{
			ExamItemType="26";
			marksDept = gzyyStudentBiz.getMarksForDept(userFlow, schDeptFlow, ExamItemType);
		}
		model.addAttribute("marksDept" , marksDept);

		//查看是否评价过老师
		ExamItemType="0";
    	List<Map<String,Object>> marksTec = gzyyStudentBiz.getMarksForTec(userFlow, schDeptFlow,ExamItemType);
		if(marksTec==null||marksTec.size()==0)
		{
			ExamItemType="20";
			marksTec = gzyyStudentBiz.getMarksForTec(userFlow, schDeptFlow, ExamItemType);
		}
		model.addAttribute("marksTec" , marksTec);

		//Dops量表
		Map<String,Object> outDops = gzyyTeacherBiz.readOutDops(schDeptFlow);
		model.addAttribute("outDops" , outDops);
		List<Map<String,String>> depts=gzyyTeacherBiz.isFzDept(schDeptFlow);
		if(depts!=null&&depts.size()>0) {
			//Minicex
			Map<String,Object> outMiniCex = gzyyTeacherBiz.readOutMiniCexFz(schDeptFlow);
			model.addAttribute("outMiniCex" , outMiniCex);
			model.addAttribute("isFz" , "Y");

		}else{

			//Minicex
			Map<String,Object> outMiniCex = gzyyTeacherBiz.readOutMiniCex(schDeptFlow);
			model.addAttribute("outMiniCex" , outMiniCex);
		}

		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/categoryProgress"},method={RequestMethod.GET})
	public String categoryProgress(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow,
								   Integer pageIndex, Integer pageSize, String dataCount, Model model){
		String result = "res/gzzyres/student/categoryProgress";
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
			List<Map<String,Object>> caseClassCatas = gzyyDoctorBiz.getCaseClassCatas(userFlow,schDeptFlow,pageIndex, pageSize);
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
			model.addAttribute("dataCount", gzyyDoctorBiz.getCaseClassCatas(userFlow,schDeptFlow,1, Integer.MAX_VALUE).size());
		}
		//操作技能
		if("D003".equals(funcFlow)){
			List<Map<String,Object>> operateSkillCatas = gzyyDoctorBiz.getOperateSkillCatas(userFlow,schDeptFlow,pageIndex, pageSize);
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
			model.addAttribute("dataCount", gzyyDoctorBiz.getOperateSkillCatas(userFlow,schDeptFlow,1, Integer.MAX_VALUE).size());
		}
		//手术
		if("D004".equals(funcFlow)){
			List<Map<String,Object>> posSkillCatas = gzyyDoctorBiz.getPOSSkillCatas(userFlow,schDeptFlow,pageIndex, pageSize);
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
			model.addAttribute("dataCount", gzyyDoctorBiz.getPOSSkillCatas(userFlow,schDeptFlow,1, Integer.MAX_VALUE).size());
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
			model.addAttribute("searchMap" , searchMap);
		}
		String result = "res/gzzyres/student/dataList";
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
//			Map<String,Object> enteredDeptEdu = gzyyStudentBiz.readEnteredDeptEdu(schDeptFlow);
//			model.addAttribute("enteredDeptEdu" , enteredDeptEdu);
//			model.addAttribute("dataCount" , 1);
//		}
		//学习情况
		if("0002".equals(funcFlow)){
			List<StudyInfo> studyInfos = gzyyStudentBiz.getStudyInfos(schDeptFlow, pageIndex, pageSize);
			model.addAttribute("studyInfos" , studyInfos);
			model.addAttribute("dataCount", gzyyStudentBiz.getStudyInfos(schDeptFlow, 1, Integer.MAX_VALUE).size());
		}
		//病例与管床
		if("0003".equals(funcFlow)){
			List<MedicalInfo> medicalInfos = gzyyStudentBiz.getMedicalInfos(schDeptFlow, pageIndex, pageSize);
			for(MedicalInfo medicalInfo : medicalInfos){
				String name = StringUtil.defaultString((String)medicalInfo.getName());
				if(name.length()>0){
					name = name.substring(0, name.length() - 1) + "★";
					medicalInfo.setName(name);
				}
			}
			int count = gzyyStudentBiz.getMedicalInfos(schDeptFlow, 1, Integer.MAX_VALUE).size();
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
			List<Activity> activitys = gzyyStudentBiz.getActicitys(params , pageIndex , pageSize);
			model.addAttribute("activitys" , activitys);
			int count =  gzyyStudentBiz.getActicitys(params , 1 , Integer.MAX_VALUE).size();
			model.addAttribute("dataCount" , count);
		}

		//大病历
		if("D001".equals(funcFlow)){
			List<Map<String,Object>> bigHistorys = gzyyDoctorBiz.getBigHistorys(userFlow,schDeptFlow, pageIndex, pageSize);
			for(Map<String,Object> bigHistory : bigHistorys){

				String RecData1= StringUtil.defaultString((String)bigHistory.get("RecData1"));
				if(RecData1.length()>0){
					RecData1 = RecData1.substring(0,RecData1.length()-1)+"★";
					bigHistory.put("RecData1", RecData1);
				}
			}
			model.addAttribute("bigHistorys" , bigHistorys);
			model.addAttribute("dataCount", gzyyDoctorBiz.getBigHistorys(userFlow,schDeptFlow, 1, Integer.MAX_VALUE).size());
		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            List<Map<String,Object>> outPatients = gzyyDoctorBiz.getOutPatients(userFlow,schDeptFlow, pageIndex, pageSize);
            for(Map<String,Object> outPatient : outPatients){
				String RecData2 = StringUtil.defaultString((String)outPatient.get("RecData2"));
				if(RecData2.length()>0){
					RecData2 = RecData2.substring(0, RecData2.length() - 1) + "★";
					outPatient.put("RecData2", RecData2);
				}
            }
            model.addAttribute("outPatients" , outPatients);
            model.addAttribute("dataCount", gzyyDoctorBiz.getOutPatients(userFlow,schDeptFlow, 1, Integer.MAX_VALUE).size());
        }

        //病种
		if("D002".equals(funcFlow)){
			List<Map<String,Object>> caseClasses = gzyyDoctorBiz.getCaseClasses(userFlow,schDeptFlow,cataFlow, pageIndex, pageSize);
			for(Map<String,Object> caseClass : caseClasses){
				String RecData3 = StringUtil.defaultString((String)caseClass.get("RecData2"));
				if(RecData3.length()>0){
					RecData3 = RecData3.substring(0, RecData3.length() - 1) + "★";
					caseClass.put("RecData2", RecData3);
				}
			}
			model.addAttribute("caseClasses" , caseClasses);
			model.addAttribute("dataCount", gzyyDoctorBiz.getCaseClasses(userFlow,schDeptFlow,cataFlow, 1, Integer.MAX_VALUE).size());
		}
		//操作技能
		if("D003".equals(funcFlow)){
			List<Map<String,Object>> operateSkills = gzyyDoctorBiz.getOperateSkills(userFlow,schDeptFlow,cataFlow, pageIndex, pageSize);
			for(Map<String,Object> operateSkill : operateSkills){
				String RecData3 = StringUtil.defaultString((String)operateSkill.get("RecData4"));
				if(RecData3.length()>0){
					RecData3 = RecData3.substring(0, RecData3.length() - 1) + "★";
					operateSkill.put("RecData4", RecData3);
				}
			}
			model.addAttribute("operateSkills" , operateSkills);
			model.addAttribute("dataCount", gzyyDoctorBiz.getOperateSkills(userFlow,schDeptFlow,cataFlow, 1, Integer.MAX_VALUE).size());
		}
		//手术
		if("D004".equals(funcFlow)){
			List<Map<String,Object>> posSkills = gzyyDoctorBiz.getPOSSkills(userFlow,schDeptFlow,cataFlow, pageIndex, pageSize);
			for(Map<String,Object> posSkill : posSkills){
				String RecData3 = StringUtil.defaultString((String)posSkill.get("RecData4"));
				if(RecData3.length()>0){
					RecData3 = RecData3.substring(0, RecData3.length() - 1) + "★";
					posSkill.put("RecData4", RecData3);
				}
			}
			model.addAttribute("posSkills" , posSkills);
			model.addAttribute("dataCount", gzyyDoctorBiz.getPOSSkills(userFlow,schDeptFlow,cataFlow, 1, Integer.MAX_VALUE).size());
		}

		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/addData"},method={RequestMethod.POST})
	public String addData(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow,
						  String cataFlow, HttpServletRequest request , Model model){

		String result = "res/gzzyres/student/addData";
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
			Map<String,Object> exitEnteredDeptEdu = gzyyStudentBiz.readEnteredDeptEdu(schDeptFlow);
			if(exitEnteredDeptEdu==null){
				Map<String,Object> enteredDeptEdu = new HashMap<String,Object>();
				enteredDeptEdu.put("userFlow", userFlow);
				enteredDeptEdu.put("schDeptFlow", schDeptFlow);
				enteredDeptEdu.put("tecName", request.getParameter("tecName"));
				enteredDeptEdu.put("dateTime", request.getParameter("dateTime"));
				String dataFlow = gzyyStudentBiz.addEnteredDeptEdu(enteredDeptEdu);
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
			String dataFlow = gzyyStudentBiz.addStudyInfo(studyInfo);
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
			String dataFlow = gzyyStudentBiz.addMedicalInfo(medicalInfo);
//			model.addAttribute("dataFlow" , dataFlow);

		}
		//评价科室
		if("0005".equals(funcFlow)){
			String ExamItemType="6";
			List<Map<String,Object>> marks = gzyyStudentBiz.getMarksForDept(userFlow, schDeptFlow, ExamItemType);
			if(marks==null||marks.size()==0)
			{
				ExamItemType="26";
				marks = gzyyStudentBiz.getMarksForDept(userFlow, schDeptFlow, ExamItemType);
			}
			if(marks!=null&&marks.size()>0)
			{
				model.addAttribute("resultId", "222222");
				model.addAttribute("resultType", "已评价，请刷新页面！");
				return result;
			}
			Map<String, Object> examInfo = new HashMap<String, Object>();
			 marks = new ArrayList<Map<String,Object>>();
			examInfo.put("examInfoType", "26");
			examInfo.put("userFlow", userFlow);
			examInfo.put("schDeptFlow", schDeptFlow);

			String examInfoDF = request.getParameter("total_score");
			examInfo.put("examInfoDF", examInfoDF);
			List<Map<String, Object>> assessTmp = gzyyStudentBiz.getExamItems("26");
			int sum=0;
			for(Map<String, Object> tmp:assessTmp){
				String reqItemId = (String) tmp.get("reqItemId");
				String score = request.getParameter(reqItemId+"_score");
				String cause = request.getParameter(reqItemId+"_cause");
				Map<String,Object> e = new HashMap<String,Object>();
				e.put("markDF", score);
				e.put("markKF", cause);
				e.put("reqItemId", reqItemId);
				if(StringUtil.isNotBlank(score))
					sum+=Integer.parseInt(score);
				marks.add(e);
			}
			examInfo.put("examInfoDF", sum+"");
			gzyyStudentBiz.addAssess(examInfo , marks );
		}
		//评价带教老师
		if("0006".equals(funcFlow)){
			String ExamItemType="0";
			List<Map<String,Object>> marks = gzyyStudentBiz.getMarksForTec(userFlow, schDeptFlow, ExamItemType);
			if(marks==null||marks.size()==0)
			{
				ExamItemType="20";
				marks = gzyyStudentBiz.getMarksForTec(userFlow, schDeptFlow, ExamItemType);
			}
			if(marks!=null&&marks.size()>0)
			{
				model.addAttribute("resultId", "222222");
				model.addAttribute("resultType", "已评价，请刷新页面！");
				return result;
			}
			Map<String, Object> examInfo = new HashMap<String, Object>();
			marks = new ArrayList<Map<String,Object>>();
			examInfo.put("examInfoType", "20");
			examInfo.put("userFlow", userFlow);
			examInfo.put("schDeptFlow", schDeptFlow);

			String examInfoDF = request.getParameter("total_score");
			examInfo.put("examInfoDF", examInfoDF);
			List<Map<String, Object>> assessTmp = gzyyStudentBiz.getExamItems("20");
			int sum=0;
			for(Map<String, Object> tmp:assessTmp){
				String reqItemId = (String) tmp.get("reqItemId");
				String score = request.getParameter(reqItemId+"_score");
				String cause = request.getParameter(reqItemId+"_cause");
				Map<String,Object> e = new HashMap<String,Object>();
				e.put("markDF", score);
				e.put("markKF", cause);
				e.put("reqItemId", reqItemId);
				if(StringUtil.isNotBlank(score))
					sum+=Integer.parseInt(score);
				marks.add(e);
			}
			examInfo.put("examInfoDF", sum+"");

			gzyyStudentBiz.addAssess(examInfo , marks );
		}
		//自我评价
		if("0007".equals(funcFlow)){
			Evaluation temp = gzyyStudentBiz.readOutSecBrief(schDeptFlow);
			if(temp==null){
				Evaluation evaluation = new Evaluation();
				String briefRequrie = request.getParameter("briefRequrie");
				evaluation.setBriefRequrie(briefRequrie);
				evaluation.setUserFlow(userFlow);
				evaluation.setSchDeptFlow(schDeptFlow);
				gzyyStudentBiz.addOutSecBrief(evaluation);
//				model.addAttribute("dataFlow" , dataFlow);
			}
		}

        //大病历
  		if("D001".equals(funcFlow)){
  			gzyyDoctorBiz.addBigHistory(userFlow,schDeptFlow,request);
  		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            gzyyDoctorBiz.addOutPatient(userFlow,schDeptFlow,request);
        }
  		//病种
		if("D002".equals(funcFlow)){
			gzyyDoctorBiz.addCaseClass(userFlow,schDeptFlow,cataFlow,request);
		}
		//操作技能
		if("D003".equals(funcFlow)){
			gzyyDoctorBiz.addOperateSkill(userFlow,schDeptFlow,cataFlow,request);
		}
		//手术
		if("D004".equals(funcFlow)){
			gzyyDoctorBiz.addPOSSkill(userFlow,schDeptFlow,cataFlow,request);
		}
		//出科小结
		if("D005".equals(funcFlow)){
			Map<String, Object> temp = gzyyDoctorBiz.readOutSecBrief(schDeptFlow);
			if(temp==null){
				gzyyDoctorBiz.addOutSecBrief(userFlow,schDeptFlow,request);
			}
		}

		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/viewData"},method={RequestMethod.GET})
	public String viewData(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow,
						   String cataFlow, String dataFlow, HttpServletRequest request, HttpServletResponse response, Model model){

		String result = "res/gzzyres/student/viewData";

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
        	Map<String,Object> enteredDeptEdu = gzyyStudentBiz.readEnteredDeptEdu(schDeptFlow);
        	model.addAttribute("enteredDeptEdu" , enteredDeptEdu);
		}
        //学习情况
        if("0002".equals(funcFlow)){
        	if(StringUtil.isNotBlank(dataFlow)){
            	StudyInfo studyInfo = gzyyStudentBiz.readStudyInfo(dataFlow);
            	model.addAttribute("studyInfo" , studyInfo);
        	}
        }
        //病例与管床
        if("0003".equals(funcFlow)){
        	if(StringUtil.isNotBlank(dataFlow)){
            	MedicalInfo medicalInfo = gzyyStudentBiz.readMedicalInfo(dataFlow);
            	model.addAttribute("medicalInfo" , medicalInfo);
        	}
        }
        //教学活动
        if("0004".equals(funcFlow)){
        	Activity activity = gzyyStudentBiz.findActivity(dataFlow, userFlow);
        	model.addAttribute("activity" , activity);
        }
        //评价科室
        if("0005".equals(funcFlow)){

			String ExamItemType="6";
			List<Map<String,Object>> marks = gzyyStudentBiz.getMarksForDept(userFlow, schDeptFlow, ExamItemType);
			if(marks==null||marks.size()==0)
			{
				ExamItemType="26";
				marks = gzyyStudentBiz.getMarksForDept(userFlow, schDeptFlow, ExamItemType);
			}
			Map<String,Object> examInfo = gzyyStudentBiz.readExamInfo(ExamItemType, schDeptFlow);
			model.addAttribute("examInfo" , examInfo);
			//获取评价模板
			List<Map<String, Object>> assessTmp = gzyyStudentBiz.getExamItems(ExamItemType);
			model.addAttribute("assessTmp" , assessTmp);

			model.addAttribute("marks" , marks);
        }
        //评价带教老师
        if("0006".equals(funcFlow)){

			String ExamItemType="0";
			List<Map<String,Object>> marks = gzyyStudentBiz.getMarksForTec(userFlow, schDeptFlow, ExamItemType);
			if(marks==null||marks.size()==0)
			{
				ExamItemType="20";
				marks = gzyyStudentBiz.getMarksForTec(userFlow, schDeptFlow, ExamItemType);
			}
        	Map<String,Object> examInfo = gzyyStudentBiz.readExamInfo(ExamItemType, schDeptFlow);
        	model.addAttribute("examInfo" , examInfo);

			List<Map<String, Object>> assessTmp = gzyyStudentBiz.getExamItems(ExamItemType);
        	model.addAttribute("assessTmp" , assessTmp);

        	model.addAttribute("marks" , marks);
        }
        //自我评价
        if("0007".equals(funcFlow)){
        	Evaluation evaluation = gzyyStudentBiz.readOutSecBrief(schDeptFlow);
        	model.addAttribute("evaluation" , evaluation);
        }
        //DOPS评估表
        if("0008".equals(funcFlow)){
    		Map<String,Object> outDops = gzyyTeacherBiz.readOutDops(schDeptFlow);
    		model.addAttribute("outDops" , outDops);

			Map<String,Object> examInfo = gzyyTeacherBiz.readExamInfo(schDeptFlow,"11");
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
			List<Map<String, Object>> assessTmp = gzyyStudentBiz.getExamItemsByItemId("11",ExamItemID);
        	model.addAttribute("assessTmp" , assessTmp);

			List<Map<String,Object>> marks = gzyyTeacherBiz.getMarks(schDeptFlow,"11");
        	model.addAttribute("marks" , marks);
        }
        //Mini-CEX评估表
        if("0009".equals(funcFlow)){
			Map<String,Object> doctorInfo = gzyyTeacherBiz.readDoctorInfo(userFlow, schDeptFlow);
			model.addAttribute("doctorInfo", doctorInfo);
			String HosCySecID= (String) doctorInfo.get("HosCySecID");
			List<Map<String,String>> depts=gzyyTeacherBiz.isFzDept(schDeptFlow);
//			List<Map<String,String>> depts=null;
			if(depts!=null&&depts.size()>0)
			{
				funcFlow="0009_1";

				Map<String, Object> outMiniCex = gzyyTeacherBiz.readOutMiniCexFz(schDeptFlow);
				model.addAttribute("outMiniCex", outMiniCex);

			}else {
				Map<String, Object> outMiniCex = gzyyTeacherBiz.readOutMiniCex(schDeptFlow);
				model.addAttribute("outMiniCex", outMiniCex);

				Map<String, Object> examInfo = gzyyTeacherBiz.readExamInfo(schDeptFlow, "12");
				model.addAttribute("examInfo", examInfo);

				List<Map<String, Object>> assessTmp = gzyyStudentBiz.getExamItemsMiniCex("12");
				model.addAttribute("assessTmp", assessTmp);

				List<Map<String, Object>> marks = gzyyTeacherBiz.getMarks(schDeptFlow, "12");
				model.addAttribute("marks", marks);
			}
        }

		//大病历
  		if("D001".equals(funcFlow)){
  			if(StringUtil.isNotBlank(dataFlow)){
  	  			Map<String,Object> bigHistory = gzyyDoctorBiz.readBigHistory(userFlow,schDeptFlow,dataFlow);
  				model.addAttribute("bigHistory" , bigHistory);
  			}
  		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            if(StringUtil.isNotBlank(dataFlow)){
                Map<String,Object> outPatient = gzyyDoctorBiz.readOutPatient(userFlow,schDeptFlow,dataFlow);
                model.addAttribute("outPatient" , outPatient);
            }
        }
  		//病种
		if("D002".equals(funcFlow)){
			if(StringUtil.isNotBlank(dataFlow)){
  	  			Map<String,Object> caseClass = gzyyDoctorBiz.readCaseClass(userFlow,schDeptFlow,dataFlow);
  				model.addAttribute("caseClass" , caseClass);
  			}
			if(StringUtil.isNotBlank(cataFlow)){
				String caseClassCataName = gzyyDoctorBiz.readCaseClassCataName(cataFlow);
				model.addAttribute("caseClassCataName" , caseClassCataName);
			}
		}
		//操作技能
		if("D003".equals(funcFlow)){
			if(StringUtil.isNotBlank(dataFlow)){
  	  			Map<String,Object> operateSkill = gzyyDoctorBiz.readOperateSkill(userFlow,schDeptFlow,dataFlow);
  				model.addAttribute("operateSkill" , operateSkill);
  			}
			if(StringUtil.isNotBlank(cataFlow)){
				String operateSkillCataName = gzyyDoctorBiz.readOperateSkillCataName(cataFlow);
				model.addAttribute("operateSkillCataName" , operateSkillCataName);
			}
		}
		//手术
		if("D004".equals(funcFlow)){
			if(StringUtil.isNotBlank(dataFlow)){
  	  			Map<String,Object> posSkill = gzyyDoctorBiz.readPOSSkill(userFlow,schDeptFlow,dataFlow);
  				model.addAttribute("posSkill" , posSkill);
  			}
			if(StringUtil.isNotBlank(cataFlow)){
				String posSkillCataName = gzyyDoctorBiz.readPOSSkillCataName(cataFlow);
				model.addAttribute("posSkillCataName" , posSkillCataName);
			}
		}
		//出科小结
		if("D005".equals(funcFlow)){
			Map<String,String> doctor = gzyyAppBiz.readUser(userFlow);
			model.addAttribute("doctor" , doctor);

			Map<String, Object> outSecBrief = gzyyDoctorBiz.readOutSecBrief(schDeptFlow);
			model.addAttribute("outSecBrief" , outSecBrief);
		}
		model.addAttribute("nfyyFuncFlow0001HttpUrl", nfyyFuncFlow0001HttpUrl);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		Map<String,List<Map<String,Object>>> itemMap=gzyyAppBiz.getItemMap();
		model.addAttribute("itemMap", itemMap);
		model.addAttribute("funcFlow", funcFlow);
		return result;
	}

	@RequestMapping(value={"/modData"},method={RequestMethod.PUT , RequestMethod.POST})
	public String modData(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow,
						  String cataFlow, String dataFlow, HttpServletRequest request , Model model){

		String result = "res/gzzyres/student/modData";
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
			gzyyStudentBiz.modEnteredDeptEdu(enteredDeptEdu);
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
			gzyyStudentBiz.modStudyInfo(studyInfo );
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
			gzyyStudentBiz.modMedicalInfo(medicalInfo);
		}
		//教学活动
		if("0004".equals(funcFlow)){
			Activity exitactivity = gzyyStudentBiz.findActivity(dataFlow, userFlow);
			Integer score = exitactivity.getScore();
			if(score==null){
				//是否可参加
				Activity activity = gzyyStudentBiz.readActivity(dataFlow);
				String currDateTime = DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
				String startDateTime = activity.getTime();

				int sing = (int) DateUtil.signMinutesBetweenTowDate(currDateTime, startDateTime);
				if (sing >= -10) {
					gzyyStudentBiz.joinActivity(dataFlow, schDeptFlow , userFlow);
				}else{
					model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_NOT_JOIN_ACTIVITY);
					model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_NOT_JOIN_ACTIVITY);
					return result;
				}
			}else if(score>=0){
				Activity exitActivity = gzyyStudentBiz.readActivity(dataFlow);
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
					gzyyStudentBiz.scoreActivity(activity);
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
			gzyyStudentBiz.modOutSecBrief(schDeptFlow, briefRequrie);
		}
		//DOPS评估表
        if("0008".equals(funcFlow)){
        	gzyyStudentBiz.saveOutDops(userFlow, schDeptFlow,request);
		}
        //Mini-CEX评估表
        if("0009".equals(funcFlow)){
			List<Map<String,String>> depts=gzyyTeacherBiz.isFzDept(schDeptFlow);
			if(depts!=null&&depts.size()>0) {

			}else {
				gzyyStudentBiz.saveOutMiniCex(userFlow, schDeptFlow, request);
			}
		}
        //大病历
  		if("D001".equals(funcFlow)){
  			gzyyDoctorBiz.modBigHistory(userFlow,schDeptFlow,dataFlow,request);
  		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            gzyyDoctorBiz.modOutPatient(userFlow,schDeptFlow,dataFlow,request);
        }
  		//病种
		if("D002".equals(funcFlow)){
			gzyyDoctorBiz.modCaseClass(userFlow,schDeptFlow,cataFlow,dataFlow,request);
		}
		//操作技能
		if("D003".equals(funcFlow)){
			gzyyDoctorBiz.modOperateSkill(userFlow,schDeptFlow,cataFlow,dataFlow,request);
		}
		//手术
		if("D004".equals(funcFlow)){
			gzyyDoctorBiz.modPOSSkill(userFlow,schDeptFlow,cataFlow,dataFlow,request);
		}
		//出科小结
		if("D005".equals(funcFlow)){
			Map<String, Object> temp = gzyyDoctorBiz.readOutSecBrief(schDeptFlow);
			if(temp!=null){
				gzyyDoctorBiz.modOutSecBrief(userFlow,schDeptFlow,request);
			}
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/deleteData"},method={RequestMethod.POST , RequestMethod.DELETE})
	public String deleteData(String userFlow,String schDeptFlow,String funcTypeId,String funcFlow,
			String dataFlow,HttpServletRequest request , Model model){
		String result = "res/gzzyres/student/deleteData";
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
			gzyyStudentBiz.delStudyInfo(dataFlow);
		}
		//病例与管床
		if("0003".equals(funcFlow)){
			gzyyStudentBiz.delMedicalInfo(dataFlow);
		}
		//大病历
  		if("D001".equals(funcFlow)){
  			gzyyDoctorBiz.delBigHistory(userFlow,schDeptFlow,dataFlow);
  		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            gzyyDoctorBiz.delOutPatient(userFlow,schDeptFlow,dataFlow);
        }
  		//病种
		if("D002".equals(funcFlow)){
			gzyyDoctorBiz.delCaseClass(userFlow,schDeptFlow,dataFlow);
		}
		//操作技能
		if("D003".equals(funcFlow)){
			gzyyDoctorBiz.delOperateSkill(userFlow,schDeptFlow,dataFlow);
		}
		//手术
		if("D004".equals(funcFlow)){
			gzyyDoctorBiz.delPOSSkill(userFlow,schDeptFlow,dataFlow);
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	//住培反馈功能，同湖北
	//住培反馈
	@RequestMapping(value = "/suggestions")
	public String suggestions(String userFlow,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzyres/student/suggestions";
		}
		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}
		model.addAttribute("pageIndex",pageIndex);
		model.addAttribute("pageSize",pageSize);
		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("userFlow",userFlow);
		paramMap.put("start",start);
		paramMap.put("end",end);
		List<Map<String,String>> trainingOpinions=resLiveTrainingBiz.readByOpinionUserFlow(paramMap);
		model.addAttribute("trainingOpinions",trainingOpinions);
		paramMap.put("start",1);
		paramMap.put("end",Integer.MAX_VALUE);
		model.addAttribute("dataCount", resLiveTrainingBiz.readByOpinionUserFlow(paramMap).size());
		return "res/gzzyres/student/suggestions";
	}

	@RequestMapping(value="/delOpinions")
	public String delOpinions(String trainingOpinionFlow,String userFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzyres/success";
		}
		if(StringUtil.isEmpty(trainingOpinionFlow)){
			model.addAttribute("resultId", "32602");
			model.addAttribute("resultType", "意见反馈流水号为空");
			return "res/gzzyres/success";
		}
		Map<String,String> trainingOpinion = resLiveTrainingBiz.read(trainingOpinionFlow);
		if(trainingOpinion==null){
			model.addAttribute("resultId", "32603");
			model.addAttribute("resultType", "意见反馈不存在");
			return "res/gzzyres/success";
		}
		resLiveTrainingBiz.delOpinion(trainingOpinionFlow);
//		if(count==0){
//			model.addAttribute("resultId", "32601");
//			model.addAttribute("resultType", "删除失败");
//		}
		return "res/gzzyres/success";
	}
	@RequestMapping(value="/opinionsDetail")
	public String opinionsDetail(String trainingOpinionFlow,String userFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzyres/student/suggestions";
		}
		if(StringUtil.isEmpty(trainingOpinionFlow)){
			model.addAttribute("resultId", "32602");
			model.addAttribute("resultType", "意见反馈流水号为空");
			return "res/gzzyres/student/suggestions";
		}
		Map<String,String> trainingOpinion = resLiveTrainingBiz.read(trainingOpinionFlow);
		List<Map<String,String>> trainingOpinions=new ArrayList<>();
		trainingOpinions.add(trainingOpinion);
		model.addAttribute("trainingOpinions",trainingOpinions);
		return "res/gzzyres/student/suggestions";
	}
	//反馈保存操作
	@RequestMapping(value="/saveOpinions")
	public String saveOpinions(ResTrainingOpinion trainingOpinion,String userFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzyres/success";
		}
		if(StringUtil.isEmpty(trainingOpinion.getOpinionUserContent())){
			model.addAttribute("resultId", "32602");
			model.addAttribute("resultType", "意见反馈信息为空");
			return "res/gzzyres/success";
		}else{
			try {
				String content = URLDecoder.decode(trainingOpinion.getOpinionUserContent(), "UTF-8") ;
				trainingOpinion.setOpinionUserContent(content);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		Map<String,String> currentUser =gzyyAppBiz.readUser(userFlow);
		String userName = currentUser.get("userName");
		if(StringUtil.isNotBlank(userName)){
			trainingOpinion.setOpinionUserName(userName);
		}
		trainingOpinion.setOpinionUserFlow(userFlow);
		String orgFlow = currentUser.get("hosID");
		if(StringUtil.isBlank(orgFlow)){
			Map<String,String> doctor = gzyyAppBiz.readDoctor(userFlow);
			orgFlow = doctor.get("hosID");
		}
//		String orgName = currentUser.getOrgName();
		if(StringUtil.isNotBlank(orgFlow)){
			trainingOpinion.setOrgFlow(orgFlow);
		}
//		if(StringUtil.isNotBlank(orgName)){
//			trainingOpinion.setOrgName(orgName);
//		}
		String currTime = DateUtil.getCurrDateTime();
		trainingOpinion.setEvaTime(currTime);
		Map<String,Object> paramMap = new HashMap<>();
		if(trainingOpinion!=null){
			paramMap.put("Record_Flow",trainingOpinion.getTrainingOpinionFlow());
			paramMap.put("Opinion_UserID",trainingOpinion.getOpinionUserFlow());
			paramMap.put("Opinion_UserName",trainingOpinion.getOpinionUserName());
			paramMap.put("Opinion_User_Content",trainingOpinion.getOpinionUserContent());
			paramMap.put("Eva_Time",trainingOpinion.getEvaTime());
			paramMap.put("Org_Flow",trainingOpinion.getOrgFlow());
			paramMap.put("Org_Name",trainingOpinion.getOrgName());
			paramMap.put("Create_UserID",trainingOpinion.getOpinionUserFlow());
		}
		resLiveTrainingBiz.edit(paramMap);
//		if(count==0){
//			model.addAttribute("resultId", "32601");
//			model.addAttribute("resultType", "保存失败");
//		}
		return "res/gzzyres/success";
	}

	//最新指南（同江苏住培）
	@SuppressWarnings("unchecked")
	@RequestMapping(value={"/getZhupeiNotices"})
	public String getZhupeiNotices(String userFlow,Integer pageIndex,Integer pageSize,String noticeTitle,HttpServletRequest request,
								   Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzyres/student/noticeList";
		}

		Map<String,String> currUser = gzyyAppBiz.readUser(userFlow);
		String orgFlow = currUser.get("hosID");
		Map<String,String> doctor = gzyyAppBiz.readDoctor(userFlow);
		if(StringUtil.isBlank(orgFlow)){
			orgFlow = doctor.get("hosID");
		}
		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}
		model.addAttribute("pageIndex",pageIndex);
		model.addAttribute("pageSize",pageSize);
		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("start",start);
		paramMap.put("end",end);
		paramMap.put("noticeTitle",noticeTitle);
		List<Map<String,String>> tarinNotices = resLiveTrainingBiz.searchByTitleOrgFlow(paramMap);
		//获取访问路径前缀
//		String uploadBaseUrl = gzzyresAppBiz.getCfgCode("upload_base_url");
//		model.addAttribute("uploadBaseUrl",uploadBaseUrl);

		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/gzzyres/showNotice/no-pic.png";
		model.addAttribute("hostUrl",hostUrl);
		model.addAttribute("tarinNotices",tarinNotices);
		paramMap.put("start",1);
		paramMap.put("end",Integer.MAX_VALUE);
		model.addAttribute("dataCount", resLiveTrainingBiz.searchByTitleOrgFlow(paramMap).size());
		return "res/gzzyres/student/noticeList";
	}

	//展示指南详情（同江苏住培）
	@RequestMapping(value={"/zpNoticeDetail"})
	public String zpNoticeDetail(String userFlow,String recordFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzyres/student/zpNoticeDetail";
		}
		if(StringUtil.isEmpty(recordFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培指南标识符为空");
			return "res/gzzyres/student/zpNoticeDetail";
		}
		Map<String,String> tarinNotices = resLiveTrainingBiz.readNotice(recordFlow);
		if(tarinNotices==null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培指南标不存在，请刷新列表页面");
			return "res/gzzyres/student/zpNoticeDetail";
		}
		model.addAttribute("title",tarinNotices.get("Res_Notice_Title"));
		model.addAttribute("content",tarinNotices.get("Res_Notice_Content"));
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/gzzyres/showNotice/showNoticeDetail.jsp?recordFlow="+recordFlow;
		String androidimgurl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/gzzyres/showNotice/showNoticeDetail2.jsp?recordFlow="+recordFlow;
		model.addAttribute("iosDetailUrl",hostUrl);
		model.addAttribute("androidDetailUrl",androidimgurl);
		return "res/gzzyres/student/zpNoticeDetail";
	}
	@RequestMapping(value={"/showZPdetail"},method ={ RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String showZPdetail(String recordFlow,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=new HashMap<>();
		Map<String,String> tarinNotices = resLiveTrainingBiz.readNotice(recordFlow);
		if(tarinNotices!=null)
		{
			map.put("title","住培指南详情——【"+tarinNotices.get("Res_Notice_Title")+"】");
			map.put("content",tarinNotices.get("Res_Notice_Content"));
		}else{
			map.put("title","无详细信息");
			map.put("content","住培指南详情");
		}
		return JSON.toJSONString(map);
	}
	//签到功能
	@RequestMapping(value={"/studentSignIn"},method ={RequestMethod.POST})
	public String studentSignIn(String userFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzyres/student/studentSignIn";
		}
		Map<String,String> currUser = gzyyAppBiz.readUser(userFlow);
		if(currUser==null){
			model.addAttribute("resultId", "3011102");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzyres/student/studentSignIn";
		}
//		Map<String,String> doctor = gzyyAppBiz.readDoctor(userFlow);
//		if(doctor==null){
//			model.addAttribute("resultId", "3011102");
//			model.addAttribute("resultType", "医师信息不存在");
//			return "res/gzzyres/student/studentSignIn";
//		}
		String nowDay=DateUtil.getCurrDate();
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("nowDay",nowDay);
		paramMap.put("userFlow",userFlow);
		List<Map<String,String>> list=gzyyAppBiz.getAttendanceDetailList(paramMap);
		int count=0;
		if(list!=null){
			count=list.size();
		}
		model.addAttribute("count",count);
		model.addAttribute("list",list);
		model.addAttribute("nowDay",nowDay);
		String timeN[]=nowDay.split("-");
		String timeInfoN=timeN[0]+"年"+timeN[1]+"月"+timeN[2]+"日";
		model.addAttribute("chinessNowDay",timeInfoN);
		model.addAttribute("currUser",currUser);
		return "res/gzzyres/student/studentSignIn";
	}

	@RequestMapping(value={"/saveSignIn"},method ={RequestMethod.POST})
	public String saveSignIn(String userFlow,String date,String time,String local,String remark,HttpServletRequest request,
							 HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzyres/success";
		}
		if(StringUtil.isBlank(date)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "签到日期为空");
			return "res/gzzyres/success";
		}
		if(StringUtil.isBlank(time)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "签到时间为空");
			return "res/gzzyres/success";
		}
		if(StringUtil.isBlank(local)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "签到地点为空");
			return "res/gzzyres/success";
		}
		Map<String,String> user = gzyyAppBiz.readUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011102");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzzyres/success";
		}
//		Map<String,String> resDoctor = gzyyAppBiz.readDoctor(userFlow);
//		if(resDoctor==null){
//			model.addAttribute("resultId", "3011102");
//			model.addAttribute("resultType", "医师信息不存在");
//			return "res/gzzyres/success";
//		}
		String nowDay=DateUtil.getCurrDate();
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("userFlow",userFlow);
		paramMap.put("nowDay",nowDay);
		Map<String,String> attendance=gzyyAppBiz.getAttendance(paramMap);
		if(attendance==null) {
			String attendanceFlow= PkUtil.getUUID();
			Map<String,Object> paramMap1 = new HashMap<>();
			paramMap1.put("recordFlow",attendanceFlow);
			paramMap1.put("userFlow",userFlow);
			paramMap1.put("userName",user.get("userName"));
			paramMap1.put("attendDate",nowDay);
			paramMap1.put("attendLocal",local);
			paramMap1.put("attendTime",time);
			paramMap1.put("selfRemarks",remark);
			paramMap1.put("createTime",DateUtil.getCurrDateTime());
			paramMap1.put("createUserFlow",userFlow);
			gzyyAppBiz.addAttendance(paramMap1);
		}
		if(attendance!=null){
			Map<String,Object> paramMap2 = new HashMap<>();
			paramMap2.put("recordFlow",PkUtil.getUUID());
			paramMap2.put("attendanceFlow",attendance.get("Record_Flow"));
			paramMap2.put("userFlow",userFlow);
			paramMap2.put("userName",user.get("userName"));
			paramMap2.put("attendDate",nowDay);
			paramMap2.put("attendLocal",local);
			paramMap2.put("attendTime",time);
			paramMap2.put("selfRemarks",remark);
			paramMap2.put("createTime",DateUtil.getCurrDateTime());
			paramMap2.put("createUserFlow",userFlow);
			gzyyAppBiz.addAttendanceDetail(paramMap2);
		}
//		if(count!=1){
//			model.addAttribute("resultId", "3011102");
//			model.addAttribute("resultType", "签到失败！");
//		}
		return "res/gzzyres/success";
	}
	@RequestMapping(value={"/joinExam"})
	public String joinExam(String userFlow,String SecID,String SpecialtyID,String processFlow,HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzyres/student/joinExam";
		}
		if(StringUtil.isEmpty(SecID)){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "SecID标识符为空");
			return "res/gzzyres/student/joinExam";
		}
		if(StringUtil.isEmpty(SpecialtyID)){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "SpecialtyID标识符为空");
			return "res/gzzyres/student/joinExam";
		}
		//考试地址
		if(!StringUtil.isNotBlank(mobile_after_url)){
			model.addAttribute("resultId", "40405");
			model.addAttribute("resultType", "请联系管理员维护考试地址!");
			return "res/gzzyres/student/joinExam";
		}
		Map<String,String> user=gzyyAppBiz.readUser(userFlow);
		//试卷id
		String ExamSoluID = "0";
		//时间戳
		String Date = "0";
		String examType="0";
		String roleFlow=user.get("roleFlow");
		if (StringUtil.isBlank(roleFlow)) {
			model.addAttribute("resultId", "40407");
			model.addAttribute("resultType", "学员人员类型错误!");
			return "res/gzzyres/student/joinExam";
		}
		//标准科室
		Map<String,String> paper = gzyyStudentBiz.getPaperByStandardDeptId(SecID,SpecialtyID,roleFlow);
		if (paper != null) {
			ExamSoluID = paper.get("Examination");
		}
		if ("0".equals(ExamSoluID)) {
			model.addAttribute("resultId", "40407");
			model.addAttribute("resultType", "该科室暂无试卷信息!");
			return "res/gzzyres/student/joinExam";
		}
		String testUrl=mobile_after_url+"?Action=ChuKeMobileExam&ExamSoluID="+ExamSoluID+"&CardID="+ URLEncoder.encode(user.get("userCode"), "utf-8")+"&Date="+Date+"&TestNum=";
		model.addAttribute("testUrl",testUrl);
		return "res/gzzyres/student/joinExam";
	}
}
