package com.pinde.res.ctrl.gzzy;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.NfyyGlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.gzzy.IGzzyAppBiz;
import com.pinde.res.biz.gzzy.IGzzyStudentBiz;
import com.pinde.res.biz.gzzy.IGzzyTeacherBiz;
import com.pinde.res.enums.nfyy.DeptStatusEnum;
import com.pinde.res.model.nfyy.mo.MedicalInfo;
import com.pinde.res.model.nfyy.mo.StudyInfo;
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
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/gzzyres/teacher")
public class GzzyTeacherController {

private static Logger logger = LoggerFactory.getLogger(GzzyTeacherController.class);

	@Autowired
	private IGzzyAppBiz appBiz;
	@Autowired
	private IGzzyStudentBiz studentBiz;
	@Autowired
	private IGzzyTeacherBiz teacherBiz;
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(getClass().getCanonicalName()+" some error happened",e);
		return "res/gzzyres/500";
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
		
		String result = "res/gzzyres/teacher/schDoctorList";
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
		List<Map<String,Object>> doctotList = teacherBiz.getDoctorList(userFlow, schStatusId, doctorName, doctorType, pageIndex, pageSize);
		model.addAttribute("doctotList", doctotList);
		model.addAttribute("dataCount", teacherBiz.getDoctorList(userFlow, schStatusId, doctorName, doctorType, 1,Integer.MAX_VALUE).size());
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/schFuncList"},method={RequestMethod.GET})
	public String schFuncList(String userFlow,String doctorFlow,Model model){
		String result = "res/gzzyres/teacher/schFuncList";
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
		Map<String,String> doctor = appBiz.readDoctor(doctorFlow);
		model.addAttribute("doctor" , doctor);
		//科室状态
		Map<String,Object> deptStatus = teacherBiz.readDoctorDeptStatus(userFlow, doctorFlow);
		model.addAttribute("deptStatus", deptStatus);
		//出科小结
		Map<String,Object> outSecBrief = teacherBiz.readOutSecBrief(doctorFlow);
		model.addAttribute("outSecBrief", outSecBrief);
		
		Map<String,Object> outDops = teacherBiz.readOutDops(doctorFlow);
		model.addAttribute("outDops" , outDops);
		
		Map<String,Object> outMiniCex = teacherBiz.readOutMiniCex(doctorFlow);
		model.addAttribute("outMiniCex" , outMiniCex);
		
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/categoryProgress"},method={RequestMethod.GET})
	public String categoryProgress(String userFlow,String doctorFlow,String funcTypeId , String funcFlow , Integer pageIndex,Integer pageSize,String dataCount,Model model){
		
		String result = "res/gzzyres/teacher/categoryProgress";
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
		String result = "res/gzzyres/teacher/dataList";
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
			List<StudyInfo> studyInfos = studentBiz.getStudyInfos(doctorFlow, pageIndex, pageSize);
			model.addAttribute("studyInfos" , studyInfos);
			model.addAttribute("dataCount", studentBiz.getStudyInfos(doctorFlow, 1, Integer.MAX_VALUE).size());
		}
		//病例与管床
		if("00022".equals(funcFlow)){
			List<MedicalInfo> medicalInfos = studentBiz.getMedicalInfos(doctorFlow, pageIndex, pageSize);
			int count = studentBiz.getMedicalInfos(doctorFlow, 1, Integer.MAX_VALUE).size();
			model.addAttribute("medicalInfos" , medicalInfos);
			model.addAttribute("dataCount" , count);
		}
		//教学活动
		if("00023".equals(funcFlow)){
			List<Map<String,Object>> activitys = teacherBiz.getActives(userFlow, doctorFlow,pageIndex, pageSize);
			model.addAttribute("activitys" , activitys);
			int count =  teacherBiz.getActives(userFlow, doctorFlow,1 , Integer.MAX_VALUE).size();
			model.addAttribute("dataCount" , count);
		}
		//活动参加人员确认
		if("0007".equals(funcFlow)){
			List<Map<String,Object>> activitys = teacherBiz.getNeedConfirmActives(userFlow, doctorFlow,pageIndex, pageSize);
			model.addAttribute("activitys" , activitys);
			int count =  teacherBiz.getActives(userFlow, doctorFlow,1 , Integer.MAX_VALUE).size();
			model.addAttribute("dataCount" , count);
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/addData"},method={RequestMethod.POST})
	public String addData(String userFlow,String doctorFlow,String funcTypeId , String funcFlow , String cataFlow,HttpServletRequest request , Model model){
		
		String result = "res/gzzyres/teacher/addData";
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
	public String viewData(String userFlow,String doctorFlow,String funcTypeId,String funcFlow , String cataFlow,String studentFlow,
						   String dataFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		
		String result = "res/gzzyres/teacher/viewData";
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
		if(StringUtil.isEmpty(studentFlow)){
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
		Map<String,String> user=appBiz.readUser(userFlow);
		model.addAttribute("user", user);
//		if(StringUtil.isBlank(dataFlow)){
//			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DATAFLOW_NULL);
//			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DATAFLOW_NULL);
//			return result;
//		}
		
		//入科确认
        if("0001".equals(funcFlow)){
        	Map<String,Object> doctorInfo = teacherBiz.readDoctorInfo(userFlow, doctorFlow);
    		model.addAttribute("doctorInfo", doctorInfo);
		}
        //学习情况
        if("00021".equals(funcFlow)){
        	if(StringUtil.isNotBlank(dataFlow)){
            	StudyInfo studyInfo = studentBiz.readStudyInfo(dataFlow);
            	model.addAttribute("studyInfo" , studyInfo);
        	}
        }
        //病例与管床
        if("00022".equals(funcFlow)){
        	if(StringUtil.isNotBlank(dataFlow)){
            	MedicalInfo medicalInfo = studentBiz.readMedicalInfo(dataFlow);
            	model.addAttribute("medicalInfo" , medicalInfo);
        	}
        }
        //教学活动
        if("00023".equals(funcFlow)){
        	Map<String,Object> activity = teacherBiz.readActivity(dataFlow, doctorFlow);
        	model.addAttribute("activity" , activity);
        }
        //过程评价
        if("0003".equals(funcFlow)){
        	Map<String,Object> evalInfo = teacherBiz.readEvalInfo(userFlow, doctorFlow);
    		model.addAttribute("evalInfo", evalInfo);
        }
		//出科审核
		if("0004".equals(funcFlow)){
			Map<String,String> doctor = appBiz.readDoctor(doctorFlow);
			model.addAttribute("doctor" , doctor);
			
			String type = "7";
			if("4".equals(doctor.get("roleFlow"))){
				type = "9";
			}
			Map<String,Object> outSecBrief = teacherBiz.readOutSecBrief(doctorFlow);
			model.addAttribute("outSecBrief" , outSecBrief);

			Map<String,Object> examInfo = teacherBiz.readExamInfo(doctorFlow,type);
        	model.addAttribute("examInfo" , examInfo);

			//学员基本信息
			Map<String,Object> baseInfo = teacherBiz.readBaseInfo(doctorFlow,studentFlow);
			model.addAttribute("baseInfo" , baseInfo);
			//日常考核表分数
			Map<String,Object> dailyInfo = teacherBiz.readDailyInfo(doctorFlow);
			model.addAttribute("dailyInfo" , dailyInfo);

			//事假
			BigDecimal shijia=teacherBiz.readShijia(baseInfo);
			model.addAttribute("shijia" , shijia);

			//病假
			BigDecimal bingjia=teacherBiz.readBingjia(baseInfo);
			model.addAttribute("bingjia" , bingjia);
			//缺勤
			Integer queqing=teacherBiz.readQueQing(baseInfo);
			model.addAttribute("queqing" , queqing);
			//主任
			String Professer=teacherBiz.readKzrName(doctorFlow);
			model.addAttribute("Professer" , Professer);

        	List<Map<String, Object>> assessTmp = this.studentBiz.getExamItems(type);
        	model.addAttribute("assessTmp" , assessTmp);
        	
        	List<Map<String,Object>> marks = teacherBiz.getMarks(doctorFlow,type);
        	model.addAttribute("marks" , marks);
        	
        	Float theoryScore = teacherBiz.readTheoryScore(doctorFlow);
            model.addAttribute("theoryScore" , theoryScore);
        	
        	Map<String,Object> cycleOutSectionRecord = teacherBiz.readCycleOutSectionRecord(userFlow, doctorFlow);
			model.addAttribute("cycleOutSectionRecord" , cycleOutSectionRecord);
		}
		//DOPS评估表
		if("0005".equals(funcFlow)){
			Map<String,Object> outDops = teacherBiz.readOutDops(doctorFlow);
			model.addAttribute("outDops" , outDops);
			
			Map<String,Object> examInfo = teacherBiz.readExamInfo(doctorFlow,"11");
        	model.addAttribute("examInfo" , examInfo);

            List<Map<String, Object>> assessTmp = this.studentBiz.getExamItemsDOPS(doctorFlow);
            model.addAttribute("assessTmp" , assessTmp);

        	List<Map<String,Object>> marks = teacherBiz.getMarks(doctorFlow,"11");
        	model.addAttribute("marks" , marks);
		}
		//Mini-CEX评估表
		if("0006".equals(funcFlow)){

			/**
			 * 获取轮转科室ID
			 */

			Map<String,Object> doctorInfo = teacherBiz.readDoctorInfo(userFlow, doctorFlow);
			model.addAttribute("doctorInfo", doctorInfo);
			String HosCySecID= (String) doctorInfo.get("HosCySecID");
			List<Map<String,String>> depts=teacherBiz.isFzDept(doctorFlow);
//			List<Map<String,String>> depts=null;
			if(depts!=null&&depts.size()>0)
			{
				funcFlow="0006_1";

				Map<String, Object> outMiniCex = teacherBiz.readOutMiniCexFz(doctorFlow);
				model.addAttribute("outMiniCex", outMiniCex);

			}else {
				Map<String, Object> outMiniCex = teacherBiz.readOutMiniCex(doctorFlow);
				model.addAttribute("outMiniCex", outMiniCex);

				Map<String, Object> examInfo = teacherBiz.readExamInfo(doctorFlow, "12");
				model.addAttribute("examInfo", examInfo);

				List<Map<String, Object>> assessTmp = this.studentBiz.getExamItemsMiniCex(doctorFlow);
				model.addAttribute("assessTmp", assessTmp);

				List<Map<String, Object>> marks = teacherBiz.getMarks(doctorFlow, "12");
				model.addAttribute("marks", marks);
			}
        }
		
		//活动参加人员确认
		if("0007".equals(funcFlow)){
			List<Map<String,Object>> activityUsers = teacherBiz.getNeedConfirmActiveUsers(dataFlow);
			model.addAttribute("activityUsers" , activityUsers);
		}

		model.addAttribute("funcFlow", funcFlow);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/modData"},method={RequestMethod.PUT , RequestMethod.POST})
	public String modData(String userFlow,String doctorFlow,String funcTypeId , String funcFlow , String cataFlow,String dataFlow
			,HttpServletRequest request , Model model){
		
		String result = "res/gzzyres/teacher/modData";
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
        	teacherBiz.enterDeptConfirm(userFlow, doctorFlow);
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
        	teacherBiz.saveEvalInfo(userFlow, doctorFlow,eval);
		}
        //出科审核
        if("0004".equals(funcFlow)){
        	teacherBiz.saveOutSecBrief(userFlow, doctorFlow,request);
		}
        //DOPS评估表
        if("0005".equals(funcFlow)){
        	teacherBiz.saveOutDops(userFlow, doctorFlow,request);
		}
        //Mini-CEX评估表
        if("0006".equals(funcFlow)){
			List<Map<String,String>> depts=teacherBiz.isFzDept(doctorFlow);
			if(depts!=null&&depts.size()>0) {
				teacherBiz.saveOutMiniCexFz(userFlow, doctorFlow, request);
			}else {
				teacherBiz.saveOutMiniCex(userFlow, doctorFlow, request);
			}
		}
        //活动参加人员确认
  		if("0007".equals(funcFlow)){
  			teacherBiz.confirmActiveUsers(dataFlow,request);
  		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	
	@RequestMapping(value={"/deleteData"},method={RequestMethod.POST , RequestMethod.DELETE})
	public String deleteData(String userFlow,String doctorFlow,String funcTypeId,String funcFlow,String dataFlow,HttpServletRequest request , Model model){
		
		String result = "res/gzzyres/student/deleteData";
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

	@RequestMapping(value={"/dataAudit"},method={RequestMethod.GET})
	public String dataAudit(String userFlow,String studentFlow,String schStatusId ,String cysecId ,Model model){

		String result = "res/gzzyres/teacher/dataAudit";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(studentFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schStatusId)||!("Entering".equals(schStatusId)||"Exited".equals(schStatusId))){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "轮转状态为空或错误");
			return result;
		}
		Map<String , String> searchMap =new HashMap<>();
		searchMap.put("doctorFlow",studentFlow);
		searchMap.put("schStatusId",schStatusId);
		searchMap.put("cySecId",cysecId);
		List<Map<String,String>> datas=teacherBiz.getDataAudit(searchMap);
		if("Exited".equals(schStatusId))
		{
			Map<String,Map<String,String>> dataMap=new HashMap<>();
			if(datas!=null&&datas.size()>0)
			{
				for(Map<String,String> d:datas)
				{
					dataMap.put(d.get("dataTypeId"),d);
				}
			}
			datas = new ArrayList<>();
			Map<String, String> data = new HashMap<>();
			data.put("dataTypeName", "大病历");
			data.put("dataTypeId", "mr");
			data.put("notAuditNum", dataMap.get("mr")==null?"0":dataMap.get("mr").get("notAuditNum"));
			datas.add(data);
			data = new HashMap<>();
			data.put("dataTypeName", "病种");
			data.put("dataTypeId", "disease");
			data.put("notAuditNum", dataMap.get("disease")==null?"0":dataMap.get("disease").get("notAuditNum"));
			datas.add(data);
			data = new HashMap<>();
			data.put("dataTypeName", "操作技能");
			data.put("dataTypeId", "skill");
			data.put("notAuditNum", dataMap.get("skill")==null?"0":dataMap.get("skill").get("notAuditNum"));
			datas.add(data);
			data = new HashMap<>();
			data.put("dataTypeName", "手术");
			data.put("dataTypeId", "operation");
			data.put("notAuditNum", dataMap.get("operation")==null?"0":dataMap.get("operation").get("notAuditNum"));
			datas.add(data);
			data = new HashMap<>();
			data.put("dataTypeName", "门诊病历");
			data.put("dataTypeId", "disciple");
			data.put("notAuditNum", dataMap.get("disciple")==null?"0":dataMap.get("disciple").get("notAuditNum"));
			datas.add(data);

		}
		model.addAttribute("datas", datas);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/dataAuditList"},method={RequestMethod.GET})
	public String dataAuditList(String userFlow,String studentFlow,String schStatusId ,
								Integer pageIndex,Integer pageSize,String dataTypeId ,
								String cysecId ,Model model){

		String result = "res/gzzyres/teacher/dataAuditList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(studentFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schStatusId)||!("Entering".equals(schStatusId)||"Exited".equals(schStatusId))){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "轮转状态为空或错误");
			return result;
		}
		if(StringUtil.isEmpty(dataTypeId)||
				!("mr".equals(dataTypeId)||"disease".equals(dataTypeId)||"skill".equals(dataTypeId)
						||"operation".equals(dataTypeId)||"disciple".equals(dataTypeId))){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "数据类型为空或错误");
			return result;
		}

		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}
		Map<String , Object> searchMap =new HashMap<>();
		searchMap.put("doctorFlow",studentFlow);
		searchMap.put("schStatusId",schStatusId);
		searchMap.put("cySecId",cysecId);
		searchMap.put("dataTypeId",dataTypeId);
		searchMap.put("pageSize",pageSize);
		searchMap.put("pageIndex",pageIndex);

		model.addAttribute("dataCount", 0);
		//大病历
		if("mr".equals(dataTypeId)){
			List<Map<String,String>> datas = teacherBiz.getMrDatas(searchMap);
			Map<String,String> finishMap = teacherBiz.getMrFinishMap(searchMap);
			model.addAttribute("finishMap" , finishMap);
			model.addAttribute("datas" , datas);

			searchMap.put("pageIndex",1);
			searchMap.put("pageSize",Integer.MAX_VALUE);
			model.addAttribute("dataCount", teacherBiz.getMrDatas(searchMap).size());
		}
		//病种
		if("disease".equals(dataTypeId)){
			List<Map<String,String>> datas = teacherBiz.getDiseaseDatas(searchMap);
			Map<String,String> finishMap = teacherBiz.getDiseaseFinishMap(searchMap);
			model.addAttribute("finishMap" , finishMap);
			model.addAttribute("datas" , datas);

			searchMap.put("pageIndex",1);
			searchMap.put("pageSize",Integer.MAX_VALUE);
			int count = teacherBiz.getDiseaseDatas(searchMap).size();
			model.addAttribute("dataCount" , count);
		}
		//操作技能
		if("skill".equals(dataTypeId)){
			List<Map<String,String>> datas = teacherBiz.getSkillDatas(searchMap);
			Map<String,String> finishMap = teacherBiz.getSkillFinishMap(searchMap);
			model.addAttribute("finishMap" , finishMap);
			model.addAttribute("datas" , datas);

			searchMap.put("pageIndex",1);
			searchMap.put("pageSize",Integer.MAX_VALUE);
			int count =  teacherBiz.getSkillDatas(searchMap).size();
			model.addAttribute("dataCount" , count);
		}
		//手术
		if("operation".equals(dataTypeId)){
			List<Map<String,String>> datas = teacherBiz.getOperationDatas(searchMap);
			Map<String,String> finishMap = teacherBiz.getOperationFinishMap(searchMap);
			model.addAttribute("finishMap" , finishMap);
			model.addAttribute("datas" , datas);

			searchMap.put("pageIndex",1);
			searchMap.put("pageSize",Integer.MAX_VALUE);
			int count =  teacherBiz.getOperationDatas(searchMap).size();
			model.addAttribute("dataCount" , count);
		}
		//门诊病历
		if("disciple".equals(dataTypeId)){
			List<Map<String,String>> datas = teacherBiz.getDiscipleDatas(searchMap);
			Map<String,String> finishMap = teacherBiz.getDiscipleFinishMap(searchMap);
			model.addAttribute("finishMap" , finishMap);
			model.addAttribute("datas" , datas);

			searchMap.put("pageIndex",1);
			searchMap.put("pageSize",Integer.MAX_VALUE);
			int count =  teacherBiz.getDiscipleDatas(searchMap).size();
			model.addAttribute("dataCount" , count);
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/batchAuditData"},method={RequestMethod.GET})
	public String batchAuditData(String userFlow,String studentFlow,String isPass ,String dataTypeId ,
								 String cysecId ,Model model){

		String result = "res/gzzyres/teacher/success";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(studentFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(isPass)||!("Y".equals(isPass)||"N".equals(isPass))){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "审核状态为空或错误");
			return result;
		}
		if(StringUtil.isEmpty(dataTypeId)||
				!("mr".equals(dataTypeId)||"disease".equals(dataTypeId)||"skill".equals(dataTypeId)
						||"operation".equals(dataTypeId)||"disciple".equals(dataTypeId))){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "数据类型为空或错误");
			return result;
		}

		Map<String , Object> searchMap =new HashMap<>();
		searchMap.put("doctorFlow",studentFlow);
		searchMap.put("schStatusId","Entering");
		searchMap.put("cySecId",cysecId);
		searchMap.put("dataTypeId",dataTypeId);

		model.addAttribute("dataCount", 0);
		List<Map<String,String>> datas=null;
		//大病历
		if("mr".equals(dataTypeId)){
			datas = teacherBiz.getAuditMrDatas(searchMap);
		}
		//病种
		if("disease".equals(dataTypeId)){
			datas = teacherBiz.getAuditDiseaseDatas(searchMap);
		}
		//操作技能
		if("skill".equals(dataTypeId)){
			datas = teacherBiz.getAuditSkillDatas(searchMap);
		}
		//手术
		if("operation".equals(dataTypeId)){
			datas = teacherBiz.getAuditOperationDatas(searchMap);
		}
		//门诊病历
		if("disciple".equals(dataTypeId)){
			datas = teacherBiz.getAuditDiscipleDatas(searchMap);
		}
		if(datas==null||datas.isEmpty())
		{
			model.addAttribute("resultId","3032011");
			model.addAttribute("resultType", "无待审核数据，请刷新列表");
			return result;
		}
		int c=teacherBiz.batchAuditData(datas,dataTypeId,isPass);
		if(c==0)
		{
			model.addAttribute("resultId","3032011");
			model.addAttribute("resultType", "审核失败");
			return result;
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping(value={"/auditData"},method={RequestMethod.GET})
	public String auditData(String userFlow,String dataFlow,String isPass ,String dataTypeId ,Model model){

		String result = "res/gzzyres/teacher/success";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(dataFlow)){
			model.addAttribute("resultId","32003200");
			model.addAttribute("resultType", "数据标识符为空");
			return result;
		}
		if(StringUtil.isEmpty(isPass)||!("Y".equals(isPass)||"N".equals(isPass))){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "审核状态为空或错误");
			return result;
		}
		if(StringUtil.isEmpty(dataTypeId)||
				!("mr".equals(dataTypeId)||"disease".equals(dataTypeId)||"skill".equals(dataTypeId)
						||"operation".equals(dataTypeId)||"disciple".equals(dataTypeId))){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "数据类型为空或错误");
			return result;
		}
		int c=teacherBiz.auditData(dataFlow,dataTypeId,isPass);
		if(c==0)
		{
			model.addAttribute("resultId","3032011");
			model.addAttribute("resultType", "审核失败");
			return result;
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/dataDetail"},method={RequestMethod.GET})
	public String dataDetail(String userFlow,String dataFlow,String dataTypeId ,Model model){

		String result = "res/gzzyres/teacher/dataDetail";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(dataTypeId)||
				!("mr".equals(dataTypeId)||"disease".equals(dataTypeId)||"skill".equals(dataTypeId)
						||"operation".equals(dataTypeId)||"disciple".equals(dataTypeId))){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "数据类型为空或错误");
			return result;
		}
		if(StringUtil.isEmpty(dataFlow)){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "数据标识符为空或错误");
			return result;
		}
		Map<String,String>data=null;
		//大病历
		if("mr".equals(dataTypeId)){
			data=teacherBiz.getMrData(dataFlow);

		}
		//病种
		if("disease".equals(dataTypeId)){
			data=teacherBiz.getDiseaseData(dataFlow);

		}
		//操作技能
		if("skill".equals(dataTypeId)){
			data=teacherBiz.getSkillData(dataFlow);

		}
		//手术
		if("operation".equals(dataTypeId)){
			data=teacherBiz.getOperationData(dataFlow);

		}
		//门诊病历
		if("disciple".equals(dataTypeId)){
			data=teacherBiz.getDiscipleData(dataFlow);

		}
		model.addAttribute("data", data);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

}
