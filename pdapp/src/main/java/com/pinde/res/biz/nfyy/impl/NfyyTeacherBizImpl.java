package com.pinde.res.biz.nfyy.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.pinde.core.util.StringUtil;
import com.pinde.res.dao.nfyy.ext.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinde.core.util.PkUtil;
import com.pinde.res.biz.nfyy.INfyyAppBiz;
import com.pinde.res.biz.nfyy.INfyyStudentBiz;
import com.pinde.res.biz.nfyy.INfyyTeacherBiz;
import com.pinde.res.dao.nfyy.ext.TeacherMapper;
import com.pinde.res.enums.nfyy.DeptStatusEnum;

@Service
@Transactional(rollbackFor=Exception.class)
public class NfyyTeacherBizImpl implements INfyyTeacherBiz{
	@Autowired
	private INfyyAppBiz nfyyAppBiz;
	@Autowired
	private INfyyStudentBiz nfyyBiz;
	@Autowired
	private TeacherMapper teacherMapper;
    @Autowired
    private StudentMapper studentMapper;

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

		Map<String,String> doctor = nfyyAppBiz.readDoctor(doctorFlow);

		String type = "7";
		if("4".equals(doctor.get("roleFlow"))){
			type = "9";
		}

		BigDecimal ExamInfoID = null ;

		List<Map<String,Object>> assessTmp = this.nfyyBiz.getExamItems(type);

		List<Map<String,Object>> marks = getMarks(doctorFlow,type);
		if(marks.isEmpty()){
			Map<String,Object> examInfo = new HashMap<String,Object>();
			examInfo.put("userFlow", userFlow);
			examInfo.put("doctorFlow", doctorFlow);
			examInfo.put("total_score", request.getParameter("total_score"));
			examInfo.put("examInfoType", type);
			teacherMapper.addExamInfo(examInfo);
			ExamInfoID = (BigDecimal) examInfo.get("ExamInfoID");

			for(Map<String,Object> tmp:assessTmp){
				String reqItemId = (String) tmp.get("reqItemId");
				String score = request.getParameter(reqItemId+"_score");
				Map<String,Object>  mark = new HashMap<String,Object>();
				mark.put("ExamInfoID", String.valueOf(ExamInfoID));
				mark.put("ReqItemID", reqItemId);
				mark.put("MarkDF", score);
				mark.put("examInfoType", type);
				teacherMapper.addMark(mark);
			}
		}else{
			Map<String,Object> examInfo = new HashMap<String,Object>();
			examInfo.put("userFlow", userFlow);
			examInfo.put("doctorFlow", doctorFlow);
			examInfo.put("total_score", request.getParameter("total_score"));
			examInfo.put("examInfoType", type);
			teacherMapper.modExamInfo(examInfo);

			for(Map<String,Object> tmp:assessTmp){
				String reqItemId = (String) tmp.get("reqItemId");
				String score = request.getParameter(reqItemId+"_score");
				Map<String,Object>  mark = new HashMap<String,Object>();
				mark.put("userFlow", userFlow);
				mark.put("doctorFlow", doctorFlow);
				mark.put("ReqItemID", reqItemId);
				mark.put("MarkDF", score);
				mark.put("examInfoType", type);
				teacherMapper.modMark(mark);
			}
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

		List<Map<String,Object>> assessTmp = this.nfyyBiz.getExamItemsDOPS(doctorFlow);

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

		List<Map<String,Object>> assessTmp = this.nfyyBiz.getExamItems("12");

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

}
