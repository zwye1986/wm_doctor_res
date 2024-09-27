package com.pinde.res.biz.osca.impl;


import com.pinde.res.biz.osca.IOscaAdminAppBiz;
import com.pinde.res.dao.osca.ext.OscaAppMapper;
import com.pinde.res.model.osca.mo.*;
import com.pinde.sci.model.mo.OscaSkillDocTeaScore;
import com.pinde.sci.model.mo.OscaSkillsAssessment;
import com.pinde.sci.model.mo.SysDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class OscaAdminAppBizmpl implements IOscaAdminAppBiz {

	@Autowired
	private OscaAppMapper oscaAppMapper;

	@Override
	public List<OscaSkillsAssessment> getSelectDateSkills(String orgFlow, String selectDate) {
		return oscaAppMapper.getSelectDateSkills(orgFlow,selectDate);
	}

	@Override
	public List<OscaDoctorSkillsExt> getSelectDateDocSkills(String orgFlow, String selectDate, String speId) {
		return oscaAppMapper.getSelectDateDocSkills(orgFlow,selectDate,speId);
	}
	@Override
	public List<OscaSkillsRoomExt> getSelectDateSkillRooms(String orgFlow, String selectDate) {
		return oscaAppMapper.getSelectDateSkillRooms(orgFlow,selectDate);
	}
	@Override
	public List<OscaSkillsPartnerExt> getSelectDateSkillPartners(String orgFlow, String selectDate) {
		return oscaAppMapper.getSelectDateSkillPartners(orgFlow,selectDate);
	}

	@Override
	public List<OscaDoctorSkillsExt> getSelectDateDocSignIns(String orgFlow, String selectDate,String speId) {
		return oscaAppMapper.getSelectDateDocSignIns(orgFlow,selectDate,speId);
	}

	@Override
	public List<OscaSkillsTimeExt> getSelectDateSkillTimes(String orgFlow, String selectDate, String speId) {
		return oscaAppMapper.getSelectDateSkillTimes(orgFlow,selectDate,speId);
	}

	@Override
	public List<OscaDoctorSkillsExt> getSelectDateAudited(String orgFlow, String selectDate, String speId) {
		return oscaAppMapper.getSelectDateAudited(orgFlow,selectDate,speId);
	}

	@Override
	public List<Map<String, String>> getTeaList(Map<String, Object> paramMap) {
		return oscaAppMapper.getTeaList(paramMap);
	}
	@Override
	public List<Map<String, String>> getNoSiginList(Map<String, Object> paramMap) {
		return oscaAppMapper.getNoSiginList(paramMap);
	}

	@Override
	public List<OscaDoctorExamBean> getOscaDoctorExamList(Map<String, Object> paramMap) {
		return oscaAppMapper.getOscaDoctorExamList(paramMap);
	}

	@Override
	public Map<String, String> getSelectDateMaxExamTime(String orgFlow, String selectDate, String speId) {
		return oscaAppMapper.getSelectDateMaxExamTime(orgFlow,selectDate,speId);
	}

	@Override
	public Double getDoctorExamScore(String clinicalFlow, String doctorFlow) {
		return oscaAppMapper.getDoctorExamScore(clinicalFlow, doctorFlow) ;
	}

	@Override
	public List<OscaSkillDocTeaScore> getOscaSkillDocTeaScores(String doctorFlow, String clinicalFlow, String stationFlow) {
		return oscaAppMapper.getOscaSkillDocTeaScores(doctorFlow,clinicalFlow,stationFlow);
	}

	@Override
	public List<SysDict> getOrgRoomList(Map<String, Object> paramMap) {
		return oscaAppMapper.getOrgRoomList(paramMap);
	}

	@Override
	public List<Map<String, String>> getRoomInfoByRoomFlow(Map<String, Object> paramMap) {
		return oscaAppMapper.getRoomInfoByRoomFlow(paramMap);
	}

	@Override
	public List<Map<String, String>> getRoomPartnerExam(Map<String, Object> paramMap) {

		return oscaAppMapper.getRoomPartnerExam(paramMap);
	}

	@Override
	public Integer getRoomPartnerNotExam(Map<String, Object> paramMap) {
		return oscaAppMapper.getRoomPartnerNotExam(paramMap);
	}

	@Override
	public List<Map<String, Object>> getCalendarInfo(String orgFlow, String month) {
		return oscaAppMapper.getCalendarInfo(orgFlow,month);
	}
	@Override
	public List<Map<String, Object>> getMonthInfo(String orgFlow, String year) {
		return oscaAppMapper.getMonthInfo(orgFlow,year);
	}

	@Override
	public Map<String, Object> getTypeInfoByMonth(String orgFlow, String month) {
		Map<String, Object> typeInfo=new HashMap<>();
		List<Map<String, Object>> list= oscaAppMapper.getTypeInfoByMonth(orgFlow,month);
		if(list!=null)
		{
			for(Map<String, Object> m:list)
			{
				typeInfo.put(String.valueOf(m.get("IS_LOCAL")),m.get("QTY"));
			}
		}
		return typeInfo;
	}
	@Override
	public Map<String, Object> getTypeInfoByYear(String orgFlow, String year) {
		Map<String, Object> typeInfo=new HashMap<>();
		List<Map<String, Object>> list= oscaAppMapper.getTypeInfoByYear(orgFlow,year);
		if(list!=null)
		{
			for(Map<String, Object> m:list)
			{
				typeInfo.put(String.valueOf(m.get("IS_LOCAL")),m.get("QTY"));
			}
		}
		return typeInfo;
	}

	@Override
	public List<SysDict> getOrgRooms(String orgFlow) {
		return oscaAppMapper.getOrgRooms(orgFlow);
	}
}
  