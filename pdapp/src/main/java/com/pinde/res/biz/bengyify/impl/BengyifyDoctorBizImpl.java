package com.pinde.res.biz.bengyify.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinde.res.biz.bengyify.IBengyifyDoctorBiz;
import com.pinde.res.dao.bengyify.ext.BengyifyDoctorMapper;

@Service
@Transactional(rollbackFor=Exception.class)
public class BengyifyDoctorBizImpl implements IBengyifyDoctorBiz{
	
	@Autowired
	private BengyifyDoctorMapper bDoctorMapper;

	@Override
	public List<Map<String, Object>> getBigHistorys(String userFlow,String schDeptFlow, int pageIndex, int pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		return bDoctorMapper.selectBigHistorys(userFlow,schDeptFlow, start, end);
	}

	@Override
	public Map<String, Object> readBigHistory(String userFlow, String schDeptFlow,String dataFlow) {
		return bDoctorMapper.selectBigHistory(dataFlow);
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
		bDoctorMapper.insertBigHistory(param);
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
		
		bDoctorMapper.updateBigHistory(param);
	}

	@Override
	public void delBigHistory(String userFlow, String schDeptFlow, String dataFlow) {
		bDoctorMapper.deleteBigHistory(dataFlow);
	}

	@Override
	public List<Map<String, Object>> getOutPatients(String userFlow, String schDeptFlow, int pageIndex, int pageSize) {
		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		return bDoctorMapper.selectOutPatients(userFlow,schDeptFlow, start, end);
	}

	@Override
	public Map<String, Object> readOutPatient(String userFlow, String schDeptFlow, String dataFlow) {
		return bDoctorMapper.selectOutPatient(dataFlow);
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
		bDoctorMapper.insertOutPatient(param);
	}

	@Override
	public void modOutPatient(String userFlow, String schDeptFlow, String dataFlow, HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("dataFlow", dataFlow);
		param.put("RecData1", request.getParameter("RecData1"));
		param.put("RecData2", request.getParameter("RecData2"));
		param.put("RecData3", request.getParameter("RecData3"));
		param.put("RecData4", request.getParameter("RecData4"));

		bDoctorMapper.updateOutPatient(param);
	}

	@Override
	public void delOutPatient(String userFlow, String schDeptFlow, String dataFlow) {
		bDoctorMapper.deleteOutPatient(dataFlow);
	}

	@Override
	public List<Map<String, Object>> getCaseClassCatas(String userFlow, String schDeptFlow, int pageIndex, int pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		return bDoctorMapper.selectCaseClassCatas(userFlow,schDeptFlow,start,end);
	}

	@Override
	public List<Map<String, Object>> getCaseClasses(String userFlow, String schDeptFlow, String cataFlow,
			Integer pageIndex, Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
	    return bDoctorMapper.selectCaseClasses(userFlow,schDeptFlow,cataFlow, start, end);
	}

	@Override
	public String readCaseClassCataName(String cataFlow) {
		return bDoctorMapper.selectCaseClassCataName(cataFlow);
	}

	@Override
	public Map<String, Object> readCaseClass(String userFlow, String schDeptFlow, String dataFlow) {
		return bDoctorMapper.selectCaseClass(dataFlow);
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
		param.put("OtherCaseClass", request.getParameter("OtherCaseClass"));
		bDoctorMapper.insertCaseClass(param);
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
		param.put("OtherCaseClass", request.getParameter("OtherCaseClass"));
		bDoctorMapper.updateCaseClass(param);
		
	}

	@Override
	public void delCaseClass(String userFlow, String schDeptFlow, String dataFlow) {
		bDoctorMapper.deleteCaseClass(dataFlow);
	}

	@Override
	public List<Map<String, Object>> getOperateSkillCatas(String userFlow, String schDeptFlow, Integer pageIndex,
			Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		return bDoctorMapper.selectOperateSkillCatas(userFlow,schDeptFlow,start,end);
	}
	
	@Override
	public String readOperateSkillCataName(String cataFlow) {
		return bDoctorMapper.selectOperateSkillCataName(cataFlow);
	}

	@Override
	public List<Map<String, Object>> getOperateSkills(String userFlow, String schDeptFlow, String cataFlow,
			Integer pageIndex, Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
	    return bDoctorMapper.selectOperateSkills(userFlow,schDeptFlow,cataFlow, start, end);
	}

	@Override
	public Map<String, Object> readOperateSkill(String userFlow, String schDeptFlow, String dataFlow) {
		return bDoctorMapper.selectOperateSkill(dataFlow);
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
		param.put("OtherSkill", request.getParameter("OtherSkill"));
		bDoctorMapper.insertOperateSkill(param);
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
		param.put("OtherSkill", request.getParameter("OtherSkill"));
		
		bDoctorMapper.updateOperateSkill(param);
	}

	@Override
	public void delOperateSkill(String userFlow, String schDeptFlow, String dataFlow) {
		bDoctorMapper.deleteOperateSkill(dataFlow);
	}

	@Override
	public List<Map<String, Object>> getPOSSkillCatas(String userFlow, String schDeptFlow, Integer pageIndex,
			Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		return bDoctorMapper.selectPOSSkillCatas(userFlow,schDeptFlow,start,end);
	}

	@Override
	public String readPOSSkillCataName(String cataFlow) {
		return bDoctorMapper.selectPOSSkillCataName(cataFlow);
	}

	@Override
	public List<Map<String, Object>> getPOSSkills(String userFlow, String schDeptFlow, String cataFlow,
			Integer pageIndex, Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
	    return bDoctorMapper.selectPOSSkills(userFlow,schDeptFlow,cataFlow, start, end);
	}

	@Override
	public Map<String, Object> readPOSSkill(String userFlow, String schDeptFlow, String dataFlow) {
		return bDoctorMapper.selectPOSSkill(dataFlow);
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
		param.put("OtherPOSSkill", request.getParameter("OtherPOSSkill"));
		bDoctorMapper.insertPOSSkill(param);
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
		param.put("OtherPOSSkill", request.getParameter("OtherPOSSkill"));
		bDoctorMapper.updatePOSSkill(param);
		
	}

	@Override
	public void delPOSSkill(String userFlow, String schDeptFlow, String dataFlow) {
		bDoctorMapper.deletePOSSkill(dataFlow);
	}

	@Override
	public Map<String, Object> readOutSecBrief(String schDeptFlow) {
		return bDoctorMapper.selectOutSecBrief(schDeptFlow);
	}

	@Override
	public void addOutSecBrief(String userFlow, String schDeptFlow, HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userFlow", userFlow);
		param.put("schDeptFlow", schDeptFlow);
		param.put("BriefRequrie", request.getParameter("BriefRequrie"));
		param.put("GainsDefect", request.getParameter("GainsDefect"));
		bDoctorMapper.insertOutSecBrief(param);
		
	}

	@Override
	public void modOutSecBrief(String userFlow, String schDeptFlow, HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userFlow", userFlow);
		param.put("schDeptFlow", schDeptFlow);
		param.put("BriefRequrie", request.getParameter("BriefRequrie"));
		param.put("GainsDefect", request.getParameter("GainsDefect"));
		bDoctorMapper.updateOutSecBrief(param);
	}
	
}
