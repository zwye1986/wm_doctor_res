package com.pinde.res.biz.osca.impl;


import com.pinde.core.common.sci.dao.*;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.osca.IOscaStudentBiz;
import com.pinde.res.dao.jswjw.ext.OscaDoctorAssessmentExtMapper;
import com.pinde.res.dao.jswjw.ext.OscaSkillRoomDocExtMapper;
import com.pinde.res.dao.jswjw.ext.OscaSkillRoomExtMapper;
import com.pinde.res.dao.jswjw.ext.OscaSubjectMainExtMapper;
import com.pinde.res.model.jswjw.mo.OscaSkillRoomExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class OscaStudentBizImpl implements IOscaStudentBiz{

	@Autowired
	private  OscaDoctorAssessmentMapper oscaDoctorAssessmentMapper;
	@Autowired
	private JsresDoctorPaperMapper paperMapper;
	@Autowired
	private JsresDoctorParticipationMapper partMapper;
	@Autowired
	private  OscaDoctorAssessmentExtMapper oscaDoctorAssessmentExtMapper;
	@Autowired
	private  OscaSkillsAssessmentMapper skillsAssessmentMapper;
	@Autowired
	private  OscaSubjectMainExtMapper oscaSubjectMainExtMapper;
	@Autowired
	private OscaSubjectMainMapper oscaSubjectMainMapper;
	@Autowired
	private  OscaSubjectStationMapper stationMapper;
	@Autowired
	private OscaSkillRoomMapper roomMapper;

	@Autowired
	private  OscaSkillRoomExtMapper roomExtMapper;

	@Autowired
	private  OscaSkillRoomDocExtMapper roomDocExtMapper;
	@Autowired
	private OscaSkillRoomDocMapper roomDocMapper;
	@Autowired
	private  ResErrorSchNoticeMapper resErrorSchNoticeMapper;
	@Autowired
	private  ResDoctorRecruitMapper recruitMapper;

	@Override
	public OscaSkillsAssessment getAuditOscaInfo(String userFlow) {
		List<OscaSkillsAssessment> list=oscaDoctorAssessmentExtMapper.getAuditOscaInfo(userFlow);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public OscaDoctorAssessment getAuditOscaDocInfo(String userFlow) {

		List<OscaDoctorAssessment> list=oscaDoctorAssessmentExtMapper.getAuditOscaDocInfo(userFlow);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public OscaSubjectMain getOscaSubjectMain(String subjectFlow) {

		return oscaSubjectMainMapper.selectByPrimaryKey(subjectFlow);
	}

	@Override
	public List<OscaSubjectStation> getOscaSubjectStations(String subjectFlow) {
		OscaSubjectStationExample station=new OscaSubjectStationExample();
		OscaSubjectStationExample.Criteria criteria=station.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
		station.setOrderByClause(" ORDINAL ASC ");
		return stationMapper.selectByExample(station);
	}

	@Override
	public List<OscaSkillRoom> getRooms(String stationFlow, String clinicalFlow) {
		OscaSkillRoomExample station=new OscaSkillRoomExample();
		OscaSkillRoomExample.Criteria criteria=station.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andClinicalFlowEqualTo(clinicalFlow)
		.andStationFlowEqualTo(stationFlow);
		station.setOrderByClause(" ORDINAL ASC ");
		return roomMapper.selectByExample(station);
	}

	@Override
	public OscaSkillRoomExt getDocRoom(String userFlow, String stationFlow, String clinicalFlow) {
		Map<String,Object>param=new HashMap<>();
		param.put("userFlow",userFlow);
		param.put("stationFlow",stationFlow);
		param.put("clinicalFlow",clinicalFlow);
		return roomExtMapper.getDocRoom(param);
	}

	@Override
	public OscaSkillRoomExt getStationBestRoom(String stationFlow, String clinicalFlow) {
		Map<String,Object>param=new HashMap<>();
		param.put("stationFlow",stationFlow);
		param.put("clinicalFlow",clinicalFlow);
		return roomExtMapper.getStationBestRoom(param);
	}

	@Override
	public int checkIsWait(Map<String, Object> param) {
		return roomExtMapper.checkIsWait(param);
	}

	@Override
	public int checkIsAssess(Map<String, Object> param) {
		return roomExtMapper.checkIsAssess(param);
	}

	@Override
	public OscaSkillRoom getRoomByFlow(String roomRecordFlow) {
		return roomMapper.selectByPrimaryKey(roomRecordFlow);
	}

	@Override
	public OscaSkillsAssessment getOscaSkillsAssessmentByFlow(String clinicalFlow) {
		return skillsAssessmentMapper.selectByPrimaryKey(clinicalFlow);
	}

	@Override
	public OscaSubjectStation getOscaSubjectStationsByFlow(String stationFlow) {
		return stationMapper.selectByPrimaryKey(stationFlow);
	}

	@Override
	public OscaSkillRoomDoc getOscaSkillRoomDocByDoc(Map<String, Object> param) {
		return roomDocExtMapper.getOscaSkillRoomDocByDoc(param);
	}

	@Override
	public int updateOscaSkillRoomDoc(OscaSkillRoomDoc docStation, SysUser user) {
		if(docStation!=null){
			if(StringUtil.isNotBlank(docStation.getDocRoomFlow())){//修改
                docStation.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				docStation.setModifyUserFlow(user.getUserFlow());
				docStation.setModifyTime(DateUtil.getCurrDateTime());
				return this.roomDocMapper.updateByPrimaryKeySelective(docStation);
			}else{//新增
				docStation.setDocRoomFlow(PkUtil.getUUID());
                docStation.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				docStation.setCreateUserFlow(user.getUserFlow());
				docStation.setCreateTime(DateUtil.getCurrDateTime());
				docStation.setModifyUserFlow(user.getUserFlow());
				docStation.setModifyTime(DateUtil.getCurrDateTime());
				return this.roomDocMapper.insertSelective(docStation);
			}
		}
		return 0;
	}

	@Override
	public List<OscaSkillRoomExt> getStationAllRoom(Map<String, Object> param) {

		return roomExtMapper.getStationAllRoom(param);
	}

	@Override
	public List<OscaSkillRoomDoc> getDocAllStation(Map<String, Object> param) {
		OscaSkillRoomDocExample example=new OscaSkillRoomDocExample();
		OscaSkillRoomDocExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		String userFlow= (String) param.get("userFlow");
		String clinicalFlow= (String) param.get("clinicalFlow");
		if(StringUtil.isNotBlank(userFlow)){
			criteria.andDoctorFlowEqualTo(userFlow);
		}
		if(StringUtil.isNotBlank(clinicalFlow)){
			criteria.andClinicalFlowEqualTo(clinicalFlow);
		}

		return roomDocMapper.selectByExample(example);
	}

	@Override
	public OscaDoctorAssessment getOscaDoctorAssessment(String userFlow, String clinicalFlow) {
		OscaDoctorAssessmentExample example=new OscaDoctorAssessmentExample();
		OscaDoctorAssessmentExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andClinicalFlowEqualTo(clinicalFlow)
				.andDoctorFlowEqualTo(userFlow);
		List<OscaDoctorAssessment>list= oscaDoctorAssessmentMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int editOscaDoctorAssessment(OscaDoctorAssessment oscaDoctorAssessment, SysUser user) {
			if(oscaDoctorAssessment!=null){
				if(StringUtil.isNotBlank(oscaDoctorAssessment.getRecordFlow())){//修改
                    oscaDoctorAssessment.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
					oscaDoctorAssessment.setModifyUserFlow(user.getUserFlow());
					oscaDoctorAssessment.setModifyTime(DateUtil.getCurrDateTime());
					return this.oscaDoctorAssessmentMapper.updateByPrimaryKeySelective(oscaDoctorAssessment);
				}else{//新增
					oscaDoctorAssessment.setRecordFlow(PkUtil.getUUID());
                    oscaDoctorAssessment.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
					oscaDoctorAssessment.setCreateUserFlow(user.getUserFlow());
					oscaDoctorAssessment.setCreateTime(DateUtil.getCurrDateTime());
					oscaDoctorAssessment.setModifyUserFlow(user.getUserFlow());
					oscaDoctorAssessment.setModifyTime(DateUtil.getCurrDateTime());
					return this.oscaDoctorAssessmentMapper.insertSelective(oscaDoctorAssessment);
				}
			}
			return 0;
	}

}
  