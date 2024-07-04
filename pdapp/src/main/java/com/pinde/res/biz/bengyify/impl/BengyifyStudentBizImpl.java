package com.pinde.res.biz.bengyify.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.pinde.core.util.StringUtil;
import com.pinde.res.dao.bengyify.ext.BengyifyAppMapper;
import com.pinde.res.dao.bengyify.ext.BengyifyStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.res.biz.bengyify.IBengyifyStudentBiz;
import com.pinde.res.biz.bengyify.IBengyifyTeacherBiz;
import com.pinde.res.model.bengyify.mo.Activity;
import com.pinde.res.model.bengyify.mo.Evaluation;
import com.pinde.res.model.bengyify.mo.MedicalInfo;
import com.pinde.res.model.bengyify.mo.StudyInfo;

@Service
@Transactional(rollbackFor=Exception.class)
public class BengyifyStudentBizImpl implements IBengyifyStudentBiz{
	@Autowired
	private BengyifyAppMapper bengyifyappMapper;
	@Autowired
	private BengyifyStudentMapper bengyifystudentMapper;
	@Autowired
	private IBengyifyTeacherBiz bengyifyTeacherBiz;


	@Override
	public List<Map<String,Object>> getPlanList(String userFlow, String schStatusId,
			String schDeptName , int pageIndex, int pageSize,int examStatusId) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		List<Map<String,Object>> schPlans =  bengyifystudentMapper.selectSchPlan(userFlow, schStatusId, schDeptName , start, end,examStatusId);
		return schPlans;
	}

	@Override
	public Map<String,Object> readEnteredDeptEdu(String schDeptFlow) {
		return bengyifystudentMapper.selectEnteredDeptEdu(schDeptFlow);
	}
	
	@Override
	public String addEnteredDeptEdu(Map<String,Object> enteredDeptEdu) {
		String schDeptFlow = (String) enteredDeptEdu.get("schDeptFlow");
		String userFlow = (String) enteredDeptEdu.get("userFlow");
		String ursdId = PkUtil.getGUID();
		enteredDeptEdu.put("ursdId",ursdId);
		
		Map<String,String> user =  bengyifyappMapper.readUser(userFlow);
		String userType = user.get("userType");
		
		String rsdId = bengyifystudentMapper.selectReadSecDocumet(schDeptFlow,userType);
		enteredDeptEdu.put("rsdId",rsdId);
		
		bengyifystudentMapper.insertEnteredDeptEdu(enteredDeptEdu);
		return ursdId;
	}

	@Override
	public void modEnteredDeptEdu(Map<String,Object> enteredDeptEdu) {
		bengyifystudentMapper.updateEnteredDeptEdu(enteredDeptEdu);
	}

	@Override
	public List<StudyInfo> getStudyInfos(String schDeptFlow, int pageIndex,
			int pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		return bengyifystudentMapper.selectStudyInfos(schDeptFlow, start, end);
	}

	@Override
	public StudyInfo readStudyInfo(String dataFlow) {
		return bengyifystudentMapper.selectStudyInfo(dataFlow);
	}
	
	@Override
	public String addStudyInfo(StudyInfo studyInfo){
		String dataFlow = PkUtil.getGUID();
		studyInfo.setDataFlow(dataFlow);
		bengyifystudentMapper.insertStudyInfo(studyInfo);
		return dataFlow;
	}

	@Override
	public void modStudyInfo(StudyInfo studyInfo){
		bengyifystudentMapper.updateStudyInfo(studyInfo);
	}
	
	public void delStudyInfo(String dataFlow){
		bengyifystudentMapper.delStudyInfo(dataFlow);
	}

	@Override
	public List<MedicalInfo> getMedicalInfos(String schDeptFlow,
			int pageIndex, int pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		return bengyifystudentMapper.selectMedicalInfos(schDeptFlow, start, end);
	}

	@Override
	public MedicalInfo readMedicalInfo(String dataFlow) {
		return bengyifystudentMapper.selectMedicalInfo(dataFlow);
	}

	@Override
	public String addMedicalInfo(MedicalInfo medicalInfo) {
		String dataFlow = PkUtil.getGUID();
		medicalInfo.setDataFlow(dataFlow);
		medicalInfo.setDateTime(DateUtil.getCurrDate());
		bengyifystudentMapper.insertMedicalInfo(medicalInfo);
		return dataFlow;
	}

	@Override
	public void modMedicalInfo(MedicalInfo medicalInfo) {
		medicalInfo.setDateTime(DateUtil.getCurrDate());
		bengyifystudentMapper.updateMedicalInfo(medicalInfo);
		
	}

	@Override
	public void delMedicalInfo(String dataFlow) {
		bengyifystudentMapper.delMedicalInfo(dataFlow);
		
	}

	@Override
	public List<Map<String, Object>> getExamItems(String type) {
		return bengyifystudentMapper.selectExamItem(type);
	}
	@Override
	public List<Map<String, Object>> getExamItemsByItemId(String type,String itemId) {
		return bengyifystudentMapper.getExamItemsByItemId(type,itemId);
	}

	@Override
	public List<Map<String, Object>> getUserSignList(String nowDay, String userFlow) {
		return bengyifystudentMapper.getUserSignList(nowDay,userFlow);
	}
	@Override
	public Map<String, Object> getDocNowDept(String userFlow) {
		return bengyifystudentMapper.getDocNowDept(userFlow);
	}

	@Override
	public int addSign(Map<String, Object> param) {
		return bengyifystudentMapper.addSign(param);
	}

	@Override
	public List<Map<String, Object>> getExamItemsDOPS(String doctorFlow) {
		String secName = bengyifystudentMapper.selectSecName(doctorFlow);
		secName = StringUtil.defaultString(secName);
		String ExamItemID = null;
		List<String> examItemIdList = bengyifystudentMapper.selectExamItemId("11");
		if(examItemIdList.size()>0){
			ExamItemID = examItemIdList.get(0);
		}
		if(secName.contains("核医学")){
			if(examItemIdList.size()>1){
				ExamItemID = examItemIdList.get(1);
			}
		}
		if(ExamItemID!=null){
			return bengyifystudentMapper.selectExamItemByExamItemID(ExamItemID);
		}else{
			return null;
		}
	}

	@Override
	public Map<String, Object> readExamInfo(String type, String schDeptFlow) {
		return bengyifystudentMapper.readExamInfo(type, schDeptFlow);
	}

	@Override
	public void addAssess(Map<String, Object> examInfo, List<Map<String, Object>> marks) {
		String examInfoType = (String) examInfo.get("examInfoType");
		String schDeptFlow = (String) examInfo.get("schDeptFlow");
		String userFlow = (String) examInfo.get("userFlow");
		
		if("0".equals(examInfoType)){
			String tecId = bengyifystudentMapper.selectTecId(schDeptFlow);
			examInfo.put("userId", tecId);
		}
		String secId = bengyifystudentMapper.selectSecId(schDeptFlow);
		examInfo.put("secId", secId);
		Map<String,String> user = bengyifyappMapper.readUser(userFlow);
		examInfo.put("examInfoKHR", user.get("userName"));
		bengyifystudentMapper.insertExamInfo(examInfo);
		BigDecimal examInfoId = (BigDecimal) examInfo.get("dataFlow");
		for(Map<String, Object> mark : marks){
			mark.put("examInfoId", examInfoId);
			this.bengyifystudentMapper.insertMark(mark);
		}
	}

	@Override
	public List<Map<String, Object>> getMarksForDept(String userFlow , String schDeptFlow) {
		String secId = bengyifystudentMapper.selectSecId(schDeptFlow);
		return bengyifystudentMapper.selectMarksForDept(userFlow, secId);
	}

	@Override
	public List<Map<String, Object>> getMarksForTec(String userFlow , String schDeptFlow) {
		String tecId = bengyifystudentMapper.selectTecId(schDeptFlow);
		return bengyifystudentMapper.selectMarksForTec(tecId, userFlow);
	}

	@Override
	public List<Activity> getActicitys(Map<String, Object> params , int pageIndex , int pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		params.put("start", start);
		params.put("end", end);
		List<Activity> activitys = bengyifystudentMapper.selectActivitys(params);
		for(Activity userActivity : activitys){
			String schDeptFlow = userActivity.getSchDeptFlow();
			String schDeptName = bengyifystudentMapper.selectHosSecName(schDeptFlow);
			userActivity.setSchDeptName(schDeptName);
		}
		return activitys;
	}

	@Override
	public Activity findActivity(String dataFlow, String userFlow) {
		return bengyifystudentMapper.selectActivity(dataFlow, userFlow);
	}

	@Override
	public Activity readActivity(String dataFlow) {
		Activity exitActivity = bengyifystudentMapper.selectActivityByFlow(dataFlow);
		return exitActivity;
	}

	@Override
	public void joinActivity(String dataFlow , String schDeptFlow , String userFlow) {
		Activity activity = bengyifystudentMapper.selectActivityByFlow(dataFlow);
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
		bengyifystudentMapper.insertActivity(activity);
	}

	@Override
	public void scoreActivity(Activity activity) {
    	int mdmq = activity.getMdmq();
		int gfsl = activity.getGfsl();
		int jxdw = activity.getJxdw();
		int xgh = activity.getXgh();
		activity.setScore(mdmq+gfsl+jxdw+xgh);
		bengyifystudentMapper.updateActivity(activity);
	}

	@Override
	public void saveOutDops(String userFlow, String schDeptFlow, HttpServletRequest request) {
		Map<String,Object> outDops = bengyifyTeacherBiz.readOutDops(schDeptFlow);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userFlow", userFlow);
		params.put("schDeptFlow", schDeptFlow);             
		params.put("DOPS_StuScore",request.getParameter("DOPS_StuScore"));   

		if(outDops!=null){
			bengyifystudentMapper.modOutDops(params);
		}
	}

	@Override
	public void saveOutMiniCex(String userFlow, String schDeptFlow, HttpServletRequest request) {
		Map<String,Object> outMiniCex = bengyifyTeacherBiz.readOutMiniCex(schDeptFlow);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userFlow", userFlow);
		params.put("schDeptFlow", schDeptFlow);
		params.put("Mini_StuScore",request.getParameter("Mini_StuScore"));
		if(outMiniCex!=null){
			bengyifystudentMapper.modOutMiniCex(params);
		}
	}
	
	@Override
	public Evaluation readOutSecBrief(String schDeptFlow) {
		return bengyifystudentMapper.selectOutSecBrief(schDeptFlow);
	}

	@Override
	public void addOutSecBrief(Evaluation evaluation) {
		String schDeptFlow = evaluation.getSchDeptFlow();
		String cySecId = bengyifystudentMapper.selectCySecId(schDeptFlow);
		evaluation.setCySecId(cySecId);
		evaluation.setCheckStatus("0");
		bengyifystudentMapper.insertOutSecBrief(evaluation);
	}

	@Override
	public void modOutSecBrief(String schDeptFlow,String briefRequrie) {
		bengyifystudentMapper.updateOutSecBrief(schDeptFlow, briefRequrie);
	}

}
