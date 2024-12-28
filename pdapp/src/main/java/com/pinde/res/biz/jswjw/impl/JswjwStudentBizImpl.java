package com.pinde.res.biz.jswjw.impl;


import com.pinde.core.common.sci.dao.*;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.IJswjwStudentBiz;
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
public class JswjwStudentBizImpl implements IJswjwStudentBiz{

	@Autowired
	private  OscaDoctorAssessmentMapper oscaDoctorAssessmentMapper;
	@Autowired
	private JsresDoctorPaperMapper paperMapper;
	@Autowired
	private JsresDoctorParticipationMapper partMapper;
	@Autowired
	private  OscaDoctorAssessmentExtMapper oscaDoctorAssessmentExtMapper;
	@Autowired
	private OscaSkillsAssessmentMapper skillsAssessmentMapper;
	@Autowired
	private  OscaSubjectMainExtMapper oscaSubjectMainExtMapper;
	@Autowired
	private  OscaSubjectMainMapper oscaSubjectMainMapper;
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

	@Override
	public int editJsresDoctorPaper(JsresDoctorPaper paper, SysUser user) {
		if(paper!=null){
			if(StringUtil.isNotBlank(paper.getRecordFlow())){//修改
                paper.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				paper.setModifyUserFlow(user.getUserFlow());
				paper.setModifyTime(DateUtil.getCurrDateTime());
				return this.paperMapper.updateByPrimaryKeySelective(paper);
			}else{//新增
				paper.setRecordFlow(PkUtil.getUUID());
                paper.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				paper.setCreateUserFlow(user.getUserFlow());
				paper.setCreateTime(DateUtil.getCurrDateTime());
				paper.setModifyUserFlow(user.getUserFlow());
				paper.setModifyTime(DateUtil.getCurrDateTime());
				return this.paperMapper.insertSelective(paper);
			}
		}
		return 0;
	}

	@Override
	public int editJsresDoctorPart(JsresDoctorParticipation part, SysUser user) {
		if(part!=null){
			if(StringUtil.isNotBlank(part.getRecordFlow())){//修改
                part.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				part.setModifyUserFlow(user.getUserFlow());
				part.setModifyTime(DateUtil.getCurrDateTime());
				return this.partMapper.updateByPrimaryKeySelective(part);
			}else{//新增
				part.setRecordFlow(PkUtil.getUUID());
                part.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				part.setCreateUserFlow(user.getUserFlow());
				part.setCreateTime(DateUtil.getCurrDateTime());
				part.setModifyUserFlow(user.getUserFlow());
				part.setModifyTime(DateUtil.getCurrDateTime());
				return this.partMapper.insertSelective(part);
			}
		}
		return 0;
	}

	@Override
	public JsresDoctorPaper readJsresDoctorPaperByFlow(String recordFlow) {
		return paperMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public JsresDoctorParticipation readJsresDoctorJsresDoctorParticipationByFlow(String recordFlow) {
		return partMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public List<JsresDoctorPaper> readJsresDoctorPaperByDoctorFlow(String userFlow) {
		JsresDoctorPaperExample example=new JsresDoctorPaperExample();
		JsresDoctorPaperExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(userFlow);
		example.setOrderByClause("create_time desc");
		return paperMapper.selectByExample(example);
	}

	@Override
	public List<JsresDoctorParticipation> readJsresDoctorJsresDoctorParticipationByDoctorFlow(String userFlow) {
		JsresDoctorParticipationExample example=new JsresDoctorParticipationExample();
		JsresDoctorParticipationExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(userFlow);
		example.setOrderByClause("create_time desc");
		return partMapper.selectByExample(example);
	}

	@Override
	public void deleteJsresDoctorPaperByFlow(String recordFlow) {
		paperMapper.deleteByPrimaryKey(recordFlow);
	}

	@Override
	public void deleteJsresDoctorParticipationByFlow(String recordFlow) {
		partMapper.deleteByPrimaryKey(recordFlow);
	}

	@Override
	public List<ResErrorSchNotice> getResErrorNotices(Map<String, Object> param) {
		if(StringUtil.isNotBlank((String) param.get("userFlow"))) {
			ResErrorSchNoticeExample example = new ResErrorSchNoticeExample();
            ResErrorSchNoticeExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			criteria.andUserFlowEqualTo((String) param.get("userFlow"));
			if(StringUtil.isNotBlank((String) param.get("statusId"))) {
				criteria.andStatusIdEqualTo((String) param.get("statusId"));
			}
			example.setOrderByClause("STATUS_ID DESC,CREATE_TIME DESC");
			return  resErrorSchNoticeMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public ResErrorSchNotice readErrorSchNotice(String recordFlow) {
		return resErrorSchNoticeMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public int editErrorNotice(ResErrorSchNotice notice, SysUser user) {
		if(notice!=null){
			if(StringUtil.isNotBlank(notice.getRecordFlow())){//修改
                notice.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				notice.setModifyUserFlow(user.getUserFlow());
				notice.setModifyTime(DateUtil.getCurrDateTime());
				return this.resErrorSchNoticeMapper.updateByPrimaryKeySelective(notice);
			}else{//新增
				notice.setRecordFlow(PkUtil.getUUID());
                notice.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				notice.setCreateUserFlow(user.getUserFlow());
				notice.setCreateTime(DateUtil.getCurrDateTime());
				notice.setModifyUserFlow(user.getUserFlow());
				notice.setModifyTime(DateUtil.getCurrDateTime());
				return this.resErrorSchNoticeMapper.insertSelective(notice);
			}
		}
		return 0;
	}

	@Override
    public List<com.pinde.core.model.ResDoctorRecruit> findCertificates(String userFlow) {
		if(StringUtil.isNotBlank(userFlow))
		{
			ResDoctorRecruitExample example=new ResDoctorRecruitExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(userFlow)
					.andGraduationCertificateNoIsNotNull();
			example.setOrderByClause("session_number desc");
			return  recruitMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public ResDoctorRecruit readRecruit(String recordFlow) {
		return recruitMapper.selectByPrimaryKey(recordFlow);
	}

	/**
	 * @param userFlow
	 * @param statusId
	 * @Department：研发部
	 * @Description 查询消息条数
	 * @Author fengxf
	 * @Date 2022/3/3
	 */
	@Override
	public int countResErrorNotices(String userFlow, String statusId) {
		if(StringUtil.isBlank(userFlow)){
			return 0;
		}
		ResErrorSchNoticeExample example = new ResErrorSchNoticeExample();
        ResErrorSchNoticeExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserFlowEqualTo(userFlow);
		if(StringUtil.isNotBlank(statusId)) {
			criteria.andStatusIdEqualTo(statusId);
		}
		example.setOrderByClause("STATUS_ID DESC,CREATE_TIME DESC");
		return resErrorSchNoticeMapper.countByExample(example);
	}
}
  