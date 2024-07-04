package com.pinde.res.biz.gzzy.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.gzzy.IGzzyStudentBiz;
import com.pinde.res.biz.gzzy.IGzzyTeacherBiz;
import com.pinde.res.dao.gzzy.ext.GzzyAppMapper;
import com.pinde.res.dao.gzzy.ext.GzzyStudentMapper;
import com.pinde.res.model.nfyy.mo.Activity;
import com.pinde.res.model.nfyy.mo.Evaluation;
import com.pinde.res.model.nfyy.mo.MedicalInfo;
import com.pinde.res.model.nfyy.mo.StudyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class GzzyStudentBizImpl implements IGzzyStudentBiz {
	@Autowired
	private GzzyAppMapper appMapper;
	@Autowired
	private GzzyStudentMapper studentMapper;
	@Autowired
	private IGzzyTeacherBiz teacherBiz;


	@Override
	public List<Map<String,Object>> getPlanList(String userFlow, String schStatusId,
			String schDeptName , int pageIndex, int pageSize,int examStatusId) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		List<Map<String,Object>> schPlans =  studentMapper.selectSchPlan(userFlow, schStatusId, schDeptName , start, end,examStatusId);
		return schPlans;
	}

	@Override
	public Map<String,Object> readEnteredDeptEdu(String schDeptFlow) {
		return studentMapper.selectEnteredDeptEdu(schDeptFlow);
	}
	
	@Override
	public String addEnteredDeptEdu(Map<String,Object> enteredDeptEdu) {
		String schDeptFlow = (String) enteredDeptEdu.get("schDeptFlow");
		String userFlow = (String) enteredDeptEdu.get("userFlow");
		String ursdId = PkUtil.getGUID();
		enteredDeptEdu.put("ursdId",ursdId);
		
		Map<String,String> user =  appMapper.readUser(userFlow).get(0);
		String userType = user.get("userType");
		
		String rsdId = studentMapper.selectReadSecDocumet(schDeptFlow,userType);
		enteredDeptEdu.put("rsdId",rsdId);
		
		studentMapper.insertEnteredDeptEdu(enteredDeptEdu);
		return ursdId;
	}

	@Override
	public void modEnteredDeptEdu(Map<String,Object> enteredDeptEdu) {
		studentMapper.updateEnteredDeptEdu(enteredDeptEdu);
	}

	@Override
	public List<StudyInfo> getStudyInfos(String schDeptFlow, int pageIndex,
			int pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		return studentMapper.selectStudyInfos(schDeptFlow, start, end);
	}

	@Override
	public StudyInfo readStudyInfo(String dataFlow) {
		return studentMapper.selectStudyInfo(dataFlow);
	}
	
	@Override
	public String addStudyInfo(StudyInfo studyInfo){
		String dataFlow = PkUtil.getGUID();
		studyInfo.setDataFlow(dataFlow);
		studentMapper.insertStudyInfo(studyInfo);
		return dataFlow;
	}

	@Override
	public void modStudyInfo(StudyInfo studyInfo){
		studentMapper.updateStudyInfo(studyInfo);
	}
	
	public void delStudyInfo(String dataFlow){
		studentMapper.delStudyInfo(dataFlow);
	}

	@Override
	public List<MedicalInfo> getMedicalInfos(String schDeptFlow,
			int pageIndex, int pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		return studentMapper.selectMedicalInfos(schDeptFlow, start, end);
	}

	@Override
	public MedicalInfo readMedicalInfo(String dataFlow) {
		return studentMapper.selectMedicalInfo(dataFlow);
	}

	@Override
	public String addMedicalInfo(MedicalInfo medicalInfo) {
		String dataFlow = PkUtil.getGUID();
		medicalInfo.setDataFlow(dataFlow);
		medicalInfo.setDateTime(DateUtil.getCurrDate());
		studentMapper.insertMedicalInfo(medicalInfo);
		return dataFlow;
	}

	@Override
	public void modMedicalInfo(MedicalInfo medicalInfo) {
		medicalInfo.setDateTime(DateUtil.getCurrDate());
		studentMapper.updateMedicalInfo(medicalInfo);
		
	}

	@Override
	public void delMedicalInfo(String dataFlow) {
		studentMapper.delMedicalInfo(dataFlow);
		
	}

	@Override
	public List<Map<String, Object>> getExamItems(String type) {
		return studentMapper.selectExamItem(type);
	}
	@Override
	public List<Map<String, Object>> getExamItemsByItemId(String type,String itemId) {
		return studentMapper.getExamItemsByItemId(type,itemId);
	}
	@Override
	public List<Map<String, Object>> getExamItemsDOPS(String doctorFlow) {
		String secName = studentMapper.selectSecName(doctorFlow);
		secName = StringUtil.defaultString(secName);
		String ExamItemID = null;
		List<String> examItemIdList = studentMapper.selectExamItemId("11");
		if(examItemIdList.size()>0){
			ExamItemID = examItemIdList.get(0);
		}
		if(secName.contains("核医学")){
			if(examItemIdList.size()>1){
				ExamItemID = examItemIdList.get(1);
			}
		}
		if(ExamItemID!=null){
			return studentMapper.selectExamItemByExamItemID(ExamItemID);
		}else{
			return null;
		}
	}
	@Override
	public List<Map<String, Object>> getExamItemsMiniCex(String doctorFlow) {
		String secName = studentMapper.selectSecName(doctorFlow);
		secName = StringUtil.defaultString(secName);
		String ExamItemID = null;
		List<String> examItemIdList = studentMapper.selectExamItemId("12");
		if(examItemIdList.size()>0){
			ExamItemID = examItemIdList.get(0);
		}
		if(secName.contains("检验")){
			if(examItemIdList.size()>1){
				ExamItemID = examItemIdList.get(1);
			}
		}
		if(ExamItemID!=null){
			return studentMapper.selectExamItemByExamItemID(ExamItemID);
		}else{
			return null;
		}
	}

	@Override
	public Map<String, Object> readExamInfo(String type, String schDeptFlow) {
		return studentMapper.readExamInfo(type, schDeptFlow);
	}

	@Override
	public void addAssess(Map<String, Object> examInfo, List<Map<String, Object>> marks) {
		String examInfoType = (String) examInfo.get("examInfoType");
		String schDeptFlow = (String) examInfo.get("schDeptFlow");
		String userFlow = (String) examInfo.get("userFlow");
		
		if("20".equals(examInfoType)){
			String tecId = studentMapper.selectTecId(schDeptFlow);
			examInfo.put("userId", tecId);
		}
		String secId = studentMapper.selectSecId(schDeptFlow);
		examInfo.put("secId", secId);
		Map<String,String> user = appMapper.readUser(userFlow).get(0);
		examInfo.put("examInfoKHR", user.get("userName"));
		studentMapper.insertExamInfo(examInfo);
		BigDecimal examInfoId = (BigDecimal) examInfo.get("dataFlow");
		for(Map<String, Object> mark : marks){
			mark.put("examInfoId", examInfoId);
			this.studentMapper.insertMark(mark);
		}
	}

	@Override
	public List<Map<String, Object>> getMarksForDept(String userFlow, String schDeptFlow, String examItemType) {
		String secId = studentMapper.selectSecId(schDeptFlow);
		return studentMapper.selectMarksForDept(userFlow, secId,examItemType);
	}

	@Override
	public List<Map<String, Object>> getMarksForTec(String userFlow , String schDeptFlow, String examItemType) {
		String tecId = studentMapper.selectTecId(schDeptFlow);
		return studentMapper.selectMarksForTec(tecId, userFlow,examItemType);
	}

	@Override
	public List<Activity> getActicitys(Map<String, Object> params , int pageIndex , int pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		params.put("start", start);
		params.put("end", end);
		List<Activity> activitys = studentMapper.selectActivitys(params);
		for(Activity userActivity : activitys){
			String schDeptFlow = userActivity.getSchDeptFlow();
			String schDeptName = studentMapper.selectHosSecName(schDeptFlow);
			userActivity.setSchDeptName(schDeptName);
		}
		return activitys;
	}

	@Override
	public Activity findActivity(String dataFlow, String userFlow) {
		return studentMapper.selectActivity(dataFlow, userFlow);
	}

	@Override
	public Activity readActivity(String dataFlow) {
		Activity exitActivity = studentMapper.selectActivityByFlow(dataFlow);
		return exitActivity;
	}

	@Override
	public void joinActivity(String dataFlow , String schDeptFlow , String userFlow) {
		Activity activity = studentMapper.selectActivityByFlow(dataFlow);
//		//是否可参加
//		String currDateTime = DateUtil.getCurrDateTime();
//		String joinDateTimeStrForUse = DateUtil.addMinute(currDateTime, NfyyGlobalConstant.JOIN_ACTIVITY_TIME);
//		String time = activity.getTime();
//		String startDateTime = DateUtil.transDateTime(time.substring(0, 19) , "yyyy-MM-dd HH:mm:ss" , "yyyyMMddHHmmss");
//		if(startDateTime.compareTo(joinDateTimeStrForUse)<0){
//			throw new RuntimeException(NfyyGlobalConstant.RESULT_NAME_NOT_JOIN_ACTIVITY);
//		}
		activity.setSchDeptFlow(schDeptFlow);
		activity.setUserFlow(userFlow);
		studentMapper.insertActivity(activity);
	}

	@Override
	public void scoreActivity(Activity activity) {
    	int mdmq = activity.getMdmq();
		int gfsl = activity.getGfsl();
		int jxdw = activity.getJxdw();
		int xgh = activity.getXgh();
		activity.setScore(mdmq+gfsl+jxdw+xgh);
		studentMapper.updateActivity(activity);
	}

	@Override
	public void saveOutDops(String userFlow, String schDeptFlow, HttpServletRequest request) {
		Map<String,Object> outDops = teacherBiz.readOutDops(schDeptFlow);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userFlow", userFlow);
		params.put("schDeptFlow", schDeptFlow);             
		params.put("DOPS_StuScore",request.getParameter("DOPS_StuScore"));   

		if(outDops!=null){
			studentMapper.modOutDops(params);
		}
	}

	@Override
	public void saveOutMiniCex(String userFlow, String schDeptFlow, HttpServletRequest request) {
		Map<String,Object> outMiniCex = teacherBiz.readOutMiniCex(schDeptFlow);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userFlow", userFlow);
		params.put("schDeptFlow", schDeptFlow);
		params.put("Mini_StuScore",request.getParameter("Mini_StuScore"));
		if(outMiniCex!=null){
			studentMapper.modOutMiniCex(params);
		}
	}
	
	@Override
	public Evaluation readOutSecBrief(String schDeptFlow) {
		return studentMapper.selectOutSecBrief(schDeptFlow);
	}

	@Override
	public void addOutSecBrief(Evaluation evaluation) {
		String schDeptFlow = evaluation.getSchDeptFlow();
		String cySecId = studentMapper.selectCySecId(schDeptFlow);
		evaluation.setCySecId(cySecId);
		evaluation.setCheckStatus("0");
		studentMapper.insertOutSecBrief(evaluation);
	}

	@Override
	public void modOutSecBrief(String schDeptFlow,String briefRequrie) {
		studentMapper.updateOutSecBrief(schDeptFlow, briefRequrie);
	}
	@Override
	public Map<String, String> getPaperByStandardDeptId(String secID, String specialtyID, String examType) {
		List<Map<String, String>> list=studentMapper.getPaperByStandardDeptId(secID,specialtyID,examType);
		if(list!=null&&list.size()>0)
		{
			return  list.get(0);
		}
		return null;
	}
}
