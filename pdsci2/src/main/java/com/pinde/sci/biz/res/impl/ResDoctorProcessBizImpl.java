package com.pinde.sci.biz.res.impl;

import com.pinde.core.model.SysUser;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.NumTrans;
import com.pinde.sci.dao.base.ResDoctorSchProcessEvalMapper;
import com.pinde.sci.dao.base.ResDoctorSchProcessMapper;
import com.pinde.sci.dao.res.ResDoctorSchProcessExtMapper;
import com.pinde.sci.form.jsres.TeacherWorkForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.ResDoctorSchProcessExample.Criteria;
import com.pinde.sci.model.res.SchProcessExt;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResDoctorProcessBizImpl implements IResDoctorProcessBiz {

	@Autowired
	private ResDoctorSchProcessMapper  resDoctorProcessMapper;
	@Autowired
	private ResDoctorSchProcessEvalMapper evalMapper;
	@Autowired
	private ResDoctorSchProcessExtMapper resDoctorProcessExtMapper;
	@Autowired
	private IResDoctorProcessBiz doctorProcessBiz;
	
	@Override
	public List<ResDoctorSchProcess> searchByResultFlows(List<String> schResultFlows) {
		List<ResDoctorSchProcess> processList = null;
		if(schResultFlows!=null&&!schResultFlows.isEmpty()){
			ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchResultFlowIn(schResultFlows);
			processList = this.resDoctorProcessMapper.selectByExample(example);
		}
		return processList;
	}
	@Override
	public int edit(ResDoctorSchProcess process) {
		if(process!=null){
			if(StringUtil.isNotBlank(process.getProcessFlow())){//修改
				GeneralMethod.setRecordInfo(process, false);
				return this.resDoctorProcessMapper.updateByPrimaryKeySelective(process);
			}else{//新增
				process.setProcessFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(process, true);
				return this.resDoctorProcessMapper.insertSelective(process);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}
	@Override
	public ResDoctorSchProcess searchByResultFlow(String resultFlow) {
		ResDoctorSchProcess process = null;
		if(StringUtil.isNotBlank(resultFlow)){
			ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchResultFlowEqualTo(resultFlow);
			List<ResDoctorSchProcess> processList = this.resDoctorProcessMapper.selectByExample(example);
			if(processList!=null&&!processList.isEmpty()){
				process = processList.get(0);
			}
		}
		return process;
	}

	@Override
	public List<ResDoctorSchProcess> searchByResultFlowPartitionList(List<List<String>> resultFlowListList) {
		if(CollectionUtils.isEmpty(resultFlowListList)) {
			return Collections.emptyList();
		}
		return resDoctorProcessMapper.searchByResultFlowPartitionList(resultFlowListList);
	}

	@Override
	public List<ResDoctorSchProcess> readResDoctorSchProcessByExample(ResDoctorSchProcessExample example) {
		return resDoctorProcessMapper.selectByExample(example);
	}

	@Override
	public List<ResDoctorSchProcess> searchCurrProcessByUserFlows(List<String> userFlows){
		ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andIsCurrentFlagEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
		.andUserFlowIn(userFlows);
		return resDoctorProcessMapper.selectByExample(example);
	}
	
	@Override
	public ResDoctorSchProcess read(String processFlow) {
		ResDoctorSchProcess process = null;
		if(StringUtil.isNotBlank(processFlow)){
			process = this.resDoctorProcessMapper.selectByPrimaryKey(processFlow);
		}
		return process;
	}
	
	@Override
	public List<ResDoctorSchProcess> searchDoctorProcess(String isOpen,String doctorName,ResDoctorSchProcess doctorProcess){

		return resDoctorProcessExtMapper.searchDoctorProcess(isOpen,doctorName,doctorProcess);
	}

	@Override
	public List<ResDoctorSchProcess> searchDoctorProcess(Map<String, Object> map) {
		return resDoctorProcessExtMapper.searchDoctorProcess2(map);
	}

	@Override
	public ResDoctorSchProcess searchProcessByRec(String doctorFlow,String schDeptFlow){
		ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(doctorFlow).andSchDeptFlowEqualTo(schDeptFlow);
		List<ResDoctorSchProcess> processList = resDoctorProcessMapper.selectByExample(example);
		ResDoctorSchProcess process = null;
		if(processList!=null && processList.size()>0){
			process = processList.get(0);
		}
		return process;
	}
	
	@Override
	public List<ResDoctorSchProcess> searchProcessByOrg(String orgFlow){
		ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow).andIsCurrentFlagEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		example.setOrderByClause("START_DATE DESC");
		return resDoctorProcessMapper.selectByExample(example);
	}
	
	@Override
	public List<ResDoctorSchProcess> searchProcessByDoctor(String doctorFlow){
		ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(doctorFlow);
		example.setOrderByClause("sch_start_date");
		return resDoctorProcessMapper.selectByExample(example);
	}

	@Override
	public List<ResDoctorSchProcess> searchProcessByDoctor(ResDoctorSchProcess resDoctorSchProcess) {
		ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        ResDoctorSchProcessExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(resDoctorSchProcess!=null){
			if(StringUtil.isNotBlank(resDoctorSchProcess.getUserFlow())){
				criteria.andUserFlowEqualTo(resDoctorSchProcess.getUserFlow());
			}
			if(StringUtil.isNotBlank(resDoctorSchProcess.getSchFlag())){
				criteria.andSchFlagEqualTo(resDoctorSchProcess.getSchFlag());
			}
			if(StringUtil.isNotBlank(resDoctorSchProcess.getSchStartDate())){
				criteria.andSchStartDateLessThanOrEqualTo(resDoctorSchProcess.getSchStartDate());
			}
		}
		example.setOrderByClause("sch_start_date");
		return resDoctorProcessMapper.selectByExample(example);
	}

	@Override
	public List<ResDoctorSchProcess> searchProcessByDoctor(ResDoctor doctor,ResDoctorSchProcess process,Map<String,String> roleFlagMap){
		return resDoctorProcessExtMapper.searchProcessByDoctor(doctor, process,roleFlagMap);
	}

	@Override
	public List<ResDoctorSchProcess> searchProcessByDoctorNew(String isOpen,ResDoctor doctor,ResDoctorSchProcess process,Map<String,String> roleFlagMap,String basicPractice){
		//basicPractice用于区分是否是中医全科的基层实践
		return resDoctorProcessExtMapper.searchProcessByDoctorNew(isOpen,doctor, process,roleFlagMap,basicPractice);
	}

	@Override
	public List<ResDoctorSchProcess> searchProcessByDoctorNew(Map<String, Object> map) {
		return resDoctorProcessExtMapper.searchProcessByMp(map);
	}

	@Override
	public List<ResDoctor> searchProcessByDoctorNew2(String flag, String isOpen, ResDoctor doctor, ResDoctorSchProcess process, Map<String, String> roleFlagMap, List<String> doctorTypeIdList){
		return resDoctorProcessExtMapper.searchProcessByDoctorNew2(flag,isOpen,doctor, process,roleFlagMap,doctorTypeIdList);
	}

	@Override
	public ResDoctorSchProcess searchCurrDept(SysUser sysUser){
		ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(sysUser!=null){
			if(StringUtil.isNotBlank(sysUser.getUserFlow())){
				criteria.andUserFlowEqualTo(sysUser.getUserFlow());
			}
		}
        criteria.andIsCurrentFlagEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		List<ResDoctorSchProcess> processes=this.resDoctorProcessMapper.selectByExample(example);
		if(processes!=null && !processes.isEmpty()){
			return processes.get(0);
		}
		return null;
	}
	
	@Override
	public List<String> searchTeachDept(String userFlow){
		return resDoctorProcessExtMapper.searchTeachDept(userFlow);
	}

	@Override
	public List<SchProcessExt> searchProcessAndResultByDoctorFlow(String doctorFlow, String deptFlow) {
		return resDoctorProcessExtMapper.searchProcessAndResultByDoctorFlow(doctorFlow,deptFlow);
	}

	@Override
	public List<String> searchRotationDoctor(List<String> doctorFlows){
		return resDoctorProcessExtMapper.searchRotationDoctor(doctorFlows);
	}
	
	@Override
	public List<ResDoctorSchProcess> searchCurrentProcessByUserFlow(String isOpen,String userFlow,String isCurrentFlag){
		return resDoctorProcessExtMapper.searchCurrentProcessByUserFlow(isOpen,userFlow,isCurrentFlag);
	}

	@Override
	public List<ResDoctorSchProcess> searchProcessByUserFlow(Map<String, Object> paramMap) {
		return resDoctorProcessExtMapper.searchProcessByUserFlow(paramMap);
	}

	@Override
	public List<ResDoctorSchProcess> searchProcessByUserSpe(String userFlow,String isCurrentFlag){
		return resDoctorProcessExtMapper.searchProcessByUserSpe(userFlow,isCurrentFlag);
	}
	@Override
	public ResDoctorSchProcess searchProcess(String userFlow,String orgFlow,String groupFlow, String deptId) {
		ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserFlowEqualTo(userFlow).andDeptFlowEqualTo(deptId).andOrgFlowEqualTo(orgFlow).andSchDeptFlowEqualTo(groupFlow);
		List<ResDoctorSchProcess> processes=this.resDoctorProcessMapper.selectByExample(example);
		if(processes!=null && !processes.isEmpty()){
			return processes.get(0);
		}
		return null;
	}
	@Override
	public List<Map<String,String>>  schDoctorSchProcessQuery(
			Map<String, String> map) {
		List<Map<String,String>>  resDoctorSchProcesseList=resDoctorProcessExtMapper.schDoctorSchProcessQuery(map);
		return resDoctorSchProcesseList;
	}
	@Override
	public int resSchDoctorSchProcessDistinctQuery(
			String teacherUserFlow) {
		return resDoctorProcessExtMapper.resSchDoctorSchProcessDistinctQuery(teacherUserFlow);
	}
	@Override
	public int resSchDoctorSchProcessTeacherQuery(
			String teacherUserFlow) {
		return resDoctorProcessExtMapper.resSchDoctorSchProcessTeacherQuery(teacherUserFlow);
	}

	@Override
	public int schDoctorSchProcessDistinctQuery(
			String teacherUserFlow) {
		return resDoctorProcessExtMapper.schDoctorSchProcessDistinctQuery(teacherUserFlow);
	}
	@Override
	public int schDoctorSchProcessDistinctQueryByDate(
			String teacherUserFlow,String startDate,String endDate) {
		return resDoctorProcessExtMapper.schDoctorSchProcessDistinctQueryByDate(teacherUserFlow,startDate,endDate);
	}
	@Override
	public int schDoctorSchProcessTeacherQuery(
			String teacherUserFlow) {
		return resDoctorProcessExtMapper.schDoctorSchProcessTeacherQuery(teacherUserFlow);
	}
	@Override
	public int schDoctorSchProcessWaitingExamineQuery(
			String teacherUserFlow) {
		return resDoctorProcessExtMapper.schDoctorSchProcessWaitingExamineQuery(teacherUserFlow);
	}
	@Override
	public int jsresSchDoctorSchProcessDistinctQuery(
			String teacherUserFlow) {
		return resDoctorProcessExtMapper.jsresSchDoctorSchProcessDistinctQuery(teacherUserFlow);
	}
	@Override
	public int jsresSchDoctorSchProcessDistinctQueryByDate(
			String teacherUserFlow, String startDate, String endDate, List<String> typeList) {
		return resDoctorProcessExtMapper.jsresSchDoctorSchProcessDistinctQueryByDate(teacherUserFlow,startDate,endDate,typeList);
	}
	@Override
	public List<SysUser> jsresTeacherSchDocByDate(
			String teacherUserFlow, String startDate, String endDate, List<String> typeList) {
		return resDoctorProcessExtMapper.jsresTeacherSchDocByDate(teacherUserFlow,startDate,endDate,typeList);
	}
	@Override
	public int jsresSchDoctorSchProcessTeacherQuery(
			String teacherUserFlow) {
		return resDoctorProcessExtMapper.jsresSchDoctorSchProcessTeacherQuery(teacherUserFlow);
	}
	@Override
	public int jsresSchDoctorSchProcessWaitingExamineQuery(
			String teacherUserFlow) {
		return resDoctorProcessExtMapper.jsresSchDoctorSchProcessWaitingExamineQuery(teacherUserFlow);
	}
	@Override
	public List<Map<String,String>>  jsresSchDoctorSchProcessQuery(
			Map<String, Object> map) {
		List<Map<String,String>>  resDoctorSchProcesseList=resDoctorProcessExtMapper.jsresSchDoctorSchProcessQuery(map);
		return resDoctorSchProcesseList;
	}
	@Override
	public List<Map<String, String>> jsresSchDoctorSchProcessHead(
			Map<String, Object> map) {
		List<Map<String, String>>  resDoctorSchProcesseList=resDoctorProcessExtMapper.jsresSchDoctorSchProcessHead(map);
		return resDoctorSchProcesseList;
	}
	@Override
	public List<Map<String, String>> temporaryOutAuditSearch(
			Map<String, Object> map) {
		List<Map<String, String>>  resDoctorSchProcesseList=resDoctorProcessExtMapper.temporaryOutAuditSearch(map);
		return resDoctorSchProcesseList;
	}
	@Override
	public List<Map<String, String>> temporaryOutSearch(
			Map<String, Object> map) {
		List<Map<String, String>>  resDoctorSchProcesseList=resDoctorProcessExtMapper.temporaryOutSearch(map);
		return resDoctorSchProcesseList;
	}
	@Override
	public int temporaryOutAudit(ResDoctorSchProcess proces){
		return resDoctorProcessExtMapper.temporaryOutAudit(proces);
	}
	@Override
	public int schProcessStudentDistinctQuery(
			String deptFlow,String userFlow) {
		return resDoctorProcessExtMapper.schProcessStudentDistinctQuery(deptFlow,userFlow);
	}

	@Override
	public List<Map<String,String>>  schProcessStudentDistinctQuery2(Map<String, Object> map) {
		return resDoctorProcessExtMapper.schProcessStudentDistinctQuery2(map);
	}

	@Override
	public int schProcessStudentQuery(
			String deptFlow,String userFlow) {
		return resDoctorProcessExtMapper.schProcessStudentQuery(deptFlow,userFlow);
	}
	@Override
	public int schProcessComingStudentQuery(
			String deptFlow,String userFlow) {
		return resDoctorProcessExtMapper.schProcessComingStudentQuery(deptFlow,userFlow);
	}
	@Override
	public List<Map<String, String>> schDoctorSchProcessHead(
			Map<String, String> map) {
		List<Map<String, String>>  resDoctorSchProcesseList=resDoctorProcessExtMapper.schDoctorSchProcessHead(map);
		return resDoctorSchProcesseList;
	}
	@Override
	public List<Map<String, String>> processRecShMap(
			Map<String, String> map) {
		List<Map<String, String>>  resschProcesseResrecList=resDoctorProcessExtMapper.processRecShMap(map);
		return resschProcesseResrecList;
	}
	@Override
	public List<Map<String, String>> processRecRecTeacherMap(
			Map<String, String> map) {
		List<Map<String, String>>  resschProcesseResrecList=resDoctorProcessExtMapper.processRecRecTeacherMap(map);
		return resschProcesseResrecList;
	}
	@Override
	public List<Map<String, Object>> searTrainingQuery(String orgFlow,String date) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("orgFlow", orgFlow);
		map.put("date", date);
		map.put("currDate", DateUtil.getCurrDate());
		return resDoctorProcessExtMapper.searTrainingQuery(map);
	}
	@Override
	public List<ResDoctorSchProcess> searchBySchResultFlow(String schResultFlow){
		ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
			if(StringUtil.isNotBlank(schResultFlow)){
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchResultFlowEqualTo(schResultFlow);
			}
		return resDoctorProcessMapper.selectByExample(example);
	}
	@Override
	public List<ResDoctorSchProcess> searchByStandadDeptIdAndGroupFLow(Map<String, String> paramsMap){
		return resDoctorProcessExtMapper.searchByStandadDeptIdAndGroupFLow(paramsMap);
	}

	@Override
	public Map<String, Object> getTeaAuditNumMap(Map<String, Object> map) {
		Map<String, Object> result=new HashMap<>();
		List<Map<String,Object>> list= resDoctorProcessExtMapper.getTeaAuditNumMap(map);
		if(list!=null&&list.size()>0)
		{
			for(Map<String,Object> b:list)
			{
				result.put((String) b.get("key"),b.get("qty"));
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> getTeaNotAuditNumMap(Map<String, Object> map) {
		Map<String, Object> result=new HashMap<>();
		List<Map<String,Object>> list= resDoctorProcessExtMapper.getTeaNotAuditNumMap(map);
		if(list!=null&&list.size()>0)
		{
			for(Map<String,Object> b:list)
			{
				result.put((String) b.get("key"),b.get("qty"));
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> getTeaAllDocNumMap(Map<String, Object> map) {
		Map<String, Object> result=new HashMap<>();
		List<Map<String,Object>> list= resDoctorProcessExtMapper.getTeaAllDocNumMap(map);
		if(list!=null&&list.size()>0)
		{
			for(Map<String,Object> b:list)
			{
				result.put((String) b.get("key"),b.get("qty"));
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> getTeaCurrDocNumMap(Map<String, Object> map) {
		Map<String, Object> result=new HashMap<>();
		List<Map<String,Object>> list= resDoctorProcessExtMapper.getTeaCurrDocNumMap(map);
		if(list!=null&&list.size()>0)
		{
			for(Map<String,Object> b:list)
			{
				result.put((String) b.get("key"),b.get("qty"));
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> getTeaSchDocNumMap(Map<String, Object> map) {
		Map<String, Object> result=new HashMap<>();
		List<Map<String,Object>> list= resDoctorProcessExtMapper.getTeaSchDocNumMap(map);
		if(list!=null&&list.size()>0)
		{
			for(Map<String,Object> b:list)
			{
				result.put((String) b.get("key"),b.get("qty"));
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> getTeaLectureNumMap(Map<String, Object> map) {
		Map<String, Object> result=new HashMap<>();
		List<Map<String,Object>> list= resDoctorProcessExtMapper.getTeaLectureNumMap(map);
		if(list!=null&&list.size()>0)
		{
			for(Map<String,Object> b:list)
			{
				result.put((String) b.get("key"),b.get("qty"));
			}
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> readHeadOrTeaStudents(Map<String, Object> paramMap) {
		return	resDoctorProcessExtMapper.readHeadOrTeaStudents(paramMap);
	}

	@Override
	public void delProcessByResultFlow(String resultFlow) {
		resDoctorProcessExtMapper.delProcessByResultFlow(resultFlow);
	}

	@Override
	public List<SchArrangeResult> searchResultsByUserFlow(Map<String, Object> paramMap) {
		return resDoctorProcessExtMapper.searchResultsByUserFlow(paramMap);
	}

	@Override
	public int checkProcessEval(String processFlow) {
		return resDoctorProcessExtMapper.checkProcessEval(processFlow);
	}

	@Override
	public void saveChooseTea(String teacherUserName, String teacherUserFlow, String resultFlow) {
		ResDoctorSchProcess process=doctorProcessBiz.searchByResultFlow(resultFlow);
		process.setTeacherUserFlow(teacherUserFlow);
		process.setTeacherUserName(teacherUserName);
		resDoctorProcessMapper.updateByPrimaryKeySelective(process);
	}

	@Override
	public List<ResDoctorSchProcess> selectProcessByDoctorNew(Map<String, Object> param) {
		return resDoctorProcessExtMapper.selectProcessByDoctorNew(param);
	}

	@Override
	public List<Map<String, String>> jsresSearchTeacherWorkInfoNew(String startDate, String endDate, List<String> typeList,SysUser sysUser,String orgFlow,String roleFlow,String orderItem,String sortName,String doctorStartDate,String doctorEndDate) {
		Map<String,Object> map=new HashMap<>();
		map.put("startDate",startDate);
		map.put("endDate",endDate);
		map.put("typeList",typeList);
		map.put("sysUser",sysUser);
		map.put("orgFlow",orgFlow);
		map.put("roleFlow",roleFlow);
		map.put("orderItem",orderItem);
		map.put("sortName",sortName);
		map.put("doctorStartDate",doctorStartDate);
		map.put("doctorEndDate",doctorEndDate);
		if(StringUtil.isNotBlank(startDate)&&StringUtil.isNotBlank(endDate)){
			String newStartDate=DateUtil.transDateTime(startDate);
			String newEndDate=DateUtil.transDateTime(endDate);
			map.put("newStartDate",newStartDate.substring(0,16));
			map.put("newEndDate",newEndDate.substring(0,16));
			map.put("startDate1",newStartDate.substring(0,10));
			map.put("endDate1",newEndDate.substring(0,10));
		}
		return resDoctorProcessExtMapper.jsresSearchTeacherWorkInfoNew(map);
	}

	/**
	 * @param teacherWorkForm
	 * @Department：研发部
	 * @Description 高校带教工作量查询
	 * @Author fengxf
	 * @Date 2020/10/12
	 */
	@Override
	public List<Map<String, String>> searchUniversityTeacherWorkInfoList(TeacherWorkForm teacherWorkForm) {
		if(StringUtil.isNotBlank(teacherWorkForm.getOperStartDate()) && StringUtil.isNotBlank(teacherWorkForm.getOperEndDate())){
			String newStartDate = DateUtil.transDateTime(teacherWorkForm.getOperStartDate());
			String newEndDate = DateUtil.transDateTime(teacherWorkForm.getOperEndDate());
			teacherWorkForm.setNewStartDate(newStartDate.substring(0,16));
			teacherWorkForm.setNewEndDate(newEndDate.substring(0,16));
			teacherWorkForm.setStartDate(newStartDate.substring(0,10));
			teacherWorkForm.setEndDate(newEndDate.substring(0,10));
		}
		return resDoctorProcessExtMapper.searchUniversityTeacherWorkInfoList(teacherWorkForm);
	}

	@Override
	public ResDoctorSchProcessEvalWithBLOBs getDoctorProcessEval(String processFlow, String startTime,String endTime) {
		ResDoctorSchProcessEvalExample evalExample=new ResDoctorSchProcessEvalExample();
        evalExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andProcessFlowEqualTo(processFlow).andStartTimeEqualTo(startTime).andEndTimeEqualTo(endTime);
		List<ResDoctorSchProcessEvalWithBLOBs> list=evalMapper.selectByExampleWithBLOBs(evalExample);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<ResDoctorSchProcess> searchByDeptFlow(String deptFlow){
		ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
		if(StringUtil.isNotBlank(deptFlow)){
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDeptFlowEqualTo(deptFlow);
		}
		return resDoctorProcessMapper.selectByExample(example);
	}

	@Override
	public List<Map<String, Object>> searchTeacherWorkload(Map<String, Object> map) {
		if(map==null)	return null;
		return  resDoctorProcessExtMapper.searchTeacherWorkload( map);
	}

	@Override
	public List<Map<String, Object>> schDoctorQuery(String teacherUserFlow, String schDeptFlow,String operEndDate,String operStartDate,List<String> doctorTypeIds) {
		if(StringUtil.isBlank(teacherUserFlow))
		return null;
		return resDoctorProcessExtMapper.schDoctorQuery(teacherUserFlow, schDeptFlow,operEndDate,operStartDate,doctorTypeIds);
	}

	@Override
	public List<Map<String, Object>> chTeacherProcessFlowRec(String teacherUserFlow, String schDeptFlow, String operEndDate, String operStartDate,List<String> doctorTypeIds) {
		if(StringUtil.isBlank(teacherUserFlow))
		return null;
		return resDoctorProcessExtMapper.chTeacherProcessFlowRec(teacherUserFlow, schDeptFlow,operEndDate,operStartDate,doctorTypeIds);
	}

	@Override
	public List<Map<String, Object>> docWorkingSearch(Map<String, Object> parMp) {
		return resDoctorProcessExtMapper.newDocWorkingSearch(parMp);
	}

	@Override
	public List<Map<String, Object>> workList(Map<String, Object> parMp) {
		return resDoctorProcessExtMapper.workList(parMp);
	}

	@Override
	public List<Map<String, Object>> workDetail(Map<String, Object> parMp) {
		return  resDoctorProcessExtMapper.workDetail(parMp);
	}

	@Override
	public int getTeaDeptAuditNum(Map<String, Object> map) {
		return resDoctorProcessExtMapper.getTeaDeptAuditNum(map);
	}

	@Override
	public int getTeaDeptNotAuditNum(Map<String, Object> map) {
		return resDoctorProcessExtMapper.getTeaDeptNotAuditNum(map);
	}

	@Override
	public void exportInfo(HttpServletResponse response, List<SchArrangeResult> arrResultList, Map<String, ResDoctorSchProcess> processMap, Map<String, String> finishPerMap)
			throws Exception {
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setColor(HSSFFont.COLOR_NORMAL);
		font.setBold(true);

		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter2 = wb.createCellStyle(); //居中
		styleCenter2.setAlignment(HorizontalAlignment.CENTER);
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		styleCenter.setFont(font);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setFont(font);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
		stylevwc.setFont(font);
		//列宽自适应
		HSSFRow rowDep = sheet.createRow(0);//第一行
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));//合并单元格
		HSSFCell cellOne = rowDep.createCell(0);
		cellOne.setCellStyle(stylevwc);
		cellOne.setCellValue("轮转科室总览");

		HSSFRow rowTwo = sheet.createRow(1);//第二行
		HSSFCell cellFour = rowTwo.createCell(0);
		cellFour.setCellStyle(styleCenter);
		cellFour.setCellValue("科室名称");
		HSSFCell cellFive = rowTwo.createCell(1);
		cellFive.setCellStyle(styleCenter);
		cellFive.setCellValue("带教老师");
		HSSFCell cellSix = rowTwo.createCell(2);
		cellSix.setCellStyle(styleCenter);
		cellSix.setCellValue("轮转时间");
		HSSFCell cellSeven = rowTwo.createCell(3);
		cellSeven.setCellStyle(styleCenter);
		cellSeven.setCellValue("开始时间");
		HSSFCell cellEight = rowTwo.createCell(4);
		cellEight.setCellStyle(styleCenter);
		cellEight.setCellValue("结束时间");
		HSSFCell cell5 = rowTwo.createCell(5);
		cell5.setCellStyle(styleCenter);
		cell5.setCellValue("数据完成率");
		HSSFCell cell6 = rowTwo.createCell(6);
		cell6.setCellStyle(styleCenter);
		cell6.setCellValue("出科成绩");

		int rowNum = 2;
		String[] dataList = null;
		if (arrResultList != null && !arrResultList.isEmpty()) {
			for (int i = 0; i < arrResultList.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行
				SchArrangeResult result = arrResultList.get(i);
				ResDoctorSchProcess process=processMap.get(result.getResultFlow());
				String teacherName="";
				String schScore="";
				if(process!=null) {
					teacherName = process.getTeacherUserName();
					if(process.getSchScore()!=null)
						schScore=process.getSchScore().toString();
				}

				String per=finishPerMap.get(result.getResultFlow());
				if(StringUtil.isBlank(per)) per="0";
				per=NumTrans.transPercent(per,2)+"%";
				String schMonth=result.getSchMonth();
                if (com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"))) {
                    schMonth = schMonth + com.pinde.core.common.enums.SchUnitEnum.Week.getName();
				}else{
                    schMonth = schMonth + com.pinde.core.common.enums.SchUnitEnum.Month.getName();
				}
				dataList = new String[]{
						result.getStandardDeptName()+"["+result.getSchDeptName()+"]",
						teacherName,
						schMonth,
						result.getSchStartDate(),
						result.getSchEndDate(),
						per,
						schScore
				};
				for (int j = 0; j < 7; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter2);
					cellFirst.setCellValue(dataList[j]);
				}
			}
		}
		String fileName = "轮转总览.xls";
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"),"ISO8859-1" )  + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());

	}

	@Override
	public List<Map<String, Object>> jsresSchDoctorSchProcessEval(Map<String, Object> param) {
		return resDoctorProcessExtMapper.jsresSchDoctorSchProcessEval(param);
	}
	@Override
	public List<Map<String, Object>> findOneProcessEvals(Map<String, Object> param) {
		return resDoctorProcessExtMapper.findOneProcessEvals(param);
	}

	@Override
	public List<ResDoctorSchProcessEval> queryEvalListByFlow() {
		ResDoctorSchProcessEvalExample example = new ResDoctorSchProcessEvalExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("PROCESS_FLOW,START_TIME");
		return evalMapper.selectByExample(example);
	}

	@Override
	public List<ResDoctorSchProcessEval> queryEvalListByProcessFlow(List<String> processFlows) {
		ResDoctorSchProcessEvalExample example = new ResDoctorSchProcessEvalExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowIn(processFlows);
		example.setOrderByClause("PROCESS_FLOW,START_TIME");
		return evalMapper.selectByExample(example);
	}

	@Override
	public int saveForm(Map<String, Object> param) {
		ResDoctorSchProcessEvalWithBLOBs eval= (ResDoctorSchProcessEvalWithBLOBs)param.get("eval");
		if(StringUtil.isNotBlank(eval.getProcessFlow())){
			List<Map<String,Object>> dataList = resDoctorProcessExtMapper.queryProcessEvalData(eval.getProcessFlow());
			eval.setRecordFlow(PkUtil.getUUID());
			eval.setDoctorFlow((String)dataList.get(0).get("userFlow"));
			eval.setDoctorName((String)dataList.get(0).get("userName"));
			eval.setEvalUserFlow((String)dataList.get(0).get("teacherUserFlow"));
			eval.setEvalUserName((String)dataList.get(0).get("teacherUserName"));
			String curTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			eval.setEvalTime(curTime);
			eval.setEvalMonth(eval.getStartTime().substring(0,7));
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(eval.getIsForm())) {
				eval.setFormCfg((String)dataList.get(0).get("formCfg"));
				List<String> scoreIdList = (List<String>)param.get("scoreIdList");
				List<String> scoreList = (List<String>)param.get("scoreList");
				Document dom = DocumentHelper.createDocument();
				Element root = dom.addElement("evalCfg");
				for(int i=0;i<scoreIdList.size();i++){
					Element titleElement = root.addElement("score").addAttribute("id",scoreIdList.get(i));
					titleElement.setText(scoreList.get(i));
				}
				eval.setEvalResult(dom.asXML());
			}else{
                eval.setIsForm(com.pinde.core.common.GlobalConstant.FLAG_N);
			}
			GeneralMethod.setRecordInfo(eval,true);
			return evalMapper.insertSelective(eval);
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public ResDoctorSchProcessEvalWithBLOBs readProcessEvalByFlow(String recordFlow) {
		return evalMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public int saveProcess(ResDoctorSchProcess process) {
		GeneralMethod.setRecordInfo(process, false);
		return resDoctorProcessMapper.updateByPrimaryKeySelective(process);
	}

	@Override
	public List<Map<String, String>> searchDocoutReport(Map<String, Object> paramMap) {
		return resDoctorProcessExtMapper.searchDocoutReport(paramMap);
	}

	@Override
	public List<Map<String, String>> jsresSearchTeacherWorkInfo(String startDate, String endDate, List<String> typeList,SysUser sysUser,String orgFlow,String roleFlow,String orderItem,String sortName) {
		Map<String,Object> map=new HashMap<>();
		map.put("startDate",startDate);
		map.put("endDate",endDate);
		map.put("typeList",typeList);
		map.put("sysUser",sysUser);
		map.put("orgFlow",orgFlow);
		map.put("roleFlow",roleFlow);
		map.put("orderItem",orderItem);
		map.put("sortName",sortName);
		if(StringUtil.isNotBlank(startDate)&&StringUtil.isNotBlank(endDate)){
			String newStartDate=DateUtil.transDateTime(startDate);
			String newEndDate=DateUtil.transDateTime(endDate);
			map.put("newStartDate",newStartDate.substring(0,16));
			map.put("newEndDate",newEndDate.substring(0,16));
			map.put("startDate1",newStartDate.substring(0,10));
			map.put("endDate1",newEndDate.substring(0,10));
		}
		return resDoctorProcessExtMapper.jsresSearchTeacherWorkInfo(map);
	}

	@Override
	public List<TeachingActivityInfo> searchActivityList(TeachingActivityInfo activityInfo) {
		Map<String,String> map=new HashMap<>();
		map.put("deptFlow",activityInfo.getDeptFlow());
		map.put("speakerFlow",activityInfo.getSpeakerFlow());
		map.put("startTime",activityInfo.getStartTime());
		map.put("endTime",activityInfo.getEndTime());
		return resDoctorProcessExtMapper.queryActivityList(map);
	}
}
