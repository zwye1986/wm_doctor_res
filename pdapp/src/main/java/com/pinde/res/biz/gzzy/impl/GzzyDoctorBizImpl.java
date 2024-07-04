package com.pinde.res.biz.gzzy.impl;

import com.pinde.res.biz.gzzy.IGzzyDoctorBiz;
import com.pinde.res.dao.gzzy.ext.GzzyDoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class GzzyDoctorBizImpl implements IGzzyDoctorBiz {
	
	@Autowired
	private GzzyDoctorMapper doctorMapper;

	@Override
	public List<Map<String, Object>> getBigHistorys(String userFlow,String schDeptFlow, int pageIndex, int pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		return doctorMapper.selectBigHistorys(userFlow,schDeptFlow, start, end);
	}

	@Override
	public Map<String, Object> readBigHistory(String userFlow, String schDeptFlow,String dataFlow) {
		return doctorMapper.selectBigHistory(dataFlow);
	}

	@Override
	public void addBigHistory(String userFlow, String schDeptFlow, HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userFlow", userFlow);
		param.put("schDeptFlow", schDeptFlow);
		param.put("RecData1", request.getParameter("RecData1"));
		param.put("RecData2", request.getParameter("RecData2"));
		param.put("RecData3", request.getParameter("RecData3"));
		param.put("RecData4", request.getParameter("RecData4"));
		param.put("RecData5", request.getParameter("RecData5"));
		param.put("RecData6", request.getParameter("RecData6"));
		doctorMapper.insertBigHistory(param);
	}

	@Override
	public void modBigHistory(String userFlow, String schDeptFlow,String dataFlow,HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("dataFlow", dataFlow);
		param.put("RecData1", request.getParameter("RecData1"));
		param.put("RecData2", request.getParameter("RecData2"));
		param.put("RecData3", request.getParameter("RecData3"));
		param.put("RecData4", request.getParameter("RecData4"));
		param.put("RecData5", request.getParameter("RecData5"));
		param.put("RecData6", request.getParameter("RecData6"));
		
		doctorMapper.updateBigHistory(param);
	}

	@Override
	public void delBigHistory(String userFlow, String schDeptFlow, String dataFlow) {
		doctorMapper.deleteBigHistory(dataFlow);
	}

	@Override
	public List<Map<String, Object>> getOutPatients(String userFlow, String schDeptFlow, int pageIndex, int pageSize) {
		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		return doctorMapper.selectOutPatients(userFlow,schDeptFlow, start, end);
	}

	@Override
	public Map<String, Object> readOutPatient(String userFlow, String schDeptFlow, String dataFlow) {
		return doctorMapper.selectOutPatient(dataFlow);
	}

	@Override
	public void addOutPatient(String userFlow, String schDeptFlow, HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userFlow", userFlow);
		param.put("schDeptFlow", schDeptFlow);
		param.put("RecData1", request.getParameter("RecData1"));
		param.put("RecData2", request.getParameter("RecData2"));
		param.put("RecData3", request.getParameter("RecData3"));
		param.put("RecData4", request.getParameter("RecData4"));
		doctorMapper.insertOutPatient(param);
	}

	@Override
	public void modOutPatient(String userFlow, String schDeptFlow, String dataFlow, HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("dataFlow", dataFlow);
		param.put("RecData1", request.getParameter("RecData1"));
		param.put("RecData2", request.getParameter("RecData2"));
		param.put("RecData3", request.getParameter("RecData3"));
		param.put("RecData4", request.getParameter("RecData4"));

		doctorMapper.updateOutPatient(param);
	}

	@Override
	public void delOutPatient(String userFlow, String schDeptFlow, String dataFlow) {
		doctorMapper.deleteOutPatient(dataFlow);
	}

	@Override
	public List<Map<String, Object>> getCaseClassCatas(String userFlow, String schDeptFlow, int pageIndex, int pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		return doctorMapper.selectCaseClassCatas(userFlow,schDeptFlow,start,end);
	}

	@Override
	public List<Map<String, Object>> getCaseClasses(String userFlow, String schDeptFlow, String cataFlow,
			Integer pageIndex, Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
	    return doctorMapper.selectCaseClasses(userFlow,schDeptFlow,cataFlow, start, end);
	}

	@Override
	public String readCaseClassCataName(String cataFlow) {
		return doctorMapper.selectCaseClassCataName(cataFlow);
	}

	@Override
	public Map<String, Object> readCaseClass(String userFlow, String schDeptFlow, String dataFlow) {
		return doctorMapper.selectCaseClass(dataFlow);
	}

	@Override
	public void addCaseClass(String userFlow, String schDeptFlow, String cataFlow, HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userFlow", userFlow);
		param.put("schDeptFlow", schDeptFlow);
		param.put("cataFlow", cataFlow);
		param.put("RecData1", request.getParameter("RecData1"));
		param.put("RecData2", request.getParameter("RecData2"));
		param.put("RecData3", request.getParameter("RecData3"));
		param.put("RecData4", request.getParameter("RecData4"));
		param.put("RecData5", request.getParameter("RecData5"));
		param.put("RecData6", request.getParameter("RecData6"));
		param.put("RecData7", request.getParameter("RecData7"));
		param.put("RecData9", request.getParameter("RecData9"));
		param.put("RecData10", request.getParameter("RecData10"));
		param.put("RecData11", request.getParameter("RecData11"));
		param.put("OtherCaseClass", request.getParameter("OtherCaseClass"));
		doctorMapper.insertCaseClass(param);
	}

	@Override
	public void modCaseClass(String userFlow, String schDeptFlow, String cataFlow, String dataFlow,
			HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("dataFlow", dataFlow);
		param.put("RecData1", request.getParameter("RecData1"));
		param.put("RecData2", request.getParameter("RecData2"));
		param.put("RecData3", request.getParameter("RecData3"));
		param.put("RecData4", request.getParameter("RecData4"));
		param.put("RecData5", request.getParameter("RecData5"));
		param.put("RecData6", request.getParameter("RecData6"));
		param.put("RecData7", request.getParameter("RecData7"));
		param.put("RecData9", request.getParameter("RecData9"));
		param.put("RecData10", request.getParameter("RecData10"));
		param.put("RecData11", request.getParameter("RecData11"));
		param.put("OtherCaseClass", request.getParameter("OtherCaseClass"));
		doctorMapper.updateCaseClass(param);
		
	}

	@Override
	public void delCaseClass(String userFlow, String schDeptFlow, String dataFlow) {
		doctorMapper.deleteCaseClass(dataFlow);
	}

	@Override
	public List<Map<String, Object>> getOperateSkillCatas(String userFlow, String schDeptFlow, Integer pageIndex,
			Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		return doctorMapper.selectOperateSkillCatas(userFlow,schDeptFlow,start,end);
	}
	
	@Override
	public String readOperateSkillCataName(String cataFlow) {
		return doctorMapper.selectOperateSkillCataName(cataFlow);
	}

	@Override
	public List<Map<String, Object>> getOperateSkills(String userFlow, String schDeptFlow, String cataFlow,
			Integer pageIndex, Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
	    return doctorMapper.selectOperateSkills(userFlow,schDeptFlow,cataFlow, start, end);
	}

	@Override
	public Map<String, Object> readOperateSkill(String userFlow, String schDeptFlow, String dataFlow) {
		return doctorMapper.selectOperateSkill(dataFlow);
	}

	@Override
	public void addOperateSkill(String userFlow, String schDeptFlow, String cataFlow, HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userFlow", userFlow);
		param.put("schDeptFlow", schDeptFlow);
		param.put("cataFlow", cataFlow);
		param.put("RecData1", request.getParameter("RecData1"));
		param.put("RecData2", request.getParameter("RecData2"));
		param.put("RecData3", request.getParameter("RecData3"));
		param.put("RecData4", request.getParameter("RecData4"));
		param.put("RecData5", request.getParameter("RecData5"));
		param.put("RecData6", request.getParameter("RecData6"));
		param.put("RecData7", request.getParameter("RecData7"));
		param.put("RecData8", request.getParameter("RecData8"));
		param.put("RecData9", request.getParameter("RecData9"));
		param.put("RecData10", request.getParameter("RecData10"));
		param.put("RecData11", request.getParameter("RecData11"));
		param.put("RecData12", request.getParameter("RecData12"));
		param.put("RecData13", request.getParameter("RecData13"));
		param.put("OtherSkill", request.getParameter("OtherSkill"));
		doctorMapper.insertOperateSkill(param);
	}

	@Override
	public void modOperateSkill(String userFlow, String schDeptFlow, String cataFlow, String dataFlow,
			HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("dataFlow", dataFlow);
		param.put("RecData1", request.getParameter("RecData1"));
		param.put("RecData2", request.getParameter("RecData2"));
		param.put("RecData3", request.getParameter("RecData3"));
		param.put("RecData4", request.getParameter("RecData4"));
		param.put("RecData5", request.getParameter("RecData5"));
		param.put("RecData6", request.getParameter("RecData6"));
		param.put("RecData7", request.getParameter("RecData7"));
		param.put("RecData8", request.getParameter("RecData8"));
		param.put("RecData9", request.getParameter("RecData9"));
		param.put("RecData10", request.getParameter("RecData10"));
		param.put("RecData11", request.getParameter("RecData11"));
		param.put("RecData12", request.getParameter("RecData12"));
		param.put("RecData13", request.getParameter("RecData13"));
		param.put("OtherSkill", request.getParameter("OtherSkill"));
		
		doctorMapper.updateOperateSkill(param);
	}

	@Override
	public void delOperateSkill(String userFlow, String schDeptFlow, String dataFlow) {
		doctorMapper.deleteOperateSkill(dataFlow);
	}

	@Override
	public List<Map<String, Object>> getPOSSkillCatas(String userFlow, String schDeptFlow, Integer pageIndex,
			Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		return doctorMapper.selectPOSSkillCatas(userFlow,schDeptFlow,start,end);
	}

	@Override
	public String readPOSSkillCataName(String cataFlow) {
		return doctorMapper.selectPOSSkillCataName(cataFlow);
	}

	@Override
	public List<Map<String, Object>> getPOSSkills(String userFlow, String schDeptFlow, String cataFlow,
			Integer pageIndex, Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
	    return doctorMapper.selectPOSSkills(userFlow,schDeptFlow,cataFlow, start, end);
	}

	@Override
	public Map<String, Object> readPOSSkill(String userFlow, String schDeptFlow, String dataFlow) {
		return doctorMapper.selectPOSSkill(dataFlow);
	}

	@Override
	public void addPOSSkill(String userFlow, String schDeptFlow, String cataFlow, HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userFlow", userFlow);
		param.put("schDeptFlow", schDeptFlow);
		param.put("cataFlow", cataFlow);
		param.put("RecData1", request.getParameter("RecData1"));
		param.put("RecData2", request.getParameter("RecData2"));
		param.put("RecData3", request.getParameter("RecData3"));
		param.put("RecData4", request.getParameter("RecData4"));
		param.put("RecData5", request.getParameter("RecData5"));
		param.put("RecData6", request.getParameter("RecData6"));
		param.put("RecData7", request.getParameter("RecData7"));
		param.put("RecData8", request.getParameter("RecData8"));
		param.put("RecData9", request.getParameter("RecData9"));
		param.put("RecData10", request.getParameter("RecData10"));
		param.put("RecData11", request.getParameter("RecData11"));
		param.put("RecData12", request.getParameter("RecData12"));
		param.put("RecData13", request.getParameter("RecData13"));
		param.put("RecData14", request.getParameter("RecData14"));
		param.put("RecData15", request.getParameter("RecData15"));
		param.put("OtherPOSSkill", request.getParameter("OtherPOSSkill"));
		doctorMapper.insertPOSSkill(param);
	}

	@Override
	public void modPOSSkill(String userFlow, String schDeptFlow, String cataFlow, String dataFlow,
			HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("dataFlow", dataFlow);
		param.put("RecData1", request.getParameter("RecData1"));
		param.put("RecData2", request.getParameter("RecData2"));
		param.put("RecData3", request.getParameter("RecData3"));
		param.put("RecData4", request.getParameter("RecData4"));
		param.put("RecData5", request.getParameter("RecData5"));
		param.put("RecData6", request.getParameter("RecData6"));
		param.put("RecData7", request.getParameter("RecData7"));
		param.put("RecData8", request.getParameter("RecData8"));
		param.put("RecData9", request.getParameter("RecData9"));
		param.put("RecData10", request.getParameter("RecData10"));
		param.put("RecData11", request.getParameter("RecData11"));
		param.put("RecData12", request.getParameter("RecData12"));
		param.put("RecData13", request.getParameter("RecData13"));
		param.put("RecData14", request.getParameter("RecData14"));
		param.put("RecData15", request.getParameter("RecData15"));
		param.put("OtherPOSSkill", request.getParameter("OtherPOSSkill"));
		doctorMapper.updatePOSSkill(param);
		
	}

	@Override
	public void delPOSSkill(String userFlow, String schDeptFlow, String dataFlow) {
		doctorMapper.deletePOSSkill(dataFlow);
	}

	@Override
	public Map<String, Object> readOutSecBrief(String schDeptFlow) {
		return doctorMapper.selectOutSecBrief(schDeptFlow);
	}

	@Override
	public void addOutSecBrief(String userFlow, String schDeptFlow, HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userFlow", userFlow);
		param.put("schDeptFlow", schDeptFlow);
		param.put("BriefRequrie", request.getParameter("BriefRequrie"));
		param.put("GainsDefect", request.getParameter("GainsDefect"));
		doctorMapper.insertOutSecBrief(param);
		
	}

	@Override
	public void modOutSecBrief(String userFlow, String schDeptFlow, HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userFlow", userFlow);
		param.put("schDeptFlow", schDeptFlow);
		param.put("BriefRequrie", request.getParameter("BriefRequrie"));
		param.put("GainsDefect", request.getParameter("GainsDefect"));
		doctorMapper.updateOutSecBrief(param);
	}
	
}
