package com.pinde.res.biz.hzyy.impl;

import com.pinde.core.commom.enums.DeptStatusEnum;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hzyy.IHzyyAppBiz;
import com.pinde.res.biz.hzyy.IHzyyStudentBiz;
import com.pinde.res.biz.hzyy.IHzyyTeacherBiz;
import com.pinde.res.ctrl.upload.FileForm;
import com.pinde.res.dao.hzyy.ext.HzyyStudentMapper;
import com.pinde.res.dao.hzyy.ext.HzyyTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(rollbackFor=Exception.class)
public class HzyyTeacherBizImpl implements IHzyyTeacherBiz {
	@Autowired
	private IHzyyAppBiz nfyyAppBiz;
	@Autowired
	private IHzyyStudentBiz nfyyBiz;
	@Autowired
	private HzyyTeacherMapper teacherMapper;
    @Autowired
    private HzyyStudentMapper studentMapper;

	@Override
	public String isNeedPatient(String ucsid)
	{
		String isNeed="Y";
		if(StringUtil.isNotBlank(ucsid))
		{
			List<Map<String,String>> dicts=readPatientList();
			if(dicts!=null&&dicts.size()>0)
			{
				String DicItemID=String.valueOf(dicts.get(0).get("DicItemID"));
				if(StringUtil.isNotBlank(DicItemID))
				{
					List<Map<String,String>> depts=readPatientDeptList(DicItemID,ucsid);
					if(depts!=null&&depts.size()>0)
					{
						isNeed="N";
					}
				}
			}
		}
		return isNeed;
	}
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
	public Map<String, String> getOutSkill(String doctorFlow) {
		return teacherMapper.getOutSkill(doctorFlow);
	}

	@Override
	public List<Map<String, String>> getSkillList(String skillName, Integer pageIndex, Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		return teacherMapper.getSkillList(skillName,start,end);
	}

	@Override
	public Map<String, String> getCycleInfo(String doctorFlow) {
		return teacherMapper.getCycleInfo(doctorFlow);
	}

	@Override
	public List<Map<String, String>> getSkillItems(String skillFlow, String isMain) {
		return teacherMapper.getSkillItems(skillFlow,isMain);
	}

	@Override
	public Map<String, String> getSkillScorelMap(String skillId) {
		if(StringUtil.isNotBlank(skillId)) {
			List<Map<String, String>> scoreList=teacherMapper.getSkillItemScores(skillId);
			if(scoreList!=null)
			{
				Map<String, String> scoreMap=new HashMap<>();
				for(Map<String, String> s:scoreList)
				{
					scoreMap.put(s.get("TItemFlow"),s.get("Score"));
				}
				return scoreMap;
			}
		}
		return null;
	}

	@Override
	public void updateSkillScore(String skill_id, String doctorFlow, String skillFlow, List<Map<String, String>> items, Map<String, List<Map<String, String>>> itemsMap, HttpServletRequest request) {
		Map<String, String> params= getParam(request.getParameterMap());
		teacherMapper.updateOutSkill(params.get("Skill_PatientName"),params.get("Skill_No"),
				params.get("Skill_Score"),params.get("Skill_Time"),skill_id,params.get("Skill_ExaminerOne"),params.get("Skill_ExaminerTwo"));
		Map<String, String> out=teacherMapper.getOutSkillById(skill_id);
		String fileFlow=params.get("fileFlow");
		String attachID=String.valueOf(out.get("Skill_AttachID"));
		if(StringUtil.isBlank(attachID)||"0".equals(attachID)||"null".equals(attachID))
		{
			attachID=teacherMapper.getMaxAttachId();
		}
		List<String> fileFlows=null;
		if(StringUtil.isNotBlank(fileFlow))
			fileFlows= Arrays.asList(fileFlow.split(","));
		//删除不在本次fileFlow中的附件。
		teacherMapper.deleteFileNotInFlows(attachID,fileFlows);
		if(fileFlows!=null&&fileFlows.size()>0) {
			teacherMapper.setFileGroupIdByFlows(attachID, fileFlows);
			teacherMapper.updateOutSkillAttachId(attachID,skill_id);
		}

		if(items!=null&&itemsMap!=null)
		{
			for(Map<String,String> item:items)
			{
				List<Map<String,String>> subItems=itemsMap.get(item.get("TItemFlow") );
				if(subItems!=null)
				{
					for(Map<String,String> sub:subItems) {
						String TItemFlow=sub.get("TItemFlow");
						String score=params.get(TItemFlow);
						teacherMapper.updateItemScore(skill_id,TItemFlow,score);
					}
				}
			}
		}
	}

	@Override
	public void insertSkillScore(String doctorFlow, String skillId, String skillFlow, Map<String, String> skill, List<Map<String, String>> items, Map<String, List<Map<String, String>>> itemsMap, HttpServletRequest request) {
		Map<String, String> params= getParam(request.getParameterMap());

		teacherMapper.insertOutSkill(params.get("Skill_PatientName"),skill.get("Skill_Name"),params.get("Skill_No"),
				params.get("Skill_Score"),params.get("Skill_Time"),doctorFlow,skillFlow,skillId,skill.get("Skill_ExaminerOne"),skill.get("Skill_ExaminerTwo"));
		Map<String, String> out=teacherMapper.getOutSkillById(skillId);
		String fileFlow=params.get("fileFlow");
		String attachID=String.valueOf(out.get("Skill_AttachID"));
		if(StringUtil.isBlank(attachID)||"0".equals(attachID)||"null".equals(attachID))
		{
			attachID=teacherMapper.getMaxAttachId();
		}
		List<String> fileFlows=null;
		if(StringUtil.isNotBlank(fileFlow))
			fileFlows= Arrays.asList(fileFlow.split(","));
		//删除不在本次fileFlow中的附件。
		teacherMapper.deleteFileNotInFlows(attachID,fileFlows);
		if(fileFlows!=null&&fileFlows.size()>0) {
			teacherMapper.setFileGroupIdByFlows(attachID, fileFlows);
			teacherMapper.updateOutSkillAttachId(attachID,skillId);
		}
		if(items!=null&&itemsMap!=null)
		{
			for(Map<String,String> item:items)
			{
				List<Map<String,String>> subItems=itemsMap.get(item.get("TItemFlow") );
				if(subItems!=null)
				{
					for(Map<String,String> sub:subItems) {
						String TItemFlow=sub.get("TItemFlow");
						String score=params.get(TItemFlow);
						teacherMapper.insertItemScore(skillId,TItemFlow,score);
					}
				}
			}
		}
	}

	@Override
	public Map<String, String> getSkill(String skillFlow) {
		return teacherMapper.getSkill(skillFlow);
	}

	@Override
	public Map<String, String> getOutPatient(String doctorFlow) {
		return teacherMapper.getOutPatient(doctorFlow);
	}

	@Override
	public Map<String, String> getPatientScorelMap(String patient_id) {

		if(StringUtil.isNotBlank(patient_id)) {
			List<Map<String, String>> scoreList=teacherMapper.getPatientItemScores(patient_id);
			if(scoreList!=null)
			{
				Map<String, String> scoreMap=new HashMap<>();
				for(Map<String, String> s:scoreList)
				{
					scoreMap.put("score"+String.valueOf(s.get("ScoreIndex")),s.get("Score")==null?"":String.valueOf(s.get("Score")));
				}
				return scoreMap;
			}
		}
		return null;
	}

	@Override
	public void updatePatientScore(String patient_id, String doctorFlow, HttpServletRequest request) {
		Map<String, String> params= getParam(request.getParameterMap());
		teacherMapper.updateOutPatient(params.get("Patient_Name"),params.get("Patient_No"),
				params.get("Patient_Score"),params.get("Patient_Remark"),patient_id,params.get("Patient_ExaminerOne"),params.get("Patient_ExaminerTwo"));
		Map<String, String> out=teacherMapper.getOutPatientById(patient_id);
		String fileFlow=params.get("fileFlow");
		String attachID=String.valueOf(out.get("Patient_AttachID"));
		if(StringUtil.isBlank(attachID)||"0".equals(attachID)||"null".equals(attachID))
		{
			attachID=teacherMapper.getMaxAttachId();
		}
		List<String> fileFlows=null;
		if(StringUtil.isNotBlank(fileFlow))
			fileFlows= Arrays.asList(fileFlow.split(","));
		//删除不在本次fileFlow中的附件。
		teacherMapper.deleteFileNotInFlows(attachID,fileFlows);
		if(fileFlows!=null&&fileFlows.size()>0) {
			teacherMapper.setFileGroupIdByFlows(attachID, fileFlows);
			teacherMapper.updateOutPatientAttachId(attachID,patient_id);
		}
		for(String key :params.keySet())
		{
			if(key.indexOf("score")==0)
			{
				String index=key.substring(5);
				teacherMapper.updatePatientItemScore(patient_id,index,params.get(key));
			}
		}
	}

	@Override
	public void insertPatientScore(String doctorFlow, String patientId, String patientTypeId, HttpServletRequest request, String userFlow) {
		Map<String, String> params= getParam(request.getParameterMap());
		String patientTypeName=patientTypeId.equals("1")?"门急诊接诊评分表":"病房接诊评分表";
		teacherMapper.insertOutPatient(params.get("Patient_Name"),params.get("Patient_No"),
				params.get("Patient_Score"),params.get("Patient_Remark"),doctorFlow,
				userFlow, DateUtil.getCurrDateTime2(),patientTypeId,patientTypeName,patientId,params.get("Patient_ExaminerOne"),params.get("Patient_ExaminerTwo"));

		Map<String, String> out=teacherMapper.getOutPatientById(patientId);
		String fileFlow=params.get("fileFlow");
		String attachID=String.valueOf(out.get("Patient_AttachID"));
		if(StringUtil.isBlank(attachID)||"0".equals(attachID)||"null".equals(attachID))
		{
			attachID=teacherMapper.getMaxAttachId();
		}
		List<String> fileFlows=null;
		if(StringUtil.isNotBlank(fileFlow))
			fileFlows= Arrays.asList(fileFlow.split(","));
		//删除不在本次fileFlow中的附件。
		teacherMapper.deleteFileNotInFlows(attachID,fileFlows);
		if(fileFlows!=null&&fileFlows.size()>0) {
			teacherMapper.setFileGroupIdByFlows(attachID, fileFlows);
			teacherMapper.updateOutPatientAttachId(attachID,patientId);
		}
		for(String key :params.keySet())
		{
			if(key.indexOf("score")==0)
			{
				String index=key.substring(5);
				teacherMapper.insertPatientItemScore(patientId,index,params.get(key));
			}
		}
	}

	@Override
	public Map<String, String> getOutSecBrief(String doctorFlow) {
		return teacherMapper.getOutSecBrief(doctorFlow);
	}

	@Override
	public int updateOutSecBrief(String doctorFlow, String theoryScore) {
		return teacherMapper.updateOutSecBrief(doctorFlow,theoryScore);
	}

	@Override
	public Map<String, String> readAfterEva(String doctorFlow) {
		return teacherMapper.readAfterEva( doctorFlow);
	}

	@Override
	public Map<String, String> getCycleOutInfo(String doctorFlow) {
		return teacherMapper.getCycleOutInfo( doctorFlow);
	}

	@Override
	public int getJxhd(String doctorFlow) {
		return teacherMapper.getJxhd(doctorFlow);
	}

	@Override
	public int updateAfterEvaluation(String id, String doctorFlow, Map<String, String> tea, HttpServletRequest request) {
		Map<String, String> params= getParam(request.getParameterMap());
		params.put("ID",id);
		params.put("UCSID",doctorFlow);
		params.put("TecID",tea.get("userFlow"));
		params.put("TecDate",DateUtil.getCurrDateTime2());
		return teacherMapper.updateAfterEvaluation(params)+
				teacherMapper.updateOutSecBriefInfo(doctorFlow,tea.get("userFlow"));
	}

	@Override
	public int insertAfterEvaluation(String doctorFlow, Map<String, String> tea, HttpServletRequest request) {
		Map<String, String> params= getParam(request.getParameterMap());
		params.put("ID",PkUtil.getUUID());
		params.put("UCSID",doctorFlow);
		params.put("VerifyState","1");
		params.put("ProfesserID","0");
		params.put("TheoryScore",params.get("Score"));
		params.put("TecID",tea.get("userFlow"));
		params.put("TecDate",DateUtil.getCurrDateTime2());
		params.put("ProfesserDate",DateUtil.getCurrDateTime2());

		return teacherMapper.insertAfterEvaluation(params)+
				teacherMapper.updateOutSecBriefInfo(doctorFlow,tea.get("userFlow"));
	}

	@Override
	public List<Map<String, String>> getFileList(String attachGroupID ) {
		return teacherMapper.getFileList(attachGroupID);
	}

	@Override
	public int delFile(String fileFlow) {
		return teacherMapper.delFile(fileFlow);
	}

	@Override
	public Map<String, String> readFile(String fileFlow) {
		return teacherMapper.readFile(fileFlow);
	}

	@Value("#{configProperties['hzyy.upload.base.dir']}")
	public  String baseDir;
	@Value("#{configProperties['hzyy.upload.dir']}")
	public  String uploadDir;
	@Override
	public int saveFile(FileForm form, Map<String, Object> param) {
		String filePath = baseDir + "/" ;
		MultipartFile uploadFile = form.getUploadFile();
		String fileUrl= (String) param.get("fileUrl");
		String dirUrl =	filePath +=fileUrl;
		//System.out.println("上传保存路径："+dirUrl);
		File dir = new File(dirUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(dirUrl);
		try {
			uploadFile.transferTo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int c= teacherMapper.saveFile(param);
		if(c==0)
		{
			return -1;
		}
		return c;
	}

	@Override
	public String getNewFileFlow() {
		return teacherMapper.getNewFileFlow();
	}

	@Override
	public List<Map<String, String>> readPatientList() {
		return teacherMapper.readPatientList();
	}

	@Override
	public List<Map<String, String>> readPatientDeptList(String dicItemID, String ucsid) {
		return teacherMapper.readPatientDeptList(dicItemID,ucsid);
	}

	private Map<String, String> getParam(Map<String, String[]> parameterMap) {
		Map<String, String> params=new HashMap<>();
		if(parameterMap!=null) {
			for (String key : parameterMap.keySet())
			{
				String[] vs = parameterMap.get(key);

				String vss = "";
				if(vs!=null && vs.length>0){
					vss = vs[0];
				}
				params.put(key,vss);
			}
		}
		return params;

	}

}
