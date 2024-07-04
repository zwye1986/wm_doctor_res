package com.pinde.res.ctrl.nfyy;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.NfyyGlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.MathUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.nfyy.INfyyAppBiz;
import com.pinde.res.biz.nfyy.INfyyDoctorBiz;
import com.pinde.res.biz.nfyy.INfyyStudentBiz;
import com.pinde.res.biz.nfyy.INfyyTeacherBiz;
import com.pinde.res.dao.nfyy.ext.StudentMapper;
import com.pinde.res.enums.nfyy.DeptStatusEnum;
import com.pinde.res.model.nfyy.mo.Activity;
import com.pinde.res.model.nfyy.mo.Evaluation;
import com.pinde.res.model.nfyy.mo.MedicalInfo;
import com.pinde.res.model.nfyy.mo.StudyInfo;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/nfyy/student")
public class NfyyStudentController {

	private static Logger logger = LoggerFactory.getLogger(NfyyStudentController.class);

	@Autowired
	private INfyyAppBiz nfyyAppBiz;
	@Autowired
	private INfyyStudentBiz nfyyStudentBiz;
	@Autowired
	private INfyyTeacherBiz nfyyTeacherBiz;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private INfyyDoctorBiz nfyyDoctorBiz;
	@Value("#{configProperties['nfyy.funcFlow.0001.httpurl']}")
	private String nfyyFuncFlow0001HttpUrl;
	@Value("#{configProperties['hzyy.mobile.after.url']}")
	private String mobile_after_url;
	@Value("#{configProperties['com.pinde.net.cfg.http']}")
	private String cfg_url;

	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(getClass().getCanonicalName()+" some error happened",e);
		return "res/nfyy/500";
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

		String result = "res/nfyy/student/schPlanList";
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


		Map<String,String> userInfo = nfyyAppBiz.readUser(userFlow);
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

		List<Map<String,Object>> schPlans = nfyyStudentBiz.getPlanList(userFlow, schStatusId, schDeptName , pageIndex, pageSize,examStatusId);
		model.addAttribute("schPlans" , schPlans);
		model.addAttribute("dataCount", nfyyStudentBiz.getPlanList(userFlow,schStatusId,schDeptName , 1,Integer.MAX_VALUE,examStatusId).size());

		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/schFuncList"},method={RequestMethod.GET})
	public String schFuncList(String userFlow,String schDeptFlow,Model model){
		String result = "res/nfyy/student/schFuncList";
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

		Map<String,String> doctor = nfyyAppBiz.readUser(userFlow);
		model.addAttribute("doctor" , doctor);
		//查看是否填写过入科教育
		Map<String,Object> deptEdu = nfyyStudentBiz.readEnteredDeptEdu(schDeptFlow);
		model.addAttribute("deptEdu" , deptEdu);

		//查看是否评价过科室
		List<Map<String,Object>> marksDept = nfyyStudentBiz.getMarksForDept(userFlow, schDeptFlow);
		model.addAttribute("marksDept" , marksDept);

		//查看是否评价过老师
    	List<Map<String,Object>> marksTec = nfyyStudentBiz.getMarksForTec(userFlow, schDeptFlow);
		model.addAttribute("marksTec" , marksTec);

		//Dops量表
		Map<String,Object> outDops = nfyyTeacherBiz.readOutDops(schDeptFlow);
		model.addAttribute("outDops" , outDops);

		//Minicex
		Map<String,Object> outMiniCex = nfyyTeacherBiz.readOutMiniCex(schDeptFlow);
		model.addAttribute("outMiniCex" , outMiniCex);

		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/categoryProgress"},method={RequestMethod.GET})
	public String categoryProgress(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow,
								   Integer pageIndex, Integer pageSize, String dataCount, Model model){
		String result = "res/nfyy/student/categoryProgress";
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
			List<Map<String,Object>> caseClassCatas = nfyyDoctorBiz.getCaseClassCatas(userFlow,schDeptFlow,pageIndex, pageSize);
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
			model.addAttribute("dataCount", nfyyDoctorBiz.getCaseClassCatas(userFlow,schDeptFlow,1, Integer.MAX_VALUE).size());
		}
		//操作技能
		if("D003".equals(funcFlow)){
			List<Map<String,Object>> operateSkillCatas = nfyyDoctorBiz.getOperateSkillCatas(userFlow,schDeptFlow,pageIndex, pageSize);
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
			model.addAttribute("dataCount", nfyyDoctorBiz.getOperateSkillCatas(userFlow,schDeptFlow,1, Integer.MAX_VALUE).size());
		}
		//手术
		if("D004".equals(funcFlow)){
			List<Map<String,Object>> posSkillCatas = nfyyDoctorBiz.getPOSSkillCatas(userFlow,schDeptFlow,pageIndex, pageSize);
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
			model.addAttribute("dataCount", nfyyDoctorBiz.getPOSSkillCatas(userFlow,schDeptFlow,1, Integer.MAX_VALUE).size());
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
		String result = "res/nfyy/student/dataList";
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
//			Map<String,Object> enteredDeptEdu = nfyyStudentBiz.readEnteredDeptEdu(schDeptFlow);
//			model.addAttribute("enteredDeptEdu" , enteredDeptEdu);
//			model.addAttribute("dataCount" , 1);
//		}
		//学习情况
		if("0002".equals(funcFlow)){
			List<StudyInfo> studyInfos = nfyyStudentBiz.getStudyInfos(schDeptFlow, pageIndex, pageSize);
			model.addAttribute("studyInfos" , studyInfos);
			model.addAttribute("dataCount", nfyyStudentBiz.getStudyInfos(schDeptFlow, 1, Integer.MAX_VALUE).size());
		}
		//病例与管床
		if("0003".equals(funcFlow)){
			List<MedicalInfo> medicalInfos = nfyyStudentBiz.getMedicalInfos(schDeptFlow, pageIndex, pageSize);
			for(MedicalInfo medicalInfo : medicalInfos){
				String name = StringUtil.defaultString((String)medicalInfo.getName());
				if(name.length()>0){
					name = name.substring(0, name.length() - 1) + "★";
					medicalInfo.setName(name);
				}
			}
			int count = nfyyStudentBiz.getMedicalInfos(schDeptFlow, 1, Integer.MAX_VALUE).size();
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
			List<Activity> activitys = nfyyStudentBiz.getActicitys(params , pageIndex , pageSize);
			model.addAttribute("activitys" , activitys);
			int count =  nfyyStudentBiz.getActicitys(params , 1 , Integer.MAX_VALUE).size();
			model.addAttribute("dataCount" , count);
		}

		//大病历
		if("D001".equals(funcFlow)){
			List<Map<String,Object>> bigHistorys = nfyyDoctorBiz.getBigHistorys(userFlow,schDeptFlow, pageIndex, pageSize);
			for(Map<String,Object> bigHistory : bigHistorys){
				String RecData2 = StringUtil.defaultString((String)bigHistory.get("RecData2"));
				if(RecData2.length()>0){
					RecData2 = RecData2.substring(0, RecData2.length() - 1) + "★";
					bigHistory.put("RecData2", RecData2);
				}
			}
			model.addAttribute("bigHistorys" , bigHistorys);
			model.addAttribute("dataCount", nfyyDoctorBiz.getBigHistorys(userFlow,schDeptFlow, 1, Integer.MAX_VALUE).size());
		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            List<Map<String,Object>> outPatients = nfyyDoctorBiz.getOutPatients(userFlow,schDeptFlow, pageIndex, pageSize);
            for(Map<String,Object> outPatient : outPatients){
                String RecData2 = StringUtil.defaultString((String)outPatient.get("RecData2"));
                if(RecData2.length()>0){
                    RecData2 = RecData2.substring(0,RecData2.length()-1)+"★";
                    outPatient.put("RecData2", RecData2);
                }
            }
            model.addAttribute("outPatients" , outPatients);
            model.addAttribute("dataCount", nfyyDoctorBiz.getOutPatients(userFlow,schDeptFlow, 1, Integer.MAX_VALUE).size());
        }

        //病种
		if("D002".equals(funcFlow)){
			List<Map<String,Object>> caseClasses = nfyyDoctorBiz.getCaseClasses(userFlow,schDeptFlow,cataFlow, pageIndex, pageSize);
			for(Map<String,Object> caseClass : caseClasses){
				String RecData3 = StringUtil.defaultString((String)caseClass.get("RecData3"));
				if(RecData3.length()>0){
					RecData3 = RecData3.substring(0, RecData3.length() - 1) + "★";
					caseClass.put("RecData3", RecData3);
				}
			}
			model.addAttribute("caseClasses" , caseClasses);
			model.addAttribute("dataCount", nfyyDoctorBiz.getCaseClasses(userFlow,schDeptFlow,cataFlow, 1, Integer.MAX_VALUE).size());
		}
		//操作技能
		if("D003".equals(funcFlow)){
			List<Map<String,Object>> operateSkills = nfyyDoctorBiz.getOperateSkills(userFlow,schDeptFlow,cataFlow, pageIndex, pageSize);
			for(Map<String,Object> operateSkill : operateSkills){
				String RecData3 = StringUtil.defaultString((String)operateSkill.get("RecData3"));
				if(RecData3.length()>0){
					RecData3 = RecData3.substring(0, RecData3.length() - 1) + "★";
					operateSkill.put("RecData3", RecData3);
				}
			}
			model.addAttribute("operateSkills" , operateSkills);
			model.addAttribute("dataCount", nfyyDoctorBiz.getOperateSkills(userFlow,schDeptFlow,cataFlow, 1, Integer.MAX_VALUE).size());
		}
		//手术
		if("D004".equals(funcFlow)){
			List<Map<String,Object>> posSkills = nfyyDoctorBiz.getPOSSkills(userFlow,schDeptFlow,cataFlow, pageIndex, pageSize);
			for(Map<String,Object> posSkill : posSkills){
				String RecData3 = StringUtil.defaultString((String)posSkill.get("RecData3"));
				if(RecData3.length()>0){
					RecData3 = RecData3.substring(0, RecData3.length() - 1) + "★";
					posSkill.put("RecData3", RecData3);
				}
			}
			model.addAttribute("posSkills" , posSkills);
			model.addAttribute("dataCount", nfyyDoctorBiz.getPOSSkills(userFlow,schDeptFlow,cataFlow, 1, Integer.MAX_VALUE).size());
		}

		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/addData"},method={RequestMethod.POST})
	public String addData(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow,
						  String cataFlow, HttpServletRequest request , Model model){

		String result = "res/nfyy/student/addData";
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
			Map<String,Object> exitEnteredDeptEdu = nfyyStudentBiz.readEnteredDeptEdu(schDeptFlow);
			if(exitEnteredDeptEdu==null){
				Map<String,Object> enteredDeptEdu = new HashMap<String,Object>();
				enteredDeptEdu.put("userFlow", userFlow);
				enteredDeptEdu.put("schDeptFlow", schDeptFlow);
				enteredDeptEdu.put("tecName", request.getParameter("tecName"));
				enteredDeptEdu.put("dateTime", request.getParameter("dateTime"));
				String dataFlow = nfyyStudentBiz.addEnteredDeptEdu(enteredDeptEdu);
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
			String dataFlow = nfyyStudentBiz.addStudyInfo(studyInfo);
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
			String dataFlow = nfyyStudentBiz.addMedicalInfo(medicalInfo);
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
			List<Map<String, Object>> assessTmp = nfyyStudentBiz.getExamItems("6");
			for(Map<String, Object> tmp:assessTmp){
				String reqItemId = (String) tmp.get("reqItemId");
				String score = request.getParameter(reqItemId+"_score");
				String cause = request.getParameter(reqItemId+"_cause");
				Map<String,Object> e = new HashMap<String,Object>();
				String  markDF1="0".equals(score)?"9":score;
				e.put("markDF", score);
				e.put("markDF1", markDF1);
				e.put("markKF", cause);
				e.put("reqItemId", reqItemId);
				marks.add(e);
			}
			nfyyStudentBiz.addAssess(examInfo , marks );
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
			List<Map<String, Object>> assessTmp = nfyyStudentBiz.getExamItems("0");
			for(Map<String, Object> tmp:assessTmp){
				String reqItemId = (String) tmp.get("reqItemId");
				String score = request.getParameter(reqItemId+"_score");
				String cause = request.getParameter(reqItemId+"_cause");
				Map<String,Object> e = new HashMap<String,Object>();
				String  markDF1="0".equals(score)?"9":score;
				e.put("markDF", score);
				e.put("markDF1", markDF1);
				e.put("reqItemId", reqItemId);
				marks.add(e);
			}

			nfyyStudentBiz.addAssess(examInfo , marks );
		}
		//自我评价
		if("0007".equals(funcFlow)){
			Evaluation temp = nfyyStudentBiz.readOutSecBrief(schDeptFlow);
			if(temp==null){
				Evaluation evaluation = new Evaluation();
				String briefRequrie = request.getParameter("briefRequrie");
				evaluation.setBriefRequrie(briefRequrie);
				evaluation.setUserFlow(userFlow);
				evaluation.setSchDeptFlow(schDeptFlow);
				nfyyStudentBiz.addOutSecBrief(evaluation);
//				model.addAttribute("dataFlow" , dataFlow);
			}
		}

        //大病历
  		if("D001".equals(funcFlow)){
  			nfyyDoctorBiz.addBigHistory(userFlow,schDeptFlow,request);
  		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            nfyyDoctorBiz.addOutPatient(userFlow,schDeptFlow,request);
        }
  		//病种
		if("D002".equals(funcFlow)){
			nfyyDoctorBiz.addCaseClass(userFlow,schDeptFlow,cataFlow,request);
		}
		//操作技能
		if("D003".equals(funcFlow)){
			nfyyDoctorBiz.addOperateSkill(userFlow,schDeptFlow,cataFlow,request);
		}
		//手术
		if("D004".equals(funcFlow)){
			nfyyDoctorBiz.addPOSSkill(userFlow,schDeptFlow,cataFlow,request);
		}
		//出科小结
		if("D005".equals(funcFlow)){
			Map<String, Object> temp = nfyyDoctorBiz.readOutSecBrief(schDeptFlow);
			if(temp==null){
				nfyyDoctorBiz.addOutSecBrief(userFlow,schDeptFlow,request);
			}
		}

		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/viewData"},method={RequestMethod.GET})
	public String viewData(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow,
						   String cataFlow, String dataFlow, HttpServletRequest request, HttpServletResponse response, Model model){

		String result = "res/nfyy/student/viewData";

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
        	Map<String,Object> enteredDeptEdu = nfyyStudentBiz.readEnteredDeptEdu(schDeptFlow);
        	model.addAttribute("enteredDeptEdu" , enteredDeptEdu);
		}
        //学习情况
        if("0002".equals(funcFlow)){
        	if(StringUtil.isNotBlank(dataFlow)){
            	StudyInfo studyInfo = nfyyStudentBiz.readStudyInfo(dataFlow);
            	model.addAttribute("studyInfo" , studyInfo);
        	}
        }
        //病例与管床
        if("0003".equals(funcFlow)){
        	if(StringUtil.isNotBlank(dataFlow)){
            	MedicalInfo medicalInfo = nfyyStudentBiz.readMedicalInfo(dataFlow);
            	model.addAttribute("medicalInfo" , medicalInfo);
        	}
        }
        //教学活动
        if("0004".equals(funcFlow)){

        	Activity activity = nfyyStudentBiz.findActivity(dataFlow, userFlow);
        	model.addAttribute("activity" , activity);

        }
        //评价科室
        if("0005".equals(funcFlow)){
        	Map<String,Object> examInfo = nfyyStudentBiz.readExamInfo("6", schDeptFlow);
        	model.addAttribute("examInfo" , examInfo);
        	//获取评价模板
        	List<Map<String, Object>> assessTmp = nfyyStudentBiz.getExamItems("6");
        	model.addAttribute("assessTmp" , assessTmp);
        	List<Map<String,Object>> marks = nfyyStudentBiz.getMarksForDept(userFlow, schDeptFlow);
        	model.addAttribute("marks" , marks);
        }
        //评价带教老师
        if("0006".equals(funcFlow)){
        	Map<String,Object> examInfo = nfyyStudentBiz.readExamInfo("0", schDeptFlow);
        	model.addAttribute("examInfo" , examInfo);

			List<Map<String, Object>> assessTmp = nfyyStudentBiz.getExamItems("0");
        	model.addAttribute("assessTmp" , assessTmp);
        	List<Map<String,Object>> marks = nfyyStudentBiz.getMarksForTec(userFlow, schDeptFlow);
        	model.addAttribute("marks" , marks);
        }
        //自我评价
        if("0007".equals(funcFlow)){
        	Evaluation evaluation = nfyyStudentBiz.readOutSecBrief(schDeptFlow);
        	model.addAttribute("evaluation" , evaluation);
        }
        //DOPS评估表
        if("0008".equals(funcFlow)){
    		Map<String,Object> outDops = nfyyTeacherBiz.readOutDops(schDeptFlow);
    		model.addAttribute("outDops" , outDops);

			Map<String,Object> examInfo = nfyyTeacherBiz.readExamInfo(schDeptFlow,"11");
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
			List<Map<String, Object>> assessTmp = nfyyStudentBiz.getExamItemsByItemId("11",ExamItemID);
        	model.addAttribute("assessTmp" , assessTmp);

			List<Map<String,Object>> marks = nfyyTeacherBiz.getMarks(schDeptFlow,"11");
        	model.addAttribute("marks" , marks);
        }
        //Mini-CEX评估表
        if("0009".equals(funcFlow)){
    		Map<String,Object> outMiniCex = nfyyTeacherBiz.readOutMiniCex(schDeptFlow);
    		model.addAttribute("outMiniCex" , outMiniCex);

			Map<String,Object> examInfo = nfyyTeacherBiz.readExamInfo(schDeptFlow,"12");
        	model.addAttribute("examInfo" , examInfo);

			List<Map<String, Object>> assessTmp = nfyyStudentBiz.getExamItemsMiniCex("12");
        	model.addAttribute("assessTmp" , assessTmp);

			List<Map<String,Object>> marks = nfyyTeacherBiz.getMarks(schDeptFlow,"12");
        	model.addAttribute("marks" , marks);
        }

		//大病历
  		if("D001".equals(funcFlow)){
  			if(StringUtil.isNotBlank(dataFlow)){
  	  			Map<String,Object> bigHistory = nfyyDoctorBiz.readBigHistory(userFlow,schDeptFlow,dataFlow);
  				model.addAttribute("bigHistory" , bigHistory);
  			}
  		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            if(StringUtil.isNotBlank(dataFlow)){
                Map<String,Object> outPatient = nfyyDoctorBiz.readOutPatient(userFlow,schDeptFlow,dataFlow);
                model.addAttribute("outPatient" , outPatient);
            }
        }
  		//病种
		if("D002".equals(funcFlow)){
			if(StringUtil.isNotBlank(dataFlow)){
  	  			Map<String,Object> caseClass = nfyyDoctorBiz.readCaseClass(userFlow,schDeptFlow,dataFlow);
  				model.addAttribute("caseClass" , caseClass);
  			}
			if(StringUtil.isNotBlank(cataFlow)){
				String caseClassCataName = nfyyDoctorBiz.readCaseClassCataName(cataFlow);
				model.addAttribute("caseClassCataName" , caseClassCataName);
			}
		}
		//操作技能
		if("D003".equals(funcFlow)){
			if(StringUtil.isNotBlank(dataFlow)){
  	  			Map<String,Object> operateSkill = nfyyDoctorBiz.readOperateSkill(userFlow,schDeptFlow,dataFlow);
  				model.addAttribute("operateSkill" , operateSkill);
  			}
			if(StringUtil.isNotBlank(cataFlow)){
				String operateSkillCataName = nfyyDoctorBiz.readOperateSkillCataName(cataFlow);
				model.addAttribute("operateSkillCataName" , operateSkillCataName);
			}
		}
		//手术
		if("D004".equals(funcFlow)){
			if(StringUtil.isNotBlank(dataFlow)){
  	  			Map<String,Object> posSkill = nfyyDoctorBiz.readPOSSkill(userFlow,schDeptFlow,dataFlow);
  				model.addAttribute("posSkill" , posSkill);
  			}
			if(StringUtil.isNotBlank(cataFlow)){
				String posSkillCataName = nfyyDoctorBiz.readPOSSkillCataName(cataFlow);
				model.addAttribute("posSkillCataName" , posSkillCataName);
			}
		}
		//出科小结
		if("D005".equals(funcFlow)){
			Map<String,String> doctor = nfyyAppBiz.readUser(userFlow);
			model.addAttribute("doctor" , doctor);

			Map<String, Object> outSecBrief = nfyyDoctorBiz.readOutSecBrief(schDeptFlow);
			model.addAttribute("outSecBrief" , outSecBrief);
		}
		model.addAttribute("nfyyFuncFlow0001HttpUrl", nfyyFuncFlow0001HttpUrl);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/modData"},method={RequestMethod.PUT , RequestMethod.POST})
	public String modData(String userFlow, String schDeptFlow, String funcTypeId, String funcFlow,
						  String cataFlow, String dataFlow, HttpServletRequest request , Model model) throws DocumentException {

		String result = "res/nfyy/student/modData";
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
			nfyyStudentBiz.modEnteredDeptEdu(enteredDeptEdu);
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
			nfyyStudentBiz.modStudyInfo(studyInfo );
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
			nfyyStudentBiz.modMedicalInfo(medicalInfo);
		}
		//教学活动
		if("0004".equals(funcFlow)){
			Activity exitactivity = nfyyStudentBiz.findActivity(dataFlow, userFlow);
			Integer score = exitactivity.getScore();
			if(StringUtil.isBlank(cfg_url))
			{
				model.addAttribute("resultId", "2020202");
				model.addAttribute("resultType", "未配置住培系统配置文件地址");
				return result;
			}

			URL urlfile = null;
			HttpURLConnection httpUrl = null;
			BufferedInputStream bis = null;
			StringBuffer resultBuffer = new StringBuffer();
			BufferedReader reader = null;
			InputStream inputStream = null;
			InputStreamReader inputStreamReader = null;
			String tempLine = null;
			String url = cfg_url;
			String xml = "";
			try {
				urlfile = new URL(url);
				httpUrl = (HttpURLConnection) urlfile.openConnection();
				httpUrl.connect();
				inputStream = httpUrl.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				reader = new BufferedReader(inputStreamReader);
				resultBuffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
				resultBuffer.append("<appSettings>");
				while ((tempLine = reader.readLine()) != null) {
					if(tempLine.indexOf("<add")>=0)
					{
						resultBuffer.append(tempLine);
						resultBuffer.append("\n");
					}
				}
				resultBuffer.append("</appSettings>");
				xml = resultBuffer.toString();
			} catch (Exception e) {
				model.addAttribute("resultId", "2020202");
				model.addAttribute("resultType", "住培系统配置文件地址配置错误");
				return result;
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
					if (bis != null) {
						bis.close();
					}
					httpUrl.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			int joinTime=10;
			int evalTime=10;
			if(StringUtil.isNotBlank(xml))
			{
				Element oldRootEle =  DocumentHelper.parseText(xml).getRootElement();
				List<Element> elements=oldRootEle.elements();
				if(elements!=null)
				{
					for(Element e:elements)
					{
						String key=e.attribute("key").getValue();
						String v=e.attribute("value").getValue();
						if("LimitInTime".equals(key))
						{
							if(StringUtil.isNotBlank(v))
							{
								joinTime=Integer.valueOf(v.trim());
							}
						}
						if("LimitEvalTime".equals(key))
						{
							if(StringUtil.isNotBlank(v))
							{
								evalTime=Integer.valueOf(v.trim());
							}
						}
					}
				}
			}
			System.out.println("joinTime:"+joinTime);
			System.out.println("evalTime:"+evalTime);
			if(score==null){
				//是否可参加
				Activity activity = nfyyStudentBiz.readActivity(dataFlow);
				String currDateTime = DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
				String startDateTime = activity.getTime();

				int sing = (int) DateUtil.signMinutesBetweenTowDate(currDateTime, startDateTime);
				if (sing >= -joinTime) {
					nfyyStudentBiz.joinActivity(dataFlow, schDeptFlow , userFlow);
				}else{
					model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_NOT_JOIN_ACTIVITY);
					model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_NOT_JOIN_ACTIVITY);
					return result;
				}
			}else if(score>=0){
				Activity exitActivity = nfyyStudentBiz.readActivity(dataFlow);
			    String endDateTime = exitActivity.getEndTime();
			    String currDateTime = DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
			    int sing = (int) DateUtil.signMinutesBetweenTowDate(endDateTime, currDateTime);
			    if(score==0&&sing>=0&&sing<=evalTime){
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
					nfyyStudentBiz.scoreActivity(activity);
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
			nfyyStudentBiz.modOutSecBrief(schDeptFlow, briefRequrie);
		}
		//DOPS评估表
        if("0008".equals(funcFlow)){
        	nfyyStudentBiz.saveOutDops(userFlow, schDeptFlow,request);
		}
        //Mini-CEX评估表
        if("0009".equals(funcFlow)){
        	nfyyStudentBiz.saveOutMiniCex(userFlow, schDeptFlow,request);
		}
        //大病历
  		if("D001".equals(funcFlow)){
  			nfyyDoctorBiz.modBigHistory(userFlow,schDeptFlow,dataFlow,request);
  		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            nfyyDoctorBiz.modOutPatient(userFlow,schDeptFlow,dataFlow,request);
        }
  		//病种
		if("D002".equals(funcFlow)){
			nfyyDoctorBiz.modCaseClass(userFlow,schDeptFlow,cataFlow,dataFlow,request);
		}
		//操作技能
		if("D003".equals(funcFlow)){
			nfyyDoctorBiz.modOperateSkill(userFlow,schDeptFlow,cataFlow,dataFlow,request);
		}
		//手术
		if("D004".equals(funcFlow)){
			nfyyDoctorBiz.modPOSSkill(userFlow,schDeptFlow,cataFlow,dataFlow,request);
		}
		//出科小结
		if("D005".equals(funcFlow)){
			Map<String, Object> temp = nfyyDoctorBiz.readOutSecBrief(schDeptFlow);
			if(temp!=null){
				nfyyDoctorBiz.modOutSecBrief(userFlow,schDeptFlow,request);
			}
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	public static void main(String args[]) throws Exception {

	}
	@RequestMapping(value={"/deleteData"},method={RequestMethod.POST , RequestMethod.DELETE})
	public String deleteData(String userFlow,String schDeptFlow,String funcTypeId,String funcFlow,
			String dataFlow,HttpServletRequest request , Model model){
		String result = "res/nfyy/student/deleteData";
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
			nfyyStudentBiz.delStudyInfo(dataFlow);
		}
		//病例与管床
		if("0003".equals(funcFlow)){
			nfyyStudentBiz.delMedicalInfo(dataFlow);
		}
		//大病历
  		if("D001".equals(funcFlow)){
  			nfyyDoctorBiz.delBigHistory(userFlow,schDeptFlow,dataFlow);
  		}
        //门诊病历
        if("D0011".equals(funcFlow)){
            nfyyDoctorBiz.delOutPatient(userFlow,schDeptFlow,dataFlow);
        }
  		//病种
		if("D002".equals(funcFlow)){
			nfyyDoctorBiz.delCaseClass(userFlow,schDeptFlow,dataFlow);
		}
		//操作技能
		if("D003".equals(funcFlow)){
			nfyyDoctorBiz.delOperateSkill(userFlow,schDeptFlow,dataFlow);
		}
		//手术
		if("D004".equals(funcFlow)){
			nfyyDoctorBiz.delPOSSkill(userFlow,schDeptFlow,dataFlow);
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/joinExam"})
	public String joinExam(String userFlow,String SecID,String SpecialtyID,String processFlow,HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/nfyy/student/joinExam";
		}
		if(StringUtil.isEmpty(SecID)){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "SecID标识符为空");
			return "res/nfyy/student/joinExam";
		}
		if(StringUtil.isEmpty(SpecialtyID)){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "SpecialtyID标识符为空");
			return "res/nfyy/student/joinExam";
		}
		//考试地址
		if(!StringUtil.isNotBlank(mobile_after_url)){
			model.addAttribute("resultId", "40405");
			model.addAttribute("resultType", "请联系管理员维护考试地址!");
			return "res/nfyy/student/joinExam";
		}
		Map<String,String> user=nfyyAppBiz.readUser(userFlow);
		//试卷id
		String ExamSoluID = "0";
		//时间戳
		String Date = "0";
		String examType="0";
		String roleFlow=user.get("roleFlow");
		if (StringUtil.isBlank(roleFlow)) {
			model.addAttribute("resultId", "40407");
			model.addAttribute("resultType", "学员人员类型错误!");
			return "res/nfyy/student/joinExam";
		}
		//标准科室
		Map<String,String> paper = nfyyStudentBiz.getPaperByStandardDeptId(SecID,SpecialtyID,roleFlow);
		if (paper != null) {
			ExamSoluID = paper.get("Examination");
		}
		if ("0".equals(ExamSoluID)) {
			model.addAttribute("resultId", "40407");
			model.addAttribute("resultType", "该科室暂无试卷信息!");
			return "res/nfyy/student/joinExam";
		}
		String testUrl=mobile_after_url+"?Action=ChuKeMobileExam&ExamSoluID="+ExamSoluID+"&CardID="+ URLEncoder.encode(user.get("userCode"), "utf-8")+"&Date="+Date+"&TestNum=";
		model.addAttribute("testUrl",testUrl);
		return "res/nfyy/student/joinExam";
	}
}
