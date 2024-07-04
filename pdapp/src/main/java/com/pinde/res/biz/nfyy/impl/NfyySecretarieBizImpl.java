package com.pinde.res.biz.nfyy.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.nfyy.INfyyAppBiz;
import com.pinde.res.biz.nfyy.INfyySecretarieBiz;
import com.pinde.res.biz.nfyy.INfyyStudentBiz;
import com.pinde.res.dao.nfyy.ext.SecretaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class NfyySecretarieBizImpl implements INfyySecretarieBiz {
	@Autowired
	private INfyyAppBiz nfyyAppBiz;
	@Autowired
	private INfyyStudentBiz nfyyBiz;
	@Autowired
	private SecretaryMapper secretaryMapper;

	@Override
	public Map<String, Object> InProcessCount(Map<String, Object> param) {
		return secretaryMapper.InProcessCount(param);
	}

	@Override
	public Map<String, Object> getAuditData(String ucsid) {
		return secretaryMapper.getAuditData(ucsid);
	}

	@Override
	public List<String> getHosSecId(String userFlow) {
		return secretaryMapper.getHosSecId(userFlow);
	}

	@Override
	public Map<String, Object> OutProcessCount(String userFlow) {
		List<Map<String, Object>> maps= secretaryMapper.OutProcessCount(userFlow);
		Map<String, Object> countMap=new HashMap<>();
		if(maps!=null)
		{
			for(Map<String, Object> m:maps)
			{
				countMap.put(String.valueOf(m.get("RoleId")),m.get("UserNum"));
			}
		}
		return countMap;
	}

	@Override
	public Map<String, Object> activitsDate(String ucsid) {
		List<Map<String, Object>> maps= secretaryMapper.activitsDate(ucsid);
		Map<String, Object> countMap=new HashMap<>();
		if(maps!=null)
		{
			for(Map<String, Object> m:maps)
			{
				countMap.put(String.valueOf(m.get("DicItem")),m.get("num"));
			}
		}
		return countMap;
	}

	@Override
	public Map<String, Object> cycleData(String cySecID, String userID) {
		return secretaryMapper.cycleData(cySecID,userID);
	}

	@Override
	public List<Map<String, Object>> getDayEvalItems() {
		return secretaryMapper.getDayEvalItems();
	}

	@Override
	public List<Map<String, String>> getDopsStuRoles() {
		return secretaryMapper.getDopsStuRoles();
	}

	@Override
	public String getSecName(String ucsid) {
		return secretaryMapper.getSecName(ucsid);
	}

	@Override
	public List<Map<String, Object>> getmbs(String type) {
		return secretaryMapper.getmbs(type);
	}
	@Override
	public List<Map<String, Object>> getmbEvals(String type, Object examItemID) {
		return secretaryMapper.getmbEvals(type,examItemID);
	}

	@Override
	public List<Map<String, Object>> getDayEvalResults(String ucsid, String type) {
		return secretaryMapper.getDayEvalResults(ucsid,type);
	}

	@Override
	public Map<String,Object> getDopsData(String ucsid) {
		return secretaryMapper.getDopsData(ucsid);
	}
	@Override
	public Map<String,Object> getMiniData(String ucsid) {
		return secretaryMapper.getMiniData(ucsid);
	}

	@Override
	public String getIsAssess(String ucsid) {
		return secretaryMapper.getIsAssess(ucsid);
	}

	@Override
	public String getDopsScore(String ucsid) {
		return secretaryMapper.getDopsScore(ucsid);
	}


	@Override
	public String getMiniScore(String ucsid) {
		return secretaryMapper.getMiniScore(ucsid);
	}

	@Override
	public Map<String, Object> getStuInitData(String ucsid) {
		return secretaryMapper.getStuInitData(ucsid);
	}
	@Override
	public List<Map<String, Object>> getZhEval(String ucsid, String type) {
		return secretaryMapper.getZhEval(ucsid,type);
	}
	@Override
	public List<Map<String, Object>> getMbEvalResults(String type, String ucsid) {
		return secretaryMapper.getMbevalResults(type,ucsid);
	}

	@Override
	public List<Map<String, Object>> getInProcessStuList(Map<String, Object> param, Integer pageIndex, Integer pageSize) {

		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		param.put("start",start);
		param.put("end",end);
		return secretaryMapper.getInProcessStuList(param);
	}
	@Override
	public List<Map<String, Object>> getOutProcessStuList(Map<String, Object> param, Integer pageIndex, Integer pageSize) {

		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		param.put("start",start);
		param.put("end",end);
		return secretaryMapper.getOutProcessStuList(param);
	}
	@Override
	public List<Map<String, Object>> getTeachPlanList(Map<String, Object> param, Integer pageIndex, Integer pageSize) {

		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		param.put("start",start);
		param.put("end",end);
		return secretaryMapper.getTeachPlanList(param);
	}
	@Override
	public List<Map<String, Object>> getInProcessFileList(Map<String, Object> param, Integer pageIndex, Integer pageSize) {

		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		param.put("start",start);
		param.put("end",end);
		return secretaryMapper.getInProcessFileList(param);
	}

	@Override
	public Map<String, Object> inProcessFileDetail(String readSecDocumentId) {
		return secretaryMapper.inProcessFileDetail(readSecDocumentId);
	}

	@Override
	public Map<String, Object> zhuPeiFileDetail(String cIFlow) {
		return secretaryMapper.zhuPeiFileDetail(cIFlow);
	}

	@Override
	public Map<String, String> deptOrgCost(String userFlow, String year) {
		return secretaryMapper.deptOrgCost(userFlow,year);
	}

	@Override
	public Map<String, Object> getOutInManageDetail(String ucsid) {
		return secretaryMapper.getOutInManageDetail(ucsid);
	}
	@Override
	public Map<String, Object> getAppraisalData(String ucsid) {
		return secretaryMapper.getAppraisalData(ucsid);
	}
	@Override
	public Map<String, Object> getDayEvalData(String ucsid) {
		return secretaryMapper.getDayEvalData(ucsid);
	}
	@Override
	public Map<String, Object> getActivitydetail(String caDisID) {
		return secretaryMapper.getActivitydetail(caDisID);
	}
	@Override
	public Map<String, Object> getTeachPlanDetail(String caDisID) {
		return secretaryMapper.getTeachPlanDetail(caDisID);
	}


	@Override
	public List<Map<String, Object>> activityStuList(Map<String, Object> param, Integer pageIndex, Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		param.put("start",start);
		param.put("end",end);
		return secretaryMapper.activityStuList(param);
	}
	@Override
	public List<Map<String, Object>> getActivityList(Map<String, Object> param, Integer pageIndex, Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		param.put("start",start);
		param.put("end",end);
		return secretaryMapper.getActivityList(param);
	}
	@Override
	public List<Map<String, Object>> getThisTeachers(String userFlow, Integer pageIndex, Integer pageSize, String tecID) {
		Map<String, Object> param=new HashMap<>();
		param.put("userFlow",userFlow);
		param.put("tecID",tecID);
		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		param.put("start",start);
		param.put("end",end);
		return secretaryMapper.getThisTeachers(param);
	}
	@Override
	public List<Map<String, Object>> getNotThisTeachers(String userFlow, Integer pageIndex, Integer pageSize, String tecID) {
		Map<String, Object> param=new HashMap<>();
		param.put("userFlow",userFlow);
		param.put("tecID",tecID);
		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		param.put("start",start);
		param.put("end",end);
		return secretaryMapper.getNotThisTeachers(param);
	}
	@Override
	public List<Map<String, Object>> getDoctors(String userFlow, Integer pageIndex, Integer pageSize) {
		Map<String, Object> param=new HashMap<>();
		param.put("userFlow",userFlow);
		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		param.put("start",start);
		param.put("end",end);
		return secretaryMapper.getDoctors(param);
	}

	@Override
	public int updateTeachPlan(Map<String, String> param) {
	 return	secretaryMapper.updateTeachPlan(param);
	}

	@Override
	public int addTeachPlan(Map<String, String> param) {
		return secretaryMapper.addTeachPlan(param);
	}
	@Override
	public int saveActivityEval(Map<String, String> param) {
		return secretaryMapper.saveActivityEval(param);
	}

	@Override
	public Map<String, String> docHosSecID(String tecID) {
		return secretaryMapper.docHosSecID(tecID);
	}

	@Override
	public String saveInProcess(Map<String, Object> param) {
		String userFlow= (String) param.get("userFlow");
		String UCSID= (String)param.get("UCSID");
		String RUserID= (String)param.get("RUserID");
		String RStartTime= (String)param.get("RStartTime");
		String REndTime= (String)param.get("REndTime");
		String RCySecID= (String)param.get("RCySecID");
		String RHosCySecID= (String)param.get("RHosCySecID");
		String HosSecID= (String)param.get("HosSecID");
		String UserTecID= (String)param.get("UserTecID");
		String SectionManagerID= (String)param.get("SectionManagerID");
		String CreateDate= DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm:ss");
		param.put("CreateDate",CreateDate);
		//判断是否有历史数据
		Integer cycleNum=secretaryMapper.getCycleNum(UCSID);
		if(cycleNum!=null&&cycleNum>0)
		{
			secretaryMapper.updateInProcess(param);
		}else{
			secretaryMapper.insertInProcess(param);
		}
		//查询是否有入科教育记录
		Integer inProcessFileNum=secretaryMapper.getInProcessFileNum(UCSID);
		if(!(inProcessFileNum!=null&&inProcessFileNum>0))
		{
			secretaryMapper.insertInProcessFile(param);
		}
		//查询是否有带教老师记录
		Integer hasTea=secretaryMapper.hasTea(RUserID,HosSecID,UCSID);
		if(hasTea!=null&&hasTea>0)
		{
			//更新带教老师信息
			secretaryMapper.updateTeaInfo(param);
		}else{
			//添加带教 老师信息
			secretaryMapper.addTeaInfo(param);
			//添加带教老师记录
			//secretaryMapper.addTeaRecord(param);
		}
		//查询是否有科主任
		Integer hasHead=secretaryMapper.hasHead(UCSID);
		if(hasHead!=null&&hasHead>0)
		{
			//更新科主任信息
			secretaryMapper.updateHeadInfo(param);
		}else{
			//添加科主任信息
			secretaryMapper.addHeadInfo(param);
		}
//		//根据轮转获取专业、科室信息
//		Map<String,Object> speDeptInfo=secretaryMapper.getSpeDeptInfo(RCySecID);
//		if(speDeptInfo==null||StringUtil.isBlank(String.valueOf(speDeptInfo.get("SpID")))||StringUtil.isBlank(String.valueOf(speDeptInfo.get("SectionID"))))
//		{
//			return "获取专业、科室信息失败！";
//		}
//		//查询（对接）出科考试试卷
//		Map<String,Object> examTestInfo=secretaryMapper.getExamTestInfo(speDeptInfo);
//		if(examTestInfo==null)
//		{
//			return "查询出科考试试卷失败！";
//		}

		//查询是否添加试卷信息
		Integer hasExam=secretaryMapper.hasExam(UCSID);
		if(!(hasExam!=null&&hasExam>0))
		{
			//添加科主任信息
			secretaryMapper.addExamInfo(param);
		}
		return "";
	}

	@Override
	public int delTeachPlan(String caDisID) {
		return secretaryMapper.delTeachPlan(caDisID);
	}

	@Override
	public List<Map<String, Object>> getActivtyDicts() {
		return secretaryMapper.getActivtyDicts();
	}

	@Override
	public List<Map<String, Object>> zhuPeiInfoList(Map<String, Object> param, Integer pageIndex, Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		param.put("start",start);
		param.put("end",end);
		return secretaryMapper.zhuPeiInfoList(param);
	}
	@Override
	public List<Map<String, Object>> internshipList(Map<String, Object> param, Integer pageIndex, Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		param.put("start",start);
		param.put("end",end);
		return secretaryMapper.internshipList(param);
	}

	@Override
	public List<Map<String, Object>> getFillInfos(String ucsid) {
		return secretaryMapper.getFillInfos(ucsid);
	}

	@Override
	public Map<String, Object> InProcessCount2(Map<String, Object> param) {
		List<Map<String, Object>> maps= secretaryMapper.InProcessCount2(param);
		Map<String, Object> countMap=new HashMap<>();
		if(maps!=null)
		{
			for(Map<String, Object> m:maps)
			{
				countMap.put(String.valueOf(m.get("RoleId")),m.get("UserCount"));
			}
		}
		return countMap;
	}

	@Override
	public String saveAppraisal(Map<String, Object> param) {
		List<Map<String, String>> items = (List<Map<String, String>>) param.get("items");
		String TecAppraise= (String) param.get("TecAppraise");
		String ExamInfoDF= (String) param.get("ExamInfoDf");
		if(items!=null&&items.size()>0)
		{
			int i=1;
			Map<String,Integer> typeMap=new HashMap<>();
			for(Map<String, String> m:items)
			{
				String id=m.get("id");
				String value=String.valueOf(m.get("value"));
				if(StringUtil.isBlank(id))
				{
					return "第"+i+"项评分标识符为空";
				}
				if(StringUtil.isBlank(value))
				{
					return "第"+i+"项评分为空";
				}
				String[] ids=id.split(",");
				if(ids.length<2)
				{
					return "第"+i+"项评分标识符出错";
				}
				String reqItemId=ids[0];
				String examInfoID=ids[1];
				m.put("reqItemId",reqItemId);
				m.put("examInfoID",examInfoID);
				Integer typeSum=typeMap.get(examInfoID);
				if(typeSum==null) typeSum=0;
				typeSum+=Integer.valueOf(value);
				typeMap.put(examInfoID,typeSum);
			}
			for(Map<String, String> m:items)
			{
				secretaryMapper.updateDayEvalItem(m);
			}
			for(String key:typeMap.keySet())
			{
				secretaryMapper.updateDayEvalType(ExamInfoDF,key,TecAppraise);
			}
			//保存教学秘书审核意见
			secretaryMapper.updateSecAppraise(param);
			return "";
		}else{
			return "评价数据为空";
		}
	}
	@Override
	public String saveDayEval(List<Map<String, String>> items, String examInfoDf) {
		if(items!=null&&items.size()>0)
		{
			int i=1;
			Map<String,Integer> typeMap=new HashMap<>();
			for(Map<String, String> m:items)
			{
				String id=m.get("id");
				String value=String.valueOf(m.get("value"));
				if(StringUtil.isBlank(id))
				{
					return "第"+i+"项评分标识符为空";
				}
				if(StringUtil.isBlank(value))
				{
					return "第"+i+"项评分为空";
				}
				String[] ids=id.split(",");
				if(ids.length<2)
				{
					return "第"+i+"项评分标识符出错";
				}
				String reqItemId=ids[0];
				String examInfoID=ids[1];
				m.put("reqItemId",reqItemId);
				m.put("examInfoID",examInfoID);
				Integer typeSum=typeMap.get(examInfoID);
				if(typeSum==null) typeSum=0;
				typeSum+=Integer.valueOf(value);
				typeMap.put(examInfoID,typeSum);
			}
			for(Map<String, String> m:items)
			{
				secretaryMapper.updateDayEvalItem(m);
			}
			for(String key:typeMap.keySet())
			{
				secretaryMapper.updateDayEvalType(examInfoDf,key,"");
			}

			return "";
		}else{
			return "评价数据为空";
		}
	}

	@Override
	public String saveAfterEvaluation1(Map<String, String> param) {
		secretaryMapper.updateAfterEvaluation1(param);
		secretaryMapper.updateAfterSummary1(param);
		return null;
	}

	@Override
	public List<Map<String, Object>> getLeaveList(Map<String, Object> param, Integer pageIndex, Integer pageSize) {

		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		param.put("start",start);
		param.put("end",end);
		return secretaryMapper.getLeaveList(param);
	}

	@Override
	public Map<String, String> readUserInfo(String userFlow) {
		return secretaryMapper.readUserInfo(userFlow);
	}

	@Override
	public Map<String, Object> getAfterSummData(String ucsid) {
		return secretaryMapper.getAfterSummData(ucsid);
	}

	@Override
	public Map<String, Object> getCycleDetail(String ucsid) {
		return secretaryMapper.getCycleDetail(ucsid);
	}

	@Override
	public List<Map<String, Object>> getTeacherList(Map<String, Object> param) {
		return secretaryMapper.getTeacherList(param);
	}
	@Override
	public List<Map<String, Object>> getHeadList(Map<String, Object> param) {
		return secretaryMapper.getHeadList(param);
	}
}
