package com.pinde.res.ctrl.nfyy;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.pinde.app.common.NfyyGlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.nfyy.INfyyAppBiz;
import com.pinde.res.biz.nfyy.INfyyStudentBiz;
import com.pinde.res.biz.nfyy.INfyyTeacherBiz;
import com.pinde.res.enums.nfyy.DeptStatusEnum;
import com.pinde.res.model.nfyy.mo.MedicalInfo;
import com.pinde.res.model.nfyy.mo.StudyInfo;

@Controller
@RequestMapping("/res/nfyy/teacher")
public class NfyyTeacherController {

private static Logger logger = LoggerFactory.getLogger(NfyyTeacherController.class);

	@Autowired
	private INfyyAppBiz nfyyAppBiz;
	@Autowired
	private INfyyStudentBiz nfyyStudentBiz;
	@Autowired
	private INfyyTeacherBiz nfyyTeacherBiz;
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(getClass().getCanonicalName()+" some error happened",e);
		return "res/nfyy/500";
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
		
		String result = "res/nfyy/teacher/schDoctorList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(schStatusId)){
			schStatusId = DeptStatusEnum.Entering.getId();
		}
		if(StringUtil.isBlank(doctorType)){
			doctorType = "Trainee"; // Trainee Graduate
		}
		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}
		List<Map<String,Object>> doctotList = nfyyTeacherBiz.getDoctorList(userFlow, schStatusId, doctorName, doctorType, pageIndex, pageSize);
		model.addAttribute("doctotList", doctotList);
		model.addAttribute("dataCount", nfyyTeacherBiz.getDoctorList(userFlow, schStatusId, doctorName, doctorType, 1,Integer.MAX_VALUE).size());
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/schFuncList"},method={RequestMethod.GET})
	public String schFuncList(String userFlow,String doctorFlow,Model model){
		String result = "res/nfyy/teacher/schFuncList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		Map<String,String> doctor = nfyyAppBiz.readDoctor(doctorFlow);
		model.addAttribute("doctor" , doctor);
		//科室状态
		Map<String,Object> deptStatus = nfyyTeacherBiz.readDoctorDeptStatus(userFlow, doctorFlow);
		model.addAttribute("deptStatus", deptStatus);
		//出科小结
		Map<String,Object> outSecBrief = nfyyTeacherBiz.readOutSecBrief(doctorFlow);
		model.addAttribute("outSecBrief", outSecBrief);
		
		Map<String,Object> outDops = nfyyTeacherBiz.readOutDops(doctorFlow);
		model.addAttribute("outDops" , outDops);
		
		Map<String,Object> outMiniCex = nfyyTeacherBiz.readOutMiniCex(doctorFlow);
		model.addAttribute("outMiniCex" , outMiniCex);
		
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/categoryProgress"},method={RequestMethod.GET})
	public String categoryProgress(String userFlow,String doctorFlow,String funcTypeId , String funcFlow , Integer pageIndex,Integer pageSize,String dataCount,Model model){
		
		String result = "res/nfyy/teacher/categoryProgress";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
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
		
		model.addAttribute("funcTypeId", funcTypeId);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
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
		String result = "res/nfyy/teacher/dataList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
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

		model.addAttribute("dataCount", 0);
		//学习情况
		if("00021".equals(funcFlow)){
			List<StudyInfo> studyInfos = nfyyStudentBiz.getStudyInfos(doctorFlow, pageIndex, pageSize);
			model.addAttribute("studyInfos" , studyInfos);
			model.addAttribute("dataCount", nfyyStudentBiz.getStudyInfos(doctorFlow, 1, Integer.MAX_VALUE).size());
		}
		//病例与管床
		if("00022".equals(funcFlow)){
			List<MedicalInfo> medicalInfos = nfyyStudentBiz.getMedicalInfos(doctorFlow, pageIndex, pageSize);
			int count = nfyyStudentBiz.getMedicalInfos(doctorFlow, 1, Integer.MAX_VALUE).size();
			model.addAttribute("medicalInfos" , medicalInfos);
			model.addAttribute("dataCount" , count);
		}
		//教学活动
		if("00023".equals(funcFlow)){
			List<Map<String,Object>> activitys = nfyyTeacherBiz.getActives(userFlow, doctorFlow,pageIndex, pageSize);
			model.addAttribute("activitys" , activitys);
			int count =  nfyyTeacherBiz.getActives(userFlow, doctorFlow,1 , Integer.MAX_VALUE).size();
			model.addAttribute("dataCount" , count);
		}
		//活动参加人员确认
		if("0007".equals(funcFlow)){
			List<Map<String,Object>> activitys = nfyyTeacherBiz.getNeedConfirmActives(userFlow, doctorFlow,pageIndex, pageSize);
			model.addAttribute("activitys" , activitys);
			int count =  nfyyTeacherBiz.getActives(userFlow, doctorFlow,1 , Integer.MAX_VALUE).size();
			model.addAttribute("dataCount" , count);
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/addData"},method={RequestMethod.POST})
	public String addData(String userFlow,String doctorFlow,String funcTypeId , String funcFlow , String cataFlow,HttpServletRequest request , Model model){
		
		String result = "res/nfyy/teacher/addData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
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
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/viewData"},method={RequestMethod.GET})
	public String viewData(String userFlow,String doctorFlow,String funcTypeId,String funcFlow , String cataFlow,String dataFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		
		String result = "res/nfyy/teacher/viewData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
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
		
		//入科确认
        if("0001".equals(funcFlow)){
        	Map<String,Object> doctorInfo = nfyyTeacherBiz.readDoctorInfo(userFlow, doctorFlow);
    		model.addAttribute("doctorInfo", doctorInfo);
		}
        //学习情况
        if("00021".equals(funcFlow)){
        	if(StringUtil.isNotBlank(dataFlow)){
            	StudyInfo studyInfo = nfyyStudentBiz.readStudyInfo(dataFlow);
            	model.addAttribute("studyInfo" , studyInfo);
        	}
        }
        //病例与管床
        if("00022".equals(funcFlow)){
        	if(StringUtil.isNotBlank(dataFlow)){
            	MedicalInfo medicalInfo = nfyyStudentBiz.readMedicalInfo(dataFlow);
            	model.addAttribute("medicalInfo" , medicalInfo);
        	}
        }
        //教学活动
        if("00023".equals(funcFlow)){
        	Map<String,Object> activity = nfyyTeacherBiz.readActivity(dataFlow, doctorFlow);
        	model.addAttribute("activity" , activity);
        }
        //过程评价
        if("0003".equals(funcFlow)){
        	Map<String,Object> evalInfo = nfyyTeacherBiz.readEvalInfo(userFlow, doctorFlow);
    		model.addAttribute("evalInfo", evalInfo);
        }
		//出科审核
		if("0004".equals(funcFlow)){
			Map<String,String> doctor = nfyyAppBiz.readDoctor(doctorFlow);
			model.addAttribute("doctor" , doctor);
			
			String type = "7";
			if("4".equals(doctor.get("roleFlow"))){
				type = "9";
			}
			Map<String,Object> outSecBrief = nfyyTeacherBiz.readOutSecBrief(doctorFlow);
			model.addAttribute("outSecBrief" , outSecBrief);

			Map<String,Object> examInfo = nfyyTeacherBiz.readExamInfo(doctorFlow,type);
        	model.addAttribute("examInfo" , examInfo);
			
        	List<Map<String, Object>> assessTmp = this.nfyyStudentBiz.getExamItems(type);
        	model.addAttribute("assessTmp" , assessTmp);
        	
        	List<Map<String,Object>> marks = nfyyTeacherBiz.getMarks(doctorFlow,type);
        	model.addAttribute("marks" , marks);
        	
        	Float theoryScore = nfyyTeacherBiz.readTheoryScore(doctorFlow);
            model.addAttribute("theoryScore" , theoryScore);
        	
        	Map<String,Object> cycleOutSectionRecord = nfyyTeacherBiz.readCycleOutSectionRecord(userFlow, doctorFlow);
			model.addAttribute("cycleOutSectionRecord" , cycleOutSectionRecord);
		}
		//DOPS评估表
		if("0005".equals(funcFlow)){
			Map<String,Object> outDops = nfyyTeacherBiz.readOutDops(doctorFlow);
			model.addAttribute("outDops" , outDops);
			
			Map<String,Object> examInfo = nfyyTeacherBiz.readExamInfo(doctorFlow,"11");
        	model.addAttribute("examInfo" , examInfo);

            List<Map<String, Object>> assessTmp = this.nfyyStudentBiz.getExamItemsDOPS(doctorFlow);
            model.addAttribute("assessTmp" , assessTmp);

        	List<Map<String,Object>> marks = nfyyTeacherBiz.getMarks(doctorFlow,"11");
        	model.addAttribute("marks" , marks);
		}
		//Mini-CEX评估表
		if("0006".equals(funcFlow)){
			Map<String,Object> outMiniCex = nfyyTeacherBiz.readOutMiniCex(doctorFlow);
			model.addAttribute("outMiniCex" , outMiniCex);
			
			Map<String,Object> examInfo = nfyyTeacherBiz.readExamInfo(doctorFlow,"12");
        	model.addAttribute("examInfo" , examInfo);
			
        	List<Map<String, Object>> assessTmp = this.nfyyStudentBiz.getExamItemsMiniCex(doctorFlow);
        	model.addAttribute("assessTmp" , assessTmp);
        	
        	List<Map<String,Object>> marks = nfyyTeacherBiz.getMarks(doctorFlow,"12");
        	model.addAttribute("marks" , marks);
        }
		
		//活动参加人员确认
		if("0007".equals(funcFlow)){
			List<Map<String,Object>> activityUsers = nfyyTeacherBiz.getNeedConfirmActiveUsers(dataFlow);
			model.addAttribute("activityUsers" , activityUsers);
		}
		
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/modData"},method={RequestMethod.PUT , RequestMethod.POST})
	public String modData(String userFlow,String doctorFlow,String funcTypeId , String funcFlow , String cataFlow,String dataFlow,HttpServletRequest request , Model model){
		
		String result = "res/nfyy/teacher/modData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
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
		//入科确认
        if("0001".equals(funcFlow)){
        	nfyyTeacherBiz.enterDeptConfirm(userFlow, doctorFlow);
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
        	nfyyTeacherBiz.saveEvalInfo(userFlow, doctorFlow,eval);
		}
        //出科审核
        if("0004".equals(funcFlow)){
        	nfyyTeacherBiz.saveOutSecBrief(userFlow, doctorFlow,request);
		}
        //DOPS评估表
        if("0005".equals(funcFlow)){
        	nfyyTeacherBiz.saveOutDops(userFlow, doctorFlow,request);
		}
        //Mini-CEX评估表
        if("0006".equals(funcFlow)){
        	nfyyTeacherBiz.saveOutMiniCex(userFlow, doctorFlow,request);
		}
        //活动参加人员确认
  		if("0007".equals(funcFlow)){
  			nfyyTeacherBiz.confirmActiveUsers(dataFlow,request);
  		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	
	@RequestMapping(value={"/deleteData"},method={RequestMethod.POST , RequestMethod.DELETE})
	public String deleteData(String userFlow,String doctorFlow,String funcTypeId,String funcFlow,String dataFlow,HttpServletRequest request , Model model){
		
		String result = "res/nfyy/student/deleteData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
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
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
}
