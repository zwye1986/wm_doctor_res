package com.pinde.res.biz.gzzy.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.gzzy.IGzzyAppBiz;
import com.pinde.res.biz.gzzy.IGzzyStudentBiz;
import com.pinde.res.biz.gzzy.IGzzyTeacherBiz;
import com.pinde.res.dao.gzzy.ext.GzzyStudentMapper;
import com.pinde.res.dao.gzzy.ext.GzzyTeacherMapper;
import com.pinde.res.enums.nfyy.DeptStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class GzzyTeacherBizImpl implements IGzzyTeacherBiz {
	@Autowired
	private IGzzyAppBiz gzzyAppBiz;
	@Autowired
	private IGzzyStudentBiz studentBiz;
	@Autowired
	private GzzyTeacherMapper teacherMapper;
    @Autowired
    private GzzyStudentMapper studentMapper;

	@Override
	public List<Map<String, Object>> getDoctorList(String userFlow, String schStatusId, String doctorName,
			String doctorType, int pageIndex, int pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
	    if(DeptStatusEnum.Entering.getId().equals(schStatusId)){
			return teacherMapper.getDoctorListEntering(userFlow, doctorName, doctorType, start, end);
	    }
	    if(DeptStatusEnum.NotEntered.getId().equals(schStatusId)){
			return teacherMapper.getDoctorListNotEntered(userFlow, doctorName, doctorType, start, end);
	    }
	    if(DeptStatusEnum.Exited.getId().equals(schStatusId)){
			return teacherMapper.getDoctorListExited(userFlow, doctorName, doctorType, start, end);
	    }
		return new ArrayList<Map<String, Object>>();
	}

	@Override
	public Map<String,Object> readDoctorDeptStatus(String userFlow, String doctorFlow) {
		return teacherMapper.readDoctorDeptStatus(userFlow,doctorFlow);
	}

	@Override
	public Map<String,Object> readDoctorInfo(String userFlow, String doctorFlow) {
		return teacherMapper.readDoctorInfo(userFlow,doctorFlow);
	}

	@Override
	public void enterDeptConfirm(String userFlow, String doctorFlow) {
		teacherMapper.enterDeptConfirm(userFlow,doctorFlow);
	}

	@Override
	public Map<String,Object> readEvalInfo(String userFlow, String doctorFlow) {
		return teacherMapper.readEvalInfo(userFlow,doctorFlow);
	}

	@Override
	public void saveEvalInfo(String userFlow, String doctorFlow,String eval) {
		teacherMapper.saveEvalInfo(userFlow,doctorFlow,eval);
	}

	@Override
	public List<Map<String, Object>> getActives(String userFlow, String doctorFlow, Integer pageIndex,
			Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
	    return teacherMapper.getActives(userFlow,doctorFlow,start,end);
	}

	@Override
	public Map<String,Object> readActivity(String dataFlow, String doctorFlow) {
		return teacherMapper.readActivity(dataFlow,doctorFlow);
	}

	@Override
	public Map<String,Object> readOutSecBrief(String doctorFlow) {
		return teacherMapper.readOutSecBrief(doctorFlow);
	}

	@Override
	public void saveOutSecBrief(String userFlow, String doctorFlow, HttpServletRequest request) {
		Map<String,Object> outSecBrief = new HashMap<String,Object>();
		outSecBrief.put("userFlow", userFlow);
		outSecBrief.put("doctorFlow", doctorFlow);
		outSecBrief.put("BriefRequrie", request.getParameter("BriefRequrie"));
		outSecBrief.put("GainsDefect", request.getParameter("GainsDefect"));
		outSecBrief.put("SecAppraise", request.getParameter("SecAppraise"));

		teacherMapper.saveOutSecBrief(outSecBrief);

		Map<String,String> doctor = gzzyAppBiz.readDoctor(doctorFlow);

		String type = "7";
		if("4".equals(doctor.get("roleFlow"))){
			type = "9";
		}

		BigDecimal ExamInfoID = null ;

		List<Map<String,Object>> assessTmp = this.studentBiz.getExamItems(type);

		List<Map<String,Object>> marks = getMarks(doctorFlow,type);
		if(marks.isEmpty()){
			Map<String,Object> examInfo = new HashMap<String,Object>();
			examInfo.put("userFlow", userFlow);
			examInfo.put("doctorFlow", doctorFlow);
			examInfo.put("total_score", request.getParameter("total_score"));
			examInfo.put("examInfoType", type);
			teacherMapper.addExamInfo(examInfo);
			ExamInfoID = (BigDecimal) examInfo.get("ExamInfoID");
//			for(Map<String,Object> tmp:assessTmp){
//				String reqItemId = (String) tmp.get("reqItemId");
//				String score = request.getParameter(reqItemId+"_score");
//				Map<String,Object>  mark = new HashMap<String,Object>();
//				mark.put("ExamInfoID", String.valueOf(ExamInfoID));
//				mark.put("ReqItemID", reqItemId);
//				mark.put("MarkDF", score);
//				mark.put("examInfoType", type);
//				teacherMapper.addMark(mark);
//			}
		}else{
			Map<String,Object> examInfo = new HashMap<String,Object>();
			examInfo.put("userFlow", userFlow);
			examInfo.put("doctorFlow", doctorFlow);
			examInfo.put("total_score", request.getParameter("total_score"));
			examInfo.put("examInfoType", type);
			teacherMapper.modExamInfo(examInfo);

//			for(Map<String,Object> tmp:assessTmp){
//				String reqItemId = (String) tmp.get("reqItemId");
//				String score = request.getParameter(reqItemId+"_score");
//				Map<String,Object>  mark = new HashMap<String,Object>();
//				mark.put("userFlow", userFlow);
//				mark.put("doctorFlow", doctorFlow);
//				mark.put("ReqItemID", reqItemId);
//				mark.put("MarkDF", score);
//				mark.put("examInfoType", type);
//				teacherMapper.modMark(mark);
//			}
		}

		Map<String,Object> dailyInfo = readDailyInfo(doctorFlow);
		Map<String,Object> daily = new HashMap<String,Object>();
		daily.put("userFlow", userFlow);
		daily.put("doctorFlow", doctorFlow);
		daily.put("Score1", request.getParameter("Score1"));
		daily.put("Score2", request.getParameter("Score2"));
		daily.put("Score3", request.getParameter("Score3"));
		daily.put("Score4", request.getParameter("Score4"));
		daily.put("Score5", request.getParameter("Score5"));
		daily.put("Score6", request.getParameter("Score6"));
		daily.put("Score7", request.getParameter("Score7"));
		daily.put("Score8", request.getParameter("Score8"));
		daily.put("Score9", request.getParameter("Score9"));
		daily.put("Score10", request.getParameter("Score10"));
		daily.put("TotalScore", request.getParameter("TotalScore"));
		daily.put("Status", request.getParameter("Status"));
		daily.put("Teacher", request.getParameter("Teacher"));
		daily.put("Professer", request.getParameter("Professer"));
		daily.put("CheckDate", request.getParameter("CheckDate"));
		if(dailyInfo==null)
		{
			teacherMapper.addDailyInfo(daily);
		}else{
			daily.put("Id", dailyInfo.get("Id"));
			teacherMapper.modDailyInfo(daily);
		}
		Map<String,Object> cycleOutSectionRecord = readCycleOutSectionRecord(userFlow, doctorFlow);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userFlow", userFlow);
		params.put("doctorFlow", doctorFlow);
		params.put("SecAppraise", request.getParameter("SecAppraise"));
		params.put("AssessmentMark", request.getParameter("AssessmentMark"));
		params.put("SickLeaveDay", request.getParameter("SickLeaveDay"));
		params.put("AbsenteeismDay", request.getParameter("AbsenteeismDay"));
		if(cycleOutSectionRecord==null){
			params.put("ExamInfoID", String.valueOf(ExamInfoID));
			teacherMapper.addCycleOutSectionRecord(params);
		}else{
			teacherMapper.modCycleOutSectionRecord(params);
		}
	}

	@Override
	public Map<String,Object> readCycleOutSectionRecord(String userFlow, String doctorFlow) {
		return teacherMapper.readCycleOutSectionRecord(userFlow,doctorFlow);
	}

	@Override
	public Map<String,Object> readExamInfo(String doctorFlow,String examInfoType) {
		return teacherMapper.readExamInfo(doctorFlow,examInfoType);
	}

	@Override
	public List<Map<String, Object>> getMarks(String doctorFlow,String examInfoType) {
	    return teacherMapper.getMarks(doctorFlow,examInfoType);
	}

	@Override
	public Map<String, Object> readOutDops(String doctorFlow) {
		return teacherMapper.readOutDops(doctorFlow);
	}

	@Override
	public Map<String, Object> readOutMiniCex(String doctorFlow) {
		return teacherMapper.readOutMiniCex(doctorFlow);
	}

	@Override
	public void saveOutDops(String userFlow, String doctorFlow, HttpServletRequest request) {
		Map<String,Object> outDops = readOutDops(doctorFlow);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userFlow", userFlow);
		params.put("doctorFlow", doctorFlow);
		params.put("DOPS_UCSID",request.getParameter("DOPS_UCSID"));
		params.put("DOPS_TecType",request.getParameter("DOPS_TecType"));
		params.put("DOPS_Time",request.getParameter("DOPS_Time"));
		params.put("DOPS_Address",request.getParameter("DOPS_Address"));
		params.put("DOPS_Name",request.getParameter("DOPS_Name"));
		params.put("DOPS_Age",request.getParameter("DOPS_Age"));
		params.put("DOPS_Sex",request.getParameter("DOPS_Sex"));
		params.put("DOPS_PType",request.getParameter("DOPS_PType"));
		params.put("DOPS_PDiagnosis",request.getParameter("DOPS_PDiagnosis"));
		params.put("DOPS_Operate",request.getParameter("DOPS_Operate"));
		params.put("DOPS_Num",request.getParameter("DOPS_Num"));
		params.put("DOPS_Level",request.getParameter("DOPS_Level"));
		params.put("DOPS_ReviewTime",request.getParameter("DOPS_ReviewTime"));
		params.put("DOPS_Feedback",request.getParameter("DOPS_Feedback"));
		params.put("DOPS_TecScore",request.getParameter("DOPS_TecScore"));
		params.put("DOPS_TecMessage",request.getParameter("DOPS_TecMessage"));
		params.put("DOPS_StuScore",request.getParameter("DOPS_StuScore"));
		params.put("DOPS_UserID",request.getParameter("DOPS_UserID"));
		params.put("DOPS_State",request.getParameter("DOPS_State"));
		params.put("DOPS_TecID",request.getParameter("DOPS_TecID"));
		params.put("DOPS_PTypeState",request.getParameter("DOPS_PTypeState"));

		if(outDops==null){
			params.put("DOPS_ID",PkUtil.getGUID());

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
                params.put("DOPS_Template",ExamItemID);
            }

			teacherMapper.addOutDops(params);
		}else{
			teacherMapper.modOutDops(params);
		}

		BigDecimal ExamInfoID = null ;

		List<Map<String,Object>> assessTmp = this.studentBiz.getExamItemsDOPS(doctorFlow);

		List<Map<String,Object>> marks = getMarks(doctorFlow,"11");
		if(marks.isEmpty()){
			Map<String,Object> examInfo = new HashMap<String,Object>();
			examInfo.put("userFlow", userFlow);
			examInfo.put("doctorFlow", doctorFlow);
			examInfo.put("total_score", request.getParameter("DOPS_TecScore"));
			examInfo.put("examInfoType", "11");
			teacherMapper.addExamInfo(examInfo);
			ExamInfoID = (BigDecimal) examInfo.get("ExamInfoID");

			for(Map<String,Object> tmp:assessTmp){
				String reqItemId = (String) tmp.get("reqItemId");
				String score = request.getParameter(reqItemId+"_score");
				Map<String,Object>  mark = new HashMap<String,Object>();
				mark.put("ExamInfoID", String.valueOf(ExamInfoID));
				mark.put("ReqItemID", reqItemId);
				mark.put("MarkDF", score);
				mark.put("examInfoType", "11");
				teacherMapper.addMark(mark);
			}
		}else{
			Map<String,Object> examInfo = new HashMap<String,Object>();
			examInfo.put("userFlow", userFlow);
			examInfo.put("doctorFlow", doctorFlow);
			examInfo.put("total_score", request.getParameter("DOPS_TecScore"));
			examInfo.put("examInfoType", "11");
			teacherMapper.modExamInfo(examInfo);

			for(Map<String,Object> tmp:assessTmp){
				String reqItemId = (String) tmp.get("reqItemId");
				String score = request.getParameter(reqItemId+"_score");
				Map<String,Object>  mark = new HashMap<String,Object>();
				mark.put("userFlow", userFlow);
				mark.put("doctorFlow", doctorFlow);
				mark.put("ReqItemID", reqItemId);
				mark.put("MarkDF", score);
				mark.put("examInfoType", "11");
				teacherMapper.modMark(mark);
			}
		}
	}

	@Override
	public void saveOutMiniCex(String userFlow, String doctorFlow, HttpServletRequest request) {
		Map<String,Object> outMiniCex = readOutMiniCex(doctorFlow);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userFlow", userFlow);
		params.put("doctorFlow", doctorFlow);
		params.put("Mini_PTypeState",request.getParameter("Mini_PTypeState"));
		params.put("Mini_PDiagnosis",request.getParameter("Mini_PDiagnosis"));
		params.put("Mini_Operate",request.getParameter("Mini_Operate"));
		params.put("Mini_Num",request.getParameter("Mini_Num"));
		params.put("Mini_ReviewTime",request.getParameter("Mini_ReviewTime"));
		params.put("Mini_Feedback",request.getParameter("Mini_Feedback"));
		params.put("Mini_TecScore",request.getParameter("Mini_TecScore"));
		params.put("Mini_TecMessage",request.getParameter("Mini_TecMessage"));
		params.put("Mini_StuScore",request.getParameter("Mini_StuScore"));
		params.put("Mini_TecID",request.getParameter("Mini_TecID"));
		params.put("Mini_UCSID",request.getParameter("Mini_UCSID"));
		params.put("Mini_UserID",request.getParameter("Mini_UserID"));
		params.put("Mini_State",request.getParameter("Mini_State"));
		params.put("Mini_TecType",request.getParameter("Mini_TecType"));
		params.put("Mini_Time",request.getParameter("Mini_Time"));
		params.put("Mini_Address",request.getParameter("Mini_Address"));
		params.put("Mini_Name",request.getParameter("Mini_Name"));
		params.put("Mini_Age",request.getParameter("Mini_Age"));
		params.put("Mini_Sex",request.getParameter("Mini_Sex"));
		params.put("Mini_PType",request.getParameter("Mini_PType"));
		if(outMiniCex==null){
			params.put("Mini_ID",PkUtil.getGUID());
			teacherMapper.addOutMiniCex(params);
		}else{
			teacherMapper.modOutMiniCex(params);
		}

		BigDecimal ExamInfoID = null ;

		List<Map<String,Object>> assessTmp = this.studentBiz.getExamItems("12");

		List<Map<String,Object>> marks = getMarks(doctorFlow,"12");
		if(marks.isEmpty()){
			Map<String,Object> examInfo = new HashMap<String,Object>();
			examInfo.put("userFlow", userFlow);
			examInfo.put("doctorFlow", doctorFlow);
			examInfo.put("total_score", request.getParameter("Mini_TecScore"));
			examInfo.put("examInfoType", "12");
			teacherMapper.addExamInfo(examInfo);
			ExamInfoID = (BigDecimal) examInfo.get("ExamInfoID");

			for(Map<String,Object> tmp:assessTmp){
				String reqItemId = (String) tmp.get("reqItemId");
				String score = request.getParameter(reqItemId+"_score");
				Map<String,Object>  mark = new HashMap<String,Object>();
				mark.put("ExamInfoID", String.valueOf(ExamInfoID));
				mark.put("ReqItemID", reqItemId);
				mark.put("MarkDF", score);
				mark.put("examInfoType", "12");
				teacherMapper.addMark(mark);
			}
		}else{
			Map<String,Object> examInfo = new HashMap<String,Object>();
			examInfo.put("userFlow", userFlow);
			examInfo.put("doctorFlow", doctorFlow);
			examInfo.put("total_score", request.getParameter("Mini_TecScore"));
			examInfo.put("examInfoType", "12");
			teacherMapper.modExamInfo(examInfo);

			for(Map<String,Object> tmp:assessTmp){
				String reqItemId = (String) tmp.get("reqItemId");
				String score = request.getParameter(reqItemId+"_score");
				Map<String,Object>  mark = new HashMap<String,Object>();
				mark.put("userFlow", userFlow);
				mark.put("doctorFlow", doctorFlow);
				mark.put("ReqItemID", reqItemId);
				mark.put("MarkDF", score);
				mark.put("examInfoType", "12");
				teacherMapper.modMark(mark);
			}
		}
	}

	@Override
	public void saveOutMiniCexFz(String userFlow, String doctorFlow, HttpServletRequest request) {
		Map<String,Object> outMiniCex = readOutMiniCexFz(doctorFlow);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userFlow", userFlow);
		params.put("doctorFlow", doctorFlow);
		params.put("Mini_Item",request.getParameter("Mini_Item"));
		params.put("Mini_Content",request.getParameter("Mini_Content"));
		params.put("Mini_Score",request.getParameter("Mini_Score"));
		params.put("Mini_TecName",request.getParameter("Mini_TecName"));
		params.put("Mini_Time",request.getParameter("Mini_Time"));
		params.put("Mini_ProfessorName",request.getParameter("Mini_ProfessorName"));
		if(outMiniCex==null){
			params.put("Mini_ID",PkUtil.getGUID());
			teacherMapper.addOutMiniCexFz(params);
		}else{
			teacherMapper.modOutMiniCexFz(params);
		}
	}

	@Override
	public List<Map<String, Object>> getWaitEvalOutSecBriefList(String userFlow) {
		return teacherMapper.getWaitEvalOutSecBriefList(userFlow);
	}

	@Override
	public Map<String,Object> getWaitArrangeActivityCount(String userFlow) {
		return teacherMapper.getWaitArrangeActivityCount(userFlow);
	}

	@Override
	public List<Map<String, Object>> getNeedConfirmActives(String userFlow, String doctorFlow, Integer pageIndex,
			Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
	    return teacherMapper.getNeedConfirmActives(userFlow,doctorFlow,start,end);
	}

	@Override
	public List<Map<String, Object>> getNeedConfirmActiveUsers(String dataFlow) {
		return teacherMapper.getNeedConfirmActiveUsers(dataFlow);
	}

	@Override
	public void confirmActiveUsers(String dataFlow, HttpServletRequest request) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("dataFlow", dataFlow);
		List<Map<String,Object>> activityUsers = getNeedConfirmActiveUsers(dataFlow);
		for(Map<String,Object> user : activityUsers){
			String CaDisID = (String) user.get("CaDisID");
			String checkStatus = request.getParameter(CaDisID);
			if("Y".equals(checkStatus)){
				params.put("CaDisID", CaDisID);
				teacherMapper.confirmActiveUsers(params);
			}
		}
	}

	@Override
	public Float readTheoryScore(String doctorFlow) {
		return teacherMapper.readTheoryScore(doctorFlow);
	}

	@Override
	public List<Map<String, String>> getDataAudit(Map<String, String> searchMap) {
		return teacherMapper.getDataAudit(searchMap);
	}

	@Override
	public List<Map<String, String>> getMrDatas(Map<String, Object> searchMap) {
		int start = ((int) searchMap.get("pageIndex")-1)*(int)searchMap.get("pageSize")+1;
		int end = (int)searchMap.get("pageIndex")*(int)searchMap.get("pageSize");
		searchMap.put("start",start);
		searchMap.put("end",end);
		return teacherMapper.getMrDatas(searchMap);
	}

	@Override
	public Map<String, String> getMrFinishMap(Map<String, Object> searchMap) {
		return teacherMapper.getMrFinishMap(searchMap);
	}

	@Override
	public List<Map<String, String>> getDiseaseDatas(Map<String, Object> searchMap) {
		int start = ((int) searchMap.get("pageIndex")-1)*(int)searchMap.get("pageSize")+1;
		int end = (int)searchMap.get("pageIndex")*(int)searchMap.get("pageSize");
		searchMap.put("start",start);
		searchMap.put("end",end);
		return teacherMapper.getDiseaseDatas(searchMap);
	}

	@Override
	public Map<String, String> getDiseaseFinishMap(Map<String, Object> searchMap) {

		return teacherMapper.getDiseaseFinishMap(searchMap);
	}

	@Override
	public List<Map<String, String>> getSkillDatas(Map<String, Object> searchMap) {
		int start = ((int) searchMap.get("pageIndex")-1)*(int)searchMap.get("pageSize")+1;
		int end = (int)searchMap.get("pageIndex")*(int)searchMap.get("pageSize");
		searchMap.put("start",start);
		searchMap.put("end",end);
		return teacherMapper.getSkillDatas(searchMap);
	}

	@Override
	public Map<String, String> getSkillFinishMap(Map<String, Object> searchMap) {
		return teacherMapper.getSkillFinishMap(searchMap);
	}

	@Override
	public List<Map<String, String>> getOperationDatas(Map<String, Object> searchMap) {
		int start = ((int) searchMap.get("pageIndex")-1)*(int)searchMap.get("pageSize")+1;
		int end = (int)searchMap.get("pageIndex")*(int)searchMap.get("pageSize");
		searchMap.put("start",start);
		searchMap.put("end",end);
		return teacherMapper.getOperationDatas(searchMap);
	}

	@Override
	public Map<String, String> getOperationFinishMap(Map<String, Object> searchMap) {
		return teacherMapper.getOperationFinishMap(searchMap);
	}

	@Override
	public List<Map<String, String>> getDiscipleDatas(Map<String, Object> searchMap) {
		int start = ((int) searchMap.get("pageIndex")-1)*(int)searchMap.get("pageSize")+1;
		int end = (int)searchMap.get("pageIndex")*(int)searchMap.get("pageSize");
		searchMap.put("start",start);
		searchMap.put("end",end);
		return teacherMapper.getDiscipleDatas(searchMap);
	}

	@Override
	public Map<String, String> getDiscipleFinishMap(Map<String, Object> searchMap) {
		return teacherMapper.getDiscipleFinishMap(searchMap);
	}

	@Override
	public Map<String, String> getMrData(String dataFlow) {
		return teacherMapper.getMrData(dataFlow);
	}

	@Override
	public Map<String, String> getDiseaseData(String dataFlow) {
		return teacherMapper.getDiseaseData(dataFlow);
	}

	@Override
	public Map<String, String> getSkillData(String dataFlow) {
		return teacherMapper.getSkillData(dataFlow);
	}

	@Override
	public Map<String, String> getOperationData(String dataFlow) {
		return teacherMapper.getOperationData(dataFlow);
	}

	@Override
	public Map<String, String> getDiscipleData(String dataFlow) {
		return teacherMapper.getDiscipleData(dataFlow);
	}

	@Override
	public List<Map<String, String>> getAuditMrDatas(Map<String, Object> searchMap) {
		return teacherMapper.getAuditMrDatas(searchMap);
	}

	@Override
	public List<Map<String, String>> getAuditDiseaseDatas(Map<String, Object> searchMap) {
		return teacherMapper.getAuditDiseaseDatas(searchMap);
	}

	@Override
	public List<Map<String, String>> getAuditSkillDatas(Map<String, Object> searchMap) {
		return teacherMapper.getAuditSkillDatas(searchMap);
	}

	@Override
	public List<Map<String, String>> getAuditOperationDatas(Map<String, Object> searchMap) {
		return teacherMapper.getAuditOperationDatas(searchMap);
	}

	@Override
	public List<Map<String, String>> getAuditDiscipleDatas(Map<String, Object> searchMap) {
		return teacherMapper.getAuditDiscipleDatas(searchMap);
	}

	@Override
	public int batchAuditData(List<Map<String, String>> datas, String dataTypeId, String isPass) {
		int c=0;
		if(datas!=null&&!datas.isEmpty())
		{
			for(Map<String, String> data:datas)
			{
				String dataFlow=data.get("RecID");
				c+=auditData(dataFlow,dataTypeId,isPass);
			}
		}
		return c;
	}


	@Override
	public  int auditData(String dataFlow, String dataTypeId, String isPass) {
		int c=0;
		if("mr".equals(dataTypeId)){
			c = teacherMapper.auditMrData(dataFlow,isPass);
		}
		//病种
		if("disease".equals(dataTypeId)){
			c = teacherMapper.auditDiseaseData(dataFlow,isPass);
		}
		//操作技能
		if("skill".equals(dataTypeId)){
			c = teacherMapper.auditSkillData(dataFlow,isPass);
		}
		//手术
		if("operation".equals(dataTypeId)){
			c = teacherMapper.auditOperationData(dataFlow,isPass);
		}
		//门诊病历
		if("disciple".equals(dataTypeId)){
			c = teacherMapper.auditDiscipleData(dataFlow,isPass);
		}
		return  c;

	}

	@Override
	public Map<String, Object> readBaseInfo(String doctorFlow, String studentFlow) {
		return teacherMapper.readBaseInfo(doctorFlow,studentFlow);
	}

	@Override
	public Map<String, Object> readDailyInfo(String doctorFlow) {
		return teacherMapper.readDailyInfo(doctorFlow);
	}

	@Override
	public BigDecimal readShijia(Map<String, Object> baseInfo) {
		return teacherMapper.readShijia(baseInfo);
	}

	@Override
	public BigDecimal readBingjia(Map<String, Object> baseInfo) {
		return teacherMapper.readBingjia(baseInfo);
	}

	@Override
	public Integer readQueQing(Map<String, Object> baseInfo) {
		return teacherMapper.readQueQing(baseInfo);
	}

	@Override
	public String readKzrName(String doctorFlow) {
		return teacherMapper.readKzrName(doctorFlow);
	}

	@Override
	public List<Map<String, String>> isFzDept(String  ucsid) {
		return teacherMapper.isFzDept(ucsid);
	}

	@Override
	public Map<String, Object> readOutMiniCexFz(String doctorFlow) {
		return teacherMapper.readOutMiniCexFz(doctorFlow);
	}
}
