package com.pinde.res.ctrl.bengyify;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.BengyifyGlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.bengyify.IBengyifyAppBiz;
import com.pinde.res.biz.bengyify.IBengyifyStudentBiz;
import com.pinde.res.biz.bengyify.IBengyifyTeacherBiz;
import com.pinde.core.commom.enums.DeptStatusEnum;
import com.pinde.res.model.bengyify.mo.MedicalInfo;
import com.pinde.res.model.bengyify.mo.StudyInfo;

@Controller
@RequestMapping("/res/bengyify/teacher")
public class BengyifyTeacherController {

private static Logger logger = LoggerFactory.getLogger(BengyifyTeacherController.class);

	@Autowired
	private IBengyifyAppBiz bengyifyAppBiz;
	@Autowired
	private IBengyifyStudentBiz bengyifyStudentBiz;
	@Autowired
	private IBengyifyTeacherBiz bengyifyTeacherBiz;
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(getClass().getCanonicalName()+" some error happened",e);
		return "res/bengyify/500";
    }
	
	@RequestMapping("/schDoctorList")
	public String schDoctorList(String userFlow , String searchData , Integer pageIndex,Integer pageSize , Model model) throws UnsupportedEncodingException{
		String schStatusId = "";
		String doctorType = "";
		String doctorName = "";
		if(StringUtil.isNotBlank(searchData)){
			searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
			Map<String , String> searchMap = (Map<String, String>) JSON.parse(searchData);
			
			for(String key:searchMap.keySet()){
				//System.out.println(key+":"+URLDecoder.decode(searchMap.get(key), "UTF-8"));
				searchMap.put(key, URLDecoder.decode(searchMap.get(key), "UTF-8"));
			}
			schStatusId = searchMap.get("schStatusId");
			doctorType = searchMap.get("doctorType");
			doctorName = searchMap.get("doctorName");
		}
		
		String result = "res/bengyify/teacher/schDoctorList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(schStatusId)){
			schStatusId = DeptStatusEnum.Entering.getId();
		}
		if(StringUtil.isBlank(doctorType)){
			doctorType = "Trainee"; // Trainee Graduate
		}
		if(pageIndex==null){
			pageIndex = BengyifyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = BengyifyGlobalConstant.DEFAULT_PAGE_SIZE;
		}
		List<Map<String,Object>> doctotList = bengyifyTeacherBiz.getDoctorList(userFlow, schStatusId, doctorName, doctorType, pageIndex, pageSize);
		model.addAttribute("doctotList", doctotList);
		model.addAttribute("dataCount", bengyifyTeacherBiz.getDoctorList(userFlow, schStatusId, doctorName, doctorType, 1,Integer.MAX_VALUE).size());
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/schFuncList"},method={RequestMethod.GET})
	public String schFuncList(String userFlow,String doctorFlow,Model model){
		String result = "res/bengyify/teacher/schFuncList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		Map<String,String> doctor = bengyifyAppBiz.readDoctor(doctorFlow);
		model.addAttribute("doctor" , doctor);
		//科室状态
		Map<String,Object> deptStatus = bengyifyTeacherBiz.readDoctorDeptStatus(userFlow, doctorFlow);
		model.addAttribute("deptStatus", deptStatus);
		//出科小结
		Map<String,Object> outSecBrief = bengyifyTeacherBiz.readOutSecBrief(doctorFlow);
		model.addAttribute("outSecBrief", outSecBrief);
		
		Map<String,Object> outDops = bengyifyTeacherBiz.readOutDops(doctorFlow);
		model.addAttribute("outDops" , outDops);
		
		Map<String,Object> outMiniCex = bengyifyTeacherBiz.readOutMiniCex(doctorFlow);
		model.addAttribute("outMiniCex" , outMiniCex);
		
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/categoryProgress"},method={RequestMethod.GET})
	public String categoryProgress(String userFlow,String doctorFlow,String funcTypeId , String funcFlow , Integer pageIndex,Integer pageSize,String dataCount,Model model){
		
		String result = "res/bengyify/teacher/categoryProgress";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
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
		
		model.addAttribute("funcTypeId", funcTypeId);
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	
	@RequestMapping(value={"/dataList"},method={RequestMethod.GET})
	public String dataList(String userFlow,String doctorFlow,String funcTypeId,String funcFlow , 
			String searchData , String cataFlow , Integer pageIndex,Integer pageSize,
			String dataCount,HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
		if(StringUtil.isNotBlank(searchData)){
			searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
			Map<String , String> searchMap = (Map<String, String>) JSON.parse(searchData);
			model.addAttribute("searchMap" , searchMap);
		}
		String result = "res/bengyify/teacher/dataList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
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

		model.addAttribute("dataCount", 0);
		//学习情况
		if("00021".equals(funcFlow)){
			List<StudyInfo> studyInfos = bengyifyStudentBiz.getStudyInfos(doctorFlow, pageIndex, pageSize);
			model.addAttribute("studyInfos" , studyInfos);
			model.addAttribute("dataCount", bengyifyStudentBiz.getStudyInfos(doctorFlow, 1, Integer.MAX_VALUE).size());
		}
		//病例与管床
		if("00022".equals(funcFlow)){
			List<MedicalInfo> medicalInfos = bengyifyStudentBiz.getMedicalInfos(doctorFlow, pageIndex, pageSize);
			int count = bengyifyStudentBiz.getMedicalInfos(doctorFlow, 1, Integer.MAX_VALUE).size();
			model.addAttribute("medicalInfos" , medicalInfos);
			model.addAttribute("dataCount" , count);
		}
		//教学活动
		if("00023".equals(funcFlow)){
			List<Map<String,Object>> activitys = bengyifyTeacherBiz.getActives(userFlow, doctorFlow,pageIndex, pageSize);
			model.addAttribute("activitys" , activitys);
			int count =  bengyifyTeacherBiz.getActives(userFlow, doctorFlow,1 , Integer.MAX_VALUE).size();
			model.addAttribute("dataCount" , count);
		}
		//活动参加人员确认
		if("0007".equals(funcFlow)){
			List<Map<String,Object>> activitys = bengyifyTeacherBiz.getNeedConfirmActives(userFlow, doctorFlow,pageIndex, pageSize);
			model.addAttribute("activitys" , activitys);
			int count =  bengyifyTeacherBiz.getActives(userFlow, doctorFlow,1 , Integer.MAX_VALUE).size();
			model.addAttribute("dataCount" , count);
		}
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/addData"},method={RequestMethod.POST})
	public String addData(String userFlow,String doctorFlow,String funcTypeId , String funcFlow , String cataFlow,HttpServletRequest request , Model model){
		
		String result = "res/bengyify/teacher/addData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
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
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/viewData"},method={RequestMethod.GET})
	public String viewData(String userFlow,String doctorFlow,String funcTypeId,String funcFlow , String cataFlow,String dataFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		
		String result = "res/bengyify/teacher/viewData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
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
		
		//入科确认
        if("0001".equals(funcFlow)){
        	Map<String,Object> doctorInfo = bengyifyTeacherBiz.readDoctorInfo(userFlow, doctorFlow);
    		model.addAttribute("doctorInfo", doctorInfo);
		}
        //学习情况
        if("00021".equals(funcFlow)){
        	if(StringUtil.isNotBlank(dataFlow)){
            	StudyInfo studyInfo = bengyifyStudentBiz.readStudyInfo(dataFlow);
            	model.addAttribute("studyInfo" , studyInfo);
        	}
        }
        //病例与管床
        if("00022".equals(funcFlow)){
        	if(StringUtil.isNotBlank(dataFlow)){
            	MedicalInfo medicalInfo = bengyifyStudentBiz.readMedicalInfo(dataFlow);
            	model.addAttribute("medicalInfo" , medicalInfo);
        	}
        }
        //教学活动
        if("00023".equals(funcFlow)){
        	Map<String,Object> activity = bengyifyTeacherBiz.readActivity(dataFlow, doctorFlow);
        	model.addAttribute("activity" , activity);
        }
        //过程评价
        if("0003".equals(funcFlow)){
        	Map<String,Object> evalInfo = bengyifyTeacherBiz.readEvalInfo(userFlow, doctorFlow);
    		model.addAttribute("evalInfo", evalInfo);
        }
		//出科审核
		if("0004".equals(funcFlow)){
			Map<String,String> doctor = bengyifyAppBiz.readDoctor(doctorFlow);
			model.addAttribute("doctor" , doctor);
			
			String type = "7";
			if("4".equals(doctor.get("roleFlow"))){
				type = "9";
			}
			Map<String,Object> outSecBrief = bengyifyTeacherBiz.readOutSecBrief(doctorFlow);
			model.addAttribute("outSecBrief" , outSecBrief);

			Map<String,Object> examInfo = bengyifyTeacherBiz.readExamInfo(doctorFlow,type);
        	model.addAttribute("examInfo" , examInfo);
			
        	List<Map<String, Object>> assessTmp = this.bengyifyStudentBiz.getExamItems(type);
        	model.addAttribute("assessTmp" , assessTmp);
        	
        	List<Map<String,Object>> marks = bengyifyTeacherBiz.getMarks(doctorFlow,type);
        	model.addAttribute("marks" , marks);
        	
        	Float theoryScore = bengyifyTeacherBiz.readTheoryScore(doctorFlow);
            model.addAttribute("theoryScore" , theoryScore);
        	
        	Map<String,Object> cycleOutSectionRecord = bengyifyTeacherBiz.readCycleOutSectionRecord(userFlow, doctorFlow);
			model.addAttribute("cycleOutSectionRecord" , cycleOutSectionRecord);
		}
		//DOPS评估表
		if("0005".equals(funcFlow)){
			Map<String,Object> outDops = bengyifyTeacherBiz.readOutDops(doctorFlow);
			model.addAttribute("outDops" , outDops);
			
			Map<String,Object> examInfo = bengyifyTeacherBiz.readExamInfo(doctorFlow,"11");
        	model.addAttribute("examInfo" , examInfo);

            List<Map<String, Object>> assessTmp = this.bengyifyStudentBiz.getExamItemsDOPS(doctorFlow);
            model.addAttribute("assessTmp" , assessTmp);

        	List<Map<String,Object>> marks = bengyifyTeacherBiz.getMarks(doctorFlow,"11");
        	model.addAttribute("marks" , marks);
		}
		//Mini-CEX评估表
		if("0006".equals(funcFlow)){
			Map<String,Object> outMiniCex = bengyifyTeacherBiz.readOutMiniCex(doctorFlow);
			model.addAttribute("outMiniCex" , outMiniCex);
			
			Map<String,Object> examInfo = bengyifyTeacherBiz.readExamInfo(doctorFlow,"12");
        	model.addAttribute("examInfo" , examInfo);
			
        	List<Map<String, Object>> assessTmp = this.bengyifyStudentBiz.getExamItems("12");
        	model.addAttribute("assessTmp" , assessTmp);
        	
        	List<Map<String,Object>> marks = bengyifyTeacherBiz.getMarks(doctorFlow,"12");
        	model.addAttribute("marks" , marks);
        }
		
		//活动参加人员确认
		if("0007".equals(funcFlow)){
			List<Map<String,Object>> activityUsers = bengyifyTeacherBiz.getNeedConfirmActiveUsers(dataFlow);
			model.addAttribute("activityUsers" , activityUsers);
		}
		
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/modData"},method={RequestMethod.PUT , RequestMethod.POST})
	public String modData(String userFlow,String doctorFlow,String funcTypeId , String funcFlow , String cataFlow,String dataFlow,HttpServletRequest request , Model model){
		
		String result = "res/bengyify/teacher/modData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
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
		//入科确认
        if("0001".equals(funcFlow)){
        	bengyifyTeacherBiz.enterDeptConfirm(userFlow, doctorFlow);
		}
        //过程评价
        if("0003".equals(funcFlow)){
        	String eval = StringUtil.defaultString(request.getParameter("eval"));
        	StringBuffer sb = new StringBuffer();
        	String [] evalArray = eval.split("\n");
        	for(int i=0;i<evalArray.length;i++){
        		String s =evalArray[i];
        		if(i==evalArray.length-1){
        			if(!s.contains(DateUtil.getDateTime("yyyy"))){
            			s = s+"     "+DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
        			}
        		}
        		sb.append(s).append("\n");
        	}
        	eval = sb.toString();
        	bengyifyTeacherBiz.saveEvalInfo(userFlow, doctorFlow,eval);
		}
        //出科审核
        if("0004".equals(funcFlow)){
        	bengyifyTeacherBiz.saveOutSecBrief(userFlow, doctorFlow,request);
		}
        //DOPS评估表
        if("0005".equals(funcFlow)){
        	bengyifyTeacherBiz.saveOutDops(userFlow, doctorFlow,request);
		}
        //Mini-CEX评估表
        if("0006".equals(funcFlow)){
        	bengyifyTeacherBiz.saveOutMiniCex(userFlow, doctorFlow,request);
		}
        //活动参加人员确认
  		if("0007".equals(funcFlow)){
  			bengyifyTeacherBiz.confirmActiveUsers(dataFlow,request);
  		}
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	
	@RequestMapping(value={"/deleteData"},method={RequestMethod.POST , RequestMethod.DELETE})
	public String deleteData(String userFlow,String doctorFlow,String funcTypeId,String funcFlow,String dataFlow,HttpServletRequest request , Model model){
		
		String result = "res/bengyify/student/deleteData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
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
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/noticeCount"},method={RequestMethod.GET})
	public String noticeCount(String userFlow , String roleId , Model model){
		String result = "res/bengyify/noticeCount";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_ROLEID_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_ROLEID_NULL);
			return result;
		}
		int dataCount = 0;
		if(BengyifyGlobalConstant.ROLE_ID_6.equals(roleId)){
			Map<String,Object> waitArrangeActivityCount = bengyifyTeacherBiz.getWaitArrangeActivityCount(userFlow);
			if((Integer)waitArrangeActivityCount.get("arrangeCount")>0){
				dataCount = dataCount +(Integer)waitArrangeActivityCount.get("arrangeCount");
			}
		}else{
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_ROLEID_ERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_ROLEID_ERROR);
			return result;
		}
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping("/activityList")
	public String activityList(String userFlow , String roleId ,String status, Integer pageIndex,Integer pageSize , Model model) throws UnsupportedEncodingException{


		String result = "res/bengyify/teacher/activityList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}

		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_ROLEID_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_ROLEID_NULL);
			return result;
		}
		if(!BengyifyGlobalConstant.ROLE_ID_6.equals(roleId)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_ROLEID_ERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_ROLEID_ERROR);
			return result;
		}
		if(pageIndex==null){
			pageIndex = BengyifyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = BengyifyGlobalConstant.DEFAULT_PAGE_SIZE;
		}
		List<Map<String,Object>> activityList = bengyifyTeacherBiz.getActivityList(userFlow, status, pageIndex, pageSize);
		model.addAttribute("activityList", activityList);
		model.addAttribute("dataCount", bengyifyTeacherBiz.getActivityList(userFlow, status, 1,Integer.MAX_VALUE).size());
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping("/activityDetail")
	public String activityDetail(String userFlow , String roleId ,String CaDisID, Model model) throws UnsupportedEncodingException{
		String result = "res/bengyify/teacher/activityDetail";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_ROLEID_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_ROLEID_NULL);
			return result;
		}
		if(StringUtil.isBlank(CaDisID)){
			model.addAttribute("resultId", "225541");
			model.addAttribute("resultType", "活动标识符为空");
			return result;
		}
		if(!BengyifyGlobalConstant.ROLE_ID_6.equals(roleId)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_ROLEID_ERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_ROLEID_ERROR);
			return result;
		}
		Map<String,Object> activityDetail = bengyifyTeacherBiz.activityDetail(CaDisID);
		model.addAttribute("activityDetail", activityDetail);
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping("/saveActivity")
	public String saveActivity(String userFlow , String roleId , String CaDisID, String uploadFile,
							   String CaDisTime ,
							   String CaDisContent ,
							   String CaName ,
							   String CaEndDisTime,
							   String fileName,
							   Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String result = "res/bengyify/teacher/success";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_ROLEID_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_ROLEID_NULL);
			return result;
		}
		if(StringUtil.isBlank(CaDisID)){
			model.addAttribute("resultId", "225541");
			model.addAttribute("resultType", "活动标识符为空");
			return result;
		}
		if(StringUtil.isBlank(CaDisTime)){
			model.addAttribute("resultId", "225541");
			model.addAttribute("resultType", "活动开始时间为空");
			return result;
		}
		if(StringUtil.isBlank(CaEndDisTime)){
			model.addAttribute("resultId", "225541");
			model.addAttribute("resultType", "活动结束时间为空");
			return result;
		}
		if(StringUtil.isBlank(CaName)){
			model.addAttribute("resultId", "225541");
			model.addAttribute("resultType", "活动名称为空");
			return result;
		}
		if(StringUtil.isBlank(CaDisContent)){
			model.addAttribute("resultId", "225541");
			model.addAttribute("resultType", "活动简介为空");
			return result;
		}
		if(!BengyifyGlobalConstant.ROLE_ID_6.equals(roleId)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_ROLEID_ERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_ROLEID_ERROR);
			return result;
		}
		Map<String,Object> activityDetail = bengyifyTeacherBiz.activityDetail(CaDisID);
		if(activityDetail==null)
		{
			model.addAttribute("resultId", "3201201");
			model.addAttribute("resultType", "活动信息不存在");
			return result;
		}else if("0".equals(activityDetail.get("CheckStatus")))
		{
			model.addAttribute("resultId", "3201201");
			model.addAttribute("resultType", "活动已安排");
			return result;
		}

		Map<String,String> dirMap=bengyifyTeacherBiz.getDirMap();
		if(dirMap==null){
			model.addAttribute("resultId", "3201201");
			model.addAttribute("resultType", "文件上传配置信息不存在");
			return result;
		}
		String uploadDir=dirMap.get("dirUrl");
		if(StringUtil.isBlank(uploadDir))
		{
			model.addAttribute("resultId", "3201201");
			model.addAttribute("resultType", "文件保存地址未配置");
			return result;
		}
		String resultMsg="";
		if(StringUtil.isNotBlank(uploadFile)&&!uploadFile.isEmpty())
		{
			if(StringUtil.isBlank(fileName))
			{
				model.addAttribute("resultId", "3201201");
				model.addAttribute("resultType", "文件名为空！");
				return result;
			}
			resultMsg=bengyifyTeacherBiz.saveUploadFile(uploadFile,userFlow,uploadDir,fileName);
			if("-1".equals(resultMsg))
			{
				model.addAttribute("resultId", "3201201");
				model.addAttribute("resultType", "上传附件保存失败");
				return result;
			}
		}
		Map<String,String> param=new HashMap<>();
		param.put("CaDisTime",CaDisTime);
		param.put("CaDisContent",CaDisContent);
		param.put("CaName",CaName);
		param.put("CheckStatus","1");
		param.put("CaEndDisTime",CaEndDisTime);
		param.put("AttachID",resultMsg);
		param.put("CaDisID",CaDisID);
		int count=bengyifyTeacherBiz.updateActivity(param);
		if(count==0)
		{
			model.addAttribute("resultId", "3201201");
			model.addAttribute("resultType", "保存失败");
			return result;
		}
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "保存成功");
		return result;
	}

}
